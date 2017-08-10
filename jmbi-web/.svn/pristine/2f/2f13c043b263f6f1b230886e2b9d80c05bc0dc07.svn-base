var common = {};
// 将data的信息逐个放入到详情信息中
common.renderDetail = function(detailDom, data) {
	// 基本信息
	$(detailDom).find("input[type=hidden]").each(function() {
		var _this = $(this);
		_this.val(data[_this.attr('name')]);
	});
	$(detailDom).find("input[type=text]").each(function() {
		var _this = $(this);
		_this.val(data[_this.attr('name')]);
	});
	$(detailDom).find("input[type=radio]").each(function() {
		var _this = $(this);
		var value = data[_this.attr('name')];
		if(value == _this.val()) {
			_this.prop('checked', true);
		} else {
			_this.prop('checked', false);
		}
	});
	$(detailDom).find("select").each(function() {
		var _this = $(this);
		_this.val(data[_this.attr('name')]);
	});
	$(detailDom).find("textarea").each(function() {
		var _this = $(this);
		_this.val(data[_this.attr('name')]);
	});
	$(detailDom).find("label").each(function() {
		var _this = $(this);
		var name = _this.attr('name');
		if(name) {
			_this.html(data[name]);
		}
	});
};

// 从detailDom中获取数据，name为key，val为value
common.getDetail = function(detailDom, data) {
	data = data ? data : {};
	$(detailDom).find("input[type=hidden]").each(function() {
		var _this = $(this);
		data[_this.attr('name')] = _this.val();
	});
	$(detailDom).find("input[type=text]").each(function() {
		var _this = $(this);
		data[_this.attr('name')] = _this.val();
	});
	$(detailDom).find("input[type=radio]:checked").each(function() {
		var _this = $(this);
		data[_this.attr('name')] = _this.val();
	});
	$(detailDom).find("select").each(function() {
		var _this = $(this);
		data[_this.attr('name')] = _this.val();
	});
	$(detailDom).find("textarea").each(function() {
		var _this = $(this);
		data[_this.attr('name')] = _this.val();
	});
	$(detailDom).find("input[type=checkbox]:checked").each(function() {
		var _this = $(this),
			name = _this.attr('name');
		if(data[name]) {
			debugger
			data[name] = data[name] + "," + _this.text();
		} else {
			data[name] = _this.text();
		}
	});
	return data;
};


common.contains = function(array, arg) {
	if(!array || array.length == 0) {
		return false;
	}
	for(var i = 0; i < array.length; i++) {
		if(array[i] == arg) {
			return true;
		}
	}
	return false;
};


/**
 * 字符串空处理。如果为空或者undefined就返回""
 * @param str
 */
function nullProcess(str){
	if (str == "" || str == undefined || str == 'null') {
		return "";
	}
	return str;
}

//是否正整数
function isInteger(number){
return number > 0 && String(number).split('.')[1] == undefined
}
 
//是否是数字
function isNumber(number){
return typeof number === 'number'
}

common.ajaxFileUpload = function(param) {
	var secureuri = param.secureuri || false,
		dataType = param.dataType || 'json',
		fileElementId = param.fileElementId,
		success = param.success,
		error = param.error,
		includes = param.includes;
	var file = $("#" + fileElementId).val();
	if(file == "") {
		alert("请选择导入文件");
		return;
	}
	if(includes && includes.length > 0) {
		var suffix = file.substring(file.lastIndexOf(".") + 1);
		if(!common.contains(includes, suffix)) {
			alert("请上传[" + includes.join(",") + "]格式的文件");
			return;
		}
	}
    return $.ajaxFileUpload({
        url: param.url, 
        secureuri: false, 
        fileElementId: param.fileElementId, 
        dataType: dataType, 
        success: function (data, status) {
            if(success) {
            	success(data, status);
            } else if(data.message){
            	alert(data.message);
            } else {
            	alert("上传成功");
            }
        },
        error: function (data, status, e){
            if(error) {
            	error(data, status, e);
            } else if(data.message){
            	alert(data.message);
            } else {
            	alert("上传失败");
            }
        }
    });
};
/**
 * added by francklin
 * @created: 2016.08.29
 */
var jk={
	//by francklin 20160826
	maxDate: function(){
		var arr=Array.prototype.slice.call(arguments,0);
		var arr1=[],arr2=[],date,value,index=-1;
		for(var i=0,j=0;i<arr.length;i++){
			date=null;value="";
			if(arr[i] instanceof Date){
				date=arr1[j]=arr2[j]=arr[i];
			} else if(typeof(arr[i])!="string"){
				continue;
			} else if($.trim(arr[i])==""){
				continue;
			} else if(arr[i].slice(0,1)!="#"){
				value=arr[i];
			} else if(arr[i].slice(-1)=="#"){
				var id=arr[i].slice(1,-1);
				if(!id) continue;
				value=$.trim($('input[name="'+id+'"]').val());
			} else {
				var id=arr[i].slice(1);
				if(!id) continue;
				value=$.trim($(arr[i]).val());
			}
			if(!date){
				if(isNaN(date=new Date(value.replace(/[.-]/g,"/")))) continue;
				arr1[j]=value;arr2[j]=date;
			}
			if(index<0) index=j;
			else if(date>arr2[index]) index=j;
			j++;
		}
		return index>=0&&arr1[index]||'';
	},
	//by francklin 20160826
	minDate: function(){
		var arr=Array.prototype.slice.call(arguments,0);
		var arr1=[],arr2=[],date,value,index=-1;
		for(var i=0,j=0;i<arr.length;i++){
			date=null;value="";
			if(arr[i] instanceof Date){
				date=arr1[j]=arr2[j]=arr[i];
			} else if(typeof(arr[i])!="string"){
				continue;
			} else if($.trim(arr[i])==""){
				continue;
			} else if(arr[i].slice(0,1)!="#"){
				value=arr[i];
			} else if(arr[i].slice(-1)=="#"){
				var id=arr[i].slice(1,-1);
				if(!id) continue;
				value=$.trim($('input[name="'+id+'"]').val());
			} else {
				var id=arr[i].slice(1);
				if(!id) continue;
				value=$.trim($(arr[i]).val());
			}
			if(!date){
				if(isNaN(date=new Date(value.replace(/[.-]/g,"/")))) continue;
				arr1[j]=value;arr2[j]=date;
			}
			if(index<0) index=j;
			else if(date<arr2[index]) index=j;
			j++;
		}
		return index>=0&&arr1[index]||'';
	}
}


$(function() {
	//for jquery.validate by francklin 2016.8.17
	var _required = $.validator.methods.required;
    $.validator.methods.required = function(value) {
        var arr=Array.prototype.slice.call(arguments,1);
        return _required.apply(this,[$.trim(value)].concat(arr));
    }
	$.validator.addMethod("mobile", function(value, element) { 
		var length = value.length; 
		var mobile = /^1[3|4|5|7|8]\d{9}$/
		return this.optional(element) || (length == 11 && mobile.test(value)); 
	});
	$.validator.addMethod("decimal", function(value, element) { 
		var reg = /^[0-9]{1,8}(\.[0-9]{1,2})?$/;
		return this.optional(element) || reg.test(value); 
	});
	$.validator.addMethod("money", function(value, element) { 
		var reg = /^[0-9]{1,12}(\.[0-9]{1,2})?$/;
		return this.optional(element) || reg.test(value); 
	});
});


/**
 * 判断是否为正整数
 */
function isPositiveNum(s){
    var re = /^[0-9]*[1-9][0-9]*$/;  
    return re.test(s);
}

