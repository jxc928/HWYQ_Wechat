package com.wechat.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wechat.message.resp.Article;
import com.wechat.message.resp.NewsMessage;

/**
 * 消息工具类
 * 
 * @author Administrator
 *
 */
public class MessageUtil {
	
	/**
	 * xml转为map集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		InputStream ins = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(ins);

		//得到xml根元素
		Element root = doc.getRootElement();
		//得到根元素所有子节点
		List<Element> list = root.elements();
		
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}

		//释放资源
		ins.close();
		ins = null;
		
		return map;
	}
	
	/**
	 * 消息转为xml
	 * 
	 * @param message
	 * @return
	 */
	public static <T> String messageToXml(T t){
		XStream xstream = new XStream();
		//更换根节点为xml
		xstream.alias("xml", t.getClass());
		//若为NewsMessage.class还需要修改item节点
		if(NewsMessage.class.equals(t.getClass())){
			xstream.alias("item", new Article().getClass());
		}
		return xstream.toXML(t);
	}
	
	
	
//	/**
//	 * 拼接回复信息
//	 * @param toUserName
//	 * @param fromUserName
//	 * @param content
//	 * @return
//	 */
//	public static String initText(String toUserName, String fromUserName, String content){
//		BaseMessage text = new BaseMessage();
//		text.setFromUserName(toUserName);
//		text.setToUserName(fromUserName);
//		text.setMsgType(MessageContant.MESSAGE_TEXT);
//		text.setCreateTime(new Date().getTime());
//		text.setContent(content);
//		return textMessageToXml(text);
//	}
//	
//	/**
//	 * 主菜单
//	 * @return
//	 */
//	public static String menuText(){
//		StringBuffer sb = new StringBuffer();
//		sb.append("欢迎您的关注，请按照菜单提示进行操作:\n\n");
//		sb.append("1. 中国城市天气实况查询\n");
//		sb.append("2. 中国城市天气预报查询\n");
//		sb.append("(以上查询信息来源:环境云)\n\n");
//		sb.append("回复\"?\"可获取该列表......");
//		
//		return sb.toString();
//	}
//	
//	/**
//	 * 1.中国城市天气实况查询格式文本
//	 * @return
//	 */
//	public static String firstMenu(){
//		StringBuffer sb = new StringBuffer();
//		sb.append("请按照如下格式查询中国城市实时天气:\n");
//		sb.append("实时北京\n");
//		sb.append("实时青岛\n");
//		sb.append("目前仅支持一次查询一个城市。");
//		
//		return sb.toString();
//	}
//	
//	/**
//	 * 2.中国城市天气预报查询格式文本
//	 * @return
//	 */
//	public static String secondMenu(){
//		StringBuffer sb = new StringBuffer();
//		sb.append("请按照如下格式查询中国城市天气预报:\n");
//		sb.append("单个城市:预报北京\n");
//		sb.append("多个城市:预报北京,青岛\n");
//		
//		return sb.toString();
//	}
}
