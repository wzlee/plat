Ext.define('Ext.ux.ComboTree', {
	extend : "Ext.form.field.Picker",
	alias : 'widget.combotree',
	requires : ['Ext.form.*', 'Ext.tree.Panel'],
	rootText : this.rootText || 'root',
	rootId : this.rootId || '0',
	rootVisible : this.rootVisible || false,
	nodeParam : this.nodeParam || 'id',
	treeUrl : this.treeUrl, // 这里是点击树前加号须返回的JSON格式节点数据
	multiSelect : this.multiSelect || false,
	fieldName : 'category',
	useId : this.useId || true, // 是否使用id域，如果不选择，则值直接使用Picker的value值
	selectedIds : '',
	values : '',
	texts : '',
	leafOnly : this.leafOnly || true, // 限定是否只能选择叶子节点
	hiddenField : null,
	resetValues : function() {
		if (this.hiddenField)
			this.hiddenField.setValue('');
		this.values = '';
		this.texts = '';
		this.setValue('');
	},
	initComponent : function() {
		var self = this;
		if (self.useId)
			self.hiddenField = Ext.create('Ext.form.field.Hidden', {
						id : self.fieldName + "Id",
						name : self.fieldName + "Id"
					}); // 由于Picker本身的value对应的是显示的文本，所以这里我们新建了一个隐藏域，用来保存诸如id等值，而不是直接的文本值，在表单提交的时候，就可以直接使用这个值了
		Ext.apply(self, {
					pickerAlign : "tl",
					name : self.fieldName
				});
		self.callParent();
		if (self.useId) {
			this.on('afterrender', function() {
						self.ownerCt.add(self.hiddenField);
					}, this);
		}
	},
	createPicker : function() {
		var self = this;
		var aurl = self.treeUrl;
		if (self.multiSelect) { // 这里增加了多选的功能，在后台返回的JSON数据中，应加上checked:true之类的属性
			if (aurl.indexOf("?") == -1)
				aurl += "?multiSelect=true&selectedIds=" + self.selectedIds;
			else
				aurl += "&multiSelect=true&selectedIds=" + self.selectedIds;
		}
		if (self.leafOnly) {
			if (aurl.indexOf("?") == -1)
				aurl += "?leafOnly=true";
			else
				aurl += "&leafOnly=true";
		}

		var store = Ext.create('Ext.data.TreeStore', {
					proxy : {
						type : 'ajax',
						url : aurl
					},
					nodeParam : self.nodeParam,
					root : {
						expanded : true,
						text : self.rootText,
						id : self.rootId,
						leaf : false
					},
					autoLoad : true
				});
		self.picker = new Ext.tree.Panel({
					height : 300,
					autoScroll : true,
					floating : true,
					focusOnToFront : false,
					shadow : true,
					ownerCt : this.ownerCt,
					useArrows : true,
					store : store,
					rootVisible : self.rootVisible
				});
		self.picker.on({
			beforehide : function(p) {
				self.setValue(self.texts);
				if (self.useId)
					self.hiddenField.setValue(self.values);
			},
			itemclick : function(view, record) {
				if (self.leafOnly) {
					if (record.get('leaf')) // 这里限定只有叶子节点可以选择
						setToValues(record);
					else {
						record.expand();
					}
				} else {
					setToValues(record);
				}
				function setToValues(record) {
					if (!self.multiSelect) {
						self.values = record.get('id');
						self.texts = record.get('text');
						self.collapse();
					} else {
						var records = self.picker.getView().getChecked(), ids = [], names = [];
						Ext.Array.each(records, function(rec) {
									ids.push(rec.get('id'));
									names.push(rec.get('text'));
								});
						self.values = ids.join(',');
						self.texts = names.join(',');
					}
					if (self.useId) {
						self.hiddenField.setValue(self.values);
					}
					self.setValue(self.texts);
				}
			}
		});
		return self.picker;
	},
	alignPicker : function() {
		var me = this, picker, isAbove, aboveSfx = '-above';
		if (this.isExpanded) {
			picker = me.getPicker();
			if (me.matchFieldWidth) {
				picker.setWidth(me.bodyEl.getWidth());
			}
			if (picker.isFloating()) {
				picker.alignTo(me.inputEl, "", me.pickerOffset);
				isAbove = picker.el.getY() < me.inputEl.getY();
				me.bodyEl[isAbove ? 'addCls' : 'removeCls'](me.openCls
						+ aboveSfx);
				picker.el[isAbove ? 'addCls' : 'removeCls'](picker.baseCls
						+ aboveSfx);
			}
		}
	}
});
