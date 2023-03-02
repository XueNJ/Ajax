package com.training.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.training.formbean.BackendForm;
import com.training.model.Goods;
import com.training.model.GoodsAdd;
import com.training.model.GoodsUpdate;
import com.training.model.Orders;
import com.training.model.ResultMessage;
import com.training.service.BackendService;
import com.training.vo.voGoods;
import com.training.vo.voGoodsAdd;
import com.training.vo.voGoodsDelete;
import com.training.vo.voGoodsOrder;
import com.training.vo.voGoodsUpdate;

import net.sf.json.JSONArray;

public class BackendAction extends DispatchAction {

	private BackendService backendService = BackendService.getInstance();

	private static BackendForm fbGood;

	/**
	 * @methodName 商品維護
	 * 
	 * @input voGoodQuery voGQ
	 * 
	 * @return GoodsLIst
	 */
	public ActionForward BackendGoodsview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("goodsView");
	}

	/**
	 * @methodName 商品訂單報表查詢
	 * 
	 * @input voGoodQuery voGQ
	 * 
	 * @return GoodsLIst
	 */
	public ActionForward BackendGoodsSaleReportview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("goodssalereportView");
	}

	/**
	 * @methodName 商品列表查詢
	 * 
	 * @input voGoodQuery voGQ
	 * 
	 * @return GoodsLIst
	 */
	public ActionForward queryGoodsview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("goodsView");
	}

	/**
	 * @methodName 商品列表查詢
	 * 
	 * @input voGoodQuery
	 * 
	 * @return GoodsLIst
	 */
	public ActionForward queryGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbGood = (BackendForm) form;

		voGoods voGoods = new voGoods();

		BeanUtils.copyProperties(voGoods, fbGood);
		
		String strMainImagePath= getServlet().getInitParameter("GoodsImgPath");

		Goods goods = backendService.queryGoods(voGoods, strMainImagePath);

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(goods));
		out.flush();
		out.close();

		return mapping.findForward("goodsView");
	}

	/**
	 * @methodName 商品列表新增
	 * 
	 * @input voGoodQuery
	 * 
	 * @return GoodsAdd
	 */
	public ActionForward createGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbGood = (BackendForm) form;

		voGoodsAdd voGoods = new voGoodsAdd();

		ResultMessage rm = new ResultMessage();

		BeanUtils.copyProperties(voGoods, fbGood);

		GoodsAdd goods = backendService.createGoods(voGoods);

		try {
			// 圖片 update ...
			FormFile formfile = voGoods.getTxtUpdateGoodsImage(); // 取得 檔案資訊
			// Get the servers upload directory real path name / server存檔位置
			String filePath = getServlet().getServletContext().getRealPath("/") + "upload";
			// 指定存放位置
			String mainFilePath = getServlet().getInitParameter("MainGoodsImgPath");
			// create the upload folder if not exists
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			String fileName = formfile.getFileName();
			// 寫入
			if (!("").equals(fileName)) {
				System.out.println("Server path:" + filePath);
				File File1 = new File(filePath, fileName); // 指定位置
				File File2 = new File(mainFilePath, fileName);
				if (!File1.exists()) {
					FileOutputStream fos = new FileOutputStream(File1);
					fos.write(formfile.getFileData());
					fos.flush();
					fos.close();
				}
				// 複製到指定位置 (原始位置, 指定位置, ...)
				Files.copy(File1.toPath(), File2.toPath(), StandardCopyOption.REPLACE_EXISTING);
				// 刪除 server 位置圖片
				Files.delete(File1.toPath());
			}
		} catch (Exception e) {
			rm.setStrResult("E");
			rm.setStrErrorMessage("新增失敗!");
			goods.setResultMessage(rm);
		}

		// out put json
		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(goods));
		out.flush();
		out.close();

		return mapping.findForward("goodsView");
	}

	/**
	 * @methodName 商品列表修改
	 * 
	 * @input voGoodModify
	 * 
	 * @return GoodsLIst
	 */
	public ActionForward updateGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbGood = (BackendForm) form;

		voGoodsUpdate voGoods = new voGoodsUpdate();

		ResultMessage rm = new ResultMessage();

		BeanUtils.copyProperties(voGoods, fbGood);

		GoodsUpdate goods = backendService.updateGoods(voGoods);

		String fileName = voGoods.getTxtUpdateGoodsImage().getFileName();
		if (fileName != "") {
			try {
				// 圖片 update ...
				FormFile formfile = voGoods.getTxtUpdateGoodsImage(); // 取得 檔案資訊
				// Get the servers upload directory real path name / server存檔位置
				String filePath = getServlet().getServletContext().getRealPath("/") + "upload";
				// 指定存放位置
				String mainFilePath = getServlet().getInitParameter("MainGoodsImgPath");
				// create the upload folder if not exists
				File folder = new File(filePath);
				if (!folder.exists()) {
					folder.mkdir();
				}
				// 寫入
				if (!("").equals(fileName)) {
					System.out.println("Server path:" + filePath);
					File File1 = new File(filePath, fileName); // 指定位置
					File File2 = new File(mainFilePath, fileName);
					if (!File1.exists()) {
						FileOutputStream fos = new FileOutputStream(File1);
						fos.write(formfile.getFileData());
						fos.flush();
						fos.close();
					}
					// 複製到指定位置 (原始位置, 指定位置, ...)
					Files.copy(File1.toPath(), File2.toPath(), StandardCopyOption.REPLACE_EXISTING);
					// 刪除 server 位置圖片
					Files.delete(File1.toPath());
				}
			} catch (Exception e) {
				rm.setStrResult("E");
				rm.setStrErrorMessage("新增失敗!");
				goods.setResultMessage(rm);
			}
		}

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(goods));
		out.flush();
		out.close();

		return mapping.findForward("goodsView");
	}

	/**
	 * @methodName 商品 刪除
	 * 
	 * @input voGoodDelete
	 * 
	 * @return GoodsDelete
	 */
	public ActionForward deleteGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbGood = (BackendForm) form;

		voGoodsDelete voGoods = new voGoodsDelete();

		BeanUtils.copyProperties(voGoods, fbGood);

		boolean boolResult = backendService.deleteGoods(voGoods);

		ResultMessage rtnResultMessage = new ResultMessage();

		if (boolResult) {
			rtnResultMessage.setStrResult("S");
			rtnResultMessage.setStrMessage("刪除成功!");
		} else {
			rtnResultMessage.setStrResult("ES");
			rtnResultMessage.setStrMessage("刪除失敗!");
		}

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(rtnResultMessage));
		out.flush();
		out.close();

		return mapping.findForward("goodsView");
	}

	/**
	 * @methodName 商品訂單列表
	 * 
	 * @input voGoodDelete
	 * 
	 * @return GoodsDelete
	 */
	public ActionForward queryOrderBetweenDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbGood = (BackendForm) form;

		voGoodsOrder voGoods = new voGoodsOrder();

		BeanUtils.copyProperties(voGoods, fbGood);

		Orders orders = backendService.queryOrderBetweenDate(voGoods);

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(orders));
		out.flush();
		out.close();

		return mapping.findForward("goodssalereportView");
	}

}
