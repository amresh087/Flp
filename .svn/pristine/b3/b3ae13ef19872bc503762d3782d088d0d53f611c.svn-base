package com.flp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.flp.dao.GenericDao;
import com.flp.model.ZoneCreate;
/**
 * 
 * @author Amresh
 *
 */
@SuppressWarnings("unchecked")
public class GenericDaoImpl<K, ID extends Serializable> implements GenericDao<K, ID> 
{
	@Autowired
	SessionFactory sessionFactory;
	
	private Class<K> kClass;
			
	public Class<K> getkClass() 
	{
		return kClass;
	}
	
	@Override
	public K mergeEntity(K object) 
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			K obj = (K) session.merge(object);
			return obj;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public K getEntity(ID id)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			K obj = (K) session.get(getkClass(), id);	
			
			if(obj != null)
				return obj;
			
			return null;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public K getEntity(String propertyName, Object propertyValue)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(getkClass());
			criteria.add(Restrictions.eq(propertyName, propertyValue));
			K obj = (K) criteria.uniqueResult();
			
			if(obj != null)
				return obj;
			
			return null;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	//========================in genericdao
	@Override
    public K saveEntityLoObject(K object)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(object);
            return object;
        }
        catch(Exception ex)
        {
           
            ex.printStackTrace();           
           
            return null;
        }
    }
	//===============================================
	
	@Override
	public List<K> getEntityList(String propertyName, Object propertyValue)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM "+getkClass().getName()+" WHERE "+propertyName +" = "+propertyValue);
			List<K> list = query.list();
			
			if (list != null && list.size() > 0) 
			{
				return list;
			} 			
			
			return null;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public List<K> getEntityList()
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM "+getkClass().getName());
			List<K> list = query.list();
			
			if (list != null && list.size() > 0) 
			{
				return list;
			} 			
			
			return null;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public K saveEntity(K object)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(object);
			return object;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();			
			
			return null;
		}
	}
	
	@Override
	public K updateEntity(K object)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(object);
			return object;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public K saveOrUpdateEntity(K object)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(object);
			return object;
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			
			return null;
		}
	}	
	
	
}
