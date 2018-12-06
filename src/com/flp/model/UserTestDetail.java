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

import com.flp.util.Utility;

@Entity
@Table(name="t_user_test_detail")
public class UserTestDetail implements Serializable{

	private static final long serialVersionUID = 1960127754493407645L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="f_id", nullable=false)
	Long id;
	
	@Column(name="f_question_id")
	Long questionId;

	@Column(name="f_result")//attempte, correct, incorrect
	int result;
	
	@Column(name="f_answer_ids")
	String answerIds;

	@Column(name="f_marks")
	float marks;
	
	@Column(name="f_user_test_id", nullable=false)
	Long userTestId;


	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "f_user_test_id", insertable=false, updatable=false, nullable=false)
	private UserTest userTest;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_question_id", insertable = false, nullable = false, updatable = false)
	private Question question;
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUserTestId() {
		return userTestId;
	}

	public void setUserTestId(Long userTestId) {
		this.userTestId = userTestId;
	}

	public UserTest getUserTest() {
		return userTest;
	}

	public void setUserTest(UserTest userTest) {
		this.userTest = userTest;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
 