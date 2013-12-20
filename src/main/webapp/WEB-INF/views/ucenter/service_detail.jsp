<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
	<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
	<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
   <!-- 左边菜单 -->
   	<jsp:include page="left.jsp" flush="true" />
    <div class="main-column">
    	<div><h3 class="top-title">服务管理 >${service.serviceName}<input onclick="window.location.href='/ucenter/service_list?op=5'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>
    	<div class="pt10"><input id="btn" onclick="showform()" type="submit" value="编辑" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;display:none"></div>
    	<div id="detail" class="auth-form-content" style="display:block;">
                <div class="control-group">
                    <label class="control-label">服务名称：</label>
                    <div class="controls">
                    	<div class="result-text">${service.serviceName}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">服务类别：</label>
                    <div class="controls">
                         <div class="result-text">${service.category.text}</div>                 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">服务方式：</label>
                    <div class="controls"> 
                    	<div class="result-text">${service.serviceMethod}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">价格：</label>
                    <div class="controls">
                        <div class="result-text">
	                        <c:if test="${service.costPrice == 0}">
	                        	面议
	                        </c:if>
	                        <c:if test="${service.costPrice != 0}">
	                        	￥${service.costPrice}
	                        </c:if>                        
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">上传图片：</label>
                    <div class="controls">
                        <div id="picture">
                        	<c:if test="${service.picture.contains('http')}">
                        		<img width="341" height="217" src="${service.picture}" onerror="javascript:this.src='resources/images/service-loading.gif';">
                        	</c:if>
                        	<c:if test="${!service.picture.contains('http')}">
                        		<img width="341" height="217" src="upload/${service.picture}" onerror="javascript:this.src='resources/images/service-loading.gif';">
                        	</c:if>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">描述：</label>
                    <div class="controls">
                        <div class="service-description">${service.serviceProcedure}</div>
                    </div>
                </div> 
        </div>
        <div id="edit" class="auth-form-content" style="display:none;">
               <form class="real_name_auth" action="ucenter/apply" method="post">                
                <div class="control-group">
                    <label class="control-label">服务名称：</label>
                    <div class="controls">                    	
                    	<input id="user" type="hidden" value="${enterpriseId}" name="enterprise.id">
                    	<input type="hidden" value="${service.id}" name="id">
                       	<c:if test="${service.currentStatus == 1}"><input class="input-large" id="serviceName" name="serviceName" type="text" value="${service.serviceName}"></c:if>
                       	<c:if test="${service.currentStatus != 1}"><div class="result-text">${service.serviceName}</div></c:if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">服务类别：</label>
                    <div id="myServices" style="display: none">
	                    [<c:forEach var="item" items="${staffRoleId == 2 ? user.parent.enterprise.myServices : user.enterprise.myServices }" varStatus="flag">
	                    ${flag.index == 0 ? "" : ","}{id : ${item.id }, text : '${item.text }'}
	                    </c:forEach>]
                    </div>
                    <div class="controls">
                        <c:if test="${service.currentStatus == 1}"><input id="serviceclass" class="easyui-combotree" style="height:40px;" name="category.id" value="${service.category.id}"></c:if>
                        <c:if test="${service.currentStatus != 1}"><div class="result-text">${service.category.text}</div></c:if>                  
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">服务方式：</label>
                    <div class="controls">
                    	<div class="checkbox-group">              	
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="柜台式服务" ${fn:indexOf(service.serviceMethod, '柜台式服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">柜台式服务</span>
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="电话服务" ${fn:indexOf(service.serviceMethod, '电话服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">电话服务</span>
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="上门服务" ${fn:indexOf(service.serviceMethod, '上门服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">上门服务</span>
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="信函服务" ${fn:indexOf(service.serviceMethod, '信函服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">信函服务</span>
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="刊物服务" ${fn:indexOf(service.serviceMethod, '刊物服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">刊物服务</span>
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="网络服务" ${fn:indexOf(service.serviceMethod, '网络服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">网络服务</span>
	                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="其他服务" ${fn:indexOf(service.serviceMethod, '其他服务') >= 0  ? 'checked=checked' : '' }><span class="separate-mini">其他服务</span>
                    	</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">价格：</label>
                    <div class="controls">
                        <input class="input-large" id="costPrice" name="costPrice" type="text" value="${service.costPrice}"><span class="separate-mini">元</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">当前服务图片：</label>
                    <div class="controls">
                        <div id="picture" class="picture">
                        	<c:if test="${service.picture.contains('http')}">
                        		<img  width="341" height="217" src="${service.picture}" onError="javascript:this.src='resources/images/service-loading.gif';">
                        	</c:if>
                        	<c:if test="${!service.picture.contains('http')}">
                        		<img  width="341" height="217" src="upload/${service.picture}" onError="javascript:this.src='resources/images/service-loading.gif';">
                        	</c:if>
                        </div>
                    </div>
                    <label class="control-label">上传新图片：</label>
                    <div class="controls">
                        <input class="file" id="picturefile"  type="file" name="file">
                        <input id="imgupdate" type="hidden" name="picture"/>
                        <div class="upload-msg">图片格式可为png, jpg，gif, 大小不超过10M</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">描述：</label>
                    <div id="procedure" class="controls">
                    	<script type="text/plain" id="myEditor" name="serviceProcedure">${service.serviceProcedure}</script>                        
                    </div>
                </div>
                <div class="separate-guide" style="clear:left;"><h3>联系信息</h3></div>
                <div class="control-group">
                    <label class="control-label">联系人：</label>
                    <div class="controls">
                        <input class="input-large" id="linkMan" name="linkMan" type="text" value="${service.linkMan}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系电话：</label>
                    <div class="controls">
                        <input class="input-large" id="tel" name="tel" type="text" value="${service.tel}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">电子邮箱：</label>
                    <div class="controls">
                        <input class="input-large" id="email" name="email" type="text" value="${service.email}">
                    </div>
                </div>
                <div class="control-group" style="">
                    <label class="control-label">在线客服：</label>
                    <div class="controls">
                        <input id="chatgroup" username="${user.userName}" chatgroupid="${chatgroupid}" class="easyui-combobox" style="height:40px;" name="chatgroup">                   
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
		                <button class="submit" id="save" type="button">保存</button>
		                <button class="submit" id="cancel" type="button">取消</button>	                
                    </div>
                </div>
            </form>
        </div>
    </div>
    <form id="picture_form" action="public/uploadFile" method="post" enctype="multipart/form-data"></form>	
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/ueditor.all.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/service_detail.js"></script>
<script type="text/javascript">
	switch(${service.currentStatus}){
		case 1 : $('#btn').css('display','block');				 		
			break;
		case 3: $('#btn').css('display','block');				
			break;
		case 6: $('#btn').css('display','block');			
			break;
		
	};
	UE.getEditor('myEditor',{
    	//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
   		toolbars:[['fontfamily','Bold','italic','underline','fontsize','forecolor','backcolor','justifyleft','justifycenter','justifyright','justifyjustify','link','insertunorderedlist','insertorderedlist','Source',]],
    	//focus时自动清空初始化时的内容
        autoClearinitialContent:false,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,            
        //默认的编辑区域宽度
        initialFrameWidth:530,
        //默认的编辑区域高度
        initialFrameHeight:300
        //更多其他参数，请参考ueditor.config.js中的配置项
   })	
</script>
</body>
</html>