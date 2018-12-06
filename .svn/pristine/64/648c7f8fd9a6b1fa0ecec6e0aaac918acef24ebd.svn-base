package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.ModuleTypeDao;
import com.flp.model.ModuleType;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class ModuleTypeDaoImpl extends GenericDaoImpl<ModuleType, Long> implements ModuleTypeDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleType> getModuleTypes(String category, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM ModuleType WHERE category = :c AND status = :status");
		query.setString("c", category);
		query.setInteger("status", status);
		
		return query.list();
	}
}
