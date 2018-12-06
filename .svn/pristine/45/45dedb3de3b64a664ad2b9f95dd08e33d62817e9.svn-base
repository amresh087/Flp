package com.flp.dao;

import java.util.List;
import java.util.Map;

import com.flp.model.SchoolStudentTest;
import com.flp.model.SchoolStudentTestDetail;
import com.flp.model.Section;
import com.flp.model.Subject;
import com.flp.model.SubjectSection;
/**
 * 
 * @author Amresh
 *
 */
public interface SubjectSectionDao extends GenericDao<SubjectSection, Long>
{
	List<Long> addSubjectSectionMapping(Subject subject, List<Section> sectionList);
	List<Long> addSubjectSectionMapping(Long subjectId, String sectionIdString);
	void updateSubjectSectionMapping(String subjectSectionIdString, Integer status);
	List<SubjectSection> getSubjectSections(String sectionIdString, Integer status);
	long saveTest(SchoolStudentTest test);
	void saveTestDetail(SchoolStudentTestDetail s);
	public List<Section> getSectionByGradeId(Long schoolId, Long sessionId, Long gradeId);
	List<SubjectSection> getUniqueSubjectsByGradeId(Long gradeId,Long schoolId);
	List<Long> getAssignGradeAndSubjectToSme(Long gradeId, Long smeId, Long sessionId);
	boolean checkMappingAvailability(Long sectionId, Long subjectId, Integer status);
	Map<Long, String> getSubjectImgBySubjectIds(List<Long> newSubjectIds);
}
