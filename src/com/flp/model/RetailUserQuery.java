package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flp.util.Utility;

@Entity
@Table(name = "t_retailuser_query")
public class RetailUserQuery implements Serializable
{
	private static final long serialVersionUID = -5924300726460111117L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="f_id")
	private Long id;
	
	@Lob
	@Column(name = "f_query")
	private String query;
	
	@Lob
	@Column(name = "f_description")
	private String description;
	
	@OneToMany(mappedBy = "retailUserQuery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RetailUserComment> retailUserComments;
	
	@OneToOne(fetch = FetchType.LAZY)				// Uni-directional
	@JoinColumn(name = "f_asked_by", insertable = false, updatable = false)
	private User askedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

	@OneToOne(fetch = FetchType.LAZY)				// Uni-directional
	@JoinColumn(name = "f_grade_id", insertable = false, updatable = false)
	private Grade grade;
	
	@Column(name = "f_status")
	private Integer status;
	
	
	
	
	
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

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<RetailUserComment> getRetailUserComments()
	{
		return retailUserComments;
	}

	public void setRetailUserComments(List<RetailUserComment> retailUserComments)
	{
		this.retailUserComments = retailUserComments;
	}

	public User getAskedBy()
	{
		return askedBy;
	}

	public void setAskedBy(User askedBy)
	{
		this.askedBy = askedBy;
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

	public Grade getGrade()
	{
		return grade;
	}

	public void setGrade(Grade grade)
	{
		this.grade = grade;
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