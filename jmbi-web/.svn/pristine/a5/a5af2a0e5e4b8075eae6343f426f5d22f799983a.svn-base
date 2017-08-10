// 地区选择修改，需要联动下级地区下拉，三级地区下拉
function changeArea(areaCode, lineType, nextArea, thirdArea) {
	if(!areaCode || areaCode == "") {
		resetArea(nextArea);
		resetArea(thirdArea);
		return;
	}
	lineType = lineType || "231"; // 默认公路
	$.ajax({
        cache: true,
        type: "get",
        url:"../base/area",
        data:{"parentCode" : areaCode, "lineType" : lineType},
        dataType:"json",
        error: function(request) {
        	layer.alert("超时或系统异常");
        },
        success: function(res) {
        	if(res && res.statusCode == 200){
        		var areaList = res.data.results;
        		resetArea(nextArea, areaList);
        		resetArea(thirdArea);
    		}else{
    			layer.alert(res.message);
    		}
        }
    });
}

function changeLineType(lineType, areas) {
	$.ajax({
        cache: true,
        type: "get",
        url:"../base/area",
        data:{"parentCode" : null, "lineType" : lineType},
        dataType:"json",
        error: function(request) {
        	layer.alert("超时或系统异常");
        },
        success: function(res) {
        	if(res && res.statusCode == 200){
        		var areaList = res.data.results;
        		for(var i = 0; i < areas.length; i++) {
        			resetArea(areas[i], areaList);
        			areas[i].trigger("change");
        		}
    		}else{
    			layer.alert(res.message);
    		}
        }
    });
}

function resetArea(area, areaList) {
	if(!area) {
		return;
	}
	area.empty();
	area.append('<option value="">请选择</option>');
	if(areaList) {
		for(var i = 0; i < areaList.length; i++) {
			area.append('<option value="' + areaList[i].id + '">' + areaList[i].text + '</option>');
		}
	}
	area.change();
}
