<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<form class="cmxform form-horizontal tasi-form bvform" method="post"
	action="${pageContext.request.contextPath}/baseUser/editUserRole?callbackMethod=refreshTable">
	<input type="hidden" name="userCode" value="${param.userCode }"/>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">角色设置</h4>
	</div>

	<div class="modal-body">
	<c:forEach items="${baseRoleList}" var="baseRole">
			<div class="checkbox checkbox-success">
				<input id="${baseRole.code}" type="checkbox" name="roles" value="${baseRole.code}" ${baseRole.checked} > <label
					for="${baseRole.code}"> ${baseRole.roleName} </label>
			</div>
	</c:forEach>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default waves-effect"
			data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-info waves-effect waves-light">确定</button>
	</div>

</form>

