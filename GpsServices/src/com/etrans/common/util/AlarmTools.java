package com.etrans.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.etrans.entity.AlarmModel;

/** 
 * ����������
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����4:55:37 
 */
public class AlarmTools {
	
	/**
	 * �������
	 */
	 private static Map<String,String> alarmMap=new HashMap<String,String>();
	 
	 /**
	  * ������Դ
	  */
	 private static Map<String, String> alarmSourceMap=new HashMap<String,String>();
	 
	 static{
		 alarmMap.put("2","���ٱ���");
		 alarmMap.put("3","ƣ�ͼ�ʻ����");
		 alarmMap.put("1","��������");
		 alarmMap.put("4","Ԥ��");
		 alarmMap.put("5","GNSSģ�鷢������");
		 alarmMap.put("6","GNSS����δ�ӻ򱻼���");
		 alarmMap.put("7","GNSS���߶�·");
		 alarmMap.put("8","�ն�����ԴǷѹ");
		 alarmMap.put("9","�ն�����Դ����");
		 alarmMap.put("10","�ն�LCD����ʾ������");
		 alarmMap.put("11","TTSģ�����");
		 alarmMap.put("12","����ͷ����");
		 alarmMap.put("13","�����ۼƼ�ʻ��ʱ");
		 alarmMap.put("14","��ʱͣ��");
		 alarmMap.put("15","��������");
		 alarmMap.put("16","����·��");
		 alarmMap.put("17","·����ʻʱ�䲻��/����");
		 alarmMap.put("18","·��ƫ�뱨��");
		 alarmMap.put("19","����VSS����");
		 alarmMap.put("20","���������쳣");
		 alarmMap.put("21","��������");
		 alarmMap.put("22","�����Ƿ����");
		 alarmMap.put("23","�����Ƿ�λ��");
		 alarmMap.put("24","��ײ�෭����");
		 alarmMap.put("315", "���ٱ���");
		 alarmMap.put("316", "ƣ�ͼ�ʻ����");
		 alarmMap.put("317", "�ص㳬ʱͣ������");
		 alarmMap.put("318", "�����ص㱨��");
		 alarmMap.put("319", "�������򱨾�");
		 alarmMap.put("320", "·��ƫ�뱨��");
		 alarmSourceMap.put("1", "�����ն�"); 
		 alarmSourceMap.put("2", "��ҵ���ƽ̨"); 
		 alarmSourceMap.put("3", "�������ƽ̨"); 
		 alarmSourceMap.put("5","ƽ̨");
		 alarmSourceMap.put("9", "����"); 
		 alarmSourceMap.put("10", "ƽ̨����");
	 }
	 
	/**
	 * �ѳ����ı�������ת�г�List�ı�������
	 * 
	 * @param vehicleQueue
	 * @return
	 */
	public static AlarmModel alarmQueueTypeToList(String[] strs,String vehicleInfo,String alarmTime) throws Exception{ 
		String[] vehicleInfoArr = vehicleInfo.split("\\|");
		String alarmNamString = "��������";
		AlarmModel alarm = new AlarmModel();
		alarm.setBeginTime(strs[2]);
		alarm.setStartTime(strs[3]);
		alarm.setSpeed1(strs[12]);
		alarm.setSpeed2(strs[11]);
		alarm.setGpsMileage1(strs[14]);
		alarm.setGpsMileage2(strs[13]);
		alarm.setHead(strs[15]);
		alarm.setState(strs[18]);
		alarm.setVehicleId(strs[0]);// ����ID
		alarm.setRegistrationNo(vehicleInfoArr[0]);// ���ƺ���
		alarm.setRegistrationColor(vehicleInfoArr[1]);// ������ɫ
		String alarmNamTypeId = strs[4];// ����ID
		if (StringUtils.isNotEmpty(alarmMap.get(alarmNamTypeId))) {
			alarmNamString = alarmMap.get(alarmNamTypeId);
		}
		alarm.setAlarmName(alarmNamString);// ��������
		alarm.setAlarmTypeId(alarmNamTypeId);// ��������Id
		Float longitude = Float.valueOf(strs[9]) * 1000000;
		Float latitude = Float.valueOf(strs[10]) * 1000000;
		alarm.setLongitude(String.valueOf(longitude));// ����
		alarm.setLatitude(String.valueOf(latitude));// γ��
		alarm.setAlarmTime(strs[1]);// ����ʱ��
		alarm.setReceveTime(alarmTime);// ����ʱ��
		alarm.setAlarmInfoId(strs[7]);// �ⲿ������ϢID
		String sourceStr = "����";
		String sourceId = strs[5];
		alarm.setSourceID(sourceId);// ������Դ
		if (StringUtils.isNotEmpty(alarmSourceMap.get(sourceId))) {
			sourceStr = alarmSourceMap.get(sourceId);
		}
		alarm.setSourceStr(sourceStr);// ������Դ����
		alarm.setDesc(strs[8]);// ��������
		return alarm;
	}
	
	/**
	 * ��֤�Ƿ��Ѿ������˴˱�����Ϣ
	 * 
	 * @param alarmMapKey keyֵ���ɡ�����ID-����ʱ��-�������͡���϶���
	 * @param processedAlarMap Map<String, String>
	 * @return true �Ѿ�����falseδ����
	 */
	public static boolean isProcessedAlar(String alarmMapKey,Map<String, String> processedAlarMap){
		if(processedAlarMap==null){ //δ����
			return true;
		}else{
			String alarmStr2 = processedAlarMap.get(alarmMapKey);
			if(null==alarmStr2||alarmStr2.equals("")){//�Ѵ���
				return true;
			}else{//�Ѿ�����
				return false;
			}
		}
	}
}

