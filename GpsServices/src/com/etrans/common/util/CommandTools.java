package com.etrans.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���ù�����
 * 
 * @author dasuan 2010-7
 * 
 */
public class CommandTools {

	private static final Log log = LogFactory.getLog(CommandTools.class);

	/**
	 * ǰ��Ӷ���
	 * @return
	 */
	public static String cmmaStr(int commandCode){
		return ","+""+commandCode+",";
	}
	
	/**
	 * ����������̬��Ϣ
	 */
	public static String convertSwapVehicleInfo(String msg,String vehicleInfo)
			throws Exception {
		try{
			// ,,����
			// �ԣ�ע��ǰ�泵��ID�����Ͷ�Ϊ�գ�ֻչʾ�����ִ����ɣ�
			String[] msgArr = msg.split(",");
			String registrationNo ="";
			String registrationNoColor ="";
			if(!StringUtils.isEmpty(vehicleInfo)){
				String[] vehicleInfoArr = vehicleInfo.split("\\|");
				registrationNo = vehicleInfoArr[0];//���ƺ���;
				registrationNoColor = vehicleInfoArr[1];//������ɫ
			}
			StringBuffer str = new StringBuffer("");
			str.append("������");
			if(registrationNo!="" || registrationNoColor!=""){
				str.append("���ƺ��룺" + registrationNo)
				.append("��������ɫ��" + registrationNoColor);
			}
			str.append(" "+msgArr[2]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ�����������̬��Ϣ");
			return null;
		}
		
	}

	/**
	 * ����������λ��Ϣ��������
	 */
	public static String convertVehicleGpsSwap(String msg, String commandCode)
			throws Exception {
		try{
			// , ,���ƺ�:��A12345��������ɫ:��ɫ����������ԭ��: Ӧ��״̬�³�����λ��Ϣ�ش�
			// �����ͣ�
			// ��A12345����������λ��Ϣ����
			// ԭ��Ӧ��״̬�³�����λ��Ϣ�ش�
			// ��ע��ͨѶ���ͺͳ�IDΪ�ղ��ܴ˳�������񣬽��к���������չʾ����
			String[] msgArr = msg.split(",");
			StringBuffer str = new StringBuffer("");
			str.append("������" + msgArr[2]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ�����������λ��Ϣ��������");
			return null;
		}
	}

	/**
	 * ����������λ��Ϣ��������
	 */
	public static String convertOverVehicleGpsSwap(String msg,
			String commandCode) throws Exception {
		try{
			// , ,���ƺ�:��A12345��������ɫ:��ɫ����������ԭ��: ����������
			// �����ͣ�
			// ��A12345����������λ��Ϣ����
			// ԭ�򣺽���������
			// ��ע��ͨѶ���ͺͳ�IDΪ�ղ��ܴ˳�������񣬽��к���������չʾ����
			String[] msgArr = msg.split(",");
			StringBuffer str = new StringBuffer("");
			str.append("������" + msgArr[2]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ�����������λ��Ϣ��������");
			return null;
		}
	}

	/**
	 * ��������ʵʱ��λ��Ϣ
	 */
	public static String convertSwapVehicleGps(String msg,String vehicleInfo)
			throws Exception {
		try{
			// ע����λ���ݶ��ڲ����д��룬��ʱ�䡢��γ�ȵȣ���ҵ������Ϊ����������
			String[] msgArr = msg.split(",");
			String registrationNo ="";
			String registrationNoColor ="";
			if(!StringUtils.isEmpty(vehicleInfo)){
				String[] vehicleInfoArr = vehicleInfo.split("\\|");
				registrationNo = vehicleInfoArr[0];//���ƺ���;
				registrationNoColor = vehicleInfoArr[1];//������ɫ
			}
			StringBuffer str = new StringBuffer("");
			str.append("������");
			if(registrationNo!="" || registrationNoColor!=""){
				str.append("���ƺ��룺" + registrationNo)
				.append("��������ɫ��" + registrationNoColor);
			}
			str.append("��"+msgArr[2]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ���������ʵʱ��λ��Ϣ");
			return null;
		}

	}

	/**
	 * ������λ��Ϣ��������
	 */
	public static String convertVehicleGpsSwapSend(String msg,String vehicleInfo) throws Exception {
		try{
			// ע����λ���ݶ��ڲ����д��룬��ʱ�䡢��γ�ȵȣ���ҵ������Ϊ����������
			String[] msgArr = msg.split(",");
			String registrationNo ="";
			String registrationNoColor ="";
			if(!StringUtils.isEmpty(vehicleInfo)){
				String[] vehicleInfoArr = vehicleInfo.split("\\|");
				registrationNo = vehicleInfoArr[0];//���ƺ���;
				registrationNoColor = vehicleInfoArr[1];//������ɫ
			}
			StringBuffer str = new StringBuffer("");
			str.append("������");
			if(registrationNo!="" || registrationNoColor!=""){
				str.append("���ƺ��룺" + registrationNo)
				.append("��������ɫ��" + registrationNoColor);
			}
			str.append("��"+msgArr[2]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ�������λ��Ϣ��������");
			return null;
		}
	}

	/**
	 * ���Ͳ��
	 * 
	 */
	public static String convertChaGuan(String msg, String commandCode)
			throws Exception {
		try{
			// �������
			// ,,3,,12,�й��׶����ģ�
			// �����ͣ�
			// ��ڶ������ͣ��¼�ƽ̨����ҵ��
			// ��ڶ���ID��0x000000000000000000000000
			// �����ϢID��12
			// �����Ϣ���ݣ��й��׶����ģ�
			// )
			// ���Ӧ��
			// ,,3,431123987789,12,����
			// �����ͣ�
			// ��ڶ������ͣ��¼�ƽ̨����ҵ��
			// ��ڶ���ID��431123987789 (��Ϊҵ����Ӫ���֤��)
			// ��ϢID��12
			// ���Ӧ�����ݣ�����
			// )
			StringBuffer str = new StringBuffer("");
			String[] msgArr = msg.split(",");
			str.append("��ڶ������ͣ�" + Tools.flatMap.get(msgArr[2]))
			.append(",��ڶ���ID��" + msgArr[3])
			.append(",�����ϢID��" + msgArr[4])
			.append(",�����Ϣ���ݣ�" + msgArr[5])
			.append("@@@" + msgArr[2])// ��ڶ�������
			.append("#" + msgArr[3])// ��ڶ���ID
			.append("#" + msgArr[4]);// ��ϢID
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ� ���Ͳ��");
			return null;
		}
	}

	/**
	 * ���ͱ���
	 * 
	 */
	public static String convertPost(String msg, String commandCode)
			throws Exception {
		try{
			// ,,1,100000001002,232,���翪��
			// �����ͣ�
			// ���Ķ������ͣ���ǰ���ӵ��¼�ƽ̨
			// ���Ķ���ID��100000001002
			// ������ϢID��232
			// ������Ϣ���ݣ����翪��
			// )
			StringBuffer str = new StringBuffer("");
			String[] msgArr = msg.split(",");
			str.append("���Ķ������ͣ�" + Tools.flatPlatMap.get(msgArr[2]))
			.append(",���Ķ���ID��" + msgArr[3])
			.append(",������ϢID��" + msgArr[4])
			.append(",������Ϣ���ݣ�" + msgArr[5])
			.append("@@@");
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ� ���ͱ���");
			return null;
		}
	}

	/**
	 * ���ͱ�������
			// ������������
			// 800,112,1,3,20120626123030,2123,
			// 20120627123030,0,����,13912345678,TEST@TEST.COM
			// �����ͣ�
			// 0ͨѶ���ͣ�800
			// 1����ID��112
			// 2������Ϣ��Դ�������ն�
			// 3�������ͣ���������
			// 4����ʱ�䣺2012��6��26�� 12:30:30
			// 5��������ID��2123
			// 6�����ֹʱ�䣺2012��6��27�� 12:30:30
			// 7���켶�𣺽���
			// 8�����ˣ�����
			// 9������ϵ�绰��13912345678
			// 10������ϵ�����ʼ���TEST@TEST.COM
			// ��
	
			// ��������Ӧ��
			// 800,1224,13453,1
			// �����ͣ�
			// �ն�ͨѶ���ͣ�800
			// ����ID��1224
			// ��������ID��13453
			// �������������Ѵ������
			// )
	 */
	public static String convertOverseeing(String msg,String vehicleInfo) throws Exception {
		try{
			StringBuffer str = new StringBuffer("");
			String[] msgArr = msg.split(",");
			String registrationNo ="";
			String registrationNoColor ="";
			if(!StringUtils.isEmpty(vehicleInfo)){
				String[] vehicleInfoArr = vehicleInfo.split("\\|");
				registrationNo = vehicleInfoArr[0];//���ƺ���;
				registrationNoColor = vehicleInfoArr[1];//������ɫ
			}
			str.append("ͨѶ���ͣ�" + msgArr[0])
				.append("�����ƺ��룺" + registrationNo)
				.append("��������ɫ��" + registrationNoColor)
				.append("��������Ϣ��Դ��" + Tools.alarmSourceStrMap.get(msgArr[2]))
				.append("���������ͣ�" + Tools.alarmTypeMap.get(msgArr[3]))
				.append("������ʱ�䣺" + msgArr[4]).append(",�����ֹʱ��:" + msgArr[6])
				.append("�����켶��" + Tools.overseeingMap.get(msgArr[7]))
				.append("�������ˣ�" + msgArr[8]).append(",������ϵ�绰:" + msgArr[9])
				.append("��������ϵ�����ʼ���" + msgArr[10])
				.append("@@@" + msgArr[0])// �ն�ͨѶ����
				.append("#" + msgArr[1])// ����ID
				.append("#" + msgArr[5])// ��������ID
				.append("#" + msgArr[3])// ��������ID
				.append("#" + msgArr[4]);// ����ʱ��
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ� ���ͱ�������");
			return null;
		}
	}

	/**
	 * ����Ԥ��
	 */
	public static String convertAlarmAdvance(String msg,String vehicleInfo) throws Exception {
		try{
			// 800,112,3,6,20120626123030,ǰ���·��������
			// �����ͣ�
			// 0ͨѶ���ͣ�800
			// 1��ID��112
			// 2������Ϣ��Դ���������ƽ̨
			// 3�������ͣ�·�ζ�������
			// 4����ʱ�䣺2012��6��26�� 12:30:30
			// 5����������ǰ���·��������
			// ��
			String[] msgArr = msg.split(",");
			String registrationNo ="";
			String registrationNoColor ="";
			if(!StringUtils.isEmpty(vehicleInfo)){
				String[] vehicleInfoArr = vehicleInfo.split("\\|");
				registrationNo = vehicleInfoArr[0];//���ƺ���;
				registrationNoColor = vehicleInfoArr[1];//������ɫ
			}
			StringBuffer str = new StringBuffer("");
			str.append("������");
			if(registrationNo!="" || registrationNoColor!=""){
				str.append("ͨѶ���ͣ�" + msgArr[0])
				   .append("�����ƺ��룺" + registrationNo)
				   .append("��������ɫ��" + registrationNoColor);
			}
			str.append("��������Ϣ��Դ��" + Tools.alarmSourceStrMap.get(msgArr[2]))
			   .append("���������ͣ�" + Tools.alarmTypeMap.get(msgArr[3]))
			   .append("������ʱ�䣺" + msgArr[4])
			   .append("������������" + msgArr[5]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ� ����Ԥ��");
			return null;
		}
	}

	/**
	 * ʵʱ��������
	 */
	public static String convertRealSwapAlarm(String msg,String vehicleInfo) throws Exception {
		try{
			// 800,112,1,1,20120626123030,��������120km/h
			// �����ͣ�
			// 0ͨѶ���ͣ�800
			// 1��ID��112
			// 2������Ϣ��Դ�������ն�
			// 3�������ͣ����ٱ���
			// 4����ʱ�䣺2012��6��26�� 12:30:30
			// 5������������������120km/h
			// ��
			String[] msgArr = msg.split(",");
			String registrationNo ="";
			String registrationNoColor ="";
			if(!StringUtils.isEmpty(vehicleInfo)){
				String[] vehicleInfoArr = vehicleInfo.split("\\|");
				registrationNo = vehicleInfoArr[0];//���ƺ���;
				registrationNoColor = vehicleInfoArr[1];//������ɫ
			}
			StringBuffer str = new StringBuffer("");
			str.append("������");
			if(registrationNo!="" || registrationNoColor!=""){
				str.append("ͨѶ���ͣ�" + msgArr[0])
				   .append("�����ƺ��룺" + registrationNo)
				   .append("��������ɫ��" + registrationNoColor);
				
			}
			str.append(",������Ϣ��Դ:" + Tools.alarmSourceStrMap.get(msgArr[2]))
			   .append(",��������:" + Tools.alarmTypeMap.get(msgArr[3]))
			   .append(",����ʱ��:" + msgArr[4])
			   .append(",��������:" + msgArr[5]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ�ʵʱ��������");
			return null;
		}
	}

	/**
	 * �ϼ��·�Ӧ����Ϣͨ��������������ָ�	
	 */
	public static String convertUpCustomAnswer(String msg,String commandCode){
		try{
			//		,, 37385,�ɹ����ϼ�ƽ̨���ɲ���
			//		����������λ��ϢӦ�𣺳ɹ����ϼ�ƽ̨���ɲ���
			//		ע������ID���ն����;�Ϊ�ա�
			String[] msgArr = msg.split(",");
			StringBuffer str = new StringBuffer("");
			str.append("�ϼ��·�Ӧ�����ͣ�"+Tools.upAnswerMap.get(msgArr[2])).append(",������"+msgArr[3]);
			return str.toString();
		}catch(Exception e){
			log.equals("�������ͣ��ϼ��·�Ӧ����Ϣͨ��������������ָ�");
			return null;
		}
	}
}


