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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.flp.util.Utility;
import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "t_user_question_answer")
@FilterDefs({
@FilterDef(name = "questionAnswerFilter", parameters = {
		@ParamDef(name = "cCount", type = "java.lang.Integer"),
		@ParamDef(name = "sUserId", type = "java.lang.Long")
		 }),
@FilterDef(name = "questionAnswerFilterAllDetails", parameters = {
					@ParamDef(name = "sUserId", type = "java.lang.Long")
					 })
})
public class UserQuestionAnswer implements Serializable
{
	private static final long serialVersionUID = 1013458709370254509L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	public static final int ATTEMPTED = 1;
	public static final int UNATTEMPTED = 0;
	public static final int CORRECT_ATTEMPT = 1;
	public static final int INCORRECT_ATTEMPT = 0;

	public static final int IS_CORRECT = 1;
	public static final int IS_INCORRECT = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_answer_ids")
	private String answerIds;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_is_attempted")
	private Integer isAttempted;

	@Column(name = "f_topicId")
	private Long topicId;

	@ColumnDefault("0")
	@Column(name = "f_correctAttemptsCount")
	private Integer correctAttemptsCount;
	@ColumnDefault("0")
	@Column(name = "f_incorrectAttemptsCount")
	private Integer incorrectAttemptsCount;

	@ColumnDefault("0")
	@Column(name = "f_notAttemptedCount")
	private Integer notAttemptedCount;

	@Description(key = "description", value = "time taken to attend question in seconds")
	@ColumnDefault("0")
	@Column(name = "f_timeTaken")
	private Integer timeTalen;

	@Column(name = "f_gradeId")
	private Long isGradeId;

	@Column(name = "f_correct_incorrect")
	private Integer correctIncorrect;
	
	@Column(name = "f_diff_value")
	private String diffValue;
	
	@Column(name = "f_zone_level")
	
	private String zoneLevel;
	public String getDiffValue()
	{
		return diffValue;
	}

	public void setDiffValue(String diffValue)
	{
		this.diffValue = diffValue;
	}

	public String getZoneLevel()
	{
		return zoneLevel;
	}

	public void setZoneLevel(String zoneLevel)
	{
		this.zoneLevel = zoneLevel;
	}

	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

	@Column(name = "f_userId")
	private Long userId;

	@Column(name = "f_questionId")
	Long questionId;
	
	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_sessionId")
	Long sessionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_topicId", insertable = false, nullable = false, updatable = false)
	private Topic topic;
	
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
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAnswerIds()
	{
		return answerIds;
	}

	public void setAnswerIds(String answerIds)
	{
		this.answerIds = answerIds;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getIsAttempted()
	{
		return isAttempted;
	}

	public void setIsAttempted(Integer isAttempted)
	{
		this.isAttempted = isAttempted;
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public Integer getCorrectAttemptsCount()
	{
		return correctAttemptsCount;
	}

	public void setCorrectAttemptsCount(Integer correctAttemptsCount)
	{
		this.correctAttemptsCount = correctAttemptsCount;
	}

	public Integer getIncorrectAttemptsCount()
	{
		return incorrectAttemptsCount;
	}

	public void setIncorrectAttemptsCount(Integer incorrectAttemptsCount)
	{
		this.incorrectAttemptsCount = incorrectAttemptsCount;
	}

	public Integer getNotAttemptedCount()
	{
		return notAttemptedCount;
	}

	public void setNotAttemptedCount(Integer notAttemptedCount)
	{
		this.notAttemptedCount = notAttemptedCount;
	}

	public Integer getTimeTalen()
	{
		return timeTalen;
	}

	public void setTimeTalen(Integer timeTalen)
	{
		this.timeTalen = timeTalen;
	}

	public Long getIsGradeId()
	{
		return isGradeId;
	}

	public void setIsGradeId(Long isGradeId)
	{
		this.isGradeId = isGradeId;
	}

	public Integer getCorrectIncorrect()
	{
		return correctIncorrect;
	}

	public void setCorrectIncorrect(Integer correctIncorrect)
	{
		this.correctIncorrect = correctIncorrect;
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

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public Long getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Long sessionId)
	{
		this.sessionId = sessionId;
	}

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}
	
	
}