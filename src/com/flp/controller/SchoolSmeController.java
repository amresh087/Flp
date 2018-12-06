package com.flp.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.flp.model.Chapter;
import com.flp.model.ModuleTopic;
import com.flp.model.ModuleType;
import com.flp.model.QuestionType;
import com.flp.model.SmeGradeSubject;
import com.flp.model.SubjectSection;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.service.AdminService;
import com.flp.service.SchoolSmeService;
import com.flp.service.SchoolTeacherService;
import com.flp.util.Utility;
import com.flp.vo.GlobalVO;

/** @author Amresh */
@Controller
public class SchoolSmeController
{
	@Autowired
	AdminService adminService;

	@Autowired
	private SchoolSmeService schoolSmeService;

	@Autowired
	SchoolTeacherService schoolTeacherService;

	@LoginAuthentication
	@RequestMapping(value = "/sme/getSmeHome.htm")
	public ModelAndView getSmeHome(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("superAdminheader");
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/sme/getChapterPage.htm")
	public ModelAndView getChapterTocPage(HttpServletRequest request, HttpServletResponse response)
	{
		List<SubjectSection> lst = schoolSmeService.getUniqueSubjectsByGradeId(161l, ((UserForm) request.getSession().getAttribute("user")).getSchoolId());
		
		
		ModelAndView mav = new ModelAndView("sme/chapter");
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("gradeList", schoolSmeService.getAssignedGrades(userForm.getSessionId(), userForm.getId(), SmeGradeSubject.STATUS_ACTIVE));
		return mav;
	}

	/** @param gradeId
	 * @param subjectId
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/sme/getChapterDataPage/{gradeId}/{subjectId}.htm")
	public ModelAndView getChapterDataTocPage(@PathVariable(value = "gradeId") Long gradeId, @PathVariable(value = "subjectId") Long subjectId, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/chapterData");
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");

		mav.addObject("chapterList", adminService.getChapters(String.valueOf(subjectId), gradeId, Chapter.STATUS_ACTIVE));
		return mav;
	}

	/** @param gradeId
	 * @param subjectId
	 * @param chapterName
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping(value = "/sme/addChapter.htm", method = RequestMethod.POST)
	public String addChapterTocStandard(@RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId, @RequestParam(value = "chapterName") String chapterName, HttpServletRequest request, HttpServletResponse response)
	{
		String message = "success";

		UserForm userForm = (UserForm) request.getSession().getAttribute("user");

		if (userForm.getSchoolId() == null)
			return "noSchool";
		else if (gradeId == null)
			return "noGrade";
		else if (subjectId == null)
			return "noSubject";
		else if (chapterName.trim().equals(""))
			return "noChapter";
		else if (adminService.checkChapterAvailability(gradeId, subjectId, Chapter.STATUS_ACTIVE, chapterName.trim()) == false)
			return "chapterNotAvailable";

		Chapter chapter = new Chapter();
		chapter.setSchoolId(userForm.getSchoolId());
		chapter.setGradeId(gradeId);
		chapter.setSubjectId(subjectId);
		chapter.setBoardId(null);
		chapter.setName(chapterName.trim());
		chapter.setModifiedDate(new Date());
		chapter.setSerialOrder(adminService.getMaxOrderChapter(subjectId, gradeId, null, userForm.getSchoolId()));
		chapter.setStatus(Chapter.STATUS_ACTIVE);

		message = String.valueOf(adminService.addChapter(chapter).getId());
		return message;
	}

	/** @param chapterIdString
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping(value = "/sme/removeChapter.htm", method = RequestMethod.POST)
	public String removeChapterTocStandard(@RequestParam(value = "chapterIdString") String chapterIdString, HttpServletRequest request, HttpServletResponse response)
	{
		String message = "success";

		if (chapterIdString.trim().equals(""))
			return "noChapter";

		adminService.updateChapter(chapterIdString, Chapter.STATUS_INACTIVE);

		return message;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/sme/getTopicPage.htm")
	public ModelAndView getTopicTocStandardPage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/topic");
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("gradeList", schoolSmeService.getAssignedGrades(userForm.getSessionId(), userForm.getId(), SmeGradeSubject.STATUS_ACTIVE));
		return mav;
	}

	/** @param chapterId
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/sme/getTopicDataPage/{chapterId}.htm")
	public ModelAndView getTopicTocDataStandardPage(@PathVariable(value = "chapterId") Long chapterId, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/topicData");
		mav.addObject("topicList", adminService.getTopics(String.valueOf(chapterId), Topic.STATUS_ACTIVE));
		return mav;
	}

	/** @param gradeId
	 * @param subjectId
	 * @param chapterId
	 * @param topicName
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping(value = "/sme/addTopic.htm", method = RequestMethod.POST)
	public String addTopicTocStandard(@RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "subjectId") Long subjectId, @RequestParam(value = "chapterId") Long chapterId, @RequestParam(value = "topicName") String topicName, HttpServletRequest request, HttpServletResponse response)
	{
		String message = "success";

		UserForm userForm = (UserForm) request.getSession().getAttribute("user");

		if (userForm.getSchoolId() == null)
			return "noSchool";
		else if (gradeId == null)
			return "noGrade";
		else if (subjectId == null)
			return "noSubject";
		else if (chapterId == null)
			return "noChapter";
		else if (topicName.trim().equals(""))
			return "noTopic";
		else if (adminService.checkTopicAvailability(chapterId, Topic.STATUS_ACTIVE, topicName.trim()) == false)
			return "topicNotAvailable";

		Topic topic = new Topic();
		topic.setBoardId(null);
		topic.setGradeId(gradeId);
		topic.setSubjectId(subjectId);
		topic.setSchoolId(userForm.getSchoolId());
		topic.setChapterId(chapterId);
		// topic.setNameUTF8(Utility.convertStringToByteUTF8(topicName.trim()));
		topic.setName(topicName.trim());
		topic.setModifiedDate(new Date());
		topic.setSerialOrder(adminService.getMaxOrderTopic(chapterId));
		topic.setStatus(Topic.STATUS_ACTIVE);

		message = String.valueOf(adminService.addTopic(topic).getId());

		return message;
	}

	/** @param topicIdString
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping(value = "/sme/removeTopic.htm", method = RequestMethod.POST)
	public String removeTopicTocStandard(@RequestParam(value = "topicIdString") String topicIdString, HttpServletRequest request, HttpServletResponse response)
	{
		String message = "success";

		if (topicIdString.trim().equals(""))
			return "noTopic";

		adminService.updateTopic(topicIdString, Topic.STATUS_INACTIVE);

		return message;
	}

	/** @param request
	 * @param response
	 * @return */

	@LoginAuthentication
	@RequestMapping("/sme/questionPage.htm")
	public ModelAndView questionPage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/question");
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("gradeList", schoolSmeService.getAssignedGrades(userForm.getSessionId(), userForm.getId(), SmeGradeSubject.STATUS_ACTIVE));

		return mav;
	}

