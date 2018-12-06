package com.flp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flp.model.Board;
import com.flp.model.ParentChildMapping;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.model.UserLogDetails;
import com.flp.service.RetailUserService;
import com.flp.service.SchoolAdminService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
import com.flp.util.Utility;
/**
 * 
 * @author Amresh
 *
 */
@Controller
@Scope("session")
public class UserAuthenticationController
{

	@Autowired
	private UserService userService;

	@Autowired
	private StudentCommonService studentCommonService;

	@Autowired
	private RetailUserService retailUserService;
	@Autowired
	private SchoolAdminService schoolAdminService;
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		// code for check user already login or not
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");
		if (loginUser != null)
		{
			User user = userService.getUserByEmailId(loginUser.getUserName());
			String returnType = getUserCredentialsDetails(user, user.getPassword(), user.getUserName(), request, response);
			return returnType;
		}
		// end
		model.addAttribute("user", new User());
		List<Board> boardList = studentCommonService.getAllBoardList();
		if (boardList != null)
		{
			model.addAttribute("boardList", boardList);
		}
		
		//schoolAdminService.updateMapping();
		return "index";
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/userlogin.htm", method = RequestMethod.POST)
	public String userLogin(@RequestParam(value = "username", required = false) String userName, @RequestParam(value = "password", required = false) String password, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		User user = userService.getUserByEmailId(userName.trim());
		String returnType = getUserCredentialsDetails(user, password, userName, request, response);
		return returnType;
	}
	/**
	 * 
	 * @param user
	 * @param password
	 * @param userName
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private String getUserCredentialsDetails(User user, String password, String userName, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if (user != null && user.getPassword().equals(password))
		{
			if (user.getStatus().equals((User.STATUS_ACTIVE)))
			{
				Long sessionId = null;
				
				
				if (user.getUserType().equals(User.USERTYPE_SCHOOL_COORDINATOR) || user.getUserType().equals(User.USERTYPE_TEACHER) || user.getUserType().equals(User.USERTYPE_STUDENT) || user.getUserType().equals(User.USERTYPE_SCHOOL_SME))
				{
					if(user.getSchool().getExpiryDate().getTime()< Utility.getCurrentDate().getTime()){
						request.getSession().setAttribute("errMsg1", "Your School Account is expiry!!!");
						return "redirect:index.htm";
					}
					
					Long schoolSessionId = schoolAdminService.getCurrentSchoolSession(user.getSchoolId());
					if (schoolSessionId != null)
					{
						sessionId = schoolSessionId;
					}

				}
				List<SubjectSection> subjectSections = null;
				if (user.getUserType().equals(User.USERTYPE_STUDENT) && user.getSchoolId() != null)
				{
					subjectSections = userService.getUserSubjects(sessionId, user.getId(), User.USERTYPE_STUDENT, user.getSectionId());
				}
				else if ((user.getUserType().equals(User.USERTYPE_DEMO) || user.getUserType().equals(User.USERTYPE_USER)) && user.getSchoolId() == null)
				{
					
					List<Subject> subjectList= retailUserService.getSubjects(user.getId(), user.getBoardId(), user.getSectionId());
					if(!subjectList.isEmpty()){
						subjectSections=new ArrayList<SubjectSection>();
						for(Subject s:subjectList){
							SubjectSection ss=new SubjectSection();
							ss.setSubject(s);
							subjectSections.add(ss);
						}
						
					}
				}

				UserForm userForm = new UserForm(user, sessionId, subjectSections);
				request.getSession().setAttribute("AUTHENTICATE", true);
				UserLogDetails uld = new UserLogDetails();
				
				String sessionId1=request.getSession().getId();
				uld.setUserId(user.getId());
				uld.setLoginTime(new Date());
				uld.setIpAddress(Utility.getUserIPAddress(request));
				uld.setSessionId(sessionId1);
				userService.saveLog(uld);
				user.setPassword(null);
				request.getSession().setAttribute("user", userForm);

				switch (user.getUserType())
				{
					case User.USERTYPE_ADMIN:
						Integer commentCnt=userService.getCommentCount();
						request.getSession().setAttribute("commentCnt", commentCnt);
						response.sendRedirect(request.getContextPath() + "/admin/getAdminHome.htm");
						return null;

					case User.USERTYPE_SCHOOL_COORDINATOR:
						response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm");
						return null;
						
					case User.USERTYPE_STUDENT:
						response.sendRedirect(request.getContextPath() + "/student/studentHome.htm");
						return null;
					case User.USERTYPE_DEMO:
					case User.USERTYPE_USER:
							response.sendRedirect(request.getContextPath() + "/user/retailUserHome.htm");
						return null;// "redirect:dashboard.htm";

					case User.USERTYPE_TEACHER:
						response.sendRedirect(request.getContextPath() + "/schoolTeacher/teacherHome.htm");
						return null;

					case User.USERTYPE_SCHOOL_SME:
						
						
						Integer commentCnt1=userService.getCommentCount();
						request.getSession().setAttribute("commentCnt", commentCnt1);
						response.sendRedirect(request.getContextPath() + "/admin/getAdminHome_for_SME.htm");
						
						//response.sendRedirect(request.getContextPath() + "/sme/getSmeHome.htm");
						return null;
						
					case User.USERTYPE_PARENT:
						response.sendRedirect(request.getContextPath() + "/parent/parentDashboard.htm");
						return null;
					
						
					default:
						return "redirect:index.htm";
				}

				/* if (user.getUserType().equals(User.USERTYPE_ADMIN)) { response.sendRedirect(request.getContextPath() + "/admin/getAdminHome.htm"); return null; } else if (user.getUserType().equals(User.USERTYPE_SCHOOL_COORDINATOR)) { response.sendRedirect(request.getContextPath() + "/schoolAdmin/studentDetails.htm"); return null; } else if (user.getUserType().equals(User.USERTYPE_DEMO) || user.getUserType().equals(User.USERTYPE_STUDENT)) { if(user.getSchoolId()!=null){
				 * response.sendRedirect(request.getContextPath() + "/student/studentHome.htm"); }else{ response.sendRedirect(request.getContextPath() + "/user/retailUserHome.htm"); } return null;// "redirect:dashboard.htm"; } else if (user.getUserType().equals(User.USERTYPE_TEACHER)) { response.sendRedirect(request.getContextPath() + "/schoolTeacher/teacherHome.htm"); return null; } else if (user.getUserType().equals(User.USERTYPE_SCHOOL_SME)) {
				 * response.sendRedirect(request.getContextPath() + "/sme/getSmeHome.htm"); return null; } else if(user.getUserType().equals(User.USERTYPE_USER)) { response.sendRedirect(request.getContextPath() + "/user/getHome.htm"); return null; } else { return "redirect:index.htm"; } */
			}
			else if (user.getResettoken() != null)
			{
				request.getSession().setAttribute("errMsg1", "Please verify your account from your emailId.");
				return "redirect:index.htm";
			}
			else
			{
				request.getSession().setAttribute("errMsg1", "Your Account is deactivated.Please contact at product@classteacher.com");
				return "redirect:index.htm";
			}
		}
		else
		{
			request.getSession().setAttribute("errMsg1", "Either username or password is incorrect");
			return "redirect:index.htm";
		}

	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userLoginPage.htm", method = RequestMethod.GET)
	public String getUserLoginPage()
	{
		return "login";
	}

	// save subscription details while new user Sign up
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activateUserAccount.htm")
	public ModelAndView activateUserAccount(HttpServletRequest request, Model model) throws Exception
	{
		String token = request.getParameter("token");
		String decrypToken = Utility.decrypt(token);
		User user = userService.getUserDetailsByTokenId(decrypToken);
		request.getSession().removeAttribute("user");
		if (user != null)
		{
			user.setResettoken(null);
			user.setStatus(User.STATUS_ACTIVE);
			user.setModifyDate(new Date());
			userService.SaveOrUpdate(user);
			request.getSession().setAttribute("successMsg1", "You are successfully activate your account.Please login");
		}
		else
		{
			request.getSession().setAttribute("errorMsg1", "url is not valid");
		}
		return new ModelAndView("redirect:index.htm");
	}
	/**
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logoutUser(HttpSession session)
	{
		String sessionId=session.getId();
		userService.updateLog(sessionId);
		
		session.invalidate();
		return "redirect:index.htm";
	}
	/**
	 * 
	 * @param token
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userParentLinking.htm")
	public ModelAndView userParentLinking(@RequestParam String token, HttpSession session) throws Exception
	{

		String decryptToken = Utility.decrypt(token);
		String[] val = decryptToken.split("\\&#");
		if (val != null && val.length > 2)
		{
			User parentObj = userService.getUserDetailsByTokenId(decryptToken);
			if (parentObj != null)
			{
				parentObj.setResettoken("");
				userService.SaveOrUpdate(parentObj);
				User studentObj = userService.getUserById(Long.parseLong(val[1]));
				studentObj.setParentId(parentObj.getId());
				userService.SaveOrUpdate(studentObj);
				saveUserParentLinking(studentObj.getId(), parentObj.getId());

			}
			else
			{
				session.setAttribute("tokenErr", "token is not valid");
			}
		}
		else
		{
			User userObj = userService.getUserDetailsByTokenId(decryptToken);
			if (userObj != null)
			{
				userObj.setResettoken("");
				session.setAttribute("parentLinkingWithStudentId", userObj.getId());
			}
			else
			{
				session.setAttribute("tokenErr", "token is not valid");
			}
		}

		return new ModelAndView("redirect:index.htm");
	}

	private void saveUserParentLinking(Long userId, Long parentId)
	{
		ParentChildMapping pcm = new ParentChildMapping();
		pcm.setJoinDate(new Date());
		pcm.setUserId(userId);
		pcm.setParentId(parentId);
		userService.saveParentChildMapping(pcm);

	}
	/**
	 * 
	 * @param token
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPasswordLink.htm")
	public ModelAndView resetPasswordLink(@RequestParam String token, HttpSession session) throws Exception
	{
		String decryptToken = Utility.decrypt(token);
		User userObj = userService.getUserDetailsByTokenId(decryptToken);
		if (userObj != null)
		{
			session.setAttribute("forgotPwdUserId", userObj.getId());
		}
		else
		{
			session.setAttribute("errMsg2", "Invalid token");

		}
		return new ModelAndView("redirect:index.htm");
	}
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/keepSesssionAlive.htm", method = RequestMethod.POST)
	public String keepSessionAlive()
	{
				return "sessionActive";
	}

}