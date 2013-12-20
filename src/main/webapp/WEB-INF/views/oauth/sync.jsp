<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中小企业服务平台-同步登陆</title>
<link href="resources/css/main/style.css" rel="stylesheet" type="text/css" />
<link href="resources/css/main/user.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.login-content{background: none;}
		.oauth-error{
			line-height: 270px;text-align: center;font-size: 14px;
		}
		.oauth-error a{
			color:#606;
		}
		.oauth-error a:hover{
			text-decoration: underline !important;
		}
		.oauth-error span.count{
			padding:0 5px;color:#f00;font-weight:700;font-size:16px;
		}
	</style>
	<script type="text/javascript">
		function dwz_jump(count,url){
			window.setTimeout(function(){ 
		        count--; 
		        if(count > 0) {
		        	document.getElementById("count").innerHTML = count;
		        	dwz_jump(count,url); 
		        } else { 
		            window.location.href = url; 
		        } 
		    }, 1000);
		}
	</script>
</head>
<body onload="dwz_jump(3,'${redirect_url}')">
	<div class="login-wrap">
	    <div class="register-header">
	        <div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
	        <div class="register-title">用户登陆</div>
	        <!-- <div class="register-link">如需帮助，请<a href="javascript:void();" class="f-purple" onclick="alert('暂未开放');">联系客服</a></div>  -->	        
	    </div>
	    <!-- /register-header -->
	    <div class="login-content" style="min-height:350px;">
	     
				<div class="oauth-error">
				    <div class="error-message">
				    	${message},页面将在<span id="count" class="count">3</span>秒后跳转至登录前页面...<a class="redirect_url" href="${redirect_url }">立即跳转</a>
				   	</div>
				</div>
			</div>

		</div>
		<!-- /login-content -->
		<div class="user-footer">
			<p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 |
				技术支持：深圳市依格欣计算机技术有限公司</p>
			<p class="s2">备案号：粤ICP备1234567 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)
				&copy;2012 1234567</p>
		</div>
	</div>
	<c:forEach items="${srcs}" var="src">
		<script type="text/javascript" src="${src }" reload="1"></script>
	</c:forEach>
	<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?a3b9e1844b990a71dee7ae1b99666bcd";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</body>
</html>