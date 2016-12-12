/**
 * 
 */

$(document)
		.ready(
				function() {
					$.ajaxSetup({
						cache : false
					// 关闭AJAX相应的缓存
					});
					// 处理session 超时
					$.ajaxSetup({
								contentType : "application/x-www-form-urlencoded;charset=utf-8",
								complete : function(XMLHttpRequest, textStatus) {
									var sessionstatus = XMLHttpRequest
											.getResponseHeader("sessionstatus");
									if (sessionstatus == "timeout") {
										// 如果超时就处理 ，指定要跳转的页面
										var targetUrl = XMLHttpRequest
												.getResponseHeader("targetUrl");
										window.location.href = targetUrl;
									}

									if (XMLHttpRequest.status == 999) {
										$("#base_modal").modal('hide');
										$.Notification.autoHideNotify('error',
												'top center', '消息提示',
												'没有访问权限，请联系管理员！');
										return;
									}
								}
							});
					// navTab 方式打开页面
					$("body").delegate(
							"*[data-target='navTab']",
							"click",
							function() {
								var url = $(this).attr("href")
										|| $(this).data("url");
								$.ajaxSetup({
									cache : false
								// close AJAX cache
								});
								$("#navTab").load(url, initHtml);
								return false;
							});
					
					// navTab 方式打开页面
					$("body").delegate(
							"*[data-target='divload']",
							"click",
							function() {
								var url = $(this).attr("href")
										|| $(this).data("url");
								var div = $(this).data("div");
								$.ajaxSetup({
									cache : false
								// close AJAX cache
								});
								
								$("#"+div).load(url, initHtml);
								
								//只加载菜单特殊处理
								if(div=="sidebar-menu"){
									$("#baseContainer").html("");
								}
								
								return false;
							});
					
					// 防止modal缓存

					$("#base_modal").on("loaded.bs.modal", function() {

						initHtml();
						// 重置校验
						resetValidator();
					});

					$("#base_modal").on("hidden.bs.modal", function() {
						// 清除modal缓存
						$(this).removeData("bs.modal");
						resetValidator();
					});
					// modal 打开完后激活 校验
					$('#base_modal').on('shown.bs.modal', function() {
						initValidator();
					});

				});

function initHtml() {
	// 绑定 ajaxToDo 事件
	$(".ajaxToDo").unbind().bind(
			"click",
			function() {
				var url = $(this).attr("href");
				$.Notification.confirm('success', 'top center', '操作提示！',
						ajaxToDo, url);
			});

	// datatable
	$('[data-toggle="table"]').bootstrapTable();
	
	//初始化 editable
	$(".editable").editable({
		error : ajaxDone,
		success : ajaxDone,
	});
	
	//初始化分页组件
	init_pagination();
	// 绑定 tableAjaxTodo 事件
	$(".tableAjaxTodo").unbind().bind(
			"click",
			function() {
				var url = $(this).attr("href");
				$.Notification.confirm('success', 'top center', '操作提示！',
						tableAjaxTodo, url);
			});
	
	$(".ajaxDel").unbind().bind(
			"click",
			function(event) {
				event.preventDefault();
				var url = $(this).attr("href");
				ajaxDel(url);
			});
	
}

function initValidator() {
	// modal form 校验 及提交
	$('.bvform').bootstrapValidator({
		excluded : ':disabled, :hidden, :not(:visible)',
		submitHandler : function(validator, form, submitButton) {
			$.ajax({
				type : form.method || 'POST',
				url : form.attr("action"),
				data : form.serializeArray(),
				dataType : "json",
				cache : false,
				success : modalAjaxDone,
				error : ajaxError
			});
			this.disableSubmitButtons(true);
		}
	});
	// 带上传文件的form 校验
	$('.iframeform').bootstrapValidator();
}

function resetValidator() {
	if ($('.bvform').data('bootstrapValidator')) {
		$('.bvform').data('bootstrapValidator').resetForm();
	}
	if ($('.iframeform').data('bootstrapValidator')) {
		$('.iframeform').data('bootstrapValidator').resetForm();
	}
}

function init_pagination () {
	var element = $('.bspagination');
	var div_element = element.parent();
	var pageSize = div_element.attr("pageSize");
	var total = div_element.attr("total");
	var totalPages = Math.ceil(total/pageSize)
	var url = div_element.attr("url");
	var keyword =$("#searchKeyword").val();
	var relaodDiv = div_element.attr("relaodDiv");
	var numberOfPages = totalPages>10?10:totalPages;
	var options = {
		bootstrapMajorVersion: 3,
		currentPage: div_element.attr("pageNo"),
		numberOfPages: numberOfPages,
		totalPages: totalPages,
		itemTexts: function(type, page, current) {
			switch(type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "尾页";
				case "page":
					return page;
			}
		},
		useBootstrapTooltip:true,
		pageUrl: function(type, page, current) {
			return "javascript:void(0);";
		},
		tooltipTitles: function(type, page, current) {
			return "第"+page+"页";
		},
		onPageClicked:function(event, originalEvent,type,page){
			
			var data = '{"pageSize":"'+pageSize+'","pageNo":"'+page+'","keyword":"'+keyword+'"}';
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
		}
	}

	element.bootstrapPaginator(options);
}
