package com.flp.dao;

import java.util.List;

import com.flp.model.Section;
/**
 * 
 * @author Amresh
 *
 */
public interface SectionDao extends GenericDao<Section, Long>
{
	List<Section> getSections(String sectionIdString, Long boardId, String sessionSchoolGradeIdString, Integer status, String gradeIdString, String order);
	List<Long> addSection(Long gradeId, String boardIdString, String sessionSchoolGradeIdString, String sectionName);
	void updateSection(String sectionIdString, Integer status);
	Long getSectionByGradeAndBoardId(Long gradeId, Long boardId);
	Section getSectionObjById(Long secId);
}
