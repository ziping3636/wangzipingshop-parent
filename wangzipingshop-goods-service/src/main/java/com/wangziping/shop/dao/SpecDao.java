package com.wangziping.shop.dao;

import java.util.List;

import com.wangziping.shop.pojo.Spec;
import com.wangziping.shop.pojo.SpecOption;

public interface SpecDao {

	List<Spec> list(String name);

	int addSpec(Spec spec);

	int addOptions(SpecOption specOption);

	int updateSpec(Spec spec);

	Spec findById(int id);

	int deleteSpecOptions(int id);

	int deleteSpec(int id);

	int deleteBatchSpecOptions(int[] ids);

	int deleteBatchSpec(int[] ids);

	List<Spec> getAllSpec();


}
