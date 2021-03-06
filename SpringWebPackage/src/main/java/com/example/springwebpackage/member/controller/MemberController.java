package com.example.springwebpackage.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.springwebpackage.member.model.dto.MemberVO;
import com.example.springwebpackage.member.service.MemberService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// MemberService 객체를 스프링에서 생성하여 주입시킴
	@Autowired
	MemberService memberService;

	// 회원 목록
	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		// controller => service => memberList dao 요청
		List<MemberVO> list = memberService.memberList();
		model.addAttribute("list", list);
		return "member/memberList";
	}

	// 회원 가입 페이지로 이동
	@RequestMapping("member/write.do")
	public String memberWrite() {

		return "member/memberWrite";
	}

	// 회원 등록 처리
	@RequestMapping("member/insert.do")
	// 폼에서 입력한 데이터를 받아오는 법 3가지
	// public String memberInsert(HttpServlet request){
	// public String memberInsert(String userId, String userPw, String userName,
	// String userEmail){
	// 위와 같은 기본적인 방법도 있지만 다음과 같이쓰면 좀더 간편하게 사용가능
	// @ModelAttribute에 폼에서 입력한 데이터가 저장된다.
	public String memberInsert(@ModelAttribute MemberVO vo) {
		// DB테이블에 입력된 값 입력
		memberService.insertMember(vo);
		// * (/)의 유무에 차이
		// /member/list.do : 루트 디렉토리를 기준
		// member/list.do : 현재 디렉토리를 기준
		return "home";
	}

	// 회원 상세정보 조회
	@RequestMapping("member/view.do")
	public String memberView(String userId, Model model) {
		// 회원 정보를 model에 저장
		model.addAttribute("dto", memberService.viewMember(userId));
		logger.info("클릭한 아이디 : " + userId);
		return "member/memberView";
	}

	// 회원 정보 수정 처리
	@RequestMapping("member/update.do")
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model) {
		// 비밀번호 체크
		boolean result = memberService.checkPw(vo.getUserId(), vo.getUserPw());
		// 비밀번호가 일치하면 수정 처리후, 전체 회원 목록으로 리다이렉트
		if (result) {
			memberService.updateMember(vo);
			return "redirect:/member/list.do";
			// 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, member_view페이지로 이동
		} else {
			// 가입일자, 수정일자 저장
			MemberVO vo2 = memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "비밀번호 불일치");
			return "member/memberView";
		}

	}

	// 회원정보 삭제 처리
	@RequestMapping("member/delete.do")
	public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model) {
		// 비밀번호 체크
		boolean result = memberService.checkPw(userId, userPw);
		// 비밀번호가 맞다면 삭제 처리후, 전체 회원 목록으로 리다이렉트
		if (result) {
			memberService.deleteMember(userId);
			return "redirect:/member/list.do";
		} else {
			model.addAttribute("message", "비밀번호 불일치");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/memberView";
		}
	}

	// 로그인 화면
	@RequestMapping("member/login.do")
	public String login() {
		return "member/login"; // views/member/login.jsp로 포워드
	}

	// 로그인 처리
	@RequestMapping("member/loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session) {
		boolean result = memberService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
		if (result == true) { // 로그인 성공
			// main.jsp로 이동
			mav.setViewName("home");
			mav.addObject("msg", "success");
		} else { // 로그인 실패
			// login.jsp로 이동
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		return mav;
	}

	// 로그아웃 처리
	@RequestMapping("member/logout.do")
	public ModelAndView logout(HttpSession session) {
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		mav.addObject("msg", "logout");
		mav.setViewName("home");
		return mav;
	}

}