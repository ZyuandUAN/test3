package com.situ.crm.grant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.RoleMapper;
import com.situ.crm.grant.model.RoleModel;
import com.situ.util.IService;

@Service
public class RoleService implements IService<RoleModel>{
@Autowired
private RoleMapper mapper;

@Override
public int insert(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.insert(t);
}

@Override
public int updateActive(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.updateActive(t);
}

@Override
public int delete(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.delete(t);
}

@Override
public int selectCount(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.selectCount(t);
}

@Override
public RoleModel selectId(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.selectId(t);
}

@Override
public List<RoleModel> selectModel(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.selectModel(t);
}

@Override
public List<RoleModel> selectList(RoleModel t) {
	// TODO Auto-generated method stub
	return mapper.selectList(t);
}
	 

}
