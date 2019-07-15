package com.situ.crm.kehu.model;

import com.situ.util.Pager;

public class ProInfoModel extends Pager{
private Integer id;
private String proCode;
private String proName;
private String count;
private String sum;//数量
private String cost;//单价
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
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getProCode() {
	return proCode;
}
public void setProCode(String proCode) {
	this.proCode = proCode;
}
public String getProName() {
	return proName;
}
public void setProName(String proName) {
	this.proName = proName;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getSum() {
	return sum;
}
public void setSum(String sum) {
	this.sum = sum;
}
public String getCost() {
	return cost;
}
public void setCost(String cost) {
	this.cost = cost;
}
@Override
public String toString() {
	return "ProInfoModel [id=" + id + ", proCode=" + proCode + ", proName=" + proName + ", count=" + count + ", sum="
			+ sum + ", cost=" + cost + ", deleted=" + deleted + ", state=" + state + ", updateBy=" + updateBy
			+ ", updateTime=" + updateTime + "]";
}
 
}
