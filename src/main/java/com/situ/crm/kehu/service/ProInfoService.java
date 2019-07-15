package com.situ.crm.kehu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.kehu.mapper.ProInfoMapper;
import com.situ.crm.kehu.model.ProInfoModel;
import com.situ.util.IService;

@Service
public class ProInfoService implements IService<ProInfoModel>{
@Autowired
private ProInfoMapper mapper;
	@Override
	public int insert(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public ProInfoModel selectId(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<ProInfoModel> selectModel(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<ProInfoModel> selectList(ProInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

}
