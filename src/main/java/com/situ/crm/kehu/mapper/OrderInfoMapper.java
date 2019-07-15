package com.situ.crm.kehu.mapper;

import java.util.List;

import com.situ.crm.kehu.model.OrderInfoModel;
import com.situ.util.IMapper;

public interface OrderInfoMapper extends IMapper<OrderInfoModel>{

	List<OrderInfoModel> selectSum();

}
