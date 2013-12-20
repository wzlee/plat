Ext.define('Ext.ux.themeChange', {
    extend: 'Ext.form.field.ComboBox',   
   	xtype: 'themeChange',    //如果需要用{xtype:'themeChange'}定义该项
   	
    id: 'themeChange',
    name: 'themeChange',
    width: 150,
    labelWidth: 60,
    labelSeparator: '：', //分隔符  
    fieldLabel: '风格',
    editable: false,
    triggerAction: 'all', //单击触发按钮显示全部数据  

    displayField: 'theme',
    valueField: 'css',
    queryMode: 'local', //本地模式  

    initComponent: function () {
 	/*定义扩展控件可能用到的变量或样式如ITEMS等 */
        var themes = [
        				['默认', 'ext-all.css'],
                      	['黑色', 'ext-all-access.css'],
                      	['巧克力', 'xtheme-chocolate.css'],
                      	['深灰色', 'xtheme-darkgray.css'],
                      	['粉色', 'xtheme-pink.css'],
                      	['浅灰色', 'xtheme-gray.css'],
                      	['墨绿', 'xtheme-green.css'],
                      	['靛青色', 'xtheme-indigo.css'],
                      	['深夜', 'xtheme-midnight.css'],
                      	['椒盐色', 'xtheme-peppermint.css'],
                      	['紫色', 'xtheme-purple.css'],
                      	['银白色', 'xtheme-silverCherry.css'],
                      	['暗蓝', 'xtheme-slate.css'],
                      	['Vista', 'xtheme-slickness.css']
                  	];
                  	
        Ext.regModel('Theme', {
            fields: ['theme', 'css']
        });

        var themeStore = Ext.create('Ext.data.Store', {
            model: 'Theme',
            data: themes
        });
      
        this.value = getCookie('cssSheet');
        Ext.util.CSS.swapStyleSheet('theme', '../Content/scripts/extjs/resources/css/' + getCookie('cssSheet'));

        Ext.applyIf(this, {
            store: themeStore,
            listeners: {

               //定义控件事件

                  collapse: function () {
                    SetCookie('cssSheet', this.value);
                    Ext.util.CSS.swapStyleSheet('theme', '../Content/scripts/extjs/resources/css/' + this.value);
                    contentIframe.window.themeChange(this.value);
                    scope: this
                }
            }
        });

        this.callParent(arguments);
    } //可能还有些控件自定义的函数或绑定函数
});