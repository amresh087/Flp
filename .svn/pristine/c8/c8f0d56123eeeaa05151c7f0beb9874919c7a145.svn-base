package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.flp.util.Utility;

@Entity
@Table(name = "t_studentTeacherSession")
public class StudentTeacherSession implements Serializable
{
	private static final long serialVersionUID = -1666905921460518134L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_userId")
	private Long userId;

	@Column(name = "f_sessionId")
	private Long sessionId;

	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_sectionId")
	private Long sectionId;
	
	@Column(name = "f_subjectId")
	private Long subjectId;
	
	@Column(name = "f_createdOn")
	private Date createdOn;
	
	@Column(name = "f_userRole")
	private Integer userRole;

	@Column(name = "f_status")
	private Integer status;
	
	
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId",insertable=false,updatable=false, nullable=false)
	private Subject subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_userId",insertable=false,updatable=false, nullable=false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_sectionId",insertable=false,updatable=false, nullable=false)
	private Section section;
	
	@Transient
	private Integer totalQuestionCount;
	
	@Transient
	private Integer correctCount;
	
	@Transient
	private Integer incorrectCount;
	
	@Transient
	private Integer skipCount;
	
	@Transient
	private Integer percentage;
	
	@Transient
	private Integer userCount;
	
	
	@Transient
	private Integer totalQuestionAttempted;
	
	
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
	
	
	
	// setters & getters
	
	public Integer getTotalQuestionCount()
	{
		return totalQuestionCount;
	}

	public void setTotalQuestionCount(Integer totalQuestionCount)
	{
		this.totalQuestionCount = totalQuestionCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Long sessionId)
	{
		this.sessionId = sessionId;
	}

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public Integer getUserRole()
	{
		return userRole;
	}

	public void setUserRole(Integer userRole)
	{
		this.userRole = userRole;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Long getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(Long sectionId)
	{
		this.sectionId = sectionId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getCorrectCount()
	{
		return correctCount;
	}

	public void setCorrectCount(Integer correctCount)
	{
		this.correctCount = correctCount;
	}

	public Integer getIncorrectCount()
	{
		return incorrectCount;
	}

	public void setIncorrectCount(Integer incorrectCount)
	{
		this.incorrectCount = incorrectCount;
	}

	public Integer getSkipCount()
	{
		return skipCount;
	}

	public void setSkipCount(Integer skipCount)
	{
		this.skipCount = skipCount;
	}

	public Integer getPercentage()
	{
		return percentage;
	}

	public void setPercentage(Integer percentage)
	{
		this.percentage = percentage;
	}

	public Integer getUserCount()
	{
		return userCount;
	}

	public void setUserCount(Integer userCount)
	{
		this.userCount = userCount;
	}

	public Integer getTotalQuestionAttempted()
	{
		return totalQuestionAttempted;
	}

	public void setTotalQuestionAttempted(Integer totalQuestionAttempted)
	{
		this.totalQuestionAttempted = totalQuestionAttempted;
	}

}
