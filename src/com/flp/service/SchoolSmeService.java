package com.flp.service;

import java.util.ArrayList;
import java.util.List;

import com.flp.model.Grade;
import com.flp.model.Section;
import com.flp.model.SmeGradeSubject;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;

public interface SchoolSmeService
{

	List<Section> getSectionByGradeId(Long schoolId, Long sessionId, Long gradeId);

	String assignSubjectToGrade(String sectionIdString, Long subjectId);

	List<SubjectSection> getUniqueSubjectsByGradeId(Long gradeId, Long schoolId);

	List<Long> getAssignGradeAndSubjectToSme(Long gradeId, Long smeId,Long sessionId);

	List<Grade> getAssignedGrades(Long sessionId, Long userId, Integer status);

	List<Subject> getAssignedSubjects(Long sessionId, Long userId, Long gradeId, Integer status);

	Boolean assignGradeToSme(String subjectIdString, Long gradeId, Long smeId, Long schoolId, Long sessionId);

	List<SmeGradeSubject> getAssignedSubjectListToSmeByGradeId(Long smeId, Long gradeId);

	Boolean unassignSubjectsFromSme(ArrayList<Long> smeGradeSubjectIdLst);
	
	List<SmeGradeSubject> getSmeAssignedGradesAndSubjects(Long userId, Long sessionId, Integer status);
}
