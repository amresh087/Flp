package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.ModuleTopicDao;
import com.flp.model.ModuleTopic;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class ModuleTopicDaoImpl extends GenericDaoImpl<ModuleTopic, Long> implements ModuleTopicDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleTopic> getModuleTopics(Long moduleId, Long topicId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String orderString = "ORDER BY mt.id DESC";
		String str = "FROM ModuleTopic mt INNER JOIN FETCH mt.module WHERE mt.status = :status "+orderString;
		Query query = null;
		
		if(moduleId != null && topicId == null)
		{
			str = str.replace(orderString, "");
			str = str + "AND mt.moduleId = :mId "+orderString;
			query = session.createQuery(str);
			query.setLong("mId", moduleId);
		}
		else if(topicId != null && moduleId == null)
		{
			str = str.replace(orderString, "");
			str = str + "AND mt.topicId = :tId "+orderString;
			query = session.createQuery(str);
			query.setLong("tId", topicId);
		}
		
		query.setInteger("status", status);
		
		return query.list();
	}

	@Override
	public void updateModuleTopic(String moduleTopicIdString, Integer status)
	{
		String[] idList = moduleTopicIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE ModuleTopic SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		query.executeUpdate();
	}
}
