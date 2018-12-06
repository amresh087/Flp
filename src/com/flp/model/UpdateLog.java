package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flp.util.Utility;

@Entity
@Table(name="t_updateLog")
public class UpdateLog implements Serializable
{
	private static final long serialVersionUID = 2145531122583319873L;

	@Id
	@Column(name="f_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="f_userId")
	private Long userid;
		
	@Column(name="f_date")
	private Date date;
	
	@Column(name="f_EntityType")
	private EntityType entityType;
	
	@Column(name="f_entityId")
	private Long entityId;
	
	@Column(name="f_oldEntityName")
	private String oldEntityName;
	
    @Column(name="f_updatedName")
    private String updatedName;
    
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
    
	public String getUpdatedName()
	{
		return updatedName;
	}

	public void setUpdatedName(String updatedName)
	{
		this.updatedName = updatedName;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUserid()
	{
		return userid;
	}

	public void setUserid(Long userid)
	{
		this.userid = userid;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public EntityType getEntityType()
	{
		return entityType;
	}

	public void setEntityType(EntityType entityType)
	{
		this.entityType = entityType;
	}

	public Long getEntityId()
	{
		return entityId;
	}

	public void setEntityId(Long entityId)
	{
		this.entityId = entityId;
	}

	public String getOldEntityName()
	{
		return oldEntityName;
	}

	public void setOldEntityName(String oldEntityName)
	{
		this.oldEntityName = oldEntityName;
	}
		
}
