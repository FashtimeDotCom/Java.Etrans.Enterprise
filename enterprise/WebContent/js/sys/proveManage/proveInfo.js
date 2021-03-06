

///********************【初始化和查询】【begin】****************************/
$(function() {
		$("#endTime").val(CurentTimeYMD()+" 23:59:59");//办证结束时间设置为当前时间
		$("#hihi").hide();
	    var params=getparams();
		$("#ProveInfoList").flexigrid( {
			url : 'proveInfoManage/findProveInfoList.action',
			dataType : 'json',
			params : params,
			colModel : [ 
			{
				display : '车牌号码',
				name : 'registrationNO',
				width : 100,
				sortable : true,
				align : 'center'
			},{
				display : '证件名称',
				name : 'proveName',
				width : 150,
				sortable : true,
				align : 'center'
			},{
				display : '办证时间',
				name : 'StartTime',
				width : 150,
				sortable : true,
				align : 'center'
			},{
				display : '到期时间',
				name : 'EndTime',
				width : 150,
				sortable : true,
				align : 'center'
			},{
				display : '办证人',
				name : 'ProveHuman',
				width : 150,
				sortable : true,
				align : 'center'
			},{
				display : '办证费用',
				name : 'ProveRate',
				width : 150,
				sortable : true,
				align : 'center'
			},{
				display : '办证地点',
				name : 'ProveAddress',
				width : 150,
				sortable : true,
				align : 'center'
			},{
				display : '提前提醒天数',
				name : 'WarnTime',
				width : 100,
				sortable : true,
				align : 'center'
			},{
				display : '操作',
				name : 'Handler',
				handlefunction : 'getHandleColumn',
				paramcolnames : ['id'],
				width : 100,
				sortable : false,//操作列不能排序
				align : 'center'
			}],	
			sortname : "id",//第一次加载数据时排序列
			sortorder : "desc",//第一次加载数据时排序类型
			usepager : true,//是否分页，默认为true
			showTableToggleBtn : true,//是否显示收起/打开按钮,默认不显示。
			useRp : true,//是否可以动态设置每页显示的结果数，默认为false。
			rp :8,//每页记录数，默认为10
			checkbox : false,//是否要多选框,默认为false。
			//rowId : 'ID',// 多选框绑定行的id,只有checkbox : true时才有效。
			singleSelect:false,
			width : 'auto',//表格宽度
			height : getNormalHeight()-20
		});
		
		/**加载下拉框**/
		initAjaxSelect_Ansynce("proveName","proveInfoManage/findProveNameList.action",2,false);
		
		//初始化验证插件
		$("#editWindow").validation();
		//按钮绑定点击事件
		$('#searchBtn').bind('click', toSearch);//查询
		$('#createBtn').bind('click', toCreate);//新增
		$('#cancelBtn').bind('click', hide);//取消
		
});

//获取查询参数
function getparams(){
	var registrationNo = $("#registrationNo").val(); //车牌号码
	var proveName = $("#proveName").val(); //证件名称id
	var startTime = $("#startTime").val(); //办证开始时间
	var endTime = $("#endTime").val(); //办证结束时间
	if(proveName==-1){
		proveName="";
	}
	
	var params = [{
		name : 'registrationNo',
		value : registrationNo
	},{
		name : 'proveName',
		value: proveName
	},{
		name : 'startTime',
		value: startTime
	},{
		name : 'endTime',
		value: endTime
	}
	];
	return params;
}


/********************【页面初始化完毕后执行】【begin】*********************/
$(document).ready(function() {
	//alert("证件过期数据id:"+proveBackID);
	if(proveBackID!=""){
		doEdit(parseInt(proveBackID));
	}
});
/********************【页面初始化完毕后执行】【end】*********************/

/**
* 查询方法
*/
function toSearch(){
	var params= getparams();
	$("#ProveInfoList").flexOptions({
			newp : 1,
			params : params
		}).flexReload();
}

/**
 * 组装操作列显示内容
 * @param id 修改记录ID
 * @param editAction 编辑ACTION名
 * @param deleteAction 删除ACTION名
 * @returns {String}
 */
