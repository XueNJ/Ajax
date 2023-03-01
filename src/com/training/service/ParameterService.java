package com.training.service;

import java.util.List;

import com.training.dao.ParameterDao;
import com.training.model.Parameter;
import com.training.vo.voParameter;

public class ParameterService {

	private static ParameterService parameterService = new ParameterService();

	private ParameterService() {
	}

	public static ParameterService getInstance() {
		return parameterService;
	}
	
	private ParameterDao parameterDao = ParameterDao.getInstance();
	
	/**
	 * 參數 查詢
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Parameter> queryParameter(voParameter voParameter) {

		return parameterDao.queryParameter(voParameter);
	}

}
