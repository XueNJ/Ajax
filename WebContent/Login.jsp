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
<link href="css/login.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<ul class="nav justify-content-end"></ul>
	</div>
	<div class="container h-100">
		<div class="row h-100 justify-content-center align-items-center">
			<form action="LoginAction.do" method="post">
				<div class="text-center" style="color: #fff;">
					<h1>歡迎光臨</h1>
				</div>
				<div class="text-center" style="margin-bottom: 5em; color: #fff;">
					<p>商品管理系統</p>
				</div>
				<input type="hidden" name="action" value="login" />
				<div class="text-center">
					<c:if test="${not empty requestScope.loginMsg}">
						<font style="color: #fff;">系統回應：</font>
						<font style="color: Red;">${requestScope.loginMsg}</font>
					</c:if>
				</div>
				<br />
				<div class="form-row justify-content-center">
					<div class="col-7">
						<input type="text" name="id" value=""
							class="form-control" id="id" placeholder="請輸入帳號">
					</div>
					<div class="col-7 my-1">
						<input type="password" name="pwd" value="" class="form-control"
							id="pwd" placeholder="請輸入密碼">
					</div>
				</div>
				<div class="form row justify-content-center">
					<div class="col-3  my-1">
						<button type="submit" class="btn btn-info btn-block"
							style="background-color: #36281d; color: #fff;">登入</button>
					</div>
					<div class="col-3  my-1">
						<div
							onclick="location.href='/JavaEE_Session4_Homework/MemberCreate.jsp';">
							<button type="button" class="btn btn-info btn-block"
								style="background-color: #36281d; color: #fff;">註冊</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>