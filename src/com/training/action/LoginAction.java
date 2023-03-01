package com.training.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.training.dao.MemberDao;
import com.training.formbean.MemberForm;
import com.training.model.Member;
import com.training.model.ResultMessage;
import com.training.service.MemberService;

import net.sf.json.JSONArray;

@MultipartConfig
public class LoginAction extends DispatchAction {

	private MemberDao memberDao = MemberDao.getInstance();

	private MemberService memberService = MemberService.getInstance();

	MemberForm fmMember;

	/**
	 * 成功登入預設頁面
	 * 
	 * @return Set(Goods)
	 */
	public ActionForward LoginDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("Default");
	}

	/**
	 * 會員 登出
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 登出請求
		HttpSession session = request.getSession();

		session.removeAttribute("account");

		return mapping.findForward("fail");
	}

	/**
	 * 會員登入
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 登入請求
		ActionForward actFwd = null;

		HttpSession session = request.getSession();

		fmMember = (MemberForm) form;

		Member voMember = new Member();

		BeanUtils.copyProperties(voMember, fmMember);

		// Step2:依使用者所輸入的帳戶名稱取得 Member
		List<Member> listMember = memberDao.queryMember(voMember);
		String loginMsg = "";
		if (listMember != null) {
			// Step3:取得帳戶後進行帳號、密碼比對
			String id = listMember.get(0).getId();
			String pwd = listMember.get(0).getPwd();
			if (id.equals(voMember.getId()) && pwd.equals(voMember.getPwd())) {
				// 將account存入session scope 以供LoginCheckFilter之後使用!
				session.setAttribute("account", listMember.get(0));
				loginMsg = "登入成功!";
				actFwd = mapping.findForward("success");
			} else {
				// Step4:帳號、密碼錯誤,轉向到 "/BankLogin.html" 要求重新登入.
				loginMsg = "帳號或密碼錯誤!";
				actFwd = mapping.findForward("fail");
			}
		} else {
			// Step5:無此帳戶名稱,轉向到 "/BankLogin.html" 要求重新登入.
			loginMsg = "無此帳戶名稱,請重新輸入!";
			actFwd = mapping.findForward("fail");
		}
		request.setAttribute("loginMsg", loginMsg);
		return actFwd;
	}

	/**
	 * 會員新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActionForward MemberCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		boolean boolResult = true;

		String strMessage = "";

		String strFwd = "";

		fmMember = (MemberForm) form;

		Member voMember = new Member();

		BeanUtils.copyProperties(voMember, fmMember);

		ResultMessage ResultMessage = new ResultMessage();

		// 檢核 前端欄位
		if (voMember.getId().equals("") || voMember.getName().equals("") || voMember.getPwd().equals("")
				|| voMember.getPwdCheck().equals("")) {
			strMessage = "欄位不可為空白";
			boolResult = false;
		} else if (!voMember.getPwd().equals(voMember.getPwdCheck())) { // 密碼 double Check
			strMessage = "密碼 & 密碼確認 不相符";
			boolResult = false;
		}
		// 檢核重複 ID
		if (boolResult) {
			List<Member> voMembers = new ArrayList<>();
			voMembers = memberService.queryMember(voMember);
			if (voMembers != null) {
				strMessage = "重複註冊!";
				boolResult = false;
			}
		}
		// 開始新增
		if (boolResult) {
			// 執行資料
			boolResult = memberService.createMember(voMember);
			strMessage = boolResult ? "帳戶資料新增成功！" : "帳戶資料新增失敗！";
			ResultMessage.setStrResult(boolResult ? "S" : "E");
			ResultMessage.setStrMessage(strMessage);
		}

		System.out.println(strMessage);

		strFwd = boolResult ? "login" : "MemberCreate";

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(ResultMessage));
		out.flush();
		out.close();

		return mapping.findForward(strFwd);
	}

}
