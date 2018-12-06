package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.SubjectSectionDao;
import com.flp.model.SchoolSession;
import com.flp.model.SchoolStudentTest;
import com.flp.model.SchoolStudentTestDetail;
import com.flp.model.Section;
import com.flp.model.SmeGradeSubject;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SubjectSectionDaoImpl extends GenericDaoImpl<SubjectSection, Long> implements SubjectSectionDao
{
	@Override
	public List<Long> addSubjectSectionMapping(Subject subject, List<Section> sectionList)
	{
		Session session = sessionFactory.getCurrentSession();
		List<Long> idList = new ArrayList<Long>();
		
		for(Section section : sectionList)
		{
			SubjectSection subjectSection = new SubjectSection();
			subjectSection.setModifiedDate(new Date());
			subjectSection.setSection(section);
			subjectSection.setSubject(subject);
			subjectSection.setStatus(SubjectSection.STATUS_ACTIVE);
			
			session.saveOrUpdate(subjectSection);
			idList.add(subjectSection.getId());
		}
		
		return idList;
	}

	@Override
	public void updateSubjectSectionMapping(String subjectSectionIdString, Integer status)
	{
		String[] idList = subjectSectionIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE SubjectSection SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectSection> getSubjectSections(String sectionIdString, Integer status)
	{
		String[] idList = sectionIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT ss FROM SubjectSection ss INNER JOIN FETCH ss.subject sub INNER JOIN FETCH ss.section sec LEFT JOIN FETCH sec.grade gr WHERE ss.status = :status AND ss.section.id IN (:ids) ORDER BY ss.id DESC");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		return query.list();
	}

	@Override
	public List<Long> addSubjectSectionMapping(Long subjectId, String sectionIdString)
	{
		String[] idList = sectionIdString.split(",");		
		List<Long> subjectSectionIdList = new ArrayList<Long>();
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SubjectSection WHERE subjectId = :subId AND sectionId = :secId AND status = :status");
		query.setInteger("status", SubjectSection.STATUS_ACTIVE);
		
		for(int i=0; i<idList.length; i++)
		{
			query.setLong("subId", subjectId);
			query.setLong("secId", Long.parseLong(idList[i]));
			
			if(query.list().size() == 0)				// Don't add mapping if mapping already exists
			{
				SubjectSection ss = new SubjectSection();
				ss.setSubjectId(subjectId);
				ss.setSectionId(Long.parseLong(idList[i]));
				ss.setModifiedDate(new Date());
				ss.setStatus(SubjectSection.STATUS_ACTIVE);
				
				session.saveOrUpdate(ss);
				
				subjectSectionIdList.add(ss.getId());
			}
		}

		return subjectSectionIdList;
	}

	@Override
	public long saveTest(SchoolStudentTest test) {
	    return (Long) sessionFactory.getCurrentSession().save(test);
	}

	@Override
	public void saveTestDetail(SchoolStudentTestDetail testDetail) {
		sessionFactory.getCurrentSession().save(testDetail);
		
	}

	@Override
	public List<Section> getSectionByGradeId(Long schoolId, Long sessionId, Long gradeId)
	{
		//from Section s inner join fetch s.sessionSchoolGrade ssg where ssg.schoolId=:schoolId and ssg.gradeId=:gradeId and ssg.sessionId=:sessionId and s.gradeId is null and s.status=:status
		//from Section s inner join fetch s.sessionSchoolGrade ssg where ssg.schoolId=:schoolId and ssg.gradeId=:gradeId and ssg.sessionId=:sessionId and s.gradeId=:gradeId and s.status=:status
		//Query query = sessionFactory.getCurrentSession().createQuery("from Section s inner join fetch s.sessionSchoolGrade ssg where ssg.schoolId=:schoolId and ssg.gradeId=:gradeId and ssg.sessionId=:sessionId and s.status=:status");
		Query query = sessionFactory.getCurrentSession().createQuery("from Section s inner join fetch s.sessionSchoolGrade ssg where ssg.schoolId=:schoolId and ssg.gradeId=:gradeId and ssg.sessionId=:sessionId and s.gradeId is null and s.status=:status");
		query.setParameter("schoolId", schoolId);
		query.setParameter("gradeId", gradeId);
		query.setParameter("sessionId", sessionId);
		query.setParameter("status", SchoolSession.STATUS_ACTIVE);
		List<Section> sectionList = query.list();
		return sectionList;

		
	
	}

	@Override
	public List<SubjectSection> getUniqueSubjectsByGradeId(Long gradeId,Long schoolId)
	{
		   Query query=sessionFactory.getCurrentSession().createQuery("select distinct(ss) from SubjectSection ss inner join fetch ss.subject  inner join fetch ss.section s inner join fetch s.sessionSchoolGrade ssg where ssg.gradeId=:gradeId and ssg.status=:status and ssg.schoolId=:schoolId and ss.status=:status and s.gradeId is null group by ss.subjectId");
	        query.setParameter("gradeId", gradeId);
	        query.setParameter("schoolId", schoolId);
	        query.setParameter("status", SubjectSection.STATUS_ACTIVE);
	        List<SubjectSection> lst = query.list();
	        return lst;
	}

	@Override
	public List<Long> getAssignGradeAndSubjectToSme(Long gradeId, Long userId,Long sessionId)
	{
		
		String str1="select sgs.subjectId from SmeGradeSubject sgs where sgs.userId=:userId and sgs.gradeId=:gradeId and sgs.status=:status  and sgs.sessionId=:sessionId";
		Query query=sessionFactory.getCurrentSession().createQuery(str1);
        query.setParameter("gradeId", gradeId);
        query.setParameter("userId", userId);
        query.setParameter("sessionId", sessionId);
        query.setParameter("status", SmeGradeSubject.STATUS_ACTIVE);
        List<Long> lst = query.list();
        return lst;
	}

	@Override
	public boolean checkMappingAvailability(Long sectionId, Long subjectId, Integer status)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From SubjectSection WHERE sectionId = :secId AND subjectId = :subId AND status = :status");
		query.setLong("secId", sectionId);
		query.setLong("subId", subjectId);
		query.setInteger("status", status);
		
		return (query.list().size() == 0 ? true : false);
	}

	@Override
	public Map<Long, String> getSubjectImgBySubjectIds(List<Long> newSubjectIds)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select subImage,id from Subject where id in(:subjectIds) and subImage is not null");
		query.setParameterList("subjectIds", newSubjectIds);
		List<Object[]> lst=query.list();
		if(!lst.isEmpty()){
			Map<Long, String> map=new HashMap<Long,String>();
			for(Object[] o:lst){
				map.put(Long.parseLong(o[1].toString()), o[0].toString());
			}
			return map;
		}
		return null;
	}
}
