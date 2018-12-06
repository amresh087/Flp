package com.flp.service;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Question;


public interface AssessmentService
{

	short getSmartScore(long objectId, String objectType, long id, long gradeId);

	Question getNextQuestion(int ss, long topicId, long id);

	Question getQuestion(long l,int questionType, long[] topicIds);

	int saveQuestion(HttpServletRequest request);
	
	
	
}
