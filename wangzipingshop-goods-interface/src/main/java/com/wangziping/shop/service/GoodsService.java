package com.wangziping.shop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Brand;
import com.wangziping.shop.pojo.Category;
import com.wangziping.shop.pojo.Sku;
import com.wangziping.shop.pojo.Spu;
import com.wangziping.shop.pojo.SpuVo;

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

	List<Brand> getAllBrands();

	int delBatchbrand(int[] ids);

	Brand findBrandById(Integer id);

	int addCategory(Category category);

	int updateCategory(Category brand);

	int deleteCategory(Integer id);

	PageInfo<Category> listCategory(String firstChar, int pageNum, int pageSize);

	List<Category> treeCategory();
	
	Spu getSpuById(Integer id);

	PageInfo<Spu> listSpu(int pageNum, int pageSize, SpuVo vo);

	int addSpu(Spu spu);

	int updateSpu(Spu spu);

	int deleteSpu(int id);

	int deleteSpuBatch(int[] ids);

	PageInfo<Sku> listSku(int pageNum, int pageSize, Sku sku);

	Sku getSkuById(Integer id);

	int addSku(Sku sku);

	int updateSku(Sku sku);

	int deleteSku(int id);

	int deleteSkuBatch(int[] ids);

}
