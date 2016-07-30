package com.wechat.message.resp;

/**
 * 回复消息 - 语音消息
 * 
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseMessage{
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
