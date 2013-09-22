package com.etrans.rmi.imp;

import com.etrans.business.service.message.MinaSendMessageTerminal;
import com.etrans.rmi.CommandServer;

/** 
 * ָ�����ӿ�ʵ��
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����2:39:10 
 */
public class CommandServerImp implements CommandServer {

 
	/**
	 * �ն˷���ָ��
	 * 
	 * @param message ��Ϣ
	 * @throws Exception 
	 */
	@Override
	public void insertCommandSendQueue(String message) throws Exception {
		MinaSendMessageTerminal.send(message);
	}

}

