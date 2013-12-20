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
    	<div><h3 class="top-title">卖家管理中心 >确认订单<input onclick="window.location.href='ucenter/seller_order?op=11'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>
    	<div id="confirm" class="auth-form-content" style="display:block;">
    		<form id="confirm" action="order/orderconfirm" method="post">  
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
                    	<div class="checkbox-group">
                    		<input type="text" id="price" onblur="validatePrice();" name="price" value="${goodsorder.transactionPrice}"/>
                    	</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">买家：</label>
                    <div class="controls">
                        <div class="result-text">${goodsorder.buyer.enterprise.name}</div>
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
                <div class="control-group">
                    <label class="control-label">买家备注：</label>
                     <div class="controls">
                        <div class="result-text">${goodsorder.remark}</div>
                    </div>
                </div>
                 <div class="control-group">
                    <label class="control-label">备注：</label>
                    <div class="controls">
                        <textarea style="height:120px; width: 400px; color:#333333; resize:none;" name="remark" ></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                     	<button class="submit" onclick="orderConfirm();" type="button">提交订单</button>
		                <button onclick="orderCancel();" class="submit" type="button">取消订单</button>        
                    	<button onclick="window.location.href='ucenter/seller_order?op=11'" class="submit" type="button">返回</button>   
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
	<div class="back-bar"><a href="ucenter/seller_order?op=11">返回订单管理</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/seller_order?op=11">返回订单管理</a></div>
</div>
</div>
<div class="overlay" id="validate_err">
<div class="auth-status-alert">
	<a href="javascript:void(0)" class="close"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交信息验证未通过,请确认后提交!</h2>
		</div>
	</div>
	<div class="back-bar"><a href="javascript:void(0);" onclick="closeValidate();">确定</a></div>
</div>
</div>
<!--<script type="text/javascript" src="jsLib/jquery.easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/seller_order.js"></script>

</body>
</html>