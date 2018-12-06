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
@Table(name = "t_teacherMarkTopicDetails")
public class TeacherMarkTopicDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 764978920621168446L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_userId")
	private Long userId;
	@Column(name = "f_subjectId")
	private Long subjectId;

	@Column(name = "f_topicId")
	private Long topicId;

	@Column(name = "f_gradeId")
	private Long gradeId;

	@Column(name = "f_sectionId")
	private Long sectionId;

	@Column(name = "f_chapterId")
	private Long chapterId;
	@Column(name = "f_createdOn", nullable = false, columnDefinition = "TIMESTAMP")
	private Date createdOn;
	@Column(name = "f_isCompleted")
	private Integer isCompleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_userId", insertable = false, updatable = false, nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_topicId", insertable = false, updatable = false, nullable = false)
	private Topic topic;
	
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

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public Long getChapterId()
	{
		return chapterId;
	}

	public void setChapterId(Long chapterId)
	{
		this.chapterId = chapterId;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Integer getIsCompleted()
	{
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted)
	{
		this.isCompleted = isCompleted;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public Long getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(Long sectionId)
	{
		this.sectionId = sectionId;
	}

	
	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	
}
