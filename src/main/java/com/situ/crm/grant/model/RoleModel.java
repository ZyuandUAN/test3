package com.situ.crm.grant.model;

import com.situ.util.Pager;

public class RoleModel extends Pager{
private Integer id;
private String roleCode;
private String roleName;
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
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
@Override
public String toString() {
	return "RoleModel [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + "]";
}

}
