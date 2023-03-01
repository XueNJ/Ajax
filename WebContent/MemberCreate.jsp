<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>商品管理系統</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="imgs/favicon.ico">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<!-- script core  -->
<script src="script/jquery-3.6.0.min.js"></script>
<script src="script/bootstrap.bundle.min.js"></script>
<script src="js/function.js"></script>
<script src="pagejs/MemberCreate.js"></script>
</head>
<body>
	<h1>Customer Create Page</h1>
	<HR>

	<h2>註冊頁面</h2>
	<br />
	<div class="modalPanel"></div>
	<div style="margin-left: 25px;">
		<div class="card-body">
			<form id="frmData" name="frmData" method="post" autocomplete="off">
				<input type="hidden" id="spnActionMode" name="spnActionMode" value="Add">
				<div class="row">
					<div id="divname" class="form-group col-lg-6" data-form-type="text">
						<label for="name" id="lblname" class="control-label">姓名</label> <input
							type="text" id="name" name="name" class="form-control text-input"
							placeholder="Enter Name">
					</div>
					<div id="divid" class="form-group col-lg-6" data-form-type="text">
						<label for="id" id="lblid" class="control-label">帳號</label> <input
							type="text" id="id" name="id" class="form-control text-input"
							placeholder="Enter Account">
					</div>
					<div id="divpwd" class="form-group col-lg-6" data-form-type="text">
						<label for="pwd" id="lblpwd" class="control-label">密碼</label> <input
							type="password" id="pwd" name="pwd" class="form-control text-input"
							placeholder="Enter Password">
					</div>
					<div id="divpwdCheck" class="form-group col-lg-6"
						data-form-type="text">
						<label for="pwdCheck" id="lblpwdCheck" class="control-label">密碼確認</label>
						<input type="password" id="pwdCheck" name="pwdCheck"
							class="form-control text-input" placeholder="Enter Check Password">
					</div>
				</div>
				<div class="text-right my-3">
					<div style="margin: 0px 2px 0px 0px;">
						<button type="button" class="btn btn-primary"
							onclick="PageAction('Save');">
							<span>送出</span>
						</button>
						<button type="button" class="btn btn-info"
							onclick="PageAction('Cancel')">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>