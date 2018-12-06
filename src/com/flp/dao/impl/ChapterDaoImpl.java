package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flp.dao.ChapterDao;
import com.flp.model.Chapter;
import com.flp.model.QuestionTopic;
import com.flp.model.Topic;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class ChapterDaoImpl extends GenericDaoImpl<Chapter, Long> implements ChapterDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getChapters(String subjectIdString, Long gradeId, Integer status)
	{
		String[] idList = subjectIdString.split(",");
		Long[] ids = new Long[idList.length];

		for (int i = 0; i < idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Chapter WHERE status = :status AND gradeId = :gId AND subjectId IN (:ids) ORDER BY serialOrder");
		query.setParameterList("ids", ids);
		query.setLong("gId", gradeId);
		query.setInteger("status", status);

		return query.list();
	}

	@Override
	public void updateChapter(String chapterIdString, Integer status)
	{
		String[] idList = chapterIdString.split(",");
		Long[] ids = new Long[idList.length];

		for (int i = 0; i < idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Chapter SET status = :status WHERE id IN (:ids)");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);

		query.executeUpdate();
	}

	@Override
	public boolean checkChapterAvailability(Long gradeId, Long subjectId, Integer status, String chapterName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Chapter WHERE status = :status AND name = :name AND gradeId = :gId AND subjectId = :sId");
		query.setLong("gId", gradeId);
		query.setLong("sId", subjectId);
		query.setInteger("status", status);
		query.setParameter("name", chapterName.trim());

		return (query.list().size() > 0 ? false : true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxOrderChapter(Long subjectId, Long gradeId, Long boardId, Long schoolId)
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "SELECT MAX(serialOrder) FROM Chapter WHERE subjectId = :sId AND gradeId = :gId ";
		Query query = null;

		if (boardId != null && schoolId == null)
		{
			queryString = queryString + "AND boardId = :bId";
			query = session.createQuery(queryString);
			query.setLong("bId", boardId);
		}
		else if (schoolId != null && boardId == null)
		{
			queryString = queryString + "AND schoolId = :schId";
			query = session.createQuery(queryString);
			query.setLong("schId", schoolId);
		}

		query.setLong("sId", subjectId);
		query.setLong("gId", gradeId);

		List<Integer> chapterList = query.list();

		if (chapterList.get(0) == null)
			return 1;

		return chapterList.get(0) + 1;
	}

	@Override
	public Chapter getChapter(Long gradeId, Long subjectId, Integer status, String chapterName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Chapter WHERE status = :status AND name = :name AND gradeId = :gId AND subjectId = :sId");
		query.setLong("gId", gradeId);
		query.setLong("sId", subjectId);
		query.setInteger("status", status);
		query.setParameter("name", chapterName.trim());

		return (Chapter) query.uniqueResult();
	}

	@Override
	public void updateChapterOrder(Long chapterId, Integer serialOrder)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Chapter set serialOrder = :order WHERE id = :cId");
		query.setLong("order", serialOrder);
		query.setLong("cId", chapterId);
		query.executeUpdate();
	}

	@Override
	@ResponseBody
	public String updateChapter(HttpServletRequest request, long id)
	{
		Session session = sessionFactory.getCurrentSession();
		Chapter chap = (Chapter) session.get(Chapter.class, Long.parseLong(request.getParameter("id")));
		chap.setName(request.getParameter("newName"));
		session.update(chap);
		return "success";
	}

	@Override
	public List<QuestionTopic> getSubjectWiseQuestions(Long subjectId, Long gradeId)
	{

		/* Query q=sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT(c) FROM Chapter c INNER JOIN FETCH c.topics t " + "inner join fetch t.questionTopic qt inner join fetch qt.difficultyLevel inner join fetch qt.question q inner join fetch q.answers "+ "where c.subjectId=:subjectId " + "and c.gradeId=:gradeId and c.status=:status and t.status=:tstatus and qt.status=:qtstatus"); */
		String sql = "select distinct(qt) from QuestionTopic qt inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch qt.topic t inner join fetch" + " qt.question q inner join fetch t.chapter c inner join fetch q.answers where  c.subjectId=:subjectId " + "and c.gradeId=:gradeId and c.status=:status and t.status=:tstatus and qt.status=:qtstatus";
		Query q = sessionFactory.getCurrentSession().createQuery(sql);
		q.setLong("subjectId", subjectId);
		q.setLong("gradeId", gradeId);
		q.setInteger("status", Chapter.STATUS_ACTIVE);
		q.setInteger("tstatus", Topic.STATUS_ACTIVE);
		q.setParameter("qtstatus", QuestionTopic.STATUS_ACTIVE);
		return q.list();
	}

	@Override
	public List<String> getChapterNamesByChapIds(String chapIds)
	{
		List<Long> chapterIds = new ArrayList<Long>();
		String[] val = chapIds.split("\\,");
		for (String s : val)
		{
			chapterIds.add(Long.parseLong(s));
		}
		Query query = sessionFactory.getCurrentSession().createQuery("select name from Chapter where id in(:chapterIds)");
		query.setParameterList("chapterIds", chapterIds);
		return query.list();
	}

	@Override
	public List<Chapter> getAllEnableDisableChapters(String subjectIdString, Long gradeId)
	{
		String[] idList = subjectIdString.split(",");
		Long[] ids = new Long[idList.length];

		for (int i = 0; i < idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Chapter WHERE status != :status AND gradeId = :gId AND subjectId IN (:ids) ORDER BY serialOrder");
		query.setParameterList("ids", ids);
		query.setLong("gId", gradeId);
		query.setInteger("status", Chapter.STATUS_INACTIVE);

		return query.list();
	}

	@Override
	public Boolean enableDisableChapter(Long chapId, Integer status)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Chapter SET status=:status where id=:chapId ");
		query.setParameter("status", status);
		query.setParameter("chapId", chapId);
		query.executeUpdate();
		return true;
	}

}