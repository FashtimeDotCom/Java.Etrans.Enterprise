/**
 * Users.java
 * Create on 2012-5-8下午13:43:12
 * Copyright (c) 2012 by e_trans.
 */
package com.etrans.bubiao.entities;

import java.io.Serializable;

/**
 * @author FengHai(冯海.)
 * @version 1.0
 * @since 2012-05-08
 */
public class WorkUnit implements Serializable {
 
	private static final long serialVersionUID = -7831505902529169852L;
	private Long id;
	/**
	 * 简称
	 */
	private String ShortName;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private String createDatetime;
	/**
	 * 创建人
	 */
	private Long createUserId;
	/**
	 * 最后修改时间
	 */
	private String modifyDatetime;
	/**
	 * 最后修改人
	 */
	private Long modifyUserId;
	/**
	 * 完整ID
	 */
	private String fullId;
	/**
	 * 是否为叶子节点
	 */
	private String isLeaf;
	/**
	 * 级别
	 */
	private String Level;
	/**
	 * 机构类别
	 */
	private Long organizationKindId;
	/**
	 * 行业类型
	 */
	private Long tradeKindId;
	
	private Long vehicleGroupId;
	public Long getVehicleGroupId() {
		return vehicleGroupId;
	}
	public void setVehicleGroupId(Long vehicleGroupId) {
		this.vehicleGroupId = vehicleGroupId;
	}
	/**
	 * 联系人
	 */
	private String linkMan;
	/**
	 * 电话
	 */
	private String phoneNo;
	/**
	 * 备用电话
	 */
	private String backupPhoneNo;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String postCode;
	/**
	 * 注册地
	 */
	private String regAddress;
	/**
	 * 管理用户
	 */
	private String adminUserId;
	/**
	 * 是否删除
	 */
	private String IsDeleted;
	/**
	 * 企业法人
	 */
	private String artificialPerson;
	/**
	 * 道路运输许可证号
	 */
	private String licenseNo;
	/**
	 * 道路运输许可证 核发机关
	 */
	private String licenseOrgan;
	/**
	 * 所属区域
	 */
	private Long areaId;
	/**
	 * 车辆总数
	 */
	private String vehicleSum;
	/**
	 * 经营范围
	 */
	private String businessScope;
	/**
	 * 备注
	 */
	private String memo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShortName() {
		return ShortName;
	}
	public void setShortName(String shortName) {
		ShortName = shortName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(String modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public Long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getFullId() {
		return fullId;
	}
	public void setFullId(String fullId) {
		this.fullId = fullId;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public Long getOrganizationKindId() {
		return organizationKindId;
	}
	public void setOrganizationKindId(Long organizationKindId) {
		this.organizationKindId = organizationKindId;
	}
	public Long getTradeKindId() {
		return tradeKindId;
	}
	public void setTradeKindId(Long tradeKindId) {
		this.tradeKindId = tradeKindId;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getBackupPhoneNo() {
		return backupPhoneNo;
	}
	public void setBackupPhoneNo(String backupPhoneNo) {
		this.backupPhoneNo = backupPhoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getRegAddress() {
		return regAddress;
	}
	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	public String getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getIsDeleted() {
		return IsDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		IsDeleted = isDeleted;
	}
	public String getArtificialPerson() {
		return artificialPerson;
	}
	public void setArtificialPerson(String artificialPerson) {
		this.artificialPerson = artificialPerson;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseOrgan() {
		return licenseOrgan;
	}
	public void setLicenseOrgan(String licenseOrgan) {
		this.licenseOrgan = licenseOrgan;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getVehicleSum() {
		return vehicleSum;
	}
	public void setVehicleSum(String vehicleSum) {
		this.vehicleSum = vehicleSum;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
