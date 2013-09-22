package com.etrans.business;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.etrans.business.parse.M;
import com.etrans.business.queue.EQueue;

/** 
 * ��Ϣ����ҵ���߳�
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����5:50:46 
 */
public class MessageHandlingThread implements Runnable{

	/** 
	 * ��־����
	 */
	private static Logger logger = Logger.getLogger(MessageHandlingThread.class);
	
	/**
	 * �洢���Map
	 */
	private Map<String, Object> resultHashMap;
	
	/**
	 * ��������
	 */
	private EQueue producterQueue;
	
	/**
	 * ���ݽ����ӿ�
	 */
	private M mHander;
	
	/**
	 * ���캯��
	 * 
	 * @param commandResultHashMap �洢���Map
	 * @param queue   ������ϢMap
	 */
	public MessageHandlingThread(Map<String, Object> resultHashMap,EQueue producterQueue,M mHander){
		this.resultHashMap = resultHashMap;
		this.producterQueue = producterQueue;
		this.mHander = mHander;
	}
	
	/**
	 * ������Ϣ�߳�
	 * <p>
	 * ���Ƿ���Э�����Ϣ���ڴ˽��д���
	 * 
	 * </p>
	 */
	@Override
	public void run() {	
		String message = "";
		while (true) {
			try{    
				message = producterQueue.consume();
				if (StringUtils.isNotEmpty(message)) {
					mHander.parse(message.split(",",4),resultHashMap);
				}else{
					Thread.sleep(10);
				}				
			}catch (Exception e) {
				e.printStackTrace();
				logger.error(mHander.getName()+"������Ϣ�����쳣:"+e.getMessage());
			}
		}
	}

}

