package com.flp.service;

import java.util.List;

import com.flp.model.ExcelUser;
import com.flp.model.Grade;
import com.flp.model.ParentChildMapping;
import com.flp.model.Section;
import com.flp.model.SessionSchoolGrade;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.User;
import com.flp.model.UserForm;

public interface SchoolAdminService {

	List<Grade> getAllGradeBySchoolId(Long schoolId);

	Long getCurrentSchoolSession(Long schoolId);
	
	User checkUser(String uEmail,String userName);

	ParentChildMapping checkParentChildMapping(long pId, long uId);
	
	String validateExcel(User user,UserForm userObj);

	String saveExcelUsers(List<ExcelUser> xcelUserlist);

	void saveParentChileMapping(Long pId, Long sId);

	void saveParent(List<User> parentList, Long sId);

	List<Section> getSectionByGradeId(Long schoolId, Long sessionId,
			Long gradeId);

	List<StudentTeacherSession> getAllStudentBySectionId(Long sectionId);

	List<ParentChildMapping> getParentsDetailsByUserId(Long userId);

	Boolean saveStudentTeacherSession(StudentTeacherSession sts);

	List<User> getAllSchoolTeacher(Long schoolId, Long sessionId);

	List<SessionSchoolGrade> getAllGradeSectionSubject(Long schoolId, Long sessionId);

	String editGradeName(long gradeId, String sectionName, long schoolId);

	String createSection(long schoolId, long sessionId, String sectionName, long gradeId);

	List<Subject> getSubjectBySectionId(Long sectionId);

	Boolean assignSubjectToTeacher(StudentTeacherSession studentTeacherSession);

	Boolean isAlreadyAssignSubject(StudentTeacherSession studentTeacherSession);

	List<StudentTeacherSession> getAssignSubjectTeacherList(Long schoolId,
			Long sessionId);

	void removeSubjectFromTeacher(Long studentTeacherSubjectId);

	List<User> getAllSchoolSmeList(Long schoolId);

	void inactivateUser(Long userId);

	void saveSubjectSectionMapping(String subjectIds, long sectionId);

	void removeMapping(Long subjectSectionId);

	Boolean isSubjectAlreadyUnassigned(StudentTeacherSession sts);

	List<Long> getStudentUnassignedSubjectList(Long userId, Long sectionId);

	void inactivateUserFromCurrentSession(Long userId, Long sessionId);

	List<StudentTeacherSession> getUnassignSubjectListByUserId(Long userId,
			Long sessionId);

	void removeUnassignedSubject(Long studentId, Long[] subjectIds,
			Long sessionId, Long schoolId);

	Long getGradeIdByectionId(Long sectionId);

	Long getUserSectionId(Long userId);

	List<Subject> getAllSubjectsBySectionIds(List<Long> sectionList);

	Boolean isAlreadyAssigned(Long subjectId, Long sectionId, Integer usertypeTeacher);

	List<Subject> getAllSubjectBySchoolId(Long schoolId);

	
	public boolean isSubjectExist(String sName, Long schoolId);
	
	List<Section> getAllSectionBySubjectId(Long subjectId, List<Long> ls);

	void updateMapping();

	

}
