package com.etrans.bubiao.action.http;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.etrans.bubiao.entities.HttpResult;
import com.etrans.bubiao.entities.ParamBean;
import com.etrans.bubiao.entities.User;
import com.etrans.bubiao.services.IbatisServices;
import com.etrans.common.util.Tools;
import com.etrans.common.util.json.JSONUtil;

/**
 * 登录相关接口
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Controller("eTGetLoginAction")
@Scope("prototype")
@Namespace("/httpService")
public class ETGetLoginAction extends HttpServiceAction
{
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired(required = true)
	private IbatisServices ibatisServices;
	
	/**
	 *http://localhost:80/enterprise/httpService/ETGetLogin.action?jsonParam={"name":"admin","password":"3180BCD2C7DF"} 
	 *{"code":0,"data":[{"userID":"51","ticket":"1358906337262-2611"}]}
	 *登录接口
	 */
	@Action(value = "ETGetLogin")
	public void ETGetLogin() throws Exception 
	{
		//返回结果
		HttpResult result = new HttpResult();
		
		try{
			boolean flag = true;
			result.setCode(Config.SUCCESS);
			
			/****************数据验证*********START***************/
			//步骤一：解释参数
			ParamBean paramBean = JSONUtil.fromJson(super.jsonParam,ParamBean.class);
			
			//步骤二：获取参数
			//本地测试用
			//String name = new String(new String(paramBean.getName().trim().getBytes("ISO-8859-1"),"gbk"));
			
			//发布用
			String name = new String(new String(paramBean.getName().trim().getBytes("UTF-8"),"utf-8"));
			
//			System.out.println("--------ISO-8859-1-----1-------gb2312---------"+new String(paramBean.getName().trim().getBytes("ISO-8859-1"),"gb2312"));
//			System.out.println("--------UTF-8----------1-------gb2312----"+new String(paramBean.getName().trim().getBytes("UTF-8"),"gb2312"));
//			System.out.println("--------GB2312---------1-------gb2312-----"+new String(paramBean.getName().trim().getBytes("GB2312"),"gb2312"));
//			System.out.println("--------GBK------------1-------gb2312--"+new String(paramBean.getName().trim().getBytes("GBK"),"gb2312"));
//
//			System.out.println("--------ISO-8859-1-----2-------gbk---------"+new String(paramBean.getName().trim().getBytes("ISO-8859-1"),"gbk"));
//			System.out.println("--------UTF-8----------2-------gbk----"+new String(paramBean.getName().trim().getBytes("UTF-8"),"gbk"));
//			System.out.println("--------GB2312---------2-------gbk-----"+new String(paramBean.getName().trim().getBytes("GB2312"),"gbk"));
//			System.out.println("--------GBK------------2-------gbk--"+new String(paramBean.getName().trim().getBytes("GBK"),"gbk"));
//
//			System.out.println("--------ISO-8859-1-----3-------utf-8---------"+new String(paramBean.getName().trim().getBytes("ISO-8859-1"),"utf-8"));
//			System.out.println("--------UTF-8----------3-------utf-8----"+new String(paramBean.getName().trim().getBytes("UTF-8"),"utf-8"));
//			System.out.println("--------GB2312---------3-------utf-8-----"+new String(paramBean.getName().trim().getBytes("GB2312"),"utf-8"));
//			System.out.println("--------GBK------------3-------utf-8--"+new String(paramBean.getName().trim().getBytes("GBK"),"utf-8"));

			
			String psw = paramBean.getPassword().trim();
			
			//步骤三：判断用户名与密码是否为空
			if(StringUtils.isEmpty(name) || StringUtils.isEmpty(psw)){
				result.setCode(Config.USERINFO_EMPTY);
				flag = false;
			}
			
			//步骤四：验证用户是否存在
			User user = Tools.queryUserTA(ibatisServices,name,psw);
			if(user==null){
				result.setCode(Config.USERINFO_ERROR);
				flag = false;
			}
			/****************数据验证********END****************/
			
			if(flag){
				/****************返回结果********START****************/
				//步骤五：组装返回对象
				String ticket = TicketManager.getInstance().getUniqID();
				
				Map<String,String> map = new HashMap<String,String>();
				map.put("userID", user.getId()+"");
				map.put("ticket", ticket);
				
				String json =JSONUtil.toJson(map);
				result.setData(json);

				/****************返回结果********END****************/
				
				/****************保存票据********START****************/
				//步骤六：票据
				TicketManager.getInstance().putTicket(ticket);
				UserInfoManager.getInstance().putUserInfo(user);
				/****************保存票据********START****************/
			}
		}catch(Exception e){
			result.setCode(Config.OTHER_ERROR);
			log.error("["+Tools.formatDate(new Date())+"]---->",e);
		}
		
		this.renderJSON(result);
	}
	
	/**
	 *http://localhost:80/enterprise/httpService/ETGetLoginOut.action?jsonParam={"userID":"51","ticket":"1358835197986-5021"} 
	 *{"code":0,"data":[{"userID":"78","ticket":"1358760398080-1248"}]}
	 *登出接口
	 */
	@Action(value = "ETGetLoginOut")
	public void ETGetLoginOut() throws Exception 
	{
		//返回结果
		HttpResult result = new HttpResult();
		
		try{
			result.setCode(Config.SUCCESS);
			
			/****************数据验证*********START***************/
			//步骤一：解释参数
			ParamBean paramBean = JSONUtil.fromJson(super.jsonParam,ParamBean.class);
			
			//步骤二：获取参数
			String ticket = paramBean.getTicket().trim();
			/****************数据验证********END****************/
				
			/****************移除票据********START****************/
			//步骤六：票据
			TicketManager.getInstance().removeTicket(ticket);
			/****************移除票据********START****************/
		}catch(Exception e){
			result.setCode(Config.OTHER_ERROR);
			log.error("["+Tools.formatDate(new Date())+"]---->",e);
		}
		
		this.renderJSON(result);
	}
}
