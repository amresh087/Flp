package com.flp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.RetailUserDAO;
import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.model.TopicSmartScore;
import com.flp.model.UserForm;
import com.flp.model.UserTest;
import com.flp.model.UserTestDetail;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */

@Repository
public class RetailUserDaoImpl implements RetailUserDAO
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Subject> getSubjects(long id, Long boardId, Long sectionId)
	{
		Query q = sessionFactory.getCurrentSession().createSQLQuery("CALL `getUserSubjects`(" + id + "," + boardId + "," + sectionId + ")").addEntity(Subject.class);
		return q.list();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.flp.dao.RetailUserDAO#saveTest(com.flp.model.UserForm, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 * 
	 * boardId, gradeId, isMultiAttempt , testName, topicIds , timeout , userId */
	@Override
	public String saveTest(UserForm loginUser, String testName, int m, String topicIds, Integer subjectId, Integer total)
	{
		String msg = null;
		try
		{
			String query = "CALL `D_createTest`(" + loginUser.getBoardId() + "," + loginUser.getGradeId() + ", 0, '" + testName + "','" + topicIds + "','" + m + "'," + loginUser.getId() + ", " + topicIds.split(",").length + "," + subjectId + ", " + total + ")";
			
			Query q = sessionFactory.getCurrentSession().createSQLQuery(query);

			int i = (int) q.list().get(0);

		

		}
		catch (Exception e)
		{
			msg = "Error in saving test to database: " + e.getMessage();
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	public Map<Long, Integer> getAvgTopicSmartScore(List<Long> topicIds)
	{
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		Query query = sessionFactory.getCurrentSession().createQuery("select topicId, avg(smartScore) as avgsmtScore from TopicSmartScore where topicId in (:topicIds) group by topicId");
		query.setParameterList("topicIds", topicIds);
		List<Object[]> lst = query.list();
		if (!lst.isEmpty())
		{
			for (Object[] o : lst)
			{
				Long topicId = (long) o[0];
				double ss = (double) o[1];
				int fss = (int) ss;
				map.put(topicId, fss);
			}
		}
		return map;
	}

	@Override
	public Map<Long, Integer> getAllTopicSmartScoreOfUser(Long userId)
	{
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		Query query = sessionFactory.getCurrentSession().createQuery("from TopicSmartScore where userId=:userId");
		query.setParameter("userId", userId);
		List<TopicSmartScore> lst = query.list();
		if (!lst.isEmpty())
		{
			for (TopicSmartScore tss : lst)
			{
				map.put(tss.getTopicId(), Integer.valueOf((tss.getSmartScore().toString())));
			}
		}
		return map;
	}

	@Override
	public List<Topic> getSuggestedTopicLst(List<Long> subjectIds, Integer randomTopicLimit, List<Long> topicIds, Long gradeId, Long boardId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(t) from Topic t inner join fetch t.chapter c inner join fetch c.subject s where s.id in (:subjectIds)  and t.id not in (:topicIds) and s.status=:status and t.status=:tstatus and t.boardId=:boardId and t.gradeId=:gradeId order by rand()");
		query.setParameterList("subjectIds", subjectIds);
		query.setParameterList("topicIds", topicIds);
		query.setParameter("status", Subject.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameter("gradeId", gradeId);
		query.setParameter("boardId", boardId);
		query.setMaxResults(randomTopicLimit);
		List<Topic> lst = query.list();
		return lst;
	}

	@Override
	public List<UserTest> getTestList(long id, Long boardId, Long gradeId)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("FROM UserTest t WHERE t.userId=:userId AND t.boardId=:boardId AND t.classId=:gradeId AND t.isActive=1");
		q.setParameter("userId", id);
		q.setParameter("boardId", boardId);
		q.setParameter("gradeId", gradeId);
		return q.list();
	}

	@Override
	public UserTest getTest(long testId)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("FROM UserTest t INNER JOIN FETCH t.userTestDetailList tl INNER JOIN FETCH tl.question q INNER JOIN FETCH q.answers INNER JOIN FETCH t.subject WHERE t.id=:testId");
		q.setParameter("testId", testId);

		List<UserTest> ut = q.list();

		if (!ut.isEmpty())
			return (UserTest) q.list().get(0);
		else
			return null;
	}



	@Override
	public void saveTest(UserTest test) {
		
		Session s=sessionFactory.getCurrentSession();
		UserTest t1=(UserTest) s.get(UserTest.class, test.getId());
		t1.setStatus(1);
		t1.setModifiedDate(Utility.getCurrentDate());
		t1.setTotalUnAttempted(test.getTotalUnAttempted());
		t1.setTopicIds(test.getTopicIds());
		t1.setTotalCorrect(test.getTotalCorrect());
		t1.setTotalIncorrect(test.getTotalIncorrect());
		t1.setTotalQuestion(test.getTotalQuestion());
		t1.setPercentage(test.getPercentage());
		s.update(t1);
		
		
		
	}



	@Override
	public void saveTestDetail(UserTestDetail d) {
		Session s=sessionFactory.getCurrentSession();
		UserTestDetail d1=(UserTestDetail) s.get(UserTestDetail.class, d.getId());
		d1.setResult(d.getResult());
		d1.setAnswerIds(d.getAnswerIds());
		
		
	}
}
