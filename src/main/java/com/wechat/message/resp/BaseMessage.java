package com.wechat.message.resp;

/**
 * 回复消息 - 消息基本类
 * 
 * @author Administrator
 *
 */
public class BaseMessage {
	// 接收方账号(收到的OpenId)
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间
	private Long CreateTime;
	// 消息类型
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long l) {
		CreateTime = l;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
