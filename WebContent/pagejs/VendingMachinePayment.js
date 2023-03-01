// **********************************************************************************************************************************
// 套件名稱：VendingMachinePayment.js Function
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
	$("#sidebarToggle").hide(); // 隱藏滑動按鈕
	ViewListAction(); // 查詢購物車
	setPageStatus('ViewList'); // 列表模式
});

// ================================================== 資料入口 ================================================== //
/// <summary>
/// 列表
/// </summary>
/// <param name="_fromData">參數</param>
/// <param name="_pageNumber">頁籤</param>
function ViewListAction() {
	let objDeferred = $.Deferred(); // 延遲物件
	let formData = new FormData();
	GetDataList(formData);
	return objDeferred.promise();
}

/// <summary>
/// 移除購物車商品
/// </summary>
function removeGoods(strID) {
	let formData = new FormData();
	formData.append('goodsID', strID);
	removeDataList(formData);
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

/// <summary>
/// 結帳
/// </summary>
function doPayAction(_formData) {
	let formData = new FormData(_formData);
	payCartGoods(formData); // 資料處理
}

// ================================================== 資料處理 ================================================== //
/// <summary>
/// 搜尋 列表
/// </summary>
/// <param name="objFromData">fromData</param>
/// <param name="boolPagination">是否執行頁籤</param>
function GetDataList(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=queryCartGoods'; // Post Path
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
			$("#tblDataList tbody").empty(); // 清除 tbody
			if (data.shoppingCartGoods.length == 0) {
				$("#tblDataList tbody").append(
					"<tr>" +
					"<td colspan=\"6\" class=\"text-center\" nowrap>" + "目前購物車無商品!" + "</td>" +
					"</tr>");
			} else {
				// table list
				$.each(data.shoppingCartGoods, function(i) { // dataArray[i].valued 
					$("#tblDataList tbody").append(
						"<tr id=\"tblDataList_" + data.shoppingCartGoods[i].goodsID + "\">" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].goodsID + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" +
						"<img src=\"imgs/" + "2013081316151831" + ".jpg\" alt=\"\" border=3 height=100 width=100></img>" +
						"</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].goodsName + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].goodsPrice + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].buyQuantity + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\"><button" +
						"type=\"button\" class=\"btn btn-danger\"" +
						"onclick=\"removeGoods('" + data.shoppingCartGoods[i].goodsID + "');\">" +
						"<i class=\"bi bi-trash\"></i>" +
						"</button></td>" +
						"</tr>"
					);
				});
			}
			// 商品總金額
			$("#totolCount").html("總金額 :　" + "<font color='red'>" + data.totalAmount + "</font> 元 ");
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
/// 搜尋 列表
/// </summary>
/// <param name="objFromData">fromData</param>
/// <param name="boolPagination">是否執行頁籤</param>
function removeDataList(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=removeCartGoods'; // Post Path
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
			$("#tblDataList tbody").empty(); // 清除 tbody
			if (data.shoppingCartGoods.length == 0) {
				$("#tblDataList tbody").append(
					"<tr>" +
					"<td colspan=\"6\" class=\"text-center\" nowrap>" + "目前購物車無商品!" + "</td>" +
					"</tr>");
			} else {
				// table list
				$.each(data.shoppingCartGoods, function(i) { // dataArray[i].valued 
					$("#tblDataList tbody").append(
						"<tr id=\"tblDataList_" + data.shoppingCartGoods[i].goodsID + "\">" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].goodsID + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" +
						"<img src=\"imgs/" + "2013081316151831" + ".jpg\" alt=\"\" border=3 height=100 width=100></img>" +
						"</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].goodsName + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].goodsPrice + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\">" + data.shoppingCartGoods[i].buyQuantity + "</td>" +
						"<td class=\"text-center\" style=\"vertical-align: middle\"><button" +
						"type=\"button\" class=\"btn btn-danger\"" +
						"onclick=\"removeGoods('" + data.shoppingCartGoods[i].goodsID + "');\">" +
						"<i class=\"bi bi-trash\"></i>" +
						"</button></td>" +
						"</tr>"
					);
				});
			}
			// 商品總金額
			$("#totolCount").html("總金額 : " + "<font color='red'>" + data.totalAmount + "</font> 元 ");
			// 移除 指定商品
			$("#tblDataList_" + strID).remove();
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

/// <summary>
/// 結帳購物車
/// </summary>
/// <param name="objFromData">fromData</param>
function payCartGoods(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'FrontendAction.do?action=buyGoods'; // Post Path
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
			console.log(data);
			if (data.strResult == "S") {
				showModalMessage('', data.strMessage, 'GoodsCancel', ""); // 付款結果
				// 清除 購物車資訊
				$("#tblDataList tbody").empty(); // 清除 tbody
				// 列表 歸 0
				$("#tblDataList tbody").append(
					"<tr>" +
					"<td colspan=\"6\" class=\"text-center\" nowrap>" + "目前購物車無商品!" + "</td>" +
					"</tr>");
				$("#totolCount").html("總金額 :　" + "<font color='red'>" + 0 + "</font> 元 ");
				$("#inputMoney").val(0); // 歸0
			} else if (data.strResult == "E") {
				showModalMessage('', data.strErrorMessage, 'GoodsCancel', ""); // 付款結果
			} else if (data.strResult == "0") {
				showModalMessage('', data.strErrorMessage, 'GoodsCancel', ""); // 付款結果
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
		},
		complete: function() {
		}
	});
	return objDeferred.promise();
}



