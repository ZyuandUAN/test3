package com.situ.crm.kehu.model;


import com.situ.util.Pager;

public class OrderInfoModel extends Pager{
private Integer id;
private String userCode;
private String custCode;
private String proCode;
private String proName;
private String count;//销量
private String TIME;
private String status;

public String getProName() {
	return proName;
}
public void setProName(String proName) {
	this.proName = proName;
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
public String getCustCode() {
	return custCode;
}
public void setCustCode(String custCode) {
	this.custCode = custCode;
}
public String getProCode() {
	return proCode;
}
public void setProCode(String proCode) {
	this.proCode = proCode;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getTIME() {
	return TIME;
}
public void setTIME(String tIME) {
	TIME = tIME;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "OrderInfoModel [id=" + id + ", userCode=" + userCode + ", custCode=" + custCode + ", proCode=" + proCode
			+ ", proName=" + proName + ", count=" + count + ", TIME=" + TIME + ", status=" + status + "]";
}
 
}
