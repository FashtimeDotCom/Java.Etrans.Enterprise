var daystr=getNowFormatDate();


$(function() {
	
		//查询参数
		var nowDate = dateutil.formatDate(new Date(dateutil.setDateBefore(new Date(),1)),dateutil.FORMAT_DATE_LONG);
		$("#RecdayStart").val(nowDate);
		$("#RecdayEnd").val(nowDate);
		var registrationNo="";
		var RecdayStart = nowDate;
		var RecdayEnd = nowDate;
		var params = [{
			name : 'RecdayStart',
			value : RecdayStart
		} ,{
			name : 'RecdayEnd',
			value : RecdayEnd
		}];
		$("#vehicleUplinePercentList").flexigrid( {
			url : 'query/stat/findVehicleUplinePercent.action',
			dataType : 'json',
			params : params,
			colModel : [ 
//			{
//				display : '所属平台',//表头
//				name : 'platformName',//JSON数据中属性名
//				width : 150,// 得加上 要不IE报错
//				sortable : true,//此字段是否能排序
//				align : 'center'//对齐方式
//			},
			{
				display : '所属单位',
				name : 'workUnitName',
				width :150,
				sortable : true,
				align : 'center'
			}, {
				display : '所属行业',
				name : 'customKindName',
				width :150,
				sortable : true,
				align : 'center'
			}, {
				display : '所属区域',
				name : 'areaName',
				width :150,
				sortable : true,
				align : 'center'
			},
			 {
				display : '车牌号码',
				name : 'RegistrationNo',
				width :100,
				sortable : true,
				align : 'center'
			},{
				display : '车牌颜色',
				name : 'RegistrationNOColor',
				width :80,
				sortable : true,
				align : 'center'
			},
			{
				display : '上线车辆总数',
				name : 'UplineQty',
				width :80,
				sortable : true,
				align : 'center'
			}, {
				display : '车辆上线率%',
				name : 'UplinePercent',
				width :100,
				sortable : true,
				align : 'center'
			}],	
			sortname : "id",//第一次加载数据时排序列
			sortorder : "asc",//第一次加载数据时排序类型
			usepager : true,//是否分页，默认为true。
			showTableToggleBtn : true,//是否显示收起/打开按钮,默认不显示。
			useRp : true,//是否可以动态设置每页显示的结果数，默认为false。
			rp : 8,//每页记录数，默认为10
			singleSelect:false,
			width : 'auto',//表格宽度
			height : getNormalHeight()//表格高度
		});
		
		//按钮绑定点击事件
		$('#searchBtn').bind('click', toSearch);
		$('#exportBtn').bind('click', toExportExl);
});



/**
 * js格式化当前时间为yyyy-mm-dd形式 
 * @return CurrentDate
 */
function getNowFormatDate() 
{ 
	var day = new Date(); 
	var Year = 0; 
	var Month = 0; 
	var Day = 0; 
	var CurrentDate = ""; 
	//初始化时间 
	Year= day.getFullYear();//ie火狐下都可以 
	Month= day.getMonth()+1; 
	Day = day.getDate(); 
	CurrentDate += Year + "-"; 
	if (Month >= 10 ) 
	{ 
	CurrentDate += Month + "-"; 
	} 
	else 
	{ 
	CurrentDate += "0" + Month + "-"; 
	} 
	if (Day >= 10 ) 
	{ 
	CurrentDate += Day ; 
	} 
	else 
	{ 
	CurrentDate += "0" + Day ; 
	} 
	return CurrentDate; 
} 

/**
 * 查询参数
 * @return
 */
function getparams(){
	var workUnitName = $("#workUnitNameParam").val();
	var RecdayStart = $("#RecdayStart").val();
	var RecdayEnd = $("#RecdayEnd").val();
	var registrationNo = $("#registrationNo").val();
	//查询参数
	var params = [{
		name : 'RecdayStart',
		value : RecdayStart
	},{
		name : 'RecdayEnd',
		value : RecdayEnd
	},{
		name : 'workUnitName',
		value : workUnitName
	},{
		name : 'registrationNo',
		value : registrationNo
	}];
	return params;
}

/**
* 查询方法
*/
function toSearch(){
	
	// 重置表格的某些参数
	$("#vehicleUplinePercentList").flexOptions({
			newp : 1,// 设置起始页
			params : getparams()// 设置查询参数
		}).flexReload();// 重新加载
	$("#vehicleUplinePercent").show();
}


/**
 * 导出方法入口
 */
function toExportExl() {
	exportExl('vehicleUplinePercentList', 'query/stat/vehicleUplinePercentExportExl.action');
}
