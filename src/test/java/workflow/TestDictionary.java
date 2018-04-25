package workflow;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.boco.xxzx.service.DictionaryService;

import common.BaseTest;

public class TestDictionary extends BaseTest{

	@Autowired
	private DictionaryService  dictionaryService;
	@Test
	public void test01(){
		System.out.println(JSON.toJSONString(dictionaryService.listDictionary()));
	}

}
