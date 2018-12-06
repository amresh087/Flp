package com.flp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flp.annotations.LoginAuthentication;
import com.flp.annotations.StudentAuthentication;
import com.flp.model.Assessment;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.DemoGradeTopic;
import com.flp.model.FavoriteTopics;
import com.flp.model.Grade;
import com.flp.model.LearnObjective;
import com.flp.model.ParentChildMapping;
import com.flp.model.QuestionComments;
import com.flp.model.QuestionTopic;
import com.flp.model.Section;
import com.flp.model.SmeGradeSubject;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.TeacherContentPermission;
import com.flp.model.TeacherMarkTopicDetails;

import com.flp.model.Topic;
import com.flp.model.TopicGraph;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.service.AdminService;
import com.flp.service.RetailUserService;
import com.flp.service.SchoolAdminService;
import com.flp.service.SchoolSmeService;
import com.flp.service.SchoolTeacherService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
import com.flp.util.MailSenderUtil;
import com.flp.util.Utility;

/** @author Amresh */
@org.springframework.web.bind.annotation.RestController
@PropertySource("classpath:application.properties")
public class RestController
{

	@Resource
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	private StudentCommonService studentCommonService;

	@Autowired
	private MailSenderUtil mailSenderUtil;

	@Autowired
	private AdminService adminService;

	@Autowired
	private SchoolAdminService schoolAdminService;

	@Autowired
	private SchoolTeacherService schoolTeacherService;

	@Autowired
	private SchoolSmeService schoolSmeService;

	@Autowired
	private RetailUserService retailUserService;

	/** @param searchUserType
	 * @param searchData
	 * @return */
	@RequestMapping(value = "/ajax/rest/searchUser/{searchUserType}/{searchData}", method = RequestMethod.POST)
	public List<User> searchUser(@PathVariable("searchUserType") Integer searchUserType, @PathVariable("searchData") String searchData)
	{
		List<User> userList = userService.getAllUsersListByType(searchData, searchUserType);
		return userList;

	}

	/** @param boardId
	 * @return */
	@RequestMapping(value = "/getGradeByBoardId/{boardId}", method = RequestMethod.POST)
	public List<Grade> getGradeByBoardId(@PathVariable("boardId") Long boardId)
	{
		List<Grade> gradeList = studentCommonService.getGradeByBoardId(boardId);
		/* if (!gradeList.isEmpty()) { for (Grade g : gradeList) { g.setSectionlist(null); } } */
		return gradeList;

	}

	/** @param boardId
	 * @param gradeId
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSubjectByBoardAndGrade/{boardId}/{gradeId}", method = RequestMethod.POST)
	@StudentAuthentication
	public List<Subject> getGradeByBoardId(@PathVariable("boardId") Long boardId, @PathVariable("gradeId") Long gradeId)
	{
		List<Subject> subjectList = studentCommonService.getSubjectsByGradeAndBoard(boardId, gradeId);
		return subjectList;

	}

	/** @param boardId
	 * @param gradeId
	 * @param subjectId
	 * @return */
	@RequestMapping(value = "/ajax/rest/getChapterByBoardGradeAndSubject/{boardId}/{gradeId}/{subjectId}", method = RequestMethod.POST)
	public List<Chapter> getChapterByBoardGradeAndSubject(@PathVariable("boardId") Long boardId, @PathVariable("gradeId") Long gradeId, @PathVariable("subjectId") Long subjectId)
	{
		/* List<Chapter> chapterList = studentCommonService.getChapterByBoardGradeAndSubject(boardId, gradeId, subjectId); List<Chapter> cList = new ArrayList<Chapter>();
		 * 
		 * for (Chapter ch : chapterList) { Chapter c = ch; c.setSubject(null); c.setTopics(null);
		 * 
		 * cList.add(c); }
		 * 
		 * return cList; */
		return null;

	}

	/** @param parentEmailId
	 * @param session
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/ajax/rest/prepareChildParentLinking/{parentEmailId:.+}", method = RequestMethod.POST)
	public List<String> prepareChildParentLinking(@PathVariable("parentEmailId") String parentEmailId, HttpSession session) throws Exception
	{
		List<String> lst = new ArrayList<String>();
		UserForm loginUserObj = (UserForm) session.getAttribute("user");
		User loginUser = userService.getUserById(loginUserObj.getId());
		if (null == loginUser.getParentId())
		{
			User obj = userService.getUserByEmailId(parentEmailId);
			String type = null;
			String token = null;
			if (obj != null && obj.getUserType().equals(User.USERTYPE_PARENT))
			{
				token = obj.getId() + "&#" + loginUserObj.getId() + "&#" + Calendar.getInstance().getTimeInMillis();
				type = "alreadyAccount";
				obj.setStudentName(loginUserObj.getName());

			}
			else if (obj != null && !obj.getUserType().equals(User.USERTYPE_PARENT))
			{
				lst.add("This email id is already assign to Student");
				return lst;
			}
			else
			{
				type = "newAccount";
				obj = loginUser;
				token = loginUserObj.getId() + "&#" + Calendar.getInstance().getTimeInMillis();

			}
			obj.setResettoken(token);
			userService.SaveOrUpdate(obj);
			obj.setEncryptedToken(Utility.encrypt(token));
			mailSenderUtil.sendParentUserLinkingMail(obj, type, parentEmailId);
			lst.add("Email sent to your parent mail id for acceptance");
		}
		else
		{
			lst.add("You have already linked with parent");
		}
		return null;
	}

	/** @param emailId
	 * @param session
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/ajax/rest/forgotPassword/{emailId:.+}", method = RequestMethod.POST)
	public String forgotPassword(@PathVariable("emailId") String emailId, HttpSession session) throws Exception
	{
		User userObj = userService.getUserByEmailId(emailId);
		if (userObj != null)
		{
			String token = userObj.getId() + "-" + Calendar.getInstance().getTimeInMillis();
			userObj.setResettoken(token);
			userService.SaveOrUpdate(userObj);
			userObj.setEncryptedToken(Utility.encrypt(token));
			String url = env.getRequiredProperty("appUrl") + "/resetPasswordLink.htm";
			userObj.setUrl(url);
			mailSenderUtil.sendForgotPasswordEmail(userObj);
			return "1";
		}
		else
		{
			return "2";
		}
	}

	/** @param pwd
	 * @param confirmPwd
	 * @param userId
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/ajax/rest/resetPassword/{pwd:.+}/{confirmPwd:.+}/{userId}", method = RequestMethod.POST)
	public Boolean resetPassword(@PathVariable("pwd") String pwd, @PathVariable("confirmPwd") String confirmPwd, @PathVariable("userId") Long userId) throws Exception
	{
		if (pwd.trim().equals(confirmPwd.trim()) && userId > 0l)
		{
			User userObj = userService.getUserById(userId);
			userObj.setResettoken("");
			userObj.setPassword(pwd);
			userService.SaveOrUpdate(userObj);
			return true;
		}

		return false;
	}

	/** @param boardId
	 * @param gradeId
	 * @return */
	@RequestMapping(value = "/mapGradeBoard")
	public String mapBoardGrade(@RequestParam(value = "boardId") Long boardId, @RequestParam(value = "gradeId") Long gradeId)
	{

		String msg = "";
		/* BoardGrade brdgrde = studentCommonService.getMapExitForBoardGrade(boardId, gradeId);
		 * 
		 * if (brdgrde != null) { msg = "Mapping Already Exit"; } else { msg = studentCommonService.mapBoardGrade(boardId, gradeId); } */
		return msg;

	}

