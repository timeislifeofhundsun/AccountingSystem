package com.hundsun.accountingsystem.TGp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.service.RzqsService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FilePathUtil;
import com.hundsun.accountingsystem.TGp.service.GPManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
* <p>
* Description:对web页面提供rest接口
* <p>
* Company: 恒生电子
* @author gaozhen
* @date 2019年3月27日
* @Version 1.1
 */
@RestController
@RequestMapping("/rest/gpRest")
public class TgpRest {
	private static final Logger log = LoggerFactory.getLogger(TgpRest.class);

	@Autowired
	private GPManageService gpManageService;

	/**
	 * 处理股票交易请求
	 * @return
	 */
	@RequestMapping("/gpjy")
	public String gpjy(@RequestParam String ywrq,@RequestParam String ztbh) {
		log.info("处理股票交易请求,业务日期:"+ywrq+",账套编号:"+ztbh);
		JSONObject response = new JSONObject();
		try{
			JSONArray jsonArray = gpManageService.selectGpjy(
					Integer.valueOf(ztbh),DateFormatUtil.getDateByString(ywrq));
			response.put("code",0);
			response.put("msg","");
			response.put("count",jsonArray.size());
			response.put("data",jsonArray);
		}catch (Exception e){
			response.put("code",1);
			response.put("msg",e.getMessage());
		}
		return response.toJSONString();
	}

	@RequestMapping("/hg")
	public String hg(@RequestParam String ywrq,@RequestParam int ztbh,@RequestParam int ywlb) {
		log.info("处理股票交易请求,业务日期:"+ywrq+",账套编号:"+ztbh);
		JSONObject response = new JSONObject();
		try{
			JSONArray jsonArray = gpManageService.selectHGQS(
					ztbh,DateFormatUtil.getDateByString(ywrq),ywlb);
			response.put("code",0);
			response.put("msg","");
			response.put("count",jsonArray.size());
			response.put("data",jsonArray);
		}catch (Exception e){
			response.put("code",1);
			response.put("msg",e.getMessage());
		}
		return response.toJSONString();
	}



}
