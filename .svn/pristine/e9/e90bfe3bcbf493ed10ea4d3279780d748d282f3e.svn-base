package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.flp.util.Utility;

@Entity
@Table(name = "t_user_test")
public class UserTest implements Serializable {

	private static final long serialVersionUID = 4911097204333113255L;

	public static final boolean STATUS_ATTEMPTED = true;
	public static final boolean STATUS_NOT_ATTEMPTED = false;
	
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_name")
	private String name;

	@Column(name = "f_summery")
	private String summery;

	@Column(name = "f_isMultiAttempt")
	private int isMultiAttempt;

	@Column(name = "f_board_id")
	private Long boardId;

	@Column(name = "f_class_id")
	private Long classId;

	@Column(name = "f_subject_id")
	private Long subjectId;

	@Column(name = "f_topicIds")
	private String topicIds;

	@Column(name = "f_total_question")
	private int totalQuestion;

	@Column(name = "f_total_unattempted")
	private int totalUnAttempted;

	@Column(name = "f_total_correct")
	private int totalCorrect;

	@Column(name = "f_total_incorrect")
	private int totalIncorrect;

	@Column(name = "f_time")
	private int time;

	/**
	 * status=0 or false: test is not attempted yet; status=1 or true: test has
	 * been attempted
	 */
	@Column(name = "f_status")
	private int status;

	@Column(name = "f_percentage")
	private float percentage;

	@Column(name = "f_student_id")
	private Long userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_student_id", nullable = false, insertable = false, updatable = false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_attempted_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modified_date")
	private Date modifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_start_date")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_end_date")
	private Date endDate;

	@Column(name = "f_isActive")
	private int isActive;
	
	

	@OneToMany(mappedBy = "userTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserTestDetail> userTestDetailList;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subject_id", insertable = false, nullable = false, updatable = false)
	private Subject subject;

	
	@Transient
	private Integer monthId;
	
	
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
	
	@Column(name = "f_dateTimeZoneEnd")
	private String dateTimeZoneEnd = Utility.convertDateToStringWithZone(new Date());

	public String getDateTimeZoneEnd()
	{
		return dateTimeZoneEnd;
	}

	public void setDateTimeZoneEnd(String dateTimeZoneEnd)
	{
		this.dateTimeZoneEnd = dateTimeZoneEnd;
	}
	
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummery() {
		return summery;
	}

	public void setSummery(String summery) {
		this.summery = summery;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getTopicIds() {
		return topicIds;
	}

	public void setTopicIds(String topicIds) {
		this.topicIds = topicIds;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public int getTotalUnAttempted() {
		return totalUnAttempted;
	}

	public void setTotalUnAttempted(int totalUnAttempted) {
		this.totalUnAttempted = totalUnAttempted;
	}

	public int getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public int getTotalIncorrect() {
		return totalIncorrect;
	}

	public void setTotalIncorrect(int totalIncorrect) {
		this.totalIncorrect = totalIncorrect;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getIsMultiAttempt() {
		return isMultiAttempt;
	}

	public void setIsMultiAttempt(int isMultiAttempt) {
		this.isMultiAttempt = isMultiAttempt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Set<UserTestDetail> getUserTestDetailList() {
		return userTestDetailList;
	}

	public void setUserTestDetailList(Set<UserTestDetail> userTestDetailList) {
		this.userTestDetailList = userTestDetailList;
	}

	public Integer getMonthId()
	{
		return monthId;
	}

	public void setMonthId(Integer monthId)
	{
		this.monthId = monthId;
	}

}
