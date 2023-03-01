// **********************************************************************************************************************************
// 套件名稱：MemberCreate.js Function
// 備    註：
// 相依程式：
// 相依樣式：
// ***********************************************************************************************************************************
// ================================================== 宣告常數 ==================================================//

// ================================================== 頁面動作 ==================================================//
/// <summary>
/// 告警視窗
/// </summary>
/// <param name="_strAction">頁面動作</param>
/// <param name="_frmFrom">頁面參數</param>
function PageAction(_strAction) {
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
			}
			break;
		case "Cancel":
			switch (strActionMode) {
				case "Add":
					showModalMessage('', '確認取消註冊?', 'Cancel', "location.href='Login.jsp'");
					break;
			}
			break;
	}
}

/// <summary>
/// 存檔
/// </summary>
/// <param name="_fromData">參數</param>
function SaveAction(_fromData, _ActionMode) {
	let objDeferred = $.Deferred(); // 延遲物件
	showPageLoading();
	setTimeout(function() {
		let formData = new FormData(_fromData);
		switch (_ActionMode) {
			case "Add":
				formData.append('ckbStatus', $("#ckbStatus").val()); // add checkbox
				AddData(formData);
				break;
		}
		objDeferred.resolve(); // 已解決
	}, 500);
	removePageLoading();
	return objDeferred.promise();
}

// ================================================== 該頁欄位檢核 ==================================================//
function CheckData(_strAction) {
	switch (_strAction) {
		case "Add":
			// name 
			if (checkDataInfo('null', "name")) { // 商品名稱
				showModalMessage('lblname', " 欄位不可為空白。", "Error", ""); // 告警視窗
				return false;
			}
			// id 
			if (checkDataInfo('null', "id")) { // 商品名稱
				showModalMessage('lblid', " 欄位不可為空白。", "Error", ""); // 告警視窗
				return false;
			}
			// pwd
			if (checkDataInfo('null', "pwd")) { // 商品名稱
				showModalMessage('lblpwd', " 欄位不可為空白。", "Error", ""); // 告警視窗
				return false;
			}
			// pwd
			if (checkDataInfo('null', "pwdCheck")) { // 商品名稱
				showModalMessage('lblpwdCheck', " 欄位不可為空白。", "Error", ""); // 告警視窗
				return false;
			}
			// pwd
			if (checkDataInfo('colnumns', "pwd", "pwdCheck")) { // 商品名稱
				showModalMessage('', "密碼 & 密碼確認 不一致。", "Error", ""); // 告警視窗
				return false;
			}
			break;
	}
	return true;
}

// ================================================== 資料處理 ================================================== //

/// <summary>
/// 新增 資料
/// </summary>
/// <param name="objFromData">from表單</param>
function AddData(objFromData) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'LoginAction.do?action=MemberCreate'; // Post Path
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
			if (data[0].strResult == "S") {
				showModalMessage('', data[0].strMessage, 'GoodsCancel', "");
				$(".text-input").val(""); // 清空
			} else {
				showModalMessage('', data[0].strErrorMessage, 'GoodsCancel', "");
			}
		},
		error: function() {
			strMessage = '系統錯誤訊息。';
		},
		complete: function() {
		}
	});
	removePageLoading();
	return objDeferred.promise();
}


