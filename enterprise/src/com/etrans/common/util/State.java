/**    
 * State.java
 * Create on 2011-6-27
 * Copyright (c) 2010 by e_trans. 
 */
package com.etrans.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * @author lihaiyan
 * @version 1.0
 * @brief
 */

public class State {
	public static Map<Integer, String> stateMap = new HashMap<Integer, String>();
	static {
		// 初始化终端状态
		
		/*
		stateMap.put(0, "ACC关|ACC开");
		stateMap.put(1, "未定位|定位");
		stateMap.put(2, "北纬|南纬");
		stateMap.put(3, "东经|西经");
		stateMap.put(4, "停营状态|运营状态");
		stateMap.put(5, "经纬度未经保密插件加密|经纬度已经保密插件加密");
		stateMap.put(6, "车辆油路正常|车辆油路断开");
		stateMap.put(7, "车辆电路正常|车辆电路断开");
		stateMap.put(8, "车门解锁|车门加锁");
		*/
		stateMap.put(7, "ACC关|ACC开");//第0位
		stateMap.put(6, "未定位|定位");//第1位
		stateMap.put(5, "北纬|南纬");//第2位
		stateMap.put(4, "东经|西经");//第3位
		stateMap.put(3, "运营状态|停运状态");//第4位
		stateMap.put(2, "经纬度未经保密插件加密|经纬度已经保密插件加密");//第5位
		stateMap.put(1, "车辆油路正常|车辆油路断开");//第6位
		stateMap.put(0, "车辆电路正常|车辆电路断开");//第7位
		
		stateMap.put(15, "车门解锁|车门加锁");//第8位
		
		stateMap.put(29, "紧急报警");//第26位
		stateMap.put(28, "超速报警");//第27位
		stateMap.put(27, "疲劳驾驶");//第28位
		stateMap.put(26, "预警");//第29位
		stateMap.put(25, "GNSS模块发生故障");//第30位
		stateMap.put(24, "GNSS天线未接或被剪断");//第31位
		
		stateMap.put(39, "GNSS天线短路");//第32位
		stateMap.put(38, "终端主电源欠压");//第33位
		stateMap.put(37, "终端主电源掉电");//第34位
		stateMap.put(36, "终端LCD或显示器故障");//第35位
		stateMap.put(35, "TTS模块故障 ");//第36位
		stateMap.put(34, "摄像头故障 ");//第37位
		stateMap.put(33, "当天累计驾驶超时");//第38位
		stateMap.put(32, "超时停车");//第39位
		
		stateMap.put(47, "进出区域");//第40位
		stateMap.put(46, "进出路线");//第41位
		stateMap.put(45, "路段行驶时间不足/过长");//第42位
		stateMap.put(44, "路线偏离报警");//第43位
		stateMap.put(43, "车辆VSS故障");//第44位
		stateMap.put(42, "车辆油量异常");//第45位
		stateMap.put(41, "车辆被盗");//第46位
		stateMap.put(40, "车辆非法点火");//第47位
		
		stateMap.put(55, "车辆非法位移");//第48位
		stateMap.put(54, "碰撞侧翻报警");//第49位
		
		stateMap.put(82, "未使用GPS卫星|使用GPS卫星定位");//第82位
		stateMap.put(83, "未使用北斗卫星定位|使用北斗卫星定位");//第83位
		stateMap.put(84, "自定义1路高传感器状态为低|自定义1路高传感器状态为高");//第84位
		stateMap.put(85, "自定义2路高传感器状态为低|自定义2路高传感器状态为高");//第85位
		stateMap.put(86, "自定义1路高传感器状态为高|自定义1路低传感器状态为低");//第86位
		stateMap.put(87, "自定义2路高传感器状态为高|自定义2路低传感器状态为低");//第87位
	}

