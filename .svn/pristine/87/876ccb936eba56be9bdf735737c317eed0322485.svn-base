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
@Table(name = "t_favoriteTopics")
public class FavoriteTopics implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -596525877833706581L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_userId")
	private Long userId;

	@Column(name = "f_topicId")
	private Long topicId;

	@Column(name = "f_chapterId")
	private Long chapterId;

	@Column(name = "f_subjectId")
	private Long subjectId;

	@Column(name = "f_createdOn")
	private Date createdOn;

	@Column(name = "f_status")
	private Integer status;
	
	@Transient
	private int smartScore;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_topicId", insertable = false, nullable = false, updatable = false)
	private Topic topic;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", insertable = false, nullable = false, updatable = false)
	private Subject subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_chapterId", insertable = false, nullable = false, updatable = false)
	private Chapter chapter;
	
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

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public static int getStatusActive()
	{
		return STATUS_ACTIVE;
	}

	public static int getStatusInactive()
	{
		return STATUS_INACTIVE;
	}

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public Chapter getChapter()
	{
		return chapter;
	}

	public void setChapter(Chapter chapter)
	{
		this.chapter = chapter;
	}

	public int getSmartScore()
	{
		return smartScore;
	}

	public void setSmartScore(int smartScore)
	{
		this.smartScore = smartScore;
	}

}
