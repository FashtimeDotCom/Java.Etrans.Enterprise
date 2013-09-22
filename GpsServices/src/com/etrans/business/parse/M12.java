package com.etrans.business.parse;

import java.util.Map;

import com.etrans.business.queue.EQueue;
import com.etrans.business.queue.EtransLinkedQueue;
import com.etrans.common.util.Tools;

/** 
 * �������ݽ�����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����6:22:22 
 */
public class M12 implements M {
	
	private EQueue queue = new EtransLinkedQueue();// ÿ�����ı�������
	private String[] business;
	private String decoderStr;
	
	/**
	 * ������Ϣ
	 * 
	 * @param message    String    base64���ܺ�ı�����Ϣ
	 * @param producQue  EQueue    ��������
	 * @param resultMap  HashMap<String,String>  ����������
	 */
	@Override
	public void parse(String[] message,Map<String, Object> resultMap) throws Exception {
		business = message;
		decoderStr = business[3];
		queue = getHashMapQueue((EQueue)resultMap.get(business[2]));
		queue.produce(decoderStr + "=====" +System.currentTimeMillis());
		resultMap.put(business[2], queue);
	}

	/**
	 * �ж�businessHashMap�еĶ���
	 * 
	 * @param queueTemp ,businessHashMap�еĶ���
	 * @return Queue<String>
	 * */
	public EQueue getHashMapQueue(EQueue queueTemp) {
		if (null == queueTemp) {
			return new EtransLinkedQueue();
		} else {
			while (queueTemp.size() >= (Tools.maxMessageNum)) { 
				queueTemp.consume();
			}
			return queueTemp;
		}
	}
	
	/**
	 * ��ȡָ�����Э������
	 * 
	 * @return
	 */
	public String getName(){
		return "��ָ���������͡�������������";
	}
}

