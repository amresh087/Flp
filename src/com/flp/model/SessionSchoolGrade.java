package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flp.util.Utility;

@Entity
@Table(name = "t_sessionSchoolGrade")
public class SessionSchoolGrade implements Serializable
{
	private static final long serialVersionUID = 660426788179861958L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	//###################################################################################//
	//##  It will contain entries of Schools only						     			 //
	//###################################################################################//
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_gradeId")
	private Long gradeId;

	@Column(name = "f_sessionId")
	private Long sessionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_gradeId", nullable = false, insertable = false, updatable = false)
	private Grade grade;

	@OneToMany(mappedBy="sessionSchoolGrade", fetch=FetchType.LAZY)
	private Set<Section> sections;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
	
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public Long getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Long sessionId)
	{
		this.sessionId = sessionId;
	}

	public Grade getGrade()
	{
		return grade;
	}

	public void setGrade(Grade grade)
	{
		this.grade = grade;
	}

	public Set<Section> getSections()
	{
		return sections;
	}

	public void setSections(Set<Section> sections)
	{
		this.sections = sections;
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
}
