package com.wechat.message.resp;

/**
 * 回复消息 - 音乐消息
 * 
 * @author Administrator
 *
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
