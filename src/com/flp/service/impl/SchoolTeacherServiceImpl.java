package com.flp.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.AnswerDao;
import com.flp.dao.ChapterDao;
import com.flp.dao.GradeDao;
import com.flp.dao.ModuleDao;
import com.flp.dao.ModuleTopicDao;
import com.flp.dao.ModuleTypeDao;
import com.flp.dao.QuestionDao;
import com.flp.dao.QuestionTopicDao;
import com.flp.dao.QuestionTypeDao;
import com.flp.dao.SchoolTeacherDao;
import com.flp.dao.SectionDao;
import com.flp.dao.StudentCommonDao;
import com.flp.dao.TopicDao;
import com.flp.model.Answer;
import com.flp.model.Assessment;
import com.flp.model.AssessmentQuestion;
import com.flp.model.AssignTopicResources;
import com.flp.model.AssignedAssessment;
import com.flp.model.Chapter;
import com.flp.model.Grade;
import com.flp.model.Module;
import com.flp.model.ModuleTopic;
import com.flp.model.ModuleType;
import com.flp.model.Question;
import com.flp.model.QuestionComments;
import com.flp.model.QuestionTopic;
import com.flp.model.QuestionType;
import com.flp.model.SchoolStudentTest;
import com.flp.model.Section;
import com.flp.model.Session;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.Topic;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserQuestionAnswer;
import com.flp.service.SchoolTeacherService;
import com.flp.util.Utility;
import com.flp.vo.GlobalVO;

@Service
public class SchoolTeacherServiceImpl implements SchoolTeacherService
{

	@Autowired
	private SchoolTeacherDao schoolTeacherDao;

	@Autowired
	private QuestionTopicDao questiTopicDao;
	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private QuestionTopicDao questionTopicDao;

	@Autowired
	private QuestionTypeDao questionTypeDao;

	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private ModuleTypeDao moduleTypeDao;

	@Autowired
	private ModuleTopicDao moduleTopicDao;

	@Autowired
	private GradeDao gradeDao;

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private TopicDao topicDao;

	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired
	private StudentCommonDao studentCommonDao;

	
	
	@Override
	@Transactional
	public List<Assessment> getUpcomingAssessmentDetails(UserForm loginUser)
	{
		return schoolTeacherDao.getUpcomingAssessmentDetails(loginUser);
	}

	@Transactional
	@Override
	public List<StudentTeacherSession> getTeacherSubjectSection(long id, Long sessionId)
	{
		return schoolTeacherDao.getTeacherSubjectSection(id, sessionId);
	}

	@Transactional
	@Override
	public List<Chapter> getChpater(long gradeId, long subjectId)
	{
		List<Chapter> chapterList = schoolTeacherDao.getChpater(gradeId, subjectId);
		for (Chapter c : chapterList)
		{

			for (Topic t : c.getTopics())
			{
				 
				t.setChapter(null);
			}
		}
		return chapterList;
	}

	@Override
	@Transactional
	public List<QuestionTopic> getQuestionList(String topicIds, long userId, long schoolId, int i)
	{
		return schoolTeacherDao.getQuestionList(topicIds, userId, schoolId, i);
	}

	@Transactional
	@Override
	public String createAssessment(String assName, String startDate, String endDate, long gradeId, long subjectId, Long schoolId, List<String> mappingList, String instruction, int testType, int duration, long userId, Long sessionId, String sequence, String chapterIds, String topics, String studentIds, Long secId)
	{
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);
		// DateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);

		String newEndDate = endDate;
		if (testType == Assessment.ASSIGNED_TEST || endDate.equals("00/default/0000 00:00") || endDate.equals("____/__/__ __:__"))
		{
			Session s = (Session) schoolTeacherDao.getSessionObj(sessionId);
			newEndDate = Utility.convertDateToString(s.getEndDate(), "dd/MMM/yyyy HH:mm");

		}

		String[] seqString = sequence.split(",");
		List<String> seqList = Arrays.asList(seqString);

		Assessment ass = new Assessment();
		try
		{
			Date sdate = format.parse(startDate);
			Date edate = format.parse(newEndDate);

			ass.setStartDate(sdate);
			ass.setEndDate(edate);
			
			ass.setDateTimeZone(Utility.convertDateToStringWithZone(sdate));
			ass.setDateTimeZoneEnd(Utility.convertDateToStringWithZone(edate) );
			 
			
			
			
			
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ass.setTestName(assName);
		ass.setCreatedBy(userId);
		ass.setStatus(2);
		ass.setCreatedOn(new Date());
		ass.setSubjectId(new Long(subjectId));
		ass.setGradeId(new Long(gradeId));
		ass.setSchoolId(new Long(schoolId));
		ass.setInstruction(instruction.replaceAll("\n", "<br>").replaceAll("\r", "<br>"));
		ass.setSessionId(sessionId);

		ass.setType(testType);
		ass.setDuration(duration);
		Set<AssessmentQuestion> list = new HashSet<AssessmentQuestion>();
		Set<AssignedAssessment> assignStudent = new HashSet<AssignedAssessment>();

		float totalMarks = 0;
		for (String qIdMarksSerial : mappingList)
		{
			String[] str = qIdMarksSerial.split("#");
			AssessmentQuestion aq = new AssessmentQuestion();
			aq.setQuestionId(Long.parseLong(str[0]));
			aq.setMarks(Float.parseFloat(str[1]));
			totalMarks += Float.parseFloat(str[1]);
			// aq.setSerial(Integer.parseInt(str[2]));
			aq.setSerial(seqList.indexOf(str[0]) + 1);
			aq.setCreatedOn(new Date());
			aq.setStatus(1);
			aq.setAssessment(ass);
			list.add(aq);
		}

		String[] sIds = studentIds.split(",");
		for (String sId : sIds)
		{
			AssignedAssessment assign = new AssignedAssessment();
			// assign.setAssessment(ass);
			assign.setStatus(AssignedAssessment.STATUS_ACTIVE);
			// assign.setAssessment(ass);
			assign.setAssignedDate(new Date());
			assign.setUserId(Long.parseLong(sId));
			assign.setAssignedBy(userId);
			assign.setSectionId(secId);
			assignStudent.add(assign);

		}
		ass.setMaxMarks(totalMarks);
		ass.setAssessmentQuestion(list);
		ass.setAssignedAssessment(assignStudent);
		ass.setTopicIds(topics);
		ass.setChapterIds(chapterIds);
		return schoolTeacherDao.createAssessment(ass);
	}

	@Override
	@Transactional
	public List<Assessment> getAllAssessmentListbyUser(Long schoolId, Long sessionId, Long loginUserId)
	{
		return schoolTeacherDao.getAllAssessmentListbyUser(schoolId, sessionId, loginUserId);
	}

	@Override
	@Transactional
	public void assignAssessment(UserForm loginUser, Long[] studentIds, Long sectionId, Long assessmentId)
	{
		Date date = new Date();
		for (Long studentId : studentIds)
		{
			Boolean assignStatus = schoolTeacherDao.isAlreadyAssigned(studentId, assessmentId);
			if (!assignStatus)
			{
				AssignedAssessment aa = new AssignedAssessment();
				aa.setAssessmentId(assessmentId);
				aa.setAssignedBy(loginUser.getId());
				aa.setAssignedDate(date);
				aa.setSectionId(sectionId);
				aa.setStatus(AssignedAssessment.STATUS_ACTIVE);
				aa.setUserId(studentId);
				schoolTeacherDao.assignedAssessment(aa);
			}
		}

	}

	@Override
	@Transactional
	public void publishedAssessment(Long assessmentId)
	{
		schoolTeacherDao.publishedAssessment(assessmentId);

	}

	@Override
	@Transactional
	public List<Topic> getTopicDetailsByChapter(Long subjectId, Long chapterId)
	{
		return schoolTeacherDao.getTopicDetailsByChapter(subjectId, chapterId);
	}

	@Override
	@Transactional
	public Integer assignedTopicCountByTopic(Long topicId, Long userId)
	{
		return schoolTeacherDao.assignedTopicCountByTopic(topicId, userId);
	}

	@Override
	@Transactional
	public void saveTopicResources(AssignTopicResources atr)
	{
		schoolTeacherDao.saveTopicResources(atr);

	}
	
	
	

