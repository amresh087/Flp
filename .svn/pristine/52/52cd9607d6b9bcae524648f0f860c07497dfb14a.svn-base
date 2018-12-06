package com.flp.dao;

import java.util.List;

import com.flp.model.Board;
/**
 * 
 * @author Amresh
 *
 */
public interface BoardDao extends GenericDao<Board, Long>
{
	List<Board> getBoards(Long ctlsBoardId, Integer status);
	List<Board> getBoardName(Long BoardId);
	boolean checkBoardAvailability(String boardName, Integer status);
	
	void updateBoard(String boardIdString, Integer status);
}
