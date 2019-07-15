package com.situ.crm.kehu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.kehu.mapper.CustComMapper;
import com.situ.crm.kehu.model.CustComModel;
import com.situ.util.IService;
@Service
public class CustComService implements IService<CustComModel>{
@Autowired
private CustComMapper mapper;;
	@Override
	public int insert(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public CustComModel selectId(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<CustComModel> selectModel(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<CustComModel> selectList(CustComModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

}
