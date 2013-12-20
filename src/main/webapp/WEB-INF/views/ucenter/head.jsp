<%@page import="com.eaglec.plat.utils.Common"%>
<%@page import="com.eaglec.plat.domain.base.EnterpriseCredit"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	EnterpriseCredit ec = (EnterpriseCredit)request.getSession().getAttribute("enterpriseCredit");
	if(ec != null){
		int buyCredit = Common.round(ec.getBuyCredit()).intValue();
		int sellCredit = Common.round(ec.getSellCredit()).intValue();
		request.setAttribute("buyCredit", buyCredit);
		request.setAttribute("sellCredit", sellCredit);
	}
%>
<c:set value='<%=request.getSession().getAttribute("loginUser")%>' var="loginUser"></c:set>
<c:set value='<%=request.getSession().getAttribute("loginEnterprise")%>' var="loginEnterprise"></c:set>
<c:set value='<%=request.getSession().getAttribute("enterpriseCredit")%>' var="enterpriseCredit"></c:set>
<c:set value='<%=request.getSession().getAttribute("messages")%>' var="messages"></c:set>
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/index.css" />

<div class="container">
    <div class="user-header clearfix">
        <div class="user-photo">
        	<a href="ucenter/index?op=1">
        		<!-- 个人用户头像和企业用logo不同 -->
        		<c:choose>
	        		<c:when test="${loginUser.isPersonal }">
	        			<c:if test="${user.headPortraint == 'default_logo.jpg' }">
	        				<img src="resources/images/ucenter/default_logo.jpg" width="78" height="78" title="用户头像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
	        			</c:if>
	        			<c:if test="${user.headPortraint != 'default_logo.jpg' }">
	        				<c:if test="${user.headPortraint.contains('http')}">
	        					<img src="${user.headPortraint }" width="78" height="78" title="用户头像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
	        				</c:if>
	        				<c:if test="${!user.headPortraint.contains('http')}">
	        					<img src="upload/${user.headPortraint }" width="78" height="78" title="用户头像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
	        				</c:if>
	        			</c:if>
	        		</c:when>
	        		<c:when test="${!loginUser.isPersonal }">
	        			<c:if test="${loginEnterprise.photo == 'enterprise_logo.jpg'}">
							<img width="78" height="78" src="resources/images/ucenter/enterprise_logo.jpg" title="企业图像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
						</c:if>
						<c:if test="${loginEnterprise.photo != 'enterprise_logo.jpg' }">
							<c:if test="${loginEnterprise.photo.contains('http')}">
								<img width="78" height="78" src="${loginEnterprise.photo }" title="企业图像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							<c:if test="${!loginEnterprise.photo.contains('http')}">
								<img width="78" height="78" src="upload/${loginEnterprise.photo }" title="企业图像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
						</c:if>
	        		</c:when>
        		</c:choose>
        	</a>
        </div>
        <div class="user-info ${!loginUser.isPersonal ? 'qiye' : ''}">
            <h3>
            	${user.userName }
            	<!-- 个人用户和企业用户的认证标识 -->
            	<c:choose>
        			<c:when test="${loginUser.isPersonal }">
        				${user.certSign == 0 ? '<div class="no-renz"></div>':'<div class="renz"></div>' }
        			</c:when>
        			<c:when test="${!loginUser.isPersonal }"><span class="line">|</span>${loginEnterprise.name }${loginEnterprise.type == 1 ? '<span class="cer cer-no"></span>' : '<span class="cer"></span>'}</c:when>
        		</c:choose>
            </h3>
            <c:if test="${loginEnterprise.type > 1}"><!-- 认证企业才有信誉评价  暂时取消显示信誉-->
	            <div class="bar clearfix">  
	            	<%-- <c:if test="${loginEnterprise.type == 2 }"><!-- 认证企业显示购买信誉 -->
		            	<div class="appraise"><span>信誉：</span>
		                	<!-- 企业信誉为空时默认信誉满分 -->
		                	<c:if test="${enterpriseCredit == null or enterpriseCredit.buyCredit == 0 }"><span class="meta-star level-0"></span></c:if>
		                	<c:if test="${enterpriseCredit != null and enterpriseCredit.buyCredit != 0 }">
			                	<c:choose>
					        		<c:when test="${buyCredit == 0 }"><span class="meta-star level-0"></span></c:when>
					        		<c:when test="${buyCredit == 1 }"><span class="meta-star level-1"></span></c:when>
					        		<c:when test="${buyCredit == 2 }"><span class="meta-star level-2"></span></c:when>
					        		<c:when test="${buyCredit == 3 }"><span class="meta-star level-3"></span></c:when>
					        		<c:when test="${buyCredit == 4 }"><span class="meta-star level-4"></span></c:when>
					        		<c:when test="${buyCredit == 5 }"><span class="meta-star level-5"></span></c:when>
		        				</c:choose>
	        				</c:if>
		                </div>
	            	</c:if> --%>
	                <%-- <c:if test="${loginEnterprise.type > 2 }"><!-- 服务机构才能出售 -->
		                <div class="appraise"><span>信誉：</span>
		                	<!-- 企业信誉为空时默认信誉满分 -->
		                	<c:if test="${enterpriseCredit == null or enterpriseCredit.sellCredit == 0 }"><span class="meta-star level-0"></span></c:if>
		                	<c:if test="${enterpriseCredit != null and enterpriseCredit.sellCredit != 0 }">
			                	<c:choose>
					        		<c:when test="${sellCredit == 0 }"><span class="meta-star level-0"></span></c:when>
					        		<c:when test="${sellCredit == 1 }"><span class="meta-star level-1"></span></c:when>
					        		<c:when test="${sellCredit == 2 }"><span class="meta-star level-2"></span></c:when>
					        		<c:when test="${sellCredit == 3 }"><span class="meta-star level-3"></span></c:when>
					        		<c:when test="${sellCredit == 4 }"><span class="meta-star level-4"></span></c:when>
					        		<c:when test="${sellCredit == 5 }"><span class="meta-star level-5"></span></c:when>
		        				</c:choose>
	        				</c:if>
		                </div>
	                </c:if> --%>
	                <c:if test="${(userType == 1 and loginEnterprise.type > 1) or (userType == 2 and staffRoleId == 25) or (userType == 2 and staffRoleId == 10) }">
		                <div class="message">系统消息：
		                	<c:if test="${messages[0] == null  }">
		                		0
		                	</c:if>
		                	<c:if test="${messages[0] != null  }">
		                		<a class="f-purple" href="ucenter/info?flag=receiver&messageType=1">${messages[0] }</a>
		                	</c:if>
		                </div>
		                <div class="message">交易消息：
		                	<c:if test="${messages[1] == null  }">
		                		0
		                	</c:if>
		                	<c:if test="${messages[1] != null  }">
		                		<a class="f-purple" href="ucenter/info?flag=receiver&messageType=2">${messages[1] }</a>
		                	</c:if>
		                </div>
	               	</c:if>
	                <c:if test="${(userType == 1 and loginEnterprise.type > 2) or (userType == 2 and staffRoleId == 2) }">
	                	<!-- 服务机构级别以上企业 -->
	                	<div class="add-service user-bg-p"><a href="ucenter/service_add?op=5">增加服务</a></div>
	                </c:if>
	            </div> 
           </c:if>
           		<c:if test="${loginUser.isPersonal and loginUser.certSign == 0}">
           			<!-- 未认证用户 -->
	          	 	<div class="bar-d">
		                <a href="ucenter/user_manage?op=88" class="p-link">完善资料</a>成为认证用户，享受专业服务
		            </div>  
	            </c:if>
        </div>
        <c:if test="${loginUser.isPersonal }"><!-- 个人用户将有有一个广告栏(暂时为空) -->
        	<div class="user-topadv"><img src="" /></div>
       </c:if>
    </div>
     <div class="clr"></div>
</div>
