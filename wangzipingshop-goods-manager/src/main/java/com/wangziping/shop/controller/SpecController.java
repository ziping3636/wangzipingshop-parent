package com.wangziping.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Spec;
import com.wangziping.shop.service.SpecService;

@Controller
@RequestMapping("spec")
public class SpecController {

	@Reference
	SpecService specService;

	/**
	 * @Title: list
	 * @Description: 规格列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize, String name) {
		PageInfo<Spec> list = specService.list(pageNum, pageSize, name);
		request.setAttribute("info", list);
		request.setAttribute("name", name);

		return "spec/list";
	}

	/**
	 * @Title: add
	 * @Description: TODO添加规格
	 * @param request
	 * @param spec
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("addSpec")
	public Object add(HttpServletRequest request, Spec spec) {
		System.err.println(spec);
		spec.getOptions().removeIf(x -> {
			return x.getOptionName() == null;
		});
		int addSpec = specService.addSpec(spec);
		return addSpec > 0 ? true : false;
	}

	/**
	 * @Title: updateSpec
	 * @Description: TODO修改规格
	 * @param request
	 * @param spec
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("updateSpec")
	public Object updateSpec(HttpServletRequest request, Spec spec) {
		System.err.println(spec);
		spec.getOptions().removeIf(x -> {
			return x.getOptionName() == null;
		});
		int updateSpec = specService.updateSpec(spec);
		return updateSpec > 0 ? true : false;
	}

	/**
	 * @Title: delSpec
	 * @Description: TODO删除规格
	 * @param request
	 * @param id
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("delSpec")
	public Object delSpec(HttpServletRequest request, Integer id) {
		return specService.deleteSpec(id) > 0 ? true : false;
	}

	/**
	 * @Title: delBatchSpec
	 * @Description: TODO批量删除规格
	 * @param request
	 * @param ids
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("delBatchSpec")
	public Object delBatchSpec(HttpServletRequest request, @RequestParam(name = "ids[]") int[] ids) {
		return specService.deleteBatchSpec(ids) > 0 ? true : false;
	}

	/**
	 * @Title: findById
	 * @Description: TODO根据id查询规格
	 * @param request
	 * @param id
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("getSpecById")
	public Object getSpecById(HttpServletRequest request, Integer id) {
		return specService.findById(id);
	}
	

}
