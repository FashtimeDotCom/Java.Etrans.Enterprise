package com.etrans.business.parse;

import java.util.Map;


/** 
 * ������Ϣ�������ӿ�
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����4:41:17 
 */
public interface M {
	
	/**
	 * ������Ϣ
	 * 
	 * @param message    String    base64���ܺ�ı�����Ϣ
	 * @param producQue  EQueue    ��������
	 * @param resultMap  HashMap<String,String>  ����������
	 */
	public void parse(String[] message,Map<String,Object> resultMap) throws Exception;
	
	/**
	 * ��ȡָ�����Э������
	 * 
	 * @return
	 */
	public String getName();
}

