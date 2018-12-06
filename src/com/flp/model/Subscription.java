package com.flp.model;

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

import com.flp.util.Utility;

@Entity
@Table(name = "t_subscription")
public class Subscription
{
	public static final int PLAN_TYPE_ANNUAL = 1;
	public static final int PLAN_TYPE_HALF_YEARLY = 2;
	public static final int PLAN_TYPE_QUATERLY = 3;
	public static final int PLAN_TYPE_DEMO = 4;

	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	public static final int SUBJECT_ID_ALL = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_userId")
	private Long userId;

	@Column(name = "f_planType")
	private Integer planType;

	@Column(name = "f_subjectId")
	private Long subjectId;

	@Column(name = "f_gradeId")
	private Long gradeId;

	@Column(name = "f_boardId")
	private Long boardId;

	@Column(name = "f_status")
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_startDate")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_expiryDate")
	private Date expiryDate;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_boardId", insertable = false, nullable = false, updatable = false)
	private Board board;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_userId", insertable = false, nullable = false, updatable = false)
	private User user;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", insertable = false, nullable = false, updatable = false)
	private Subject subject;

	// uni-directional many-to-one association to Subject
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_gradeId", insertable = false, nullable = false, updatable = false)
	private Grade grade;
	
	
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

	// getters & setters

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Integer getPlanType()
	{
		return planType;
	}

	public void setPlanType(Integer planType)
	{
		this.planType = planType;
	}

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
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

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public Grade getGrade()
	{
		return grade;
	}

	public void setGrade(Grade grade)
	{
		this.grade = grade;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
