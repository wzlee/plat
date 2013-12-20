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
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
    <!-- 左边菜单 -->
   	<jsp:include page="../ucenter/left.jsp" flush="true" />
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
	                    	<input type="hidden" id="bid" value="${res.biddingService.id}"/>
	                        <div class="result-text">${res.biddingService.bidNo}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务类别：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.category.text}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务名称：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.name}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">招标价格：</label>
	                    <div class="controls">
	                    	<c:if test="${res.biddingService.minPrice != null}">
	                    		<div class="result-text">${res.biddingService.minPrice}-${res.biddingService.maxPrice}&nbsp;&nbsp;元</div>
	                    	</c:if>
	                    	<c:if test="${res.biddingService.minPrice == null}">
	                    		<div class="result-text">面议</div>
	                    	</c:if>	                        
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">附件：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.attachment}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系人：</label>
	                    <div class="controls">
	                        <div class="service-description">${res.biddingService.linkMan}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系电话：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.tel}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">邮箱：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.email}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建人：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.user.userName}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建时间：</label>
	                    <div class="controls">
	                        <div class="result-text">${res.biddingService.createTime}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">描述：</label>
	                    <div class="controls">
	                        <div class="service-description">${res.biddingService.description}</div>
	                    </div>
	                </div>
				</div>
           	</div>           	
		        <div class="col-bar">
		        	<div class="title">应标信息</div>
		        	<ul class="action">	        		
		        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
		        	</ul>
		        </div>
	           	<div class="switch-content">				
					<div class="show-info">
		           	    <div class="control-group">
		                    <label class="control-label">用户名：</label>
		                    <div class="controls">
		                        <div class="result-text">${res.user!=null?res.user.userName:res.staff.userName}</div>
		                    </div>
		                </div>
		           	    <div class="control-group">
		                    <label class="control-label">企业名称：</label>
		                    <div class="controls">
		                        <div class="result-text">${loginEnterprise.name}</div>
		                    </div>
		                </div>
		           	    <div class="control-group">
		                    <label class="control-label">应标价格：</label>
		                    <div class="controls">
		                    	<c:if test="${res.bidPrice != null}">
	                    			<div class="result-text">${res.bidPrice}&nbsp;&nbsp;元</div>
	                    		</c:if>
		                    	<c:if test="${res.bidPrice == null}">
		                    		<div class="result-text">面议</div>
		                    	</c:if>		                        
		                    </div>
		                </div>
		           	    <div class="control-group">
		                    <label class="control-label">应标时间：</label>
		                    <div class="controls">
		                        <div class="result-text">${res.responseTime}</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">附件：</label>
		                    <div class="controls">
		                    	<div class="result-text">${res.attachment}</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">联系人：</label>
		                    <div class="controls">
		                        <div class="result-text">${res.linkMan}</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">联系电话：</label>
		                    <div class="controls">
		                        <div class="result-text">${res.tel}</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">邮箱：</label>
		                    <div class="controls">
		                        <div class="result-text">${res.email}</div>
		                    </div>
		                </div>	                
		                <div class="control-group">
		                    <label class="control-label">应标备注：</label>
		                    <div class="controls">
		                        <div class="service-description">${res.description}</div>
		                    </div>
		                </div>
		                <div class="control-group">
                   			<div class="controls">		           	
	                			<button class="submit" id="close" type="button" onclick="window.close();">关 闭</button>		           	
                   			</div>
           				</div>	                
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
					<table id="bidding-log" style="height: 400px">
					</table>
				</div>
           	</div>           	          	
        </div>
    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="jsLib/jquery.easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jsLib/jquery.easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resources/js/ucenter/auth_form.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding_detail.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
</body>
</html>