// ********************************************************************************************************************************** //
// 套件名稱：共用 Function
// 備    註：
// 相依程式：
// 相依樣式：
// *********************************************************************************************************************************** //

// ================================================== 檢核項目 ================================================== //
/// <summary>
/// 檢核欄位
/// </summary>
/// <param name="_Item">項目</param>
/// <param name="_Params">參數</param>
function checkDataInfo(_Item, _strTxt1, _strTxt2) {
	let blFlag = true; // Return Check Result
	switch (_Item) {
		case "null":
			blFlag = $("#" + _strTxt1).val().replace(/(^s*)|(s*$)/g, "").length === 0;
			break;
		case "Email": // 正確 :true
			let emailRegxp = /^([\w]+)(.[\w]+)*@([\w]+)(.[\w]{2,3}){1,2}$/;
			blFlag = emailRegxp.test($("#" + _strTxt1).val());
			break;
		case "NaN": // 判斷數字
			blFlag = isNaN($("#" + _strTxt1).val());
			break;
		case "Integer": // 判斷整字
			blFlag = !($("#" + _strTxt1).val() % 1 === 0);
			break;
		case "filenull": // 判斷file空值
			blFlag = $("#" + _strTxt1)[0].files[0] === undefined;
			break;
		case "colnumns": // 判斷欄位欄位相同
			blFlag = $("#" + _strTxt1).val() !== $("#" + _strTxt2).val();
			break;
	}
	return blFlag;
}

// ================================================== 頁面狀態 ================================================== //

