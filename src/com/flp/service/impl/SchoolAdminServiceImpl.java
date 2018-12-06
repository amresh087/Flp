package com.flp.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.SchoolAdminDao;
import com.flp.dao.SubjectSectionDao;
import com.flp.model.ExcelUser;
import com.flp.model.Grade;
import com.flp.model.ParentChildMapping;
import com.flp.model.Section;
import com.flp.model.SessionSchoolGrade;
import com.flp.model.StudentTeacherSession;
import com.flp.model.Subject;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.service.SchoolAdminService;
import com.flp.service.UserService;

@Service
@Transactional
public class SchoolAdminServiceImpl implements SchoolAdminService{
	@Autowired
	private SchoolAdminDao schoolAdminDao;
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectSectionDao subjectSectionDao;
	
	@Override
	@Transactional
	public List<Grade> getAllGradeBySchoolId(Long schoolId) {
		
		return schoolAdminDao.getAllGradeBySchoolId(schoolId);
	}

	@Override
	@Transactional
	public Long getCurrentSchoolSession(Long schoolId) {
		return schoolAdminDao.getCurrentSchoolSession(schoolId);
	}

	@Override
	@Transactional
	public User checkUser(String uEmail,String userName) {
		// TODO Auto-generated method stub
		return schoolAdminDao.checkUser(uEmail,userName);
	}

	@Override
	@Transactional
	public ParentChildMapping checkParentChildMapping(long pId, long uId) {

		return schoolAdminDao.checkParentChildMapping(pId,uId) ;
	}

	@Override
	@Transactional
	
