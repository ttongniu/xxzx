package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 
 * @ClassName:  Dictionary   
 * @Description:(字典实体类)   
 * @author: niutongtong  
 * @date:   2017年11月10日 下午4:13:03   
 *
 */
@Alias("Dictionary")
public class Dictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	  private Integer id;
	  /**
	   * 数据类型代码
	   */
      private String code;
      /**
       * 数据键
       */
      private String key;
      /**
       * 数据值
       */
      private String value;
       /**
        * 描述
        */
      private String description ;
      private User  lastModifyUser;
      private Date createTime;
      private Date updateTime;
	public Dictionary(Integer id, String code, String key, String value, String description, User lastModifyUser,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.code = code;
		this.key = key;
		this.value = value;
		this.description = description;
		this.lastModifyUser = lastModifyUser;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Dictionary() {
		super();
	}
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", code=" + code + ", key=" + key + ", value=" + value + ", description="
				+ description + ", lastModifyUser=" + lastModifyUser + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(User lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
      
      
      
}
