<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="column-org">
     <h3>行业机构TOP10</h3>
     <ul class="list">
     	<c:forEach var="enterprise" items="${enterpriseList }">	
     		 <li><a href="#">${enterprise.name }</a></li>
     	</c:forEach>
     <!--   <li><a href="">Cartier</a></li>
       <li><a href="">布契拉提</a></li>
       <li><a href="">TIFFINY</a></li>
       <li><a href="">周大福</a></li>
       <li><a href="">梵克雅宝</a></li>
       <li><a href="">Graff</a></li>
       <li><a href="">伯爵</a></li>
       <li><a href="">哈利•温斯顿</a></li>
       <li><a href="">MIKIMOTO</a></li>
       <li><a href="">宝格丽</a></li> -->
     </ul>
   </div>