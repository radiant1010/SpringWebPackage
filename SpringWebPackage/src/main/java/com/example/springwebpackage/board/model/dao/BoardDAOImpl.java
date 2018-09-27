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

	// 01. 게시글 작성
	@Override
	public void create(BoardVO vo) throws Exception {
		SqlSession.insert("board.insert", vo);
	}

	// 02. 게시글 상세보기
	@Override
	public BoardVO read(int bno) throws Exception {
		return SqlSession.selectOne("board.view", bno);
	}

	// 03. 게시글 수정
	@Override
	public void update(BoardVO vo) throws Exception {
		SqlSession.update("board.updateArticle", vo);

	}

	// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		SqlSession.delete("board.deleteArticle", bno);

	}

	// 05. 게시글 전체 목록
	@Override
	public List<BoardVO> listAll() throws Exception {
		return SqlSession.selectList("board.listAll");
	}

	// 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		SqlSession.update("board.increaseViewcnt", bno);
	}

}
