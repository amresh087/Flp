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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.flp.util.Utility;

@Entity
@Table(name = "t_question")
public class Question implements Serializable
{
	private static final long serialVersionUID = 3432598052058368011L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false)
	private Long id;

	@Lob
	@Column(name = "f_description")
	private String description;


	@Column(name = "f_image_path")
	private String imagePath;
	
	@Column(name = "f_hint_content")
	private String hintContent;
	
	@Column(name = "f_explanation_content")
	private String explanationContent;
	
	

	public String getExplanationContent()
	{
		return explanationContent;
	}

	public void setExplanationContent(String explanationContent)
	{
		this.explanationContent = explanationContent;
	}

	public String getHintContent()
	{
		return hintContent;
	}

	public void setHintContent(String hintContent)
	{
		this.hintContent = hintContent;
	}

	@Column(name = "f_cognitive_level")
	private Long cognitiveLevelId;

	@Column(name = "f_difficulty_level")
	private Long difficultyLevelId;

	@Column(name = "f_question_type")
	private Long questionTypeId;

	@Column(name = "f_question_category")
	private Long questionCategoryId;
	
	@Column(name="f_image_present")
	private Integer imagePresent;
	
	@Column(name="f_ans_solution", columnDefinition="TEXT")
	private String AnsSolution;


	@Lob
	@Column(name = "f_hint")
	private String hint;
	
	@Column(name="f_hint2")
	private String hint2;

	@Column(name="f_hint3")
	private String hint3;
	
	
	@Column(name = "f_hint_type_id")
	private Long hinttypeid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_hintType", insertable = false, updatable = false)
	private HintType hintType;
	
	 
	
	

	public Long getHinttypeid()
	{
		return hinttypeid;
	}

	public void setHinttypeid(Long hinttypeid)
	{
		this.hinttypeid = hinttypeid;
	}

	public HintType getHintType()
	{
		return hintType;
	}

	public void setHintType(HintType hintType)
	{
		this.hintType = hintType;
	}

	@Column(name = "f_explanation_id")
	private Long explanationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_explanationType", insertable = false, updatable = false)
	private ExplanationType explanationType;
	
	public Long getExplanationId()
	{
		return explanationId;
	}

	public void setExplanationId(Long explanationId)
	{
		this.explanationId = explanationId;
	}

	public ExplanationType getExplanationType()
	{
		return explanationType;
	}

	public void setExplanationType(ExplanationType explanationType)
	{
		this.explanationType = explanationType;
	}

	// uni-directional many-to-one association to QuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_cognitive_level", insertable = false, updatable = false)
	private QuestionType cognitiveLevel;

	// uni-directional many-to-one association to QuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_difficulty_level", insertable = false, updatable = false)
	private QuestionType difficultyLevel;

	// uni-directional many-to-one association to QuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_question_type", insertable = false, updatable = false)
	private QuestionType questionType;

	// uni-directional many-to-one association to QuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_question_category", insertable = false, updatable = false)
	private QuestionType questionCategory;
	

	@Column(name = "f_created_by")
	private Long createdBy;
   
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_created_by", insertable = false, updatable = false, nullable = false)
	private User user;
	
	@Transient
	private Long topicId;
	
	@Transient
	private Long locountQ;
	
	
	public Long getLocountQ()
	{
		return locountQ;
	}

	public void setLocountQ(Long locountQ)
	{
		this.locountQ = locountQ;
	}

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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(name = "f_status")
	private Integer status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Answer> answers;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QuestionComments> questionComment;

	@Column(name = "f_school_id")
	private Long schoolId;

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public Long getCognitiveLevelId()
	{
		return cognitiveLevelId;
	}

	public void setCognitiveLevelId(Long cognitiveLevelId)
	{
		this.cognitiveLevelId = cognitiveLevelId;
	}

	public Long getDifficultyLevelId()
	{
		return difficultyLevelId;
	}

	public void setDifficultyLevelId(Long difficultyLevelId)
	{
		this.difficultyLevelId = difficultyLevelId;
	}

	public Long getQuestionTypeId()
	{
		return questionTypeId;
	}

	public void setQuestionTypeId(Long questionTypeId)
	{
		this.questionTypeId = questionTypeId;
	}

	public QuestionType getCognitiveLevel()
	{
		return cognitiveLevel;
	}

	public void setCognitiveLevel(QuestionType cognitiveLevel)
	{
		this.cognitiveLevel = cognitiveLevel;
	}

	public QuestionType getDifficultyLevel()
	{
		return difficultyLevel;
	}

	public void setDifficultyLevel(QuestionType difficultyLevel)
	{
		this.difficultyLevel = difficultyLevel;
	}

	public Long getQuestionCategoryId()
	{
		return questionCategoryId;
	}

	public void setQuestionCategoryId(Long questionCategoryId)
	{
		this.questionCategoryId = questionCategoryId;
	}

	public QuestionType getQuestionCategory()
	{
		return questionCategory;
	}

	public void setQuestionCategory(QuestionType questionCategory)
	{
		this.questionCategory = questionCategory;
	}

	public QuestionType getQuestionType()
	{
		return questionType;
	}

	public void setQuestionType(QuestionType questionType)
	{
		this.questionType = questionType;
	}

	public Long getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Long createdBy)
	{
		this.createdBy = createdBy;
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

	
	public List<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<Answer> answers)
	{
		this.answers = answers;
	}

	public String getDescription()
	{
		return description;
	}

	public String getHint()
	{
		return hint;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setHint(String hint)
	{
		this.hint = hint;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Integer getImagePresent()
	{
		return imagePresent;
	}

	public void setImagePresent(Integer isImageP)
	{
		this.imagePresent = isImageP;
	}

	public Set<QuestionComments> getQuestionComment()
	{
		return questionComment;
	}

	public void setQuestionComment(Set<QuestionComments> questionComment)
	{
		this.questionComment = questionComment;
	}
	
	public String getAnsSolution()
	{
		return AnsSolution;
	}

	public void setAnsSolution(String ansSolution)
	{
		AnsSolution = ansSolution;
	}

	public String getHint2()
	{
		return hint2;
	}

	public void setHint2(String hint2)
	{
		this.hint2 = hint2;
	}

	public String getHint3()
	{
		return hint3;
	}

	public void setHint3(String hint3)
	{
		this.hint3 = hint3;
	}
	
	
	
}