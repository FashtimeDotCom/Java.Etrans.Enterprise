package com.etrans.system.control.mqdcswitch;

/**
 * �����������ĸ������ӡ����ص���բ
 * 
 * @author Pomelo(����.)
 * @since 2012-12-27 11:14
 * @version 1.0
 */
public abstract class MqdcSwitch {
	
	/** MSC����״̬ */
	public static boolean mscIsLink = true;
	
	/** mq������Ƿ�ɲ���*/
	protected boolean mqServerIsCanOper = true;
	
	/** �ܵ���־����*/
	public static boolean loggerSwitch = true;
	
	/**
	 * MQ�ķ�����Ƿ�ɲ���
	 * 
	 * @return mqServerIsCanOper boolean
	 */
	public boolean isMqServerIsCanOper() {
		return mqServerIsCanOper;
	}

	/**
	 * ��ȡMsc_Islink״̬
	 * 
	 * @return msc_isLink boolean
	 */
	public static boolean isMscIsLink() {
		return mscIsLink;
	}

	/**
	 * �ر����п��أ�����MSC��
	 * <p>
	 * [MSC������Ҫ�����ӣ������Լ��жϷ��������ֶ��ر�]
	 * </p>
	 */
	public void stopAllSwitchExceptMsc(){
		// �ر�MQ����˵Ĳ���
		setMqServerIsCanOper(false);
	}
	
	/**
	 * �����п��أ�����MSC��
	 * <p>
	 * [MSC������Ҫ�����ӣ������Լ��жϷ��������ֶ��ر�]
	 * </p>
	 */
	public void openAllSwitch(){
		// �ر�MQ����˵Ĳ���
		setMqServerIsCanOper(true);	
		// �����������
	}
	
	/**
	 * ����MSC����״̬�Ƿ������
	 * 
	 * @param isLink
	 */
	public static synchronized void setMscIslink(boolean isLink){
		mscIsLink = isLink;
	}
	
	/**
	 * ����˿ɲ������ƣ��þ����MQ����˿��������ƣ�����ͳһһ��ʵ��
	 * 
	 * @param mqServerIsCanOper  boolean
	 */
	public abstract void setMqServerIsCanOper(boolean mqServerIsCanOper);
}
