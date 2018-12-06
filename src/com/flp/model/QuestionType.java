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
@Table(name = "t_question_type")
public class QuestionType implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	public static final String QUESTION_DIFFICULTY = "DIFFICULTY";
	public static final String QUESTION_TYPE = "TYPE";
	public static final String QUESTION_COGNITIVE = "COGNITIVE";
	public static final String QUESTION_CATEGORY = "CATEGORY";
	public static final String QUESTION_ZONE = "LEARNING ZONE";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_name")
	private String name;

	@Column(name="f_category")
	private String category;

	@Column(name="f_status")
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
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