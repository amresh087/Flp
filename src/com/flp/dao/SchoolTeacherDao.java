package com.flp.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.flp.model.Answer;
import com.flp.model.Assessment;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.Module;
import com.flp.model.Question;
import com.flp.model.QuestionComments;
import com.flp.model.QuestionTopic;
import com.flp.model.SchoolStudentTest;
import com.flp.model.Session;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;

import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.vo.GlobalVO;
/**
 * 
 * @author Amresh
 *
 */
public interface SchoolTeacherDao {

	List<Assessment> getUpcomingAssessmentDetails(UserForm loginUser);

	List<StudentTeacherSession> getTeacherSubjectSection(long userId, long sessionId);

	List<Chapter> getChpater(long gradeId, long subjectId);

	List<QuestionTopic> getQuestionList(String topicIds, long userId, long schoold, int i);

	String createAssessment(Assessment assessment);

	List<Assessment> getAllAssessmentListbyUser(Long schoolId, Long sessionId,
			Long loginUserId);

	void assignedAssessment(AssignedAssessment aa);
	
	long saveAnswerLoQuestion(Answer a);
	long saveLoQuestion(Question q);

	Boolean isAlreadyAssigned(Long studentId, Long assessmentId);

	void publishedAssessment(Long assessmentId);

	List<Topic> getTopicDetailsByChapter(Long subjectId, Long chapterId);

	Integer assignedTopicCountByTopic(Long topicId,Long userId);

	void saveTopicResources(AssignTopicResources atr);

	Boolean isAlreadyAssignedTopic(AssignTopicResources atr);

	List<AssignTopicResources> getSectionsByTopicId(Long topicId,
			Integer isTopic);

	void unAssignTopicFromSection(Long sectionId, Long topicId);

	List<Module> getModulesByTopic(Long topicId);

	Integer assignedStudentCountByModuleId(Long moduleId,Long userId);

	Boolean isAlreadyAssignedModule(AssignTopicResources atr);

	List<AssignTopicResources> getSectionsByModuleId(Long topicId,
			Long moduleId, Integer isResources);

	void unAssignModuleFromSection(Long sectionId, Long topicId, Long moduleId);

	List<AssignedAssessment> getAllAssignedAssessmentStudentList(
			Long asssessmentId, Long assignedBy);

	void unAssignAssessment(long assignBy, Long[] studentIds, Long assessmentId);

	Assessment getAssessment(long assessmentId);

	void removeAssessment(Long assessmentId);

	long saveQuestion(Question q);

	long saveAnswer(Answer a);


	List<Chapter> getChpatersByGradeAndSubjects(List<Long> gradeIds,	List<Long> subjectIds, Long schoolId);
	Map<Long,Integer>getModuleCountByTopicId(List<Long> topicIdList);
	Map<Long,Integer>getQuestionCountByTopicId(List<Long> topicIdList);
	
	List<QuestionTopic> getAllQuestionsByTopic(Long topicId);

	List<Assessment> getCompletedTest(UserForm loginUser);

	List<Assessment> getCreatedTest(UserForm loginUser);

	void updateAssessment(Assessment ass);

	Boolean updatetopicdetails(Long id, String name);

	Boolean updatechapterdetails(Long id, String name);

	boolean isMappedToTest(Long questionId);

	List<Assessment> getAssessmentByLimitDate(UserForm loginUser, Date currentDate, Date endDate);

	List<SchoolStudentTest> getStudentAssessmentDetailsById(Long assessmentId);

	List<SubjectSection> getLearningReportForSubjects(Long sectionId, Long schoolId, Long sessionId);

	Integer getTotalQuestionCountBySubject(Long subjectId);

	List<Chapter> getLearningReportForChapters(Long subjectId, Long schoolId, Long sessionId);

	Integer getTotalQuestionCountByChapter(Long chapterId);

	List<Topic> getLearningReportForTopic(Long chapterId, Long schoolId);

	Integer getTotalQuestionCountByTopic(Long topicId);

	List<StudentTeacherSession> getStudentListBySubjectId(Long subjectId, Long sectionId, Long schoolId, Long sessionId);

	List<UserQuestionAnswer> getUserQuestionAnswerList(String type, Long id, Long gradeId, Long schoolId);

	Long getTopicIdByGradeSubjectChapterTopic(String gradeName, String subjectName, String chapterName,
			String topicName, String topicName2);

