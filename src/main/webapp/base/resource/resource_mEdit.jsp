<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseResource/edit?callbackMethod=getTree" onsubmit="validateCallback(this, modalAjaxDone)"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	>
	<input type="hidden" name="id" value="${baseResource.id }" /> 
	<input type="hidden" name="version" value="${baseResource.version }" />
	<input type="hidden" name="resourceType" value="${baseResource.resourceType }" /> 
	<input type="hidden" name="code" value="${baseResource.code }" />
	<input type="hidden" name="parentResCode" value="${baseResource.parentResCode}" />
	<input type="hidden" name="status" value="${baseResource.status }" /> 
	<input type="hidden" name="createdBy" value="${baseResource.createdBy }" /> 
	<input type="hidden" name="createdDate" value="${baseResource.createdDate }" /> 
	<input type="hidden" name="lastUpdatedBy" value="${baseResource.lastUpdatedBy }" /> 
	<input type="hidden" name="lastUpdatedDate" value="${baseResource.lastUpdatedDate }" /> 
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">修改菜单</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="resourceName" class="control-label">名称 *</label> <input
				type="text" class="form-control" id="resourceName"
				name="resourceName" placeholder="名称" data-bv-notempty="true"
				data-bv-notempty-message="请输入用户名" value="${baseResource.resourceName }">
		</div>
		<div class="form-group">
			<label for="orderd" class="control-label">排序 *</label> <input
				type="number" class="form-control" id="orderd" name="orderd"
				placeholder="排序" data-bv-integer-message="排序必须为数字"
				data-bv-notempty="true" data-bv-notempty-message="请输入排序" value="${baseResource.orderd }">
		</div>
		<div class="form-group">
			<label for="resourceUrl" class="control-label">URL </label> <input
				type="text" class="form-control" id="resourceUrl" name="resourceUrl"
				placeholder="URL" value="${baseResource.resourceUrl }">
		</div>
		<div class="form-group">
			<label for="icon" class="control-label">图标</label> <input type="text"
				class="form-control" id="icon" placeholder="图标" name="icon" value="${baseResource.icon }">
		</div>
		<div class="form-group no-margin">
			<label for="resourceDesc" class="control-label">描述 </label>
			<textarea class="form-control autogrow" id="resourceDesc"
				name="resourceDesc" placeholder="Write something about yourself"
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 104px;">${baseResource.resourceDesc }</textarea>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>