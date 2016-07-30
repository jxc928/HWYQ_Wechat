package com.wechat.dispatcher;

import java.util.Map;

import com.wechat.constant.MessageConstant;

/**
 * 事件业务处理分发器
 * @author Administrator
 *
 */
public class EventDispatcher {
	public static String processEvent(Map<String, String> map){
		if (map.get("Event").equals(MessageConstant.EVENT_SUBSCRIBE)) {//关注事件
            System.out.println("==========SubsribeEvent");
        }
		if (map.get("Event").equals(MessageConstant.EVENT_UNSUBSCRIBE)) {//取消关注事件
            System.out.println("==========UnsubsribeEvent");
        }
		if (map.get("Event").equals(MessageConstant.EVENT_SCAN)) {//扫描二维码事件
            System.out.println("==========ScanEvent");
        }
		if (map.get("Event").equals(MessageConstant.EVENT_LOCATION)) {//上报地理位置事件
            System.out.println("==========LocationEvent");
        }
		if (map.get("Event").equals(MessageConstant.EVENT_CLICK)) {//自定义菜单点击事件
            System.out.println("==========ClickEvent");
        }
		if (map.get("Event").equals(MessageConstant.EVENT_VIEW)) {//自定义菜单跳转事件
            System.out.println("==========ViewEvent");
        }
		
		return null;
	}
}
