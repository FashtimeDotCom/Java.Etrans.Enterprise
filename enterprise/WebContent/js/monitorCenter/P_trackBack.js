var tip=true;
var maplet= null;   // 地图全局变量
var vehicleId='';//终端号
var gpsInfoArray=null; // 回放轨迹数据数组成部分
var posSize = 0; // 轨迹播放次数
var playTrackFlag = false; // 轨迹播放标识
var changeConditionFlag=true;
var timeOutFlag = true; // 循环播放标识
var getDataEndFlag=false;//获取轨迹数据结束标识
var getDataTime=1800000;//每次获取轨迹时间段，
var playRate = 300; // 播放频率
function load(sim) {
	vehicleId=sim;
	$('#map').css("width", getMapWidth()+"px");
	$('#map').css("height",getMapHeight() -120+ "px");
	maplet= new EzMap(document.getElementById("map"));
	maplet.initialize();
	//显示地图左侧的比例尺
	maplet.showMapControl();
	maplet.showCopyright();//显示地图版权信息
	maplet.showMapScale();//显示地图的比例尺右下角
	//maplet.centerAndZoom(new Point(116.39885,39.96571),4);
	var overview=new OverView();//鹰眼
	overview.width=200;
	overview.height=200;
	maplet.addOverView(overview);
	
	$('#beginTime').bind('click', changeCondition);
	$('#endTime').bind('click', changeCondition);
	$('#playRate').bind('change', changeCondition);
}

function getMapWidth() {
	var width;
	if (window.innerWidth)
		width = window.innerWidth;
	else
		width = document.body.offsetWidth;

	return width;
}
function getMapHeight() {
	var height;
	height = document.body.clientHeight;
	return height;
}

function changeCondition(){
  changeConditionFlag=true;
  $("#playback").attr("src","Images/play.gif");
}

/** 查询回放轨迹数据 */
function findPlayBackTrack() {
	if(playTrackFlag){
	    playTrackFlag = false;
	     $("#playback").val("播放");
	  }else{
	  	playTrackFlag = true;
	  	$("#playback").val("暂停");
	  if(changeConditionFlag){

		    maplet.clearOverlays();//清屏
	    	changeConditionFlag=false;
	    		$("#finish").html("");
	       var xx = $("#beginTime").val().split(/[-\s:]/); // 开始时间
	   var yy = $("#endTime").val().split(/[-\s:]/); // 结束时间
	  if (xx == '' || yy == '') {
	  		playTrackFlag=false;
			changeConditionFlag=true;
			$("#playback").val("播放");
	  	$("#finish").html("查询时间段不能为空");
		return false;
	}
	var beginTime = (new Date(xx[0], xx[1] - 1, xx[2], xx[3], xx[4], xx[5])).valueOf(); // 把时间转换成毫秒数
	var endTime = (new Date(yy[0], yy[1] - 1, yy[2], yy[3], yy[4], yy[5])).valueOf(); // 把时间转换成毫秒数
	if(beginTime>endTime){
	  	playTrackFlag=false;
			changeConditionFlag=true;
			gpsInfoArray==null;
			$("#playback").val("播放");
			$("#finish").html("开始时间必须大于或等于结束时间");
		return false;
	}
		if((endTime - beginTime) > 259200000){
			playTrackFlag=false;
			changeConditionFlag=true;
			gpsInfoArray==null;
			$("#playback").val("播放");
			$("#finish").html("请将查询时间段控制在3天以内");
		return false;
	}
	
	
	$("#back").css("display", "block"); // 正在......
	var finshTime=beginTime;
	for (var  bt = beginTime; bt <= endTime; bt = (bt + getDataTime)){//1800000
		   var newbt=bt + getDataTime;
		   var newEndTime=newbt>= endTime?endTime:newbt;
		   var jsonParams = {
		   beginTime : bt,
		   endTime : newEndTime,
		   vehicleId : vehicleId
	    };
	    
	   $.ajax({
		url : "monitorCenter/findPlayBackTrack.action",
		type : "POST",
		data : jsonParams,
		success : function(data){
			finshTime=finshTime+getDataTime;
		 if (data!='false') {
			var arrlist = new Array();
			arrlist = eval("(" + data + ")");
		  getTrack(arrlist); // 传给轨迹回放函数
		}
			if(finshTime>=endTime){//获取轨迹数据结束
				  getDataEndFlag=true;
				  if(gpsInfoArray==null||gpsInfoArray.length==0){
				  	  gpsInfoArray=null;
				  		playTrackFlag=false;
		          changeConditionFlag=true;
			        $("#playback").val("播放");
			        $("#back").css("display", "none"); // 正在......
				       $("#finish").html("本车辆在这时间段内没轨迹数，请换其它时间段");
				  }
			}
		}
	})
	
	}
	
	  }
	    
	  }
	
}



