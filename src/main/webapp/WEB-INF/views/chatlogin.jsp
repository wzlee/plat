<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	function init(){
		document.forms[0].submit();
	}
</script>
</head>
<c:if test="${userLogin}">
	<body onload="init();">
	   <form action="http://192.168.0.230/customer/index.php/login" method="post">
<!-- 	   		<input type="hidden" name="eid" value="10012"/> -->
<!-- 	   		<input type="hidden" name="login" value="kf"/> -->
<!-- 	   		<input type="hidden" name="password" value="123"/> -->
	   		<input type="hidden" name="eid" value="${chateid}"/>
	   		<input type="hidden" name="login" value="${user.userName}"/>
	   		<input type="hidden" name="password" value="${user.password}"/>
	   </form>
	</body>
</c:if>
</html>