	@Override
	@Transactional
	public Boolean isAlreadyAssignedTopic(AssignTopicResources atr)
	{
		return schoolTeacherDao.isAlreadyAssignedTopic(atr);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getSectionsByTopicId(Long topicId, Integer isTopic)
	{
		return schoolTeacherDao.getSectionsByTopicId(topicId, isTopic);
	}

	@Override
	@Transactional
	public void unAssignTopicFromSection(Long sectionId, Long topicId)
	{
		schoolTeacherDao.unAssignTopicFromSection(sectionId, topicId);

	}

	@Override
	@Transactional
	public List<Module> getModulesByTopic(Long topicId)
	{
		return schoolTeacherDao.getModulesByTopic(topicId);
	}

	@Override
	@Transactional
	public Integer assignedStudentCountByModuleId(Long moduleId, Long userId)
	{
		return schoolTeacherDao.assignedStudentCountByModuleId(moduleId, userId);
	}

	@Override
	@Transactional
	public Boolean isAlreadyAssignedModule(AssignTopicResources atr)
	{
		return schoolTeacherDao.isAlreadyAssignedModule(atr);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getSectionsByModuleId(Long topicId, Long moduleId, Integer isResources)
	{
		return schoolTeacherDao.getSectionsByModuleId(topicId, moduleId, isResources);
	}

	@Override
	@Transactional
	public void unAssignModuleFromSection(Long sectionId, Long topicId, Long moduleId)
	{
		schoolTeacherDao.unAssignModuleFromSection(sectionId, topicId, moduleId);

	}

	@Override
	@Transactional
	public List<AssignedAssessment> getAllAssignedAssessmentStudentList(Long asssessmentId, Long assignedBy)
	{
		return schoolTeacherDao.getAllAssignedAssessmentStudentList(asssessmentId, assignedBy);
	}
	
	

   @Override
	@Transactional(rollbackFor = { IOException.class })
	public String saveUpdateQuestionUpdate(GlobalVO globalVO) throws IOException
	{
		
		 
			String message = "success"; 
			
			//System.out.println("globalVO.isToRemove()=="+globalVO.isToRemove());
			Integer questionCategoryId = globalVO.getQuestionCategoryId().intValue();
			/* boolean flag = schoolTeacherDao.isMappedToTest(globalVO.getQuestionId());
			 * 
			 * if (flag) //Questions are updated without taking anything into account. { */
			switch (questionCategoryId)
			{
				case 1: // For textual questions

					break;

				case 2: // For image type questions

					if (globalVO.isToRemove() == true) // When removing question
					{
						questionTopicDao.updateQuestionTopic(String.valueOf(globalVO.getQuestionTopicId()), QuestionTopic.STATUS_INACTIVE);

						return message;
					}
					else{
					
					

					String msg = AddUpdateQuestionDetails2(globalVO, message);
					
					String[] msg1 = msg.split("\\@");
					 /* Saving questionTopic entries
					 QuestionTopic questionTopic = (globalVO.getQuestionTopicId() != null ? questionTopicDao.getEntity(globalVO.getQuestionTopicId()) : new QuestionTopic());
					 questionTopicDao.getQuestionTopics(questionId, topicId, status)
					*/
					
					QuestionTopic questionTopic=questionTopicDao.updateQuestionTopicId(globalVO.getQuestionTopicId(), QuestionTopic.STATUS_ACTIVE);
					questionTopic.setQuestionId(Long.parseLong(msg1[0]));
					questionTopic.setTopicId(globalVO.getTopicId());
					questionTopic.setModifiedDate(new Date());
					questionTopic.setStatus(QuestionTopic.STATUS_ACTIVE);
					questionTopic.setCognitiveLevelId(globalVO.getQuestionCognitiveLevelId());
					questionTopic.setDifficultyLevelId(globalVO.getQuestionDifficultyLevelId());
					if(null!=globalVO.getZoneId()){
					questionTopic.setZoneLevelId(globalVO.getZoneId());
					
					} 
					questionTopicDao.saveOrUpdateEntity(questionTopic);
					}
					

					break;

				case 3: // For video type questions

					break;

				default:

					break;
			}
			return (message.contains(".") ? "success" : message); // If message
																	 // contains
																	 // image
																	 // (.jpg)
																	 // then
																	 // return
																	 // success.
			/* } else return "used"; */

		}



private String AddUpdateQuestionDetails2(GlobalVO globalVO, String message) throws IOException
	{  
		
		//System.out.println("globalVO.getQuestionId()"+globalVO.getQuestionId());
	
	    Question question=	questionDao.getQuestionData(globalVO.getQuestionId())	; 
		question.setCognitiveLevelId(globalVO.getQuestionCognitiveLevelId());
		question.setDifficultyLevelId(globalVO.getQuestionDifficultyLevelId());
		question.setQuestionTypeId(globalVO.getQuestionTypeId());
		question.setQuestionCategoryId(globalVO.getQuestionCategoryId());
		question.setCreatedBy(globalVO.getLoginUserId());
		question.setModifiedDate(new Date());
		question.setStatus(Question.STATUS_ACTIVE);
		
		question.setHinttypeid(globalVO.getHintTypeTextSelector());
		question.setExplanationId(globalVO.getExplanationTypeSelector());
		
		if(globalVO.getHintTypeTextSelector()==3 ||globalVO.getHintTypeTextSelector()==4){
			
			question.setHintContent(globalVO.getHintTypeImg().getName());
		}
		else{
			question.setHintContent(globalVO.getHintTypeText());
		}
		
if(globalVO.getExplanationTypeSelector()==22 ||globalVO.getExplanationTypeSelector()==23){
			
			question.setExplanationContent(globalVO.getExplanationTypeImg().getName());
		}
		else{
			question.setExplanationContent(globalVO.getExplanationTypeText());
		}
		
		
		if (globalVO.getLoginUserType() != null && globalVO.getLoginUserType() != User.USERTYPE_ADMIN)
		{
			// For School Teacher or SME // For admin, schoolId will be null
			question.setSchoolId(globalVO.getSchoolId());
		}

		if (globalVO.getQuestion() != null && globalVO.getQuestion().trim().length() > 0)
		{
			question.setDescription(globalVO.getQuestion().trim().replaceAll("\r", " ").replaceAll("\n", " ")); // Removing next line syntax

			questionDao.saveOrUpdateEntity(question);
		} 

		// ##### Answer1 ######// 
		Answer answer1=answerDao.getansOptions(globalVO.getOptionId1()); 
		//answer1 = (globalVO.getOptionId1() != null ? answerDao.getEntity(globalVO.getOptionId1()) : new Answer());
		answer1.setIsCorrect((globalVO.getIsCorrectOption1() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer1.setStatus(Answer.STATUS_ACTIVE);
		answer1.setQuestionId(question.getId());
		answer1.setModifiedDate(new Date());

		
		if (globalVO.getOption1() != null && !globalVO.getOption1().trim().equals(""))
		{
			answer1.setDescription(globalVO.getOption1().trim().replaceAll("\r", " ").replaceAll("\n", " ")); // Removing next line syntax
			answerDao.saveOrUpdateEntity(answer1);
		}
		  
		// ##### Answer2 ######//
		//Answer answer2 = (globalVO.getOptionId2() != null ? answerDao.getEntity(globalVO.getOptionId2()) : new Answer());
		Answer answer2=answerDao.getansOptions(globalVO.getOptionId2()); 
		answer2.setDescription(globalVO.getOption2().trim().replaceAll("\r", " ").replaceAll("\n", " "));
		answer2.setIsCorrect((globalVO.getIsCorrectOption2() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer2.setStatus(Answer.STATUS_ACTIVE);
		answer2.setQuestionId(question.getId());
		answer2.setModifiedDate(new Date());

		if (globalVO.getOption2() != null && !globalVO.getOption2().trim().equals(""))
		{
			answerDao.saveOrUpdateEntity(answer2);
		}

		// ##### Answer3 ######//
		//Answer answer3 = (globalVO.getOptionId3() != null ? answerDao.getEntity(globalVO.getOptionId3()) : new Answer());
		Answer answer3=answerDao.getansOptions(globalVO.getOptionId3());
		System.out.println("globalVO.getOption3()="+globalVO.getOption3());
		if (globalVO.getOption3() != null && !globalVO.getOption3().trim().equals("")){
		answer3.setDescription(globalVO.getOption3().trim().replaceAll("\r", " ").replaceAll("\n", " "));
		answer3.setIsCorrect((globalVO.getIsCorrectOption3() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer3.setStatus(Answer.STATUS_ACTIVE);
		answer3.setQuestionId(question.getId());
		answer3.setModifiedDate(new Date());
		}
		
		if (globalVO.getOption3() != null && !globalVO.getOption3().trim().equals(""))
		{
			answerDao.saveOrUpdateEntity(answer3);
		}

		// ##### Answer4 ######//
		//Answer answer4 = (globalVO.getOptionId4() != null ? answerDao.getEntity(globalVO.getOptionId4()) : new Answer());
		Answer answer4=answerDao.getansOptions(globalVO.getOptionId4()); 
		System.out.println("globalVO.getOption4()="+globalVO.getOption4());
		
		if (globalVO.getOption4() != null && !globalVO.getOption4().trim().equals("")){
		answer4.setDescription(globalVO.getOption4().trim().replaceAll("\r", " ").replaceAll("\n", " "));
		answer4.setIsCorrect((globalVO.getIsCorrectOption4() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer4.setStatus(Answer.STATUS_ACTIVE);
		answer4.setQuestionId(question.getId());
		answer4.setModifiedDate(new Date());
		}
		if (globalVO.getOption4() != null && !globalVO.getOption4().trim().equals(""))
		{
			answerDao.saveOrUpdateEntity(answer4);
		}

		// Saving image (if provided) by passing question and answer ids.

		if (globalVO.getQuestionImage() != null && globalVO.getQuestionImage().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getQuestionImage(), question.getId(), null, globalVO.getLoginUserId(), "Q");

			question.setImagePath(message);
			questionDao.saveOrUpdateEntity(question);
		}else if(globalVO.isQuestionImgCheck()!=null&&globalVO.isQuestionImgCheck().equals("on")){
			
			question.setImagePath("");
	        questionDao.saveOrUpdateEntity(question);
		}

		if (globalVO.getOption1Image() != null && globalVO.getOption1Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption1Image(), question.getId(), answer1.getId(), globalVO.getLoginUserId(), "A");
 
			answer1.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer1);
		}else if(globalVO.isOption1ImgCheck()!=null&&globalVO.isOption1ImgCheck().equals("on")){
			
	answer1.setImagePath("");
//	answerDao.saveOrUpdateEntity(answer1);
		}

		if (globalVO.getOption2Image() != null && globalVO.getOption2Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption2Image(), question.getId(), answer2.getId(), globalVO.getLoginUserId(), "A");

			answer2.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer2);
		}else if(globalVO.isOption2ImgCheck()!=null&&globalVO.isOption2ImgCheck().equals("on")){
			
	answer2.setImagePath("");
	answerDao.saveOrUpdateEntity(answer2);
		}

		if (globalVO.getOption3Image() != null && globalVO.getOption3Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption3Image(), question.getId(), answer3.getId(), globalVO.getLoginUserId(), "A");

			answer3.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer3);
		}else if(globalVO.isOption3ImgCheck()!=null&&globalVO.isOption3ImgCheck().equals("on")){
			
	answer3.setImagePath("");
	answerDao.saveOrUpdateEntity(answer3);
		}
		
		
       //////////////////////////////////////////////////////////////////////////////////////////
		
           if (globalVO.getHintTypeImg() != null && globalVO.getHintTypeImg().getBytes().length > 0)
                {
             message = Utility.saveImageToDisk(globalVO.getHintTypeImg(), question.getId(), globalVO.getHintTypeTextSelector(), globalVO.getLoginUserId(), "H");

                 question.setHintContent(message);
                questionDao.saveOrUpdateEntity(question);


                }



                   if (globalVO.getExplanationTypeImg() != null && globalVO.getExplanationTypeImg().getBytes().length > 0)
                        {
                    message = Utility.saveImageToDisk(globalVO.getExplanationTypeImg(), question.getId(), globalVO.getExplanationTypeSelector(), globalVO.getLoginUserId(), "E");

                           question.setExplanationContent(message);
                        questionDao.saveOrUpdateEntity(question);


                          }









////////////////////////////////////////////////////////////////////////////////////////////

		
		

		if (globalVO.getOption4Image() != null && globalVO.getOption4Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption4Image(), question.getId(), answer4.getId(), globalVO.getLoginUserId(), "A");

			answer4.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer4);
		}else if(globalVO.isOption4ImgCheck()!=null&&globalVO.isOption4ImgCheck().equals("on")){
			
	answer4.setImagePath("");
	answerDao.saveOrUpdateEntity(answer4);
		}

		questionDao.saveOrUpdateEntity(question); // Updating the image urls in question and answer tables

		return question.getId() + "@" + message;
	}



	
	
	
	
	

	@Override
	@Transactional(rollbackFor = { IOException.class }) // Making transaction
														 // rollback for checked
														 // exception.
	public String saveUpdateQuestion(GlobalVO globalVO) throws IOException
	{
		String message = "success";

		Integer questionCategoryId = globalVO.getQuestionCategoryId().intValue();
		/* boolean flag = schoolTeacherDao.isMappedToTest(globalVO.getQuestionId());
		 * 
		 * if (flag) //Questions are updated without taking anything into account. { */
		switch (questionCategoryId)
		{
			case 1: // For textual questions

				break;

			case 2: // For image type questions

				if (globalVO.isToRemove() == true) // When removing question
				{
					questionTopicDao.updateQuestionTopic(String.valueOf(globalVO.getQuestionTopicId()), QuestionTopic.STATUS_INACTIVE);

					return message;
				}

				String msg = AddUpdateQuestionDetails(globalVO, message);
				String[] msg1 = msg.split("\\@");
				// Saving questionTopic entries
				QuestionTopic questionTopic = (globalVO.getQuestionTopicId() != null ? questionTopicDao.getEntity(globalVO.getQuestionTopicId()) : new QuestionTopic());
				questionTopic.setQuestionId(Long.parseLong(msg1[0]));
				questionTopic.setTopicId(globalVO.getTopicId());
				questionTopic.setModifiedDate(new Date());
				questionTopic.setStatus(QuestionTopic.STATUS_ACTIVE);
				questionTopic.setCognitiveLevelId(globalVO.getQuestionCognitiveLevelId());
				questionTopic.setDifficultyLevelId(globalVO.getQuestionDifficultyLevelId());
				questionTopic.setZoneLevelId(globalVO.getZoneId());
				questionTopic.setZoneLevelId(globalVO.getQuestionLearnZoneId());
				questionTopic.setLoId(globalVO.getLoId());
				
				
				
				
				
				questionTopicDao.saveOrUpdateEntity(questionTopic);

				break;

			case 3: // For video type questions

				break;

			default:

				break;
		}
		return (message.contains(".") ? "success" : message); // If message
																 // contains
																 // image
																 // (.jpg)
																 // then
																 // return
																 // success.
		/* } else return "used"; */

	}

	private String AddUpdateQuestionDetails(GlobalVO globalVO, String message) throws IOException
	{


	
		// ##### Questions ######//
		Question question = (globalVO.getQuestionId() != null ? questionDao.getEntity(globalVO.getQuestionId()) : new Question());
		// question.setCognitiveLevelId(globalVO.getQuestionCognitiveLevelId());
		// question.setDifficultyLevelId(globalVO.getQuestionDifficultyLevelId());
		question.setQuestionTypeId(globalVO.getQuestionTypeId());
		question.setQuestionCategoryId(globalVO.getQuestionCategoryId());
		question.setCreatedBy(globalVO.getLoginUserId());
		question.setModifiedDate(new Date());
		question.setStatus(Question.STATUS_ACTIVE);
		
		
		question.setHinttypeid(globalVO.getHintTypeTextSelector());
		question.setExplanationId(globalVO.getExplanationTypeSelector());
		
		if(globalVO.getHintTypeTextSelector()==3 ||globalVO.getHintTypeTextSelector()==4){
			
			question.setHintContent(globalVO.getHintTypeImg().getName());
		}
		else{
			question.setHintContent(globalVO.getHintTypeText());
		}
		
if(globalVO.getExplanationTypeSelector()==22 ||globalVO.getExplanationTypeSelector()==23){
			
			question.setExplanationContent(globalVO.getExplanationTypeImg().getName());
		}
		else{
			question.setExplanationContent(globalVO.getExplanationTypeText());
		}
		
		question.setStatus(Question.STATUS_ACTIVE);
		question.setStatus(Question.STATUS_ACTIVE);
		
		
		
		if (globalVO.getLoginUserType() != null && globalVO.getLoginUserType() != User.USERTYPE_ADMIN)
		{
			// For School Teacher or SME // For admin, schoolId will be null
			question.setSchoolId(globalVO.getSchoolId());
		}

		if (globalVO.getQuestion() != null && globalVO.getQuestion().trim().length() > 0)
		{
			question.setDescription(globalVO.getQuestion().trim().replaceAll("\r", " ").replaceAll("\n", " ")); // Removing next line syntax

			questionDao.saveOrUpdateEntity(question);
		}

		// ##### Answer1 ######//
		Answer answer1 = (globalVO.getOptionId1() != null ? answerDao.getEntity(globalVO.getOptionId1()) : new Answer());
		answer1.setIsCorrect((globalVO.getIsCorrectOption1() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer1.setStatus(Answer.STATUS_ACTIVE);
		answer1.setQuestionId(question.getId());
		answer1.setModifiedDate(new Date());

		
		if (globalVO.getOption1() != null && !globalVO.getOption1().trim().equals(""))
		{
			answer1.setDescription(globalVO.getOption1().trim().replaceAll("\r", " ").replaceAll("\n", " ")); // Removing next line syntax
			answerDao.saveOrUpdateEntity(answer1);
		}

		// ##### Answer2 ######//
		Answer answer2 = (globalVO.getOptionId2() != null ? answerDao.getEntity(globalVO.getOptionId2()) : new Answer());
		answer2.setDescription(globalVO.getOption2().trim().replaceAll("\r", " ").replaceAll("\n", " "));
		answer2.setIsCorrect((globalVO.getIsCorrectOption2() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer2.setStatus(Answer.STATUS_ACTIVE);
		answer2.setQuestionId(question.getId());
		answer2.setModifiedDate(new Date());

		if (globalVO.getOption2() != null && !globalVO.getOption2().trim().equals(""))
		{
			answerDao.saveOrUpdateEntity(answer2);
		}

		// ##### Answer3 ######//
		Answer answer3 = (globalVO.getOptionId3() != null ? answerDao.getEntity(globalVO.getOptionId3()) : new Answer());
		answer3.setDescription(globalVO.getOption3().trim().replaceAll("\r", " ").replaceAll("\n", " "));
		answer3.setIsCorrect((globalVO.getIsCorrectOption3() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer3.setStatus(Answer.STATUS_ACTIVE);
		answer3.setQuestionId(question.getId());
		answer3.setModifiedDate(new Date());
		
		if (globalVO.getOption3() != null && !globalVO.getOption3().trim().equals(""))
		{
			answerDao.saveOrUpdateEntity(answer3);
		}

		// ##### Answer4 ######//
		Answer answer4 = (globalVO.getOptionId4() != null ? answerDao.getEntity(globalVO.getOptionId4()) : new Answer());
		answer4.setDescription(globalVO.getOption4().trim().replaceAll("\r", " ").replaceAll("\n", " "));
		answer4.setIsCorrect((globalVO.getIsCorrectOption4() == 1 ? Answer.CORRECT : Answer.INCORRECT));
		answer4.setStatus(Answer.STATUS_ACTIVE);
		answer4.setQuestionId(question.getId());
		answer4.setModifiedDate(new Date());
		
		if (globalVO.getOption4() != null && !globalVO.getOption4().trim().equals(""))
		{
			answerDao.saveOrUpdateEntity(answer4);
		}

		// Saving image (if provided) by passing question and answer ids.

		if (globalVO.getQuestionImage() != null && globalVO.getQuestionImage().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getQuestionImage(), question.getId(), null, globalVO.getLoginUserId(), "Q");

			question.setImagePath(message);
			questionDao.saveOrUpdateEntity(question);
		}else if(globalVO.isQuestionImgCheck()!=null&&globalVO.isQuestionImgCheck().equals("on")){
			
			question.setImagePath("");
	questionDao.saveOrUpdateEntity(question);
		}

		if (globalVO.getOption1Image() != null && globalVO.getOption1Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption1Image(), question.getId(), answer1.getId(), globalVO.getLoginUserId(), "A");

			answer1.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer1);
		}else if(globalVO.isOption1ImgCheck()!=null&&globalVO.isOption1ImgCheck().equals("on")){
			
	answer1.setImagePath("");
//	answerDao.saveOrUpdateEntity(answer1);
		}

		if (globalVO.getOption2Image() != null && globalVO.getOption2Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption2Image(), question.getId(), answer2.getId(), globalVO.getLoginUserId(), "A");

			answer2.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer2);
		}else if(globalVO.isOption2ImgCheck()!=null&&globalVO.isOption2ImgCheck().equals("on")){
			
	answer2.setImagePath("");
	answerDao.saveOrUpdateEntity(answer2);
		}

		if (globalVO.getOption3Image() != null && globalVO.getOption3Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption3Image(), question.getId(), answer3.getId(), globalVO.getLoginUserId(), "A");

			answer3.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer3);
		}else if(globalVO.isOption3ImgCheck()!=null&&globalVO.isOption3ImgCheck().equals("on")){
			
	answer3.setImagePath("");
	answerDao.saveOrUpdateEntity(answer3);
		}

		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		if (globalVO.getHintTypeImg() != null && globalVO.getHintTypeImg().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getHintTypeImg(), question.getId(), globalVO.getHintTypeTextSelector(), globalVO.getLoginUserId(), "H");

			question.setHintContent(message);
			questionDao.saveOrUpdateEntity(question);
			
		
		}
		
		
		