	/** @param boardId
	 * @param gradeId
	 * @param subjectId
	 * @return */
	@RequestMapping(value = "/mapGradeBoardSubject")
	public String mapBoardGradeSubject(@RequestParam(value = "boardId") Long boardId, @RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId)
	{

		String msg = "";

		return msg;

	}

	/** @param boardId
	 * @param gradeId
	 * @param subjectId
	 * @param chapterName
	 * @return */
	@RequestMapping(value = "/mapGradeBoardSubjectChapter")
	public String mapBoardGradeSubjectChapter(@RequestParam(value = "boardId") Long boardId, @RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId, @RequestParam(value = "chapterName") String chapterName)
	{
		String msg;
		Chapter chp = studentCommonService.getMapExitForBoardGradeSubjectChapter(boardId, gradeId, subjectId, chapterName);

		if (chp != null)
		{
			msg = "Mapping Already Exit";
		}
		else
		{
			msg = studentCommonService.mapBoardGradeSubjectChapter(boardId, gradeId, subjectId, chapterName);
		}
		return msg;
	}

	/** @param boardId
	 * @param gradeId
	 * @param subjectId
	 * @param chapterId
	 * @param topicName
	 * @return */
	@RequestMapping(value = "/mapBrdGrdSubChapTop")
	public String mapBrdGrdSubChpTop(@RequestParam(value = "boardId") Long boardId, @RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId, @RequestParam(value = "chapterId") Long chapterId, @RequestParam(value = "topicName") String topicName)
	{

		String msg;

		Topic topic = studentCommonService.getMapExitForBoardGradeSubjectChapterTopic(boardId, gradeId, subjectId, chapterId, topicName);
		if (topic != null)
		{
			msg = "Mapping Already Exit";
		}
		else
		{
			msg = studentCommonService.mapBoardGradeSubjectChapterTopic(boardId, gradeId, subjectId, chapterId, topicName);
		}

		return msg;
	}

	/** @param boardId
	 * @param gradeId
	 * @param subjectId
	 * @param chapterId
	 * @param topicId
	 * @return */
	@RequestMapping(value = "/mapBrdGrdSubChapTopic")
	public String mapBrdGrdSubChpTopic(@RequestParam(value = "boardId") Long boardId, @RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId, @RequestParam(value = "chapterId") Long chapterId, @RequestParam(value = "topicId") Long topicId)
	{
		String msg;

		DemoGradeTopic DemoGradeTopic = studentCommonService.getMappingForDemo(boardId, gradeId, subjectId, chapterId, topicId);
		if (DemoGradeTopic != null)
		{
			msg = "Mapping Already Exit";
		}
		else
		{
			msg = studentCommonService.mapWithDemo(boardId, gradeId, subjectId, chapterId, topicId);
			// msg=studentCommonService.mapDemo(boardId,gradeId,subjectId,chapterId,topicId);
		}

		return msg;
	}

