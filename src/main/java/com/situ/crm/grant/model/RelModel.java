package com.situ.crm.grant.model;

import com.situ.util.Pager;

public class RelModel extends Pager{
private Integer id;
private String roleCode;
private String menuCode;
private String roleName;
private String menuName;
private MenuModel menuModel;
private RoleModel roleModel;


public MenuModel getMenuModel() {
	return menuModel;
}
public void setMenuModel(MenuModel menuModel) {
	this.menuModel = menuModel;
}
public RoleModel getRoleModel() {
	return roleModel;
}
public void setRoleModel(RoleModel roleModel) {
	this.roleModel = roleModel;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getMenuName() {
	return menuName;
}
public void setMenuName(String menuName) {
	this.menuName = menuName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getRoleCode() {
	return roleCode;
}
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}
public String getMenuCode() {
	return menuCode;
}
public void setMenuCode(String menuCode) {
	this.menuCode = menuCode;
}
@Override
public String toString() {
	return "RelModel [id=" + id + ", roleCode=" + roleCode + ", menuCode=" + menuCode + ", roleName=" + roleName
			+ ", menuName=" + menuName + ", menuModel=" + menuModel + ", roleModel=" + roleModel + "]";
}
 
}
