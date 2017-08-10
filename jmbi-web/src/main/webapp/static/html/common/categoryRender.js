function renderCategory(categoryTree, parent) {
	var categories = categoryTree.categories;
	if(!categories) {
		return;
	}
	if(categories.length != 3) {
		return;
	}
	var category1 = parent.find("[name=category1]"),
		category2 = parent.find("[name=category2]"),
		category3 = parent.find("[name=category3]");
	category1.empty();
	category2.empty();
	category3.empty();
	category1.select2({
		data: getSelectData(categories[0]),
		placeholder:'请选择',
		width:"80px"
	});
	category2.select2({
		data: getSelectData(categories[1]),
		placeholder:'请选择',
		width:"80px"
	});
	category3.select2({
		data: getSelectData(categories[2]),
		placeholder:'请选择',
		width:"80px"
	});
	selectCategory(categories[0], category1);
	selectCategory(categories[1], category2);
	selectCategory(categories[2], category3);
	category1.on('change', function() {
		var _this =$(this),
		id = _this.val();
		$.ajax({
            cache: true,
            type: "GET",
            url:"base/categroy",
            data:{"parentId" : id}, 
            dataType:"json",
            error: function(request) {
            	alert("超时或系统异常");
            },
            success: function(res) {
            	console.log(res);
            	if(res && res.statusCode == 200){
            		var categories = res.data;
            		if(categories.length > 0) {
            			var data = getSelectData(categories);
            			category2.empty();
            			category2.select2({
            				data:data
            			});
            		} else {
            			category2.empty();
            		}
            		category3.empty();
        		}else{
        			alert(res.message);
        		}
            }
        });
	});
	
	category2.on('change', function() {
		var _this =$(this),
		id = _this.val();
		$.ajax({
            cache: true,
            type: "GET",
            url:"base/categroy",
            data:{"parentId" : id}, 
            dataType:"json",
            error: function(request) {
            	alert("超时或系统异常");
            },
            success: function(res) {
            	console.log(res);
            	if(res && res.statusCode == 200){
            		var categories = res.data;
            		if(categories.length > 0) {
            			var data = getSelectData(categories);
            			category3.select2({
            				data:data
            			});
            		} else {
            			category3.empty();
            		}
        		} else {
        			alert(res.message);
        		}
            }
        });
	});
}

function selectCategory(categories, proShape) {
	for(var i = 0; i < categories.length; i++) {
		if(categories[i].checked) {
			proShape.val(categories[i].id).trigger("change");
		}
	}
}

function getSelectData(categories) {
	var data = [];
	for(var j = 0; j < categories.length; j++) {
		data.push({id:categories[j].id, text:categories[j].cateName});
	}
	return data;
}

