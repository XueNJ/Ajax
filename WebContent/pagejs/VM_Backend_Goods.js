// **********************************************************************************************************************************
// 套件名稱：VM_Backend_Goods.js Function
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
	// 頁面 下拉
	SetDDLAction('ddlSearchOrderBy', '', '1'); // 下拉選單 : 排序(搜尋狀態)
	SetDDLAction('ddlSearchStatus', '', '1'); // 下拉選單 : 狀態(搜尋狀態)
});

// ================================================== 頁面動作 ==================================================//
/// <summary>
/// 告警視窗
/// </summary>
/// <param name="_strAction">頁面動作</param>
/// <param name="_frmFrom">頁面參數</param>
function PageAction(_strAction, _frmForm) {
	let strActionMode = $("#spnActionMode").val(); // Detail 模式狀態
	// 頁面執行 告警視窗
	switch (_strAction) {
		case "Save":
			switch (strActionMode) {
				case "Add":
					if (CheckData(strActionMode)) {
						showModalMessage('', '確認新增資料?', 'Cancel', 'SaveAction(document.forms.frmData,\'Add\')');
					}
					break;
				case "Modify":
					if (CheckData(strActionMode)) {
						showModalMessage('', '確認修改資料?', 'Cancel', 'SaveAction(document.forms.frmData,\'Modify\')');
					}
					break;
			}
			break;
		case "Remove":
			showModalMessage('', '確認刪除資料?', 'Cancel', 'RemoveAction(document.forms.frmData.hidPrimaryID.value)');
			break;
		case "Cancel":
			switch (strActionMode) {
				case "Add":
					showModalMessage('', '確認執行取消新增?', 'Cancel', "setPageStatus('Cancel')");
					break;
				case "Modify":
					showModalMessage('', '確認執行取消修改?', 'Cancel', "setPageStatus('Cancel')");
					break;
				case "ViewDetail":
					setPageStatus('Cancel');
					break;
				case "":
					setPageStatus('Cancel');
					break;
			}
			break;
	}
}
// ================================================== 該頁欄位檢核 ==================================================//
function CheckData(_strAction) {
	switch (_strAction) {
		case "Search":
			break;
		case "Add" || "Modify":
			if (checkDataInfo('null', "txtGoodsName")) { // 商品名稱
				showModalMessage('lblGoodsName', "欄位不可為空白。", "Error", ""); // 告警視窗
				return false;
			}
			if (checkDataInfo('null', "txtGoodsPrice")) { // 商品價格
				showModalMessage('lblGoodsPrice', "欄位不可為空白。", "Error"); // 告警視窗
				return false;
			}
			if (checkDataInfo('NaN', "txtGoodsPrice")) { // 商品價格
				showModalMessage('lblGoodsPrice', "欄位必須為數字。", "Error"); // 告警視窗
				return false;
			}
			if (checkDataInfo('Integer', "txtGoodsPrice")) { // 商品價格
				showModalMessage('lblGoodsPrice', "欄位必須為整數。", "Error"); // 告警視窗
				return false;
			}
			if (checkDataInfo('null', "txtGoodsQuantity")) { // 初始數量
				showModalMessage('lblGoodsQuantity', "欄位不可為空白。", "Error"); // 告警視窗
				return false;
			}
			if (checkDataInfo('NaN', "txtGoodsQuantity")) { // 初始數量
				showModalMessage('lblGoodsQuantity', "欄位必須為數字。", "Error"); // 告警視窗
				return false;
			}
			if (checkDataInfo('Integer', "txtGoodsQuantity")) { // 初始數量
				showModalMessage('lblGoodsPrice', "欄位必須為整數。", "Error"); // 告警視窗
				return false;
			}
			if (checkDataInfo('filenull', "txtUpdateGoodsImage")) { // 上傳圖片
				showModalMessage('lblUpdateGoodsImage', "欄位不可為空白。", "Error"); // 告警視窗
				return false;
			}
			break;
	}
	return true;
}

// ================================================== 該頁欄位檢核 ==================================================//
/// <summary>
/// 下拉式選單 DDL
/// </summary>
/// <param name="txtName">欄位名稱</param>
/// <param name="strDefaultValue">預設參數Value</param>
/// <param name="intNoBlankOption">0:無空白/1:有空白</param>
function SetDDLAction(txtName, strDefaultValue, intNoBlankOption) {
	let objDeferred = $.Deferred(); // 延遲物件
	let formData = new FormData();
	switch (txtName) {
		case "ddlSearchOrderBy": // 狀態(搜尋列表)
			formData.append('ID', 'OrderBy');
			GetDDL(formData, txtName, strDefaultValue, intNoBlankOption);
			objDeferred.resolve();
			break;
		case "ddlSearchStatus": // 狀態(搜尋列表)
			formData.append('ID', 'Status');
			GetDDL(formData, txtName, strDefaultValue, intNoBlankOption);
			objDeferred.resolve();
			break;
		case "ddlRecordPages": // 狀態(搜尋列表)
			formData.append('ID', 'Page');
			GetDDL(formData, txtName, strDefaultValue, intNoBlankOption);
			objDeferred.resolve();
			break;
	}
	return objDeferred.promise();
}

