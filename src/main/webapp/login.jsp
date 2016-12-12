<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<!-- Base Css Files -->
		<link href="css/bootstrap.min.css" rel="stylesheet" />

		<!-- Font Icons -->
		<link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<link href="assets/ionicon/css/ionicons.min.css" rel="stylesheet" />
		<link href="css/material-design-iconic-font.min.css" rel="stylesheet">

		<!-- animate css -->
		<link href="css/animate.css" rel="stylesheet" />

		<!-- Waves-effect -->
		<link href="css/waves-effect.css" rel="stylesheet">

		<!-- Custom Files -->
		<link href="css/helper.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />

		<!-- bootstrapValidator Files -->
		<link href="css/bootstrapValidator.min.css" rel="stylesheet" type="text/css" />
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
		<script src="js/modernizr.min.js"></script>
		<style>
			.loginfooter {
				position: absolute;
				width: 100%;
				bottom: 0px;
				padding: 20px 10px;
				border-top: 0px solid rgba(0, 0, 0, 0.05);
			}
		</style>
	</head>

	<body>
		<img src="img/login_bg1.jpg" width="100%" height="100%" style="z-index:-100;position:absolute;left:0;top:0;">
		<div class="wrapper-page">
			<div class="panel panel-color panel-primary panel-pages">
				<div class="panel-heading bg-img">
					<div class="bg-overlay"></div>
					<h3 class="text-center m-t-10 text-white"> <strong>用户登陆</strong> </h3>
				</div>

				<div class="panel-body">
				<p class="text-danger text-center ${param.error==0?"":'hide'}">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message }</p>
				<c:if test="${param.error==2}">
				<p class="text-danger text-center">登陆超时</p>
				</c:if>
				<c:if test="${param.error==3}">
				<p class="text-danger text-center">该用户已经在其他位置登陆！</p>
				</c:if>
					<form class="form-horizontal m-t-20" id="loginForm" action="${pageContext.request.contextPath}/j_spring_security_check" method="post" 
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
					data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
					data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

						<div class="form-group ">
							<div class="col-xs-12 ">
							<div class="input-group">
								<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
								<input class="form-control input-lg " name="j_username" type="text" placeholder="用户名" data-bv-notempty="true" data-bv-notempty-message="请输入用户名">
							</div>
							</div>
						</div>

						<div class="form-group">
						
						
							<div class="col-xs-12 ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
									<input class="form-control input-lg" type="password" placeholder="密码" name="j_password" data-bv-notempty="true"
										data-bv-notempty-message="请输入密码">
								</div>
						
							</div>
						</div>

						<div class="form-group" style="display: none;">
							<div class="col-xs-12">
								<div class="checkbox checkbox-primary">
									<input id="checkbox-signup" type="checkbox">
									<label for="checkbox-signup">
                                   	 记住账号
                                </label>
								</div>

							</div>
						</div>

						<div class="form-group text-center m-t-40">
							<div class="col-xs-12">
								<button class="btn btn-primary btn-lg w-lg waves-effect waves-light" type="submit">登陆</button>
							</div>
						</div>

						<div class="form-group m-t-30">
							<div class="col-sm-7">
								<a href="recoverpw.html"><i class="fa fa-lock m-r-5"></i> 忘记密码?</a>
							</div>
							<div class="col-sm-5 text-right">
								<a href="register.html">注册新用户</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="loginfooter text-center">Copyright © 1998 - 2016 Tencent. All Rights Reserved</div>
	</body>
	<script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrapValidator.min.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('#loginForm').bootstrapValidator({
		        submitHandler: function(validator, form, submitButton) {					
					this.defaultSubmit();
		        }
			});
		}) 
	</script>
</html>