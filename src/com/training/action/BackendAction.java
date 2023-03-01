package com.training.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.formbean.GoodForm;
import com.training.model.Goods;
import com.training.model.GoodsAdd;
import com.training.model.GoodsUpdate;
import com.training.model.Orders;
import com.training.service.BackendService;
import com.training.vo.voGoods;
import com.training.vo.voGoodsAdd;
import com.training.vo.voGoodsOrder;
import com.training.vo.voGoodsUpdate;

import net.sf.json.JSONArray;

public class BackendAction extends DispatchAction {

	private BackendService backendService = BackendService.getInstance();

	private static GoodForm fbGood;
	
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

		fbGood = (GoodForm) form;

		voGoods voGoods = new voGoods();

		BeanUtils.copyProperties(voGoods, fbGood);

		Goods goods = backendService.queryGoods(voGoods);

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

		fbGood = (GoodForm) form;

		voGoodsAdd voGoods = new voGoodsAdd();

		BeanUtils.copyProperties(voGoods, fbGood);

		GoodsAdd goods = backendService.createGoods(voGoods);

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(goods));
		out.flush();
		out.close();

		return mapping.findForward("goodsCreateView");
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

		fbGood = (GoodForm) form;

		voGoodsUpdate voGoods = new voGoodsUpdate();

		BeanUtils.copyProperties(voGoods, fbGood);

		GoodsUpdate goods = backendService.updateGoods(voGoods);

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(goods));
		out.flush();
		out.close();

		return mapping.findForward("goodsUpdateView");
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

		fbGood = (GoodForm) form;

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
