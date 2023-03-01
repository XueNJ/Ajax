// **********************************************************************************************************************************
// 套件名稱：VendingMachine.js Function
// 備    註：
// 相依程式：
// 相依樣式：
// ***********************************************************************************************************************************
// ================================================== 宣告常數 ==================================================//

// ================================================== 初始載入 ==================================================//
/// <summary>
/// 初始載入
/// </summary>
$(document).ready(function() {
	$("#sidebarToggle").hide();
	$('.divDataPanel').show();
	ViewListAction(document.forms.frmSearch); // 列表搜尋
});

// ================================================== 頁面動作 ================================================== //
/// <summary>
/// 告警視窗
/// </summary>
/// <param name="_strAction">頁面動作</param>
/// <param name="_frmFrom">頁面參數</param>
function PageAction(_strAction, _frmForm) {
	switch (_strAction) {
		case "query":
			break;
		case "clear":
			break;
		case "query":
			break;
		case "pay":
			break;
		case "logout":
			showModalMessage('', '是否確認登出?', 'logoutCancel', "logout()");
			break;
	}
}

// ================================================== 資料入口 ================================================== //
/// <summary>
/// 列表
/// </summary>
/// <param name="_fromData">參數</param>
/// <param name="_pageNumber">頁籤</param>
function ViewListAction(_fromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let formData = new FormData(_fromData);
	formData.append('strMainPage', 1);
	formData.append('strShowPage', $("#ddlRecordPages").val() === undefined ? 10 : $("#ddlRecordPages").val());
	formData.append('strAction', 'List');
	GetDataList(formData, true);
	return objDeferred.promise();
}
/// <summary>
/// 加入購物車
/// </summary>
/// <param name="goodsID">商品編號</param>
/// <param name="buyQuantityIdx">購買數量</param>
function addCartGoods(goodsID) {
	let objbuyQuantity = $("#txtQuantitys_" + goodsID) // 商品數量
	let objGoodsName = $("#divGoodsName_" + goodsID) // 商品名稱
	if (objbuyQuantity.val() == 0) {
		showModalMessage('', objGoodsName.html() + " : [0]，請輸入購買數量。", 'GoodsCancel', "setPageStatus('Cancel')");
	} else {
		const objFormData = new FormData();
		objFormData.append('goodsID', goodsID); // 商品編號
		objFormData.append('buyQuantity', objbuyQuantity.val()); // 商品數量
		putIntoCartGoods(objFormData); // 資料處理
		objbuyQuantity.val(0); // 歸0
	}
}
/// <summary>
/// 查詢購物車
/// </summary>
function doQueryCartGoods() {
	queryCartGoods(); // 資料處理
}
/// <summary>
/// 清空購物車
/// </summary>
function doClearCartGoods() {
	clearCartGoods(); // 資料處理
}

