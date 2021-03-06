var CONST = {}; // 常量对象
CONST.ROOT_NODE_ID = 1; // 默认根节点ID
var parentNodeId = CONST.ROOT_NODE_ID; // 父节点(功能)ID

$(function(){
	initTree();
	$("#submitBtn").click(toSearch);
});

function initTree() {
	
	// 终端
		$('#terminalKindIDTree').tree({
			url : "sys/tree/getTerminalKindIDTree.action",
			animate : true,
			onClick : function(node) {
			  var ids=node.id;
			  var strs=ids.split("|"); //字符分割      
			  window.parent.document.getElementById("TerminalKindID").value =strs[0];
		      window.parent.document.getElementById("TerminalKindIDName").value =node.text;
		      var oInput = window.parent.document.getElementById("TerminalKindIDName");
		      oInput.focus();//验证时获取到鼠标焦点
		      window.parent.closeDialog();
			}
		});	
}

function toSearch(){
	
	var terminalKindName = $("#terminalKindName").val();
	
	if(terminalKindName.length>0){
		   $('#terminalKindIDTree').tree({
				url : "sys/tree/getTerminalKindIDTree.action?terminalKindName="+terminalKindName,
				animate : true,
				onClick : function(node) {
				  var ids=node.id;
				  var strs=ids.split("|"); //字符分割      
				  window.parent.document.getElementById("TerminalKindID").value =strs[0];
			      window.parent.document.getElementById("TerminalKindIDName").value =node.text;
			      var oInput = window.parent.document.getElementById("TerminalKindIDName");
			      oInput.focus();//验证时获取到鼠标焦点
			      window.parent.closeDialog();
				}
			});	
	}else{
		$('#terminalKindIDTree').tree({
			url : "sys/tree/getTerminalKindIDTree.action",
			animate : true,
			onClick : function(node) {
			  var ids=node.id;
			  var strs=ids.split("|"); //字符分割      
			  window.parent.document.getElementById("TerminalKindID").value =strs[0];
		      window.parent.document.getElementById("TerminalKindIDName").value =node.text;
		      var oInput = window.parent.document.getElementById("TerminalKindIDName");
		      oInput.focus();//验证时获取到鼠标焦点
		      window.parent.closeDialog();
			}
		});	
}   
}




