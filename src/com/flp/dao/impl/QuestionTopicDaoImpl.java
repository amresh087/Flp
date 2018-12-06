package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.QuestionTopicDao;
import com.flp.model.QuestionTopic;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class QuestionTopicDaoImpl extends GenericDaoImpl<QuestionTopic, Long> implements QuestionTopicDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionTopic> getQuestionTopics(Long questionId, Long topicId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM QuestionTopic qt INNER JOIN FETCH qt.question WHERE qt.status = :status ";
		Query query = null;
		
		if(questionId != null && topicId == null)
		{
			str = str + "AND qt.questionId = :qId";
			query = session.createQuery(str);
			query.setLong("qId", questionId);
		}
		else if(topicId != null && questionId == null)
		{
			str = str + "AND qt.topicId = :tId";
			query = session.createQuery(str);
			query.setLong("tId", topicId);
		}
		
		query.setInteger("status", status);
		
		return query.list();
	}

	@Override
	public void updateQuestionTopic(String questionTopicIdString, Integer status)
	{
		String[] idList = questionTopicIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE QuestionTopic SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}
	
	

@Override
	public QuestionTopic updateQuestionTopicId(Long questionTopicId, Integer status)
	{
          Session session = sessionFactory.getCurrentSession();
		
          QuestionTopic  questiontopic=null;
		String queryString ="  from QuestionTopic q  where q.id=:qTopicid ";
		Query query1 = session.createQuery(queryString);
		query1.setLong("qTopicid", questionTopicId);  
		
		if (!query1.list().isEmpty())
        {
            
            return  questiontopic = (QuestionTopic) query1.list().get(0); 
        }
		else{
			return null;
		}
	    
		 
	}
	
	
}