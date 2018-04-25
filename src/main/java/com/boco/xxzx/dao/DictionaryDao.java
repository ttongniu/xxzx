package com.boco.xxzx.dao;

import java.util.List;
import java.util.Map;

import com.boco.xxzx.model.Dictionary;
/**
 * 
 * @ClassName:  DictionaryDao   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: niutongtong  
 * @date:   2017年11月10日 下午6:20:43   
 *
 */
public interface DictionaryDao {
	/**
	 * 
	 * <p>
	 * Title: listDictionary
	 * </p>
	 * <p>
	 * Description: TODO(数据字典搜索的方法)
	 * </p>
	 * 
	 * @param: @param dictionary
	 * @param: @return
	 * @return: List<User>
	 *
	 */
	public List<Dictionary> listDictionary();
	/**
	 * 
	 * <p>Title: listDictionaryByCode</p>   
	 * <p>Description: (通过类别查询字典信息)</p>  
	 * @param: @param dictionary
	 * @param: @return      
	 * @return: List<Dictionary>      
	 *
	 */
	public List<Dictionary> listDictionaryByCode(String code);
	/**
	 * 
	 * <p>
	 * Title: saveDictionary
	 * </p>
	 * <p>
	 * Description: TODO(数据字典添加的方法)
	 * </p>
	 * 
	 * @param: @param dictionary
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public void saveDictionary(Dictionary dictionary);
	/**
	 * 
	 * <p>
	 * Title: updateDictionary
	 * </p>
	 * <p>
	 * Description: TODO(数据字典修改的方法)
	 * </p>
	 * 
	 * @param: @param dictionary
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public void updateDictionary(Dictionary dictionary);
	/**
	 * 
	 * <p>Title: removeData</p>   
	 * <p>Description: (删除字典根据id)</p>  
	 * @param: @param id
	 * @param: @return      
	 * @return: Integer      
	 *
	 */
	public Integer removeDataById(long id);
	/**
	 * 
	 * <p>Title: getDictionary</p>   
	 * <p>Description: (根据id 查询字典信息)</p>  
	 * @param: @param id
	 * @param: @return      
	 * @return: Object      
	 *
	 */
	public Dictionary getDictionary(String id);
	/**
	 * 
	 * <p>Title: listDictionaryCode</p>   
	 * <p>Description: (类别代码)</p>  
	 * @param: @return      
	 * @return: List<String>      
	 *
	 */
	public   List<String>  listDictionaryCode();
	
	/**
	 * 
	 * <p>Title: listDictionaryByCodeAndValue</p>   
	 * <p>Description: (多条件查询)</p>  
	 * @param: @param map
	 * @param: @return      
	 * @return: List<Dictionary>      
	 *
	 */
	public List<Dictionary>  listDictionaryByCodeAndValue(Map<String,Object> map);
	
	
	
}
