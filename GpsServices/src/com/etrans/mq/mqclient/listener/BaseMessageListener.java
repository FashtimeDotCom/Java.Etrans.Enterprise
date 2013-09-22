package com.etrans.mq.mqclient.listener;

import com.etrans.business.queue.EQueue;
import com.etrans.common.netbase.mq.MQClientListener;
import com.etrans.common.netbase.mq.MQMessageDecipher;

/** 
 * �ʺϽ��ո�����������
 * �粻��������̳�MQClientListener������չ
 * for Example
 * <code>
 * extends MQClientListener
 * </code>
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-9 ����4:59:40 
 */
public class BaseMessageListener extends MQClientListener {

	/** �������ݱ�־*/
	private String NEEDFLAG="5";
	
	/**
	 * �����๹�췽��
	 * 
	 * @param session  ��ǰSession
	 * @param clientNo ��ǰ�����߱��String
	 */	
	public BaseMessageListener(MQMessageDecipher decipher, EQueue queue,
			String messageKey,String NEEDFLAG) {
		super(decipher, queue, messageKey);
		this.NEEDFLAG = NEEDFLAG;
	}

	/**
	 * ������Ϣ
	 * 
	 * scpt_00_UserLogonOK       = 0,  // �û���֤�ɹ�
     * scpt_01_UserLogonError    = 1,  // �û���֤ʧ��
     * scpt_02_OtherConnect      = 2,  // �����û���¼
     * scpt_03_OtherData         = 3,  // ��������
     * scpt_04_OtherBroken       = 4,  // �����û��˳�
     * scpt_05_GpsData           = 5,  // �켣����
     * scpt_06_Operation         = 6,  // ҵ������
     * scpt_07_TransmitResult    = 7,  // ���ͻ�Ӧ��Ϣ
     * scpt_08_SendResult        = 8,  // ָ��ظ�
     * scpt_09_CarList           = 9,  // �����б���Ϣ
     * scpt_10_ImageData         = 10, // ͼ�� ����Ƶ����Ƶ����
     * scpt_11_GpsHistory        = 11, // ��ʷ���ݡ��������
     * scpt_12_Alarm             = 12, // ��������
     * scpt_13_Trans             = 13  // ����͸��
     * scpt_14_Command           = 14, // �����ն�ָ��
	 * scpt_15_Command           = 15, // ����ƽָ̨��
	 * scpt_16_Request	         = 16, //��������
	 * scpt_17_TA_Alarm	         = 17, //�켣��������
	 * scpt_18_Attach            = 18, //������Ϣ
	 * @param message String[] ����
	 */
	@Override
	public void parseMessage(String[] message) {
		if (message[1].equalsIgnoreCase(NEEDFLAG)) {
			queue.produce(message[0]+","+message[1]+","+message[2]+","+ decipher.decryptMessage(message[3]));
		}
	}
}

