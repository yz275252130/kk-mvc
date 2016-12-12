<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseDictionary/edit?callbackMethod=refreshTable"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	>
	<input type="hidden" name="id" value="${baseDictionary.id }" /> 
	<input type="hidden" name="version" value="${baseDictionary.version }" />
	<input type="hidden" name="parentCode" value="${baseDictionary.parentCode}" />
	<input type="hidden" name="status" value="${baseDictionary.status }" /> 
	<input type="hidden" name="createdBy" value="${baseDictionary.createdBy }" /> 
	<input type="hidden" name="createdDate" value="${baseDictionary.createdDate }" /> 
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">修改数据</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="name" class="control-label">名称 *</label> <input
				type="text" class="form-control" id="name"
				name="name" placeholder="名称" data-bv-notempty="true" value="${baseDictionary.name }"
				data-bv-notempty-message="请输字典户名">
		</div>
		<div class="form-group">
			<label for="code" class="control-label">code </label> <input
				type="text" class="form-control" id="code" name="code" data-bv-notempty="true" value="${baseDictionary.code }"
				data-bv-notempty-message="请输code"
				placeholder="code">
		</div>
		<div class="form-group no-margin">
			<label for="description" class="control-label">描述 </label>
			<textarea class="form-control autogrow" id="description"
				name="description"
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 104px;">${baseDictionary.description }</textarea>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>