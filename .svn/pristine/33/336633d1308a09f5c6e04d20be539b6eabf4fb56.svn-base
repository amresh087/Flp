package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.flp.util.Utility;

@Entity
@Table(name = "t_session")
public class Session implements Serializable
{
	private static final long serialVersionUID = -920827778994275363L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private Long id;
	@Column(name = "f_sessionName")
	private String sessionName;

	@Column(name = "f_startDate")
	private Date startDate;
	@Column(name = "f_endDate")
	private Date endDate;
	@Column(name = "f_createdOn")
	private Date createdOn;
	@Column(name = "f_status")
	private Integer status;

	@Transient
	private String sessionStartDate;

	@Transient
	private String sessionEndDate;
	
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

	// setter & getters
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getSessionName()
	{
		return sessionName;
	}

	public void setSessionName(String sessionName)
	{
		this.sessionName = sessionName;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public String getSessionStartDate()
	{
		return sessionStartDate;
	}

	public void setSessionStartDate(String sessionStartDate)
	{
		this.sessionStartDate = sessionStartDate;
	}

	public String getSessionEndDate()
	{
		return sessionEndDate;
	}

	public void setSessionEndDate(String sessionEndDate)
	{
		this.sessionEndDate = sessionEndDate;
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
