<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form" method="post"
	action="${pageContext.request.contextPath}/baseRole/editRoelRes"
	onsubmit="return submitRoleRes(this);"
	>
	<input type="hidden" id="roleCode" name="roleCode" value="${param.code}"/>
	<input type="hidden" id="res" name="res" value=""/>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">权限设置</h4>
	</div>
	<div class="modal-body">
		<a href="#" class="btn btn-danger waves-effect waves-light btn-block ">资源</a>
		<div id="roletree"></div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button  class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>

<!-- End row -->
<script type="text/javascript">
var roleRes = null;
	function getTree() {
		// Some logic to retrieve, or generate tree structure
		var url = "${pageContext.request.contextPath}/baseRole/getRoleRes.json?code=${param.code}";
		$.ajax({
			type : 'POST',
			url : url,
			dataType : "html",
			cache : false,
			success : function(data) {
				var dataObj=eval("("+data+")");
				roleRes = dataObj.roleRes;
				$("#res").val(roleRes);
				$('#roletree').treeview({
					data : dataObj.treeData, // data is not optional
					levels : 4,
					showCheckbox : true,
					showAction : false,
					selectedBackColor: '#FFFFFF',
					onhoverColor: '#FFFFFF',
					clickHandler: false,
					onNodeChecked: onNodeChecked,
					onNodeUnchecked:onNodeUnchecked,
				//borderColor: '#00BFFF',
				});
				initHtml();
			},
			error : function() {
				$.Notification.autoHideNotify('error', 'top center', '消息提示！',
						"操作失败！");
			}
		});
	}

	$(document).ready(function() {
		getTree();
	});
	

	function onNodeChecked(event, node) {
		if($.inArray(node.code,roleRes)==-1){
			roleRes.push(node.code);
		}
		
		$("#res").val(roleRes);
	}
	
	function onNodeUnchecked(event, node){
		roleRes.splice($.inArray(node.code,roleRes),1);
		$("#res").val(roleRes);
	}
	
	function submitRoleRes(form){
		var $form = $(form);
		var _submitFn = function(){
			$.ajax({
				type: 'POST',
				url:$form.attr("action"),
		        data : $form.serializeArray(),
		        dataType : "json",
				cache: false,
				success:modalAjaxDone,
				error: ajaxError
			});
		}
		_submitFn();
		return false;
	}

</script>