	Boolean addQuestionComment(QuestionComments qc);

	List<QuestionComments> getAllTeacherCommentsByQuestId(Long questId, Long userId);

	List<String> getQuestionListforAuto(String topicIds, long userId, Long totalQuestions, Long schoolId, Integer createBy, Integer eQuest, Integer mQuest, Integer dQuest);

	List<StudentTeacherSession> getTeacherGradeSubjectDetails(Long userId, Long schoolId);

	Map<Long, Integer> getStudentCountBySectionIds(List<Long> sectionIds,Long schoolId,List<Long> subjectIds);
	List getTeacherSubjectStudat(List<Long> sectionIds,Long schoolId,List<Long> subjectIds);

	
	
	
	Long getTypeId(String qtDifficultyLevel);

	Long getQuestionTopicId(Long topicId, Long quesId, Long difficultyId, Long cognitiveId);


	List<Assessment> getUpcomingAssessment(UserForm loginUser, Long gradeId, Long subjectId);

	List<Assessment> getCompletedAssessment(UserForm loginUser, Long gradeId, Long subjectId);

	List<User> getUnassignStudent(Long subId, Long secId);

	List<Assessment> getAssignmentDetail(Long gradeId, Long subjectId);

	List<AssignTopicResources> getTopicAssignedStudents(Long id, Long userId);

	List<Long> getAllTopicIdsByChapter(Long subjectId, Long chapterId);

	Long getStudentCountBySectionIds(Long subjectId, Long secId);

	List<AssignTopicResources> getAllAssignTopicListByUser(List<Long> topicIds, Long userId, Long subjectId, Long sectionId);

	List<AssignTopicResources> getAllAssignModuleListByUser(Long topicId, Long userId, Long subjectId, Long secId);

    Assessment getAllTestDetails(UserForm loginUser,Long grdId, Long subId, Long assesmentId);
    
    Assessment getUnattemtedAllTestDetails(UserForm loginUser,Long grdId, Long subId, Long assesmentId);
    
	List<Long> getAllTopicIdsInSubject(Long subjectId, Long schoolId);

	Long getAllQuestionCountByTopicIds(List<Long> topicIds);

	//List<Assessment> getAllTestDetail(UserForm loginUser,Long grdId, Long subId, Long assesmentId);

	Boolean unassignStudentfromAssignment(Long gId, Long sId, Long assmentId, Long[] unassignUserLst);
	
	List<Long> getMarkTopics(Long userId,Long subjectId);
	List<Long> getMarkTopicsContentPermission(Long userId,Long subjectId);
	 String updateModuleResources(GlobalVO globalVO) throws IOException;
	Map<Long, Integer> getTopicCountBySubjectId(List<Long> subjectIdsSet, Long gradeId);

	Map<Long, Integer> getTeacherProgressTopicCountBySubjectId(List<Long> subIds, Long userId, Long sectionId);

	Session getSessionObj(Long sessionId);


	

	

	List<User> getStudentCompletedAssessment(UserForm loginUser, Long grdId, Long subId, String search , Long secId);


	List<SchoolStudentTest> searchStudentTestDetail(UserForm loginUser, Long grdId, Long subId, Long secId, Long studentId);

	Subject getSubjectObjById(Long subId);

	Integer updateUpcomingAssessment(Assessment ass);

	List<Long> getAllAssignedTopicByTopicIds(List<Long> topicIdList, Long secId, Long grdId, Long subId);

	List<Assessment> getCreatedTestByTeacher(UserForm loginUser, Long subId, Long grdId);

	String updateResource(GlobalVO globalVO);


	List<QuestionTopic> autoPreviewAssessment(List<String> result, String topicIds, long id, Long schoolId);


	List<AssignTopicResources> getAssignedTopicReport(Long grdId, Long subId, Long secId,long userId);

	List<AssignTopicResources> getAssignedResourceReport(Long grdId, Long subId, Long secId, long userid);
	
	String removeAssmt(Long assmtId, Long subId, Long grdId, long id, Long schoolId);

	//List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId);
	
	public List<QuestionTopic> getPageQuestionsByTopic(int result, int offsetreal,Long topicId);
	
	public int getSize(Long topicId);
	List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId,Long loid);

	public List<User> getUserDetailForEmail(List<Long> id,int userType);
	

}
