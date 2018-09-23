package com.example.springwebpackage.member.service;

import java.util.List;

import com.example.springwebpackage.member.model.dto.MemberVO;

public interface MemberService {
	// 회원 목록
	public List<MemberVO> memberList();

	// 회원 입력
	public void insertMember(MemberVO vo);

	// 회원 정보 상세보기
	public MemberVO viewMember(String userId);

	// 회원삭제
	public void deleteMember(String userId);

	// 회원정보 수정
	public void updateMember(MemberVO vo);

	public boolean checkPw(String userId, String userPw);

}