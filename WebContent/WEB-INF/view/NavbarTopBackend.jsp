<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Top Nav Start-->
<div class="topmanu">
	<!-- [TopMenu](上選單) -->
	<nav
		class="navbar navbar-expand-lg navbar-dark  bg-dark  border-bottom">
		<div class="container-fluid">
			<div id="divBrand" class="mr-3">
				<img src="imgs/logo.gif" width="30" height="30" class=" mr-3"
					title="商標"><a id="aSystemCode"
					class=" navbar-brand align-middle mr-1" href="#" onclick=""
					title="後台維護系統">後台維護系統</a>
				<button class="side-bar logo-name__button btn_border bg"
					id="sidebarToggle">
					<i class="bi bi-arrow-bar-left icon_white" id="logo-name__icon"></i>
				</button>
			</div>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mt-2 mt-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="FrontendAction.do?action=FrontendGoodsview">販賣機購物</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="BackendAction.do?action=BackendGoodsview">後台機維護</a></li>
				</ul>
				<span class="">
					<button class="btn btn-outline-warning" type="button"
						onclick="doLogout();">
						<i class="bi bi-box-arrow-right"></i> 登出
					</button>
				</span>
			</div>
		</div>
	</nav>
</div>
<!-- Top Nav End-->