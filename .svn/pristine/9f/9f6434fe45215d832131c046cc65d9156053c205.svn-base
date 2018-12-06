package com.flp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.flp.util.Utility;

@Entity
@Table(name="t_school_student_test")
	@FilterDef(name="studentTestFilter", parameters={
			@ParamDef(name = "userId", type = "java.lang.Long"),
			@ParamDef(name = "sessionId", type = "java.lang.Long"),
			@ParamDef(name = "status", type = "java.lang.Integer")
})
public class SchoolStudentTest implements Serializable{

	private static final long serialVersionUID = 4911097204333113255L;
	
	public static final int STATUS_ACTIVE =    1;
	public static final int STATUS_INACTIVE =  0;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="f_id")
	Long id;
	
	@Column(name="f_assessment_id")
	Long assessmentId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_attempted_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date attemptDate = new Date();
	
	@Column(name="f_marks")
	float marks;
	
	@Column(name="f_school_id")
	Long schoolId;
	
	@Column(name="f_session_id")
	Long sessionId;
	
	@Column(name="f_subject_id")
	Long subjectId;
	
	@Column(name="f_student_id")
	Long studentId;
	
	@Column(name="f_total_question")
	int totalQuestion;
	
	@Column(name="f_total_unattempted")
	int totalUnAttempted;
	
	@Column(name="f_total_correct")
	int totalCorrect;
	
	@Column(name="f_total_incorrect")
	int totalIncorrect;
	
	@Column(name="f_status")
	int status;

	@Column(name="f_percentage")
	float percentage;
	
	@OneToMany(mappedBy="schoolStudentTest", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<SchoolStudentTestDetail> schoolStudentTestDetail;
	
	@Transient
	private List<SchoolStudentTestDetail> testDeatail;
	
	public List<SchoolStudentTestDetail> getTestDeatail() {
		return testDeatail;
	}


	public void setTestDeatail(List<SchoolStudentTestDetail> testDeatail) {
		this.testDeatail = testDeatail;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="f_assessment_id", nullable=false, insertable=false, updatable=false)
    private Assessment assessment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="f_student_id", nullable=false, insertable=false, updatable=false)
    private User user;
	
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
	
	
	

	public float getPercentage() {
		return percentage;
	}

	


	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Date getAttemptDate() {
		return attemptDate;
	}

	public void setAttemptDate(Date attemptDate) {
		this.attemptDate = attemptDate;
	}

	public float getMarks() {
		return marks;
	}

	public void setMarks(float marks) {
		this.marks = marks;
	}


	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}


	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

	public Set<SchoolStudentTestDetail> getSchoolStudentTestDetail() {
		return schoolStudentTestDetail;
	}

	public void setSchoolStudentTestDetail(
			Set<SchoolStudentTestDetail> schoolStudentTestDetail) {
		this.schoolStudentTestDetail = schoolStudentTestDetail;
	}
	
	
	

	public int getStatus()
	{
		return status;
	}


	public void setStatus(int status)
	{
		this.status = status;
	}


	public Assessment getAssessment()
	{
		return assessment;
	}

	public void setAssessment(Assessment assessment)
	{
		this.assessment = assessment;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			SchoolStudentTest sst = (SchoolStudentTest) object;
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
		hash = 7 * hash + this.assessmentId.hashCode();
		return hash;
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
