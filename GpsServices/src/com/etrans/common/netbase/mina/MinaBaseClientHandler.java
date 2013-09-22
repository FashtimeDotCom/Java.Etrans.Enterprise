package com.etrans.common.netbase.mina;

import org.apache.mina.core.service.IoHandlerAdapter;

import com.etrans.mina.MinaConfigEntity;

/**
 * �켣����ͻ���ҵ���߼����������
 * 
 * @author Pomelo(����.)
 * @since 2012-12-27 14:00
 * @version 1.0
 */
public class MinaBaseClientHandler  extends IoHandlerAdapter {
	
	/** ������MSC�ɹ���־ */
	protected boolean LinkMscFlag = false;
	
	/** Mina����*/
	private MinaConfigEntity minaConfigEntity;
	
	/**
	 * ��ȡMina����
	 * 
	 * @return minaConfigEntity MinaConfigEntity
	 */
	public MinaConfigEntity getMinaConfigEntity() {
		return minaConfigEntity;
	}

	/**
	 * ����Mina����
	 * 
	 * @param minaConfigEntity
	 */
	public void setMinaConfigEntity(MinaConfigEntity minaConfigEntity) {
		this.minaConfigEntity = minaConfigEntity;
	}

	/**
	 * ���õ�½״̬
	 * 
	 * @param  LinkMscFlag  boolean
	 */ 
	public void setLinkMscFlag(boolean linkMscFlag) {
		LinkMscFlag = linkMscFlag;
	}
	
	/**
	 * ��ȡ�Ƿ�ɹ���½
	 * 
	 * @return  LinkMscFlag  boolean
	 */ 
	public boolean isLinkMscFlag() {
		return LinkMscFlag;
	}
}