/// <summary>
/// 頁面狀態
/// </summary>
/// <param name="_strAction">頁面狀態</param>
/// <param name="_frmFrom">頁面參數</param>
function setPageStatus(_PageStatus) {
	switch (_PageStatus) {
		case "ViewList": // 瀏覽列表 模式
			// 頁面 狀態
			$('#spnActionMode').html('瀏覽列表狀態');
			$('#spnActionMode').val('ViewList');
			// Div
			$("#divDefaultDataList").hide(); // 隱藏 列表 初始告知
			$(".divDetailPanel").hide(); // 隱藏 列表 初始告知
			$(".divDataList").prop("disabled", false).show(); // 開啟
			$(".dataField").show();
			$('.divTotalRecords').show();
			//$('#divPaginationArea').show();
			// 按鈕
			$(".btnSearch ").prop("disabled", false).show(); // 搜尋
			$(".btnCreate ").prop("disabled", false).show(); // 新增
			$(".btnModify ").prop("disabled", false).hide(); // 修改
			$(".btnSave ").prop("disabled", false).hide(); // 存檔
			$(".btnRemove ").prop("disabled", false).hide(); // 移除
			$(".btnCancel ").prop("disabled", false).hide(); // 取消
			// 欄位 禁用
			$('.data-status').attr('disabled', true);
			break;
		case "ViewDetail":
			// 頁面 狀態
			$('#spnActionMode').html('瀏覽明細狀態');
			$('#spnActionMode').val('ViewDetail');
			// Div
			$(".divSearchPanel").prop("disabled", false).show(); // 開啟
			$(".divDataPanel").prop("disabled", false).show(); // 開啟
			$(".divDetailPanel").prop("disabled", false).show(); // 開啟
			$(".divDataList").prop("disabled", false).show(); // 開啟
			$(".divDetailPanel").prop("disabled", false).show(); // 開啟
			$(".dataField").show();
			$('.divTotalRecords').show();
			//$('#divPaginationArea').show();
			// 按鈕區
			$(".btnSearch ").prop("disabled", false).show(); // 搜尋
			$(".btnCreate ").prop("disabled", false).hide(); // 新增
			$(".btnModify ").prop("disabled", false).show(); // 修改
			$(".btnSave ").prop("disabled", false).hide(); // 存檔
			$(".btnRemove ").prop("disabled", false).show(); // 移除
			$(".btnCancel ").prop("disabled", false).show(); // 取消
			// 欄位 禁用
			$('.data-status').attr('disabled', true);
			break;
		case "Add": // 新增模式
			// 頁面 狀態
			$('#spnActionMode').html('新增狀態');
			$('#spnActionMode').val('Add');
			//DIV
			$(".divSearchPanel").prop("disabled", false).hide(); // 開啟
			$(".divDataPanel").prop("disabled", false).hide(); // 開啟
			$(".divDataList").prop("disabled", false).show(); // 開啟
			$(".divDetailPanel").prop("disabled", false).show(); // 開啟
			$('.divTotalRecords').hide();
			//$('#divPaginationArea').hide();
			// 按鈕區
			$(".btnSearch ").prop("disabled", false).hide(); // 搜尋
			$(".btnCreate ").prop("disabled", false).hide(); // 新增
			$(".btnModify ").prop("disabled", false).hide(); // 修改
			$(".btnSave ").prop("disabled", false).show(); // 存檔
			$(".btnRemove ").prop("disabled", false).hide(); // 移除
			$(".btnCancel ").prop("disabled", false).show(); // 取消
			// 欄位 禁用
			$('.data-status').attr('disabled', false);
			break;
		case "Remove":
			// 頁面 狀態

			// DIV
			$(".dataField").show();
			$('.divTotalRecords').show();
			// $('#divPaginationArea').show();
			// 按鈕區

			// 欄位狀態

			break;
		case "Modify": // 修改 模式
			// 頁面狀態
			$('#spnActionMode').val('Modify');
			$('#spnActionMode').html('修改狀態');
			// Div
			$(".divSearchPanel").prop("disabled", false).hide(); // 開啟
			$(".divDataPanel").prop("disabled", false).hide(); // 開啟
			$(".divDataList").prop("disabled", false).hide(); // 開啟
			$(".divDetailPanel").prop("disabled", false).show(); // 開啟
			$('.divTotalRecords').hide();
			//$('#divPaginationArea').hide();
			// 按鈕區
			$(".btnSearch ").prop("disabled", false).hide(); // 搜尋
			$(".btnCreate ").prop("disabled", false).hide(); // 新增
			$(".btnModify ").prop("disabled", false).hide(); // 修改
			$(".btnSave ").prop("disabled", false).show(); // 存檔
			$(".btnRemove ").prop("disabled", false).hide(); // 移除
			$(".btnCancel ").prop("disabled", false).show(); // 取消
			// 欄位 狀態
			$('.data-status').attr('disabled', false);
			break;
		case "Cancel": // 取消 模式
			// 頁面 狀態

			// Div
			$(".divSearchPanel").prop("disabled", false).show(); // 開啟
			$(".divDataPanel").prop("disabled", false).show(); // 開啟
			$(".divDetailPanel").prop("disabled", false).hide(); // 開啟
			if ($('#divDefaultDataList').css('display') === "none") {
				$("#divDefaultDataList").hide();
				$(".divDataList").prop("disabled", false).show(); // 開啟
				$('.divTotalRecords').show();
				//$('#divPaginationArea').show();
			} else {
				$("#divDefaultDataList").show();
				$(".divDataList").prop("disabled", false).hide(); // 開啟
				$('.divTotalRecords').hide();
				//$('#divPaginationArea').hide();
			}
			// 按鈕區
			$(".btnSearch ").prop("disabled", false).show(); // 搜尋
			$(".btnCreate ").prop("disabled", false).show(); // 新增
			$(".btnModify ").prop("disabled", false).hide(); // 修改
			$(".btnSave ").prop("disabled", false).hide(); // 存檔
			$(".btnRemove ").prop("disabled", false).hide(); // 移除
			$(".btnCancel ").prop("disabled", false).hide(); // 取消
			// 欄位狀態
			$('.data-status').val(""); // 明細清空
			//$('.data-status').html(""); // 明細清空
			break;
		case "Defalut": // 初始模式
			// Div
			$(".divSearchPanel").prop("disabled", false).show(); // 開啟
			$(".divDetailPanel").prop("disabled", false).hide(); // 開啟
			$("#divDefaultDataList").show(); // 隱藏 列表 初始告知
			$(".dataField").show();
			$('.divTotalRecords').hide();
			//$('#divPaginationArea').hide();
			// 按鈕區
			$(".btnSearch ").prop("disabled", false).show(); // 搜尋
			$(".btnCreate ").prop("disabled", false).show(); // 新增
			$(".btnModify ").prop("disabled", false).hide(); // 修改
			$(".btnSave ").prop("disabled", false).hide(); // 存檔
			$(".btnRemove ").prop("disabled", false).hide(); // 移除
			$(".btnCancel ").prop("disabled", false).hide(); // 取消
			// 欄位 禁用
			$('.data-status').attr('disabled', false);
			break;
	}
}

