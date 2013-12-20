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
	
	<style type="text/css">
		.control-label{
			width: 150px;
		}
	</style>
<link rel="stylesheet" href="resources/js/main/Selecter/jquery.fs.selecter.css" />
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
    	<div><h3 class="top-title">服务管理 >新增服务<input onclick="window.location.href='/ucenter/service_list?op=5'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>
    	<div class="auth-form-content">
            <form class="real_name_auth" action="ucenter/apply" method="post">                
                <div class="control-group">
                    <label class="control-label">服务名称：</label>
                    <div class="controls">
                    	<input id="user" type="hidden" value="${enterpriseId}" name="enterprise.id">
                        <input class="input-large" id="serviceName" name="serviceName" type="text" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">服务类别：</label>
                    
                    <div id="myServices" style="display: none">
	                    [<c:forEach var="item" items="${staffRoleId == 2 ? user.parent.enterprise.myServices : user.enterprise.myServices}" varStatus="flag">
	                    ${flag.index == 0 ? "" : ","}{id : ${item.id }, text : '${item.text }'}
	                    </c:forEach>]
                    </div>
                    <div class="controls service-fangsi">
                        <input id="serviceclass" class="easyui-combotree" style="height:40px;" name="category.id">                   
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">服务方式：</label>
                    <div  class="controls"> 
                    	<div class="checkbox-group">
	                    	<div class="">
	                    		<input id="serviceMethod" type="checkbox" name="serviceMethod" value="柜台式服务"><span class="separate-mini">柜台式服务</span>
		                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="电话服务"><span class="separate-mini">电话服务</span>
		                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="上门服务"><span class="separate-mini">上门服务</span>
		                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="信函服务"><span class="separate-mini">信函服务</span>
		                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="刊物服务"><span class="separate-mini">刊物服务</span>
		                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="网络服务"><span class="separate-mini">网络服务</span>
		                    	<input id="serviceMethod" type="checkbox" name="serviceMethod" value="其他服务"><span class="separate-mini">其他服务</span>
	                    	</div>
                    	</div>                    	
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">价格：</label>
                    <div class="controls">
                        <input class="input-large" id="costPrice" name="costPrice" type="text"  value="0"/><span class="separate-mini">元</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">上传图片：</label>
                    <div class="controls">
                        <input class="file" id="picturefile" type="file" name="file"/>
                        <input id="picture" type="hidden" name="picture"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">描述：</label>
                    <div id="procedure" class="controls">
                        <script type="text/plain" id="myEditor" name="serviceProcedure"></script>                        
                    </div>                    
                </div>
                <div class="separate-guide" style="clear:left;"><h3>联系信息</h3></div>
                <div class="control-group">
                    <label class="control-label">联系人：</label>
                    <div class="controls">
                        <input class="input-large" id="linkMan" name="linkMan" type="text" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系电话：</label>
                    <div class="controls">
                        <input class="input-large" id="tel" name="tel" type="text" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">电子邮箱：</label>
                    <div class="controls">
                        <input class="input-large" id="email" name="email" type="text" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">所属客服组：</label>
                    <div class="controls">
                        <input id="chatgroup" username="${user.userName}" class="easyui-combobox custom-select" style="height:40px;" name="chatgroup">                   
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
		                <button class="submit" id="save" type="button">保存</button>
		                <button class="submit" id="saveandup" type="button">保存并申请上架</button>
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
<script type="text/javascript" src="resources/js/main/Selecter/jquery.fs.selecter.js"></script>
<script type="text/javascript" src="resources/js/ucenter/service_add.js"></script>
<script type="text/javascript">
	UE.getEditor('myEditor',{
    	//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
   		toolbars:[['fontfamily','Bold','italic','underline','fontsize','forecolor','backcolor','justifyleft','justifycenter','justifyright','justifyjustify','link','insertunorderedlist','insertorderedlist','Source']],
    	//focus时自动清空初始化时的内容
        autoClearinitialContent:true,
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


