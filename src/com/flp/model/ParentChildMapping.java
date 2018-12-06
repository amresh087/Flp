package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flp.util.Utility;

@Entity
@Table(name = "t_parentChildMapping")
public class ParentChildMapping implements Serializable
{
	

	private static final long serialVersionUID = -827804759564912899L;
	public static final int STATUS_ACTIVE 		= 1;
	public static final int STATUS_INACTIVE 	= 0;
	public static final int STATUS_PENDING 		= 2;

	@Id
	@GeneratedValue
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_userId")
	private Long userId;

	@Column(name = "f_parentId")
	private Long parentId;

	@Column(name = "f_joinDate")
	private Date joinDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Column(name="f_status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate")
	private Date modifiedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_parentId",insertable=false,updatable=false, nullable=false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_userId",insertable=false,updatable=false, nullable=false)
	private User user1;
	
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
	
	
	public User getUser1()
	{
		return user1;
	}

	public void setUser1(User user1)
	{
		this.user1 = user1;
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

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public Date getJoinDate()
	{
		return joinDate;
	}

	public void setJoinDate(Date joinDate)
	{
		this.joinDate = joinDate;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
	
}
