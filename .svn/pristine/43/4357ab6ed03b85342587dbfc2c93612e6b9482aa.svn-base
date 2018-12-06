package com.flp.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.SchoolTeacherDao;
import com.flp.model.Answer;
import com.flp.model.Assessment;
import com.flp.model.AssessmentQuestion;
import com.flp.model.AssessmentTopicChapterDetails;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.Grade;
import com.flp.model.Module;
import com.flp.model.ModuleTopic;
import com.flp.model.Question;
import com.flp.model.QuestionComments;
import com.flp.model.QuestionTopic;
import com.flp.model.SchoolStudentTest;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;

import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.util.Utility;
import com.flp.vo.GlobalVO;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SchoolTeacherDaoImpl implements SchoolTeacherDao
{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Assessment> getUpcomingAssessmentDetails(UserForm loginUser)
	{
		// before new teacher dashboard
		// Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject left join fetch a.assignedAssessment " + " where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status = :status and a.createdBy=:createdBy and a.startDate > :date order by a.endDate");
		// end
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status = :status and a.createdBy=:createdBy and a.startDate > :date order by a.endDate");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("createdBy", loginUser.getId());
		query.setParameter("date", Utility.getCurrentDate());
		List<Assessment> upcomingAssessmentDetails = query.list();
		return upcomingAssessmentDetails;
	}

	@Override
	public List<StudentTeacherSession> getTeacherSubjectSection(long userId, long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from StudentTeacherSession sts inner join fetch sts.subject inner join fetch sts.section sec " + "inner join fetch sec.sessionSchoolGrade ssg inner join fetch ssg.grade where sts.userId=:userId and sts.sessionId=:sessionId and sts.status=:status ORDER BY ssg.gradeId");
		query.setLong("userId", userId);
		query.setLong("sessionId", sessionId);
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<Chapter> getChpater(long gradeId, long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(c) from Chapter c inner join fetch c.topics t where c.gradeId=:gradeId and c.subjectId=:subjectId and c.status=:status and t.status=:tstatus");
		query.setLong("gradeId", gradeId);
		query.setLong("subjectId", subjectId);
		query.setInteger("status", Chapter.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<QuestionTopic> getQuestionList(String topicIds, long userId, long schoolId, int i)
	{
		String q = null;
		switch (i)
		{
			// for self
			case 2:
				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where q.createdBy=" + userId + " and q.schoolId=" + schoolId + " and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";

				break;

			// for ctls
			case 3:
				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where q.createdBy=" + 3l + " AND q.schoolId is null " + "and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";

				break;

			// for sme
			case 4:
				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where q.createdBy !=" + userId + " and q.schoolId=" + schoolId + "and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";
				break;

			// self and ctls

			case 5:
				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where  (q.createdBy =" + userId + "or q.createdBy=" + 3l + ") And  (q.schoolId is null or q.schoolId=" + schoolId + ")" + " and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";
				break;

			case 6:// self and sme

				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where q.schoolId=" + schoolId + "and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";
				break;

			case 7:
				// ctls and sme

				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where  (q.createdBy !=" + userId + "or q.createdBy=" + 3l + ") AND  (q.schoolId is null or q.schoolId=" + schoolId + ")" + "and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";
				break;
			case 9:
				// self and sme and ctls

				q = "select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch q.cognitiveLevel cl inner join fetch qt.topic top inner join fetch top.chapter inner join fetch qt.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where ((q.schoolId is null and q.createdBy=" + 3l + ") or q.schoolId=" + schoolId + ")" + "and qt.status=" + QuestionTopic.STATUS_ACTIVE + "and qt.topic.id IN(" + topicIds + ") order by q.id DESC";

				break;

		}
		

		/* ======= if (userId != -1) q = "select distinct q " + "from Question q inner join fetch q.difficultyLevel " + "inner join fetch q.answers " + "left join fetch q.user where q.createdBy=" + userId + " AND q.id IN (" + "Select distinct(qt.question.id) from QuestionTopic qt where qt.status=" + QuestionTopic.STATUS_ACTIVE + " and qt.topic.id IN(" + topicIds + ")) order by q.id DESC"; else q =
		 * "select distinct q from Question q inner join fetch q.difficultyLevel  inner join fetch q.answers left join fetch q.user " + "where (q.schoolId is null or q.schoolId=" + schoolId + ") and q.id IN (" + "Select distinct(qt.question.id) " + "from QuestionTopic qt where qt.status=" + QuestionTopic.STATUS_ACTIVE + " and qt.topic.id IN(" + topicIds + ")) order by q.id DESC"; >>>>>>> .r3312 */
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		List<QuestionTopic> list = query.list();
		return list;

	}

	@Override
	public String createAssessment(Assessment assessment)
	{
		Session session = sessionFactory.getCurrentSession();
		long asessmentId = (Long) session.save(assessment);
		
		for (AssessmentQuestion aq : assessment.getAssessmentQuestion())
		{
			aq.setAssessmentId(asessmentId);
			session.save(aq);

		}

		// Assign test to students
		for (AssignedAssessment aa : assessment.getAssignedAssessment())
		{
			aa.setAssessmentId(asessmentId);
			session.save(aa);

		}

		String chpterIds = assessment.getChapterIds();
		String topicIds = assessment.getTopicIds();

		AssessmentTopicChapterDetails assTCD = new AssessmentTopicChapterDetails();
		assTCD.setTopicId(topicIds);
		assTCD.setChapterId(chpterIds);
		assTCD.setAssessmentId(asessmentId);
		session.save(assTCD);

		return "success";
	}

	@Override
	public List<Assessment> getAllAssessmentListbyUser(Long schoolId, Long sessionId, Long loginUserId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Assessment where schoolId=:schoolId and sessionId=:sessionId and createdBy=:createdBy and status=:status");
		query.setLong("schoolId", schoolId);
		query.setLong("sessionId", sessionId);
		query.setLong("createdBy", loginUserId);
		query.setInteger("status", Assessment.STATUS_ACTIVE);
		return query.list();
	}
	

	//===================================SchoolTeacherDaoImpl==================	
	@Override
		public long saveLoQuestion(Question q)
		{
			return (Long) sessionFactory.getCurrentSession().save(q);
		}


	@Override
		public long saveAnswerLoQuestion(Answer a)
		{
			return (Long) sessionFactory.getCurrentSession().save(a);
		}
	
	
	
//====================================================================
	@Override
	public void assignedAssessment(AssignedAssessment aa)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(aa);

	}

	@Override
	public Boolean isAlreadyAssigned(Long studentId, Long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from AssignedAssessment where userId=:userId and assessmentId=:assessmentId");
		query.setLong("userId", studentId);
		query.setLong("assessmentId", assessmentId);
		Long id = (Long) query.uniqueResult();
		if (id != null)
		{
			return true;
		}
		return false;
	}

	@Override
	public void publishedAssessment(Long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Assessment SET status=:status where id=:assessmentId");
		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("assessmentId", assessmentId);
		query.executeUpdate();
	}

	@Override
	public List<Topic> getTopicDetailsByChapter(Long subjectId, Long chapterId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Topic where subjectId=:subjectId and chapterId=:chapterId and status=:status");
		query.setLong("subjectId", subjectId);
		query.setLong("chapterId", chapterId);
		query.setInteger("status", Topic.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public Integer assignedTopicCountByTopic(Long topicId, Long userId)
	{
		Integer count = 0;
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(atr) from AssignTopicResources atr left join fetch atr.section s " + "inner join fetch s.studentTeacherSession sts" + " where sts.userRole=:userRole and sts.subjectId is NULL and sts.status=1 and atr.topicId=:topicId " + "and atr.assignBy=:userId and atr.status=:status and atr.type=:type");
		query.setInteger("userRole", User.USERTYPE_STUDENT);
		query.setLong("topicId", topicId);
		query.setLong("userId", userId);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_TOPIC);
		List<AssignTopicResources> lst = query.list();
		if (lst != null && lst.size() > 0)
		{
			for (AssignTopicResources atr : lst)
			{
				if (atr.getSection().getStudentTeacherSession() != null)
				{
					count = count + atr.getSection().getStudentTeacherSession().size();
				}
			}
		}
		return count;
	}

	@Override

	public List<AssignTopicResources> getTopicAssignedStudents(Long id, Long userId)
	{
		ArrayList<AssignTopicResources> assigntopic = new ArrayList<>();
		Query query = sessionFactory.getCurrentSession().createQuery("from AssignTopicResources atr left join fetch atr.section s " + "inner join fetch s.studentTeacherSession sts" + " where sts.userRole=:userRole and sts.subjectId is NULL and sts.status=1 and atr.topicId=:topicId " + "and atr.assignBy=:userId and atr.status=:status and atr.type=:type");

		query.setInteger("userRole", User.USERTYPE_STUDENT);
		query.setLong("topicId", id);
		query.setLong("userId", userId);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_TOPIC);
		List<AssignTopicResources> lst = query.list();

		return assigntopic;
	}

	@Override
	public void saveTopicResources(AssignTopicResources atr)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(atr);
	}

	@Override
	public Boolean isAlreadyAssignedTopic(AssignTopicResources atr)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from AssignTopicResources where sectionId=:sectionId and topicId=:topicId and type=:type");

		query.setLong("sectionId", atr.getSectionId());
		query.setLong("topicId", atr.getTopicId());
		query.setInteger("type", atr.getType());
		Long val = (Long) query.uniqueResult();
		if (val != null && val > 0l)
		{

			return true;
		}
		return false;
	}

	@Override
	public List<AssignTopicResources> getSectionsByTopicId(Long topicId, Integer isTopic)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(atr) from AssignTopicResources atr inner join fetch atr.section where atr.topicId=:topicId and atr.type=:type");
		query.setLong("topicId", topicId);
		query.setInteger("type", isTopic);
		return query.list();
	}

	@Override
	public void unAssignTopicFromSection(Long sectionId, Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("delete from AssignTopicResources where sectionId=:sectionId and topicId=:topicId and type=:type");
		query.setLong("sectionId", sectionId);
		query.setLong("topicId", topicId);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_TOPIC);
		query.executeUpdate();
	}

	@Override

	public List<Module> getModulesByTopic(Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from ModuleTopic mt inner join fetch mt.module  where mt.topicId=:topicId and mt.status=:status");
		query.setLong("topicId", topicId);
		query.setInteger("status", ModuleTopic.STATUS_ACTIVE);
		List<ModuleTopic> lst = query.list();
		List<Module> moduleList = new ArrayList<Module>();
		if (lst != null && lst.size() > 0)
		{
			for (ModuleTopic mt : lst)
			{
				moduleList.add(mt.getModule());
			}
		}
		return moduleList;
	}

	@Override
	public Integer assignedStudentCountByModuleId(Long moduleId, Long userId)
	{

		Integer count = 0;
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(atr) from AssignTopicResources atr left join fetch atr.section s " + "inner join fetch s.studentTeacherSession sts" + " where sts.userRole=:userRole and sts.subjectId is NULL and sts.status=1 and atr.resourceId=:resourceId " + "and atr.assignBy=:userId and atr.status=:status and atr.type=:type");
		query.setInteger("userRole", User.USERTYPE_STUDENT);
		query.setLong("resourceId", moduleId);
		query.setLong("userId", userId);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_RESOURCES);
		List<AssignTopicResources> lst = query.list();
		if (lst != null && lst.size() > 0)
		{
			for (AssignTopicResources atr : lst)
			{
				if (atr.getSection().getStudentTeacherSession() != null)
				{
					count = count + atr.getSection().getStudentTeacherSession().size();
				}
			}
		}
		return count;
	}

	@Override
	public Boolean isAlreadyAssignedModule(AssignTopicResources atr)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from AssignTopicResources where sectionId=:sectionId and topicId=:topicId and resourceId=:resourceId  and type=:type");
		query.setLong("sectionId", atr.getSectionId());
		query.setLong("topicId", atr.getTopicId());
		query.setLong("resourceId", atr.getResourceId());
		query.setInteger("type", atr.getType());
		Long val = (Long) query.uniqueResult();
		if (val != null && val > 0l)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<AssignTopicResources> getSectionsByModuleId(Long topicId, Long moduleId, Integer isResources)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(atr) from AssignTopicResources atr inner join fetch atr.section where atr.topicId=:topicId and atr.resourceId=:resourceId and atr.type=:type");
		query.setLong("topicId", topicId);
		query.setLong("resourceId", moduleId);
		query.setInteger("type", isResources);
		return query.list();
	}

	@Override
	public void unAssignModuleFromSection(Long sectionId, Long topicId, Long moduleId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("delete from AssignTopicResources where sectionId=:sectionId and topicId=:topicId and resourceId=:resourceId and type=:type");
		query.setLong("sectionId", sectionId);
		query.setLong("topicId", topicId);
		query.setLong("resourceId", moduleId);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_RESOURCES);
		query.executeUpdate();
	}

	@Override
	public List<AssignedAssessment> getAllAssignedAssessmentStudentList(Long asssessmentId, Long assignedBy)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from AssignedAssessment aa inner join fetch aa.user where aa.assignedBy=:assignedBy and aa.assessmentId=:assessmentId and aa.status=:status");
		query.setLong("assignedBy", assignedBy);
		query.setLong("assessmentId", asssessmentId);
		query.setInteger("status", AssignedAssessment.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public void unAssignAssessment(long assignBy, Long[] studentIds, Long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("delete from AssignedAssessment where assessmentId=:assessmentId and assignedBy=:assignedBy and userId in (:userIds)");
		query.setLong("assessmentId", assessmentId);
		query.setLong("assignedBy", assignBy);
		query.setParameterList("userIds", studentIds);
		query.executeUpdate();

	}

	@Override
	public Assessment getAssessment(long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select Distinct(ass) from Assessment ass inner join fetch ass.grade inner join fetch ass.subject inner join fetch ass.assessmentQuestion assQ inner join fetch assQ.question q inner join fetch q.answers where ass.id=:assessmentId order by assQ.serial");
		query.setParameter("assessmentId", assessmentId);
		query.setMaxResults(1);
		return (Assessment) query.uniqueResult();
	}

	@Override
	public void removeAssessment(Long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Assessment SET status=:status where id=:assessmentId");
		query.setInteger("status", Assessment.STATUS_INACTIVE);
		query.setLong("assessmentId", assessmentId);
		query.executeUpdate();
	}

	@Override
	public long saveQuestion(Question q)
	{
		return (Long) sessionFactory.getCurrentSession().save(q);
	}

	@Override
	public long saveAnswer(Answer a)
	{
		return (Long) sessionFactory.getCurrentSession().save(a);
	}

	@Override
	public List<Chapter> getChpatersByGradeAndSubjects(List<Long> gradeIds, List<Long> subjectIds, Long schoolId)
	{

		Session session = sessionFactory.getCurrentSession();
		String str = "SELECT distinct(c) FROM Chapter c INNER JOIN FETCH c.topics t where c.gradeId IN(:gradeIds) AND c.subjectId IN(:subjectIds) AND c.status = :status AND c.schoolId = :schoolId AND t.status=:tstatus order by c.serialOrder, t.serialOrder";
		
		Query query = session.createQuery(str);
		query.setParameter("schoolId", schoolId);
		query.setParameter("status", Chapter.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameterList("gradeIds", gradeIds);
		query.setParameterList("subjectIds", subjectIds);

		return query.list();
	}

	@Override
	public Map<Long, Integer> getQuestionCountByTopicId(List<Long> topicIdList)
	{
		
		Session session = sessionFactory.getCurrentSession();
		String str = "SELECT COUNT(id),topicId FROM QuestionTopic WHERE topicId IN (:topicId) and status=:status group by topicId";
		
		Query query = session.createQuery(str);
		query.setParameterList("topicId", topicIdList);
		// query.setParameter("topicId", topicIdL);
		query.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		List<Object[]> lst = query.list();

		if (!lst.isEmpty())
		{
			Map<Long, Integer> questionCountWithTopic = new HashMap<Long, Integer>();
			for (Object[] objArr : lst)
			{
				questionCountWithTopic.put(Long.valueOf(objArr[1].toString()), Integer.parseInt(objArr[0].toString()));

			}
			return questionCountWithTopic;
		}
		return null;

	}

	@Override
	public Map<Long, Integer> getModuleCountByTopicId(List<Long> topicIdList)
	{

		Session session = sessionFactory.getCurrentSession();
		String str = "SELECT COUNT(id),topicId FROM ModuleTopic WHERE topicId IN (:topicId) and status=:status group by topicId";
		Query query = session.createQuery(str);
		query.setParameterList("topicId", topicIdList);

		query.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		List<Object[]> lst = query.list();

		if (!lst.isEmpty())
		{
			Map<Long, Integer> questionCountWithTopic = new HashMap<Long, Integer>();
			for (Object[] objArr : lst)
			{
				questionCountWithTopic.put(Long.valueOf(objArr[1].toString()), Integer.parseInt(objArr[0].toString()));

			}
			return questionCountWithTopic;
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionTopic> getAllQuestionsByTopic(Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch q.answers a left join fetch q.questionComment left join fetch q.user where qt.topicId=:topicId and qt.loId=:loId and qt.status=:status ORDER BY qt.id DESC");
		query.setLong("topicId", topicId);
		query.setLong("loId", 20l);
		query.setInteger("status", QuestionTopic.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<Assessment> getCompletedTest(UserForm loginUser)
	{
		// before new teacher dashboard
		// Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject inner join fetch a.assignedAssessment left join fetch a.studentTest " + "where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status =:status and a.createdBy=:createdBy and a.startDate <= :date order by a.endDate DESC ");
		// end
		//Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status =:status and a.createdBy=:createdBy and a.startDate <= :date order by a.endDate ");
		
		 Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject inner join fetch a.assignedAssessment left join fetch a.studentTest " + "where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status =:status and a.createdBy=:createdBy and a.startDate <= :date order by a.endDate DESC ");
			
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("createdBy", loginUser.getId());
		query.setParameter("date", Utility.getCurrentDate());
		return query.list();
	}

	@Override
	public List<Assessment> getCreatedTest(UserForm loginUser)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject left join fetch a.assignedAssessment " + " where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status!=:status and a.createdBy=:createdBy and a.endDate>:endDate  order by a.id desc");

		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setInteger("status", Assessment.STATUS_INACTIVE);
		query.setLong("createdBy", loginUser.getId());
		query.setParameter("endDate", Utility.getCurrentDate());

		return query.list();
	}

	@Override
	public void updateAssessment(Assessment ass)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Assessment SET testName=:testName, startDate=:startDate, endDate=:endDate where id=:assessmentId");
		query.setParameter("testName", ass.getTestName());
		query.setParameter("startDate", ass.getStartDate());
		query.setParameter("endDate", ass.getEndDate());
		query.setParameter("assessmentId", ass.getId());
		query.executeUpdate();

	}

	@Override
	public Boolean updatetopicdetails(Long id, String name)
	{
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("Update Topic set name=:name ,modifiedDate=:modifiedDate where id=:id ");
		query.setParameter("name", name);
		query.setParameter("id", id);
		query.setParameter("modifiedDate", Utility.getCurrentDate());
		query.executeUpdate();
		return true;

	}

	@Override
	public Boolean updatechapterdetails(Long id, String name)
	{
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("Update Chapter set name=:name ,modifiedDate=:modifiedDate where id=:id ");
		query.setParameter("name", name);
		query.setParameter("id", id);
		query.setParameter("modifiedDate", Utility.getCurrentDate());
		query.executeUpdate();

		return true;

	}

	@Override
	public boolean isMappedToTest(Long questionId)
	{
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(1) FROM t_assessment_question WHERE f_questionId=:questionId");
		query.setParameter("questionId", questionId);
		query.setMaxResults(1);
		int count = ((BigInteger) query.uniqueResult()).intValue();
		if (count > 0)
			return false;
		else
			return true;
	}

	@Override
	public List<Assessment> getAssessmentByLimitDate(UserForm loginUser, Date sDate, Date endDate)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status = :status and a.createdBy=:createdBy order by a.endDate desc");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("createdBy", loginUser.getId());
		/* query.setParameter("stDate", sDate); query.setParameter("edDate", endDate); */
		List<Assessment> upcomingAssessmentDetails = query.list();
		return upcomingAssessmentDetails;
	}

	@Override
	public List<SchoolStudentTest> getStudentAssessmentDetailsById(Long assessmentId)
	{
		  Query query = sessionFactory.getCurrentSession().createQuery("select distinct(sst) From SchoolStudentTest sst inner join fetch sst.user where sst.assessmentId=:assessmentId and sst.status=:status order by sst.percentage desc");
	    //  Query query = sessionFactory.getCurrentSession().createQuery("select distinct(sst) From SchoolStudentTest sst inner join fetch sst.user   order by sst.percentage desc");
		 
	    query.setParameter("assessmentId", assessmentId);
		 
		  query.setInteger("status", SchoolStudentTest.STATUS_ACTIVE); 
		List<SchoolStudentTest> studentTestDetails = query.list();
		return studentTestDetails;
	}

	@Override
	public List<SubjectSection> getLearningReportForSubjects(Long sectionId, Long schoolId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(ss) From SubjectSection ss left join fetch ss.subject s where ss.sectionId=:sectionId  and s.schoolId=:schoolId and ss.status=:status and s.status=:sstatus");
		query.setParameter("sectionId", sectionId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("status", SubjectSection.STATUS_ACTIVE);
		query.setParameter("sstatus", Subject.STATUS_ACTIVE);
		List<SubjectSection> subjectSectionList = query.list();
		return subjectSectionList;
	}

	@Override
	public Integer getTotalQuestionCountBySubject(Long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(q) From QuestionTopic q inner join fetch q.topic t inner join fetch t.chapter c where c.subjectId=:subjectId and q.status=:status and t.status=:tstatus and c.status=:cstatus");
		query.setParameter("subjectId", subjectId);
		query.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameter("cstatus", Chapter.STATUS_ACTIVE);
		List<SubjectSection> questionList = query.list();
		if (!questionList.isEmpty())
		{
			return questionList.size();
		}
		return 0;
	}

	@Override
	public List<Chapter> getLearningReportForChapters(Long subjectId, Long schoolId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(c) From Chapter c where c.subjectId=:subjectId and c.schoolId=:schoolId and c.status=:status");
		query.setParameter("subjectId", subjectId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("status", Chapter.STATUS_ACTIVE);
		List<Chapter> chapterLst = query.list();
		return chapterLst;
	}

	@Override
	public Integer getTotalQuestionCountByChapter(Long chapterId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(q) From QuestionTopic q inner join fetch q.topic t  where t.chapterId=:chapterId and q.status=:status and t.status=:tstatus");
		query.setParameter("chapterId", chapterId);
		query.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		List<SubjectSection> questionList = query.list();
		if (!questionList.isEmpty())
		{
			return questionList.size();
		}
		return 0;
	}

	@Override
	public List<Topic> getLearningReportForTopic(Long chapterId, Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(t) From Topic t where t.chapterId=:chapterId and t.schoolId=:schoolId and t.status=:status");
		query.setParameter("chapterId", chapterId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("status", Topic.STATUS_ACTIVE);
		List<Topic> topicLst = query.list();
		return topicLst;
	}

	@Override
	public Integer getTotalQuestionCountByTopic(Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(q) From QuestionTopic q where q.topicId=:topicId and q.status=:status");
		query.setParameter("topicId", topicId);
		query.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		List<SubjectSection> questionList = query.list();
		if (!questionList.isEmpty())
		{
			return questionList.size();
		}
		return 0;
	}

	@Override
	public List<StudentTeacherSession> getStudentListBySubjectId(Long subjectId, Long sectionId, Long schoolId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(sts) From StudentTeacherSession sts inner join fetch sts.user u" + " where sts.schoolId=:schoolId and sts.sessionId=:sessionId and sts.sectionId=:sectionId and sts.status=:status and " + "sts.userRole=:userRole and sts.subjectId is NULL and sts.userId not in(select userId from StudentTeacherSession where subjectId=:subjectId and sectionId=:sectionId and status=:status)");
		query.setParameter("schoolId", schoolId);
		query.setParameter("sessionId", sessionId);
		query.setParameter("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setParameter("sectionId", sectionId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("userRole", User.USERTYPE_STUDENT);

		List<StudentTeacherSession> lst = query.list();
		return lst;
	}

	@Override
	public List<UserQuestionAnswer> getUserQuestionAnswerList(String reportType, Long id, Long gradeId, Long schoolId)
	{
		Query query = null;
		if (reportType.equals("subjectwise"))
		{
			query = sessionFactory.getCurrentSession().createQuery("select distinct(uqa) from UserQuestionAnswer uqa inner join fetch uqa.topic t inner join fetch t.chapter c " + "where uqa.isGradeId=:gradeId and uqa.schoolId=:schoolId and c.subjectId=:subjectId and uqa.status=:status");
			query.setParameter("gradeId", gradeId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("subjectId", id);
			query.setParameter("status", UserQuestionAnswer.STATUS_ACTIVE);
		}
		else if (reportType.equals("chapterwise"))
		{
			query = sessionFactory.getCurrentSession().createQuery("select distinct(uqa) from UserQuestionAnswer uqa inner join fetch uqa.topic t " + "where uqa.isGradeId=:gradeId and uqa.schoolId=:schoolId and t.chapterId=:chapterId and uqa.status=:status");
			query.setParameter("gradeId", gradeId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("chapterId", id);
			query.setParameter("status", UserQuestionAnswer.STATUS_ACTIVE);
		}
		else if (reportType.equals("topicwise"))
		{
			query = sessionFactory.getCurrentSession().createQuery("select distinct(uqa) from UserQuestionAnswer uqa " + "where uqa.isGradeId=:gradeId and uqa.schoolId=:schoolId and uqa.topicId=:topicId and uqa.status=:status");
			query.setParameter("gradeId", gradeId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("topicId", id);
			query.setParameter("status", UserQuestionAnswer.STATUS_ACTIVE);
		}
		return query.list();
	}

	@Override

	public Long getTopicIdByGradeSubjectChapterTopic(String boardName, String gradeName, String subjectName, String chapterName, String topicName)
	{
		
		
		String sql="SELECT t.`f_id` FROM t_topic t  INNER JOIN `t_chapter` c ON c.f_id=t.`f_chapter_id` INNER JOIN `t_subject` s ON s.`f_id`=c.`f_subjectId`INNER JOIN `t_grade` g ON c.`f_grade_id`=g.f_id INNER JOIN `t_board` b ON b.f_id=c.`f_board_id` "
				+ "WHERE t.f_name='"+topicName+"' AND c.f_name='"+chapterName+"' AND s.f_name='"+subjectName+"' AND t.f_status='"+Topic.STATUS_ACTIVE+"' AND c.f_status='"+Chapter.STATUS_ACTIVE+"' AND g.f_status='"+Grade.STATUS_ACTIVE+"' AND s.f_status='"+Subject.STATUS_ACTIVE+"' AND g.f_name='"+gradeName+"' AND b.`f_name`='"+boardName+"'";
		/* query.setParameter("topicName", topicName); query.setParameter("chapterName", chapterName); query.setParameter("subjectName", subjectName); query.setParameter("gradeName", gradeName); */
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				
		query.setMaxResults(1);
		BigInteger bi = (BigInteger) query.uniqueResult();
		if (bi != null)
			return Long.valueOf(bi.toString());
		return null;
	}

	@Override
	public Boolean addQuestionComment(QuestionComments qc)
	{
		sessionFactory.getCurrentSession().save(qc);
		return true;
	}

	@Override
	public List<QuestionComments> getAllTeacherCommentsByQuestId(Long questId, Long userId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(qc) FROM QuestionComments qc inner join fetch qc.user WHERE qc.status=:status and qc.userId=:userId and qc.questionId=:questionId ORDER BY qc.updatedOn DESC");
		query.setParameter("status", QuestionComments.STATUS_ACTIVE);
		query.setParameter("userId", userId);
		query.setParameter("questionId", questId);

		return query.list();
	}

	@Override
	public List<String> getQuestionListforAuto(String topicIds, long userId, Long totalQuestion, Long schoolId, Integer createBy, Integer eQuest, Integer mQuest, Integer dQuest)
	{
		List<String> questions = new ArrayList<String>();
		Query q = sessionFactory.getCurrentSession().createSQLQuery("call getAutoQuestionList(:topicIds,:userId,:totalQuestion,:schoolId,:createBy,:easyQ,:midQ,:diffQ)");

		q.setParameter("topicIds", topicIds);
		q.setParameter("userId", userId);
		q.setParameter("totalQuestion", totalQuestion);
		q.setParameter("schoolId", schoolId);
		q.setParameter("createBy", createBy);
		q.setParameter("easyQ", eQuest);
		q.setParameter("midQ", mQuest);
		q.setParameter("diffQ", dQuest);

		String question = (String) q.list().get(0);
		if (question == null)
		{

			questions.add("Only 0 question are present in question bank!!!");
		}
		else if (question.trim().contains("-1") || question.replaceAll("\\s", "").contains(" "))
		{
			String[] qPresent = question.split("\\+");
			questions.add("Only " + qPresent[1] + " questions are present in question bank!!");
		}

		else
		{
			String[] questionIds = question.split(",");
			questions.addAll(Arrays.asList(questionIds));
		}

		return questions;

	}

	@Override
	public List<StudentTeacherSession> getTeacherGradeSubjectDetails(Long userId, Long schoolId)
	{
		String sql = "SELECT DISTINCT(sts) from StudentTeacherSession sts INNER JOIN fetch sts.subject INNER JOIN fetch sts.section sec INNER JOIN fetch " + "sec.sessionSchoolGrade ssg INNER JOIN fetch ssg.grade where sts.userId=:userId AND sts.schoolId=:schoolId AND sts.status=:status";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("status", StudentTeacherSession.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public Map<Long, Integer> getStudentCountBySectionIds(List<Long> sectionIds, Long schoolId, List<Long> subjectIds)
	{
		String sql = "SELECT COUNT(id), sectionId FROM User WHERE status=:status AND userType=:userType AND schoolId=:schoolId AND sectionId in (:sectionIds) " + "and id not in (SELECT userId FROM StudentTeacherSession WHERE sectionId IN (:sectionIds) AND userRole=:userRole AND subjectId IN (:subjectIds) AND status=:sstatus) GROUP BY sectionId";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("status", User.STATUS_ACTIVE);
		query.setParameter("userType", User.USERTYPE_STUDENT);
		query.setParameter("schoolId", schoolId);
		query.setParameter("userRole", User.USERTYPE_STUDENT);
		query.setParameterList("sectionIds", sectionIds);
		query.setParameterList("subjectIds", subjectIds);
		query.setParameter("sstatus", StudentTeacherSession.STATUS_ACTIVE);
		List<Object[]> lst = query.list();
		if (!lst.isEmpty())
		{
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			for (Object[] o : lst)
			{
				map.put(Long.valueOf(o[1].toString()), Integer.valueOf(o[0].toString()));
			}
			return map;
		}
		return null;
	}

	@Override
	public List getTeacherSubjectStudat(List<Long> sectionIds, Long schoolId, List<Long> subjectIds)
	{
		Long l1 = 78L;
		Integer l2 = 2;
		String sql = "select firstName FROM User WHERE sectionId=:l AND userType=:ll";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		// query.setParameter("userIda",163L);
		query.setParameter("ll", l2);
		query.setParameter("l", l1);
		List lst = query.list();
		if (!lst.isEmpty())
		{
			/* Map<Long, Integer> map=new HashMap<Long, Integer>(); for(Object[] o:lst){ map.put(Long.valueOf(o[1].toString()), Integer.valueOf(o[0].toString())); } */
			return lst;
		}
		return null;
	}

	@Override
	public Long getTypeId(String qtDifficultyLevel)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("SELECT id FROM QuestionType WHERE name =:qtDifficultyLevel");
		q.setParameter("qtDifficultyLevel", qtDifficultyLevel);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long getQuestionTopicId(Long topicId, Long quesId, Long difficultyId, Long cognitiveId)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("SELECT id FROM QuestionTopic WHERE questionId=:questionId AND topicId=:topicId AND cognitiveLevelId=:cognitiveLevelId AND difficultyLevelId=:difficultyLevelId AND status=:status");
		q.setParameter("questionId", quesId);
		q.setParameter("topicId", topicId);
		q.setParameter("difficultyLevelId", difficultyId);
		q.setParameter("cognitiveLevelId", cognitiveId);
		q.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		return (Long) q.uniqueResult();
	}

	@Override
	public List<Assessment> getUpcomingAssessment(UserForm loginUser, Long gradeId, Long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.assignedAssessment left join fetch a.studentTest" + " where a.schoolId=:schoolId and a.sessionId=:sessionId and a.gradeId = :gradeId and a.subjectId = :subjectId and a.status = :status and a.createdBy=:createdBy and a.startDate > :date order by a.endDate DESC");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setLong("gradeId", gradeId);
		query.setLong("subjectId", subjectId);
		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("createdBy", loginUser.getId());
		query.setParameter("date", Utility.getCurrentDate());
		List<Assessment> upcomingAssessmentDetails = query.list();
		return upcomingAssessmentDetails;
	}

	@Override
	public List<Assessment> getCompletedAssessment(UserForm loginUser, Long gradeId, Long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.assignedAssessment left join fetch a.studentTest  " + "where a.schoolId=:schoolId and a.sessionId=:sessionId and a.gradeId=:gradeId and a.subjectId=:subjectId and a.status =:status and a.createdBy=:createdBy and a.startDate <= :date order by a.endDate DESC ");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());

		query.setLong("gradeId", gradeId);
		query.setLong("subjectId", subjectId);

		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("createdBy", loginUser.getId());
		query.setParameter("date", Utility.getCurrentDate());
		return query.list();
	}

	@Override
	public List<User> getUnassignStudent(Long subId, Long secId)
	{

		Integer Role = 2;
		String st = " FROM User WHERE " + "sectionId =:secId  AND id" + " NOT IN (SELECT userId FROM StudentTeacherSession WHERE sectionId=:secId AND userRole=:usrRole AND subjectId=:subId )";

		Query query = sessionFactory.getCurrentSession().createQuery(st);
		query.setParameter("subId", subId);
		query.setParameter("secId", secId);
		query.setParameter("usrRole", Role);
		// query.setParameter("usrStatus", User.STATUS_ACTIVE);
		// query.setParameter("stsStatus", StudentTeacherSession.STATUS_ACTIVE);


		return query.list();
	}

	@Override
	public List<Assessment> getAssignmentDetail(Long gradeId, Long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(asmnt) from Assessment asmnt left join fetch asmnt.assessmentTopicChapterDetails inner join fetch asmnt.assignedAssessment a inner join fetch a.user where  asmnt.gradeId=:gId  AND asmnt.subjectId=:sId AND asmnt.type=:aType AND asmnt.status=:status order by asmnt.endDate desc");
		query.setParameter("gId", gradeId);
		query.setParameter("sId", subjectId);
		query.setParameter("aType", Assessment.ASSIGNED_TEST);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
		return query.list();
	}

	@Override
	public List<Long> getAllTopicIdsByChapter(Long subjectId, Long chapterId)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("select id from Topic where subjectId=:subjectId and chapterId=:chapterId and status=:status");
		query.setLong("subjectId", subjectId);
		query.setLong("chapterId", chapterId);
		query.setInteger("status", Topic.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public Long getStudentCountBySectionIds(Long subjectId, Long secId)
	{
		Integer Role = 2;
		String st = "select count(id) FROM User WHERE " + "sectionId =:secId  AND id" + " NOT IN (SELECT userId FROM StudentTeacherSession WHERE sectionId=:secId AND userRole=:usrRole AND subjectId=:subId )";

		Query query = sessionFactory.getCurrentSession().createQuery(st);
		query.setParameter("subId", subjectId);
		query.setParameter("secId", secId);
		query.setParameter("usrRole", User.USERTYPE_STUDENT);

		return (Long) query.uniqueResult();
	}

	@Override
	public List<AssignTopicResources> getAllAssignTopicListByUser(List<Long> topicIds, Long userId, Long subjectId, Long sectionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select Distinct(atr) from AssignTopicResources atr inner join fetch atr.topic t where atr.topicId in (:topicIds) and atr.assignBy=:userId and atr.status=:status and atr.type=:type and atr.subjectId=:subjectId and atr.sectionId=:sectionId and t.status=:tstatus ");
		query.setParameterList("topicIds", topicIds);
		query.setLong("userId", userId);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_TOPIC);
		query.setParameter("subjectId", subjectId);
		query.setParameter("sectionId", sectionId);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<AssignTopicResources> getAllAssignModuleListByUser(Long topicId, Long userId, Long subjectId, Long secId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select Distinct(atr) from AssignTopicResources atr inner join fetch atr.module m inner join m.resourceType where atr.topicId=:topicId and atr.assignBy=:userId and atr.status=:status and atr.type=:type and atr.subjectId=:subjectId and atr.sectionId=:sectionId and atr.resourceId is not null and m.status=:mstatus");
		query.setParameter("topicId", topicId);
		query.setLong("userId", userId);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_RESOURCES);
		query.setParameter("subjectId", subjectId);
		query.setParameter("sectionId", secId);
		query.setParameter("mstatus", Module.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<Long> getAllTopicIdsInSubject(Long subjectId, Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select t.id from Topic t inner join t.chapter c where t.subjectId=:subjectId and t.status=:status and c.status=:cstatus");
		query.setParameter("subjectId", subjectId);
		query.setParameter("status", Topic.STATUS_ACTIVE);
		query.setParameter("cstatus", Chapter.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public Long getAllQuestionCountByTopicIds(List<Long> topicIds)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select count(id) from QuestionTopic where topicId in (:topicIds) and status=:status");
		query.setParameterList("topicIds", topicIds);
		query.setParameter("status", QuestionTopic.STATUS_ACTIVE);
		Long count = (Long) query.uniqueResult();
		if (count != null && count > 0)
		{
			return count;
		}
		return 0l;
	}

	@Override
	public Assessment getAllTestDetails(UserForm loginUser, Long grdId, Long subId, Long assesmentId)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) from Assessment a inner join fetch a.grade gr inner join fetch a.subject sb inner join fetch a.studentTest st inner join fetch a.assessmentQuestion aq inner join fetch a.assignedAssessment asassmnt inner join fetch asassmnt.user  uuu where a.schoolId=:schoolId and a.sessionId=:sessionId and a.gradeId=:grdId and a.subjectId=:subId and  a.createdBy=:createdBy and st.assessmentId=:assmntId and a.status=:status order by asassmnt.user.firstName");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setParameter("grdId", grdId);
		query.setParameter("subId", subId);
		query.setParameter("assmntId", assesmentId);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
		
		query.setLong("createdBy", loginUser.getId());
		
		
		
		
		query.setMaxResults(1);
		return (Assessment) query.uniqueResult();
	}
	@Override
	public Assessment getUnattemtedAllTestDetails(UserForm loginUser, Long grdId, Long subId, Long assesmentId)
	{

	/*	Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) from Assessment a inner join fetch a.grade gr inner join fetch a.subject sb inner join fetch a.studentTest st inner join fetch a.assessmentQuestion aq inner join fetch a.assignedAssessment asassmnt inner join fetch asassmnt.user  where a.schoolId=:schoolId and a.sessionId=:sessionId and a.gradeId=:grdId and a.subjectId=:subId and  a.createdBy=:createdBy ");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setParameter("grdId", grdId);
		query.setParameter("subId", subId);
		//query.setParameter("assmntId", assesmentId);
		//query.setParameter("status", Assessment.STATUS_INACTIVE);
		query.setLong("createdBy", loginUser.getId());
		query.setMaxResults(1);*/
		
		Query query = sessionFactory.getCurrentSession().
		createQuery("SELECT DISTINCT(a) FROM Assessment a INNER JOIN FETCH a.grade gr INNER JOIN FETCH  a.subject sb INNER JOIN FETCH a.studentTest st INNER JOIN FETCH a.assessmentQuestion aq INNER JOIN FETCH a.assignedAssessment asassmnt INNER JOIN FETCH asassmnt.user  uuu  WHERE a.schoolId=:schoolId AND a.sessionId=:sessionId AND a.gradeId=:grdId AND a.subjectId=:subId AND   a.createdBy=:createdBy AND st.assessmentId=:assmntId AND a.status=:status        AND  asassmnt.user.id NOT IN ( SELECT  sst.studentId  FROM  SchoolStudentTest   sst     WHERE  a.schoolId=:schoolId AND a.sessionId=:sessionId  AND a.subjectId=:subId AND    st.assessmentId=:assmntId AND a.status=:status   )");
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setParameter("grdId", grdId);
		query.setParameter("subId", subId);
		query.setParameter("assmntId", assesmentId);
		query.setParameter("status", Assessment.STATUS_INACTIVE);
		query.setLong("createdBy", loginUser.getId());
		
		
		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		//query.setParameter("grdId", grdId);
		query.setParameter("subId", subId);
		query.setParameter("assmntId", assesmentId);
		query.setParameter("status", Assessment.STATUS_INACTIVE);
		//query.setLong("createdBy", loginUser.getId());
		query.setMaxResults(1); 
		
		
		
		
		
		return (Assessment) query.uniqueResult();
	}
	
	@Override
	public Boolean unassignStudentfromAssignment(Long gId, Long sId, Long assmentId, Long[] unassignUserLst)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("delete from AssignedAssessment where assessmentId=:assmntId  and userId in (:userIds)");

		query.setParameter("assmntId", assmentId);
		query.setParameterList("userIds", unassignUserLst);
		query.executeUpdate();
		return true;
	}

	@Override
	public List<Long> getMarkTopics(Long userId, Long subjectId)
	{

		Session session = sessionFactory.getCurrentSession();
		String str = "select topicId FROM TeacherMarkTopicDetails where userId=:userId and subjectId=:subjectId";
		Query query = session.createQuery(str);
		query.setParameter("userId", userId);
		query.setParameter("subjectId", subjectId);

		List<Long> list = query.list();

		
		return list;

	}
	
	@Override
	public List<Long> getMarkTopicsContentPermission(Long userId, Long subjectId)
	{

		Session session = sessionFactory.getCurrentSession();
		String str = "select topicId FROM TeacherContentPermission where userId=:userId and subjectId=:subjectId and contentPermission=:contentPermission";
		Query query = session.createQuery(str);
		query.setParameter("userId", userId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("contentPermission", 1);
		
		List<Long> list = query.list();

		 return list;

	}

	@Override
	public Map<Long, Integer> getTopicCountBySubjectId(List<Long> subjectIdsSet, Long gradeId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select tt.subjectId, Count(tt.id) from Topic tt where tt.subjectId in (:subjectId) and tt.gradeId=:gradeId and tt.status=:status group by tt.subjectId");
		query.setParameterList("subjectId", subjectIdsSet);
		query.setParameter("gradeId", gradeId);
		query.setParameter("status", Topic.STATUS_ACTIVE);
		// query.setParameter("cstatus", Chapter.STATUS_ACTIVE);
		List<Object[]> obj = query.list();
		if (!obj.isEmpty())
		{
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			for (Object[] o : obj)
			{
				map.put(Long.parseLong(o[0].toString()), Integer.parseInt(o[1].toString()));
			}
			return map;
		}
		return null;
	}

	@Override
	public Map<Long, Integer> getTeacherProgressTopicCountBySubjectId(List<Long> subIds, Long userId, Long sectionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select count(topicId), subjectId from TeacherMarkTopicDetails where subjectId in (:subjectIds) and userId=:userId and sectionId=:sectionId group by subjectId");
		query.setParameterList("subjectIds", subIds);
		query.setParameter("userId", userId);
		query.setParameter("sectionId", sectionId);
		List<Object[]> obj = query.list();
		if (!obj.isEmpty())
		{
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			for (Object[] o : obj)
			{
				map.put(Long.parseLong(o[1].toString()), Integer.parseInt(o[0].toString()));
			}
			return map;
		}
		return null;
	}

	@Override
	public Subject getSubjectObjById(Long subId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Subject where id=:subjectId");
		query.setParameter("subjectId", subId);
		query.setMaxResults(1);
		return (Subject) query.uniqueResult();

	}

	@Override
	public String updateModuleResources(GlobalVO globalVO) throws IOException
	{

		Query query = null;
		if (globalVO.getContentType() == Module.CONTENT_TYPE_RESOURCE)
		{
			// 1 media Resources
			query = sessionFactory.getCurrentSession().createQuery("UPDATE Module SET name=:name , description=:description , gradeTypeId=:gradeTypeId  where id=:id");
			query.setParameter("name", globalVO.getModuleName());
			query.setParameter("description", globalVO.getModuleDescription());
			query.setParameter("gradeTypeId", globalVO.getGradeTypeId());
		}
		else if (globalVO.getContentType() == Module.CONTENT_TYPE_WEB_RESOURCE)
		{
			// 2 Web Resources
			query = sessionFactory.getCurrentSession().createQuery("UPDATE Module SET name=:name , contentUrl=:contentUrl , resourceTypeId=:resourceTypeId  where id=:id");
			query.setParameter("name", globalVO.getModuleName());
			query.setParameter("contentUrl", globalVO.getContentUrl());
			query.setParameter("resourceTypeId", globalVO.getResourceTypeId());
		}
		else if (globalVO.getContentType() == Module.CONTENT_TYPE_DOCUMENT)
		{
			// 3 Document Library
			query = sessionFactory.getCurrentSession().createQuery("UPDATE Module SET name=:name , moduleType=:moduleType , description=:description  where id=:id");
			query.setParameter("name", globalVO.getModuleName());
			query.setParameter("moduleType", globalVO.getModuleType());
			query.setParameter("description", globalVO.getModuleDescription());
		}
		query.setParameter("id", globalVO.getModuleId());
		int i = query.executeUpdate();
		if (i != 0 && i > 0)
		{
			return "1";
		}
		else
		{
			return "0";
		}

	}

	@Override
	public com.flp.model.Session getSessionObj(Long sessionId)
	{

		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Session where startDate <=:currentDate and endDate >=:currentDate";

		Query query = session.createQuery(str);
		query.setParameter("currentDate", Utility.getCurrentDate());

		query.setMaxResults(1);

		return (com.flp.model.Session) query.uniqueResult();
	}

	@Override
	public List<User> getStudentCompletedAssessment(UserForm loginUser, Long grdId, Long subId, String search, Long secId)
	{
		// Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) from Assessment a inner join fetch a.studentTest inner join fetch a.subject inner join fetch a.assignedAssessment asg inner join fetch asg.user usr where a.schoolId=:schoolId and a.sessionId=:sessionId and a.gradeId=:gradeId and a.subjectId=:subjectId and a.status=:status and a.createdBy=:createdBy and usr.firstName LIKE :nameSearch and a.startDate < :date order by a.endDate DESC");

		/* Query query=sessionFactory.getCurrentSession().createQuery("from User usr inner join fetch usr.grade g where usr.firstName LIKE :nameSearch and usr.sectionId=:sectnId and " +"usr.id NOT IN(select st.studentId from SchoolStudentTest st inner join st.assessment a where a.id=st.assessmentId and" +" st.subjectId=:subjId and a.gradeId=:gradId and a.schoolId=:schoolId and a.sessionId=:sessionId and a.status=:status and a.createdBy=:createdBy and" +
		 * " a.startDate < :date order by a.endDate DESC)"); */

		String st = " FROM User usr inner join fetch usr.grade g  WHERE " + " usr.firstName LIKE :nameSearch and usr.sectionId =:sectnId  AND usr.id" + " NOT IN (SELECT userId FROM StudentTeacherSession WHERE sectionId=:sectnId AND userRole=:usrRole AND subjectId=:subjId )";
		Query query = sessionFactory.getCurrentSession().createQuery(st);

		query.setParameter("nameSearch", search + "%");

		// query.setLong("gradId", grdId);
		query.setLong("subjId", subId);
		query.setLong("sectnId", secId);
		query.setParameter("usrRole", User.USERTYPE_STUDENT);

		return query.list();
	}

	/* @Override public List<SchoolStudentTest> searchStudentTestDetail(UserForm loginUser, Long grdId, Long subId, String search, Long secId, List<Long> userids) {
	 * 
	 * Query query = sessionFactory.getCurrentSession().createQuery("from SchoolStudentTest st inner join fetch st.assessment a where st.studentId IN(:userids) and  a.id=st.assessmentId and" +" st.subjectId=:subjId and a.gradeId=:gradId and a.schoolId=:schoolId and a.sessionId=:sessionId and a.status=:status and a.createdBy=:createdBy and" + " a.startDate < :date order by a.endDate DESC");
	 * 
	 * query.setLong("schoolId", loginUser.getSchoolId()); query.setLong("sessionId", loginUser.getSessionId()); query.setLong("gradId", grdId); query.setLong("subjId", subId); //query.setLong("sectnId", secId); query.setParameterList("userids", userids); query.setInteger("status", Assessment.STATUS_PUBLISHED); query.setLong("createdBy", loginUser.getId()); query.setParameter("date", Utility.getCurrentDate());
	 * 
	 * 
	 * 
	 * return query.list(); } */

	@Override
	public List<SchoolStudentTest> searchStudentTestDetail(UserForm loginUser, Long grdId, Long subId, Long secId, Long studentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from SchoolStudentTest st inner join fetch st.assessment a left join fetch a.assessmentTopicChapterDetails where st.studentId=:stuId and a.id=st.assessmentId and"
				+" st.subjectId=:subjId and a.gradeId=:gradId and a.schoolId=:schoolId and a.sessionId=:sessionId and a.status=:status and a.createdBy=:createdBy and"
				+ " a.startDate < :date order by a.endDate DESC");

		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setLong("gradId", grdId);
		query.setLong("subjId", subId);
		// query.setLong("sectnId", secId);
		query.setParameter("stuId", studentId);
		query.setInteger("status", Assessment.STATUS_PUBLISHED);
		query.setLong("createdBy", loginUser.getId());
		query.setParameter("date", Utility.getCurrentDate());

		return query.list();
	}

	@Override
	public Integer updateUpcomingAssessment(Assessment ass)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Assessment SET testName=:testName, startDate=:startDate, endDate=:endDate where id=:assessmentId");
		query.setParameter("testName", ass.getTestName());
		query.setParameter("startDate", ass.getStartDate());
		query.setParameter("endDate", ass.getEndDate());
		query.setParameter("assessmentId", ass.getId());
		query.executeUpdate();
		
		return 1;
	}

	@Override
	public List<Long> getAllAssignedTopicByTopicIds(List<Long> topicIdList, Long secId, Long grdId, Long subId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select topicId from AssignTopicResources where topicId in (:topicIds) and sectionId=:sectionId and gradeId=:gradeId"
				+ " and subjectId=:subjectId and sectionId=:sectionId").setParameterList("topicIds", topicIdList).setParameter("sectionId", secId).setParameter("subjectId", subId).setParameter("gradeId",grdId);
		return query.list();
	}
	
	
	@Override
	public List<Assessment> getCreatedTestByTeacher(UserForm loginUser,Long subId,Long grdId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(a) From Assessment a inner join fetch a.grade inner join fetch a.subject left join fetch a.assignedAssessment " + " where a.schoolId=:schoolId and a.sessionId=:sessionId and a.status!=:status and a.createdBy=:createdBy and a.gradeId=:gradeId and a.subjectId=:subId  order by a.id desc");

		query.setLong("schoolId", loginUser.getSchoolId());
		query.setLong("sessionId", loginUser.getSessionId());
		query.setInteger("status", Assessment.STATUS_INACTIVE);
		query.setLong("createdBy", loginUser.getId());
		//query.setParameter("endDate", Utility.getCurrentDate());
		query.setParameter("gradeId", grdId);
		query.setParameter("subId", subId);

		return query.list();
	}

	@Override
	public String updateResource(GlobalVO globalVO)
	{
		
		String message="success";
		
		Query query=null;
		query = sessionFactory.getCurrentSession().createQuery("UPDATE Module SET name=:name , contentUrl=:contentUrl , resourceTypeId=:resourceTypeId ,description=:descrip ,contentUrl=:url,modifiedDate=:modfdDate where id=:id");
		query.setParameter("name", globalVO.getModuleName());
		query.setParameter("contentUrl", globalVO.getContentUrl());
		query.setParameter("resourceTypeId", globalVO.getResourceTypeId());
		query.setParameter("descrip", globalVO.getModuleDescription());
		query.setParameter("id",globalVO.getModuleId());
		query.setParameter("url",globalVO.getContentUrl());
		query.setParameter("modfdDate", Utility.getCurrentDate());
		
		query.executeUpdate();
		
		return message;
	}


	@Override
	public List<QuestionTopic> autoPreviewAssessment(List<String> result, String topicIds, long id, Long schoolId)
	{
		List<Long> quesIds=new ArrayList<Long>();
		
		for(String s : result)
			{
			
			quesIds.add(Long.valueOf(s.trim()));
			}
		List<Long> newTopicsIds = new ArrayList<Long>();
		String to[]=topicIds.split(",");
		for(String s:to)
		{
			newTopicsIds.add(Long.parseLong(s));
		}
		
		Query query=null;
		query=sessionFactory.getCurrentSession().createQuery("select distinct(qt) from QuestionTopic qt inner join fetch qt.cognitiveLevel cl inner join fetch qt.difficultyLevel qd  inner join fetch qt.question q inner join fetch q.answers a  where qt.topicId IN (:topicIds) AND qt.questionId IN (:result)");
		query.setParameterList("topicIds",newTopicsIds);
		query.setParameterList("result",quesIds);
		
		
		
		return query.list();
	}


	@Override
	public List<AssignTopicResources> getAssignedTopicReport(Long grdId, Long subId, Long secId,long userId)
	{
		
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(atr) from AssignTopicResources atr inner join fetch atr.topic t "
				+ "inner join fetch t.chapter c where atr.gradeId=:grdId and atr.subjectId=:subId and "
				+ "atr.sectionId=:secId and atr.type=:type and atr.assignBy=:usrId and "
				+ "atr.status=:status and t.status=:tstatus and c.status=:cstatus");
		query.setLong("grdId", grdId);
		query.setLong("subId", subId);
		query.setLong("usrId", userId);
		query.setLong("secId", secId);
		query.setInteger("type", AssignTopicResources.ASSIGN_TYPE_TOPIC);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameter("cstatus", Chapter.STATUS_ACTIVE);
		
		
		return query.list();
	}

	@Override
	public List<AssignTopicResources> getAssignedResourceReport(Long grdId, Long subId, Long secId, long userid)
	{
		
		Query query= sessionFactory.getCurrentSession().createQuery("select distinct(atr) from AssignTopicResources atr "
				+ "inner join fetch atr.module m inner join fetch m.resourceType "
				+ "inner join fetch atr.topic t "
				+ "inner join fetch t.chapter c where atr.gradeId=:grdid and atr.subjectId=:subid and "
				+ "atr.sectionId=:secid and atr.type=:type and atr.assignBy=:usrid and atr.resourceId is not null and "
				+ "atr.status=:status and t.status=:tstatus and c.status=:cstatus and m.status=:mstatus");
		
	
		query.setLong("grdid", grdId);
		query.setLong("subid", subId);
		query.setLong("secid", secId);
		query.setLong("usrid", userid);
		query.setInteger("type",AssignTopicResources.ASSIGN_TYPE_RESOURCES);
		query.setInteger("status", AssignTopicResources.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameter("cstatus", Chapter.STATUS_ACTIVE);
		query.setParameter("mstatus", Module.STATUS_ACTIVE);
		
		return query.list();
	}

	/*@Override
	public List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId)
	{
		
			Query query = sessionFactory.getCurrentSession().createQuery("select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch q.answers a left join fetch q.questionComment left join fetch q.user where qt.topicId=:topicId and qt.status!=:status ORDER BY qt.id DESC");
			query.setLong("topicId", topicId);
			query.setInteger("status", QuestionTopic.STATUS_INACTIVE);
			List<QuestionTopic> allq= query.list();
			return allq;
	}
*/
	
	
	@Override
	public List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId, Long loid)
	{

		Query query = null;
		List<QuestionTopic> allq = null;
		if (null != loid && loid != 0 && null != topicId)
		{
			System.out.println("iiiiiiiiiiiiiiiiiiii");
			query = sessionFactory.getCurrentSession().createQuery("select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch q.answers a left join fetch q.questionComment left join fetch q.user where qt.topicId=:topicId and qt.loId=:loId    and qt.status!=:status ORDER BY qt.id DESC");

			query.setLong("topicId", topicId);
			query.setLong("loId", loid);
			query.setInteger("status", QuestionTopic.STATUS_INACTIVE);
			allq = query.list();

		}
		else if (null != loid && null != topicId)
		{
			System.out.println("eeeeeeeeeeeeeeeeeee");
			query = sessionFactory.getCurrentSession().createQuery("select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch q.answers a left join fetch q.questionComment left join fetch q.user where qt.topicId=:topicId and qt.status!=:status ORDER BY qt.id DESC");
			query.setLong("topicId", topicId);
			query.setInteger("status", QuestionTopic.STATUS_INACTIVE);
			allq = query.list();

		}
		return allq;

	}
	
	
	@Override
	public String removeAssmt(Long assmtId, Long subId, Long grdId, long id, Long schoolId)
	{
		Query query=null;
		Query query1=null;
		
		String str="UPDATE Assessment SET status=:status where id=:assmtId and gradeId=:grdId and subjectId=:subId and schoolId=:schoolId";
		String str1="UPDATE AssignedAssessment SET status=:status where assessmentId=:assmtId ";
		query=sessionFactory.getCurrentSession().createQuery(str);
		
		query.setParameter("status", Assessment.STATUS_INACTIVE);
		query.setParameter("grdId", grdId);
		query.setParameter("subId", subId);
		query.setParameter("assmtId", assmtId);
		query.setParameter("schoolId", schoolId);
		query.executeUpdate();
		
		query1=sessionFactory.getCurrentSession().createQuery(str1);
		query1.setParameter("status",AssignedAssessment.STATUS_INACTIVE);
		query1.setParameter("assmtId", assmtId);
		query1.executeUpdate();
		
		
		
		return "success";
	}

	
	
	


	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionTopic> getPageQuestionsByTopic(int result, int offsetreal,Long topicId)
	{
		
		Query criteria = sessionFactory.getCurrentSession().createQuery("select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch q.answers a left join fetch q.questionComment left join fetch q.user where qt.topicId=:topicId and qt.status=:status ORDER BY qt.id DESC");
				criteria.setFirstResult(offsetreal);
				criteria.setMaxResults(result);
				criteria.setLong("topicId", topicId);
				criteria.setInteger("status", QuestionTopic.STATUS_ACTIVE);
		
				
		return criteria.list();
		
	}
	@Override
	public int getSize(Long topicId) {
		return sessionFactory.getCurrentSession().createCriteria("select distinct(qt) from QuestionTopic qt inner join fetch qt.question q inner join fetch qt.difficultyLevel inner join fetch qt.cognitiveLevel inner join fetch q.answers a left join fetch q.questionComment left join fetch q.user where qt.topicId="+topicId+" and qt.status="+QuestionTopic.STATUS_ACTIVE+" ORDER BY qt.id DESC").list().size();
	}



	@Override
	public List<User> getUserDetailForEmail(List<Long> id,int userType)
	{
		
			Query query = sessionFactory.getCurrentSession().createQuery("SELECT distinct(usr)  FROM User usr WHERE usr.id IN (:id) AND usr.userType=:userType");
			query.setParameterList("id",id);
			query.setInteger("userType",userType);
			List<User> usr=query.list();
			return usr;
	}
	
}
