package com.etrans.mq.mqclient.decipher;

import com.etrans.common.netbase.mq.MQMessageDecipher;

/**
 * Ĭ�Ͻ�����
 * 
 * @author Pomelo(����.)
 * @since 2012-12-13 10:49
 * @version 1.0
 */
public class DefaultDecipher extends MQMessageDecipher{
	/**
	 * ��Ϣ����
	 * 
	 * @param object Object ��Ϣ����
	 */
	@Override
	public Object decryptMessage(String object) {
		return object;
	}
}
