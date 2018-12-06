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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.flp.util.Utility;

@Entity
@Table(name = "t_assignTopicResources")
public class AssignTopicResources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3303102870763881856L;

	// status
	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;

	// type
	public static final Integer ASSIGN_TYPE_TOPIC = 1;
	public static final Integer ASSIGN_TYPE_RESOURCES = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false)
	private Long id;

	@Column(name = "f_topicId")
	private Long topicId;

	@Column(name = "f_resourceId")
	private Long resourceId;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_sectionId")
	private Long sectionId;

	@Column(name = "f_assignedOn")
	private Date assignedOn;

	@Column(name = "f_type")
	private Integer type;
	
	@Column(name="f_assignBy")
	private Long assignBy;
	
	@Column(name="f_gradeId")
	private Long gradeId;
	
	@Column(name="f_subjectId")
	private Long subjectId;
	
	@Column(name="f_schoolId")
	private Long schoolId;
	
	@Column(name="f_sessionId")
	private Long sessionId;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_sectionId", nullable=false, insertable=false, updatable=false)
	private Section section;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", nullable=false, insertable=false, updatable=false)
	private Subject subject;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_topicId", nullable=false, insertable=false, updatable=false)
	private Topic topic;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_resourceId", nullable=false, insertable=false, updatable=false)
	private Module module;
	
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
	

	// setters and getters

	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Date getAssignedOn() {
		return assignedOn;
	}

	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Long getAssignBy() {
		return assignBy;
	}

	public void setAssignBy(Long assignBy) {
		this.assignBy = assignBy;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
