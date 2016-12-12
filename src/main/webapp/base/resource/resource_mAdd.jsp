<%@ page language="java" pageEncoding="utf-8"%>

<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="./baseResource/add?callbackMethod=getTree"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	
	>
	<input type="hidden" name="parentResCode" value="${param.code }" /> 
	<input type="hidden" name="resourceType" value="1" />
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">添加菜单</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="resourceName" class="control-label">名称 *</label> <input
				type="text" class="form-control" id="resourceName"
				name="resourceName" placeholder="名称" data-bv-notempty="true"
				data-bv-notempty-message="请输入用户名">
		</div>
		<div class="form-group">
			<label for="orderd" class="control-label">排序 *</label> <input
				type="number" class="form-control" id="orderd" name="orderd"
				placeholder="排序" data-bv-integer-message="排序必须为数字"
				data-bv-notempty="true" data-bv-notempty-message="请输入排序">
		</div>
		<div class="form-group">
			<label for="resourceUrl" class="control-label">URL </label> <input
				type="text" class="form-control" id="resourceUrl" name="resourceUrl"
				placeholder="URL">
		</div>
		<div class="form-group">
			<label for="icon" class="control-label">图标</label> <input type="text"
				class="form-control" id="icon" placeholder="图标" name="icon">
		</div>
		<div class="form-group no-margin">
			<label for="resourceDesc" class="control-label">描述 </label>
			<textarea class="form-control autogrow" id="resourceDesc"
				name="resourceDesc" placeholder="Write something about yourself"
				style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 104px;"></textarea>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>

