package com.flp.dao;

import java.util.List;

import com.flp.model.ParentChildMapping;
import com.flp.model.RetailUserQuery;
/**
 * 
 * @author Amresh
 *
 */
public interface RetailUserQueryDao extends GenericDao<RetailUserQuery, Long>
{
	List<RetailUserQuery> getRetailUserQueries(Long userId, Long gradeId, Boolean includeLowerGrades, Integer status);

	List<ParentChildMapping> getParentRequestList(Long studentId);

	Boolean acceptRejectParentLinking(Long parentLinkingId,Integer acceptOrReject);
}
