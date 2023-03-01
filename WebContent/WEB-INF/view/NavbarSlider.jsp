<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Siderbar Start-->
<!-- [Siderbar](左選單) -->
<div class="divSliderBar">
	<div class="border-end bg-white" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">${sessionScope.account.name}
			先生/小姐您好!</div>
		<div class="list-group list-group-flush">
			<ul
				class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-left align-items-sm-start"
				id="menu">
				<li><a href="#submenu1" data-bs-toggle="collapse"
					class="btn btn-toggle nav-link px-0 align-middle"
					aria-expanded="false"> <i class="bi bi-house-fill"></i> <span
						class="ms-1 d-none d-sm-inline">首頁</span>
				</a>
					<ul class="collapse nav flex-column ms-1" id="submenu1"
						data-bs-parent="#menu">
						<li class="w-100"><a href="#"
							class="nav-link px-0 font-drak " onclick=""> <i
								class="bi bi-speedometer2"></i> <span class="d-none d-sm-inline">Dashboard</span>
						</a></li>
					</ul>
					</li>
				<li><a href="#submenu2" data-bs-toggle="collapse"
					class="btn btn-toggle nav-link px-0 align-middle"
					aria-expanded="true"> <i class="bi bi-cart-dash"></i> <span
						class="ms-1 d-none d-sm-inline">商品維護</span>
				</a>
					<ul class="collapse nav flex-column ms-1 show" id="submenu2"
						data-bs-parent="#menu">
						<li class="w-100"><a href="#"
							class="nav-link px-0 font-drak "
							onclick="location.href='BackendAction.do?action=BackendGoodsview'">
								<i class="bi bi-search"></i><span class="d-none d-sm-inline">商品查詢</span>
						</a></li>
						<li class="w-100"><a href="#"
							class="nav-link px-0 font-drak "
							onclick="location.href='BackendAction.do?action=BackendGoodsSaleReportview'">
								<i class="bi bi-calendar-week-fill"></i><span
								class="d-none d-sm-inline">訂單查詢</span>
						</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>
<!-- Sidebar End-->