	/** @param boardId
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSectionsForBoard/{boardId}", method = RequestMethod.POST)
	public List<Section> getSections(@PathVariable("boardId") Long boardId)
	{
		List<Section> sectionList = adminService.getSections(null, boardId, null, Section.STATUS_ACTIVE, null, "asc");

		/* List<Section> list = null; if (sectionList != null && sectionList.size() > 0) { list = new ArrayList<Section>(); for (Section s : sectionList) { s.getGrade().setSectionlist(null); s.setSessionSchoolGrade(null); s.setSubjectSectionList(null); s.setStudentTeacherSession(null); list.add(s); } } return list; */
		return sectionList;
	}

	/** @param sectionId
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSubjects/{sectionId}", method = RequestMethod.POST)
	public List<Subject> getSubjectSections(@PathVariable("sectionId") Long sectionId)
	{
		List<SubjectSection> subjectSectionList = adminService.getSubjectSections(String.valueOf(sectionId), SubjectSection.STATUS_ACTIVE);

		List<Subject> list = null;

		if (subjectSectionList != null && subjectSectionList.size() > 0)
		{
			list = new ArrayList<Subject>();
			for (SubjectSection ss : subjectSectionList)
			{

				// ss.getSubject().setChapters(null);
				list.add(ss.getSubject());
			}
		}
		return list;
	}

	/** @param boardId
	 * @param status
	 * @return */
	@RequestMapping(value = "/ajax/rest/getDistinctSubjects/{boardId}/{status}", method = RequestMethod.POST)
	public List<Subject> getDistinctSubjects(@PathVariable("boardId") Long boardId, @PathVariable("status") Integer status)
	{
		List<Subject> subjectList = adminService.getDistinctSubjects(boardId, status);

		/* List<Subject> list = null;
		 * 
		 * if (subjectList != null && subjectList.size() > 0) { list = new ArrayList<Subject>(); for (Subject s : subjectList) { s.setChapters(null); list.add(s); } } return list; */
		return subjectList;
	}

	/** @param gradeId
	 * @param subjectId
	 * @return */
	@RequestMapping(value = "/ajax/rest/getChapters/{gradeId}/{subjectId}", method = RequestMethod.POST)
	public List<Chapter> getChapters(@PathVariable("gradeId") Long gradeId, @PathVariable("subjectId") Long subjectId)
	{

		List<Chapter> chapterList = adminService.getChapters(String.valueOf(subjectId), gradeId, Chapter.STATUS_ACTIVE);

		/* List<Chapter> list = null;
		 * 
		 * if (chapterList != null && chapterList.size() > 0) { list = new ArrayList<Chapter>(); for (Chapter c : chapterList) { c.setTopics(null); c.setSubject(null); list.add(c); } } return list; */
		return chapterList;
	}

	/** @param topicId
	 * @return */
	@RequestMapping(value = "/ajax/rest/getLo/{topicId}", method = RequestMethod.POST)
	public List<LearnObjective> getLos(@PathVariable("topicId") Long topicId)
	{

		List<LearnObjective> loList = adminService.getLos(String.valueOf(topicId), Topic.STATUS_ACTIVE);

		/* List<Topic> list = null;
		 * 
		 * if (topicList != null && topicList.size() > 0) { list = new ArrayList<Topic>(); for (Topic t : topicList) { t.setChapter(null); t.setQuestionTopic(null); t.setUserQuestionAnswer(null); t.setSubject(null); list.add(t); } } return list; */
		return loList;
	}

	@RequestMapping(value = "/ajax/rest/getTopics/{chapterId}", method = RequestMethod.POST)
	public List<Topic> getTopics(@PathVariable("chapterId") Long chapterId)
	{
		List<Topic> topicList = adminService.getTopics(String.valueOf(chapterId), Topic.STATUS_ACTIVE);

		return topicList;
	}

	/** @param gradeId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSectionByGradeId/{gradeId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Section> getSectionDetails(@PathVariable("gradeId") Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Section> sectionList = schoolAdminService.getSectionByGradeId(loginUser.getSchoolId(), loginUser.getSessionId(), gradeId);
		/* for (Section s : sectionList) { s.setGrade(null); s.setSessionSchoolGrade(null); s.setSubjectSectionList(null); s.setStudentTeacherSession(null); } */
		return sectionList;
	}

	/** @param userId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getParentsDetailsByUserId/{userId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<User> getParentsDetailsByUserId(@PathVariable("userId") Long userId, HttpServletRequest request, HttpServletResponse response)
	{
		List<ParentChildMapping> parentChildList = schoolAdminService.getParentsDetailsByUserId(userId);
		List<User> parentList = new ArrayList<User>();
		if (parentChildList != null && parentChildList.size() > 0)
		{
			for (ParentChildMapping pcl : parentChildList)
			{
				// pcl.getUser().setSchool(null);
				// pcl.getUser().setGrade(null);
				parentList.add(pcl.getUser());
			}
		}
		return parentList;
	}

	/** @param sectionId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSubjectBySectionId/{sectionId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Subject> getSubjectBySectionId(@PathVariable("sectionId") Long sectionId, HttpServletRequest request, HttpServletResponse response)
	{

		List<Subject> subjectList = schoolAdminService.getSubjectBySectionId(sectionId);
		/* if (subjectList != null && subjectList.size() > 0) { for (Subject s : subjectList) { s.setChapters(null); } } */
		return subjectList;
	}

	/** @param userId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getUnassignSubjectListByUserId/{userId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Subject> getUnassignSubjectListByUserId(@PathVariable("userId") Long userId, HttpServletRequest request, HttpServletResponse response)
	{
		List<Subject> subjectList = new ArrayList<Subject>();
		List<StudentTeacherSession> studentTeacherSessionList = schoolAdminService.getUnassignSubjectListByUserId(userId, ((UserForm) request.getSession().getAttribute("user")).getSessionId());
		if (studentTeacherSessionList != null && studentTeacherSessionList.size() > 0)
		{
			for (StudentTeacherSession sts : studentTeacherSessionList)
			{
				// sts.getSubject().setChapters(null);
				subjectList.add(sts.getSubject());
			}
		}
		return subjectList;
	}

	/** @param sectionId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getStudentsBySectionId/{sectionId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<StudentTeacherSession> getStudentsBySectionId(@PathVariable("sectionId") Long sectionId, HttpServletRequest request, HttpServletResponse response)
	{
		List<StudentTeacherSession> stsList = schoolAdminService.getAllStudentBySectionId(sectionId);
		/* if (stsList != null && stsList.size() > 0) { for (StudentTeacherSession sts : stsList) { sts.setSection(null); sts.setSubject(null); sts.getUser().setSchool(null); sts.getUser().setGrade(null); } } */
		return stsList;
	}

	/** @param gradeId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSubjectsByGradeId/{gradeId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Subject> getSubjectsByGradeId(@PathVariable("gradeId") Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Section> sectionList = schoolAdminService.getSectionByGradeId(loginUser.getSchoolId(), loginUser.getSessionId(), gradeId);
		if (sectionList != null && sectionList.size() > 0)
		{
			List<Long> sectionIds = new ArrayList<Long>();
			for (Section s : sectionList)
			{
				sectionIds.add(s.getId());
			}
			List<Subject> subjectList = schoolAdminService.getAllSubjectsBySectionIds(sectionIds);
			/* if (subjectList != null && subjectList.size() > 0) { for (Subject s : subjectList) { s.setChapters(null); } } */
			return subjectList;
		}

		return null;
	}

	/** @param request
	 * @param response
	 * @param gradeId
	 * @param subjectId
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/ajax/rest/getAssignmentReport", method = RequestMethod.GET)
	public ModelAndView getAssignmentReport(HttpServletRequest request, HttpServletResponse response, @RequestParam Long gradeId, @RequestParam Long subjectId)
	{

		ModelAndView mav = new ModelAndView("/schoolTeacher/assignmentReport");
		List<Assessment> assignmentLst = schoolTeacherService.getAssignmentDetail(gradeId, subjectId);

		for (Assessment a : assignmentLst)
		{
			for (AssignedAssessment aa : a.getAssignedAssessment())
			{
				aa.setAssessment(null);

			}
			if (a.getAssessmentTopicChapterDetails() != null)
			{
				String chapterIds = a.getAssessmentTopicChapterDetails().getChapterId();
				String topicIds = a.getAssessmentTopicChapterDetails().getTopicId();
				a.setChapCount(chapterIds.split("\\,").length);
				a.setTopicCount(topicIds.split("\\,").length);
				List<String> chapDetails = schoolTeacherService.getChapterNamesByChapIds(chapterIds);
				a.setChapterIds(StringUtils.join(chapDetails, ',').replaceAll("\\,", "\\, "));
				List<String> topicDetails = schoolTeacherService.getTopicNamesbyIds(topicIds);
				a.setTopicIds(StringUtils.join(topicDetails, ',').replaceAll("\\,", "\\, "));

			}
		}

		mav.addObject("assignmentLst", assignmentLst);
		mav.addObject("currentDate", Utility.getCurrentDate());
		mav.addObject("gId", gradeId);
		mav.addObject("sId", subjectId);

		return mav;
	}

	/** @param request
	 * @param response
	 * @param UnassignUserLst
	 * @param gId
	 * @param sId
	 * @param assmentId
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/ajax/rest/unassignStudentfromAssignment/{UnassignUserLst}/{gId}/{sId}/{assmentId}", method = RequestMethod.GET)
	public Boolean unassignStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable Long UnassignUserLst[], @PathVariable Long gId, @PathVariable Long sId, @PathVariable Long assmentId)
	{

		Boolean statusChange = schoolTeacherService.unassignStudentfromAssignment(gId, sId, assmentId, UnassignUserLst);

		return statusChange;

	}

	/** @param subjectId
	 * @param topicId
	 * @param secId
	 * @param request
	 * @param response
	 * @return */

	@RequestMapping(value = "/ajax/rest/getAssignUnassignModuleDetails/{subjectId}/{topicId}/{secId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<AssignTopicResources> getAssignUnassignModuleDetails(@PathVariable Long subjectId, @PathVariable Long topicId, @PathVariable Long secId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		/* List<Module> moduleLst = schoolTeacherService.getModulesByTopic(topicId); if (moduleLst != null && moduleLst.size() > 0) { for (Module m : moduleLst) { Integer assignedStudentCount = schoolTeacherService.assignedStudentCountByModuleId(m.getId(), loginUser.getId()); m.setCount(assignedStudentCount); // m.setResourceType(null); // m.setGradeType(null); // m.setAnimationType(null);
		 * 
		 * } } */
		Long studentCount = schoolTeacherService.getStudentCountBySectionId(subjectId, secId);
		List<AssignTopicResources> assignTopicResourceLst = schoolTeacherService.getAllAssignModuleListByUser(topicId, loginUser.getId(), subjectId, secId);
		if (!assignTopicResourceLst.isEmpty())
		{
			for (AssignTopicResources atr : assignTopicResourceLst)
			{
				atr.getModule().setCount(Integer.parseInt(studentCount.toString()));
			}
		}
		return assignTopicResourceLst;
	}

	/** @param sectionId
	 * @param topicId
	 * @param gradeId
	 * @param subjectId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/assignTopicToSection/{sectionId}/{topicId}/{gradeId}/{subjectId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Integer assignTopicToSection(@PathVariable Long sectionId, @PathVariable Long topicId, @PathVariable Long gradeId, @PathVariable Long subjectId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		Integer assignedTopicCount = 0;
		AssignTopicResources atr = new AssignTopicResources();
		atr.setAssignedOn(new Date());
		atr.setSectionId(sectionId);
		atr.setStatus(AssignTopicResources.STATUS_ACTIVE);
		atr.setTopicId(topicId);
		atr.setType(AssignTopicResources.ASSIGN_TYPE_TOPIC);
		atr.setGradeId(gradeId);
		atr.setSubjectId(subjectId);
		atr.setAssignBy(userForm.getId());
		atr.setSchoolId(userForm.getSchoolId());
		atr.setSessionId(userForm.getSessionId());

		Boolean isAlreadyAssignedTopic = schoolTeacherService.isAlreadyAssignedTopic(atr);
		if (!isAlreadyAssignedTopic)
		{

			schoolTeacherService.saveTopicResources(atr);
			// assignedTopicCount = schoolTeacherService.assignedTopicCountByTopic(topicId, userForm.getId());
			assignedTopicCount = 1;
		}
		return assignedTopicCount;
	}

	/** @param sectionId
	 * @param topicId
	 * @param moduleId
	 * @param gradeId
	 * @param subjectId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/assignModuleToSection/{sectionId}/{topicId}/{moduleId}/{gradeId}/{subjectId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Integer assignModuleToSection(@PathVariable Long sectionId, @PathVariable Long topicId, @PathVariable Long moduleId, @PathVariable Long gradeId, @PathVariable Long subjectId, HttpServletRequest request, HttpServletResponse response)
	{
		Integer assignedTopicCount = 0;
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");

		AssignTopicResources atr = new AssignTopicResources();
		atr.setAssignedOn(new Date());
		atr.setSectionId(sectionId);
		atr.setStatus(AssignTopicResources.STATUS_ACTIVE);
		atr.setTopicId(topicId);
		atr.setType(AssignTopicResources.ASSIGN_TYPE_RESOURCES);
		atr.setResourceId(moduleId);
		atr.setGradeId(gradeId);
		atr.setSubjectId(subjectId);
		atr.setAssignBy(userForm.getId());
		atr.setSchoolId(userForm.getSchoolId());
		atr.setSessionId(userForm.getSessionId());
		Boolean isAlreadyAssignedModule = schoolTeacherService.isAlreadyAssignedModule(atr);
		if (!isAlreadyAssignedModule)
		{
			schoolTeacherService.saveTopicResources(atr);
			// assignedTopicCount = schoolTeacherService.assignedStudentCountByModuleId(moduleId, userForm.getId());
			assignedTopicCount = 1;
		}
		return assignedTopicCount;
	}

	/** @param topicId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSectionsByTopicId/{topicId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Section> getSectionsByTopicId(@PathVariable Long topicId, HttpServletRequest request, HttpServletResponse response)
	{
		List<AssignTopicResources> atrList = schoolTeacherService.getSectionsByTopicId(topicId, AssignTopicResources.ASSIGN_TYPE_TOPIC);
		List<Section> sectionList = new ArrayList<Section>();
		if (atrList != null && atrList.size() > 0)
		{
			for (AssignTopicResources atr : atrList)
			{
				// atr.getSection().setSessionSchoolGrade(null);
				// atr.getSection().setStudentTeacherSession(null);
				// atr.getSection().setSubjectSectionList(null);
				// atr.getSection().setGrade(null);
				sectionList.add(atr.getSection());
			}
		}
		return sectionList;

	}

	/** @param topicId
	 * @param moduleId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSectionsByModuleId/{topicId}/{moduleId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Section> getSectionsByModuleId(@PathVariable Long topicId, @PathVariable Long moduleId, HttpServletRequest request, HttpServletResponse response)
	{
		List<AssignTopicResources> atrList = schoolTeacherService.getSectionsByModuleId(topicId, moduleId, AssignTopicResources.ASSIGN_TYPE_RESOURCES);
		List<Section> sectionList = new ArrayList<Section>();
		if (atrList != null && atrList.size() > 0)
		{
			for (AssignTopicResources atr : atrList)
			{
				// atr.getSection().setSessionSchoolGrade(null);
				// atr.getSection().setStudentTeacherSession(null);
				// atr.getSection().setSubjectSectionList(null);
				// atr.getSection().setGrade(null);
				sectionList.add(atr.getSection());
			}
		}
		return sectionList;

	}

	/** @param sectionId
	 * @param topicId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/unAssignTopicFromSection/{sectionId}/{topicId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Integer unAssignTopicFromSection(@PathVariable Long sectionId, @PathVariable Long topicId, HttpServletRequest request, HttpServletResponse response)
	{
		// UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		schoolTeacherService.unAssignTopicFromSection(sectionId, topicId);
		// Integer assignedTopicCount = schoolTeacherService.assignedTopicCountByTopic(topicId, loginUser.getId());
		return 1;
	}

	/** @param sectionId
	 * @param topicId
	 * @param moduleId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/unAssignModuleFromSection/{sectionId}/{topicId}/{moduleId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Integer unAssignModuleFromSection(@PathVariable Long sectionId, @PathVariable Long topicId, @PathVariable Long moduleId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		schoolTeacherService.unAssignModuleFromSection(sectionId, topicId, moduleId);
		Integer assignedModuleCount = schoolTeacherService.assignedStudentCountByModuleId(moduleId, loginUser.getId());
		return assignedModuleCount;
	}

	/** @param asssessmentId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getAllAssignedAssessmentStudentList", method = RequestMethod.POST)
	@LoginAuthentication
	public List<User> getAllAssignedAssessmentStudentList(@RequestParam Long asssessmentId, HttpServletRequest request, HttpServletResponse response)
	{
		List<AssignedAssessment> assessmentList = schoolTeacherService.getAllAssignedAssessmentStudentList(asssessmentId, ((UserForm) request.getSession().getAttribute("user")).getId());
		List<User> userList = new ArrayList<User>();
		if (assessmentList != null && assessmentList.size() > 0)
		{
			for (AssignedAssessment aa : assessmentList)
			{
				// aa.getUser().setSchool(null);
				// aa.getUser().setGrade(null);
				userList.add(aa.getUser());
			}
		}
		return userList;
	}

	/** @param request
	 * @param response
	 * @param assessmentId
	 * @return */
	@RequestMapping("/teacherController/removeAssessment/{assessmentId}")
	public String removeAssessment(HttpServletRequest request, HttpServletResponse response, @PathVariable Long assessmentId)
	{
		schoolTeacherService.removeAssessment(assessmentId);
		return "" + true;
	}

	/** @param request
	 * @param response
	 * @param topicId
	 * @return */
	@RequestMapping("/ajax/rest/getCorrectIncorrectQuestionCount/{topicId}")
	public String getCorrectIncorrectQuestionCount(HttpServletRequest request, HttpServletResponse response, @PathVariable Long topicId)
	{
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		Object[] data = studentCommonService.getCorrectIncorrectQuestionCount(userForm.getId(), topicId);
		Integer userTopicJscore = studentCommonService.getuserTopicGenScore(userForm.getId(), topicId);
		return data[0] + "," + data[1] + "," + userTopicJscore;
	}

	/** @param gradeId
	 * @param request
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSubjectsSme/{gradeId}", method = RequestMethod.POST)
	public List<Subject> getSubjectsSme(@PathVariable("gradeId") Long gradeId, HttpServletRequest request)
	{
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		List<Subject> subjectList = schoolSmeService.getAssignedSubjects(userForm.getSessionId(), userForm.getId(), gradeId, SmeGradeSubject.STATUS_ACTIVE);

		/* List<Subject> list = null;
		 * 
		 * if (subjectList != null && subjectList.size() > 0) { list = new ArrayList<Subject>(); for (Subject s : subjectList) { s.setChapters(null); list.add(s); } } return list; */
		return subjectList;
	}

	/** @param gradeId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getAllSectionByGradeId/{gradeId}", method = RequestMethod.POST)
	@LoginAuthentication
	public List<Section> getSectionByGradeIdAndSubjectId(@PathVariable("gradeId") Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Section> sectionList = schoolSmeService.getSectionByGradeId(loginUser.getSchoolId(), loginUser.getSessionId(), gradeId);
		/* for (Section s : sectionList) { s.setGrade(null); s.setSessionSchoolGrade(null); s.setSubjectSectionList(null); s.setStudentTeacherSession(null); } */
		return sectionList;
	}

	/** @param gradeId
	 * @param subjectId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getSectionByGradeId1/{gradeId}/{subjectId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Map<String, List> getStudentDetails1(@PathVariable("gradeId") Long gradeId, @PathVariable("subjectId") Long subjectId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Section> sectionList = schoolAdminService.getSectionByGradeId(loginUser.getSchoolId(), loginUser.getSessionId(), gradeId);

		Long sectionId = null;
		List ls = new ArrayList();
		for (Section s : sectionList)
		{
			// s.setGrade(null);
			// s.setSessionSchoolGrade(null);
			// s.setSubjectSectionList(null);
			// s.setStudentTeacherSession(null);
			sectionId = s.getId();
			if (sectionId == null)
			{
				break;
			}
			ls.add(sectionId);
		}

		List<Section> sectionList1 = schoolAdminService.getAllSectionBySubjectId(subjectId, ls);
		Map<String, List> data = new HashMap<String, List>();
		data.put("sectionC", sectionList);
		data.put("sectionIn", sectionList1);

		return data;
	}

	/** @param smeId
	 * @param gradeId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getAssignedSubjectListToSme", method = RequestMethod.POST)
	@LoginAuthentication
	public List<SmeGradeSubject> getAssignedSubjectListToSme(@RequestParam Long smeId, @RequestParam Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{
		List<SmeGradeSubject> smeGradeSubjectLst = schoolSmeService.getAssignedSubjectListToSmeByGradeId(smeId, gradeId);
		/* if (smeGradeSubjectLst != null && smeGradeSubjectLst.size() > 0) { for (SmeGradeSubject sgs : smeGradeSubjectLst) { sgs.setGrade(null); sgs.setSchool(null); sgs.setSession(null); sgs.setUser(null); sgs.getSubject().setChapters(null); } } */
		return smeGradeSubjectLst;
	}

	/** @param request
	 * @param response
	 * @return */
	@ResponseBody
	@RequestMapping(value = "/ajax/rest/getAllParents", method = RequestMethod.POST)
	public List<User> getAllParents(HttpServletRequest request, HttpServletResponse response)
	{

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<User> user = userService.getAllParentList(loginUser.getSchoolId());
		/* if (user != null && user.size() > 0) { for (User u : user) { u.setSchool(null); u.setGrade(null); } } */
		return user;
	}

	/** @param request
	 * @param response
	 * @param id
	 * @param userId
	 * @return */
	@ResponseBody
	@RequestMapping(value = "/ajax/rest/parentChildMapping/{id}/{userId}", method = RequestMethod.POST)
	public String saveParentChildMapping(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, @PathVariable Long userId)
	{
		ParentChildMapping pcm1 = schoolAdminService.checkParentChildMapping(id, userId);
		if (pcm1 == null)
		{
			ParentChildMapping pcm = new ParentChildMapping();
			pcm.setUserId(userId);
			pcm.setParentId(id);
			pcm.setModifiedDate(new Date());
			pcm.setInsertDate(new Date());
			userService.saveParentChildMapping(pcm);
		}
		else
		{
			return "Already Exist";
		}
		return "success";
	}

	/** @param smeGradeSubjectIds
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/unassignSubjectFromSme/{smeGradeSubjectIds}", method = RequestMethod.POST)
	@LoginAuthentication
	public Boolean unassignSubjectFromSme(@PathVariable String smeGradeSubjectIds, HttpServletRequest request, HttpServletResponse response)
	{
		String[] str = smeGradeSubjectIds.split("\\,");
		ArrayList<Long> smeGradeSubjectIdLst = new ArrayList<Long>();
		for (String s : str)
		{
			smeGradeSubjectIdLst.add(Long.parseLong(s));
		}
		Boolean status = schoolSmeService.unassignSubjectsFromSme(smeGradeSubjectIdLst);
		return status;
	}

	/** @param id
	 * @param type
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getStudentLearningReports/{id}/{type}", method = RequestMethod.POST)
	@LoginAuthentication
	public String getStudentLearningReports(@PathVariable Long id, @PathVariable String type, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<UserQuestionAnswer> reportLst = studentCommonService.getStudentLearningReportsbyChapterOrTopicId(loginUser, id, type);

		Integer correctCount = 0, incorrectCount = 0, skipCount = 0, totalAttempt = 0, genScore = 0;
		if (type.equalsIgnoreCase("topicwise"))
		{
			genScore = studentCommonService.getStudentGenScoreByTopicId(loginUser, id);
		}
		if (!reportLst.isEmpty())
		{

			for (UserQuestionAnswer uqa : reportLst)
			{
				totalAttempt += 1;
				if (uqa.getIsAttempted().equals(1))
				{
					if (uqa.getCorrectIncorrect().equals(1))
					{
						correctCount += 1;
					}
					else
					{
						incorrectCount += 1;
					}
				}
				else
				{
					skipCount += 1;
				}
			}
		}
		return totalAttempt + "-" + correctCount + "-" + incorrectCount + "-" + skipCount + "-" + genScore;
	}

	/** @param request
	 * @param response
	 * @param subId
	 * @param chapId
	 * @param topicId
	 * @return */
	@RequestMapping(value = "/ajax/rest/addFavouriteTopic", method = RequestMethod.POST)
	public String addFavouriteTopic(HttpServletRequest request, HttpServletResponse response, @RequestParam Long subId, @RequestParam Long chapId, @RequestParam Long topicId)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		FavoriteTopics ft = new FavoriteTopics();
		ft.setChapterId(chapId);
		ft.setCreatedOn(new Date());
		ft.setStatus(FavoriteTopics.STATUS_ACTIVE);
		ft.setSubjectId(subId);
		ft.setTopicId(topicId);
		ft.setUserId(loginUser.getId());
		studentCommonService.addFavouriteTopic(ft);
		return "true";

	}

	/** @param request
	 * @param response
	 * @param parentLinkingId
	 * @param acceptOrNot
	 * @return */
	@RequestMapping(value = "/ajax/rest/acceptRejectParentLinking/{parentLinkingId}", method = RequestMethod.POST)
	public Boolean addParentLinking(HttpServletRequest request, HttpServletResponse response, @PathVariable Long parentLinkingId, @RequestParam Integer acceptOrNot)
	{
		Integer acceptOrReject = ParentChildMapping.STATUS_ACTIVE;
		if (acceptOrNot.equals(0))
		{
			acceptOrReject = ParentChildMapping.STATUS_INACTIVE;
		}
		Boolean status = retailUserService.acceptRejectParentLinking(parentLinkingId, acceptOrReject);
		return status;

	}

	/** @param limit
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping("/admin/getCommentsnotificationsWithLimit/{limit}")
	public List<QuestionComments> getCommentsnotificationsWithLimit(@PathVariable Integer limit, HttpServletRequest request, HttpServletResponse response)
	{

		List<QuestionComments> commentsLst = adminService.getCommentsnotificationsWithLimit(limit);
		adminService.updateNotificationCountStatus();
		request.getSession().setAttribute("commentCnt", 0);
		return commentsLst;
	}

	/** @param questionId
	 * @param questComment
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/teacher/addQuestionComment/{questionId}", method = RequestMethod.POST)
	public Boolean addQuestionComment(@PathVariable Long questionId, @RequestParam String questComment, HttpServletRequest request, HttpServletResponse response)
	{

		QuestionComments qc = new QuestionComments();
		Date d = new Date();
		qc.setCommentDesc(questComment);
		qc.setPostedOn(d);
		qc.setUpdatedOn(d);
		qc.setQuestionId(questionId);
		qc.setStatus(QuestionComments.STATUS_ACTIVE);
		qc.setUserId(((UserForm) request.getSession().getAttribute("user")).getId());
		qc.setRead(false);
		Boolean status = schoolTeacherService.addQuestionComment(qc);
		Integer count = 1;
		Object commentCnt = request.getSession().getAttribute("commentCnt");
		if (commentCnt != null)
		{
			count += Integer.valueOf(commentCnt.toString());
		}
		request.getSession().setAttribute("commentCnt", count);
		return status;
	}

	/** @param questId
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping("/teacher/getAllTeacherCommentsByQuestId/{questId}")
	public List<QuestionComments> getAllTeacherCommentsByQuestId(@PathVariable Long questId, HttpServletRequest request, HttpServletResponse response)
	{
		Long userId = ((UserForm) request.getSession().getAttribute("user")).getId();
		List<QuestionComments> commentsLst = schoolTeacherService.getAllTeacherCommentsByQuestId(questId, userId);
		return commentsLst;
	}

	/** @param topicIdMap
	 * @return */
	@RequestMapping(value = "/ajax/rest/getQuestions/{topicIdMap}", method = RequestMethod.POST)
	public List<QuestionTopic> getQuestions(@PathVariable("topicIdMap") Long topicIdMap)
	{

		List<QuestionTopic> questions = adminService.getQuestions(String.valueOf(topicIdMap), Topic.STATUS_ACTIVE);

		/* List<Topic> list = null;
		 * 
		 * if (topicList != null && topicList.size() > 0) { list = new ArrayList<Topic>(); for (Topic t : topicList) { t.setChapter(null); t.setQuestionTopic(null); t.setUserQuestionAnswer(null); t.setSubject(null); list.add(t); } } return list; */
		return questions;
	}

	/** @param QmapWithTopic
	 * @param allQuestionsChecked
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/ajax/rest/mapQuestionWithTopic/{QmapWithTopic}/{allQuestionsChecked}", method = RequestMethod.POST)
	public String mapQuestionWithTopic(@PathVariable Long QmapWithTopic, @PathVariable String allQuestionsChecked[], HttpServletRequest request, HttpServletResponse response)
	{
		// UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		String status = adminService.MapQuestionWithTopic(QmapWithTopic, allQuestionsChecked);
		return status;

	}

	/** @param request
	 * @param response
	 * @param password
	 * @param confirmPassword
	 * @return
	 * @throws Exception */
	@LoginAuthentication
	@RequestMapping(value = "/ajax/rest/resetUserPassword/{password}/{confirmPassword}", method = RequestMethod.POST)
	public boolean resetUserPassword(HttpServletRequest request, HttpServletResponse response, @PathVariable String password, @PathVariable String confirmPassword) throws Exception
	{

		UserForm uf = ((UserForm) request.getSession().getAttribute("user"));
		Long userId = uf.getId();
		String Oldpassword = studentCommonService.getPassword(userId);
		if (Oldpassword.trim().equals(password.trim()))
		{
			studentCommonService.updateUserPassword(userId, confirmPassword.trim());
			return true;
		}
		else
		{
			return false;
		}

	}

	/** @param request
	 * @param response
	 * @param subjectId
	 * @param userId
	 * @return
	 * @throws Exception */
	@LoginAuthentication
	@RequestMapping(value = "/ajax/rest/getStudentSubjectProgressDetails/{subjectId}/{userId}")
	public int resetUserPassword(HttpServletRequest request, HttpServletResponse response, @PathVariable Long subjectId, @PathVariable Long userId) throws Exception
	{
		int percentage = 0;
		UserForm uf = ((UserForm) request.getSession().getAttribute("user"));
		List<Long> topicIds = schoolTeacherService.getAllTopicIdsInSubject(subjectId, uf.getSchoolId());
		Long questCount = schoolTeacherService.getAllQuestionCountByTopicIds(topicIds);
		if (questCount > 0)
		{
			Long questAttemptCount = studentCommonService.getAllAttemptedQuestionCount(topicIds, userId, uf.getSchoolId());
			percentage = (int) Math.round((questAttemptCount * 100) / questCount);
		}
		return percentage;

	}

	/** @param topicId
	 * @param chapterID
	 * @param subjectId
	 * @param checkUncheckedVal
	 * @param sectionId
	 * @param gradeId
	 * @param request
	 * @param response
	 * @return */

	@RequestMapping(value = "/ajax/rest/markCompleted/{topicId}/{chapterID}/{subjectId}/{checkUncheckedVal}/{sectionId}/{gradeId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Integer markCompleted(@PathVariable Long topicId, @PathVariable Long chapterID, @PathVariable Long subjectId, @PathVariable("checkUncheckedVal") String checkUncheckedVal, @PathVariable Long sectionId, @PathVariable Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{

		UserForm uf = ((UserForm) request.getSession().getAttribute("user"));
		Long userId = uf.getId();
		int isCompleted = 1;
		TeacherMarkTopicDetails teacherMark = new TeacherMarkTopicDetails();
		teacherMark.setChapterId(chapterID);
		teacherMark.setSubjectId(subjectId);
		teacherMark.setCreatedOn(new Date());
		teacherMark.setIsCompleted(isCompleted);
		teacherMark.setTopicId(topicId);
		teacherMark.setUserId(userId);
		teacherMark.setSectionId(sectionId);
		teacherMark.setGradeId(gradeId);
		if (checkUncheckedVal.equals("checked"))
		{

			userService.teacherMarkTopicDetails(teacherMark);

			return 1;
		}
		else
		{
			userService.teacherMarkTopicDetailsUnCheck(teacherMark);
			return 0;
		}

	}

	/** @param topicId
	 * @param moduleId
	 * @param contentType
	 * @param request
	 * @param response
	 * @return */

	@RequestMapping(value = "/ajax/rest/deleteModule/{topicId}/{moduleId}/{contentType}", method = RequestMethod.POST)
	@LoginAuthentication
	public boolean deleteModule(@PathVariable Long topicId, @PathVariable Long moduleId, @PathVariable Long contentType, HttpServletRequest request, HttpServletResponse response)
	{
		boolean val = userService.deleteModuleOrDeactivateStatus(topicId, moduleId);
		return val;
	}

	@RequestMapping("/ajax/schoolTeacher/getAllStudents")
	public List<User> getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

		long secId = Long.parseLong(request.getParameter("secId"));
		long subjectId = Long.parseLong(request.getParameter("subjectId"));

		List<User> StudentLst = schoolTeacherService.getUnassignStudent(subjectId, secId);
		return StudentLst;
	}

	/** @param chapIds
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getChapterDetailsByChapIds")
	@LoginAuthentication
	public List<String> getChapterDetailsByChapIds(@RequestParam String chapIds, HttpServletRequest request, HttpServletResponse response)
	{
		List<String> chapDetails = schoolTeacherService.getChapterNamesByChapIds(chapIds);
		return chapDetails;
	}

	/** @param grdId
	 * @param subId
	 * @param secId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/getAssignedTopicReport/{grdId}/{subId}/{secId}")
	@LoginAuthentication
	public List<AssignTopicResources> getAssignedTopicReport(@PathVariable Long grdId, @PathVariable Long subId, @PathVariable Long secId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<AssignTopicResources> ChapterWithTopicLst = schoolTeacherService.getAssignedTopicReport(grdId, subId, secId, loginUser.getId());

		Long studentCount = schoolTeacherService.getStudentCountBySectionId(subId, secId);
		if (!ChapterWithTopicLst.isEmpty())
		{
			for (AssignTopicResources atr : ChapterWithTopicLst)
			{
				atr.getTopic().setCount(Integer.parseInt(studentCount.toString()));
			}
		}

		return ChapterWithTopicLst;

	}

	/** @param grdId
	 * @param subId
	 * @param secId
	 * @param request
	 * @param response
	 * @return */

	@RequestMapping(value = "/ajax/rest/getAssignResourceReport/{grdId}/{subId}/{secId}")
	@LoginAuthentication
	public List<AssignTopicResources> getAssignedResourceReport(@PathVariable Long grdId, @PathVariable Long subId, @PathVariable Long secId, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<AssignTopicResources> getAllAssignedModulesLst = schoolTeacherService.getAssignedResourceReport(grdId, subId, secId, loginUser.getId());

		Long studentCount = schoolTeacherService.getStudentCountBySectionId(subId, secId);
		if (!getAllAssignedModulesLst.isEmpty())
		{
			for (AssignTopicResources atr : getAllAssignedModulesLst)
			{
				atr.getModule().setCount(Integer.parseInt(studentCount.toString()));
			}
		}

		return getAllAssignedModulesLst;
	}

	/** @param response
	 * @param request
	 * @param search
	 * @param grdId
	 * @param subId
	 * @param secId
	 * @return */
	@RequestMapping("/ajax/rest/searchStudentDetail")
	@LoginAuthentication
	public List<User> searchStudent(HttpServletResponse response, HttpServletRequest request, @RequestParam String search, @RequestParam Long grdId, @RequestParam Long subId, @RequestParam Long secId)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		List<User> searchedStudentDetailLst = schoolTeacherService.getStudentCompletedAssessment(loginUser, grdId, subId, search, secId);

		return searchedStudentDetailLst;

	}

	/** @param response
	 * @param request
	 * @param questionId
	 * @param status
	 * @return */

	@RequestMapping("/ajax/rest/enableDisableQuestion/{questionId}/{status}")
	@LoginAuthentication
	public Boolean enableDisableQuestion(HttpServletResponse response, HttpServletRequest request, @PathVariable Long questionId, @PathVariable Integer status)
	{
		Boolean flag = true;
		if (status.equals(2))
		{
			flag = adminService.checkQuestionIsAssignedOrNot(questionId);
		}
		if (flag)
			return adminService.enableDisableQuestion(questionId, status);
		return false;

	}

	/** @param response
	 * @param request
	 * @param chapId
	 * @param status
	 * @return */
	@RequestMapping("/ajax/rest/enableDisableChapter/{chapId}/{status}")
	@LoginAuthentication
	public Boolean enableDisableChapter(HttpServletResponse response, HttpServletRequest request, @PathVariable Long chapId, @PathVariable Integer status)
	{

		return adminService.enableDisableChapter(chapId, status);
	}

	/** @param response
	 * @param request
	 * @param topicId
	 * @param status
	 * @return */
	@RequestMapping("/ajax/rest/enableDisableTopic/{topicId}/{status}")
	@LoginAuthentication
	public Boolean enableDisableTopic(HttpServletResponse response, HttpServletRequest request, @PathVariable Long topicId, @PathVariable Integer status)
	{

		return adminService.enableDisableTopic(topicId, status);
	}

	/** @param topicId
	 * @param chapterID
	 * @param subjectId
	 * @param checkUncheckedVal
	 * @param sectionId
	 * @param gradeId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/rest/contentPermission/{topicId}/{chapterID}/{subjectId}/{checkUncheckedVal}/{sectionId}/{gradeId}", method = RequestMethod.POST)
	@LoginAuthentication
	public Integer contentPermission(@PathVariable Long topicId, @PathVariable Long chapterID, @PathVariable Long subjectId, @PathVariable("checkUncheckedVal") String checkUncheckedVal, @PathVariable Long sectionId, @PathVariable Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{

		UserForm uf = ((UserForm) request.getSession().getAttribute("user"));
		Long userId = uf.getId();
		int isCompleted = 1;
		TeacherContentPermission teacherMark = new TeacherContentPermission();
		teacherMark.setChapterId(chapterID);
		teacherMark.setSubjectId(subjectId);
		teacherMark.setCreatedOn(new Date());
		teacherMark.setContentPermission(isCompleted);
		teacherMark.setTopicId(topicId);
		teacherMark.setUserId(userId);
		teacherMark.setSectionId(sectionId);
		teacherMark.setGradeId(gradeId);
		if (checkUncheckedVal.equals("checked"))
		{

			userService.teacherMarkTopicContentPermissionDetails(teacherMark);

			return 1;
		}
		else
		{
			userService.teacherMarkTopicContentPermissionDetailsUnCheck(teacherMark);
			return 0;
		}

	}
	@RequestMapping(value = "/ajax/rest/getTopicGraph/{topicId}", method = RequestMethod.POST)
	public String getTopicGraph(@PathVariable("topicId") Long topicId, HttpSession session)
	{
		
	
		UserForm loginUserObj = (UserForm)session.getAttribute("user");
		String userId=""+loginUserObj.getId();
		String topicIdString=""+topicId;
		String map="";
		
		/*String userId="1066";
		String topicIdString="18814";*/
		List<TopicGraph> topicGraphList = adminService.getTopicGraph(topicIdString,userId);
		ObjectMapper mapper=new ObjectMapper();
		
		try
		{
			 map=mapper.writeValueAsString(topicGraphList) ;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (TopicGraph topicGraphL : topicGraphList)
		{

			// ss.getSubject().setChapters(null);
			topicGraphL.getCoorect();
			topicGraphL.getOrderMonth();
			topicGraphL.getOrderYear();
			System.out.println("Coorect================================="+topicGraphL.getCoorect());
			System.out.println("OrderMonth================================="+topicGraphL.getOrderMonth());
			System.out.println("OrderYear================================="+topicGraphL.getOrderYear());
			
		}*/
		return map;
	}
}
