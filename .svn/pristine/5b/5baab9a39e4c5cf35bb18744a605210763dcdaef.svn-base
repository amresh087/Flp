package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.SchoolAdminDao;
import com.flp.model.Grade;
import com.flp.model.ParentChildMapping;
import com.flp.model.Question;
import com.flp.model.SchoolSession;
import com.flp.model.Section;
import com.flp.model.SessionSchoolGrade;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.User;
import com.flp.service.UserService;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class SchoolAdminDaoImpl implements SchoolAdminDao
{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserService userService;

	@Override
	public List<Grade> getAllGradeBySchoolId(Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Grade where schoolId=:schoolId and status=:status");
		query.setLong("schoolId", schoolId);
		query.setInteger("status", Grade.STATUS_ACTIVE);
		List<Grade> gradeList = query.list();
		return gradeList;

	}

	@Override
	public Long getCurrentSchoolSession(Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select sessionId from SchoolSession where schoolId=:schoolId and status=:status");
		query.setLong("schoolId", schoolId);
		query.setInteger("status", SchoolSession.STATUS_ACTIVE);
		Long sessionId = (long) query.uniqueResult();
		return sessionId;

	}

	@Override
	public List<Section> getSectionByGradeId(Long schoolId, Long sessionId, Long gradeId)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("from Section s inner join fetch s.sessionSchoolGrade ssg where ssg.schoolId=:schoolId and ssg.gradeId=:gradeId and ssg.sessionId=:sessionId and s.gradeId is null and s.status=:status");
		query.setLong("schoolId", schoolId);
		query.setLong("gradeId", gradeId);
		query.setLong("sessionId", sessionId);
		query.setInteger("status", SchoolSession.STATUS_ACTIVE);
		List<Section> sectionList = query.list();
		return sectionList;

	}

	@Override
	public List<StudentTeacherSession> getAllStudentBySectionId(Long sectionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from StudentTeacherSession sts inner join fetch sts.user u  where sts.sectionId=:sectionId and sts.status=:status and sts.userRole=:userType GROUP BY sts.userId");
		query.setLong("sectionId", sectionId);
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setInteger("userType", User.USERTYPE_STUDENT);
		List<StudentTeacherSession> lst = query.list();
		return lst;
	}

	@Override
	public User checkUser(String uEmail, String userName)
	{

		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where emailId=:uEmail or userName=:userName");
		query.setString("uEmail", uEmail.trim());
		query.setString("userName", userName.trim());
		User user = (User) query.uniqueResult();

		return user;
	}

	@Override
	public ParentChildMapping checkParentChildMapping(long parentId, long userId)
	{
		ParentChildMapping pcm = null;
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ParentChildMapping where userId=:userId and parentId=:parentId");
		query.setLong("userId", userId);
		query.setLong("parentId", parentId);
		query.setMaxResults(1);
		// pcm=(ParentChildMapping)query.uniqueResult();
		return (ParentChildMapping) query.uniqueResult();

	}

	@Override
	public List<ParentChildMapping> getParentsDetailsByUserId(Long userId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from ParentChildMapping pcm inner join fetch pcm.user u where pcm.userId=:userId and u.status=:status");
		query.setLong("userId", userId);
		query.setInteger("status", User.STATUS_ACTIVE);
		List<ParentChildMapping> parentList = query.list();
		return parentList;
	}

	@Override
	public Boolean saveStudentTeacherSession(StudentTeacherSession sts)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(sts);
		return true;
	}

	@Override
	public List<User> getAllSchoolTeacher(Long schoolId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where schoolId=:schoolId and userType=:userType and status=:status order by firstName");
		query.setLong("schoolId", schoolId);
		query.setLong("userType", User.USERTYPE_TEACHER);
		query.setInteger("status", User.STATUS_ACTIVE);
		List<User> lst = query.list();
		return lst;
	}

	@Override
	public List<Subject> getSubjectBySectionId(Long sectionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select subject from SubjectSection ss where ss.section.id=:sectionId and ss.status=:status");
		query.setLong("sectionId", sectionId);
		query.setInteger("status", SubjectSection.STATUS_ACTIVE);
		List<Subject> subList = query.list();
		return subList;
	}

	@Override
	public Boolean assignSubjectToTeacher(StudentTeacherSession studentTeacherSession)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(studentTeacherSession);
		return true;
	}

	@Override
	public Boolean isAlreadyAssignSubject(StudentTeacherSession studentTeacherSession)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from StudentTeacherSession where sectionId=:sectionId and subjectId=:subjectId and userId=:userId and status=:status");
		query.setLong("sectionId", studentTeacherSession.getSectionId());
		query.setLong("subjectId", studentTeacherSession.getSubjectId());
		query.setLong("userId", studentTeacherSession.getUserId());
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		List<Long> lst = query.list();
		if (lst != null && lst.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<SessionSchoolGrade> getAllGradeSectionSubject(Long schoolId, Long sessionId)
	{
		// get SchoolSessionId on basis of school and current session Id and add filter to Section for colum session school grade id
		Session session = sessionFactory.getCurrentSession();
		session.enableFilter("status").setParameter("active", 1);
		Query query = session.createQuery("select distinct (ssg) from SessionSchoolGrade ssg " + "inner join fetch ssg.grade g inner join fetch ssg.sections s " + "left join fetch s.subjectSectionList ss left join fetch ss.subject sub " + "where ssg.schoolId=:schoolId and ssg.status=:status and ssg.sessionId=:sessionId");
		query.setLong("schoolId", schoolId);
		query.setInteger("status", Grade.STATUS_ACTIVE);
		query.setLong("sessionId", sessionId);
		List<SessionSchoolGrade> sessionGradeList = query.list();
		
		return sessionGradeList;
	}

	@Override
	public String editGradeName(Long gradeId, String gradeName)
	{
		Session session = sessionFactory.getCurrentSession();
		Grade grade = (Grade) session.get(Grade.class, gradeId);
		if (grade == null)
			return "error";

		grade.setName(gradeName);
		session.update(grade);

		return "success";
	}

	@Override
	public boolean isSectionExist(Long schoolId, String gradeName)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from com.flp.model.Grade where name=:gradeName and schoolId=:schoolId and status=1");
		query.setLong("schoolId", schoolId);
		query.setParameter("gradeName", gradeName);
		if (query.list().size() > 0)
			return true;
		else
			return false;

	}

	@Override
	public String createSection(long schoolId, long sessionId, String sectionName, long gradeId)
	{
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from SessionSchoolGrade where  gradeId=:gradeId and schoolId=:schoolId and sessionId=:sessionId and status=1");
		query.setLong("gradeId", gradeId);
		query.setLong("schoolId", schoolId);
		query.setLong("sessionId", sessionId);
		List<SessionSchoolGrade> list = query.list();
		if (list.size() > 0)
		{
			query = session.createQuery("from Section where sessionSchoolGradeId=:sesionSchoolGradeId and name=:sectionName and status=1");
			query.setLong("sesionSchoolGradeId", list.get(0).getId());
			query.setString("sectionName", sectionName.trim());
			if (query.list().size() == 0)
			{
				Date d = new Date();
				Section sec = new Section();
				//sec.setGradeId(gradeId);
				sec.setSessionSchoolGrade(list.get(0));
				sec.setInsertDate(d);
				sec.setModifiedDate(d);
				sec.setName(sectionName);
				sec.setSessionSchoolGradeId(list.get(0).getId());
				sec.setStatus(1);
				session.save(sec);
				return "success";
			}
			else
				return "duplicate";

		}
		else
			return "error";
	}

	@Override
	public List<StudentTeacherSession> getAssignSubjectTeacherList(Long schoolId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from StudentTeacherSession sts" + " inner join fetch sts.user" + " inner join fetch sts.subject " + "inner join fetch sts.section s " + "inner join fetch s.sessionSchoolGrade ssg " + "inner join fetch ssg.grade  " + "where sts.schoolId=:schoolId and sts.sessionId=:sessionId and sts.status=:status and sts.userRole=:type");
		query.setLong("schoolId", schoolId);
		query.setLong("sessionId", sessionId);
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setInteger("type", User.USERTYPE_TEACHER);
		List<StudentTeacherSession> lst = query.list();
		return lst;
	}

	@Override
	public void removeSubjectFromTeacher(Long studentTeacherSubjectId)
	{

		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE StudentTeacherSession SET status=:status where id=:studentTeacherSubjectId");
		query.setInteger("status", StudentTeacherSession.STATUS_INACTIVE);
		query.setLong("studentTeacherSubjectId", studentTeacherSubjectId);
		query.executeUpdate();

	}

	@Override
	public List<User> getAllSchoolSmeList(Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where schoolId=:schoolId and " + "userType=:userType and status=:status");
		query.setLong("schoolId", schoolId);
		query.setInteger("userType", User.USERTYPE_SCHOOL_SME);
		query.setInteger("status", User.STATUS_ACTIVE);
		List<User> lst = query.list();
		return lst;

	}

	@Override
	public void inactivateUser(Long userId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE User SET status=:status where id=:userId");
		query.setInteger("status", User.STATUS_INACTIVE);
		query.setLong("userId", userId);
		query.executeUpdate();

	}

	@Override
	public void removeMapping(Long subjectSectionId)
	{
		Session session = sessionFactory.getCurrentSession();
		SubjectSection ss = (SubjectSection) session.get(SubjectSection.class, subjectSectionId);
		ss.setStatus(SubjectSection.STATUS_INACTIVE);
		ss.setModifiedDate(new Date());
		session.update(ss);

	}

	@Override
	public Boolean isSubjectAlreadyUnassigned(StudentTeacherSession sts)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from StudentTeacherSession where user=:userId and sectionId=:sectionId and subjectId=:subjectId and status=:status");
		query.setLong("userId", sts.getUserId());
		query.setLong("sectionId", sts.getSectionId());
		query.setLong("subjectId", sts.getSubjectId());
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		List<Long> ids = query.list();
		if (ids != null && ids.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Long> getStudentUnassignedSubjectList(Long userId, Long sectionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select subjectId from StudentTeacherSession where userId=:userId and sectionId=:sectionId and status=:status and userRole=:type and subjectId is not null");
		query.setLong("userId", userId);
		query.setLong("sectionId", sectionId);
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setInteger("type", User.USERTYPE_STUDENT);
		List<Long> lst = query.list();
		return lst;
	}

	@Override
	public void inactivateUserFromCurrentSession(Long userId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE StudentTeacherSession SET status=:status where userId=:userId and sessionId=:sessionId");
		query.setInteger("status", StudentTeacherSession.STATUS_INACTIVE);
		query.setLong("userId", userId);
		query.setLong("sessionId", sessionId);
		query.executeUpdate();

	}

	@Override
	public List<StudentTeacherSession> getUnassignSubjectListByUserId(Long userId, Long sessionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from StudentTeacherSession sts inner join fetch sts.subject s where " + "sts.userId=:userId and sts.sessionId=:sessionId and sts.status=:status and sts.userRole=:type and sts.subjectId is not null");
		query.setLong("userId", userId);
		query.setLong("sessionId", sessionId);
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setInteger("type", User.USERTYPE_STUDENT);
		List<StudentTeacherSession> lst = query.list();
		return lst;
	}

	@Override
	public void removeUnassignedSubject(Long studentId, Long[] subjectIds, Long sessionId, Long schoolId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("delete from StudentTeacherSession where userId=:userId and sessionId=:sessionId and schoolId=:schoolId and status=:status and subjectId in (:subjectIds)");
		query.setLong("userId", studentId);
		query.setLong("sessionId", sessionId);
		query.setLong("schoolId", schoolId);
		query.setInteger("status", StudentTeacherSession.STATUS_ACTIVE);
		query.setParameterList("subjectIds", subjectIds);
		query.executeUpdate();
	}

	@Override
	public Long getGradeIdByectionId(Long sectionId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Section s inner join fetch s.sessionSchoolGrade where s.id=:sectionId");
		query.setLong("sectionId", sectionId);
		List<Section> lst = query.list();
		return (Long) lst.get(0).getSessionSchoolGrade().getGradeId();
	}

	@Override
	public Long getUserSectionId(Long userId)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select sectionId from StudentTeacherSession where userId=:userId");
		query.setLong("userId", userId);
		List<Long> lst = query.list();
		return (Long) lst.get(0);
	}

	@Override
	public List<Subject> getAllSubjectsBySectionIds(List<Long> sectionIds)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from SubjectSection ss inner join fetch ss.subject where ss.sectionId in (:sectionList) and ss.status=:status group by ss.subjectId");
		query.setParameterList("sectionList", sectionIds);
		query.setInteger("status", SubjectSection.STATUS_ACTIVE);
		List<SubjectSection> lst = query.list();
		List<Subject> subList = new ArrayList<Subject>();
		if (lst != null && lst.size() > 0)
		{
			for (SubjectSection ss : lst)
			{
				subList.add(ss.getSubject());
			}
		}
		return subList;
	}

	@Override
	public Boolean isAlreadyAssigned(Long subjectId, Long sectionId, Integer userType)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("select id from StudentTeacherSession where sectionId=:sectionId and subjectId=:subjectId and userRole=:userRole");
		query.setLong("sectionId", sectionId);
		query.setLong("subjectId", subjectId);
		query.setInteger("userRole", userType);
		query.setMaxResults(1);
		Object id = query.uniqueResult();
		if (id != null && Long.parseLong(id.toString()) > 0l)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Subject> getAllSubjectsBySchoolId(Long schoolId) {
		Query query=sessionFactory.getCurrentSession().createQuery("from Subject where schoolId=:schoolId order by name");
		query.setLong("schoolId", schoolId);
		return query.list();
		
	
		
		
	}

	@Override
	public boolean isSubjectExist(String sName,Long schoolId) {
		
		//byte[] b = sName.getBytes(Charset.forName("UTF-8"));
		String uName=sName.toUpperCase();
		//String str="SELECT `f_id`, UPPER(CONVERT(`f_name` USING latin1)) FROM `t_subject` WHERE `f_schoolId`=2 AND UPPER(CONVERT(`f_name` USING latin1))='HINDI'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(" select count(1) FROM t_subject WHERE UPPER(CONVERT(`f_name` USING latin1))=:subjectName AND f_schoolId=:schoolId");
		query.setParameter("subjectName", Utility.convertStringToByteUTF8(uName));
		query.setParameter("schoolId", schoolId);
		
		if (Integer.parseInt(query.list().get(0).toString())> 0)
			return true;
		else
			return false;

	}
	@Override
	public List<Section> getAllSectionBySubjectId(Long subjectId,List<Long> sectionIds) {
		
		String str = "from SubjectSection ss inner join fetch ss.subject where ss.subjectId=:subjectId  and ss.status=:status ";
		if(sectionIds != null && sectionIds.size() > 0){
			str += " and ss.sectionId in (:sectionList)";
		}
		
		Query query=sessionFactory.getCurrentSession().createQuery(str);
			
	
		query.setParameter("subjectId", subjectId);
		query.setInteger("status", SubjectSection.STATUS_ACTIVE);
		if(sectionIds != null && sectionIds.size() > 0){
			query.setParameterList("sectionList", sectionIds);
		}
		List<SubjectSection> lst=query.list();
		List<Section> subList=null;
		Section setion;
		if(lst!=null && lst.size()>0){
			subList=new ArrayList<Section>();
		for(SubjectSection ss:lst){
			setion=new Section();
			setion.setId(ss.getSectionId());
			subList.add(setion);
		}
		}
		return subList;
		
	}


	public List<SessionSchoolGrade> gffgf(Long gradeId) {
		Query query=sessionFactory.getCurrentSession().createQuery("select distinct(ss) from SubjectSection ss inner join fetch ss.subject inner join fetch ss.section s inner join fetch s.sessionSchoolGrade ssg where ssg.gradeId=:gradeId and ssg.status=:status and ss.status=:status");
		query.setParameter("gradeId", gradeId);
		query.setParameter("stauts", SubjectSection.STATUS_ACTIVE);
		List<SessionSchoolGrade> lst = query.list();
		return lst;
	}

	@Override
	public void updateMapping()
	{
		Query query=sessionFactory.getCurrentSession().createQuery("from Question");
		List<Question> lst=query.list();
		for(Question q:lst){
			Query query1=sessionFactory.getCurrentSession().createQuery("update QuestionTopic set cognitiveLevelId=:cognitiveLevelId, difficultyLevelId=:difficultyLevelId where questionId=:questionId");
			query1.setParameter("cognitiveLevelId", q.getCognitiveLevelId());
			query1.setParameter("difficultyLevelId", q.getDifficultyLevelId());
			query1.setParameter("questionId", q.getId());
			query1.executeUpdate();
			
		}
		
	}
	
}