/// <summary>
/// 取得 下拉式選單
/// </summary>
/// <param name="str"> 參數字串</param>
/// <param name="txtID">欄位 ID</param>
/// <param name="strDefaultValue">預設 欄位ID</param>
function GetDDL(objFromData, txtID, strDefaultValue, strBlankOption) {
	let objDeferred = $.Deferred(); // 延遲物件
	let strAJAXUrl = 'ParameterAction.do?action=queuryParameter'; // Post Path
	let objData = objFromData;
	let strType = 'POST';
	let boolAsync = false;
	let boolContentType = false;
	let boolProcessData = false;
	let strMimeType = 'multipart/form-data';
	$.ajax({
		url: strAJAXUrl,
		data: objData,
		async: boolAsync,
		type: strType,
		contentType: boolContentType, //required
		processData: boolProcessData, // required
		mimeType: strMimeType,
		success: function(data, textStatus, jqXHR) {
			// success Action ...
			let strDDL = "";
			var dataArray = JSON.parse(data);
			$('#' + txtID + " option").remove(); // 清空
			strDDL = strBlankOption === "1" ? "<option value=\"\"></option>" : ""; // 下拉顯示第一欄空值
			$.each(dataArray, function(i) {
				if (dataArray[i].valued === strDefaultValue) {
					strDDL += "<option value=\"" + dataArray[i].valued + "\" selected=\"selected\">" + dataArray[i].selected + "</option>";
				} else {
					strDDL += "<option value=\"" + dataArray[i].valued + "\">" + dataArray[i].selected + "</option>";
				}
			});
			$('#' + txtID).html(strDDL); // Input text
		},
		error: function(jqXHR, textStatus, errorThrown) {
			// Ajax Error Action...
		},
		complete: function(jqXHR, textStatus) {
			// Final Action ...
		}
	});
	return objDeferred.promise();
}

/// <summary>
/// 控制 SwtichToggle 滑動按紐 控制
/// </summary>
function setSwtichToggle(strID, strParam) {
	let objSwtichToggle = $('#' + strID);
	// 畫面 : on & off 
	objSwtichToggle.bootstrapToggle(strParam, true);
	// value : 0 / 1 
	if (objSwtichToggle.prop('checked')) {
		objSwtichToggle.val(1);
	} else {
		objSwtichToggle.val(0);
	}
}
/// <summary>
/// SwtichToggle 滑動按紐 value : 1/0
/// </summary>
$(function() {
	$('.data-checkbox').change(function() {
		if ($(this).prop('checked')) {
			$("#" + this.id).val(1);
		} else {
			$("#" + this.id).val(0);
		}
	})
})

/// <summary>
/// 顯示 告警視窗
/// </summary>
/// <param name="_textName">項目名稱</param>
/// <param name="_strMessage">告警訊息</param>
/// <param name="_strHeaderColor">告警面板顏色</param>
/// <param name="_strFunction">Onclick Function</param>
function showModalMessage(_textName, _strMessage, _strHeaderColor, _strFunction) {
	let objDeferred = $.Deferred(); // 延遲物件
	_textName = _textName === "" ? _textName : $('#' + _textName).text();
	_strMessage = _textName + _strMessage;
	setModalsLoading(_strMessage, _strHeaderColor, _strFunction);
	setTimeout(function() {
		$('.modalControl').modal('show');
	}, 0); // 延遲
	return objDeferred.promise();
}

/// <summary>
/// 導入款式 告警視窗 
/// </summary>
/// <param name="_Message">告警訊息</param>
/// <param name="_strHeaderStatus">告警狀態</param>
/// <param name="_strFunction">方法</param>
function setModalsLoading(_Message, _strHeaderStatus, _strFunction) {
	switch (_strHeaderStatus) {
		case "Success":
			$(".modalPanel").html(
				"<div class=\"modal fade modalControl\" data-bs-backdrop=\"static\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">" +
				"<div class=\"modal-dialog modal-dialog-centered\">" +
				"<div class=\"modal-content\">" +
				"<div class=\"modal-success-header\">" +
				"<h5 class=\"modal-title\" id=\"exampleModalLabel\">告警視窗</h5>" +
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>" +
				"</div>" +
				"<div class=\"modal-body\">" + _Message + "</div>" +
				"<div class=\"modal-footer\"><button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">關閉</button></div>" +
				"</div>" +
				"</div>" +
				"</div>"
			);
			break;
		case "Error":
			$(".modalPanel").html(
				"<div class=\"modal fade modalControl\" data-bs-backdrop=\"static\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">" +
				"<div class=\"modal-dialog modal-dialog-centered\">" +
				"<div class=\"modal-content\">" +
				"<div class=\"modal-danger-header\">" +
				"<h5 class=\"modal-title\" id=\"exampleModalLabel\">提醒視窗</h5>" +
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>" +
				"</div>" +
				"<div class=\"modal-body\">" + _Message + "</div>" +
				"<div class=\"modal-footer\"><button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">關閉</button></div>" +
				"</div>" +
				"</div>" +
				"</div>"
			);
			break;
		case "Cancel":
			$(".modalPanel").html(
				"<div class=\"modal fade modalControl\" data-bs-backdrop=\"static\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">" +
				"<div class=\"modal-dialog modal-dialog-centered\">" +
				"<div class=\"modal-content\">" +
				"<div class=\"modal-cancel-header\">" +
				"<h5 class=\"modal-title\" id=\"exampleModalLabel\">確認視窗</h5>" +
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>" +
				"</div>" +
				"<div class=\"modal-body\">" + _Message + "</div>" +
				"<div class=\"modal-footer\">" +
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" onclick=\"" + _strFunction + "\">確認</button>" +
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">關閉</button>" +
				"</div>" +
				"</div>" +
				"</div>" +
				"</div>"
			);
			break;
		case "GoodsCancel":
			$(".modalPanel").html(
				"<div class=\"modal fade modalControl\" data-bs-backdrop=\"static\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">" +
				"<div class=\"modal-dialog modal-dialog-centered\">" +
				"<div class=\"modal-content\">" +
				"<div class=\"modal-cancel-header\">" +
				"<h5 class=\"modal-title\" id=\"exampleModalLabel\">系統貼心提醒:</h5>" +
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>" +
				"</div>" +
				"<div class=\"modal-body\">" + _Message + "</div>" +
				"<div class=\"modal-footer\">" +
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">關閉</button>" +
				"</div>" +
				"</div>" +
				"</div>" +
				"</div>"
			);
			break;
		case "logoutCancel":
			$(".modalPanel").html(
				"<div class=\"modal fade modalControl\" data-bs-backdrop=\"static\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">" +
				"<div class=\"modal-dialog modal-dialog-centered\">" +
				"<div class=\"modal-content\">" +
				"<div class=\"modal-cancel-header\">" +
				"<h5 class=\"modal-title\" id=\"exampleModalLabel\">系統貼心提醒:</h5>" +
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>" +
				"</div>" +
				"<div class=\"modal-body\">" + _Message + "</div>" +
				"<div class=\"modal-footer\">" +
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" onclick=\"" + _strFunction + "\">確認</button>" +
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">關閉</button>" +
				"</div>" +
				"</div>" +
				"</div>" +
				"</div>"
			);
			break;
	}
}

