<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>中小企业公共服务平台--运营支撑系统</title>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/css/desktop.css" />
	<link rel="stylesheet" type="text/css" href="jsLib/extjs/resources/css/ext-patch.css"/>
	<link rel="stylesheet" type="text/css" href="jsLib/extjs/shared/example.css"/>
    <link rel="stylesheet" type="text/css" href="jsLib/extjs/resources/css/ext-icon.css" />
    <link rel="stylesheet" type="text/css" href="jsLib/HtmlEditorImageUpload/css/styles.css" />
</head>
<body>
    <script type="text/javascript" src="jsLib/extjs/shared/include-ext.js"></script>
    <script type="text/javascript" src="jsLib/extjs/shared/options-toolbar.js"></script>
    <script type="text/javascript" src="jsLib/extjs/shared/examples.js"></script>
    <script type="text/javascript" src="jsLib/extjs/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="jsLib/HtmlEditorImageUpload/HtmlEditorImage.js"></script>
    
    <script type="text/javascript">
		Ext.require('Ext.form.*');

		Ext.onReady(function() {
		
			Ext.QuickTips.init();
		
			Ext.createWidget('form', {
				renderTo: 'htmleditor-panel',
				title: 'Image Upload Plugin Demo',
				bodyPadding: 5,
				frame: true,
				items: [{
                    xtype: 'htmleditor',
					plugins: [new Ext.create('Ext.ux.form.HtmlEditor.imageUpload', {enableContextMenu:true})],
                    height: 400,
                    width : 600,
                    style: 'background-color: white;',
                    anchor: '100%',
					value: '<h1>Welcome to Image Upload Plugin Demo </h1><h3>(Upload limited to 100KB)</h3><img src="https://ssl.gstatic.com/images/icons/feature/filing_cabinet-g42.png" style="float:left;width:64px;height:64px">Check the last button.<img src="img/image_upload.png" style="display:inline"> It will allow You to insert and modify inserted images.<br>In order to modify an image, just click on it and click on the Image Upload button. You will be able to change the alignment, margins, paddings e.t.c.&nbsp;<br><br>I implemented a little script to allow image resizing on drag and on mousewheel in WebKit browsers (Chrome/Safari). Just click on the image and drag it, or use the mousewheel to change its size.<br><br>Please, before using the upload functionallity, make sure that php is on and modify htmlEditorImageUpload.php. You must change $imagesPath and $imagesUrl to suit your server settings.<br><br>$imagesPath must point to the path where the images will be uploaded. For example on a windows Wamp installation will be something like<b> "c:&#92;&#92;wamp&#92;&#92;www&#92;&#92;imageuploadPlugin&#92;&#92;uploads&#92;&#92;"</b><br><br><br>$imagesUrl must point to the http path where the images will be accesible. For example on a Windows Wamp installation will be like <b>"http:\\\\localhost\\imageuploadPlugin\\uploads\\"</b><br>'
                }]

			});
		});
	
	</script>
    <div id="htmleditor-panel" style="width:600px;margin:100px auto"></div>
</body>
</html>
