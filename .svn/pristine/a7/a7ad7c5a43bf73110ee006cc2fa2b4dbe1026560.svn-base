package com.flp.dao;

import java.util.List;

import com.flp.model.Question;
import com.flp.model.QuestionTopic;
/**
 * 
 * @author Amresh
 *
 */
public interface QuestionDao extends GenericDao<Question, Long>
{
	List<QuestionTopic> getQuestions(String topicIdMap, Integer status);
	
	Question  getQuestionData(Long quid );
	
	void updateQuestion(String questionidString,Integer status);
	
	
	
}
