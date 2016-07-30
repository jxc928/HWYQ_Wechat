package com.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wechat.constant.MessageConstant;
import com.wechat.dispatcher.EventDispatcher;
import com.wechat.dispatcher.MsgDispatcher;
import com.wechat.util.MessageUtil;
import com.wechat.util.SignUtil;

@Controller
@RequestMapping("/wechat")
public class WechatSecurity {
	private static Logger logger = Logger.getLogger(WechatSecurity.class);
	
	/**
	 * 接收get参数，返回验证参数
	 * @param req
	 * @param resp
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 */
	@RequestMapping(value = "security", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp, 
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String nonce,
			@RequestParam(value = "echostr", required = true) String echostr){
		try {
			if(SignUtil.checkSignature(signature, timestamp, nonce)){
				PrintWriter out = resp.getWriter();
				out.print(echostr);
				out.close();
			}else{
				logger.info("非法请求!");
			}
		} catch (IOException e) {
			logger.error(e, e.fillInStackTrace());
		}
	}
	
	/**
	 * 接收微信服务端消息并做分发
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	@RequestMapping(value = "security", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		PrintWriter out;
		try {
			//统一request，response编码
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();
			
			String backMessage = null;

			Map<String, String> map = MessageUtil.xmlToMap(req);
			String msgType = map.get("MsgType");
			if(MessageConstant.EVENT.equals(msgType)){
				backMessage = EventDispatcher.processEvent(map);//进入事件分发
			}else{
				backMessage = MsgDispatcher.processMsg(map);//进入消息分发
			}
			
			out.println(backMessage);
			out.close();
		} catch (Exception e) {
			logger.error(e, e.fillInStackTrace());
		} finally {
			out = null;
		}
	}
}
