package com.flp.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flp.annotations.LoginAuthentication;
import com.flp.model.Grade;
import com.flp.model.ParentChildMapping;
import com.flp.model.Section;
import com.flp.model.SessionSchoolGrade;
import com.flp.model.SmeGradeSubject;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.service.AdminService;
import com.flp.service.SchoolAdminService;
import com.flp.service.SchoolSmeService;
import com.flp.service.UserService;
import com.flp.util.Utility;

/** @author Amresh */
@Controller
@PropertySource("classpath:application.properties")
public class SchoolAdminController
{

	@Resource
	private Environment env;

	@Autowired
	private SchoolAdminService schoolAdminService;

	@Autowired
	private UserService userService;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private AdminService adminService;

	@Autowired
	private SchoolSmeService schoolSmeService;

	/** @param request
	 * @param response */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void fileUpload(HttpServletRequest request, HttpServletResponse response)
	{

	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/schoolAdmin/studentDetails.htm", method = RequestMethod.GET)
	@LoginAuthentication
	public ModelAndView studentDetails(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("schoolAdmin/studentDetails");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Grade> gradeList = schoolAdminService.getAllGradeBySchoolId(loginUser.getSchoolId());
		mav.addObject("gradeList", gradeList);
		mav.addObject("user", new User());
		Object sectionId = request.getSession().getAttribute("sectionId");
		if (sectionId != null)
		{
			request.getSession().removeAttribute("sectionId");
			Long gradeId = schoolAdminService.getGradeIdByectionId(Long.parseLong(sectionId.toString()));
			mav.addObject("grade", gradeId);
			List<Section> sectionList = schoolAdminService.getSectionByGradeId(loginUser.getSchoolId(), loginUser.getSessionId(), gradeId);
			mav.addObject("sectionList", sectionList);
			mav.addObject("section", Long.parseLong(sectionId.toString()));

		}
		return mav;
	}

	/** @param request
	 * @param response
	 * @param sectionId
	 * @return */
	@RequestMapping(value = "/ajax/schoolAdmin/getStudentDetails/{sectionId}", method = RequestMethod.POST)
	@LoginAuthentication
	public ModelAndView getStudentList(HttpServletRequest request, HttpServletResponse response, @PathVariable("sectionId") Long sectionId)
	{
		ModelAndView mav = new ModelAndView("schoolAdmin/studentList");
		List<StudentTeacherSession> stsList = schoolAdminService.getAllStudentBySectionId(sectionId);
		List<User> studentList = new ArrayList<User>();
		if (stsList != null && stsList.size() > 0)
		{
			for (StudentTeacherSession sts : stsList)
			{
				List<Long> unsignedSubjectList = schoolAdminService.getStudentUnassignedSubjectList(sts.getUserId(), sts.getSectionId());
				if (unsignedSubjectList != null && unsignedSubjectList.size() > 0)
				{
					sts.getUser().setUnassignedSubjectId(unsignedSubjectList);
				}
				studentList.add(sts.getUser());
			}
		}
		mav.addObject("studentList", studentList);
		List<SubjectSection> subjectSectionLst = adminService.getSubjectSections(sectionId.toString(), SubjectSection.STATUS_ACTIVE);
		mav.addObject("subjectSectionLst", subjectSectionLst);
		return mav;
	}

	/** @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/studentTeacherRegistration.htm", method = RequestMethod.POST)
	@LoginAuthentication
	public ModelAndView studentTeacherRegistration(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		if (user.getUserType().equals(User.USERTYPE_STUDENT))
		{
			user.setUserName(user.getUserName() + "-" + loginUser.getSchoolId() + "-" + user.getAdmissionNo());
		}

		Boolean isUserExist = userService.isUserExistByUserNameOrAdmissionNo(user.getUserName(), user.getAdmissionNo(), loginUser.getSchoolId());
		int status = 2;
		if (!isUserExist)
		{

			Date currentDate = new Date();
			user.setStatus(User.STATUS_ACTIVE);
			user.setCreatedDate(currentDate);
			user.setModifyDate(new Date());
			user.setSchoolId(loginUser.getSchoolId());
			user.setCreatedBy(loginUser.getId());
			userService.SaveOrUpdate(user);
			if (user.getUserType().equals(User.USERTYPE_STUDENT))
			{
				// code for save mapping of student with gradeSection
				Integer userRole = User.USERTYPE_STUDENT;
				StudentTeacherSession sts = new StudentTeacherSession();
				sts.setSchoolId(loginUser.getSchoolId());
				sts.setSessionId(loginUser.getSessionId());
				sts.setSectionId(user.getSectionId());
				sts.setStatus(StudentTeacherSession.STATUS_ACTIVE);
				sts.setUserId(user.getId());
				sts.setUserRole(userRole);
				status = 1;
				schoolAdminService.saveStudentTeacherSession(sts);
				request.getSession().setAttribute("sectionId", user.getSectionId());
			}
		}
		else
		{
			status = 3;
		}
		request.getSession().setAttribute("key", status);
		if (user.getUserType().equals(User.USERTYPE_STUDENT))
		{
			request.getSession().setAttribute("sectionId", user.getSectionId());
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		}
		else if (user.getUserType().equals(User.USERTYPE_TEACHER))
		{
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/SubjectTeacherSectionDetails.htm");
		}

		return null;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/schoolAdmin/SubjectTeacherSectionDetails.htm")
	@LoginAuthentication
	public ModelAndView SubjectTeacherSectionDetails(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("schoolAdmin/SubjectTeacherSectionDetails");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<Grade> gradeList = schoolAdminService.getAllGradeBySchoolId(loginUser.getSchoolId());
		mav.addObject("gradeList", gradeList);
		List<User> schoolTeacherList = schoolAdminService.getAllSchoolTeacher(loginUser.getSchoolId(), loginUser.getSessionId());
		mav.addObject("teacherList", schoolTeacherList);
		mav.addObject("studentTeacherSession", new StudentTeacherSession());
		List<StudentTeacherSession> assignTeacherSectionSubjectList = schoolAdminService.getAssignSubjectTeacherList(loginUser.getSchoolId(), loginUser.getSessionId());
		mav.addObject("assignTeacherSectionSubjectList", assignTeacherSectionSubjectList);
		return mav;

	}

	/** @param studentTeacherSession
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/assignSubjectToTeacher.htm")
	@LoginAuthentication
	public ModelAndView assignSubjectToTeacher(@ModelAttribute("studentTeacherSession") StudentTeacherSession studentTeacherSession, HttpServletRequest request, HttpServletResponse response) throws IOException
	{

		Boolean isAlreadyAssignSubject = schoolAdminService.isAlreadyAssignSubject(studentTeacherSession);
		if (!isAlreadyAssignSubject)
		{
			UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
			studentTeacherSession.setCreatedOn(new Date());
			studentTeacherSession.setSchoolId(loginUser.getSchoolId());
			studentTeacherSession.setSessionId(loginUser.getSessionId());
			studentTeacherSession.setStatus(StudentTeacherSession.STATUS_ACTIVE);
			studentTeacherSession.setUserRole(User.USERTYPE_TEACHER);
			schoolAdminService.assignSubjectToTeacher(studentTeacherSession);
		}
		else
		{
			request.getSession().setAttribute("error", "assignError");
		}
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/SubjectTeacherSectionDetails.htm");
		return null;

	}

	/** @param studentTeacherSubjectId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/removeSubjectFromTeacher/{studentTeacherSubjectId}")
	@LoginAuthentication
	public ModelAndView removeSubjectFromTeacher(@PathVariable("studentTeacherSubjectId") Long studentTeacherSubjectId, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		schoolAdminService.removeSubjectFromTeacher(studentTeacherSubjectId);
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/SubjectTeacherSectionDetails.htm");
		return null;

	}

	/** @param request
	 * @param response
	 * @return
	 * @throws IOException */
	// add grade List
	@RequestMapping(value = "/schoolAdmin/smeDetails.htm")
	@LoginAuthentication
	public ModelAndView smeDetails(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ModelAndView mav = new ModelAndView("schoolAdmin/smeDetails");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<User> schoolSmeList = schoolAdminService.getAllSchoolSmeList(loginUser.getSchoolId());
		List<Grade> gradeList = schoolAdminService.getAllGradeBySchoolId(loginUser.getSchoolId());
		mav.addObject("schoolSmeList", schoolSmeList);
		mav.addObject("gradeList", gradeList);
		mav.addObject("user", new User());
		return mav;

	}

	/** @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/upload.htm", method = RequestMethod.POST)
	public ModelAndView fileUpload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User form) throws IOException
	{
		String msg = null;
		String fileName = form.getFileData().getOriginalFilename();
		int i = fileName.lastIndexOf('.');
		String newFile = fileName.substring(i + 1, fileName.length());
		if (newFile.equalsIgnoreCase("xlsx"))
		{
			if (!form.getFileData().isEmpty())
			{
				try
				{
					UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

					msg = schoolAdminService.validateExcel(form, loginUser);
				}
				catch (Exception e)
				{
					msg = "You failed to upload " + form.getFileData().getOriginalFilename() + " => " + e.getMessage();
				}

			}
			/* msg= "You failed to upload " + form.getFileData().getOriginalFilename() + " because the file was empty."; */
		}
		request.getSession().setAttribute("excelMsg", msg);
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		return null;
	}

	/** @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/schoolAdmin/saveSmeRegistration.htm", method = RequestMethod.POST)
	@LoginAuthentication
	public ModelAndView saveAdminRegistration(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Boolean emailStatus = userService.isEmailExist(user.getEmailId());
		if (!emailStatus)
		{
			UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
			user.setSchoolId(loginUser.getSchoolId());
			user.setStatus(User.STATUS_ACTIVE);
			user.setCreatedBy(loginUser.getId());
			user.setCreatedDate(new Date());
			user.setUserType(User.USERTYPE_SCHOOL_SME);
			user.setModifyDate(new Date());
			user.setUserName(user.getEmailId());
			userService.saveUserRegistration(user);
			/* request.getSession().setAttribute("key", "User Add Successfully"); */

		}
		else
		{
			request.getSession().setAttribute("key", "UserName Already Exist");
		}
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/smeDetails.htm");
		return null;
	}

	/** @param userId
	 * @param userType
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/inactivateUser/{userId}/{userType}")
	@LoginAuthentication
	public ModelAndView inactivateUser(@PathVariable("userId") Long userId, @PathVariable("userType") Integer userType, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		schoolAdminService.inactivateUser(userId);
		if (userType.equals(User.USERTYPE_STUDENT))
		{
			UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
			schoolAdminService.inactivateUserFromCurrentSession(userId, loginUser.getSessionId());
			Long sectionId = schoolAdminService.getUserSectionId(userId);
			request.getSession().setAttribute("sectionId", sectionId);
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		}
		else if (userType.equals(User.USERTYPE_SCHOOL_SME))
		{
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/smeDetails.htm");
		}

		return null;

	}

	/** @param request
	 * @param response
	 * @param studentIds
	 * @param subjectId
	 * @param sectionId
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/unassignedSubject.htm")
	@LoginAuthentication
	public ModelAndView unassignedSubject(HttpServletRequest request, HttpServletResponse response, @RequestParam("studentIds") Long[] studentIds, @RequestParam("subjectId") Long subjectId, @RequestParam("sectionId") Long sectionId) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		String subjectIds = request.getParameter("subjectId");
		if (studentIds != null && studentIds.length > 0 && subjectIds != null && sectionId > 0)
		{
			for (Long studentId : studentIds)
			{

				StudentTeacherSession sts = new StudentTeacherSession();
				sts.setCreatedOn(new Date());
				sts.setSchoolId(loginUser.getSchoolId());
				sts.setSectionId(sectionId);
				sts.setSessionId(loginUser.getSessionId());
				sts.setStatus(StudentTeacherSession.STATUS_ACTIVE);
				sts.setSubjectId(subjectId);
				sts.setUserId(studentId);
				sts.setUserRole(User.USERTYPE_STUDENT);
				Boolean flag = schoolAdminService.isSubjectAlreadyUnassigned(sts);
				if (!flag)
				{
					schoolAdminService.saveStudentTeacherSession(sts);
				}
			}
		}
		request.getSession().setAttribute("sectionId", sectionId);
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		return null;

	}

	/** @param request
	 * @param response
	 * @param subjectIds
	 * @param studentId
	 * @param sectionId
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/assignedSubject.htm")
	@LoginAuthentication
	public ModelAndView assignedSubject(HttpServletRequest request, HttpServletResponse response, @RequestParam("subjectIds") Long[] subjectIds, @RequestParam("studentId") Long studentId, @RequestParam("sectionId") Long sectionId) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		if (studentId != null && subjectIds != null && subjectIds.length > 0)
		{
			schoolAdminService.removeUnassignedSubject(studentId, subjectIds, loginUser.getSessionId(), loginUser.getSchoolId());
		}
		request.getSession().setAttribute("sectionId", sectionId);
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		return null;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping("/schoolAdmin/editGradeName.htm")
	@LoginAuthentication
	public ModelAndView showEditGrade(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("schoolAdmin/editGradeName");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<SessionSchoolGrade> list = schoolAdminService.getAllGradeSectionSubject(loginUser.getSchoolId(), loginUser.getSessionId());
		// Set<Grade> gList = new HashSet<Grade>();
		/* for (SessionSchoolGrade g : list) { gList.add(g.getGrade()); } */
		mav.addObject("gradeList", list);
		return mav;
	}

	/** to change grade name by gradeId and gradeName
	 * 
	 * @param request
	 * @param response
	 * @return message : error: for Error, success: successfull, duplicate: duplicate data */
	@ResponseBody
	@LoginAuthentication
	@RequestMapping(value = "/ajax/schoolAdmin/editGradeName", method = RequestMethod.POST)
	public String editGradeName(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		long gradeId = Long.parseLong(request.getParameter("gradeId"));
		String gradeName = null;
		try
		{
			gradeName = new String(request.getParameter("gradeName").getBytes("ISO-8859-1"), "UTF-8");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			gradeName = new String(request.getParameter("gradeName").getBytes("ISO-8859-1"), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		long schoolId = loginUser.getSchoolId();
		return schoolAdminService.editGradeName(gradeId, gradeName, schoolId);

	}

	/** Creation section for a grade
	 * 
	 * @param request
	 * @param response
	 * @return message : error: for Error, success: successfull, duplicate: duplicate data */
	@ResponseBody
	@LoginAuthentication
	@RequestMapping("/ajax/schoolAdmin/createSection")
	public String createSection(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		long schoolId = loginUser.getSchoolId();
		long sessionId = loginUser.getSessionId();
		String sectionName = request.getParameter("sectionName");
		long gradeId = Long.parseLong(request.getParameter("gradeId"));
		return schoolAdminService.createSection(schoolId, sessionId, sectionName, gradeId);
	}

	/** @param request
	 * @param response
	 * @return */
	@ResponseBody
	@LoginAuthentication
	@RequestMapping("/ajax/schoolAdmin/mapSubjecttoSection")
	public String mapSubjectToSection(HttpServletRequest request, HttpServletResponse response)
	{
		String subjectIds = request.getParameter("subjectIds");
		long sectionId = Long.parseLong(request.getParameter("sectionId"));

		schoolAdminService.saveSubjectSectionMapping(subjectIds, sectionId);

		return "success";
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping("/ajax/schoolAdmin/removeSectionSubjectMapping")
	@LoginAuthentication
	@ResponseBody
	public String disableSubjectSectionMapping(HttpServletRequest request, HttpServletResponse response)
	{
		Long subjectSectionId = Long.parseLong(request.getParameter("ssId"));
		Long sectionId = Long.parseLong(request.getParameter("sectionId"));
		Long subjectId = Long.parseLong(request.getParameter("subjectId"));
		Boolean isAssignedToTeacher = schoolAdminService.isAlreadyAssigned(subjectId, sectionId, User.USERTYPE_TEACHER);
		if (!isAssignedToTeacher)
		{
			schoolAdminService.removeMapping(subjectSectionId);
			return "success";
		}
		else
		{
			return "assigned";
		}

	}

	/** @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/schoolAdmin/downloadExcel", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getExcel1(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Student.xlsx");
		String path = Utility.resourcesFolderPath;
		return new FileSystemResource(path + "/StudentRegistration.xlsx");
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/homeParent.htm", method = RequestMethod.GET)
	public ModelAndView parentFrom(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("parentDetails");

	}

	/** @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/updateUser.htm", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) throws IOException
	{
		User userObj = userService.getUserById(user.getId());
		userObj.setFirstName(user.getFirstName());
		if (user.getEmailId() != null && user.getEmailId() != "")
		{
			userObj.setEmailId(user.getEmailId());
		}
		if (user.getMobileNo() != null && user.getMobileNo() != "")
		{
			userObj.setMobileNo(user.getMobileNo());
		}
		userService.SaveOrUpdate(userObj);
		if (userObj.getUserType().equals(User.USERTYPE_STUDENT))
		{
			request.getSession().setAttribute("sectionId", userObj.getSectionId());
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		}
		else if (userObj.getUserType().equals(User.USERTYPE_SCHOOL_SME))
		{
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/smeDetails.htm");
		}
		else if (userObj.getUserType().equals(User.USERTYPE_TEACHER))
		{
			response.sendRedirect(request.getContextPath() + "/schoolAdmin/SubjectTeacherSectionDetails.htm");
		}
		return null;
	}

	/** @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@ResponseBody
	@RequestMapping(value = "/schoolAdmin/parentRegistration.htm", method = RequestMethod.GET)
	public String parentRegistration(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int status = 3;
		Date date = new Date();
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		Long id = Long.parseLong(request.getParameter("hName"));
		Boolean isUserExits = userService.isEmailExist(user.getEmailId());
		if (!isUserExits)
		{

			user.setCreatedBy(loginUser.getId());
			user.setCreatedDate(date);
			user.setStatus(User.STATUS_ACTIVE);
			user.setSchoolId(loginUser.getSchoolId());
			user.setUserType(User.USERTYPE_PARENT);
			user.setUserName(user.getEmailId());
			userService.SaveOrUpdate(user);
			Long userId = user.getId();
			ParentChildMapping pcm = new ParentChildMapping();
			pcm.setUserId(id);
			pcm.setParentId(userId);
			pcm.setModifiedDate(new Date());
			pcm.setInsertDate(new Date());
			userService.saveParentChildMapping(pcm);
			status = 1;

		}
		else
		{
			request.getSession().setAttribute("userId5", id);
		}

		request.getSession().setAttribute("Pkey", status);
		Long sectionId = schoolAdminService.getUserSectionId(id);
		request.getSession().setAttribute("sectionId", sectionId);
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
		return null;
	}

	/** @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/getAllSubjects.htm")
	@LoginAuthentication
	public ModelAndView getAllSubjcet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		Long schoolId = loginUser.getSchoolId();

		List<Subject> lst = schoolAdminService.getAllSubjectBySchoolId(schoolId);
		List<Grade> gradeList = schoolAdminService.getAllGradeBySchoolId(loginUser.getSchoolId());

		ModelAndView mav = new ModelAndView("subjectList");
		mav.addObject("sList", lst);
		mav.addObject("gradeList", gradeList);
		return mav;

	}

	/** @param request
	 * @param response
	 * @param subjectImageNew
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/addSubject.htm", method = RequestMethod.POST)
	public String saveSubject(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "subjectImageNew", required = false) MultipartFile subjectImageNew) throws IOException
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		Long schoolId = loginUser.getSchoolId();
		String sName = request.getParameter("sName");
		Boolean sub = schoolAdminService.isSubjectExist(sName, schoolId);
		if (sub)
		{

			request.getSession().setAttribute("flag", "1");
		}
		else
		{
			Subject subject = new Subject();
			if (subjectImageNew.getSize() > 0l)
			{

				String fileName = "";
				File excelFile = new File(subjectImageNew.getOriginalFilename());
				fileName = excelFile.getName();
				String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				Calendar d = Calendar.getInstance();
				Long name = d.getTimeInMillis();
				String swName = name.toString().concat("." + ext);
				Utility.saveImageInResources(subjectImageNew, new File(env.getRequiredProperty("flp.subjectImage")), swName);
				subject.setSubImage(swName);
			}

			subject.setSchoolId(schoolId);
			subject.setInsertDate(new Date());
			subject.setStatus(Subject.STATUS_ACTIVE);
			subject.setName(sName);
			adminService.addSubject(subject);
			request.getSession().setAttribute("flag", "2");

		}
		response.sendRedirect(request.getContextPath() + "/schoolAdmin/getAllSubjects.htm");
		return null;
	}

	/** @param sectionIdString
	 * @param subjectId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/assignSubjectToGrade.htm")
	@LoginAuthentication
	@ResponseBody
	public String assignSubjectToGrade(@RequestParam(value = "sectionIdString") String sectionIdString, @RequestParam(value = "subjectId") Long subjectId, HttpServletRequest request, HttpServletResponse response) throws IOException
	{

		String msg = schoolSmeService.assignSubjectToGrade(sectionIdString, subjectId);

		return msg;

	}

	/** @param gradeId
	 * @param smeId
	 * @param request
	 * @param response
	 * @return */
	@ResponseBody
	@LoginAuthentication
	@RequestMapping(value = "/schoolAdmin/getUniqueSubjectsByGradeId/{gradeId}/{smeId}")
	public Map<String, List> getUniqueSubjectsByGradeId(@PathVariable(value = "gradeId") Long gradeId, @PathVariable(value = "smeId") Long smeId, HttpServletRequest request, HttpServletResponse response)
	{

		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<SubjectSection> lst = schoolSmeService.getUniqueSubjectsByGradeId(gradeId, loginUser.getSchoolId());

		List<Subject> subjectList = new ArrayList<Subject>();
		if (lst != null && lst.size() > 0)
		{
			for (SubjectSection ss : lst)
			{
				ss.getSubject().setChapters(null);
				subjectList.add(ss.getSubject());
			}
		}
		Map<String, List> data = new HashMap<String, List>();
		List<Long> sectionList1 = schoolSmeService.getAssignGradeAndSubjectToSme(gradeId, smeId, loginUser.getSessionId());
		data.put("subjectAll", subjectList);
		data.put("subjectIn", sectionList1);
		return data;
	}

	/** @param subjectIdString
	 * @param gradeId
	 * @param smeId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping(value = "/schoolAdmin/assignGradeToSme.htm")
	@LoginAuthentication
	@ResponseBody
	public String assignGradeToSme(@RequestParam(value = "subjectIdString") String subjectIdString, @RequestParam(value = "gradeId") Long gradeId, @RequestParam(value = "smeId") Long smeId, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		Long schoolId = userForm.getSchoolId();
		Long sessionId = userForm.getSessionId();

		boolean flag = schoolSmeService.assignGradeToSme(subjectIdString, gradeId, smeId, schoolId, sessionId);

		return flag ? "success" : "Error in saving values in database. Please try again later.";

	}

	/** @param userId
	 * @param sessionId
	 * @return */
	@LoginAuthentication
	@ResponseBody
	@RequestMapping(value = "/schoolAdmin/getSmeAssignedGradesAndSubjects")
	public List<SmeGradeSubject> getSmeAssignedGradesAndSubjects(@RequestParam(value = "userId") Long userId, @RequestParam(value = "sessionId") Long sessionId)
	{
		return schoolSmeService.getSmeAssignedGradesAndSubjects(userId, sessionId, SmeGradeSubject.STATUS_ACTIVE);
	}

}
