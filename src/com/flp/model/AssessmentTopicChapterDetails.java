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
@Table(name = "t_AssessmentTopicChapterDetails")
public class AssessmentTopicChapterDetails implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1075168245537987116L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false)
	private Long id;
	
	@Column(name = "f_topicId")
	private String topicId;
	
	@Column(name = "f_chapterId")
	private String chapterId;
	
	@Column(name = "f_assessmentId")
	private Long assessmentId;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_assessmentId", nullable = false, insertable = false, updatable = false)
	private Assessment assessment;
	
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

	public Assessment getAssessment()
	{
		return assessment;
	}

	public void setAssessment(Assessment assessment)
	{
		this.assessment = assessment;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTopicId()
	{
		return topicId;
	}

	public void setTopicId(String topicId)
	{
		this.topicId = topicId;
	}

	public String getChapterId()
	{
		return chapterId;
	}

	public void setChapterId(String chapterId)
	{
		this.chapterId = chapterId;
	}

	public Long getAssessmentId()
	{
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId)
	{
		this.assessmentId = assessmentId;
	}

		
	
}
