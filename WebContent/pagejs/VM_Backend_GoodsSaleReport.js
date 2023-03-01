// **********************************************************************************************************************************
// 套件名稱：VM_Backend_GoodsSaleReport.js Function
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
	setPageStatus('Defalut'); // 頁面 初始模式
	$('.input-daterange').datepicker({
		format: "yyyy/mm/dd"
	});
});

// ================================================== 該頁動作特殊處理 ==================================================//
/// <summary>
/// 該頁面欄位狀態處理
/// </summary>
/// <param name="_strAction">頁面動作</param>
function setMainPageStatus(_PageStatus) {
	setPageStatus(_PageStatus); // Action Page Setting
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

// ================================================== 資料處理 ================================================== //
/// <summary>
/// 搜尋 列表
/// </summary>
/// <param name="objFromData">fromData</param>
/// <param name="boolPagination">是否執行頁籤</param>
function GetDataList(objFromData, boolPagination) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'BackendAction.do?action=queryOrderBetweenDate'; // Post Path
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
			$("#tblDataList tbody").empty(); // 清除 tbody
			intTotalCount = data[0].totalCount; // 總筆數
			$("#divTotalRecords").html("&nbsp;總筆數:" + intTotalCount + "筆");
			if (intTotalCount == 0) {
				$("#tblDataList tbody").append(
					"<tr>" +
					"<td colspan=\"7\" class=\"text-center\" nowrap>" + "查無資料!" + "</td>" +
					"</tr>");
			} else {
				// table list
				$.each(data[0].listOrder, function(i) { // dataArray[i].valued 
					$("#tblDataList tbody").append(
						"<tr id=\"tblDataList_" + data[0].listOrder[i].orderID + "\">" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].orderID + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].orderDate + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].customerID + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].customerName + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].goodsBuyPrice + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].buyQuantity + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listOrder[i].buyAmount + "</td>" +
						"</tr>");
				});
			}

			// 頁面搜尋才符合條片
			if (boolPagination) {
				setPagination(intTotalCount); // 
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			strMessage = '系統錯誤訊息。';
			showModalMessage('', strMessage); // 告警視窗
		},
		complete: function() {
			setPageStatus('ViewList');
		}
	});
	return objDeferred.promise();
}