// 取轨迹回放数据
function getTrack(gpsInfoArrayTemp) {
	$("#back").css("display", "none"); // 正在......
	if(gpsInfoArray==null){
	  gpsInfoArray = new Array();
	  gpsInfoArray = gpsInfoArrayTemp;
	  posSize = 0; // 从头开始播放
	  timeOutFlag = true; // 默认循环播放状态为true
	 
	playRate =$("#playRate").val();
	 
	playTrack();
	}else{
		for(var i=0;i<gpsInfoArrayTemp.length;i++){
			gpsInfoArray.push(gpsInfoArrayTemp[i]);
		}
	}

}

// 播放轨迹
function playTrack() {
	if (playTrackFlag) {
		// 每次播放的范围
		var endSize = 0;
		if (1 * (posSize + 1) < (gpsInfoArray.length)) {
			endSize = 1 * (posSize + 1);
		}else if(!getDataEndFlag){//获取轨迹数据还没有结束
		    setTimeout("playTrack()", 1000);//暂停1秒
		    return false;
		} else {//播放结束
			try{//画上最后的点
				var lastxy = eval("({" + gpsInfoArray[gpsInfoArray.length] + "})");
				var lastPoint=new Point(lastxy.sHlon, lastxy.sHlat)
				var lastMarker = createimg(lastPoint, 1,lastxy);
				maplet.addOverlay(lastMarker);
			}catch(e){
				
			}
			gpsInfoArray=null;
			playTrackFlag=false;
			changeConditionFlag=true;
			getDataEndFlag=false;
			$("#playback").val("播放");
			$("#finish").html("本次回放已结束");
			return false;
		}

		var pos = new Array(); // 轨迹点数组
		var poss=new Array();//轨迹点转换数组

		// 待播放的轨迹数组
		
		for ( var i = posSize * 1; i <= endSize; i++) {

			// lat,lon ,sd,gs,gt,st,m,o
			// 经度,纬度,速度,状态,时间,停车时长|停车时长毫秒,里程,油位
			var xy = eval("({" + gpsInfoArray[i] + "})");
			pos.push(new Point(xy.sHlon, xy.sHlat));
		}
		 
		// 画图标
		if (posSize % 2 == 0) {
			var point_temp = new Array();
			point_temp = xy.st.split('|');
			var stopTime = 300000;//window.frames["trackFrame"].document.getElementById("stopTime").value;
			var pointNum = 1;
			if (stopTime = point_temp[1]) {
				pointNum = 2;
			} else if (stopTime > point_temp[1]) {
				pointNum = 3;
			}
			// alert(stopTime);
			var newMarker = createimg(pos[0], pointNum,xy);
			
			maplet.addOverlay(newMarker);
		}
		// 画轨迹线
		var pline = new Polyline(pos, "#330099", 2);
		if (posSize == 1) {
			maplet.centerAndZoom(pos[0],15);
		}
	   var boundsLatLng=maplet.getBoundsLatLng();
	   var minX=boundsLatLng.minX();
	   var maxX=boundsLatLng.maxX();
	   var minY=boundsLatLng.minY();
	   var maxY=boundsLatLng.maxY();
	    if(pos[1].getLng()>minX || pos[1].getLng()<maxX  || pos[1].getLat()<maxY || pos[1].getLat()>minY){
         	 maplet.recenterOrPanToLatLng(pos[1]);//平滑
	    } 
	 
		maplet.addOverlay(pline);
		posSize++;
	}
	if (timeOutFlag) {
		setTimeout("playTrack()", playRate);
	}
}

// 地图画点
function createimg(latlng, no,obCar) {
	 
	var icon;
	var w;
	var h;
	if (no == 1) {
		src = "imgs/car/point1.gif";
	} else if (no == 2) {
		src = "imgs/car/point2.gif";
	} else {
		src = "imgs/car/point3.gif";
	}
	w = 15;
	h = 15;
	icon=new Icon();
	icon.heigth=h;
	icon.width=w;
	icon.image=src;
	//var infowindow=createInfoWindow(obCar);
	var newMarker;
	newMarker = new Marker(latlng,icon);
	
	
	//鼠标点击出对详细对话框
	newMarker.addListener("click",function(){
		newMarker.openInfoWinHtml(createInfoWindow(obCar));
		   
	  });
	 

	return newMarker;
}

