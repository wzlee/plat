var uid = $("#uid").val();   //企业ID
var bName = "";
var bCategoryId = "";
var begintime = "";
var endtime = "";
var bStatus = "";
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
	$('#responselist').datagrid({
    	method:'post',
        url:'bidding/queryResponse',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:20,
		remoteSort:false,
		pageList:[10,20,30,40,50],
        queryParams:{        	
        	uid:$("#uid").val(),
        	bStatus:$("#bStatus").combobox("getValue"),
        	bName:$("#bName").val(),
        	bCategoryId:$('#sclass').combobox("getValue"),
        	begintime:$("#begintime").datebox('getValue'),
        	endtime:$("#endtime").datebox('getValue')
        },
        columns:[[
        	{field:'bidNo',title:'招标单号',align:'center',width:60,formatter:function(value,rowData,rowIndex){            	
            	return "<a target='_blank' href='ucenter/response_detail?op=6&rid="+rowData.id+"'>"+ rowData.biddingService.bidNo +"</a>";
            }},        	
            {field:'category.text',title:'服务类别',align:'center',width:80,formatter:function(value,rowData,rowIndex){            	
            	return rowData.biddingService.category.text;
            }},   
            {field:'biddingService.name',title:'服务名称',align:'center',width:100,formatter:function(value,rowData,rowIndex){ 
            	return rowData.biddingService.name;
            }}, 
            {field:'biddingService.price',title:'招标价格',width:100,align:'center',formatter:function(value,rowData,rowIndex){            	
            	if(rowData.biddingService.minPrice){
            		return rowData.biddingService.minPrice+'-'+rowData.biddingService.maxPrice; 
            	}else {
            		return "面议"; 
            	}            	           	
            }},
            {field:'biddingService.attachment',title:'附件',align:'center',width:179,sortable:true,formatter : function(value, rowData, rowIndex) {
            	value = rowData.biddingService.attachment;
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
            {field:'biddingService.description',title:'描述',align:'center',width:50,sortable:true,formatter : function(value, rowData, rowIndex) {
            	value = rowData.biddingService.description;
				if(value){
					var temp = value.replace(/<[^>].*?>/g,'').substring(0,4);  
					value = '<div class="zb-description">'+temp+'<div class="zb-description-all">'+value+'</div></div>';
				}
				return value;
			},styler : function(value,row,index){
				return 'overflow: visible;';
			}},
            {field:'biddingService.linkMan',title:'联系人',align:'center',width:70,sortable:true,formatter:function(value,rowData,rowIndex){              	            	
            	return rowData.biddingService.linkMan;
            }},
            {field:'status',title:'状态',align:'center',width:70,sortable:true,formatter:function(value,rowData,rowIndex){
            	bstatus = rowData.biddingService.status;
            	//招标状态 4、招标中 6、交易进行中 7、等待买家关闭 8、等待卖家关闭 9、申诉处理中 10、交易结束 11、招标取消
            	//应标状态5、应标中  6、应标失败  7、交易进行中            	
            	switch(value){   
            		case 5 :if(bstatus==11){value = "<span>招标取消</span>";}else {value="<span>应标中</span>";};
            			break;
            		case 6 :value = "<span>应标失败</span>";
            			break;
            		case 7 :if(bstatus==10){value="<span>交易结束</span>";}else if(bstatus==11){value="<span>招标取消</span>";}else {value="<span>交易进行中</span>";};
            			break;
            	}            	
            	return value;
            }},
            {field:'responseTime',title:'应标时间',align:'center',width:80,sortable:true,formatter:function(value,rowData,rowIndex){            	
            	return value.substr(0, 10);      	           	
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