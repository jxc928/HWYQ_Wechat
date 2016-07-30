package com.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wechat.constant.GlobalConstant;
import com.wechat.util.HttpUtil;

import net.sf.json.JSONObject;

/**
 * 微信access_token两小时定时获取任务体
 * @author Administrator
 *
 */
public class WechatTask {
	private static Logger logger = Logger.getLogger(WechatTask.class);
	
	public void getToken() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("grant_type", "client_credential");// 获取access_token填写client_credential
			params.put("appid", GlobalConstant.getWechatProperties().get("appId"));//
			params.put("secret", GlobalConstant.getWechatProperties().get("appSecret"));
			
			String jsonToken = HttpUtil.sendGet(
					GlobalConstant.getInterfaceUrlProperties().get("getTokenUrl"), params);
			
			JSONObject jsonObject = JSONObject.fromObject(jsonToken);
			if(jsonObject.has("access_token")){
				String access_token = jsonObject.getString("access_token");
				GlobalConstant.getWechatProperties().put("access_token", access_token);
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "=================access_token为: " + access_token);
			}else if(jsonObject.has("errcode")){
				logger.error("获取access_token异常, 错误码:" + jsonObject.get("errcode"));
			}
		} catch (Exception e) {
			logger.error("获取access_token异常, 无错误码!!", e.fillInStackTrace());
		}
	}
}
