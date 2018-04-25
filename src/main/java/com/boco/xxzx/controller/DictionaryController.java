package com.boco.xxzx.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.service.DictionaryService;
import com.boco.xxzx.utils.session.SessionContext;
import com.boco.xxzx.utils.util.DateUtils;
/**
 * 
 * @ClassName:  DictionaryController   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: niutongtong  
 * @date:   2017年11月13日 下午5:23:00   
 *
 */
@Controller
@RequestMapping("/dict")
public class DictionaryController {
	   Logger  logger=Logger.getLogger(this.getClass());
       @Autowired
       private DictionaryService  dictionaryService;
       
       @RequestMapping("/toSaveDictionary")
       public String  toSaveDictionary(){
    	   return "dictionary/saveDictionary";
       }
       
       @RequestMapping("/listDictionary")
       public String listDictionary(String code,String value, Model model){
    	   model.addAttribute("code", code);
    	   model.addAttribute("value", value);
    	 List<String> codeList = dictionaryService.listDictionaryCode();
    	 model.addAttribute("codeList", codeList);
    	 List<Dictionary>  dictionaries= new ArrayList<Dictionary>();
    	  if(code==null&&value==null){
    		    dictionaries =  dictionaryService.listDictionary(); 
    	  }else{
    		    dictionaries =  dictionaryService.listDictionaryByCodeAndValue(code, value); 
    	  }
    	  model.addAttribute("dictionaries", dictionaries);
		  return "dictionary/showDictionaryList";
       }
       
       @RequestMapping("/toEditDictionary")
       public String  toEditDictionary(String id,Model model){
    	   Dictionary dictionary = dictionaryService.getDictionary(id);
    	   model.addAttribute("dictionary", dictionary);
    	   return "dictionary/editDictionary";
       }
       
       @RequestMapping("/saveDictionary")
       public String  saveDictionary(Dictionary dictionary){
    	   logger.info("*****"+dictionary.toString());
    	   dictionary.setLastModifyUser(SessionContext.getCurrentUser());
    	   dictionaryService.saveDictionary(dictionary);
    	   return "redirect:/dict/listDictionary.do";
       }
       
       @RequestMapping("/updateDictionary")
       public String  updateDictionary(Dictionary dictionary){
    	   dictionary.setUpdateTime(DateUtils.getNow());
    	   dictionary.setLastModifyUser(SessionContext.getCurrentUser());
    	   dictionaryService.updateDictionary(dictionary);
    	   return "redirect:/dict/listDictionary.do";
       }
       
       @RequestMapping("/removeDictionary")
       public String  removeDictionary(long id){
    	   dictionaryService.removeDataById(id);
    	   return "redirect:/dict/listDictionary.do";
       }
       
}
