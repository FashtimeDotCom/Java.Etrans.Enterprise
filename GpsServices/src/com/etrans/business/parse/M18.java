package com.etrans.business.parse;

import java.util.Map;

import com.etrans.entity.AffixationBean;

/** 
 * ������Ϣ
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����6:23:17 
 */
public class M18 implements M {
	
	private AffixationBean affixationBean = new AffixationBean();
	private String[] business;
	private String decoderStr;
 
	/**
	 * ������Ϣ
	 * 
	 * @param message    String    base64���ܺ�ı�����Ϣ
	 * @param producQue  EQueue    ��������
	 * @param resultMap  HashMap<String,String>  ����������
	 */
	@Override
	public void parse(String[] message,Map<String, Object> resultMap) throws Exception {
		business = message;
		decoderStr =business[3].toString();
		affixationBean=getaffixationHashMap(business[2],decoderStr,resultMap);
		resultMap.put(business[2].toString(), affixationBean);
	}
	
	/***
	 * �������ݵ�
	 * 
	 * vehicleId ����id
	 * decoderStr ��������
	 * @return
	 */
	public AffixationBean getaffixationHashMap(String vehicleId,String decoderStr,Map<String, Object> resultMap){
		AffixationBean affixationBean = (AffixationBean)resultMap.get(vehicleId);
		if(affixationBean==null){
			affixationBean = new AffixationBean();
			addAffixationBeanInfoByTypeId(decoderStr,affixationBean);
		}else{
			addAffixationBeanInfoByTypeId(decoderStr,affixationBean);
		}
		return affixationBean;
	}	
	
	/**
	 * ���ݸ�����������id����affixationBeanʵ��
	 * 
	 * decoderStr ��������
	 * affixationBean ��������ʵ��
	 */
	public void addAffixationBeanInfoByTypeId(String decoderStr,AffixationBean affixationBean){		
		String[] val = decoderStr.split(",");
		String typeId = val[2];//��������
		String value="";//��������
		if(val.length==4){
			value = val[3];
		}
		affixationBean.setVehicleID(val[0]);//����id
		affixationBean.setDate(val[1]);//�ϴ�ʱ��
		if(typeId.equals("9")){ //˾��IC����
			affixationBean.setDriverIC(value);
		}else if(typeId.equals("10")){//˾����ʻ֤��
			affixationBean.setDrivingLicence(value);
		}else if(typeId.equals("11")){//˾������
			affixationBean.setDrivinName(value);
		}else if(typeId.equals("12")){//�˹�ȷ�ϱ����¼���ID
			affixationBean.setAlarmAffairID(value);
		}else if(typeId.equals("13")){//���ٱ���
			affixationBean.setOverspeedAlarm(value);
		}else if(typeId.equals("14")){//��������
			affixationBean.setTurnoverArea(value);
		}else if(typeId.equals("18")){//�ն�˾�����
			affixationBean.setZdDriverCode(value);
		}else if(typeId.equals("19")){// �ն��Ƿ����IC��
			affixationBean.setZdWhetherIC(value);
		}else if(typeId.equals("20")){ //������Ϣ�����ַ���
			affixationBean.setFjInfo(value);
		}
	}
	/**
	 * ��ȡָ�����Э������
	 * 
	 * @return
	 */
	public String getName(){
		return "��ָ���������͡�������������Ϣ��";
	}
}

