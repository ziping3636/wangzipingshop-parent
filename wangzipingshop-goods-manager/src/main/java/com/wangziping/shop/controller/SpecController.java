package com.wangziping.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Spec;
import com.wangziping.shop.pojo.SpecOption;
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
}
