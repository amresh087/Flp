package com.flp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.ParentChildMapping;
import com.flp.model.RetailUserQuery;
import com.flp.model.Subject;
import com.flp.model.UserForm;
import com.flp.model.Topic;
import com.flp.model.UserTest;

public interface RetailUserService 
{

	List<Subject> getSubjects(long id, Long boardId, Long sectionId);

	String saveTest(HttpServletRequest request);
	
	List<RetailUserQuery> getRetailUserQueries(Long userId, Long gradeId, Boolean includeLowerGrades, Integer status);
	
	List<Topic> getTopics(Long boardId, Long gradeId, Integer status);

	Map<Long, Integer> getAvgTopicSmartScore(List<Long> topicIds);

	Map<Long, Integer> getAllTopicSmartScoreOfUser(Long id);

	List<Topic> getSuggestedTopicLst(List<Long> subjectIds, Integer randomTopicLimit, List<Long> topicIds, Long gradeId, Long boardId);

	List<UserTest> getTestList(long id, Long boardId, Long gradeId);

	UserTest getTest(long testId);

	void saveTest(long subjectId, int total, int correct, int incorrect,
			int unAttempted, UserForm loginUser, List<String> questionDetail,
			long assessmentId);

	List<ParentChildMapping> getParentRequestList(Long studentId);

	Boolean acceptRejectParentLinking(Long parentLinkingId,Integer acceptOrReject);

}
