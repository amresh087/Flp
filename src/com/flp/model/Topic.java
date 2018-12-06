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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import com.flp.util.Utility;

@Entity
@Table(name = "t_topic")
@FilterDefs({ @FilterDef(name = "topicFilterOrder", parameters = { @ParamDef(name = "active", type = "java.lang.Integer"), @ParamDef(name = "order", type = "java.lang.Integer") }), @FilterDef(name = "topicFilterStatus", parameters = { @ParamDef(name = "active", type = "java.lang.Integer")

}) })
public class Topic implements Serializable
{
	private static final long serialVersionUID = -6261927378551356127L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_DISABLED = 2;

	// ###################################################################################//
	// ## User-wise : schoolId will be null //
	// ## School-wise : boardId will be null //
	// ## Extra fields : gradeId, boardId, subjectId, schoolId //
	// ###################################################################################//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Lob
	@Column(name = "f_name")
	private String name;

	@Column(name = "f_refTopicId")
	private Long refTopicId;
	
	
	
	public Long getRefTopicId()
	{
		return refTopicId;
	}

	public void setRefTopicId(Long refTopicId)
	{
		this.refTopicId = refTopicId;
	}

	@Column(name = "f_chapter_id")
	private Long chapterId;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_chapter_id", insertable = false, nullable = false, updatable = false)
	private Chapter chapter;

	@Column(name = "f_grade_id")
	private Long gradeId;

	@Column(name = "f_board_id")
	private Long boardId;

	@Lob
	@Column(name = "f_description")
	private String description;

	@Column(name = "f_subjectId")
	private Long subjectId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", insertable = false, nullable = false, updatable = false)
	private Subject subject;

	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_order")
	private Integer serialOrder;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
	private Set<QuestionTopic> questionTopic;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
	@Filters({ @Filter(name = "questionAnswerFilter", condition = "f_correct_incorrect = :cCount AND f_userId=:sUserId"), 
		@Filter(name = "questionAnswerFilterAllDetails", condition = "f_userId=:sUserId") })
	private Set<UserQuestionAnswer> userQuestionAnswer;

	// @OneToOne(fetch = FetchType.LAZY, mappedBy = "topic")
	// @Filter(name = "smartScoreFliter", condition = "f_userId=:sUserId")
	// private TopicSmartScore topicSmartScore;

	@Transient
	private Float lemdaValue;
	
	@Transient
	private String lxDate;


	public String getLxDate()
	{
		return lxDate;
	}

	public void setLxDate(String lxDate)
	{
		this.lxDate = lxDate;
	}
	
	@Transient
	private Integer lx;

	public Integer getLx()
	{
		return lx;
	}

	public void setLx(Integer lx)
	{
		this.lx = lx;
	}


	@Transient
	private Integer studentAssignCount;
	
	
	
	@Transient
	private int percent;
	@Transient
	private int markTopic;
	
	@Transient
	private int markTopicContentPermission;
	
	
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
	
	

	public int getMarkTopicContentPermission()
	{
		return markTopicContentPermission;
	}

	public void setMarkTopicContentPermission(int markTopicContentPermission)
	{
		this.markTopicContentPermission = markTopicContentPermission;
	}

	@Transient
	private int isAssigned;
	
	public int getMarkTopic()
	{
		return markTopic;
	}

	public void setMarkTopic(int markTopic)
	{
		this.markTopic = markTopic;
	}

	@Transient
	private int rightCount;
	
	@Transient
	private int wrongCount;
	
	@Transient
	private int skipCount;
	
	@Transient
	private int tModulecount;
	public int gettModulecount()
	{
		return tModulecount;
	}

	public void settModulecount(int tModulecount)
	{
		this.tModulecount = tModulecount;
	}

	@Transient
	private int count;

	@Transient
	private Boolean isfavorite;

	@Transient
	private int smartScore;

	@Transient
	private int avgSmartScore;

	// @Transient
	// private String name;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	/* public Byte[] getNameUTF8() { return nameUTF8; }
	 * 
	 * public void setNameUTF8(Byte[] nameUTF8) { this.nameUTF8 = nameUTF8; } */

	public Long getChapterId()
	{
		return chapterId;
	}

	public void setChapterId(Long chapterId)
	{
		this.chapterId = chapterId;
	}

	public Chapter getChapter()
	{
		return chapter;
	}

	public void setChapter(Chapter chapter)
	{
		this.chapter = chapter;
	}

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
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

	public Float getLemdaValue()
	{
		return lemdaValue;
	}

	public void setLemdaValue(Float lemdaValue)
	{
		this.lemdaValue = lemdaValue;
	}

	public Integer getStudentAssignCount()
	{
		return studentAssignCount;
	}

	public void setStudentAssignCount(Integer studentAssignCount)
	{
		this.studentAssignCount = studentAssignCount;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		// return Utility.convertByteUTF8ToString(getNameUTF8());
		return name;
	}

	public Integer getSerialOrder()
	{
		return serialOrder;
	}

	public void setSerialOrder(Integer serialOrder)
	{
		this.serialOrder = serialOrder;
	}

	public Set<QuestionTopic> getQuestionTopic()
	{
		return questionTopic;
	}

	public void setQuestionTopic(Set<QuestionTopic> questionTopic)
	{
		this.questionTopic = questionTopic;
	}

	public Set<UserQuestionAnswer> getUserQuestionAnswer()
	{
		return userQuestionAnswer;
	}

	public void setUserQuestionAnswer(Set<UserQuestionAnswer> userQuestionAnswer)
	{
		this.userQuestionAnswer = userQuestionAnswer;
	}

	public int getPercent()
	{
		return percent;
	}

	public void setPercent(int percent)
	{
		this.percent = percent;
	}

	/* public TopicSmartScore getTopicSmartScore() { return topicSmartScore; }
	 * 
	 * public void setTopicSmartScore(TopicSmartScore topicSmartScore) { this.topicSmartScore = topicSmartScore; } */

	public int getSmartScore()
	{
		return smartScore;
	}

	public void setSmartScore(int smartScore)
	{
		this.smartScore = smartScore;
	}

	public int getAvgSmartScore()
	{
		return avgSmartScore;
	}

	public void setAvgSmartScore(int avgSmartScore)
	{
		this.avgSmartScore = avgSmartScore;
	}

	public Boolean getIsfavorite()
	{
		return isfavorite;
	}

	public void setIsfavorite(Boolean isfavorite)
	{
		this.isfavorite = isfavorite;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	
	
	
	public int getRightCount()
	{
		return rightCount;
	}

	public void setRightCount(int rightCount)
	{
		this.rightCount = rightCount;
	}

	public int getWrongCount()
	{
		return wrongCount;
	}

	public void setWrongCount(int wrongCount)
	{
		this.wrongCount = wrongCount;
	}
	
	

	public int getSkipCount()
	{
		return skipCount;
	}

	public void setSkipCount(int skipCount)
	{
		this.skipCount = skipCount;
	}
	
	
	
	

	public int getIsAssigned()
	{
		return isAssigned;
	}

	public void setIsAssigned(int isAssigned)
	{
		this.isAssigned = isAssigned;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Topic)
		{
			Topic t = (Topic) obj;
			return this.id == t.getId() ? true : false;
		}
		else
			return false;

	}

	@Override
	public int hashCode()
	{
		return this.id.hashCode();
	}
}