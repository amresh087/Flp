package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.ParentDao;
import com.flp.model.ParentChildMapping;
import com.flp.model.User;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class ParentDaoImpl implements ParentDao
{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ParentChildMapping> getAllChildListByParentId(Long parentId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select distinct(pcm) from ParentChildMapping pcm inner join fetch pcm.user1 u1 where pcm.parentId=:parentId and pcm.status=:status and u1.status=:ustatus");
		query.setParameter("parentId", parentId);
		query.setParameter("status", ParentChildMapping.STATUS_ACTIVE);
		query.setParameter("ustatus", User.STATUS_ACTIVE);
		return query.list();
	}

}
