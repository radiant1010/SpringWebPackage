package com.example.springwebpackage.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.springwebpackage.board.model.dto.BoardVO;

public interface BoardService {
	// 01. 게시글 작성
	public void boardCreate(BoardVO vo) throws Exception;

	// 02. 게시글 상세보기
	public BoardVO boardRead(int bno) throws Exception;

	// 03. 게시글 수정
	public void boardUpdate(BoardVO vo) throws Exception;

	// 04. 게시글 삭제
	public void boardDelete(int bno) throws Exception;

	// 05. 게시글 전체 목록
	public List<BoardVO> boardListAll() throws Exception;

	// 06. 게시글 조회
	public void boardCount(int bno, HttpSession session) throws Exception;
}
