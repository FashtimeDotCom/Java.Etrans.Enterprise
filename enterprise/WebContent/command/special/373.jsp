<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	<base href="<%=basePath%>"></base>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>指令发送</title>
		<link href="<%=basePath%>css/command.css" type="text/css" rel="stylesheet" />
	        <script type="text/javascript" src="<%=basePath%>js/jq/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jq/jquery.Query.js"></script>
		<script src="<%=basePath%>js/command/special/373.js" type="text/javascript"></script>
		<script  type="text/javascript" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
	    <style type="text/css">
<!--
.STYLE1 {color: #003366}
-->
        </style>
</head>

	<body>
	 <table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr><td height="20">&nbsp;</td></tr>
			<tr>
			  <td width="100%" align="center">
				  <fieldset style="border-Color:#80CAEA;width: 98%;"><legend>设置圆形区域：指令参数</legend>
				  		<table width="100%" cellpadding="0" cellspacing="1">
				            <tr>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td width="3" rowspan="3" background="imgs/commandbg.gif">.</td>
				              <td>&nbsp;</td>
				              <td colspan="2">&nbsp;</td>
				            </tr>
				            <tr>
				              <td width="3">&nbsp;</td>
				              <td width="269" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				                <tr>
				                  <td height="25" nowrap="nowrap"><div align="right">设置属性：</div></td>
				                  <td valign="top" nowrap="nowrap">
								  	 <select name="param1" id="param1">
										<option value="0">更新区域</option>
										<option value="1">追加区域</option>
										<option value="2">修改区域</option>
<!-- 										<option value="3">删除圆形区域</option> -->
<!-- 										<option value="4">删除所有圆形区域</option> -->
									  </select>								  </td>
			                    </tr>
				                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">区域ID：</div></td>
				                  <td valign="middle" nowrap="nowrap"><div align="left">
                                      <input name="param2" 
											   id="param2" 
											   type="text"/>
				                      (0-255)</td>
			                    </tr>
			                    <tr valign="top">
				                  <td height="25" nowrap="nowrap"><div align="right">经度：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param15" id="param15">
				                    <option value="0">东经</option>
				                    <option value="1">西经</option>
                                                                    </select></td>
			                    </tr>
			                    <tr>
				                  <td height="25" nowrap="nowrap"><div align="right">纬度：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param14" id="param14">
				                    <option value="0">北纬</option>
				                    <option value="1">南纬</option>
                                                                    </select></td>
			                    </tr>
				                <tr>
				                  <td height="25" nowrap="nowrap"><div align="right">出区域/路线报警给平台：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param13" id="param13">
				                    <option value="1">是</option>
				                    <option value="0">否</option>
                                                                    </select></td>
			                    </tr>
				                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">出区域/路线报警给驾驶员：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param12" id="param12">
                                      <option value="1">是</option>
                                      <option value="0">否</option>
                                  </select></td>
			                    </tr>
				                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">进区域/路线报警给平台：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param11" id="param11">
                                      <option value="1">是</option>
                                      <option value="0">否</option>
                                  </select></td>
			                    </tr>
				                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">进区域/路线报警给驾驶员：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param10" id="param10">
                                      <option value="1">是</option>
                                      <option value="0">否</option>
                                  </select></td>
			                    </tr>
				                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">是否限速：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param9" id="param9">
                                      <option value="1">是</option>
                                      <option value="0">否</option>
                                  </select></td>
			                    </tr>
				                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">根据时间：</div></td>
				                  <td valign="top" nowrap="nowrap"><select name="param8" id="param8">
                                      <option value="1">是</option>
                                      <option value="0">否</option>
                                  </select></td>
			                    </tr>
				              </table></td>
				              <td width="30">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				              <td width="16" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
                                  <td height="25" nowrap="nowrap"><div align="right">选择圆形区域：</div></td>
				                  <td valign="top" nowrap="nowrap">
				                    <div align="left">
				                        <select id="area"></select>
                                  	</div>
                                  </td>
			                    </tr>
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">中心点纬度：</div></td>
                                  <td colspan="2" valign="middle" nowrap="nowrap"><div align="left">
                                    <div align="left">
                                      <input name="param3-1" 
											   id="param3-1" 
											   type="text"
											   value=""/>
                                    </div>
                                  </div></td>
                                </tr>
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">中心点经度：</div></td>
                                  <td colspan="2" valign="middle" nowrap="nowrap"><div align="left">
                                    <div align="left">
                                      <input name="param3-2" 
											   id="param3-2" 
											   type="text" 
											   value=""/>
                                    </div>
                                  </div></td>
                                </tr>
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">半径（米）：</div></td>
                                  <td colspan="2" valign="middle" nowrap="nowrap"><div align="left">
                                    <div align="left">
                                      <input name="param3-3" 
											   id="param3-3" 
											   type="text" 
											   size="10"
											   value=""
											  />
                                    </div>
                                  </div></td>
                                </tr>
                                
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">起始时间：</div></td>
                                  <td valign="bottom" nowrap="nowrap"><div align="left"><input id="param4" name="param4" size="18" class="input"  maxlength="12"  style="height: 20px;font-size: 12px;"/>
                                  </div></td>
                                  <td valign="bottom" nowrap="nowrap">&nbsp;<img onClick="WdatePicker({firstDayOfWeek:1,isShowWeek:true,el:document.getElementById('param4'),dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										 src="Images/time.jpg" width="20" height="24" /></td>
                                </tr>
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">结束时间：</div></td>
                                  <td valign="bottom" nowrap="nowrap"><div align="left"><input id="param5" name="param5" size="18" class="input" maxlength="12"  style="height: 20px;font-size: 12px;"/>
                                  </div></td>
                                  <td valign="bottom" nowrap="nowrap">&nbsp;<img onClick="WdatePicker({firstDayOfWeek:1,isShowWeek:true,el:document.getElementById('param5'),dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										 src="Images/time.jpg" width="20" height="24" /></td>
                                </tr>
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">最高速度：</div></td>
                                  <td colspan="2" valign="middle" nowrap="nowrap"><input name="param6" 
											   id="param6" 
											   type="text" 
											   size="10"
											   value=""
											  />(1-255)(km/h)</td>
                                </tr>
                                <tr>
                                  <td height="25" nowrap="nowrap"><div align="right">超速持续时间：</div></td>
                                  <td colspan="2" valign="middle" nowrap="nowrap">
                                    <input name="param7" 
											   id="param7" 
											   type="text" 
											   size="10"
											   value=""
											   />(0-65535)(秒)</td>
                                </tr>
                              </table></td>
				              <td width="16">&nbsp;</td>
				              <td width="13">&nbsp;</td>
<%--				              <td width="184"><a href="javascript:void(0)" onClick="sendMessage();"><img src="imgs/command.gif"  border="0"></a><a  href="javascript:void(0)" class="ser_btn" onClick="parent.hideSpecial();"><img src="imgs/cancel.gif"  border="0"></a><div id="result"></div></td>--%>
<%--				              <td width="685"><div id="back" class="hiddiv" style="display: none;"><img alt="正在发送指令" src="imgs/load.gif">正在发送指令......</div></td>--%>
				            </tr>
				            
							  <tr>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td></td>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td>&nbsp;</td>
				              <td colspan="2">&nbsp;</td>
				            </tr>
		          		</table>
				  </fieldset>
			  </td>
			</tr>
			<%--按钮--%>
			<tr>
	   			 <td valign="top">
	   			 	<a href="javascript:void(0)" onClick="sendMessage();">
	   			 	<img src="imgs/command.gif"  border="0"></a>
	   			 	<a  href="javascript:void(0)" class="ser_btn" onClick="parent.hideSpecial();">
	   			 	<img src="imgs/cancel.gif"  border="0"></a>
	   			 	<div id="result"></div>
	   			 </td>
	   			 <td width="685">
		   			 <div id="back" class="hiddiv" style="display: none;"><img alt="正在发送指令" src="imgs/load.gif">正在发送指令......
		   			 </div>
	   			 </td>
			</tr>
		</table>
	</body>
</html>