function getHandleColumn(id){
	var editStr = "";
	var deleteStr = "";
	//变量resources为用户的所有资源权限 格式：|findrecordList||createPlatForm||updatePlatForm||deletePlatForm|
	if(resources!=null){
		//判断ACTION的访问权限
		 if(resources.indexOf("|updProveInfo|")!=-1){
			 editStr = '<a href="javascript:void(0)"  title="编辑" onclick="doEdit(' + id + ')">编辑</a>';
		 }
		 if(resources.indexOf("|delProveInfo|")!=-1){
			 deleteStr = '<a href="javascript:void(0)" title="删除" onclick="doDelete(' + id + ')">删除</a>';
		 }
	}
	return '&nbsp;&nbsp;' +editStr + '&nbsp;&nbsp;' + deleteStr;
}

/********************【初始化和查询】【end】***********************/


/********************【新增】【begin】****************************/
/**
 * 新增加方法入口
 */
function toCreate(){
	$("#vehicleTitle").html("证件信息新增");
	//关闭详细
	hideView();
	//清除点击事件
	$("#submitBtn").unbind("click");
	//清空DIV中包含的所有表单的值
	clearForm("editWindow");
	$("#warnTime").val(30);
	$("#proveNameID").attr("disabled",false);
	/**给时间赋值**/
	$("#startTime_value").val(CurentTime());
	$("#endTime_value").val(CurentTime());
	
	/**加载下拉框**/
	initAjaxSelect_Ansynce("proveNameID","proveInfoManage/findProveNameList.action",0,false);

	/**打开新增**/
	showEditForm();
	//提交
	$('#submitBtn').bind("click",ConfigAdd); 
}

/**
 * 新增方法
 */
function ConfigAdd(){
	
	var flag = $("#editWindow").beforeSubmit();
	if(flag){
		/**保存参数**/
		var proveInfo = getParams();
		var vehicleID = $("#registrationVehicleId").val();//车辆id
		$.ajax({
		    type : "POST",
		    url : "proveInfoManage/addProveInfo.action",
		    data : {proveInfo:proveInfo},
		    dataType : "JSON",
		    async:false,
		    success : function(data) {
		    	if(data.code == '1'){
		    		 hide();
		    		 //刷新查询列表
		    		$("#ProveInfoList").flexReload();
		    	}else{
		    		showError();
		    	}
		    },
		    error : function(data) {
		    	showError();
		    }
	    });
		
	}
	
}

/**
 * 保存参数
 */
function getParams(){
	var registrationVhicleNo = $("#registrationVhicleNo").val();//车牌号码
	var vehicleID = $("#registrationVehicleId").val();//车辆id
	var proveNameID = $("#proveNameID").val();//证件名称id
	var id = $("#id").val(); //数据信息id
	var startTime_value = $("#startTime_value").val();//办证时间
	var endTime_value = $("#endTime_value").val();//过期时间
	var proveHumanValue = $("#proveHumanValue").val();//办证人
	var principalValue = $("#principalValue").val();//责任人
	var proveRateValue = $("#proveRateValue").val();//所花费用
	var manageAreaValue = $("#manageAreaValue").val();//所辖机构
	var proveAddressValue = $("#proveAddressValue").val();//办证地点
	var warnTime = $("#warnTime").val();//提前提醒天数
	var remark=$("#memo").val();//备注
	
	
	var proveInfo  = "{'registrationVhicleNo':'" + registrationVhicleNo+"'"
	+",'vehicleID':" + "'"+vehicleID+"'"
	+",'proveNameID':" + "'"+proveNameID+"'"
	+",'id':" + "'"+id+"'"
	+",'startTime_value':" + "'"+startTime_value+"'"
	+",'endTime_value':" + "'"+endTime_value+"'"
	+",'proveHumanValue':" + "'"+proveHumanValue+"'"
	+",'principalValue':" + "'"+principalValue+"'"
	+",'proveRateValue':" + "'"+proveRateValue+"'"
	+",'manageAreaValue':" + "'"+manageAreaValue+"'"
	+",'proveAddressValue':" + "'"+proveAddressValue+"'"
	+",'warnTime':"+"'"+warnTime+"'"
	+",'remark':"+"'"+remark+"'";
	
	
	proveInfo = proveInfo+"}";
	return proveInfo;
}



/********************【修改】***********************/
/**
 * 修改
 * @return
 */
