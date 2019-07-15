package com.situ.crm.grant.model;

import com.situ.util.Pager;

public class UserModel extends Pager{
	private Integer id;
	private String userCode;
	private String pass;
	private String name;
	private String roleCode;
	private String roleName;
	private String parentCode;
	private boolean admin;
    private Integer deleted = 0; // 删除标志（0=未删，1=已删）
    private Integer state   = 1; // 状态标志（0=禁用，1=可用）
    private String updateBy;    // 更新人（一般为用户表主键）
    private String  updateTime;  // 更新时间
    private String  createTime;  // 创建时间
    private Integer createBy;    // 创建人（一般为用户表主键）
    
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserModel() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userCode=" + userCode + ", pass=" + pass + ", name=" + name + ", roleCode="
				+ roleCode + ", roleName=" + roleName + ", parentCode=" + parentCode + ", admin=" + admin + ", deleted="
				+ deleted + ", state=" + state + ", updateBy=" + updateBy + ", updateTime=" + updateTime
				+ ", createTime=" + createTime + ", createBy=" + createBy + "]";
	}
}
