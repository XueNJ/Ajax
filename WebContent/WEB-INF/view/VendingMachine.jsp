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
<script type="text/javascript" src="pagejs/VendingMachine.js"></script>
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
							您可以用更近的距離，輕鬆買到日常用品及雜貨食品；優質且平價的賣場商品，給您物超所值的購物選擇；多樣進口商品，滿足您不同的美好生活體驗。
							Market 超市給您新鮮好品質，新鮮直送的蔬果產品，以及有機、履歷蔬菜產品，讓您買得安心吃得放心，天天享受天然好滋味。
							還有提供多樣的精選非食品及季節性商品，滿足您的需求。 在Market
							超市您也可以使用賣場好康卡/聯名卡，讓您享受會員紅利積點優惠，享受便利生活!.</p>
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
											<%-- <input type="number" class="form-control"
												placeholder="Recipient's username"
												aria-describedby="button-addon2" value="0">
											<button class="btn btn-outline-secondary" type="button"
												id="button-addon2">結帳</button> --%>
												<button type="button" class="btn btn-outline-primary" onclick="location.href='FrontendAction.do?action=FrontendGoodsPayview'"><i class="bi bi-credit-card-2-back"></i> 結帳 </button>
										</div>
									</div>
									<br/>
								</div>
							</div>
						</div>
						<div class="col-md-8 col-lg-9 order-md-last">
							<div class="row">
								<form class="my-2 my-lg-0" id="frmSearch" name="frmSearch"
									method="post" autocomplete="off">
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											placeholder="請輸入您要的商品名稱或關鍵字..."
											aria-label="請輸入您要的商品名稱或關鍵字..."
											aria-describedby="button-addon2" id="searchKeyword"
											name="searchKeyword">
										<div class="input-group-append">
											<button class="btn btn-outline-secondary" type="button"
												id="button-addon2"
												onclick="ViewListAction(document.forms.frmSearch);"><i class="bi bi-search"></i> 查詢</button>
										</div>
									</div>
								</form>
							</div>
							<div class="row" id="dataList"></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<!-- Pagination & TotalPage -->
						<div class="divTotalRecords form-inline float-right hide">
							<div>
								<input type="hidden" id="hidPaginationPages"
									name="hidPaginationPages" value="1">
							</div>
							<div id="divPaginationArea" class="row float-right clearRight">
								<div class="container marketing">
									<div class="row" id="pagination"></div>
								</div>
							</div>
							<div id="divTotalRecords"></div>
						</div>
						<!-- Pagination & TotalPage  -->
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