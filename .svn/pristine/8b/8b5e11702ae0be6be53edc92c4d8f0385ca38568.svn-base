package com.flp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.flp.model.Assessment;
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
import com.flp.model.Session;
import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserActiveTopic;
import com.flp.model.UserActivity;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.model.UserTest;

public interface StudentCommonService
{

	List<Board> getAllBoardList();

	List<Grade> getGradeByBoardId(Long boardId);

	List<Grade> getAllGradeList();

	String mapBoardGrade(Long boardId, Long gradeId);

	List<Subject> getAllSubjectList();

	List<Session>studentSession( Long Id);
	
	List<Topic> getAllTopicList();

	String mapBoardGradeSubject(Long boardId, Long gradeId, Long subjectId);

	List<Chapter> getAllChapterList();

	String mapBoardGradeSubjectChapter(Long boardId, Long gradeId, Long subjectId, String chapterName);

	//BoardGrade getMapExitForBoardGrade(Long boardId, Long gradeId);

	//BoardGradeSubject getMapExitForBoardGradeSubject(Long boardId, Long gradeId, Long subjectId);

	Chapter getMapExitForBoardGradeSubjectChapter(Long boardId, Long gradeId, Long subjectId, String chapterName);

	Topic getMapExitForBoardGradeSubjectChapterTopic(Long boardId, Long gradeId, Long subjectId, Long chapterId, String topicName);

	String mapBoardGradeSubjectChapterTopic(Long boardId, Long gradeId, Long subjectId, Long chapterId, String topicName);

	//List<BoardGradeSubject> getSubjectByBoardAndGrade(Long boardId, Long gradeId);

	List<Chapter> getChapterByBoardGradeAndSubject(Long boardId, Long gradeId, Long subjectId);

	DemoGradeTopic getMappingForDemo(Long boardId, Long gradeId, Long subjectId, Long chapterId, Long topicId);

	String mapWithDemo(Long boardId, Long gradeId, Long subjectId, Long chapterId, Long topicId);

	List<Assessment> geteTestList(Long id,Long sessionId);

	List<Subject> getSubjectChapterTopics(List<Long> subjectIdList, boolean isFirst, Long gradeId, Long schoolId,Long userId);

	List<AssignTopicResources> getAssignedResoureAndTopics(
			List<Long> subjectIdList,  Long gradeId, Long schoolId,
			Long sessionId, Long sectionId, Long userId);

	List<Chapter> getChaptersTopicsBySubject(Long subjectId, Long gradeId,Long userId);

	List<Assessment> getAssignedTest(Long id, Long sessionId);

	UserActiveTopic getStudentLastActiveTopic(long id, Long schoolId, Long sessionId, Long subjecctId);

	void saveTest(float marks, long subjectId, int total, int correct,
			int incorrect, int unAttempted, UserForm loginUser,
			List<String> questionDetail, long assessmentId, float totalMarks);

	SchoolStudentTest getStudentAssessmentSummary(Long id, Long assessmentId);

	Object[] getTopperAndMaxAttendAssessment(Long schoolId, Long assessmentId);

	Integer isValidAssessment(UserForm loginUser, Long testId);

	Object[] getCorrectIncorrectQuestionCount(Long userId, Long topicId);

	int getGScore(long topicId, UserForm loginUser);
	 
	int questionAttemptcount(long topicId, UserForm loginUser);
	
	int updatescoreData(long topicId, UserForm loginUser);
	
	Date  getmaxLxDate(long topicId, UserForm loginUser);

	Question getNextQuestion(long topicId, int gScore, long userId);

	int saveQuestion(HttpServletRequest request);
	String saveOrUpdateProfileImg(long Id,String pImgName );

	 String getPassword(long userId);
	 public String updateUserPassword(long userId,String password );
	Integer getuserTopicGenScore(Long userId, Long topicId);

	List<ModuleTopic> getModulesByTopicId(Long topicId);
	
	List<AssignedAssessment> getAssessmentByStudentId(Long userId);

	Map<Long, String> getTopperAndMaxStudentAttendAssessments(Long gradeId, Long schoolId);

	List<UserQuestionAnswer> getStudentLearningReportsbyChapterOrTopicId(UserForm loginUser, Long id, String type);

	List<Subject> getSubjectChapterTopicsForRetailUser(List<Long> subjectIds, boolean b, Long gradeId, Long boardId, Long id);

	UserActivity getLastUserActivity(Long topicId, Long subjectId,Long userId);

	void saveOrUpdateUserActivity(UserActivity userActivity);

	UserActiveTopic getUserActiveTopic(Long subjectId, Long userId);

	void saveOrUpdateUserActiveTopic(UserActiveTopic userActivity);

	UserActivity getUserLastActiveTopic(Long userId, Long boardId, Long sid);

	List<Subject> getSubjectsByGradeAndBoard(Long boardId, Long gradeId);

	Subject getSubjectDetailsById(Long subjectId);

	Boolean addFavouriteTopic(FavoriteTopics ft);

	Map<Long, Long> getAllFavoriteTopic(Long userId, Long subjectId);

	List<FavoriteTopics> getStudentFavoriteTopic(Long userId, Integer limit, List<Long> subjectIds);

	List<UserActivity> getAllLastActivityOfRetailUser(Long id, Long boardId,Integer limit, List<Long> subjectIds);

	List<UserTest> getAttemptUnattemptTestDetails(Long id, Long boardId, Boolean attemptedOrUnattempted, Integer limit);

	Topic getTopicObj(Long topicId);

	User getUserObjById(Long stuId);

	List<UserActiveTopic> getUserActivityDetails(Long id, Long sessionId, List<Long> subjectIds, Integer limit);

	List<Chapter> getChaptersTopicsBySubjectWithAnswerDetails(Long subjectId, Long gradeId, Long userId);

	List<Assessment> getAllEtestAndAssignedTestByUser(Long userId, Long sessionId);

	Map<Long, String> getHighScoreAndTotalAttemptAssessment(List<Long> assIds);

	Map<Long, Float> getAssessmentQuestionMap(Long assessmentId);

	Double getAssessmentAverageMarks(Long assessmentId);

	Integer getStudentGenScoreByTopicId(UserForm loginUser, Long id);

	Long getAllAttemptedQuestionCount(List<Long> topicIds, Long userId, Long schoolId);
	
	
	List<Long> getMarkTopicsContentPermissionStudent(Long gradeId,Long sectionId,Long subjectId,Long teacherId);
	List<Long> getMarkTopicsContentPermissionStudentchapter(Long gradeId,Long sectionId,Long subjectId,Long teacherId);
	Long getTeacherID(Long schoolId,Long sessionId,Long sectionId,Long subjectId);
	
	 User getStudentDetail(String schoolcode,String addmitionNo);
	 User getStudentDetailOnCode(String schoolcode,String addmitionNo);
	User getStudentId(Long schoolcode,String addmitionNo);
		String getGradeName(Long gradeId);
		
		String getSection(Long SectionId);
}
