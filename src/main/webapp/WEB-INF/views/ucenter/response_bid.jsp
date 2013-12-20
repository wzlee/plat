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
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />
<link rel="stylesheet" href="jsLib/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" />
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
    <!-- 左边菜单 -->
   	<jsp:include page="../ucenter/left.jsp" flush="true" />
    <div class="main-column">        
        <div class="auth-form-content">
	        <div class="col-bar">
	        	<div class="title">招标详情</div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">
				<div class="show-info">
	           	    <div class="control-group">
	                    <label class="control-label">招标单号：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.bidNo}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务类别：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.category.text}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">服务名称：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.name}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">招标价格：</label>
	                    <div class="controls">
	                    	<c:if test="${bidding.minPrice != null}">
	                    		<div class="result-text">${bidding.minPrice}-${bidding.maxPrice}&nbsp;&nbsp;元</div>
	                    	</c:if>
	                    	<c:if test="${bidding.minPrice == null}">
	                    		<div class="result-text">面议</div>
	                    	</c:if>	                        
	                        <input id="minPrice" type="hidden" value="${bidding.minPrice}">
	                        <input id="maxPrice" type="hidden" value="${bidding.maxPrice}">
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">附件：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.attachment}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系人：</label>
	                    <div class="controls">
	                        <div class="service-description">${bidding.linkMan}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系电话：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.tel}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">邮箱：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.email}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建人：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.user.userName}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">创建时间：</label>
	                    <div class="controls">
	                        <div class="result-text">${bidding.createTime}</div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">描述：</label>
	                    <div class="controls">
	                        <div class="service-description">${bidding.description}</div>
	                    </div>
	                </div>
				</div>
           	</div>
           	<!-- /基本信息 -->
	        <div class="col-bar">
	        	<div class="title">应标信息</div>
	        	<ul class="action">	        		
	        		<li class="s2" action-data='up'><a href="javascript:void(0)"><span class="hide">开关</span></a></li>
	        	</ul>
	        </div>
           	<div class="switch-content">				
				<div class="show-info">
                <form id="submitResponse" action="">
                	<input type="hidden" value="${user.id}" name="${usertype==1?'user.id':'staff.id'}">                	
                	<input id="bid" type="hidden" value="${bidding.id}" name="biddingService.id">
	           	    <div class="control-group">
	                    <label class="control-label">应标价格：</label>
	                    <div class="controls">
	                        <input id="bidPrice" class="input-large" type="text" name="bidPrice"><a class="result-text">&nbsp;&nbsp;元</a>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系人：</label>
	                    <div class="controls">
	                        <input id="linkMan" class="input-large" type="text" name="linkMan" value="${loginEnterprise.linkman}">
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">联系电话：</label>
	                    <div class="controls">
	                        <input id="tel" class="input-large" type="text" name="tel" value="${loginEnterprise.tel}">
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">邮箱：</label>
	                    <div class="controls">
	                        <input id="email" class="input-large" type="text" name="email" value="${loginEnterprise.email}">
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
	                    <div id="procedure" class="controls">
	                    	<script type="text/plain" id="myEditor" name="description"></script>	                        
	                    </div>
	                </div>	                
	            </form>
                </div>
                <div class="control-group" style="clear:left;padding-top: 20px;">
                    <div class="controls">
		                <button class="submit" id="submit" type="button" style="margin-right: 30px;">提交应标</button>
		                <button class="submit" id="return" type="button">返回</button>
                    </div>
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
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/ueditor.all.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="jsLib/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="resources/js/ucenter/auth_form.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/response_bid.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
<script type="text/javascript">
	UE.getEditor('myEditor',{
    	//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
   		toolbars:[['fontfamily','Bold','italic','underline','fontsize','forecolor','backcolor','justifyleft','justifycenter','justifyright','justifyjustify','link','insertunorderedlist','insertorderedlist','Source',]],
    	//focus时自动清空初始化时的内容
        autoClearinitialContent:true,
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