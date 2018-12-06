package com.flp.dao;

import java.util.List;

import com.flp.model.LearnObjective;
import com.flp.model.Topic;
import com.flp.model.TopicGraph;
/**
 * 
 * @author Amresh
 *
 */
public interface LODao extends GenericDao<LearnObjective, Long>

{
	List<LearnObjective> getLos(String topicIdString, Integer status);
	Long getlocount(Long topicId, Integer status); 
	List<TopicGraph> getTopicGraph(String topicIdString, String userId);
	
	 
}
