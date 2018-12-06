package com.flp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.RetailUserDAO;
import com.flp.dao.RetailUserQueryDao;
import com.flp.dao.TopicDao;
import com.flp.model.ParentChildMapping;
import com.flp.model.RetailUserQuery;
import com.flp.model.Subject;
import com.flp.model.Topic;
import com.flp.model.UserForm;
import com.flp.model.UserTest;
import com.flp.model.UserTestDetail;
import com.flp.service.RetailUserService;

@Service
public class RetailUserServiceImpl implements RetailUserService
{

	@Autowired
	RetailUserDAO retailUserDAO;

	@Autowired
	RetailUserQueryDao retailUserQueriesDao;

	@Autowired
	TopicDao topicDao;

	@Override
	@Transactional
	public List<Subject> getSubjects(long id, Long boardId, Long sectionId)
	{
		return retailUserDAO.getSubjects(id, boardId, sectionId);
	}

	/* name subId time total topicIds */@Transactional
	@Override
	public String saveTest(HttpServletRequest request) {
	String msg=null;
	
	try {
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		String testName=request.getParameter("name");
		String time=request.getParameter("time");
		String topicIds=request.getParameter("topicIds");
		String hours=time.split(":")[0];
		String mins=time.split(":")[1];
		int m=Integer.parseInt(mins);
		m=m+(Integer.parseInt(hours)*60);
		
		
		
		
		
		
		Integer subjectId=Integer.parseInt(request.getParameter("subId"));
		Integer total=Integer.parseInt(request.getParameter("total"));
		msg =retailUserDAO.saveTest(loginUser,testName, m, topicIds, subjectId, total);
		
	} catch (NumberFormatException e) {
		msg="Invalid Number of question or subject";
		e.printStackTrace();
	}catch (NullPointerException e) {
		msg="1 or more required value is missing";
		e.printStackTrace();
	}
	catch (Exception e) {
		msg="Internal server error: "+ e.getMessage();
		e.printStackTrace();
	}
	
	return msg!=null?msg:"success";
	}



	@Override
	@Transactional
	public List<RetailUserQuery> getRetailUserQueries(Long userId, Long gradeId, Boolean includeLowerGrades, Integer status)
	{
		return retailUserQueriesDao.getRetailUserQueries(userId, gradeId, includeLowerGrades, status);
	}

	@Transactional
	@Override
	public List<Topic> getTopics(Long boardId, Long gradeId, Integer status)
	{
		return topicDao.getTopics(boardId, gradeId, status);
	}

	@Override
	@Transactional
	public List<UserTest> getTestList(long id, Long boardId, Long gradeId)
	{
		return retailUserDAO.getTestList(id, boardId, gradeId);
	}



@Override
@Transactional
public Map<Long, Integer> getAvgTopicSmartScore(List<Long> topicIds)
{
	
	return retailUserDAO.getAvgTopicSmartScore(topicIds);

	
}

@Override
@Transactional
public UserTest getTest(long testId)
{
	return retailUserDAO.getTest(testId);
}

@Override
@Transactional
public Map<Long, Integer> getAllTopicSmartScoreOfUser(Long userId)
{
	return retailUserDAO.getAllTopicSmartScoreOfUser(userId);
}



@Override
@Transactional
public List<Topic> getSuggestedTopicLst(List<Long> subjectIds, Integer randomTopicLimit, List<Long> topicIds, Long gradeId, Long boardId)
{
	return retailUserDAO.getSuggestedTopicLst(subjectIds,randomTopicLimit,topicIds,gradeId,boardId);
}



@Transactional
@Override
public void saveTest(long subjectId, int total, int correct, int incorrect,
		int unAttempted, UserForm loginUser, List<String> questionDetail,
		long assessmentId) {
	
	List<UserTestDetail> testDetailsList=new ArrayList<>();
	UserTest test=new UserTest();
	test.setId(assessmentId);
	//test.setMarks(marks);
	//test.setSchoolId(loginUser.getSchoolId());
	//test.setSessionId(loginUser.getSessionId());
	//test.setSubjectId(subjectId);
	test.setTotalQuestion(total);
	test.setTotalCorrect(correct);
	test.setTotalIncorrect(incorrect);
	test.setTotalUnAttempted(unAttempted);
	//test.setStudentId(loginUser.getId());
	test.setStatus(1);
	test.setPercentage(Float.parseFloat(String.format("%.2f", ((float)correct*100)/total)));
	
	for (String s:questionDetail) {
		String[] ar=s.split("#");
		String ansString;
		if(ar[2].trim().equals(""))
			ansString="";
		else
			ansString=ar[2].replaceAll("@", ",").substring(0,(ar[2].length()-1));
		
		UserTestDetail tDetail=new UserTestDetail();
		tDetail.setQuestionId(Long.parseLong(ar[0]));
		tDetail.setResult(Integer.parseInt(ar[1]));
		tDetail.setAnswerIds(ansString);
		tDetail.setId(Long.parseLong(ar[3]));
		testDetailsList.add(tDetail);
	}
	
	retailUserDAO.saveTest(test);
	
	
	for (UserTestDetail s: testDetailsList) {
		//s.setSchoolStudentTestId(testId);
		retailUserDAO.saveTestDetail(s);
		
		System.out.println(s.getQuestionId());
		System.out.println(s.getId());
		System.out.println(s.getAnswerIds());
		System.out.println(s.getResult());
		System.out.println("________________");
	}
	
	
}

@Override
@Transactional
public List<ParentChildMapping> getParentRequestList(Long studentId)
{
	return retailUserQueriesDao.getParentRequestList(studentId);
}

@Override
@Transactional
public Boolean acceptRejectParentLinking(Long parentLinkingId,Integer acceptOrReject)
{
	return retailUserQueriesDao.acceptRejectParentLinking(parentLinkingId,acceptOrReject);
}

}
