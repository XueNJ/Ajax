package com.training.service;

import java.util.List;

import com.training.dao.MemberDao;
import com.training.model.Member;

public class MemberService {

	private static MemberService bankService = new MemberService();

	private MemberService() {
	}

	public static MemberService getInstance() {
		return bankService;
	}
	
	private MemberDao mamberDao = MemberDao.getInstance();
	
	/**
	 * 會員登入 查詢
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Member> queryMember(Member voMember) {

		return mamberDao.queryMember(voMember);
	}
	
	/**
	 * 會員註冊
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean createMember(Member voMember) {
		
		return mamberDao.createMember(voMember);
	}

}
