package com.etrans.system;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/** 
 * RMI����ע��
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-10 ����4:52:03 
 */
public class RmiServerRegister {
	
	private Logger logger = Logger.getLogger(RmiServerRegister.class);
	
	/**
	 * registerRmiServer
	 * 
	 * <p>
	 * ע��RMI�������ӿ�
	 * </p>
	 */
	public void registerRmiServer(){
        new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("///////////////////Spring rmi ����������///////////////////");
	}
}

