package workflow;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.boco.xxzx.model.BoFile;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.BoFileService;
import com.boco.xxzx.service.DictionaryService;

import common.BaseTest;

public class TestBoFile extends BaseTest{

	@Autowired
	private BoFileService boFileService;
	
	/*@Test
	public void test(){
		System.out.println(JSON.toJSONString(dictionaryService.listDictionary()));
	}*/
    
	@Test
	public void test(){
		BoFile  boFile=new BoFile();
		User user =new User();
		user.setId(3);
		boFile.setCode("122501");
		boFile.setFileName("123.txt");
		boFile.setFilePath("/xxzx/upadte/123.txt");
		boFile.setCreateMan(user);
		boFileService.saveFile(boFile);
	    System.out.println("***********");
	}
	
	@Test
	public void test01(){
		System.out.println(JSON.toJSONString(boFileService.listFilesByCode("122501")));
	}
}
