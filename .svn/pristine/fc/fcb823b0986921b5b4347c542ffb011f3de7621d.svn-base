package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.flp.util.Utility;

/** The persistent class for the school database table. */
@Entity
@Table(name = "t_school")
public class School implements Serializable
{
	private static final long serialVersionUID = 4459548145576099865L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_address")
	private String address;

	@Column(name = "f_city")
	private String city;

	@Column(name = "f_country")
	private String country;

	@Column(name = "f_state")
	private String state;

	@Column(name = "f_zipCode")
	private String zipCode;

	@Column(name = "f_currentSessionId")
	private Long currentSessionId;

	@Column(name = "f_adminName")
	private String adminName;

	@Column(name = "f_adminPhoneNo")
	private String adminPhoneNo;

	@Column(name = "f_adminEmail")
	private String adminEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate")
	private Date modifiedDate;

	@Column(name = "f_updatedBy")
	private Long updatedBy;

	@Column(name = "f_schoolName")
	private String schoolName;

	@Column(name = "f_contactNo")
	private String contactNo;

	@Column(name = "f_contactEmailId")
	private String contactEmail;

	@Column(name = "f_principalEmail")
	private String principalEmail;

	@Column(name = "f_principalId")
	private Long principalId;

	@Column(name = "f_principalPhoneNo")
	private String principalPhoneNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertedDate")
	private Date insertedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_expiryDate")
	private Date expiryDate;

	@Column(name = "f_shortName")
	private String shortName;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_boardId")
	private Long boardId;

	@Transient
	private String expDate;

	@Transient
	private Long sessionId;
	
	
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

	// getters and setters

	public Long getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Long sessionId)
	{
		this.sessionId = sessionId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public Long getCurrentSessionId()
	{
		return currentSessionId;
	}

	public void setCurrentSessionId(Long currentSessionId)
	{
		this.currentSessionId = currentSessionId;
	}

	public String getAdminName()
	{
		return adminName;
	}

	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}

	public String getAdminPhoneNo()
	{
		return adminPhoneNo;
	}

	public void setAdminPhoneNo(String adminPhoneNo)
	{
		this.adminPhoneNo = adminPhoneNo;
	}

	public String getAdminEmail()
	{
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail)
	{
		this.adminEmail = adminEmail;
	}

	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public Long getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	public String getContactNo()
	{
		return contactNo;
	}

	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}

	public String getContactEmail()
	{
		return contactEmail;
	}

	public void setContactEmail(String contactEmail)
	{
		this.contactEmail = contactEmail;
	}

	public String getPrincipalEmail()
	{
		return principalEmail;
	}

	public void setPrincipalEmail(String principalEmail)
	{
		this.principalEmail = principalEmail;
	}

	public Long getPrincipalId()
	{
		return principalId;
	}

	public void setPrincipalId(Long principalId)
	{
		this.principalId = principalId;
	}

	public String getPrincipalPhoneNo()
	{
		return principalPhoneNo;
	}

	public void setPrincipalPhoneNo(String principalPhoneNo)
	{
		this.principalPhoneNo = principalPhoneNo;
	}

	public Date getInsertedDate()
	{
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate)
	{
		this.insertedDate = insertedDate;
	}

	public Date getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public static int getStatusActive()
	{
		return STATUS_ACTIVE;
	}

	public static int getStatusInactive()
	{
		return STATUS_INACTIVE;
	}

	public String getExpDate()
	{
		return expDate;
	}

	public void setExpDate(String expDate)
	{
		this.expDate = expDate;
	}
}