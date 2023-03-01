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
<script src="script/function.js"></script>
<script src="pagejs/VM_Backend_Goods.js"></script>

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
								<li class="breadcrumb-item active" aria-current="page">商品查詢</li>
							</ol>
						</nav>
					</div>
					<!-- PageName -->
					<div class="divPageName">
						<h2 id="hPageName" class="text-muted font-weight-bold">商品查詢頁面</h2>
					</div>
					<!-- h r -->
					<hr class="hr-primary">
					<!-- Search -->
					<div class="divSearchPanel">
						<form id="frmSearch" name="frmSearch" method="post"
							autocomplete="off">
							<div class="divSearchField row">
								<div id="divtSearchGoodsID" class="form-group col-lg-3"
									data-form-type="text">
									<label for="txtSearchGoodsID" id="lblSearchGoodsID"
										class="control-label">商品編號</label> <input type="text"
										id="txtSearchGoodsID" name="txtSearchGoodsID"
										class="form-control" placeholder="Enter number">
								</div>
								<div id="divSearchGoodsName" class="form-group col-lg-3"
									data-form-type="text">
									<label for="txtSearchGoodsName" id="lblSearchGoodsName"
										class="control-label">商品名稱(不區分大小寫)</label> <input type="text"
										id="txtSearchGoodsName" name="txtSearchGoodsName"
										class="form-control" placeholder="Enter text">
								</div>
								<div id="divPrice"
									class="formField dataField form-group col-lg-6">
									<label class="control-label">商品價格(最低-最高)</label>
									<div class="input-group">
										<input type="text" id="txtSearchPriceDown"
											name="txtSearchPriceDown" class="form-control"
											placeholder="Enter goods price Start"> <label
											class="input-group-btn"> <span
											class="btn btn-default"> <span class="bi bi-dash-lg"></span>
										</span>
										</label><input type="text" id="txtSearchPriceUp"
											name="txtSearchPriceUp" class="form-control"
											placeholder="Enter goods price end">
									</div>
								</div>
								<div id="divSearchOrderBy" class="form-group col-lg-3"
									data-form-type="select">
									<label for="ddlSearchOrderBy" id="lblSearchOrderBy"
										class="control-label">商品價格排序</label> <select
										id="ddlSearchOrderBy" name="ddlSearchOrderBy"
										class="form-control form-select"></select>
								</div>
								<div id="txtSearchGoodsQuantityDown" class="form-group col-lg-3"
									data-form-type="text">
									<label for="txtSearchGoodsQuantityDown"
										id="lblSearchGoodsQuantityDown" class="control-label">商品低於庫存量</label>
									<input type="text" id="txtSearchGoodsQuantityDown"
										name="txtSearchGoodsQuantityDown" class="form-control">
								</div>
								<div id="divSearchStatus" class="form-group col-lg-3"
									data-form-type="select">
									<label for="ddlSearchStatus" id="lblSearchStatus"
										class="control-label">商品狀態</label> <select
										id="ddlSearchStatus" name="ddlSearchStatus"
										class="form-control form-select"></select>
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
												<th class="text-center" nowrap><span class="">商品編號</span></th>
												<th class="text-center" nowrap><span class="">商品名稱</span></th>
												<th class="text-center" nowrap><span class="">商品價格</span></th>
												<th class="text-center" nowrap><span class="">商品庫存</span></th>
												<th class="text-center" nowrap><span class="">商品狀態</span></th>
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
					<br />
					<!-- Data & Detail Button Panel -->
					<div class="buttonAction dataButton text-right clearRight my-3">
						<button type="button" class="btnCreate btn btn-info btn-sm"
							onclick="setMainPageStatus('Add');" style="">
							<i class="bi bi-plus-circle"></i><span class=""> 新增</span>
						</button>
						<button type="button" class="btnModify btn btn-info btn-sm"
							onclick="setMainPageStatus('Modify')" disabled
							style="display: none;">
							<i class="bi bi-pencil-square"></i><span class=""> 修改</span>
						</button>
						<button type="button" class="btnSave btn btn-primary btn-sm"
							onclick="PageAction('Save',document.forms.frmData)"
							style="display: none;" disabled>
							<i class="bi bi-save2"></i> <span class="">存檔</span>
						</button>
						<button type="button" class="btnRemove btn btn-danger btn-sm"
							onclick="PageAction('Remove',document.forms.frmData)"
							style="display: none;" disabled>
							<i class="bi bi-trash3"></i> <span class="">刪除</span>
						</button>
						<button type="button" class="btnCancel btn btn-warning btn-sm"
							onclick="PageAction('Cancel')" style="display: none;" disabled>
							<i class="bi bi-x-circle"></i><span class="">取消</span>
						</button>
					</div>
					<!-- Detail -->
					<div class="divDetailPanel">
						<form id="frmData" name="frmData" method="post" autocomplete="off"
							enctype="multipart/form-data">
							<input type="hidden" id="hidPrimaryID" name="hidPrimaryID">
							<div class="card">
								<div class=" card-header">
									資料明細：<span id="spnActionMode">新增模式</span>
								</div>
								<div class="card-body">
									<div class="row">
										<div id="divGoodsImage"
											class="formField dataField form-group col-lg-12">
											<label id="lblGoodsImage" class="control-label">商品圖片</label>
											<div>
												<img src="imgs/nothing.jpg" id="imgGoodsImage"
													class="rounded mx-auto d-block" alt="..." width="304"
													height="236">
											</div>
										</div>
									</div>
									<div class="row">
										<div id="divGoodsID"
											class="formField dataField form-group col-lg-3">
											<label for="txtGoodsID" id="lblGoodsID" class="control-label">商品ID</label>
											<input type="text" id="txtGoodsID" name="txtGoodsID"
												class=" form-control data-status">
										</div>
									</div>
									<div class="row">
										<div id="divGoodsName"
											class="formField dataField form-group col-lg-6">
											<label for="txtGoodsName" id="lblGoodsName"
												class="control-label">商品名稱</label> <input type="text"
												id="txtGoodsName" name="txtGoodsName"
												class=" form-control data-status">
										</div>
										<div id="divGoodsPrice"
											class="formField dataField form-group col-lg-3">
											<label for="txtGoodsPrice" id="lblGoodsPrice"
												class="control-label">商品價格</label> <input type="text"
												id="txtGoodsPrice" name="txtGoodsPrice"
												class=" form-control data-status">
										</div>
										<div id="divGoodsQuantity"
											class="formField dataField form-group col-lg-3">
											<label for="txtGoodsQuantity" id="lblGoodsQuantity"
												class="control-label">初始數量</label> <input type="text"
												id="txtGoodsQuantity" name="txtGoodsQuantity"
												class=" form-control data-status">
										</div>
									</div>
									<div class="row">
										<div id="divGoodsImageName"
											class="formField dataField form-group col-lg-6">
											<label for="txtGoodsImageName" id="lblGoodsImageName"
												class="control-label">商品圖片名稱</label> <input type="text"
												id="txtGoodsImageName" name="txtGoodsImageName"
												class=" form-control data-status">
										</div>
										<div id="divUpdateGoodsImage"
											class="formField dataField form-group col-lg-6">
											<label for="txtUpdateGoodsImage" id="lblUpdateGoodsImage"
												class="control-label">圖片上傳</label> <input type="file"
												id="txtUpdateGoodsImage" name="txtUpdateGoodsImage"
												class=" form-control data-status">
										</div>
										<div id="divStatus"
											class="formField dataField form-group col-lg-3 ">
											<div>
												<label for="ckbStatus" id="lblStatus" class="control-label">商品狀態</label>
											</div>
											<div>
												<input type="checkbox" checked data-toggle="toggle"
													data-on="啟用" data-off="停用" data-onstyle="primary"
													data-offstyle="danger" id="ckbStatus" name="ckbStatus"
													value="0" class=" form-control data-checkbox data-status">
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- Data & Detail Button Panel -->
						<div class="buttonAction dataButton text-right clearRight my-3">
							<button type="button" class="btnCreate btn btn-info btn-sm"
								onclick="setMainPageStatus('Add');" style="">
								<i class="bi bi-plus-circle"></i><span class=""> 新增</span>
							</button>
							<button type="button" class="btnModify btn btn-info btn-sm"
								onclick="setMainPageStatus('Modify')" disabled
								style="display: none;">
								<i class="bi bi-pencil-square"></i> <span class=""> 修改</span>
							</button>
							<button type="button" class="btnSave btn btn-primary btn-sm"
								onclick="PageAction('Save',document.forms.frmData)"
								style="display: none;" disabled>
								<i class="bi bi-save2"></i> <span class="">存檔</span>
							</button>
							<button type="button" class="btnRemove btn btn-danger btn-sm"
								onclick="PageAction('Remove',document.forms.frmData)"
								style="display: none;" disabled>
								<i class="bi bi-trash3"></i> <span class="">刪除</span>
							</button>
							<button type="button" class="btnCancel btn btn-warning btn-sm"
								onclick="PageAction('Cancel')" style="display: none;" disabled>
								<i class="bi bi-x-circle"></i><span class="">取消</span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%-- Page Info End --%>
	</div>
	<%-- Top Navbar & Page Info End --%>
</body>
</html>