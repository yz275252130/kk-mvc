<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseUser/changePassword"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	>
	<input type="hidden" name="userAccount" value="${sessionScope.USER.userAccount}">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">修改密码</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="password" class="control-label">旧密码 *</label> <input
				type="text" class="form-control" id="password"
				name="password" placeholder="旧密码" data-bv-notempty="true"
				data-bv-notempty-message="旧密码"
				data-bv-regexp="true"
                data-bv-regexp-regexp="^\w+$"
                data-bv-regexp-message="密码只能是字母或数字"
                data-bv-stringlength="true"
                data-bv-stringlength-min="6"
                data-bv-stringlength-max="30"
                data-bv-stringlength-message="用户名必须为6-30位"
				>
		</div>
		<div class="form-group">
			<label for="newPassword" class="control-label">新密码 *</label> <input
				type="text" class="form-control" id="newPassword"
				name="newPassword" placeholder="新密码" data-bv-notempty="true"
				data-bv-notempty-message="新密码"
				data-bv-regexp="true"
                data-bv-regexp-regexp="^\w+$"
                data-bv-regexp-message="密码只能是字母或数字"
                data-bv-stringlength="true"
                data-bv-stringlength-min="6"
                data-bv-stringlength-max="30"
                data-bv-stringlength-message="用户名必须为6-30位"
                
                data-bv-identical="true"
                data-bv-identical-field="confirmPassword"
                data-bv-identical-message="两次输入的新密码不一致"
				>
		</div>
		<div class="form-group">
			<label for="confirmPassword" class="control-label">新密码 *</label> <input
				type="text" class="form-control" id="confirmPassword"
				name="confirmPassword" placeholder="再次输入新密码" data-bv-notempty="true"
				data-bv-notempty-message="再次输入新密码"
				data-bv-regexp="true"
                data-bv-regexp-regexp="^\w+$"
                data-bv-regexp-message="密码只能是字母或数字"
                data-bv-stringlength="true"
                data-bv-stringlength-min="6"
                data-bv-stringlength-max="30"
                data-bv-stringlength-message="用户名必须为6-30位"
                
                data-bv-identical="true"
                data-bv-identical-field="newPassword"
                data-bv-identical-message="两次输入的新密码不一致"
				>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>
</form>

