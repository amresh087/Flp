package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
 

import com.flp.util.Utility;

@Entity
@Table(name = "t_section")
public class Section implements Serializable
{
	private static final long serialVersionUID = -7323687535186908872L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	// ###################################################################################//
	// ## User-wise : sessionSchoolGradeId will be null //
	// ## School-wise : boardId and gradeId will be null //
	// ## Extra fields : boardId //
	// ###################################################################################//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_sectionName")
	private String name;

	@Column(name = "f_sessionSchoolGradeId")
	private Long sessionSchoolGradeId;

	@Column(name = "f_boardId")
	private Long boardId;

	@Column(name = "f_gradeId")
	private Long gradeId;

	@Column(name = "f_status")
	private Integer status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_gradeId", insertable = false, updatable = false, nullable = false)
	private Grade grade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_sessionSchoolGradeId", insertable = false, updatable = false, nullable = false)
	private SessionSchoolGrade sessionSchoolGrade;

	@OneToMany(mappedBy = "section", fetch=FetchType.LAZY)
	@Filter(name = "status", condition = "f_status = :active")
	//@LazyCollection(LazyCollectionOption.FALSE)
	private Set<SubjectSection> subjectSectionList;
	// setters & getters method

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
	private Set<StudentTeacherSession> studentTeacherSession;
	
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
	

	public Set<StudentTeacherSession> getStudentTeacherSession()
	{
		return studentTeacherSession;
	}

	public void setStudentTeacherSession(Set<StudentTeacherSession> studentTeacherSession)
	{
		this.studentTeacherSession = studentTeacherSession;
	}

	public Set<SubjectSection> getSubjectSectionList()
	{
		return subjectSectionList;
	}

	public void setSubjectSectionList(Set<SubjectSection> subjectSectionList)
	{
		this.subjectSectionList = subjectSectionList;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
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

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public SessionSchoolGrade getSessionSchoolGrade()
	{
		return sessionSchoolGrade;
	}

	public void setSessionSchoolGrade(SessionSchoolGrade sessionSchoolGrade)
	{
		this.sessionSchoolGrade = sessionSchoolGrade;
	}

	public Long getSessionSchoolGradeId()
	{
		return sessionSchoolGradeId;
	}

	public void setSessionSchoolGradeId(Long sessionSchoolGradeId)
	{
		this.sessionSchoolGradeId = sessionSchoolGradeId;
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

	public Grade getGrade()
	{
		return grade;
	}

	public void setGrade(Grade grade)
	{
		this.grade = grade;
	}
	@Transient
	private Long subjectId;
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

}
