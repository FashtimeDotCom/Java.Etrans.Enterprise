var kind='';
var registrationNo='';
var commandCode='';
function loadBu(kinds){
	var mainFrame = window.dialogArguments; // 接收Left页面传来的参数
	kind=kinds;//mainFrame[0];
	//vehicleId=$.query.get("vehicleId");//mainFrame[1];
	$("input[name=commandCodeVehicle]").bind("click",commandCodeVehicle);
	$("input[name=commandCodePlatform]").bind("click",commandCodePlatform);
	getCommandResult();
}


function commandCodeVehicle(){
	$("#result").html("");
	var pre="command/";
	commandCode = $("input[name=commandCodeVehicle]:checked").val();
	var src=pre+"/vehicle/"+commandCode+".jsp";
	$("#mainFrame2").attr("src","");
	$("#mainFrame2").attr("src",src);
	$("#commandSendThree2").animate({height: 'show', opacity: 'show'}, 400);
}
function commandCodePlatform(){
	$("#result").html("");
	var pre="command/";
	commandCode = $("input[name=commandCodePlatform]:checked").val();
	var src=pre+"/qiye/"+commandCode+".jsp";
	$("#mainFrame2").attr("src","");
	$("#mainFrame2").attr("src",src);
	$("#commandSendThree2").animate({height: 'show', opacity: 'show'}, 400);
}
/**
 * 发送指令
 */
function sendMessage2(paramMeassageValue) {
	if(paramMeassageValue==false){
		return;
	}
	var aryCar = parent.leftFrame.getSelectedCarList();
	if(aryCar==null || aryCar.length==0){
		alert("请选择车辆！");
		return;
	}
	for(var i=0;i<aryCar.length;i++){
		paramMeassageValue['commandCode']=commandCode;
		paramMeassageValue['VehicleId']=aryCar[i];
		paramMeassageValue['terminalType']=kind;
		$.ajax({
			url:"buCommand/sendBuMessage.action",
			type:"POST",
			dataType:"json",
			data:paramMeassageValue,
			success:function(data){
				if(data){
					$("#result").html("发送成功");
				}else{
					$("#result").html("发送失败");
				}
			},
			error:function(data){
				$("#result").html("发送失败");
			}
		});
	}
}

function getCommandResult() {
	//都取7900协议的回复 
    //补发车辆定位信息请求 
	//申请交换指定车辆定位信息请求 
	//取消交换指定车辆定位信息请求 
//	if(getResultVehicle!=null && getResultVehicle.length>0){
//		for(var i=0;i<getResultVehicle.length;i++){
//			var vehicleId= getResultVehicle[i];
	var jsonParams = {
		vehicleId : "7900Command",
		datetimes : new Date().getTime()
	};
	$.post("buCommand/findCommandResult.action", jsonParams, function(data) {
		if (data != 'false' && data!='') {
			var resultObj = $("#result");
			 resultObj.html("回复结果："+data);
		}
	     setTimeout('getCommandResult()', 5000);
   });
}
