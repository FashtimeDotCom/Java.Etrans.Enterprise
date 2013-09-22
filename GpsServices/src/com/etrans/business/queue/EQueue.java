package com.etrans.business.queue;
/** 
 * E����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����5:42:04 
 */
public interface EQueue {
	/**
	 * �洢��Ϣ
	 * 
	 * @param message
	 */
	public void produce(String message);
	
	/**
	 * ������Ϣ
	 * 
	 * @return
	 */
	public String consume();
	
	/**
	 * ���д�Сs
	 * @return
	 */
	public int size();
	
	/**
	 * ת��������
	 * 
	 * @param a
	 * @return
	 */
	public String[]  toArray(String[] a);
}

