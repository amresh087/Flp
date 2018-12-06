package com.flp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Board;
import com.flp.model.Chapter;
import com.flp.model.Grade;
import com.flp.model.Question;
import com.flp.model.QuestionComments;
import com.flp.model.School;
import com.flp.model.SchoolSession;
import com.flp.model.Session;
import com.flp.model.UpdateLog;
/**
 * 
 * @author Amresh
 *
 */
public interface AdminDao {

	Boolean addSchool(School school);

	List<School> getAllSchoolDetails(Boolean status);

	Boolean deactivateSchool(Long schoolId,Integer status);

	School getSchoolDetailsById(Long schoolId);

	Boolean addNewSession(Session session);

	List<Session> getAllSessionList();

	void saveSchoolSession(SchoolSession ss);

	String savePrice(String boardId, String gradeId, String subjectId,
			String price1, String price2, String price3);

	String insertUpdateLog(UpdateLog updateLog);

	List<QuestionComments> getCommentsnotificationsWithLimit(Integer limit);

	Question getQuestionDetailsById(Long questionId);

	List<QuestionComments> getAllCommentsByQuestionId(Long questionId);

	void updateNotificationCountStatus();

	Boolean enableDisableQuestion(Long questionId, Integer status);

	Boolean getAllEnableDisableTopics(Long questionId);

	List<Grade> syncingStatus(Long boardId,Long gradeId);

	void syningUpdate(Long sectionIdSource, Long boardIdTarget, Long gradeIdTarget, Long sectionIdTarget);

	void addGradeAndSectionMappingExistedMerge(Long sectionIdSource, Long boardIdTarget, Long gradeIdTarget, Long sectionIdTarget);


}
