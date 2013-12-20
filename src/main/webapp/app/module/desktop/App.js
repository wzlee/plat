/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Plat.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        'Ext.window.MessageBox',

        'Ext.ux.desktop.ShortcutModel',

        'Plat.module.SystemStatus',
        'Plat.module.VideoWindow',
        'Plat.module.GridWindow',
        'Plat.module.TabWindow',
        'Plat.module.AccordionWindow',
        'Plat.module.Notepad',
        'Plat.module.BogusMenuModule',
        'Plat.module.BogusModule',
        'Plat.module.ServiceManager',

//        'Plat.Blockalanche',
        'Plat.module.Settings'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return [
            new Plat.module.VideoWindow(),
            //new Plat.Blockalanche(),
            new Plat.module.SystemStatus(),
            new Plat.module.GridWindow(),
            new Plat.module.TabWindow(),
            new Plat.module.AccordionWindow(),
            new Plat.module.Notepad(),
            new Plat.module.BogusMenuModule(),
            new Plat.module.BogusModule(),
            new Plat.module.ServiceManager()
        ];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
//            cls: 'ux-desktop-black',
            contextMenuItems: [
                { text: '更改设置', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: [
                    { name: '用户管理', iconCls: 'grid-shortcut', module: 'grid-win' },
                    { name: '服务管理', iconCls: 'grid-shortcut', module: 'service-manager' },
                    { name: '订单管理', iconCls: 'grid-shortcut', module: 'grid-win' },
                    { name: '在线客服', iconCls: 'grid-shortcut', module: 'grid-win' },
                    { name: '网站管理', iconCls: 'grid-shortcut', module: 'grid-win' },
                    { name: 'E点通', iconCls: 'accordion-shortcut', module: 'acc-win' },
                    { name: '记事本', iconCls: 'notepad-shortcut', module: 'notepad' },
                    { name: '系统监控', iconCls: 'cpu-shortcut', module: 'systemstatus'}
                ]
            }),

            wallpaper: 'resources/wallpapers/Blue-Sencha.jpg',
            wallpaperStretch: false
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            title: 'Don Griffin',
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'设置',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'退出',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart: [
                { name: '手风琴窗口', iconCls: 'accordion', module: 'acc-win' },
                { name: '表格窗口', iconCls: 'icon-grid', module: 'grid-win' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('退出', '你确定要退出登陆吗?');
        Ext.Msg.confirm('退出', '您确定要退出吗?');
    },

    onSettings: function () {
        var dlg = new Plat.module.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
