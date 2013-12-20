<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />

</head>

<body>
	<jsp:include page="header.jsp" flush="true" />
	<!-- /header -->
	<jsp:include page="head.jsp" flush="true" />
	<!-- /user-header -->
	<!-- /container -->
	<div class="main-container">
		<!-- 左边菜单 -->
		<jsp:include page="left.jsp" flush="true" />
		<div class="main-column">
			<h3 class="top-title">用户管理</h3>
			<div class="auth-form-content">
				<div class="col-bar">
					<div class="title">基本信息</div>
					<input type="text" style="display: none;" id="userId"  value ="${user.id }" name=""></input>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<div class="controls">
								<div class="result-text">${user.userName }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">昵称：</label>
							<div class="controls">
								<div class="result-text">${user.nickName == '' or user.nickName == null ? '待完善' : user.nickName }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">真实姓名：</label>
							<div class="controls">
								<div class="result-text">${user.realName == '' or user.realName == null ? '待完善' : user.realName }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别：</label>
							<div class="controls">
								<div class="result-text">
								<c:choose>
									<c:when test="${ user.sex == null }">待完善</c:when>
									<c:otherwise>
										${user.sex == 0 ? '男' : '女' }
									</c:otherwise>
								</c:choose>
								<%-- <c:if test="${user.sex == '' or user.sex == null }">待完善</c:if>
								<c:if test="${user.sex != '' and user.sex != null }">
									<c:out value="${user.sex }"></c:out>
									<c:out value="${user.sex == 0}"></c:out>
									${user.sex == 0 ? '男' : '女' }
								</c:if> --%>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮箱：</label>
							<div class="controls">
								<div class="result-text">${user.email == null or user.email == '' ? '待完善' : user.email}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">用户类型：</label>
							<div class="controls">
								<div class="result-text">
									${user.certSign == 0 ? '未认证用户' : '认证用户'}
								</div>
							</div>
						</div>
						<c:if test="${user.certSign == 1 }">
						<div class="control-group">
							<label class="control-label">个人近照：</label>
							<div class="controls">
								<div class="result-text">
								<c:if test="${user.personalPhoto == null  }">待实名认证</c:if>
								<c:if test="${user.personalPhoto != null  }">
									<c:if test="${user.personalPhoto.contains('http')}">
										<a target="_blank" href="${user.personalPhoto}" class="f-purple">查看图片</a>
									</c:if>
									<c:if test="${!user.personalPhoto.contains('http')}">
										<a target="_blank" href="upload/${user.personalPhoto}" class="f-purple">查看图片</a>
									</c:if>
								</c:if>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">身份证附件：</label>
							<div class="controls">
								<div class="result-text">
									<c:if test="${user.idCardPhoto == null  }">待实名认证</c:if>
									<c:if test="${user.idCardPhoto != null  }">
										<c:if test="${user.idCardPhoto.contains('http')}">
											<a target="_blank" href="${user.idCardPhoto}" class="f-purple">查看图片</a>											
										</c:if>
										<c:if test="${!user.idCardPhoto.contains('http')}">
											<a target="_blank" href="upload/${user.idCardPhoto}" class="f-purple">查看图片</a>											
										</c:if>
									</c:if>
								</div>
							</div>
						</div>
						</c:if>
						<div class="control-group">
							<label class="control-label">联系电话：</label>
							<div class="controls">
								<div class="result-text">${user.mobile == '' or user.mobile == null ? '待完善' : user.mobile }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系地址：</label>
							<div class="controls">
								<div class="result-text">${user.address == '' or user.address == null ? '待完善' : user.address }</div>
							</div>
						</div>
						<%-- <div class="control-group">
							<label class="control-label">是否为领域专家：</label>
							<div class="controls">
								<div class="result-text">${user.isDomainExpert ? '是' : '否' }</div>
							</div>
						</div> --%>
					</div>
					<div class="info-form">
						<form action="" id="userinfoform">
							<input name="id" style="display: none;"  type="text"
										value="${user.id }" />
							<div class="control-group">
								<label class="control-label">用户名：</label>
								<div class="controls">
										<input name="userName" class="input-large" type="text"
											value="${user.userName }" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">昵称：</label>
								<div class="controls">
									<input name="nickName" id="nickName" class= "input-large" type="text"
										value="${user.nickName }" /> 
								</div>
							</div>
						<div class="control-group">
							<label class="control-label">真实姓名：</label>
							<div class="controls">
								<input name="realName" id="realName" class= "input-large" type="text"
										value="${user.realName }" /> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别：</label>
							<div class="controls">
								<div class="checkbox-group">
									
									<input id="sex" name="sex" type="radio" ${user.sex == 0 ? 'checked="checked"' : '' } value="0" />男
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input id="sex" name="sex" type="radio" ${user.sex == 1 ? 'checked="checked"' : '' } value="1" />女
								</div>
							</div>
						</div>
							<div class="control-group">
								<label class="control-label">邮箱：</label>
								<div class="controls">
										<c:choose>
											<c:when test="${user.email == '' ||user.email == null}">
												<input id="userEmail" name="email" class="input-large" type="text"
												value="" />
											</c:when>
											<c:otherwise>
												<input name="email" class="input-large" type="text"
												value="${user.email }" readonly="readonly"/>
												<c:choose>
													<c:when test="${user.isApproved == true}">
														<a href="ucenter/security_uemail?op=88" >更换邮箱并申请认证</a>
													</c:when>
													<c:otherwise><a href="ucenter/security_uemail?op=88" class="f-purple tdu">去邮箱认证</a></c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
								</div>
							</div>
							<input type="hidden" name="certSign" value="${user.certSign }"/>
							<%-- <div class="control-group">
								<label class="control-label">用户类型：</label>
								<div class="controls">
									<input name="certSign" id="certSign" class="input-large" type="text"
										value="${user.certSign == 0 ? '未认证用户':'认证用户'}" readonly="readonly"/>
								</div>
							</div>  --%>
							<div class="control-group">
								<label class="control-label">联系电话：</label>
								<div class="controls">
									<input id="mobile" name="mobile" class="input-large" type="text"
										value="${user.mobile}" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">联系地址：</label>
								<div class="controls">
									<input id="address" name="address" class="input-large" type="text"
										value="${user.address}" />
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">是否为领域专家：</label>
								<div class="controls">
									<div class="checkbox-group">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="isDomainExpert"  type="radio" ${user.isDomainExpert ? 'checked="checked"' : '' } value="true" />是
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="isDomainExpert"  type="radio" ${!user.isDomainExpert ? 'checked="checked"' : '' } value="false" />否
									</div>
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
										<li class="s1" action-data='save' id="infocommit"><a
											href="javascript:void(0)" class="btn-save">保存</a></li>
									
									</ul>	
								</div>
							</div>							
						
						</form>
					</div>
				</div>
				<!-- /基本信息 -->
				<div class="col-bar">
					<div class="title">图像设置</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">用户头像：</label>
							<div class="controls">
								<div class="result-text2">
									<c:if test="${user.headPortraint == 'default_logo.jpg'}">
									<img width="188" height="146" src="resources/images/ucenter/default_logo.jpg" title="用户头像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
									</c:if>
									<c:if test="${user.headPortraint != 'default_logo.jpg' }">
										<c:if test="${user.headPortraint.contains('http')}">
											<img width="188" height="146" src="${user.headPortraint }" title="用户头像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
										</c:if>
										<c:if test="${!user.headPortraint.contains('http')}">
											<img width="188" height="146" src="upload/${user.headPortraint }" title="用户头像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
										</c:if>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="" id="photoform" enctype="multipart/form-data">
							<div class="control-group">
								<label class="control-label">用户头像：</label>
								<div class="controls">
									<input id="headPortraint" class="input-large" type="file" name="file"
										value="${user.headPortraint }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
										<li class="s1" action-data='save' id="photocommit">
											<a href="javascript:void(0)" class="btn-save">保存</a>
										</li>									
									</ul>	
								</div>
							</div>								
						</form>
					</div>
				</div>
				<!-- /图像设置 -->
 				<c:if test="${user.certSign == 1 }"><!-- 认证用户才有我的服务领域 -->
				<div class="col-bar">
					<div class="title">我的服务领域</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">我的服务领域：</label>
							<div class="controls">
								<div class="result-text2" id="myservice2">
									<c:choose>
										<c:when test="${user.userServices.size()>0}">
										<c:forEach items="${user.userServices}" var="item">
	                       				 <span>${item.text }</span>
	                       	 			</c:forEach>
										</c:when>
										<c:otherwise>
	                       	 			待完善
	                       				</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="">
							<input name="user.id" style="display: none;"  type="text"
										value="${user.id }" />
							<div class="control-group">
								<label class="control-label">我的服务领域：</label>
								<div class="controls">
								<div class="result-text2" id="myservice1">
									<c:choose>
										<c:when test="${user.userServices.size()>0}">
										<c:forEach items="${user.userServices}" var="item">
	                       				 <span>${item.text }</span>
	                       	 			</c:forEach>
										</c:when>
										<c:otherwise>
	                       	 			待完善
	                       				</c:otherwise>
									</c:choose>
								</div>
							</div>
								<div class="controls">
									<input class="service-type-my" type="button" value="请选择" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
										<li class="s1" action-data='save' id="myservicecommit"><a
											href="javascript:void(0)" class="btn-save">保存</a></li>
									
									</ul>	
								</div>
							</div>								
						</form>
					</div>
				</div>
				</c:if>
				<!-- /我的服务领域 -->
				<div class="col-bar">
					<div class="title">我感兴趣的服务领域</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">我感兴趣的服务领域：</label>
							<div class="controls">
								<div class="result-text2" id="myinterestservice1">
									<c:choose>
										<c:when test="${user.interestServices.size()>0}">
											<c:forEach items="${user.interestServices}"
												var="interestServiceItem">
	                       			 <span>${interestServiceItem.text }</span>
	                      		  </c:forEach>
										</c:when>
										<c:otherwise>
	                      	 		待完善
	                      	 	</c:otherwise>
									</c:choose>

								</div>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="">
							<div class="control-group">
								<label class="control-label">我感兴趣的服务领域：</label>
								<div class="controls">
								<div class="result-text2" id="myinterestservice2">
									<c:choose>
									<c:when test="${user.interestServices.size()>0}">
											<c:forEach items="${user.interestServices}"
												var="interestServiceItem">
	                       			 <span>${interestServiceItem.text }</span>
	                      		  </c:forEach>
										</c:when>
										<c:otherwise>
	                      	 		待完善
	                      	 	</c:otherwise>
									</c:choose>

								</div>
							</div>
								<div class="controls">
									<input class="service-type" type="button" value="请选择" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
										<li class="s1" action-data='save' id="interestservicescommit"><a
											href="javascript:void(0)" class="btn-save">保存</a></li>
									
									</ul>	
								</div>
							</div>							
						</form>
					</div>
				</div>
				<!-- /我关注的服务领域 -->
			</div>
		</div>
		<div class="clr"></div>
	</div>
	<!-- /container -->
	<jsp:include page="../layout/footer.jsp" flush="true" />
	<!-- 我感兴趣的服务领域 -->
	<div class="overlay interesting">
		<div class="service-layout">
			<div class="service-head">
				请选择服务类别 
				<!-- <span class="tip"><i>*注意</i>:请准确选择符合企业资质的服务,以便顺利通过审核。最多可选10项</span> -->
				<a class="close" href="javascript:void(0)">X</a>
			</div>
			<div class="service-body">
				<div class="selected-items">
					<div class="selected-name">已选择服务类别：</div>
					<ul class="selected-list">
					<c:forEach items="${user.interestServices}" var="item">
						<li>
							<input type="checkbox" value="${item.id }" name="${item.id }" checked="checked"/>
							<span>${item.text }</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:forEach var="parent" items="${requestScope.root.children }">
					<div class="service-row">
						<div class="service-name">${parent.text }</div>
						<ul class="service-list">
							<c:forEach var="leaf" items="${parent.children }">
								<li><input type="checkbox" name="${leaf.id }"
									value="${leaf.id }" ${user.interestServices.contains(leaf)==true?'checked="checked"':'' }  /><span>${leaf.text }</span></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<!-- 我的服务 -->
	<div class="overlay myservice">
		<div class="service-layout-my">
			<div class="service-head-my">
				请选择服务类别 <span class="tip"><i>*注意</i>:请准确选择符合企业资质的服务,以便顺利通过审核。最多可选10项</span><a
					class="close" href="javascript:void(0)">X</a>
			</div>
			<div class="service-body-my">
				<div class="selected-items-my">
					<div class="selected-name-my">已选择服务类别：</div>
					<ul class="selected-list-my">
					<c:forEach items="${user.userServices }" var="item">
						<li>
							<input type="checkbox" value="${item.id}" name="${item.id }" checked="checked">
							<span>${item.text }</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:forEach var="parent" items="${requestScope.root.children }">
					<div class="service-row-my">
						<div class="service-name-my">${parent.text }</div>
						<ul class="service-list-my">
							<c:forEach var="leaf" items="${parent.children }">
								<li>
									<input  type="checkbox" name="${leaf.id }"
										value="${leaf.id }" ${user.userServices.contains(leaf)==true?'checked="checked"':'' } /><span>${leaf.text }</span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/personaluser_manage.js"></script>