		if (globalVO.getExplanationTypeImg() != null && globalVO.getExplanationTypeImg().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getExplanationTypeImg(), question.getId(), globalVO.getExplanationTypeSelector(), globalVO.getLoginUserId(), "E");

			question.setExplanationContent(message);
			questionDao.saveOrUpdateEntity(question);
			
		
		}
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		if (globalVO.getOption4Image() != null && globalVO.getOption4Image().getBytes().length > 0)
		{
			message = Utility.saveImageToDisk(globalVO.getOption4Image(), question.getId(), answer4.getId(), globalVO.getLoginUserId(), "A");

			answer4.setImagePath(message);
			answerDao.saveOrUpdateEntity(answer4);
		}else if(globalVO.isOption4ImgCheck()!=null&&globalVO.isOption4ImgCheck().equals("on")){
			
	answer4.setImagePath("");
	answerDao.saveOrUpdateEntity(answer4);
		}

		questionDao.saveOrUpdateEntity(question); // Updating the image urls in question and answer tables

		return question.getId() + "@" + message;
	}

	@Override
	@Transactional
	public void uploadDocument(GlobalVO globalVO)
	{

		Calendar cal = Calendar.getInstance();
		Long token = cal.getTimeInMillis();

		Module module = new Module(); // For new/edit module
		module.setContentUrl(token + "_" + globalVO.getModuleFile().getOriginalFilename());
		module.setResourceTypeId(globalVO.getResourceTypeId());
		module.setName(globalVO.getModuleName());
		module.setStatus(Module.STATUS_ACTIVE);
		module.setDescription(globalVO.getModuleDescription());
		module.setModuleType(globalVO.getModuleType());
		module.setContentType(Module.CONTENT_TYPE_DOCUMENT);
		module.setCreatedBy(globalVO.getCreatedBy());
		if (globalVO.getLoginUserType() != null && User.USERTYPE_ADMIN != globalVO.getLoginUserType())
		{
			module.setSchoolId(globalVO.getSchoolId());
		}
		// If admin then schoolId will be null

		module = moduleDao.saveOrUpdateEntity(module);
		// code for save file into drive
		String propertyDirectoryString = Utility.getValueFromPropertyFile("flp.modules");
		File dir = new File(propertyDirectoryString);
		Utility.saveImageInResources(globalVO.getModuleFile(), dir, "/" + token + "_" + globalVO.getModuleFile().getOriginalFilename());
		// end

		// save mapping topic with module
		ModuleTopic moduleTopic = new ModuleTopic();
		moduleTopic.setModuleId(module.getId());
		moduleTopic.setStatus(Module.STATUS_ACTIVE);
		moduleTopic.setTopicId(globalVO.getTopicId());
		moduleTopicDao.saveOrUpdateEntity(moduleTopic);
		// end
	}

	@Override
	@Transactional
	public void addWebModule(GlobalVO globalVO)
	{
		Module module = new Module(); // For new/edit module
		module.setContentUrl(globalVO.getContentUrl());
		module.setStatus(Module.STATUS_ACTIVE);
		module.setResourceTypeId(globalVO.getResourceTypeId());
		module.setName(globalVO.getModuleName());
		module.setContentType(Module.CONTENT_TYPE_WEB_RESOURCE);
		module.setCreatedBy(globalVO.getCreatedBy());
		module.setDescription(globalVO.getModuleDescription());
		if (globalVO.getLoginUserType() != null && User.USERTYPE_ADMIN != globalVO.getLoginUserType())
		{
			module.setSchoolId(globalVO.getSchoolId());
		}
		// If admin then schoolId will be null
		module = moduleDao.saveOrUpdateEntity(module);
		// save mapping topic with module
		ModuleTopic moduleTopic = new ModuleTopic();
		moduleTopic.setModuleId(module.getId());
		moduleTopic.setStatus(Module.STATUS_ACTIVE);
		moduleTopic.setTopicId(globalVO.getTopicId());
		moduleTopicDao.saveOrUpdateEntity(moduleTopic);
		// end

	}

	@Override
	@Transactional(rollbackFor = { IOException.class })
	// Making transaction rollback for checked exception.
	public String uploadModule(GlobalVO globalVO) throws IOException
	{
	
		String message = "success";

		if (globalVO.isToRemove() == true) // When removing modules
		{
			moduleTopicDao.updateModuleTopic(String.valueOf(globalVO.getModuleTopicId()), ModuleTopic.STATUS_INACTIVE);
			return message;
		}

		Module module = (globalVO.getModuleId() != null ? moduleDao.getEntity(globalVO.getModuleId()) : new Module()); // For
																														 // new/edit
																														 // module
		module.setAnimationTypeId(globalVO.getAnimationTypeId());
		module.setGradeTypeId(globalVO.getGradeTypeId());
		module.setResourceTypeId(globalVO.getResourceTypeId());
		module.setName(globalVO.getModuleName());
		module.setStatus(Module.STATUS_ACTIVE);
		module.setContentType(Module.CONTENT_TYPE_RESOURCE);
		module.setDescription(globalVO.getModuleDescription());
		module.setCreatedBy(globalVO.getCreatedBy());
		if (globalVO.getLoginUserType() != null && User.USERTYPE_ADMIN == globalVO.getLoginUserType())
		{
			// If admin then schoolId will be null
		}
		else
		{
			module.setSchoolId(globalVO.getSchoolId());
		}

		module = moduleDao.saveOrUpdateEntity(module);

		if (globalVO.getModuleFile() != null && globalVO.getModuleFile().getBytes().length > 0) 
		{
			String originalFileName = globalVO.getModuleFile().getOriginalFilename();
			String fileFormat = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());

			if (!fileFormat.equalsIgnoreCase(".zip"))
				return "notZip";

			List<String> fileNamesFromZip;
			fileNamesFromZip = Utility.readFromZipAndSaveToDisk(globalVO.getModuleFile(), globalVO.getSchoolId(), module.getId(), globalVO.getBoardId());

			for (String fileUrl : fileNamesFromZip)
			{
				if (globalVO.getResourceTypeId().equals(1l)) // If it contains
															 // .swf file
				{
					if (fileUrl.toLowerCase().contains("/main"))
						module.setContentUrl(fileUrl);
				}
				else
				{
					if (!fileUrl.toLowerCase().contains("/thumbnail"))
					{
						module.setContentUrl(fileUrl);
					}
				}

				if (fileUrl.toLowerCase().contains("/thumbnail"))
					module.setThumbnailUrl(fileUrl);
			}

			module = moduleDao.saveOrUpdateEntity(module);
		}

		ModuleTopic moduleTopic = (globalVO.getModuleTopicId() != null ? moduleTopicDao.getEntity(globalVO.getModuleTopicId()) : new ModuleTopic());
		moduleTopic.setModuleId(module.getId());
		moduleTopic.setStatus(Module.STATUS_ACTIVE);
		moduleTopic.setTopicId(globalVO.getTopicId());

		moduleTopicDao.saveOrUpdateEntity(moduleTopic);

		return message;
	}

	@Override
	@Transactional(rollbackFor = { IOException.class })
	public String uploadResources(GlobalVO globalVO) throws IOException
	{
		String message = "success";
		Module module = (globalVO.getModuleId() != null ? moduleDao.getEntity(globalVO.getModuleId()) : new Module()); // For
		module.setContentUrl(globalVO.getContentUrl());
		// module.setAnimationTypeId(globalVO.getAnimationTypeId());
		// module.setGradeTypeId(globalVO.getGradeTypeId());
		module.setResourceTypeId(globalVO.getResourceTypeId());
		module.setName(globalVO.getModuleName());
		module.setStatus(Module.STATUS_ACTIVE);
		module.setContentType(Module.CONTENT_TYPE_WEB_RESOURCE);
		module.setDescription(globalVO.getModuleDescription());
		module.setCreatedBy(globalVO.getCreatedBy());
		if (globalVO.getLoginUserType() != null && User.USERTYPE_ADMIN == globalVO.getLoginUserType())
		{
			module.setSchoolId(null);
			// If admin then schoolId will be null
		}
		else
		{
			module.setSchoolId(globalVO.getSchoolId());
		}

		module = moduleDao.saveOrUpdateEntity(module);
		ModuleTopic moduleTopic = (globalVO.getModuleTopicId() != null ? moduleTopicDao.getEntity(globalVO.getModuleTopicId()) : new ModuleTopic());
		moduleTopic.setModuleId(module.getId());
		moduleTopic.setStatus(Module.STATUS_ACTIVE);
		moduleTopic.setTopicId(globalVO.getTopicId());

		moduleTopicDao.saveOrUpdateEntity(moduleTopic);

		// TODO Auto-generated method stub
		return message;
	}

	@Override
	@Transactional
	public void unAssignAssessment(UserForm loginUser, Long[] studentIds, Long assessmentId)
	{
		schoolTeacherDao.unAssignAssessment(loginUser.getId(), studentIds, assessmentId);

	}

	@Transactional
	@Override
	public Assessment getAssessment(long assessmentId)
	{
		Assessment ass = schoolTeacherDao.getAssessment(assessmentId);
		List<AssessmentQuestion> aqlist = new ArrayList<AssessmentQuestion>();
		if (ass != null)
		{
			for (AssessmentQuestion aq : ass.getAssessmentQuestion())
			{
				aqlist.add(aq);
			}
			if (aqlist.size() > 0)
			{
				Collections.sort(aqlist, new Comparator<AssessmentQuestion>()
				{

					@Override
					public int compare(AssessmentQuestion o1, AssessmentQuestion o2)
					{
						return o1.getSerial() - o2.getSerial();
					}
				});
			}
			Collections.shuffle(aqlist);
			ass.setAssessmentQuestionList(aqlist);
		}

		return ass;
	}

	@Override
	@Transactional
	public void removeAssessment(Long assessmentId)
	{
		schoolTeacherDao.removeAssessment(assessmentId);

	}
	
	
	//========================uploadLoQuestionFile===========
	@Transactional
	@Override
	public String uploadLoQuestionFile(File excelFile, Long userId, Long schoolId, Long topicId,Long loid)
	{
		try
		{
			ByteArrayInputStream bis = new ByteArrayInputStream(FileUtils.readFileToByteArray(excelFile));
			XSSFWorkbook workbook = new XSSFWorkbook(bis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<Question> questionList = new ArrayList<Question>();
			String qDifficultyLevel = "";
			String qCongnitiveLevel = "";
			String qLearnobjective = "";
			String qLearningzone = "";
			String hinttype="";
			String hintcontent="";
			String explanationContent="";
			String  explanationType="";
			Date date = new Date();
			
            int counter = 2;
            String LoCount = Long.toString(studentCommonDao.getLOCount(topicId));
            int loCount=Integer.parseInt(LoCount);
			DataFormatter formate = new DataFormatter();
			if (rowIterator.hasNext())
			{
				rowIterator.next();
			}
			else
			{
				return "Please fill the content !!!";
			}

			while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();

				Cell cellQuestion = row.getCell(0);
				Cell cellA1 = row.getCell(1);
				Cell cellA2 = row.getCell(2);
				Cell cellA3 = row.getCell(3);
				Cell cellA4 = row.getCell(4);
				Cell cellAnswer = row.getCell(5);
				Cell cellSolution = row.getCell(6);
				Cell cellhint1 = row.getCell(7);
				Cell cellhint2 = row.getCell(8);
				Cell cellhint3 = row.getCell(9);

				Cell cellQDifficultyLevel = row.getCell(10);
				Cell cellQCongnitiveLevel = row.getCell(11);
				Cell cellIsImageP = row.getCell(12);
				Cell cellQimgPath = row.getCell(13);
				Cell cellA1imgPath = row.getCell(14);
				Cell cellA2imgPath = row.getCell(15);
				Cell cellA3imgPath = row.getCell(16);
				Cell cellA4imgPath = row.getCell(17);
				//Cell cellQLearnobjectiveLevel=row.getCell(18);
				Cell cellQLlearningzoneLevel=row.getCell(18);
				Cell cellhinttype=row.getCell(19);
				Cell cellhintcontent=row.getCell(20);
				Cell cellexplanationtype=row.getCell(21);
				Cell cellexplanationContent=row.getCell(22);
				
				
				String question = cellQuestion != null ? handleCell(cellQuestion, cellQuestion.getCellType()) : "";
				String imgPath = cellQimgPath != null ? handleCell(cellQimgPath, cellQimgPath.getCellType()) : "";
				String a1 = cellA1 != null ? handleCell(cellA1, cellA1.getCellType()) : "";
				String a2 = cellA2 != null ? handleCell(cellA2, cellA2.getCellType()) : "";
				String a3 = cellA3 != null ? handleCell(cellA3, cellA3.getCellType()) : "";
				String a4 = cellA4 != null ? handleCell(cellA4, cellA4.getCellType()) : "";
				String imgA1 = cellA1imgPath != null ? handleCell(cellA1imgPath, cellA1imgPath.getCellType()) : "";
				String imgA2 = cellA2imgPath != null ? handleCell(cellA2imgPath, cellA2imgPath.getCellType()) : "";
				String imgA3 = cellA3imgPath != null ? handleCell(cellA3imgPath, cellA3imgPath.getCellType()) : "";
				String imgA4 = cellA4imgPath != null ? handleCell(cellA4imgPath, cellA4imgPath.getCellType()) : "";
				String solution = cellSolution == null ? null : handleCell(cellSolution, cellSolution.getCellType());
				String hint1 = cellhint1 == null ? null : handleCell(cellhint1, cellhint1.getCellType());
				String hint2 = cellhint2 == null ? null : handleCell(cellhint2, cellhint2.getCellType());
				String hint3 = cellhint3 == null ? null : handleCell(cellhint3, cellhint3.getCellType());
				
				
				String ansString = formate.formatCellValue(row.getCell(5));
				qDifficultyLevel = formate.formatCellValue(cellQDifficultyLevel);
				qCongnitiveLevel = formate.formatCellValue(cellQCongnitiveLevel);
				//qLearnobjective = formate.formatCellValue(cellQLearnobjectiveLevel);
				qLearningzone = formate.formatCellValue(cellQLlearningzoneLevel);
				hinttype=formate.formatCellValue(cellhinttype);
				hintcontent=formate.formatCellValue(cellhintcontent);
				String isImageP = formate.formatCellValue(cellIsImageP);
				explanationType=formate.formatCellValue(cellexplanationtype);
				explanationContent=formate.formatCellValue(cellexplanationContent);
				

				List<Answer> ansSet = new ArrayList<Answer>();

				if (notEmpty1(question))
				{
					Question q = new Question();
					Answer ans1 = null;
					Answer ans2 = null;
					Answer ans3 = null;
					Answer ans4 = null;
					String[] answers = null;

					int correctCount = 0;
					int ansNum = 0;
					if (notEmpty1(ansString))
					{
						if (ansString.contains(","))
						{
							answers = ansString.split(",");
						}
						else
						{
							try
							{
								Double d = Double.parseDouble(ansString);

								ansNum = d.intValue();
							}
							catch (NumberFormatException e)
							{
								e.printStackTrace();
								return "Invalid answer for question " + counter;
							}
						}

					}

					if (notEmpty1(isImageP))
					{
						if (isImageP.equalsIgnoreCase("true") || isImageP.toString().equals("1.0") || isImageP.contains("TRUE()"))
						{
							q.setImagePresent(1);
						}
						else if (isImageP.equalsIgnoreCase("false") || isImageP.toString().equals("0.0") || isImageP.contains("FALSE()"))
						{
							q.setImagePresent(0);
						}

					}
					else
						return "Please provide entry for image present or not at row " + counter;

					q.setDescription(question.replaceAll("\r", " ").replaceAll("\n", " "));
					q.setInsertDate(date);
					q.setImagePath(imgPath);
					q.setModifiedDate(date);
					q.setQuestionCategoryId(13l); // Question Category : text
													 // based
					q.setCreatedBy(userId);
					q.setSchoolId(schoolId);
					q.setStatus(Question.STATUS_ACTIVE);
				
					
					if (notEmpty1(hinttype))
					{

						String s = hinttype.trim().toLowerCase();
						switch (s)
						{
							case "text":
								q.setHinttypeid(2l);
								break;
							case "image":
								q.setHinttypeid(3l);
								break;
							case "video":
								q.setHinttypeid(4l);
								break;
							default:
								q.setHinttypeid(1l);
						}

					}
					else
						q.setHinttypeid(0l);
					
					if (notEmpty1(explanationType))
					{

						String s = explanationType.trim().toLowerCase();
						switch (s)
						{
							case "text":
								q.setExplanationId(21l);
								break;
							case "image":
								q.setExplanationId(22l);
								break;
							case "video":
								q.setExplanationId(23l);
								break;
							default:
								q.setExplanationId(0l);
						}
					}
					else
						q.setExplanationId(0l);
					 
					q.setHint2(hint2);
					q.setHint3(hint3);
					
					q.setHintContent(hintcontent);
					q.setExplanationContent(explanationContent);
					// q.setCognitiveLevelId(4l); // Cognitive level :
					// knowledge
					if (notEmpty1(a1) || notEmpty1(imgA1))
					{
						ans1 = new Answer();
						ans1.setStatus(Answer.STATUS_ACTIVE);
						ans1.setIsCorrect((byte) 0);
						ans1.setDescription(a1.replaceAll("\r", " ").replaceAll("\n", " "));
						ans1.setInsertDate(date);
						ans1.setModifiedDate(date);
						ans1.setImagePath(imgA1);
						ansSet.add(ans1);

						if (ansNum == 1)
						{
							ans1.setIsCorrect((byte) 1);
							correctCount++;
						}
					}
					else
						return "Please provide first Answer for question at row " + counter;

					if (notEmpty1(a2) || notEmpty1(imgA2))
					{

						ans2 = new Answer();
						ans2.setDescription(a2.replaceAll("\r", " ").replaceAll("\n", " "));
						ans2.setInsertDate(date);
						ans2.setModifiedDate(date);
						ans2.setStatus(Answer.STATUS_ACTIVE);
						ans2.setIsCorrect((byte) 0);
						ans2.setImagePath(imgA2);
						ansSet.add(ans2);
						if (ansNum == 2)
						{
							ans2.setIsCorrect((byte) 1);
							correctCount++;
						}

					}
					else
						return "Please provide second answer for question at row " + counter;

					if (notEmpty1(a3) || notEmpty1(imgA3))
					{
						ans3 = new Answer();
						ans3.setDescription(a3.replaceAll("\r", " ").replaceAll("\n", " "));
						ans3.setInsertDate(date);
						ans3.setModifiedDate(date);
						ans3.setStatus(Answer.STATUS_ACTIVE);
						ans3.setImagePath(imgA3);
						if (a3.trim().length() > 0 || imgA3.trim().length() > 0)
							ansSet.add(ans3);
						ans3.setIsCorrect((byte) 0);
						if (ansNum == 3)
						{
							ans3.setIsCorrect((byte) 1);
							correctCount++;
						}
					}

					if (notEmpty1(a4) || notEmpty1(imgA4))
					{
						ans4 = new Answer();
						ans4.setDescription(a4.replaceAll("\r", " ").replaceAll("\n", " "));
						ans4.setInsertDate(date);
						ans4.setModifiedDate(date);
						ans4.setStatus(Answer.STATUS_ACTIVE);
						ans4.setIsCorrect((byte) 0);
						ans4.setImagePath(imgA4);
						if (a4.trim().length() > 0 || imgA4.length() > 0)
							ansSet.add(ans4);

						if (ansNum == 4)
						{
							ans4.setIsCorrect((byte) 1);
							correctCount++;
						}
					}

					if (answers != null)
					{
						for (int i = 0; i < answers.length; i++)
						{
							String a = answers[i].trim();

							switch (a)
							{
								case "1":
									ans1.setIsCorrect((byte) 1);

									correctCount++;
									break;

								case "2":
									ans2.setIsCorrect((byte) 1);
									correctCount++;
									break;
								case "3":
									ans3.setIsCorrect((byte) 1);
									correctCount++;
									break;
								case "4":
									ans4.setIsCorrect((byte) 1);
									correctCount++;
									break;

								default:
									break;
							}
						}
					}

					if (notEmpty1(qCongnitiveLevel))
					{

						q.setAnsSolution(solution);
					}

					if (correctCount == 1)
						q.setQuestionTypeId(10l); // Question type : MCQ
					else if (correctCount > 1)
						q.setQuestionTypeId(16l); // Question type : MMCQ
					else
						return "Please provide valid correct answer number for question at row " + counter;

					if (notEmpty1(qDifficultyLevel))
					{

						String s = qDifficultyLevel.trim().toLowerCase();
						switch (s)
						{
							case "easy":
								q.setDifficultyLevelId(1l);
								break;
							case "medium":
							case "intermediate":
								q.setDifficultyLevelId(2l);
								break;
							case "difficult":
								q.setDifficultyLevelId(3l);
								break;
							default:
								return "Please provide correct difficulty level for question at row " + counter;
						}

					}
					else
						return "Please provide difficulty level for question at row " + counter;

					String c = qCongnitiveLevel.trim().toLowerCase();
					if (notEmpty1(qCongnitiveLevel))
					{
						switch (c)
						{
							case "understanding":
								q.setCognitiveLevelId(20l);
								break;
							
							case "knowledge":
								q.setCognitiveLevelId(4l);
								break;
							case "comprehensive":
								q.setCognitiveLevelId(5l);
								break;

							case "application":
								q.setCognitiveLevelId(6l);
								break;

							case "analysis":
								q.setCognitiveLevelId(7l);
								break;

							case "synthesis":
								q.setCognitiveLevelId(8l);
								break;

							case "evaluation":
								q.setCognitiveLevelId(9l);
								break;

							default:

								return "Please provide correct cognitive level for question at row " + counter;

						}

					}
					else
						return "Please provide congnitive level for question at row " + counter;

					q.setAnswers(ansSet);
					//questionList.add(q);
					
					
					//==================
					
					long questionId = schoolTeacherDao.saveLoQuestion(q);

					QuestionTopic tq = new QuestionTopic();
					tq.setQuestionId(questionId);
					tq.setTopicId(topicId);
					tq.setInsertDate(date);
					tq.setModifiedDate(date);
					tq.setDifficultyLevelId(q.getDifficultyLevelId());
					tq.setCognitiveLevelId(q.getCognitiveLevelId());
					tq.setLoId(loid);

					//..........locount 1........................
                    if(loCount==1) 
                       {
                            if (notEmpty1(qLearningzone))
                            {
       
                                String s = qLearningzone.trim().toLowerCase();
                              
                                switch (s)
                                {
                                   
                                    case "learnlevel":
                                        tq.setZoneLevelId(17l);
                                       
                                       
                                        break;
                                    case "practicelevel":                       
                                        tq.setZoneLevelId(18l);
                                       
                                        break;
                                    case "masterylevel":
                                        tq.setZoneLevelId(19l);
                                     
                                        break;
                                    default:
                                        return "Please provide correct Zone level for question at row " + counter;
                                }
                                
       
                            }
                            else
                                return "Please provide Zone level for question at row " + counter;
                       }
                   
                   
                   
                  //..........locount 2........................
                    else if(loCount==2)
                    {
                        if (notEmpty1(qLearningzone))
                        {
   
                            String s = qLearningzone.trim().toLowerCase();
                             switch (s)
                            {
                               
                                case "learnlevel":
                                    tq.setZoneLevelId(17l);
                               
                                   
                                    break;
                                case "practicelevel":                       
                                    tq.setZoneLevelId(18l);
                                   
                                    break;
                                case "masterylevel":
                                    tq.setZoneLevelId(19l);
                                 
                                    break;
                                default:
                                    return "Please provide correct Zone level for question at row " + counter;
                            }
                           
                        }
                        else
                            return "Please provide Zone level for question at row " + counter;
                       
                       
                       
                    }
                   
                  //..........locount 3........................
                    else
                    {
                        if (notEmpty1(qLearningzone))
                        {
   
                            String s = qLearningzone.trim().toLowerCase();
                             switch (s)
                            {
                               
                                case "learnlevel":
                                    tq.setZoneLevelId(17l);
                                 
                                   
                                    break;
                                case "practicelevel":                       
                                    tq.setZoneLevelId(18l);
                                 
                                    break;
                                case "masterylevel":
                                    tq.setZoneLevelId(19l);
                                    
                                    break;
                                default:
                                    return "Please provide correct Zone level for question at row " + counter;
                            }
                            
   
                        }
                        else
                            return "Please provide Zone level for question at row " + counter;
                       
                    }
					
					tq.setStatus(QuestionTopic.STATUS_ACTIVE);

					questiTopicDao.saveEntityLoObject(tq);

					
					for (Answer a : q.getAnswers())
					{
						a.setQuestionId(questionId);
						long answerId = schoolTeacherDao.saveAnswerLoQuestion(a);
						
					}
					
				}

				counter++;
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "Error saving excel file";
		}
		return "success";

	}

	public boolean notEmpty1(String s)
	{
		return s == null || s.trim().equals("") ? false : true;
	}
	
	
	//=============================================================================
	
	

	@Transactional
	@Override
	public String uploadQuestionFile(File excelFile, Long userId, Long schoolId, Long topicId)
	{
		try
		{
			ByteArrayInputStream bis = new ByteArrayInputStream(FileUtils.readFileToByteArray(excelFile));
			XSSFWorkbook workbook = new XSSFWorkbook(bis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<Question> questionList = new ArrayList<Question>();
			String qDifficultyLevel = "";
			String qCongnitiveLevel = "";
			Date date = new Date();
			int counter = 2;
			DataFormatter formate = new DataFormatter();
			if (rowIterator.hasNext())
			{
				rowIterator.next();
			}
			else
			{
				return "Please fill the content !!!";
			}

			while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();

				Cell cellQuestion = row.getCell(0);//
				Cell cellA1 = row.getCell(1);
				Cell cellA2 = row.getCell(2);
				Cell cellA3 = row.getCell(3);
				Cell cellA4 = row.getCell(4);

				Cell cellSolution = row.getCell(6);
				Cell cellhint1 = row.getCell(7);
				Cell cellhint2 = row.getCell(8);
				Cell cellhint3 = row.getCell(9);

				Cell cellQDifficultyLevel = row.getCell(10);
				Cell cellQCongnitiveLevel = row.getCell(11);
				Cell cellIsImageP = row.getCell(12);
				Cell cellQimgPath = row.getCell(13);
				Cell cellA1imgPath = row.getCell(14);
				Cell cellA2imgPath = row.getCell(15);
				Cell cellA3imgPath = row.getCell(16);
				Cell cellA4imgPath = row.getCell(17);

				String question = cellQuestion != null ? handleCell(cellQuestion, cellQuestion.getCellType()) : "";
				String imgPath = cellQimgPath != null ? handleCell(cellQimgPath, cellQimgPath.getCellType()) : "";
				String a1 = cellA1 != null ? handleCell(cellA1, cellA1.getCellType()) : "";
				String a2 = cellA2 != null ? handleCell(cellA2, cellA2.getCellType()) : "";
				String a3 = cellA3 != null ? handleCell(cellA3, cellA3.getCellType()) : "";
				String a4 = cellA4 != null ? handleCell(cellA4, cellA4.getCellType()) : "";
				String imgA1 = cellA1imgPath != null ? handleCell(cellA1imgPath, cellA1imgPath.getCellType()) : "";
				String imgA2 = cellA2imgPath != null ? handleCell(cellA2imgPath, cellA2imgPath.getCellType()) : "";
				String imgA3 = cellA3imgPath != null ? handleCell(cellA3imgPath, cellA3imgPath.getCellType()) : "";
				String imgA4 = cellA4imgPath != null ? handleCell(cellA4imgPath, cellA4imgPath.getCellType()) : "";
				String solution = cellSolution == null ? null : handleCell(cellSolution, cellSolution.getCellType());
				String hint1 = cellhint1 == null ? null : handleCell(cellhint1, cellhint1.getCellType());
				String hint2 = cellhint2 == null ? null : handleCell(cellhint2, cellhint2.getCellType());
				String hint3 = cellhint3 == null ? null : handleCell(cellhint3, cellhint3.getCellType());
				String congnitiveLevel = cellQCongnitiveLevel != null ? handleCell(cellQCongnitiveLevel, cellQCongnitiveLevel.getCellType()) : "";

				// formate.formatCellValue(row.getCell(5));;
				String ansString = formate.formatCellValue(row.getCell(5));
				qDifficultyLevel = formate.formatCellValue(cellQDifficultyLevel);
				qCongnitiveLevel = formate.formatCellValue(cellQCongnitiveLevel);
				String isImageP = formate.formatCellValue(cellIsImageP);

				List<Answer> ansSet = new ArrayList<Answer>();

				if (notEmpty(question))
				{
					Question q = new Question();
					Answer ans1 = null;
					Answer ans2 = null;
					Answer ans3 = null;
					Answer ans4 = null;
					String[] answers = null;

					int correctCount = 0;
					int ansNum = 0;
					if (notEmpty(ansString))
					{
						if (ansString.contains(","))
						{
							answers = ansString.split(",");
						}
						else
						{
							try
							{
								Double d = Double.parseDouble(ansString);

								ansNum = d.intValue();
							}
							catch (NumberFormatException e)
							{
								e.printStackTrace();
								return "Invalid answer for question " + counter;
							}
						}

					}

					if (notEmpty(isImageP))
					{
						if (isImageP.equalsIgnoreCase("true") || isImageP.toString().equals("1.0") || isImageP.contains("TRUE()"))
						{
							q.setImagePresent(1);
						}
						else if (isImageP.equalsIgnoreCase("false") || isImageP.toString().equals("0.0") || isImageP.contains("FALSE()"))
						{
							q.setImagePresent(0);
						}

					}
					else
						return "Please provide entry for image present or not at row " + counter;

					q.setDescription(question.replaceAll("\r", " ").replaceAll("\n", " "));
					q.setInsertDate(date);
					q.setImagePath(imgPath);
					q.setModifiedDate(date);
					q.setQuestionCategoryId(13l); // Question Category : text
													 // based
					q.setCreatedBy(userId);
					q.setSchoolId(schoolId);
					q.setStatus(Question.STATUS_ACTIVE);
					q.setHint(hint1);
					q.setHint2(hint2);
					q.setHint3(hint3);
					// q.setCognitiveLevelId(4l); // Cognitive level :
					// knowledge
					if (notEmpty(a1) || notEmpty(imgA1))
					{
						ans1 = new Answer();
						ans1.setStatus(Answer.STATUS_ACTIVE);
						ans1.setIsCorrect((byte) 0);
						ans1.setDescription(a1.replaceAll("\r", " ").replaceAll("\n", " "));
						ans1.setInsertDate(date);
						ans1.setModifiedDate(date);
						ans1.setImagePath(imgA1);
						ansSet.add(ans1);

						if (ansNum == 1)
						{
							ans1.setIsCorrect((byte) 1);
							correctCount++;
						}
					}
					else
						return "Please provide first Answer for question at row " + counter;

					if (notEmpty(a2) || notEmpty(imgA2))
					{

						ans2 = new Answer();
						ans2.setDescription(a2.replaceAll("\r", " ").replaceAll("\n", " "));
						ans2.setInsertDate(date);
						ans2.setModifiedDate(date);
						ans2.setStatus(Answer.STATUS_ACTIVE);
						ans2.setIsCorrect((byte) 0);
						ans2.setImagePath(imgA2);
						ansSet.add(ans2);
						if (ansNum == 2)
						{
							ans2.setIsCorrect((byte) 1);
							correctCount++;
						}

					}
					else
						return "Please provide second answer for question at row " + counter;

					if (notEmpty(a3) || notEmpty(imgA3))
					{
						ans3 = new Answer();
						ans3.setDescription(a3.replaceAll("\r", " ").replaceAll("\n", " "));
						ans3.setInsertDate(date);
						ans3.setModifiedDate(date);
						ans3.setStatus(Answer.STATUS_ACTIVE);
						ans3.setImagePath(imgA3);
						if (a3.trim().length() > 0 || imgA3.trim().length() > 0)
							ansSet.add(ans3);
						ans3.setIsCorrect((byte) 0);
						if (ansNum == 3)
						{
							ans3.setIsCorrect((byte) 1);
							correctCount++;
						}
					}

					if (notEmpty(a4) || notEmpty(imgA4))
					{
						ans4 = new Answer();
						ans4.setDescription(a4.replaceAll("\r", " ").replaceAll("\n", " "));
						ans4.setInsertDate(date);
						ans4.setModifiedDate(date);
						ans4.setStatus(Answer.STATUS_ACTIVE);
						ans4.setIsCorrect((byte) 0);
						ans4.setImagePath(imgA4);
						if (a4.trim().length() > 0 || imgA4.length() > 0)
							ansSet.add(ans4);

						if (ansNum == 4)
						{
							ans4.setIsCorrect((byte) 1);
							correctCount++;
						}
					}

					if (answers != null)
					{
						for (int i = 0; i < answers.length; i++)
						{
							String a = answers[i].trim();

							switch (a)
							{
								case "1":
									ans1.setIsCorrect((byte) 1);

									correctCount++;
									break;

								case "2":
									ans2.setIsCorrect((byte) 1);
									correctCount++;
									break;
								case "3":
									ans3.setIsCorrect((byte) 1);
									correctCount++;
									break;
								case "4":
									ans4.setIsCorrect((byte) 1);
									correctCount++;
									break;

								default:
									break;
							}
						}
					}

					if (notEmpty(qCongnitiveLevel))
					{

						q.setAnsSolution(solution);
					}

					if (correctCount == 1)
						q.setQuestionTypeId(10l); // Question type : MCQ
					else if (correctCount > 1)
						q.setQuestionTypeId(16l); // Question type : MMCQ
					else
						return "Please provide valid correct answer number for question at row " + counter;

					if (notEmpty(qDifficultyLevel))
					{

						String s = qDifficultyLevel.trim().toLowerCase();
						switch (s)
						{
							case "easy":
								q.setDifficultyLevelId(1l);
								break;
							case "medium":
							case "intermediate":
								q.setDifficultyLevelId(2l);
								break;
							case "difficult":
								q.setDifficultyLevelId(3l);
								break;
							default:
								return "Please provide correct difficulty level for question at row " + counter;
						}

					}
					else
						return "Please provide difficulty level for question at row " + counter;

					String c = qCongnitiveLevel.trim().toLowerCase();
					if (notEmpty(qCongnitiveLevel))
					{
						switch (c)
						{
							case "knowledge":
								q.setCognitiveLevelId(4l);
								break;
							case "comprehensive":
								q.setCognitiveLevelId(5l);
								break;

							case "application":
								q.setCognitiveLevelId(6l);
								break;

							case "analysis":
								q.setCognitiveLevelId(7l);
								break;

							case "synthesis":
								q.setCognitiveLevelId(8l);
								break;

							case "evaluation":
								q.setCognitiveLevelId(9l);
								break;

							default:

								return "Please provide correct cognitive level for question at row " + counter;

						}

					}
					else
						return "Please provide congnitive level for question at row " + counter;

					q.setAnswers(ansSet);
					questionList.add(q);

				}

				counter++;
			}

			for (Question q : questionList)
			{
				long questionId = schoolTeacherDao.saveQuestion(q);

				QuestionTopic tq = new QuestionTopic();
				tq.setQuestionId(questionId);
				tq.setTopicId(topicId);
				tq.setInsertDate(date);
				tq.setModifiedDate(date);
				tq.setDifficultyLevelId(q.getDifficultyLevelId());
				tq.setCognitiveLevelId(q.getCognitiveLevelId());

				tq.setStatus(QuestionTopic.STATUS_ACTIVE);

				questiTopicDao.saveEntity(tq);

				
				for (Answer a : q.getAnswers())
				{
					a.setQuestionId(questionId);
					long answerId = schoolTeacherDao.saveAnswer(a);
					
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "Error saving excel file";
		}
		return "success";

	}

	public boolean notEmpty(String s)
	{
		return s == null || s.trim().equals("") ? false : true;
	}

	@Override
	@Transactional
	public List<Chapter> getChpatersByGradeAndSubjects(List<Long> gradeIds, List<Long> subjectIds, Long schoolId)
	{
		// TODO Auto-generated method stub
		return schoolTeacherDao.getChpatersByGradeAndSubjects(gradeIds, subjectIds, schoolId);
	}

	@Override
	@Transactional
	public Map<Long, Integer> getQuestionCountByTopicId(List<Long> topicIdList)
	{
		// TODO Auto-generated method stub
		return schoolTeacherDao.getQuestionCountByTopicId(topicIdList);
	}

	@Override
	@Transactional
	public Map<Long, Integer> getModuleCountByTopicId(List<Long> topicIdList)
	{
		// TODO Auto-generated method stub
		return schoolTeacherDao.getModuleCountByTopicId(topicIdList);
	}

	@Override
	@Transactional
	public List<QuestionTopic> getAllQuestionsByTopic(Long topicId)
	{
		return schoolTeacherDao.getAllQuestionsByTopic(topicId);
	}

	@Override
	@Transactional
	public List<QuestionType> getQuestionType(String category, Integer status)
	{
		return questionTypeDao.getQuestionType(category, status);
	}

	@Override
	@Transactional
	public List<ModuleType> getModuleTypes(String category, Integer status)
	{
		return moduleTypeDao.getModuleTypes(category, status);
	}

	@Override
	@Transactional
	public List<Assessment> getCompletedTest(UserForm loginUser)
	{
		// TODO Auto-generated method stub
		return schoolTeacherDao.getCompletedTest(loginUser);
	}

	@Override
	@Transactional
	public List<Assessment> getCreatedTest(UserForm loginUser)
	{
		// TODO Auto-generated method stub
		return schoolTeacherDao.getCreatedTest(loginUser);
	}

	@Override
	@Transactional
	public void updateAssessment(Assessment ass)
	{
		schoolTeacherDao.updateAssessment(ass);

	}

	@Override
	@Transactional
	public Boolean updatetopicdetails(Long id, String name)
	{
		// TODO Auto-generated method stub

		return schoolTeacherDao.updatetopicdetails(id, name);

	}

	@Override
	@Transactional
	public Boolean updatechapterdetails(Long id, String name)
	{
		// TODO Auto-generated method stub
		return schoolTeacherDao.updatechapterdetails(id, name);
	}

	@Override
	@Transactional
	public List<Assessment> getAssessmentByLimitDate(UserForm loginUser, Date startDate, Date endDate)
	{
		return schoolTeacherDao.getAssessmentByLimitDate(loginUser, startDate, endDate);
	}

	@Override
	@Transactional
	public List<SchoolStudentTest> getStudentAssessmentDetailsById(Long assessmentId)
	{
		return schoolTeacherDao.getStudentAssessmentDetailsById(assessmentId);
	}

	@Override
	@Transactional
	public List<SubjectSection> getLearningReportForSubjects(Long sectionId, Long schoolId, Long sessionId)
	{
		return schoolTeacherDao.getLearningReportForSubjects(sectionId, schoolId, sessionId);
	}

	@Override
	@Transactional
	public Integer getTotalQuestionCountBySubject(Long subjectId)
	{
		return schoolTeacherDao.getTotalQuestionCountBySubject(subjectId);
	}

	@Override
	@Transactional
	public List<Chapter> getLearningReportForChapters(Long subjectId, Long schoolId, Long sessionId)
	{
		return schoolTeacherDao.getLearningReportForChapters(subjectId, schoolId, sessionId);
	}

	@Override
	@Transactional
	public Integer getTotalQuestionCountByChapter(Long chapterId)
	{
		return schoolTeacherDao.getTotalQuestionCountByChapter(chapterId);
	}

	@Override
	@Transactional
	public List<Topic> getLearningReportForTopic(Long chapterId, Long schoolId)
	{
		return schoolTeacherDao.getLearningReportForTopic(chapterId, schoolId);
	}

	@Override
	@Transactional
	public Integer getTotalQuestionCountByTopic(Long topicId)
	{
		return schoolTeacherDao.getTotalQuestionCountByTopic(topicId);
	}

	@Override
	@Transactional
	public List<StudentTeacherSession> getStudentListBySubjectId(Long subjectId, Long sectionId, Long schoolId, Long sessionId)
	{
		return schoolTeacherDao.getStudentListBySubjectId(subjectId, sectionId, schoolId, sessionId);
	}

	@Override
	@Transactional
	public List<UserQuestionAnswer> getUserQuestionAnswerList(String type, Long id, Long gradeId, Long schoolId)
	{
		return schoolTeacherDao.getUserQuestionAnswerList(type, id, gradeId, schoolId);
	}

	private String handleCell(Cell cell, int cellType)
	{

		if (HSSFCell.CELL_TYPE_NUMERIC == cellType)
		{
			DataFormatter formate = new DataFormatter();
			return formate.formatCellValue(cell);
		}
		else if (HSSFCell.CELL_TYPE_STRING == cellType)
		{
			return cell.getStringCellValue();
		}
		else if (HSSFCell.CELL_TYPE_BOOLEAN == cellType)
			return Boolean.toString(cell.getBooleanCellValue()).toUpperCase();

		else if (HSSFCell.CELL_TYPE_BLANK == cellType)
			return "";
		else if (HSSFCell.CELL_TYPE_FORMULA == cellType)

			return handleCell(cell, cell.getCachedFormulaResultType());
		else
			return "";
	}

	@Override
	@Transactional
	public String uploadQuestionWithGradeAndSubjectwise(File excelFile, Long userId, Long schoolId)
	{
		try
		{
			ByteArrayInputStream bis = new ByteArrayInputStream(FileUtils.readFileToByteArray(excelFile));
			XSSFWorkbook workbook = new XSSFWorkbook(bis);
			XSSFSheet sheet = workbook.getSheetAt(0);
		
			Iterator<Row> rowIterator = sheet.iterator();
			List<Question> questionList = new ArrayList<Question>();
			Date date = new Date();
			int counter = 2;
			DataFormatter formate = new DataFormatter();
			Map<String, Long> topicIdMap = new HashMap<String, Long>();
			String qDifficultyLevel = "";
			String qCongnitiveLevel = "";
			if (rowIterator.hasNext())
			{
				rowIterator.next();
			}
			else
			{
				return "Please fill the content !!!";
			}

			while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();

				Cell cellGrade = row.getCell(0);
				Cell cellSubject = row.getCell(1);
				Cell cellChapter = row.getCell(2);
				Cell cellTopic = row.getCell(3);

				Cell cellQuestion = row.getCell(4);
				Cell cellA1 = row.getCell(5);
				Cell cellA2 = row.getCell(6);
				Cell cellA3 = row.getCell(7);
				Cell cellA4 = row.getCell(8);

				// Cell cellAns=row.getCell(10);
				Cell cellSolution = row.getCell(10);
				Cell cellHint1 = row.getCell(11);
				Cell cellHint2 = row.getCell(12);
				Cell cellHint3 = row.getCell(13);

				Cell cellQDifficultyLevel = row.getCell(14);
				Cell cellQCongnitiveLevel = row.getCell(15);

				Cell cellIsImageP = row.getCell(16);
				Cell cellQimgPath = row.getCell(17);
				Cell cellA1imgPath = row.getCell(18);
				Cell cellA2imgPath = row.getCell(19);
				Cell cellA3imgPath = row.getCell(20);
				Cell cellA4imgPath = row.getCell(21);

				String gradeName = cellGrade != null ? handleCell(cellGrade, cellGrade.getCellType()).trim() : "";
				String subjectName = cellSubject != null ? handleCell(cellSubject, cellSubject.getCellType()).trim() : "";
				String chapterName = cellChapter != null ? handleCell(cellChapter, cellChapter.getCellType()).trim() : "";
				String topicName = cellTopic != null ? handleCell(cellTopic, cellTopic.getCellType()).trim() : "";
				Long topicId = null;
				if (notEmpty(gradeName) && notEmpty(subjectName) && notEmpty(chapterName) && notEmpty(topicName))
				{
					String key = gradeName + "_" + subjectName + "_" + chapterName + "_" + topicName;
					topicId = topicIdMap.get(key);
					if (topicId == null)
					{
						topicId = schoolTeacherDao.getTopicIdByGradeSubjectChapterTopic("CTLS", gradeName.trim(), subjectName.trim(), chapterName.trim(), topicName);
						if (topicId != null && topicId > 0l)
						{
							topicIdMap.put(key, topicId);
						}
						else
						{
							return "Either grade or subject or chapter or topic name is not exists at row " + counter;
						}
					}
				}
				else
				{
					return "Please provide proper Details of Grade, Subject, Chapter, Topic at row" + counter;
				}

				String question = cellQuestion != null ? handleCell(cellQuestion, cellQuestion.getCellType()) : "";
				String imgPath = cellQimgPath != null ? handleCell(cellQimgPath, cellQimgPath.getCellType()) : "";
				String a1 = cellA1 != null ? handleCell(cellA1, cellA1.getCellType()) : "";
				String a2 = cellA2 != null ? handleCell(cellA2, cellA2.getCellType()) : "";
				String a3 = cellA3 != null ? handleCell(cellA3, cellA3.getCellType()) : "";
				String a4 = cellA4 != null ? handleCell(cellA4, cellA4.getCellType()) : "";
				String imgA1 = cellA1imgPath != null ? handleCell(cellA1imgPath, cellA1imgPath.getCellType()) : "";
				String imgA2 = cellA2imgPath != null ? handleCell(cellA2imgPath, cellA2imgPath.getCellType()) : "";
				String imgA3 = cellA3imgPath != null ? handleCell(cellA3imgPath, cellA3imgPath.getCellType()) : "";
				String imgA4 = cellA4imgPath != null ? handleCell(cellA4imgPath, cellA4imgPath.getCellType()) : "";

				String solution = cellSolution == null ? null : handleCell(cellSolution, cellSolution.getCellType());
				String hint1 = cellHint1 == null ? null : handleCell(cellHint1, cellHint1.getCellType());
				String hint2 = cellHint2 == null ? null : handleCell(cellHint2, cellHint2.getCellType());
				String hint3 = cellHint3 == null ? null : handleCell(cellHint3, cellHint3.getCellType());
				String congnitiveLevel = cellQCongnitiveLevel != null ? handleCell(cellQCongnitiveLevel, cellQCongnitiveLevel.getCellType()) : "";

				question = rewriteImgTag(question);
				a1 = rewriteImgTag(a1);
				a2 = rewriteImgTag(a2);
				a3 = rewriteImgTag(a3);
				a4 = rewriteImgTag(a4);

				
				// formate.formatCellValue(row.getCell(5));;
				String ansString = formate.formatCellValue(row.getCell(9));
				qDifficultyLevel = formate.formatCellValue(cellQDifficultyLevel);
				qCongnitiveLevel = formate.formatCellValue(cellQCongnitiveLevel);
				String isImageP = formate.formatCellValue(cellIsImageP);

				List<Answer> ansSet = new ArrayList<Answer>();

				if (notEmpty(question))
				{
					Question q = new Question();
					Answer ans1 = null;
					Answer ans2 = null;
					Answer ans3 = null;
					Answer ans4 = null;
					// String[] answers = null;

					int correctCount = 0;
					int ansNum = 0;
					if (notEmpty(ansString))
					{
						/* if (ansString.contains(","))// for MMCQ { answers = ansString.split(","); } */

						try
						{
							Double d = Double.parseDouble(ansString);

							ansNum = d.intValue();
						}
						catch (NumberFormatException e)
						{
							e.printStackTrace();
							return "Invalid answer for question " + counter;
						}

					}
					if (notEmpty(isImageP))
					{
						if (isImageP.equalsIgnoreCase("true") || isImageP.toString().equals("1.0"))
						{
							q.setImagePresent(1);
						}
						else if (isImageP.equalsIgnoreCase("false") || isImageP.toString().equals("0.0"))
						{
							q.setImagePresent(0);
						}

					}
					else
						return "Please provide entry for image present or not at row " + counter;

					if (notEmpty(solution))
					{

						q.setAnsSolution(solution);

					}

					q.setDescription(question.replaceAll("\r", " ").replaceAll("\n", " "));
					q.setInsertDate(date);
					q.setImagePath(imgPath);
					q.setModifiedDate(date);
					q.setQuestionCategoryId(13l); // Question Category : text
													 // based
					q.setCreatedBy(userId);
					q.setSchoolId(schoolId);

					q.setHint(hint1);
					q.setHint2(hint2);
					q.setHint3(hint3);

					q.setStatus(Question.STATUS_ACTIVE);

					// Cognitive level :
					// Knowledge
					if (notEmpty(a1) || notEmpty(imgA1))
					{
						ans1 = new Answer();
						ans1.setStatus(Answer.STATUS_ACTIVE);
						ans1.setIsCorrect((byte) 0);
						ans1.setDescription(a1.replaceAll("\r", " ").replaceAll("\n", " "));
						ans1.setInsertDate(date);
						ans1.setModifiedDate(date);
						ans1.setImagePath(imgA1);
						ansSet.add(ans1);

						if (ansNum == 1)
						{
							ans1.setIsCorrect((byte) 1);
							correctCount++;
						}
					}
					else
						return "Please provide first Answer for question at row " + counter;

					if (notEmpty(a2) || notEmpty(imgA2))
					{

						ans2 = new Answer();
						ans2.setDescription(a2.replaceAll("\r", " ").replaceAll("\n", " "));
						ans2.setInsertDate(date);
						ans2.setModifiedDate(date);
						ans2.setStatus(Answer.STATUS_ACTIVE);
						ans2.setIsCorrect((byte) 0);
						ans2.setImagePath(imgA2);
						ansSet.add(ans2);
						if (ansNum == 2)
						{
							ans2.setIsCorrect((byte) 1);
							correctCount++;
						}

					}
					else
						return "Please provide second answer for question at row " + counter;

					if (notEmpty(a3) || notEmpty(imgA3))
					{
						ans3 = new Answer();
						ans3.setDescription(a3.replaceAll("\r", " ").replaceAll("\n", " "));
						ans3.setInsertDate(date);
						ans3.setModifiedDate(date);
						ans3.setStatus(Answer.STATUS_ACTIVE);
						ans3.setImagePath(imgA3);
						if (a3.trim().length() > 0 || imgA3.trim().length() > 0)
							ansSet.add(ans3);
						ans3.setIsCorrect((byte) 0);
						if (ansNum == 3)
						{
							ans3.setIsCorrect((byte) 1);
							correctCount++;
						}
					}

					if (notEmpty(a4) || notEmpty(imgA4))
					{
						ans4 = new Answer();
						ans4.setDescription(a4.replaceAll("\r", " ").replaceAll("\n", " "));
						ans4.setInsertDate(date);
						ans4.setModifiedDate(date);
						ans4.setStatus(Answer.STATUS_ACTIVE);
						ans4.setIsCorrect((byte) 0);
						ans4.setImagePath(imgA4);
						if (a4.trim().length() > 0 || imgA4.length() > 0)
							ansSet.add(ans4);

						if (ansNum == 4)
						{
							ans4.setIsCorrect((byte) 1);
							correctCount++;
						}
					}

					/* if (answers != null) { for (int i = 0; i < answers.length; i++) { String a = answers[i].trim();
					 * 
					 * switch (a) { case "1": ans1.setIsCorrect((byte) 1);
					 * 
					 * correctCount++; break;
					 * 
					 * case "2": ans2.setIsCorrect((byte) 1); correctCount++; break; case "3": ans3.setIsCorrect((byte) 1); correctCount++; break; case "4": ans4.setIsCorrect((byte) 1); correctCount++; break;
					 * 
					 * default: break; } } } */

					if (correctCount == 1)
						q.setQuestionTypeId(10l); // Question type : MCQ
					else if (correctCount > 1)
						q.setQuestionTypeId(16l); // Question type : MMCQ
					else
						return "Please provide valid correct answer number for question at row " + counter;

					if (notEmpty(qDifficultyLevel))
					{

						String s = qDifficultyLevel.trim().toLowerCase();
						switch (s)
						{
							case "easy":
								q.setDifficultyLevelId(1l);
								break;
							case "medium":
							case "intermediate":
								q.setDifficultyLevelId(2l);
								break;
							case "difficult":
								q.setDifficultyLevelId(3l);
								break;
							default:
								return "Please provide correct difficulty level for question at row " + counter;
						}

					}
					else
						return "Please provide difficulty level for question at row " + counter;

					String c = qCongnitiveLevel.trim().toLowerCase();
					if (notEmpty(qCongnitiveLevel))
					{
						switch (c)
						{
							case "knowledge":
								q.setCognitiveLevelId(4l);
								break;
							case "comprehensive":
								q.setCognitiveLevelId(5l);
								break;

							case "application":
								q.setCognitiveLevelId(6l);
								break;

							case "analysis":
								q.setCognitiveLevelId(7l);
								break;

							case "synthesis":
								q.setCognitiveLevelId(8l);
								break;

							case "evaluation":
								q.setCognitiveLevelId(9l);
								break;

							default:

								return "Please provide correct cognitive level for question at row " + counter;

						}

					}
					else
						return "Please provide congnitive level for question at row " + counter;

					q.setAnswers(ansSet);
					q.setTopicId(topicId);
					questionList.add(q);
				}

				counter++;
			}

			for (Question q : questionList)
			{
				long questionId = schoolTeacherDao.saveQuestion(q);

				QuestionTopic tq = new QuestionTopic();
				tq.setQuestionId(questionId);
				tq.setTopicId(q.getTopicId());
				tq.setInsertDate(date);
				tq.setModifiedDate(date);
				tq.setStatus(QuestionTopic.STATUS_ACTIVE);
				tq.setDifficultyLevelId(q.getDifficultyLevelId());
				tq.setCognitiveLevelId(q.getCognitiveLevelId());
				questiTopicDao.saveEntity(tq);

				for (Answer a : q.getAnswers())
				{
					a.setQuestionId(questionId);
					long answerId = schoolTeacherDao.saveAnswer(a);
				
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "Error saving excel file";
		}
		return "success";
	}

	/* private static final Pattern IMG_PATTERN = Pattern.compile( "<img(\\s+.*?)(?:src\\s*=\\s*(?:'|\")(.*?)(?:'|\"))(.*?)/>",
	 * 
	 * Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
	 * 
	 * private static final Pattern source=Pattern.compile("src\\s*=\\s*(?:'|\")"); */

	public static String rewriteImgTag(String str)
	{

		String s = Utility.getValueFromPropertyFile("flp.qa.images.retrieve");
		String abc = str.replaceAll("src\\s*=\\s*(?:\")", " src=\"" + s);
		abc = abc.replaceAll("src\\s*=\\s*(?:')", " src='" + s);
		return abc;
	} 
	@Override
	@Transactional
	public Boolean addQuestionComment(QuestionComments qc)
	{
		return schoolTeacherDao.addQuestionComment(qc);
	}

	@Override
	@Transactional
	public List<QuestionComments> getAllTeacherCommentsByQuestId(Long questId, Long userId)
	{
		return schoolTeacherDao.getAllTeacherCommentsByQuestId(questId, userId);
	}

	@Override
	@Transactional
	public List<String> getQuestionListforAuto(String topicIds, long userId, Long totalQuestion, Long schoolId, Integer createBy, Integer eQuest, Integer mQuest, Integer dQuest)
	{

		return schoolTeacherDao.getQuestionListforAuto(topicIds, userId, totalQuestion, schoolId, createBy, eQuest, mQuest, dQuest);
	}

	/*@Transactional
	@Override
	public String addAutoCreateAssessment(List<String> result, String testName, Integer testType, String startDate, String endDate, Long gradeId, Long subjectId, Long totalQuestion, Integer duration, String instruction,
			Long schoolId, long userId, Long sessionId, Integer totalMarks, String studentsIds, Long secId, String chpaterIds, String topicIds, 
			String eMarks, String iMarks, String dMarks)
	{
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);
		String newEndDate = endDate;
		if (endDate.equals("00/default/0000 00:00") || endDate.equals("____/__/__ __:__"))
		{
			Session s = (Session) schoolTeacherDao.getSessionObj(sessionId);
			newEndDate = Utility.convertDateToString(s.getEndDate(), "dd/MMM/yyyy HH:mm");

		}
		System.out.println(eMarks);

		Assessment ass = new Assessment();
		try
		{
			Date sdate = format.parse(startDate);
			Date edate = format.parse(newEndDate);

			ass.setStartDate(sdate);
			ass.setEndDate(edate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		ass.setTestName(testName);
		ass.setCreatedBy(userId);
		ass.setStatus(2);
		ass.setCreatedOn(new Date());
		ass.setSubjectId(new Long(subjectId));
		ass.setGradeId(new Long(gradeId));
		ass.setSchoolId(new Long(schoolId));
		ass.setInstruction(instruction.replaceAll("\n", "<br>").replaceAll("\r", "<br>"));
		ass.setSessionId(sessionId);

		ass.setType(testType);
		ass.setDuration(duration);
		Set<AssessmentQuestion> list = new HashSet<AssessmentQuestion>();
		Set<AssignedAssessment> assignStudent = new HashSet<AssignedAssessment>();

		float tMarks = (float) totalMarks;
		int i = 0;
		//result.remove(result.size() - 1);

		for (String qId : result)
		{
			AssessmentQuestion aq = new AssessmentQuestion();
			char testT = qId.trim().charAt(0);
			Long questId = Long.parseLong(qId.trim().substring(1, qId.length()-1).toString());
			switch (testT)
			{
				case 'd':
						aq.setMarks(Float.parseFloat(dMarks));
					break;
				case 'm':
					aq.setMarks(Float.parseFloat(iMarks));
					break;
				case 'e':
					aq.setMarks(Float.parseFloat(eMarks));
					break;
			}

			aq.setQuestionId(questId);

			aq.setSerial(++i);
			aq.setCreatedOn(new Date());
			aq.setStatus(1);
			aq.setAssessment(ass);
			list.add(aq);
		}
		String[] sIds = studentsIds.split(",");
		for (String sId : sIds)
		{
			AssignedAssessment assign = new AssignedAssessment();
			// assign.setAssessment(ass);
			assign.setStatus(AssignedAssessment.STATUS_ACTIVE);
			// assign.setAssessment(ass);
			assign.setAssignedDate(new Date());
			assign.setUserId(Long.parseLong(sId));
			assign.setAssignedBy(userId);
			assign.setSectionId(secId);
			assignStudent.add(assign);

		}

		ass.setMaxMarks(tMarks);
		ass.setAssessmentQuestion(list);
		ass.setAssignedAssessment(assignStudent);
		ass.setTopicIds(topicIds);
		ass.setChapterIds(chpaterIds);

		return schoolTeacherDao.createAssessment(ass);

	}
*/
	
	
	
	
	@Transactional
	@Override
	public String addAutoCreateAssessment(String result, String testName, Integer testType, String startDate, String endDate, Long gradeId, Long subjectId,
			Long totalQuestion, Integer duration, String instruction, Long schoolId, long userId, Long sessionId, 
			Integer totalMarks, String studentsIds, Long secId, String chpaterIds, String topicIds)
	{
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);
		String newEndDate = endDate;
		if (endDate.equals("00/default/0000 00:00") || endDate.equals("____/__/__ __:__"))
		{
			Session s = (Session) schoolTeacherDao.getSessionObj(sessionId);
			newEndDate = Utility.convertDateToString(s.getEndDate(), "dd/MMM/yyyy HH:mm");

		}

		Assessment ass = new Assessment();
		try
		{
			Date sdate = format.parse(startDate);
			Date edate = format.parse(newEndDate);

			ass.setStartDate(sdate);
			ass.setEndDate(edate);
			
			
			ass.setDateTimeZone(Utility.convertDateToStringWithZone(sdate));
			ass.setDateTimeZoneEnd(Utility.convertDateToStringWithZone(edate) );
			 
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		ass.setTestName(testName);
		ass.setCreatedBy(userId);
		ass.setStatus(2);
		ass.setCreatedOn(new Date());
		ass.setSubjectId(new Long(subjectId));
		ass.setGradeId(new Long(gradeId));
		ass.setSchoolId(new Long(schoolId));
		ass.setInstruction(instruction.replaceAll("\n", "<br>").replaceAll("\r", "<br>"));
		ass.setSessionId(sessionId);

		ass.setType(testType);
		ass.setDuration(duration);
		Set<AssessmentQuestion> list = new HashSet<AssessmentQuestion>();
		Set<AssignedAssessment> assignStudent = new HashSet<AssignedAssessment>();

	//	float tMarks = (float)  (result.size()*2);
		int i = 0;
		String[] qMIds = result.split(",");
		for(String qId : qMIds)
		{
			AssessmentQuestion aq = new AssessmentQuestion();
			String[] mQId=qId.split("#");
			
			
				aq.setQuestionId(Long.parseLong(mQId[0].toString()));
				aq.setMarks(Float.parseFloat(mQId[1].toString()));
				aq.setSerial(++i);
				aq.setCreatedOn(new Date());
				aq.setStatus(1);
				aq.setAssessment(ass);
				list.add(aq);
		}
		//result.remove(result.size()-1);
		/*for (String qId : result)
		{

			AssessmentQuestion aq = new AssessmentQuestion();
			aq.setQuestionId(Long.parseLong(qId.trim()));
			aq.setMarks(2F);
			aq.setSerial(++i);
			aq.setCreatedOn(new Date());
			aq.setStatus(1);
			aq.setAssessment(ass);
			list.add(aq);
		}*/
		String[] sIds = studentsIds.split(",");
		for (String sId : sIds)
		{
			AssignedAssessment assign = new AssignedAssessment();
			 assign.setAssessment(ass);
			assign.setStatus(AssignedAssessment.STATUS_ACTIVE);
			 assign.setAssessment(ass);
			assign.setAssignedDate(new Date());
			assign.setUserId(Long.parseLong(sId));
			assign.setAssignedBy(userId);
			assign.setSectionId(secId);
			assignStudent.add(assign);

		}

		ass.setMaxMarks(totalMarks);
		ass.setAssessmentQuestion(list);
		ass.setAssignedAssessment(assignStudent);
		ass.setTopicIds(topicIds);
		ass.setChapterIds(chpaterIds);

		return schoolTeacherDao.createAssessment(ass);

	}

	
	@Override
	@Transactional
	public void saveUpdateQuestionComment(GlobalVO globalVO) throws IOException
	{
		AddUpdateQuestionDetails(globalVO, "");
	}

	@Transactional
	@Override
	public String updateExistingQuestions(File excelFile, long userId, Long schoolId)
	{
		try
		{
			ByteArrayInputStream bis = new ByteArrayInputStream(FileUtils.readFileToByteArray(excelFile));
			XSSFWorkbook workbook = new XSSFWorkbook(bis);
			XSSFSheet sheet = workbook.getSheetAt(0);
		
			Iterator<Row> rowIterator = sheet.iterator();
			List<Question> questionList = new ArrayList<Question>();
			List<QuestionTopic> questionTopicList = new ArrayList<QuestionTopic>();
			Date date = new Date();
			int counter = 2;
			DataFormatter formate = new DataFormatter();
			Map<String, Long> topicIdMap = new HashMap<String, Long>();
			Map<String, Long> questionTypeMap = new HashMap<String, Long>();

			if (rowIterator.hasNext())
			{
				rowIterator.next();
			}
			else
			{
				return "Please fill the content !!!";
			}

			while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();

				Cell cellBoard = row.getCell(0);
				Cell cellGrade = row.getCell(1);
				Cell cellSubject = row.getCell(2);
				Cell cellChapter = row.getCell(3);
				Cell cellTopic = row.getCell(4);

				Cell cellQuestionId = row.getCell(5);

				Cell cellQDifficultyLevel = row.getCell(16);
				Cell cellQCognitiveLevel = row.getCell(17);

				String boardName = cellBoard != null ? handleCell(cellBoard, cellBoard.getCellType()).trim() : "";
				String gradeName = cellGrade != null ? handleCell(cellGrade, cellGrade.getCellType()).trim() : "";
				String subjectName = cellSubject != null ? handleCell(cellSubject, cellSubject.getCellType()).trim() : "";
				String chapterName = cellChapter != null ? handleCell(cellChapter, cellChapter.getCellType()).trim() : "";
				String topicName = cellTopic != null ? handleCell(cellTopic, cellTopic.getCellType()).trim() : "";

				Long topicId = null;
				if (notEmpty(boardName) && notEmpty(gradeName) && notEmpty(subjectName) && notEmpty(chapterName) && notEmpty(topicName))
				{
					String key = boardName + "_" + gradeName + "_" + subjectName + "_" + chapterName + "_" + topicName;
					topicId = topicIdMap.get(key);
					if (topicId == null)
					{
						topicId = schoolTeacherDao.getTopicIdByGradeSubjectChapterTopic(boardName.trim(), gradeName.trim(), subjectName.trim(), chapterName.trim(), topicName);
						if (topicId != null && topicId > 0l)
						{
							topicIdMap.put(key, topicId);
						}
						else
						{
							return "Either board or grade or subject or chapter or topic name is not exists at row " + counter;
						}
					}
				}
				else
				{
					return "Please provide proper Details of Board, Grade, Subject, Chapter, Topic at row" + counter;
				}

				String questionId = (cellQuestionId != null ? handleCell(cellQuestionId, cellQuestionId.getCellType()) : "");

				String qtDifficultyLevel = formate.formatCellValue(cellQDifficultyLevel).trim();
				String qtCognitiveLevel = formate.formatCellValue(cellQCognitiveLevel).trim();

				Long difficultyId = null;
				Long cognitiveId = null;
				if (notEmpty(qtDifficultyLevel))
				{
					difficultyId = questionTypeMap.get(qtDifficultyLevel);
					if (difficultyId == null)
					{
						difficultyId = schoolTeacherDao.getTypeId(qtDifficultyLevel);
						questionTypeMap.put(qtDifficultyLevel, difficultyId);
					}

				}
				else
				{
					return "Please provide correct Difficulty Level !!";
				}

				if (notEmpty(qtCognitiveLevel))
				{
					cognitiveId = questionTypeMap.get(qtCognitiveLevel);
					if (cognitiveId == null)
					{
						cognitiveId = schoolTeacherDao.getTypeId(qtCognitiveLevel);
						questionTypeMap.put(qtCognitiveLevel, cognitiveId);
					}

				}
				else
				{

					return "Please provide correct Cognitive Level !!" + counter;
				}

				if (notEmpty(questionId))
				{
					Long quesTopicId = null;
					Long quesId = (long) (Double.parseDouble(questionId));
					QuestionTopic qt = new QuestionTopic();
					quesTopicId = schoolTeacherDao.getQuestionTopicId(topicId, quesId, difficultyId, cognitiveId);
					if (quesTopicId == null)
					{

						qt.setTopicId(topicId);
						qt.setQuestionId(quesId);
						qt.setDifficultyLevelId(difficultyId);
						qt.setCognitiveLevelId(cognitiveId);
						qt.setInsertDate(date);
						qt.setModifiedDate(date);
						qt.setStatus(QuestionTopic.STATUS_ACTIVE);
						questionTopicList.add(qt);

					}
				}
				else
				{
					return "Question Id has been modified !!" + counter;
				}

				counter++;
			}

			for (QuestionTopic tq : questionTopicList)
			{
				questiTopicDao.saveEntity(tq);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "Error saving excel file";
		}
		return "success";

	}

	@Override
	@Transactional
	public List<StudentTeacherSession> getTeacherGradeSubjectDetails(Long userId, Long schoolId)
	{
		return schoolTeacherDao.getTeacherGradeSubjectDetails(userId, schoolId);
	}

	@Override
	@Transactional
	public Map<Long, Integer> getStudentCountBySectionIds(List<Long> sectionIds, Long schoolId, List<Long> subjectIds)
	{
		return schoolTeacherDao.getStudentCountBySectionIds(sectionIds, schoolId, subjectIds);
	}

	@Override
	@Transactional
	public List getTeacherSubjectStudat(List<Long> sectionIds, Long schoolId, List<Long> subjectIds)
	{
		return schoolTeacherDao.getTeacherSubjectStudat(sectionIds, schoolId, subjectIds);
	}

	@Override
	@Transactional
	public List<Assessment> getUpcomingAssessment(UserForm loginUser, Long gradeId, Long subjectId)
	{

		return schoolTeacherDao.getUpcomingAssessment(loginUser, gradeId, subjectId);
	}

	@Override
	@Transactional
	public List<Assessment> getCompletedAssessment(UserForm loginUser, Long gradeId, Long subjectId)
	{

		return schoolTeacherDao.getCompletedAssessment(loginUser, gradeId, subjectId);
	}

	@Override
	@Transactional
	public List<User> getUnassignStudent(Long subId, Long secId)
	{
		return schoolTeacherDao.getUnassignStudent(subId, secId);
	}

	@Override
	@Transactional
	public List<Assessment> getAssignmentDetail(Long gradeId, Long subjectId)
	{
		return schoolTeacherDao.getAssignmentDetail(gradeId, subjectId);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getTopicAssignedStudents(Long id, Long userId)
	{

		return schoolTeacherDao.getTopicAssignedStudents(id, userId);
	}

	@Override
	@Transactional
	public List<Long> getAllTopicIdsByChapter(Long subjectId, Long chapterId)
	{
		return schoolTeacherDao.getAllTopicIdsByChapter(subjectId, chapterId);
	}

	@Override
	@Transactional
	public Long getStudentCountBySectionId(Long subjectId, Long secId)
	{
		return schoolTeacherDao.getStudentCountBySectionIds(subjectId, secId);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getAllAssignTopicListByUser(List<Long> topicIds, Long userId, Long subjectId, Long sectionId)
	{
		return schoolTeacherDao.getAllAssignTopicListByUser(topicIds, userId, subjectId, sectionId);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getAllAssignModuleListByUser(Long topicId, Long userId, Long subjectId, Long secId)
	{
		return schoolTeacherDao.getAllAssignModuleListByUser(topicId, userId, subjectId, secId);
	}

	@Override
	@Transactional
	public List<Long> getAllTopicIdsInSubject(Long subjectId, Long schoolId)
	{
		return schoolTeacherDao.getAllTopicIdsInSubject(subjectId, schoolId);
	}

	@Override
	@Transactional
	public Long getAllQuestionCountByTopicIds(List<Long> topicIds)
	{
		return schoolTeacherDao.getAllQuestionCountByTopicIds(topicIds);
	}

	@Override
	@Transactional
	public Assessment getAllTestDetails(UserForm loginUser, Long grdId, Long subId, Long assesmentId)
	{
		return schoolTeacherDao.getAllTestDetails(loginUser, grdId, subId, assesmentId);
	}

	@Override
	@Transactional
	public Assessment getUnattemtedAllTestDetails(UserForm loginUser, Long grdId, Long subId, Long assesmentId)
	{
		return schoolTeacherDao.getUnattemtedAllTestDetails(loginUser, grdId, subId, assesmentId);
	}

	
	
	
	@Override
	@Transactional
	public Boolean unassignStudentfromAssignment(Long gId, Long sId, Long assmentId, Long[] unassignUserLst)
	{
		return schoolTeacherDao.unassignStudentfromAssignment(gId, sId, assmentId, unassignUserLst);
	}

	/* @Override public List<Assessment> getAllTestDetail(Long grdId, Long subId, Long assesmentId) { // TODO Auto-generated method stub return null; } */

	@Override
	@Transactional
	public List<Long> getMarkTopics(Long userId, Long subjectId)
	{
		return schoolTeacherDao.getMarkTopics(userId, subjectId);

	}
	@Override
	@Transactional
	public List<Long> getMarkTopicsContentPermission(Long userId, Long subjectId)
	{
		return schoolTeacherDao.getMarkTopicsContentPermission(userId, subjectId);

	}

	@Override
	@Transactional
	public List<User> getStudentCompletedAssessment(UserForm loginUser, Long grdId, Long subId, String search, Long secId)
	{
		return schoolTeacherDao.getStudentCompletedAssessment(loginUser, grdId, subId, search, secId);
	}

	@Override
	@Transactional
	public List<SchoolStudentTest> searchStudentTestDetail(UserForm loginUser, Long grdId, Long subId, Long secId, Long studentId)
	{
		return schoolTeacherDao.searchStudentTestDetail(loginUser, grdId, subId, secId, studentId);
	}

	@Override
	@Transactional
	public Map<Long, Integer> getTopicCountBySubjectId(List<Long> subjectIdsSet, Long gradeId)
	{
		return schoolTeacherDao.getTopicCountBySubjectId(subjectIdsSet, gradeId);
	}

	@Override
	@Transactional
	public Map<Long, Integer> getTeacherProgressTopicCountBySubjectId(List<Long> subIds, Long userId, Long sectionId)
	{
		return schoolTeacherDao.getTeacherProgressTopicCountBySubjectId(subIds, userId, sectionId);
	}

	@Override
	@Transactional
	public Subject getSubjectObjById(Long subId)
	{

		return schoolTeacherDao.getSubjectObjById(subId);
	}
	

	@Override
	@Transactional(rollbackFor = { IOException.class })
	public String updateModuleResources(GlobalVO globalVO) throws IOException
	{
		return schoolTeacherDao.updateModuleResources(globalVO);
	}

	@Override
	@Transactional
	public Grade getGradeObjById(Long gradeId)
	{
		return gradeDao.getGradeObjById(gradeId);
	}

	@Override
	@Transactional
	public Section getSectionObjById(Long secId)
	{
		return sectionDao.getSectionObjById(secId);
	}

	@Override
	@Transactional
	public Topic getTopicobjById(Long topicId)
	{
		return topicDao.getTopicObj(topicId);
	}

	@Override
	@Transactional
	public List<String> getChapterNamesByChapIds(String chapIds)
	{
		return chapterDao.getChapterNamesByChapIds(chapIds);
	}

	@Override
	@Transactional
	public List<String> getTopicNamesbyIds(String topicIds)
	{
		return topicDao.getTopicNamesbyIds(topicIds);
	}

	@Override
	@Transactional
	public Integer updateUpcomingAssessment(Assessment ass)
	{
		return schoolTeacherDao.updateUpcomingAssessment(ass);

	}

	@Override
	@Transactional
	public List<Long> getAllAssignedTopicByTopicIds(List<Long> topicIdList, Long secId, Long grdId, Long subId)
	{
		return schoolTeacherDao.getAllAssignedTopicByTopicIds(topicIdList, secId, grdId, subId);
	}

	@Override
	@Transactional
	public List<Assessment> getCreatedTestByTeacher(UserForm loginUser, Long subId, Long grdId)
	{
		

		return schoolTeacherDao.getCreatedTestByTeacher(loginUser, subId, grdId);

	}

	@Override
	@Transactional
	public String updateResource(GlobalVO globalVO)
	{

		return schoolTeacherDao.updateResource(globalVO);

	}


	@Override
	@Transactional
	public List<QuestionTopic> autoPreviewAssessment(List<String> result, String topicIds, long id, Long schoolId)
	{
		
		return schoolTeacherDao.autoPreviewAssessment(result,topicIds, id, schoolId);
	}


	@Override
	@Transactional
	public List<AssignTopicResources> getAssignedTopicReport(Long grdId, Long subId, Long secId,long userId)
	{
		return schoolTeacherDao.getAssignedTopicReport(grdId,subId,secId,userId);
	}

	@Override
	@Transactional
	public List<AssignTopicResources> getAssignedResourceReport(Long grdId, Long subId, Long secId, long userid)
	{
		return schoolTeacherDao.getAssignedResourceReport(grdId,subId,secId,userid) ;
	}

	/*@Override
	@Transactional
	public List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId)
	{
		return schoolTeacherDao.getAllEnableDisableQuestionsByTopic(topicId);
	}*/
	
	@Override
	@Transactional
	public List<QuestionTopic> getAllEnableDisableQuestionsByTopic(Long topicId,Long loid)
	{
		return schoolTeacherDao.getAllEnableDisableQuestionsByTopic(topicId,loid);
	}



	@Override
	@Transactional
	public String removeAssmt(Long assmtId, Long subId, Long grdId, long id, Long schoolId)
	{
		return schoolTeacherDao.removeAssmt(assmtId,subId,grdId,id,schoolId);
	}

	@Override
	@Transactional
	public String getSessionDate(UserForm loginUser)
	{
		String newEndDate = "";
		Session s = (Session) schoolTeacherDao.getSessionObj(loginUser.getSessionId());
		newEndDate = Utility.convertDateToString(s.getEndDate(), "dd/MMM/yyyy HH:mm");
		return newEndDate;
	}


	

	@Override
	@Transactional
	public List<QuestionTopic> getPageQuestionsByTopic(int result, int offsetreal, Long topicId)
	{
		return schoolTeacherDao.getPageQuestionsByTopic(result,offsetreal,topicId);
		
	}

	@Override
	@Transactional
	public int getSize(Long topicId)
	{
		return schoolTeacherDao.getSize(topicId);
	}

	@Override
	@Transactional
	public List<User> getUserDetailForEmail(List<Long> id,int userType)
	{
		
			
			return schoolTeacherDao.getUserDetailForEmail(id,userType);
	}

	
}