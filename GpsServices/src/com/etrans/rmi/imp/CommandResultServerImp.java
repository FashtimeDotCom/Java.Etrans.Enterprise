package com.etrans.rmi.imp;

import java.util.Queue;

import com.etrans.business.queue.ResultMaps;
import com.etrans.rmi.CommandResulServer;

/** 
 * ָ��ظ��������ӿ�ʵ����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����2:38:42 
 */
public class CommandResultServerImp implements CommandResulServer {
 
	/**
	 * ��ȡͼƬ·��
	 * 
	 * @param vehicleId ����ID
	 * @return msg ͼƬ·��
	 */
	@Override
	public String findPictureResult(String vehicleId) {
		String msg = ResultMaps.specialCommandResultMap.get(vehicleId);
		ResultMaps.specialCommandResultMap.remove(vehicleId);
		return msg;
	}
	
	
	/**
	 * ƽ̨��Ϣ
	 * 
	 * @return  ResultMaps.lowerFlatQueue
	 */
	@Override
	public Queue<String> getLowerFlatQueue() {
		return ResultMaps.lowerFlatQueue;
	}


	/**
	 * ͨ������ID��ȡָ��ظ�����
	 * 
	 * @param vehicleId 
	 * @return �˳�����ָ��ظ�
	 */
	public String getCommandResult(String vehicleId){
		 String msg = ResultMaps.commandResultHashMap.get(vehicleId);
		 ResultMaps.commandResultHashMap.remove(vehicleId);//
		 return msg;
	}

	/**
	 * �ж��Ƿ����¼�ƽ̨
	 * 
	 * @return boolean false,�ޣ�true ,��
	 */
	@Override
	public boolean isLowerPlatFormInfo() {
		if (ResultMaps.lowerFlatQueue == null || ResultMaps.lowerFlatQueue.size() <= 0)return false;
		return true;
	}

	
}

