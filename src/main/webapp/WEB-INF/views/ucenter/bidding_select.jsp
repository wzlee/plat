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
<link type="text/css" rel="stylesheet" href="jsLib/artDialog-5.0.3/skins/default.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
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
        <div class="auth-form-content">
	        <div class="col-bar">
	        	<div class="title">招标详情</div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">
				<div class="show-info">
	           	    <div class="control-group">
	                    <label class="control-label">招标单号：</label>
	                    <div class="controls">
	                    	<input id="bid" type="hidden" value="${bidding.id }"/>
	                    	<div class="result-text">${bidding.bidNo}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务类别：</label>
	                    <div class="controls">
	                         <div class="result-text">${bidding.category.text}</div>                 
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务名称：</label>
	                    <div class="controls"> 
	                    	<div class="result-text">${bidding.name}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">招标价格：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.minPrice}-${bidding.maxPrice}</div>
	                    </div>
	                </div>                
	                <div class="control-group">
	                    <label class="control-label">联系人：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.linkMan}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系电话：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.tel}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">邮箱：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.email}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建人：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.user == null ? bidding.staff.userName : bidding.user.userName }</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建时间：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.createTime}</div>
	                    </div>
	                </div>
				</div>
           	</div>
	        <div class="col-bar">
	        	<div class="title">应标列表</div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">				
				<div class="show-info">
	           	    <table id="response-list" style="height: 250px;">
		            </table>
                </div>
           	</div>
           	<div class="control-group" style="margin-top: 10px;">
                  <div class="controls">
	           	<button class="submit" id="select" type="button">选择服务</button>
	        	<button class="submit" id="cancel" type="button">取消招标</button>
	        	<button class="submit" type="button" onclick="history.back();">返 回</button>
                  </div>
            </div>
            <div class="col-bar">
	        	<div class="title">操作日志>></div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">
           		<div class="show-info">
					<table id="bidding-log" style="height: 250px">
					</table>
				</div>
           	</div>          	
        </div>
    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();">></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>提交成功！</h2>
		</div>
	</div>
	<div class="back-bar"><a href="bidding/toDeallist?op=101">返回待我处理</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_err').hide();">></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="bidding/toDeallist?op=101">返回待我处理</a></div>
</div>
</div>
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding_select.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding_detail.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
</body>
</html>