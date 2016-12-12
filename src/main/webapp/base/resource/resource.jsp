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

	<!-- Left sidebar -->
	<div class="col-lg-3 col-md-4">
		<a href="#" class="btn btn-danger waves-effect waves-light btn-block ">菜单</a>
		<div class="panel panel-default p-0  m-t-20">
			<div class="panel-body p-0">
				<div id="tree"></div>

			</div>
		</div>
	</div>
	<!-- End Left sidebar -->

	<!-- Right Sidebar -->
	<div class="col-lg-9 col-md-8" id="right_panel">
		
	</div>


	<!-- end Col-9 -->

</div>

<!-- End row -->
<script type="text/javascript">
	var tree = []
	function getTree() {
		// Some logic to retrieve, or generate tree structure
		var url = "${pageContext.request.contextPath}/baseResource/resource_menu.json";
		$.ajax({
			type : 'POST',
			url : url,
			dataType : "html",
			cache : false,
			success : function(data) {
				$('#tree').treeview({
					data : data, // data is not optional
					levels : 3,
					showAction:true,
					actionParam:'node.code',
				//borderColor: '#00BFFF',
				});
				initHtml();
			},
			error : function() {
				$.Notification.autoHideNotify('error', 'top center', '消息提示！',
						"操作失败！");
			}
		});
	}

	$(document).ready(function() {
		getTree();
	});
</script>