function createInfoWindow(obCar) {
	// lat,lon ,sd,gs,gd,gt,st,m,o
	// 经度,纬度,速度,状态,司机信息,时间,停车时长|停车时长毫秒,里程,油位
	var time=obCar.gt;
	 time=time.replace(" ","@")
	 var div ='<div><table width="290" border="0" cellspacing="2" cellpadding="0" style="font-size: 12px;">';
	div+='<tr bgcolor="#e6eaea"><td colspan="2" height="30">'+obCar.gs+'</td></tr>'
	   +'<tr><td height="18">速度：'+obCar.sd+'公里/小时</td></tr>'
	   + '<tr bgcolor="#e6eaea"><td height="18">经度：' + obCar.lon+ '</td>' + '<td >纬度：' + obCar.lat + '</td></tr>' 
	   + '<tr ><td height="18">所属行业：' + obCar.kindName+ '</td>' + '<td >所属业户：' + obCar.workunitName + '</td></tr>' 
	    +'<tr bgcolor="#e6eaea"><td><a href="javascript:void(0)" onclick=getDriverMessage("' +time+ '","' + obCar.sim+ '")> 查看司机信息</a></td><td><a href="javascript:void(0)" onclick=getLocationInfo("' + obCar.sim + '","' + obCar.lat + '","' + obCar.lon + '")> 显示位置</a></td></tr>'
	   +'<tr><td colspan="2" id="message" height="50">&nbsp;</td></tr></table></div>';
	div = div
			+ "<table width='260px' height='12px' cellspacing='0' cellpadding='0'><tr><td><span style='color:#0000cc;cursor:pointer;text-decoration:underline;font-size:12px;' onclick='javascript:map.closeInfoWindow();'></span></td>";
	div = div + "<td style='font-size:12px;background:#e6eaea;' align='right'>定位时间:" + obCar.gt + "</td></tr></table>"
	try {
		return div;
	} finally {
		div = null;
	}
}
function getHeadDes(head) {
	if ((head >= 0 && head < 22)||(head >= 336)){
		return "北向";
	}else if(head >= 22 && head <66){
		return "东北向";
	}else if(head>=66&&head<112){
		return "东向";
	}else if(head>=112&&head<156){
		return "东南向";
	}else if(head>=156&&head<202){
		return "南向";
	}else if(head>=202&&head<246){
		return "西南向";
	}else if(head>=246&&head<292){
		return "西向";
	}else if(head>=292&&head<336){
		return "西北向";
	}
	
		
}

/**
 * 获取车辆驾驶员信息
 * @param {} vehicleId
 */
function getDriverMessage(gpsTime,vehicleId){
		document.getElementById("message").innerHTML = '<img src="imgs/load.gif" />正在加载数据......';
  	gpsTime=gpsTime.replace("@"," ");
   var jsonParams = {
		gpsTime : gpsTime,
		vehicleId : vehicleId,
		de : new Date()
	};
	
	
	 	$.ajax({
		url:"monitorCenter/getDriverMessage.action",
		type:"POST",
		data: jsonParams,
		success: function(data){
		    if (data != ''&&data!='null'&& data!=null) {
		    	var name=data.name;
		    	var identityCard=data.identityCard;
		    	var drivingLicence=data.drivingLicence;
		    	var workCertificate=data.workCertificate;
			    var message="姓名:"+data.name;
			    if(identityCard!=''){
			     message+="&nbsp;身份证:"+identityCard;
			    }
			    if(drivingLicence!=''){
			    message+="&nbsp;驾驶证号码:"+drivingLicence;
			    	
			    }
			     if(workCertificate!=''){
			    message+="&nbsp;从业资格证号:"+workCertificate;
			    }
		   $("#message").html(message);
		}else{
			$("#message").html("当前没有司机信息");
		  }
		}
		
	});
	
} 


//弹出窗口,取中文地址
function getLocationInfo(sim,lat,lon) {
	$("#message").html('<img src="imgs/load.gif" />正在加载数据......');
	
	 var param={
    		 date:new Date(),
             lnglat:lon+","+lat	 
             }
	 
	  var url="monitorCenter/getAddressRepeat.action";
	  	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data: param,
		success:function(data){
		try{
			if(data.status=="ok"){
				$("#message").html(data.result.address);
			}else{
				$("#message").html(data.result.error);
			}
		   }catch(exception){
			   $("#message").html("因网络不畅，数据加载未完成,请稍后再试！");
		 }
		
	} ,
	error:function(){
		$("#message").html("因网络不畅，数据加载未完成,请稍后再试！");
	}

		
	});
}
function exportTrack(basePath){
	var xx = $("#beginTime").val().split(/[-\s:]/); // 开始时间
	var yy = $("#endTime").val().split(/[-\s:]/); // 结束时间
	
	if (xx == '' || yy == '') {
		alert('查询时间段不能为空');
		return false;
	}
	var beginTime = (new Date(xx[0], xx[1] - 1, xx[2], xx[3], xx[4], xx[5])).valueOf(); // 把时间转换成毫秒数
	var endTime = (new Date(yy[0], yy[1] - 1, yy[2], yy[3], yy[4], yy[5])).valueOf(); // 把时间转换成毫秒数
	if(beginTime>endTime){
		alert('开始时间必须大于或等于结束时间!');
		return false;
	}
		if((endTime - beginTime) > 259200000){
		alert('请将查询时间段控制在3天以内!');
		return false;
	}
    
     document.location.href=basePath+'monitorCenter/exportTrack.action?vehicleId=' +vehicleId+'&beginTime='+beginTime + '&endTime='+ endTime;
	
}
function backMonitor(basePath){
	parent.mapFrame.location.href=basePath+"monitorCenter/maphn.jsp";
	}