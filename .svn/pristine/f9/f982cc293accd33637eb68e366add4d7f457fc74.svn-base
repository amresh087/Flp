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

import com.flp.util.Utility;


@Entity
@Table(name = "t_Zone_Create")
public class ZoneCreate implements Serializable

  {
	
	
	private static final long serialVersionUID = 5577955771458149868L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	public static final long BOARD_CTLS = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_description")
	private String description;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_name")
	private String name;
	
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public static int getStatusActive()
	{
		return STATUS_ACTIVE;
	}

	public static int getStatusInactive()
	{
		return STATUS_INACTIVE;
	}

	public static long getBoardCtls()
	{
		return BOARD_CTLS;
	}

	@Column(nullable = false, name = "insertDate", columnDefinition= "TIMESTAMP WITH TIME ZONE")
		@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate =null;
	
     @Column(nullable = false, name = "start_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate =null;
	
	

    }
