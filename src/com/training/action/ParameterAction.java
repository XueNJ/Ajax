package com.training.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.formbean.ParameterForm;
import com.training.model.Parameter;
import com.training.service.ParameterService;
import com.training.vo.voParameter;

import net.sf.json.JSONArray;

public class ParameterAction extends DispatchAction {

	private ParameterService parameterService = ParameterService.getInstance();

	private static ParameterForm prParameter;

	/**
	 * 參數列表
	 * 
	 * @return Set(Goods)
	 */
	public ActionForward queuryParameter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		prParameter = (ParameterForm) form;

		voParameter voParameter = new voParameter();

		BeanUtils.copyProperties(voParameter, prParameter);

		List<Parameter> listParameter = parameterService.queryParameter(voParameter);
		
		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(listParameter));
		out.flush();
		out.close();
		
		return mapping.findForward("goodsListView");
	}
}
