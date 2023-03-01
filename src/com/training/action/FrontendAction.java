package com.training.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.formbean.MemberForm;
import com.training.model.Good;
import com.training.model.Goods;
import com.training.model.Member;
import com.training.model.ResultMessage;
import com.training.model.ShoppingCarGoods;
import com.training.model.ShoppingCartGoodsInfo;
import com.training.service.FrontendService;
import com.training.vo.voMemberBuy;
import com.training.vo.voMemberSearch;
import com.training.vo.voShoppingCarGoods;
import com.training.vo.voShoppingCartGoodsInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@MultipartConfig
public class FrontendAction extends DispatchAction {

	private FrontendService frontEndDao = FrontendService.getInstance();

	MemberForm fbMember;

	/**
	 * 前臺商品
	 * 
	 * @return view("MemberGoodsListView") / VendingMachine.jsp
	 */
	public ActionForward FrontendGoodsview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("MemberGoodsListView");
	}

	/**
	 * 前臺結帳
	 * 
	 * @return view("MemberGoodsPaymentView") / VendingMachinePayment.jsp
	 */
	public ActionForward FrontendGoodsPayview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("MemberGoodsPaymentView");
	}

	/**
	 * 後臺管理商品列表
	 * 
	 * @return Set(Goods)
	 */
	public ActionForward searchGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbMember = (MemberForm) form;

		voMemberSearch voCustomerSearch = new voMemberSearch();

		BeanUtils.copyProperties(voCustomerSearch, fbMember);

		Goods gds = frontEndDao.searchGoods(voCustomerSearch);

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(gds));
		out.flush();
		out.close();

		return mapping.findForward("MemberGoodsListView");
	}

	/**
	 * 查詢顧客所購買商品資料(價格、庫存)
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public ActionForward buyGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbMember = (MemberForm) form;

		voMemberBuy voMemberBuy = new voMemberBuy();

		BeanUtils.copyProperties(voMemberBuy, fbMember);

		HttpSession session = request.getSession();
		Member objMember = (Member) session.getAttribute("account");

		voMemberBuy.setMemberID(objMember.getId());

		PrintWriter out = response.getWriter();

		// get CartGoods Session
		@SuppressWarnings("unchecked")
		Map<BigDecimal, ShoppingCarGoods> mapCarGoods = (Map<BigDecimal, ShoppingCarGoods>) session
				.getAttribute("carGoods");

		// 判斷 購物車Session 有無商品
		voShoppingCartGoodsInfo voShoppingCartGoodsInfo = new voShoppingCartGoodsInfo();
		int intTotaAmount = 0;
		int intBuyQ = 0;
		int intPay = voMemberBuy.getInputMoney();
		String strID = voMemberBuy.getMemberID();

		ResultMessage _ResultMessage = new ResultMessage();

		Set<voShoppingCarGoods> ShoppingCarGoods = new LinkedHashSet<voShoppingCarGoods>();
		if (mapCarGoods == null) {
			voShoppingCartGoodsInfo.setShoppingCartGoods(ShoppingCarGoods);
			voShoppingCartGoodsInfo.setTotalAmount(0);
			_ResultMessage.setStrResult("E");
			_ResultMessage.setStrErrorMessage("商品目前為:[0]，無需付款。");

		} else {
			// confirm DB Goods
			Set<BigDecimal> goodsIDs = new HashSet<>();
			for (Map.Entry<BigDecimal, ShoppingCarGoods> entry : mapCarGoods.entrySet()) {
				goodsIDs.add(entry.getKey());
			}
			Map<BigDecimal, Good> buyGoods = frontEndDao.queryBuyGoods(goodsIDs);
			// set CartGoods
			for (Entry<BigDecimal, Good> entry : buyGoods.entrySet()) {
				voShoppingCarGoods _ShoppingCarGoods = new voShoppingCarGoods();
				_ShoppingCarGoods.setGoodsID(entry.getValue().getGoodsID());
				_ShoppingCarGoods.setGoodsName(entry.getValue().getGoodsName());
				_ShoppingCarGoods.setGoodsPrice(entry.getValue().getGoodsPrice());
				if (mapCarGoods.containsKey(entry.getValue().getGoodsID())) {
					intBuyQ = mapCarGoods.get(entry.getValue().getGoodsID()).getBuyQuantity();
				}
				_ShoppingCarGoods.setBuyQuantity(intBuyQ);
				ShoppingCarGoods.add(_ShoppingCarGoods);
				intTotaAmount += entry.getValue().getGoodsPrice() * intBuyQ;
			}
			voShoppingCartGoodsInfo.setShoppingCartGoods(ShoppingCarGoods);
			voShoppingCartGoodsInfo.setTotalAmount(intTotaAmount);
		}
		// check pay > totalAmount and rtn message
		boolean boolCheckOrder = false; // 訂單寫入結果 預設失敗
		boolean boolCheckUpdate = false; // 庫存更新結果 預設失敗
		int intMakingChange = 0; // 找零錢、餘額
		if (intTotaAmount == 0) {
			_ResultMessage.setStrResult("0");
			_ResultMessage.setStrMessage("購物車無放入商品，無須付款!");
		} else {
			if (intPay >= intTotaAmount) {
				intMakingChange = intPay - intTotaAmount;
				// build order list
				boolCheckOrder = frontEndDao.batchCreateGoodsOrder(strID, ShoppingCarGoods);
				// update inventory
				if (boolCheckOrder) {
					boolCheckUpdate = frontEndDao.batchUpdateGoodsQuantity(ShoppingCarGoods);
				}
				// set Message
				if (boolCheckOrder && boolCheckUpdate) {
					// remove CartGoods Session
					session.removeAttribute("carGoods");
					// // rtn message
					_ResultMessage.setStrResult("S");
					_ResultMessage.setStrMessage("付款成功！找零:" + intMakingChange);
				} else {
					_ResultMessage.setStrResult("E");
					_ResultMessage.setStrMessage("建立訂單失敗！付款失敗。");
				}
			} else {
				// rtn message
				_ResultMessage.setStrResult("E");
				_ResultMessage.setStrErrorMessage(
						"商品總金額:" + intTotaAmount + "，付款金額:" + intPay + "。[不足:" + (intTotaAmount - intPay) + "]");
			}
		}
		// out
		out.println(JSONObject.fromObject(_ResultMessage));
		out.flush();
		out.close();

		return mapping.findForward("MemberGoodsListView");
	}

	/**
	 * 查詢購物車
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public ActionForward queryCartGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		// 取得 購物車Session
		@SuppressWarnings("unchecked")
		Map<BigDecimal, ShoppingCarGoods> mapCarGoods = (Map<BigDecimal, ShoppingCarGoods>) session
				.getAttribute("carGoods");
		ShoppingCartGoodsInfo voShoppingCartGoodsInfo = new ShoppingCartGoodsInfo();
		// 判斷 購物車Session 有無商品
		int intTotaAmount = 0;
		int intBuyQ = 0;
		Set<ShoppingCarGoods> setShoppingCarGoods = new LinkedHashSet<ShoppingCarGoods>();
		if (mapCarGoods == null) {
			voShoppingCartGoodsInfo.setShoppingCartGoods(setShoppingCarGoods);
			voShoppingCartGoodsInfo.setTotalAmount(0);
		} else {
			// confirm DB Goods
			Set<BigDecimal> goodsIDs = new HashSet<>();
			for (Map.Entry<BigDecimal, ShoppingCarGoods> entry : mapCarGoods.entrySet()) {
				goodsIDs.add(entry.getKey());
			}
			Map<BigDecimal, Good> buyGoods = frontEndDao.queryBuyGoods(goodsIDs);
			// set CartGoods
			for (Entry<BigDecimal, Good> entry : buyGoods.entrySet()) {
				ShoppingCarGoods _ShoppingCarGoods = new ShoppingCarGoods();
				_ShoppingCarGoods.setGoodsID(entry.getValue().getGoodsID());
				_ShoppingCarGoods.setGoodsName(entry.getValue().getGoodsName());
				_ShoppingCarGoods.setGoodsPrice(entry.getValue().getGoodsPrice());
				if (mapCarGoods.containsKey(entry.getValue().getGoodsID())) {
					intBuyQ = mapCarGoods.get(entry.getValue().getGoodsID()).getBuyQuantity();
				}
				_ShoppingCarGoods.setBuyQuantity(intBuyQ);
				setShoppingCarGoods.add(_ShoppingCarGoods);
				intTotaAmount += entry.getValue().getGoodsPrice() * intBuyQ;
			}
			voShoppingCartGoodsInfo.setShoppingCartGoods(setShoppingCarGoods);
			voShoppingCartGoodsInfo.setTotalAmount(intTotaAmount);
		}
		out.println(JSONObject.fromObject(voShoppingCartGoodsInfo));
		out.flush();
		out.close();

		return mapping.findForward("MemberGoodsListView");
	}

	/**
	 * 加入購物車
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public ActionForward addCartGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		boolean boolCheckGoods = true;

		fbMember = (MemberForm) form;

		ShoppingCarGoods voShoppingCarGoods = new ShoppingCarGoods();

		BeanUtils.copyProperties(voShoppingCarGoods, fbMember);

		HttpSession session = request.getSession();
		// 查詢商品明細
		Set<BigDecimal> goodsIDs = new HashSet<>();
		goodsIDs.add(voShoppingCarGoods.getGoodsID());
		Map<BigDecimal, Good> buyGoods = frontEndDao.queryBuyGoods(goodsIDs);

		voShoppingCarGoods.setGoodsName(buyGoods.get(voShoppingCarGoods.getGoodsID()).getGoodsName());
		voShoppingCarGoods.setGoodsPrice(buyGoods.get(voShoppingCarGoods.getGoodsID()).getGoodsPrice());
		// 取得 購物車Session。Map(key=GoodID/value=MemberBuy);
		@SuppressWarnings("unchecked")
		Map<BigDecimal, ShoppingCarGoods> SessionCarGoods = (Map<BigDecimal, ShoppingCarGoods>) session
				.getAttribute("carGoods"); // for
		// 產品狀態確認
		PrintWriter out = response.getWriter();

		if (SessionCarGoods != null) {

			if (SessionCarGoods.containsKey(voShoppingCarGoods.getGoodsID())) {
				// 商品數量
				voShoppingCarGoods.setBuyQuantity(SessionCarGoods.get(voShoppingCarGoods.getGoodsID()).getBuyQuantity()
						+ voShoppingCarGoods.getBuyQuantity());
				// 判斷購買數量 是否小於 庫存
				if (voShoppingCarGoods.getBuyQuantity() > buyGoods.get(voShoppingCarGoods.getGoodsID())
						.getGoodsQuantity()) {
					boolCheckGoods = false;
				} else {
					// 商品ID
					SessionCarGoods.replace(voShoppingCarGoods.getGoodsID(), voShoppingCarGoods);
				}
			} else {
				// 判斷購買數量 是否小於 庫存
				if (voShoppingCarGoods.getBuyQuantity() > buyGoods.get(voShoppingCarGoods.getGoodsID())
						.getGoodsQuantity()) {
					boolCheckGoods = false;
				} else {
					// 商品ID
					SessionCarGoods.put(voShoppingCarGoods.getGoodsID(), voShoppingCarGoods);
				}
			}
		} else {
			if (voShoppingCarGoods.getBuyQuantity() > buyGoods.get(voShoppingCarGoods.getGoodsID())
					.getGoodsQuantity()) {
				boolCheckGoods = false;
			} else {
				SessionCarGoods = new HashMap<BigDecimal, ShoppingCarGoods>();
				SessionCarGoods.put(voShoppingCarGoods.getGoodsID(), voShoppingCarGoods);
				session.setAttribute("carGoods", SessionCarGoods);
			}
		}
		String args1 = JSONArray.fromObject(voShoppingCarGoods).toString();
		// 回傳庫存不足結果
		ResultMessage rtnResultMessage = new ResultMessage();
		String args2 = "";
		if (!boolCheckGoods) {
			rtnResultMessage.setStrResult("E");
			rtnResultMessage.setStrMessage("庫存不足!");
			args2 = JSONArray.fromObject(rtnResultMessage).toString();
		} else {
			rtnResultMessage.setStrResult("S");
			rtnResultMessage.setStrMessage("加入購物車成功!");
		}
		args2 = JSONArray.fromObject(rtnResultMessage).toString();
		out.println(JSONArray.fromObject("[" + args2 + "," + args1 + "]"));
		out.flush();
		out.close();

		return mapping.findForward("MemberGoodsListView");
	}

	/**
	 * 查詢購物車
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public ActionForward removeCartGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		fbMember = (MemberForm) form;

		ShoppingCarGoods voShoppingCarGoods = new ShoppingCarGoods();

		BeanUtils.copyProperties(voShoppingCarGoods, fbMember);

		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		// 取得 購物車Session
		@SuppressWarnings("unchecked")
		Map<BigDecimal, ShoppingCarGoods> mapCarGoods = (Map<BigDecimal, ShoppingCarGoods>) session
				.getAttribute("carGoods");
		ShoppingCartGoodsInfo voShoppingCartGoodsInfo = new ShoppingCartGoodsInfo();
		// 判斷 購物車Session 有無商品
		int intTotaAmount = 0;
		Set<ShoppingCarGoods> setShoppingCarGoods = new LinkedHashSet<ShoppingCarGoods>();
		if (mapCarGoods == null) {
			voShoppingCartGoodsInfo.setShoppingCartGoods(setShoppingCarGoods);
			voShoppingCartGoodsInfo.setTotalAmount(intTotaAmount);
		} else {
			// 移除 商品
			mapCarGoods.remove(voShoppingCarGoods.getGoodsID());
			// 重新加總
			for (Map.Entry<BigDecimal, ShoppingCarGoods> entry : mapCarGoods.entrySet()) {
				setShoppingCarGoods.add(entry.getValue());
				intTotaAmount += entry.getValue().getGoodsPrice() * entry.getValue().getBuyQuantity();
			}
			voShoppingCartGoodsInfo.setShoppingCartGoods(setShoppingCarGoods);
			voShoppingCartGoodsInfo.setTotalAmount(intTotaAmount);
		}
		out.println(JSONObject.fromObject(voShoppingCartGoodsInfo));
		out.flush();
		out.close();

		return mapping.findForward("MemberGoodsListView");
	}

	/**
	 * 清空購物車
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public ActionForward clearCartGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 清空購物車
		HttpSession session = request.getSession();

		ResultMessage rtnResultMessage = new ResultMessage();
		// 取得 購物車Session
		@SuppressWarnings("unchecked")
		Map<BigDecimal, ShoppingCarGoods> mapCarGoods = (Map<BigDecimal, ShoppingCarGoods>) session
				.getAttribute("carGoods");
		if (mapCarGoods != null) {
			try {
				session.removeAttribute("carGoods");
				rtnResultMessage.setStrResult("S");
				rtnResultMessage.setStrMessage("購物車清除成功!");
			} catch (Exception ex) {
				rtnResultMessage.setStrResult("E");
				rtnResultMessage.setStrMessage("購物車清除成功!");
			}
		} else {
			rtnResultMessage.setStrResult("S");
			rtnResultMessage.setStrMessage("目前購物車商品數量[0]。");
		}

		PrintWriter out = response.getWriter();
		out.println(JSONArray.fromObject(rtnResultMessage));
		out.flush();
		out.close();

		return mapping.findForward("MemberGoodsListView");
	}

}
