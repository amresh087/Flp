package com.flp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Topic;
import com.flp.model.TopicSmartScore;
import com.flp.model.UserForm;
/**
 * 
 * @author Amresh
 *
 */
public interface TopicDao extends GenericDao<Topic, Long>
{
	List<Topic> getTopics(Long boardId, Long gradeId, Long chapterId);

	List<TopicSmartScore> getUserSSByTopic(UserForm user);

	List<Topic> getTopics(String chapterIdString, Integer status);
	
	boolean checkTopicAvailability(Long chapterId, Integer status, String topicName);
	
	void updateTopic(String topicIdString, Integer status);
	
	Integer getMaxOrderTopic(Long chapterId);
	
	List<Topic> getTopics(Long boardId, Long gradeId, Integer status);

	Topic getTopicObj(Long topicId);
	
	void updateTopicOrder(Long topicId, Integer serialOrder);
	
	String updateTopic(HttpServletRequest request, long id);

	List<String> getTopicNamesbyIds(String topicIds);

	Boolean enableDisableTopic(Long topicId, Integer status);

	List<Topic> getAllEnableDisableTopics(Long chapterId, Integer status);
}
