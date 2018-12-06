package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.flp.util.Utility;

@Entity
@Table(name = "t_questionComments")
public class QuestionComments implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4107827745153753156L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_commentDesc", columnDefinition = "TEXT")
	private String commentDesc;

	@Column(name = "f_postedOn", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date postedOn;
	
	@Column(name = "f_updatedOn", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updatedOn;

	@Column(name = "f_status")
	private int status;

	@Column(name = "f_isRead")
	private boolean isRead;
	
	

	@Column(name = "f_questionId")
	private Long questionId;

	@Column(name = "f_userId")
	private Long userId;

	// uni-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "f_questionId", insertable = false, updatable = false)
	private Question question;

	// uni-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "f_userId", insertable = false, updatable = false)
	private User user;
	
	
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

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCommentDesc()
	{
		return commentDesc;
	}

	public void setCommentDesc(String commentDesc)
	{
		this.commentDesc = commentDesc;
	}

	public Date getPostedOn()
	{
		return postedOn;
	}

	public void setPostedOn(Date postedOn)
	{
		this.postedOn = postedOn;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Long getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Date getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn)
	{
		this.updatedOn = updatedOn;
	}

	
	public boolean isRead()
	{
		return isRead;
	}

	public void setRead(boolean isRead)
	{
		this.isRead = isRead;
	}
	
}
