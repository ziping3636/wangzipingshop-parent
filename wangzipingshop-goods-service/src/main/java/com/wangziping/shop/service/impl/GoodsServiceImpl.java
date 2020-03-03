package com.wangziping.shop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Brand;
import com.wangziping.shop.pojo.Category;
import com.wangziping.shop.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	public int addBrand(Brand brand) {
		return 0;
	}

	public int updateBrand(Brand brand) {
		return 0;
	}

	public int deleteBrand(Integer id) {
		return 0;
	}

	public PageInfo<Brand> listBrand(String firstChar, int pageNum, int pageSize) {
		return null;
	}

	public int addCategory(Category categoty) {
		return 0;
	}

	public int updateCategory(Category brand) {
		return 0;
	}

	public int deleteCategory(Integer id) {
		return 0;
	}

	public PageInfo<Category> listCategory(String firstChar, int pageNum, int pageSize) {
		return null;
	}

	public List<Category> treeCategory() {
		return null;
	}

}
