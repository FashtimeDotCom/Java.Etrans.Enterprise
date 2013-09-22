package com.etrans.business.service.message;

import com.etrans.common.netbase.mina.MinaClient;

/** 
 * Mina��������ͨ���ӿ�
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-16 ����5:16:59 
 */
public class MinaSendMessageParent {
	
	public static MinaSendMessageParent minaSend=null;
	public static MinaClient minaClient;
	
	/**
	 * ˽�й���
	 * 
	 * @param minaClient
	 */
	private MinaSendMessageParent(MinaClient minaClient){	
		MinaSendMessageParent.minaClient = minaClient;
	}
	
	/**
	 * ��ȡ����
	 * 
	 * @param minaClient
	 * @return
	 */
	public static MinaSendMessageParent getMinaSend(MinaClient minaClient){
		if(minaSend==null)minaSend = new MinaSendMessageParent(minaClient);
		return minaSend;
	}
	
	/**
	 * ������Ϣ
	 * 
	 * @param message
	 * @throws Exception 
	 */
	public static void send(String message) throws Exception{
		if(minaClient==null)throw new Exception("��ƽ̨809��д����Ϣͨ��Ϊ��!");
		minaClient.write(message.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", ""));
	}
}

