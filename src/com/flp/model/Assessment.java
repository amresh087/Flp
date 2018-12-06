package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;

import com.flp.util.Utility;

@Entity
@Table(name = "t_assessment")
public class Assessment implements Serializable
{

	// type
	public static final int E_TEST = 1;
	public static final int ASSIGNED_TEST = 2;

	// status
	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;
	public static final Integer STATUS_PUBLISHED = 2;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7499546116850674844L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false)
	private Long id;

	@Column(name = "f_testName")
	private String testName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_startDate")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_endDate")
	private Date endDate;

	@Column(name = "f_duration")
	// test duration in minutes
	int duration;

	@Column(name = "f_createdOn")
	private Date createdOn;

	@Column(name = "f_schoolId")
	private Long schoolId;

	@Column(name = "f_createdBy")
	private Long createdBy;

	@Column(name = "f_sessionId")
	private Long sessionId;

	@Column(name = "f_gradeId")
	private Long gradeId;

	@Column(name = "f_subjectId")
	private Long subjectId;

	@Column(name = "f_type")
	private Integer type;

	@Column(name = "f_status")
	private Integer status;

	@Column(name = "f_instruction",columnDefinition="Text",length= 65535)
	private String instruction;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assessment")
	private Set<AssessmentQuestion> assessmentQuestion;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "assessment")
	private AssessmentTopicChapterDetails assessmentTopicChapterDetails;

	public AssessmentTopicChapterDetails getAssessmentTopicChapterDetails()
	{
		return assessmentTopicChapterDetails;
	}

	public void setAssessmentTopicChapterDetails(AssessmentTopicChapterDetails assessmentTopicChapterDetails)
	{
		this.assessmentTopicChapterDetails = assessmentTopicChapterDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_gradeId", nullable = false, insertable = false, updatable = false)
	private Grade grade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_createdBy", nullable = false, insertable = false, updatable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", nullable = false, insertable = false, updatable = false)
	private Subject subject;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assessment")
	private Set<AssignedAssessment> assignedAssessment;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assessment")
	@Filter(name = "studentTestFilter", condition = "f_student_id = :userId and f_session_id = :sessionId and f_status = :status")
	private Set<SchoolStudentTest> studentTest;

	@Transient
	private Float totalMarks;
	
	@Transient
	private String topicIds;
	
	@Transient
	private String chapterIds;
	
	@Transient
	private int chapCount;
	
	@Transient
	private int topicCount;
	
	
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
	

	public String getTopicIds()
	{
		return topicIds;
	}

	public void setTopicIds(String topicIds)
	{
		this.topicIds = topicIds;
	}

	public String getChapterIds()
	{
		return chapterIds;
	}

	public void setChapterIds(String chapterIds)
	{
		this.chapterIds = chapterIds;
	}

	public Float getTotalMarks()
	{
		return totalMarks;
	}

	public void setTotalMarks(Float totalMarks)
	{
		this.totalMarks = totalMarks;
	}

	@Transient
	private Integer totalQuestion;

	@Transient
	private float average;

	@Column(name="f_maxMarks")
	private float maxMarks;

	public float getMaxMarks()
	{
		return maxMarks;
	}

	public void setMaxMarks(float maxMarks)
	{
		this.maxMarks = maxMarks;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	@Transient
	private int count;

	@Transient
	private String dateDifference;

	@Transient
	private Integer flag;

	@Transient
	private List<AssessmentQuestion> assessmentQuestionList;

	public List<AssessmentQuestion> getAssessmentQuestionList()
	{
		return assessmentQuestionList;
	}

	public void setAssessmentQuestionList(List<AssessmentQuestion> assessmentQuestionList)
	{
		this.assessmentQuestionList = assessmentQuestionList;
	}

	public float getAverage()
	{
		return average;
	}

	public void setAverage(float average)
	{
		this.average = average;
	}

	public Set<AssignedAssessment> getAssignedAssessment()
	{
		return assignedAssessment;
	}

	public void setAssignedAssessment(Set<AssignedAssessment> assignedAssessment)
	{
		this.assignedAssessment = assignedAssessment;
	}

	// setters & getters
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTestName()
	{
		return testName;
	}

	public void setTestName(String testName)
	{
		this.testName = testName;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
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

	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Set<AssessmentQuestion> getAssessmentQuestion()
	{
		return assessmentQuestion;
	}

	public void setAssessmentQuestion(Set<AssessmentQuestion> assessmentQuestion)
	{
		this.assessmentQuestion = assessmentQuestion;
	}

	public Grade getGrade()
	{
		return grade;
	}

	public void setGrade(Grade grade)
	{
		this.grade = grade;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public static int geteTest()
	{
		return E_TEST;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	

	public Long getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Long createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getInstruction()
	{
		return instruction;
	}

	public void setInstruction(String instruction)
	{
		this.instruction = instruction;
	}

	public Integer getTotalQuestion()
	{
		return totalQuestion;
	}

	public void setTotalQuestion(Integer totalQuestion)
	{
		this.totalQuestion = totalQuestion;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public Set<SchoolStudentTest> getStudentTest()
	{
		return studentTest;
	}

	public void setStudentTest(Set<SchoolStudentTest> studentTest)
	{
		this.studentTest = studentTest;
	}

	public String getDateDifference()
	{
		return dateDifference;
	}

	public void setDateDifference(String dateDifference)
	{
		this.dateDifference = dateDifference;
	}

	public Integer getFlag()
	{
		return flag;
	}

	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public int getChapCount()
	{
		return chapCount;
	}

	public void setChapCount(int chapCount)
	{
		this.chapCount = chapCount;
	}

	public int getTopicCount()
	{
		return topicCount;
	}

	public void setTopicCount(int topicCount)
	{
		this.topicCount = topicCount;
	}

	
	
}
