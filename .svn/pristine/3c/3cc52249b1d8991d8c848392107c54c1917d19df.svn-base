package com.flp.admin.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flp.model.Board;
import com.flp.model.Chapter;
import com.flp.model.Grade;
import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.service.StudentCommonService;
/**
 * 
 * @author Amresh
 *
 */
@Controller
public class AdminControllerOLD {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private StudentCommonService studentCommonService;

	@RequestMapping(value = "/demo")
	public ModelAndView demo(Model model) {

		List<Board> boardList = studentCommonService.getAllBoardList();
		List<Grade> gradeList = studentCommonService.getAllGradeList();
		List<Subject> subjectList = studentCommonService.getAllSubjectList();
		List<Chapter> chapterList = studentCommonService.getAllChapterList();
		List<Topic> topicList= studentCommonService.getAllTopicList();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("subjectList",subjectList);
		model.addAttribute("chapterList",chapterList);
		model.addAttribute("topicList",topicList);
		
		
		
		//model.addAttribute("chapterList",chapterList);

	//	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*************************");
		return new ModelAndView("demo");
	}


}
