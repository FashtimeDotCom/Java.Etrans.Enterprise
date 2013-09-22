/**
 * LoginServices.java
 * Create on 2012-1-11下午02:58:47
 * Copyright (c) 2012 by e_trans.
 */
package com.etrans.bubiao.services;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.bubiao.entities.LoginLog;
import com.etrans.bubiao.entities.User;
import com.etrans.bubiao.entities.WorkUnit;
import com.etrans.common.util.encrypt.CsEncodeUtils;
import com.etrans.common.util.web.Struts2Utils;

/**
 * @author Ivan
 * @version 1.0
 * @briefs 登录Services
 */

@Service
public class LoginServices {

	@Autowired
	private IbatisServices ibatisServices;
	
	public void setIbatisServices(IbatisServices ibatisServices)
	{
		this.ibatisServices = ibatisServices;
	}
	
	/**
	 * 根据单位/企业ID查询企业信息
	 * 
	 * @param workUnitId
	 * @return WorkUnit
	 */
	public WorkUnit queryUserWorkUnit(Long workUnitId){
		WorkUnit workUnit = null;
		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("id", workUnitId);
		workUnit = this.ibatisServices.queryForObject(WorkUnit.class, "getWorkUnitById",param);
		return workUnit;
	}
	
	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public User queryUser(String userName, String password) throws Exception {

		User user = null;

		Map<String, Object> param = new HashMap<String, Object>(2);
		param.put("userName", userName);
		param.put("password", CsEncodeUtils.Encrypt(password));

		List<User> list = this.ibatisServices.queryForList(User.class, "checkUserLogin",param);
		if (list!=null && list.size()>0) {
			user = list.get(0);
		}

		return user;

	}
	
	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码(MD5)
	 * @return
	 * @throws Exception
	 */
	public User queryUserTA(String userName, String password) throws Exception {

		User user = null;

		Map<String, Object> param = new HashMap<String, Object>(2);
		param.put("userName", userName);
		param.put("password", password);

		List<User> list = this.ibatisServices.queryForList(User.class, "checkUserLogin",param);
		if (list!=null && list.size()>0) {
			user = list.get(0);
		}

		return user;

	}

