package com.wangziping.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangziping.shop.pojo.Sku;
import com.wangziping.shop.pojo.SpecOption;

public interface SkuDao {

	List<Sku> list(Sku sku);

	Sku getSkuById(Integer id);

	int addSku(Sku sku);

	int addSkuSpec(@Param("skuId")int skuId, @Param("so")SpecOption so);

	/**
	 * @Title: listSkuBySpu 
	 * @Description: TODO 根据spu 获取一个 sku 列表
	 * @param spuId
	 * @return
	 * @return: List<Sku>
	 */
	List<Sku> listSkuBySpu(int spuId);
	
	Sku findSkuByid(Integer id);
}
