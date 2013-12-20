var cityArr = ["深圳市|0","深圳市外|1"];

// 区县列表
var county = [["罗湖区|1","福田区|2","南山区|3","宝安区|4","龙岗区|5","其它区|6"],[]];

// -----------------------------------------------
// 显示选择后的子项目列表
function showCounty(_city, _child){
	var cityid = Number(_city.selectedIndex)-1;
	cleanCounty(_child);
	addOptions(county[cityid], _child);
}

// 删除指定列表中所有项目
function cleanCounty(_obj){
	if(_obj){
		for(var i=_obj.options.length-1;i>=0;i--){
			if(_obj.options[i].value != "")_obj.remove(i);
		}
	}
}

// 增加所有选项
function addOptions(_arr, _obj){
	if(_arr.length){
		for(var i=0;i<_arr.length;i++){
			addOption(_obj, _arr[i]);
		}
	}
}

// 增加单个选项
function addOption(oListbox, op) 
{
	var oOption = document.createElement("option");
	oOption.appendChild(document.createTextNode(getName(op)));
	
	if (arguments.length == 2)// 参数数量
	{
		oOption.setAttribute("value", getValue(op));
	}
	
	oListbox.appendChild(oOption);
}

// 分析返回选项名称
function getName(op){
	return op.split("|")[0];
}

// 分析返回选项值
function getValue(op){
	return op.split("|")[1];
}
// -----------------------------------------------

// 页面初始化
window.onload = function(){
	var mainCity2 = document.linkinfofrom.areaCity;
	var areaId = document.getElementById("areaId");
	cleanCounty(mainCity2);
	addOptions(cityArr, mainCity2);
	areaId.selectedIndex = 0;
};


