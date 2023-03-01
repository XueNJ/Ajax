<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/" var="WEB_PATH" />
<c:url value="/js" var="JS_PATH" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="imgs/favicon.ico" rel="shortcut icon">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/bootstrap-datepicker.css" rel="stylesheet">
<link href="css/bootstrap4-toggle.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<link href="css/pagination.css" rel="stylesheet">

<script src="script/jquery-3.6.0.min.js"></script>
<script src="script/bootstrap.bundle.min.js"></script>
<script src="script/bootstrap-datepicker.js"></script>
<script src="script/bootstrap4-toggle.min.js"></script>
<script src="script/bootstrap-slidemenu.js"></script>
<script src="script/pagination.js"></script>
<script src="js/function.js"></script>
<script src="pagejs/VM_Backend_GoodsSaleReport.js"></script>

<title>販賣機-後臺</title>
</head>
<body>
	<%-- ModalPanel Start --%>
	<%@ include file="../view/ModalPanel.jsp"%>
	<%-- ModalPanel End --%>
	<div class="d-flex" id="wrapper">
		<%-- Siderbar Start --%>
		<%@ include file="../view/NavbarSlider.jsp"%>
		<%-- Siderbar End --%>
		<%-- Top Navbar & Page Info Start --%>
		<div class="toppagebox" id="page-content-wrapper">
			<%-- TopMenu Start --%>
			<%@ include file="../view/NavbarTopBackend.jsp"%>
			<%-- TopMenu End --%>
			<%-- Page Info Start --%>
			<div class="pageinfo">
				<div class="container-fluid">
					<!-- PagePath -->
					<div class="divPagePath nav navbar-default navbar-fixed">
						<nav id="navPath" aria-label="breadcrumb" class="navPath">
							<ol class="breadcrumb bg-light olBreadcrumb">
								<li class="breadcrumb-item"><a href="#">商品維護</a></li>
								<li class="breadcrumb-item active" aria-current="page">訂單查詢</li>
							</ol>
						</nav>
					</div>
					<!-- PageName -->
					<div class="divPageName">
						<h2 id="hPageName" class="text-muted font-weight-bold">訂單查詢頁面</h2>
					</div>
					<!-- h r -->
					<hr class="hr-primary">
					<!-- Search -->
					<div class="divSearchPanel">
						<form id="frmSearch" name="frmSearch" method="post"
							autocomplete="off">
							<div class="divSearchField row">
								<div id="divPrice"
									class="formField dataField form-group col-lg-6">
									<label class="control-label">訂單日期(起-迄)</label>
									<div class="input-daterange input-group" id="datepicker">
										<input type="text" id="txtSearchStartDate"
											name="txtSearchStartDate" class="form-control"> <label
											class="input-group-btn"> <span
											class="btn btn-default"> <span class="bi bi-dash-lg"></span>
										</span>
										</label><input type="text" id="txtSearchEndDate"
											name="txtSearchEndDate" class="form-control" >
									</div>
								</div>
							</div>
							<div class="buttonAction text-right">
								<button type="button" class="btnSearch btn btn-success btn-sm"
									onclick="ViewListAction(document.forms.frmSearch)">
									<i class="bi bi-search"></i> <span class=""> 搜尋</span>
								</button>
							</div>
						</form>
					</div>
					<br />
					<!-- Input Pagination -->
					<!-- Data -->
					<div class="divDataPanel">
						<hr />
						<form id="frmList" name="frmList" method="post" autocomplete="off">
							<input type="text" name="txtPreventSubmit" class="d-none">
							<div id="divDefaultDataList" class=" alert alert-warning"
								style="display: none;">請按下搜尋按鈕以查詢資料...</div>
							<!-- 資料列表開始 -->
							<div class="divDataList" style="display: none;">
								<!-- 資料表格開始 -->
								<div class="table-responsive">
									<table id="tblDataList"
										class="table table-striped table-sm table-hover">
										<thead>
											<tr>
												<th class="text-center" nowrap="nowrap"><span class="">訂單編號</span></th>
												<th class="text-center" nowrap="nowrap"><span class="">訂單日期</span></th>
												<th class="text-center" nowrap="nowrap"><span class="">客戶名稱</span></th>
												<th class="text-center" nowrap="nowrap"><span class="">客戶編號</span></th>
												<th class="text-center" nowrap="nowrap"><span class="">商品金額</span></th>
												<th class="text-center" nowrap="nowrap"><span class="">購買數量</span></th>
												<th class="text-center" nowrap="nowrap"><span class="">總金額</span></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- 資料表格結束 -->
							</div>
							<!-- 資料列表結束 -->
						</form>
					</div>
					<!-- Pagination & TotalPage -->
					<div class="divTotalRecords form-inline float-right hide">
						<div>
							<input type="hidden" id="hidPaginationPages"
								name="hidPaginationPages" value="1">
						</div>
						<div id="divPaginationArea" class="row float-right clearRight">
							<div id="pagination"></div>
						</div>
						<div id="divTotalRecords"></div>
					</div>
					<!-- TotalPage -->
				</div>
			</div>
		</div>
		<%-- Page Info End --%>
	</div>
	<%-- Top Navbar & Page Info End --%>
</body>
</html>