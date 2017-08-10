$(function(){
 
	
    $("form").validate({
        onfocusout: false,
        onclick: false,
        onkeyup: false,
        rules: {
        	content: {
                minlength: 2,
                maxlength: 10000
            },
            name: {
                minlength: 2,
                maxlength: 20
            },
            account: {
                minlength: 2,
                maxlength: 20
            },
            department: {
                minlength: 2,
                maxlength: 30
            },
            phone: {
                minlength: 2,
                maxlength: 50
            },
            remark: {
                minlength: 2,
                maxlength: 30
            },
            mail: {
                email: true
            },
            totalStaff: {
                number: true
            },
            website: {
                url: true
            }
        },
        messages: {
        	content: {
                minlength: "内容至少2个字符,最多10000个字符！",
                maxlength: "内容至少2个字符,最多10000个字符！"
            },
            name: {
                minlength: "标题至少2个字符,最多20个字符！",
                maxlength: "标题至少2个字符,最多20个字符！"
            },
            account: {
            	 minlength: "标题至少2个字符,最多20个字符！",
                 maxlength: "标题至少2个字符,最多20个字符！"
            },
            department: {
            	 minlength: "标题至少2个字符,最多30个字符！",
                 maxlength: "标题至少2个字符,最多30个字符！"
            },
            phone: {
            	 minlength: "标题至少2个字符,最多50个字符！",
                 maxlength: "标题至少2个字符,最多50个字符！"
            },
            remark: {
            	 minlength: "标题至少2个字符,最多30个字符！",
                 maxlength: "标题至少2个字符,最多30个字符！"
            },
            mail: {
                email: "请输入正确的邮箱！"
            },
            totalStaff: {
                number: "请输入正确的公司人数！"
            },
            website: {
                url: "请输入正确的企业网站地址！形如：'http://xxx.xxx'"
            }
        },
        showErrors: function(errorMap,errorList){           
            if(errorList.length){
                layer.alert(errorList[0].message,function(index){
                    layer.close(index);
                    $(errorList[0].element).focus();
                });
            }
        }
    });	
	$(".img-list").on('click','.img-box i',function(){
		var iIndex = $(this).parents('.img-box').index();
		$(this).parents('.img-box').remove();
		$(".urlInput input").eq(iIndex).remove();
	});
	$("#J_cancel1").on('click',function(){
		var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
		parent.layer.close(index);
	});
 
    // 保存
	$("#J_confirm1").on('click', function() { 
	    var str = $("input[name='title']").val();
	 
	    if(!$("form").valid()) return false;
	    var indexLoading=layer.load(0,{shade: 0.2});
	    $.ajax({
	        cache: true,
	        type: "POST",
	        url: $('form').attr("action"),
	        data: $('form').serialize(),// 你的formid
	        dataType: "json",
	        error: function (request) {
	            setErrorAlert("超时或系统异常");
	        },
	        success: function (data) {
	            parent.window.refresh_table();
	            if (data && data.statusCode == 200) {
	                layer.msg(data.message, {
	                  time: 2000 // 2秒关闭（如果不配置，默认是3秒）
	                }, function(){
	                    parent.layer.closeAll();
	                }); 
	            } else {
	            	 layer.close(indexLoading);
	                if (data.message) {
	                    layer.alert(data.message);
	                } else {
	                    layer.alert("操作失败");
	                }
	            }
	        }
	    });

	});
})
 
 