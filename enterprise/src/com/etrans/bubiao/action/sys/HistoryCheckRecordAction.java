package com.etrans.bubiao.action.sys;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.etrans.bubiao.action.BaseAction;
import com.etrans.bubiao.action.sys.log.LogActionTypes;
import com.etrans.bubiao.action.sys.log.LogUtil;
import com.etrans.bubiao.auth.SessionUser;
import com.etrans.bubiao.services.sys.HistoryCheckupRecordServices;
import com.etrans.bubiao.sys.UserContext;
import com.etrans.common.util.FlexiGridUtil;
import com.etrans.common.util.json.JSONUtil;

@Controller
@Scope("prototype")
@Namespace("/sys")
public class HistoryCheckRecordAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HistoryCheckupRecordServices historyCheckupRecordServices;

	public HistoryCheckupRecordServices getHistoryCheckupRecordServices() {
		return historyCheckupRecordServices;
	}

	public void setHistoryCheckupRecordServices(
			HistoryCheckupRecordServices historyCheckupRecordServices) {
		this.historyCheckupRecordServices = historyCheckupRecordServices;
	}
	/**
	 * 历史查岗记录分页查询
	 */
	@Action(value = "historyCheckupRecordList")
	public void historyCheckupRecordList() {
		try {
			Map<String,Object> params = FlexiGridUtil.parseParam(this.getGridParams());
			SessionUser user = UserContext.getLoginUser();
			if(user!=null){
				if(UserContext.isBsRootUser()){
					params.put("isSuper", true);
				}
				else{
					String fullId = user.getWorkUnitFullId();
					params.put("fullId", fullId);
				}
			}
			this.renderJSON(JSONUtil.toJson(historyCheckupRecordServices.getHistoryCheckupRecords(params)));
			LogUtil.insertLog(LogActionTypes.READ, "成功", "历史查岗记录", "", "历史查岗记录");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.READ, "失败", "历史查岗记录", "", "历史查岗记录");
			e.printStackTrace();
		}
	}
	
	/**
	 * 历史查岗记录导出
	 */
	@Action(value="exportHistoryCheckRecord")
	public void exportHistoryCheckRecord(){
		Map<String,Object> params = FlexiGridUtil.parseParam(this.getGridParams());
		params = this.getExportParams(params);
		
		try {
			
			String[] titleArray = {};
			titleArray = new String[5];
			titleArray[0]="查岗时间";
			titleArray[1]="查岗内容";
			titleArray[2]="查岗回复时间";
			titleArray[3]="回复内容";
			titleArray[4]="标志";
	
			
			
			
			String[] columnArray = {};
			columnArray = new String[5];
			columnArray[0]="CheckTime";
			columnArray[1]="CheckContent";
			columnArray[2]="CheckReturnTime";
			columnArray[3]="CheckReturnContent";
			columnArray[4]="isResult";
		
			
			
			List<Map<String,Object>> rows = historyCheckupRecordServices.getHistoryCheckupRecordList(params);
			exportExl("historyCheckupRecordList", titleArray, columnArray, rows);
			LogUtil.insertLog(LogActionTypes.READ, "成功", "历史查岗记录导出", "", "历史查岗记录导出");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.READ, "失败", "历史查岗记录导出", "", "历史查岗记录导出");
			e.printStackTrace();
		}
	}
}