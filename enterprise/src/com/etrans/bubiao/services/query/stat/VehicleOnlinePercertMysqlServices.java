package com.etrans.bubiao.services.query.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.bubiao.auth.SessionUser;
import com.etrans.bubiao.http.ParamKey;
import com.etrans.bubiao.http.ParamMap;
import com.etrans.bubiao.http.Result;
import com.etrans.bubiao.services.BaseServices;
import com.etrans.bubiao.services.IbatisServices;
import com.etrans.bubiao.services.TaService;
import com.etrans.bubiao.sys.UserContext;
import com.etrans.common.util.FlexiGridUtil;
import com.etrans.common.util.HttpConfig;
import com.etrans.common.util.web.RowNumUtil;
@Service
public class VehicleOnlinePercertMysqlServices extends BaseServices {
	@Autowired
	private IbatisServices ibatisServices;

	public IbatisServices getIbatisServices() {
		return ibatisServices;
	}
	
	public void setIbatisServices(IbatisServices ibatisServices) {
		this.ibatisServices = ibatisServices;
	}
	
	
	/**
	 * 设置配置服务路径
	 */
	private void SetHttpConfig() {
		 httpClient.addServerURL(prepareServerURL());
	}
	
	/**
	 * 重设配置路径
	 * @return
	 */
	private  List<String> prepareServerURL()
	{
		String config="/httpTAService_config.properties";
		return HttpConfig.getServiceHttpConfig(config);
	}
	
	
	/**
     * 车辆在线统计列表（Mysql库）
	 * @param queryJSON
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findVehicleOnlineMysqlList (String queryJSON) throws Exception {
		this.SetHttpConfig();
		ParamMap params = FlexiGridUtil.parseJSONParam_01(queryJSON);
		params.putTableName("vehicleOnlinePercentMysqlSQL");
		params.putTotalName("vehicleOnlinePercentCountMysqlSQL");
		
		List<Map<String, Object>>  list = null;
		Map paramMap = new HashMap();
		SessionUser user = UserContext.getLoginUser();
		if(user != null){
			if(UserContext.isBsRootUser()){
				params.putToWhereParam("isSuper", "0");
				
			}else if(user.isWorkUnitSuperAdmin()){
				String fullId = user.getWorkUnitFullId();
				params.putToWhereParam("fullId", fullId);
				params.putToWhereParam("isWorkUnitSuperAdmin", "1");
				
			}else{
				params.putToWhereParam("isWorkUnitSuperAdmin", "2");
				params.putToWhereParam("userId", user.getUserID());
				paramMap.put("userId", user.getUserID());
				list=this.ibatisServices.queryForList(Map.class, "getVehicleIdstatMySQL", paramMap);
			}
			if (list != null || "".equals(list)) {
				String vehiclestr = "";
				String vehicleID = null;
				for (int i = 0; i < list.size(); i++) {
					vehiclestr += list.get(i).get("vehicleid").toString()+ ",";
				}
				vehicleID = vehiclestr.substring(0, vehiclestr.length() - 1);
				params.putToWhereParam("vehicleID", String.valueOf(vehicleID));
			}
			
		 }
		
		//查询数据开始行数和结束行数
		String page = (String)params.getWhereParam().get(ParamKey.PAGE);
		String pageSize = (String)params.getWhereParam().get(ParamKey.PAGE_SIZE);
		int count = Integer.parseInt(page);
		Integer fromRow=0;
		if(count==1){
			fromRow= RowNumUtil.getFromRowNum(page, "0");
		}else{
			fromRow= RowNumUtil.getFromRowNum(page, pageSize)-1;
		}
		
		Integer toRow = Integer.parseInt(pageSize);
		params.putToWhereParam("fromRow", fromRow);
		params.putToWhereParam("toRow", toRow);
		return this.queryAsPageJson1(params);
	}
	
	/**
	 * 车辆在线统计导出到EXCEL（Mysql库）
	 * @param queryJSON
	 * @param fromPage
	 * @param toPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> vehicleOnlinePercertExportMysqlExl (String queryJSON,String fromPage,String toPage) throws Exception {
		
		this.SetHttpConfig();
		ParamMap params = FlexiGridUtil.parseJSONParam_01(queryJSON);
		params.putTableName("vehicleOnlinePercentMysqlSQL");
		params.putTotalName("vehicleOnlinePercentCountMysqlSQL");
		
		List<Map<String, Object>>  list = null;
		Map paramMap = new HashMap();
		SessionUser user = UserContext.getLoginUser();
		if(user != null){
			if(UserContext.isBsRootUser()){
				params.putToWhereParam("isSuper", "0");
				
			}else if(user.isWorkUnitSuperAdmin()){
				String fullId = user.getWorkUnitFullId();
				params.putToWhereParam("fullId", fullId);
				params.putToWhereParam("isWorkUnitSuperAdmin", "1");
				
			}else{
				params.putToWhereParam("isWorkUnitSuperAdmin", "2");
				params.putToWhereParam("userId", user.getUserID());
				paramMap.put("userId", user.getUserID());
				list=this.ibatisServices.queryForList(Map.class, "getVehicleIdstatMySQL", paramMap);
			}
			if (list != null || "".equals(list)) {
				String vehiclestr = "";
				String vehicleID = null;
				for (int i = 0; i < list.size(); i++) {
					vehiclestr += list.get(i).get("vehicleid").toString()+ ",";
				}
				vehicleID = vehiclestr.substring(0, vehiclestr.length() - 1);
				params.putToWhereParam("vehicleID", String.valueOf(vehicleID));
			}
			
		 }
		
	
		String page = fromPage;//开始页
		String endPage = toPage;//结束页
		String pageSize = (String)params.getWhereParam().get(ParamKey.PAGE_SIZE);
		int startCount = Integer.parseInt(page);//开始行
		int endCount = Integer.parseInt(endPage);//结束行
		
		Integer fromRow=0;
		Integer toRow = 0;
		if(startCount==1){
			fromRow= RowNumUtil.getFromRowNum(page, "0");
		    toRow = RowNumUtil.getToRowNum(toPage, pageSize);
		}else{
			int i=Integer.parseInt(pageSize);
			fromRow=(startCount-1)*i ;
			if(endCount-startCount==0){
				toRow=i;
			}else{
				toRow = i*(endCount-startCount+1);
			}
			
		}
		
		
		params.putToWhereParam("fromRow", fromRow);
		params.putToWhereParam("toRow", toRow);
		
		//查询数据开始行数和结束行数
//		String pageSize = (String)params.getWhereParam().get(ParamKey.PAGE_SIZE);
//		Integer fromRow = RowNumUtil.getFromRowNum(fromPage, pageSize);
//		
//		params.putToWhereParam("fromRow", fromRow);
//		params.putToWhereParam("toRow", toRow);
//		
		Result result = this.queryAsResult(params);
		List<Map<String,Object>> rows = (List<Map<String,Object>>)result.getData();
		return rows;
	}
	
}
