package com.wechat.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.wechat.constant.GlobalConstant;

/**
 * 项目启动初始化: 加载接口URL地址配置文件 & 平台测试号信息配置文件
 * @author Administrator
 *
 */
public class InitProps {
	public synchronized static void init(){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		InputStream ins = null;
		try {
			ins = cl.getResourceAsStream("interface_url.properties");
			props.load(ins);
			for(Object key : props.keySet()){
                GlobalConstant.getInterfaceUrlProperties().put((String)key, (String)props.get(key));
            }
			
			props = new Properties();
			ins = cl.getResourceAsStream("wechat.properties");
			props.load(ins);
			for(Object key : props.keySet()){
                GlobalConstant.getWechatProperties().put((String)key, (String)props.get(key));
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ins != null){
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return;
	}
}
