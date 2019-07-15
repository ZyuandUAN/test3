package com.situ.crm.grant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.RelMapper;
import com.situ.crm.grant.model.RelModel;
import com.situ.util.IService;
@Service
public class RelService implements IService<RelModel>{
@Autowired
private RelMapper  mapper;
	@Override
	public int insert(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public RelModel selectId(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<RelModel> selectModel(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<RelModel> selectList(RelModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

}
