<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="yz" uri="/WEB-INF/tld/yz.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
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
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="col-md-8">
					<div class="btn-toolbar">
						<div class="btn-group">
							<button type="button" title="添加"
								class="btn btn-success waves-effect waves-light "
								data-toggle="modal" data-target="#base_modal"
								href="${pageContext.request.contextPath}/baseUser/bfAdd">
								<i class="fa fa-plus"></i>
							</button>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<form class="cmxform form-horizontal " method="post"
						onsubmit="return searchClick(this);">
						<input type="hidden" name="pageSize" value="${page.pageSize}">
						<div class="input-group">
							<input type="text" id="searchKeyword" name="keyword"
								value="${page.keyword}" class="form-control " placeholder="搜索">
							<span class="input-group-btn">
								<button type="submit"
									class="btn waves-effect waves-light btn-primary">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</div>

<div id="pageData">
	<div class="row">
		<c:forEach var="baseUser" items="${page.list }">
			<div class="col-sm-6 col-lg-4">
				<div class="panel">
					<div class="panel-body">
						<div class="media-main">
							<a class="pull-left" href="javascript:void(0);"> <img
								class="thumb-lg img-circle"
							<c:if test="${empty baseUser.userPhoto}">
								src="${pageContext.request.contextPath}/img/user/default.png"
							</c:if>
							<c:if test="${not empty baseUser.userPhoto}">
								src="${url}${baseUser.userPhoto}"
							</c:if>	
								alt="">
							</a>
							<div class="pull-right btn-group-sm">
								<a  data-toggle="modal" data-target="#base_modal" href="./baseUser/bfEdit?id=${baseUser.id}"
									class="btn btn-warning waves-effect waves-light " title="修改"
									> <i class="md md-border-color"></i>
								</a> <a href="./baseUser/delete?callbackMethod=relaodPageData&ids=${baseUser.id}" title="删除"
									class="btn btn-danger waves-effect waves-light  ajaxDel"
									> <i class="fa fa-close"></i>
								</a>
							</div>
							<div class="info ">
							<h4>${baseUser.userName }</h4>
							<p class="text-muted">${baseUser.userAccount }</p>
							<a href="#" data-mode="popup" data-source="./baseUser/getRoles.json" data-showbuttons="true"  data-url="./baseUser/editUserRole" class="editable" data-type="checklist" data-pk="${baseUser.code }" data-value="${baseUser.roleCodes }" data-title="用户角色"></a>
							<a href="#" data-mode="popup" data-source="./baseDictionary/listData.json?pCode=user_status" data-showbuttons="false"  data-url="./baseUser/editStatus" class="editable pull-right" data-type="select" data-pk="${baseUser.code }" data-value="${baseUser.status }" data-title="用户状态"></a>
								
							</div>
						</div>
						<div class="clearfix"></div>
						<hr />
						<ul class="social-links list-inline">
						</ul>
					</div>
					<!-- panel-body -->
				</div>
				<!-- panel -->
			</div>
			<!-- end col -->
		</c:forEach>

	</div>
	<!-- End row -->

	<div class="row">
		<div class="col-md-12 ">
			<div id="bspagination" class="pull-right" pageNo="${page.pageNo}"
				pageSize="${page.pageSize}" total="${page.total}"
				url="./baseUser/list?code=${param.code }" relaodDiv="pageData">
				<ul class="bspagination"></ul>
			</div>
		</div>
	</div>
</div>

<script>

</script>