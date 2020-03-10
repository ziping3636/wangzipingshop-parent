package com.wangziping.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangziping.shop.pojo.Category;
import com.wangziping.shop.service.GoodsService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Reference
	private GoodsService goodsService;

	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		return "category/list";
	}

	@ResponseBody
	@RequestMapping("treeData")
	public List<Category> treeData(HttpServletRequest request) {

		return goodsService.treeCategory();
	}

	@ResponseBody
	@RequestMapping("addChild")
	public Object addChild(HttpServletRequest request, @RequestParam(defaultValue = "0") int parentId, String name) {
		Category category = new Category();
		category.setParentId(parentId);
		category.setName(name);
		return goodsService.addCategory(category);
	}

	@ResponseBody
	@RequestMapping("updateCategory")
	public Object updateCategory(HttpServletRequest request, Category category) {
		return goodsService.updateCategory(category);
	}

	@ResponseBody
	@RequestMapping("delCategory")
	public Object addCategory(HttpServletRequest request, Integer id) {
		return goodsService.deleteCategory(id);
	}
}
