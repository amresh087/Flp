package com.flp.dao;

import java.util.List;

import com.flp.model.QuestionTopic;
/**
 * 
 * @author Amresh
 *
 */
public interface QuestionTopicDao extends GenericDao<QuestionTopic, Long>
{
	List<QuestionTopic> getQuestionTopics(Long questionId, Long topicId, Integer status);
	void updateQuestionTopic(String questionTopicIdString, Integer status);
	QuestionTopic updateQuestionTopicId(Long questionTopicIdString, Integer status);
}
