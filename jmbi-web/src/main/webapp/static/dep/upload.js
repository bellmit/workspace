/**
 * 初始化uploadify上传
 * @param imgId 上传成功后 显示上传图片的 img标签的ID
 * @param filePathId 上传成功后 文件存储路径
 * @param webFilePath 文件服务主域名
 * @param fileTypeExts 文件类型 可选 ("file","image","doc") 多个可使用 "," 分隔
 */
function fileUpload(param) {
	var timestamp = (new Date()).valueOf();
	var token = "";
	var language = "cn";
	var domain = "";
	var uploadId,imgId,filePathId,webFilePath,fileTypeExts,callback,fileQueue="fileQueue";
	if(param){
		uploadId = param.uploadId;
		if(param.imgId){imgId = param.imgId}
		if(param.filePathId){filePathId = param.filePathId}
		if(param.webFilePath){webFilePath = param.webFilePath}
		if(param.fileType){fileTypeExts =param.fileType}
		if(param.fileQueue){fileQueue =param.fileQueue}
		if(param.callback){callback = param.callback}
	}
	var uploadBtn = $("#"+uploadId);
	var contextPath = param.contextPath;
	$.getJSON(contextPath+'/UploadApiServlet?t='+timestamp,
		 function(data){
			if (data.uploadToken) {
				token = data.uploadToken;
				language = data.language;
				domain = data.domain;
				 uploadBtn.uploadify({
				        'swf': param.swf,
				        'uploader': webFilePath+'/upload.do?action=upload',//后台处理的请求
				        'formData':{moduleFlag:"report","token":token,"domain":domain,"language":language,"fileType":fileTypeExts},//后台参数 json格式
				        'queueID': 'fileQueue',//与下面的id对应
				        'buttonText' : '',
				        'queueSizeLimit': 6,
				        'fileTypeExts': fileTypeExts, //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
				   		'fileTypeDesc': 'Any old file you want...',
				        'auto': true,
				        'multi': true,
				        'width': param.width,
				        'height': param.height,
				        'uploadLimit': param.uploadLimit,
				        'fileSizeLimit': '5MB',//文件上传大小限制
//				        'buttonImg': 'erp/js/uploadify/images/choose.png',
				        'onUploadError': function (event, queueID, fileObj, errorObj) {
				            alert(errorObj.type + "Error:" + errorObj.info);
				        },
				        'onSelect': function(fileObj){
						    if (fileObj.size > 5*1024*1024){ 
						    	 layer.alert("上传文件不允许大于5MB", {icon: 2});
							     $("#uploadify").uploadifyCancel(queueID);
							     return false;
						    }
						    /*if(fileObj.type=='.jpg'||fileObj.type=='.bmp'||fileObj.type=='.gig'||fileObj.type=='.png'||fileObj.type=='.tif'||fileObj.type=='.rgb'||fileObj.type=='.dib'||fileObj.type=='.eps'||fileObj.type=='.jpe'||fileObj.type=='.pcx'||fileObj.type=='.bmp'||fileObj.type=='.gif'||fileObj.type=='.pdf'){
				        	
						    }else{
				        		alert('文件类型不对');
				        		return false;
				        	}*/
						}, 
						'onSelectError' : function(file) {
				            alert('The file ' + file.name + ' returned an error and was not added to the queue.');
				        },
				        'onUploadComplete': function (fileObj, _path, data) {
				        	$.cookie('JSESSIONID',token);//跨域传输之后必须设置cookie 否则会丢失此次的session 
				        	if(param.replace){
				        		$(".uploadifyp").hide();
				        	}
				        },
				        'onUploadSuccess':function(fileObj, _path, data){
				        	var obj=eval('(' + _path + ')');
				        	//执行回调函数 判断是否有回调函数,如果没有就默认执行
				    		if(callback && callback != undefined && typeof callback === 'string'){
				    			callback = window[callback];
				    			if(typeof callback === 'function'){
				    				callback.apply([],[obj]);
				    			}
				    		}else{
				        		if(obj && obj.resultCode == 4){
				        			debugger;
				        			var filePaths = obj.filePaths;//文件查看路径
				        			var originalFileNames = obj.originalFileNames;//原文件名
									var fileType=fileObj.type.toLowerCase();
				        			for(var i=0;i<filePaths.length;i++){
				        				if(fileType=='.jpg'||fileType=='.bmp'||fileType=='.gig'||fileType=='.png'||fileType=='.tif'||fileType=='.rgb'||fileType=='.dib'||fileType=='.eps'||fileType=='.jpe'||fileType=='.pcx'||fileType=='.bmp'||fileType=='.gif'||fileType=='.pdf'){
				        					if(param.replace){
				        						var str = '<div class="img-box"><img name="'+originalFileNames+'" src="' + webFilePath + '/' + filePaths[i] + '"/></div>';
				        					}else{
				        						var str = '<div class="img-box"><img name="'+originalFileNames+'" src="' + webFilePath + '/' + filePaths[i] + '"/><i>X</i></div>';
				        					}										
											//name=imageUrl禁止改动，意向单上传合同使用到，请联系罗超
											var pathSrc ='<input type="hidden"  name="imageUrl" value="' + filePaths[i] +'">';
											$(".uploadifyp").before(str)
											$("#"+filePathId).append(pathSrc);
										}
				        			}
				        			
				        		}else{
				            		alert(obj.resultMsg);
				        		}
					        		
					        	

				    		}
				        }
				    });
			}
	 	 }
	 );
}