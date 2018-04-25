package com.boco.xxzx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.DictionaryDao;
import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.service.DictionaryService;
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService{
    @Autowired
    private  DictionaryDao  dictionaryDao;
    
	public List<Dictionary> listDictionary() {
		
		return dictionaryDao.listDictionary();
	}

	public List<Dictionary> listDictionaryByCode(String code) {
		
		return dictionaryDao.listDictionaryByCode(code);
	}

	public void saveDictionary(Dictionary dictionary) {
		
	     dictionaryDao.saveDictionary(dictionary);
	}

	public void updateDictionary(Dictionary dictionary) {
		
		 dictionaryDao.updateDictionary(dictionary);
	}

	public Integer removeDataById(long id) {

		return dictionaryDao.removeDataById(id);
	}

	public Dictionary getDictionary(String id) {
		
		return dictionaryDao.getDictionary(id);
	}

	public List<String> listDictionaryCode() {
		
		return dictionaryDao.listDictionaryCode();
	}

	public List<Dictionary> listDictionaryByCodeAndValue(String code, String value) {
	   Map<String, Object> map= new HashMap<String, Object>(16);
	   map.put("code", code);
	   map.put("value", value);
	   return dictionaryDao.listDictionaryByCodeAndValue(map);
	}

}
