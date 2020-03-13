package com.wangziping.shop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangziping.shop.dao.BrandDao;
import com.wangziping.shop.dao.CategoryDao;
import com.wangziping.shop.dao.SkuDao;
import com.wangziping.shop.dao.SpuDao;
import com.wangziping.shop.pojo.Brand;
import com.wangziping.shop.pojo.Category;
import com.wangziping.shop.pojo.Sku;
import com.wangziping.shop.pojo.SpecOption;
import com.wangziping.shop.pojo.Spu;
import com.wangziping.shop.pojo.SpuEsVo;
import com.wangziping.shop.pojo.SpuVo;
import com.wangziping.shop.service.GoodsService;
import com.wangziping.shop.utils.ElasticSearchUtils;

@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private SpuDao spuDao;

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private ElasticSearchUtils<SpuEsVo> elasticSearchUtils;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public int addBrand(Brand brand) {
		return brandDao.addBrand(brand);
	}

	public int updateBrand(Brand brand) {
		return brandDao.updateBrand(brand);
	}

	public int deleteBrand(Integer id) {
		return brandDao.deleteBrand(id);
	}

	public PageInfo<Brand> listBrand(String firstChar, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		return new PageInfo<Brand>(brandDao.getBrandList(firstChar));
	}

	@Override
	public Brand findBrandById(Integer id) {
		return brandDao.findBrandById(id);
	}

	@Override
	public int delBatchbrand(int[] ids) {
		return brandDao.delBatchbrand(ids);
	}

	public int addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	public int deleteCategory(Integer id) {
		return categoryDao.deleteCategory(id);
	}

	public PageInfo<Category> listCategory(String firstChar, int pageNum, int pageSize) {
		return null;
	}

	public List<Category> treeCategory() {
		return categoryDao.treeCategory();
	}

	@Override
	public PageInfo<Spu> listSpu(int pageNum, int pageSize, SpuVo vo) {
		PageHelper.startPage(pageNum, pageSize);

		return new PageInfo<Spu>(spuDao.listSpu(vo));
	}

	@Override
	public int addSpu(Spu spu) {
		int addSpu = spuDao.addSpu(spu);
		Spu findById = spuDao.findById(spu.getId());
		SpuEsVo spuEsVo = new SpuEsVo(findById);
		elasticSearchUtils.saveObject(spuEsVo.getId().toString(), spuEsVo);

		kafkaTemplate.send("MyAddSpu", "addSpu", spu.getId().toString());
		return addSpu;
	}

	@Override
	public int updateSpu(Spu spu) {
		return spuDao.update(spu);
	}

	@Override
	public int deleteSpu(int id) {
		return spuDao.deleteSpu(id);
	}

	@Override
	public int deleteSpuBatch(int[] ids) {
		return spuDao.deleteSpuBatch(ids);
	}

	@Override
	public List<Brand> getAllBrands() {
		return brandDao.getAllBrands();
	}

	@Override
	public PageInfo<Sku> listSku(int pageNum, int pageSize, Sku sku) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Sku>(skuDao.list(sku));
	}

	@Override
	public Sku getSkuById(Integer id) {
		return skuDao.getSkuById(id);
	}

	@Override
	public int addSku(Sku sku) {
		int addSku = skuDao.addSku(sku);
		List<SpecOption> specs = sku.getSpecs();
		for (SpecOption specOption : specs) {
			addSku += skuDao.addSkuSpec(sku.getId(), specOption);
		}
		return addSku;
	}

	@Override
	public int updateSku(Sku sku) {
		return 0;
	}

	@Override
	public int deleteSku(int id) {
		return 0;
	}

	@Override
	public int deleteSkuBatch(int[] ids) {
		return 0;
	}

	@Override
	public Spu getSpuById(Integer id) {
		return spuDao.findById(id);
	}

	@Override
	public List<Sku> listSkuBySpu(int spuId) {
		return skuDao.listSkuBySpu(spuId);
	}

}
