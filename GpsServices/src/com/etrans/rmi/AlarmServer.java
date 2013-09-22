package com.etrans.rmi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.etrans.entity.AlarmModel;

/** 
 * ������Ϣ��ȡԶ�̽ӿ�
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����9:48:36 
 */
public interface AlarmServer{
	
	/**
	 * ��ȡʵʱ����
	 * 
	 * @param alarmTypeNo
	 * @param ls
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getRealTimeVehicleAlarmTest(
			String alarmTypeNo, List<HashMap<String, String>> ls)
			throws Exception;
	/**
	 * ��ȡʵʱ����
	 * 
	 * @param alarmTypeNo
	 * @param alarmSourceNo
	 * @param registrationNo
	 * @return List<AlarmModel>
	 */
	public List<AlarmModel> getRealTimeAlarm(String alarmTypeNo,String alarmSourceNo,String registrationNo,Map<String,String> vehicleInfoMap,Map<String, String> processedAlarMap);
	
	/**
	 * ��ȡ�û�����ʵʱ����
	 * 
	 * @param alarmTypeNo
	 * @param ls
	 * @return ������Ϣ�б� List<Map<String,String>>
	 */
	public List<Map<String,String>> getRealTimeVehicleAlarm(String alarmTypeNo,List<HashMap<String,String>> ls);
	
	/**
	 * �ж����Ƿ���ڱ���[�������б���]
	 * 
	 * @param processedAlarMap
	 * @return true �б���,false�ޱ���
	 */
	public boolean findIsHaveAlarm(Map<String, String> processedAlarMap);
	
	/**
	 * �ж��Ƿ��б���[����ָ���]
	 * 
	 * @param ProcessedAlarMap
	 * @param vehicleMap
	 * @return  true �б���,false�ޱ���
	 */
	public boolean findIsHaveAlarm(Map<String, String> ProcessedAlarMap,Map<String, String> vehicleMap);
}



