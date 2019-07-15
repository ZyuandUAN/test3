package com.situ.crm.kehu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.kehu.mapper.OrderInfoMapper;
import com.situ.crm.kehu.model.OrderInfoModel;
import com.situ.util.IService;

@Service
public class OrderInfoService implements IService<OrderInfoModel>{
@Autowired
private OrderInfoMapper mapper;
	@Override
	public int insert(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.insert(t);
	}

	@Override
	public int updateActive(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.updateActive(t);
	}

	@Override
	public int delete(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.delete(t);
	}

	@Override
	public int selectCount(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectCount(t);
	}

	@Override
	public OrderInfoModel selectId(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectId(t);
	}

	@Override
	public List<OrderInfoModel> selectModel(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectModel(t);
	}

	@Override
	public List<OrderInfoModel> selectList(OrderInfoModel t) {
		// TODO Auto-generated method stub
		return mapper.selectList(t);
	}

	public List<OrderInfoModel> selectSum(){
		return mapper.selectSum();
	}

}
