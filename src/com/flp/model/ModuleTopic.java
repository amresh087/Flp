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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flp.util.Utility;

@Entity
@Table(name = "t_module_topic")
public class ModuleTopic implements Serializable
{
	private static final long serialVersionUID = -7406169479133586058L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private Long id;
	
	@Column(name="f_module_id")
	private Long moduleId;
	
	@Column(name="f_topic_id")
	private Long topicId;
	
	
	@Column(name = "f_refModuletopicId")
	private Long refModuletopicId; 

	
	
	public Long getRefModuletopicId()
	{
		return refModuletopicId;
	}

	public void setRefModuletopicId(Long refModuletopicId)
	{
		this.refModuletopicId = refModuletopicId;
	}

	// Uni-directional mapping
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="f_module_id", insertable=false, updatable=false)
	private Module module;
	
	// Uni-directional mapping
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="f_topic_id", insertable=false, updatable=false)
	private Topic topic;
	
	
	@Column(name = "f_learnobjective_id")
	private Long loId; 
	
	public Long getLoId()
	{
		return loId;
	}

	public LearnObjective getLearnObjective()
	{
		return learnObjective;
	}

	public void setLoId(Long loId)
	{
		this.loId = loId;
	}

	public void setLearnObjective(LearnObjective learnObjective)
	{
		this.learnObjective = learnObjective;
	}

	// uni-directional many-to-one association to Question
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_learnObjective_id", insertable=false, updatable=false)
	private LearnObjective learnObjective;
	
	
	@Column(name="f_status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
	
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

	public Long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public Module getModule()
	{
		return module;
	}

	public void setModule(Module module)
	{
		this.module = module;
	}

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
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
}
