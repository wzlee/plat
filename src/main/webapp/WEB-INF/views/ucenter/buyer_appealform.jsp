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
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link rel="stylesheet" href="resources/js/main/Selecter/jquery.fs.selecter.css" />
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
    	<div><h3 class="top-title">买家管理中心 >提交申诉<input onclick="window.location.href='ucenter/buyer_order?op=8'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>
    	<div id="appeal" class="auth-form-content" style="display:block;">
    		<form id="appeal" action="order/orderappeal" method="post">  
    			<input type="hidden" name="orderId" value="${goodsorder.id }" />
                <div class="control-group">
                    <label class="control-label">订单编号：</label>
                    <div class="controls">
                    	<div class="result-text">${goodsorder.orderNumber}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">申请的服务：</label>
                    <div class="controls">
                         <div class="result-text">${goodsorder.service.serviceName}</div>                 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">价格：</label>
                    <div class="controls">
                    	<div class="result-text">${goodsorder.transactionPrice}</div> 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">卖家：</label>
                    <div class="controls">
                        <div class="result-text">${goodsorder.service.enterprise.name}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系人姓名：</label>
                    <div class="controls">
                        <div class="result-text">${goodsorder.userName}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系电话：</label>
                    <div class="controls">
                        <div class="result-text">${goodsorder.phone}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">邮箱：</label>
                    <div class="controls">
                        <div class="result-text">${goodsorder.email}</div>
                    </div>
                </div>
                <div class="control-group clearfix">
                    <label class="control-label">申诉原因：</label>
                     <div class="controls">
                     	<div class="checkbox-group" id="cc-sel">
	                       <select id="cc" name="reason" style="width:150px;height:30px;">  
							    <option value="1">买家违约</option>  
							    <option value="2">卖家违约</option> 
							    <option value="3">其他</option>     
							</select>
					 	</div>
                    </div>
                </div>
                 <div class="control-group">
                    <label class="control-label">备注：</label>
                    <div class="controls">
                        <textarea name="remark" style="height:120px; width:400px; color:#333333; resize:none;" ></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">附件：</label>
                    <div class="controls">
                    	<div class="checkbox-group">
	                        <input id="attachment" type="file" name="file"/>
	   	 					<input type="hidden" name="attachment"/>
   	 					</div>
   	 					<div class="upload-msg">附件格式可为.jpg,.doc,.pdf,.rar,.zip,.xls,大小不超过10M</div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                     	<button class="submit" onclick="appealSubmit();" type="button">提交申诉</button>
		                <button onclick="window.location.href='ucenter/buyer_order?op=8'" class="submit" type="button">返回</button>     
                    </div>
                </div>
       		</form>
        </div>
   </div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>提交成功！</h2>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/buyer_order?op=8">返回订单管理</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_err').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/buyer_order?op=8">返回订单管理</a></div>
</div>
</div>
<!--<script type="text/javascript" src="jsLib/jquery.easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/order.js"></script>
<script type="text/javascript" src="resources/js/main/Selecter/jquery.fs.selecter.js"></script>
<script type="text/javascript">
$(function(){
	$("#cc").selecter({
        defaultLabel: $("#cc").find('option:selected').text()
    });
})
</script>
</body>
</html>