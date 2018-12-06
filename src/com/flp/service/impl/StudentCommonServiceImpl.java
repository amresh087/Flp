package com.flp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flp.dao.ChapterDao;
import com.flp.dao.StudentCommonDao;
import com.flp.dao.SubjectSectionDao;
import com.flp.dao.TopicDao;
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
import com.flp.model.SchoolStudentTestDetail;
import com.flp.model.Session;
import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserActiveTopic;
import com.flp.model.UserActivity;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.model.UserTest;
import com.flp.service.StudentCommonService;

@Service
public class StudentCommonServiceImpl implements StudentCommonService
{

	@Autowired
	private StudentCommonDao studentCommonDao;
	
	@Autowired
	private SubjectSectionDao subjectSectionDao;
	
	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired
	private TopicDao topicDao;

	@Transactional
	@Override
	public List<Board> getAllBoardList()
	{
		return studentCommonDao.getAllBoardList();
	}

	@Transactional
	@Override
	public List<Grade> getGradeByBoardId(Long boardId)
	{
		return studentCommonDao.getGradeByBoardId(boardId);
	}

	@Transactional
	@Override
	public List<Grade> getAllGradeList()
	{

		return studentCommonDao.getAllGradeList();
	}
	 
	
	
	

	@Transactional
	@Override
	public String mapBoardGrade(Long boardId, Long gradeId)
	{
		return studentCommonDao.mapBoardGrade(boardId, gradeId);
	}

	@Transactional
	@Override
	public List<Subject> getAllSubjectList()
	{
		return studentCommonDao.getAllSubjectList();
	}

	@Transactional
	@Override
	public String mapBoardGradeSubject(Long boardId, Long gradeId, Long subjectId)
	{
		return studentCommonDao.mapBoardGradeSubject(boardId, gradeId, subjectId);
	}

	@Transactional
	@Override
	public List<Chapter> getAllChapterList()
	{
		return studentCommonDao.getAllChapterList();
	}

	@Transactional
	@Override
	public String mapBoardGradeSubjectChapter(Long boardId, Long gradeId, Long subjectId, String chapterName)
	{
		return studentCommonDao.mapBoardGradeSubjectChapter(boardId, gradeId, subjectId, chapterName);

	}

	/*@Transactional
	@Override
	public BoardGrade getMapExitForBoardGrade(Long boardId, Long gradeId)
	{
		return studentCommonDao.getMapExitForBoardGrade(boardId, gradeId);
	}*/

	/*@Transactional
	@Override
	public BoardGradeSubject getMapExitForBoardGradeSubject(Long boardId, Long gradeId, Long subjectId)
	{
		return studentCommonDao.getMapExitForBoardGradeSubject(boardId, gradeId, subjectId);

	}*/

	@Transactional
	@Override
	public Chapter getMapExitForBoardGradeSubjectChapter(Long boardId, Long gradeId, Long subjectId, String chapterName)
	{
		return studentCommonDao.getMapExitForBoardGradeSubjectChapter(boardId, gradeId, subjectId, chapterName);
	}

	@Transactional
	@Override
	public Topic getMapExitForBoardGradeSubjectChapterTopic(Long boardId, Long gradeId, Long subjectId, Long chapterId, String topicName)
	{

		return studentCommonDao.getMapExitForBoardGradeSubjectChapterTopic(boardId, gradeId, subjectId, chapterId, topicName);

	}

	@Transactional
	@Override
	public String mapBoardGradeSubjectChapterTopic(Long boardId, Long gradeId, Long subjectId, Long chapterId, String topicName)
	{
		return studentCommonDao.mapBoardGradeSubjectChapterTopic(boardId, gradeId, subjectId, chapterId, topicName);
	}

	/*@Transactional
	@Override
	public List<BoardGradeSubject> getSubjectByBoardAndGrade(Long boardId, Long gradeId)
	{
		return studentCommonDao.getSubjectByBoardAndGrade(boardId, gradeId);
	}*/
	
	@Override
	@Transactional
	public List<Session>studentSession( Long Id)
	{
		return studentCommonDao.getStudentSession(Id);
	}

	@Transactional
	@Override
	public List<Topic> getAllTopicList()
	{
		return studentCommonDao.getAllTopicList();
	}

