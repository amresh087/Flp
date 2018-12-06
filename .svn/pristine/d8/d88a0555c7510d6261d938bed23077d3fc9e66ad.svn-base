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
@Table(name = "t_board")
//@Table(name = "test_board")
public class Board implements Serializable
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
    @Column(name="f_modifiedDateNew")
	private String modifiedDateNew =null;
    
    
    /* 4
down vote
accepted
	

I don't think you need any documentation, the java docs are self explaining. If I understand you correctly you need a way to set a default value for a field. If yes please see the following code snippet.

@Entity
@Table(name = "my_entity")
public class SomeEntity extends BaseEntity {

public static final class MyValueGenerator implements
        ValueGenerator<String> {
    @Override
    public String generateValue(Session session, Object owner) {
        return "This is my default name";
    }
}

@Basic
@Column(name = "name", insertable = true, updatable = true, nullable = false, length = 255)
// This will add a DDL default
@ColumnDefault("'This is my default name'")
// This will add a runtime default.
@GeneratorType(type = MyValueGenerator.class)
private String name;

// getters and setters

}

*/
    
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

   /* 
    @Column(name="f_dateTimeZone",nullable=false)
   	private String dateTimeZone = Utility.convertDateToStringWithZone(new Date()); 
	
    public String getDateTimeZone()
	{
		return dateTimeZone;
	}

	public void setDateTimeZone(String dateTimeZone)
	{
		this.dateTimeZone = dateTimeZone;
	}*/

	public String getModifiedDateNew()
	{
		return modifiedDateNew;
	}

	public void setModifiedDateNew(String modifiedDateNew)
	{
		this.modifiedDateNew = modifiedDateNew;
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
}
