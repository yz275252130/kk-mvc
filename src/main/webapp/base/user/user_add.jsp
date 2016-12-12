<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseUser/add?callbackMethod=relaodPageData"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">添加用户</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="userAccount" class="control-label">账号 *</label> <input
				type="text" class="form-control" id="userAccount"
				name="userAccount" placeholder="账号" data-bv-notempty="true"
				data-bv-notempty-message="请输入账号"
				data-bv-regexp="true"
                data-bv-regexp-regexp="^\w+$"
                data-bv-regexp-message="用户名只能是字母或数字"
                data-bv-stringlength="true"
                data-bv-stringlength-min="6"
                data-bv-stringlength-max="30"
                data-bv-stringlength-message="用户名必须为6-30位"
				
				>
		</div>
		<div class="form-group">
			<label for="userName" class="control-label">名称 *</label> <input
				type="text" class="form-control" id="userName"
				name="userName" placeholder="名称" data-bv-notempty="true"
				data-bv-notempty-message="请输入名称">
		</div>
		<div class="form-group no-margin">
			<label for="userDesc" class="control-label">描述 </label>
			<textarea class="form-control autogrow" id="userDesc"
				name="userDesc" 
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 104px;"></textarea>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>

