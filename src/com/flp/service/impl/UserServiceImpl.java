package com.flp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.DemoGradeTopicDao;
import com.flp.dao.SectionDao;
import com.flp.dao.SubjectDao;
import com.flp.dao.TopicDao;
import com.flp.dao.UserDAO;
import com.flp.dao.UserQuestionAnswerDao;
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
import com.flp.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DemoGradeTopicDao demoGradeTopicDao;
	@Autowired
	private UserQuestionAnswerDao userQuestionAnswerDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private SectionDao sectionDao;

	@Transactional
	@Override
	public Boolean saveUserRegistration(User user)
	{
		return userDAO.saveUserRegistration(user);
	}

	@Transactional
	@Override
	public User getUserById(Long userId)
	{
		try
		{
			return userDAO.getUserById(userId);
		}
		catch (Exception e)
		{

		}
		return null;

	}

	@Transactional
	@Override
	public void SaveOrUpdate(User user)
	{
		userDAO.saveUserRegistration(user);

	}

	@Transactional
	@Override
	public User getUserByEmailId(String emailId)
	{
		return userDAO.getUserByEmailId(emailId);
		//return null;
	}

	@Override
	public User mergeUser(User user)
	{
		return userDAO.mergeEntity(user);
	}

	@Override
	@Transactional
	public List<User> getAllUsersListByType(String searchData, Integer searchUserType)
	{
		return userDAO.getAllUsersListByType(searchData, searchUserType);
	}

	@Override
	@Transactional
	public User getUserDetailsByTokenId(String decrypToken)
	{
		return userDAO.getUserDetailsByTokenId(decrypToken);
	}

	@Override
	@Transactional
	public void saveLog(UserLogDetails uld)
	{
		userDAO.saveLog(uld);

	}

	@Override
	@Transactional
	public Boolean isEmailExist(String emailId) {
		return userDAO.isEmailExist(emailId);
	}

	@Override
	@Transactional
	public List<DemoGradeTopic> getDemoTopics(Long boardId, Long gradeId)
	{
		return demoGradeTopicDao.getDemoTopics(boardId, gradeId);
	}

	@Override
	@Transactional
	public List<Float> getPieChartReport(Long userId, Long gradeId)
	{
		return userQuestionAnswerDao.getPieChartReport(userId, gradeId);
	}

	@Override
	@Transactional
	public void saveParentChildMapping(ParentChildMapping pcm) {
		userDAO.saveParentChildMapping(pcm);
	}
	

	@Override
	@Transactional
	public List<Subject> getLearningSubjects(UserForm user)
	{
		List<Subject> list = null;
		if(user.getUserType() == User.USERTYPE_DEMO)
		{
			list = subjectDao.getDemoSubjects(user.getBoardId(), user.getGradeId());
		}
		else
		{
			list = subjectDao.getSubscribedSubjects(user.getBoardId(), user.getGradeId(), user.getId());
		}
		
		return list;
	}

	@Override
	@Transactional
	public List<Topic> getTopics(Long boardId, Long gradeId, Long chapterId)
	{
		return topicDao.getTopics(boardId, gradeId, chapterId);
	}


	@Override
	@Transactional
	public List<Long> getDemoTopicIds(Long chapterId)
	{
		return demoGradeTopicDao.getDemoTopicIds(chapterId);
	}

	@Override
	@Transactional
	public List<TopicSmartScore> getUserSSByTopic(UserForm user) {
		return topicDao.getUserSSByTopic(user);
	}

	@Override
	@Transactional
	public List<Subject> getAllSubjectWithChapterAndTopic(Long boardId,Long gradeId) {
		return subjectDao.getAllSubjectWithChapterAndTopic(boardId,gradeId);
	}
	
	@Override
	@Transactional
	public List<User> getAllUserByRoleId(Integer roleId) {
		return userDAO.getAllUserByRoleId(roleId);
	}

	@Override
	@Transactional
	public List<User> getAllUserBySchoolId(Long sId) {

		
		
		return userDAO.getAllUserBySchoolId(sId);
	}

	@Override
	@Transactional
	public Boolean isUserExistByUserNameOrAdmissionNo(String userName,
			String admissionNo,Long schoolId) {
		return userDAO.isUserExistByUserNameOrAdmissionNo(userName,admissionNo,schoolId);
	}

	@Override
	@Transactional
	public Section getSection(Long schoolId, String gName, String sectionName) {
		return userDAO.getSection(schoolId,gName,sectionName);
	}

	@Override
	public void saveStudentTeacherSession(StudentTeacherSession sts) {

		
		 userDAO.saveStudentTeacherSession(sts); 
	}

	@Override
	@Transactional
	public List<SubjectSection> getUserSubjects(Long sessionId, Long userId,
			Integer userType, Long sectionId) {
		return userDAO.getUserSubjects(sessionId, userId, userType, sectionId); 
	}

	@Override
	@Transactional
	public List<User> getAllUserByAdminsionNo(List<String> admisionNoList,
			Long schoolId) {
		// TODO Auto-generated method stub
		return userDAO. getAllUserByAdminsionNo(admisionNoList,	schoolId);
	}

	@Override
	@Transactional
	public List<User> getAllParentList(Long schoolId) {
		// TODO Auto-generated method stub
		return userDAO.getAllParentList(schoolId);
	}

	@Override
	@Transactional
	public Long getSectionByGradeAndBoardId(Long gradeId, Long boardId)
	{
		return sectionDao.getSectionByGradeAndBoardId(gradeId,boardId);
	}

	@Override
	@Transactional
	public List<SubjectSection> getRetailUserSubjects(Long userId, Integer userType)
	{
		return userDAO.getRetailUserSubjects(userId,userType);
	}

	@Override
	@Transactional
	public Boolean updateLog( String sessionId)
	{
		return userDAO.updateLog(sessionId);
	}

	@Override
	@Transactional
	public Integer getCommentCount()
	{
		return userDAO.getCommentCount();
	}
	@Override
	@Transactional
	public void teacherMarkTopicDetails(TeacherMarkTopicDetails tmd) {
		userDAO.teacherMarkTopicDetails(tmd);
	}
	
	
	
	
	
	
	@Override
	@Transactional
	public void teacherMarkTopicDetailsUnCheck(TeacherMarkTopicDetails tmd) {
		userDAO.teacherMarkTopicDetailsUnCheck(tmd);
				
		
	}
	@Override
	@Transactional
	public boolean deleteModuleOrDeactivateStatus(Long topicId,Long moduleId){
		return userDAO.deleteModuleOrDeactivateStatus(topicId,moduleId);
	}

	
	
	@Override
	@Transactional
	public void teacherMarkTopicContentPermissionDetails(TeacherContentPermission tmd) {
		userDAO.teacherMarkTopicContentPermissionDetails(tmd);
	}
	@Override
	@Transactional
	public void teacherMarkTopicContentPermissionDetailsUnCheck(TeacherContentPermission tmd) {
		userDAO.teacherMarkTopicContentPermissionDetailsUnCheck(tmd);
	}
	

	
	
	
}
