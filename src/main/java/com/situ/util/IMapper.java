package com.situ.util;

import java.util.List;

public interface IMapper<T> {
	int insert(T t);// 添加

	int updateActive(T t);// 不为空字段修改

	int delete(T t);// 删除

	int selectCount(T t);// 条数

	T selectId(T t);// 查询单个用户

	List<T> selectModel(T t);// 分页列表

	List<T> selectList(T t);
}
