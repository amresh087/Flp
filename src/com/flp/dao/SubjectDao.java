package com.flp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Subject;
/**
 * 
 * @author Amresh
 *
 */
public interface SubjectDao extends GenericDao<Subject, Long>
{
	List<Subject> getSubscribedSubjects(Long boardId, Long gradeId, Long userId);
	List<Subject> getDemoSubjects(Long boardId, Long gradeId);
	List<Subject> getAllSubjectWithChapterAndTopic(Long boardId,Long gradeId);
	boolean checkSubjectAvailability(Long boardId, Long schoolId, Integer status, String subjectName);
	Subject addSubject(Subject subject);
	List<Subject> getSubjects(Long boardId, Long schoolId, String subjectName, Integer status);
	List<Subject> getDistinctSubjects(Long boardId, Integer status);
	String updateSubject(HttpServletRequest request, long userId, String swName);
	
	
}
