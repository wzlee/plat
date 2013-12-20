var uid = $("#uid").val();   //企业ID
$(function(){
	$('#sclass').combobox({  
        data : eval($('#myServices').html()),
   		valueField:'id',  
        textField:'text',
        editable:false
    });
	loadpush();	  
	$("#search").click(function(){		
		loadpush();
	});
});
function loadpush(){	
	$('#pushlist').datagrid({
    	method:'post',
        url:'bidding/queryBidding',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:20,
		remoteSort:false,
		pageList:[10,20,30,40,50],
        queryParams:{
        	uid:uid,        	
        	name:$("#sname").val(),
        	categoryId:$('#sclass').combobox("getValue")
        },
        columns:[[
        	{field:'bidNo',title:'招标单号',align:'center',width:60},        	
            {field:'category.text',title:'服务类别',align:'center',width:80,formatter:function(value,rowData,rowIndex){            	
            	return '<strong>'+ rowData.category.text +'</strong>';
            }},   
            {field:'name',title:'服务名称',align:'center',width:100}, 
            {field:'Price',title:'招标价格',width:100,align:'center',formatter:function(value,rowData,rowIndex){
            	if(rowData.maxPrice){
            		return rowData.minPrice+'-'+rowData.maxPrice; 
            	}else {
            		return "面议";
            	}     	           	
            }},
            {field:'attachment',title:'附件',align:'center',width:179,sortable:true,formatter : function(value, rowData, rowIndex) {
				if(value){
					var fnames = value.split(',');
					value = [];
					for(var i=0;i<fnames.length;i++){
						var aname = '附件' + (i+1);
						value[i] = '<a target="_blank" href="upload/'+fnames[i]+'">' + aname + '</a>';
					}
					value = value.join(' ');
				}
				return value;
			}},
            {field:'description',title:'描述',align:'center',width:60,sortable:true,formatter : function(value, rowData, rowIndex) {
				if(value){
					var temp = value.replace(/<[^>].*?>/g,'').substring(0,5);  
					value = '<div class="zb-description">'+temp+'<div class="zb-description-all">'+value+'</div></div>';
				}
				return value;
			},styler : function(value,row,index){
				return 'overflow: visible;';
			}},
            {field:'linkMan',title:'联系人',align:'center',width:70,sortable:true},
            {field:'createTime',title:'创建时间',align:'center',width:80,sortable:true,formatter:function(value,rowData,rowIndex){            	
            	return value.substr(0, 10);      	           	
            }},
            {field:'controller',title:'操作',align:'center',width:60,sortable:true,formatter:function(value,rowData,rowIndex){              	            	
            	return "<a href='ucenter/response_bid?op=6&bid="+ rowData.id +"'>处理</a>";
            }}
        ]],
        onLoadSuccess:function(){
        	$('.zb-description').hover(function(){
				$(this).children().show();
			},function(){
				$(this).children().hide();
			});
        }
    });
};