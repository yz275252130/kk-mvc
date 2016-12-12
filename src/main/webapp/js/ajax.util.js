
//带校验的form表单 提交
function validateCallback(form, callback) {

    var $form = $(form);

    var data = $(form).data('bootstrapValidator').validate();
    if (!data.isValid()) {
        return false;
    }

    $.ajax({
        type : form.method || 'POST',
        url : $form.attr("action"),
        data : $form.serializeArray(),
        dataType : "json",
        cache : false,
        success : callback || ajaxDone,
        error : ajaxError
    });

    return false;
}

//关闭modal窗口 刷新父页面，或指定div 或根据回调函数刷新
function modalAjaxDone(json){
	if(json.statusCode==200){
		//关闭mode 
		$("#base_modal").modal('hide');
//		$('#base_modal').removeData("bs.modal");
		//提示成功消息
		$.Notification.autoHideNotify('success', 'top center', '消息提示！',json.message);
		//刷新页面
		var callbackMethod = json.callbackMethod;
		//如果存在 回调函数 则 调用回调函数进行刷新
		if(callbackMethod!=null &&callbackMethod!=""){
			eval(callbackMethod+"()");
		}
	}else{
		if(typeof json.statusCode === 'string'){
			//提示失败消息
			$.Notification.autoHideNotify('error', 'top center', '消息提示！',json.message);
		}
		
	}
}

function ajaxError(json){
	if(json.status==200){
		if(json.message){
			$.Notification.autoHideNotify('error', 'top center', '消息提示！',json.message);
		}
	}
	 
}

function ajaxToDo(url){
    $.ajax({
        type : 'POST',
        url : url,
        dataType : "json",
        cache : false,
        success : ajaxDone,
        error : ajaxError
    });
}

// 刷新父页面，或指定div 或根据回调函数刷新
function ajaxDone(json){
	if(json&&json.statusCode==200){
		//提示成功消息
		$.Notification.autoHideNotify('success', 'top center', '消息提示！',json.message);
		//刷新页面
		var callbackMethod = json.callbackMethod;
		//如果存在 回调函数 则 调用回调函数进行刷新
		if(callbackMethod!=null &&callbackMethod!=""){
			eval(callbackMethod+"()");
		}
	}else{
		if(json && typeof json.statusCode === 'string'){
			//提示失败消息
			$.Notification.autoHideNotify('error', 'top center', '消息提示！',json.message);
		}
		
	}
}

function refreshTable(){
	$('[data-toggle="table"]').bootstrapTable('refresh');
}

function tableAjaxTodo(url){
	 var ids = $.map($('[data-toggle="table"]').bootstrapTable('getSelections'), function (row) {
        return row.id;
    });
	 
	 if(ids==''){
		 $.Notification.autoHideNotify('error','top center', '操作提示', '请选择一条记录!');
	 }else{ 
		  url=url+"&ids="+ids;
			$.ajax({
				type:'POST',
				url:url,
				dataType:"json",
				cache: false,
				success:ajaxDone,
				error: ajaxError
			});
	 }
}

function ajaxDel(url){
	
	$.Notification.confirm('success','top center', '操作提示！', ajaxToDo, url);
}
//搜索
function searchClick(form){
	var $form = $(form);
	var bspaginationElement =$("#bspagination");
	var relaodDiv = bspaginationElement.attr("relaodDiv");
	var url =  bspaginationElement.attr("url");
	$.ajax({
		  url:url,
		  type:"POST",
		  data:$form.serializeArray(),
		  cache : false,
		  success: function(data){
			  var pageData = $(data).filter("#pageData").html()||$(data).find("#pageData").html();
			  $("#"+relaodDiv).html(pageData);
			  initHtml();
		  }
		});
	return false;
}

function relaodPageData(){
	var bspaginationElement =$("#bspagination");
	var relaodDiv = bspaginationElement.attr("relaodDiv");
	var url =  bspaginationElement.attr("url");
	var pageSize =  bspaginationElement.attr("pageSize");
	var pageNo =  bspaginationElement.attr("pageNo");
	var keyword =$("#searchKeyword").val();
	var data = '{"pageSize":"'+pageSize+'","pageNo":"'+pageNo+'","keyword":"'+keyword+'"}';
	var jsonData = $.parseJSON(data);
	$.ajax({
		  url:url,
		  type:"POST",
		  data:jsonData,
		  cache : false,
		  success: function(data){
				 var pageData = $(data).filter("#pageData").html();
				  $("#"+relaodDiv).html(pageData);
				  initHtml();
		  }
		});
	return false;
}


/**
 * 带文件上传的ajax表单提交
 * @param {Object} form
 * @param {Object} callback
 */
function iframeCallback(form, callback){
    var $form = $(form), $iframe = $("#callbackframe");
    $("#fileImage",$form).remove();
    if ($iframe.size() == 0) {
        $iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
    }
    if(!form.ajax) {
        $form.append('<input type="hidden" name="ajax" value="1" />');
    }
    form.target = "callbackframe";
    
    _iframeResponse($iframe[0], callback || ajaxDone);
}
function _iframeResponse(iframe, callback){
    var $iframe = $(iframe), $document = $(document);
    $document.trigger("ajaxStart");

    $iframe.bind("load", function(event){

        $iframe.unbind("load");
        $document.trigger("ajaxStop");

        if (iframe.src == "javascript:'%3Chtml%3E%3C/html%3E';" || // For Safari
            iframe.src == "javascript:'<html></html>';") { // For FF, IE
            return;
        }

        var doc = iframe.contentDocument || iframe.document;
        if(doc){
        	// fixing Opera 9.26,10.00
        	if (doc.readyState && doc.readyState != 'complete') return;
        	// fixing Opera 9.64
        	if (doc.body && doc.body.innerHTML == "false") return;
        }


        var response;

        if (doc&&doc.XMLDocument) {
            // response is a xml document Internet Explorer property
            response = doc.XMLDocument;
        } else if (doc&&doc.body){
            try{
                response = $iframe.contents().find("body").text();
                response = jQuery.parseJSON(response);
            } catch (e){ // response is html document or plain text
                response = doc.body.innerHTML;
            }
        } else {
            // response is a xml document
            response = doc;
        }

        callback(response);
        $(iframe).remove();
    });
}