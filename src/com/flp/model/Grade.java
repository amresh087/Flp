package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.flp.util.Utility;

@Entity
@Table(name = "t_grade")
public class Grade implements Serializable
{
	private static final long serialVersionUID = 1L;
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

	@Column(name = "f_name")
	private String name;

	@Column(name = "f_level_id")
	private Long levelId;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_boardId")
	private Long boardId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
	 @OneToMany(mappedBy="grade", fetch=FetchType.LAZY)
	 private Set<Section> sectionlist;
	 
	 @Transient
	 private List<Subject> subjectList;
	 
	
	@Column(name="f_sectionIdTarget")
	 private Long sectionIdTarget;
	 
	 @Column(name="f_gradeIdTarget")
	 private Long gradeIdTarget;
	 
	 @Column(name="f_boardIdTarget")
	 private Long boardIdTarget;
	 
	 
	 @Column(name="f_sectionIdSource")
	 private Long sectionIdSource;
	 
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
	 
	 
	 public Long getSectionIdTarget()
		{
			return sectionIdTarget;
		}

		public void setSectionIdTarget(Long sectionIdTarget)
		{
			this.sectionIdTarget = sectionIdTarget;
		}

		public Long getSectionIdSource()
		{
			return sectionIdSource;
		}

		public void setSectionIdSource(Long sectionIdSource)
		{
			this.sectionIdSource = sectionIdSource;
		}

	

	public Long getBoardIdTarget()
	{
		return boardIdTarget;
	}

	public void setBoardIdTarget(Long boardIdTarget)
	{
		this.boardIdTarget = boardIdTarget;
	}

	public Long getGradeIdTarget()
	{
		return gradeIdTarget;
	}

	public void setGradeIdTarget(Long gradeIdTarget)
	{
		this.gradeIdTarget = gradeIdTarget;
	}

	
	 
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Set<Section> getSectionlist() {
		return sectionlist;
	}

	public void setSectionlist(Set<Section> sectionlist) {
		this.sectionlist = sectionlist;
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

	public Long getLevelId()
	{
		return levelId;
	}

	public void setLevelId(Long levelId)
	{
		this.levelId = levelId;
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

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
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