package com.wangziping.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Brand;
import com.wangziping.shop.service.GoodsService;

@Controller
@RequestMapping("brand")
public class BrandController {

	@Reference
	private GoodsService goodsService;

	/**
	 * @Title: list
	 * @Description: TODO查询所有品牌
	 * @param request
	 * @param pageNum
	 * @param pageSize
	 * @param firstChar
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize, String firstChar) {
		PageInfo<Brand> listBrand = goodsService.listBrand(firstChar, pageNum, pageSize);
		request.setAttribute("info", listBrand);
		request.setAttribute("firstChar", firstChar);
		return "brand/list";
	}

	/**
	 * @Title: addBrand
	 * @Description: TODO添加品牌
	 * @param request
	 * @param brand
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("addBrand")
	public Object addBrand(HttpServletRequest request, Brand brand) {

		return goodsService.addBrand(brand);
	}

	/**
	 * @Title: updateBrand
	 * @Description: TODO修改品牌
	 * @param request
	 * @param brand
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("updateBrand")
	public Object updateBrand(HttpServletRequest request, Brand brand) {

		return goodsService.updateBrand(brand);
	}

	/**
	 * @Title: delBrand
	 * @Description: TODO删除品牌
	 * @param request
	 * @param id
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("delBrand")
	public Object delBrand(HttpServletRequest request, Integer id) {

		return goodsService.deleteBrand(id);
	}

	/**
	 * @Title: getBrandById
	 * @Description: TODO根据Id获取品牌信息
	 * @param request
	 * @param id
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("getBrandById")
	public Object getBrandById(HttpServletRequest request, Integer id) {

		return goodsService.findBrandById(id);
	}
	
	/**
	 * @Title: delBatchbrand 
	 * @Description: TODO批量删除品牌
	 * @param request
	 * @param ids
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("delBatchbrand")
	public Object delBatchbrand(HttpServletRequest request, @RequestParam(name = "ids[]")int[] ids) {
		
		return goodsService.delBatchbrand(ids);
	}

}
