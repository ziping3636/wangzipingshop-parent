package com.wangziping.shop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Brand;
import com.wangziping.shop.pojo.Category;

/**
 * @ClassName: GoodsService
 * @Description: TODO
 * @author: wangziping
 * @date: 2020年3月3日 上午9:17:03
 */
public interface GoodsService {

	int addBrand(Brand brand);

	int updateBrand(Brand brand);

	int deleteBrand(Integer id);

	PageInfo<Brand> listBrand(String firstChar, int pageNum, int pageSize);

	int addCategory(Category categoty);

	int updateCategory(Category brand);

	int deleteCategory(Integer id);

	PageInfo<Category> listCategory(String firstChar, int pageNum, int pageSize);

	List<Category> treeCategory();
}
