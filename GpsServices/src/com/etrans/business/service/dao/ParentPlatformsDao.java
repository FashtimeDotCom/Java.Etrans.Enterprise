package com.etrans.business.service.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.etrans.common.db.DaoSuper;

/** 
 * ƽ̨����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-17 ����4:35:30 
 */
public class ParentPlatformsDao extends DaoSuper {
	
	private static Logger logger = Logger.getLogger(ParentPlatformsDao.class);
	
	private String clientCommandSql="INSERT INTO Ent_ClientCommandSendLog(vehicleId,userId,userName,CommName,CommContent,SendTime)VALUES(?,?,?,?,?,?)";
	private String HighLevelPatrolSql = "INSERT INTO Ent_HighLevelPatrol_Log(CheckingNo,CheckContent,CheckTime,IsResult)VALUES(?,?,?,?)";
	private String AlarmOverSeeingSql = "insert into Ent_Alarm_OverSeeing(vehicleId,alarmType,alarmDate,overSeeingId,overSeeingDate,termialType,alarmInfoSource,overSeeingLevel,overSeeingUser,telphone,eMail)values(?,?,?,?,?,?,?,?,?,?,?)";
	
 	private String[] clientCommandSqlKey = new String[]{"vehicleId","userId","userName","CommName","CommContent","SendTime"};
 	private String[] HighLevelPatrolKey = new String[]{"CheckingNo","CheckContent","CheckTime","IsResult"};
 	private String[] AlarmOverSeeingKey = new String[]{"vehicleId","alarmType","alarmDate","overSeeingId","overSeeingDate","termialType","alarmInfoSource","overSeeingLevel","overSeeingUser","telphone","eMail"};
	
	private Map<String,String> sqlMap = new HashMap<String,String>();
	private Map<String,String[]> sqlKeyMap = new HashMap<String,String[]>();
	
	{
		sqlMap.put("clientCommandSql", clientCommandSql);
		sqlMap.put("HighLevelPatrolSql", HighLevelPatrolSql);
		sqlMap.put("AlarmOverSeeingSql", AlarmOverSeeingSql);
		sqlKeyMap.put("clientCommandSqlKey", clientCommandSqlKey);
		sqlKeyMap.put("HighLevelPatrolSqlKey", HighLevelPatrolKey);
		sqlKeyMap.put("AlarmOverSeeingSqlKey", AlarmOverSeeingKey);
	}
	
	/**
	 * ������־
	 * 
	 * @param valueMap
	 * @param getSqlKey
	 * @return
	 * @throws SQLException
	 */
	public int addLog(Map<String, Object> valueMap,String getSqlKey) throws SQLException{
		try {
			int affectRow = executePreUpdate(sqlMap.get(getSqlKey), sqlKeyMap.get(getSqlKey+"Key"), valueMap);
			if(affectRow>0)logger.info("���ݿ�����ɹ�!");
			return affectRow;
		} catch (Exception e) {
			logger.error("���ݲ����쳣:"+e.getMessage()+" SQL:"+sqlMap.get(getSqlKey));
			throw new SQLException("���ݲ����쳣:"+e.getMessage()+" SQL:"+sqlMap.get(getSqlKey));
		}
	}
}

