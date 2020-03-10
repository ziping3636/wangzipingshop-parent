package com.wangziping.shop.dao;

import java.util.List;

import com.wangziping.shop.pojo.Spu;
import com.wangziping.shop.pojo.SpuVo;

public interface SpuDao {

	List<Spu> listSpu(SpuVo vo);

	int addSpu(Spu spu);

	int update(Spu spu);

	int deleteSpu(int id);

	int deleteSpuBatch(int[] ids);
	
	Spu findById(Integer id);

}
