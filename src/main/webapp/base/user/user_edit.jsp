<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseUser/edit?callbackMethod=relaodPageData"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	>
	<input type="hidden" name="id" value="${baseUser.id }" /> 
	<input type="hidden" name="version" value="${baseUser.version }" />
	<input type="hidden" name="code" value="${baseUser.code }" />
	<input type="hidden" name="status" value="${baseUser.status }" /> 
	<input type="hidden" name="userPassword" value="${baseUser.userPassword }" /> 
	<input type="hidden" name="loginFailurecount" value="${baseUser.loginFailurecount }" /> 
	<input type="hidden" name="userLastIp" value="${baseUser.userLastIp }" /> 
	<input type="hidden" name="userLoginIp" value="${baseUser.userLoginIp }" /> 
	<input type="hidden" name="userLoginTime" value="${baseUser.userLoginTime }" /> 
	<input type="hidden" name="userLastTime" value="${baseUser.userLastTime }" /> 
	<input type="hidden" name="type" value="${baseUser.type }" /> 
	<input type="hidden" name="createdBy" value="${baseUser.createdBy }" /> 
	<input type="hidden" name="createdDate" value="${baseUser.createdDate }" /> 
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">修改用户</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="userAccount" class="control-label">账号 *</label> <input
				type="text" class="form-control" id="roleName"
				name="userAccount" placeholder="账号" data-bv-notempty="true"
				data-bv-notempty-message="请输入账号" value="${baseUser.userAccount }">
		</div>
		<div class="form-group">
			<label for="userName" class="control-label">名称 *</label> <input
				type="text" class="form-control" id="userName"
				name="userName" placeholder="名称" data-bv-notempty="true" value="${baseUser.userName }"
				data-bv-notempty-message="请输入名称">
		</div>
		<div class="form-group no-margin">
			<label for="userDesc" class="control-label">描述 </label>
			<textarea class="form-control autogrow" id="userDesc"
				name="userDesc" 
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 104px;">${baseUser.userDesc }</textarea>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>
</form>