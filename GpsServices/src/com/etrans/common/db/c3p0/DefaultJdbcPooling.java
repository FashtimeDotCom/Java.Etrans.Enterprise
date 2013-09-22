package com.etrans.common.db.c3p0;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
 * Ĭ�ϵĵ����ݿ����ӳ��ṩ��
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-17 ����4:30:52 
 */
public class DefaultJdbcPooling{
	
	/**
	 * ����Դ
	 */
	public final static DataSource datasource;
	
	/**
	 * ��ʼ������Դ
	 */
	static{
		datasource = new ComboPooledDataSource();
	}
	
	
	/**
	 * �����ӳػ�ȡ���ݿ�����
	 * 
	 * @return Connection ���ݿ�����
	 */
	public static Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	/**
	 * �ͷŵ�ǰ����
	 * 
	 * @param con Connection
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws SQLException {
		con.close();
	}
}

