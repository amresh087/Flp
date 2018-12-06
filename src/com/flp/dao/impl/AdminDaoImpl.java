package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.AdminDao;
import com.flp.model.Grade;
import com.flp.model.Question;
import com.flp.model.QuestionComments;
import com.flp.model.School;
import com.flp.model.SchoolSession;
import com.flp.model.Session;
import com.flp.model.UpdateLog;

/** @author Amresh */
@Repository
public class AdminDaoImpl implements AdminDao
{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Boolean addSchool(School school)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(school);
		return true;
	}

	@Override
	public List<School> getAllSchoolDetails(Boolean schoolStatus)
	{
		Query query = null;
		if (!schoolStatus)
		{
			query = sessionFactory.getCurrentSession().createQuery("from School ORDER BY id DESC");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery("from School where status=:status ORDER BY id DESC");
			query.setInteger("status", School.STATUS_ACTIVE);
		}
		List<School> schoolLst = query.list();
		if (schoolLst != null && schoolLst.size() > 0)
		{
			return schoolLst;
		}
		return null;
	}

	@Override
	public Boolean deactivateSchool(Long schoolId, Integer status)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE School  SET status=:status where id=:schoolId ");
		query.setParameter("status", status);
		query.setParameter("schoolId", schoolId);
		query.executeUpdate();
		return true;
	}

	@Override
	public School getSchoolDetailsById(Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from School where id=:schoolId");
		query.setLong("schoolId", schoolId);
		List<School> lst = query.list();
		if (lst != null && lst.size() > 0)
		{
			return lst.get(0);
		}
		return null;
	}

	@Override
	public Boolean addNewSession(Session session)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(session);
		return true;
	}

	@Override
	public List<Session> getAllSessionList()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Session where status=:status");
		query.setParameter("status", Session.STATUS_ACTIVE);
		List<Session> sessionList = query.list();
		if (sessionList != null && sessionList.size() > 0)
		{
			return sessionList;
		}
		return null;
	}

	@Override
	public void saveSchoolSession(SchoolSession ss)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(ss);
	}

	@Override
	public String savePrice(String boardId, String gradeId, String subjectId, String price1, String price2, String price3)
	{
		String msg = null;
		try
		{
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("CALL D_save_price(" + boardId + "," + gradeId + "," + subjectId + "," + price1 + ", " + price2 + "," + price3 + ")");

			Integer result = (Integer) query.list().get(0);

			msg = "success";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			msg = "Error: while updating price";

		}

		return msg;
	}

	@Override
	public String insertUpdateLog(UpdateLog updateLog)
	{
		sessionFactory.getCurrentSession().save(updateLog);
		return "success";
	}

	@Override
	public List<QuestionComments> getCommentsnotificationsWithLimit(Integer limit)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(qc) FROM QuestionComments qc inner join fetch qc.user WHERE qc.status=:status ORDER BY qc.updatedOn DESC");
		query.setParameter("status", QuestionComments.STATUS_ACTIVE);
		if (limit > 0)
		{
			query.setMaxResults(limit);
		}
		return query.list();
	}

	@Override
	public Question getQuestionDetailsById(Long questionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(q) from Question q inner join fetch q.answers inner join fetch q.difficultyLevel where q.id=:id");
		query.setParameter("id", questionId);
		return (Question) query.uniqueResult();
	}

	@Override
	public List<QuestionComments> getAllCommentsByQuestionId(Long questionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(qc) FROM QuestionComments qc inner join fetch qc.user WHERE qc.questionId=:questId and qc.status=:status ORDER BY qc.updatedOn DESC");
		query.setParameter("status", QuestionComments.STATUS_ACTIVE);
		query.setParameter("questId", questionId);
		return query.list();
	}

	@Override
	public void updateNotificationCountStatus()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("update QuestionComments set isRead=:isRead");
		query.setParameter("isRead", true);
		query.executeUpdate();

	}

	@Override
	public Boolean enableDisableQuestion(Long questionId, Integer status)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE QuestionTopic SET status=:status where questionId=:questionId ");
		query.setParameter("status", status);
		query.setParameter("questionId", questionId);
		query.executeUpdate();
		return true;
	}

	@Override
	public Boolean getAllEnableDisableTopics(Long questionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from AssessmentQuestion where questionId=:questionId");
		query.setParameter("questionId", questionId);
		if (!query.list().isEmpty())
		{
			return false;
		}
		return true;
	}

	@Override
	public List<Grade> syncingStatus(Long boardId, Long gradeId)
	{

		String str = "from Grade where boardId=? And id=?";

		Query query = sessionFactory.getCurrentSession().createQuery(str);
		query.setLong(0, boardId);
		query.setLong(1, gradeId);
		// query.setParameter("boardId",boardId);
		// query.setParameter("id",gradeId);
		List<Grade> ls = query.list();

		return ls;

	}

	@Override
	public void syningUpdate(Long sectionIdSource, Long boardIdTarget, Long gradeIdTarget, Long sectionIdTarget)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Grade  SET sectionIdSource=:sectionIdSource,boardIdTarget=:boardIdTarget,gradeIdTarget=:gradeIdTarget,sectionIdTarget=:sectionIdTarget where id=:gradeIdTarget1 ");

		query.setParameter("sectionIdSource", sectionIdSource);
		query.setParameter("boardIdTarget", boardIdTarget);
		query.setParameter("gradeIdTarget", gradeIdTarget);
		query.setParameter("sectionIdTarget", sectionIdTarget);
		query.setParameter("gradeIdTarget1", gradeIdTarget);
		query.executeUpdate();

	}

	@Override
	public void addGradeAndSectionMappingExistedMerge(Long sectionIdSource, Long boardIdTarget, Long gradeIdTarget, Long sectionIdTarget)
	{
		// TODO Auto-generated method stub

	}

}
