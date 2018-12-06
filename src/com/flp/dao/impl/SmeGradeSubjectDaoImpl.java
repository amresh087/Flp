package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.SmeGradeSubjectDao;
import com.flp.model.Grade;
import com.flp.model.SmeGradeSubject;
import com.flp.model.Subject;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SmeGradeSubjectDaoImpl extends GenericDaoImpl<SmeGradeSubject, Long> implements SmeGradeSubjectDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getAssignedGrades(Long sessionId, Long userId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SmeGradeSubject sgs INNER JOIN FETCH sgs.grade g WHERE sgs.sessionId = :sId AND sgs.userId = :uId AND sgs.status = :status group by sgs.gradeId");
		query.setLong("sId", sessionId);
		query.setLong("uId", userId);
		query.setInteger("status", status);
		
		List<SmeGradeSubject> list = query.list();
		List<Grade> gradeList = new ArrayList<Grade>();
		
		for(SmeGradeSubject sgs : list)
		{
			gradeList.add(sgs.getGrade());
		}
		
		return gradeList;
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAssignedSubjects(Long sessionId, Long userId, Long gradeId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SmeGradeSubject sgs INNER JOIN FETCH sgs.subject s WHERE sgs.sessionId = :sId AND sgs.userId = :uId AND sgs.gradeId = :gId AND sgs.status = :status");
		query.setLong("sId", sessionId);
		query.setLong("uId", userId);
		query.setLong("gId", gradeId);
		query.setInteger("status", status);
		
		List<SmeGradeSubject> list = query.list();
		List<Subject> subjectList = new ArrayList<Subject>();
		
		for(SmeGradeSubject sgs : list)
		{
			subjectList.add(sgs.getSubject());
		}
		
		return subjectList;
	}

	@Override
	public List<SmeGradeSubject> getAssignedSubjectListToSmeByGradeId(Long smeId, Long gradeId)
	{
		Query query =sessionFactory.getCurrentSession().createQuery("from SmeGradeSubject sgs inner join fetch sgs.subject where sgs.gradeId=:gradeId and sgs.userId=:userId and sgs.status=:status");
		query.setParameter("gradeId", gradeId);
		query.setParameter("userId", smeId);
		query.setParameter("status", SmeGradeSubject.STATUS_ACTIVE);
		List<SmeGradeSubject> lst=query.list();
		return lst;
				
	}

	@Override
	public Boolean unassignSubjectsFromSme(ArrayList<Long> smeGradeSubjectIdLst)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("UPDATE SmeGradeSubject SET status=:status where id in (:ids)");
		query.setParameter("status", SmeGradeSubject.STATUS_INACTIVE);
		query.setParameterList("ids", smeGradeSubjectIdLst);
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmeGradeSubject> getSmeAssignedGradesAndSubjects(Long userId, Long sessionId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SmeGradeSubject sgs INNER JOIN FETCH sgs.grade g  INNER JOIN FETCH sgs.subject sub WHERE sgs.sessionId = :sId AND sgs.userId = :uId AND sgs.status = :status");
		query.setLong("sId", sessionId);
		query.setLong("uId", userId);
		query.setInteger("status", status);
		
		List<SmeGradeSubject> list = query.list();
		
		List<SmeGradeSubject> smeList = new ArrayList<SmeGradeSubject>();
		
		for(SmeGradeSubject sgs : list)
		{
			Grade grade = new Grade();
			grade.setId(sgs.getGradeId());
			grade.setName(sgs.getGrade().getName());
			
			Subject subject = new Subject();
			subject.setId(sgs.getSubjectId());
			subject.setName(sgs.getSubject().getName());
			
			SmeGradeSubject s = new SmeGradeSubject();
			s.setId(sgs.getId());
			s.setGrade(grade);
			s.setSubject(subject);
			
			smeList.add(s);
		}
		
		return smeList;
	}
}
