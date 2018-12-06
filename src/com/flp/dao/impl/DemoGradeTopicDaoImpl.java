package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.DemoGradeTopicDao;
import com.flp.model.DemoGradeTopic;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class DemoGradeTopicDaoImpl extends GenericDaoImpl<DemoGradeTopic, Long> implements DemoGradeTopicDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DemoGradeTopic> getDemoTopics(Long boardId, Long gradeId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM DemoGradeTopic WHERE boardId = "+boardId+" AND grade.id = "+gradeId);
		List<DemoGradeTopic> list = query.list();
		if (list != null && list.size() > 0) 
		{
			return list;
		} 			
		
		return null;
	}

	@Override
	public List<Long> getDemoTopicIds(Long chapterId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select topic.id FROM DemoGradeTopic WHERE chapterId = :chapterId");
		query.setParameter("chapterId", chapterId);
		return query.list();
	}
}
