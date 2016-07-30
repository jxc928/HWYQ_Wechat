package com.wechat.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 平台相关通用信息缓存
 * @author Administrator
 *
 */
public class GlobalConstant {
	/** 接口调用地址信息interface_url.properties **/
	private static Map<String, String> interfaceUrlProperties = new ConcurrentHashMap<String, String>();
	/** 公众号信息wechat.properties **/
	private static Map<String, String> wechatProperties = new ConcurrentHashMap<String, String>();
	
	public static Map<String, String> getInterfaceUrlProperties() {
		return interfaceUrlProperties;
	}

	public static Map<String, String> getWechatProperties() {
		return wechatProperties;
	}
}
