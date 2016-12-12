<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<style>
.kv-avatar .file-preview-frame,.kv-avatar:hover {
    margin: 0;
    padding: 0;
    border: none;
    box-shadow: none;
    text-align: center;
}
.kv-avatar .file-input {
    display: table-cell;
    max-width: 220px;
}
</style>
<div class="row">

	<div class="panel panel-border panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">用户详情</h3>
		</div>
		<div class="panel-body">
			<form action="#" class="form-horizontal">
				<div class="col-lg-2" >
					<div class="kv-avatar">
						<input type="hidden"  id="photoImg"
						<c:if test="${empty baseUser.userPhoto}">
								value="${pageContext.request.contextPath}/fream/images/user/22.jpg"
							</c:if>
							<c:if test="${not empty baseUser.userPhoto}">
								value="${url}${baseUser.userPhoto}"
							</c:if>	
						>
						<input id="userPhoto" name="photoFile" data-pk="${baseUser.id }" type="file" class="file-loading">
					</div>
					<div class="kv-avatar">
						<input type="hidden" id="showingImg"
						<c:if test="${empty baseUser.userShowing}">
								value="${pageContext.request.contextPath}/fream/images/user/11.jpg"
							</c:if>
							<c:if test="${not empty baseUser.userShowing}">
								value="${url}${baseUser.userShowing}"
							</c:if>	
						>
						<input id="userShowing" name="showingFile" data-pk="${baseUser.id }" type="file" class="file-loading">
					</div>
				</div>
				<div class="col-lg-10">
					<div class="form-group">
						<label class="col-sm-2 control-label">昵称：</label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-showbuttons="true"
								data-url="../baseUser/userEdit" class="editable"
								data-type="text" data-name="userName" data-pk="${baseUser.id }"
								data-value="${baseUser.userName }" data-title="昵称"></a>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"> 手机： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-showbuttons="true"
								data-url="../baseUser/userEdit" class="editable"
								data-type="text" data-name="userPhone" data-pk="${baseUser.id }"
								data-value="${baseUser.userPhone }" data-title="手机"></a>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"> 标题： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userTitle"
								data-pk="${baseUser.id }" data-value="${baseUser.userTitle }"
								data-title="标题"></a>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"> 擅长方向： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userSpec"
								data-pk="${baseUser.id }" data-value="${baseUser.userSpec }"
								data-title="擅长方向"></a>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"> 专业资质： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userQual"
								data-pk="${baseUser.id }" data-value="${baseUser.userQual }"
								data-title="专业资质"></a>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"> 擅长咨询领域与方法： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userTech"
								data-pk="${baseUser.id }" data-value="${baseUser.userTech }"
								data-title="擅长咨询领域与方法"></a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"> 教育背景与咨询背景 ： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userBack"
								data-pk="${baseUser.id }" data-value="${baseUser.userBack }"
								data-title="教育背景与咨询背景"></a>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"> 其他 ： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userOther"
								data-pk="${baseUser.id }" data-value="${baseUser.userOther }"
								data-title="其他"></a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"> 简介 ： </label>
						<div class="col-sm-10">
							<a href="#" data-mode="inline" data-rows="5" data-cols="100"
								data-showbuttons="true" data-url="../baseUser/userEdit"
								class="editable" data-type="textarea" data-name="userNote"
								data-pk="${baseUser.id }" data-value="${baseUser.userNote }"
								data-title="简介"></a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- end row -->
<script>
$("#userPhoto").fileinput({
    overwriteInitial: true,
    maxFileSize: 1500,
    showClose: false,
    showCaption: false,
    showBrowse: false,
    browseOnZoneClick: true,
    previewClass:'img-circle',
    showRemove: false,
    elErrorContainer: '#kv-avatar-errors-2',
    msgErrorClass: 'alert alert-block alert-danger',
    defaultPreviewContent: '<a href="#"><img id="defultImg" src="'+$("#photoImg").val()+'" alt="图像" style="width:128px;height:128px " class="img-circle"></a>',
    layoutTemplates: {main2: '{preview}{remove}{browse}'},
    allowedFileExtensions: ["jpg", "png", "gif"],
    fileActionSettings:{showZoom: false, showDrag: false},
    previewSettings:{image: {width: "128px", height: "128px"}},
    imageClass:'img-circle',
    uploadUrl: "../baseUser/editPhoto", // server upload action
    uploadAsync: true,
    uploadExtraData: function() {
        return {"id": $("#userPhoto").attr("data-pk")};
    }
});

$('#userPhoto').on('fileuploaded', function(event, data, previewId, index) {
	if('200'==data.response.statusCode){
		$('#userPhoto').fileinput('reset').trigger('custom-event');
		$("#defultImg").attr("src",data.response.jsonData);
	}else{
		
	}
});


$("#userShowing").fileinput({
    overwriteInitial: true,
    maxFileSize: 1500,
    showClose: false,
    showCaption: false,
    showBrowse: false,
    browseOnZoneClick: true,
    previewClass:'img-circle',
    showRemove: false,
    elErrorContainer: '#kv-avatar-errors-2',
    msgErrorClass: 'alert alert-block alert-danger',
    defaultPreviewContent: '<a href="#"><img id="userShowingImg" src="'+$("#showingImg").val()+'" alt="靓照" style="width:170px;height:170px " ></a>',
    layoutTemplates: {main2: '{preview}{remove}{browse}'},
    allowedFileExtensions: ["jpg", "png", "gif"],
    fileActionSettings:{showZoom: false, showDrag: false},
    previewSettings:{image: {width: "128px", height: "128px"}},
    uploadUrl: "../baseUser/editShowing", // server upload action
    uploadAsync: true,
    uploadExtraData: function() {
        return {"id": $("#userShowing").attr("data-pk")};
    }
});

$('#userShowing').on('fileuploaded', function(event, data, previewId, index) {
	if('200'==data.response.statusCode){
		$('#userShowing').fileinput('reset').trigger('custom-event');
		$("#userShowingImg").attr("src",data.response.jsonData);
	}else{
		
	}
});
</script>