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
@Table(name = "t_userActivity")
public class UserActivity implements Serializable
{

	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 763281120420213273L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_userId")
	private Long userId;

	@Column(name = "f_subjectId")
	private Long subjectId;

	@Column(name = "f_chapterId")
	private Long chapterId;

	@Column(name = "f_topicId")
	private Long topicId;

	@Column(name = "f_boardId")
	private Long boardId;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_insertDate")
	private Date insertDate;

	@Column(name = "f_modifyDate")
	private Date modifyDate;
	
	@Transient
	private int smartScore;
	
	@Transient
	private int avgSmartScore;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_topicId", insertable = false, nullable = false, updatable = false)
	private Topic topic;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", insertable = false, nullable = false, updatable = false)
	private Subject subject;

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
	
	// setters and getters
	
	

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

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Long getChapterId()
	{
		return chapterId;
	}

	public void setChapterId(Long chapterId)
	{
		this.chapterId = chapterId;
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public Date getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
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

	public int getSmartScore()
	{
		return smartScore;
	}

	public void setSmartScore(int smartScore)
	{
		this.smartScore = smartScore;
	}

	public int getAvgSmartScore()
	{
		return avgSmartScore;
	}

	public void setAvgSmartScore(int avgSmartScore)
	{
		this.avgSmartScore = avgSmartScore;
	}

	
	
}
