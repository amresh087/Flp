package com.flp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.ParentChildMappingDao;
import com.flp.dao.ParentDao;
import com.flp.dao.UserDAO;
import com.flp.model.ParentChildMapping;
import com.flp.model.User;
import com.flp.service.ParentService;

@Service
public class ParentServiceImpl implements ParentService
{
	
	@Autowired
	private ParentDao parentDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ParentChildMappingDao parentChildMappingDao;
	
	@Override
	@Transactional
	public List<ParentChildMapping> getAllChildListByParentId(Long parentId)
	{
		return parentDao.getAllChildListByParentId(parentId);
	}

	@Override
	@Transactional
	public List<User> getSearchedStudents(String studentName)
	{
		return userDao.getSearchedStudents(studentName);
	}

	@Override
	@Transactional
	public String addSearchedStudent(Long parentId, Long studentId)
	{
		ParentChildMapping pcm = parentChildMappingDao.getParentChildMapping(parentId, studentId, ParentChildMapping.STATUS_INACTIVE);
		
		if(pcm != null && pcm.getStatus() == ParentChildMapping.STATUS_ACTIVE)
			return "alreadyMapped";
		else if(pcm != null && pcm.getStatus() == ParentChildMapping.STATUS_PENDING)
			return "alreadySent";
		
		pcm = new ParentChildMapping();
		pcm.setModifiedDate(new Date());
		pcm.setParentId(parentId);
		pcm.setUserId(studentId);
		pcm.setStatus(ParentChildMapping.STATUS_PENDING);
		
		pcm = parentChildMappingDao.saveEntity(pcm);
		
		return (pcm.getId() != null? "success" : "fail");
	}
}
