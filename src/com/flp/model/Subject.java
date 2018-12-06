package com.flp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import com.flp.util.Utility;

@Entity
@Table(name = "t_subject")
public class Subject implements Serializable
{
	private static final long serialVersionUID = -1742285945750285109L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	//###################################################################################//
	//##  User-wise : schoolId will be null								     			 //
	//##  School-wise : boardId will be null								 			 //
	//###################################################################################//
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Lob
	@Column(name = "f_name")
	private String name;
	
	@Column(name = "f_refSubjectId")
	private Long refSubjectId;
	
	public Long getRefSubjectId()
	{
		return refSubjectId;
	}

	public void setRefSubjectId(Long refSubjectId)
	{
		this.refSubjectId = refSubjectId;
	}

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_image")
	private String subImage;
	
	

	
	public String getSubImage()
	{
		return subImage;
	}

	public void setSubImage(String subImage)
	{
		this.subImage = subImage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_boardId")
	private Long boardId;
	
	@OneToMany(mappedBy="subject", fetch=FetchType.LAZY)
	@Filters({
		@Filter(name = "chapterFilterOrder", condition = "f_status = :active AND f_order = :order AND f_grade_Id = :gradeId AND f_schoolId = :schoolId"),
		@Filter(name = "chapterFilterOrderRetail", condition = "f_status = :active AND f_order = :order AND f_grade_Id = :gradeId AND f_board_id = :boardId"),
		@Filter(name = "chapterFilterStatus", condition = "f_status = :active AND f_grade_Id = :gradeId AND f_schoolId = :schoolId")
	})	
	Set<Chapter> chapters;

	

	@Transient
	private Boolean isSubscribed;
	@Transient
	private Integer count;
	
	
	@OneToMany
	 @JoinTable(name = "subjectId", joinColumns = @JoinColumn(name = "f_id"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
	  private Collection<PriceList> price;
	
   
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
		
		//Utility.convertDateToStringWithZone(insertDate) ;
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

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public Boolean getIsSubscribed()
	{
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed)
	{
		this.isSubscribed = isSubscribed;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Integer getCount()
	{
		return count;
	}

	public void setCount(Integer count)
	{
		this.count = count;
	}
	
}