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
@Table(name = "t_ContentSyncing")
public class ContentSyncing implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;
	
	@Column(name = "f_boardId")
	private Long boardId;
	
	@Column(name = "f_gradeId")
	private Long gradeId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_gradeUpdate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date gradeUpdate;
	
	@Column(name = "f_sectionId")
	private Long sectionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_sectionUpdate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date sectionUpdate;
	
	@Column(name = "f_subjectId")
	private Long subjectId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_subjectUpdate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date subjectUpdate;
	
	@Column(name = "f_topicId")
	private Long topicId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_topicUpdate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date topicUpdate;
	
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

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public Date getGradeUpdate()
	{
		return gradeUpdate;
	}

	public void setGradeUpdate(Date gradeUpdate)
	{
		this.gradeUpdate = gradeUpdate;
	}

	public Long getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(Long sectionId)
	{
		this.sectionId = sectionId;
	}

	public Date getSectionUpdate()
	{
		return sectionUpdate;
	}

	public void setSectionUpdate(Date sectionUpdate)
	{
		this.sectionUpdate = sectionUpdate;
	}

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Date getSubjectUpdate()
	{
		return subjectUpdate;
	}

	public void setSubjectUpdate(Date subjectUpdate)
	{
		this.subjectUpdate = subjectUpdate;
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public Date getTopicUpdate()
	{
		return topicUpdate;
	}

	public void setTopicUpdate(Date topicUpdate)
	{
		this.topicUpdate = topicUpdate;
	}	
	
	
	
	
}
