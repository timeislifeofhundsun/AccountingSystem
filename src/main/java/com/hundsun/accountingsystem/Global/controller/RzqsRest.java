package com.hundsun.accountingsystem.Global.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.accountingsystem.Global.service.RzqsService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FilePathUtil;

/**
 * 
* <p>
* Description:对web页面提供rest接口
* <p>
* Company: 恒生电子
* @author gaozhen
* @date 2019年3月13日
* @Version 1.1
 */
@RestController
@RequestMapping("/rest/RzqsRest")
public class RzqsRest {
	private static final Logger log = LoggerFactory.getLogger(RzqsRest.class);
	
	@Autowired
	private RzqsService rzqsService;
	
	/**
	 * 
	* @Description: 日终清算Rest方法 
	* @param
	* @return JSONObject    返回类型
	* @author gaozhen
	 */
	@RequestMapping("/Rzqs")
	public JSONObject rzqs(@RequestBody String reqstr) {
		log.info("请求原始数据:"+reqstr);
		JSONObject response = new JSONObject();
		response.put("res", false);
		try {
			JSONObject resquest = JSONObject.parseObject(reqstr);
			Integer ztbh = resquest.getInteger("ztbh");
			Date ywrq = DateFormatUtil.getDateByString(resquest.getString("ywrq"));
			boolean res = rzqsService.rzqs(ztbh, ywrq);
			if(res) {
				response.put("res", true);
			}
		}catch (ParseException e) {
			log.error(e.getMessage());
			response.put("msg","参数不规范\n"+e.getMessage());
		} catch(Exception e){
			response.put("msg",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/getFileStatus")
	public JSONObject getFileStatus(@RequestBody String reqstr) {
		log.info("请求原始数据:"+reqstr);
		JSONObject response = new JSONObject();
		response.put("res", false);
		try {
			JSONObject resquest = JSONObject.parseObject(reqstr);
			Date ywrq = DateFormatUtil.getDateByString(resquest.getString("ywrq"));
			Map<String,String> files = FilePathUtil.
					getFilePathByDate(DateFormatUtil.getStringByDate(ywrq));
			JSONArray array = new JSONArray();
			for(String fileName:files.keySet()) {
				JSONObject obj = new JSONObject();
				String path = files.get(fileName);
				obj.put("fileName", fileName);
				obj.put("filePath", path);
				array.add(obj);
			}
			response.put("res", true);
			response.put("data", array);
		}catch (ParseException e) {
			log.error(e.getMessage());
			response.put("msg","参数不规范\n"+e.getMessage());
		} catch(Exception e){
			response.put("msg",e.getMessage());
		}
		log.info("处理完成");
		return response;
	}


}
