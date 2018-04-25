package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName:  File   
 * @Description:(文件实体类)   
 * @author: niutongtong  
 * @date:   2017年11月16日 上午11:31:59   
 *
 */
public class BoFile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private long id;
	/**
	 * 编码
	 */
      private String code;
      /**
  	 * 文件路径
  	 */
      private String filePath;
      /**
  	 * 文件名称
  	 */
      private String fileName;
      private User   createMan;
      private Date   createTime;
      
	public BoFile() {
		super();
	}

	public BoFile(long id, String code, String filePath, String fileName, User createMan, Date createTime) {
		super();
		this.id = id;
		this.code = code;
		this.filePath = filePath;
		this.fileName = fileName;
		this.createMan = createMan;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", code=" + code + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", createMan=" + createMan + ", createTime=" + createTime + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public User getCreateMan() {
		return createMan;
	}

	public void setCreateMan(User createMan) {
		this.createMan = createMan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
      
      
      
}
