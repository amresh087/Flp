package com.flp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flp.model.QuestionTopic;
import com.flp.service.SchoolTeacherService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;

/** @author Amresh */
@Controller
public class CommonController
{

	@Autowired
	private StudentCommonService studentCommonService;
	@Autowired
	private UserService userService;

	@Autowired
	private SchoolTeacherService schoolTeacherService;

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/header.htm")
	public ModelAndView headerPage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("header");
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/invalid.htm")
	public ModelAndView invalid(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("unAuthorized");
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/about.htm")
	public ModelAndView about(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("about");
		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/out-leadership.htm")
	public ModelAndView outleadership(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("out-leadership");
		return mav;
	}

	/** @param request
	 * @param response
	 * @param topicId
	 * @return */

	@RequestMapping("/common/getQuestionPagination")
	@ResponseBody
	public List<QuestionTopic> getQuestionTopicForPagination(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "topicId") Long topicId)
	{

		String offset = "1";
		int result = 1;
		List<QuestionTopic> list = null;
		if (offset != null)
		{
			int offsetreal = Integer.parseInt(offset);
			offsetreal = offsetreal * 10;
			list = schoolTeacherService.getPageQuestionsByTopic(result, offsetreal, topicId);

		}
		else
		{
			list = schoolTeacherService.getPageQuestionsByTopic(result, 0, topicId);
			int size = schoolTeacherService.getSize(topicId);

		}

		ObjectMapper mapper = new ObjectMapper();

		return list;
	}

}