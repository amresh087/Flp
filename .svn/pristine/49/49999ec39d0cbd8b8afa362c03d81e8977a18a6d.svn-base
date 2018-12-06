package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.BoardDao;
import com.flp.model.Board;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class BoardDaoImpl extends GenericDaoImpl<Board, Long> implements BoardDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> getBoards(Long ctlsBoardId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String orderString = "ORDER BY id DESC";
		String str = "FROM Board WHERE status = :status "+orderString;
		Query query = session.createQuery(str);
		
		if(ctlsBoardId != null)
		{
			str = str.replace(orderString, "");
			str = str + "AND id != :ctlsBoardId " + orderString;
			query = session.createQuery(str);
			query.setLong("ctlsBoardId", ctlsBoardId);
		}
		
		query.setInteger("status", status);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> getBoardName(Long BoardId)
	{
		Session session = sessionFactory.getCurrentSession();
		
		String str = "FROM Board WHERE id = :id ";
		Query query = session.createQuery(str);
		query.setParameter("id", BoardId);
		
		
		
		return query.list();
	}
	/*@Override
	public boolean checkBoardAvailability(String boardName, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Board WHERE status = :status AND name = :name");
		query.setInteger("status", status);
		query.setString("name", boardName);
		
		return (query.list().size() > 0 ? false : true);
	}*/
	
	@Override
	public boolean checkBoardAvailability(String boardName, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Board WHERE status = :status AND name = :name");
		query.setInteger("status", status);
		query.setString("name", boardName);
		
		return (query.list().size() > 0 ? false : true);
	}

	@Override
	public void updateBoard(String boardIdString, Integer status)
	{
		String[] idList = boardIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Board SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}
}
