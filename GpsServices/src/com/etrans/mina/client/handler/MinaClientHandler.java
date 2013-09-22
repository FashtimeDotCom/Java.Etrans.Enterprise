package com.etrans.mina.client.handler;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.etrans.common.netbase.mina.MinaBaseClientHandler;
import com.etrans.common.netbase.mq.MQServer;

/**
 * �켣����ͻ���ҵ���߼����������
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 10:42
 * @version 1.0
 */
public class MinaClientHandler  extends MinaBaseClientHandler {
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(MinaClientHandler.class);
	
	/** �켣�ͻ���MQ*/
	private MQServer mq = null;

	/** ������������ */
//	private String[] mscDataAry;
	
	/** �ɹ��ñ�־ */
//	private final String LOGINSUCFLAG = "0"; 
	
	/** ��¼���շ���˹켣����ʱ�� */
	private long receiveTime = System.currentTimeMillis();
	
	/**
	 * �켣�ͻ���ҵ������
	 * 
	 * @throws JMSException 
	 */
	public MinaClientHandler(MQServer mq) throws JMSException{
		this.mq = mq;
	}
	
	/**
	 * ��ȡ���Է���˷������Ĺ켣����
	 * 
	 * @param session IoSession IO�Ự����
	 * @param message Object    ��Ϣ
	 */
	@Override
	public void messageReceived(IoSession session, Object message){	
		try{
			if(message!=null && (!message.toString().contains("NOOP"))){
				mq.sendMessage(message);
			}
			receiveTime = System.currentTimeMillis();
		}catch(Exception e){
			logger.error("������MSC�����쳣��"+e.getMessage());
		}
	}

	/**
	 * ����Mq�����
	 * 
	 * @return mq MQServer
	 */	
	public MQServer getMq() {
		return mq;
	}

	/**
	 * ����Mq�����
	 * 
	 * @param mq MQServer
	 */
	public void setMq(MQServer mq) {
		this.mq = mq;
	}
	
	/**
	 * ��ȡ���µĽ��չ켣����ʱ��
	 * 
	 * @return receiveTime long
	 */
	public long getReceiveTime() {
		return receiveTime;
	}
 
	/**
	 * �쳣
	 * 
	 * @param  session IoSession
	 * @param  cause   Throwable
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.info("�ͻ��˷����쳣...", cause);
	}
}
