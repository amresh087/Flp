package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flp.util.Utility;

@Entity
@Table(name="t_userLogDetails")
public class UserLogDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7674436756580049133L;

	@Id
	@GeneratedValue
	@Column(name = "f_id")
	private Long id;
	
	@Column(name="f_ipAddress")
	private String ipAddress;
	
	@Column(name="f_loginTime")
	private Date loginTime;
	
	@Column(name="f_sessionId")
	private String sessionId;
	
	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public Long getId() {
		return id;
	}


	@Column(name="f_logoutTime")
	private Date logoutTime;
	
	@Column(name="f_userId")
	private Long userId;

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
	//settres & getters
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
