package com.flp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

 
import com.flp.util.Utility;

@Entity
@Table(name="t_topic_smart_score")
@FilterDef(name = "smartScoreFliter", parameters = {
		@ParamDef(name = "sUserId", type = "java.lang.Long")
		 })
public class TopicSmartScore {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="f_id")
	private Long id;
	
	// uni-directional many-to-one association to Subject
	@Column(name="f_topicId")
	private Long topicId ;
	
	@Column(name="f_smartScore")
	private Short smartScore;

	@Column(name="f_userId")
	private Long userId;
	
	@Column(name="f_gradeId")
	private Long gradeId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_topicId", insertable = false, nullable = false, updatable = false)
	private Topic topic;
	
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
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Short getSmartScore() {
		return smartScore;
	}

	public void setSmartScore(Short smartScore) {
		this.smartScore = smartScore;
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
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
