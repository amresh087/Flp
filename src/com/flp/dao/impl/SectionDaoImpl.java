package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.SectionDao;
import com.flp.model.Section;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SectionDaoImpl extends GenericDaoImpl<Section, Long> implements SectionDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Section> getSections(String sectionIdString, Long boardId, String sessionSchoolGradeIdString, Integer status, String gradeIdString, String order)
	{
		String[] idList = null;		
		Long[] ids = null;

		String orderString = (order.equalsIgnoreCase("desc") ? "ORDER BY sec.id DESC" : "ORDER BY sec.id ASC");
		Session session = sessionFactory.getCurrentSession();
		String str = "SELECT sec FROM Section AS sec INNER JOIN FETCH sec.grade AS gr WHERE sec.status = :status "+orderString;
		Query query = null;
		
		// Getting section objects on section id basis
		if((sectionIdString != null && !sectionIdString.trim().equals("")) && 
			(sessionSchoolGradeIdString == null || sessionSchoolGradeIdString.trim().equals("")) && 
			(gradeIdString == null || gradeIdString.trim().equals("")) && boardId == null)
		{
			 
			idList = sectionIdString.split(",");
			ids = new Long[idList.length];
			
			for(int i=0;i<idList.length;i++)
			{
				ids[i] = Long.parseLong(idList[i]);
			}
			
			str = str.replace(orderString, "");
			str = str + "AND sec.id IN (:sId) "+orderString;
			
			query = session.createQuery(str);
			query.setParameterList("sId", ids);
		}
		
		// For School-wise : boardId and gradeId will be null	(boardId is extra field)
		// Getting all sections of school(s)
		if((sessionSchoolGradeIdString != null && !sessionSchoolGradeIdString.trim().equals("")) && 
		   (sectionIdString == null || sectionIdString.trim().equals("")) &&
		   (gradeIdString == null || gradeIdString.trim().equals("")) && boardId == null)		
		{
			 
			
			idList = sessionSchoolGradeIdString.split(",");
			ids = new Long[idList.length];
			
			for(int i=0;i<idList.length;i++)
			{
				ids[i] = Long.parseLong(idList[i]);
			}
			
			str = str.replace(orderString, "");
			str = str + "AND sec.sessionSchoolGradeId IN (:ssgId) "+orderString;
			
			query = session.createQuery(str);
			query.setParameterList("ssgId", ids);
		}
		// For User-wise : sessionSchoolGradeId will be null
		// Getting all sections of particular board on grade(s) basis
		else if((gradeIdString != null && !gradeIdString.trim().equals("")) &&  boardId != null &&
				(sectionIdString == null || sectionIdString.trim().equals("")) &&
				(sessionSchoolGradeIdString == null || sessionSchoolGradeIdString.trim().equals("")))		
		{
			 
			
			idList = gradeIdString.split(",");
			ids = new Long[idList.length];
			
			for(int i=0;i<idList.length;i++)
			{
				ids[i] = Long.parseLong(idList[i]);
			}
			
			str = str.replace(orderString, "");
			str = str + "AND sec.boardId = :bId AND gr.id IN (:gId) "+orderString;
			
			query = session.createQuery(str);			
			query.setLong("bId", boardId);
			query.setParameterList("gId", ids);
		}
		// For User-wise : sessionSchoolGradeId will be null
		// Getting all sections of particular board
		else if(boardId != null &&  
				(sectionIdString == null || sectionIdString.trim().equals("")) &&
			    (gradeIdString == null || gradeIdString.trim().equals("")) && 
			    (sessionSchoolGradeIdString == null || sessionSchoolGradeIdString.trim().equals("")))
		{
			
			 
			str = str.replace(orderString, "");
			str = str + "AND sec.boardId = :bId "+orderString;
			
			
			System.out.println("==========================="+str);
			
			
			query = session.createQuery(str);
			query.setLong("bId", boardId);
		}
		
		query.setInteger("status", status);
		
		return query.list();
	}

	@Override
	public List<Long> addSection(Long gradeId, String boardIdString, String sessionSchoolGradeIdString, String sectionName)
	{
		String[] idList = null;		
		ArrayList<Long> sectionIdList = null;

		Session session = sessionFactory.getCurrentSession();
		
		// User-wise (sessionSchoolGradeId will be null)
		if((boardIdString != null && !boardIdString.trim().equals("")) && gradeId != null &&
			(sessionSchoolGradeIdString == null || sessionSchoolGradeIdString.trim().equals("")))
		{
			sectionIdList = new ArrayList<Long>();
			
			idList = boardIdString.split(",");
			
			for(int i=0;i<idList.length;i++)
			{
				Section section = new Section();
				section.setSessionSchoolGradeId(null);
				section.setBoardId(Long.valueOf(idList[i]));
				section.setGradeId(gradeId);
				section.setName(sectionName);
				section.setStatus(Section.STATUS_ACTIVE);
				section.setModifiedDate(new Date());
				
				session.save(section);
				
				sectionIdList.add(section.getId());
			}
		}
		// School-wise (boardId and gradeId will be null)
		else if((sessionSchoolGradeIdString != null && !sessionSchoolGradeIdString.trim().equals("")) &&
				(boardIdString == null || !boardIdString.trim().equals("")) && gradeId == null)
		{
			sectionIdList = new ArrayList<Long>();
			
			idList = boardIdString.split(",");
			
			for(int i=0;i<idList.length;i++)
			{
				Section section = new Section();
				section.setSessionSchoolGradeId(Long.valueOf(idList[i]));
				section.setBoardId(null);
				section.setGradeId(null);
				section.setName(sectionName);
				section.setStatus(Section.STATUS_ACTIVE);
				section.setModifiedDate(new Date());
				
				session.save(section);
				
				sectionIdList.add(section.getId());
			}
		}
		
		return sectionIdList;
	}

	@Override
	public void updateSection(String sectionIdString, Integer status)
	{
		String[] idList = sectionIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Section SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}

	@Override
	public Long getSectionByGradeAndBoardId(Long gradeId, Long boardId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select id from Section where boardId=:boardId and gradeId=:gradeId");
		query.setParameter("boardId", boardId);
		query.setParameter("gradeId", gradeId);
		query.setMaxResults(1);
		return (Long) query.uniqueResult();
		
	}

	@Override
	public Section getSectionObjById(Long secId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("from Section where id=:secId");
		query.setParameter("secId",secId);
		return (Section) query.uniqueResult();
	}
}
