package com.wechat.message.resp;

/**
 * 回复消息 - 图片消息体
 * @author Administrator
 *
 */
public class Image {
	//通过素材管理接口上传多媒体文件，得到的id。
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
