package com.etrans.rmi;


/** 
 * ָ�����ӿ�
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����1:47:27 
 */
public interface CommandServer{
	
	/**
	 * ����ָ��Ͷ���
	 * 
	 * @param message
	 * @throws Exception 
	 */
	public void insertCommandSendQueue(String message) throws Exception;
}

