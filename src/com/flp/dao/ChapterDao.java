package com.flp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Chapter;
import com.flp.model.QuestionTopic;
/**
 * 
 * @author Amresh
 *
 */
public interface ChapterDao extends GenericDao<Chapter, Long>
{
	List<Chapter> getChapters(String subjectIdString, Long gradeId, Integer status);
	void updateChapter(String chapterIdString, Integer status);
	boolean checkChapterAvailability(Long gradeId, Long subjectId, Integer status, String chapterName);
	Integer getMaxOrderChapter(Long subjectId, Long gradeId, Long boardId, Long schoolId);
	Chapter getChapter(Long gradeId, Long subjectId, Integer status, String chapterName);
	void updateChapterOrder(Long chapterId, Integer serialOrder);
	String updateChapter(HttpServletRequest request, long id);
	List<QuestionTopic> getSubjectWiseQuestions(Long subjectId, Long gradeId);
	List<String> getChapterNamesByChapIds(String chapIds);
	List<Chapter> getAllEnableDisableChapters(String subjectIdString, Long gradeId);
	Boolean enableDisableChapter(Long chapId, Integer status);
}
