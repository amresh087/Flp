package com.flp.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.flp.model.ModuleType;
import com.flp.model.QuestionType;

public class GlobalVO
{
	
	
	private Long hintTypeTextSelector;
	private MultipartFile hintTypeImg;
	private String hintTypeText;
	private Long explanationTypeSelector;
	private MultipartFile explanationTypeImg;
	private String explanationTypeText;
	public Long getHintTypeTextSelector()
	{
		return hintTypeTextSelector;
	}
	public MultipartFile getHintTypeImg()
	{
		return hintTypeImg;
	}
	public String getHintTypeText()
	{
		return hintTypeText;
	}
	public Long getExplanationTypeSelector()
	{
		return explanationTypeSelector;
	}
	public MultipartFile getExplanationTypeImg()
	{
		return explanationTypeImg;
	}
	public String getExplanationTypeText()
	{
		return explanationTypeText;
	}
	public void setHintTypeTextSelector(Long hintTypeTextSelector)
	{
		this.hintTypeTextSelector = hintTypeTextSelector;
	}
	public void setHintTypeImg(MultipartFile hintTypeImg)
	{
		this.hintTypeImg = hintTypeImg;
	}
	public void setHintTypeText(String hintTypeText)
	{
		this.hintTypeText = hintTypeText;
	}
	public void setExplanationTypeSelector(Long explanationTypeSelector)
	{
		this.explanationTypeSelector = explanationTypeSelector;
	}
	public void setExplanationTypeImg(MultipartFile explanationTypeImg)
	{
		this.explanationTypeImg = explanationTypeImg;
	}
	public void setExplanationTypeText(String explanationTypeText)
	{
		this.explanationTypeText = explanationTypeText;
	}
	
	
	
	
	private Long createdBy;
	public Long getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(Long createdBy)
	{
		this.createdBy = createdBy;
	}
	//remove Qouestion and Option Image
	private String questionImgCheck;
	private String option1ImgCheck;
	private String option2ImgCheck;
	private String option3ImgCheck;
	private String option4ImgCheck;
	
	
	private Long questionId;
	private Long optionId1;
	private Long optionId2;
	private Long optionId3;
	private Long optionId4;
	private Long questionTopicId;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private MultipartFile questionImage;
	private MultipartFile option1Image;
	private MultipartFile option2Image;
	private MultipartFile option3Image;
	private MultipartFile option4Image;
	private byte isCorrectOption1;
	private byte isCorrectOption2;
	private byte isCorrectOption3;
	private byte isCorrectOption4;
	private Long questionCognitiveLevelId;
	private Long questionDifficultyLevelId;
	private Long questionTypeId;
	private Long questionCategoryId;
	//For ModuleAssignement
	private Long secId;
	private Long subId;
	private Long grdId;
	
	private Long zoneId;
	
	public Long getZoneId()
	{
		return zoneId;
	}
	public void setZoneId(Long zoneId)
	{
		this.zoneId = zoneId;
	}
	public Long getSecId()
	{
		return secId;
	}
	public void setSecId(Long secId)
	{
		this.secId = secId;
	}
	public Long getSubId()
	{
		return subId;
	}
	public void setSubId(Long subId)
	{
		this.subId = subId;
	}
	public Long getGrdId()
	{
		return grdId;
	}
	public void setGrdId(Long grdId)
	{
		this.grdId = grdId;
	}
	// For modules
	private Long moduleId;
	private Long moduleTopicId;
	private String moduleName;
	private MultipartFile moduleFile;
	private String moduleDescription;
	private Long resourceTypeId;
	private Long gradeTypeId;
	private Long animationTypeId;
	private String moduleType;
	private String contentUrl;
	private int contentType;
	
	public int getContentType()
	{
		return contentType;
	}
	public void setContentType(int contentType)
	{
		this.contentType = contentType;
	}
	// Others
	private Long loginUserId;
	private Long topicId;
	private Long loId;
	public Long getLoId()
	{
		return loId;
	}
	public void setLoId(Long loId)
	{
		this.loId = loId;
	}
	private Long schoolId;
	private List<QuestionType> questionLevelList;
	private List<QuestionType> questionTypeList;
	private List<QuestionType> questionCognitiveLevelList;
	
	private List<QuestionType>  zoneList;
	
	public List<QuestionType> getZoneList()
	{
		return zoneList;
	}
	public void setZoneList(List<QuestionType> zoneList)
	{
		this.zoneList = zoneList;
	}
	private List<QuestionType> questionDifficultyLevelList;
	private List<ModuleType> resourceTypeList;
	private List<ModuleType> gradeTypeList;
	private List<ModuleType> animationTypeList;
	private Integer loginUserType;
	private boolean toRemove;
	private Long boardId;
	private Long questionLearnZoneId;
	
	
	public Long getQuestionLearnZoneId()
	{
		return questionLearnZoneId;
	}
	public void setQuestionLearnZoneId(Long questionLearnZoneId)
	{
		this.questionLearnZoneId = questionLearnZoneId;
	}
	private String questionResourceDirectory;
	private String moduleResourceDirectory;
	
