package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flp.util.Utility;

@Entity
@Table(name = "t_retailuser_comment")
public class RetailUserComment implements Serializable
{
	private static final long serialVersionUID = 5840007813546779173L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="f_id")
	private Long id;
	
	@Lob
	@Column(name = "f_comment")
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)							// Bi-directional mapping
	@JoinColumn(name = "f_retailuser_query_id", insertable=false, updatable=false)
	private RetailUserQuery retailUserQuery;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_answered_by", insertable = false, updatable = false)
	private User answeredBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

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

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public RetailUserQuery getRetailUserQuery()
	{
		return retailUserQuery;
	}

	public void setRetailUserQuery(RetailUserQuery retailUserQuery)
	{
		this.retailUserQuery = retailUserQuery;
	}

	public User getAnsweredBy()
	{
		return answeredBy;
	}

	public void setAnsweredBy(User answeredBy)
	{
		this.answeredBy = answeredBy;
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
}