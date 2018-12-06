package com.flp.service;

import java.util.List;


import com.flp.model.DemoGradeTopic;
import com.flp.model.ParentChildMapping;
import com.flp.model.Section;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.TeacherContentPermission;
import com.flp.model.TeacherMarkTopicDetails;

import com.flp.model.Topic;
import com.flp.model.TopicSmartScore;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserLogDetails;
public interface UserService
{

	Boolean saveUserRegistration(User user);

	User getUserById(Long userId);

	void SaveOrUpdate(User user);

	User getUserByEmailId(String emailId);

	User mergeUser(User user);

	List<User> getAllUsersListByType(String searchData, Integer searchUserType);

	User getUserDetailsByTokenId(String decrypToken);

	void saveLog(UserLogDetails uld);

	Boolean isEmailExist(String emailId);

	List<DemoGradeTopic> getDemoTopics(Long boardId, Long gradeId);
	
	List<Subject> getLearningSubjects(UserForm user);
	
	List<Topic> getTopics(Long boardId, Long gradeId, Long chapterId);
	
	List<Float> getPieChartReport(Long userId, Long gradeId);

	void saveParentChildMapping(ParentChildMapping pcm);
	void teacherMarkTopicDetails(TeacherMarkTopicDetails tmd);
	void teacherMarkTopicDetailsUnCheck(TeacherMarkTopicDetails tmd);
	List<Long> getDemoTopicIds(Long chapterId);

	List<TopicSmartScore> getUserSSByTopic(UserForm user);

	List<Subject> getAllSubjectWithChapterAndTopic(Long boardId,Long gradeId);
	List<User> getAllUserByRoleId(Integer roleId);
	// Anand
	List<User> getAllUserBySchoolId(Long sId);

	Boolean isUserExistByUserNameOrAdmissionNo(String userName,
			String admissionNo,Long schoolId);

	Section getSection(Long schoolId, String gName, String sectionName);

	void saveStudentTeacherSession(StudentTeacherSession sts);

	List<SubjectSection> getUserSubjects(Long sessionId, Long id, Integer usertypeStudent, Long sectionId);

	List<User> getAllUserByAdminsionNo(List<String> admisionNoList,
			Long schoolId);

	List<User> getAllParentList(Long schoolId);

	Long getSectionByGradeAndBoardId(Long gradeId, Long boardId);

	List<SubjectSection> getRetailUserSubjects(Long userId, Integer userType);

	Boolean updateLog( String sessionId);

	Integer getCommentCount();
	boolean deleteModuleOrDeactivateStatus(Long topicId,Long moduleId);
	
	void teacherMarkTopicContentPermissionDetails(TeacherContentPermission tmd);
	void teacherMarkTopicContentPermissionDetailsUnCheck(TeacherContentPermission tmd);

	

}
