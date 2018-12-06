package com.flp.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.flp.annotations.LoginAuthentication;
import com.flp.model.Assessment;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.Grade;
import com.flp.model.Module;
import com.flp.model.ModuleType;
import com.flp.model.QuestionTopic;
import com.flp.model.QuestionType;
import com.flp.model.SchoolStudentTest;
import com.flp.model.SchoolStudentTestDetail;
import com.flp.model.Section;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.service.SchoolTeacherService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
import com.flp.util.MailSenderUtil;
import com.flp.util.SchoolTeacherUtil;
import com.flp.util.Utility;
import com.flp.vo.GlobalVO;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/** @author Amresh */
@Controller
public class SchoolTeacherController
{
	private static final String flagModule = "M";
	private static final String flagQuestion = "Q";
	@Autowired
	private UserService userService;
	@Autowired
	private SchoolTeacherUtil schoolTeacherUtil;
	@Autowired
	private MailSenderUtil mailSenderUtil;
	@Autowired
	private SchoolTeacherService schoolTeacherService;

	@Autowired
	private StudentCommonService studentCommonService;

	Map<Long, String> gradeMap = new HashMap<Long, String>();
	Map<Long, String> sectionMap = new HashMap<Long, String>();

	/** @param request
	 * @param response
	 * @param secId
	 * @param subId
	 * @param grade
	 * @param sec
	 * @param sub
	 * @param grdId
	 * @return */
	@LoginAuthentication
	@RequestMapping("/schoolTeacher/getCreateAssessment.htm")
	public ModelAndView getCreateTestPage(HttpServletRequest request, HttpServletResponse response, @RequestParam Long secId, @RequestParam Long subId, @RequestParam(required = false) String grade, @RequestParam(required = false) String sec, @RequestParam(required = false) String sub, @RequestParam Long grdId)
	{

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		String sessionDate = schoolTeacherService.getSessionDate(loginUser);
		List<Assessment> assessmentList = schoolTeacherService.getCreatedTestByTeacher(loginUser, subId, grdId);
		List<QuestionType> quesType = schoolTeacherService.getQuestionType(QuestionType.QUESTION_COGNITIVE, QuestionType.STATUS_ACTIVE);
		List<User> StudentLst = schoolTeacherService.getUnassignStudent(subId, secId);
		ModelAndView mav = new ModelAndView("schoolTeacher/addAssessment");

		if (grade == null)
		{
			grade = (schoolTeacherService.getGradeObjById(grdId)).getName();
			sec = (schoolTeacherService.getSectionObjById(secId)).getName();
			sub = (schoolTeacherService.getSubjectObjById(subId)).getName();
		}

		mav.addObject("quesType", quesType);

		mav.addObject("currentDate", Utility.getCurrentDate());

		mav.addObject("grade", grade);
		mav.addObject("subject", sub);
		mav.addObject("section", sec);
		mav.addObject("subjectId", subId);
		mav.addObject("gradeId", grdId);
		mav.addObject("sectionId", secId);
		mav.addObject("studentList", StudentLst);

		mav.addObject("assessmentList", assessmentList);
		mav.addObject("sessionDate", sessionDate);

		getSubjectProgressWithImage(subId, mav, loginUser, secId, grdId);

		return mav;
	}

