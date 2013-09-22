package com.etrans.business.service.message;

import com.etrans.common.netbase.mina.MinaClient;

/** 
 * Mina��������ͨ���ӿ�
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-16 ����5:16:59 
 */
public class MinaSendMessageTerminal {
	
	public static MinaSendMessageTerminal minaSend=null;
	public static MinaClient minaClient;
	
	/**
	 * ˽�й���
	 * 
	 * @param minaClient
	 */
	private MinaSendMessageTerminal(MinaClient minaClient){	
		MinaSendMessageTerminal.minaClient = minaClient;
	}
	
	/**
	 * ��ȡ����
	 * 
	 * @param minaClient
	 * @return
	 */
	public static MinaSendMessageTerminal getMinaSend(MinaClient minaClient){
		if(minaSend==null)minaSend = new MinaSendMessageTerminal(minaClient);
		return minaSend;
	}
	
	/**
	 * ������Ϣ
	 * 
	 * @param message
	 * @throws Exception 
	 */
	public static void send(String message) throws Exception{
		if(minaClient==null)throw new Exception("���ն�808��д����Ϣͨ��Ϊ��!");
		minaClient.write(message.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", ""));
	}
}

