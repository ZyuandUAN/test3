package com.situ.crm.grant.model;

import java.util.ArrayList;
import java.util.List;

import com.situ.util.Pager;

public class MenuModel extends Pager{
private Integer id;
private String menuCode;//一级菜单编号
private String menuName;//一级菜单名称 
private String menuUrl;//一级菜单
private String parentCode;
private String level;
private List<MenuModel>child = new ArrayList<>();

public List<MenuModel> getChild() {
	return child;
}
public void setChild(List<MenuModel> child) {
	this.child = child;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getMenuCode() {
	return menuCode;
}
public void setMenuCode(String menuCode) {
	this.menuCode = menuCode;
}
public String getMenuName() {
	return menuName;
}
public void setMenuName(String menuName) {
	this.menuName = menuName;
}
public String getMenuUrl() {
	return menuUrl;
}
public void setMenuUrl(String menuUrl) {
	this.menuUrl = menuUrl;
}
public String getParentCode() {
	return parentCode;
}
public void setParentCode(String parentCode) {
	this.parentCode = parentCode;
}
@Override
public String toString() {
	return "MenuModel [id=" + id + ", menuCode=" + menuCode + ", menuName=" + menuName + ", menuUrl=" + menuUrl
			+ ", parentCode=" + parentCode + ", level=" + level + ", child=" + child + "]";
}
 
}
