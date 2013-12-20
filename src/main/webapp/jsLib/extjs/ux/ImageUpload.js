Ext.define('plat.view.util.ImageUpload', {
	extend:'Ext.Container',
	alias:'widget.imageupload',
	url : "",
	style : "padding : 5px",
	initComponent : function() {
//		HRM.External.ImageUpload.superclass.initComponent.call(this, arguments);
		this.addEvents("selected");
		this.add(this.getImage(true), this.getUploadField(true));
		this.callParent(arguments);
	},
	getImage : function(autoCreate) {
		if (autoCreate) {
			return this.image || (function() {
				this.image = new HRM.External.Image({
					height : this.height - 35,
					width : this.width - 10,
					imageSrc : "src/Resources/images/default.gif"
				});
				return this.image;
			}.createDelegate(this))();
		} else {
			return this.image || {};
		}
	},
	getUploadField : function(autoCreate) {
		if (autoCreate) {
			return this.uploadField || (function() {
				//Ext.ux.Form.FileUploadField是官方的插件,可以再例子里看到它
				this.uploadField = new Ext.ux.form.FileUploadField({
					width : this.width,
					name : "upload",
					buttonText : "选择照片.."
				});
				this.uploadField.on("fileselected", function(obj, value) {
					this.fireEvent("selected");
				}, this);
				return this.uploadField;
			}.createDelegate(this))();
		} else {
			return this.uploadField || {};
		}
	}
});