function doEdit(id){
	$("#vehicleTitle").html("证件信息编辑");
	hide();
	closeMeasageAll();
	if(id != null && id != ''){
		clearForm("editWindow");
		$("#proveNameID").attr("disabled",true);
		//打开查看
		showView(1);
		/**加载下拉框**/
		initAjaxSelect_Ansynce("proveNameID","proveInfoManage/findProveNameList.action",0,false);
		
		$.post("proveInfoManage/findProveInfos.action", {id : id}, function(data){
			var config = data.data;
			//设置车辆id
//			$("#registrationVehicleId").val(config.registrationVehicleId);
			
			//设置input
			$("#editWindow input:not(:button)").each(function(i) {
				$(this).val(config[$(this).attr("name")]);
			});
			//设置下拉列表
			$("#editWindow select").each(function(i) {
				$(this).val(config[$(this).attr("name")]);
			});
			
		});
		$("#submitBtn").unbind("click");
		//提交
		$('#submitBtn').bind("click",UpdateInfo); 
		
	}
}

/**
 * 保存修改【直接修改】
 * @param str
 * @return
 */
function UpdateInfo(){
	var id = $("#id").val(); //数据信息id
	if (id != null || id.length > 0) {
		/**保存参数**/
		var proveInfo = getParams();
		$.ajax({
		    type : "POST",
		    url : "proveInfoManage/updProveInfo.action",
		    data : {proveInfo:proveInfo},
		    dataType : "JSON",
		    async:false,
		    success : function(data) {
		    	if(data.code=="1"){//成功
		    		hide();
		    		$("#ProveInfoList").flexReload();
		    	}else{
		    		showError();
		    	}
		    },
		    error : function(data) {
		    	showError();
		    }
	    });
	}
	
}

/********************【删除】***********************/
/**
 * 执行后台方法删除数据
 * @param ids
 * @returns {Boolean}
 */
function doDelete(ids){
//	alert(ids);
	if (ids != null || ids.length > 0) {
		if (!confirm("确定删除?")) {
			return false;
		} else {
				$.ajax({
				    type : "POST",
				    url : "proveInfoManage/delProveInfo.action",
				    data : {id:ids.toString()},
				    dataType : "JSON",
				    async:false,
				    success : function(data) {
				    	if(data.code == '1'){
				    		hide();
				    		$("#ProveInfoList").flexReload();
				    	}else{
				    		showError();
				    	}
				    },
				    error : function(data) {
				    	showError();
				    }
			    });
			return true;
		}
	}
}




/********************【辅助】***********************/

//删除左右两端的空格   
function lrtrim_analyeseGroup(str){   
 return str.replace(/(^\s*)|(\s*$)/g, "");   
}  

/**
 * 关闭详细框
 */
function hideView(){
	$("#viewWindow").animate({height: 'hide', opacity: 'hide'}, 400);
}

/**
 * 打开编辑窗口
 */
function showEditForm(){
	showView(1);
	$("#editWindow .errorMsg").closeMessage();
}

/**
 * 打开【编辑窗口】或者【详细框】
 */
function showView(op){
	//编辑窗口
	if(op==1){
		$("#editWindow").animate({height: 'show', opacity: 'show'}, 300);
	}
	//详细框
	else if(op==2){
		$("#viewWindow").animate({height: 'show', opacity: 'show'}, 300);
	}
}

/**
 * 关闭所有错误提示
 * @return
 */
function closeMeasageAll(){
	$("#registrationVhicleNospan").closeMessage();
	$("#proveNameIDspan").closeMessage();
	$("#startTime_valuespan").closeMessage();
	$("#endTime_valuespan").closeMessage();
	$("#ProveHumanValuespan").closeMessage();
	$("#PrincipalValuespan").closeMessage();
	
	$("#ProveRateValuespan").closeMessage();
	$("#ManageAreaValuespan").closeMessage();
	$("#ProveAddressValuespan").closeMessage();
}


/**
 * 设置控件div的高度
 * @param id
 * @return
 */
function divCss(id,value){
	$(id).css("height",value);
}

/**修改车牌号码之后**/
function onchangeVehicleNo(){
	
	$("#registrationVhicleNospan").closeMessage();
}

/**
 * 获得当前时间字符串 
 * 当前日期加时间(如:2009-06-12 12:12:12)
 * @return
 */
function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var sd = now.getSeconds();       //秒
   
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + " ";
   
    if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    
    clock +=":";
    if(sd<10) clock += '0'; 
    clock += sd; 
    
    return(clock); 
}


/**
 * 获得当前时间字符串 
 * 当前日期加时间(如:2009-06-12)
 * @return
 */
function CurentTimeYMD(){
    var now = new Date();
    
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    
    var clock = year + "-";
    
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day;
    
    
    return(clock); 
}

