package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flp.dao.TopicDao;
import com.flp.model.Topic;
import com.flp.model.TopicSmartScore;
import com.flp.model.UserForm;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class TopicDaoImpl extends GenericDaoImpl<Topic, Long> implements TopicDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopics(Long boardId, Long gradeId, Long chapterId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Topic WHERE boardId = :bId AND gradeId = :gId AND chapter.id = :cId");
		query.setLong("bId", boardId);
		query.setLong("gId", gradeId);
		query.setLong("cId", chapterId);

		List<Topic> list = query.list();
		if (list == null || list.size() == 0)
			return null;

		return list;
	}

	@Override
	public List<TopicSmartScore> getUserSSByTopic(UserForm user)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TopicSmartScore WHERE userId=:userId");
		query.setLong("userId", user.getId());

		List<TopicSmartScore> list = query.list();
		if (list != null && !list.isEmpty())
		{
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopics(String chapterIdString, Integer status)
	{
		String[] idList = chapterIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Topic WHERE status = :status AND chapterId IN (:ids) ORDER BY serialOrder");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		return query.list();
	}

	@Override
	public boolean checkTopicAvailability(Long chapterId, Integer status, String topicName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Topic WHERE status = :status AND name = :name AND chapterId = :cId");
		query.setLong("cId", chapterId);
		query.setInteger("status", status);
		query.setParameter("name", topicName.trim());
		
		return (query.list().size() > 0 ? false : true);
	}

	@Override
	public void updateTopic(String topicIdString, Integer status)
	{
		String[] idList = topicIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Topic SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxOrderTopic(Long chapterId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT MAX(serialOrder) FROM Topic WHERE chapterId = :cId");
		query.setLong("cId", chapterId);
		
		List<Integer> topicList = query.list();
		
		if(topicList.get(0) == null)
			return 1;
		
		return topicList.get(0)+1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopics(Long boardId, Long gradeId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Topic t INNER JOIN FETCH t.subject s WHERE t.boardId = :bId AND t.gradeId = :gId AND t.status = :status");
		query.setLong("bId", boardId);
		query.setLong("gId", gradeId);
		query.setLong("status", status);
		
		return query.list();
	}

	@Override
	public Topic getTopicObj(Long topicId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select distinct(t) from Topic t inner join fetch t.chapter c inner join fetch c.subject where t.id=:topicId");
		query.setParameter("topicId",topicId);
		return (Topic) query.uniqueResult();
	}

	@Override
	public void updateTopicOrder(Long topicId, Integer serialOrder)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Topic set serialOrder = :order WHERE id = :tId");
		query.setLong("order", serialOrder);
		query.setLong("tId", topicId);
		query.executeUpdate();
	}

	@Override
	@ResponseBody
	public String updateTopic(HttpServletRequest request, long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Topic top=(Topic) session.get(Topic.class, Long.parseLong(request.getParameter("id")));
		top.setName(request.getParameter("newName"));
		session.update(top);
		return "success";
	}

	@Override
	public List<String> getTopicNamesbyIds(String topicIds)
	{
		List<Long> topIds = new ArrayList<Long>();
		String[] val = topicIds.split("\\,");
		for (String s : val)
		{
			topIds.add(Long.parseLong(s));
		}
		Query query = sessionFactory.getCurrentSession().createQuery("select name from Topic where id in(:topIds)");
		query.setParameterList("topIds", topIds);
		return query.list();
	}

	@Override
	public Boolean enableDisableTopic(Long topicId, Integer status)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Topic SET status=:status where id=:topicId ");
		query.setParameter("status", status);
		query.setParameter("topicId", topicId);
		query.executeUpdate();
		return true;
	}

	@Override
	public List<Topic> getAllEnableDisableTopics(Long chapterId, Integer status)
	{

		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Topic WHERE status != :status AND chapterId =:chapterId ORDER BY serialOrder");
		query.setParameter("chapterId", chapterId);
		query.setInteger("status", status);
		
		return query.list();
	}

	
}