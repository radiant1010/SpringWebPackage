package com.example.springwebpackage.member.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.springwebpackage.member.controller.MemberController;
import com.example.springwebpackage.member.model.dao.MemberDAOImpl;
import com.example.springwebpackage.member.model.dto.MemberVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
    @Inject
	MemberDAOImpl memberDao;

	// 01. 전체 회원 목록 조회
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}

	// 02. 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	// 03. 회원 정보 상세 조회
	@Override
	public MemberVO viewMember(String userId) {
		return memberDao.viewMember(userId);
	}

	   // 04. 회원 정보 수정 처리
    @Override
    public void deleteMember(String userId) {
        memberDao.deleteMember(userId);
    }
    // 05. 회원 정보 삭제 처리
    @Override
    public void updateMember(MemberVO vo) {
        memberDao.updateMember(vo);
    }
    // 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
    @Override
    public boolean checkPw(String userId, String userPw) {
        return memberDao.checkPw(userId, userPw);
    }
    
 // 01_01. 회원 로그인체크
    @Override
    public boolean loginCheck(MemberVO vo, HttpSession session) {
        boolean result = memberDao.loginCheck(vo);
        if (result) { // true일 경우 세션에 등록
            MemberVO vo2 = loginviewMember(vo);
            // 세션 변수 등록
            session.setAttribute("userId", vo2.getUserId());
            session.setAttribute("userName", vo2.getUserName());
        } 
        return result;
    }
    // 01_02. 회원 로그인 정보
    @Override
    public MemberVO loginviewMember(MemberVO vo) {
        return memberDao.loginviewMember(vo);
    }
    // 02. 회원 로그아웃
    @Override
    public void logout(HttpSession session) {
        // 세션 변수 개별 삭제
        // session.removeAttribute("userId");
        // 세션 정보를 초기화 시킴
        session.invalidate();
    }

}