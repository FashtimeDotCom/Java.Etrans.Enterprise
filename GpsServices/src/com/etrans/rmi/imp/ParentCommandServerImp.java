package com.etrans.rmi.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.etrans.business.CommandCode;
import com.etrans.business.queue.ResultMaps;
import com.etrans.business.service.message.MinaSendMessageParent;
import com.etrans.common.util.CommandTools;
import com.etrans.rmi.ParentCommandServer;

/** 
 * �ϼ�ƽָ̨�����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-16 ����4:41:03 
 */
public class ParentCommandServerImp implements ParentCommandServer {

	
	/**
	 * ƽ̨����ָ��
	 * 
	 * @param message ��Ϣ
	 */
	@Override
	public void insertCommandSendQueue(String message) throws Exception {
		MinaSendMessageParent.send(message);
	}

	/**
	 * ��ȡָ��ظ�
	 * 
	 * @param vehicleId ����ID
	 */
	@Override
	public String getCommandResult(String vehicleId) {
		return ResultMaps.parentCommandResultHashMap.get(vehicleId);
	}

	/**
	 * ��������ȡ�ϼ���Ϣ
	 * 
	 * @param vehicleMap Map<String,String>
	 */
	@Override
	public List<String> getPlatFormInfo(Map<String, String> vehicleMap) {
		String[] strings = ResultMaps.flatQueue.toArray(new String[]{});
		List<String> lst = null;
		String vehicleInfo;
		if (null != strings){
			lst = new ArrayList<String>();
			for (String str : strings){
				String[] msg = str.split("\\|");
				int commandCode = Integer.parseInt(msg[0]);
				vehicleInfo =  vehicleMap.get(msg[1]);
				try{
					switch(commandCode){
						case CommandCode.COMMAND_7106://������������
							lst.add(msg[0]+"|"+CommandTools.convertOverseeing(msg[1],vehicleInfo)+"|"+msg[2]);
							break;
						case CommandCode.COMMAND_7107://����Ԥ��
							lst.add(msg[0]+"|"+CommandTools.convertAlarmAdvance(msg[1],vehicleInfo)+"|"+msg[2]);
							break;
						case CommandCode.COMMAND_7108://ʵʱ��������
							lst.add(msg[0]+"|"+CommandTools.convertRealSwapAlarm(msg[1],vehicleInfo)+"|"+msg[2]);
							break;
						case CommandCode.COMMAND_7109://����������̬��Ϣ
							lst.add(msg[0]+"|"+CommandTools.convertSwapVehicleInfo(msg[1],vehicleInfo)+"|"+msg[2]);
							break;
						case CommandCode.COMMAND_7113://������λ��Ϣ��������
							lst.add(msg[0]+"|"+CommandTools.convertVehicleGpsSwapSend(msg[1],vehicleInfo)+"|"+msg[2]);
							break;
						case CommandCode.COMMAND_7112://��������ʵʱ��λ��Ϣ
							lst.add(msg[0]+"|"+CommandTools.convertSwapVehicleGps(msg[1],vehicleInfo)+"|"+msg[2]);
							break;
						default:
							lst.add(str);
						}
				}catch (Exception e) {
				}
			}
		}
		return lst;
	}

	/**
	 * ��ȡ�ϼ���Ϣ��С
	 * 
	 * @return
	 */
	public int getFlatQueueSize(){
		return ResultMaps.flatQueue.size();
	}
}

