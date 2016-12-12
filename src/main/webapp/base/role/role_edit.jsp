<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseRole/edit?callbackMethod=refreshTable"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	>
	<input type="hidden" name="id" value="${baseRole.id }" /> 
	<input type="hidden" name="version" value="${baseRole.version }" />
	<input type="hidden" name="code" value="${baseRole.code }" />
	<input type="hidden" name="status" value="${baseRole.status }" /> 
	<input type="hidden" name="createdBy" value="${baseRole.createdBy }" /> 
	<input type="hidden" name="createdDate" value="${baseRole.createdDate }" /> 
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">修改角色</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="roleName" class="control-label">名称 *</label> <input
				type="text" class="form-control" id="roleName"
				name="roleName" placeholder="角色名称" data-bv-notempty="true"
				data-bv-notempty-message="请输入角色名称" value="${baseRole.roleName }">
		</div>
		<div class="form-group no-margin">
			<label for="roleDesc" class="control-label">描述 </label>
			<textarea class="form-control autogrow" id="roleDesc"name="roleDesc" 
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 104px;">${baseRole.roleDesc }</textarea>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>