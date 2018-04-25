package com.boco.xxzx.vo;
/**
 * 
 * @ClassName:  WfGroupVO   
 * @Description:TODO(流程权限组VO)   
 * @author: niutongtong  
 * @date:   2017年9月28日 下午5:37:58   
 *
 */
public class WfGroupVO {
	  /**组名称*/
      private String id; 
      /**版本*/
      private String rev;
      /**组描述*/
      private String name;
      /**
       * 组类型
       */
      private String type;
	public WfGroupVO(String id, String rev, String name, String type) {
		super();
		this.id = id;
		this.rev = rev;
		this.name = name;
		this.type = type;
	}
	public WfGroupVO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "WfGroupVO [id=" + id + ", rev=" + rev + ", name=" + name + ", type=" + type + "]";
	}
      
      
}
