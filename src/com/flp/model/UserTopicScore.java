package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flp.util.Utility;

/*@Entity*/
@Table(name="t_userTopicScore")
public class UserTopicScore implements Serializable {
	
private static final long serialVersionUID = 1703755081474091751L;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id", nullable=false)
private Long f_id;

@Column(name="f_topicId", nullable=false)
private Long topicId;

@Column(name="f_subjectId")
private Long subjectId;

@Column(name="f_chapterId")
private Long chapterId;

@Column(name="f_sessionId")
private Long sessionId;

@Column(name="f_userId")
Long userId;

@Column(name="f_gScore", nullable=false)
private Integer gScore=0;

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


public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public Integer getgScore() {
	return gScore;
}

public void setgScore(Integer gScore) {
	this.gScore = gScore;
}

public Long getF_id() {
	return f_id;
}

public void setF_id(Long f_id) {
	this.f_id = f_id;
}

public Long getTopicId() {
	return topicId;
}

public void setTopicId(Long topicId) {
	this.topicId = topicId;
}

public Long getSubjectId() {
	return subjectId;
}

public void setSubjectId(Long subjectId) {
	this.subjectId = subjectId;
}

public Long getChapterId() {
	return chapterId;
}

public void setChapterId(Long chapterId) {
	this.chapterId = chapterId;
}

public Long getSessionId() {
	return sessionId;
}

public void setSessionId(Long sessionId) {
	this.sessionId = sessionId;
}


}
