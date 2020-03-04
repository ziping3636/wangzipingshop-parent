package com.wangziping.shop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangziping.shop.dao.SpecDao;
import com.wangziping.shop.pojo.Spec;
import com.wangziping.shop.pojo.SpecOption;
import com.wangziping.shop.service.SpecService;

@Service(interfaceClass = SpecService.class)
public class SpecServiceImpl implements SpecService {

	@Autowired
	SpecDao specDao;

	public PageInfo<Spec> list(int pageNum, int pageSize, String name) {
		PageHelper.startPage(pageNum, pageSize);

		return new PageInfo<Spec>(specDao.list(name));
	}

	public int addSpec(Spec spec) {
		// 添加主表
		specDao.addSpec(spec);
		// 这里才能获取到主键ID
		// 添加字表option
		List<SpecOption> options = spec.getOptions();
		int num = 1;
		for (SpecOption specOption : options) {
			specOption.setSpecId(spec.getId());
			specDao.addOptions(specOption);
			num++;
		}
		// 返回的是影响数据的条数
		return num;
	}

	public int updateSpec(Spec spec) {
		return specDao.updateSpec(spec);
	}

	public Spec findById(int id) {
		return specDao.findById(id);
	}

	public int deleteSpec(int id) {
		// 删除子表
		specDao.deleteSpecOptions(id);
		// 删除主表
		specDao.deleteSpec(id);

		return 1;
	}

	public int deleteBatchSpec(int[] ids) {
		// 删除子表
		specDao.deleteBatchSpecOptions(ids);
		// 删除主表
		specDao.deleteBatchSpec(ids);
		return 1;
	}

}