	/**
	 * 解析用户订制的终端状态
	 * @param gpsInfoMessageState ,终端状态
	 * */
	public static String getTermianlState( String gpsInfoMessageState) {
		//gpsInfoMessageState=Tools.getByteHex(gpsInfoMessageState);//16进制字符转成16进制ASCII码
		StringBuffer stateStr = new StringBuffer();
		// 终端状态解析
		String terminalStateBinary = getTerminalStateBinary(gpsInfoMessageState);
        String gpsStateString=getGpsState(terminalStateBinary, stateMap);
        gpsStateString=gpsStateString.substring(0,gpsStateString.length()-1);
		String alarmStateString=getAlarmsState(terminalStateBinary, stateMap);
		if(StringUtils.isEmpty(alarmStateString)){
			alarmStateString="当前没有报警";
		}else{
			alarmStateString=alarmStateString.substring(0,alarmStateString.length()-1);
		}
		stateStr.append(gpsStateString);
		stateStr.append("|")
		        .append(alarmStateString);

        String stateString=stateStr.toString();
		return stateString;
	}


	
	
	
	/**
	 * 将16进制的终端状态转换成2进制
	 * @param gpsInfoMessageState ,16进制的终端状态
	 * @return 2进制的终端状态
	 * */
	public static String getTerminalStateBinary(String gpsInfoMessageState) 
	{
		StringBuffer terminalState = new StringBuffer();
		for (int i = 0; i < gpsInfoMessageState.length(); i = i + 2) 
		{
			String temp = Long.toBinaryString(Long.parseLong(Long.valueOf(gpsInfoMessageState.substring(i, i + 2), 16).toString()));
			terminalState.append("00000000".substring(0, (8 - temp.length())) + temp);
		}

		return terminalState.toString();
	}

	/**
	 * 解析用户所订制的终端状态结果
	 * @param terminalStateBinary ,2进制的终端状态
	 * @param stateMap ,终端状态的MAP
	 * @param userTerminalState ,用户订制的终端状态
	 * @return 用户所订制的终端状态结果
	 * */
	public static String getGpsState(String terminalStateBinary, Map<Integer, String> stateMap) {
		StringBuffer stateStr = new StringBuffer();
		char[] terminalStateArray = terminalStateBinary.toCharArray();
		for (int s =0; s<16; s++) 
		{
			if (null != stateMap.get(s)) {
				   stateStr.append(String.valueOf(stateMap.get(s)).split("\\|")[Integer.parseInt(String.valueOf(terminalStateArray[s]))]);
				   stateStr.append(",");  
				  
					
			}
		}
		return stateStr.toString();
	}
	
	
	/**
	 * 解析报警状态
	 * @param terminalStateBinary ,2进制的终端状态
	 * @param stateMap ,终端状态的MAP
	 * @param userTerminalState ,用户订制的终端状态
	 * @return 用户所订制的终端状态结果
	 * */
	public static String getAlarmsState(String terminalStateBinary, Map<Integer, String> stateMap) {
		StringBuffer stateStr = new StringBuffer();
		char[] terminalStateArray = terminalStateBinary.toCharArray();
		for (int s =24; s<=55; s++) 
		{
			if (null != stateMap.get(s)) {
				 if(Integer.parseInt(String.valueOf(terminalStateArray[s]))==1){
					   stateStr.append(stateMap.get(s));
					   stateStr.append(",");  
				   }
			}
		}
		for (int s =82; s<=87; s++) 
		{
			if (null != stateMap.get(s)) {
				if(Integer.parseInt(String.valueOf(terminalStateArray[s]))==1){
					stateStr.append(String.valueOf(stateMap.get(s)).split("\\|")[Integer.parseInt(String.valueOf(terminalStateArray[s]))]);
					stateStr.append(",");
				}
			}
		}
		return stateStr.toString();
	}
	
	
	
	public static void main(String args[])
	{
//	     for(int i=0;i<10;i++){
//	   L:  if(i==5)
//    	   {
//    		  break L;
//    	   }
//    	   System.out.println(i);
//		}
		
		/**16进制转换成十进制再转换成二进制数**/
//		String gpsInfoMessageState = "4100000000000000000000000000000000000000000000000000000000000000";
		String gpsInfoMessageState = "41";
		
		StringBuffer terminalState = new StringBuffer();
		for (int i = 0; i < gpsInfoMessageState.length(); i = i + 1) 
		{
			String ss = gpsInfoMessageState.substring(i, i + 1);
			String aa = Long.valueOf(ss, 16).toString();
			Long bb = Long.parseLong(aa);
			String temp = Long.toBinaryString(bb);
			terminalState.append("0000".substring(0, (4 - temp.length())) + temp);
		}

		System.out.println(terminalState.toString());
//		
//		/**16进制转换成二进制数**/
//		String str="41";
//		int i=Integer.parseInt(str,16);
//		String str2=Integer.toBinaryString(i);
//		System.out.println(str2);
//		
//		String terminalStateBinary = "01000001";
//		char[] terminalStateArray = terminalStateBinary.toCharArray();
//		String ee = String.valueOf(terminalStateArray[0]);
//		System.out.println(ee);
	} 
	
	

}
