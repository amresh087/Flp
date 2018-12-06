package com.flp.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.flp.model.Assessment;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.Grade;
import com.flp.model.Module;
import com.flp.model.ModuleType;
import com.flp.model.QuestionComments;
import com.flp.model.QuestionTopic;
import com.flp.model.QuestionType;
import com.flp.model.SchoolStudentTest;
import com.flp.model.Section;
import com.flp.model.Session;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.vo.GlobalVO;


public interface SchoolTeacherService
{

	List<Assessment> getUpcomingAssessmentDetails(UserForm loginUser);

	List<StudentTeacherSession> getTeacherSubjectSection(long id, Long sessionId);

	List<Chapter> getChpater(long gradeId, long subjectId);

	List<QuestionTopic> getQuestionList(String topicIds, long userId, long schoolId, int i);

	String createAssessment(String assName, String startDate, String endDate, long gradeId, long subjectId, Long schoolId, List<String> mappingList, String instruction, int testType, int duration, long userId, Long sessionId, String sequence, String chapterIds, String topics, String studentIds, Long secId);

	List<Assessment> getAllAssessmentListbyUser(Long schoolId, Long sessionId, Long loginUserId);

	void assignAssessment(UserForm loginUser, Long[] studentIds, Long sectionId, Long assessmentId);

	void publishedAssessment(Long assessmentId);

	List<Topic> getTopicDetailsByChapter(Long subjectId, Long chapterId);

	Integer assignedTopicCountByTopic(Long id, Long userId);

	void saveTopicResources(AssignTopicResources atr);

	Boolean isAlreadyAssignedTopic(AssignTopicResources atr);

	List<AssignTopicResources> getSectionsByTopicId(Long topicId, Integer isTopic);

	void unAssignTopicFromSection(Long sectionId, Long topicId);

	List<Module> getModulesByTopic(Long topicId);

	Integer assignedStudentCountByModuleId(Long id, Long userId);

	Boolean isAlreadyAssignedModule(AssignTopicResources atr);
	
	String uploadLoQuestionFile(File mpf, Long userId, Long schoolId, Long topicId,Long loid);

	List<AssignTopicResources> getSectionsByModuleId(Long topicId, Long moduleId, Integer isResources);

	void unAssignModuleFromSection(Long sectionId, Long topicId, Long moduleId);

	List<AssignedAssessment> getAllAssignedAssessmentStudentList(Long asssessmentId, Long assignedBy);

	String saveUpdateQuestion(GlobalVO globalVO) throws IOException;
	String saveUpdateQuestionUpdate(GlobalVO globalVO) throws IOException;
	String uploadModule(GlobalVO globalVO) throws IOException;
	
	void unAssignAssessment(UserForm loginUser, Long[] studentIds, Long assessmentId);

	Assessment getAssessment(long assessmentId);

	void removeAssessment(Long assessmentId);

	String uploadQuestionFile(File mpf, Long userId, Long schoolId, Long topicId);

	List<Chapter> getChpatersByGradeAndSubjects(List<Long> gradeIds, List<Long> subjectIds, Long schoolId);

	Map<Long, Integer> getQuestionCountByTopicId(List<Long> topicIdList);

	Map<Long, Integer> getModuleCountByTopicId(List<Long> topicIdList);

	List<QuestionTopic> getAllQuestionsByTopic(Long topicId);
	 

	List<QuestionType> getQuestionType(String category, Integer status);

	List<ModuleType> getModuleTypes(String category, Integer status);

	List<Assessment> getCompletedTest(UserForm loginUser);

	List<Assessment> getCreatedTest(UserForm loginUser);

	void updateAssessment(Assessment ass);

	void uploadDocument(GlobalVO globalVO);

	void addWebModule(GlobalVO globalVO);

	Boolean updatetopicdetails(Long id, String name);

	Boolean updatechapterdetails(Long id, String name);

	List<Assessment> getAssessmentByLimitDate(UserForm loginUser, Date currentDate, Date endDate);

	List<SchoolStudentTest> getStudentAssessmentDetailsById(Long assessmentId);

	List<SubjectSection> getLearningReportForSubjects(Long sectionId, Long schoolId, Long sessionId);

	Integer getTotalQuestionCountBySubject(Long subjectId);

	List<Chapter> getLearningReportForChapters(Long subjectId, Long schoolId, Long sessionId);

	Integer getTotalQuestionCountByChapter(Long id);

	List<Topic> getLearningReportForTopic(Long chapterId, Long schoolId);

	Integer getTotalQuestionCountByTopic(Long id);

	List<StudentTeacherSession> getStudentListBySubjectId(Long subjectId, Long sectionId, Long schoolId, Long sessionId);

	List<UserQuestionAnswer> getUserQuestionAnswerList(String type, Long id, Long gradeId, Long schoolId);

	String uploadQuestionWithGradeAndSubjectwise(File excelFile, Long id, Long schoolId);

