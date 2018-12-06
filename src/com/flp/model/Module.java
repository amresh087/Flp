package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.flp.util.Utility;

@Entity
@Table(name = "t_module")
public class Module implements Serializable
{
	private static final long serialVersionUID = -2877798574999990242L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	public static final int CONTENT_TYPE_RESOURCE = 1;
	public static final int CONTENT_TYPE_WEB_RESOURCE = 2;
	public static final int CONTENT_TYPE_DOCUMENT = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private Long id;
	
	@Lob
	@Column(name = "f_name")
	private String name;

	@Lob
	@Column(name="f_description")
	private String description;
	
	@Column(name="f_content_url")
	private String contentUrl;
	
	@Column(name="f_thumbnail_url")
	private String thumbnailUrl;
	
	@Column(name="f_animation_type")
	private Long animationTypeId;
	
	@Column(name="f_grade_type")
	private Long gradeTypeId;
	
	@Column(name="f_resource_type")
	private Long resourceTypeId;
	
	@Column(name="f_schoolId")
	private Long schoolId;
	
	@Column(name="f_createdBy")
	private Long createdBy;
	
	
	public Long getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Long createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name="f_contentType")
	private int contentType=1;
	
	@Column(name="f_module_type")
	private String moduleType;
	
	// uni-directional many-to-one association
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_animation_type", insertable=false, updatable=false)
	private ModuleType animationType;
	
	// uni-directional many-to-one association
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_grade_type", insertable=false, updatable=false)
	private ModuleType gradeType;
		
	// uni-directional many-to-one association
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "f_resource_type", insertable=false, updatable=false)
	private ModuleType resourceType;
		
	@Column(name="f_status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
	@Transient
	private Integer count;
	
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

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public Long getAnimationTypeId()
	{
		return animationTypeId;
	}

	public void setAnimationTypeId(Long animationTypeId)
	{
		this.animationTypeId = animationTypeId;
	}

	public Long getGradeTypeId()
	{
		return gradeTypeId;
	}

	public void setGradeTypeId(Long gradeTypeId)
	{
		this.gradeTypeId = gradeTypeId;
	}

	public Long getResourceTypeId()
	{
		return resourceTypeId;
	}

	public void setResourceTypeId(Long resourceTypeId)
	{
		this.resourceTypeId = resourceTypeId;
	}

	public ModuleType getAnimationType()
	{
		return animationType;
	}

	public void setAnimationType(ModuleType animationType)
	{
		this.animationType = animationType;
	}

	public ModuleType getGradeType()
	{
		return gradeType;
	}

	public void setGradeType(ModuleType gradeType)
	{
		this.gradeType = gradeType;
	}

	public ModuleType getResourceType()
	{
		return resourceType;
	}

	public void setResourceType(ModuleType resourceType)
	{
		this.resourceType = resourceType;
	}

	public String getContentUrl()
	{
		return contentUrl;
	}

	public void setContentUrl(String contentUrl)
	{
		this.contentUrl = contentUrl;
	}

	public String getThumbnailUrl()
	{
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl)
	{
		this.thumbnailUrl = thumbnailUrl;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
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

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getContentType()
	{
		return contentType;
	}

	public void setContentType(int contentType)
	{
		this.contentType = contentType;
	}

	public String getModuleType()
	{
		return moduleType;
	}

	public void setModuleType(String moduleType)
	{
		this.moduleType = moduleType;
	}
	
	
}
