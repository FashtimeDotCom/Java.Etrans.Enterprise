package com.etrans.rmi;
/**��Ȩ���� (C) 2013-2023 �����ڳ̽�ͨ��Ϣ ��������Ȩ��*/

import java.util.Map;

import com.etrans.entity.AffixationBean;

/** 
 * ������Ϣ
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����9:45:32 
 */
public interface AffixationServer{
	
	public AffixationBean getAffixationBean(String vehicleId);
	/**
	 * ���ݳ���id��ȡʵʱ���ݵ����µ�˾����Ϣ
	 * 
	 * @param vehilceId
	 * @return Map<String, Object> 
	 */ 
	public Map<String, Object> getNewestDriverMessage(String vehilceId);
}

