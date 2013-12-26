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
    	<div>
    		<c:if test="${param.op == 3 }">
    			<h3 class="top-title">买家管理中心 >订单详情<input onclick="window.location.href='/ucenter/buyer_order?op=3'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;" /></h3>
    		</c:if>
    		<c:if test="${param.op == 4 }">
    			<h3 class="top-title">卖家管理中心 >订单详情<input onclick="window.location.href='/ucenter/seller_order?op=4'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;" /></h3>
    		</c:if>
    	</div>
    	<div id="appeal" class="auth-form-content" style="display:block;">
    		<div class="col-bar">
	        	<div class="title">订单详情</div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
	        <div class="switch-content">
	        	<div class="show-info">
	    			<input type="hidden" id="orderId" name="orderId" value="${goodsorder.id }" />
	        		<input type="hidden" id="bid" value="${goodsorder.biddingService.id }" />
	        	<div class="control-group">
                    <label class="control-label">订单编号：</label>
                    <div class="controls">
                    	<div class="result-text">${goodsorder.orderNumber}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">申请的服务：</label>
                    <div class="controls">
                         <div class="result-text">
                         	<c:choose>
                         		<c:when test="${goodsorder.orderSource == 1 }">${goodsorder.service.serviceName }</c:when>
                         		<c:when test="${goodsorder.orderSource == 2 }">${goodsorder.biddingService.name }</c:when>
                         	</c:choose>
                         </div>                 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">价格：</label>
                    <div class="controls">
                    	<div class="result-text">${goodsorder.transactionPrice}</div> 
                    </div>
                </div>
                <c:if test="${param.op == 4 }">
               		<div class="control-group">
	                    <label class="control-label">买家：</label>
	                    <div class="controls">
	                        <div class="result-text">
		                        <c:choose>
	                         		<c:when test="${goodsorder.orderSource == 1 }">${goodsorder.buyer.enterprise.name}</c:when>
	                         		<c:when test="${goodsorder.orderSource == 2 }">${goodsorder.biddingService.user.enterprise.name }</c:when>
	                         	</c:choose>
	                        </div>
	                    </div>
	                </div>
                </c:if>
                <c:if test="${param.op == 3 }">
	                <div class="control-group">
	                    <label class="control-label">卖家：</label>
	                    <div class="controls">
	                        <div class="result-text">
		                        <c:choose>
	                         		<c:when test="${goodsorder.orderSource == 1 }">${goodsorder.service.enterprise.name}</c:when>
	                         		<c:when test="${goodsorder.orderSource == 2 }">${goodsorder.biddingService.rname }</c:when>
	                         	</c:choose>
	                        </div>
	                    </div>
	                </div>
                </c:if>
                <div class="control-group">
                    <label class="control-label">联系人姓名：</label>
                    <div class="controls">
                        <div class="result-text">
                        	${goodsorder.userName}
                        </div>
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
	        	</div>
	        </div>
	        
			<div class="col-bar">
		        <div class="title">评价信息</div>
		        <ul class="action">	        		
		        	<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
		       	</ul>
		    </div>
            <div class="switch-content">				
				<div class="show-info">
					<div class="control-group">
						 <label class="control-label">买家评价：</label>
						 <div class="controls">
						 	<div class="result-text">
						 		<c:if test="${buyerEval == null}">
						 			尚未评价
						 		</c:if>
						 		<c:if test="${buyerEval != null}">
						 			<c:choose>
										<c:when test="${buyerEval == 1 }">差</c:when>
										<c:when test="${buyerEval == 2 }">不满意</c:when>
										<c:when test="${buyerEval == 3 }">一般</c:when>
										<c:when test="${buyerEval == 4 }">满意</c:when>
										<c:when test="${buyerEval == 5 }">很满意</c:when>
									</c:choose>
						 		</c:if>
						 	</div>
						 </div>
					</div>
					<div class="control-group">
						 <label class="control-label">卖家评价：</label>
						 <div class="controls">
						 	<div class="result-text">
						 		<c:if test="${sellerEval == null}">
						 			尚未评价
						 		</c:if>
						 		<c:if test="${sellerEval != null}">
						 			<c:choose>
										<c:when test="${sellerEval == 1 }">差</c:when>
										<c:when test="${sellerEval == 2 }">不满意</c:when>
										<c:when test="${sellerEval == 3 }">一般</c:when>
										<c:when test="${sellerEval == 4 }">满意</c:when>
										<c:when test="${sellerEval == 5 }">很满意</c:when>
									</c:choose>
						 		</c:if>
						 	</div>
						 </div>
					</div>
				</div>
			</div>    
  			<div class="control-group">
            	<div class="controls">
            		<button class="submit" id="close" type="button" onclick="window.close();">关 闭</button>
               	</div>
            </div>
       		<!-- 订单流水 -->
       		<div class="col-bar">
	        	<div class="title">操作日志>></div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">
           		<div class="show-info">
           			 <c:choose>
	                 	<c:when test="${goodsorder.orderSource == 1 }">
	                 		<table id="order-log" style="height: 400px">
							</table>
	                 	</c:when>
	                    <c:when test="${goodsorder.orderSource == 2 }">
	                    	<table id="bidding-log" style="height: 400px">
							</table>
	                    </c:when>
	                 </c:choose>
					
				</div>
           	</div>  
        </div>
    </div>	
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<!--<script type="text/javascript" src="jsLib/jquery.easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/auth_form.js"></script>
<script type="text/javascript" src="resources/js/ucenter/order_detail.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>

</body>
</html>