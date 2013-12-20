<%@page import="com.eaglec.plat.utils.Constant"%>
<%@ page language="java" import="java.util.*,com.eaglec.plat.domain.base.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Object o = request.getSession().getAttribute("user");
	boolean flag = o instanceof User;
	if(flag){
		request.setAttribute("loginUser", (User)o);
	}
%>
<ul class="sidebar">
	<c:choose>
		<c:when test="<%=flag %>">
			<li ${param.op == 1 ? 'class="on"' : ''}><a href="ucenter/index?op=1">首页</a></li>
			<li ${param.op == 2 ? 'class="on"' : ''}><a href="ucenter/auth?op=2"' }>认证管理</a></li>
			<c:if test="${loginEnterprise.type > 1}">
				<li ${param.op == 46? 'class="on"' : ''}><a href="sme/identifie?op=46"' }>中小微企业认定</a></li>
			</c:if>
		    <li ${param.op == 88 ? 'class="on buyer"' : ''} ><a href="javascript:void(0);">账号管理</a>
				<div class="mod-sub-cate" style="display: none;">
					<dl>
						<dt>
							<a href="ucenter/user_manage?op=88">${loginUser.isPersonal ? '账号管理' : '主账号管理' }</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="ucenter/security?op=88">安全中心</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<c:if test="${!loginUser.isPersonal }">
								<a href="ucenter/staff_list?op=88">子账号管理</a>
							</c:if>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="ucenter/financial?op=88">金融账号管理</a>
							
						</dt>
					</dl>
				</div>
			</li>
			<c:if test="${loginEnterprise.type >= 3 or loginUser.certSign == 1}">
					<li ${param.op == 5 ? 'class="on"' : ''}><a href="ucenter/service_list?op=5">服务管理</a></li>
			</c:if>
			
			<c:if test="${loginEnterprise.type > 1 or loginUser.certSign == 1 }">
			
				<li ${param.op == 8 ? 'class="on buyer"' : ''}><a href="javascript:void(0);">买家管理中心</a>
					<div class="mod-sub-cate" style="display: none;">
						<dl>
							<dt>
								<a href="ucenter/buyer_order?op=8" >订单管理</a>
							</dt>
						</dl>
						<dl>
							<dt>
								<a href="ucenter/buyer_appeal?op=8">申诉管理</a>
							</dt>
						</dl>
					</div>
				</li>
			</c:if>
			
			<c:if test="${loginEnterprise.type == 3 or loginEnterprise.type == 4 or loginUser.certSign == 1}"> <%-- 服务机构拥有 --%>
				<li ${param.op == 11 ? 'class="on buyer"' : ''}><a href="javascript:void(0);">卖家管理中心</a>
					<div class="mod-sub-cate" style="display: none;">
						<dl>
							<dt>
								<a href="ucenter/seller_order?op=11">订单管理</a>
							</dt>
						</dl>
						<dl>
							<dt>
								<a href="ucenter/seller_appeal?op=11">申诉管理</a>
							</dt>
						</dl>
					</div>
				</li>
			</c:if>
			<%-- 招标管理 --%>
			<c:if test="${loginEnterprise.type > 1 or loginUser.certSign == 1}">
				<li ${param.op == 101 ? 'class="on buyer"' : ''}><a href="javascript:void(0);">招标管理</a>
					<div class="mod-sub-cate" style="display: none;">
						<dl>
							<dt>
								<a href="bidding/toAdd?op=101">招标申请</a>
							</dt>
						</dl>
						<dl>
							<dt>
								<a href="bidding/toDeallist?op=101">待我处理</a>
							</dt>
						</dl>
						<dl>
							<dt>
								<a href="bidding/toBiddingList?op=101">我的招标</a>
							</dt>
						</dl>
					</div>
				</li>
			</c:if>
			<%-- 应标管理 （服务机构和政府机构拥有）--%>
			<c:if test="${loginEnterprise.type >= 3 or loginUser.certSign == 1}">
				<li ${param.op == 6 ? 'class="on buyer"' : ''}><a href="javascript:void(0);">应标管理</a>
					<div class="mod-sub-cate" style="display: none;">
						<dl>
							<dt>
								<a href="ucenter/response_push?op=6">给我推送</a>
							</dt>
						</dl>
						<dl>
							<dt>
								<a href="ucenter/my_response?op=6">我的应标</a>
							</dt>
						</dl>
					</div>
				</li>
			</c:if>
			
		    <li><a href="myFavorites/toList">我的收藏</a></li> 
		    
		  <!--    <li class="buyer"><a href="javascript:void(0);">我的社区</a>
				<div class="mod-sub-cate" style="display: none;">
					<dl>
						<dt>
							<a href="#">社交关系</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="#">社区动态</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="#">官方应用</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="#">社区管理</a>
						</dt>
					</dl>
				</div>
			</li>-->
			
			<c:if test="${loginEnterprise.type>1 }">
			  <!--  <li><a href="javascript:void(0);">我的百科</a></li> --> 
			</c:if>
		    
		    <!--  <li><a href="javascript:void(0);">平台客服</a></li>-->
		    
		    <li><a href="javascript:void(0);">平台消息</a>
				<div class="mod-sub-cate" style="display: none;">
					<dl>
						<dt>
							<a href="ucenter/info">站内消息</a>
						</dt>
					</dl>
<!-- 					<dl>
						<dt>
							<a href="ucenter/category">消息类别</a>
						</dt>
					</dl> -->
				</div>
		    </li>
		    
		   <!--  <li><a href="javascript:void(0);">平台帮助</a></li> -->
		    
		    <li><a href="chat/chatonline">在线客服</a></li>
		</c:when>
		<c:otherwise>
			<c:forEach var="menu" items="${staffmenu}" varStatus="status">
				<c:if test="${empty menu.parent}">
					<li ${param.op == menu.id ? 'class="on"' : ''}>
						<a href="${menu.authCode}${fn:indexOf(menu.authCode, '?') > 0 ? '&' : '?'}op=${menu.id}">${menu.text}</a>
						<c:if test="${not empty menu.children}">
							<div class="mod-sub-cate" style="display: none;">
								<c:forEach var="children" items="${menu.children }" varStatus="status">
									<dl><dt><a href="${children.authCode}${fn:indexOf(children.authCode, '?') > 0 ? '&' : '?'}op=${menu.id}">${children.text}</a></dt></dl>
								</c:forEach>
							</div>	
						</c:if>
					</li>
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</ul>