package com.situ.crm.grant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.UserMapper;
import com.situ.crm.grant.model.UserModel;
import com.situ.util.IService;

@Service
public class UserService implements IService<UserModel> {
	@Autowired
	private  UserMapper mapper;

	@Override
	public int insert(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public UserModel selectId(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<UserModel> selectModel(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<UserModel> selectList(UserModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

}
