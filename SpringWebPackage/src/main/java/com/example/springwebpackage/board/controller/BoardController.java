package com.example.springwebpackage.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.springwebpackage.board.model.dto.BoardVO;
import com.example.springwebpackage.board.service.BoardService;
import com.example.springwebpackage.member.controller.MemberController;

@Controller    // 현재 클래스를 컨트롤러 빈(bean)으로 등록
@RequestMapping("/board/*")
public class BoardController {
    
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);


    // 의존관계 주입 => BoardServiceImpl 생성
    @Autowired
    BoardService boardService;
    
    //  게시글 목록
    @RequestMapping("boardList.do")
    public ModelAndView boardList() throws Exception{
    	
    	logger.debug("boardList.do");

        List<BoardVO> list = boardService.boardListAll();
        // ModelAndView - 모델과 뷰
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/boardList"); // 뷰를 list.jsp로 설정
        mv.addObject("boardList", list); // 데이터를 저장
        return mv; // boardList.jsp로 List가 전달된다.
    }
    
    // 게시글 작성화면
    // value="", method="전송방식"
    @RequestMapping(value="boardWrite.do", method=RequestMethod.GET)
    public String boardWrite(){
    	logger.debug("/member/selectMember.do");

        return "board/boardWrite";
    }
    
    // 게시글 작성처리
    @RequestMapping(value="boardInsert.do", method=RequestMethod.POST)
    public String boardInsert(@ModelAttribute BoardVO vo) throws Exception{
        boardService.boardCreate(vo);
        return "redirect:boardList.do";
    }
    
    // 게시글 상세내용 조회, 게시글 조회수 증가 처리
    // @RequestParam : get/post방식으로 전달된 변수 1개
    // HttpSession 세션객체
    @RequestMapping(value="boardView.do", method=RequestMethod.GET)
    public ModelAndView boardView(@RequestParam int bno, HttpSession session) throws Exception{
        // 조회수 증가 처리
        boardService.boardCount(bno, session);
        // 모델(데이터)+뷰(화면)를 함께 전달하는 객체
        ModelAndView mv = new ModelAndView();
        // 뷰의 이름
        mv.setViewName("board/boardView");
        // 뷰에 전달할 데이터
        mv.addObject("dto", boardService.boardRead(bno));
        return mv;
    }
    
    // 게시글 수정
    // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
    @RequestMapping(value="boardUpdate.do", method=RequestMethod.POST)
    public String boardUpdate(@ModelAttribute BoardVO vo) throws Exception{
        boardService.boardUpdate(vo);
        return "redirect:boardList.do";
    }
    
    // 게시글 삭제
    @RequestMapping("boardDelete.do")
    public String boardDelete(@RequestParam int bno) throws Exception{
        boardService.boardDelete(bno);
        return "redirect:boardList.do";
    }
    
}
 
