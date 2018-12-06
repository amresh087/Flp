package com.flp.dao;

import java.util.List;

import com.flp.model.ModuleTopic;
/**
 * 
 * @author Amresh
 *
 */
public interface ModuleTopicDao extends GenericDao<ModuleTopic, Long>
{
	List<ModuleTopic> getModuleTopics(Long moduleId, Long topicId, Integer status);
	void updateModuleTopic(String moduleTopicIdString, Integer status);
}
