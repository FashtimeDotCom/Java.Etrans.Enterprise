package com.etrans.business.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.etrans.entity.AffixationBean;

/** 
 * ��Ž����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-9 ����5:52:58 
 */
public class ResultMaps {
	public static ConcurrentHashMap<String, String> gpsInfoHashMap = new ConcurrentHashMap<String, String>();
	public static HashMap<String,AffixationBean> affixationHashMap = new HashMap<String,AffixationBean>();
	public static HashMap<String, EQueue>  alarmHashMap = new HashMap<String, EQueue>();
	public static HashMap<String,String> commandResultHashMap = new HashMap<String,String>();
	public static HashMap<String,String> specialCommandResultMap = new HashMap<String,String>();
	public static ConcurrentHashMap<String, String>  billMap = new ConcurrentHashMap<String, String>();
	public static Queue<String> lowerFlatQueue = new LinkedList<String>();
	public static Queue<String> flatQueue = new LinkedList<String>();
	public static HashMap<String,String> parentCommandResultHashMap = new HashMap<String,String>();
}

