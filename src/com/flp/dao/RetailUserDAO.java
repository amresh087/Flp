package com.flp.dao;

import java.util.List;
import java.util.Map;

import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.model.UserForm;
import com.flp.model.UserTest;
import com.flp.model.UserTestDetail;
/**
 * 
 * @author Amresh
 *
 */
public interface RetailUserDAO {

	List<Subject> getSubjects(long id, Long boardId, Long sectionId);

	String saveTest(UserForm loginUser, String testName, int m,
			String topicIds, Integer subjectId, Integer total);

	Map<Long, Integer> getAvgTopicSmartScore(List<Long> topicIds);

	Map<Long, Integer> getAllTopicSmartScoreOfUser(Long userId);

	List<Topic> getSuggestedTopicLst(List<Long> subjectIds, Integer randomTopicLimit, List<Long> topicIds, Long gradeId, Long boardId);

	List<UserTest> getTestList(long id, Long boardId, Long sectionId);

	UserTest getTest(long testId);

	void saveTest(UserTest test);

	void saveTestDetail(UserTestDetail s);

}
