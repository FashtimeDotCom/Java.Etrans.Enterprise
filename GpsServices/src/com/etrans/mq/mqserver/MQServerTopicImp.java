package com.etrans.mq.mqserver;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import org.apache.log4j.Logger;
import com.etrans.common.netbase.mq.MQServerTopic;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

/**
 * MQ�ͻ���,�켣����MQ
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 14:14
 * @version 1.0
 */
public class MQServerTopicImp extends MQServerTopic{

	
	/** �ύ����ͳ��*/
	private long counsum = 0;
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(MQServerTopicImp.class);

	/**
	 * �켣MQ�����
	 * 
	 * {@link Default}
	 * @throws JMSException 
	 */
	public MQServerTopicImp(String topic,String messageKey) throws JMSException{
		super(topic,messageKey);
		super.createMessageProducer();
		Thread thread = new Thread(new SessionManager());
		thread.start();
	}
	
	/**
	 * �����߷������ݰ�
	 * 
	 * @param ob:Object
	 * @throws JMSException JMS�쳣
	 * @throws InterruptedException 
	 */
	@Override
	public void sendMessage(Object obj) throws JMSException {		
		MapMessage message = super.session.createMapMessage();
		message.setString(messageKey, obj.toString());
		counsum++;
		producer.send(message);		
	}
	
	/**
	 * �ύ��Ϣ,��������ʽ�ύ
	 */
	public void commit() {
		try {
			if(counsum>0){
				session.commit();
			}
		} catch (JMSException e) {
			logger.error("�ύ��Ϣ�쳣");
		}
	}

	/**
	 * MQ�ͻ���,Session�����߳�
	 * 
	 * @author Pomelo(����.)
	 * @since 2012-12-10 18:01
	 * @version 1.0
	 */
	class SessionManager implements Runnable{

		/**
		 * �߳���������
		 * 
		 * �÷�������������Ϣ���������ύ�����10������Ϊһ�������ύ
		 */
		@Override
		public void run() {
			while (true)
			{
				try {
					commit();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.error("���߳��쳣"+e.getMessage());
				}
			}
		}
	}

}
