<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/search.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/mall_list.css" />
</head>

<body>
	<jsp:include page="layout/head.jsp" flush="true"/>
<div class="wrap">
	<div class="crumb-nav posi">首页 &gt;  <span class="current">服务机构</span></div>
		<div class="organization-cate">
			<div class="cate-guide">全部机构分类</div>
			<ul class="organization-cate-list clearfix">
				<li class="active"><p>信息服务</p></li>
				<li><p>投融资服务</p></li>
				<li><p>创业服务</p></li>
				<li><p>人才与培训服务</p></li>
				<li><p>技术创新和质量服务</p></li>
				<li><p>管理咨询服务</p></li>
				<li><p>市场开拓服务</p></li>
				<li><p>法律服务</p></li>
				<li><p>其他服务</p></li>
			</ul>
			<ul class="organization-cate-sub">
				<li class="s1"><a href="#">信息服务1</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s2"><a href="#">信息服务2</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s3"><a href="#">信息服务3</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s4"><a href="#">信息服务4</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s5"><a href="#">信息服务5</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s6"><a href="#">信息服务6</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s7"><a href="#">信息服务7</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s8">信息服务8 | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
				<li class="s9"><a href="#">信息服务9</a> | <a href="#">投融资服务</a> | <a href="#">创业服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a> | <a href="#">信息服务</a> | <a href="#">投融资服务</a> | <a href="#">创业
服务</a> | <a href="#">人才与培训服务</a> | <a href="#">技术创新和质量服务</a> | <a href="#">管理咨询服务</a> | <a href="#">市场开拓服务</a> | <a href="#">法律服务</a> | <a href="#">其他服务</a></li>
			</ul>
		</div>
		<!-- /organization-cate -->
        <div class="sortbar mt-50">
        <ul class="sortpics">
          <li>
            <a href="" class="active">
              申请数<span class="icon-bottom"></span>    
            </a>
          </li>
          <li><a href="">评价<span class="icon-bottom"></span></a></li>
          <li><a href="">时间<span class="icon-bottom"></span></a></li>
          <li><a href="">价格<span class="icon-bottom"></span></a></li>
          <li class="price-search">
            <div id="rank-priceform">
              <form action="" method="post">
                <input type="text" name="" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" class="pri jiage" value="" />&nbsp;—&nbsp;
                <input type="text" name="" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" class="pri jiage" value="" />
                <input type="reset" name="" class="reset" value="清除" />
                <input type="submit" value="确定" class="search-submit" />
              </form>
            </div>&nbsp;
          </li>      
        </ul>
        <ul class="show-type">
           <li class=""><a class="s1" href="javascript:void(0)" title="大图显示"><span>大图</span></a></li>
           <li class="active"><a class="s2" href="javascript:void(0)" title="列表显示"><span>列表</span></a></li>
        </ul>
      </div>
		  <!-- /条件搜索 -->
	  <div class="search-items">
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul class="clearfix">
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div>
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div>    
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div>  
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div>  	
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div> 	
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div> 	
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div> 	
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div> 	
	    <div class="list-view mall-listd">
	      <div class="pic"><a href=""><img src="resources/images/main/s_pic.jpg" width="168" height="144" /></a></div>
	      <div class="company">
	        <h3 class="name"><a href="">政企一键通</a></h3>
	        <p>  政企一键通介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。字符不超过XX个字符字符不超。</p>
	      	<div class="extend clearfix">
	      		<h4 class="c-name"><a href="">XXXX科技有限公司科技有限</a></h4>
	          <div class="is-cer">认证企业</div>
<!-- 	          <div class="online-consultation"><a href="">在线咨询</a></div> -->
	        </div>
	      </div>
	      <div class="introduction">
	         <ul>
	         	<li class="col-1 pr"><span class="label hd">价&nbsp;&nbsp;&nbsp;&nbsp;格：</span><i>¥</i><em>96542</em></li>
	         	<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span><span class="stars star3"></span></li>
	         	<li class="col-3"><span class="label">已申请：</span>542次</li>
	         	<li class="col-4"><span class="label">发布时间：</span>2013-09-05</li>
	         </ul>
	      </div>
	      <div class="apply-service"><a href="">申请服务</a></div>
	    </div> 		    	    	    	            	    
    </div>	
    <!-- 分页 -->
</div>
<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true"/>
	<script type="text/javascript" src="resources/js/main/mall_list.js"></script>
	<script type="text/javascript" src="resources/js/main/search_service.js"></script>
</body>
</html>