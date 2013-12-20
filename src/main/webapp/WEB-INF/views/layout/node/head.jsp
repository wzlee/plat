<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/login.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/style.css" />
<c:set value='<%=request.getSession().getAttribute("user")%>' var="msg"/>
	<div class="header">
    <div class="top-toolbar">
	<c:choose>
		<c:when test="${empty msg}">
			<div class="top-toolbar-panel" id="nologin" >
				你好，欢迎来到 <span class="name">SMEmall</span>. [ <a class="login" href="login">登录</a> | <a href="/register_step_1?direction=${direction }">免费注册</a> ]
			</div>
		</c:when>
		<c:otherwise>
			<div class="top-toolbar-panel" id="yselogin" >
				你好，欢迎来到 <span class="name">SMEmall</span>.&nbsp;&nbsp;&nbsp;<a class="login" href="ucenter/auth">${msg.userName }</a> | <a href="logout?direction=${direction }">退出</a>
			</div>
		</c:otherwise>
	</c:choose>
    </div>
    <div class="top-container">
      <div class="logo"><h1><a href=""><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
      <div class="search-box">
        <form method="post" action="">
        <div class="selected">
          <div class="selected-choose">
            <span data-display="服务" data-index="1" class="txt">服务</span>
            <span class="arrow"></span>
          </div>
          <ul class="select-list">
            <li data-display="服务" data-index="1"><a href="javascript:void(0)">服务</a></li>
            <li data-display="机构" data-index="2"><a href="javascript:void(0)">机构</a></li>
          </ul>
        </div>
        <div class="controls">
          <input type="text" id="search-input" class="text" placeholder="搜索服务" />
          <button type="submit" class="button"></button>
        </div>
        </form>
      </div>
    </div>
    <div class="top-nav">
      <ul>
      	
		<c:forEach var="channel" items="${channelList }" varStatus="status">
			<c:choose>
				<c:when test="${channel.cname == '首页'}">
					<li class="on">
						<a href="${channel.chttp}">首页</a>
					</li>
				</c:when>
				<c:otherwise>
					<li><a href="${channel.chttp}">${channel.cname}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
      </ul>
    </div>
</div>
<!-- 申请窗口 -->	
<div class="apply-html hide">
	<form class="apply-form">
	   <table width="100%" class="apply-data">
	      <tr>
	      	<td colspan="3">
	      		请您确认以下信息，并确保信息的真实性，服务申请提交成功
			</td>
	      </tr>
	      <tr>
	      	<td colspan="3">
	      		后，我们将与您线下联系具体事宜。
			</td>
	      </tr>
	   	  <input class="apply-sid" name="sid" type="hidden">
	      <tr><td class="s1" style="font-weight:bold;" >申请的服务：</td><td class="sname"></td></tr>
	      <tr><td class="s1" style="font-weight:bold;" >价格：</td><td class="sprice"></td></tr>
	      <tr>
	      	<td class="s1" style="font-weight:bold;" >申请人姓名：</td>
	      	<td>
	      		<input class="link-name" name="linker" type="text">
	      		<span class="help-inline apply-name-info" data-label="联系人"></span>
	      	</td>
	      </tr>
	      <tr>
	      	<td class="s1" style="font-weight:bold;" >联系电话：</td>
	      	<td class="linkTel">
	      		<span class="tel"></span>
	      		<input class="link-tel" name="linkTel" type="hidden">
	      		<span  class="help-inline apply-tel-info" data-label="联系电话"></span>
	      	</td>
	      	<td style="width:40px"><a onclick="updateTel();" class="updateTel" href="javascript:void(0);">修改</a></td>	
	      </tr>
	      <tr>
	      	<td class="s1" style="font-weight:bold;" >邮箱：</td>
	      	<td class="linkMail"> 	 
	      		<span class="email"></span>
	      		<input class="link-mail" name="linkMail" type="hidden">
	      		<span class="help-inline apply-email-info" data-label="联系邮箱"></span>
	      	</td>
	      	<td style="width:40px"><a onclick="updateEmail();" class="updateEmail" href="javascript:void(0);">修改</a></td>	
	      </tr>
	   </table>
  	</form>
</div>
<!-- 服务确认提交窗口 -->
<div class="apply-html hide">
	<form class="submit-form">
	   <table width="100%" class="apply-data">
	   <tr>
	   		<td style="font-size:14px">服务申请已提交</td>
	   </tr>
	   <tr>
	   		<td>你可在<a href="javascript:void(0);">企业管理用户中心</a>中查看已申请</td>
	   </tr>
	   <tr>
	   		<td><img src="resources/images
	   		/u41_normal.png" /></td>
	   </tr>
	    <tr>
	   		<td>3秒后自动关闭窗口</td>
	   </tr>
	   <tr>
	   		<td><input type="button" value="关闭" /></td>
	   </tr>
	   </table>
  	</form>
</div>
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.min.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.plugins.min.js"></script>
<script type="text/javascript" src="resources/js/public/applyService.js"></script>