// ================================================== 資料處理 ================================================== //
/// <summary>
/// 搜尋 列表
/// </summary>
/// <param name="objFromData">fromData</param>
/// <param name="boolPagination">是否執行頁籤</param>
function GetDataList(objFromData, boolPagination) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=searchGoods'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true; // 非同步 ture 非同步 false 同步
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	let intTotalCount = 0;
	$.ajax({
		url: strAJAXUrl,
		data: objData,
		dataType: strDataType,
		type: strType,
		async: boolAsync,
		contentType: boolContentType, //required
		processData: boolProcessData, // required
		mimeType: strMimeType,
		success: function(data) {
			$("#dataList").empty(); // 清除 div
			intTotalCount = data[0].totalCount; // 總筆數
			$("#divTotalRecords").html("&nbsp;總計查詢商品數量:" + intTotalCount + "個");
			if (intTotalCount == 0) {
				showModalMessage('', '查無此商品！', 'GoodsCancel', "");
			} else {
				// table list
				$.each(data[0].listGoods, function(i) { // dataArray[i].valued 
					$("#dataList").append(
						"<div class=\"col-sm-4 col-md-4 col-xl-3 mb-3 text-center\">" +
						"<div class=\"card mb-4 shadow-sm\">" +
						"<div class=\"card-header\">" +
						"<h5 class=\"my-0 font-weight-normal\"><div id=\"divGoodsName_" + data[0].listGoods[i].goodsID + "\">" + data[0].listGoods[i].goodsName + "</div></h4>" +
						"</div>" +
						"<div class=\"card-body\">" +
						"<div style=\"height:210px\">" +
						"<img class=\"bd-placeholder-img card-img-top\"src=\"DrinksImage/" + data[0].listGoods[i].goodsImageName + "\" height=\"100%\">" +
						"</div>" +
						"<ul class=\"list-unstyled mt-3 mb-4\">" +
						"<li>價格:" + data[0].listGoods[i].goodsPrice + "元/(庫存:" + data[0].listGoods[i].goodsQuantity + ")</li>" +
						"</ul>" +
						"<div class=\"input-group mb-3\">" +
						"<input type=\"number\" class=\"form-control\" aria-describedby=\"button-addon2\" id=\"txtQuantitys_" + data[0].listGoods[i].goodsID + "\" name=\"txtQuantitys_" + data[0].listGoods[i].goodsID + "\" value=\"0\">" +
						"<div class=\"input-group-append\">" +
						"<button class=\"btn btn-outline-secondary\" type=\"button\" id=\"button-addon2\" onclick=\"addCartGoods('" + data[0].listGoods[i].goodsID + "');\"><i class=\"bi bi-bag-plus-fill\"></i></button>" +
						"</div>" +
						"</div>" +
						"</div>" +
						"</div>" +
						"</div>"
					);
				});
			}

			// 頁面搜尋才符合條片
			if (boolPagination) {
				setPagination(intTotalCount); // 
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
		},
		complete: function() {
			setPageStatus('ViewList');
		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 加入購物車
/// </summary>
/// <param name="objFromData">fromData</param>
function putIntoCartGoods(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=addCartGoods'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true; // 非同步 ture 非同步 false 同步
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	$.ajax({
		url: strAJAXUrl,
		data: objData,
		dataType: strDataType,
		type: strType,
		async: boolAsync,
		contentType: boolContentType, //required
		processData: boolProcessData, // required
		mimeType: strMimeType,
		success: function(data) {
			if (data[0][0].strResult == 'S') {
				showModalMessage('', data[1][0].goodsName + '，加入購物車成功!', 'GoodsCancel', "setPageStatus('Cancel')");
			} else {
				showModalMessage('', data[1][0].goodsName + '，' + data[0][0].strMessage + '!', 'GoodsCancel', "setPageStatus('Cancel')");
			}
			console.log(data);

		},
		error: function(jqXHR, textStatus, errorThrown) {
		},
		complete: function() {
		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 查詢購物車
/// </summary>
/// <param name="objFromData">fromData</param>
function queryCartGoods() {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=queryCartGoods'; // Post Path
	//let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true; // 非同步 ture 非同步 false 同步
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	$.ajax({
		url: strAJAXUrl,
		//data: objData,
		dataType: strDataType,
		type: strType,
		async: boolAsync,
		contentType: boolContentType, //required
		processData: boolProcessData, // required
		mimeType: strMimeType,
		success: function(data) {
			let strShowMessage = '';
			if (data.totalAmount == 0 && data.shoppingCartGoods == '') {
				showModalMessage('', '目前購物車 : [0]件商品。', 'GoodsCancel', "");
			} else {
				// 商品
				$.each(data.shoppingCartGoods, function(i) { // dataArray[i].valued 
					strShowMessage += '商品名稱 : ' + data.shoppingCartGoods[i].goodsName +
						'/商品單價(' + data.shoppingCartGoods[i].goodsPrice + ')' +
						'/商品數量(' + data.shoppingCartGoods[i].buyQuantity + ')' +
						'<br/>';
				});
				// 金額
				strShowMessage += '總金額 : ' + data.totalAmount + "元";
				// 提醒視窗
				showModalMessage('', strShowMessage, 'GoodsCancel', "");
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
		},
		complete: function() {
		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 清除購物車
/// </summary>
/// <param name="objFromData">fromData</param>
function clearCartGoods() {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=clearCartGoods'; // Post Path
	//let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true; // 非同步 ture 非同步 false 同步
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	$.ajax({
		url: strAJAXUrl,
		//data: objData,
		dataType: strDataType,
		type: strType,
		async: boolAsync,
		contentType: boolContentType, //required
		processData: boolProcessData, // required
		mimeType: strMimeType,
		success: function(data) {
			if (data[0].strResult == 'S') {
				showModalMessage('', data[0].strMessage, 'GoodsCancel', "setPageStatus('Cancel')");
				ViewListAction();
			} else {
				showModalMessage('', data[0].strMessage, 'GoodsCancel', "setPageStatus('Cancel')");
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
		},
		complete: function() {
		}
	});
	return objDeferred.promise();
}




