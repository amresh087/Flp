package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flp.dao.SubjectDao;
import com.flp.model.Chapter;
import com.flp.model.DemoGradeTopic;
import com.flp.model.Subject;
import com.flp.model.Subscription;
import com.flp.model.Topic;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SubjectDaoImpl extends GenericDaoImpl<Subject, Long> implements SubjectDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubscribedSubjects(Long boardId, Long gradeId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Subscription WHERE userId = :uId AND boardId = :bId AND gradeId = :gId AND status = :status AND startDate <= :sDate AND expiryDate >= :eDate AND planType != :pType");
		query.setLong("uId", userId);
		query.setLong("bId", boardId);
		query.setLong("gId", gradeId);
		query.setInteger("status", Subscription.STATUS_ACTIVE);
		query.setInteger("pType", Subscription.PLAN_TYPE_DEMO);
		query.setTimestamp("sDate", Utility.getCurrentDate());
		query.setTimestamp("eDate", Utility.getCurrentDate());

		List<Subscription> list = query.list();
		if (list == null || list.size() == 0)
			return null;

		List<Subject> subjectList = null;
		List<Long> subjectids = new ArrayList<Long>();

		if (list.size() > 0)
		{
			for (Subscription sub : list)
			{
				if (sub.getSubjectId().equals(-1l))
				{
					subjectids = new ArrayList<Long>();
					subjectids.add(-1l);
					break;
				}
				else
				{
					subjectids.add(sub.getSubjectId());
				}
			}

			subjectList = getSubjectsWithChapters(boardId, gradeId, subjectids);
		}

		return subjectList;
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getSubjectsWithChapters(Long boardId, Long gradeId, List<Long> subjectIds)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "select distinct(s) FROM Subject s INNER JOIN FETCH s.chapters c WHERE c.boardId = :boardId AND c.gradeId = :gradeId";
		if (!subjectIds.contains(-1l))
		{
			str = str + " AND s.id IN(:subjectIds)";
		}

		Query query = session.createQuery(str);
		query.setLong("boardId", boardId);
		query.setLong("gradeId", gradeId);
		if (!subjectIds.contains(-1l))
		{
			query.setParameterList("subjectIds", subjectIds);
		}

		List<Subject> list = query.list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getDemoSubjects(Long boardId, Long gradeId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM DemoGradeTopic WHERE boardId = :bId AND grade.id = :gId AND status = :status");
		query.setLong("bId", boardId);
		query.setLong("gId", gradeId);
		query.setInteger("status", Subscription.STATUS_ACTIVE);

		List<DemoGradeTopic> list = query.list();
		if (list == null || list.size() == 0)
			return null;

		List<Subject> subjectList = null;
		List<Long> subjectids = new ArrayList<Long>();

		if (list.size() > 0)
		{
			for (DemoGradeTopic dgs : list)
			{
				subjectids.add(dgs.getSubjectId());
			}

			subjectList = getSubjectsWithChapters(boardId, gradeId, subjectids);
		}

		return subjectList;
	}

	@Override
	public List<Subject> getAllSubjectWithChapterAndTopic(Long boardId, Long gradeId)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "select distinct(s) FROM Subject s INNER JOIN FETCH s.chapters c INNER JOIN FETCH c.topics t where s.status=:status and c.boardId=:boardId and c.gradeId=:gradeId";
		Query query = session.createQuery(str);
		query.setParameter("status", Subject.STATUS_ACTIVE);
		query.setParameter("boardId", boardId);
		query.setParameter("gradeId", gradeId);
		List<Subject> list = query.list();

		return list;
	}

	@Override
	public boolean checkSubjectAvailability(Long boardId, Long schoolId, Integer status, String subjectName)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Subject WHERE status =:status AND name =:name ";
		Query query = null;
		query=session.createQuery(str);

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
		
		query.setParameter("status", status);
		query.setParameter("name", subjectName.trim());
		
		return (query.list().size() > 0 ? false : true);
	}

	@Override
	public Subject addSubject(Subject subject)
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(subject);
		
		return subject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjects(Long boardId, Long schoolId, String subjectName, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Subject WHERE status = :status ";
		Query query = null;
		
		if(subjectName != null && !subjectName.trim().equals(""))
		{
			str = str + "AND name = :name ";
		}
		
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
		if(subjectName != null && !subjectName.trim().equals(""))
		{
			query.setString("name", subjectName);
		}
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getDistinctSubjects(Long boardId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Subject sub WHERE boardId = :bId AND sub.status = :status GROUP BY sub.name";
		Query query = session.createQuery(str);
		query.setLong("bId", boardId);
		query.setInteger("status", Subject.STATUS_ACTIVE);
		
		return query.list();
	}

	@Override
	@ResponseBody
	public String updateSubject(HttpServletRequest request, long userId, String swName)
	{
		Session session = sessionFactory.getCurrentSession();
		Subject sub=(Subject) session.get(Subject.class, Long.parseLong(request.getParameter("txtSubjectId")));
		sub.setName(request.getParameter("txtNewSubject"));
		if(swName!=null){
			sub.setSubImage(swName);
		}
		session.update(sub);
		return "success";
	}
	
	
}