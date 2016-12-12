<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<link rel="shortcut icon" href="img/favicon_1.ico">

<title>Moltran - Responsive Admin Dashboard Template</title>
<!-- Base Css Files -->
<link href="css/bootstrap.min.css" rel="stylesheet" />

<!-- Font Icons -->
<link href="assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/ionicon/css/ionicons.min.css" rel="stylesheet" />
<link href="css/material-design-iconic-font.min.css" rel="stylesheet">

<!-- animate css -->
<link href="css/animate.css" rel="stylesheet" />

<!-- Waves-effect -->
<link href="css/waves-effect.css" rel="stylesheet">
<!-- 信息提示插件 -->
<link href="assets/notifications/notification.css" rel="stylesheet" type="text/css" />

<!-- Bootstrap table-->
<link rel="stylesheet" href="css/bootstrap-table.css">

<!-- X-editable css -->
<link type="text/css" href="assets/bootstrap3-editable/bootstrap-editable.css" rel="stylesheet">

<!-- bootstrap-treeview -->
<link href="css/bootstrap-treeview.min.css" rel="stylesheet" />
<!-- Custom Files -->
<link href="css/helper.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="js/modernizr.min.js"></script>

</head>



<body class="fixed-left">
	<!-- modal -->
	<div id="base_modal" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true"
		style="display: none;">

		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /.modal -->
	<!-- Begin page -->
	<div id="wrapper">

		<!-- Top Bar Start -->
		<div class="topbar">
			<!-- LOGO -->
			<div class="topbar-left">
				<div class="text-center">
					<a href="javascript:void(0);" class="logo"><i
						class="md md-terrain"></i> <span>跨客汇运营后台 </span></a>
				</div>
			</div>
			<!-- Button mobile view to collapse sidebar menu -->
			<div class="navbar navbar-default" role="navigation">
				<div class="container">
					<div class="">
				    <div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
					</div>
					
						<ul class="navbar-collapse collapse nav navbar-nav navbar-left">
						<c:forEach items="${menuList}" var="resource">
							<sec:authorize url="${resource.resourceUrl}">
								<li><a
									href="${pageContext.request.contextPath}${resource.resourceUrl}${resource.code}"
									data-target="divload" data-div="sidebar-menu" class="smoothScroll"><span style="font-size: 15px;font-weight: bold;">${resource.resourceName}
									</span></a></li>
							</sec:authorize>
						</c:forEach>
						</ul>
						<ul class="nav navbar-nav navbar-right pull-right">
							<li class="dropdown"><a href=""
								class="dropdown-toggle profile" data-toggle="dropdown"
								aria-expanded="true">欢迎： ${sessionScope.USER.userName} </a>
										<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/baseUser/bfChangePassword" data-toggle="modal"
						data-target="#base_modal" ><i class="md md-lock"></i>修改密码</a></li>
								<li><a
									href="${pageContext.request.contextPath}/j_spring_security_logout"><i
										class="md md-settings-power"></i>退出</a></li>
							</ul></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<!-- Top Bar End -->


		<!-- ========== Left Sidebar Start ========== -->

		<div class="left side-menu">
			<div class="sidebar-inner slimscrollleft">
			
				<!--- Divider -->
				<div id="sidebar-menu">
		
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- Left Sidebar End -->



		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container" id="baseContainer">

					<!-- Page-Title -->
					<div class="row">
					</div>

					<!-- Pls Remove -->
					<div style="height: 600px;"></div>


				</div>
				<!-- container -->

			</div>
			<!-- content -->

			<footer class="footer text-center"> Copyright © 1998 - 2016
				Tencent. All Rights Reserved </footer>

		</div>
		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->
	</div>
	<!-- END wrapper -->

	<script>
		var resizefunc = [];
	</script>

	<!-- jQuery  -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/waves.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="assets/jquery-detectmobile/detect.js"></script>
	<script src="assets/fastclick/fastclick.js"></script>
	<script src="assets/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script src="assets/jquery-blockui/jquery.blockUI.js"></script>
	<script src="js/bootstrapValidator.min.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script src="js/bootstrap-table-zh-CN.js"></script>
	<script src="js/bootstrap-paginator.js"></script>
	<!-- CUSTOM JS -->
	<script src="js/jquery.app.js"></script>
	<!--信息提示插件  -->
	<script src="assets/notifications/notify.min.js"></script>
	<script src="assets/notifications/notify-metro.js"></script>
	<script src="assets/notifications/notifications.js"></script>
		<!-- XEditable Plugin -->
	<script type="text/javascript" src="assets/bootstrap3-editable/moment.min.js"></script>
	<script type="text/javascript" src="assets/bootstrap3-editable/bootstrap-editable.js"></script>
	<script type="text/javascript" src="assets/bootstrap3-editable/demo-xeditable.js"></script>
	<!--bootstrap-treeview  -->
	<script src="js/bootstrap-treeview.js"></script>
	<!-- base-ui -->
	<script src="js/base-ui.js"></script>
	<script src="js/ajax.util.js"></script>
</body>
</html>