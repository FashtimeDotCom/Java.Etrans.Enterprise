package com.etrans.bubiao.action.monitorCenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.etrans.bubiao.action.BaseAction;
import com.etrans.bubiao.action.sys.log.LogActionTypes;
import com.etrans.bubiao.action.sys.log.LogUtil;
import com.etrans.bubiao.entities.Result;
import com.etrans.bubiao.services.monitorCenter.LineAwayConfigServices;
import com.etrans.bubiao.sys.UserContext;
import com.etrans.common.util.FlexiGridUtil;
import com.etrans.common.util.json.JSONUtil;

/**
 * 路线偏移设置Action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@Namespace("/monitorCenter")
public class LineAwayConfigAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	protected Logger log = LogManager.getLogger(this.getClass().getName());
	
	private LineAwayConfigServices lineAwayConfigServices;

	public LineAwayConfigServices getLineAwayConfigServices() {
		return lineAwayConfigServices;
	}

	public void setLineAwayConfigServices(
			LineAwayConfigServices lineAwayConfigServices) {
		this.lineAwayConfigServices = lineAwayConfigServices;
	}

	/**
	 * 查询路线偏移设置列表
	 */
	@Action(value="findLineAwayConfigList")
	public void findLineAwayConfigList(){
		
		Map params = FlexiGridUtil.parseParam(this.getGridParams());
		params = putUserParams(params);
		
		//超级管理员
		if(UserContext.isBsRootUser()){
			params.remove("workunitId");
		}
		try {
			this.renderJSON(lineAwayConfigServices.findLineAwayConfigList(params,new Random().nextLong()));
			LogUtil.insertLog(LogActionTypes.READ, "成功", "路线偏移设置", "", "路线偏移设置");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.READ, "失败", "路线偏移设置", "", "路线偏移设置");
			e.printStackTrace();
			log.error("查询路线偏移设置列表异常！"+e.getMessage());
		}
	}
	
	
	/**
	 * 路线偏移设置名称唯一验证
	 */
	@Action(value = "checkLineAwayName")
	public void checkLineAwayName() {
		
		String name = getParameter("name"); 
		
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("name", name);
		
		try {
			this.renderJSON(JSONUtil.toJson(lineAwayConfigServices.checkLineAwayName(whereMap)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 新增路线偏移设置
	 */
	@Action(value = "createLineAwayConfig")
	public void createLineAwayConfig() {
		
		String name = getParameter("name");
		String lineId = getParameter("lineId");
		String allowMaxAway = getParameter("allowMaxAway");
		String vehicleIds = getParameter("vehicleIds");
		String description = getParameter("description");
		String dateTypeId = getParameter("dateTypeId");
		String workingDays = getParameter("workingDays");
		String beginDate = getParameter("beginDate");
		String endDate = getParameter("endDate");
		String beginTime = getParameter("beginTime");
		String endTime = getParameter("endTime");
		String isAlarm = getParameter("isAlarm");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", name);
		params.put("lineId", lineId);
		params.put("allowMaxAway", allowMaxAway);
		params.put("vehicleIds", vehicleIds);
		params.put("description", description);
		params.put("dateTypeId", dateTypeId);
		params.put("workingDays", workingDays);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("isAlarm", isAlarm);
		
		params = putUserParams(params);
		
		Result result = new Result();
		try {
			lineAwayConfigServices.createLineAwayConfig(params);
			result.setCode(1);
			this.renderJSON(JSONUtil.toJson(result));
			LogUtil.insertLog(LogActionTypes.INSERT, "成功", "路线偏移设置", "", "新增路线偏移设置");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.INSERT, "失败", "路线偏移设置", "", "新增路线偏移设置");
			e.printStackTrace();
			log.error("新增路线偏移设置异常！"+e.getMessage());
		}
		result.setCode(0);
		this.renderJSON(JSONUtil.toJson(result));
		
	}
	
	/**
	 * 查询详细信息
	 */
	@Action(value = "getLineAwayConfigById")
	public void getLineAwayConfigById() {
		
		String id = getParameter("id");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		
		Result result = new Result();
		try {
			Map<String,Object> areaConfig = lineAwayConfigServices.getLineAwayConfigById(params);
			result.setCode(1);
			result.setData(areaConfig);
			this.renderJSON(JSONUtil.toJson(result));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询路线偏移设置详细信息异常！"+e.getMessage());
		}
		
		result.setCode(0);
		this.renderJSON(JSONUtil.toJson(result));
	}
	
	/**
	 * 删除路线偏移设置
	 */
	@Action(value = "deleteLineAwayConfig")
	public void deleteLineAwayConfig() {
		
		String id = getParameter("id");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		
		Result result = new Result();
		try {
			lineAwayConfigServices.deleteLineAwayConfig(params);
			result.setCode(1);
			this.renderJSON(JSONUtil.toJson(result));
			LogUtil.insertLog(LogActionTypes.DELETE, "成功", "路线偏移设置", "", "删除路线偏移设置");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.DELETE, "失败", "路线偏移设置", "", "删除路线偏移设置");
			e.printStackTrace();
			log.error("删除路线偏移设置异常！"+e.getMessage());
		}
		result.setCode(0);
		this.renderJSON(JSONUtil.toJson(result));
		
	}
	
	/**
	 * 获取用户信息
	 * @param params
	 * @return
	 */
	public Map<String,Object> putUserParams(Map<String,Object> params) {
		
		Long userId = UserContext.isSuperUser() ? 0 : UserContext.getLoginUserID();
		Long workUnitId = UserContext.getLoginUser() == null ? -1 : UserContext.getLoginUser().getWorkUnitID();
		String userName = UserContext.getLoginUser() == null ? "" : UserContext.getLoginUser().getUserName();
		
		params.put("userId", userId);
		params.put("workunitId", workUnitId);
		params.put("userName", userName);
		
		return params;
	}
	
}