	public Long getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}
	public Long getOptionId1()
	{
		return optionId1;
	}
	public void setOptionId1(Long optionId1)
	{
		this.optionId1 = optionId1;
	}
	public Long getOptionId2()
	{
		return optionId2;
	}
	public void setOptionId2(Long optionId2)
	{
		this.optionId2 = optionId2;
	}
	public Long getOptionId3()
	{
		return optionId3;
	}
	public void setOptionId3(Long optionId3)
	{
		this.optionId3 = optionId3;
	}
	public Long getOptionId4()
	{
		return optionId4;
	}
	public void setOptionId4(Long optionId4)
	{
		this.optionId4 = optionId4;
	}
	public Long getQuestionTopicId()
	{
		return questionTopicId;
	}
	public void setQuestionTopicId(Long questionTopicId)
	{
		this.questionTopicId = questionTopicId;
	}
	public String getQuestion()
	{
		return question;
	}
	public void setQuestion(String question)
	{
		this.question = question;
	}
	public String getOption1()
	{
		return option1;
	}
	public void setOption1(String option1)
	{
		this.option1 = option1;
	}
	public String getOption2()
	{
		return option2;
	}
	public void setOption2(String option2)
	{
		this.option2 = option2;
	}
	public String getOption3()
	{
		return option3;
	}
	public void setOption3(String option3)
	{
		this.option3 = option3;
	}
	public String getOption4()
	{
		return option4;
	}
	public void setOption4(String option4)
	{
		this.option4 = option4;
	}
	public MultipartFile getQuestionImage()
	{
		return questionImage;
	}
	public void setQuestionImage(MultipartFile questionImage)
	{
		this.questionImage = questionImage;
	}
	public MultipartFile getOption1Image()
	{
		return option1Image;
	}
	public void setOption1Image(MultipartFile option1Image)
	{
		this.option1Image = option1Image;
	}
	public MultipartFile getOption2Image()
	{
		return option2Image;
	}
	public void setOption2Image(MultipartFile option2Image)
	{
		this.option2Image = option2Image;
	}
	public MultipartFile getOption3Image()
	{
		return option3Image;
	}
	public void setOption3Image(MultipartFile option3Image)
	{
		this.option3Image = option3Image;
	}
	public MultipartFile getOption4Image()
	{
		return option4Image;
	}
	public void setOption4Image(MultipartFile option4Image)
	{
		this.option4Image = option4Image;
	}
	public byte getIsCorrectOption1()
	{
		return isCorrectOption1;
	}
	public void setIsCorrectOption1(byte isCorrectOption1)
	{
		this.isCorrectOption1 = isCorrectOption1;
	}
	public byte getIsCorrectOption2()
	{
		return isCorrectOption2;
	}
	public void setIsCorrectOption2(byte isCorrectOption2)
	{
		this.isCorrectOption2 = isCorrectOption2;
	}
	public byte getIsCorrectOption3()
	{
		return isCorrectOption3;
	}
	public void setIsCorrectOption3(byte isCorrectOption3)
	{
		this.isCorrectOption3 = isCorrectOption3;
	}
	public byte getIsCorrectOption4()
	{
		return isCorrectOption4;
	}
	public void setIsCorrectOption4(byte isCorrectOption4)
	{
		this.isCorrectOption4 = isCorrectOption4;
	}
	public Long getQuestionCognitiveLevelId()
	{
		return questionCognitiveLevelId;
	}
	public void setQuestionCognitiveLevelId(Long questionCognitiveLevelId)
	{
		this.questionCognitiveLevelId = questionCognitiveLevelId;
	}
	public Long getQuestionDifficultyLevelId()
	{
		return questionDifficultyLevelId;
	}
	public void setQuestionDifficultyLevelId(Long questionDifficultyLevelId)
	{
		this.questionDifficultyLevelId = questionDifficultyLevelId;
	}
	public Long getQuestionTypeId()
	{
		return questionTypeId;
	}
	public void setQuestionTypeId(Long questionTypeId)
	{
		this.questionTypeId = questionTypeId;
	}
	public Long getQuestionCategoryId()
	{
		return questionCategoryId;
	}
	public void setQuestionCategoryId(Long questionCategoryId)
	{
		this.questionCategoryId = questionCategoryId;
	}
	public Long getModuleId()
	{
		return moduleId;
	}
	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}
	public Long getModuleTopicId()
	{
		return moduleTopicId;
	}
	public void setModuleTopicId(Long moduleTopicId)
	{
		this.moduleTopicId = moduleTopicId;
	}
	public String getModuleName()
	{
		return moduleName;
	}
	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}
	public MultipartFile getModuleFile()
	{
		return moduleFile;
	}
	public void setModuleFile(MultipartFile moduleFile)
	{
		this.moduleFile = moduleFile;
	}
	public String getModuleDescription()
	{
		return moduleDescription;
	}
	public void setModuleDescription(String moduleDescription)
	{
		this.moduleDescription = moduleDescription;
	}
	public Long getResourceTypeId()
	{
		return resourceTypeId;
	}
	public void setResourceTypeId(Long resourceTypeId)
	{
		this.resourceTypeId = resourceTypeId;
	}
	public Long getGradeTypeId()
	{
		return gradeTypeId;
	}
	public void setGradeTypeId(Long gradeTypeId)
	{
		this.gradeTypeId = gradeTypeId;
	}
	public Long getAnimationTypeId()
	{
		return animationTypeId;
	}
	public void setAnimationTypeId(Long animationTypeId)
	{
		this.animationTypeId = animationTypeId;
	}
	public Long getLoginUserId()
	{
		return loginUserId;
	}
	public void setLoginUserId(Long loginUserId)
	{
		this.loginUserId = loginUserId;
	}
	public Long getTopicId()
	{
		return topicId;
	}
	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}
	public Long getSchoolId()
	{
		return schoolId;
	}
	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}
	public List<QuestionType> getQuestionLevelList()
	{
		return questionLevelList;
	}
	public void setQuestionLevelList(List<QuestionType> questionLevelList)
	{
		this.questionLevelList = questionLevelList;
	}
	public List<QuestionType> getQuestionTypeList()
	{
		return questionTypeList;
	}
	public void setQuestionTypeList(List<QuestionType> questionTypeList)
	{
		this.questionTypeList = questionTypeList;
	}
	public List<QuestionType> getQuestionCognitiveLevelList()
	{
		return questionCognitiveLevelList;
	}
	public void setQuestionCognitiveLevelList(List<QuestionType> questionCognitiveLevelList)
	{
		this.questionCognitiveLevelList = questionCognitiveLevelList;
	}
	public List<QuestionType> getQuestionDifficultyLevelList()
	{
		return questionDifficultyLevelList;
	}
	public void setQuestionDifficultyLevelList(List<QuestionType> questionDifficultyLevelList)
	{
		this.questionDifficultyLevelList = questionDifficultyLevelList;
	}
	public List<ModuleType> getResourceTypeList()
	{
		return resourceTypeList;
	}
	public void setResourceTypeList(List<ModuleType> resourceTypeList)
	{
		this.resourceTypeList = resourceTypeList;
	}
	public List<ModuleType> getGradeTypeList()
	{
		return gradeTypeList;
	}
	public void setGradeTypeList(List<ModuleType> gradeTypeList)
	{
		this.gradeTypeList = gradeTypeList;
	}
	public List<ModuleType> getAnimationTypeList()
	{
		return animationTypeList;
	}
	public void setAnimationTypeList(List<ModuleType> animationTypeList)
	{
		this.animationTypeList = animationTypeList;
	}
	public Integer getLoginUserType()
	{
		return loginUserType;
	}
	public void setLoginUserType(Integer loginUserType)
	{
		this.loginUserType = loginUserType;
	}
	public boolean isToRemove()
	{
		return toRemove;
	}
	public void setToRemove(boolean toRemove)
	{
		this.toRemove = toRemove;
	}
	public Long getBoardId()
	{
		return boardId;
	}
	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}
	public String getQuestionResourceDirectory()
	{
		return questionResourceDirectory;
	}
	public void setQuestionResourceDirectory(String questionResourceDirectory)
	{
		this.questionResourceDirectory = questionResourceDirectory;
	}
	public String getModuleResourceDirectory()
	{
		return moduleResourceDirectory;
	}
	public void setModuleResourceDirectory(String moduleResourceDirectory)
	{
		this.moduleResourceDirectory = moduleResourceDirectory;
	}
	public String getModuleType()
	{
		return moduleType;
	}
	public void setModuleType(String moduleType)
	{
		this.moduleType = moduleType;
	}
	public String getContentUrl()
	{
		return contentUrl;
	}
	public void setContentUrl(String contentUrl)
	{
		this.contentUrl = contentUrl;
	}
	public String isQuestionImgCheck()
	{
		return questionImgCheck;
	}
	public void setQuestionImgCheck(String questionImgCheck)
	{
		this.questionImgCheck = questionImgCheck;
	}
	public String isOption1ImgCheck()
	{
		return option1ImgCheck;
	}
	public void setOption1ImgCheck(String option1ImgCheck)
	{
		this.option1ImgCheck = option1ImgCheck;
	}
	public String isOption2ImgCheck()
	{
		return option2ImgCheck;
	}
	public void setOption2ImgCheck(String option2ImgCheck)
	{
		this.option2ImgCheck = option2ImgCheck;
	}
	public String isOption3ImgCheck()
	{
		return option3ImgCheck;
	}
	public void setOption3ImgCheck(String option3ImgCheck)
	{
		this.option3ImgCheck = option3ImgCheck;
	}
	public String isOption4ImgCheck()
	{
		return option4ImgCheck;
	}
	public void setOption4ImgCheck(String option4ImgCheck)
	{
		this.option4ImgCheck = option4ImgCheck;
	}
	
}
