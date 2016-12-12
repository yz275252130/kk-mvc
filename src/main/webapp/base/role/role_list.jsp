<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="yz" uri="/WEB-INF/tld/yz.tld"%>
<div class="row">
	<div class="col-sm-12">
		<h4 class="pull-left page-title">Welcome !</h4>
		<ol class="breadcrumb pull-right">
			<yz:menu code="${param.code}"></yz:menu>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="btn-toolbar" id="toolbar">
			<div class="btn-group">
				<button type="button" title="添加" 
					class="btn btn-success waves-effect waves-light " data-toggle="modal" data-target="#base_modal" href="${pageContext.request.contextPath}/baseRole/bfAdd">
					<i class="fa fa-plus"></i>
				</button>


			</div>
			<div class="btn-group">
				<button type="button" title="删除" 
					class="btn btn-danger waves-effect waves-light tableAjaxTodo" href="${pageContext.request.contextPath}/baseRole/delete?callbackMethod=refreshTable" >
					<i class="fa fa-trash-o"></i>
				</button>
			</div>
		</div>
	</div>
</div>
<!-- End row -->

<div class="panel panel-border panel-info ">
	<div class="panel-heading">
		<h6 class="panel-title">角色管理</h6>
	</div>
	<div class="panel-body">
		<table data-toggle="table" data-classes=" table table-hover"
			data-pagination="true" data-search="true" data-show-columns="true"
			data-show-refresh="true" data-show-toggle="true" 
			data-toolbar="#toolbar"
			data-url="${pageContext.request.contextPath}/baseRole/listData.json"
			data-side-pagination="server">
			<thead>
				<tr>
					<th  data-checkbox="true" data-align="center">ID</th>
					<th data-field="id"  data-align="center">ID</th>
					<th data-field="roleName" data-align="center">名称</th>
					<th data-field="roleDesc" data-align="center">描述</th>
					<th data-align="center" data-formatter="operateFormatter">操作</th>
				</tr>
			</thead>
		</table>
		<div>
		</div>
	</div>
	<!-- panel body -->
</div>
<!-- panel -->

<script type="text/javascript">

	function operateFormatter(value, row, index) {
		
		var action ='<button class="btn btn-icon btn-warning m-b-5 btn-xs " title="修改" data-toggle="modal" data-target="#base_modal" href="./baseRole/bfEdit?id='+row.id+'"> <i class="md md-border-color"></i></button> <button class="btn btn-icon btn-danger m-b-5 btn-xs" title="删除" onclick="ajaxDel(\'./baseRole/delete?callbackMethod=refreshTable&ids='+row.id+'\')"> <i class="md md-clear"></i></button> <button class="btn btn-icon btn-purple m-b-5 btn-xs" title="权限设置" data-toggle="modal" data-target="#base_modal" href="./baseRole/roleRes?code='+row.code+'" > <i class="fa fa-cog fa-spin "> </i></button>'
		return action;
				
	}
</script>
 