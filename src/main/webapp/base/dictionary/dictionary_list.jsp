<%@ page language="java" pageEncoding="utf-8"%>
<div class="row">
	<div class="col-lg-12">
		<div class="btn-toolbar" id="toolbar">
			<div class="btn-group">
				<button type="button"
					class="btn btn-success waves-effect waves-light " data-toggle="modal" data-target="#base_modal" href="./baseDictionary/bfAdd?code=${code}">
					<i class="fa fa-plus"></i>
				</button>


			</div>
			<div class="btn-group">
				<button type="button"
					class="btn btn-danger waves-effect waves-light tableAjaxTodo" href="./baseDictionary/del?callbackMethod=refreshTable">
					<i class="fa fa-trash-o"></i>
				</button>
			</div>
		</div>
	</div>
</div>
<!-- End row -->

<div class="panel panel-color panel-info ">
	<div class="panel-heading">
		<h6 class="panel-title">数据</h6>
	</div>
	<div class="panel-body">
		<table data-toggle="table" data-classes=" table table-hover"
			data-pagination="true" data-search="true" data-show-columns="true"
			data-show-refresh="true" data-show-toggle="true" 
			data-toolbar="#toolbar"
			data-url="${pageContext.request.contextPath}/baseDictionary/listByPcode.json?pCode=${code}"
			data-side-pagination="server">
			<thead>
				<tr>
					<th  data-checkbox="true" data-align="center">ID</th>
					<th data-field="id"  data-align="center">ID</th>
					<th data-field="name" data-align="center">名称</th>
					<th data-field="code" data-align="center">value</th>
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
		
		var action ='<button class="btn btn-icon btn-warning m-b-5 btn-xs " data-toggle="modal" data-target="#base_modal" href="./baseDictionary/bfEdit?id='+row.id+'"> <i class="md md-border-color"></i></button> <button class="btn btn-icon btn-danger m-b-5 btn-xs" onclick="ajaxDel(\'./baseDictionary/del?callbackMethod=refreshTable&ids='+row.id+'\')"> <i class="md md-clear"></i></button>'
		return action;
				
	}
</script>
 