	@LoginAuthentication
	@RequestMapping("/sme/questionDataPage/{topicId}.htm")
	public ModelAndView questionDataPage(@PathVariable(value = "topicId") Long topicId, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/questionData");
		// mav.addObject("sectionList", adminService.getSections(null,
		// Board.BOARD_CTLS, null, Section.STATUS_ACTIVE, null));
		mav.addObject("questionTopic", schoolTeacherService.getAllQuestionsByTopic(topicId));

		String propertyDirectoryString = Utility.getValueFromPropertyFile("flp.qa.images.retrieve");
		String questionResourceDirectory = (propertyDirectoryString.charAt(propertyDirectoryString.length() - 1) != '/' ? propertyDirectoryString + "/" : propertyDirectoryString); // "D:/FLP_RESOURCES/MODULES";

		GlobalVO globalVO = new GlobalVO();
		globalVO.setQuestionTypeList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_TYPE, QuestionType.STATUS_ACTIVE));
		globalVO.setQuestionDifficultyLevelList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_DIFFICULTY, QuestionType.STATUS_ACTIVE));
		globalVO.setQuestionCognitiveLevelList(schoolTeacherService.getQuestionType(QuestionType.QUESTION_COGNITIVE, QuestionType.STATUS_ACTIVE));
		globalVO.setTopicId(topicId);
		globalVO.setLoginUserType(User.USERTYPE_SCHOOL_SME);
		globalVO.setQuestionResourceDirectory(questionResourceDirectory);
		mav.addObject("globalVO", globalVO);

		return mav;
	}

	/** @param globalVO
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping("/sme/uploadQuestion.htm")
	public String uploadQuestion(@ModelAttribute(value = "globalVO") GlobalVO globalVO, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String message = "success";

		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		globalVO.setSchoolId(userForm.getSchoolId());

		message = schoolTeacherService.saveUpdateQuestion(globalVO);

		return message;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping("/sme/modulePage.htm")
	public ModelAndView modulePage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/module");
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("gradeList", schoolSmeService.getAssignedGrades(userForm.getSessionId(), userForm.getId(), SmeGradeSubject.STATUS_ACTIVE));

		return mav;
	}

	/** @param topicId
	 * @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping("/sme/moduleDataPage/{topicId}.htm")
	public ModelAndView moduleDataPage(@PathVariable(value = "topicId") Long topicId, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("sme/moduleData");

		String propertyDirectoryString = Utility.getValueFromPropertyFile("flp.modules.retrieve");
		String moduleResourceDirectory = (propertyDirectoryString.charAt(propertyDirectoryString.length() - 1) != '/' ? propertyDirectoryString + "/" : propertyDirectoryString); // "D:/FLP_RESOURCES/MODULES";

		GlobalVO globalVO = new GlobalVO();
		globalVO.setResourceTypeList(schoolTeacherService.getModuleTypes(ModuleType.MODULE_RESOURCE_TYPE, ModuleType.STATUS_ACTIVE));
		globalVO.setGradeTypeList(schoolTeacherService.getModuleTypes(ModuleType.MODULE_GRADE_TYPE, ModuleType.STATUS_ACTIVE));
		globalVO.setTopicId(topicId);
		globalVO.setLoginUserType(User.USERTYPE_SCHOOL_SME);
		globalVO.setModuleResourceDirectory(moduleResourceDirectory);
		mav.addObject("globalVO", globalVO);
		mav.addObject("moduleTopicList", adminService.getModuleTopics(null, topicId, ModuleTopic.STATUS_ACTIVE));

		return mav;
	}

	/** @param globalVO
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping("/sme/uploadModule.htm")
	public String uploadModule(@ModelAttribute(value = "globalVO") GlobalVO globalVO, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String message = "success";

		UserForm userForm = (UserForm) request.getSession().getAttribute("user");

		globalVO.setSchoolId(userForm.getSchoolId());

		message = schoolTeacherService.uploadModule(globalVO);

		return message;
	}

	/** @param request
	 * @param response
	 * @param mpf
	 * @return */

	@LoginAuthentication
	@ResponseBody
	@RequestMapping(value = "/sme/uploadQuestionExcel.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam("file2") MultipartFile mpf)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		long topicId = Long.parseLong(request.getParameter("topicId"));
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
			}
			else
				msg = "Please provide excel file";

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return msg;

	}

	/** @param request
	 * @param response
	 * @param id
	 * @throws UnsupportedEncodingException */
	@RequestMapping("/editTopic")
	@ResponseBody
	public void edituser(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws UnsupportedEncodingException
	{
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		schoolTeacherService.updatetopicdetails(id, name);

	}

	/** @param request
	 * @param response
	 * @param id
	 * @throws UnsupportedEncodingException */
	@RequestMapping("/editChapter")
	@ResponseBody
	public void editChaptername(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws UnsupportedEncodingException
	{
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		schoolTeacherService.updatechapterdetails(id, name);

	}

}