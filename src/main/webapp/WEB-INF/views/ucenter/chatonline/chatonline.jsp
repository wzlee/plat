<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>在线客服</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
	<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
	
	<style type="text/css">
		.window {
		    background: linear-gradient(to bottom, #F8F8F8 0px, #EEEEEE 20%) repeat-x scroll 0 0 rgba(0, 0, 0, 0);
		}
		.window, .window .window-body {
		    border-color: #D3D3D3;
		}
		.searchimg-button {
		    background: url("jsLib/extjs/shared/icons/fam/image_add.png") no-repeat scroll center center rgba(0, 0, 0, 0);
		    cursor: pointer;
		    display: inline-block;
		    height: 20px;
		    opacity: 0.6;
		    overflow: hidden;
		    vertical-align: top;
		    width: 18px;
		}
		.searchimg-button-hover {
			opacity: 1;
		}
		.combo {
		    border-color: #D3D3D3;
		}
		.dissatisfied{
			border-radius: 3px;
			color: #FFFFFF;
		    display: inline-block;
		    font-size: 11.844px;
		    font-weight: bold;
		    line-height: 14px;
		    padding: 2px 4px;
		    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
		    vertical-align: baseline;
		    white-space: nowrap;
		}
		.chat_dp{
			opacity:0.5;
			filter:alpha(opacity=50);
		}
		.chat_over{
			background:#FBEC88;
		}
		.addgroup{background: url("jsLib/jquery.easyui/themes/icons/groupadd.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.updategroup{background: url("jsLib/jquery.easyui/themes/icons/groupupdate.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.deletegroup{background: url("jsLib/jquery.easyui/themes/icons/groupdelete.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		
		.addusers{background: url("jsLib/jquery.easyui/themes/icons/user_add.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.updateusers{background: url("jsLib/jquery.easyui/themes/icons/user_edit.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.deleteusers{background: url("jsLib/jquery.easyui/themes/icons/user_delete.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		
		.search{background: url("jsLib/jquery.easyui/themes/icons/search.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		
		.robotAdd{background: url("jsLib/jquery.easyui/themes/icons/robotAdd.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.robotUpdate{background: url("jsLib/jquery.easyui/themes/icons/robotUpdate.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.robotDelete{background: url("jsLib/jquery.easyui/themes/icons/robotDelete.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.robotQusery{background: url("jsLib/jquery.easyui/themes/icons/robotQuery.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		
		.questionAdd{background: url("jsLib/jquery.easyui/themes/icons/addQuestion.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.questionUpdate{background: url("jsLib/jquery.easyui/themes/icons/updateQuestion.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.questionDelete{background: url("jsLib/jquery.easyui/themes/icons/deleteQuestion.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		
		.sentencesAdd{background: url("jsLib/jquery.easyui/themes/icons/sentencesAdd.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.sentencesUpdate{background: url("jsLib/jquery.easyui/themes/icons/sentencesUpdate.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		.sentencesDelete{background: url("jsLib/jquery.easyui/themes/icons/sentencesDelete.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
		
		.saveData{background: url("jsLib/jquery.easyui/themes/icons/saveData.png") no-repeat scroll center center rgba(0, 0, 0, 0);}
	</style>
</head>

<body>
	<jsp:include page="../../layout/head.jsp" flush="true" />
	<jsp:include page="../head.jsp" flush="true" />
	<div class="main-container">
		<!-- 左边菜单 -->
		<jsp:include page="../left.jsp" flush="true" />
		<div class="main-column" style="padding:10px;">
        	<h3 class="top-title">在线客服</h3>
        	<div id="chatonline" username="${user.userName}" eid="${loginEnterprise.id}" chateid="${chateid}" class="easyui-tabs" data-options="plain:true" style="width:780px;height:650px">
				<div title="在线聊天" selected="true" style="padding-top:5px;">
					<script src="http://119.147.47.115/webim/runIndex2.do?uid=${chatuserid}"></script>
				</div>
				<c:if test="${usertype != 2}">
					<div title="分组管理" selected="false" style="padding-top:5px;">
						<table id="chatgroup" style="text-align:center;width:780px;height:450px"></table>
					</div>
					<div title="客服管理" selected="false" style="padding-top:5px;">
						<div style="border:0px solid #ccc;width:auto;height:auto;float:left;margin:5px;">
							<table id="chatusers" style="text-align:center;"></table>
						</div>
						<div id="targetGrid" style="border:0px solid #ccc;width:auto;height:auto;float:left;margin:5px;">
							<table id="targetUsers" style="text-align:center;"></table>
						</div>
						<div style="margin: 480px 350px 0px 350px;">
							<a id="chatUserSave" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'saveData'">保存</a>
						</div>
					</div>
					<div title="机器人管理" selected="false" style="display:none;padding-top:5px;">
						<table id="chatrobots" style="text-align:center;width:780px;height:450px"></table>
					</div>
					<div title="常用语管理" selected="false" style="display:none;padding-top:5px;">
						<table id="chatsentences" style="text-align:center;width:780px;height:450px"></table>
					</div>
					<div title="访客检索" selected="false" style="display:none;padding-top:5px;">
						<div id="visitorstoolbar">
							<table>
								<tr style="padding:5px;">
									<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;昵称：</td>
									<td>
										<input id="visitnick" class="easyui-validatebox" style="height:22px;" />
									</td>
									<td width="80px">&nbsp;&nbsp;&nbsp;&nbsp;访问时间：</td>
									<td style="padding-right:10px;">
										<input id="visittime" class="easyui-datebox" style="height:22px;" />
									</td>
									<td>
										<a id="visitsearch" class="easyui-linkbutton" data-options="iconCls:'search',plain:true">查询</a>
									</td>
								</tr>
							</table>
						</div>
						<table id="chatvisitors" style="text-align:center;width:780px;height:450px"></table>
					</div>
					<div title="访客留言" selected="false" style="display:none;padding-top:5px;">
						<div id="notestoolbar">
							<table>
								<tr style="padding:5px;">
									<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;状态：</td>
									<td>
										<input id="notesstatus" name="status" />
									</td>
									<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;群组：</td>
									<td style="padding-right:10px;">
										<input id="notes_groupid" name="group_id" />
									</td>
								</tr>
							</table>
						</div>
						<table id="chatnotes" style="text-align:center;width:780px;height:450px"></table>
					</div>
					<div title="客服评价" selected="false" style="display:none;padding-top:5px;">
						<div id="evalstoolbar">
							<table>
								<tr style="padding:5px;">
									<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;评分：</td>
									<td>
										<input id="evals_grade" name="grade" />
									</td>
									<td width="50px">&nbsp;&nbsp;&nbsp;&nbsp;群组：</td>
									<td style="padding-right:10px;">
										<input id="evals_groupid" name="group_id" />
									</td>
								</tr>
							</table>
						</div>
						<table id="chatevals" style="text-align:center;width:780px;height:450px"></table>
					</div>
				</c:if>
				<div title="聊天记录" selected="false" style="display:none;padding-top:5px">
					<div id="historytoolbar">
						<table>
							<tr style="padding:5px;">
								<td width="80px">&nbsp;&nbsp;&nbsp;&nbsp;查询条件：</td>
								<td>
									<input id="content" class="easyui-validatebox" style="height:22px;" />
								</td>
								<td width="80px">&nbsp;&nbsp;&nbsp;&nbsp;发送时间：</td>
								<td style="padding-right:10px;">
									<input id="sendtime" class="easyui-datebox" style="height:22px;" />
								</td>
								<td>
									<a id="historysearch" class="easyui-linkbutton" data-options="iconCls:'search',plain:true">查询</a>
								</td>
							</tr>
						</table>
					</div>
					<table id="chathistory" style="width:780px;height:450px;"></table>
				</div>
			</div>
		</div>
		<div class="clr"></div>
	</div>
	
	<div id="groupwin" class="easyui-dialog" style="width:350px;height:auto;padding:10px 20px" closed="true" buttons="#group-buttons" data-options="modal:true">
		<form id="groupform" type="add" method="post" novalidate>
			<input id="groupid" type="hidden" name="id"/>
			<table>
				<tr>
					<td width="150px">分组名称：</td>
					<td>
						<input type="text" name="name" class="easyui-validatebox" style="padding:0px;width: 200px;" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>分组机器人：</td>
					<td style="padding:10px 0px;">
						<input id="combox_robots" name="rid">
					</td>
				</tr>
				<tr>
					<td width="150px">分组图标：</td>
					<td>
						<span class="searchbox" style="width:200px;height:20px;border-color:#D3D3D3;">
							<input id="pic" name="pic" readonly="readonly" type="text" class="searchbox-text searchbox-prompt" style="color:#555555;width:178px; height: 20px; line-height: 20px;">
							<span>
								<span class="searchimg-button" style="height:20px;"></span>
							</span>
						</span>
					</td>
				</tr>
				<tr id="previewTD" style="display:none;">
					<td width="150px" style="padding-top:10px;">分组图标：</td>
					<td style="padding-top:10px;">
						<img id="previewImg" />
					</td>
				</tr>
				<tr>
					<td>分组备注：</td>
					<td style="padding-top:10px;">
						<textarea name="desc" style="height:60px;width:200px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div id="group-buttons">
			<a id="savegroup" class="easyui-linkbutton">确定</a>
			<a class="easyui-linkbutton" onclick="$('#groupwin').window('close')">取消</a>
		</div>
	</div>
	
	<div id="groupimgwin" class="easyui-dialog" style="width:250px;height:auto;padding:10px 20px" closed="true" buttons="#groupimg-buttons" data-options="modal:true">
		<form id="groupimgform" method="post" enctype="multipart/form-data" novalidate>
			<input type="file" name="file" style="padding:0px;" />
		</form>
		<div id="groupimg-buttons">
			<a id="savegroupimg" class="easyui-linkbutton">确定</a>
			<a class="easyui-linkbutton" onclick="$('#groupimgwin').window('close')">取消</a>
		</div>
	</div>
	
	<div id="userwin" class="easyui-dialog" style="width:300px;height:auto;padding:10px 20px" closed="true" buttons="#user-buttons" data-options="modal:true">
		<form id="userform" type="add" method="post" novalidate>
			<input id="userid" type="hidden" name="id"/>
			<table>
				<tr>
					<td width="120px">帐号：</td>
					<td>
						<span class="searchbox" style="width:180px; height:20px;">
							<input name="login" readonly="readonly" type="text" class="searchbox-text searchbox-prompt" style="color:#555555;width:155px; height: 20px; line-height: 20px;">
							<span>
								<span class="searchbox-button" style="height:20px;"></span>
							</span>
						</span> 
					</td>
				</tr>
				<tr>
					<td style="padding-top:10px;width:120px;">昵称：</td>
					<td style="padding-top:10px;">
						<input id="nick" name="nick" type="text" class="easyui-validatebox" style="padding:0px;width:180px;" />
					</td>
				</tr>
				<tr>
					<td style="padding-top:10px;width:120px;">邮箱：</td>
					<td style="padding-top:10px;">
						<input id="email" name="email" type="text" class="easyui-validatebox" style="padding:0px;width:180px;" />
					</td>
				</tr>
				<tr>
					<td>所属群组：</td>
					<td style="padding:10px 0px;">
						<input id="group_id" style="width:180px;" name="group_id" />
					</td>
				</tr>
				<tr>
					<td>所属角色：</td>
					<td>
						<select class="easyui-combobox" style="width:180px;" name="role">
							<option value="user">客服</option>
							<option value="admin">管理员</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
		<div id="user-buttons">
			<a id="saveuser" class="easyui-linkbutton">确定</a>
			<a class="easyui-linkbutton" onclick="$('#userwin').window('close')">取消</a>
		</div>
	</div>
	<!-- 子帐号列表 -->
	<div id="userlist" style="padding:2px;">
		<table id="usergrid" style="text-align:center;"></table>
	</div>
	<!-- 机器人配置 -->
	<div id="robotwin" class="easyui-dialog" style="width:330px;height:auto;padding:10px 20px" closed="true" buttons="#robot-buttons" data-options="modal:true">
		<form id="robotform" type="add" method="post" novalidate>
			<input id="robotid" type="hidden" name="id"/>
			<table>
				<tr>
					<td width="150px">机器人名称：</td>
					<td>
						<input type="text" name="name" class="easyui-validatebox" style="padding:0px;width: 200px;" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td align="right">分组备注：</td>
					<td style="padding-top:10px;">
						<textarea name="remark" style="height:60px;width:200px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div id="robot-buttons">
			<a id="saverobot" class="easyui-linkbutton">确定</a>
			<a class="easyui-linkbutton" onclick="$('#robotwin').window('close')">取消</a>
		</div>
	</div>
	
	<!-- 机器人问题Grid界面 -->
	<div id="robotsQuestionWin" class="easyui-dialog" style="width:750px;height:500px;padding:1px" closed="true" data-options="modal:true">
		<table id="robotsQuestionGrid"></table>
	</div>
	
	<!-- 机器人问题配置 -->
	<div id="questionWin" class="easyui-dialog" style="width:330px;height:auto;padding:10px 20px" closed="true" buttons="#question-buttons" data-options="modal:true">
		<form id="questionform" type="add" method="post" novalidate>
			<input id="questionid" type="hidden" name="id"/>
			<input id="q_robotid" type="hidden" name="rid"/>
			<table>
				<tr>
					<td align="right">问题ID：</td>
					<td>
						<input id="questionID" type="text" name="qid" class="easyui-numberbox" style="padding:0px;width: 220px;" data-options="required:true, min:1000, precision:0"/>
					</td>
				</tr>
				<tr>
					<td align="right">问题：</td>
					<td style="padding-top:10px;">
						<input type="text" name="question" class="easyui-validatebox" style="padding:0px;width: 220px;" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td align="right">答案：</td>
					<td style="padding-top:10px;">
						<textarea name="answer" style="height:60px;width:220px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div id="question-buttons">
			<a id="savequestion" class="easyui-linkbutton">确定</a>
			<a class="easyui-linkbutton" onclick="$('#questionWin').window('close')">取消</a>
		</div>
	</div>
	
	<!-- 常用语配置 -->
	<div id="sentencesWin" class="easyui-dialog" style="width:330px;height:auto;padding:10px 20px" closed="true" buttons="#sentences-buttons" data-options="modal:true">
		<form id="sentencesform" type="add" method="post" novalidate>
			<input id="sentencesid" type="hidden" name="id"/>
			<table>
				<tr>
					<td width="150px" align="right">所属群组：</td>
					<td>
						<input id="s_groupID" type="text" name="group_id" />
					</td>
				</tr>
				<tr>
					<td align="right">内容：</td>
					<td style="padding-top:10px;">
						<textarea name="content" style="height:60px;width:200px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div id="sentences-buttons">
			<a id="savesentences" class="easyui-linkbutton">确定</a>
			<a class="easyui-linkbutton" onclick="$('#sentencesWin').window('close')">取消</a>
		</div>
	</div>
	
	<jsp:include page="../../layout/footer.jsp" flush="true" />
	
	<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
	<script type="text/javascript" src="jsLib/jquery.easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jsLib/jquery.easyui/plugins/datagrid-detailview.js"></script>
	<script type="text/javascript" src="jsLib/jquery.easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="resources/js/ucenter/chatonline/chatonline.js"></script>
</body>
</html>

