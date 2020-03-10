package com.wangziping.shop.dao;

import java.util.List;

import com.wangziping.shop.pojo.Category;

public interface CategoryDao {

	List<Category> treeCategory();

	int addCategory(Category category);

	int deleteCategory(Integer id);

	int updateCategory(Category category);
	
	Category findById(Integer id);
}