	@Transactional
	@Override
	public List<Chapter> getChapterByBoardGradeAndSubject(Long boardId, Long gradeId, Long subjectId)
	{
		return studentCommonDao.getChapterByBoardGradeAndSubject(boardId, gradeId, subjectId);
	}

	@Transactional
	@Override
	public DemoGradeTopic getMappingForDemo(Long boardId, Long gradeId, Long subjectId, Long chapterId, Long topicId)
	{
		return studentCommonDao.getMapExitForDemo(boardId, gradeId, subjectId, chapterId, topicId);
	}

	@Transactional
	@Override
	public String mapWithDemo(Long boardId, Long gradeId, Long subjectId, Long chapterId, Long topicId)
	{
		return studentCommonDao.mapWithDemo(boardId, gradeId, subjectId, chapterId, topicId);
	}

	@Transactional
	@Override
	public List<Assessment> geteTestList(Long id,Long sessionId) {
		return studentCommonDao.geteTestList(id,sessionId);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectChapterTopics(List<Long> subjectIdList, boolean isFirst, Long gradeId, Long schoolId,Long userId) {
		// TODO Auto-generated method stub
		return studentCommonDao.getSubjectChapterTopics(subjectIdList, isFirst, gradeId, schoolId,userId);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getAssignedResoureAndTopics(
			List<Long> subjectIdList, Long gradeId, Long schoolId,
			Long sessionId, Long sectionId,Long userId) {
		return studentCommonDao.getAssignedResoureAndTopics(subjectIdList, gradeId, schoolId, sessionId, sectionId, userId);
	}

	@Override
	@Transactional
	public List<Chapter> getChaptersTopicsBySubject(Long subjectId,Long gradeId,Long userId)
	{
		return studentCommonDao.getChaptersTopicsBySubject(subjectId,gradeId,userId);
	}
	
	
	
	@Override
	@Transactional
	public List<Long> getMarkTopicsContentPermissionStudentchapter(Long gradeId,Long sectionId,Long subjectId,Long teacherId)
	{//aaa
		return studentCommonDao.getMarkTopicsContentPermissionStudentchapter(gradeId,sectionId,subjectId,teacherId);
	} 
	
	@Override
	@Transactional
	public List<Long> getMarkTopicsContentPermissionStudent(Long gradeId,Long sectionId,Long subjectId,Long teacherId)
	{
		return studentCommonDao.getMarkTopicsContentPermissionStudent(gradeId,sectionId,subjectId,teacherId);
	} 
	
	@Override
	@Transactional
	public List<Assessment> getAssignedTest(Long userId, Long sessionId) {
		// TODO Auto-generated method stub
		return studentCommonDao.getAssignedTest(userId, sessionId);
	}

	@Override
	@Transactional
	public UserActiveTopic getStudentLastActiveTopic(long userId, Long schoolId, Long sessionId, Long subjecctId)
	{
		return studentCommonDao.getStudentLastActiveTopic(userId,schoolId,sessionId,subjecctId);
	}

	

	@Override
	@Transactional
	public void saveTest(float marks, long subjectId, int total, int correct,
			int incorrect, int unAttempted, UserForm loginUser,
			List<String> questionDetail, long assessmentId, float totalMarks) {
		
		List<SchoolStudentTestDetail> testDetailsList=new ArrayList<>();
		SchoolStudentTest test=new SchoolStudentTest();
		test.setAssessmentId(assessmentId);
		test.setMarks(marks);
		test.setSchoolId(loginUser.getSchoolId());
		test.setSessionId(loginUser.getSessionId());
		test.setSubjectId(subjectId);
		test.setTotalQuestion(total);
		test.setTotalCorrect(correct);
		test.setTotalIncorrect(incorrect);
		test.setTotalUnAttempted(unAttempted);
		test.setStudentId(loginUser.getId());
		test.setStatus(1);
		test.setPercentage(Float.parseFloat(String.format("%.2f", ((float)marks*100)/totalMarks)));
		
		for (String s:questionDetail) {
			String[] ar=s.split("#");
			String ansString;
			if(ar[2].trim().equals(""))
				ansString="";
			else
				ansString=ar[2].replaceAll("@", ",").substring(0,(ar[2].length()-1));
			
			SchoolStudentTestDetail tDetail=new SchoolStudentTestDetail();
			tDetail.setQuestionId(Long.parseLong(ar[0]));
			tDetail.setResult(Integer.parseInt(ar[1]));
			tDetail.setAnswerIds(ansString);
			tDetail.setMarks(Float.parseFloat(ar[3]));
			testDetailsList.add(tDetail);
		}
		
		long testId=subjectSectionDao.saveTest(test);
		
		
		for (SchoolStudentTestDetail s: testDetailsList) {
			s.setSchoolStudentTestId(testId);
			subjectSectionDao.saveTestDetail(s);
		}
		
		
	}

	@Override
	@Transactional
	public SchoolStudentTest getStudentAssessmentSummary(Long userId, Long assessmentId)
	{
		return studentCommonDao.getStudentAssessmentSummary(userId,assessmentId);
	}

	@Override
	@Transactional
	public Object[] getTopperAndMaxAttendAssessment(Long schoolId, Long assessmentId)
	{
		return studentCommonDao.getTopperAndMaxAttendAssessment(schoolId,assessmentId);
	}

	@Transactional
	@Override
	public Integer isValidAssessment(UserForm loginUser, Long testId) {
			return studentCommonDao.isValidAssessment(loginUser, testId);
	}

	@Override
	@Transactional
	public Object[] getCorrectIncorrectQuestionCount(Long userId, Long topicId)
	{
		return studentCommonDao.getCorrectIncorrectQuestionCount(userId,topicId);
	}

	@Override
	@Transactional
	public int getGScore(long topicId, UserForm loginUser) {
		return studentCommonDao.getGScore(topicId, loginUser);
	}
	
	@Override
	@Transactional
	public int updatescoreData(long topicId, UserForm loginUser)
	{  
	  return studentCommonDao.updatescoreData(topicId, loginUser);
	}

	
	@Override
	@Transactional
	public int questionAttemptcount(long topicId, UserForm loginUser)
	{
		// TODO Auto-generated method stub
		 return studentCommonDao.questionAttemptcount(topicId, loginUser);
	}
	
	@Override
	@Transactional
	public Date getmaxLxDate(long topicId, UserForm loginUser) {
		return studentCommonDao.getmaxLxDate(topicId, loginUser);
	}
	
	

	@Override
	@Transactional
	public Question getNextQuestion(long topicId, int gScore, long userId) {
		return studentCommonDao.getNextQuestion(topicId,gScore, userId);
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int saveQuestion(HttpServletRequest request)
	{
		UserForm user = (UserForm) request.getSession().getAttribute("user");
		String topicId = request.getParameter("topicId");
		String questionId = request.getParameter("questionId");
		String isAttempted = request.getParameter("isAttempted");
		String isCorrect = request.getParameter("isCorrect");
		String userAnswer = request.getParameter("userValues");
		String timeTaken = request.getParameter("timeTaken");
		String diffValue = request.getParameter("diffValue");
	 
		Map<Long, Integer> map = (Map<Long, Integer>) request.getSession().getAttribute("gScore");
		Integer gScore = map.get(Long.parseLong(topicId));
		int gScoreCopy = gScore;
		 
		int correctCount = Integer.parseInt(request.getParameter("correctCount"));
		Long loCount = studentCommonDao.getLOCount(Long.parseLong(topicId)); 
		String zoneid= studentCommonDao.getzoneId( Long.parseLong(questionId), Long.parseLong(topicId));
		 
		boolean isCheckRun = false;
		// 1 new
		boolean gScoreStatusForJspShowOrHide = false;
		if (gScore < 40 && !isCheckRun)
		{
			isCheckRun = true;
			if (loCount == 1 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 4 */ && gScore < 16)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 4, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 4;
			}

			// 16

			else if (loCount == 1 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 2 */ && gScore >= 16 && gScore < 26)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 5, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 5;
			}

			// 26

			else if (loCount == 1 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 2 */ && gScore >= 26 && gScore < 40)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 7, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 7;
				gScoreStatusForJspShowOrHide = true;

			}

			// 1 new

			// 2
			if (loCount == 2 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 8 */ && gScore < 16)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 2;
			}

			else if (loCount == 2 && Integer.parseInt(isCorrect)== 1 /* && correctCount <=2 */ && gScore >= 16 && gScore < 22)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 3, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 3;
				gScoreStatusForJspShowOrHide = true;
			}

