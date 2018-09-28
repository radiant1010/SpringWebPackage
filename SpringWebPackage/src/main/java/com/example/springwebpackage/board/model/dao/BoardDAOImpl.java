package com.example.springwebpackage.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springwebpackage.board.model.dto.BoardVO;
import com.example.springwebpackage.member.controller.MemberController;

@Repository // 현재 클래스를 dao bean으로 등록
public class BoardDAOImpl implements BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	SqlSession SqlSession;

	// 게시글 작성
	@Override
	public void boardCreate(BoardVO vo) throws Exception {
		SqlSession.insert("board.boardInsert", vo);
	}

	// 게시글 상세보기
	@Override
	public BoardVO boardRead(int bno) throws Exception {
		return SqlSession.selectOne("board.boardView", bno);
	}

	// 게시글 수정
	@Override
	public void boardUpdate(BoardVO vo) throws Exception {
		SqlSession.update("board.updateArticle", vo);

	}

	// 게시글 삭제
	@Override
	public void boardDelete(int bno) throws Exception {
		SqlSession.delete("board.deleteArticle", bno);

	}

	// 게시글 전체 목록
	@Override
	public List<BoardVO> boardListAll() throws Exception {
		return SqlSession.selectList("board.boardListAll");
	}

	// 게시글 조회수 증가
	@Override
	public void boardCount(int bno) throws Exception {
		SqlSession.update("board.boardCount", bno);
	}

}
