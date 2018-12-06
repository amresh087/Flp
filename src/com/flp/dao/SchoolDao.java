package com.flp.dao;

import java.util.List;

import com.flp.model.School;
/**
 * 
 * @author Amresh
 *
 */
public interface SchoolDao extends GenericDao<School, Long>
{
	List<School> getSchools(String schoolIdString, Integer status);
}
