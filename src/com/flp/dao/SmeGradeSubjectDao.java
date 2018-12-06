package com.flp.dao;

import java.util.ArrayList;
import java.util.List;

import com.flp.model.Grade;
import com.flp.model.SmeGradeSubject;
import com.flp.model.Subject;
/**
 * 
 * @author Amresh
 *
 */
public interface SmeGradeSubjectDao extends GenericDao<SmeGradeSubject, Long>
{
	List<Grade> getAssignedGrades(Long sessionId, Long userId, Integer status);
	List<Subject> getAssignedSubjects(Long sessionId, Long userId, Long gradeId, Integer status);
	List<SmeGradeSubject> getAssignedSubjectListToSmeByGradeId(Long smeId, Long gradeId);
	Boolean unassignSubjectsFromSme(ArrayList<Long> smeGradeSubjectIdLst);
	List<SmeGradeSubject> getSmeAssignedGradesAndSubjects(Long userId, Long sessionId, Integer status);
}
