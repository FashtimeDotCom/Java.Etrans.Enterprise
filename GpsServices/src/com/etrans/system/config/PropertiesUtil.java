package com.etrans.system.config;

import java.lang.reflect.Method;

import com.etrans.common.MQDCConfigUtil;
import com.etrans.common.invoker.BeanUtil;
import com.etrans.common.invoker.MethodInvoker;
import com.etrans.common.util.SysUtil;

/** 
 * PropertiesUtil��Դ�ļ�������
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-2-5 ����4:11:53 
 */
public class PropertiesUtil {

	private final String SET_METHOD="set";
	private BeanUtil beanUtil = new BeanUtil();
	
	/**
	 * ����Դ�ļ����з������
	 * ֻ������������������������ƶ�Ӧ�ϵĲŻ���跴�丳ֵ����
	 * 
	 * @param obj ��ֵ����
	 */
	public void invokerPropertiesToEntity(Object obj){
		beanUtil.setObj(obj);
		Method[] methods = beanUtil.getMethods(SET_METHOD);
		String filedName;
		for(int i=0;i<methods.length;i++){
			try {
				filedName = SysUtil.firstWLowerCase(
						methods[i].getName().substring(3)
				);
				new MethodInvoker(methods[i]).invoke(
						obj, 
						new Object[]{
								MQDCConfigUtil.getObject(filedName)
						}
				);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * ָ�����ԣ�ָ����Դ�ļ�key���ж�����
	 * 
	 * @param obj Object
	 * @param propertiesKey String[]
	 * @param invokerField String[] 
	 */
	public void invokerPropertiesToEntity(Object obj,
			String[] propertiesKey,String[] methodName){
		beanUtil.setObj(obj);
		Method[] methods = beanUtil.getMethods(methodName);
		for(int i=0;i<methods.length;i++){
			try {
				new MethodInvoker(methods[i]).invoke(
						obj, 
						new Object[]{
								MQDCConfigUtil.getObject(propertiesKey[i])
						}
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