	public String validateExcel(User user,UserForm userObj) {
		
		Date date=new Date();
		StringBuilder msg=new StringBuilder("Please fill following column :");
		int errCount=0;
		List<ExcelUser> xcelUserlist = new ArrayList<ExcelUser>();
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(user
					.getFileData().getBytes());
			
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(bis);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

		
			Long boradId=userObj.getSchoolId();
			userObj.getSchoolId();
			//System.out.println("boradId---------------------->>"+boradId);
			DataFormatter formate = new DataFormatter();
            List<User> listUser=new ArrayList<User>();
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//User user = null;
				ExcelUser excelUser=new ExcelUser();
				User student=new User();
				List<User> parentList =new ArrayList<User>();
				if (i > 0) {
					
					
						
						 
						 
						 String adm=formate.formatCellValue(row.getCell(0));
						 String fName=formate.formatCellValue(row.getCell(1));
						 String clss=formate.formatCellValue(row.getCell(2));
						 String sec=formate.formatCellValue(row.getCell(3));
						 String uName=formate.formatCellValue(row.getCell(4));
						 String pass=formate.formatCellValue(row.getCell(5));
						 String mobile=formate.formatCellValue(row.getCell(7));
						 String email=formate.formatCellValue(row.getCell(6));
						 String pName=formate.formatCellValue(row.getCell(8));
						
						 
						 String pEmail=formate.formatCellValue(row.getCell(9));
						 String pMobile=formate.formatCellValue(row.getCell(10));
						 String p2Name=formate.formatCellValue(row.getCell(11));
						 String p2Email=formate.formatCellValue(row.getCell(12));
						 String p2Mobile=formate.formatCellValue(row.getCell(13));
						 String p3Name=formate.formatCellValue(row.getCell(14));
						 String p3Email=formate.formatCellValue(row.getCell(15));
						 String p3Mobile=formate.formatCellValue(row.getCell(16));
						 
						  
						 
						 if( !notEmpty(adm) && !notEmpty(fName) && !notEmpty(clss) && !notEmpty(sec) && !notEmpty(uName) && !notEmpty(pass) && !notEmpty(mobile) 
								 &&!notEmpty(email) && !notEmpty(pName) && !notEmpty(pEmail) && !notEmpty(pMobile) && !notEmpty(p2Name) && !notEmpty(p2Email) && 
								 !notEmpty(p2Mobile) &&!notEmpty(p3Name) && !notEmpty(p3Email) && !notEmpty(p3Mobile)){
							 continue;
						 }
					
				
					
					
					
					int col;
					for( col=0;col<8;col++){
						 Cell cell=row.getCell(col);
						 String value=formate.formatCellValue(cell);
						
				//	Cell cell = row.getCell(col,Row.RETURN_BLANK_AS_NULL);
					
					if(value==null||value.toString()==""){
						System.out.println("Colunm no:"+col+1);
						msg.append("Row: "+(i+1)+" column: "+(col+1)+" ,");
						errCount++;
						if(errCount>4)
							return msg.toString();
					}else
					{
							switch (col) {
							case 0:
								
								 String adm1=formate.formatCellValue(row.getCell(0));
								
									student.setAdmissionNo(adm1!=null?adm1.toString():"");
						//aaaa		
									student.setStatus(student.STATUS_INACTIVE);
								 
								break;
							case 1:
								
								student.setFirstName(row.getCell(1)!=null?row.getCell(1).toString():"");
								break;
								
							case 2:
								student.setGradeName(row.getCell(2)!=null?row.getCell(2).toString():"");
								break;
							case 3:
								student.setSectionName(row.getCell(3)!=null?row.getCell(3).toString():"");
								break;
							case 4:
								student.setSchoolIdCode(row.getCell(4)!=null?row.getCell(4).toString():"");
								break;
							case 5:
								 String pass1=formate.formatCellValue(row.getCell(5));
								//cell=row.getCell(5);
								//int s=(int)cell.getNumericCellValue();
								//String pass1=String.valueOf(cell);
								student.setPassword(pass1);
								break;
							case 6:
								student.setEmailId(row.getCell(6)!=null?row.getCell(6).toString():"");
								break;
							case 7:
								cell=row.getCell(7);
								int s1=(int)cell.getNumericCellValue();
								String mob=String.valueOf(s1);
								/*cell.setCellType(cell.CELL_TYPE_STRING);
								cell=row.getCell(7);
								*/student.setMobileNo(mob!=null?mob.toString():"");
								break;
									default:
							break;
						}
					
					}
					
					}
					
					Cell cell8=row.getCell(8,Row.RETURN_BLANK_AS_NULL);
					Cell cell9=row.getCell(9,Row.RETURN_BLANK_AS_NULL);
					Cell cell10=row.getCell(10,Row.RETURN_BLANK_AS_NULL);
					/*String re=cell8.getStringCellValue();
					String re1=cell9.getStringCellValue();
					String re2=cell10.getStringCellValue();*/
					if(cell8!=null || cell9!=null || cell10!=null)
					{
						User parent=new User();
						
						if(cell8==null || cell8.toString()=="")
						{
							
							System.out.println("row number"+8);
							msg.append("Row: "+(i+1)+" column: "+(8+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
						}else{
							parent.setFirstName(row.getCell(8)!=null?row.getCell(8).toString():"");
							
						} if(cell9==null || cell9.toString()=="")
							{
							msg.append("Row: "+(i+1)+" column: "+(9+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							}
						else{
							parent.setUserName(row.getCell(9)!=null?row.getCell(9).toString():"");
							parent.setEmailId(row.getCell(9)!=null?row.getCell(9).toString():"");
						}
						if(cell10==null || cell10.toString()=="")
						{
							msg.append("Row: "+(i+1)+" column: "+(10+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
						}
						else
						{
							Cell cell=row.getCell(10);
							Double s=cell.getNumericCellValue();
							String mob=String.valueOf(Math.round(s));
							parent.setMobileNo(mob);
							//parent.setMobileNo(row.getCell(10)!=null?row.getCell(10).toString():"");
						
						}
						parent.setCreatedBy(userObj.getId());
						parent.setModifyDate(date);
						parent.setUserType(3);
						parentList.add(parent);
					}
				
					
					Cell cell11=row.getCell(11,Row.RETURN_NULL_AND_BLANK);
					Cell cell12=row.getCell(12,Row.RETURN_NULL_AND_BLANK);
					Cell cell13=row.getCell(13,Row.RETURN_NULL_AND_BLANK);
					
					if(cell11!=null||cell12!=null||cell13!=null)
					{
						System.out.println("hey");
					}
					else
					{
						System.out.println("bey");
					}
					
					if((cell11!=null && cell11.toString()!="") ||(cell12!=null && cell12.toString()!="")||(cell13!=null &&cell13.toString()!=""))
						{
						User parent=new User();
						if(cell11==null|| cell11.toString()=="")
						{
							msg.append("Row: "+(i+1)+" column: "+(11+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
							
						}else{
							parent.setFirstName(row.getCell(11)!=null?row.getCell(11).toString():"");
						}
						
						if(cell12==null || cell12.toString()=="")
							{
							msg.append("Row: "+(i+1)+" column: "+(12+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
						}
						else{
							parent.setUserName(row.getCell(12)!=null?row.getCell(12).toString():"");
							parent.setEmailId(row.getCell(12)!=null?row.getCell(12).toString():"");
							
						}
						if(cell13==null|| cell13.toString()=="")
						{
							msg.append("Row: "+(i+1)+" column: "+(13+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
						
					}
						else{
							Cell cell=row.getCell(13);
							Double s=cell.getNumericCellValue();
							String mob=String.valueOf(Math.round(s));
							parent.setMobileNo(mob);
							
							//parent.setMobileNo(row.getCell(13)!=null?row.getCell(13).toString():"");
						}
						parent.setCreatedBy(userObj.getId());
						parent.setModifyDate(date);
						parent.setUserType(3);
						parentList.add(parent);
					}
					
					Cell cell14=row.getCell(14,Row.RETURN_NULL_AND_BLANK);
					Cell cell15=row.getCell(15,Row.RETURN_NULL_AND_BLANK);
					Cell cell16=row.getCell(16,Row.RETURN_NULL_AND_BLANK);
					if((cell14!=null && cell14.toString()!="") ||(cell15!=null && cell15.toString()!="")||(cell16!=null &&cell16.toString()!=""))
					{
						User parent=new User();
						if(cell14==null|| cell14.toString()=="")
						{
							msg.append("Row: "+(i+1)+" column: "+(14+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
						}else
							{
							parent.setFirstName(row.getCell(14)!=null?row.getCell(14).toString():"");
							}
							if(cell15==null || cell15.toString()=="")							{
								msg.append("Row: "+(i+1)+" column: "+(15+1)+" ,");
								errCount++;
								if(errCount>4)
									return msg.toString();
							}
							else
							{
								parent.setEmailId(row.getCell(15)!=null?row.getCell(15).toString():"");
								parent.setUserName(row.getCell(15)!=null?row.getCell(15).toString():"");
							}
							
							
							
						if(cell16==null || cell16.toString()=="")
						{
							msg.append("Row: "+(i+1)+" column: "+(16+1)+" ,");
							errCount++;
							if(errCount>4)
								return msg.toString();
							
						}
						else{
							Cell cell=row.getCell(16);
							//int s=(int)cell.getNumericCellValue();
							Double s=cell.getNumericCellValue();
							String mob=String.valueOf(Math.round(s));
							//parent.setMobileNo(row.getCell(16)!=null?row.getCell(16).toString():"");
							parent.setCreatedBy(userObj.getId());
							//------------------------------------>>>>>
							userObj.getSessionId();
							
							
							parent.setUserType(3);
						}
						if(errCount>0)
						{
							return msg.toString();
						}
						parent.setCreatedBy(userObj.getId());
						parent.setModifyDate(new Date());
						parent.setUserType(User.USERTYPE_PARENT);
						parentList.add(parent);
					}
					
					
					
					student.setSchoolId(userObj.getSchoolId());
					student.setCreatedDate(date);
					student.setStatus(User.STATUS_ACTIVE);
					student.setCreatedBy(userObj.getId());
					student.setSessionId(userObj.getSessionId());
					student.setModifyDate(date);
					student.setUserType(User.USERTYPE_STUDENT);
					
					
					excelUser.setStudent(student);
			
					excelUser.setParents(parentList);
					xcelUserlist.add(excelUser);			
				}
				
					i++;
			}
			if(errCount>0)
			{
				return msg.toString();
			}
				
				
		//	file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	return saveExcelUsers(xcelUserlist);
		
	/*	return "Data Successfully Upload";*/
	}
	
	
	@Transactional
	@Override
	public String saveExcelUsers(List<ExcelUser>xcelUserlist)
	{
		String msg="Students Successfully Uploaded";
		int count=0;
		int uncount=0;
		Long addNo=null;
		Date date=new Date();
		
		Long sId=null;
		Long schoolId = null;
		List<String> admisionNoList = new ArrayList<String>();
		for(ExcelUser xcelUserList: xcelUserlist)
		{
			User user=xcelUserList.getStudent();
			admisionNoList.add(user.getAdmissionNo());			
			 schoolId=user.getSchoolId();
		}
		
		List<User> userList = userService.getAllUserByAdminsionNo(admisionNoList,schoolId);
		if(userList != null && userList.size() > 0){
			msg = "";
			
			for(User user : userList){
				msg += user.getAdmissionNo()+", ";
			}
			return "Adminsion No already exist - "+msg.substring(0, msg.length() -2);
		}else{
		
			
		for(ExcelUser xcelUserList: xcelUserlist)
		{
			User user=xcelUserList.getStudent();
		
		schoolId = user.getSchoolId();
	
		
		user.setUserName(user.getSchoolIdCode()+"-"+user.getAdmissionNo());
			
			/*Boolean isUserExist = userService.isUserExistByUserNameOrAdmissionNo(
					user.getUserName(), user.getAdmissionNo(),user.getSchoolId());
					if(!isUserExist){*/
						
						
						Section section =userService.getSection(schoolId,user.getGradeName(),user.getSectionName());
							if(section!=null)
							{
								//set student username append with schoolid and student admission no
								user.setSectionId(section.getId());
								user.setStatus(User.STATUS_INACTIVE); 
								user.setGradeId(section.getSessionSchoolGrade().getGradeId());
								userService.SaveOrUpdate(user);
								sId=user.getId();
								StudentTeacherSession sts=new StudentTeacherSession();
								sts.setSchoolId(schoolId);
								sts.setStatus(StudentTeacherSession.STATUS_ACTIVE);
								sts.setCreatedOn(date);
								sts.setUserRole(User.USERTYPE_STUDENT);
								sts.setSectionId(section.getId());
								sts.setSessionId(user.getSessionId());
								sts.setCreatedOn(null); 
								//sts.setSubjectId(null);
								sts.setUserId(sId);
								userService.saveStudentTeacherSession(sts);
								count++;
							}
							else{
								return msg="Either Grade or Section name is not Exist";
							}
						
					
					saveParent(xcelUserList.getParents(),sId);
				}
		}
		return msg;
		
		
	}



	@Transactional
	@Override
	public void saveParent(List<User> parentList, Long sId)
	{
		
		Date date=new Date();
		Long pId=null;
	
				for(User parents:parentList)
				{
					String pEmail=parents.getEmailId();
					Boolean isUserExist = userService.isUserExistByUserNameOrAdmissionNo(
							parents.getUserName(),null,null);
					if(!isUserExist)
					{
							parents.setCreatedDate(date);
							parents.setStatus(User.STATUS_ACTIVE);
							parents.setUserName(parents.getEmailId());
							userService.SaveOrUpdate(parents);
							pId=parents.getId();
							
						
					}
					else
					{
						User user1=checkUser(pEmail,pEmail);
						pId=user1.getId();
					}
					saveParentChileMapping(pId, sId);
					
				}
				
	
		
		
		
	}

	@Transactional
	@Override
	public void saveParentChileMapping(Long pId,Long sId)
	{
		StringBuilder msg=new StringBuilder();
		Date date=new Date();
		ParentChildMapping pcm=checkParentChildMapping(pId, sId);
		if(pcm==null)
		{
			ParentChildMapping pcm1=new ParentChildMapping();
			pcm1.setInsertDate(date);
			pcm1.setParentId(pId);
			pcm1.setUserId(sId);
			userService.saveParentChildMapping(pcm1);
			
		}

	
		
	}
	@Override
	@Transactional
	public List<Section> getSectionByGradeId(Long schoolId, Long sessionId,
			Long gradeId) {
		return schoolAdminDao.getSectionByGradeId(schoolId,sessionId,gradeId);
	}

	@Override
	@Transactional
	public List<StudentTeacherSession> getAllStudentBySectionId(Long sectionId) {
		return schoolAdminDao.getAllStudentBySectionId(sectionId);
	}

	@Override
	@Transactional
	public List<ParentChildMapping> getParentsDetailsByUserId(Long userId) {
		return schoolAdminDao.getParentsDetailsByUserId(userId);
	}

	@Override
	@Transactional
	public Boolean saveStudentTeacherSession(StudentTeacherSession sts) {
		return schoolAdminDao.saveStudentTeacherSession(sts);
	}

	@Override
	@Transactional
	public List<User> getAllSchoolTeacher(Long schoolId, Long sessionId) {
		return schoolAdminDao.getAllSchoolTeacher(schoolId,sessionId);
	}

	@Override
	@Transactional
	public List<SessionSchoolGrade> getAllGradeSectionSubject(Long schoolId, Long sessionId) {
		return schoolAdminDao.getAllGradeSectionSubject(schoolId, sessionId);
	}

	@Override
	@Transactional
	public String editGradeName(long gradeId, String gradeName, long schoolId) {
		if(schoolAdminDao.isSectionExist(schoolId,gradeName))
			return "duplicate";
			else
			return schoolAdminDao.editGradeName(gradeId, gradeName) ;
	}

	@Override
	@Transactional
	public String createSection(long schoolId, long sessionId,
			String sectionName, long gradeId) {
		return schoolAdminDao.createSection(schoolId, sessionId, sectionName, gradeId);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectBySectionId(Long sectionId) {
		return schoolAdminDao.getSubjectBySectionId(sectionId);
	}

	@Override
	@Transactional
	public Boolean assignSubjectToTeacher(
			StudentTeacherSession studentTeacherSession) {
		return schoolAdminDao.assignSubjectToTeacher(studentTeacherSession);
	}

	@Override
	@Transactional
	public Boolean isAlreadyAssignSubject(
			StudentTeacherSession studentTeacherSession) {
		return schoolAdminDao.isAlreadyAssignSubject(studentTeacherSession);
	}

	@Override
	@Transactional
	public List<StudentTeacherSession> getAssignSubjectTeacherList(
			Long schoolId, Long sessionId) {
		return schoolAdminDao.getAssignSubjectTeacherList(schoolId,sessionId);
	}

	@Override
	@Transactional
	public void removeSubjectFromTeacher(Long studentTeacherSubjectId) {
		schoolAdminDao.removeSubjectFromTeacher(studentTeacherSubjectId);
		
	}

	@Override
	@Transactional
	public List<User> getAllSchoolSmeList(Long schoolId) {
		return schoolAdminDao.getAllSchoolSmeList(schoolId);
	}

	@Override
	@Transactional
	public void inactivateUser(Long userId) {
		 schoolAdminDao.inactivateUser(userId);
		
	}

	@Override
	@Transactional
	public void saveSubjectSectionMapping(String subjectIds, long sectionId) {
		String subjectId[]=subjectIds.split(",");
		for (int i = 0; i < subjectId.length; i++) {
			System.out.println("mapping create for subject: "+ subjectId[i]+" and section :"+ sectionId+"with id: "+subjectSectionDao.addSubjectSectionMapping(Long.parseLong(subjectId[i]), sectionId+""));
		}
		
		
	}

	@Transactional
	@Override
	public void removeMapping(Long subjectSectionId) {
		 schoolAdminDao.removeMapping(subjectSectionId);
		
	}

	@Override
	@Transactional
	public Boolean isSubjectAlreadyUnassigned(StudentTeacherSession sts) {
		return schoolAdminDao.isSubjectAlreadyUnassigned(sts);
	}

	@Override
	@Transactional
	public List<Long> getStudentUnassignedSubjectList(Long userId,
			Long sectionId) {
		return schoolAdminDao.getStudentUnassignedSubjectList(userId,sectionId);
	}

	@Override
	@Transactional
	public void inactivateUserFromCurrentSession(Long userId, Long sessionId) {
		schoolAdminDao.inactivateUserFromCurrentSession(userId,sessionId);
	}

	@Override
	@Transactional
	public List<StudentTeacherSession> getUnassignSubjectListByUserId(
			Long userId, Long sessionId) {
		return schoolAdminDao.getUnassignSubjectListByUserId(userId,sessionId);
	}

	@Override
	@Transactional
	public void removeUnassignedSubject(Long studentId, Long[] subjectIds,
			Long sessionId, Long schoolId) {
		schoolAdminDao.removeUnassignedSubject(studentId,subjectIds,sessionId,schoolId);
		
	}

	@Override
	@Transactional
	public Long getGradeIdByectionId(Long sectionId) {
		return schoolAdminDao.getGradeIdByectionId(sectionId);
	}

	@Override
	@Transactional
	public Long getUserSectionId(Long userId) {
		return schoolAdminDao.getUserSectionId(userId);
	}

	@Override
	@Transactional
	public List<Subject> getAllSubjectsBySectionIds(List<Long> sectionList) {
		return schoolAdminDao.getAllSubjectsBySectionIds(sectionList);
	}

	@Override
	@Transactional
	public Boolean isAlreadyAssigned(Long subjectId, Long sectionId, Integer userType)
	{
		return schoolAdminDao.isAlreadyAssigned(subjectId,sectionId,userType);
	}

	
	public boolean notEmpty(String s)
	{
		return s == null || s.trim().equals("") ? false : true;
	}

	@Override
	@Transactional
	public List<Subject> getAllSubjectBySchoolId(Long schoolId) {
		
		
		 return schoolAdminDao.getAllSubjectsBySchoolId(schoolId);
	}

	@Override
	@Transactional
	public  boolean isSubjectExist(String sName,Long schoolId) {
		return schoolAdminDao.isSubjectExist(sName,schoolId);
	}

	@Override
	@Transactional
	public List<Section> getAllSectionBySubjectId(Long subjectId,List<Long> schoolIds) {

		return schoolAdminDao.getAllSectionBySubjectId(subjectId,schoolIds);
	}

	@Override
	@Transactional
	public void updateMapping()
	{
		schoolAdminDao.updateMapping();
		
	}

	

}
	
	
	

