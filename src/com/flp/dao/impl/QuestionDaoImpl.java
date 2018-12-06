package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.QuestionDao;
import com.flp.model.Question;
import com.flp.model.QuestionTopic;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Question, Long> implements QuestionDao
{
	@Override
	public List<QuestionTopic> getQuestions(String topicIdMap, Integer status)
	{
	
			Session session = sessionFactory.getCurrentSession();
			
			String queryString="select distinct(qt) from QuestionTopic qt inner join fetch qt.question inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel where qt.topicId=:topicId and qt.status=:status";
			Query query1 = session.createQuery(queryString);
			query1.setLong("topicId", Long.parseLong(topicIdMap));
			query1.setLong("status", status);
			//List<Question> li=query1.list();
		
			
		
		// TODO Auto-generated method stub
		return query1.list();
	}
	
	
	

@Override
	public Question getQuestionData(Long quid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		Question  question=null;
		String queryString="  from Question q  where q.id=:quid ";
		Query query1 = session.createQuery(queryString);
		query1.setLong("quid", quid);  
		
		if (!query1.list().isEmpty())
        {
            
            return  question = (Question) query1.list().get(0); 
        }
		else{
			return null;
		}
	    
		 
	}
 

@Override
public void updateQuestion(String questionidString,Integer status)
{
	String[] idList = questionidString.split(",");		
	Long[] ids = new Long[idList.length];
	
	for(int i=0; i<idList.length; i++)
	{
		ids[i] = Long.parseLong(idList[i]);
	}
	
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("UPDATE Question SET status = :status WHERE id IN (:ids)");
	query.setParameterList("ids", ids);
	query.setInteger("status", status);
	
	query.executeUpdate();
}






}
