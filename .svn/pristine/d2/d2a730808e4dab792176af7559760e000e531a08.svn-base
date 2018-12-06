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
@Table(name = "t_assignedAssessment")
public class AssignedAssessment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3032282519891744260L;
	

	//status
	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false)
	private Long id;
	
	@Column(name = "f_assessmentId")
	private Long assessmentId;
	
	@Column(name = "f_sectionId")
	private Long sectionId;
	
	@Column(name = "f_userId")
	private Long userId;
	
	@Column(name = "f_assignedBy")
	private Long assignedBy;
	
	@Column(name = "f_assignedDate")
	private Date assignedDate;
	
	@Column(name = "f_status")
	private Integer status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_assessmentId", insertable = false, nullable = false, updatable = false)
	private Assessment assessment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_userId", insertable = false, nullable = false, updatable = false)
	private User user;
	
	@Transient
	private Double maxMarks;
	
	@Transient
	private Integer count;
	
	
	@Column(name="f_dateTimeZone")
   	private String dateTimeZone=Utility.convertDateToStringWithZone(new Date()); 
	
    public String getDateTimeZone()
	{
		return dateTimeZone;
	}

	public void setDateTimeZone(String dateTimeZone)
	{
		this.dateTimeZone = dateTimeZone;
	}
	
	public Double getMaxMarks()
	{
		return maxMarks;
	}
	public void setMaxMarks(Double maxMarks)
	{
		this.maxMarks = maxMarks;
	}
	public Integer getCount()
	{
		return count;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}
	//setters & getters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(Long assignedBy) {
		this.assignedBy = assignedBy;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Assessment getAssessment() {
		return assessment;
	}
	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
