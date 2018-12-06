package com.flp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flp.util.Utility;

public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3933830446322117394L;
	// user type
	public static final Integer USERTYPE_STUDENT = 2;
	public static final Integer USERTYPE_PARENT = 3;
	public static final Integer USERTYPE_DEMO = 4;

	private long id;
	private long parentid;
	private String email;
	private String userName;
	private Integer userType;
	private byte gScore;
	private Long gradeId;
	private Long boardId;
	private String name;
	private String profilePath;
	private Long schoolId;
	private Long sessionId;
	private String schoolName;
	private List<Subject> subjects;
	private Long sectionId;
	private Long selectedSubject;
	private String gradeName;
	private String staticRootPath;
	private String admissionNo;
	
private Date studentDob;



private String mobileNo;
private String address;
private String addressTwo;
private String city;
private Long pinCode;
private String state;
	
	public Long getSelectedSubject() {
		return selectedSubject;
	}



	public void setSelectedSubject(Long selectedSubject) {
		this.selectedSubject = selectedSubject;
	}



	public UserForm(User user,Long sessionId, List<SubjectSection> subjectSections) {
		this.id = user.getId();
		this.email = user.getEmailId();
		this.userType = user.getUserType();
		this.gradeId = user.getGradeId();
		this.name = user.getFirstName();
		this.boardId = user.getBoardId();
		this.userName=user.getUserName();
		this.sectionId = user.getSectionId();
		this.staticRootPath=Utility.staticContentPath;
		if(subjectSections != null && subjectSections.size() > 0){
			subjects = new ArrayList<Subject>();
			for(SubjectSection s : subjectSections){
				this.subjects.add(s.getSubject());	
			}
		}
		
		if (user.getParentId() != null) {
			this.parentid = user.getParentId();
		}
		if (user.getProfilePath() != null) {
        this.setProfilePath(user.getProfilePath());
		}
		if(user.getSchoolId()!=null){
			this.schoolId=user.getSchoolId();
		}
		if(sessionId!=null){
			this.setSessionId(sessionId);
		}
		if(user.getSchool()!=null){
			this.schoolName=user.getSchool().getSchoolName();
		}
		if(user.getGrade()!=null){
			this.gradeName=user.getGrade().getName();
		}
		if(user.getAdmissionNo()!=null){
			this.admissionNo=user.getAdmissionNo();
		}
		if(user.getDateOfBirth()!=null){
			this.studentDob=user.getDateOfBirth();
		}
	
		if(user.getAddress()!=null){
			this.address=user.getAddress();
		}
		
		if(user.getAddressTwo()!=null){
			this.addressTwo=user.getAddressTwo();
		}
		
		if(user.getCity()!=null){
			this.city=user.getCity();
		}
		if(user.getPinCode()!=null){
			this.pinCode=user.getPinCode();
		}
		if(user.getState()!=null){
			this.state=user.getState();
		}
		if(user.getMobileNo()!=null){
			this.mobileNo=user.getMobileNo();
		}
		
	}

	

	public String getAdmissionNo()
	{
		return admissionNo;
	}



	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}



	public List<Subject> getSubjects() {
		return subjects;
	}



	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}



	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public byte getgScore() {
		return gScore;
	}



	public void setgScore(byte gScore) {
		this.gScore = gScore;
	}



	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}



	public String getGradeName()
	{
		return gradeName;
	}



	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}



	public String getStaticRootPath()
	{
		return staticRootPath;
	}



	public void setStaticRootPath(String staticRootPath)
	{
		this.staticRootPath = staticRootPath;
	}



	public Date getStudentDob()
	{
		return studentDob;
	}



	public void setStudentDob(Date studentDob)
	{
		this.studentDob = studentDob;
	}

public String getAddress()
{
	return address;
}



public void setAddress(String address)
{
	this.address = address;
}



public String getAddressTwo()
{
	return addressTwo;
}



public void setAddressTwo(String addressTwo)
{
	this.addressTwo = addressTwo;
}



public String getCity()
{
	return city;
}



public void setCity(String city)
{
	this.city = city;
}



public Long getPinCode()
{
	return pinCode;
}



public void setPinCode(Long pinCode)
{
	this.pinCode = pinCode;
}



public String getState()
{
	return state;
}



public void setState(String state)
{
	this.state = state;
}



public String getMobileNo()
{
	return mobileNo;
}



public void setMobileNo(String mobileNo)
{
	this.mobileNo = mobileNo;
}

	
	
}
