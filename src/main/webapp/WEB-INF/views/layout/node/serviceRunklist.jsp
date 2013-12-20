<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="column-service">
     <h3>服务排行</h3>
     <ul class="list">
     	<c:forEach items="${serviceList}" var="service" varStatus="status" >
     		<li class="s${status.count}">
     			<a href="" class="type">${service.serviceName}</a>
     			<a title="${service.enterpriseName}" class="name" href="">${service.enterpriseName}</a></li>
     	</c:forEach>
      <!--  <li class="s1"><a href="" class="type">宝石鉴定在在在</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s2"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s3"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s4"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s5"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s6"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s7"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
       <li class="s8"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li> -->
     </ul>
   </div>