package com.etrans.entity;

import java.io.Serializable;

/** 
	�°汨��Э��
	sendBody := Format(
	0 '%d,' +                     //����ID                     4
    1 '%s,' +                     //����ʱ��                   8
    2 '%s,' +                     //������ʼʱ��               8
    3 '%s,' +                     //�������ο�ʼ����ʱ��       8
    4 '%d,' +                     //�������ͣ�1:�������� 2:���� 3:ƣ�ͣ�...                               4
    5 '%d,' +                     //������Դ��1:�����նˣ�2:��ҵ���ƽ̨��3:�������ƽ̨��5:PA; 9��������
    6 '%s,' +                     //������Դ����
    7 '%d,' +                     //�ⲿ������ϢID                                                        4
    8 '%s,' +                     //�磺ƣ�����ޡ���Ϣʱ�䡢��ǰ��ʻʱ�䡣 ���ֱ�������������ͬ           N
    9 '%.6f,'+                    //����            1/1E6      4
    10'%.6f,'+                    //γ��            1/1E6      4
    11'%d,' +                     //�������ٶ�      1km        1
    12'%d,' +                     //GPS�ٶ�         1km        1
    13'%d,' +                     //���������      0.1km      4
    14'%d,' +                     //GPS���         0.1km      4
    15'%d,' +                     //����            2dec       1
    16'%d,' +                     //���α�������               2
    17'%d,' +                     //�ۼƱ�������               4
  	18'%s',                       //״̬�� 32λ               32      sum = 57 + 32 + N
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-5 ����9:52:54 
 */
@SuppressWarnings("serial")
public class AlarmModel implements Serializable{
	
	private String startTime;//���ο�ʼ����ʱ��
	private String beginTime;//��ʼ����ʱ��
	private String speed1;//GPS�ٶ� 
	private String speed2;//�������ٶ�
	private String gpsMileage1;//GPS��� 
	private String gpsMileage2;//���������
	private String head;//����
	private String state;//״̬
	
	private String termialType;//�ն�����
	private String registrationNo; //���ƺ�
	private String registrationColor;//������ɫ
	private String alarmName; // �������ͣ�����������
	private String desc; //�������� 
	private String commNo;// �ն˺�
	private String alarmTypeId; // ��������Id
	private String vehicleId;// ����Id
	private String alarmTime; // ����ʱ��
	private String receveTime;//����ʱ��
	private String typeNo;
	private String longitude; // ����
	private String latitude;//γ��
	private String sourceID;//������ԴID
	private String sourceStr;//������Դ
	private String alarmInfoId;//�ⲿ������ϢID 
	
	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getHead()
	{
		return head;
	}

	public void setHead(String head)
	{
		this.head = head;
	}
	
	public String getGpsMileage1()
	{
		return gpsMileage1;
	}

	public void setGpsMileage1(String gpsMileage1)
	{
		this.gpsMileage1 = gpsMileage1;
	}
	
	public String getGpsMileage2()
	{
		return gpsMileage2;
	}

	public void setGpsMileage2(String gpsMileage2)
	{
		this.gpsMileage2 = gpsMileage2;
	}
	
	public String getSpeed1()
	{
		return speed1;
	}

	public void setSpeed1(String speed1)
	{
		this.speed1 = speed1;
	}
	
	public String getSpeed2()
	{
		return speed2;
	}

	public void setSpeed2(String speed2)
	{
		this.speed2 = speed2;
	}
	
	
	public String getRegistrationNo()
	{
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo)
	{
		this.registrationNo = registrationNo;
	}

	public String getAlarmName()
	{
		return alarmName;
	}

	public void setAlarmName(String alarmName)
	{
		this.alarmName = alarmName;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getCommNo()
	{
		return commNo;
	}

	public void setCommNo(String commNo)
	{
		this.commNo = commNo;
	}

	public String getAlarmTypeId()
	{
		return alarmTypeId;
	}

	public void setAlarmTypeId(String alarmTypeId)
	{
		this.alarmTypeId = alarmTypeId;
	}

	public String getVehicleId()
	{
		return vehicleId;
	}

	public void setVehicleId(String vehicleId)
	{
		this.vehicleId = vehicleId;
	}

	public String getAlarmTime()
	{
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime)
	{
		this.alarmTime = alarmTime;
	}

	public String getTypeNo()
	{
		return typeNo;
	}

	public void setTypeNo(String typeNo)
	{
		this.typeNo = typeNo;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public String getRegistrationColor() {
		return registrationColor;
	}

	public void setRegistrationColor(String registrationColor) {
		this.registrationColor = registrationColor;
	}

	public String getReceveTime() {
		return receveTime;
	}

	public void setReceveTime(String receveTime) {
		this.receveTime = receveTime;
	}

	public String getSourceID()
	{
		return sourceID;
	}

	public void setSourceID(String sourceID)
	{
		this.sourceID = sourceID;
	}

	public String getAlarmInfoId() {
		return alarmInfoId;
	}

	public void setAlarmInfoId(String alarmInfoId) {
		this.alarmInfoId = alarmInfoId;
	}

	public String getSourceStr() {
		return sourceStr;
	}

	public void setSourceStr(String sourceStr) {
		this.sourceStr = sourceStr;
	}

	public String getTermialType() {
		return termialType;
	}

	public void setTermialType(String termialType) {
		this.termialType = termialType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}	
}

