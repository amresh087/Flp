package com.flp.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.UserQuestionAnswerDao;
import com.flp.model.UserQuestionAnswer;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class UserQuestionAnswerDaoImpl extends GenericDaoImpl<UserQuestionAnswer, Long> implements UserQuestionAnswerDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Float> getPieChartReport(Long userId, Long gradeId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT COUNT(*) AS total, "+
							"(SELECT COUNT(*) FROM t_user_question_answer WHERE f_user_id = 4 AND f_grade_id=1 AND f_is_attempted = 1 AND f_correct_incorrect = 1) AS correct, "+ 
							"(SELECT COUNT(*) FROM t_user_question_answer WHERE f_user_id = 4 AND f_grade_id=1 AND f_is_attempted = 1 AND f_correct_incorrect = 0) AS incorrect, "+
							"(SELECT COUNT(*) FROM t_user_question_answer WHERE f_user_id = 4 AND f_grade_id=1 AND f_is_attempted = 0) AS unattempted "+
							"FROM t_user_question_answer WHERE f_user_id = :uId AND f_grade_id = :gId");
		query.setLong("uId", userId);
		query.setLong("gId", gradeId);
		List<Object> list = query.list();			
		
		Object[] objArray = (Object[]) list.get(0);
		
		if(list == null || list.size() == 0 || ((BigInteger)objArray[0]).intValue() == 0)
			return null;
		
		Float correct = (((BigInteger)objArray[1]).floatValue() * 100 ) / ((BigInteger)objArray[0]).floatValue();
		Float incorrect = (((BigInteger)objArray[2]).floatValue() * 100 ) / ((BigInteger)objArray[0]).floatValue();
		Float unattempted = (((BigInteger)objArray[3]).floatValue() * 100 ) / ((BigInteger)objArray[0]).floatValue();
		
		List<Float> fList = new ArrayList<Float>();
		fList.add(correct);
		fList.add(incorrect);
		fList.add(unattempted);
		
		return fList;
	}
}
