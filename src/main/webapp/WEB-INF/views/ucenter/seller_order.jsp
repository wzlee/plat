<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/order.css" />

<style type="text/css">
	
</style>
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
   	<!-- 左边菜单 -->
   	<jsp:include page="left.jsp" flush="true" />
    <div class="main-column">
        <h3 class="top-title">卖家管理中心>订单管理</h3>
        <!-- <div style="color:green;margin:10px;font-weight:bold;">我的交易提醒</div> -->
        <div id="seller-order" class="easyui-tabs" data-options="tabWidth:112,plain:true" style="width:780px;height:650px">
		<div title="订单查询" style="padding:10px">
			<form id="serach_form" method="post">  
			    <div class="c_orderNo">  
			        <label for="orderNo">订单编号:</label>  
			        <input id="orderNo" style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="orderNo" />  <!--data-options="required:true" --> 
			    </div>  
			    <div class="c_serviceName">  
			        <label for="serviceName">服务商品:</label>  
			        <input id="serviceName" style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="serviceName" />  
			    </div>
			    <div class="c_orderStatus">  
			        <label for="email">订单状态:</label>  
			       	<select id="orderStatus" style="width:150px;" class="easyui-combobox" name="orderStatus">  
					    <option value="">所有订单</option>
					    <option value="1">等待卖家确认</option>  
					    <option value="6" ${param.orderStatus == 6 ? 'selected="selected"' : '' }>交易进行中</option>  
					    <option value="7" ${param.orderStatus == 7 ? 'selected="selected"' : '' }>等待买家关闭</option>  
					    <option value="8" >等待卖家关闭</option>  
					    <option value="9" ${param.orderStatus == 9 ? 'selected="selected"' : '' }>申诉处理中</option> 
					    <option value="10" ${param.orderStatus == 10 ? 'selected="selected"' : '' }>交易结束</option>  
					    <option value="11" ${param.orderStatus == 11 ? 'selected="selected"' : '' }>订单取消</option>   
					</select> 
			    </div> 
			    <div class="c_orderTime">  
			      	<label for="email">下单时间:</label>  
			       <input id="startTime" class="easyui-datebox" style="width:150px" type="text"
			       	name="startTime" data-options="showSeconds:false"  >  
			    </div>
			    <div class="c_endTime">
			    	<label for="email">结束时间:</label> 
			     	<input id="endTime" class="easyui-datebox" style="width:150px" type="text"
			        	name="endTime" data-options="showSeconds:false">
			    </div>
			    <div class="c_submit">
			    <input id="search" onclick ="orderList()" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px; margin-top: 4px;color: #800080;">
			    	<!-- <a href="javascript:void(0);" onclick ="orderList()"class="easyui-linkbutton " data-options="iconCls:'icon-search'">查询</a> -->
			    </div>   
			</form>  
			<table id="order-list" style="width:770px;height:524px">
			</table>
		</div>
		<div title="待我处理" style="padding:10px">
			<table id="wait-handler" style="width:750px;height:609px">

			</table>
		</div>
		<div title="待我确认" ${param.orderStatus == 1 ? 'selected="true"' : '' } style="padding:10px">
			<table id="wait-confirm" style="width:750px;height:609px">

			</table>
		</div>
		<div title="待我关闭" ${param.orderStatus == 8 ? 'selected="true"' : '' } style="padding:10px">
			<table id="wait-close" style="width:750px;height:609px">

			</table>
		</div>
    </div>
    <div class="clr"></div>
    <!-- 确认窗口
    <div id="win" class="easyui-window" title="订单确认" style="width:410px;height:460px"  
        data-options="iconCls:'icon-save',modal:true,closed:true">  
   	 	<form id="confirm" method="post" >  
   	 		<input type="hidden" name="orderId" id="orderId"/>
   	 		<table>
   	 			<tr>
   	 				<th>订单编号</th>
   	 				<td id="orderNumber" class="content"></td>
   	 			</tr>	
   	 			<tr>
   	 				<th>申请的服务</th>
   	 				<td id="serviceName" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>价格</th>
   	 				<td class="content">
   	 					<input id="price" type="text" style="text-align:right;padding-right:20px;" class="easyui-numberbox t_price"  name="price" required data-options="min:0,groupSeparator:',',prefix:'￥'" />
   	 				</td>
   	 			</tr>
   	 			<tr>
   	 				<th>买家</th>
   	 				<td id="buyer" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>申请人姓名</th>
   	 				<td id="userName" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>联系电话</th>
   	 				<td id="phone" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>邮箱</th>
   	 				<td id="email" class="content"></td>
   	 			</tr>
   	 			 
   	 			<tr>
   	 				<th>企业名称</th>
   	 				<td class="content"></td>
   	 			</tr>
   	 			
   	 			<tr>
   	 				<th>买家备注</th>
   	 				
   	 				<td id="buyRemark" class= "content">
   	 				 	
   	 					<textarea class="t_buyerRemark" col="20" rows="2" name="buyerRemark" /></textarea>
   	 				
   	 				</td>
   	 			</tr>
   	 			<tr >
   	 				<th>备注</th>
   	 				<td class="content">
   	 					<textarea class="t_remark" col="20" rows="2" name="remark" id="remark"/></textarea>
   	 				</td>
   	 			</tr>
   	 			<tr>
   	 				<td colspan="2">
   	 					<a onclick="orderConfirm();" href="javascript:void(0);" class="easyui-linkbutton " data-options="iconCls:'icon-ok'">确认提交</a>
   	 					<a onclick="orderCancel();" href="javascript:void(0);" class="easyui-linkbutton " data-options="iconCls:'icon-cancel'">取消订单</a>
   	 				</td>
   	 			</tr>
   	 		</table>  
		</form>  
	</div>
	 -->  
	    
	<!-- 交易关闭窗口 
	<div id="dealEndWin" class="easyui-window" title="交易结束" style="width:410px;height:460px"  
        data-options="iconCls:'icon-save',modal:true,closed:true">  
   	 	<form id="close" method="post">  
   	 		<input type="hidden" name=orderId id="orderId"/>
   	 		<table>
   	 			<tr>
   	 				<th>订单编号</th>
   	 				<td id="orderNumber" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>申请的服务</th>
   	 				<td id="serviceName" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>价格</th>
   	 				<td id="price" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>买家</th>
   	 				<td id="buyer" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>申请人姓名</th>
   	 				<td id="userName" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>联系电话</th>
   	 				<td id="phone" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>邮箱</th>
   	 				<td id="email" class="content"></td>
   	 			</tr>
   	 			
   	 			<tr>
   	 				<th>企业名称</th>
   	 				<td class="content"></td>
   	 			</tr>
   	 		
   	 			<tr>
   	 				<th>满意度</th>
   	 				<td class="content">
   	 					<input type="radio" name="satisfaction" checked="checked" value="5"/>十分满意
   	 					<input type="radio" name="satisfaction" value="4"/>满意
   	 					<input type="radio" name="satisfaction" value="3"/>一般
   	 					<input type="radio" name="satisfaction" value="2"/>不满意
   	 					<input type="radio" name="satisfaction" value="1"/>差
   	 				</td>
   	 			</tr>
   	 			<tr >
   	 				<th>备注</th>
   	 				<td class="content"><textarea id="remark" class="t_remark" col="20" rows="2" name="remark" /></textarea></td>
   	 			</tr>
   	 			<tr>
   	 				<td colspan="2">
   	 					<a onclick="closeDeal();" href="javascript:void(0);" class="easyui-linkbutton " data-options="iconCls:'icon-ok'">关闭交易</a>
   	 					<a onclick="$('#dealEndWin').window('close');" href="javascript:void(0);" class="easyui-linkbutton " data-options="iconCls:'icon-undo'">返回</a>
   	 				</td>
   	 			</tr>
   	 		</table>  
		</form>  
	</div>    
	--> 
	<!-- 申诉窗口
	<div id="appealWin" class="easyui-window" title="我要申诉" style="width:410px;height:500px"  
        data-options="iconCls:'icon-save',modal:true,closed:true">  
   	 	<form id="appeal" method="post">  
   	 		<input type="hidden" name="orderId" id="orderId"/>
   	 		<table>
   	 			<tr>
   	 				<th>订单编号</th>
   	 				<td id="orderNumber" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>申请的服务</th>
   	 				<td id="serviceName" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>价格</th>
   	 				<td id="price" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>买家</th>
   	 				<td id="buyer" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>申请人姓名</th>
   	 				<td id="userName" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>联系电话</th>
   	 				<td id="phone" class="content"></td>
   	 			</tr>
   	 			<tr>
   	 				<th>邮箱</th>
   	 				<td id="email" class="content"></td>
   	 			</tr>
   	 			 
   	 			<tr>
   	 				<th>企业名称</th>
   	 				<td class="content"></td>
   	 			</tr>
   	 			
   	 			<tr>
   	 				<th>申诉原因</th>
   	 				<td class="content">
   	 					<select id="cc" class="easyui-combobox" name="reason" style="width:150px;height:25px;">  
						    <option value="1">买家违约</option>  
						    <option value="2">卖家违约</option> 
						    <option value="3">其他</option>     
						</select> 
   	 				</td>
   	 			</tr>
   	 			<tr>
   	 				<th>备注</th>
   	 				<td class="content"><textarea id="remark" class="t_remark" col="20" rows="2" name="remark" /></textarea></td>
   	 			</tr>
   	 			<tr>
   	 				<th>附件</th>
   	 				<td class="content">
   	 					<input id="attachment" type="file" name="file"/>
   	 					<input type="hidden" name="attachment"/>
   	 				</td>
   	 			</tr>
   	 			<tr>
   	 				<td colspan="2">附件格式可为.txt,.doc,.pdf,.rar,.zip,.xls,大小不超过10M</td>
   	 			</tr>
   	 			<tr>
   	 				<td colspan="2">
   	 					<a onclick="appealSubmit();" href="javascript:void(0);" class="easyui-linkbutton " data-options="iconCls:'icon-ok'">提交申诉</a>
   	 					<a onclick="$('#appealWin').window('close');" href="javascript:void(0);" class="easyui-linkbutton " data-options="iconCls:'icon-undo'">返回</a>
   	 				</td>
   	 			</tr>
   	 		</table>  
		</form>  
	</div> 
	 -->
</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/seller_order.js"></script>
</body>
</html>