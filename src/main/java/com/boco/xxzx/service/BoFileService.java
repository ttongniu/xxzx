package com.boco.xxzx.service;

import java.util.List;

import com.boco.xxzx.model.BoFile;
/**
 * 
 * @ClassName:  BoFileService   
 * @Description:(文件服务接口)   
 * @author: niutongtong  
 * @date:   2017年11月16日 下午2:51:30   
 *
 */
public interface BoFileService {
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
