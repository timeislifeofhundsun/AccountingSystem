package com.hundsun.accountingsystem.TJj.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TQsbVO;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.service.TZqxxService;
import com.hundsun.accountingsystem.TJj.service.TjjSgService;

@RestController
public class TjjSgController {

	@Autowired
	TjjSgService tjjSgServiceImpl;
	
	@Autowired
	TZqxxService tzqxxServiceImpl;
	
	private double yhck;
	private double zqqsk;
	private String yhckid;
	private String zqqskid;
	
	@GetMapping("/TQsb")
	public String findList(int page,int limit) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里不按账套进行区分）
		 * */
		List<TQsb> findList = tjjSgServiceImpl.selectByPage(page,limit);
		int count = tjjSgServiceImpl.getCounts();
		TQsbVO layuiJson = new TQsbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/findTQsbByZqdm")
	public String findList(int page,int limit,int ztbh) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里按账套进行区分）
		 * */
		List<TQsb> findList = tjjSgServiceImpl.selectByPageAndZtbh(page,limit,ztbh);
		int count = tjjSgServiceImpl.getCountsByZtbh(ztbh);
		TQsbVO layuiJson = new TQsbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/TQsbByDate")
	public String findListByDate(int page,int limit,@RequestParam(value = "date",required = true) String date) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里按日期进行区分）
		 * */
		List<TQsb> findList = tjjSgServiceImpl.selectByPageAndDate(page,limit,date);
		int count = tjjSgServiceImpl.getCountsByDate(date);
		TQsbVO layuiJson = new TQsbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@PostMapping("/TQsb")
	public String insertQsb(@RequestParam(value = "Sgsq",required = true) String data) {	
		/*
		 * 1.将页面传递过来的申购申请信息进行对象转换
		 * 2.查询出持仓余额表中的银行存款科目（100201 ）余额以及证券清算款余额（1133）
		 * 3.补充业务类别(根据证券代码去证券信息表里面查出该证券到底是货币基金还是非货币基金)
		 * 4.补充买卖方向、证券清算款、extendc（401代表手工录入的基金申购数据）
		 * 5.将信息插入到清算表中
		 * 6.将信息同步插入到持仓余额表
		 * */
				
		//转换成bean对象
		TQsb tqsb = JSON.parseObject(data,TQsb.class);

		//查询持仓余额表
		selectCcyebAmount(tqsb.getZtbh());
		
		//如果银行存款和证券清算款的和小于申购金额，则申购失败
		if((yhck)<tqsb.getAmount()) {
			return "银行存款不足";
		}
		
		//根据证券代码去证券信息表中查出证券信息，根据fxfs判断基金类别，并补充业务类别
		List<TZqxx> list = tzqxxServiceImpl.findByZqdm(tqsb.getZqcode());
		if(list!=null && list.size()==1) {
			int flag = list.get(0).getFxfs();
			if(flag==1) {
				tqsb.setYwlb(4201);
			}else if(flag==2){
				tqsb.setYwlb(4101);
			}
		}
		//补充买卖方向、证券清算款、extendc
		tqsb.setBs("B");
		tqsb.setZqqsk(tqsb.getAmount());
		tqsb.setExtendc("401");
		
		//插入到清算表中
		try {
			tjjSgServiceImpl.insertIntoQsb(tqsb);
		} catch (Exception e) {
			return "插入清算库中出错了";
		}
		
		//同步到持仓余额表中（银行存款科目减少（100201 ），证券清算款科目增加（1133））		
		double money = tqsb.getAmount();
		try {
			updateCcyeb(tqsb.getZtbh(),money,tqsb.getRq());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	private void selectCcyebAmount(int ztbh) {
		Map<String,Double> map = tjjSgServiceImpl.getCcyebXx(ztbh);
		yhck=map.get("100201");
		zqqsk=map.get("1133");
		zqqskid = map.get("zqqskid")+"";
		yhckid = map.get("yhckid")+"";
	}
	
	private String updateCcyeb(int ztbh,double money,Date rq) throws Exception{
		yhck-=money;
		zqqsk+=money;
		TCcyeb tyhck = new TCcyeb();
		tyhck.setKjkmdm("100201");
		tyhck.setExtenda("银行存款_活期存款");
		tyhck.setZqcb(yhck);
		tyhck.setZtbh(ztbh);
		tyhck.setFsrq(rq);
		//String yhid=map.get("yhckid")+"";		
		tyhck.setId(Integer.parseInt(yhckid.substring(0,yhckid.length()-2)));
		
		TCcyeb tzqqsk = new TCcyeb();
		tzqqsk.setKjkmdm("1133");
		tzqqsk.setExtenda("证券清算款");
		tzqqsk.setZqcb(zqqsk);
		tzqqsk.setZtbh(ztbh);
		tzqqsk.setFsrq(rq);
		//String zqqskid=map.get("zqqskid")+"";
		tzqqsk.setId(Integer.parseInt(zqqskid.substring(0,zqqskid.length()-2)));
		
		try {
			tjjSgServiceImpl.updateCcyeb(tyhck);
			tjjSgServiceImpl.updateCcyeb(tzqqsk);
		}catch (Exception e) {
			throw new Exception("更新持仓余额时出错了");
		}
		
		return String.valueOf(1);
	}
	
	@PostMapping("/TUpdateQsb")
	public String updateQsb(@RequestParam(value = "Sgsq",required = true) String data) {
		/*
		 * 1.将data数据转为对象
		 * 2.判断所要修改的数据是不是当天还未生成凭证的数据，如果是，允许修改，如果不是。则作废当天的凭证后允许修改。
		 * 		如果作废的是今天的凭证，那么更改后的清算数据会在今天的下一次做账后生成凭证，如果作废的不是当日的凭证，那么
		 * 		修改后的清算数据是不会在今天的做账中被生成凭证，到时候查看凭证的时候作废的凭证是不会显示出来的，账套的估值也是对的。
		 * 3.如果未生成凭证过则修改清算表以及持仓余额表
		 * 4.如果已经生成了凭证，则提示“请先作废凭证”，结束。
		 * */
		//或者考虑出错了的话就删除重新录入
		
		TQsb tqsb = JSON.parseObject(data,TQsb.class);
		//根据清算编号去凭证表里面通过extendf查询，如果能够查到数据，说明凭证已经生成，必须先作废凭证才能进行修改。
		boolean flag = tjjSgServiceImpl.isHavePz(tqsb.getId());
		if(!flag) {
			return "该业务已经生成凭证，请先作废凭证再进行修改";
		}
		//先通过id拿到原始数据
		TQsb beforeTsqb = tjjSgServiceImpl.getTqsbByiD(tqsb.getId());
		
		//修改清算表数据
		//补充修改的信息
		tqsb.setBs(beforeTsqb.getBs());
		tqsb.setYwlb(beforeTsqb.getYwlb());
		tqsb.setExtendc(beforeTsqb.getExtendc());
		tqsb.setZqqsk(tqsb.getAmount());
		tjjSgServiceImpl.updateTqsb(tqsb);
		
		//修改持仓余额表数据
		//先拿到持仓数据
		selectCcyebAmount(tqsb.getZtbh());
		
		//更新持仓数据
		double chajia = tqsb.getAmount() - beforeTsqb.getAmount();
		try {
			updateCcyeb(tqsb.getZtbh(),chajia,tqsb.getRq());
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	@PostMapping("/deleteTSgsq")
	public String deleteTSgsq(@RequestParam(value = "id",required = true) int id) {
		/*
		 * 1.判断该数据是否已经生成凭证
		 * 2.如果已经生成了凭证，那么提示删除失败信息并结束
		 * 3.根据id从清算表里面查出数据
		 * 4.如果还没有生成凭证，那么删除清算表里面的数据
		 * 5.同时同步更新持仓余额表
		 * */
		
		//根据清算编号去凭证表里面通过extendf查询，如果能够查到数据，说明凭证已经生成，必须先作废凭证才能进行修改。
		boolean flag = tjjSgServiceImpl.isHavePz(id);
		if(!flag) {
			return "该业务已经生成凭证，请先作废凭证再进行删除";
		}
		//去清算表里面查出数据
		TQsb tqsb = tjjSgServiceImpl.getTqsbByiD(id);
		
		//删除清算表里面的数据
		try {
			tjjSgServiceImpl.deleteTqsbById(id);
		}catch (Exception e) {
			return "删除清算表时出错了";
		}
		
		//同步更新持仓余额表
		//先获取持仓余额表的数据
		selectCcyebAmount(tqsb.getZtbh());
		
		double money = tqsb.getAmount();
		//更新持仓余额表
		try {
			updateCcyeb(tqsb.getZtbh(),-money,tqsb.getRq());
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
}
