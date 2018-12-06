package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.UserDAO;
import com.flp.model.ModuleTopic;
import com.flp.model.ParentChildMapping;
import com.flp.model.QuestionComments;
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
@Repository
public class UserDAOImpl extends GenericDaoImpl<User, Long> implements UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean saveUserRegistration(User user)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}

	@Override
	public User getUserById(Long userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE id = :userId");
		query.setParameter("userId", userId);
		return (User) query.uniqueResult();
	}

	@Override
	public List<User> getAllUsersListByType(String searchData,
			Integer searchUserType) {
		Query query = null;
		if (!searchUserType.equals(-1)) {
			query = sessionFactory.getCurrentSession().createQuery(
					"from User where userType=:userType and status=:status and (firstName  like '"
							+ searchData + "%'  or lastName like '"
							+ searchData + "%')");
			query.setParameter("userType", searchUserType);
		} else {
			query = sessionFactory.getCurrentSession().createQuery(
					"from User where status=:status and (firstName  like '"
							+ searchData + "%'  or lastName like '"
							+ searchData + "%')");
		}
		query.setParameter("status", 1);
		
		List<User> lst = query.list();
		if (lst != null && !lst.isEmpty())
		{
			return lst;
		}
		return null;
	}

	@Override
	public User getUserDetailsByTokenId(String decrypToken)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where resettoken=:resettoken");
		query.setParameter("resettoken", decrypToken);
		List<User> lst = query.list();
		if (lst != null && !lst.isEmpty())
		{
			return lst.get(0);
		}
		return null;
	}

	@Override
	public void saveLog(UserLogDetails uld)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(uld);

	}

	@Override
	public Boolean isEmailExist(String emailId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where emailId=:emailId");
		query.setParameter("emailId", emailId);
		List<User> lst = query.list();
		if (lst != null && !lst.isEmpty())
		{
			return true;
		}
		return false;
	}

	@Override
	public void saveParentChildMapping(ParentChildMapping pcm)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(pcm);
	}


	@Override
	public List<User> getAllUserByRoleId(Integer roleId) {
		Query query=sessionFactory.getCurrentSession().createQuery("select u from User u INNER JOIN FETCH u.school where u.userType=:userType ORDER by u.id DESC");
		query.setInteger("userType", roleId);
		List<User> userList=query.list();
		if(userList!=null && userList.size()>0){
			return userList;
		}
		return null;
	}

	@Override
	public List<User> getAllUserBySchoolId(Long sId) {

		Query query=sessionFactory.getCurrentSession().createQuery("from User u where u.schoolId = :sId");
		query.setLong("sId", sId);
		List<User> userList=query.list();
		if(userList!=null && userList.size()>0)
		{
			return userList;
		}
		
		return null;
	}

	@Override
	public User getUserByEmailId(String emailId) {
		Session session = sessionFactory.getCurrentSession();
		//Query query = session.createQuery("FROM User u left join fetch u.school left join fetch u.grade WHERE u.userName = :emailId");
		Query query = session.createQuery("FROM User u left join fetch u.school left join fetch u.grade WHERE u.userName = :emailId  or u.emailId= :emailId ");
		query.setParameter("emailId", emailId);
		//query.setParameter("emailId", emailId);
		return (User) query.uniqueResult();
	}

	@Override
	public Boolean isUserExistByUserNameOrAdmissionNo(String userName,
			String admissionNo,Long schoolId) {
		try{
		Session session = sessionFactory.getCurrentSession();
		Query query=null;
		if(admissionNo!=null && schoolId!=null)
		{
			
			query = session.createQuery("select id FROM User WHERE userName = :userName or (admissionNo=:admissionNo and schoolId=:schoolId )");
			query.setParameter("userName", userName);
			query.setParameter("admissionNo", admissionNo);
			query.setLong("schoolId", schoolId);
			
		}
		else
		{
			
			query = session.createQuery("select id FROM User WHERE userName = :userName ");
			query.setParameter("userName", userName);
			
			}
		List<Long> ids=query.list();
		if(ids!=null && ids.size()>0){
			return true;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Section getSection(Long schoolId, String gName, String sectionName) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("FROM Section s INNER JOIN fetch s.sessionSchoolGrade ssg inner join fetch ssg.grade g where g.name=:gName and s.name=:sName and ssg.schoolId=:schoolId");
		query.setString("gName", gName);
		query.setString("sName", sectionName);
		query.setLong("schoolId", schoolId);
		query.setMaxResults(1);
		return (Section) query.uniqueResult();
	
	}

	@Override
	public void saveStudentTeacherSession(StudentTeacherSession sts) {

		
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(sts);
	}

	@Override
	public List<SubjectSection> getUserSubjects(Long sessionId, Long userId,
			Integer userType, Long sectionId) {
		Session session=sessionFactory.getCurrentSession();
		String str =  "FROM SubjectSection ss INNER JOIN FETCH ss.subject s "
				    + "WHERE ss.sectionId = :sectionId AND ss.status = :status and "
				    + "ss.subjectId not in(SELECT subjectId FROM StudentTeacherSession "
				    + "WHERE userId = :userId AND userRole = :userType AND status = :status AND sessionId = :sessionId  AND subjectId is not null) ";
		
	/*	String str1 ="SELECT ss.f_subjectId  t_subject_section ss ON s.f_id = ss.f_sectionId AND ss.f_status= "+SubjectSection.STATUS_ACTIVE+" "
				+ "WHERE sts.f_userid = "+userId+" AND sts.f_userRole = "+userType+" AND sts.f_status = 1 AND sts.f_sessionId ="+sessionId+" AND  "
				+ "sts.f_subjectId IS NULL AND "
				+ "ss.f_subjectId NOT IN(SELECT f_subjectid FROM t_studentTeacherSession WHERE f_userId = "+userId+" AND f_userRole = "+userType+" AND f_subjectId IS NOT NULL AND sts.f_sessionId ="+sessionId+")"; 
*/
		
		Query query = session.createQuery(str);
		query.setParameter("userId", userId);
		query.setParameter("userType", userType);
		query.setParameter("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setParameter("sessionId", sessionId);
		query.setParameter("sectionId", sectionId);
		
		return  query.list();
		
	}

	@Override
	public List<User> getAllUserByAdminsionNo(List<String> admisionNoList,
			Long schoolId) {
		Session session=sessionFactory.getCurrentSession();
		String str = "FROM User where schoolId = :schoolId and admissionNo in(:admisionList) ";
		Query query = session.createQuery(str);
		query.setParameter("schoolId", schoolId);
		query.setParameterList("admisionList", admisionNoList);
		return query.list();
	}

	@Override
	public List<User> getAllParentList(Long schoolId) {
		Session session=sessionFactory.getCurrentSession();
		String str="FROM User where schoolId=:schoolId and userType=:userType";
		
		Query query=session.createQuery(str);
		query.setParameter("schoolId", schoolId);
		query.setParameter("userType", User.USERTYPE_PARENT);
		return query.list();
	}

	@Override
	public List<SubjectSection> getRetailUserSubjects(Long userId, Integer userType)
	{
		String sql="select distinct(ss) from SubjectSection ss inner join fetch ss.subject s where ss.status=:status and ss.sectionId=(select sectionId from StudentTeacherSession where status=:sstatus and userId=:userId and userRole=:userRole and schoolId is null)";
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("status", SubjectSection.STATUS_ACTIVE);
		query.setParameter("sstatus", StudentTeacherSession.STATUS_ACTIVE);
		query.setParameter("userId", userId);
		query.setParameter("userRole", userType);
		List<SubjectSection> subjectSectionlst=query.list();
		return subjectSectionlst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getSearchedStudents(String studentName)
	{
		ArrayList<Integer> userTypeList = new ArrayList<Integer>();
		userTypeList.add(User.USERTYPE_DEMO);
		userTypeList.add(User.USERTYPE_USER);
		userTypeList.add(User.USERTYPE_STUDENT);
		
		Session session=sessionFactory.getCurrentSession();
		String str="FROM User u INNER JOIN FETCH u.grade g LEFT JOIN FETCH u.school s where u.userType IN (:uType) AND u.status = :status AND (u.firstName LIKE :fName OR u.userName LIKE :uName)";
		
		Query query=session.createQuery(str);
		query.setParameterList("uType", userTypeList);
		query.setInteger("status", User.STATUS_ACTIVE);
		query.setParameter("fName", "%"+studentName+"%");
		query.setParameter("uName", "%"+studentName+"%");
		
		return query.list();
		
	}

	@Override
	public Boolean updateLog(String sessionId)
	{
		//String str = "UPDATE UserLogDetails set logoutTime ="+new Date()+"where sessionId=:sessionId";
		String str1="UPDATE UserLogDetails set logoutTime = :logoutTime "  + 
	             "WHERE sessionId = :sessionId";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(str1);
		query.setParameter("sessionId", sessionId);
		query.setParameter("logoutTime", new Date());
		
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		if(result>=1)
		return true;
		else
			return false;
	}

	@Override
	public Integer getCommentCount()
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select Count(id) from QuestionComments where status=:status and isRead=:notReadable");
		query.setParameter("status",QuestionComments.STATUS_ACTIVE);
		query.setParameter("notReadable", false);
		Object count=query.uniqueResult();
		if(count!=null){
			return Integer.valueOf(count.toString());
		}
		return 0;
	}
	@Override
	public void teacherMarkTopicDetails(TeacherMarkTopicDetails tmd){
		
		Session session=sessionFactory.getCurrentSession();
		String str="select id FROM TeacherMarkTopicDetails where topicId=:topicId and chapterId=:chapterId and userId=:userId and subjectId=:subjectId and sectionId=:sectionId" ;
		Query query=session.createQuery(str);
		query.setParameter("topicId", tmd.getTopicId());
		query.setParameter("chapterId", tmd.getChapterId());
		query.setParameter("userId",tmd.getUserId());
		query.setParameter("subjectId",tmd.getSubjectId());
		query.setParameter("sectionId",tmd.getSectionId());
		query.setMaxResults(1);
	
		Object size =query.uniqueResult();
		if(size==null){
			sessionFactory.getCurrentSession().saveOrUpdate(tmd);
		}
		
		
	
		
	}
	@Override
	public void teacherMarkTopicDetailsUnCheck(TeacherMarkTopicDetails tmd){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete FROM TeacherMarkTopicDetails where topicId=:topicId and chapterId=:chapterId and userId=:userId and subjectId=:subjectId and sectionId=:sectionId");
		query.setParameter("topicId", tmd.getTopicId());
		query.setParameter("chapterId", tmd.getChapterId());
		query.setParameter("userId",tmd.getUserId());
		query.setParameter("subjectId",tmd.getSubjectId());
		query.setParameter("sectionId",tmd.getSectionId());
		int result = query.executeUpdate();
		
	}
	@Override
	public boolean deleteModuleOrDeactivateStatus(Long topicId,Long moduleId){
		String str1="UPDATE ModuleTopic set modifiedDate = :modifiedDate , status = :status "  + 
	             "WHERE topicId = :topicId and moduleId = :moduleId";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(str1);
		query.setParameter("moduleId", moduleId);
		query.setParameter("topicId",topicId);
		query.setParameter("modifiedDate", new Date());
		query.setParameter("status",ModuleTopic.STATUS_INACTIVE);
		
		
		int result = query.executeUpdate();
	System.out.println(topicId+" Topic  : Module  "+moduleId+"  Rows affected: " + result);
		if(result>=1)
		return true;
		else
			return false;
		
		
	}

	@Override
	public void teacherMarkTopicContentPermissionDetails(TeacherContentPermission tmd)
	{
		
			
			sessionFactory.getCurrentSession().saveOrUpdate(tmd);
		
		
		
	}

	@Override
	public void teacherMarkTopicContentPermissionDetailsUnCheck(TeacherContentPermission tmd)
	{
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete FROM TeacherContentPermission where topicId=:topicId and chapterId=:chapterId and userId=:userId and subjectId=:subjectId and sectionId=:sectionId");
		query.setParameter("topicId", tmd.getTopicId());
		query.setParameter("chapterId", tmd.getChapterId());
		query.setParameter("userId",tmd.getUserId());
		query.setParameter("subjectId",tmd.getSubjectId());
		query.setParameter("sectionId",tmd.getSectionId());
		int result = query.executeUpdate();
	}

	
	
	
	
}
