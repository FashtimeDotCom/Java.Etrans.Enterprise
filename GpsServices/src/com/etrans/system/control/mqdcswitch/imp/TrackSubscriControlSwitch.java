package com.etrans.system.control.mqdcswitch.imp;

import com.etrans.system.control.mqdcswitch.MqdcSwitch;

/**
 * �켣���Ŀ�������
 * 
 * @author Pomelo(����.)
 * @since 2012-12-27 11:14
 * @version 1.0
 */
public class TrackSubscriControlSwitch extends MqdcSwitch{

	@Override
	public void setMqServerIsCanOper(boolean mqServerIsCanOper) {
		super.mqServerIsCanOper = mqServerIsCanOper;
	}
}
