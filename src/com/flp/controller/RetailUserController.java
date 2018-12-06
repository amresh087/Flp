package com.flp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.flp.annotations.LoginAuthentication;
import com.flp.model.Chapter;
import com.flp.model.FavoriteTopics;
import com.flp.model.ParentChildMapping;
import com.flp.model.RetailUserQuery;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.Subscription;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserActivity;
import com.flp.model.UserForm;
import com.flp.model.UserTest;
import com.flp.service.PriceService;
import com.flp.service.RetailUserService;
import com.flp.service.SchoolAdminService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
import com.flp.util.MailSenderUtil;
import com.flp.util.Utility;
/** @author Amresh */
@Controller
@RequestMapping(value = "/user")
@PropertySource("classpath:application.properties")
public class RetailUserController
{

	@Resource
	private Environment env;

	@Autowired
	RetailUserService retailUserService;

	@Autowired
	UserService userService;

	@Autowired
	private MailSenderUtil mailSenderUtil;

	@Autowired
	private PriceService priceService;

	@Autowired
	StudentCommonService studentCommonService;

	@Autowired
	private SchoolAdminService schoolAdminService;

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = { "/retailUserHome.htm", "/" }, method = RequestMethod.GET)
	public ModelAndView retailUserHome(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		ModelAndView mav = new ModelAndView("retailUser/homePage");
		if (loginUser.getSubjects() != null && loginUser.getSubjects().size() > 0)
		{
			Integer limit = 10;
			// code for get User SmartScore
			Map<Long, Integer> userSmartScoreMap = retailUserService.getAllTopicSmartScoreOfUser(loginUser.getId());
			List<Long> subjectIds = new ArrayList<Long>();
			if (!loginUser.getSubjects().isEmpty())
			{
				for (Subject s : loginUser.getSubjects())
				{
					subjectIds.add(s.getId());
				}
			}
			List<UserActivity> userActivityList = studentCommonService.getAllLastActivityOfRetailUser(loginUser.getId(), loginUser.getBoardId(), limit, subjectIds);
			List<Long> topicIds = new ArrayList<Long>();
			List<Topic> suggestedTopicLst = new ArrayList<Topic>();

			if (!userActivityList.isEmpty())
			{
				for (UserActivity ua : userActivityList)
				{
					topicIds.add(ua.getTopicId());
				}
				Integer randomTopicLimit = 0;
				if (userActivityList.size() < 10)
				{
					randomTopicLimit = 10 - userActivityList.size();

					suggestedTopicLst = retailUserService.getSuggestedTopicLst(subjectIds, randomTopicLimit, topicIds, loginUser.getGradeId(), loginUser.getBoardId());
					if (!suggestedTopicLst.isEmpty())
					{
						for (Topic t : suggestedTopicLst)
						{
							topicIds.add(t.getId());
						}
					}
				}

				Map<Long, Integer> avgTopicSmartScore = retailUserService.getAvgTopicSmartScore(topicIds);
				// code for setsmartScore and avg smartscore
				for (UserActivity ua : userActivityList)
				{
					Integer ss = userSmartScoreMap.get(ua.getTopicId());
					if (ss != null)
					{
						ua.setSmartScore(ss);
					}
					Integer avgSS = avgTopicSmartScore.get(ua.getTopicId());
					if (avgSS != null)
					{
						ua.setAvgSmartScore(avgSS);
					}
				}

				// code for set smartscore and avg smartscore
				for (Topic t : suggestedTopicLst)
				{
					Integer ss = userSmartScoreMap.get(t.getId());
					if (ss != null)
					{
						t.setSmartScore(ss);
					}
					Integer avgSS = avgTopicSmartScore.get(t.getId());
					if (avgSS != null)
					{
						t.setAvgSmartScore(avgSS);
					}
				}

			}
			else if (!loginUser.getSubjects().isEmpty())
			{
				if (topicIds.isEmpty())
				{
					topicIds.add(0l);
				}

				suggestedTopicLst = retailUserService.getSuggestedTopicLst(subjectIds, 10, topicIds, loginUser.getGradeId(), loginUser.getBoardId());
				if (!suggestedTopicLst.isEmpty())
				{
					for (Topic t : suggestedTopicLst)
					{
						topicIds.add(t.getId());
					}
				}
				Map<Long, Integer> avgTopicSmartScore = retailUserService.getAvgTopicSmartScore(topicIds);
				// code for set smartscore and avg smartscore
				for (Topic t : suggestedTopicLst)
				{
					Integer ss = userSmartScoreMap.get(t.getId());
					if (ss != null)
					{
						t.setSmartScore(ss);
					}
					Integer avgSS = avgTopicSmartScore.get(t.getId());
					if (avgSS != null)
					{
						t.setAvgSmartScore(avgSS);
					}
				}
			}
			mav.addObject("userActivityList", userActivityList);
			mav.addObject("suggestedTopicLst", suggestedTopicLst);

			limit = 15;
			List<FavoriteTopics> favTopicList = studentCommonService.getStudentFavoriteTopic(loginUser.getId(), limit, subjectIds);

			if (!favTopicList.isEmpty())
			{
				for (FavoriteTopics ua : favTopicList)
				{
					Integer ss = userSmartScoreMap.get(ua.getTopicId());
					if (ss != null)
					{
						ua.setSmartScore(ss);
					}
				}
			}
			mav.addObject("favTopicList", favTopicList);

			// get latest test with limit
			List<UserTest> testDetails = studentCommonService.getAttemptUnattemptTestDetails(loginUser.getId(), loginUser.getBoardId(), null, limit);
			mav.addObject("testDetails", testDetails);
		}

		mav.addObject("pageType", 0);
		mav.addObject("searchTopics", retailUserService.getTopics(loginUser.getBoardId(), loginUser.getGradeId(), Topic.STATUS_ACTIVE));

		List<ParentChildMapping> parentReqList = retailUserService.getParentRequestList(loginUser.getId());
		mav.addObject("parentReqList", parentReqList);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception */

	@LoginAuthentication
	@RequestMapping(value = "/user/profile.htm")
	public ModelAndView prepareEditProfile(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception
	{
		ModelAndView mav = new ModelAndView("editProfile");
		UserForm user = (UserForm) request.getSession().getAttribute("user");
		User userObj = userService.getUserById(user.getId());
		mav.addObject("user", userObj);
		return mav;
	}

	/** @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@LoginAuthentication
	@RequestMapping(value = "/user/editProfile.htm")
	public ModelAndView editProfile(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView("redirect:prepareEditProfile.htm");
		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		User userObj = userService.getUserById(userForm.getId());
		userObj.setModifyDate(new Date());
		userObj.setFirstName(user.getFirstName());
		userObj.setAddress(user.getAddress());
		userObj.setCity(user.getCity());
		userObj.setState(user.getState());
		userObj.setMobileNo(user.getMobileNo());
		userService.SaveOrUpdate(userObj);
		request.getSession().setAttribute("successMsg", "you have successfully updated your profile");
		mav.addObject("user", userObj);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param req
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/user/uploadImage.htm", method = RequestMethod.POST)
	public ModelAndView Image(HttpServletRequest request, HttpServletResponse response, DefaultMultipartHttpServletRequest req)
	{
		MultipartFile file = req.getFile("file");

		UserForm userForm = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("redirect:retailUserHome.htm");

		File dir = new File(Utility.getValueFromPropertyFile("flp.pImage") + File.separator + "profileImage");
		Utility.saveImageInResources(file, dir, userForm.getEmail() + ".jpg");

		User user = userService.getUserById(userForm.getId());
		user.setProfilePath(user.getEmailId() + ".jpg");
		user.setModifyDate(new Date());
		userService.SaveOrUpdate(user);
		userForm.setProfilePath(user.getEmailId() + ".jpg");
		request.getSession().setAttribute("user", userForm);
		return mav;
	}

	/** @param user
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@RequestMapping(value = "/retailUserOrParentSignup.htm", method = RequestMethod.POST)
	public ModelAndView saveUserRegistration(@ModelAttribute("user") User user, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Boolean emailStatus = userService.isEmailExist(user.getEmailId());
		if (!emailStatus)
		{
			user.setUserName(user.getEmailId());
			user.setStatus(User.STATUS_INACTIVE);
			user.setCreatedBy(-1l);
			user.setCreatedDate(new Date());
			user.setModifyDate(new Date());
			String token = UUID.randomUUID().toString().toUpperCase() + Calendar.getInstance().getTimeInMillis();
			user.setResettoken(token);

			if (user.getUserType().equals(User.USERTYPE_DEMO))
			{
				Long sectionId = userService.getSectionByGradeAndBoardId(user.getGradeId(), user.getBoardId());
				user.setSectionId(sectionId);
				userService.saveUserRegistration(user);

				// save user data in subscription table
				Long subscriptionId = saveSubscription(user);
				user.setSubscriptionid(subscriptionId);
				// update subscription id in user table
				userService.SaveOrUpdate(user);

				// assign section to student
				assignSectionToUser(user);

			}
			else
			{
				userService.saveUserRegistration(user);
			}
			user.setEncryptedToken(Utility.encrypt(token));
			// send mail for user verification
			String url = env.getRequiredProperty("appUrl") + "/activateUserAccount.htm";
			user.setUrl(url);
			mailSenderUtil.sendConfirmationEmail(user);

			request.getSession().setAttribute("successMsg", "You have successfully registered.Please Activate your account click on a link send to your mail id.");

		}
		else
		{
			request.getSession().setAttribute("errMsg2", "Email id is already exist");
		}
		response.sendRedirect(request.getContextPath() + "/index.htm");
		return null;
	}

	/** @param user */
	private void assignSectionToUser(User user)
	{

		Long sectionId = userService.getSectionByGradeAndBoardId(user.getGradeId(), user.getBoardId());
		if (sectionId != null)
		{
			StudentTeacherSession sts = new StudentTeacherSession();
			sts.setSectionId(sectionId);
			sts.setStatus(1);
			sts.setUserId(user.getId());
			sts.setUserRole(User.USERTYPE_DEMO);
			sts.setCreatedOn(new Date());
			schoolAdminService.saveStudentTeacherSession(sts);
		}
	}

	/** @param user
	 * @return */
	private Long saveSubscription(User user)
	{
		// saving in subscription
		Subscription subscription = new Subscription();
		subscription.setBoardId(user.getBoardId());
		subscription.setGradeId(user.getGradeId());
		subscription.setSubjectId(-1l);
		subscription.setUserId(user.getId());
		subscription.setPlanType(Subscription.PLAN_TYPE_DEMO);
		Calendar cal = Calendar.getInstance();
		Date date = Utility.getCurrentDate();
		cal.setTime(date);
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 30); // add 30 days
		subscription.setExpiryDate(cal.getTime());
		subscription.setStartDate(date);
		subscription.setStatus(Subscription.STATUS_ACTIVE);
		return priceService.save(subscription);

	}

	/** @param request
	 * @param response
	 * @param sid
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/subjectDetail.htm", method = RequestMethod.GET)
	public ModelAndView subjectDetails(HttpServletRequest request, HttpServletResponse response, @RequestParam Long sid)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		loginUser.setSelectedSubject(sid);
		ModelAndView mav = new ModelAndView("retailUser/subjectDetails");
		List<Chapter> chapterList = studentCommonService.getChaptersTopicsBySubject(sid, loginUser.getGradeId(), loginUser.getId());
		Map<Long, Long> favoriteTopicMap = studentCommonService.getAllFavoriteTopic(loginUser.getId(), sid);

		if (chapterList != null && chapterList.size() > 0)
		{
			for (Chapter c : chapterList)
			{
				LinkedHashSet<Topic> topicSet = new LinkedHashSet<Topic>();
				Integer questionCount = 0;
				Integer rightQuestionCount = 0;
				for (Topic t : c.getTopics())
				{

					if (!topicSet.contains(t))
					{
						questionCount = questionCount + t.getQuestionTopic().size();
						rightQuestionCount = rightQuestionCount + t.getUserQuestionAnswer().size();
						Integer topicPercentage = 0;
						if (t.getQuestionTopic() != null && t.getQuestionTopic().size() > 0)
						{
							double data1 = ((Double.parseDouble(String.valueOf(t.getUserQuestionAnswer().size())) * 100) / Double.parseDouble(String.valueOf(t.getQuestionTopic().size())));
							topicPercentage = (int) Math.round(data1);
						}
						t.setPercent(topicPercentage);

						topicSet.add(t);
					}
					if (favoriteTopicMap.get(t.getId()) != null)
					{
						t.setIsfavorite(true);
					}
					else
					{
						t.setIsfavorite(false);
					}

				}

				Integer percent = 0;
				if (questionCount > 0)
				{
					Double data = (Double.parseDouble(rightQuestionCount.toString()) * 100) / Double.parseDouble(questionCount.toString());
					percent = (int) Math.round(data);
				}
				c.setResultPercent(percent);
				c.setTopicSet(topicSet);
			}
		}
		mav.addObject("chapterList", chapterList);
		mav.addObject("subjectId", sid);
		mav.addObject("activeChapter", 0);
		UserActivity studentActiveTopic = studentCommonService.getUserLastActiveTopic(loginUser.getId(), loginUser.getBoardId(), sid);
		if (studentActiveTopic != null)
		{
			mav.addObject("activeChapter", studentActiveTopic.getChapterId());
		}
		else
		{
			mav.addObject("activeChapter", 0);
		}
		mav.addObject("pageType", 1);
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/testReports.htm", method = RequestMethod.GET)
	@LoginAuthentication
	public ModelAndView retailUserAssessmentreports(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("retailUser/reports");
		List<UserTest> testDetails = studentCommonService.getAttemptUnattemptTestDetails(loginUser.getId(), loginUser.getBoardId(), UserTest.STATUS_ATTEMPTED, null);
		mav.addObject("testDetails", testDetails);
		return mav;

	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/getHome.htm", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest request, HttpServletResponse response)
	{
		return "/user/userDashboard";
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/getCreateTestPage.htm")
	@LoginAuthentication
	public ModelAndView getCreateTestPage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("/retailUser/manageTest");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		List<Subject> subjectList = retailUserService.getSubjects(loginUser.getId(), loginUser.getBoardId(), loginUser.getSectionId());
		List<UserTest> testList = retailUserService.getTestList(loginUser.getId(), loginUser.getBoardId(), loginUser.getGradeId());
		mav.addObject("subjects", subjectList);
		mav.addObject("tests", testList);
		return mav;
	}

	/** @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@LoginAuthentication
	@RequestMapping(value = "/interactiveZone.htm")
	public ModelAndView interactiveZone(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView("retailUser/interactiveZone");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		List<RetailUserQuery> list = retailUserService.getRetailUserQueries(loginUser.getId(), loginUser.getGradeId(), true, RetailUserQuery.STATUS_ACTIVE);
		mav.addObject("queries", list);

		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/createTest")
	@ResponseBody
	public String createTest(HttpServletRequest request, HttpServletResponse response)
	{
		return retailUserService.saveTest(request);

	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/getTestSummery.htm")
	public ModelAndView getTestSummery(HttpServletRequest request, HttpServletResponse response)
	{
		long testId = Long.parseLong(request.getParameter("testId"));
		ModelAndView mav = new ModelAndView("retailUser/testSummery");
		UserTest test = retailUserService.getTest(testId);
		mav.addObject("test", test);
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/attemptTest")
	public ModelAndView getTest(HttpServletRequest request, HttpServletResponse response)
	{

		ModelAndView mav = new ModelAndView("/retailUser/testPage");
		long testId = Long.parseLong(request.getParameter("testId"));
		UserTest test = retailUserService.getTest(testId);
		mav.addObject("assessment", test);
		return mav;
	}

	/** @param request
	 * @param response
	 * @param questionDetail
	 * @return */
	@ResponseBody
	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public String saveAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam("questionDetailList") List<String> questionDetail)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		// ModelAndView mav = new ModelAndView("/student/testDetails");

		// long marks = Long.parseLong(request.getParameter("marks"));
		int correct = Integer.parseInt(request.getParameter("correct"));
		int incorrect = Integer.parseInt(request.getParameter("incorrect"));
		int unAttempted = Integer.parseInt(request.getParameter("unAttempted"));
		int total = Integer.parseInt(request.getParameter("total"));
		long subjectId = Long.parseLong(request.getParameter("subjectId"));
		long assessmentId = Long.parseLong(request.getParameter("assessmentId"));
		retailUserService.saveTest(subjectId, total, correct, incorrect, unAttempted, loginUser, questionDetail, assessmentId);

		return "success";
	}
	
	
	@RequestMapping(value = "/studentcompleteRegistration", method = RequestMethod.POST)
	public ModelAndView saveStudentRegistration(@ModelAttribute("user") User user, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		User userdb=studentCommonService.getStudentId(user.getSchoolId(),user.getAdmissionNo());
		ModelAndView mav =null;
		System.out.println("sfsdfsdfdsfdsfdseeee"+user.getEmailId());
		Boolean emailStatus = userService.isEmailExist(user.getEmailId());
		if (!emailStatus){
			
			user.setId(userdb.getId());
			user.setGradeId(userdb.getGradeId());
			user.setCreatedBy(userdb.getCreatedBy());
			user.setSchoolId(user.getSchoolId());
			user.setUserName(user.getUserName());
			user.setStatus(User.STATUS_ACTIVE);
			user.setUserType(User.USERTYPE_STUDENT);
			user.setAdmissionNo(user.getAdmissionNo());
			user.setPassword(user.getPassword());
			if(user.getFirstName()!=null|| user.getFirstName()!="")
			user.setFirstName(user.getFirstName());
			if(user.getLastName()!=null||user.getLastName()!="")
			user.setLastName(user.getLastName());
			//System.out.println(userdb.getSectionId()+"user.getSectionId()==="+user.getSectionId());
			user.setSectionId(userdb.getSectionId());
			//user.setGrade(user.getGrade());
			user.setGradeId(user.getGradeId());
			user.setSectionName(user.getSectionName());
			user.setEmailId(user.getEmailId());
			if(user.getMobileNo()!=null||user.getMobileNo()!="")
			user.setMobileNo(user.getMobileNo());
			String token = UUID.randomUUID().toString().toUpperCase() + Calendar.getInstance().getTimeInMillis();
			user.setResettoken(token);
			userService.saveUserRegistration(user);
			user.setEncryptedToken(Utility.encrypt(token));
			// send mail for user verification
			String url = env.getRequiredProperty("appUrl") + "/activateUserAccount.htm";
			user.setUrl(url);
			mailSenderUtil.sendConfirmationEmail(user);

			request.getSession().setAttribute("successMsg1", "You have successfully registered.Please Login With  Your UserName  and password"
					+ " OR EmailId and password .");
			response.sendRedirect(request.getContextPath() + "/index.htm");
			return mav;
		}
		else
		{
			
			request.getSession().setAttribute("errMsg2", "Email id is already exist");
			//response.sendRedirect(request.getContextPath() + "/index.htm");
			
			
			User usr =studentCommonService.getStudentDetailOnCode(user.getSchoolIdCode(),user.getAdmissionNo());
			
			//System.out.println("usr.getGradeId()=="+usr.getGradeId());
			
			String gradeName=studentCommonService.getGradeName(usr.getGradeId());
			if(null!=usr.getSectionId()){
			String section=studentCommonService.getSection(usr.getSectionId());
			mav = new ModelAndView("/student/studentForm");
			mav.addObject("schoolId", usr.getSchoolId());
			mav.addObject("schoolIdCode", usr.getSchoolIdCode());
			mav.addObject("admissionNo", usr.getAdmissionNo());
			mav.addObject("firstName",usr.getFirstName());
			mav.addObject("lastName",usr.getLastName());
			//String userName=usr.getSchoolId()+usr.getAdmissionNo();
			mav.addObject("userName",usr.getUserName());
			mav.addObject("gradeName",gradeName);
			mav.addObject("section",section);
			}
			
			return mav;
			//response.sendRedirect(request.getContextPath() + "/studentRegisterForm");
			//ModelAndView mav = new ModelAndView("/student/studentForm");
			
		}
		//
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value ="/studentRegisterForm", method = RequestMethod.POST)
	public ModelAndView getStudentDetail(@RequestParam("schoolcode") String schoolcode,@RequestParam("admissionNo") String admissionNo, HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		String returnType = getStudentCredentialDetailsIdCode(schoolcode, admissionNo, request, response);
		
		//String returnType = getStudentCredentialDetails(schoolcode, admissionNo, request, response);
		if(returnType=="true"){
		ModelAndView mav = new ModelAndView("/student/studentForm");
	    
		//User usr =studentCommonService.getStudentDetail(schoolcode,admissionNo);
		
		User usr =studentCommonService.getStudentDetailOnCode(schoolcode,admissionNo);
		String gradeName=studentCommonService.getGradeName(usr.getGradeId());
		if(null!=usr.getSectionId()){
		String section=studentCommonService.getSection(usr.getSectionId()); 
		mav.addObject("schoolId", usr.getSchoolId());
		mav.addObject("schoolIdCode", usr.getSchoolIdCode());
		mav.addObject("admissionNo", usr.getAdmissionNo()); 
		mav.addObject("gradeIdss", usr.getGradeId());
		mav.addObject("sectionss", usr.getSectionId());
		mav.addObject("firstName",usr.getFirstName());
		mav.addObject("lastName",usr.getLastName());
		//String userName=usr.getSchoolId()+usr.getAdmissionNo();
		mav.addObject("userName",usr.getUserName());
		mav.addObject("gradeName",gradeName);
		mav.addObject("section",section);
		return mav;
		}
		else{
			request.getSession().setAttribute("errorMsgStudentAuth", " This Admission Number are  Registered Please check Your Mail .");
		}
		return mav;
		}
		else{
			request.getSession().setAttribute("errorMsgStudentAuth", "Either School Id or Admission Number invalid.");
			/*ModelAndView mav = new ModelAndView("/index");
			return mav;*/
			
			try
			{
				response.sendRedirect(request.getContextPath() + "/index.htm");
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			//return null;
		}
		
	}
	
	
	private String getStudentCredentialDetailsIdCode( String schoolcode, String admissionNo, HttpServletRequest request, HttpServletResponse response) 
	{
		
		
		User usr=studentCommonService.getStudentDetailOnCode(schoolcode, admissionNo) ;// getStudentDetail(schoolcode,admissionNo);
		if(usr!=null)
			return "true";
		else
		return "false";
	}
	
	private String getStudentCredentialDetails( String schoolcode, String admissionNo, HttpServletRequest request, HttpServletResponse response) 
	{
		
		
		User usr=studentCommonService.getStudentDetail(schoolcode,admissionNo);
		if(usr!=null)
			return "true";
		else
		return "false";
	}
}