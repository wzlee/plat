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
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="../ucenter/head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
    <!-- 左边菜单 -->
   	<jsp:include page="../ucenter/left.jsp" flush="true" />
    <div id="appeal" class="main-column">
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
	                    	<input type="hidden" name="orderId" id="orderId" value="${goodsOrder.id}"/>
	                       	<input type="hidden" id="bid" value="${goodsOrder.biddingService.id }" />
	                        <div class="result-text">${goodsOrder.biddingService.bidNo}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务类别：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.category.text}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务名称：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.name}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">招标价格：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.minPrice}-${goodsOrder.biddingService.maxPrice}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">附件：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.attachment}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系人姓名：</label>
	                    <div class="controls">
	                        <div class="service-description">${goodsOrder.biddingService.linkMan}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系电话：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.tel}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">邮箱：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.email}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建人：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.user.userName}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建时间：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.createTime}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">描述：</label>
	                    <div class="controls">
	                        <div class="result-text">${goodsOrder.biddingService.description}</div>
	                    </div>
	                </div>
				</div>
           	</div>
           	<c:if test="${responseBidding != null }">
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
		                        <div class="result-text">${responseBidding.user.userName }</div>
		                    </div>
		                </div>
		           	    <div class="control-group">
		                    <label class="control-label">企业名称：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.user.enterprise.name }</div>
		                    </div>
		                </div>
		           	    <div class="control-group">
		                    <label class="control-label">应标价格：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.bidPrice }元</div>
		                    </div>
		                </div>
		           	    <div class="control-group">
		                    <label class="control-label">应标时间：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.responseTime }</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">附件：</label>
		                    <div class="controls">
		                    	<div class="result-text">${responseBidding.attachment }</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">联系人：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.linkMan }</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">联系电话：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.tel }</div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <label class="control-label">邮箱：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.email }</div>
		                    </div>
		                </div>	                
		                <div class="control-group">
		                    <label class="control-label">应标备注：</label>
		                    <div class="controls">
		                        <div class="result-text">${responseBidding.description }</div>
		                    </div>
		                </div>	                
	                </div>
	           	</div>
           	</c:if>
   
             <div class="col-bar">
		        	<div class="title">申诉信息</div>
		        	<ul class="action">	        		
		        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
		        	</ul>
		     </div>
		  	<div class="switch-content">				
					<div class="show-info">
					 	<div class="control-group">
		                    <label class="control-label">申诉原因：</label>
		                     <div class="controls">
		                     	<div class="checkbox-group">
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
					               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					            <button class="submit" type="button" onclick="window.location.href='/ucenter/buyer_order?op=8'">返回</button>
				           	
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
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/auth_form.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding_detail.js"></script>
<script type="text/javascript" src="resources/js/ucenter/order.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
</body>
</html>