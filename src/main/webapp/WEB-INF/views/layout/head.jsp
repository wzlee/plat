<%@ page language="java" import="com.eaglec.plat.domain.base.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Object apply = 0;//是否申请服务权限
	Integer enterpriseId = null;	//全局函数定义登录用户的企业id(不管子账号还是主账号)
	Integer enterpriseType = null;	//登录企业的企业类型	
	if(request.getSession().getAttribute("user") != null){
		Object userType = request.getSession().getAttribute("usertype");
		if(userType.equals(1)){//user
			User user = (User)request.getSession().getAttribute("user");
			if(!user.getIsPersonal()){
				apply = 1;
				enterpriseId = user.getEnterprise().getId();
				enterpriseType = user.getEnterprise().getType();
			}
		}else if(userType.equals(2)){//staff
			Staff staff = (Staff)request.getSession().getAttribute("user");
			apply = staff.getStaffRole().isApply();
			enterpriseId = staff.getParent().getEnterprise().getId();
			enterpriseType = staff.getParent().getEnterprise().getType();
		}
	}
 %>
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/login.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/style.css?v=20131203am" />
	<c:set value='<%=request.getSession().getAttribute("user")%>' var="msg"/>
	<input type="hidden" id="attentionid" value="${attentionid }"/> 
	<input type="hidden" id="beattentionid" value="${beattentionid }"/> 
	<input type="hidden" id="isattention" value="${isattention }"/> 
	<div class="header">
    	<div class="top-toolbar">
		<c:choose>
			<c:when test="${empty msg}">
				<div class="top-toolbar-panel" id="nologin" >
					你好，欢迎来到 <span class="name">SMEmall</span>. [ <a class="login" href="login">登录</a> | <a href="signup">免费注册</a> ]
				</div>
			</c:when>
			<c:otherwise>
				<div class="top-toolbar-panel" id="yselogin" >
					你好，欢迎来到 <span class="name">SMEmall</span>.&nbsp;&nbsp;&nbsp;<a class="login" href="ucenter/index?op=1">${msg.userName }</a> | <a href="logout">退出</a>
				</div>
			</c:otherwise>
		</c:choose>
    </div>
    <div class="top-container clearfix">
      <div class="logo"><h1><a href=""><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
      <div class="search-box">
        <form method="post" action=
        	<c:choose>
		      	<c:when test="${param.search_index == 2 }">'enter/search?search_index=2'</c:when>
		      	<c:when test="${param.search_index == 3 }">'policy/index?search_index=3'</c:when>
		      	<c:when test="${param.search_index == 1 }">'service/result?search_index=1'</c:when>
		      	<c:otherwise>'service/result?search_index=1'</c:otherwise>
      		</c:choose>
        >
	        <div class="selected">
	          <div class="selected-choose">
	            <span data-display=
	            	<c:choose>
				      	<c:when test="${param.search_index == 2 }">'机构'</c:when>
				      	<c:when test="${param.search_index == 3 }">'政策'</c:when>
				      	<c:when test="${param.search_index == 1 }">'服务'</c:when>
				      	<c:otherwise>'请选择'</c:otherwise>
      				</c:choose>
      			 data-index=
      			 	<c:choose>
				      	<c:when test="${param.search_index == 2 }">'2'</c:when>
				      	<c:when test="${param.search_index == 3 }">'3'</c:when>
				      	<c:when test="${param.search_index == 1 }">'1'</c:when>
				      	<c:otherwise>'1'</c:otherwise>
      				</c:choose> class="txt">
      				<c:choose>
				      	<c:when test="${param.search_index == 2 }">机构</c:when>
				      	<c:when test="${param.search_index == 3 }">政策</c:when>
				      	<c:when test="${param.search_index == 1 }">服务</c:when>
				      	<c:otherwise>请选择</c:otherwise>
      				</c:choose></span>
	            <span class="arrow"></span>
	          </div>
	          <ul class="select-list">
	          	<li data-display="服务" data-index="1"><a href="javascript:void(0)">服务</a></li>
	          	<li data-display="机构" data-index="2"><a href="javascript:void(0)">机构</a></li>
	          	<li data-display="政策" data-index="3"><a href="javascript:void(0)">政策</a></li>
	          </ul>
	        </div>
	        <div class="controls">
	         <input type="text" name="name" id="search-input" class="text" 
	         	placeholder=
	         	<c:choose>
			      	<c:when test="${param.search_index == 2 }">'输入机构名称'</c:when>
			      	<c:when test="${param.search_index == 3 }">'输入政策名称'</c:when>
			      	<c:when test="${param.search_index == 1 }">'输入服务名称'</c:when>
			      	<c:otherwise>'输入服务名称'</c:otherwise>
      			</c:choose>
				value="${param.name}"/>
	          <button type="submit" class="button">搜索</button>
	        </div>
	         <!-- 搜索服务结果显示方式 -->
	         <input name="type" type="text" value="0" style="display: none;" />
	         <!-- 查询结果排序方式 -->
	         <input name="order" type="text" style="display: none;" value="${order}"/>
	         <!-- 查询结果升序或者降序排列 -->
	         <input name="upOrDown" type="text" style="display: none;" value="${upOrDown}"/>
	         <!-- 查询价格最小值 -->
	         <input name="min" type="text" style="display: none;" value="${min}"/>
	         <!-- 查询价格最大值 -->
	         <input name="max" type="text" style="display: none;" value="${max}"/>
        </form>
      </div>
      <div class="w-rending" style="position:relative;">
      		<a href="sme/auth" ><img src="resources/images/main/weird.png" alt="中小微企业认定"/></a>
      		<!-- 首页热线电话 -->
      		<c:if test="${pic_phone == 1}">
      		<div style="position:absolute;right:-140px;top:-18px;z-index:1000;"><img src="resources/images/main/tel-0755.png" alt="tel" /></div>
      		<!-- /首页热线电话 -->
      		</c:if>
      </div>
    </div>
    <div class="top-nav clearfix">
      <ul>
		<c:forEach var="channel" items="${channelList}" varStatus="status">
			<li>
				<c:set var="tempUrl" value="${channel.chttp }${fn:indexOf(channel.chttp, '?') > 0 ? '&' : '?'}iop=${channel.id}"/>
				<c:if test="${fn:indexOf(tempUrl, 'flag') > 0 }">
					<c:set var="tempUrl" value="${fn:substring(tempUrl, 0 , fn:indexOf(tempUrl, '?'))}"/>
				</c:if>
				<a ${param.iop==channel.id ? 'style="background: #ffffff;color: #606;"' :'' }
					href="${tempUrl }" 
					target="${channel.linktype}">${channel.cname}</a>
				<c:if test="${channel.children != null}">
					<ul class="sub-nav" style="display: none;">
						<c:forEach var="chachild" items="${channel.children }" varStatus="status">
							<c:if test="${chachild.isChannel}">
								<c:set var="tempUrl" value="${chachild.chttp}${fn:indexOf(chachild.chttp, '?') > 0 ? '&' : '?'}iop=${channel.id}"/>
								<c:if test="${fn:indexOf(tempUrl, 'flag') > 0 }">
									<c:set var="tempUrl" value="${fn:substring(tempUrl, 0 , fn:indexOf(tempUrl, '?'))}"/>
								</c:if>
								<li><a href="${tempUrl }" target="${chachild.linktype}">${chachild.cname}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
      </ul>
    </div>
</div>

<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jsLib/jquery/jquery.form.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.min.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.plugins.min.js"></script>
<script type="text/javascript" src="jsLib/bootstrap/js/bootstrap-button.js"></script>
<script type="text/javascript" src="resources/js/main/main.js?v=20131210am"></script>
<script type="text/javascript">
	var isApply = <%=apply %>;
	var userId = <%=enterpriseId %>;
	var enterType = <%=enterpriseType %>;
	var isLogin = ${not empty user}; 
</script>