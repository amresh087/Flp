package com.flp.dao;

import java.util.List;

import com.flp.model.ParentChildMapping;
import com.flp.model.Section;
import com.flp.model.StudentTeacherSession;
import com.flp.model.SubjectSection;
import com.flp.model.TeacherContentPermission;
import com.flp.model.TeacherMarkTopicDetails;

import com.flp.model.User;
import com.flp.model.UserLogDetails;
/**
 * 
 * @author Amresh
 *
 */
public interface UserDAO extends GenericDao<User, Long>
{
	Boolean saveUserRegistration(User user);

	User getUserById(Long userId);

	List<User> getAllUsersListByType(String searchData, Integer searchUserType);

	User getUserDetailsByTokenId(String decrypToken);

	void saveLog(UserLogDetails uld);

	Boolean isEmailExist(String emailId);

	void saveParentChildMapping(ParentChildMapping pcm);

	List<User> getAllUserByRoleId(Integer roleId);

	List<User> getAllUserBySchoolId(Long sId);

	User getUserByEmailId(String emailId);

	Boolean isUserExistByUserNameOrAdmissionNo(String userName,
			String admissionNo,Long schoolId);

	Section getSection(Long schoolId, String gName, String sectionName);

	void saveStudentTeacherSession(StudentTeacherSession sts);

	List<SubjectSection> getUserSubjects(Long sessionId, Long userId, Integer userType, Long sectionId);

	List<User> getAllUserByAdminsionNo(List<String> admisionNoList,
			Long schoolId);

	List<User> getAllParentList(Long schoolId);

	List<SubjectSection> getRetailUserSubjects(Long userId, Integer userType);

	List<User> getSearchedStudents(String studentName);

	Boolean updateLog( String sessionId);

	Integer getCommentCount();
	void teacherMarkTopicDetails(TeacherMarkTopicDetails tmd);
	void teacherMarkTopicDetailsUnCheck(TeacherMarkTopicDetails tmd);
	boolean deleteModuleOrDeactivateStatus(Long topicId,Long moduleId);
	void teacherMarkTopicContentPermissionDetails(TeacherContentPermission tmd);
	void teacherMarkTopicContentPermissionDetailsUnCheck(TeacherContentPermission tmd);

	
}
