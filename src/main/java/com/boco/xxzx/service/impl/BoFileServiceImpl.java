package com.boco.xxzx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.BoFileDao;
import com.boco.xxzx.model.BoFile;
import com.boco.xxzx.service.BoFileService;
@Service("boFileService")
public class BoFileServiceImpl implements BoFileService {
     @Autowired 
     private BoFileDao  boFileDao;
	public List<BoFile> listFilesByCode(String code) {
		
		return boFileDao.listFilesByCode(code);
	}

	public void saveFile(BoFile boFile) {
		boFileDao.saveFile(boFile);
	}
       
}
