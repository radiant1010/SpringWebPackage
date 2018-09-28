package com.example.springwebpackage.board.model.dao;

import java.util.List;

import com.example.springwebpackage.board.model.dto.BoardVO;

public interface BoardDAO {
	// 게시글 작성
	public void boardCreate(BoardVO vo) throws Exception;

	// 게시글 상세보기
	public BoardVO boardRead(int bno) throws Exception;

	// 게시글 수정
	public void boardUpdate(BoardVO vo) throws Exception;

	// 게시글 삭제
	public void boardDelete(int bno) throws Exception;

	// 게시글 전체 목록
	public List<BoardVO> boardListAll() throws Exception;

	// 게시글 조회 증가
	public void boardCount(int bno) throws Exception;
}