// ================================================== 該頁動作特殊處理 ==================================================//
/// <summary>
/// 該頁面欄位狀態處理
/// </summary>
/// <param name="_strAction">頁面動作</param>
function setMainPageStatus(_PageStatus) {
	setPageStatus(_PageStatus); // Action Page Setting
	switch (_PageStatus) {  // Main Page Setting
		case "ViewDetail":
			$("#divGoodsID").show();
			$("#divGoodsImage").show(); // 商品圖片
			$("#divGoodsImageName").show(); // 圖片名稱
			$("#divUpdateGoodsImage").hide(); // 更新圖片
			break;
		case "Add":
			$("#divGoodsID").hide();
			$("#divGoodsImage").hide(); // 商品圖片
			$("#divGoodsImageName").hide(); // 圖片名稱
			$("#divUpdateGoodsImage").show(); // 更新圖片
			setSwtichToggle('ckbStatus', 'on'); // 狀態 預設:啟用
			break;
		case "Modify":
			$('#txtGoodsID').attr('disabled', true);
			$('#txtGoodsImageName').attr('disabled', true);
			$("#divGoodsID").show(); // ID
			$("#divGoodsImage").show(); // 商品圖片
			$("#divGoodsImageName").show(); // 圖片名稱
			$("#divUpdateGoodsImage").show(); // 更新圖片
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
/// 明細
/// </summary>
/// <param name="_strID">參數</param>
function ViewDetailAction(_strID) {
	let objDeferred = $.Deferred(); // 延遲物件
	$('.data-status').attr('disabled', false); // 欄位啟用
	let formData = new FormData();
	formData.append('txtSearchGoodsID', _strID);
	formData.append('strAction', 'Detail');
	GetDataDetail(formData); // 資料處理
	return objDeferred.promise();
}
/// <summary>
/// 存檔
/// </summary>
/// <param name="_fromData">參數</param>
function SaveAction(_fromData, _ActionMode) {
	let objDeferred = $.Deferred(); // 延遲物件
	let formData = new FormData(_fromData);
	switch (_ActionMode) {
		case "Add":
			formData.append('ckbStatus', $("#ckbStatus").val()); // add checkbox
			AddData(formData);
			break;
		case "Modify":
			formData.append('hidPrimaryID', $('#hidPrimaryID').val()); // add hid txt
			formData.append('ckbStatus', $("#ckbStatus").val()); // add checkbox
			ModifyData(formData);
			break;
	}
	objDeferred.resolve(); // 已解決
	return objDeferred.promise();
}
/// <summary>
/// 刪除
/// </summary>
/// <param name="_strID">參數</param>
function RemoveAction(_strID) {
	let objDeferred = $.Deferred(); // 延遲物件
	let formData = new FormData();
	formData.append('ID', _strID);
	RemoveData(formData); // 資料處理
	objDeferred.resolve(); // 已解決
	return objDeferred.promise();
}
/// <summary>
/// 列表更新
/// </summary>
/// <param name="_strID">參數</param>
function setDataAction(_strID) {
	let objDeferred = $.Deferred(); // 延遲物件
	let formData = new FormData();
	formData.append('txtSearchGoodsID', _strID);
	formData.append('strAction', 'Detail');
	SetDataList(formData, false); // 資料處理
	objDeferred.resolve(); // 已解決
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
	let strAJAXUrl = 'BackendAction.do?action=queryGoods'; // Post Path
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
					"<td colspan=\"5\" class=\"text-center\" nowrap>" + "查無資料!" + "</td>" +
					"</tr>");
			} else {
				// table list
				$.each(data[0].listGoods, function(i) { // dataArray[i].valued 
					$("#tblDataList tbody").append(
						"<tr id=\"tblDataList_" + data[0].listGoods[i].goodsID + "\" onclick=\"ViewDetailAction('" + data[0].listGoods[i].goodsID + "')\">" +
						"<td class=\"text-center\" nowrap>" + data[0].listGoods[i].goodsID + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listGoods[i].goodsName + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listGoods[i].goodsPrice + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listGoods[i].goodsQuantity + "</td>" +
						"<td class=\"text-center\" nowrap>" + data[0].listGoods[i].statusName + "</td>" +
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

/// <summary>
/// 搜尋 明細
/// </summary>
/// <param name="strJson">JSON</param>
function GetDataDetail(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'BackendAction.do?action=queryGoods'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true;
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
			if (data[0].totalCount === 1) {
				$("#hidPrimaryID").val(data[0].listGoods[0].goodsID); // hid ID
				$("#imgGoodsImage").attr("src", "imgs/" + (data[0].listGoods[0].goodsImageName === "" ? "nothing.jpg" : data[0].listGoods[0].goodsImageName)); // 圖片位置
				$("#txtGoodsID").val(data[0].listGoods[0].goodsID); // ID
				$("#txtGoodsName").val(data[0].listGoods[0].goodsName); // 商品名稱
				$("#txtGoodsPrice").val(data[0].listGoods[0].goodsPrice); // 商品價格
				$("#txtGoodsQuantity").val(data[0].listGoods[0].goodsQuantity); // 商品數量
				$("#txtGoodsImageName").val(data[0].listGoods[0].goodsImageName); // 商品圖片名稱
				setSwtichToggle('ckbStatus', data[0].listGoods[0].status.toString() === "1" ? 'on' : 'off'); // 商品狀態 (0/1)
			}
		},
		error: function() {
			strMessage = '系統錯誤訊息。';
			showModalMessage('', strMessage); // 告警視窗

		},
		complete: function() {
			setMainPageStatus('ViewDetail');
		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 新增&修改後新一筆資料搜出放入列表
/// </summary>
/// <param name="objFromData">fromData</param>
function SetDataList(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'BackendAction.do?action=queryGoods'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true; // 非同步
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
			// 新增修改完畢資料加入列表
			if (data[0].totalCount === 1) {
				$("#tblDataList tbody").append(
					"<tr id=\"tblDataList_" + data[0].listGoods[0].goodsID + "\" onclick=\"ViewDetailAction('" + data[0].listGoods[0].goodsID + "')\">" +
					"<td class=\"text-center\" nowrap>" + data[0].listGoods[0].goodsID + "</td>" +
					"<td class=\"text-center\" nowrap>" + data[0].listGoods[0].goodsName + "</td>" +
					"<td class=\"text-center\" nowrap>" + data[0].listGoods[0].goodsPrice + "</td>" +
					"<td class=\"text-center\" nowrap>" + data[0].listGoods[0].goodsQuantity + "</td>" +
					"<td class=\"text-center\" nowrap>" + data[0].listGoods[0].statusName + "</td>" +
					"</tr>");
			}
			intTotalCount = data[0].totalCount;
			$("#divTotalRecords").html("&nbsp;總筆數:" + intTotalCount + "筆");
			setPagination(intTotalCount); // 
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

/// <summary>
/// 新增 資料
/// </summary>
/// <param name="objFromData">from表單</param>
function AddData(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'BackendAction.do?action=createGoods'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true;
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	var strMessage = ''; // 訊息視窗
	var strID = '';
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
			switch (data[0].resultMessage.strResult) {
				case "S":
					strID = data[0].ID;
					strMessage = data[0].resultMessage.strMessage;
					break;
				case "E":
					strMessage = data[0].data[0].resultMessage.strErrorMessage;
					break;
			}
		},
		error: function() {
			strMessage = '系統錯誤訊息。';
		},
		complete: function() {
			setDataAction(strID);
			showModalMessage('', strMessage, 'Success'); // 告警視窗
			setPageStatus('Cancel');

		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 刪除 資料
/// </summary>
/// <param name="strJson">JSON</param>
function RemoveData(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'BackendAction.do?'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true;
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	var strMessage = ''; // 訊息視
	var strResult = ''; // 回傳結果
	var strModelStatus = ''; // 告警視窗顏色
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
			strResult = data[0].resultMessage.strResult;
			strMessage = data[0].resultMessage.strMessage;
			strMessage = data[0].data[0].resultMessage.strErrorMessage;
			switch (strResult) {
				case "S":
					$("#tblDataList_" + data[0].ID).empty(); // 清除該列資料
					strModelStatus = 'Success'; // 告警狀態
					break;
				case "E":
					strModelStatus = 'Error'; // 告警狀態
					break;
			}
		},
		error: function() {
			strMessage = '系統錯誤訊息。';
		},
		complete: function() {
			setPageStatus('Cancel');
			showModalMessage('', strMessage, strModelStatus); // 告警視窗
		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 修改 資料
/// </summary>
/// <param name="strJson">JSON</param>
function ModifyData(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'BackendAction.do?action=updateGoods'; // Post Path
	let objData = objFromData; // form Data
	let strDataType = 'json';
	let strType = 'POST';
	let boolAsync = true;
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	var strMessage = ''; // 訊息視
	var strResult = ''; // 回傳結果
	var strModelStatus = ''; // 告警視窗顏色
	var strID = '';
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
			strResult = data[0].resultMessage.strResult;
			switch (strResult) {
				case "S":
					strModelStatus = 'Success'; // 告警狀態
					strID = data[0].ID;
					strMessage = data[0].resultMessage.strMessage;
					break;
				case "E":
					strModelStatus = 'Error'; // 告警狀態
					strMessage = data[0].resultMessage.strErrorMessage;
					break;
			}
		},
		error: function() {
			strMessage = '系統錯誤訊息。';
		},
		complete: function() {
			setDataAction(strID);
			showModalMessage('', strMessage, strModelStatus); // 告警視窗
			setPageStatus('Cancel');
		}
	});
	return objDeferred.promise();
}


