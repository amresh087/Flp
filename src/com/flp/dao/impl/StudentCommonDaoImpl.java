package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.StudentCommonDao;
import com.flp.model.Answer;
import com.flp.model.Assessment;
import com.flp.model.AssessmentQuestion;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Board;
import com.flp.model.Chapter;
import com.flp.model.DemoGradeTopic;
import com.flp.model.FavoriteTopics;
import com.flp.model.Grade;
import com.flp.model.ModuleTopic;
import com.flp.model.Question;
import com.flp.model.SchoolStudentTest;
import com.flp.model.Section;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.Topic;
import com.flp.model.TopicSmartScore;
import com.flp.model.User;
import com.flp.model.UserActiveTopic;
import com.flp.model.UserActivity;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.model.UserTest;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class StudentCommonDaoImpl implements StudentCommonDao
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Board> getAllBoardList()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Board where status=:status");
		query.setParameter("status", Board.STATUS_ACTIVE);
		List<Board> lst = query.list();
		if (lst != null && !lst.isEmpty())
		{
			return lst;
		}
		return null;
	}

	@Override
	public List<Grade> getGradeByBoardId(Long boardId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Grade where boardId=:boardId and status=:status order by id");
		query.setParameter("boardId", boardId);
		query.setParameter("status", Grade.STATUS_ACTIVE);
		List<Grade> lst = query.list();
		/* if (lst != null && !lst.isEmpty()) { List<Grade> gradeList = new ArrayList<Grade>(); for (Grade b : lst) { gradeList.add(b); } return gradeList; } */
		return lst;
	}

	@Override
	public List<Grade> getAllGradeList()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Grade");
		List<Grade> lst = query.list();
		if (lst != null && !lst.isEmpty())
		{
			return lst;
		}
		return null;
	}
	
	@Transactional
	@Override 
	public Long getLOCount(Long topicId)
	{
		Session session = sessionFactory.getCurrentSession();
        
        Query countlo = session.createQuery("select count(*) FROM LearnObjective WHERE topicId=:topicId ");
        countlo.setParameter("topicId", topicId); 
        Long locount = (Long)countlo.uniqueResult(); 
		return locount; 
	}
	
	
	@Override
	public String getzoneId(Long questionId, Long topicId)
	{
        Session session = sessionFactory.getCurrentSession(); 
        Query zoneid = session.createQuery("select distinct zoneLevelId FROM QuestionTopic WHERE  questionId=:questionId  AND topicId=:topicId ");
        zoneid.setParameter("questionId", questionId);
        zoneid.setParameter("topicId", topicId); 
        Long locount = (Long)zoneid.uniqueResult(); 
		return locount.toString(); 
	}

	

	/* public BoardGrade getMapExitForBoardGrade(Long boardId, Long gradeId) {
	 * 
	 * Session session = sessionFactory.getCurrentSession();
	 * 
	 * Query query = session.createQuery("FROM BoardGrade WHERE board.id=:board_id AND grade.id=:grade_id"); query.setParameter("board_id", boardId); query.setParameter("grade_id", gradeId);
	 * 
	 * BoardGrade brdgrd = (BoardGrade) query.uniqueResult();
	 * 
	 * return brdgrd; } */

	@Override
	public String mapBoardGrade(Long boardId, Long gradeId)
	{
		String msg = "";
		/* try { // Transaction tx=session.beginTransaction(); // System.out.println(bId+">>"+gId); Session session = sessionFactory.getCurrentSession(); Board board = (Board) session.get(Board.class, boardId); Grade grade = (Grade) session.get(Grade.class, gradeId); BoardGrade boardGrade = new BoardGrade(); boardGrade.setBoard(board); boardGrade.setGrade(grade); boardGrade.setInsertDate(new Date()); boardGrade.setStatus(BoardGrade.STATUS_ACTIVE); session.save(boardGrade); //
		 * System.out.println(">>>>>>>>>"+session.save(boardGrade)); // tx.commit(); // System.out.println("Mappeed"); msg = "success";
		 * 
		 * System.out.println(boardId); } catch (Exception e) { e.printStackTrace(); msg = "Error: " + e.getMessage(); } */
		return msg;
	}

	@Override
	public List<Subject> getAllSubjectList()
	{

		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Subject");
			List<Subject> lst = query.list();
			if (lst != null && !lst.isEmpty())
			{
				return lst;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String mapBoardGradeSubject(Long boardId, Long gradeId, Long subjectId)
	{
		String msg = "";
		/* try { Session session = sessionFactory.getCurrentSession(); Board board = (Board) session.get(Board.class, boardId); Grade grade = (Grade) session.get(Grade.class, gradeId); Subject subject = (Subject) session.get(Subject.class, subjectId);
		 * 
		 * BoardGradeSubject bgs = new BoardGradeSubject(); bgs.setBoard(board); bgs.setGrade(grade); bgs.setSubject(subject); bgs.setInsertDate(new Date()); bgs.setStatus(BoardGradeSubject.STATUS_ACTIVE); session.save(bgs); msg = "success";
		 * 
		 * } catch (Exception e) { e.printStackTrace(); msg = "Error" + e.getMessage();
		 * 
		 * } */
		return msg;
	}

	@Override
	public List<Chapter> getAllChapterList()
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Chapter");

			List<Chapter> lst = query.list();
			if (lst != null && !lst.isEmpty())
			{
				return lst;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String mapBoardGradeSubjectChapter(Long boardId, Long gradeId, Long subjectId, String chapterName)
	{
		String msg = "";
		/* try { Session session = sessionFactory.getCurrentSession(); Subject subject = (Subject) session.get(Subject.class, subjectId); Chapter chapter = new Chapter(); chapter.setBoardId(boardId); chapter.setGradeId(gradeId); chapter.setInsertDate(new Date()); chapter.setName(chapterName); chapter.setStatus(Chapter.STATUS_ACTIVE); chapter.setSubject(subject); session.save(chapter); msg = "success";
		 * 
		 * } catch (Exception e) { e.printStackTrace(); msg = "Error" + e.getMessage();
		 * 
		 * } */
		return msg;
	}

	/* @Override public BoardGradeSubject getMapExitForBoardGradeSubject(Long boardId, Long gradeId, Long subjectId) {
	 * 
	 * Session session = sessionFactory.getCurrentSession(); Query query = session.createQuery(" FROM BoardGradeSubject WHERE board.id=:board_id AND grade.id=:grade_id AND subject.id=:subject_id"); query.setParameter("board_id", boardId); query.setParameter("grade_id", gradeId); query.setParameter("subject_id", subjectId);
	 * 
	 * BoardGradeSubject brdgrdsub = (BoardGradeSubject) query.uniqueResult();
	 * 
	 * return brdgrdsub;
	 * 
	 * } */

	@Override
	public Chapter getMapExitForBoardGradeSubjectChapter(Long boardId, Long gradeId, Long subjectId, String chapterName)
	{

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM Chapter WHERE boardId=:board_id AND gradeId=:grade_id AND subject.id=:subject_id AND name=:chapter_name");
		query.setParameter("board_id", boardId);
		query.setParameter("grade_id", gradeId);
		query.setParameter("subject_id", subjectId);
		query.setParameter("chapter_name", chapterName);

		Chapter chapter = (Chapter) query.uniqueResult();

		return chapter;

	}

	@Override
	public Topic getMapExitForBoardGradeSubjectChapterTopic(Long boardId, Long gradeId, Long subjectId, Long chapterId, String topicName)
	{

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Topic WHERE boardId=:board_id AND gradeId=:grade_id AND subjectId=:subject_id AND chapter.id=:chapter_id AND name=:topic_name");
		query.setParameter("board_id", boardId);
		query.setParameter("grade_id", gradeId);
		query.setParameter("subject_id", subjectId);
		query.setParameter("chapter_id", chapterId);
		query.setParameter("topic_name", topicName);

		Topic topic = (Topic) query.uniqueResult();
		return topic;
	}

	@Override
	public String mapBoardGradeSubjectChapterTopic(Long boardId, Long gradeId, Long subjectId, Long chapterId, String topicName)
	{

		String msg = "";
		/* try { Session session = sessionFactory.getCurrentSession(); Chapter chapter = (Chapter) session.get(Chapter.class, chapterId); Topic topic = new Topic(); topic.setBoardId(boardId); topic.setChapter(chapter); topic.setGradeId(gradeId); topic.setStatus(Topic.STATUS_ACTIVE); topic.setInsertDate(new Date()); topic.setName(topicName); topic.setSubjectId(subjectId);
		 * 
		 * session.save(topic); msg = "success";
		 * 
		 * } catch (Exception e) { e.printStackTrace(); msg = "Error" + e.getMessage();
		 * 
		 * } */
		return msg;
	}

	/* @Override public List<BoardGradeSubject> getSubjectByBoardAndGrade(Long boardId, Long gradeId) { Session session = sessionFactory.getCurrentSession(); String str = "FROM BoardGradeSubject where board.id =:boardId and grade.id = :gradeId and status = :status"; Query query = session.createQuery(str); query.setParameter("boardId", boardId); query.setParameter("gradeId", gradeId); query.setParameter("status", BoardGradeSubject.STATUS_ACTIVE); return query.list(); } */

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getChapterByBoardGradeAndSubject(Long boardId, Long gradeId, Long subjectId)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Chapter where boardId =:boardId and gradeId = :gradeId and subjectId = :subjectId and status = :status";
		Query query = session.createQuery(str);
		query.setParameter("boardId", boardId);
		query.setParameter("gradeId", gradeId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("status", Subject.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<com.flp.model.Session> getStudentSession(Long Id)
	{
		Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Session as s where s.id=:id");
	query.setParameter("id", Id);
	
	
	return query.list();
	
	}
	
	@Override
	public List<Topic> getAllTopicList()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Topic");
		List<Topic> lst = query.list();
		if (lst != null && !lst.isEmpty())
		{
			return lst;
		}
		return null;
	}

	@Override
	public DemoGradeTopic getMapExitForDemo(Long boardId, Long gradeId, Long subjectId, Long chapterId, Long topicId)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("FROM DemoGradeTopic WHERE boardId = :board_id AND grade.id  = :grade_id AND subjectId = :subject_id AND chapterId = :chapter_id AND topic.id = :topicId");

		query.setParameter("chapter_id", chapterId);
		query.setParameter("subject_id", subjectId);
		query.setParameter("topicId", topicId);
		query.setParameter("grade_id", gradeId);
		query.setParameter("board_id", boardId);
		DemoGradeTopic demo = (DemoGradeTopic) query.uniqueResult();

		

		return demo;
	}

	@Override
	public String mapWithDemo(Long boardId, Long gradeId, Long subjectId, Long chapterId, Long topicId)
	{

		String msg;
		try
		{
			Session session = sessionFactory.getCurrentSession();
			DemoGradeTopic demoTable = new DemoGradeTopic();
			Topic topic = (Topic) session.get(Topic.class, topicId);
			Grade grade = (Grade) session.get(Grade.class, gradeId);
			demoTable.setBoardId(boardId);
			demoTable.setChapterId(chapterId);
			demoTable.setInsertDate(new Date());
			demoTable.setSubjectId(subjectId);
			demoTable.setStatus(DemoGradeTopic.STATUS_ACTIVE);
			demoTable.setTopic(topic);
			demoTable.setGrade(grade);

			session.save(demoTable);
			msg = "sucess";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			msg = "error" + e.getMessage();
		}

		return msg;
	}

	@Override
	public List<Assessment> geteTestList(Long userId, Long sessionId)
	{
		/* Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("studentTestFilter");
		filter.setParameter("userId", userId);
		filter.setParameter("status", SchoolStudentTest.STATUS_ACTIVE);
		filter.setParameter("sessionId", sessionId);
		String str = "select distinct(a) From Assessment a left join fetch a.studentTest st inner join fetch a.grade inner join fetch a.subject inner join fetch a.user left join fetch a.assignedAssessment aa " + " where  a.status = :status  and a.endDate>= :date and aa.userId = :userId and a.type = :type order by st.id, a.endDate ";
		Query query = session.createQuery(str);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
		query.setParameter("date", Utility.getCurrentDate());
		query.setParameter("userId", userId);
		query.setParameter("type", Assessment.E_TEST);
		return query.list(); 
		*/
		 Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("studentTestFilter");
		filter.setParameter("userId", userId);
		filter.setParameter("status", SchoolStudentTest.STATUS_ACTIVE);
		filter.setParameter("sessionId", sessionId);
	 //	String str = "select distinct(a) From Assessment a left join fetch a.studentTest st inner join fetch a.grade inner join fetch a.subject inner join fetch a.user left join fetch a.assignedAssessment aa " + " where  a.status = :status  and a.endDate>= :date and aa.userId = :userId and a.type = :type order by st.id, a.endDate ";
		
		String str = "select distinct(a) From Assessment a left join fetch a.studentTest st inner join fetch a.grade inner join fetch a.subject inner join fetch a.user left join fetch a.assignedAssessment aa " + " where  a.status = :status  and  cast(a.dateTimeZoneEnd as string)>= :date and aa.userId = :userId and a.type = :type order by st.id, a.endDate ";
	 	
		Query query = session.createQuery(str);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
	//	query.setParameter("date", Utility.getCurrentDate());
		
	 	
		query.setParameter("date", Utility.convertDateToStringWithZone(new Date()));
		query.setParameter("userId", userId);
		query.setParameter("type", Assessment.E_TEST);
		

		return query.list(); 
		
	}

	@Override
	public List<Assessment> getAssignedTest(Long userId, Long sessionId)
	{
		/* Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("studentTestFilter");
		filter.setParameter("userId", userId);
		filter.setParameter("status", SchoolStudentTest.STATUS_ACTIVE);
		filter.setParameter("sessionId", sessionId);

		String str = "select distinct(a) From Assessment a left join fetch a.studentTest st inner join fetch a.grade inner join fetch a.subject inner join fetch a.user left join fetch a.assignedAssessment aa " + " where  a.status = :status and a.endDate>= :date   and aa.userId = :userId and a.type = :type order by st.id , a.endDate ";
		Query query = session.createQuery(str);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
		query.setParameter("date", Utility.getCurrentDate());
		query.setParameter("userId", userId);
		query.setParameter("type", Assessment.ASSIGNED_TEST);
		return query.list(); 
		*/
		
		 
		Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("studentTestFilter");
		filter.setParameter("userId", userId);
		filter.setParameter("status", SchoolStudentTest.STATUS_ACTIVE);
		filter.setParameter("sessionId", sessionId);

		String str = "select distinct(a) From Assessment a left join fetch a.studentTest st inner join fetch a.grade inner join fetch a.subject inner join fetch a.user left join fetch a.assignedAssessment aa " + " where  a.status = :status and cast(a.dateTimeZoneEnd as string)>= :date   and aa.userId = :userId and a.type = :type order by st.id , a.endDate ";
		Query query = session.createQuery(str);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
		
		query.setParameter("date", Utility.convertDateToStringWithZone(new Date()));
		//query.setParameter("date", Utility.getCurrentDate());
		query.setParameter("userId", userId);
		query.setParameter("type", Assessment.ASSIGNED_TEST);
		return query.list(); 
	}

	@Override
	public List<Subject> getSubjectChapterTopics(List<Long> subjectIdList, boolean isFirst, Long gradeId, Long schoolId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("chapterFilterOrder");
		filter.setParameter("active", Chapter.STATUS_ACTIVE);
		filter.setParameter("gradeId", gradeId);
		filter.setParameter("schoolId", schoolId);
		if (isFirst)
		{
			filter.setParameter("order", 1);
		}

		Filter filter1 = session.enableFilter("topicFilterOrder");
		filter1.setParameter("active", Topic.STATUS_ACTIVE);
		if (isFirst)
		{
			filter1.setParameter("order", 1);
		}

		/* Filter filter2 = session.enableFilter("smartScoreFliter"); filter2.setParameter("sUserId", userId); */

		/* Filter filter1 = session.enableFilter("topicFilter"); filter1.setParameter("active", Topic.STATUS_ACTIVE); if(isFirst){ filter1.setParameter("order", 1); } */
		String str = "SELECT DISTINCT s FROM Subject s INNER JOIN FETCH s.chapters c INNER JOIN FETCH c.topics t  where s.id in(:subjectIds)";
		Query query = session.createQuery(str);
		query.setParameterList("subjectIds", subjectIdList);
		return query.list();
	}

	@Override
	public List<AssignTopicResources> getAssignedResoureAndTopics(List<Long> subjectIdList, Long gradeId, Long schoolId, Long sessionId, Long sectionId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		/* Filter filter2 = session.enableFilter("smartScoreFliter"); filter2.setParameter("sUserId", userId); */
		String str = "FROM AssignTopicResources ats INNER JOIN FETCH ats.subject s INNER JOIN FETCH ats.section LEFT JOIN FETCH ats.module INNER JOIN FETCH ats.topic t WHERE ats.sectionId = :sectionId AND ats.gradeId = :gradeId AND ats.schoolId = :schoolId AND ats.sessionId = :sessionId AND t.status=:tstatus And ats.status=:status AND ats.subjectId IN(:subjectIds)";
		Query query = session.createQuery(str);
		query.setParameter("sectionId", sectionId);
		query.setParameter("gradeId", gradeId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("sessionId", sessionId);
		query.setParameter("status", AssignTopicResources.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameterList("subjectIds", subjectIdList);
		return query.list();
	}
	
	
	
	
	@Override
	public List<Chapter> getChaptersTopicsBySubject(Long subjectId, Long gradeId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("questionAnswerFilter");
		filter.setParameter("cCount", UserQuestionAnswer.IS_CORRECT);
		filter.setParameter("sUserId", userId);

		Query query = session.createQuery("SELECT DISTINCT(c) FROM Chapter c LEFT JOIN FETCH c.topics t left join fetch t.questionTopic qtt left join fetch t.userQuestionAnswer where " + "c.gradeId=:gradeId and c.status=:status and c.subjectId=:subjectId and t.status=:tstatus ");

		query.setLong("subjectId", subjectId);
		query.setLong("gradeId", gradeId);
		query.setInteger("status", Chapter.STATUS_ACTIVE);
		query.setInteger("tstatus", Topic.STATUS_ACTIVE);
		 
		Query query22 = session.createQuery("  SELECT DISTINCT(c) FROM Chapter c  LEFT JOIN FETCH c.topics t  WHERE  " + " c.gradeId=:gradeId and c.subjectId=:subjectId  and t.id IS NULL   ");
		query22.setLong("subjectId", subjectId);
		query22.setLong("gradeId", gradeId);
		 
		List<Chapter> list = query22.list();
		List<Chapter> list2 = query.list();
		list2.addAll(list);
		 

		// return list;

		return list2;
	}


	/*@Override
	public List<Chapter> getChaptersTopicsBySubject(Long subjectId, Long gradeId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		 Filter filter1 = session.enableFilter("topicFilterStatus"); filter1.setParameter("active", Topic.STATUS_ACTIVE); 
		Filter filter = session.enableFilter("questionAnswerFilter");
		filter.setParameter("cCount", UserQuestionAnswer.IS_CORRECT);
		filter.setParameter("sUserId", userId);
		Query query = session.createQuery("SELECT DISTINCT(c) FROM Chapter c INNER JOIN FETCH c.topics t inner join fetch t.questionTopic qtt left join fetch t.userQuestionAnswer where "
				+"c.contentPermissionby=:contentPermissionby and c.teacherToStudentContentstatus=:teacherToStudentContentstatus and c.gradeId=:gradeId and c.status=:status and c.subjectId=:subjectId and t.status=:tstatus and qtt.status=:qtstatus ");
		
		
		Query query = session.createQuery("SELECT DISTINCT(c) FROM Chapter c INNER JOIN FETCH c.topics t inner join fetch t.questionTopic qtt left join fetch t.userQuestionAnswer where "
				+"c.gradeId=:gradeId and c.status=:status and c.subjectId=:subjectId and t.status=:tstatus and qtt.status=:qtstatus ");
	
		
		
		Query query = session.createQuery("SELECT DISTINCT(c) FROM Chapter c LEFT JOIN FETCH c.topics t left join fetch t.questionTopic qtt left join fetch t.userQuestionAnswer where "
				+"c.gradeId=:gradeId and c.status=:status and c.subjectId=:subjectId and t.status=:tstatus  "
				+ " UNION  "
				+ "  SELECT DISTINCT(c) FROM Chapter c  where "
				+"c.gradeId=:gradeId and c.status=:status and c.subjectId=:subjectId    ");
	
		query.setLong("subjectId", subjectId);
		query.setLong("gradeId", gradeId);
		query.setInteger("status", Chapter.STATUS_ACTIVE);
		query.setInteger("tstatus", Topic.STATUS_ACTIVE);
		
		
		query.setLong("subjectId", subjectId);
		//query.setLong("gradeId", gradeId);
		query.setInteger("status", Chapter.STATUS_ACTIVE);
	//	query.setInteger("tstatus", Topic.STATUS_ACTIVE);
		
		
		//query.setParameter("teacherToStudentContentstatus",Chapter.STATUS_teacherToStudentContentstatus_ACTIVE);
		//query.setInteger("contentPermissionby",Integer.parseInt(""+contentPermissionby) );
		//query.setParameter("qtstatus", QuestionTopic.STATUS_ACTIVE);
		
		
		System.out.println("sssssssssssssssss==="+query.list().size());
		
		
		return query.list();
	}*/

	@Override
	public Integer isValidAssessment(UserForm loginUser, Long testId)
	{
		Calendar calendar = Calendar.getInstance();
		TimeZone fromTimeZone = calendar.getTimeZone();
		TimeZone toTimeZone = TimeZone.getTimeZone("IST");
		calendar.setTimeZone(fromTimeZone);
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime()))
		{
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
		}

		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		if (toTimeZone.inDaylightTime(calendar.getTime()))
		{
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT isValid(:userId,:testId,:sectionId,:dateTime)");
		query.setLong("userId", loginUser.getId());
		query.setLong("testId", testId);
		query.setLong("sectionId", loginUser.getSectionId());
		query.setParameter("dateTime", calendar.getTime());// Utility.getCurrentDate());//

		return (Integer) query.uniqueResult();

	}

	@Override
	public UserActiveTopic getStudentLastActiveTopic(long userId, Long schoolId, Long sessionId, Long subjecctId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from UserActiveTopic where userId=:userId and subjectId=:subjectId and sessionId=:sessionId and status=:status");
		query.setLong("userId", userId);
		query.setLong("subjectId", subjecctId);
		query.setLong("sessionId", sessionId);
		query.setLong("status", UserActiveTopic.STATUS_ACTIVE);
		query.setMaxResults(1);
		return (UserActiveTopic) query.uniqueResult();
	}

	@Override
	public SchoolStudentTest getStudentAssessmentSummary(Long userId, Long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct(sst) from SchoolStudentTest sst inner join fetch sst.user inner join fetch sst.assessment inner join fetch sst.schoolStudentTestDetail sstd inner join fetch sstd.question q inner join fetch q.answers where sst.assessmentId=:assessmentId and sst.studentId=:studentId");
		query.setLong("assessmentId", assessmentId);
		query.setLong("studentId", userId);
		query.setMaxResults(1);
		return (SchoolStudentTest) query.uniqueResult();
	}

	@Override
	public Object[] getTopperAndMaxAttendAssessment(Long schoolId, Long assessmentId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT MAX(marks),COUNT(id) FROM SchoolStudentTest WHERE assessmentId=:assessmentId and schoolId=:schoolId");
		query.setLong("assessmentId", assessmentId);
		query.setLong("schoolId", schoolId);
		return (Object[]) query.uniqueResult();

	}

	@Override
	public Object[] getCorrectIncorrectQuestionCount(Long userId, Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserQuestionAnswer WHERE userId=:userId and topicId=:topicId and status=:status");
		query.setLong("userId", userId);
		query.setLong("topicId", topicId);
		query.setInteger("status", UserQuestionAnswer.STATUS_ACTIVE);
		List<UserQuestionAnswer> lst = query.list();
		Integer correctCount = 0;
		Integer incorrectCount = 0;
		if (lst != null && lst.size() > 0)
		{
			for (UserQuestionAnswer uqa : lst)
			{
				if (uqa.getCorrectIncorrect().equals(UserQuestionAnswer.IS_CORRECT))
				{
					correctCount = correctCount + 1;
				}
				else if (uqa.getCorrectIncorrect().equals(UserQuestionAnswer.IS_INCORRECT))
				{
					incorrectCount = incorrectCount + 1;
				}
			}
		}
		Object[] object = new Object[2];
		object[0] = correctCount;
		object[1] = incorrectCount;
		return object;
	}

	@Override
	public int getGScore(long topicId, UserForm loginUser)
	{
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM TopicSmartScore u WHERE topicId=:topicId AND userId=:userId");
		q.setParameter("topicId", topicId);
		q.setParameter("userId", loginUser.getId());

		List<TopicSmartScore> list = q.list();

		if (list.size() > 0)
			return list.get(0).getSmartScore();
		else
		{
			/* UserTopicScore gs = new UserTopicScore(); gs.setSessionId(loginUser.getSessionId()); gs.setTopicId(topicId);
			 * 
			 * session.save(gs); */
			return 0;
		}

	}
	
	@Override
	public int updatescoreData(long topicId, UserForm loginUser)
	{
		Query q = sessionFactory.getCurrentSession().createSQLQuery("UPDATE t_topic_smart_score SET f_smartScore=:gScore WHERE f_topicId=:topicId AND f_userId=:userId");
		q.setParameter("gScore", 0);
		q.setParameter("topicId", topicId);
		q.setParameter("userId", loginUser.getId());
		int result = q.executeUpdate(); 
		
		if(result>0){
			
			Session session = sessionFactory.getCurrentSession();
			Query q2 = session.createQuery("Delete  FROM UserQuestionAnswer u WHERE topicId=:topicId AND userId=:userId");
			q2.setParameter("topicId", topicId);
			q2.setParameter("userId", loginUser.getId()); 
			int result2 = q2.executeUpdate();  
		} 
		
		
		Session session = sessionFactory.getCurrentSession();
		Query q3 = session.createQuery("FROM TopicSmartScore u WHERE topicId=:topicId AND userId=:userId");
		q3.setParameter("topicId", topicId);
		q3.setParameter("userId", loginUser.getId());
		List<TopicSmartScore> list = q3.list();
 
		 

         if (list.size() > 0)
	        return list.get(0).getSmartScore();
          else
            { 
	          return 0;
               }


	}

	@Override
	public int questionAttemptcount(long topicId, UserForm loginUser)
	{
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select count(u) FROM UserQuestionAnswer u WHERE topicId=:topicId AND userId=:userId");
		q.setParameter("topicId", topicId);
		q.setParameter("userId", loginUser.getId());

		long list = (long) q.list().get(0);

		return (int) list;

	}
	
	
	
	@Override
	public Date getmaxLxDate(long topicId, UserForm loginUser)
	{
		
		
		Session session = sessionFactory.getCurrentSession();
		//Query q = session.createQuery("SELECT MAX(u.insertDate)  FROM UserQuestionAnswer u WHERE topicId=:topicId AND userId=:userId");
		
		Query q = session.createQuery("from UserQuestionAnswer u where u.insertDate IN ( select max(b.insertDate)  from UserQuestionAnswer b  WHERE b.topicId=:topicId AND b.userId=:userId)");
		q.setParameter("topicId", topicId);
		q.setParameter("userId", loginUser.getId());  
	    List<UserQuestionAnswer> ss=q.list() ;
	    
	    if(ss.size()>0){
	    return ss.get(0).getInsertDate() ; 
	    }
	    else{
	    	return new Date();
	    }
	}
	
	
	
	@Override
    public Question getNextQuestion(long topicId, int gScore, long userId)
    {
	 
        Question question = null;

        try
        {
           
           
            Session session = sessionFactory.getCurrentSession();
           
            Query countlo = session.createQuery("select count(*) FROM LearnObjective WHERE topicId=:topicId ");
            countlo.setParameter("topicId", topicId);
           
           
        //int loc=countlo.getFirstResult();
        Long locount = (Long)countlo.uniqueResult();
         

            /*Query q = session.createSQLQuery("call getNextQuestion( :gScore,:userId , :topicId )").addEntity(Question.class);*/
            Query q = session.createSQLQuery("call getNextQuestionNextFinal( :gScore,:userId , :topicId , :locount )").addEntity(Question.class);
            q.setParameter("gScore", gScore);
            q.setParameter("userId", userId);
            q.setParameter("topicId", topicId);
            q.setParameter("locount", locount);
            
            
          
            if (!q.list().isEmpty())
            {
                question = (Question) q.list().get(0);
                question.setLocountQ(locount);
                question.setAnswers(getAnswerSet(question.getId()));
            }
        }
        
       

        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("No question found for give topic by getNextQuestion procedure");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return question;
    }

	public List<Answer> getAnswerSet(long questionId)
	{
		List<Answer> answerSet = null;
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery("from Answer where questionId=:questionId");
			q.setParameter("questionId", questionId);

			answerSet = q.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return answerSet;
	}

	@Override
	public int saveQuestion(String topicId, String questionId, String isAttempted, String userAnswer, String isCorrect, String timeTaken, UserForm user,String diffValue,String zoneid)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			SQLQuery q = session.createSQLQuery("select saveQuestion(" + topicId + "," + user.getId() + "," + user.getGradeId() + "," + questionId + ",'" + userAnswer + "'," + isAttempted + "," + isCorrect + "," + timeTaken + "," + user.getSchoolId() + "," + user.getSessionId() + "," + diffValue +"," + zoneid +")");

			session.setFlushMode(FlushMode.MANUAL);
			if(null !=q){
			return Integer.parseInt(q.list().get(0).toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveOrUpdateGScore(String topicId, Integer gScore, long userId, long gradeId)
	{
		
		 
		Query q = sessionFactory.getCurrentSession().createSQLQuery("UPDATE t_topic_smart_score SET f_smartScore=:gScore WHERE f_topicId=:topicId AND f_userId=:userId");
		q.setParameter("gScore", gScore);
		q.setParameter("topicId", topicId);
		q.setParameter("userId", userId);
		int result = q.executeUpdate();
		if (result < 1)
		{
			TopicSmartScore tp = new TopicSmartScore();
			tp.setGradeId(gradeId);
			tp.setSmartScore((short) gScore.intValue());
			tp.setUserId(userId);
			tp.setTopicId(Long.parseLong(topicId));
			sessionFactory.getCurrentSession().save(tp);
			return 1;
		}
		return 1;
	}

	@Override
	public Integer getuserTopicGenScore(Long userId, Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from TopicSmartScore where userId=:userId and topicId=:topicId");
		query.setLong("userId", userId);
		query.setLong("topicId", topicId);
		query.setMaxResults(1);
		TopicSmartScore ts = (TopicSmartScore) query.uniqueResult();
		if (ts != null)
		{
			return (int) ts.getSmartScore();
		}
		return 0;

	}

	@Override
	public List<ModuleTopic> getModulesByTopicId(Long topicId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from ModuleTopic mt inner join fetch mt.module where mt.topicId=:topicId and mt.status=:status");
		query.setLong("topicId", topicId);
		query.setInteger("status", ModuleTopic.STATUS_ACTIVE);
		List<ModuleTopic> lst = query.list();
		return lst;
	}

	@Override
	public List<AssignedAssessment> getAssessmentByStudentId(Long userId)
	{

		Query q = sessionFactory.getCurrentSession().createQuery("select distinct(aa) from AssignedAssessment aa inner join fetch aa.assessment a inner join " + "fetch a.studentTest inner join " + "fetch a.subject where aa.userId=:ID");
		q.setParameter("ID", userId);
		List<AssignedAssessment> ass = q.list();
		return ass;

	}

	@Override
	public Map<Long, String> getTopperAndMaxStudentAttendAssessments(Long gradeId, Long schoolId)
	{
		Map<Long, String> map = new HashMap<Long, String>();
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT sst.assessmentId, MAX(sst.marks), COUNT(sst.id) FROM SchoolStudentTest sst WHERE sst.schoolId=:schoolId group by sst.assessmentId");
		query.setLong("schoolId", schoolId);
		List<Object[]> lst = query.list();
		if (!lst.isEmpty())
		{
			for (Object[] o : lst)
			{
				map.put(Long.parseLong(o[0].toString()), o[1] + "," + o[2]);
			}
		}
		return map;
	}

	@Override
	public List<UserQuestionAnswer> getStudentLearningReportsbyChapterOrTopicId(UserForm loginUser, Long id, String type)
	{
		Query query = null;
		if (type.equals("topicwise"))
		{
			query = sessionFactory.getCurrentSession().createQuery("select distinct(uqa) from UserQuestionAnswer uqa where uqa.topicId=:id and uqa.userId=:userId and (uqa.sessionId=:sessionId or uqa.sessionId is NULL) and uqa.status=:status");

		}
		else if (type.equals("chapterwise"))
		{
			query = sessionFactory.getCurrentSession().createQuery("select distinct(uqa) from UserQuestionAnswer uqa inner join fetch uqa.topic t where uqa.userId=:userId and (uqa.sessionId=:sessionId or uqa.sessionId is NULL) and uqa.status=:status and t.chapter=:id and t.status=:tstatus");
			query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		}
		query.setParameter("id", id);
		query.setParameter("userId", loginUser.getId());
		query.setParameter("sessionId", loginUser.getSessionId());
		query.setParameter("status", UserQuestionAnswer.STATUS_ACTIVE);
		List<UserQuestionAnswer> lst = query.list();
		return lst;
	}

	@Override
	public List<Subject> getSubjectChapterTopicsForRetailUser(List<Long> subjectIdList, boolean isFirst, Long gradeId, Long boardId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("chapterFilterOrderRetail");
		filter.setParameter("active", Chapter.STATUS_ACTIVE);
		filter.setParameter("gradeId", gradeId);
		filter.setParameter("boardId", boardId);
		if (isFirst)
		{
			filter.setParameter("order", 1);
		}

		Filter filter1 = session.enableFilter("topicFilterOrder");
		filter1.setParameter("active", Topic.STATUS_ACTIVE);
		if (isFirst)
		{
			filter1.setParameter("order", 1);
		}

		Filter filter2 = session.enableFilter("smartScoreFliter");
		filter2.setParameter("sUserId", userId);

		/* Filter filter1 = session.enableFilter("topicFilter"); filter1.setParameter("active", Topic.STATUS_ACTIVE); if(isFirst){ filter1.setParameter("order", 1); } */
		String str = "FROM Subject s INNER JOIN FETCH s.chapters c INNER JOIN FETCH c.topics t left join fetch t.topicSmartScore where s.id in(:subjectIds)";
		Query query = session.createQuery(str);
		query.setParameterList("subjectIds", subjectIdList);
		return query.list();
	}

	@Override
	public UserActivity getLastUserActivity(Long topicId, Long subjectId, Long userId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from UserActivity where userId=:userId and subjectId=:subjectId and status=:status");
		query.setParameter("userId", userId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("status", UserActivity.STATUS_ACTIVE);
		query.setMaxResults(1);
		return (UserActivity) query.uniqueResult();
	}

	@Override
	public void saveOrUpdateUserActivity(UserActivity userActivity)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(userActivity);
	}

	@Override
	public UserActiveTopic getUserActiveTopic(Long subjectId, Long userId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from UserActiveTopic where userId=:userId and subjectId=:subjectId and status=:status");
		query.setParameter("userId", userId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("status", UserActiveTopic.STATUS_ACTIVE);
		query.setMaxResults(1);
		return (UserActiveTopic) query.uniqueResult();
	}

	@Override
	public void saveOrUpdateUserActiveTopic(UserActiveTopic userActivity)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(userActivity);

	}

	@Override
	public UserActivity getUserLastActiveTopic(Long userId, Long boardId, Long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from UserActivity where userId=:userId and subjectId=:subjectId and boardId=:boardId and status=:status");
		query.setLong("userId", userId);
		query.setLong("subjectId", subjectId);
		query.setLong("boardId", boardId);
		query.setLong("status", UserActiveTopic.STATUS_ACTIVE);
		query.setMaxResults(1);
		return (UserActivity) query.uniqueResult();
	}

	@Override
	public List<Subject> getSubjectsByGradeAndBoard(Long boardId, Long gradeId)
	{
		List<Subject> subList = null;
		String sql = "select Distinct(ss) from SubjectSection ss inner join fetch ss.subject where ss.sectionId =(select s.id from Section s where s.gradeId=:gradeId and s.boardId=:boardId and s.status=:sstatus) and ss.status=:status";
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setParameter("gradeId", gradeId).setParameter("boardId", boardId).setParameter("sstatus", Section.STATUS_ACTIVE).setParameter("status", SubjectSection.STATUS_ACTIVE);
		List<SubjectSection> lst = query.list();
		if (!lst.isEmpty())
		{
			subList = new ArrayList<Subject>();
			for (SubjectSection ss : lst)
			{
				ss.getSubject().setChapters(null);
				subList.add(ss.getSubject());
			}
			return subList;
		}

		return null;
	}

	@Override
	public Subject getSubjectDetailsById(Long subjectId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Subject where id=:subjectId");
		query.setParameter("subjectId", subjectId);
		query.setMaxResults(1);

		return (Subject) query.uniqueResult();
	}

	@Override
	public Boolean addFavouriteTopic(FavoriteTopics ft)
	{
		sessionFactory.getCurrentSession().save(ft);
		return true;
	}

	@Override
	public Map<Long, Long> getAllFavoriteTopic(Long userId, Long subjectId)
	{
		Map<Long, Long> favTopic = new HashMap<Long, Long>();
		Query query = sessionFactory.getCurrentSession().createQuery("from FavoriteTopics where userId=:userId and subjectId=:subjectId and status=:status");
		query.setParameter("userId", userId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("status", FavoriteTopics.STATUS_ACTIVE);
		List<FavoriteTopics> lst = query.list();
		if (!lst.isEmpty())
		{
			for (FavoriteTopics ft : lst)
			{
				favTopic.put(ft.getTopicId(), ft.getId());
			}
		}
		return favTopic;
	}

	@Override
	public List<FavoriteTopics> getStudentFavoriteTopic(Long userId, Integer limit, List<Long> subjectIds)
	{
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("select distinct(ft) from FavoriteTopics ft inner join fetch ft.chapter c inner join fetch ft.subject s inner join fetch ft.topic t where ft.userId=:userId and s.status=:sstatus and s.id in (:subjectIds) and ft.status=:status order by ft.id desc");
		query.setParameter("userId", userId);
		query.setParameter("sstatus", Subject.STATUS_ACTIVE);
		query.setParameterList("subjectIds", subjectIds);
		query.setParameter("status", FavoriteTopics.STATUS_ACTIVE);
		if (limit != null)
		{
			query.setMaxResults(limit);
		}
		return query.list();
	}

	@Override
	public List<UserActivity> getAllLastActivityOfRetailUser(Long userId, Long boardId, Integer limit, List<Long> subjectIds)
	{
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("select distinct(ua) from UserActivity ua inner join fetch ua.subject s inner join fetch ua.topic t inner join fetch t.chapter  where ua.userId=:userId  and s.id in (:subjectIds) and ua.status=:status and t.status=:tstatus and s.status=:sstatus and ua.boardId=:boardId order by ua.id desc");
		query.setParameter("userId", userId);
		query.setParameter("status", UserActivity.STATUS_ACTIVE);
		query.setParameter("sstatus", Subject.STATUS_ACTIVE);
		query.setParameterList("subjectIds", subjectIds);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		query.setParameter("sstatus", Subject.STATUS_ACTIVE);
		query.setParameter("boardId", boardId);
		if (limit != null)
		{
			query.setMaxResults(limit);
		}
		return query.list();
	}

	@Override
	public List<UserTest> getAttemptUnattemptTestDetails(Long userId, Long boardId, Boolean attemptedOrUnattempted, Integer limit)
	{
		Query query = null;
		if (attemptedOrUnattempted != null)
		{
			Integer flag = 0;
			if (attemptedOrUnattempted)
			{
				flag = 1;
			}
			query = sessionFactory.getCurrentSession().createQuery("from UserTest ut where ut.userId=:userId and ut.boardId=:boardId and ut.status=:status and ut.isActive=:isActive order by ut.id desc");

			query.setParameter("status", flag);
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery("from UserTest ut where ut.userId=:userId and ut.boardId=:boardId and ut.isActive=:isActive order by ut.id desc");

		}
		query.setParameter("userId", userId);
		query.setParameter("boardId", boardId);
		query.setParameter("isActive", UserTest.STATUS_ACTIVE);
		if (limit != null)
		{
			query.setMaxResults(limit);
		}
		return query.list();
	}

	@Override
	public User getUserObjById(Long stuId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where id=:stuId");
		query.setParameter("stuId", stuId);
		query.setMaxResults(1);

		return (User) query.uniqueResult();
	}

	@Override
	public List<UserActiveTopic> getUserActivityDetails(Long userId, Long sessionId, List<Long> subjectIds, Integer limit)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("Select distinct(uat) from UserActiveTopic uat inner join fetch uat.subject s inner join fetch uat.topic t inner join fetch t.chapter where uat.userId=:userId and uat.sessionId=:sessionId and uat.subjectId in (:subjectIds) and uat.status=:status and s.status=:sstatus and t.status=:tstatus");
		query.setParameter("userId", userId);
		query.setParameter("sessionId", sessionId);
		query.setParameterList("subjectIds", subjectIds);
		query.setParameter("status", UserActiveTopic.STATUS_ACTIVE);
		query.setParameter("sstatus", Subject.STATUS_ACTIVE);
		query.setParameter("tstatus", Topic.STATUS_ACTIVE);
		if (limit != null)
		{
			query.setMaxResults(limit);
		}

		return query.list();
	}

	@Override
	public List<Chapter> getChaptersTopicsBySubjectWithAnswerDetails(Long subjectId, Long gradeId, Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("questionAnswerFilterAllDetails");
		filter.setParameter("sUserId", userId);
		Query query = session.createQuery("SELECT DISTINCT(c) FROM Chapter c INNER JOIN FETCH c.topics t left join fetch t.questionTopic left join fetch t.userQuestionAnswer where c.subjectId=:subjectId and c.gradeId=:gradeId and c.status=:status and t.status=:tstatus");
		query.setLong("subjectId", subjectId);
		query.setLong("gradeId", gradeId);
		query.setInteger("status", Chapter.STATUS_ACTIVE);
		query.setInteger("tstatus", Topic.STATUS_ACTIVE);
		return query.list();
	}

	@Override
	public List<Assessment> getAllEtestAndAssignedTestByUser(Long userId, Long sessionId)
	{
		Session session = sessionFactory.getCurrentSession();
		Filter filter = session.enableFilter("studentTestFilter");
		filter.setParameter("userId", userId);
		filter.setParameter("status", SchoolStudentTest.STATUS_ACTIVE);
		filter.setParameter("sessionId", sessionId);
		String str = "select distinct(a) From Assessment a inner join fetch a.grade left join fetch a.studentTest st inner join fetch a.assignedAssessment aa  where  a.status = :status and aa.userId = :userId ";
		Query query = session.createQuery(str);
		query.setParameter("status", Assessment.STATUS_PUBLISHED);
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public Map<Long, String> getHighScoreAndTotalAttemptAssessment(List<Long> assIds)
	{

		Map<Long, String> map = new HashMap<Long, String>();
		if (!assIds.isEmpty())
		{
			Query query = sessionFactory.getCurrentSession().createQuery("select sst.assessmentId as id, max(sst.marks) as maxMarks,count(sst.id) as totalCount from SchoolStudentTest sst where sst.assessmentId in (:assessmentIds) group by sst.assessmentId");
			query.setParameterList("assessmentIds", assIds);
			List<Object[]> obj = query.list();
			if (!obj.isEmpty())
			{
				for (Object[] o : obj)
				{
					Long topicId = (long) o[0];
					String s = o[1] + "," + o[2];
					map.put(topicId, s);
				}
			}
		}
		return map;
	}

	@Override
	public List<AssessmentQuestion> getAssessmnetQuestLst(Long assessmentId)
	{
		String sql = "from AssessmentQuestion where assessmentId=:assessmentId and status=:status";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("assessmentId", assessmentId);
		query.setParameter("status", AssessmentQuestion.STATUS_ACTIVE);
		return query.list();

	}

	@Override
	public Double getAssessmentAverageMarks(Long assessmentId)
	{
		String sql = "SELECT AVG(marks) FROM SchoolStudentTest WHERE assessmentId=:assessmentId and status=:status GROUP BY assessmentId";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("assessmentId", assessmentId);
		query.setParameter("status", SchoolStudentTest.STATUS_ACTIVE);
		query.setMaxResults(1);
		return (Double) query.uniqueResult();
	}

	@Override
	public Integer getStudentGenScoreByTopicId(UserForm loginUser, Long topicId)
	{
		String sql = "select smartScore from TopicSmartScore where topicId=:topicId and userId=:userId";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("topicId", topicId);
		query.setParameter("userId", loginUser.getId());
		query.setMaxResults(1);
		Short s = (Short) query.uniqueResult();
		if (s != null)
		{
			return s.intValue();
		}
		return 0;
	}

	@Override
	public String saveOrUpdateProfileImg(long Id, String pImgName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE User SET profilePath=:profilePath WHERE id=:id");
		query.setLong("id", Id);
		query.setString("profilePath", pImgName);
		query.executeUpdate();

		return "success";
	}

	@Override
	public String getPassword(long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT password FROM User WHERE id=:id");
		query.setLong("id", userId);
		List listUser = (List) query.list();

		if (listUser != null && !listUser.isEmpty())
		{
			return "" + listUser.get(0);
		}
		else
		{
			return "";
		}

	}

	@Override
	public String updateUserPassword(long userId, String password)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE User SET password=:password WHERE id=:id");
		query.setLong("id", userId);
		query.setString("password", password);
		query.executeUpdate();

		return "success";
	}

	@Override
	public Long getAllAttemptedQuestionCount(List<Long> topicIds, Long userId, Long schoolId)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select count(id) from UserQuestionAnswer where topicId in (:topicIds) "
				+ "and userId=:userId and schoolId=:schoolId and isAttempted=:isAttempted and correctIncorrect=:correctIncorrect and status=:status");
		query.setParameterList("topicIds", topicIds).setParameter("userId", userId)
		.setParameter("schoolId", schoolId).setParameter("isAttempted", UserQuestionAnswer.ATTEMPTED)
		.setParameter("correctIncorrect", UserQuestionAnswer.IS_CORRECT).setParameter("status", UserQuestionAnswer.STATUS_ACTIVE);
		Long val=(Long) query.uniqueResult();
		if(val!=null && val>0l){
			return val;
		}
		return 0l;
	}
	
	
	@Override
	public List<Long> getMarkTopicsContentPermissionStudent(Long gradeId,Long sectionId,Long subjectId,Long teacherId)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "select topicId FROM TeacherContentPermission where gradeId=:gradeId and subjectId=:subjectId and sectionId=:sectionId and userId=:userId";
		Query query = session.createQuery(str);
		query.setParameter("gradeId", gradeId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("sectionId", sectionId);
		query.setParameter("userId", teacherId);

		List<Long> list = query.list();

	
		return list;
	}
	
	
	
	@Override
	public List<Long> getMarkTopicsContentPermissionStudentchapter(Long gradeId,Long sectionId,Long subjectId,Long teacherId)
	{
		Session session = sessionFactory.getCurrentSession();
		String str = "select chapterId FROM TeacherContentPermission where gradeId=:gradeId and subjectId=:subjectId and sectionId=:sectionId and userId=:userId";
		Query query = session.createQuery(str);
		query.setParameter("gradeId", gradeId);
		query.setParameter("subjectId", subjectId);
		query.setParameter("sectionId", sectionId);
		query.setParameter("userId", teacherId);

		
		
		List<Long> list = query.list();

	
		return list;
	}
	
	
	

	@Override
	public Long getTeacherID(Long schoolId, Long sessionId, Long sectionId, Long subjectId)
	{
		// TODO Auto-generated method stub
		
		
		
		
		Session session = sessionFactory.getCurrentSession();
		String str = "SELECT  userId FROM StudentTeacherSession where sessionId=:sessionId and schoolId=:schoolId and sectionId=:sectionId and subjectId=:subjectId and userRole=:userRole and status=:status";
		Query query = session.createQuery(str);
		
		query.setParameter("sessionId", sessionId);
		query.setParameter("schoolId", schoolId);
		query.setParameter("sectionId", sectionId);
		query.setParameter("subjectId", subjectId);
		
		query.setParameter("userRole", User.USERTYPE_TEACHER);
		query.setParameter("status", User.STATUS_ACTIVE);

		Long list = (Long) query.uniqueResult();

		return list;
		
		
		
	}

	@Override
	public String getGradeName(Long gradeId)
	{
		
		
		
		 Session session = sessionFactory.getCurrentSession();
		 Query query = session.createQuery("Select name FROM Grade WHERE id=:gradeId");
		 query.setLong("gradeId", gradeId);
		 return (String)query.uniqueResult();
	}
	
	@Override
	public String getSection(Long sectionId)
	{
		 Session session = sessionFactory.getCurrentSession();
		 Query query = session.createQuery("Select name FROM Section WHERE id=:sectionId");
		 query.setLong("sectionId", sectionId);
		return (String)query.uniqueResult();
	}
		
	 
	
	
	
	@Override 
	public User getStudentDetailOnCode(String schoolIdCode,String admissionNo) {
	      
		System.out.println("schoolIdCode$$$$$$$$$$$$$$$4=="+schoolIdCode);
		
		 Session session = sessionFactory.getCurrentSession();
		 //Long schoolIds=Long.parseLong(schoolId);
		 if(null!=schoolIdCode &&  schoolIdCode.length()>0){ 
		 Query query = session.createQuery("FROM User WHERE schoolIdCode=:schoolIdCode and admissionNo=:admissionNo ");
		 query.setParameter("schoolIdCode", schoolIdCode);
		 query.setParameter("admissionNo", admissionNo);
		 User user = (User) query.uniqueResult();
		 System.out.println("4328432========="+user);
	     return user;
		 }
		 else {
			 return null; 
		 }
	  
	  } 
	

	 @Override 
	 public User getStudentDetail(String schoolId,String admissionNo) {
	      
	
		 Session session = sessionFactory.getCurrentSession();
		 Long schoolIds=Long.parseLong(schoolId);
		 Query query = session.createQuery("FROM User WHERE schoolId=:schoolIds and admissionNo=:admissionNo ");
		 query.setLong("schoolIds", schoolIds);
		 query.setParameter("admissionNo", admissionNo);
		 User user = (User) query.uniqueResult();
	  return user;
	  
	  } 
	 
	 @Override public User getStudentId( Long schoolcode,String  addmitionNo)
	 {
		 
		 Session session = sessionFactory.getCurrentSession();
		 Query query = session.createQuery("FROM User WHERE admissionNo=:addmitionNo and schoolId=:schoolcode");
		 query.setLong("schoolcode", schoolcode);
		 query.setParameter("addmitionNo", addmitionNo);
		 User user = (User) query.uniqueResult();
		return user;
	}
	
}
