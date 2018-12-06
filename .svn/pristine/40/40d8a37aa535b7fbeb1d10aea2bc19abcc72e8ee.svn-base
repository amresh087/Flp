package com.flp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.flp.util.Utility;

@Entity
@Table(name="t_school_student_test_detail")
public class SchoolStudentTestDetail implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 877833781737505155L;


@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="f_id", nullable=false)
Long id;


@Column(name="f_school_student_test_id", nullable=false)
Long schoolStudentTestId;

@Column(name="f_question_id")
Long questionId;

@Column(name="f_result")//attempte, correct, incorrect
int result;

@Column(name="f_answer_ids")
String answerIds;

@Column(name="f_marks")
float marks;

@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
@JoinColumn(name = "f_school_student_test_id", insertable=false, updatable=false, nullable=false)
private SchoolStudentTest schoolStudentTest;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "f_question_id", insertable = false, nullable = false, updatable = false)
private Question question;


@Transient
private float questmarks;

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


@Column(name="f_dateTimeZoneEnd")
	private String dateTimeZoneEnd=Utility.convertDateToStringWithZone(new Date()); 

public String getDateTimeZoneEnd()
{
	return dateTimeZoneEnd;
}

public void setDateTimeZoneEnd(String dateTimeZoneEnd)
{
	this.dateTimeZoneEnd = dateTimeZoneEnd;
}


public Question getQuestion()
{
	return question;
}

public void setQuestion(Question question)
{
	this.question = question;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getSchoolStudentTestId() {
	return schoolStudentTestId;
}

public void setSchoolStudentTestId(Long schoolStudentTestId) {
	this.schoolStudentTestId = schoolStudentTestId;
}

public Long getQuestionId() {
	return questionId;
}

public void setQuestionId(Long questionId) {
	this.questionId = questionId;
}

public int getResult() {
	return result;
}

public void setResult(int result) {
	this.result = result;
}

public String getAnswerIds() {
	return answerIds;
}

public void setAnswerIds(String answerIds) {
	this.answerIds = answerIds;
}

public float getMarks() {
	return marks;
}

public void setMarks(float marks) {
	this.marks = marks;
}

public SchoolStudentTest getSchoolStudentTest() {
	return schoolStudentTest;
}

public void setSchoolStudentTest(SchoolStudentTest schoolStudentTest) {
	this.schoolStudentTest = schoolStudentTest;
}
	




public float getQuestmarks()
{
	return questmarks;
}

public void setQuestmarks(float questmarks)
{
	this.questmarks = questmarks;
}

@Override
public boolean equals(Object object) {
	boolean result = false;
	if (object == null || object.getClass() != getClass()) {
		result = false;
	} else {
		SchoolStudentTestDetail sst = (SchoolStudentTestDetail) object;
		if (this.id == sst.getId()) {
			result = true;
		}
	}
	return result;
}

// just omitted null checks
@Override
public int hashCode() {
	int hash = 3;
	hash = 7 * hash + this.questionId.hashCode();
	return hash;
}


}
