package com.boco.xxzx.model;

import java.util.Date;
/**
 * 
 * @ClassName:  LeaveBill   
 * @Description:TODO(实体类)   
 * @author: niutongtong  
 * @date:   2017年10月19日 下午2:50:29   
 *
 */
public class LeaveBill {
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 请假天数
	 */
	private Integer days; 
	/**
	 * 请假内容
	 */
	private String content; 
	/**
	 * 请假时间
	 */
	private Date leaveDate = new Date(); 
	/**
	 * 备注
	 */
	private String remark; 
	/**
	 * 请假人
	 */
	private User user;
	/**
	 * 请假单状态 0初始录入,1.开始审批,2为审批完成
	 */
	private String state="0"; 
    /**
     * 创建时间
     */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	public LeaveBill() {
		super();
	}

	
   
	public LeaveBill(Long id, Integer days, String content, Date leaveDate, String remark, User user, String state,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.days = days;
		this.content = content;
		this.leaveDate = leaveDate;
		this.remark = remark;
		this.user = user;
		this.state = state;
		this.setCreateTime(createTime);
		this.setUpdateTime(updateTime);
	}



	@Override
	public String toString() {
		return "LeaveBill [id=" + id + ", days=" + days + ", content=" + content + ", leaveDate=" + leaveDate
				+ ", remark=" + remark + ", user=" + user + ", state=" + state + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