	/**
	 * 记录用户登录日志
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public LoginLog loginLog(Long userId) throws Exception {
		LoginLog loginLog = new LoginLog();
		Map<String,Object> valueMap = new HashMap<String,Object>();
		valueMap.put("userID", userId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		Date date =calendar.getTime();
		String logoutTime = format.format(date);
		valueMap.put("logonTime", logoutTime);
		valueMap.put("islogin", 0);
		valueMap.put("issucessful", 0);
		
//		Map<String, String> sysmap = System.getenv();	    
//		String logonHost = sysmap.get("COMPUTERNAME");// 获取来访者计算机名	   
//		valueMap.put("logonHost", logonHost);
//		InetAddress addr = InetAddress.getLocalHost();
//		valueMap.put("logonIP", addr.getHostAddress().toString());//获取来访者ip;
		

		
//		String logonHost=null;
//		String ip =  Struts2Utils.getRequest().getHeader("x-forwarded-for"); 
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		    ip =  Struts2Utils.getRequest().getHeader("PRoxy-Client-IP"); 
//		} 
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		    ip =  Struts2Utils.getRequest().getHeader("WL-Proxy-Client-IP"); 
//		} 
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		    ip =  Struts2Utils.getRequest().getRemoteAddr(); 
//		} 
//		InetAddress a; 
//		try {  
//		   a = InetAddress.getLocalHost(); 
//		   logonHost=a.getByName(ip).getHostName();
//		
//		} catch (Exception e) { 
//		} 

		
		
		String ip =null;
		String logonHost=null;
		ip = Struts2Utils.getRequest().getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = Struts2Utils.getRequest().getHeader("Proxy-Client-IP");
		    if(ip==null){
		    	logonHost=null;
		    }else{
		    	logonHost=Struts2Utils.getRequest().getRemoteHost().toString();
		    }
	     }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = Struts2Utils.getRequest().getHeader("WL-Proxy-Client-IP");
		    if(ip==null){
		    	logonHost=null;
		    }else{
		    	logonHost=Struts2Utils.getRequest().getRemoteHost().toString();
		    }
	     }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = Struts2Utils.getRequest().getRemoteAddr().toString();
	    	if(ip==null){
	    		logonHost=null;
		    }else{
		    	logonHost=Struts2Utils.getRequest().getRemoteHost().toString();
		    }
	    }
	    if(ip.equals("127.0.0.1")){
	    	InetAddress addr = InetAddress.getLocalHost();
	    	ip = addr.getHostAddress().toString();
	    	if(ip==null){
		    	logonHost=null;
		    }else{
		    	logonHost= InetAddress.getByName(ip).getHostName().toString();
		    }
	    }

		 valueMap.put("logonIP", ip);//获取来访者ip;
		 valueMap.put("logonHost", logonHost);// 获取来访者计算机名	 
	   
		
		
		ibatisServices.insertIbatisObject("addLoginInfo", valueMap);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userID", userId);
		List<HashMap<String, String>> resultstr = ibatisServices.findIbatisList("getLogonInfoById",paramMap);
		String id=null;
		if(resultstr!=null &&resultstr.size()>0){
			Map<String,String> mapId=resultstr.get(0);
			id = String.valueOf(mapId.get("ID"));
			paramMap.put("id", id);
			List<HashMap<String, String>> LogonInfoMap = ibatisServices.findIbatisList("getLastLogOnTimeSQL",paramMap);
			if(LogonInfoMap!=null && LogonInfoMap.size()>0){
				Map<String,String> map=LogonInfoMap.get(0);
				loginLog.setId(Long.parseLong(String.valueOf(map.get("id"))));
				loginLog.setLogonTime(String.valueOf(map.get("logonTime")));
				loginLog.setLastLogOnTime(String.valueOf(map.get("lastLogOnTime")));
				loginLog.setUserID(userId);
			}
		}
		return loginLog;
	}
	
	/**
	 * 记录用户登出日志
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public Object logoutLog(Long logId) throws Exception {
		
		Map<String,Object> valueMap = new HashMap<String,Object>();
		valueMap.put("userID", logId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		Date date =calendar.getTime();
		String logoutTime = format.format(date);
		valueMap.put("logonTime", logoutTime);
		valueMap.put("islogin", 1);
		valueMap.put("issucessful", 0);
//		valueMap.put("logonHost", Struts2Utils.getRequest().getLocalName());
//		valueMap.put("logonIP", Struts2Utils.getRequest().getLocalAddr());
		
//		Map<String, String> sysmap = System.getenv();	    
//		String logonHost = sysmap.get("COMPUTERNAME");// 获取来访者计算机名	   
//		valueMap.put("logonHost", logonHost);
//		InetAddress addr = InetAddress.getLocalHost();
//		valueMap.put("logonIP", addr.getHostAddress().toString());//获取来访者ip;
		
		
//		String logonHost=null;
//		String ip =  Struts2Utils.getRequest().getHeader("x-forwarded-for"); 
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		    ip =  Struts2Utils.getRequest().getHeader("PRoxy-Client-IP"); 
//		} 
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		    ip =  Struts2Utils.getRequest().getHeader("WL-Proxy-Client-IP"); 
//		} 
//		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		    ip =  Struts2Utils.getRequest().getRemoteAddr(); 
//		} 
//		
//		try {  
//		   logonHost=InetAddress.getByName(ip).getHostName();
//		
//		} catch (Exception e) { 
//		} 

		
		String ip =null;
		String logonHost=null;
		ip = Struts2Utils.getRequest().getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = Struts2Utils.getRequest().getHeader("Proxy-Client-IP");
		    if(ip==null){
		    	logonHost=null;
		    }else{
		    	logonHost=Struts2Utils.getRequest().getRemoteHost().toString();
		    }
	     }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = Struts2Utils.getRequest().getHeader("WL-Proxy-Client-IP");
		    if(ip==null){
		    	logonHost=null;
		    }else{
		    	logonHost=Struts2Utils.getRequest().getRemoteHost().toString();
		    }
	     }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = Struts2Utils.getRequest().getRemoteAddr().toString();
	    	if(ip==null){
	    		logonHost=null;
		    }else{
		    	logonHost=Struts2Utils.getRequest().getRemoteHost().toString();
		    }
	    }
	    if(ip.equals("127.0.0.1")){
	    	InetAddress addr = InetAddress.getLocalHost();
	    	ip = addr.getHostAddress().toString();
	    	if(ip==null){
		    	logonHost=null;
		    }else{
		    	logonHost= InetAddress.getByName(ip).getHostName().toString();
		    }
	    }
		
		 valueMap.put("logonIP", ip);//获取来访者ip;
		 valueMap.put("logonHost", logonHost);// 获取来访者计算机名	 
		 
		
		return ibatisServices.insertIbatisObject("addLoginInfo", valueMap);
	}
	
	/**查询系统公告*/
//	public List<Map<String, String>> getNoticeName(){
//		//获取开始时间和结束时间
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式 
//		 Calendar   cal   =   Calendar.getInstance(); 
//		 String time=dateFormat.format(cal.getTime());
//		 String endDate=time.substring(0, 16);// 2007-10-30 09:30
//		 cal.add(Calendar.DAY_OF_MONTH, -2);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String mDateTime=formatter.format(cal.getTime());
//		String startDate=mDateTime.substring(0, 16);//2007-10-29 09:30
//		Map whereMap=new HashMap();
//		whereMap.put("startDate",startDate);
//		whereMap.put("endDate", endDate);
//		List<Map<String, String>> getSystemNoticeList=this.ibatisServices.queryForList(Map.class,"getSystemNoticeShowSQL",whereMap);
//		if(getSystemNoticeList.size()<=0){
//			getSystemNoticeList=this.ibatisServices.queryForList(Map.class, "getSystemNoticePageAllSQL",whereMap);
//		}
//	    return getSystemNoticeList;
//	}
	
}