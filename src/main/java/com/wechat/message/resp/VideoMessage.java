package com.wechat.message.resp;

/**
 * 回复消息 - 视频消息
 * 
 * @author Administrator
 *
 */
public class VideoMessage extends BaseMessage{
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
