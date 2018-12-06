package com.flp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flp.model.Question;
import com.flp.model.UserForm;
import com.flp.service.AssessmentService;
import com.sun.org.glassfish.gmbal.Description;
/**
 * 
 * @author Amresh
 *
 */
@Controller
@Description(value="will controll all assessment related work")
public class AssessmentController {
	@Autowired
	AssessmentService assessmentService;
	
	
	/**
	 * 
	 * @param topicId
	 * @param ss
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getNextQuestionBySS")
	public String getQuestion(@RequestParam(value="topicId")long topicId, @RequestParam(value="ss",required=false)int ss, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response){
		UserForm user=(UserForm) session.getAttribute("user");
		if(ss==-1)
			ss=assessmentService.getSmartScore(topicId, "TOPIC", user.getId(), user.getGradeId());
		session.setAttribute("ss", ss);
		Question q=assessmentService.getNextQuestion(ss, topicId, user.getId());
		model.addAttribute("question", q);
		model.addAttribute("topicId", topicId);
		return "assessment";
	}

	/**Gets the smart score for given Object type 
	 * @author dv
	 * @param objectid- SubjectId, ChapterId,TopicId 
	 * @param ObjectType: string constant: SUBJECT, CHAPTER, TOPIC, NULL(for overall smartscore) 
	 * @return smartScore
	 */
	@ResponseBody
	@RequestMapping(value="/getSmartScore")
	public short getSmartScore(@RequestParam(value="objectId") long objectId, @RequestParam(value="type")String objectType, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		UserForm user=(UserForm) session.getAttribute("user");
		
		short smartScore=assessmentService.getSmartScore(objectId, objectType, user.getId(),user.getGradeId());
		session.setAttribute("ss", smartScore);
		return smartScore;
	}
	
	
	/**Save Question
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param questionId
	 * @param isAttempted
	 * @param isCorrect
	 * @param answers
	 * 
	 * @return
	 */
	@RequestMapping(value="/saveQuestion", method=RequestMethod.POST)
	public @ResponseBody String saveQuestion(HttpServletRequest request, HttpServletResponse response){
		int msg=assessmentService.saveQuestion(request);
		((UserForm)request.getSession().getAttribute("user")).setgScore((byte) msg);
		//save question here
	
		return "success";
	}
	
	/**
	 * Get the question from questions attempted by user
	 * @param session
	 * @param questionType
	 * @param topicIds // values must be 0:Fresh, 1:Correct, 2:Wrong, 3:UnAttempted Questions by user
	 * @return Next Question
	 */
	@ResponseBody
	@RequestMapping("getNextQuestion")
	public Question getQuestion(HttpSession session,@RequestParam("questionType")int questionType , HttpServletRequest request, HttpServletResponse response,@RequestParam(value="topicId")long... topicIds){
		UserForm user=(UserForm) session.getAttribute("user");
		return assessmentService.getQuestion(user.getId(),questionType ,topicIds);
		
		
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("assessment")
	public String getAssessmentPage(HttpServletRequest request, HttpServletResponse response)
	{
		return "assessment";
	}

}
