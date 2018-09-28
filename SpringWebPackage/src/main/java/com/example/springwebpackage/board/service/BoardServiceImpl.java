package com.example.springwebpackage.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springwebpackage.board.model.dao.BoardDAO;
import com.example.springwebpackage.board.model.dto.BoardVO;
import com.example.springwebpackage.member.controller.MemberController;

@Service
public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	BoardDAO boardDao;

	// 게시글쓰기
	@Override
	public void boardCreate(BoardVO vo) throws Exception {
		String title = vo.getTitle();
		String content = vo.getContent();
		String writer = vo.getWriter();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		boardDao.boardCreate(vo);
	}

	// 게시글 상세보기
	@Override
	public BoardVO boardRead(int bno) throws Exception {
		return boardDao.boardRead(bno);
	}

	// 게시글 수정
	@Override
	public void boardUpdate(BoardVO vo) throws Exception {
		boardDao.boardUpdate(vo);
	}

	// 게시글 삭제
	@Override
	public void boardDelete(int bno) throws Exception {
		boardDao.boardDelete(bno);
	}

	// 게시글 전체 목록
	@Override
	public List<BoardVO> boardListAll() throws Exception {
		return boardDao.boardListAll();
	}

	// 게시글 조회수 증가
	@Override
	public void boardCount(int bno, HttpSession session) throws Exception {
		long update_time = 0;
		// 세션에 저장된 조회시간 검색
		// 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
		if (session.getAttribute("update_time_" + bno) != null) {
			// 세션에서 읽어오기
			update_time = (Long)session.getAttribute("update_time_" + bno);
		}
		// 시스템의 현재시간을 current_time에 저장
		long current_time = System.currentTimeMillis();
		// 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
		// 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
		if (current_time - update_time > 5 * 1000) {
			boardDao.boardCount(bno);
			// 세션에 시간을 저장 : "update_time_"+bno는 다른변수와 중복되지 않게 명명한 것
			session.setAttribute("update_time_" + bno, current_time);

		}
	}

}