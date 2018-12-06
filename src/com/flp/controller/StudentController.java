package com.flp.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.web.servlet.ModelAndView;

import com.flp.annotations.LoginAuthentication;
import com.flp.annotations.StudentAuthentication;
import com.flp.model.Assessment;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.ModuleTopic;
import com.flp.model.Question;
import com.flp.model.SchoolStudentTest;
import com.flp.model.SchoolStudentTestDetail;
import com.flp.model.Session;
import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserActiveTopic;
import com.flp.model.UserActivity;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.service.RetailUserService;
import com.flp.service.SchoolTeacherService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */
@Controller
public class StudentController
{
	@Autowired
	StudentCommonService studentCommonService;

	@Autowired
	SchoolTeacherService schoolTeacherService;

	@Autowired
	private RetailUserService retailUserService;

	@Autowired
	private UserService userService;
	
	@Resource
	private Environment env;
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@LoginAuthentication
	@StudentAuthentication
	@RequestMapping(value = "/student/studentHome.htm", method = RequestMethod.GET)
	public ModelAndView studentHome(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("student/studentHome");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		request.getSession().setAttribute("rootpathnew", env.getRequiredProperty("flp.static.rootPath")); 

        request.getSession().setAttribute("lmsimagepath", env.getRequiredProperty("flp.static.lmsimagepath"));
		Map<Subject, List<Chapter>> allsubject = new HashMap<Subject, List<Chapter>>();

		List<Long> tpid = new ArrayList<Long>();
		
		if(loginUser.getSubjects()!=null)
		{
		for (Subject sub : loginUser.getSubjects())
		{

			 

			List<Chapter> chapterList = studentCommonService.getChaptersTopicsBySubject(sub.getId(), loginUser.getGradeId(), loginUser.getId());
 
			for (Chapter c : chapterList)
			{

				List<Topic> allnewtopics = new ArrayList<Topic>();

				for (Topic top : c.getTopics())
				{

					if (!tpid.contains(top.getId()))
					{

						tpid.add(top.getId());
						int lx = studentCommonService.getGScore(top.getId(), loginUser);
						if (lx > 0)
						{

							Calendar calendar = Calendar.getInstance();
							Date dd = studentCommonService.getmaxLxDate(top.getId(), loginUser);
							calendar.setTime(dd);
							int month = calendar.get(Calendar.MONTH) + 1;

							Topic temptopic = new Topic();
							temptopic.setId(top.getId());
							temptopic.setName(top.getName());
							temptopic.setLx(lx);
							 
							temptopic.setLxDate(calendar.get(Calendar.YEAR) + "/" + month + "/" + calendar.get(Calendar.DAY_OF_MONTH));

							allnewtopics.add(temptopic);

						}

					}

					c.setTopics(allnewtopics);
				}

				if (c.getTopics().isEmpty())
				{
					c.setName("pp");
				}

			}

			allsubject.put(sub, chapterList);

		}
		}

		mav.addObject("allsubject", allsubject);

		/**
		 for test and assment logic below
		 */
		
		
		/*List<Assessment> etestList = studentCommonService.geteTestList(loginUser.getId(), loginUser.getSessionId());
		Date currentDate = null;
		Calendar calendar = Calendar.getInstance();
		TimeZone fromTimeZone = calendar.getTimeZone();
		TimeZone toTimeZone = TimeZone.getTimeZone("IST");
		calendar.setTimeZone(fromTimeZone);
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime()))
		{
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
		}

		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		if (toTimeZone.inDaylightTime(calendar.getTime()))
		{
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}
		
		
		
		if (etestList != null && etestList.size() > 0)
		{
			for (Assessment as : etestList)
			{
				
				
				Date startDate = Utility.convertStringToDateWithZone(as.getDateTimeZone());
				currentDate = calendar.getTime();
						   long diffInMillisec = startDate.getTime() -currentDate.getTime();
						   if (diffInMillisec > 0)
							{
						   long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);
						   long   seconds = diffInSec % 60;
						   diffInSec/= 60;
						   long   minutes =diffInSec % 60;
						   diffInSec /= 60;
						   long   hours = diffInSec % 24;
						   diffInSec /= 24;
						   long	   days = diffInSec;
						   
							as.setDateDifference(hours + "," + minutes + "," + seconds+ ","+days);
							
							  
							}
				
			}
		}
		mav.addObject("etestList", etestList);
		
		
		
		

		List<Assessment> assignedTestList = studentCommonService.getAssignedTest(loginUser.getId(), loginUser.getSessionId());
		if (assignedTestList != null && assignedTestList.size() > 0)
		{
			for (Assessment as : assignedTestList)
			{

				currentDate = calendar.getTime();
		      // Date startDate = as.getStartDate();
				Date startDate = Utility.convertStringToDateWithZone(as.getDateTimeZone());
				
				// currentDate = Utility.getCurrentDate();
				long diff1 = currentDate.getTime() - as.getEndDate().getTime();
				if (diff1 > 0)
				{
					as.setFlag(1);
				}
				else
				{
					
					
					   long diffInMillisec = startDate.getTime() -currentDate.getTime();
					   if (diffInMillisec > 0)
						{
					   long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);
					   long   seconds = diffInSec % 60;
					   diffInSec/= 60;
					   long   minutes =diffInSec % 60;
					   diffInSec /= 60;
					   long   hours = diffInSec % 24;
					   diffInSec /= 24;
					   long	   days = diffInSec;
					   
					   
					   as.setDateDifference(hours + "," + minutes + "," + seconds+ ","+days);
						
					//	as.setDateDifference(hours + "," + minutes + "," + seconds);
			
						}
				}
			}
		}
		mav.addObject("assignedTestList", assignedTestList);
		
		

		if (loginUser.getSubjects() != null && loginUser.getSubjects().size() > 0)
		{
			List<Long> subjectIds = new ArrayList<Long>();
			for (Subject s : loginUser.getSubjects())
			{
				subjectIds.add(s.getId());
			}
			Map<Long, Integer> userSmartScoreMap = retailUserService.getAllTopicSmartScoreOfUser(loginUser.getId());

			List<Subject> subjectList = studentCommonService.getSubjectChapterTopics(subjectIds, true, loginUser.getGradeId(), loginUser.getSchoolId(), loginUser.getId());
			mav.addObject("subjectList", subjectList);

			if (!userSmartScoreMap.isEmpty())
			{
				for (Subject s : subjectList)
				{
					for (Chapter c : s.getChapters())
					{
						for (Topic t : c.getTopics())
						{
							if (userSmartScoreMap.containsKey(t.getId()))
							{
								Integer score = userSmartScoreMap.get(t.getId());
								t.setSmartScore(score);
							}
						}
					}
				}
			}

			List<AssignTopicResources> topicResourceList = studentCommonService.getAssignedResoureAndTopics(subjectIds, loginUser.getGradeId(), loginUser.getSchoolId(), loginUser.getSessionId(), loginUser.getSectionId(), loginUser.getId());
			if (!topicResourceList.isEmpty())
			{

				for (AssignTopicResources atr : topicResourceList)
				{
					Integer ss = userSmartScoreMap.get(atr.getTopicId());
					if (ss != null)
					{
						atr.getTopic().setSmartScore(ss);
					}
				}
				mav.addObject("topicResourceList", topicResourceList);
			}

		}
		mav.addObject("pageType", 0);*/
		/*String startYear;
		String endYear;                                      
		List<Session>studentSession=studentCommonService.studentSession( loginUser.getSessionId());
		for(Session s:studentSession)
		{
			
			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(s.getStartDate());
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(s.getEndDate());

			int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
			
			
			
			String date[]=s.getSessionName().split("-");
			startYear=date[0];
			endYear = date[1];
			
		}
		*/
		
		
		/*for(Subject sub:loginUser.getSubjects())
		{
		List<Chapter> chapterListPeriodWise = studentCommonService.getChaptersTopicsBySubjectPreiodWise(sub.getId(), loginUser.getGradeId(), loginUser.getId(), loginUser.getSessionId());
		}
		
       */  
		
		
		return mav;
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param sid
	 * @return
	 */

	@LoginAuthentication
	@StudentAuthentication
	@RequestMapping(value = "/student/subjectDetail.htm", method = RequestMethod.GET)
	public ModelAndView subjectDetails(HttpServletRequest request, HttpServletResponse response, @RequestParam Long sid)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		loginUser.setSelectedSubject(sid);
		
		
		ModelAndView mav = new ModelAndView("student/subjectDetails");
		List<Chapter> chapterList = studentCommonService.getChaptersTopicsBySubject(sid, loginUser.getGradeId(), loginUser.getId());
	
		Long teacherId=	studentCommonService.getTeacherID(loginUser.getSchoolId(),loginUser.getSessionId(),loginUser.getSectionId(),sid);
		request.getSession().setAttribute("teacherId", teacherId);
		
		
		List<Long> topicIdList = new ArrayList<Long>();
		for (Chapter ss : chapterList)
		{

			for (Topic top : ss.getTopics())
			{
				topicIdList.add(top.getId());
			
			}

		}
		
		Map<Long, Integer> questCount = schoolTeacherService.getQuestionCountByTopicId(topicIdList);
		Map<Long, Integer> moduleCountList = schoolTeacherService.getModuleCountByTopicId(topicIdList);
		
		List<Long> contentPermission=	studentCommonService.getMarkTopicsContentPermissionStudent(loginUser.getGradeId(),loginUser.getSectionId(),sid,teacherId);
		
		
		List<Long> contentPermissionChapter=	studentCommonService.getMarkTopicsContentPermissionStudentchapter(loginUser.getGradeId(),loginUser.getSectionId(),sid,teacherId);
		
		
		
		
		
		
		//System.out.println(contentPermission+"contentPermissionChapter==ppp=="+contentPermissionChapter);
		
		
		
		//System.out.println(	"getGradeId="+loginUser.getGradeId()+"getSectionId="+loginUser.getSectionId()+"sid="+sid+"teacherId="+teacherId);
		
		//List<Long> contentPermission = schoolTeacherService.getMarkTopicsContentPermission(163L, sid);
		if (chapterList != null && chapterList.size() > 0)
		{
			for (Chapter c : chapterList)
			{
				LinkedHashSet<Topic> topicSet = new LinkedHashSet<Topic>();
				Boolean flagForChapterContentPermission = false;
				Integer questionCount = 0;
				Integer rightQuestionCount = 0;
				Integer chapCount = 0;
				Integer chapmoduleCount = 0;
				
				
				if (contentPermissionChapter.contains(c.getId()))
				{
					c.setChapterLock(1);
					
					
					
				}
				
				System.out.println("========------------========="+c.getChapterLock());
				
				
				for (Topic t : c.getTopics())
				{
					if (contentPermission.contains(t.getId()))
					{
						t.setMarkTopicContentPermission(1);
					}
					else
					{
						//t.setLx(studentCommonService.getGScore(t.getId(), loginUser));
						t.setMarkTopicContentPermission(0);
						flagForChapterContentPermission = true;
						
					}
					

					if (!topicSet.contains(t))
					{
						questionCount = questionCount + t.getQuestionTopic().size();
						
						
						for(UserQuestionAnswer uq:t.getUserQuestionAnswer())
						{
							if(uq.getCorrectAttemptsCount()==1)
							{
								
								rightQuestionCount++;
							}
							
							
						}
						
						
						
						
						
						Integer topicPercentage = 0;
						
						if (t.getQuestionTopic() != null && t.getQuestionTopic().size() > 0)
						{
							

							 
							double data1 = ((Double.parseDouble(String.valueOf(t.getUserQuestionAnswer().size())) * 100) / Double.parseDouble(String.valueOf(t.getQuestionTopic().size())));
							topicPercentage = (int) Math.round(data1);
							t.setLx(studentCommonService.getGScore(t.getId(), loginUser));
							
						}
						t.setPercent(topicPercentage);
						t.setLx(studentCommonService.getGScore(t.getId(), loginUser));
						topicSet.add(t);
					}
					
					if (questCount != null)
					{
						Integer questioncount = questCount.get(t.getId());
						if (questioncount != null)
						{
							t.setCount(questioncount);
							chapCount += questioncount;
						}
						else
						{
							t.setCount(0);

						}
					}
					
					if (moduleCountList != null)
					{
						Integer moduleCount = moduleCountList.get(t.getId());

						if (moduleCount != null)
						{
							t.settModulecount(moduleCount);
							chapmoduleCount += moduleCount;
						}
						else
						{
							t.settModulecount(0);
						}
					}
				}
				
				if (!flagForChapterContentPermission)
				{
					c.setMarkChapterContentPermission(1);
				}
				else
				{
					c.setMarkChapterContentPermission(0);
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
		UserActiveTopic studentActiveTopic = studentCommonService.getStudentLastActiveTopic(loginUser.getId(), loginUser.getSchoolId(), loginUser.getSessionId(), sid);
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
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@StudentAuthentication
	@RequestMapping(value = "/student/testDetail.htm", method = RequestMethod.GET)
	public ModelAndView testDetail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("student/testDetails");
		return mav;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@StudentAuthentication
	@RequestMapping(value = "/student/summary.htm", method = RequestMethod.GET)
	public ModelAndView summary(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("student/summary");
		return mav;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param testId
	 * @return
	 */
	@StudentAuthentication
	@RequestMapping("/student/getTestPage/{testId}.htm")
	@LoginAuthentication
	public ModelAndView getAssessment(HttpServletRequest request, HttpServletResponse response, @PathVariable Long testId)
	{

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		ModelAndView mav;
		Integer msg = studentCommonService.isValidAssessment(loginUser, testId);
		if (msg != 0)
		{
			mav = new ModelAndView("/student/assessmentError");
			mav.addObject("error", msg);
			mav.addObject("assessmentId", testId);
		}
		else
		{
			mav = new ModelAndView("/student/testDetails");
			mav.addObject("assessment", schoolTeacherService.getAssessment(testId));
		}
		return mav;

	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param questionDetail
	 * @return
	 */
	@StudentAuthentication
	@ResponseBody
	@RequestMapping(value = "/student/saveTestPage", method = RequestMethod.POST)
	public String saveAssessment(HttpServletRequest request, HttpServletResponse response, @RequestParam("questionDetailList") List<String> questionDetail)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		// ModelAndView mav = new ModelAndView("/student/testDetails");

		float marks = Float.parseFloat(request.getParameter("marks"));
		int correct = Integer.parseInt(request.getParameter("correct"));
		int incorrect = Integer.parseInt(request.getParameter("incorrect"));
		int unAttempted = Integer.parseInt(request.getParameter("unAttempted"));
		int total = Integer.parseInt(request.getParameter("total"));
		long subjectId = Long.parseLong(request.getParameter("subjectId"));
		long assessmentId = Long.parseLong(request.getParameter("assessmentId"));
		float totalMarks=Float.parseFloat(request.getParameter("totalMarks"));
		studentCommonService.saveTest(marks, subjectId, total, correct, incorrect, unAttempted, loginUser, questionDetail, assessmentId,totalMarks);

		return "success";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param assessmentId
	 * @param subjectName
	 * @return
	 */
	@LoginAuthentication
	@StudentAuthentication
	@RequestMapping(value = "/student/assessmentSummary.htm", method = RequestMethod.GET)
	public ModelAndView assessmentSummary(HttpServletRequest request, HttpServletResponse response, @RequestParam Long assessmentId, @RequestParam String subjectName)
	{
		ModelAndView mav = new ModelAndView("/student/assessmentSummary");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		SchoolStudentTest sst = studentCommonService.getStudentAssessmentSummary(loginUser.getId(), assessmentId);
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
		mav.addObject("maxAssessmentMarks", object[0]);
		mav.addObject("subjectName", subjectName);
		return mav;
	}
 
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	
	@LoginAuthentication
	@StudentAuthentication

	@RequestMapping(value = "/student/getNextQuestion", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getQuestion(HttpServletRequest request, HttpServletResponse response)
	{
		 
		ModelAndView mav = new ModelAndView("/student/test-question");
		HttpSession session = request.getSession();

		UserForm loginUser = (UserForm) session.getAttribute("user");
		long topicId = Long.parseLong(request.getParameter("topicId"));
		
		
		//int questionAttemptcount=studentCommonService.questionAttemptcount(topicId, loginUser);
		
		 
		
		
		
		
		Question q = studentCommonService.getNextQuestion(topicId, studentCommonService.getGScore(topicId, loginUser), loginUser.getId());  
		
		
	
		String val = ""+((int)(Math.random()*9000)+1000);
		mav.addObject("val", val); 
	    mav.addObject("question", q); 
		mav.addObject("score", studentCommonService.getGScore(topicId, loginUser));
		if(null!=q)
		mav.addObject("locountQ", q.getLocountQ());
		
		
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@LoginAuthentication
	@StudentAuthentication
	@RequestMapping(value = "/student/getAttemptCount", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView  getAttemptCount(HttpServletRequest request, HttpServletResponse response)
	{
		 
		ModelAndView mav = new ModelAndView("student/tempscroe");
		 
		HttpSession session = request.getSession();
		UserForm loginUser = (UserForm) session.getAttribute("user");
		long topicId = Long.parseLong(request.getParameter("topicId"));
		int questionAttemptcount=studentCommonService.questionAttemptcount(topicId, loginUser);
		
		
		
		if(questionAttemptcount<7){
			int setUpdate=studentCommonService.updatescoreData(topicId, loginUser);
			
			mav.addObject("score87",setUpdate); 
		}else 
		{
			mav.addObject("score87", studentCommonService.getGScore(topicId, loginUser)); 
		}
		
		
		
		
		return mav; 
	
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/student/keepSessionAlive", method = RequestMethod.POST)
	@ResponseBody
	public String keepSessionAlive()
	{
		return "Server msg: Session Alive";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@StudentAuthentication
	@RequestMapping(value = "student/saveQuestion", method = RequestMethod.POST)
	@LoginAuthentication
	public @ResponseBody int saveQuestion(HttpServletRequest request, HttpServletResponse response)
	{
		int msg = studentCommonService.saveQuestion(request);
     
		return msg;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@StudentAuthentication
	@LoginAuthentication
	@RequestMapping(value = "/student/getQuestionAssessmentPage.htm")
	public ModelAndView getQuestionAssessmentPage(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		ModelAndView mav = new ModelAndView("student/questionAssessmentPageNewUIPage");
		Long topicId = Long.parseLong(request.getParameter("topicId"));
		Long chapteid = Long.parseLong(request.getParameter("chapterId"));
		String topicName = new String(request.getParameter("topicName").getBytes("ISO-8859-1"), "UTF-8");
		String subjectName = new String(request.getParameter("subjectName").getBytes("ISO-8859-1"), "UTF-8");
		mav.addObject("topicName", topicName);
		mav.addObject("subjectName", subjectName);
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("topicId", topicId);
		int gScore = studentCommonService.getGScore(topicId, loginUser);
		
		
		
		Map<Long, Integer> m = new HashMap<Long, Integer>();
		m.put(topicId, gScore);
		request.getSession().setAttribute("gScore", m);
		mav.addObject("score", gScore);
		mav.addObject("score1", gScore);
		List<ModuleTopic> moduleTopicList = studentCommonService.getModulesByTopicId(Long.parseLong(request.getParameter("topicId")));
		mav.addObject("moduleTopicList", moduleTopicList);
		mav.addObject("pageType", 1);
		Date currentDate = new Date();
		if (loginUser.getSchoolId() != null)
		{
			// save school user activity
			UserActiveTopic userActivity = studentCommonService.getUserActiveTopic(Long.parseLong(request.getParameter("sId")), loginUser.getId());

			if (userActivity != null)
			{
				userActivity.setTopicId(topicId);
				userActivity.setChapterId(Long.parseLong(request.getParameter("chapterId")));
				userActivity.setModifyDate(currentDate);
			}
			else
			{
				userActivity = new UserActiveTopic();
				userActivity.setSchoolId(loginUser.getSchoolId());
				userActivity.setSessionId(loginUser.getSessionId());
				userActivity.setInsertDate(currentDate);
				userActivity.setModifyDate(currentDate);
				userActivity.setStatus(UserActivity.STATUS_ACTIVE);
				userActivity.setSubjectId(Long.parseLong(request.getParameter("sId")));
				userActivity.setChapterId(Long.parseLong(request.getParameter("chapterId")));
				userActivity.setTopicId(topicId);
				userActivity.setUserId(loginUser.getId());
			}
			studentCommonService.saveOrUpdateUserActiveTopic(userActivity);

		}
		else
		{
			// save retail user activity
			UserActivity userActivity = studentCommonService.getLastUserActivity(topicId, Long.parseLong(request.getParameter("sId")), loginUser.getId());

			if (userActivity != null)
			{
				userActivity.setTopicId(topicId);
				userActivity.setChapterId(Long.parseLong(request.getParameter("chapterId")));
				userActivity.setModifyDate(currentDate);
			}
			else
			{
				userActivity = new UserActivity();
				userActivity.setBoardId(loginUser.getBoardId());
				userActivity.setChapterId(Long.parseLong(request.getParameter("chapterId")));
				userActivity.setInsertDate(currentDate);
				userActivity.setModifyDate(currentDate);
				userActivity.setStatus(UserActivity.STATUS_ACTIVE);
				userActivity.setSubjectId(Long.parseLong(request.getParameter("sId")));
				userActivity.setTopicId(topicId);
				userActivity.setUserId(loginUser.getId());
			}
			studentCommonService.saveOrUpdateUserActivity(userActivity);
		}
		return mav;
	}
	/**
	 * 
	 * @param request
	 * @param responses
	 * @param model
	 * @return
	 */
	@LoginAuthentication
	@StudentAuthentication
	@RequestMapping(value = "/student/getTopicSubmissionMessage.htm")
	public String getTopicSubmissionMessage(HttpServletRequest request, HttpServletResponse responses, Model model)
	{
		// topicId
		// case
		Topic topic = studentCommonService.getTopicObj(Long.parseLong(request.getParameter("topicId")));
		model.addAttribute("topic", topic);
		// model.addAttribute("topicId", request.getParameter("topicId"));
		model.addAttribute("msg", request.getParameter("case"));

		return "/student/topicSubmissionMessage";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/student/testReports.htm", method = RequestMethod.GET)
	public ModelAndView testReport(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("student/reports");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		List<AssignedAssessment> ass = studentCommonService.getAssessmentByStudentId(loginUser.getId());
		Map<Long, String> getToppersScoreLst = studentCommonService.getTopperAndMaxStudentAttendAssessments(loginUser.getGradeId(), loginUser.getSchoolId());
		if (!getToppersScoreLst.isEmpty())
		{
			for (AssignedAssessment a : ass)
			{
				String data = getToppersScoreLst.get(a.getAssessment().getId());
				if (data != null)
				{
					String[] val = data.split("\\,");
					a.setMaxMarks(Double.valueOf(val[0]));
					a.setCount(Integer.parseInt(val[1]));
				}
				else
				{
					a.setMaxMarks(Double.valueOf(0.0));
					a.setCount(0);
				}
				/* Object[] object = studentCommonService.getTopperAndMaxAttendAssessment( loginUser.getSchoolId(), a.getAssessmentId()); mav.addObject("TotalStudentAttempt", object[1]); mav.addObject("maxAssessmentMarks", object[0]); */
			}
		}

		mav.addObject("assessment", ass);
		mav.addObject("pageType", 2);
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
		@LoginAuthentication
		@RequestMapping(value = "/student/learningReports.htm", method = RequestMethod.GET)
		public ModelAndView studentLearningReports(HttpServletRequest request, HttpServletResponse response)
		{
			UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

			int topicMaster=0,chapterMaster=0,topicProgress=0,totalCorrectCount=0,totalTimeTaken=0;
			long totalQuestioncount=0;
			Long subjectId = loginUser.getSubjects().get(0).getId();
			if (request.getParameter("sid") != null)
			{
				subjectId = Long.parseLong(request.getParameter("sid"));
			}
			loginUser.setSelectedSubject(subjectId);
			ModelAndView mav = new ModelAndView("student/learningReports");
			
			List<Chapter> chapterList = studentCommonService.getChaptersTopicsBySubject(subjectId, loginUser.getGradeId(), loginUser.getId());
			
			
			if (chapterList != null && chapterList.size() > 0)
			{
				for (Chapter c : chapterList)
				{	LinkedHashSet<Topic> topicSet = new LinkedHashSet<Topic>();
					Integer questionCount = 0;
					Integer rightQuestionCount = 0;
					for (Topic t : c.getTopics())
					{
						if (!topicSet.contains(t))
						{
							for(UserQuestionAnswer uq:t.getUserQuestionAnswer())
							{
								if(uq.getCorrectAttemptsCount()==1)
								{
									totalCorrectCount++;
									rightQuestionCount++;
								}
								totalTimeTaken=totalTimeTaken+uq.getTimeTalen();
								
							}
							
							questionCount = questionCount + t.getQuestionTopic().size();
							//rightQuestionCount = rightQuestionCount + t.getUserQuestionAnswer().size();
							
							Integer topicPercentage = 0;
							if (t.getQuestionTopic() != null && t.getQuestionTopic().size() > 0)
							{
								double data1 = ((Double.parseDouble(String.valueOf(t.getUserQuestionAnswer().size())) * 100) / Double.parseDouble(String.valueOf(t.getQuestionTopic().size())));
								topicPercentage = (int) Math.round(data1);
							}
							t.setPercent(topicPercentage);
							t.setCount(t.getQuestionTopic().size());
							topicSet.add(t);
						}
					}
					
					Integer percent = 0;
					if (questionCount > 0)
					{
						Double data = (Double.parseDouble(rightQuestionCount.toString()) * 100) / Double.parseDouble(questionCount.toString());
						percent = (int) Math.round(data);
					}
					c.setResultPercent(percent);
					c.setCount(questionCount);
					c.setTopicSet(topicSet);
				}
			}
			List<Long> topicIdList=new ArrayList<Long>();
			for(Chapter ch:chapterList)
			{
				if(ch.getResultPercent()==100)
				{
					chapterMaster++;
				}
				topicProgress=topicProgress+ch.getTopics().size();
				for(Topic tc:ch.getTopics())
				{   
					
					topicIdList.add(tc.getId());
					
					if(tc.getPercent()==100)
					{
					
						topicMaster++;
					}
				}
			}
			Map<Long, Integer> questCount = schoolTeacherService.getQuestionCountByTopicId(topicIdList);
			for(Map.Entry<Long, Integer> me:questCount.entrySet())
			{
				totalQuestioncount=totalQuestioncount+me.getValue();
			}
			
			Date d = new Date(totalTimeTaken * 1000L);
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			String time = df.format(d);
			
			
			int chapterProgressPercent=((chapterMaster)*100/(chapterList.size()));
			int topicProgressPerccent=(topicMaster*100/topicProgress);
			int totalQuestionPercent=(totalCorrectCount*100/(int)totalQuestioncount);
			mav.addObject("totalCorrectCount", totalQuestioncount);
			mav.addObject("totalQuestionPercent", totalQuestionPercent);
			mav.addObject("time", time);
			mav.addObject("chapterProgressPercent", chapterProgressPercent);
			mav.addObject("totalCorrectCount", totalCorrectCount);
			mav.addObject("topicProgressPerccent", topicProgressPerccent);
			mav.addObject("chapterMaster", chapterMaster);
			mav.addObject("topicMaster", topicMaster);
			mav.addObject("chapterList", chapterList);
			mav.addObject("subjectId", subjectId);
			mav.addObject("pageType", 2);
			
			
			return mav;
		}
		/**
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
	@StudentAuthentication
	@RequestMapping(value = "/student/studentprofile.htm", method = RequestMethod.GET)
	public ModelAndView studentProfile(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("student/studentprofile");
		return mav;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param fileBucket
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/student/singleSave", method = RequestMethod.POST)
	public ModelAndView singleFileUpload(HttpServletRequest request, HttpServletResponse response, @Validated FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException
	{
		UserForm studentData = (UserForm) request.getSession().getAttribute("user");
		String sAdmissionNo = studentData.getAdmissionNo();
		long sId = studentData.getId();
		String realname = fileBucket.getFile().getOriginalFilename();
		String imageName = null;
		if (realname.contains(".png"))
		{
			imageName = "" + sId + "_" + sAdmissionNo + ".png";
		}
		else if (realname.contains(".gif"))
		{
			imageName = "" + sId + "_" + sAdmissionNo + ".gif";
		}
		else if (realname.contains(".jpg"))
		{
			imageName = "" + sId + "_" + sAdmissionNo + ".jpg";
		}
		String profilePath = Utility.profilePath;
		if (result.hasErrors())
		{
			System.out.println("validation errors");
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
			response.sendRedirect(request.getContextPath() + "/student/studentprofile.htm");
			return null;
		}
	}
	/**
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param fileBucket
	 * @return
	 * @throws IOException
	 */
	@StudentAuthentication
	@RequestMapping(value = "/student/updateStudentPersonalInformation", method = RequestMethod.POST)
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
		response.sendRedirect(request.getContextPath() + "/student/studentprofile.htm");
		return null;
	}
	/**
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@StudentAuthentication
	@RequestMapping(value = "/student/updateContactInformation", method = RequestMethod.POST)
	public ModelAndView updateContactInformation(@ModelAttribute("updateContactInformation") User user, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		User userObj = studentCommonService.getUserObjById(((UserForm) request.getSession().getAttribute("user")).getId());
		userObj.setAddress(user.getAddress());
		userObj.setAddressTwo(user.getAddressTwo());
		userObj.setCity(user.getCity());
		userObj.setPinCode(user.getPinCode());
		userObj.setState(user.getState());
		userService.SaveOrUpdate(userObj);
		UserForm uf = (UserForm) request.getSession().getAttribute("user");
		uf.setAddress(user.getAddress());
		uf.setAddressTwo(user.getAddressTwo());
		uf.setCity(user.getCity());
		uf.setPinCode(user.getPinCode());
		uf.setState(user.getState());
		request.getSession().setAttribute("user", uf);
		response.sendRedirect(request.getContextPath() + "/student/studentprofile.htm");
		return null;
	}
}
