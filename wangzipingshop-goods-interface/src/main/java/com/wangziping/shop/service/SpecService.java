package com.wangziping.shop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Spec;

/** 
 * @ClassName: SpecService 
 * @Description: 规格
 * @author: wangziping
 * @date: 2020年3月4日 上午10:13:59  
 */
public interface SpecService {

	PageInfo<Spec> list(int pageNum, int pageSize, String name);

	int addSpec(Spec spec);

	int updateSpec(Spec spec);

	Spec findById(int id);

	int deleteSpec(int id);

	/**
	 * @Title: deleteBatchSpec
	 * @Description: 批量删除
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteBatchSpec(int[] ids);

	List<Spec> getAllSpec();
}