	/** @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping("/ajax/schoolTeacher/getChapters")
	@ResponseBody
	@LoginAuthentication
	public List<Chapter> getChapters(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

		long gradeId = Long.parseLong(request.getParameter("gradeId"));
		long subjectId = Long.parseLong(request.getParameter("subjectId"));
		List<Chapter> chapterList = schoolTeacherService.getChpater(gradeId, subjectId);
		if (chapterList != null && chapterList.size() > 0)
		{
			for (Chapter c : chapterList)
			{
				c.setSubject(null);
				for (Topic t : c.getTopics())
				{
					t.setQuestionTopic(null);
					t.setUserQuestionAnswer(null);
					// t.setTopicSmartScore(null);
					// t.setSubject(null);
				}
			}
		}
		return chapterList;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping("/schoolTeacher/getQuestions")
	@ResponseBody
	@LoginAuthentication
	public ModelAndView getQuestionPage(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("schoolTeacher/questionSelect");
		String topicIds = request.getParameter("topicIds");
		long userId = -1;
		// int createdBy = Integer.parseInt(request.getParameter("createdBy"));
		String createByIds = request.getParameter("createdById");

		int i = 0;
		String[] ab = createByIds.split(",");
		for (String a : ab)
		{
			int j = Integer.parseInt(a);
			i = i + j;
		}

		/* switch(createdBy) { case 1: userId=1; // self break; case 2: userId=2; // ctls break; case 3: userId=3; // sme } */
		/* if (createdBy == 1) userId = -1; else */
		userId = loginUser.getId();
		List<QuestionTopic> quesTopictList = schoolTeacherService.getQuestionList(topicIds, userId, loginUser.getSchoolId(), i);
		List<QuestionType> quesType = schoolTeacherService.getQuestionType(QuestionType.QUESTION_COGNITIVE, QuestionType.STATUS_ACTIVE);
		int totalCount = 0, easyCount = 0, intermediateCount = 0, hardCount = 0;
		;
		if (!quesTopictList.isEmpty())
		{
			totalCount = quesTopictList.size();
			for (QuestionTopic q : quesTopictList)
			{
				if (q.getDifficultyLevelId().equals(1l))
				{

					easyCount++;
				}
				else if (q.getDifficultyLevelId().equals(2l))
				{
					intermediateCount++;
				}
				else if (q.getDifficultyLevelId().equals(3l))
				{
					hardCount++;
				}

			}
		}
		mav.addObject("questionList", quesTopictList);
		mav.addObject("totalCount", totalCount);
		mav.addObject("easyCount", easyCount);
		mav.addObject("intermediateCount", intermediateCount);
		mav.addObject("hardCount", hardCount);
		mav.addObject("quesType", quesType);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param mappingList
	 * @return */
	@RequestMapping("/ajax/schoolTeacher/createAssessment")
	@ResponseBody
	public String createAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "mappingList") List<String> mappingList)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		String assName = request.getParameter("name");
		String sequence = request.getParameter("sequence");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		long gradeId = Long.parseLong(request.getParameter("gradeId"));
		long subjectId = Long.parseLong(request.getParameter("subjectId"));
		String instruction = request.getParameter("instruction");
		int testType = Integer.parseInt(request.getParameter("testType"));
		int duration = Integer.parseInt(request.getParameter("duration"));
		String chapterIds = request.getParameter("chapterIds");
		String topics = request.getParameter("topicId");
		String studentIds = request.getParameter("assStudentlst");
		Long secId = Long.parseLong(request.getParameter("secId"));
		request.getSession().setAttribute("tab123", "mTest");
		String[] sIds = studentIds.split(",");

		String result = schoolTeacherService.createAssessment(assName, startDate, endDate, gradeId, subjectId, loginUser.getSchoolId(), mappingList, instruction, testType, duration, loginUser.getId(), loginUser.getSessionId(), sequence, chapterIds, topics, studentIds, secId);
		List<Long> t_allStudent_id = schoolTeacherUtil.convertStringArrayToList(sIds);
		List<User> userDetailsForEmail = schoolTeacherService.getUserDetailForEmail(t_allStudent_id, User.USERTYPE_STUDENT);
		mailSenderUtil.sendMultipleMails(userDetailsForEmail, "Assessment " + assName, "Assessment " + assName + "created. starting date is : " + startDate + " and end on : " + endDate + ". :::" + instruction);
		return result;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping("/schoolAdmin/addAssessment.htm")
	@LoginAuthentication
	public ModelAndView addAssessment(HttpServletRequest request, HttpServletResponse response)
	{

		return new ModelAndView("schoolTeacher/addAssessment");

	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping("/schoolTeacher/getAssignedTaskDetails.htm")
	@LoginAuthentication
	public ModelAndView getAssignedAssessment(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("schoolTeacher/assignTask");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Assessment> assessmentList = schoolTeacherService.getCreatedTest(loginUser);
		mav.addObject("assessmentList", assessmentList);
		// List<Grade> gradeList = adminService.getGrades(null, loginUser.getSchoolId(), Grade.STATUS_ACTIVE);

		List<StudentTeacherSession> list = schoolTeacherService.getTeacherSubjectSection(loginUser.getId(), loginUser.getSessionId());
		mav.addObject("teacherSection", list);

		Date date = Utility.getCurrentDate();

		mav.addObject("currentDate", date);
		// mav.addObject("gradeList", gradeList);

		return mav;
	}

	/** @param request
	 * @param response
	 * @param studentIds
	 * @param sectionId
	 * @param assessmentId
	 * @return
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/assignAssessment.htm")
	@LoginAuthentication
	public ModelAndView assignAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam Long[] studentIds, @RequestParam Long sectionId, @RequestParam Long assessmentId) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		if (studentIds != null && studentIds.length > 0 && sectionId != null)
		{
			schoolTeacherService.assignAssessment(loginUser, studentIds, sectionId, assessmentId);
		}
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getAssignedTaskDetails.htm");
		return null;
	}

	/** @param request
	 * @param response
	 * @param studentIds
	 * @param assessmentId
	 * @return
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/unAssignAssessment.htm")
	@LoginAuthentication
	public ModelAndView unAssignAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam Long[] studentIds, @RequestParam("assessmentId") Long assessmentId) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		if (studentIds != null && studentIds.length > 0 && assessmentId != null)
		{
			schoolTeacherService.unAssignAssessment(loginUser, studentIds, assessmentId);
		}
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getAssignedTaskDetails.htm");
		return null;
	}

	/** @param request
	 * @param response
	 * @param assessmentId
	 * @return
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/publishedAssessment/{assessmentId}")
	@LoginAuthentication
	public ModelAndView publishedAssessment(HttpServletRequest request, HttpServletResponse response, @PathVariable Long assessmentId) throws IOException
	{
		schoolTeacherService.publishedAssessment(assessmentId);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getAssignedTaskDetails.htm");
		return null;
	}

	/** @param request
	 * @param response
	 * @param secId
	 * @param subId
	 * @param grade
	 * @param sec
	 * @param sub
	 * @param grdId
	 * @return */
	@RequestMapping(value = "/schoolTeacher/getManageTocHome.htm", method = RequestMethod.GET)
	@LoginAuthentication
	public ModelAndView questionPage(HttpServletRequest request, HttpServletResponse response, @RequestParam Long secId, @RequestParam Long subId, @RequestParam String grade, @RequestParam String sec, @RequestParam String sub, @RequestParam Long grdId)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("/schoolTeacher/manageTocHome");

		if (subId != null && subId > 0l)
		{
			List<Chapter> chaperList = schoolTeacherService.getChpater(grdId, subId);
			// List<Chapter> chaperList = schoolTeacherService.getChpatersByGradeAndSubjects(gIds, subjectIds, loginUser.getSchoolId());
			List<Long> topicIdList = new ArrayList<Long>();
			for (Chapter ss : chaperList)
			{

				for (Topic top : ss.getTopics())
				{
					topicIdList.add(top.getId());
				}

			}
			// List<Long>
			Map<Long, Integer> questCount = schoolTeacherService.getQuestionCountByTopicId(topicIdList);
			Map<Long, Integer> moduleCountList = schoolTeacherService.getModuleCountByTopicId(topicIdList);
			List<Long> isTopicAssigned = schoolTeacherService.getAllAssignedTopicByTopicIds(topicIdList, secId, grdId, subId);
			List<Long> ll = schoolTeacherService.getMarkTopics(loginUser.getId(), subId);
			List<Long> contentPermission = schoolTeacherService.getMarkTopicsContentPermission(loginUser.getId(), subId);
			for (Chapter ss : chaperList)
			{
				Boolean flagForChapterContentPermission = false;
				Boolean flag = false;
				Integer chapCount = 0;
				Integer chapmoduleCount = 0;
				for (Topic top : ss.getTopics())
				{
					if (contentPermission.contains(top.getId()))
					{
						top.setMarkTopicContentPermission(1);
					}
					else
					{
						top.setMarkTopicContentPermission(0);
						flagForChapterContentPermission = true;
					}

					if (isTopicAssigned.contains(top.getId()))
					{
						top.setIsAssigned(1);
					}

					if (ll.contains(top.getId()))
					{
						top.setMarkTopic(1);
					}
					else
					{
						top.setMarkTopic(0);
						flag = true;
					}
					if (questCount != null)
					{
						Integer questioncount = questCount.get(top.getId());
						if (questioncount != null)
						{
							top.setCount(questioncount);

							chapCount += questioncount;

						}
						else
						{
							top.setCount(0);

						}
					}
					if (moduleCountList != null)
					{
						Integer moduleCount = moduleCountList.get(top.getId());

						if (moduleCount != null)
						{
							top.settModulecount(moduleCount);
							chapmoduleCount += moduleCount;
						}
						else
						{
							top.settModulecount(0);
						}
					}

				}
				if (!flag)
				{
					ss.setIsCompleted(1);
				}
				else
				{
					ss.setIsCompleted(0);
				}
				if (!flagForChapterContentPermission)
				{
					ss.setMarkChapterContentPermission(1);
				}
				else
				{
					ss.setMarkChapterContentPermission(0);
				}

				ss.setCount(chapCount);
				ss.setModuleCount(chapmoduleCount);
			}

			mav.addObject("chapterList", chaperList);

		}

		mav.addObject("grade", grade);
		mav.addObject("subject", sub);
		mav.addObject("section", sec);
		mav.addObject("subjectId", subId);
		mav.addObject("gradeId", grdId);
		mav.addObject("sectionId", secId);

		getSubjectProgressWithImage(subId, mav, loginUser, secId, grdId);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param topicId
	 * @param secId
	 * @param subId
	 * @param grdId
	 * @param flagQorM
	 * @return */
	@RequestMapping(value = "/schoolTeacher/getManageTocModule.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@LoginAuthentication
	public ModelAndView questionPage2(HttpServletRequest request, HttpServletResponse response, @RequestParam Long topicId, @RequestParam Long secId, @RequestParam Long subId, @RequestParam Long grdId, @RequestParam String flagQorM)
	{
		ModelAndView mav = new ModelAndView("/schoolTeacher/manageTocModule");

		String propertyDirectoryString = null;
		propertyDirectoryString = Utility.getValueFromPropertyFile("flp.qa.images.retrieve");
		String questionResourceDirectory = (propertyDirectoryString.charAt(propertyDirectoryString.length() - 1) != '/' ? propertyDirectoryString + "/" : propertyDirectoryString);		// "D:/FLP_RESOURCES/QA_IMAGES";

		propertyDirectoryString = Utility.getValueFromPropertyFile("flp.modules.retrieve");
		String moduleResourceDirectory = (propertyDirectoryString.charAt(propertyDirectoryString.length() - 1) != '/' ? propertyDirectoryString + "/" : propertyDirectoryString);		// "D:/FLP_RESOURCES/MODULES";

		List<QuestionTopic> questionTopicList = schoolTeacherService.getAllQuestionsByTopic(topicId);
		mav.addObject("questionTopic", questionTopicList);
		List<Module> moduleLst = schoolTeacherService.getModulesByTopic(topicId);

		GlobalVO globalVO = new GlobalVO();
		globalVO.setQuestionTypeList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_TYPE, QuestionType.STATUS_ACTIVE));
		globalVO.setQuestionDifficultyLevelList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_DIFFICULTY, QuestionType.STATUS_ACTIVE));
		globalVO.setQuestionCognitiveLevelList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_COGNITIVE, QuestionType.STATUS_ACTIVE));

		globalVO.setResourceTypeList(schoolTeacherService.getModuleTypes(ModuleType.MODULE_RESOURCE_TYPE, ModuleType.STATUS_ACTIVE));
		globalVO.setGradeTypeList(schoolTeacherService.getModuleTypes(ModuleType.MODULE_GRADE_TYPE, ModuleType.STATUS_ACTIVE));

		globalVO.setQuestionResourceDirectory(questionResourceDirectory);
		globalVO.setModuleResourceDirectory(moduleResourceDirectory);

		globalVO.setTopicId(topicId);

		// mav.addObject("topicId",topicId);
		mav.addObject("moduleLst", moduleLst);
		mav.addObject("globalVO", globalVO);

		if (gradeMap != null)
		{
			String gradeName = gradeMap.get(grdId);
			if (gradeName == null)
			{
				gradeName = (schoolTeacherService.getGradeObjById(grdId)).getName();
				gradeMap.put(grdId, gradeName);

			}
			mav.addObject("grade", gradeName);
		}

		if (sectionMap != null)
		{
			String sectionName = sectionMap.get(secId);
			if (sectionName == null)
			{
				sectionName = (schoolTeacherService.getSectionObjById(secId)).getName();
				sectionMap.put(secId, sectionName);
			}
			mav.addObject("section", sectionName);
		}

		Topic topicObj = schoolTeacherService.getTopicobjById(topicId);
		mav.addObject("subject", topicObj.getChapter().getSubject().getName());
		mav.addObject("chapterName", topicObj.getChapter().getName());
		mav.addObject("chapterId", topicObj.getChapter().getId());
		mav.addObject("topicName", topicObj.getName());
		mav.addObject("subjectId", subId);
		mav.addObject("gradeId", grdId);
		mav.addObject("sectionId", secId);

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		getSubjectProgressWithImage(subId, mav, loginUser, secId, grdId);
		mav.addObject("checkFlagQorM", flagQorM);

		return mav;
	}

	/** @param request
	 * @param response
	 * @param topicId
	 * @param secId
	 * @param subId
	 * @param gradeId
	 * @return */
	@RequestMapping("/schoolTeacher/getManageTocQuestion.htm")
	@LoginAuthentication
	public ModelAndView questionPage3(HttpServletRequest request, HttpServletResponse response, @RequestParam Long topicId, @RequestParam Long secId, @RequestParam Long subId, @RequestParam Long gradeId)
	{
		GlobalVO globalVO = new GlobalVO();
		globalVO.setQuestionTypeList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_TYPE, QuestionType.STATUS_ACTIVE));
		globalVO.setQuestionDifficultyLevelList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_DIFFICULTY, QuestionType.STATUS_ACTIVE));
		globalVO.setQuestionCognitiveLevelList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_COGNITIVE, QuestionType.STATUS_ACTIVE));
		globalVO.setTopicId(topicId);

		ModelAndView mav = new ModelAndView("/schoolTeacher/manageTocQuestion");
		mav.addObject("globalVO", globalVO);
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		getSubjectProgressWithImage(subId, mav, loginUser, secId, gradeId);
		Section sectionObj = schoolTeacherService.getSectionObjById(secId);
		Grade gradeObj = schoolTeacherService.getGradeObjById(gradeId);
		mav.addObject("grade", gradeObj.getName());
		mav.addObject("subject", request.getParameter("subjectName"));
		mav.addObject("section", sectionObj.getName());
		mav.addObject("subjectId", subId);
		mav.addObject("gradeId", gradeId);
		mav.addObject("sectionId", secId);

		return mav;
	}

	/** @param request
	 * @param response
	 * @param globalVO
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/addModule.htm")
	@LoginAuthentication
	public void addModule(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("globalVO") GlobalVO globalVO) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		globalVO.setSchoolId(loginUser.getSchoolId());
		globalVO.setCreatedBy(loginUser.getId());

		schoolTeacherService.uploadModule(globalVO);
		request.getSession().setAttribute("flagModule", 1);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&flagQorM=" + flagModule);

	}

	/** @param request
	 * @param response
	 * @param globalVO
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/addWebModule.htm")
	@LoginAuthentication
	public void addWebModule(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("globalVO") GlobalVO globalVO) throws IOException
	{

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		globalVO.setSchoolId(loginUser.getSchoolId());
		globalVO.setCreatedBy(loginUser.getId());
		schoolTeacherService.addWebModule(globalVO);
		request.getSession().setAttribute("flagModule", 1);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&flagQorM=" + flagModule);
		// response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId()+ "&subjectName=" + request.getParameter("subjectName") + "&chapName=" + request.getParameter("chapName") + "&topicName=" + request.getParameter("topicName"));
	}

	/** @param request
	 * @param response
	 * @param globalVO
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/addDocument.htm")
	@LoginAuthentication
	public void addDocument(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("globalVO") GlobalVO globalVO) throws IOException
	{

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		globalVO.setSchoolId(loginUser.getSchoolId());
		globalVO.setCreatedBy(loginUser.getId());
		schoolTeacherService.uploadDocument(globalVO);
		request.getSession().setAttribute("flagModule", 1);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&flagQorM=" + flagModule);
		// response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId()+ "&subjectName=" + request.getParameter("subjectName") + "&chapName=" + request.getParameter("chapName") + "&topicName=" + request.getParameter("topicName"));
	}

	/** @param globalVO
	 * @param request
	 * @param response
	 * @throws IOException */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/uploadQuestion.htm")
	public void uploadQuestion(@ModelAttribute(value = "globalVO") GlobalVO globalVO, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// String URL = request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&subjectName=" + request.getParameter("subjectName") + "&chapName=" + request.getParameter("chapName") + "&topicName=" + request.getParameter("topicName") + "&submit=true";

		schoolTeacherService.saveUpdateQuestion(globalVO);
		request.getSession().setAttribute("saveQuestion", true);

		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&flagQorM=" + flagQuestion);
		// response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&subjectName=" + request.getParameter("subjectName") + "&chapName=" + request.getParameter("chapName") + "&topicName=" + request.getParameter("topicName"));

		// return message;
	}

	/** @param globalVO
	 * @param request
	 * @param response
	 * @throws IOException */
	@ResponseBody
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/editQuestion.htm", method = RequestMethod.POST)
	public void editQuestion(@ModelAttribute(value = "globalVO") GlobalVO globalVO, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String message = schoolTeacherService.saveUpdateQuestion(globalVO);

		if (message.equalsIgnoreCase("used"))
			request.getSession().setAttribute("msg", message);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&flagQorM=" + flagQuestion);
		// response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&subjectName=" + request.getParameter("subjectName") + "&chapName=" + request.getParameter("chapName") + "&topicName=" + request.getParameter("topicName"));

	}

	/** @param request
	 * @param response
	 * @param assessmentId
	 * @return */
	@RequestMapping("/schoolTeacher/assessmentPreview/{assessmentId}")
	public ModelAndView viewEditAssessment(HttpServletRequest request, HttpServletResponse response, @PathVariable long assessmentId)
	{
		ModelAndView mav = new ModelAndView("/schoolTeacher/assessmentPreview");
		Assessment assObj = schoolTeacherService.getAssessment(assessmentId);
		if (assObj != null)
		{
			Integer totalQuestion = 0;
			// Integer totalMarks = 0;
			/* for (AssessmentQuestion aq : assObj.getAssessmentQuestion()) { totalMarks = (int) (totalMarks + aq.getMarks()); totalQuestion++; } assObj.setTotalMarks(totalMarks); */
			assObj.setTotalQuestion(totalQuestion);
			mav.addObject("assessment", assObj);
		}
		return mav;
	}

	/** @param request
	 * @param response
	 * @param mpf
	 * @param topicId
	 * @param secId
	 * @param subId
	 * @param grdId
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/teacher/uploadQuestionExcel.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam("file2") MultipartFile mpf, @RequestParam Long topicId, @RequestParam Long secId, @RequestParam Long subId, @RequestParam Long grdId)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		String msg = null;
		try
		{
			Iterator<String> itr = request.getFileNames();

			// MultipartFile mpf = request.getFile(itr.next());
			File excelFile = new File(mpf.getOriginalFilename());
			String fileName = excelFile.getName();
			mpf.transferTo(excelFile);

			if (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).equals("xlsx") || fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).equals("xls"))
			{
				msg = schoolTeacherService.uploadQuestionFile(excelFile, loginUser.getId(), loginUser.getSchoolId(), topicId);
				if (msg.equalsIgnoreCase("success"))
					msg = "Questions uploaded successfully";
			}
			else
				msg = "Please provide excel file";

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		request.getSession().setAttribute("excelMsg", msg);
		try
		{
			response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + topicId + "&secId=" + secId + "&subId=" + subId + "&grdId=" + grdId + "&flagQorM=" + flagQuestion);

			// response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?tab=question&topicId=" + topicId);
			return null;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/** @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/updateAssessment.htm")
	@LoginAuthentication
	public ModelAndView updateAssessment(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Assessment ass = new Assessment();
		ass.setTestName(request.getParameter("assessmentName"));
		ass.setId(Long.parseLong(request.getParameter("assessmentId")));
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);
		try
		{
			Date sDate = format.parse(request.getParameter("startDate"));
			Date eDate = format.parse(request.getParameter("endDate"));
			ass.setStartDate(sDate);
			ass.setEndDate(eDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		schoolTeacherService.updateAssessment(ass);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getCreateAssessment.htm?tab=mTest");
		return null;
	}

	/** @param request
	 * @param response
	 * @param testName
	 * @param strtDate
	 * @param endDate
	 * @param assmentId
	 * @return */

	@ResponseBody
	@RequestMapping("/schoolTeacher/updateAssessments")
	@LoginAuthentication
	public Integer updateUpcomingAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam String testName, @RequestParam String strtDate, @RequestParam String endDate, @RequestParam Long assmentId)
	{

		Assessment ass = new Assessment();
		Integer a;
		DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);

		try
		{

			Date newStartDate = formatter.parse(strtDate);
			Date newEndDate = formatter.parse(endDate);
			ass.setStartDate(newStartDate);
			ass.setEndDate(newEndDate);

			ass.setTestName(testName);
			ass.setId(assmentId);

		}

		catch (ParseException e)
		{
			e.printStackTrace();
		}

		a = schoolTeacherService.updateUpcomingAssessment(ass);

		return a;

	}

	/** @param request
	 * @param response
	 * @return */

	@RequestMapping("/schoolTeacher/getAssessmentReports.htm")
	@LoginAuthentication
	public ModelAndView getAssessmentReports(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("/schoolTeacher/assessmentReports");
		Date endDate = Utility.getCurrentDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.DATE, -7);  // number of days to add
		Date startDate = calendar.getTime();
		List<Assessment> assessmentList = schoolTeacherService.getAssessmentByLimitDate(loginUser, startDate, endDate);
		mav.addObject("assessmentList", assessmentList);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param assessmentId
	 * @param assessmentName
	 * @param mm
	 * @return */
	@RequestMapping("/schoolTeacher/getAssessmentReportDetails")
	@LoginAuthentication
	public ModelAndView getAssessmentReportDetails(HttpServletRequest request, HttpServletResponse response, @RequestParam Long assessmentId, @RequestParam String assessmentName, @RequestParam Float mm)
	{

		List<SchoolStudentTest> studentAssessmentDetais = schoolTeacherService.getStudentAssessmentDetailsById(assessmentId);
		ModelAndView mav = new ModelAndView("/schoolTeacher/assessmentReportDetails");
		mav.addObject("maxMarks", mm);
		mav.addObject("assessmentName", assessmentName);
		mav.addObject("assessmentId", assessmentId);
		mav.addObject("studentAssessmentDetais", studentAssessmentDetais);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param id
	 * @param reportType
	 * @return */
	@RequestMapping("/schoolTeacher/getLearningReportBySubjctOrChapterOrTopic/{id}/{reportType}")
	@LoginAuthentication
	public ModelAndView getLearningReportBySubjcts(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, @PathVariable String reportType)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("/schoolTeacher/learningReportDetails");
		if (reportType.equals("reportsBySubject"))
		{
			Long sectionId = id;
			List<SubjectSection> subjectSectionLst = schoolTeacherService.getLearningReportForSubjects(sectionId, loginUser.getSchoolId(), loginUser.getSessionId());
			if (!subjectSectionLst.isEmpty())
			{
				for (SubjectSection ss : subjectSectionLst)
				{
					Integer questionCount = schoolTeacherService.getTotalQuestionCountBySubject(ss.getSubjectId());
					ss.getSubject().setCount(questionCount);
				}
			}
			mav.addObject("reportListBySubject", subjectSectionLst);

		}
		else if (reportType.equals("reportsByChapter"))
		{
			Long subjectId = id;
			List<Chapter> chaptersList = schoolTeacherService.getLearningReportForChapters(subjectId, loginUser.getSchoolId(), loginUser.getSessionId());
			if (!chaptersList.isEmpty())
			{
				for (Chapter c : chaptersList)
				{
					Integer questionCount = schoolTeacherService.getTotalQuestionCountByChapter(c.getId());
					c.setCount(questionCount);
				}
			}
			mav.addObject("reportListBySubject", chaptersList);
		}

		else if (reportType.equals("reportsByTopic"))
		{
			Long chapterId = id;
			List<Topic> topicList = schoolTeacherService.getLearningReportForTopic(chapterId, loginUser.getSchoolId());
			if (!topicList.isEmpty())
			{
				for (Topic t : topicList)
				{
					Integer questionCount = schoolTeacherService.getTotalQuestionCountByTopic(t.getId());
					t.setCount(questionCount);
				}
			}
			mav.addObject("reportListBySubject", topicList);
		}
		mav.addObject("reportType", reportType);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param gradeId
	 * @param sectionId
	 * @param subjectId
	 * @param reportType
	 * @param questionCount
	 * @return */
	@RequestMapping("/schoolTeacher/getStudentwiseReport")
	@LoginAuthentication
	public ModelAndView getStudentwiseReport(HttpServletRequest request, HttpServletResponse response, @RequestParam Long gradeId, @RequestParam Long sectionId, @RequestParam Long subjectId, @RequestParam String reportType, @RequestParam Integer questionCount)
	{
		ModelAndView mav = new ModelAndView("/schoolTeacher/questionwiseReportViewDetails");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		Long id = subjectId;
		if (reportType.equals("chapterwise"))
		{
			id = Long.parseLong(request.getParameter("chapterId"));
		}
		else if (reportType.equals("topicwise"))
		{
			id = Long.parseLong(request.getParameter("topicId"));
		}
		Map<Long, Integer> rightQuestionCount = new HashMap<Long, Integer>();
		Map<Long, Integer> wrongQuestionCount = new HashMap<Long, Integer>();
		Map<Long, Integer> skipQuestionCount = new HashMap<Long, Integer>();
		List<UserQuestionAnswer> userQuestionAnswerLst = schoolTeacherService.getUserQuestionAnswerList(reportType, id, gradeId, loginUser.getSchoolId());
		if (!userQuestionAnswerLst.isEmpty())
		{
			for (UserQuestionAnswer uqa : userQuestionAnswerLst)
			{

				if (uqa.getIsAttempted().equals(1))
				{
					if (uqa.getCorrectIncorrect().equals(1))
					{
						Integer rightCount = rightQuestionCount.get(uqa.getUserId());
						if (rightCount != null)
						{
							rightCount++;
							rightQuestionCount.put(uqa.getUserId(), rightCount);
						}
						else
						{
							rightQuestionCount.put(uqa.getUserId(), 1);
						}
					}
					else
					{
						Integer wrongCount = wrongQuestionCount.get(uqa.getUserId());
						if (wrongCount != null)
						{
							wrongCount++;
							wrongQuestionCount.put(uqa.getUserId(), wrongCount);
						}
						else
						{
							wrongQuestionCount.put(uqa.getUserId(), 1);
						}
					}
				}
				else
				{
					Integer skipCount = skipQuestionCount.get(uqa.getUserId());
					if (skipCount != null)
					{
						skipCount++;
						skipQuestionCount.put(uqa.getUserId(), skipCount);
					}
					else
					{
						skipQuestionCount.put(uqa.getUserId(), 1);
					}
				}
			}
		}
		List<StudentTeacherSession> userList = schoolTeacherService.getStudentListBySubjectId(subjectId, sectionId, loginUser.getSchoolId(), loginUser.getSessionId());
		if (!userList.isEmpty())
		{
			for (StudentTeacherSession sts : userList)
			{
				Integer correctCount = rightQuestionCount.get(sts.getUserId()) != null ? rightQuestionCount.get(sts.getUserId()) : 0;
				Integer incorrectCount = wrongQuestionCount.get(sts.getUserId()) != null ? wrongQuestionCount.get(sts.getUserId()) : 0;
				Integer skipCount = skipQuestionCount.get(sts.getUserId()) != null ? skipQuestionCount.get(sts.getUserId()) : 0;
				Integer totalAttempt = correctCount + incorrectCount + skipCount;
				Integer percentage = 0;
				if (questionCount > 0)
				{
					Double data = (Double.parseDouble(correctCount.toString()) * 100) / Double.parseDouble(questionCount.toString());
					percentage = (int) Math.round(data);

				}
				sts.setCorrectCount(correctCount);
				sts.setIncorrectCount(incorrectCount);
				sts.setSkipCount(skipCount);
				sts.setPercentage(percentage);
				sts.setTotalQuestionAttempted(totalAttempt);
				sts.setTotalQuestionCount(questionCount);
			}
		}
		mav.addObject("studentReports", userList);
		return mav;
	}

	/** @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/schoolTeacher/downloadExcel", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getExcel(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=QuestionUpload.xlsx");
		String path = Utility.resourcesFolderPath;
		return new FileSystemResource(path + "/excelFormats/Question_Format.xlsx");
	}

	/** @param request
	 * @param response
	 * @param stuId
	 * @param assessmentId
	 * @param maxPer
	 * @param minPer
	 * @param avgPer
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/stuAssessmentSummary")
	public ModelAndView stuAssessmentSummary(HttpServletRequest request, HttpServletResponse response, @RequestParam Long stuId, @RequestParam Long assessmentId, @RequestParam float maxPer, @RequestParam float minPer, @RequestParam float avgPer/* , @RequestParam float yourPercentage */)
	{
		ModelAndView mav = new ModelAndView("/schoolTeacher/testDetail");

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		SchoolStudentTest sst = studentCommonService.getStudentAssessmentSummary(stuId, assessmentId);
		List<SchoolStudentTestDetail> detailList = new ArrayList<SchoolStudentTestDetail>();
		for (SchoolStudentTestDetail d : sst.getSchoolStudentTestDetail())
		{
			detailList.add(d);
		}
		Collections.sort(detailList, new Comparator<SchoolStudentTestDetail>()
		{

			@Override
			public int compare(SchoolStudentTestDetail o1, SchoolStudentTestDetail o2)
			{
				return o1.getId().compareTo(o2.getId());
			}
		});

		sst.setTestDeatail(detailList);
		Map<Long, Float> map = studentCommonService.getAssessmentQuestionMap(assessmentId);
		if (sst != null && sst.getTestDeatail() != null && map != null)
		{
			for (SchoolStudentTestDetail sstd : sst.getTestDeatail())
			{
				Float marks = map.get(sstd.getQuestionId());
				if (marks != null)
				{
					sstd.setQuestmarks(marks);
				}
			}
		}

		Double avgMarks = studentCommonService.getAssessmentAverageMarks(assessmentId);
		mav.addObject("avgMarks", avgMarks);
		mav.addObject("studentAssessmentSummary", sst);
		Object[] object = studentCommonService.getTopperAndMaxAttendAssessment(loginUser.getSchoolId(), assessmentId);
		mav.addObject("TotalStudentAttempt", object[1]);
		mav.addObject("TopperAssessmentMarks", object[0]);
		mav.addObject("minPer1", minPer);
		mav.addObject("maxPer1", maxPer);
		mav.addObject("avgPer1", avgPer);
		// mav.addObject("yourPercentage1", yourPercentage);
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/schoolTeacherProfile.htm", method = RequestMethod.GET)
	public ModelAndView studentProfile(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("schoolTeacher/teacherprofile");
		UserForm userObj = (UserForm) request.getSession().getAttribute("user");
		List<StudentTeacherSession> teacherGradeSubjectDetails = schoolTeacherService.getTeacherGradeSubjectDetails(userObj.getId(), userObj.getSchoolId());
		mav.addObject("teacherGradeSubjectDetails", teacherGradeSubjectDetails);
		// ${sts.section.sessionSchoolGrade.grade.name} (${sts.section.name})
		StringBuffer gradeSectionString = new StringBuffer();
		for (StudentTeacherSession gradeSection : teacherGradeSubjectDetails)
		{
			if (!gradeSectionString.toString().contains(gradeSection.getSection().getSessionSchoolGrade().getGrade().getName()))
			{
				gradeSectionString.append(gradeSection.getSection().getSessionSchoolGrade().getGrade().getName());
				gradeSectionString.append("(" + gradeSection.getSection().getName() + ")");
				gradeSectionString.append(",");
			}

		}
		mav.addObject("gradeSectionString", gradeSectionString.toString());
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/studenttestreport.htm", method = RequestMethod.GET)
	public ModelAndView studenttestreport(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("schoolTeacher/studenttestreport");
		return mav;
	}

	/** @param request
	 * @param response
	 * @param secId
	 * @param subId
	 * @param grade
	 * @param sec
	 * @param sub
	 * @param grdId
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/gradesubjectdetail.htm", method = RequestMethod.GET)
	public ModelAndView gradesubjectdetail(HttpServletRequest request, HttpServletResponse response, @RequestParam Long secId, @RequestParam Long subId, @RequestParam String grade, @RequestParam String sec, @RequestParam String sub, @RequestParam Long grdId)
	{
		ModelAndView mav = new ModelAndView("schoolTeacher/gradesubjectdetail");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<User> unassignStudentLst = schoolTeacherService.getUnassignStudent(subId, secId);

		mav.addObject("grade", grade);
		mav.addObject("subject", sub);
		mav.addObject("section", sec);
		mav.addObject("usl", unassignStudentLst);
		mav.addObject("subjectId", subId);
		mav.addObject("gradeId", grdId);
		mav.addObject("sectionId", secId);

		getSubjectProgressWithImage(subId, mav, loginUser, secId, grdId);

		// test report (upcoming ongoing and completed)

		List<Assessment> upcomingAssessment = schoolTeacherService.getUpcomingAssessment(loginUser, grdId, subId);
		List<Assessment> completedAssessment = schoolTeacherService.getCompletedAssessment(loginUser, grdId, subId);
		Date currentDate = null;
		boolean isCompleted = false;
		boolean isOngoing = false;
		if (completedAssessment != null && completedAssessment.size() > 0)
		{
			currentDate = Utility.getCurrentDate();
			for (Assessment as : completedAssessment)
			{
				int marks = 0;

				Date startDate = as.getStartDate();
				currentDate = Utility.getCurrentDate();

				if (startDate.getTime() <= currentDate.getTime() && as.getEndDate().getTime() > currentDate.getTime())
				{
					as.setFlag(1);
					isOngoing = true;

				}
				else
				{
					isCompleted = true;
					as.setFlag(2);
				}

				if (as.getStudentTest() != null && as.getStudentTest().size() > 0)
				{
					for (SchoolStudentTest sst : as.getStudentTest())
					{
						marks += sst.getMarks();
					}

					as.setAverage(marks / as.getStudentTest().size());
				}
				else
				{
					as.setAverage(0);
				}
			}
		}

		mav.addObject("isCompleted", isCompleted);
		mav.addObject("isOngoing", isOngoing);
		mav.addObject("upcomingAss", upcomingAssessment);
		mav.addObject("completedAss", completedAssessment);

		return mav;

	}

	/** @param subId
	 * @param mav
	 * @param loginUser
	 * @param secId
	 * @param gradeId */
	private void getSubjectProgressWithImage(Long subId, ModelAndView mav, UserForm loginUser, Long secId, Long gradeId)
	{
		Subject subObj = schoolTeacherService.getSubjectObjById(subId);
		if (subObj.getSubImage() != null)
		{
			mav.addObject("subImg", subObj.getSubImage());
		}
		List<Long> subjectIdslst = new ArrayList<Long>();
		subjectIdslst.add(subId);
		Map<Long, Integer> topicCountMap = schoolTeacherService.getTopicCountBySubjectId(subjectIdslst, gradeId);
		Map<Long, Integer> teacherTopicProgressMap = schoolTeacherService.getTeacherProgressTopicCountBySubjectId(subjectIdslst, loginUser.getId(), secId);
		if (teacherTopicProgressMap != null)
		{
			Integer teacherTopicCount = teacherTopicProgressMap.get(subId);
			if (teacherTopicCount != null)
			{
				Integer percentage = (int) ((teacherTopicCount * 100) / topicCountMap.get(subId));
				mav.addObject("subPercentage", percentage);
			}
			else
			{
				mav.addObject("subPercentage", 0);
			}
		}
		else
		{
			mav.addObject("subPercentage", 0);
		}

		List<StudentTeacherSession> teacherGradeSubjectDetails = schoolTeacherService.getTeacherGradeSubjectDetails(loginUser.getId(), loginUser.getSchoolId());

		mav.addObject("teacherGradeSubjectDetails", teacherGradeSubjectDetails);
	}

	/** @param request
	 * @param response
	 * @param grdId
	 * @param subId
	 * @param assesmentId
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/getTestWiseReport", method = RequestMethod.GET)
	public ModelAndView getTestReport(HttpServletRequest request, HttpServletResponse response, @RequestParam Long grdId, @RequestParam Long subId, @RequestParam Long assesmentId)
	{
		ModelAndView mav = new ModelAndView("schoolTeacher/testWiseReport");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		Assessment getTestDetail = schoolTeacherService.getAllTestDetails(loginUser, grdId, subId, assesmentId);

		Assessment unattemptedTest = schoolTeacherService.getUnattemtedAllTestDetails(loginUser, grdId, subId, assesmentId);
		ArrayList<Float> sort = new ArrayList<Float>();
		ArrayList<Float> mrks = new ArrayList<Float>();
		List<SchoolStudentTest> zone1 = new ArrayList<>();

		List<SchoolStudentTest> zone2 = new ArrayList<>();
		List<SchoolStudentTest> zone3 = new ArrayList<>();
		List<SchoolStudentTest> zone4 = new ArrayList<>();
		List<SchoolStudentTest> zone5 = new ArrayList<>();
		List<SchoolStudentTest> zone6 = new ArrayList<>();

		List<SchoolStudentTest> nameStudent = new ArrayList<>();

		float minPer = 0;
		float maxPer = 0;
		float avgPer = 0;
		float sumPer = 0;
		float percentage = 0;

		int marks = 0;
		float max = 0;
		float min = 0;

		if (getTestDetail == null)
		{

			mav.addObject("notfound", 0);

		}

		else
		{

			if (getTestDetail.getStudentTest() != null && getTestDetail.getStudentTest().size() > 0)
			{
				for (SchoolStudentTest st : getTestDetail.getStudentTest())
				{

					percentage = (st.getMarks() / st.getAssessment().getMaxMarks()) * 100;

					mrks.add(percentage);
					sumPer = sumPer + percentage;

				}

				if (mrks != null)
				{
					Collections.sort(mrks);
					minPer = (float) mrks.get(0);

					maxPer = (float) mrks.get(mrks.size() - 1);

					avgPer = (float) sumPer / getTestDetail.getStudentTest().size();

				}
				else
				{

					minPer = 0;
					maxPer = 0;
					avgPer = 0;
				}

				for (SchoolStudentTest sst : getTestDetail.getStudentTest())
				{
					sort.add(sst.getMarks());
					marks += sst.getMarks();
				}
				Collections.sort(sort);
				max = sort.get(sort.size() - 1);
				min = sort.get(0);

				getTestDetail.setAverage(marks / getTestDetail.getStudentTest().size());
			}

			else
			{

				getTestDetail.setAverage(0);
				mav.addObject("notfound", 0);
			}

			for (SchoolStudentTest as : getTestDetail.getStudentTest())
			{

				if (as.getPercentage() >= 0 && as.getPercentage() <= 30)
				{
					zone1.add(as);

				}
				else if (as.getPercentage() >= 31 && as.getPercentage() <= 45)
				{
					zone2.add(as);

				}
				else if (as.getPercentage() >= 46 && as.getPercentage() <= 60)
				{
					zone3.add(as);

				}
				else if (as.getPercentage() >= 61 && as.getPercentage() <= 75)
				{
					zone4.add(as);

				}
				else if (as.getPercentage() >= 76 && as.getPercentage() <= 90)
				{
					zone5.add(as);

				}
				else if (as.getPercentage() >= 91 && as.getPercentage() <= 100)
				{
					zone6.add(as);

				}
				else
				{
					System.out.println("Invalid data");
				}

			}
			mav.addObject("getTestDetail", getTestDetail);
			mav.addObject("zone1", zone1);
			mav.addObject("zone2", zone2);
			mav.addObject("zone3", zone3);
			mav.addObject("zone4", zone4);
			mav.addObject("zone5", zone5);
			mav.addObject("zone6", zone6);
			mav.addObject("maxMarks", max);
			mav.addObject("minMarks", min);
			mav.addObject("minPer", minPer);
			mav.addObject("maxPer", maxPer);
			mav.addObject("avgPer", avgPer);
			mav.addObject("yourPercentage" + percentage);

		}

		if (getTestDetail != null && getTestDetail.getStudentTest() != null && getTestDetail.getStudentTest().size() > 0)
		{
			Set<AssignedAssessment> setass = getTestDetail.getAssignedAssessment();

			Set<SchoolStudentTest> testdetail = getTestDetail.getStudentTest();

			List<Long> actuallistpresent = new ArrayList<Long>();

			List<AssignedAssessment> actuallistabsent = new ArrayList<AssignedAssessment>();

			for (SchoolStudentTest sst : testdetail)
			{
				actuallistpresent.add(sst.getUser().getId());
			}

			if (getTestDetail.getAssignedAssessment() != null && getTestDetail.getAssignedAssessment().size() > 0)
			{
				for (AssignedAssessment ypp : setass)
				{
					if (actuallistpresent.contains(ypp.getUserId()))
					{

					}
					else
					{
						actuallistabsent.add(ypp);
					}

				}

			}

			mav.addObject("actuallistabsent", actuallistabsent);
		}

		return mav;

	}

	/** @param request
	 * @param response
	 * @param fileBucket
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolTeacher/singleSave", method = RequestMethod.POST)
	public ModelAndView singleFileUpload(HttpServletRequest request, HttpServletResponse response, @Validated FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException
	{
		UserForm studentData = (UserForm) request.getSession().getAttribute("user");
		// String sAdmissionNo = studentData.getAdmissionNo();
		long sId = studentData.getId();
		String realname = fileBucket.getFile().getOriginalFilename();
		String imageName = null;
		if (realname.contains(".png"))
		{
			imageName = "" + sId + ".png";
		}
		else if (realname.contains(".gif"))
		{
			imageName = "" + sId + ".gif";
		}
		else if (realname.contains(".jpg"))
		{
			imageName = "" + sId + ".jpg";
		}
		else if (realname.contains(".jpeg"))
		{
			imageName = "" + sId + ".jpg";
		}
		String profilePath = Utility.profilePath;
		if (result.hasErrors())
		{

			return null;
		}
		else
		{

			MultipartFile multipartFile = fileBucket.getFile();
			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(profilePath + imageName));
			String saveOrUpdateStatus = studentCommonService.saveOrUpdateProfileImg(sId, imageName);
			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			UserForm uf = (UserForm) request.getSession().getAttribute("user");
			uf.setProfilePath(imageName);
			request.getSession().setAttribute("user", uf);
			response.sendRedirect(request.getContextPath() + "/schoolTeacher/schoolTeacherProfile.htm");
			return null;
		}
	}

	/** @param user
	 * @param request
	 * @param response
	 * @param fileBucket
	 * @return
	 * @throws IOException */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/updateStudentPersonalInformation", method = RequestMethod.POST)
	public ModelAndView updateStudentPersonalInformation(@ModelAttribute("updatePInformation") User user, HttpServletRequest request, HttpServletResponse response, @Validated FileBucket fileBucket) throws IOException
	{
		User userObj = studentCommonService.getUserObjById(((UserForm) request.getSession().getAttribute("user")).getId());
		userObj.setFirstName(user.getFirstName());
		userObj.setDateOfBirth(user.getDateOfBirth());
		userService.SaveOrUpdate(userObj);
		UserForm uf = (UserForm) request.getSession().getAttribute("user");
		uf.setName(user.getFirstName());
		uf.setStudentDob(user.getDateOfBirth());
		request.getSession().setAttribute("user", uf);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/schoolTeacherProfile.htm");
		return null;
	}

	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/updateContactInformation", method = RequestMethod.POST)
	public ModelAndView updateContactInformation(@ModelAttribute("updateContactInformation") User user, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		User userObj = studentCommonService.getUserObjById(((UserForm) request.getSession().getAttribute("user")).getId());
		userObj.setEmailId(user.getEmailId());
		userObj.setMobileNo(user.getMobileNo());
		userObj.setAddress(user.getAddress());
		userObj.setAddressTwo(user.getAddressTwo());
		userObj.setCity(user.getCity());
		userObj.setPinCode(user.getPinCode());
		userObj.setState(user.getState());
		userService.SaveOrUpdate(userObj);
		UserForm uf = (UserForm) request.getSession().getAttribute("user");
		uf.setMobileNo(user.getMobileNo());
		uf.setEmail(user.getEmailId());
		uf.setAddress(user.getAddress());
		uf.setAddressTwo(user.getAddressTwo());
		uf.setCity(user.getCity());
		uf.setPinCode(user.getPinCode());
		uf.setState(user.getState());
		request.getSession().setAttribute("user", uf);
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/schoolTeacherProfile.htm");
		return null;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/teacherHome.htm")
	public ModelAndView updateContactInformation(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm userObj = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("schoolTeacher/teacherHome");
		// User userObj = studentCommonService.getUserObjById(loginUser.getId());
		List<StudentTeacherSession> teacherGradeSubjectDetails = schoolTeacherService.getTeacherGradeSubjectDetails(userObj.getId(), userObj.getSchoolId());

		Set<Long> sectionIdsSet = new HashSet<Long>();
		Set<Long> subjectIdsSet = new HashSet<Long>();
		if (!teacherGradeSubjectDetails.isEmpty())
		{
			for (StudentTeacherSession sts : teacherGradeSubjectDetails)
			{
				sectionIdsSet.add(sts.getSectionId());
				subjectIdsSet.add(sts.getSubjectId());
			}
			List<Long> subIds = new ArrayList<Long>(subjectIdsSet);
			Map<Long, Integer> stuCount = schoolTeacherService.getStudentCountBySectionIds(new ArrayList<Long>(sectionIdsSet), userObj.getSchoolId(), subIds);

			for (StudentTeacherSession sts : teacherGradeSubjectDetails)
			{
				Integer count = null;
				if (stuCount != null)
				{
					count = stuCount.get(sts.getSectionId());
				}

				if (count != null)
				{
					sts.setUserCount(count);

				}
				else
				{
					sts.setUserCount(0);
				}
				List<Long> subjectIdslst = new ArrayList<Long>();
				subjectIdslst.add(sts.getSubjectId());
				Map<Long, Integer> topicCountMap = schoolTeacherService.getTopicCountBySubjectId(subjectIdslst, sts.getSection().getSessionSchoolGrade().getGradeId());
				Map<Long, Integer> teacherTopicProgressMap = schoolTeacherService.getTeacherProgressTopicCountBySubjectId(subjectIdslst, userObj.getId(), sts.getSectionId());
				if (teacherTopicProgressMap != null)
				{
					Integer teacherTopicCount = teacherTopicProgressMap.get(sts.getSubjectId());
					if (teacherTopicCount != null)
					{
						Integer percentage = (int) ((teacherTopicCount * 100) / topicCountMap.get(sts.getSubjectId()));
						sts.setPercentage(percentage);

					}
					else
					{
						sts.setPercentage(0);
					}
				}
				else
				{
					sts.setPercentage(0);
				}

			}
		}
		mav.addObject("teacherGradeSubjectDetails", teacherGradeSubjectDetails);

		getTeacherAllAssessmentDetails(userObj, mav);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param grdId
	 * @param subjctId
	 * @param secId
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/learningReport", method = RequestMethod.GET)
	public ModelAndView learningReport1(HttpServletRequest request, HttpServletResponse response, @RequestParam Long grdId, @RequestParam Long subjctId, @RequestParam Long secId)
	{
		ModelAndView mv = new ModelAndView("schoolTeacher/gradeSubjectData");

		List<Chapter> chapterList = schoolTeacherService.getChpater(grdId, subjctId);
		List<Long> topicIdList = new ArrayList<Long>();
		if (chapterList != null && chapterList.size() > 0)
		{
			for (Chapter c : chapterList)
			{
				for (Topic t : c.getTopics())
				{
					topicIdList.add(t.getId());
				}
			}

			Map<Long, Integer> questCount = schoolTeacherService.getQuestionCountByTopicId(topicIdList);

			for (Chapter ss : chapterList)
			{

				ss.setSubject(null);
				Integer chapCount = 0;
				for (Topic top : ss.getTopics())
				{

					top.setQuestionTopic(null);
					top.setUserQuestionAnswer(null);
					Integer questioncount = questCount.get(top.getId());

					if (questioncount != null)
					{
						top.setCount(questioncount);
						chapCount += questioncount;
					}
					else
					{
						top.setCount(0);
					}

				}
				ss.setCount(chapCount);
			}

		}

		mv.addObject("chapterlst", chapterList);
		mv.addObject("subjectId", subjctId);
		mv.addObject("sectionId", secId);
		mv.addObject("idGrade", grdId);
		mv.addObject("idSubject", subjctId);

		return mv;

	}

	/** @param loginUser
	 * @param mav */

	private void getTeacherAllAssessmentDetails(UserForm loginUser, ModelAndView mav)
	{

		List<Assessment> upcomingTestList = schoolTeacherService.getUpcomingAssessmentDetails(loginUser);

		mav.addObject("upcomingTestList", upcomingTestList);
		List<Assessment> completedTest = schoolTeacherService.getCompletedTest(loginUser);

		Assessment recentTest = null;
		Date currentDate = null;
		boolean isCompleted = false;
		boolean isOngoing = false;
		if (completedTest != null && completedTest.size() > 0)
		{
			currentDate = Utility.getCurrentDate();
			Boolean flag = true;
			for (Assessment as : completedTest)
			{
				// int marks = 0;

				Date startDate = as.getStartDate();
				currentDate = Utility.getCurrentDate();

				if (startDate.getTime() <= currentDate.getTime() && as.getEndDate().getTime() > currentDate.getTime())
				{
					as.setFlag(1);
					isOngoing = true;
				}
				else
				{

					if (flag)
					{

						flag = false;
						recentTest = as;

					}
					isCompleted = true;
					as.setFlag(2);
				}

			}
		}
		mav.addObject("isCompleted", isCompleted);
		mav.addObject("isOngoing", isOngoing);
		mav.addObject("completedTest", completedTest);
		mav.addObject("currDate", Utility.getCurrentDate());

		if (recentTest != null)
		{

			List<SchoolStudentTest> studentAssessmentDetails = schoolTeacherService.getStudentAssessmentDetailsById(recentTest.getId());

			List<SchoolStudentTest> highestScore = new ArrayList<SchoolStudentTest>();
			List<SchoolStudentTest> lowScore = new ArrayList<SchoolStudentTest>();
			List<SchoolStudentTest> middelPercentage = new ArrayList<SchoolStudentTest>();
			if (studentAssessmentDetails != null && studentAssessmentDetails.size() >= 3)
			{
				highestScore.addAll(studentAssessmentDetails.subList(0, 3));
				mav.addObject("highestScoreGraph", highestScore.get(0).getPercentage());
				if (studentAssessmentDetails.size() >= 6)
				{
					lowScore.addAll(studentAssessmentDetails.subList(studentAssessmentDetails.size() - 3, studentAssessmentDetails.size()));
					mav.addObject("lowScoreGraph", lowScore.get(lowScore.size() - 1).getPercentage());
				}
				middelPercentage = studentAssessmentDetails;
				float averageAdd = 0f;
				for (SchoolStudentTest middelPercentageFor : middelPercentage)
				{
					averageAdd = averageAdd + middelPercentageFor.getPercentage();
				}
				Double avgPercentage = (double) (averageAdd / studentAssessmentDetails.size());
				mav.addObject("middelPercentageScoreGraph", Utility.getDecimalFormat(avgPercentage, 2));
				mav.addObject("highestScore", highestScore);
				mav.addObject("lowestScore", lowScore);
			}
			else
			{
				if (highestScore == null || highestScore.size() <= 3)
				{

					mav.addObject("highestScoreGraph", highestScore);
				}

				if (studentAssessmentDetails == null || studentAssessmentDetails.size() <= 6)
				{
					mav.addObject("lowScoreGraph", lowScore);
				}
				float averageAdd = 0f;
				mav.addObject("middelPercentageScoreGraph", averageAdd);

				mav.addObject("highestScore", highestScore);
				mav.addObject("lowestScore", lowScore);
			}

		}

	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/getLastAssessmentReport")
	public ModelAndView getLastAssessmentReport(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView();

		return view;

	}

	/** @author amresh */

	class ValueComparator implements Comparator<String>
	{
		Map<String, Double> base;

		public ValueComparator(Map<String, Double> base)
		{
			this.base = base;
		}

		public int compare(String a, String b)
		{
			if (base.get(a) >= base.get(b))
			{
				return -1;
			}
			else
			{
				return 1;
			} // returning 0 would merge keys
		}
	}

	/** @param request
	 * @param response
	 * @param globalVO
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/updateModule.htm")
	@LoginAuthentication
	public void updateModule(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("globalVO") GlobalVO globalVO) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		globalVO.setSchoolId(loginUser.getSchoolId());
		globalVO.setCreatedBy(loginUser.getId());

		schoolTeacherService.updateModuleResources(globalVO);
		request.getSession().setAttribute("flagModule", 1);
		// response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId()+ "&subjectName=" + request.getParameter("subjectName") + "&chapName=" + request.getParameter("chapName") + "&topicName=" + request.getParameter("topicName"));
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getManageTocModule.htm?topicId=" + globalVO.getTopicId() + "&secId=" + globalVO.getSecId() + "&subId=" + globalVO.getSubId() + "&grdId=" + globalVO.getGrdId() + "&flagQorM=" + flagModule);
	}

	/** @param request
	 * @param response
	 * @param search
	 * @param grdId
	 * @param subId
	 * @param secId
	 * @param grade
	 * @param subject
	 * @param section
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/searchStudents", method = RequestMethod.GET)
	public ModelAndView searchStudentsAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam String search, @RequestParam Long grdId, @RequestParam Long subId, @RequestParam Long secId, @RequestParam String grade, @RequestParam String subject, @RequestParam String section)
	{
		ModelAndView mav = new ModelAndView("/schoolTeacher/studentWiseReport");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<User> getAllAssessment = schoolTeacherService.getStudentCompletedAssessment(loginUser, grdId, subId, search, secId);

		mav.addObject("userDetail", getAllAssessment);
		mav.addObject("name" + search);
		mav.addObject("gradeId", grdId);
		mav.addObject("subjectId", subId);
		mav.addObject("sectionId", secId);
		mav.addObject("gradename", grade);
		mav.addObject("subjectname", subject);
		mav.addObject("sectionname", section);
		return mav;

	}

	/** @param request
	 * @param response
	 * @param studentId
	 * @param grdId
	 * @param subId
	 * @param secId
	 * @return */
	@ResponseBody
	@LoginAuthentication
	@RequestMapping(value = "/schoolTeacher/searchStudentTestDetail")
	public List<SchoolStudentTest> searchStudentTestDetail(HttpServletRequest request, HttpServletResponse response, Long studentId, Long grdId, Long subId, Long secId)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<SchoolStudentTest> searchDetails = schoolTeacherService.searchStudentTestDetail(loginUser, grdId, subId, secId, studentId);
		if (!searchDetails.isEmpty())
		{
			for (SchoolStudentTest sst : searchDetails)
			{
				Assessment asst = sst.getAssessment();
				/* asst.setAssessmentQuestion(null); asst.setAssignedAssessment(null); asst.setStudentTest(null); */

				if (sst.getAssessment().getAssessmentTopicChapterDetails() != null)
				{
					asst.getAssessmentTopicChapterDetails().setAssessment(null);
					String chapterIds = asst.getAssessmentTopicChapterDetails().getChapterId();
					String topicIds = asst.getAssessmentTopicChapterDetails().getTopicId();
					sst.getAssessment().setChapCount(chapterIds.split("\\,").length);
					sst.getAssessment().setTopicCount(topicIds.split("\\,").length);
					List<String> chapDetails = schoolTeacherService.getChapterNamesByChapIds(chapterIds);
					sst.getAssessment().setChapterIds(StringUtils.join(chapDetails, ',').replaceAll("\\,", "\\, "));
					List<String> topicDetails = schoolTeacherService.getTopicNamesbyIds(topicIds);
					sst.getAssessment().setTopicIds(StringUtils.join(topicDetails, ',').replaceAll("\\,", "\\, "));

				}
			}

		}
		return searchDetails;

	}

	/** @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/updateAssessmentByTeacher.htm")
	@LoginAuthentication
	public ModelAndView updateAssessmentByTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Assessment ass = new Assessment();
		ass.setTestName(request.getParameter("assessmentName"));
		Long grdId = Long.parseLong(request.getParameter("grdId"));
		Long subId = Long.parseLong(request.getParameter("subId"));
		Long secId = Long.parseLong(request.getParameter("secId"));
		/* String sec=request.getParameter("sec"); String sub=request.getParameter("sub"); String grd=request.getParameter("grd"); */

		ass.setId(Long.parseLong(request.getParameter("assessmentId")));
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);
		try
		{
			Date sDate = format.parse(request.getParameter("startDate"));
			Date eDate = format.parse(request.getParameter("endDate"));
			ass.setStartDate(sDate);
			ass.setEndDate(eDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		schoolTeacherService.updateAssessment(ass);

		request.getSession().setAttribute("tab123", "mTest");
		response.sendRedirect(request.getContextPath() + "/schoolTeacher/getCreateAssessment.htm?subId=" + subId + "&grdId=" + grdId + "&secId=" + secId);
		return null;
	}

	/** @param request
	 * @param response
	 * @param totalQuestion
	 * @param createdBy
	 * @param eQuest
	 * @param mQuest
	 * @param dQuest
	 * @param gradeName
	 * @param subName
	 * @param gradeId
	 * @param subId
	 * @param secId
	 * @param instruction
	 * @return
	 * @throws IOException */
	@RequestMapping("/schoolTeacher/autoPreviewAssessment.htm")
	@LoginAuthentication
	public ModelAndView autoPreviewAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "totalQues") Long totalQuestion, @RequestParam(value = "createdBy") String createdBy, @RequestParam(value = "eQuest") Integer eQuest, @RequestParam(value = "mQuest") Integer mQuest, @RequestParam(value = "dQuest") Integer dQuest, @RequestParam(value = "grade") String gradeName, @RequestParam(value = "sub") String subName, @RequestParam(value = "gradeId") String gradeId, @RequestParam(value = "subId") String subId, @RequestParam(value = "secId") String secId,

			@RequestParam(value = "instruction") String instruction) throws IOException
	{

		ModelAndView mav = new ModelAndView("/schoolTeacher/autoPreviewQuestionList");

		String eMarks = request.getParameter("eMarks");
		String dMarks = request.getParameter("dMarks");
		String iMarks = request.getParameter("iMarks");

		int createdById = 0;
		String[] ab = createdBy.split(",");
		for (String a : ab)
		{
			int j = Integer.parseInt(a);
			createdById = createdById + j;
		}

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		String topicIds = request.getParameter("topicIds");
		List<String> result = getTopicIds(topicIds, loginUser.getId(), totalQuestion, loginUser.getSchoolId(), createdById, eQuest, mQuest, dQuest);

		// List<String> result = schoolTeacherService.getQuestionListforAuto(topicIds, loginUser.getId(), totalQuestion, loginUser.getSchoolId(), createdById, eQuest, mQuest, dQuest);

		if (result.get(0).contains("Only"))
		{

			mav.addObject("false", "false");
			return mav;// result.get(0);

		}
		else
		{

			List<QuestionTopic> questionAns = schoolTeacherService.autoPreviewAssessment(result, topicIds, loginUser.getId(), loginUser.getSchoolId());
			int totalCount = 0, easyCount = 0, intermediateCount = 0, hardCount = 0;
			totalCount = questionAns.size();
			for (QuestionTopic q : questionAns)
			{
				if (q.getDifficultyLevelId().equals(1l))
				{

					easyCount++;
				}
				else if (q.getDifficultyLevelId().equals(2l))
				{
					intermediateCount++;
				}
				else if (q.getDifficultyLevelId().equals(3l))
				{
					hardCount++;
				}

			}

			easyCount = (easyCount * 100) / totalCount;
			intermediateCount = (intermediateCount * 100) / totalCount;
			hardCount = (hardCount * 100) / totalCount;

			mav.addObject("questionList", questionAns);
			mav.addObject("ins", instruction);
			mav.addObject("eMarks", eMarks);
			mav.addObject("dMarks", dMarks);
			mav.addObject("iMarks", iMarks);
			mav.addObject("gradeName", gradeName);
			mav.addObject("subName", subName);
			mav.addObject("gradeId", gradeId);
			mav.addObject("subId", subId);
			mav.addObject("secId", secId);
			mav.addObject("totalCount", totalCount);
			mav.addObject("easyCount", easyCount);
			mav.addObject("intermediateCount", intermediateCount);
			mav.addObject("hardCount", hardCount);

			return mav;
		}
	}

	/** @param topicIds
	 * @param userId
	 * @param totalQuestion
	 * @param schoolId
	 * @param createdById
	 * @param eQuest
	 * @param mQuest
	 * @param dQuest
	 * @return */
	private List<String> getTopicIds(String topicIds, long userId, Long totalQuestion, Long schoolId, int createdById, Integer eQuest, Integer mQuest, Integer dQuest)
	{
		List<String> result = schoolTeacherService.getQuestionListforAuto(topicIds, userId, totalQuestion, schoolId, createdById, eQuest, mQuest, dQuest);
		Integer size = new HashSet(result).size();
		if (!size.equals(totalQuestion.intValue()))
		{

			result = getTopicIds(topicIds, userId, totalQuestion, schoolId, createdById, eQuest, mQuest, dQuest);
		}

		return result;
	}

	/** @param request
	 * @param response
	 * @param result
	 * @param totalQuestion
	 * @param testName
	 * @param testType
	 * @param startDate
	 * @param endDate
	 * @param totalMarks
	 * @param instruction
	 * @param topicIds
	 * @param duration
	 * @param chapterIds
	 * @param assStudentlst
	 * @param gradeId
	 * @param subjectId
	 * @param secId
	 * @return */
	@RequestMapping("/schoolTeacher/createAutoTest")
	@ResponseBody
	public String createAutoTest(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "result") String result, @RequestParam(value = "totalQus") Long totalQuestion, @RequestParam(value = "testName") String testName, @RequestParam(value = "testType") Integer testType, @RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate, @RequestParam(value = "totalMarks") Integer totalMarks, @RequestParam(value = "instruction") String instruction, @RequestParam(value = "topicIds") String topicIds, @RequestParam(value = "duration") Integer duration, @RequestParam(value = "chapterIds") String chapterIds, @RequestParam(value = "assStudentlst") String assStudentlst, @RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId, @RequestParam(value = "secId") Long secId)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		request.getSession().setAttribute("tab420", "mTest");
		String satusQ = schoolTeacherService.addAutoCreateAssessment(result, testName, testType, startDate, endDate, gradeId, subjectId, totalQuestion, duration, instruction, loginUser.getSchoolId(), loginUser.getId(), loginUser.getSessionId(), totalMarks, assStudentlst, secId, chapterIds, topicIds);

		sendTestCreationEmails(assStudentlst, loginUser);

		return satusQ;

	}

	/** @param request
	 * @param response
	 * @param assmtId
	 * @param subId
	 * @param grdId
	 * @return */
	@RequestMapping("/schoolTeacher/removeAssmt")
	@ResponseBody
	public String removeAssmt(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "assmtId") Long assmtId, @RequestParam(value = "subId") Long subId, @RequestParam(value = "grdId") Long grdId)
	{
		String msg = "";
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		request.getSession().setAttribute("tab420", "mTest");
		msg = schoolTeacherService.removeAssmt(assmtId, subId, grdId, loginUser.getId(), loginUser.getSchoolId());
		request.getSession().setAttribute("removeAsst", "mTest");
		return msg;

	}

	/** @param assStudentlst
	 * @param loginUser */

	private void sendTestCreationEmails(String assStudentlst, UserForm loginUser)
	{

		// emailSender for all student
		String[] sIds = assStudentlst.split(",");

		List<Long> t_allStudent_id = schoolTeacherUtil.convertStringArrayToList(sIds);
		List<User> userDetailsForEmail = schoolTeacherService.getUserDetailForEmail(t_allStudent_id, User.USERTYPE_STUDENT);
		mailSenderUtil.sendMultipleMails(userDetailsForEmail, "createAutoTestStudent", "createAutoTest");
		///////

		// emailSenderForTeacher
		mailSenderUtil.singleEmailSender(loginUser.getEmail(), "createAutoTestTeacher", "createAutoTestTeacher");
		// emailSender to school admin
		mailSenderUtil.singleEmailSender(loginUser.getEmail(), "createAutoTestAdmin", "createAutoTestAdmin");

	}

}