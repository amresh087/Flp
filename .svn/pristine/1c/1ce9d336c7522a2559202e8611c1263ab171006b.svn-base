package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.flp.util.Utility;

@Entity
@Table(name = "t_user")
public class User implements Serializable
{
	private static final long serialVersionUID = 5833640476085734266L;

	// Status
	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;

	// user type
	public static final int USERTYPE_ADMIN = 1;
	public static final int USERTYPE_STUDENT = 2;
	public static final int USERTYPE_PARENT = 3;
	public static final int USERTYPE_DEMO = 4;
	public static final int USERTYPE_SCHOOL_COORDINATOR = 5;
	public static final int USERTYPE_SCHOOL_SME = 6;
	public static final int USERTYPE_TEACHER = 7;
	public static final int USERTYPE_USER = 8;
	public static final int USERTYPE_MASTER_SME = 99;

	@Id
	@GeneratedValue
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_username")
	private String userName;

	@Column(name = "f_dateofbirth")
	private Date dateOfBirth;

	@Column(name = "f_admissionNo")
	private String admissionNo;

	@Column(name = "f_emailId")
	private String emailId;

	@Column(name = "f_password")
	private String password;

	@Column(name = "f_mobileno")
	private String mobileNo;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_created_date")
	private Date createdDate;

	@Column(name = "f_modify_date")
	private Date modifyDate;

	@Column(name = "f_createdBy")
	private Long createdBy;

	@Column(name = "f_usertype")
	private Integer userType;

	@Column(name = "f_boardid")
	private Long boardId;

	@Column(name = "f_gradeId")
	private Long gradeId;

	@Column(name = "f_city")
	private String city;

	@Column(name = "f_firstName")
	private String firstName;

	@Column(name = "f_lastName")
	private String lastName;

	@Column(name = "f_profilePath")
	private String profilePath;

	@Column(name = "f_resettoken")
	private String resettoken;

	@Column(name = "f_state")
	private String state;

	@Column(name = "f_pinCode")
	private Long pinCode;

	@Column(name = "f_subscriptionid")
	private Long subscriptionid;

	@Column(name = "f_address")
	private String address;
	@Column(name = "f_addressTwo")
	private String addressTwo;
	
	@Column(name = "f_gender")
	private String gender;

	@Column(name = "f_signUpComplete")
	private Integer signUpComplete;

	@Column(name = "f_discountCoupon")
	private String discountCoupon;

	@Column(name = "f_ipAddress")
	private String ipAddress;

	@Column(name = "f_parentId")
	private Long parentId;

	@Column(name = "f_sectionId")
	private Long sectionId;

	@Column(name = "f_schoolId")
	private Long schoolId;
	@Column(name = "f_schoolIdCode")
	private String schoolIdCode;

 

	public String getSchoolIdCode()
	{
		return schoolIdCode;
	}

	public void setSchoolIdCode(String schoolIdCode)
	{
		this.schoolIdCode = schoolIdCode;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "f_schoolId", insertable = false, updatable = false, nullable = false)
	private School school;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_gradeId", insertable = false, updatable = false, nullable = false)
	private Grade grade;

	@Transient
	private String encryptedToken;

	@Transient
	private String url;

	@Transient
	private String studentName;
	@Transient
	private String gradeName;
	@Transient
	private String sectionName;
	@Transient
	private Long sessionId;

	@Transient
	private List<Long> unassignedSubjectId;

	@Transient
	private CommonsMultipartFile fileData;
	
	@Column(name = "f_dateTimeZone")
	private String dateTimeZone = Utility.convertDateToStringWithZone(new Date());

	public String getDateTimeZone()
	{
		return dateTimeZone;
	}

	public void setDateTimeZone(String dateTimeZone)
	{
		this.dateTimeZone = dateTimeZone;
	}

	// getters & setters

	public Long getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Long sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getGradeName()
	{
		return gradeName;
	}

	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}

	public String getSectionName()
	{
		return sectionName;
	}

	public void setSectionName(String sectionName)
	{
		this.sectionName = sectionName;
	}

	public Long getId()
	{
		return id;
	}

	public List<Long> getUnassignedSubjectId()
	{
		return unassignedSubjectId;
	}

	public void setUnassignedSubjectId(List<Long> unassignedSubjectId)
	{
		this.unassignedSubjectId = unassignedSubjectId;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName.trim();
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMobileNo()
	{
		return mobileNo;
	}

	public void setMobileNo(String mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public Long getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Long createdBy)
	{
		this.createdBy = createdBy;
	}

	public Integer getUserType()
	{
		return userType;
	}

	public void setUserType(Integer userType)
	{
		this.userType = userType;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName.trim();
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getProfilePath()
	{
		return profilePath;
	}

	public void setProfilePath(String profilePath)
	{
		this.profilePath = profilePath;
	}

	public String getResettoken()
	{
		return resettoken;
	}

	public void setResettoken(String resettoken)
	{
		this.resettoken = resettoken;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Long getSubscriptionid()
	{
		return subscriptionid;
	}

	public String getEncryptedToken()
	{
		return encryptedToken;
	}

	public void setEncryptedToken(String encryptedToken)
	{
		this.encryptedToken = encryptedToken;
	}

	public void setSubscriptionid(Long subscriptionid)
	{

		this.subscriptionid = subscriptionid;
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
	
	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Integer getSignUpComplete()
	{
		return signUpComplete;
	}

	public void setSignUpComplete(Integer signUpComplete)
	{
		this.signUpComplete = signUpComplete;
	}

	public String getDiscountCoupon()
	{
		return discountCoupon;
	}

	public void setDiscountCoupon(String discountCoupon)
	{
		this.discountCoupon = discountCoupon;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public School getSchool()
	{
		return school;
	}

	public void setSchool(School school)
	{
		this.school = school;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo.trim();
	}

	public Long getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(Long sectionId)
	{
		this.sectionId = sectionId;
	}

	public CommonsMultipartFile getFileData()
	{
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData)
	{
		this.fileData = fileData;
	}

	public Grade getGrade()
	{
		return grade;
	}

	public void setGrade(Grade grade)
	{
		this.grade = grade;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth()
	{
		return this.dateOfBirth;
	}

	public Long getPinCode()
	{
		return pinCode;
	}

	public void setPinCode(Long pinCode)
	{
		this.pinCode = pinCode;
	}
}
