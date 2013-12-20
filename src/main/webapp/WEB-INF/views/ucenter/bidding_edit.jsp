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
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="../ucenter/head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
    <!-- 左边菜单 -->
   	<jsp:include page="../ucenter/left.jsp" flush="true" />
    <div class="main-column">
       	<h3 class="top-title">招标管理>编辑需求</h3>
        <div class="auth-form-content">
	        <div class="col-bar">
	        	<div class="title">招标详情</div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">
				<div class="show-info">
				<form class="real_name_auth" action="bidding/edit" method="post">
	           	    <div class="control-group">
	                    <label class="control-label" for="username">服务类别：</label>
	                    <div class="controls">
	                    	<input id="categoryId" style="height:40px;width:300px" name="category.id" class="easyui-combobox" value="${bidding.category.id }"/> 
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务名称：</label>
	                    <div class="controls">
	                        <input class="input-large" id="name" name="name" type="text" value="${bidding.name }"/>
	                        <%-- 其他隐藏值 --%>
	                    	<input type="hidden" id="bid" name="id" value="${bidding.id}"/>
	                    	<input type="hidden" name="bidNo" value="${bidding.bidNo}"/>
	                    	<input type="hidden" name="status" value="${bidding.status }"/>
							<input type="hidden" name="${bidding.user == null ? 'staff.id' : 'user.id'}" value="${bidding.user == null ? bidding.staff.id : bidding.user.id }"/>
							<input type="hidden" name="createTime" value="${bidding.createTime }"/>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">招标价格：</label>
	                    <div class="controls">
	                        <input class="input-large" style="width: 120px;" id="minPrice" name="minPrice" value="${bidding.minPrice }" type="text" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" />
							 到
	                        <input class="input-large" style="width: 120px;" id="maxPrice" name="maxPrice" value="${bidding.maxPrice }" type="text" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" />
					                        元
	                    </div>
	                </div>
	                <div class="control-group plupload-file">
	                    <label class="control-label">上传附件：</label>
	                    <div class="controls">
	                    	<button class="submit" type="button" id="uploadon">添加</button>
	                    	<input type="hidden" id="attachment" name="attachment" />
	                    </div>
	                </div>
	                <div class="upload-box"></div>
	                
	                <div class="control-group">
	                    <label class="control-label">描述：</label>
	                    <div class="controls">
	                        <%-- <input class="input-large" id="description" name="description" type="text" value="${bidding.description }"/> --%>
	                        <script type="text/plain" id="myEditor" name="description">${bidding.description }</script>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系人：</label>
	                    <div class="controls">
	                        <input class="input-large" id="linkMan" name="linkMan" type="text" value="${bidding.linkMan }"/>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系电话：</label>
	                    <div class="controls">
	                        <input class="input-large" id="tel" name="tel" type="text" value="${bidding.tel }"/>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">邮箱：</label>
	                    <div class="controls">
	                        <input class="input-large" id="email" name="email" type="text" value="${bidding.email }"/>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <div class="controls">
			                <button id="save" class="submit" type="button">保存</button>
	                    </div>
	                </div>
                </form>
				</div>
           	</div>
            <div class="col-bar">
	        	<div class="title">操作日志>></div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">
           		<div class="show-info">
					<table id="bidding-log" style="height: 400px">
					</table>
				</div>
           	</div>          	
        </div>
    </div>
    <form id="attachment_form" action="public/uploadAttachments" enctype="multipart/form-data" method="post"></form>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close"  onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>提交成功！</h2>
		</div>
	</div>
	<div class="back-bar"><a href="bidding/toBiddingList?op=101">返回我的招标</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close"  onclick="$('#apply_err').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="bidding/toBiddingList?op=101">返回我的招标</a></div>
</div>
</div>
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding_detail.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/ueditor.all.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
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
        initialFrameWidth:450,
        //默认的编辑区域高度
        initialFrameHeight:300
        //更多其他参数，请参考ueditor.config.js中的配置项
   })
</script>
</body>
</html>