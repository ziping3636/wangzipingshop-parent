package com.wangziping.shop.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.comm.ShopConstant;
import com.wangziping.shop.pojo.Category;
import com.wangziping.shop.pojo.Sku;
import com.wangziping.shop.pojo.Spu;
import com.wangziping.shop.pojo.SpuEsVo;
import com.wangziping.shop.pojo.SpuVo;
import com.wangziping.shop.service.GoodsService;
import com.wangziping.shop.utils.ElasticSearchUtils;

/**
 * @ClassName: IndexController
 * @Description: TODO 首页
 * @author: wangzping
 * @date: 2020年3月11日 上午9:39:10
 */
@Controller
public class IndexController {

	@Autowired
	private RedisTemplate<String, PageInfo<Spu>> redisTemplate;

	@Autowired
	private ElasticSearchUtils<SpuEsVo> elasticSearchUtils;

	@Reference
	private GoodsService goodsService;

	@RequestMapping({ "", "/", "index" })
	public String index(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int categoryId) {
		if (pageNum == 1 && categoryId == 0) {
			ValueOperations<String, PageInfo<Spu>> opsForValue = redisTemplate.opsForValue();
			if (redisTemplate.hasKey(ShopConstant.SPU_CACHE_KEY)) {
				PageInfo<Spu> pageInfo = opsForValue.get(ShopConstant.SPU_CACHE_KEY);
				request.setAttribute("info", pageInfo);
				request.setAttribute("categoryId", categoryId);
				return "index";
			} else {
				// 从服务中获取数据
				PageInfo<Spu> listSpu = goodsService.listSpu(pageNum, pageSize, new SpuVo());
				opsForValue.set(ShopConstant.SPU_CACHE_KEY, listSpu, 30, TimeUnit.MINUTES);
				request.setAttribute("info", listSpu);
				request.setAttribute("categoryId", categoryId);
			}
		}
		PageInfo<Spu> listSpu = goodsService.listSpu(pageNum, pageSize, new SpuVo());
		request.setAttribute("info", listSpu);
		request.setAttribute("categoryId", categoryId);
		return "index";
	}

	@RequestMapping("search")
	public String search(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize, String key) {
		AggregatedPage<SpuEsVo> queryObjects = elasticSearchUtils.queryObjects(SpuEsVo.class, pageNum, pageSize,
				new String[] { "goodsName", "caption", "brandName", "categoryName\r\n" + "" }, key);
		request.setAttribute("info", queryObjects);
		return null;
	}

	@RequestMapping("detail")
	public String detail(HttpServletRequest request, int spuId) {
		Spu spuById = goodsService.getSpuById(spuId);
		List<Sku> listSkuBySpu = goodsService.listSkuBySpu(spuId);
		request.setAttribute("spu", spuById);
		request.setAttribute("skus", listSkuBySpu);

		return "detail";
	}

	@ResponseBody
	@RequestMapping("treeData")
	public List<Category> treeData(HttpServletRequest request) {

		return goodsService.treeCategory();
	}
}
