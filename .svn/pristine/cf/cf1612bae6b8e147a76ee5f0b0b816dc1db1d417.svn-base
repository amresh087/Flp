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
 

import com.flp.util.Utility;

@Entity
@Table(name = "t_assessment_question")
public class AssessmentQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9066094262172412092L;
	
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false)
	private Long id;

	@Column(name = "f_assessmentId")
	private Long assessmentId;

	@Column(name = "f_questionId")
	private Long questionId;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_marks")
	private Float marks;

	@Column(name="f_serial")
	private int serial;
	
	public Float getMarks() {
		return marks;
	}

	public void setMarks(Float marks) {
		this.marks = marks;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	@Column(name = "f_createdOn")
	private Date createdOn;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_assessmentId", insertable = false, nullable = false, updatable = false)
	private Assessment assessment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_questionId", insertable = false, nullable = false, updatable = false)
	private Question question;
	
	
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

	// setters & getters

	public Long getId() {
		return id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
