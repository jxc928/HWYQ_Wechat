package com.wechat.quartz;

import com.wechat.common.WechatTask;

public class QuartzJob {
	/**
	 * 执行获取access_token任务
	 */
	public void getTokenJob(){
		WechatTask task = new WechatTask();
		task.getToken();
	}
}
