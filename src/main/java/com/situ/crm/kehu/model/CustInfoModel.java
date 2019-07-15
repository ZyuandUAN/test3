package com.situ.crm.kehu.model;

import com.situ.util.Pager;

public class CustInfoModel extends Pager{
private Integer id;
private String custCode;
private String custName;
private String status;
private String email;
private String roleName;
private String userName;
private Integer deleted = 0; // 删除标志（0=未删，1=已删）
private Integer state   = 1; // 状态标志（0=禁用，1=可用）
private String updateBy;    // 更新人（一般为用户表主键）
private String  updateTime;  // 更新时间

public String getUpdateBy() {
	return updateBy;
}
public void setUpdateBy(String updateBy) {
	this.updateBy = updateBy;
}
public String getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getCustCode() {
	return custCode;
}
public void setCustCode(String custCode) {
	this.custCode = custCode;
}
public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
@Override
public String toString() {
	return "CustInfoModel [id=" + id + ", custCode=" + custCode + ", custName=" + custName + ", status=" + status
			+ ", email=" + email + ", roleName=" + roleName + ", userName=" + userName + ", deleted=" + deleted
			+ ", state=" + state + ", updateBy=" + updateBy + ", updateTime=" + updateTime + "]";
}

}
