package com.etrans.business.service.message;

import com.etrans.common.netbase.mina.MinaClient;

/** 
 * Mina发送数据通道接口
 * 
 * @author Pomelo(柚子.)  
 * @version 1.0
 * @since 创建时间：2013-7-16 下午5:16:59 
 */
public class MinaSendMessageTerminal {
	
	public static MinaSendMessageTerminal minaSend=null;
	public static MinaClient minaClient;
	
	/**
	 * 私有构造
	 * 
	 * @param minaClient
	 */
	private MinaSendMessageTerminal(MinaClient minaClient){	
		MinaSendMessageTerminal.minaClient = minaClient;
	}
	
	/**
	 * 获取单例
	 * 
	 * @param minaClient
	 * @return
	 */
	public static MinaSendMessageTerminal getMinaSend(MinaClient minaClient){
		if(minaSend==null)minaSend = new MinaSendMessageTerminal(minaClient);
		return minaSend;
	}
	
	/**
	 * 发送消息
	 * 
	 * @param message
	 * @throws Exception 
	 */
	public static void send(String message) throws Exception{
		if(minaClient==null)throw new Exception("【终端808】写入消息通道为空!");
		minaClient.write(message.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", ""));
	}
}

