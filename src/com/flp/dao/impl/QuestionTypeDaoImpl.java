package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.QuestionTypeDao;
import com.flp.model.QuestionType;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class QuestionTypeDaoImpl extends GenericDaoImpl<QuestionType, Long> implements QuestionTypeDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionType> getQuestionType(String category, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM QuestionType WHERE category = :c AND status = :status");
		query.setString("c", category);
		query.setInteger("status", status);
		
		return query.list();
	}
}
