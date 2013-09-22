package com.etrans.mq.mqclient.decipher;

import org.apache.log4j.Logger;

import com.etrans.common.netbase.mq.MQMessageDecipher;
import com.etrans.common.netbase.util.Base64ThreadLocal;

/**
 * �켣���ݽ���[64λ����]
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 18:57
 * @version 1.0
 */
public class TrackBase64Decipher extends MQMessageDecipher {
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(TrackBase64Decipher.class);
	
	/** Base64 ���� */
	private Base64ThreadLocal base64 = new Base64ThreadLocal();
	
	/** GPS��Ϣ */
	private String gpsInfo = "";
	
	
	/**
	 * ��Ϣ����
	 * 
	 * @param object Object ��Ϣ����
	 */
	@Override
	public Object decryptMessage(String object) {
		try {
			gpsInfo = base64.decoderMessageApache(object);
		} catch (Exception e) {
			logger.error("��Base64�����쳣��ԭ���ġ���"+object+"��");
		}
		return  gpsInfo;
	}

}
