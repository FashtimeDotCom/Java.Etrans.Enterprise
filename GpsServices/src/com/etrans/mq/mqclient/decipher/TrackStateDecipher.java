package com.etrans.mq.mqclient.decipher;

import org.apache.log4j.Logger;

import com.etrans.common.netbase.mq.MQMessageDecipher;

/**
 * �켣���ݽ���[״̬����]
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 18:57
 * @version 1.0
 */
public class TrackStateDecipher extends MQMessageDecipher {
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(TrackStateDecipher.class);

	/**
	 * ��Ϣ����
	 * 
	 * @param object Object ��Ϣ����
	 */
	@Override
	public Object decryptMessage(String object) {
		logger.info("״̬���ܺ�Ϊ:��"+object.toString()+"��");
		return object;
	}

}
