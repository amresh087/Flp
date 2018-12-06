package com.flp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flp.annotations.LoginAuthentication;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.service.UserService;
import com.flp.util.Utility;

/** @author Amresh */
@Controller
public class LearningController
{
	@Autowired
	UserService userService;

	/** @param request
	 * @param response
	 * @return */
	@LoginAuthentication
	@RequestMapping(value = "/learning/index.htm", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("learning");

		UserForm user = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("subjectList", userService.getLearningSubjects(user));

		return mav;
	}

	/** @param chapterDetails
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception */
	@LoginAuthentication
	@RequestMapping(value = "/learning/topic.htm", method = RequestMethod.GET)
	public ModelAndView topic(@RequestParam(value = "chapter") String chapterDetails, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String str = Utility.decrypt(chapterDetails);
		String[] chapDetails = str.split("#");
		String subjectName = chapDetails[0];
		String chapterName = chapDetails[1];
		Long chapterId = Long.parseLong(chapDetails[2]);

		ModelAndView mav = new ModelAndView("topic");
		UserForm user = (UserForm) request.getSession().getAttribute("user");
		mav.addObject("topicList", userService.getTopics(user.getBoardId(), user.getGradeId(), chapterId));

		List<Long> topicIds = new ArrayList<Long>();
		if (user.getUserType() == User.USERTYPE_DEMO)
			topicIds = userService.getDemoTopicIds(chapterId);

		mav.addObject("demoTopicIds", topicIds);
		mav.addObject("subjectName", subjectName);
		mav.addObject("chapterName", chapterName);

		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/learning/practiceZone.htm", method = RequestMethod.GET)
	public ModelAndView practiceZone(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("practiceZone");

		return mav;
	}

}