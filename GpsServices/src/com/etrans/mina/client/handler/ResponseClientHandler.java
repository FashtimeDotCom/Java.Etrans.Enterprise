package com.etrans.mina.client.handler;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.etrans.common.netbase.mina.MinaBaseClientHandler;

/**
 * �켣����ͻ���ҵ���߼����������
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 10:42
 * @version 1.0
 */
public class ResponseClientHandler  extends MinaBaseClientHandler{
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(ResponseClientHandler.class);
	
	/** ������MSC�ɹ���־ */
	private boolean LinkMscFlag = false;
	
	/** ������������ */
	private String[] mscDataAry;

	/**
	 * �켣�ͻ���ҵ������
	 * 
	 * @throws JMSException 
	 */
	public ResponseClientHandler() throws JMSException{
	}
	
	/**
	 * ��ȡ���Է���˷������Ĺ켣����
	 * 
	 * @param session IoSession IO�Ự����
	 * @param message Object    ��Ϣ
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		try {
			// һ���ɹ�����䲻������MSC��������
			if(!LinkMscFlag){
				mscDataAry = message.toString().split(",");
				if(mscDataAry.length==4){
					if(mscDataAry[1].equals("0")){
						LinkMscFlag = true;
						logger.info("/////////////////////////////��������Ϣ������MSCͨ���ɹ�/////////////////////////////");
					}
				}
			}
		} catch (Exception e) {
			LinkMscFlag = false;
		}		
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
		logger.error("�ͻ��˷����쳣...", cause);
	}

	
	/**
	 * ��ȡ�Ƿ����ӳɹ���MSC
	 * 
	 * @return LinkMscFlag boolean
	 */
	public boolean isLinkMscFlag() {
		return LinkMscFlag;
	}
}
