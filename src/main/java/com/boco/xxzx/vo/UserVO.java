package com.boco.xxzx.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/** 
 * @author   liushaoqing 
 * @date 创建时间：2017年10月26日 下午4:30:19 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
@Alias(value="User")
public class UserVO implements Serializable{
	private static final long serialVersionUID = -5863231760533215204L;
	private Integer id;              //用户id
	private String employeeId;       //员工编号（全拼）
	private String employeeName;     //员工姓名（汉字）
	private String depNum;           //部门编号
	private String password;         //密码
	private String mobile;           //手机号
	private String email;            //邮箱
	private String creater;          //创建人
	private Date createTime;         //创建时间
	private String modifier;         //修改人
	private Date modifyTime;         //修改时间
	private Integer status;          //状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDepNum() {
		return depNum;
	}
	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}
	public UserVO() {
		super();
	}
	public UserVO(Integer id, String employeeId, String employeeName,
			String password, String mobile, String email, String creater,
			Date createTime, String modifier, Date modifyTime,
			Integer status,String depNum) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.creater = creater;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.status = status;
		this.depNum=depNum;
	}
	
}
