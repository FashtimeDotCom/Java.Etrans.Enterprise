package com.etrans.business.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/** 
 * ͬ������
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����6:34:53 
 */
public class EtransSynLinkQueue implements EQueue {
	
	private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>(); 
	private int countQueueSize=0;
	/**
	 * ��ֵ���������
	 * 
	 * @param message ��Ϣ
	 */
	public synchronized void produce(String message) {
		queue.add(message);
		++countQueueSize;
	}

	/**
	 * ȡ����ֵ,��ɾ����ȡֵ
	 */
	public String consume() {
		String message = null;
		if (!queue.isEmpty()) {
			message = (String) queue.poll();
			--countQueueSize;
		}
		return message;
	}
	
	/**
	 * ��ͬ�����в���ͳ�ƶ��д�С
	 * 
	 * @return size
	 */
	@Override
	public synchronized int size() {
		return countQueueSize;
	}
	
	@Override
	public String[] toArray(String[] a) {
		return queue.toArray(a);
	}
}

