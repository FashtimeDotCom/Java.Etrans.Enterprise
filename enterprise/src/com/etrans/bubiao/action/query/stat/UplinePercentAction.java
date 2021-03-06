package com.etrans.bubiao.action.query.stat;



import java.util.ArrayList;
import java.util.HashMap;
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
import com.etrans.bubiao.http.ParamKey;
import com.etrans.bubiao.services.query.stat.UplinePercentService;
import com.etrans.bubiao.sys.UserContext;
import com.etrans.common.util.FlexiGridUtil;
import com.etrans.common.util.chart.Chart;
import com.etrans.common.util.chart.ChartData;
import com.etrans.common.util.chart.Data;
import com.etrans.common.util.json.JSONUtil;
import com.etrans.common.util.web.RowNumUtil;

@Controller
@Scope("prototype")
@Namespace("/query/stat")
public class UplinePercentAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String checkUserAuth() throws Exception{
		//-1:超级管理员   1：普通用户  0：企业管理员
		String isSuper = "1";
		if(UserContext.isBsRootUser()){
			isSuper = "-1";
		}else if(UserContext.getLoginUser().isWorkUnitSuperAdmin()){
			isSuper = "0";
		}
		return isSuper;
	}
	
	/**
	 *车辆上线统计	
	 */
	@Action(value = "findUplinePercent")
	public void findUplinePercent() {
		
		try {
			//-1:超级管理员   1：普通用户  0：企业管理员
			String isSuper = checkUserAuth();
			
			Map<String,Object> params = FlexiGridUtil.parseParam(this.getGridParams());
			
			if(params==null){
				params = new HashMap<String,Object>();
			}
			params.put("isSuper", isSuper);
			params.put("userId", UserContext.getLoginUserID());
			params.put("workUnitId", UserContext.getLoginUser().getWorkUnitID());
			
			params.put(params.get("sortname")+"Order","");
			
			this.renderJSON(JSONUtil.toJson(uplinePercentService.getUplinePercents(params)));
			LogUtil.insertLog(LogActionTypes.READ, "成功", "企业车辆上线率统计查询", "", "企业车辆上线率统计查询");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.READ, "失败", "企业车辆上线率统计查询", "", "企业车辆上线率统计查询");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 车辆上线率统计导出EXECL表格
	 */
	@Action(value = "uplinePercentExportExl")
	public void uplinePercentExportExl() {
		
		try {
			//-1:超级管理员   1：普通用户  0：企业管理员
			String isSuper = checkUserAuth();
			
			// 导出数据时的开始页数
			String fromPage = getParameter("frompage");
			// 导出数据时的结束页数
			String toPage = getParameter("topage");
			
			Map<String,Object> params = FlexiGridUtil.parseParam(this.getGridParams());

			String pageSize = String.valueOf(params.get(ParamKey.PAGE_SIZE));
			Integer fromRow = RowNumUtil.getFromRowNum(fromPage, pageSize);
			Integer toRow = RowNumUtil.getToRowNum(toPage, pageSize);
			params.put("@FromRow",String.valueOf(fromRow));
			params.put("@ToRow",String.valueOf(toRow));

			params.put("isSuper", isSuper);
			params.put("userId", UserContext.getLoginUserID());
			params.put("workUnitId", UserContext.getLoginUser().getWorkUnitID());
		
			params.put(params.get("sortname")+"Order","");
			
			String[] titleArray = {};
			titleArray = new String[6];
		//	titleArray[0]="所属平台";
			titleArray[0]="所属单位";
			titleArray[1]="所属行业";
			titleArray[2]="所属区域";
			titleArray[3]="车辆总数";
			titleArray[4]="上线车辆总数";
			titleArray[5]="车辆上线率";
			
			String[] columnArray = {};
			columnArray = new String[6];
			//columnArray[0]="platformName";
			columnArray[0]="workUnitName";
			columnArray[1]="customKindName";
			columnArray[2]="AreaName";
			columnArray[3]="TotalQty";
			columnArray[4]="UplineQty";
			columnArray[5]="UplinePercent";
			
			List<Map<String,Object>>  rows = uplinePercentService.uplinePercentExportExl(params);
			exportExl("uplinePercent", titleArray, columnArray, rows);
			LogUtil.insertLog(LogActionTypes.READ, "成功", "企业车辆上线率统计导出", "", "企业车辆上线率统计导出");
		} catch (Exception e) {
			LogUtil.insertLog(LogActionTypes.READ, "失败", "企业车辆上线率统计导出", "", "企业车辆上线率统计导出");
			e.printStackTrace();
		}
	}
	
	/**
	 * 统计图
	 * 
	 * @return
	 */
	@Action(value="getUplinePercentCharts")
	public void getUplinePercentCharts() {
		try{
			//-1:超级管理员   1：普通用户  0：企业管理员
			String isSuper = checkUserAuth();
			
			Map<String,Object> params = new HashMap<String,Object>();
			String startDate = this.getParameter("startDate");
			String endDate = this.getParameter("endDate");
			String workUnitNameWhere = this.getParameter("workUnitNameWhere");
			
			params.put("startDate",startDate);
			params.put("endDate",endDate);
			params.put("workUnitId", UserContext.getLoginUser().getWorkUnitID());
			params.put("workUnitNameWhere",workUnitNameWhere);
			params.put("isSuper", isSuper);
			params.put("userId", UserContext.getLoginUserID());
			
			List<Map<String,Object>>  rows = uplinePercentService.getUplinePercentCharts(params);
			Chart charts = ChartData.chartSet("车辆上线率统计", "单位名称", "上线车辆总数");
			if (rows!=null && 0 < rows.size()) {
				
				List<Data> dataList = new ArrayList<Data>();
				for (Map<String, Object> obj : rows) {
					dataList.add(new Data(String.valueOf(obj.get("workUnitName").toString().trim()), String.valueOf(obj.get("UplinePercent"))));
				}
				String jsons = new ChartData().jsonData(charts, dataList);
				this.renderText(jsons);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Autowired
	private UplinePercentService uplinePercentService;

	public UplinePercentService getUplinePercentService() {
		return uplinePercentService;
	}

	public void setUplinePercentService(UplinePercentService uplinePercentService) {
		this.uplinePercentService = uplinePercentService;
	}


	
	
	

}
