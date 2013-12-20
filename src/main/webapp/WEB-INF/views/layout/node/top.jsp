<%@ page pageEncoding="utf-8"%>
<c:set value='<%=request.getSession().getAttribute("user")%>' var="user"/>
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/login.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.slider/skitter.styles.css" />
<c:set value='<%=request.getSession().getAttribute("user")%>' var="msg"/>
<input type="hidden" id="attentionid" value="${attentionid }"/> 
<input type="hidden" id="beattentionid" value="${beattentionid }"/> 
<input type="hidden" id="isattention" value="${isattention }"/>  
<div class="header">
    <div class="top-toolbar">
	<c:choose>
		<c:when test="${empty msg}">
			<div class="top-toolbar-panel" id="nologin" >
				你好，欢迎来到 <span class="name">SMEmall</span>. [ <a class="login" href="login">登录</a> | <a href="/signup?direction=${direction }">免费注册</a> ]
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
      <div class="logo"><a href="."><img src="resources/images/logo/${guild}.png" /></a></div>
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
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.min.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.plugins.min.js"></script>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/public/applyService.js"></script>
<script type="text/javascript">
	var isLogin = ${not empty user};
</script>