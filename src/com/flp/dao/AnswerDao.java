package com.flp.dao;

import com.flp.model.Answer;
/**
 * 
 * @author Amresh
 *
 */
public interface AnswerDao extends GenericDao<Answer, Long>
{
	
	Answer  getansOptions(Long optoon);
	
}
