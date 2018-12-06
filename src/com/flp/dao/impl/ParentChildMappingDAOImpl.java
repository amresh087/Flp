package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.ParentChildMappingDao;
import com.flp.model.ParentChildMapping;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class ParentChildMappingDAOImpl extends GenericDaoImpl<ParentChildMapping, Long> implements ParentChildMappingDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ParentChildMapping getParentChildMapping(Long parentId, Long studentId, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ParentChildMapping.class);
		criteria.add(Restrictions.eq("parentId", parentId));
		criteria.add(Restrictions.eq("userId", studentId));
		criteria.add(Restrictions.gt("status", ParentChildMapping.STATUS_INACTIVE));
		criteria.setMaxResults(1);
		List<ParentChildMapping> pcm = criteria.list();
		if(!pcm.isEmpty()){
			return pcm.get(0);
		}
		return null;
	}
}
