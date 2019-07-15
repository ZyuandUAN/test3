package com.situ.crm.kehu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.kehu.mapper.CustInfoMapper;
import com.situ.crm.kehu.model.CustInfoModel;
import com.situ.util.IService;
@Service
public class CustInfoService implements IService<CustInfoModel>{
@Autowired
private CustInfoMapper mapper;
	@Override
	public int insert(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public CustInfoModel selectId(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<CustInfoModel> selectModel(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<CustInfoModel> selectList(CustInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

}
