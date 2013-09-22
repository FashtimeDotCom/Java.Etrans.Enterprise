package com.etrans.common.netbase.mq;

import javax.jms.Destination;
import javax.jms.JMSException;

/**
 * MQ�ͻ���(��Ե�) ����
 * 
 * @author Pomelo(����.)
 * @since 2012-12-13 11:14
 * @version 1.0
 */
public class MQClientQueue extends MQClient {

	/**
	 * ��Ե�MQ�ͻ��˳��๹�캯��
	 * 
	 * @param clientNo
	 * @param decipher
	 * @param topic
	 * @throws JMSException
	 */
	public MQClientQueue(MQClientListener listener,String topic) throws JMSException{
		super(topic,listener);
	}
	
	/**
	 * ����������
	 * 
	 * @param subject String
	 * @throws JMSException JMS�쳣
	 */
	@Override
	public Destination createDestination(String subject) throws JMSException {
		return super.getSession().createQueue(subject);
	}

}
