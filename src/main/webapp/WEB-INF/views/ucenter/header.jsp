<%@ page language="java" import="com.eaglec.plat.domain.base.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
Integer enterpriseId = null;	//全局函数定义登录用户的企业id(不管子账号还是主账号)
Integer enterpriseType = null;	//登录企业的企业类型	
Integer roleId = null;			//子账号角色
Integer userType = null;		//用户类型  user或者staff
if(request.getSession().getAttribute("user") != null){
	userType = (Integer)request.getSession().getAttribute("usertype");
	if(userType.equals(1)){//user
		User user = (User)request.getSession().getAttribute("user");
		if(!user.getIsPersonal()){
			enterpriseId = user.getEnterprise().getId();
			enterpriseType = user.getEnterprise().getType();
		}
	}else if(userType.equals(2)){//staff
		Staff staff = (Staff)request.getSession().getAttribute("user");
		roleId = staff.getStaffRole().getId();
		enterpriseId = staff.getParent().getEnterprise().getId();
		enterpriseType = staff.getParent().getEnterprise().getType();
		request.setAttribute("staffRoleId", roleId);
	}
	request.setAttribute("userType", userType);
	request.setAttribute("enterpriseId", enterpriseId);
}
%>
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<c:set value='<%=request.getSession().getAttribute("user")%>' var="msg"/>
<div class="header">
	<div class="container">
    	<div class="topbar">
		<c:choose>
			<c:when test="${empty msg}">
				<div class="top-toolbar-panel" id="nologin" >
					你好，欢迎来到 <span>SMEmall</span>. [ <a class="login f-purple" href="login" >登录</a> | <a href="signup">免费注册</a> ]
				</div>
			</c:when>
			<c:otherwise>
				<div class="top-toolbar-panel" id="yselogin" >
					你好，欢迎来到 <span>SMEmall</span>.&nbsp;<a class="login" style="color:#660066;font-weight:600" href="ucenter/index?op=1">${msg.userName }</a> | <a href="logout" style="color:#666666;">退出</a>
				</div>
			</c:otherwise>
		</c:choose>
    </div>
    <div class="top-main">
    
      <div class="logo"><h1><a href=""><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
      <div class="title">企业用户应用中心</div>
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
				      	<c:otherwise>'服务'</c:otherwise>
      				</c:choose> 
      			data-index=
      				<c:choose>
				      	<c:when test="${param.search_index == 2 }">'2'</c:when>
				      	<c:when test="${param.search_index == 3 }">'3'</c:when>
				      	<c:when test="${param.search_index == 1 }">'1'</c:when>
				      	<c:otherwise>'1'</c:otherwise>
      				</c:choose>  class="txt">
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
	         placeholder=<c:choose>
			      	<c:when test="${param.search_index == 2 }">'输入机构名称'</c:when>
			      	<c:when test="${param.search_index == 3 }">'输入政策名称'</c:when>
			      	<c:when test="${param.search_index == 1 }">'输入服务名称'</c:when>
			      	<c:otherwise>'输入服务名称'</c:otherwise>
      			</c:choose> value="${name}"/>
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
    </div>
 </div>
</div>
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/main/main.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.min.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.plugins.min.js"></script>
<script type="text/javascript" src="jsLib/jquery.easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jsLib/jquery.easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var userId = <%=enterpriseId %>
	var enterType = <%=enterpriseType %>
	var isLogin = ${not empty user}; 
</script>