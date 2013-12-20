/**
 * @author <a href="mailto:andy_ghg@163.com">葛昊</a></br>
 * @version 1.0
 * @description 图片组件
 * @class HRM.External.Image
 * @extends Ext.BoxComponent
 */
Ext.define("MVC.view.util.ExternalImage",{
	extend:'Ext.Component',
	alias:'wedget.externalimage',
	imageSrc : "",
	initComponent : function() {
		this.on("render",function(){
			this.setImage(this.imageSrc);
		},this);
		this.callParent(arguments);
	},
	/**
	 * 获取XTemplate对象
	 * @return {@link Ext.XTemplate}
	 */
	getXTemplate : function() {
		return this.xtpl || (function() {
			this.xtpl = new Ext.XTemplate("<div><img id='{id}' src='{imgSrc}' height='{height}' width='{width}' border='{border}' /></div>");
			return this.xtpl;
		}.createDelegate(this))();
	},
	/**
	 * 获取图片属性对象
	 * @return {Object}
	 */
	getImage : function() {
		return this.imageData || (function() {
			this.imageData = {
				id : this.getId()+"_img",
				imgSrc : "",
				height : this.height,
				width : this.width,
				border : 0
			}
			return this.imageData;
		}.createDelegate(this))();
	},
	/**
	 * 设置图片路径
	 * @param {String} src 图片路径
	 */
	setImage : function(src) {
		this.getImage().imgSrc = src;
//		console.log(this.getImage());
		this.getXTemplate().overwrite(this.getEl(),this.getImage());
	}
});