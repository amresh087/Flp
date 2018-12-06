package com.flp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.SmeGradeSubjectDao;
import com.flp.dao.SubjectSectionDao;
import com.flp.model.Grade;
import com.flp.model.Section;
import com.flp.model.SmeGradeSubject;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.service.SchoolSmeService;

@Service
@Transactional
public class SchoolSmeServiceImpl implements SchoolSmeService
{
	@Autowired
	private SubjectSectionDao subjectSectionDao;

	@Autowired
	SmeGradeSubjectDao smeGradeSubjectDao;

	@Override
	@Transactional
	public List<Section> getSectionByGradeId(Long schoolId, Long sessionId, Long gradeId)
	{

		return subjectSectionDao.getSectionByGradeId(schoolId, sessionId, gradeId);
		//
	}

	@Override
	@Transactional
	public String assignSubjectToGrade(String sectionIdString, Long subjectId)
	{
		String[] sectionIds = sectionIdString.split(",");
		SubjectSection subjectSection;
		try
		{
			for (int i = 0; i < sectionIds.length; i++)
			{
				subjectSection = new SubjectSection();
				subjectSection.setInsertDate(new Date());
				subjectSection.setModifiedDate(new Date());
				subjectSection.setSubjectId(subjectId);
				subjectSection.setSectionId(Long.parseLong(sectionIds[i]));
				subjectSection.setStatus(subjectSection.STATUS_ACTIVE);
				subjectSectionDao.saveOrUpdateEntity(subjectSection);
			}

			return "success";
		}
		catch (Exception e)
		{
			return "error";
		}

	}

	@Override
	@Transactional
	public List<SubjectSection> getUniqueSubjectsByGradeId(Long gradeId, Long schoolId)
	{
		return subjectSectionDao.getUniqueSubjectsByGradeId(gradeId, schoolId);
	}

	@Override
	public List<Long> getAssignGradeAndSubjectToSme(Long gradeId, Long smeId, Long sessionId)
	{
		// TODO Auto-generated method stub
		return subjectSectionDao.getAssignGradeAndSubjectToSme(gradeId, smeId, sessionId);
	}

	@Transactional
	@Override
	public List<Grade> getAssignedGrades(Long sessionId, Long userId, Integer status)
	{
		return smeGradeSubjectDao.getAssignedGrades(sessionId, userId, status);
	}

	@Transactional
	@Override
	public List<Subject> getAssignedSubjects(Long sessionId, Long userId, Long gradeId, Integer status)
	{
		return smeGradeSubjectDao.getAssignedSubjects(sessionId, userId, gradeId, status);
	}

	@Transactional
	@Override
	public Boolean assignGradeToSme(String subjectIdString, Long gradeId, Long smeId, Long schoolId, Long sessionId)
	{
		String[] subjectIds = subjectIdString.split(",");
		SmeGradeSubject smeGradeSubject;
		try
		{
		
			for (int i = 0; i < subjectIds.length; i++)
			{
				smeGradeSubject = new SmeGradeSubject();
				smeGradeSubject.setInsertDate(new Date());
				smeGradeSubject.setModifiedDate(new Date());
				smeGradeSubject.setSubjectId(Long.parseLong(subjectIds[i]));
				smeGradeSubject.setSchoolId(schoolId);
				smeGradeSubject.setStatus(SmeGradeSubject.STATUS_ACTIVE);
				smeGradeSubject.setGradeId(gradeId);
				smeGradeSubject.setSessionId(sessionId);
				smeGradeSubject.setUserId(smeId);

				smeGradeSubjectDao.saveOrUpdateEntity(smeGradeSubject);
			}
			return true;

		}
		catch (Exception e)
		{
			return false;
		}
		
	}

	@Override
	@Transactional
	public List<SmeGradeSubject> getAssignedSubjectListToSmeByGradeId(Long smeId, Long gradeId)
	{
		return smeGradeSubjectDao.getAssignedSubjectListToSmeByGradeId(smeId, gradeId);
	}

	@Override
	@Transactional
	public Boolean unassignSubjectsFromSme(ArrayList<Long> smeGradeSubjectIdLst)
	{
		return smeGradeSubjectDao.unassignSubjectsFromSme(smeGradeSubjectIdLst);
	}	


	@Transactional
	@Override
	public List<SmeGradeSubject> getSmeAssignedGradesAndSubjects(Long userId, Long sessionId, Integer status)
	{
		return smeGradeSubjectDao.getSmeAssignedGradesAndSubjects(userId, sessionId, status);
	}
}