	Boolean addQuestionComment(QuestionComments qc);

	List<QuestionComments> getAllTeacherCommentsByQuestId(Long questId, Long userId);

	List<String> getQuestionListforAuto(String topicIds, long userId, Long totalQuestions,Long schoolId,Integer createBy,Integer eQuest,Integer mQuest,Integer dQuest);

	String addAutoCreateAssessment(String result, String testName, Integer testType, String startDate, String endDate, Long gradeId, Long subjectId, Long totalQuestion, Integer duration, String instruction, Long schoolId, long userId, Long sessionId,Integer totalMarks,String studentIds,Long secId,String chpaterIds,String topicIds);

	void saveUpdateQuestionComment(GlobalVO globalVO) throws IOException;

	List<StudentTeacherSession> getTeacherGradeSubjectDetails(Long userId, Long schoolId);

	Map<Long, Integer> getStudentCountBySectionIds(List<Long> sectionIds, Long schoolId, List<Long> subjectIds);

	List getTeacherSubjectStudat(List<Long> sectionIds, Long schoolId, List<Long> subjectIds);

	String updateExistingQuestions(File excelFile, long id, Long schoolId);

	List<Assessment> getUpcomingAssessment(UserForm loginUser, Long gradeId, Long subjectId);

	List<Assessment> getCompletedAssessment(UserForm loginUser, Long gradeId, Long subjectId);

	List<User> getUnassignStudent(Long subId, Long secId);

	List<Assessment> getAssignmentDetail(Long gradeId, Long subjectId);

	List<AssignTopicResources> getTopicAssignedStudents(Long id, Long userId);

	List<Long> getAllTopicIdsByChapter(Long subjectId, Long chapterId);

	Long getStudentCountBySectionId(Long subjectId, Long secId);

	List<AssignTopicResources> getAllAssignTopicListByUser(List<Long> topicIds, Long userId, Long subjectId, Long sectionId);

	List<AssignTopicResources> getAllAssignModuleListByUser(Long topicId, Long userId, Long subjectId, Long secId);

	List<Long> getAllTopicIdsInSubject(Long subjectId, Long schoolId);

	Assessment getAllTestDetails(UserForm loginUser, Long grdId, Long subId, Long assesmentId);
	Assessment getUnattemtedAllTestDetails(UserForm loginUser, Long grdId, Long subId, Long assesmentId);
	
	Long getAllQuestionCountByTopicIds(List<Long> topicIds);

	// List<Assessment> getAllTestDetail(Long grdId, Long subId, Long assesmentId);

	//List<Assessment> getAllTestDetail(UserForm loginUser, Long grdId, Long subId, Long assesmentId);

	List<Long> getMarkTopics(Long userId,Long subjectId);
	List<Long> getMarkTopicsContentPermission(Long userId,Long subjectId);
	
	Boolean unassignStudentfromAssignment(Long gId, Long sId, Long assmentId, Long[] unassignUserLst);
	String updateModuleResources(GlobalVO globalVO) throws IOException;
	Map<Long, Integer> getTopicCountBySubjectId(List<Long> subjectIdsSet, Long gradeId);

	Map<Long, Integer> getTeacherProgressTopicCountBySubjectId(List<Long> subIds, Long id, Long sectionId);

	Subject getSubjectObjById(Long subId);

	Grade getGradeObjById(Long gradeId);

	Section getSectionObjById(Long secId);

	List<User> getStudentCompletedAssessment(UserForm loginUser, Long grdId, Long subId, String search , Long secId);

	List<SchoolStudentTest> searchStudentTestDetail(UserForm loginUser, Long grdId, Long subId, Long secId, Long studentId);

	Topic getTopicobjById(Long topicId);

	List<String> getChapterNamesByChapIds(String chapIds);

	List<String> getTopicNamesbyIds(String topicIds);

	Integer updateUpcomingAssessment(Assessment ass);

	List<Long> getAllAssignedTopicByTopicIds(List<Long> topicIdList, Long secId, Long grdId, Long subId);

	List<Assessment> getCreatedTestByTeacher(UserForm loginUser, Long subId, Long grdId);

	String uploadResources(GlobalVO globalVO) throws IOException;

	String updateResource(GlobalVO globalVO);


	List<QuestionTopic> autoPreviewAssessment(List<String> result, String topicIds, long id, Long schoolId);


	List<AssignTopicResources> getAssignedTopicReport(Long grdId, Long subId, Long secId, long userId);

	List<AssignTopicResources> getAssignedResourceReport(Long grdId, Long subId, Long secId, long id);

	//List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId);

	String removeAssmt(Long assmtId, Long subId, Long grdId, long id, Long schoolId);

	String getSessionDate(UserForm loginUser);
	List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId,Long loid);
	
	List<QuestionTopic> getPageQuestionsByTopic(int result, int offsetreal, Long topicId);

	public int getSize(Long topicId);


	public List<User> getUserDetailForEmail(List<Long> id,int userType);


}
