package com.etrans.common.netbase.mq;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

/**
 * MQ�����(����/�����߷�ʽ) ����
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 14:14
 * @version 1.0
 */
public abstract class MQServerTopic extends MQSuper implements MQServer{
	
	/** ��Ϣ������*/
	protected MessageProducer producer;

	/** ���� */
	private Topic topic = null;
	
	/** IO�Ự */
	protected Session session;
	
	/** ����*/
	protected String topicStr;
	
	/** ��Ϣkey*/
	protected String messageKey;
	
	/**
	 * ����
	 * 
	 * @param mqServerEntity
	 */
	public MQServerTopic(String topic,String messageKey){
		this.topicStr = topic;
		this.messageKey = messageKey;
	}
	
	/**
	 * ����һ��������
	 * 
	 * @parma  subject ����
	 * @return MessageProducer ������
	 * @throws JMSException JMS�쳣
	 */
	protected MessageProducer createMessageProducer() throws JMSException{
		session = super.createSession();
		topic = session.createTopic(topicStr);
		producer = session.createProducer(topic);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		return producer;
	}
	
	/**
	 * ������Ϣ
	 * 
	 * @param obj Object
	 */
	@Override
	public abstract void sendMessage(Object obj) throws JMSException;
}
