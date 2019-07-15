package com.situ.crm.grant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.MenuMapper;
import com.situ.crm.grant.model.MenuModel;
import com.situ.util.IService;
@Service
public class MenuService implements IService<MenuModel>{
@Autowired
private  MenuMapper mapper;
	@Override
	public int insert(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public MenuModel selectId(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<MenuModel> selectModel(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<MenuModel> selectList(MenuModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

}
