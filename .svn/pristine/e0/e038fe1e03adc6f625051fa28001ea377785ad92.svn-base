package com.flp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flp.model.Assessment;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.ParentChildMapping;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserActiveTopic;
import com.flp.model.UserActivity;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.model.UserTest;
import com.flp.service.ParentService;
import com.flp.service.RetailUserService;
import com.flp.service.SchoolAdminService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
/**
 * 
 * @author Amresh
 *
 */
@Controller
@RequestMapping(value = "/parent")
public class ParentController
{

	@Autowired
	private ParentService parentService;

	@Autowired
	private RetailUserService retailUserService;

	@Autowired
	private StudentCommonService studentCommonService;

	@Autowired
	private UserService userService;

	@Autowired
	private SchoolAdminService schoolAdminService;
/**
 * 
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value = "/parentDashboard.htm")
	public ModelAndView parentDashboard(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("parent/parentDashBoard");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<ParentChildMapping> childrenList = parentService.getAllChildListByParentId(loginUser.getId());
		if (!childrenList.isEmpty())
		{
			Long studentId = 0l;
			String id = request.getParameter("studentId");
			if (id != null && id != "")
			{
				studentId = Long.parseLong(id);
			}
			if (studentId > 0)
			{
				getStudentDetails(studentId, mav, request);
				mav.addObject("stuId", studentId);
			}
			else if (childrenList.size() > 1)
			{
				mav.addObject("flag", "moreThanOneChild");

			}
			else
			{
				Long stuId = childrenList.get(0).getUserId();
				getStudentDetails(stuId, mav, request);
			}
			mav.addObject("childrenList", childrenList);
		}
		return mav;
	}
/**
 * 
 * @param stuId
 * @param mav
 * @param req
 */
	private void getStudentDetails(Long stuId, ModelAndView mav, HttpServletRequest req)
	{
		Map<Long, Integer> userSmartScoreMap = retailUserService.getAllTopicSmartScoreOfUser(stuId);
		User stuObj = studentCommonService.getUserObjById(stuId);
		req.getSession().setAttribute("userObj", stuObj);
		req.getSession().removeAttribute("subjectDetails");
		List<Long> subjectIds = new ArrayList<Long>();
		Integer limit = 6;
		Calendar cal = Calendar.getInstance();
		int m=cal.get(Calendar.MONTH) + 1;
		mav.addObject("m", m);
		
		if (stuObj.getUserType().equals(User.USERTYPE_DEMO) || stuObj.getUserType().equals(User.USERTYPE_USER))
		{
			Map<Integer, List<UserTest>> map = new HashMap<Integer, List<UserTest>>();
			for (int i = 1; i < 13; i++)
			{
				map.put(i, new ArrayList<UserTest>());
			}
			List<Subject> subjectList = retailUserService.getSubjects(stuId, stuObj.getBoardId(), stuObj.getSectionId());
			if(!subjectList.isEmpty()){
			req.getSession().setAttribute("subjectDetails", subjectList);
			
			if (!subjectList.isEmpty())
			{
				for (Subject s : subjectList)
				{
					subjectIds.add(s.getId());
				}
			}

			List<UserActivity> userActivityList = studentCommonService.getAllLastActivityOfRetailUser(stuId, stuObj.getBoardId(), limit, subjectIds);
			if (!userActivityList.isEmpty())
			{
				List<Long> topicIds = new ArrayList<Long>();
				for (UserActivity ua : userActivityList)
				{
					topicIds.add(ua.getTopicId());
				}
				Map<Long, Integer> avgTopicSmartScore = retailUserService.getAvgTopicSmartScore(topicIds);
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
				mav.addObject("userActivityList", userActivityList);
			}
			List<UserTest> testDetails = studentCommonService.getAttemptUnattemptTestDetails(stuObj.getId(), stuObj.getBoardId(), null, null);
			if(!testDetails.isEmpty()){
				for(UserTest ut:testDetails){
					cal.setTime(ut.getCreatedDate());
					Integer month = cal.get(Calendar.MONTH) + 1;
					ut.setMonthId(month);
					List<UserTest> assment = map.get(month);
					assment.add(ut);
				}
				mav.addObject("testDetails", map);
			}
			
			
		}
		}
		else if (stuObj.getUserType().equals(User.USERTYPE_STUDENT))
		{
			Map<Integer, List<Assessment>> map = new HashMap<Integer, List<Assessment>>();
			for (int i = 1; i < 13; i++)
			{
				map.put(i, new ArrayList<Assessment>());
			}
			mav.addObject("userType", stuObj.getUserType());
			Long schoolSessionId = schoolAdminService.getCurrentSchoolSession(stuObj.getSchoolId());
			List<SubjectSection> subjectSections = userService.getUserSubjects(schoolSessionId, stuObj.getId(), User.USERTYPE_STUDENT, stuObj.getSchoolId());
			List<Subject> subjectList = new ArrayList<Subject>();
			if (!subjectSections.isEmpty())
			{
				for (SubjectSection ss : subjectSections)
				{
					subjectIds.add(ss.getSubject().getId());
					subjectList.add(ss.getSubject());
				}
				req.getSession().setAttribute("subjectDetails", subjectList);
				List<UserActiveTopic> userActivity = studentCommonService.getUserActivityDetails(stuObj.getId(), schoolSessionId, subjectIds, limit);
				if (!userActivity.isEmpty())
				{
					List<Long> topicIds = new ArrayList<Long>();
					for (UserActiveTopic uat : userActivity)
					{
						topicIds.add(uat.getTopicId());
					}
					Map<Long, Integer> avgTopicSmartScore = retailUserService.getAvgTopicSmartScore(topicIds);
					for (UserActiveTopic uat : userActivity)
					{
						{
							Integer ss = userSmartScoreMap.get(uat.getTopicId());
							if (ss != null)
							{
								uat.setSmartScore(ss);
							}
							Integer avgSS = avgTopicSmartScore.get(uat.getTopicId());
							if (avgSS != null)
							{
								uat.setAvgSmartScore(avgSS);
							}
						}
					}
					mav.addObject("userActivityList", userActivity);
				}
				List<Assessment> assessmentList = studentCommonService.getAllEtestAndAssignedTestByUser(stuObj.getId(), schoolSessionId);
				
				if (!assessmentList.isEmpty())
				{
					List<Long> assIds = new ArrayList<Long>();
					for (Assessment a : assessmentList)
					{
						if (a.getStudentTest() != null && a.getStudentTest().size() > 0)
							assIds.add(a.getId());

					}
					Map<Long, String> scoreMap = studentCommonService.getHighScoreAndTotalAttemptAssessment(assIds);
					for (Assessment a : assessmentList)
					{
						if (a.getAssignedAssessment() != null && a.getAssignedAssessment().size() > 0)
						{
							AssignedAssessment aa = (AssignedAssessment) a.getAssignedAssessment().iterator().next();
							String val = scoreMap.get(a.getId());
							if (val != null)
							{
								String[] data = val.split("\\,");
								a.setCount(Integer.parseInt(data[1]));
								a.setMaxMarks(Float.parseFloat(data[0]));

							}
							cal.setTime(aa.getAssignedDate());
							Integer month = cal.get(Calendar.MONTH) + 1;
							List<Assessment> assment = map.get(month);
							assment.add(a);

						}
					}
				}
				mav.addObject("assessmentMap", map);
			}
		}

	}
/**
 * 
 */
	@RequestMapping(value = "/studentPerformance.htm")
	public ModelAndView studentPerformance(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("parent/studentPerformance");
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		List<ParentChildMapping> childrenList = parentService.getAllChildListByParentId(loginUser.getId());
		Long userId = Long.parseLong(request.getParameter("userId"));
		Long subjectId = Long.parseLong(request.getParameter("subjectId"));;
		User userObj = userService.getUserById(userId);
		List<Subject> subjectList = getSubjectDetails(userObj);
		request.getSession().removeAttribute("subjectDetails");
		request.getSession().setAttribute("subjectDetails", subjectList);
		request.getSession().setAttribute("userObj", userObj);
		mav.addObject("subjectList", subjectList);
		mav.addObject("childrenList", childrenList);
		mav.addObject("subjectId", subjectId);
		mav.addObject("userId", userId);
		return mav;
	}
/**
 * 
 * @param userObj
 * @return
 */
	private List<Subject> getSubjectDetails(User userObj)
	{
		List<Subject> subjectList = new ArrayList<Subject>();
		if (userObj.getUserType().equals(User.USERTYPE_DEMO) || userObj.getUserType().equals(User.USERTYPE_USER))
		{
			subjectList = retailUserService.getSubjects(userObj.getId(), userObj.getBoardId(), userObj.getSectionId());
		}
		else if (userObj.getUserType().equals(User.USERTYPE_STUDENT))
		{
			Long schoolSessionId = schoolAdminService.getCurrentSchoolSession(userObj.getSchoolId());
			List<SubjectSection> subjectSections = userService.getUserSubjects(schoolSessionId, userObj.getId(), User.USERTYPE_STUDENT, userObj.getSchoolId());
			if (!subjectSections.isEmpty())
			{
				for (SubjectSection ss : subjectSections)
				{
					subjectList.add(ss.getSubject());
				}
			}
		}
		return subjectList;
	}
/**
 * 
 * @param request
 * @param response
 * @param userId
 * @param subjectId
 * @return
 */
	@RequestMapping(value = "/getPerformanceReport/{userId}/{subjectId}")
	@ResponseBody
	public ModelAndView getPerformanceReport(HttpServletRequest request, HttpServletResponse response, @PathVariable Long userId, @PathVariable Long subjectId)
	{
		ModelAndView mav = new ModelAndView("parent/performanceReport");
		User user = studentCommonService.getUserObjById(userId);
	
		List<Chapter> chapterList = studentCommonService.getChaptersTopicsBySubjectWithAnswerDetails(subjectId, user.getGradeId(), user.getId());
		if (chapterList != null && chapterList.size() > 0)
		{
			Map<Long, Integer> userSmartScoreMap = retailUserService.getAllTopicSmartScoreOfUser(user.getId());
			for (Chapter c : chapterList)
			{
				LinkedHashSet<Topic> topicSet = new LinkedHashSet<Topic>();
				Integer questionCount = 0;
				Integer rightQuestionCount = 0;
				List<Long> topicIds = new ArrayList<Long>();
				for (Topic t : c.getTopics())
				{
					if (!topicSet.contains(t))
					{
						topicIds.add(t.getId());
						questionCount = questionCount + t.getQuestionTopic().size();
						int count = t.getQuestionTopic().size();
						if (!t.getUserQuestionAnswer().isEmpty())
						{
							int rightCount = 0;
							int wrongCount = 0;
							int skipCount = 0;
							for (UserQuestionAnswer uqa : t.getUserQuestionAnswer())
							{
								if (uqa.getIsAttempted().equals(UserQuestionAnswer.ATTEMPTED))
								{
									if (uqa.getCorrectIncorrect().equals(UserQuestionAnswer.IS_CORRECT))
									{
										rightCount++;
										rightQuestionCount++;
									}
									else if (uqa.getCorrectIncorrect().equals(UserQuestionAnswer.IS_INCORRECT))
									{
										wrongCount++;
									}
								}
								else
								{
									skipCount++;
								}
							}
							Integer topicPercentage = 0;
							if (count > 0)
							{
								double data1 = ((Double.parseDouble(String.valueOf(rightCount)) * 100) / Double.parseDouble(String.valueOf(count)));
								topicPercentage = (int) Math.round(data1);
							}
							t.setPercent(topicPercentage);
							t.setCount(count);
							t.setRightCount(rightCount);
							t.setWrongCount(wrongCount);
							t.setSkipCount(skipCount);
						}
					}
				}
				Map<Long, Integer> avgTopicSmartScore = retailUserService.getAvgTopicSmartScore(topicIds);

				for (Topic t : c.getTopics())
				{
					if (!topicSet.contains(t))
					{

						Integer gScore = userSmartScoreMap.get(t.getId());
						if (gScore != null)
						{
							t.setSmartScore(gScore);
						}
						Integer avgScore = avgTopicSmartScore.get(t.getId());
						if (avgScore != null)
						{
							t.setAvgSmartScore(avgScore);
						}
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
				c.setTopicSet(topicSet);
			}
		}
		mav.addObject("chapterList", chapterList);

		return mav;
	}
/**
 * 
 * @param request
 * @param response
 * @param userId
 * @return
 */
	@RequestMapping(value = "/getSubjectDetailByUserId/{userId}")
	@ResponseBody
	public List<Subject> getSubjectDetailByUserId(HttpServletRequest request, HttpServletResponse response, @PathVariable Long userId)
	{
		User userObj = userService.getUserById(userId);
		List<Subject> subjectList = getSubjectDetails(userObj);
		request.getSession().removeAttribute("subjectDetails");
		request.getSession().setAttribute("subjectDetails", subjectList);

		request.getSession().setAttribute("userObj", userObj);
		return subjectList;

	}
/**
 * 
 * @param studentName
 * @return
 */
	@RequestMapping(value="/getSearchedStudent", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getSearchedStudent(@RequestParam(value="query") String studentName)
	{
		List<User> list = parentService.getSearchedStudents(studentName.trim());
		for(User u : list)
		{
			u.setPassword(null);
			u.setResettoken(null);
			u.setSubscriptionid(null);
			u.setAddress(null);
		}
		
		return list;
	}
	/**
	 * 
	 * @param studentId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addSearchedStudent")
	@ResponseBody
	public String addSearchedStudent(@RequestParam(value="studentId") Long studentId, HttpServletRequest request)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		
		return parentService.addSearchedStudent(loginUser.getId(), studentId);
	}
}
