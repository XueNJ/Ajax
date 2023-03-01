<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/" var="WEB_PATH" />
<c:url value="/js" var="JS_PATH" />
<!DOCTYPE html>
<html>
<head>
<link href="imgs/favicon.ico" rel="shortcut icon">
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="imgs/favicon.ico" rel="shortcut icon">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
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
<script type="text/javascript" src="pagejs/VendingMachinePayment.js"></script>
<title>販賣機</title>
</head>
<body>
	<%-- ModalPanel Start --%>
	<%@ include file="../view/ModalPanel.jsp"%>
	<%-- ModalPanel End --%>
	<%-- Top Navbar & Page Info Start --%>
	<div class="d-flex" id="wrapper">
		<div class="toppagebox" id="page-content-wrapper">
			<%-- TopMenu Start --%>
			<%@ include file="../view/NavbarTopFrontend.jsp"%>
			<%-- TopMenu End --%>
			<%-- Page Info Start --%>
			<div class="container-fluid">
				<br />
				<!-- Data -->
				<div class="divDataPanel">
					<!-- 特價品 -->
					<div class="py-5 text-center">
						<div style="height: 400px">
							<img class="d-block mx-auto mb-4" src="DrinksImage/0712.jpg"
								alt="" width="80%" height="80%">
						</div>
						<h2>歡迎光臨賣場</h2>
						<p class="lead">將賣場搬到您的住家附近，提供多樣的選擇，讓您輕鬆購買平日所需，讓生活更方便。
							您可以用更近的距離，輕鬆買到日常用品及雜貨食品；優質且平價的家樂福商品，給您物超所值的購物選擇；多樣進口商品，滿足您不同的美好生活體驗。
							Market 超市給您新鮮好品質，新鮮直送的蔬果產品，以及有機、履歷蔬菜產品，讓您買得安心吃得放心，天天享受天然好滋味。
							還有提供多樣的精選非食品及季節性商品，滿足您的需求。 在Market
							超市您也可以使用家樂福好康卡/聯名卡，讓您享受會員紅利積點優惠，享受便利生活!.</p>
					</div>
					<!-- 特價品 -->
					<div class="row g-5">
						<div class="col-md-4 col-lg-3">
							<div class="card text-center">
								<h5>歡迎光臨，Tomcat！</h5>
								<div class="container">
									<div class="row align-self-center">
										<div class="col-12" style="height: 150px">
											<img border="0" src="imgs/coffee.jpg" height="80%">
										</div>
										<div>
											<form id="frmPay" name="frmPay" method="post"
												autocomplete="off">
												<div class="input-group mb-3">
													<input type="number" class="form-control" id="inputMoney"
														name="inputMoney" placeholder="請輸入付款金額..."
														aria-label="請輸入付款金額..." aria-describedby="btnPay"
														value="0">
													<div class="input-group-append">
														<button class="btn btn-outline-secondary" type="button"
															id="btnPay" name=""
															onclick="doPayAction(document.forms.frmPay);">付款</button>
													</div>
												</div>
											</form>
										</div>
									</div>
									<br />
								</div>
							</div>
						</div>
						<div class="col-md-8 col-lg-9 order-md-last">
							<div class="card text-center">
								<div class="" id="dataList">
									<form id="frmList" name="frmList" method="post"
										autocomplete="off">
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
															<th class="text-center" nowrap><span class="">商品圖片</span></th>
															<th class="text-center" nowrap><span class="">商品名稱</span></th>
															<th class="text-center" nowrap><span class="">商品價格</span></th>
															<th class="text-center" nowrap><span class="">購買數量</span></th>
															<th class="text-center" nowrap><span class="">刪除</span></th>
														</tr>
													</thead>
													<tbody>
														<tr id="tblDataList_1">
															<td class="text-center" style="vertical-align: middle">1</td>
															<td class="text-center" style="vertical-align: middle"><img
																src="imgs/2013081316151831.jpg" alt="" border=3
																height=100 width=100></img></td>
															<td class="text-center" style="vertical-align: middle">coke_original</td>
															<td class="text-center" style="vertical-align: middle">100</td>
															<td class="text-center" style="vertical-align: middle">5</td>
															<td class="text-center" style="vertical-align: middle"><button
																	type="button" class="btn btn-danger"
																	onclick="removeGoods('1');">
																	<i class="bi bi-trash"></i>
																</button></td>
														</tr>
													</tbody>
												</table>
												<hr />
												<span style="float: right" id="totolCount">總金額 : 0 元</span>
												<br /> <br />
											</div>
											<!-- 資料表格結束 -->
										</div>
										<!-- 資料列表結束 -->
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%-- Page Info End --%>
	</div>
	<br />
	<%-- Top Navbar & Page Info End --%>
	<footer class="container">
		<div class="text-center">
			<p>© Company 2017-2022</p>
		</div>
	</footer>
</body>
</html>