package com.wechat.constant;

/**
 * 消息/事件常量
 * @author Administrator
 *
 */
public class MessageConstant {
	/**
	 * 接收普通消息
	 */
	//文本消息
	public static final String MESSAGE_TEXT = "text";
	//图片消息
	public static final String MESSAGE_IMAGE = "image";
	//语音消息
	public static final String MESSAGE_VOICE = "voice";
	//视频消息
	public static final String MESSAGE_VIDEO = "video";
	//小视频消息
	public static final String MESSAGE_SHORTVIDEO = "shortvideo";
	//地理位置消息
	public static final String MESSAGE_LOCATION = "location";
	//链接消息
	public static final String MESSAGE_LINK = "link";
	//音乐消息
	public static final String MESSAGE_MUSIC = "music";
	//图文消息
	public static final String MESSAGE_NEWS = "news";
	
	/**
	 * 接收事件推送
	 */
	//事件
	public static final String EVENT = "event";
	//订阅、扫描二维码(未关注时的event)
	public static final String EVENT_SUBSCRIBE = "subscribe";
	//取消订阅
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	//扫描二维码(已关注时的event)
	public static final String EVENT_SCAN = "SCAN";
	//上报地理位置
	public static final String EVENT_LOCATION = "LOCATION";
	//点击菜单拉取消息
	public static final String EVENT_CLICK = "CLICK";
	//点击菜单跳转链接
	public static final String EVENT_VIEW = "VIEW";
	
}
