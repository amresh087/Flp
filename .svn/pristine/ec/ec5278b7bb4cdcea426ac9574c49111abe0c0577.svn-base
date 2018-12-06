package com.flp.dao;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.ZoneCreate;
/**
 * 
 * @author Amresh
 *
 */
public interface GenericDao<K, ID extends Serializable>
{
	public K mergeEntity(K object);
	public K getEntity(ID id);
	public K getEntity(String propertyName, Object propertyValue);
	public List<K> getEntityList(String propertyName, Object propertyValue);
	public List<K> getEntityList();
	public K saveEntity(K object);
	public K updateEntity(K object);
	public K saveEntityLoObject(K object);
	public K saveOrUpdateEntity(K object);
	
//	public ZoneCreate saveOrUpdateEntity2(ZoneCreate board);
	
}
