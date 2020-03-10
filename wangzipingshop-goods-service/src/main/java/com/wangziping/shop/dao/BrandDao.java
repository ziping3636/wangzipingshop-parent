package com.wangziping.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangziping.shop.pojo.Brand;

public interface BrandDao {

	List<Brand> getBrandList(@Param("firstChar") String firstChar);

	int addBrand(Brand brand);

	int deleteBrand(Integer id);

	int updateBrand(Brand brand);

	Brand findBrandById(Integer id);

	int delBatchbrand(int[] ids);

	List<Brand> getAllBrands();

}
