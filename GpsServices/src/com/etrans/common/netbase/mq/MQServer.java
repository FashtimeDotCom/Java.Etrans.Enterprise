package com.etrans.common.netbase.mq;

import javax.jms.JMSException;

/**
 * MQ����˽ӿ�
 * 
 * @author Pomelo(����.)
 *
 */
public interface MQServer {
	/**
	 * ������Ϣ
	 * 
	 * @throws InterruptedException 
	 * @tHROWS JMSException
	 */
	public void sendMessage(Object obj) throws JMSException, InterruptedException;
}
