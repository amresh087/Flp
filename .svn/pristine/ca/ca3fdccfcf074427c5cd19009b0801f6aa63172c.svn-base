package com.flp.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.AnswerDao;
import com.flp.model.Answer;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Answer, Long> implements AnswerDao
{
	

@Override
	public Answer getansOptions(Long optoon)
	{
	  
		Answer answer=null;
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Answer where id=:optoon");
		q.setParameter("optoon", optoon);
       
         if (!q.list().isEmpty())
         {
        	return  answer = (Answer) q.list().get(0);
              
         } 
		return null;
	}



}