			else if (loCount == 2 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 2 */ && gScore >= 22 && gScore <= 25)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 3, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 3;
				gScoreStatusForJspShowOrHide = true;
			}
			// gScore =28
			else if (loCount == 2 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 3 */ && gScore >= 28 && gScore <= 36)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 4, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 4;
				gScoreStatusForJspShowOrHide = true;
			}

			// 3 new

			if (loCount == 3 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 9 */ && gScore < 9)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 1, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 1;
			}

			else if (loCount == 3 && Integer.parseInt(isCorrect)== 1/* && correctCount <=6 */ && gScore >= 9 && gScore < 21)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 2;
				gScoreStatusForJspShowOrHide = true;

			}
			else if (loCount == 3 && Integer.parseInt(isCorrect)== 1 /* && correctCount <=3 */ && gScore >= 21 && gScore <= 40)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 6, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 6;
				if (gScore == 39)
				{
					gScore = gScore + 1;
					studentCommonDao.saveOrUpdateGScore(topicId, gScore, user.getId(), user.getGradeId());
				}
				gScoreStatusForJspShowOrHide = true;

			}

		}

		// Practic level
		if (gScore >= 40 && gScore < 100 && !isCheckRun)
		{
			isCheckRun = true;
			if (loCount == 1 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 2 */ && gScore >= 40 && gScore <= 42)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 2;
				gScoreStatusForJspShowOrHide = true;
			}
			else if (loCount == 1 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 3 */ && gScore >= 44 && gScore <= 60)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 8, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 8;
				gScoreStatusForJspShowOrHide = true;
			}
			else if (loCount == 1 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 2 */ && gScore >= 68 && gScore <= 84)

			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 16, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 16;
				gScoreStatusForJspShowOrHide = true;
			}

			// 2
			if (loCount == 2 && Integer.parseInt(isCorrect)== 1 /* && correctCount <= 4 */ && gScore >= 40 && gScore <= 52)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 4, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 4;
				gScoreStatusForJspShowOrHide = true;
			}

			else if (loCount == 2 && Integer.parseInt(isCorrect)== 1 /* && correctCount <=4 */ && gScore >= 56 && gScore <= 71)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 5, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 5;
				gScoreStatusForJspShowOrHide = true;
			}

			else if (loCount == 2 && Integer.parseInt(isCorrect)== 1 /* && correctCount <= 3 */ && gScore >= 76 && gScore <= 100)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 8, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 8;
			}

			// 3 new

			if (loCount == 3 && Integer.parseInt(isCorrect)== 1/* && correctCount <= 4 */ && gScore >= 40 && gScore <= 49)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 3, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 3;
			}

			else if (loCount == 3 && Integer.parseInt(isCorrect)== 1/* && correctCount <=6 */ && gScore >= 52 && gScore <= 77)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 5, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 5;
			}

			else if (loCount == 3 && Integer.parseInt(isCorrect)== 1/* && correctCount <=3 */ && gScore >= 82 && gScore <= 100)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 6, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 6;
			}

		}

		// Mastery level
		if (gScore >= 100 && gScore < 200 && !isCheckRun)
		{
			isCheckRun = true;
			 
			// 3 new

			if (loCount == 1 && Integer.parseInt(isCorrect)== 1 /* && correctCount ==1 */ && gScore < 200)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 20, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 20;
				/*if (gScore == 150)
				{
					gScoreStatusForJspShowOrHide = true;
				}
				else if (gScore < 200)
				{
					gScoreStatusForJspShowOrHide = false;
				}
				else
				{
					gScoreStatusForJspShowOrHide = true;
				}*/

			}

			if (loCount == 2 && Integer.parseInt(isCorrect)== 1 /* && correctCount ==1 */ && gScore < 200)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 10, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 10;
				/*if (gScore == 150)
				{
					gScoreStatusForJspShowOrHide = true;
				}
				else if (gScore < 200)
				{
					gScoreStatusForJspShowOrHide = false;
				}
				else
				{
					gScoreStatusForJspShowOrHide = true;
				}*/
			}

			if (loCount == 3 && Integer.parseInt(isCorrect)== 1 /* && Integer.parseInt(isCorrect)== 1 */ && gScore < 200)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 5, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 5;
				/*if (gScore == 150)
				{
					gScoreStatusForJspShowOrHide = true;
				}
				else if (gScore < 200)
				{
					gScoreStatusForJspShowOrHide = false;
				}
				else
				{
					gScoreStatusForJspShowOrHide = true;
				}*/
			}

			/* else if (loCount == 3 && correctCount == 2 && gScore > 200) { studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId()); correctCount = 0; gScore = 100 + 20 * 5;
			 * 
			 *   */

		}

		if (gScore >= 200  && !isCheckRun)
		{
			isCheckRun = true;

			if (loCount == 1 && Integer.parseInt(isCorrect)== 1 && gScore >= 200)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 2;
			}
			if (loCount == 2 && Integer.parseInt(isCorrect)== 1 && gScore >= 200)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 2;
			}
			if (loCount == 3 && Integer.parseInt(isCorrect)== 1 && gScore >= 200)
			{
				studentCommonDao.saveOrUpdateGScore(topicId, gScore + 2, user.getId(), user.getGradeId());
				correctCount = 0;
				gScore = gScore + 2;
			}
		}
		
		 

		if (gScore != gScoreCopy)
		{
			map.put(Long.parseLong(topicId), gScore);
			request.getSession().setAttribute("gScore", map); 
		}
		 

		studentCommonDao.saveQuestion(topicId, questionId, isAttempted, userAnswer, isCorrect, timeTaken, user,diffValue,zoneid);

		 
		return gScore;

	}
	 
	/* @SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int saveQuestion(HttpServletRequest request) {
		UserForm user=(UserForm) request.getSession().getAttribute("user");
		String topicId=request.getParameter("topicId");
		String questionId=request.getParameter("questionId");
		String isAttempted=request.getParameter("isAttempted");
		String isCorrect=request.getParameter("isCorrect");
		String userAnswer=request.getParameter("userValues");
		String timeTaken=request.getParameter("timeTaken");
		
	    String  locount=request.getParameter("locount");
	    System.out.println("locount=="+locount);
		
		//String questionType=request.getParameter("questionType");
		Map<Long, Integer> map=(Map<Long, Integer>)request.getSession().getAttribute("gScore");
		Integer gScore=map.get(Long.parseLong(topicId));
		int gScoreCopy=gScore;
		
		System.out.println("gScore="+gScore+"locount=="+locount);
		
		int correctCount=Integer.parseInt(request.getParameter("correctCount"));
		
		if(gScore==0 && correctCount==10){
			studentCommonDao.saveOrUpdateGScore(topicId, 20, user.getId(), user.getGradeId());
			correctCount=0;
			gScore=20;
		}
		else if(gScore==20 && correctCount==4){
			studentCommonDao.saveOrUpdateGScore(topicId, 32, user.getId(), user.getGradeId());
			correctCount=0;
			gScore=32;
		}
		else if(gScore==32 && correctCount==2)
		{
			studentCommonDao.saveOrUpdateGScore(topicId, 40, user.getId(), user.getGradeId());
			correctCount=0;
			gScore=40;
		}
		else if(gScore==40 && correctCount==2){
			studentCommonDao.saveOrUpdateGScore(topicId, 44, user.getId(), user.getGradeId());
			gScore=44;
			correctCount=0;
		}
		else if(gScore==44 && correctCount==8){
			studentCommonDao.saveOrUpdateGScore(topicId, 68, user.getId(), user.getGradeId());
			gScore=68;
			correctCount=0;
		}
		else if(gScore==68 && correctCount==8)
		{
			studentCommonDao.saveOrUpdateGScore(topicId, 100, user.getId(), user.getGradeId());
			gScore=100;
			correctCount=0;
		}
		else if((gScore==100 ||(gScore>100 && gScore%100==0)) && correctCount==5){
			studentCommonDao.saveOrUpdateGScore(topicId, gScore+10, user.getId(), user.getGradeId());
			gScore=gScore+10;
			correctCount=0;
		}
		else if((gScore==110 ||(gScore>100 && gScore%100==10)) && correctCount==10)
		{
			studentCommonDao.saveOrUpdateGScore(topicId, gScore+30, user.getId(), user.getGradeId());
			gScore=gScore+30;
			correctCount=0;
		}
		else if((gScore==140 ||(gScore>100 && gScore%100==40)) && correctCount==15)
		{
			studentCommonDao.saveOrUpdateGScore(topicId, gScore+60, user.getId(), user.getGradeId());
			gScore=gScore+60;
			correctCount=0;
		}
		
		if(gScore!=gScoreCopy)
		{
			map.put(Long.parseLong(topicId), gScore);
			request.getSession().setAttribute("gScore",map);
		}

		studentCommonDao.saveQuestion(topicId,questionId,isAttempted,userAnswer,isCorrect,timeTaken, user);
		return gScore;
		
	} 
 */
	
	
	@Override
	@Transactional
	public Integer getuserTopicGenScore(Long userId, Long topicId)
	{
		return studentCommonDao.getuserTopicGenScore(userId,topicId);
	}

	@Override
	@Transactional
	public List<ModuleTopic> getModulesByTopicId(Long topicId)
	{
		return studentCommonDao.getModulesByTopicId(topicId);
	}
	
	@Override
	@Transactional
	public List<AssignedAssessment> getAssessmentByStudentId(Long UserId){
		
		return studentCommonDao.getAssessmentByStudentId(UserId);
	}

	@Override
	@Transactional
	public Map<Long, String> getTopperAndMaxStudentAttendAssessments(Long gradeId, Long schoolId)
	{
		return studentCommonDao.getTopperAndMaxStudentAttendAssessments(gradeId,schoolId);
	}

	@Override
	@Transactional
	public List<UserQuestionAnswer> getStudentLearningReportsbyChapterOrTopicId(UserForm loginUser, Long id, String type)
	{
		return studentCommonDao.getStudentLearningReportsbyChapterOrTopicId(loginUser,id,type);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectChapterTopicsForRetailUser(List<Long> subjectIds, boolean isFirst, Long gradeId, Long boardId, Long userId)
	{
		return studentCommonDao.getSubjectChapterTopicsForRetailUser(subjectIds,isFirst,gradeId,boardId,userId);
	}

	@Override
	@Transactional
	public UserActivity getLastUserActivity(Long topicId, Long subjectId,Long userId)
	{
		return studentCommonDao.getLastUserActivity(topicId,subjectId,userId);
	}

	@Override
	@Transactional
	public void saveOrUpdateUserActivity(UserActivity userActivity)
	{
		 studentCommonDao.saveOrUpdateUserActivity(userActivity);
		
	}

	@Override
	@Transactional
	public UserActiveTopic getUserActiveTopic(Long subjectId, Long userId)
	{
		return studentCommonDao.getUserActiveTopic(subjectId,userId);
	}

	@Override
	@Transactional
	public void saveOrUpdateUserActiveTopic(UserActiveTopic userActivity)
	{
		studentCommonDao.saveOrUpdateUserActiveTopic(userActivity);
		
	}

	@Override
	@Transactional
	public UserActivity getUserLastActiveTopic(Long userId, Long boardId, Long sid)
	{
		return studentCommonDao.getUserLastActiveTopic(userId,boardId,sid);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByGradeAndBoard(Long boardId, Long gradeId)
	{
		return studentCommonDao.getSubjectsByGradeAndBoard(boardId,gradeId);
	}

	@Override
	@Transactional
	public Subject getSubjectDetailsById(Long subjectId)
	{
		return studentCommonDao.getSubjectDetailsById(subjectId);
	}

	@Override
	@Transactional
	public Boolean addFavouriteTopic(FavoriteTopics ft)
	{
		return studentCommonDao.addFavouriteTopic(ft);
	}

	@Override
	@Transactional
	public Map<Long, Long> getAllFavoriteTopic(Long userId, Long subjectId)
	{
		return studentCommonDao.getAllFavoriteTopic(userId,subjectId);
	}

	@Override
	@Transactional
	public List<FavoriteTopics> getStudentFavoriteTopic(Long userId, Integer limit,List<Long> subjectIds)
	{
		return studentCommonDao.getStudentFavoriteTopic(userId,limit,subjectIds);
	}

	@Override
	@Transactional
	public List<UserActivity> getAllLastActivityOfRetailUser(Long userId, Long boardId,Integer limit, List<Long> subjectIds)
	{
		return studentCommonDao.getAllLastActivityOfRetailUser(userId,boardId,limit,subjectIds);
	}

	@Override
	@Transactional
	public List<UserTest> getAttemptUnattemptTestDetails(Long userId, Long boardId, Boolean attemptedOrUnattempted, Integer limit)
	{
		return studentCommonDao.getAttemptUnattemptTestDetails(userId,boardId,attemptedOrUnattempted,limit);
	}

	@Override
	@Transactional
	public Topic getTopicObj(Long topicId)
	{
		return topicDao.getTopicObj(topicId);
	}

	@Override
	@Transactional
	public User getUserObjById(Long stuId)
	{
		return studentCommonDao.getUserObjById(stuId);
	}

	@Override
	@Transactional
	public List<UserActiveTopic> getUserActivityDetails(Long userId, Long sessionId, List<Long> subjectIds, Integer limit)
	{
		return studentCommonDao.getUserActivityDetails(userId,sessionId,subjectIds,limit);
	}

	@Override
	@Transactional
	public List<Chapter> getChaptersTopicsBySubjectWithAnswerDetails(Long subjectId, Long gradeId, Long userId)
	{
		return studentCommonDao.getChaptersTopicsBySubjectWithAnswerDetails(subjectId,gradeId,userId);
	}

	@Override
	@Transactional
	public List<Assessment> getAllEtestAndAssignedTestByUser(Long userId, Long sessionId)
	{
		return studentCommonDao.getAllEtestAndAssignedTestByUser(userId,sessionId);
	}

	@Override
	@Transactional
	public Map<Long, String> getHighScoreAndTotalAttemptAssessment(List<Long> assIds)
	{
		return studentCommonDao.getHighScoreAndTotalAttemptAssessment(assIds);
	}

	@Override
	@Transactional
	public Map<Long, Float> getAssessmentQuestionMap(Long assessmentId)
	{
		List<AssessmentQuestion> assessmnetQuestLst=studentCommonDao.getAssessmnetQuestLst(assessmentId);
		if(!assessmnetQuestLst.isEmpty()){
			Map<Long, Float> map=new HashMap<Long, Float>();
		for(AssessmentQuestion aq:assessmnetQuestLst){
			map.put(aq.getQuestionId(), aq.getMarks());
		}
		return map;
		}
		return null;
	}

	@Override
	@Transactional
	public Double getAssessmentAverageMarks(Long assessmentId)
	{
		
	return studentCommonDao.getAssessmentAverageMarks(assessmentId);
	}

	@Override
	@Transactional
	public Integer getStudentGenScoreByTopicId(UserForm loginUser, Long topicId)
	{
		return studentCommonDao.getStudentGenScoreByTopicId(loginUser,topicId);
	}
	@Override
	@Transactional
	public String saveOrUpdateProfileImg(long Id,String pImgName ){
		
		return studentCommonDao.saveOrUpdateProfileImg(Id, pImgName);
	}
	@Override
	@Transactional
	public String getPassword(long userId ){
		
		return studentCommonDao.getPassword(userId);
	}
	@Override
	@Transactional
	public String updateUserPassword(long userId,String password ){
		
		return studentCommonDao.updateUserPassword(userId,password);
	}

	@Override
	@Transactional
	public Long getAllAttemptedQuestionCount(List<Long> topicIds, Long userId, Long schoolId)
	{
		return studentCommonDao.getAllAttemptedQuestionCount(topicIds,userId,schoolId);
	}
	
	@Override
	@Transactional
	public Long getTeacherID(Long schoolId,Long sessionId,Long sectionId,Long subjectId)
	{
		
		 
		return studentCommonDao.getTeacherID(schoolId,sessionId,sectionId,subjectId);
	}

	
	
	@Transactional
	@Override
	public String getGradeName(Long gradeId)
	{
		return studentCommonDao.getGradeName(gradeId);
	}

	
	@Transactional
	@Override
	public String getSection(Long SectionId)
	{
		return studentCommonDao.getSection(SectionId);
	}

	
	
@Transactional
	@Override
	public User getStudentDetailOnCode(String schoolcode,String addmitionNo)
	{
		return studentCommonDao.getStudentDetailOnCode( schoolcode, addmitionNo);

	}



@Transactional
@Override
public User getStudentDetail(String schoolcode,String addmitionNo)
{
	return studentCommonDao.getStudentDetail( schoolcode, addmitionNo);

}

@Transactional
@Override
public User getStudentId(Long schoolcode,String addmitionNo)
{
	return studentCommonDao.getStudentId( schoolcode, addmitionNo);

}
	
}
