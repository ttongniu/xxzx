package com.boco.xxzx.dao;

import java.util.List;

import com.boco.xxzx.model.BoFile;
/**
 * 
 * @ClassName:  BoFileDao   
 * @Description:(文件dao)   
 * @author: niutongtong  
 * @date:   2017年11月16日 下午2:46:02   
 *
 */	
public interface BoFileDao {
	      /**
	       * 
	       * <p>Title: listFilesByCode</p>   
	       * <p>Description: (根据code查询文件)</p>  
	       * @param: @param code
	       * @param: @return      
	       * @return: List<BoFile>      
	       *
	       */
          List<BoFile>	listFilesByCode(String code);
          /**
           * 
           * <p>Title: saveFile</p>   
           * <p>Description: (添加文件)</p>  
           * @param: @param boFile      
           * @return: void      
           *
           */
          void saveFile(BoFile boFile);

}
