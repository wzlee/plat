<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
<link href="resources/css/main/style.css" rel="stylesheet" type="text/css" />
<link href="resources/js/main/Selecter/jquery.fs.selecter.css" rel="stylesheet" type="text/css" />
<link href="resources/css/main/user.css" rel="stylesheet" type="text/css" />
<script src="jsLib/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/main/Selecter/jquery.fs.selecter.min.js"></script>
<script src="resources/js/jewelry/register.js" type="text/javascript"></script>
</head>

<body>
<div class="register-wrap">
<div class="register-header">
	<div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
    <div class="register-title">用户注册</div>
    <div class="register-link">已经是注册用户？请<a href="login?direct_url=./" class="f-purple">登录</a></div>
</div>
<!-- /register-header -->
<div class="register-content">
   <div class="register-success">
       <div class="success-panel">
           <h2><span class="name">HTM</span>恭喜您已经成功注册SMEmall！</h2>
           <p>此账号作为企业admin账号，可以设定企业员工子账号</p>
       </div>
       <form  action="/user/registerover"method="post" class="second-form" enctype="multipart/form-data">
       <input type="hidden" name="id" value=${user.id }>
	   <%-- <input type="hidden" name="password" value=${user.password }>
	   <input type="hidden" name="email" value=${user.email }>
	   <input type="hidden" name="enterpriseName" value=${user.enterpriseName }>
	   <input type="hidden" name="checkcode" value="sfa"> --%>
       <h6 class="notice">*为了您可以正常使用SMEmall平台服务，并获得更精准的商业机会，我们建议您立即完善以下信息。</p>
        <div class="form-column">
            <h3 class="title">完善资料</h3>
            <div class="control-group">
                <label class="control-label">公司所属行业：</label>
                <div class="controls">
                    <select class="custom-select" name="industryType">
                        <option value="1">互联网行业</option>
                        <option value="2">光伏行业</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">公司联系人：</label>
                <div class="controls"><input type="text" class="xxlarge" name="linkman"/></div>
                <div class="notice">请填写真实姓名</div>
            </div>
            <div class="control-group">
                <label class="control-label">职位：</label>
                <div class="controls"><input type="text" class="xxlarge" name="occupation" /></div>
            </div>
            <div class="control-group">
                <label class="control-label">固定电话：</label>
                <div class="controls">
                    <input name="tel1"id="tel1" type="text" class="large" value="0755" onfocus="this.value=''" onblur="if(this.value=='')this.value='0755'" />
                    <div class="connect-to">-</div>
                    <input name="tel2"id="tel1" type="text" class="xlarge" value="电话号码" onfocus="this.value=''" onblur="if(this.value=='')this.value='电话号码'" />
                </div>
            </div>
            <h3 class="title">公司实名认证</h3>
            <div class="control-group">
                <label class="control-label">公司营业执照：</label>
                <div class="controls"><input type="text" name="licenceDuplicate" id="licenceDuplicate" class="xxlarge"></div>
                <div class="upload-file">
                    <input type="file" name="licenceDuplicatefile" style="display:none;" id="business_licence_file" />
                    <div class="input-append">
                        <a class="btn" onclick="$('#business_licence_file').click();">浏览</a>
                    </div>
                </div>
                <p class="intro">请上传营业执照最新年检副本的扫描件或照片。 <span class="link">查看范例</span><br>支持jpg,png,gif格式，大小不超过2M/张。</p>
            </div>
            <div class="control-group">
                <label class="control-label">组织机构号：</label>
                <div class="controls"><input name="icRegNumber" type="text" class="xxlarge" /></div>
            </div>
            <div class="control-group">
                <label class="control-label">企业公函：</label>
                <div class="controls"><input type="text" name="businessLetter" id="businessLetter" class="xxlarge" /></div>
                <div class="upload-file">
                    <input type="file" name="businessLetterfile" style="display:none;" id="official_letter_file" />
                    <div class="input-append">
                        <a class="btn" onclick="$('#official_letter_file').click();">浏览</a>
                    </div>
                </div>
                <p class="intro">请下载<a class="link" href="">《企业实名认证申请公函》</a> ，加盖企业公章（合同章、财务章<br>等无效）后扫描或者拍照上传。</p>
            </div>
            <div class="control-group">
                <label class="control-label" for="checkcode"></label>
                <div class="controls">
                    <button class="submit-form" type="submit">提交</button>
                    <a href="" class="dont-submit">以后再说</a>
                </div>
            </div>
        </div>
        </form>
   </div>
</div>
<!-- /register-content -->
<div class="user-footer">
       <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
       <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
</div>
</div>
</body>
</html>
