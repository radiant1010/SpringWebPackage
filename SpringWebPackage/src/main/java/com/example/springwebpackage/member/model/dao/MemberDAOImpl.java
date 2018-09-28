package com.example.springwebpackage.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springwebpackage.member.controller.MemberController;
import com.example.springwebpackage.member.model.dto.MemberVO;

// 현재 클래스를 DAO bean으로 등록시킴
@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계 주입(Dependency Injection, DI)
	// 느스한 결함
	// IoC(Inversion of Control, 제어의 역전)
	// @Inject
	// Inject애노테이션이 없으면 sqlSession은 null상태이지만
	// Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다.
	// try catch문, finally문, 객체를 close할 필요가 없어졌다.

	@Autowired
	SqlSession sqlSession;

	// 전체 회원 목록 조회
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}

	// 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);
	}

	// 회원 정보 상세 조회
	@Override
	public MemberVO viewMember(String userId) {
		return sqlSession.selectOne("member.viewMember", userId);
	}

	// 회원 정보 수정 처리
	@Override
	public void deleteMember(String userId) {
		sqlSession.delete("member.deleteMember", userId);
	}

	// 회원 정보 삭제 처리
	@Override
	public void updateMember(MemberVO vo) {
		sqlSession.update("member.updateMember", vo);

	}

	// 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	// Controller단에서 체크할 수 있게 비밀번호를 map에 올려둔다.
	@Override
	public boolean checkPw(String userId, String userPw) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		// sqlSession.selectOne => 하나의 객체를 리턴, 즉! 비밀번호가 입력한것과
		// Controller에서 DB에 입력된 것과 일치하면~ 1로 카운트하고 result값을 false에서 true로 바꾼다.
		int count = sqlSession.selectOne("member.checkPw", map);
		if (count == 1)
			result = true;
		return result;
	}

	// 회원 로그인체크
	@Override
	public boolean loginCheck(MemberVO vo) {
		String name = sqlSession.selectOne("member.loginCheck", vo);
		return (name == null) ? false : true;
	}

	// 회원 로그인 정보
	@Override
	public MemberVO loginviewMember(MemberVO vo) {
		return sqlSession.selectOne("member.loginviewMember", vo);
	}

	// 회원 로그아웃
	@Override
	public void logout(HttpSession sessin) {
	}
}
