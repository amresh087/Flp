package com.flp.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flp.dao.AssessmentDAO;
import com.flp.model.Question;
import com.flp.model.UserForm;
import com.flp.service.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	AssessmentDAO assessmentDAO;
	
	@Transactional
	@Override
	public short getSmartScore(long objectId, String objectType, long id,long gradeId) {
	 return	assessmentDAO.getSmartScore(objectId,objectType,id,gradeId);
	}

	@Transactional
	@Override
	public Question getNextQuestion(int ss, long topicId, long id) {
		return assessmentDAO.getNextQuestion(ss,  topicId, id);
	}

	@Transactional
	@Override
	public Question getQuestion(long userId,int questionType, long[] topicIds) {
		return assessmentDAO.getQuestion(userId, questionType, topicIds);
		
	}

	@Override
	@Transactional
	public int saveQuestion(HttpServletRequest request) {
		UserForm user=(UserForm) request.getSession().getAttribute("user");
		String topicId=request.getParameter("topicId");
		String questionId=request.getParameter("questionId");
		String isAttempted=request.getParameter("isAttempted");
		String isCorrect=request.getParameter("isCorrect");
		String userAnswer=request.getParameter("userValues");
		String timeTaken="30";
		String questionType=request.getParameter("questionType");
		return assessmentDAO.saveQuestion(topicId,questionId,isAttempted,userAnswer,isCorrect,timeTaken,getSmartScore(questionType, isAttempted,isCorrect, user.getgScore()), user);
		
	}

	private int getSmartScore(String questionType, String isAttempted,
			String isCorrect, int us) {	
		int ss=0;
		if(isAttempted.equals("0"))
			return ss;
		else if(isCorrect.equals("1"))
		{
			if(questionType.equals("1"))
			{
				if(us>=0 && us<=30)
					ss=2;
				else if(us <=80)
					ss=2;
				else if (us <100)
					ss=1;
			}
			else if(questionType.equals("2"))
			{
				if(us>=0 && us<=30)
					ss=3;
				else if(us <=80)
					ss=2;
				else if (us <100)
					ss=1;
			}
			else if(questionType.equals("3"))
			{
				if(us>=0 && us<=30)
					ss=5;
				else if(us <=80)
					ss=5;
				else if (us <100)
					ss=3;
			}
		}
		else
		{
			if(questionType.equals("1"))
			{
				if(us >= 30 && us<80)
					ss=-1;
				else if (us>80 && us<=100)
					ss=-1;
			}
			else if(questionType.equals("2"))
			{
				if(us >= 30 && us<80)
					ss=-1;
				else if (us>80 && us<=100)
					ss=-2;
			}
			else if(questionType.equals("3"))
			{
				if(us >= 30 && us<80)
					ss=-2;
				else if (us>80 && us<=100)
					ss=-3;
			}
		}
		return ss;
	}

}
