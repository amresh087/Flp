package com.flp.dao;

import java.util.List;

import com.flp.model.ParentChildMapping;
/**
 * 
 * @author Amresh
 *
 */
public interface ParentDao
{

	List<ParentChildMapping> getAllChildListByParentId(Long parentId);

}
