package com.etrans.rmi.imp;

import java.util.HashMap;
import java.util.Map;

import com.etrans.business.queue.ResultMaps;
import com.etrans.entity.AffixationBean;
import com.etrans.rmi.AffixationServer;

/** 
 * ������Ϣ����ӿ�ʵ����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����2:37:52 
 */
public class AffixationServerImp implements AffixationServer {

	/**
	 * ͨ������id�Ż�ȡ������Ϣ
	 * 
	 * @param vehicleId ,����id
	 * @return ������Ϣʵ��bean
	 * */
	public AffixationBean getAffixationBean(String vehicleId){
		return ResultMaps.affixationHashMap.get(vehicleId);
	}
	
	
	/**
	 * ���ݳ���id��ȡʵʱ���ݵ����µ�˾����Ϣ
	 * 
	 * @param vehilceId String
	 * @return result  Map<String, Object>
	 */
	@Override
	public Map<String, Object> getNewestDriverMessage(String vehilceId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			AffixationBean affixationBean = this.getAffixationBean(vehilceId);
			if(affixationBean!=null){
				result.put("name", affixationBean.getDrivinName()==null?"":affixationBean.getDrivinName().toString());
				result.put("driverIC", affixationBean.getDriverIC()==null?"":affixationBean.getDriverIC().toString());
				result.put("drivingLicence", affixationBean.getDrivingLicence()==null?"":affixationBean.getDrivingLicence().toString());
				result.put("zdDriverCode", affixationBean.getZdDriverCode()==null?"":affixationBean.getZdDriverCode().toString());
				result.put("zdWhetherIC", affixationBean.getZdWhetherIC()==null?"":affixationBean.getZdWhetherIC().toString());
			}
		} catch (Exception e) {}
		return result;
	}
}

