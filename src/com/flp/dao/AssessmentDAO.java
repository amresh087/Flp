package com.flp.dao;

import java.util.List;

import com.flp.model.Answer;
import com.flp.model.Question;
import com.flp.model.User;
import com.flp.model.UserForm;

/**
 * 
 * @author Amresh
 *
 */

public interface AssessmentDAO 
{

	short getSmartScore(Long objectId, String objectType, long id, Long gradeId);

	Question getNextQuestion(int ss, long topicId, long id);



	List<Answer> getAnswerSet(long questionId);

	Question getQuestion(long userId, int questionType, long[] topicIds);

	int saveQuestion(String topicId, String questionId, String isAttempted,
			String userAnswer, String isCorrect, String timeTaken, int ss, UserForm user);

	
	
	
}
