package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.SchoolDao;
import com.flp.model.School;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SchoolDaoImpl extends GenericDaoImpl<School, Long> implements SchoolDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<School> getSchools(String schoolIdString, Integer status)
	{
		String[] idList = schoolIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM School WHERE id IN (:ids) AND status = :status");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		return query.list();
	}
}
