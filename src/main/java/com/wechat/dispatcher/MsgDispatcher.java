package com.wechat.dispatcher;

import java.util.Date;
import java.util.Map;

import com.wechat.constant.MessageConstant;
import com.wechat.message.resp.TextMessage;
import com.wechat.util.MessageUtil;

/**
 * 消息业务处理分发器
 * @author Administrator
 *
 */
public class MsgDispatcher {
	public static String processMsg(Map<String, String> map){
		String openId = map.get("FromUserName");//用户openId
		String mpId = map.get("ToUserName");//公众号Id
		
		if(map.get("MsgType").equals(MessageConstant.MESSAGE_TEXT)){//文本消息
			TextMessage txtMsg = new TextMessage();
			txtMsg.setToUserName(openId);
			txtMsg.setFromUserName(mpId);
			txtMsg.setMsgType(MessageConstant.MESSAGE_TEXT);
			txtMsg.setCreateTime(new Date().getTime());;
			
			txtMsg.setContent("发的信息为: " + map.get("Content"));
			
//			System.out.println("==========TextMessage");
			return MessageUtil.messageToXml(txtMsg);
		}
		if(map.get("MsgType").equals(MessageConstant.MESSAGE_IMAGE)){//图片消息
			System.out.println("==========ImageMessage");
		}
		if(map.get("MsgType").equals(MessageConstant.MESSAGE_VOICE)){//语音消息
			System.out.println("==========VoiceMessage");
		}
		if(map.get("MsgType").equals(MessageConstant.MESSAGE_VIDEO)){//视频消息
			System.out.println("==========VideoMessage");
		}
		if(map.get("MsgType").equals(MessageConstant.MESSAGE_LOCATION)){//地理位置消息
			System.out.println("==========LocationMessage");
		}
		if(map.get("MsgType").equals(MessageConstant.MESSAGE_LINK)){//链接消息
			System.out.println("==========LinkMessage");
		}
		
		return null;
	}
}
