package com.etrans.system.control;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import com.etrans.common.MQDCConfigUtil;
import com.etrans.common.netbase.mina.MinaClient;
import com.etrans.common.netbase.mq.MQClientListener;
import com.etrans.system.control.mqdcswitch.MqdcSwitch;


/**
 * MQ�������ļ�ؼ��
 * 
 * @author Pomelo(����.)
 * @since 2012-12-27 14:14
 * @version 1.0
 */
public class MQDCCheckMonitor extends Thread{

	/** ��־����*/
	private static Logger logger = Logger.getLogger(MQDCCheckMonitor.class);
	
	/** Mina�ͻ���*/
	private MinaClient client = null;
	
	/** MQҵ�������*/
	private MQClientListener mqListener = null;	
	
	/** �ܿ���*/
	private MqdcSwitch mqdcSwitch;
	
	/**
	 * ��½MSC
	 */
	private LoginMsc login;
	
	/**
	 * �Ƿ����¶���
	 */
	public static boolean ISRESUBSCRI = false;

	/**
	 * MQDC����ع��캯��
	 * 
	 * @param client MinaClient
	 * @param mqListener Listener
	 */
	public MQDCCheckMonitor(MinaClient client,MQClientListener mqListener,MqdcSwitch mqdcSwitch){
		this.client = client;
		this.mqListener = mqListener;
		this.mqdcSwitch = mqdcSwitch;		
	}
	
	/**
	 * �߳����з���
	 * <p>
	 * ������MSC�ڸ���ʱ����û���ɷ���Ч���ݹ���ʱ�����������¶���
	 * </P
	 */
	@Override
	public void run() {
		logger.info("//////////////////////////���MSC�ɷ�״̬�߳�������//////////////////////////");
		// ��һ�ν��������¼�
		long timeInterval = 0l;
		// ���µ�½��־
		boolean reLoginFlag = false;
		while(true){
			try {			
				Thread.sleep(10000);
				// ��MSC���1����δ�ɷ����ݹ�����������ʧ��ʱ����������
				timeInterval = System.currentTimeMillis()-mqListener.getReceiveTime();
				if(MqdcSwitch.isMscIsLink()){
					// ����Զ������˻��߳�ʱû���յ���Ч���ݶ��������¶���
					if(timeInterval>MQDCConfigUtil.MQ_SERVER_TRACK_DATA_INVALID || MQDCCheckMonitor.ISRESUBSCRI){
						mqdcSwitch.stopAllSwitchExceptMsc();
						try {
							// �����ѵ�¼Ϊfalse
							client.getClientHandler().setLinkMscFlag(false);
							// ��ʼ���µ�½
							login = new LoginMsc(this.client);
							reLoginFlag = login.login("##001,0,rachelDong,"+new BASE64Encoder().encode("gtt,288,99".getBytes("GBK")));
							if(reLoginFlag){
								mqdcSwitch.openAllSwitch();
								// �����ǰ����û����ϻ�����ֹ���򲻷�����һ�ζ��ģ�ֱ����һ����ɻ����յ��ж��ź�
								// �˴����ƶ����̶߳��̶߳���
								//����MSC�����������ǲ����ɷ����ݵ�ʱ��,�����ŶӶ��ĳ�����һ����ȫ�������30��������ղ�����Ч���ݲŷ������¶��ġ�
							}
						} catch (UnsupportedEncodingException e) {
							logger.error("Base64�����쳣!"+e.getMessage());
						}
					}else{
						logger.info("���ط������̡߳�����������30������Ч���ݲŷ������¶���///////////////////////");
					}
				}else{
					// ���MSC�ж��ˣ�Ĭ����Ҫ���·�����
					logger.info("���ط������̡߳��޷�����MSC,����ʧ�ܣ�///////////////////////");
				}
			}catch(InterruptedException e){
				logger.error("�ط������߳��쳣,Exception:"+e.getMessage());
			}
		}
	}
}
