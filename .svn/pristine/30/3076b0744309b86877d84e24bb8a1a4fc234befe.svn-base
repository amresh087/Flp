package com.flp.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.GradeDao;
import com.flp.model.Board;
import com.flp.model.Grade;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class GradeDaoImpl extends GenericDaoImpl<Grade, Long> implements GradeDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getGrades(Long boardId, Long schoolId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Grade WHERE status = :status ";
		Query query = null;
		
		if(boardId != null && schoolId == null)
		{
			str = str + "AND boardId = :bId";
			query = session.createQuery(str);
			query.setLong("bId", boardId);
		}
		else if(schoolId != null && boardId == null)
		{
			str = str + "AND schoolId = :sId";
			query = session.createQuery(str);
			query.setLong("sId", schoolId);
		}
		
		query.setInteger("status", status);
		
		return query.list();
	}

	@Override
	public boolean checkGradeAvailability(String gradeName, Long schoolId, Long boardId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Grade WHERE status = :status AND name = :name ";
		Query query = null;
		
		if(boardId != null && schoolId == null)
		{
			str = str + "AND boardId = :bId";
			query = session.createQuery(str);
			query.setLong("bId", boardId);
		}
		else if(schoolId != null && boardId == null)
		{
			str = str + "AND schoolId = :sId";
			query = session.createQuery(str);
			query.setLong("sId", schoolId);
		}
		
		query.setInteger("status", status);
		query.setString("name", gradeName);
		
		return (query.list().size() > 0 ? false : true);
	}

	@Override
	public void updateGrade(String gradeIdString, Integer status)
	{
		String[] idList = gradeIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Grade SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}

	@Override
	public String updateGrade(HttpServletRequest request, long id)
	{
		Session session = sessionFactory.getCurrentSession();
		Grade grade=(Grade) session.get(Grade.class, Long.parseLong(request.getParameter("id")));
		grade.setName(request.getParameter("newName"));
		session.update(grade);
		return "success";
	}

	@Override
	public String updateBoard(HttpServletRequest request, long id)
	{
		Session session = sessionFactory.getCurrentSession();
		Board board=(Board) session.get(Board.class, Long.parseLong(request.getParameter("id")));
		board.setName(request.getParameter("newName"));
		session.update(board);
		return "success";
	}

	@Override
	public Grade getGradeObjById(Long gradeId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("from Grade where id=:gradeId");
		query.setParameter("gradeId", gradeId);
		return (Grade) query.uniqueResult();
	}
}
