package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.RetailUserQueryDao;
import com.flp.model.ParentChildMapping;
import com.flp.model.RetailUserQuery;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class RetailUserQueryDaoImpl extends GenericDaoImpl<RetailUserQuery, Long> implements RetailUserQueryDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<RetailUserQuery> getRetailUserQueries(Long userId, Long gradeId, Boolean includeLowerGrades, Integer status)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM RetailUserQuery ruq INNER JOIN FETCH ruq.askedBy ab INNER JOIN FETCH ruq.grade g WHERE ruq.status = :status ";
		Query query = null;
		
		if(includeLowerGrades != null && includeLowerGrades == true)
		{
			str = str + "AND ruq.grade.id <= :gradeId ORDER BY FIELD(ruq.grade.id, :orderGradeId) DESC, FIELD(ruq.askedBy, :orderUserId) DESC";
			query = session.createQuery(str);
			query.setLong("gradeId", gradeId);
			query.setLong("orderGradeId", gradeId);
			query.setLong("orderUserId", userId);
		}
				
		query.setInteger("status", status);
		
		List<RetailUserQuery> list = query.list();
		list.get(0).getRetailUserComments().get(0).getId();
		
		// Retrieving RetailUserComment list for first query only
		//List<RetailUserComment> sublist = list.get(0).getRetailUserComments().get(0).getId();
		//sublist.get(0).getId();		
		//list.get(0).setRetailUserComments(sublist);
		
		return list;
	}

	@Override
	public List<ParentChildMapping> getParentRequestList(Long studentId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select Distinct(pcm) from ParentChildMapping pcm inner join fetch pcm.user where pcm.userId=:userId and pcm.status=:status group by pcm.parentId");
		query.setParameter("userId", studentId);
		query.setParameter("status", ParentChildMapping.STATUS_PENDING);
		return query.list();
	}

	@Override
	public Boolean acceptRejectParentLinking(Long parentLinkingId,Integer acceptOrReject)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("UPDATE ParentChildMapping SET joinDate=:date, status=:status, modifiedDate=:date where id=:id");
		query.setParameter("status", acceptOrReject);
		query.setParameter("date", Utility.getCurrentDate());
		query.setParameter("id", parentLinkingId);
		query.executeUpdate();
		return true;
	}
}