/// <summary>
/// 頁籤設定
/// </summary>
/// <param name="_Total">總筆數</param>
function setPagination(TotalCount) {
	let boolCheck = false; // 檢核是否第一次搜尋
	let intTotalPage = TotalCount; // 總筆數
	// let intShowInfo = $("#ddlRecordPages").val()===undefined?10:$("#ddlRecordPages").val(); // 顯示筆數
	intShowInfo = $("#ddlRecordPages").val() === undefined ? 10 : $("#ddlRecordPages").val();
	//console.log(intShowInfo);
	// 頁數 計算
	var sources = function() {
		var result = [];
		for (var i = 1; i < intTotalPage; i++) {
			result.push(i);
		}
		return result;
	}();
	$('#pagination').pagination({
		dataSource: sources, // 頁籤陣列[1,2,...]
		pageNumber: 1, // 預設指定頁籤
		pageSize: intShowInfo, // 每頁顯示頁數
		formatGoInput: '跳頁 : <%= input %>', // button 樣式
		showGoInput: true, // 是否顯示 跳頁框
		showGoButton: true, // 是否顯示 跳頁GO按鈕(如果沒有直接按下Enter)
		showSizeChanger: true, // 是否顯示 每頁數量下拉選單
		callback: function(data, pagination) { // function (指定頁籤的顯示序號/頁籤明細)
			// template method of yourself 按下頁籤後的動作
			//console.log(data);
			//console.log(pagination);
			let formData = new FormData(document.forms.frmSearch);
			// 備註 form bean 命名 
			//使用spring注释形式注入bean，通过@Component、@Repository、 @Service和@Controller注释类，文档中说“注解如果没有指定bean的名字，默认为小写开头的类名”。例如类名是MyClass，则spring返回myClass的bean名。 但是如果类名...
			//正文使用spring注释形式注入bean，通过@Component、@Repository、 @Service和@Controller注释类，文档中说“注解如果没有指定bean的名字，默认为小写开头的类名”。例如类名是MyClass，则spring返回myClass的bean名。
			//但是如果类名前两个字母都是大写，则返回的bean名也是大写，即类名是MYClass，bean名也是MYClass。
			formData.append('strMainPage', pagination.pageNumber.toString());
			formData.append('strShowPage', pagination.pageSize.toString());
			formData.append('strAction', 'List');
			//formData.append('action', 'queryGoods');
			if (boolCheck) {
				GetDataList(formData, false);
			}
		}
	})
	boolCheck = true;
}

/// <summary>
/// 登出 (動作確認)
/// </summary>
function doLogout () {
	showModalMessage('', '是否確認登出?', 'logoutCancel', "logout()");
}

/// <summary>
/// 登出
/// </summary>
/// <param name="objFromData">fromData</param>
function logout () {
	window.location.href = 'LoginAction.do?action=logout';
}



