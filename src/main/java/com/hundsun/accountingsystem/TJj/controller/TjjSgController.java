package com.hundsun.accountingsystem.TJj.controller;

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
		Map<String,Double> map = tjjSgServiceImpl.getCcyebXx(tqsb.getZtbh());
		yhck=map.get("100201");
		zqqsk=map.get("1133");
		System.out.println(yhck+" "+zqqsk+"..........");
		
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
		yhck-=money;
		zqqsk+=money;
		TCcyeb tyhck = new TCcyeb();
		tyhck.setKjkmdm("100201");
		tyhck.setExtenda("银行存款_活期存款");
		tyhck.setZqcb(yhck);
		tyhck.setZtbh(tqsb.getZtbh());
		String yhid=map.get("yhckid")+"";		
		tyhck.setId(Integer.parseInt(yhid.substring(0,yhid.length()-2)));
		
		TCcyeb tzqqsk = new TCcyeb();
		tzqqsk.setKjkmdm("1133");
		tzqqsk.setExtenda("证券清算款");
		tzqqsk.setZqcb(zqqsk);
		tzqqsk.setZtbh(tqsb.getZtbh());
		String zqqskid=map.get("zqqskid")+"";
		tzqqsk.setId(Integer.parseInt(zqqskid.substring(0,zqqskid.length()-2)));
		try {
			tjjSgServiceImpl.updateCcyeb(tyhck);
			tjjSgServiceImpl.updateCcyeb(tzqqsk);
		}catch (Exception e) {
			return "更新持仓余额时出错了";
		}
		
		return String.valueOf(1);
	}
}
