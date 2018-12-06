package com.flp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Grade;
/**
 * 
 * @author Amresh
 *
 */
public interface GradeDao extends GenericDao<Grade, Long>
{
	List<Grade> getGrades(Long boardId, Long schoolId, Integer status);
	boolean checkGradeAvailability(String gradeName, Long schoolId, Long boardId, Integer status);
	void updateGrade(String gradeIdString, Integer status);
	String updateGrade(HttpServletRequest request, long id);
	String updateBoard(HttpServletRequest request, long id);
	Grade getGradeObjById(Long gradeId);
}
