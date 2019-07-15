package com.situ.crm.kehu.model;

import com.situ.util.Pager;

public class CustComModel extends Pager{
private Integer id;
private String userCode;
private String custCode;
private String TIME;
private String type;
private String content;
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
public String getTIME() {
	return TIME;
}
public void setTIME(String tIME) {
	TIME = tIME;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "CustComModel [id=" + id + ", userCode=" + userCode + ", custCode=" + custCode + ", TIME=" + TIME + ", type="
			+ type + ", content=" + content + "]";
}

}
