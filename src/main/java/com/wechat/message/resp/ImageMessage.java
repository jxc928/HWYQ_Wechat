package com.wechat.message.resp;

/**
 * 回复消息 - 图片消息
 * 
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage{
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
