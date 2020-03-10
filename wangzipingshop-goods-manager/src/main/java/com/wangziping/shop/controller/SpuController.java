package com.wangziping.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Brand;
import com.wangziping.shop.pojo.Sku;
import com.wangziping.shop.pojo.Spec;
import com.wangziping.shop.pojo.SpecOption;
import com.wangziping.shop.pojo.Spu;
import com.wangziping.shop.pojo.SpuVo;
import com.wangziping.shop.service.GoodsService;
import com.wangziping.shop.service.SpecService;

@Controller
@RequestMapping("spu")
public class SpuController {

	@Reference
	private GoodsService goodsService;
	@Reference
	private SpecService specService;

	@RequestMapping("list")
	public String getSpuList(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize, SpuVo vo) {

		PageInfo<Spu> listSpu = goodsService.listSpu(pageNum, pageSize, vo);
		request.setAttribute("info", listSpu);
		request.setAttribute("vo", vo);
		return "spu/list";
	}

	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request) {
		List<Brand> allBrands = goodsService.getAllBrands();
		request.setAttribute("brand", allBrands);
		return "spu/add";
	}

	@ResponseBody
	@RequestMapping("addSpu")
	public Object addSpu(HttpServletRequest request, Spu spu, @RequestParam(value = "file") MultipartFile file)
			throws Exception {

		String filePath = processFile(file);
		spu.setSmallPic(filePath);
		return goodsService.addSpu(spu);
	}

	@ResponseBody
	@RequestMapping("updateSpu")
	public Object updateSpu(HttpServletRequest request, Spu spu) {
		return goodsService.updateSpu(spu);
	}

	/**
	 * @Title: downLoad
	 * @Description: TODO下载小图
	 * @param response
	 * @param file
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("down")
	public String downLoad(HttpServletResponse response, String file) {
		return "";
	}

	@ResponseBody
	@RequestMapping("delSpu")
	public Object delSpu(HttpServletRequest request, Integer id) {
		return goodsService.deleteSpu(id);
	}

	@ResponseBody
	@RequestMapping("delBatchSpu")
	public Object delBatchSpu(@RequestParam(name = "ids[]") int[] ids) {
		return goodsService.deleteSpuBatch(ids);
	}

	@RequestMapping("download")
	public void download(HttpServletResponse response, String fileName) throws Exception {
		// 读到流中
		InputStream inStream = new FileInputStream("d:\\pic\\" + fileName);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		// 循环取出流中的数据
		byte[] b = new byte[1024];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: processFile
	 * @Description: TODO上传文件
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @return: String
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty());
		System.out.println("file.name :" + file.getOriginalFilename());

		if (file.isEmpty() || "".equals(file.getOriginalFilename())
				|| file.getOriginalFilename().lastIndexOf('.') < 0) {
			return "";
		}

		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = path + "/" + UUID.randomUUID().toString() + suffixName;
		File distFile = new File(destFileName);
		file.transferTo(distFile);// 文件另存到这个目录下边
		return destFileName.substring(7);
	}

	@RequestMapping("sku/list")
	public String getSkuList(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize, Sku sku) {
		PageInfo<Sku> listSku = goodsService.listSku(pageNum, pageSize, sku);
		request.setAttribute("info", listSku);
		request.setAttribute("sku", sku);

		return "sku/list";
	}
	
	@RequestMapping("sku/toAddSku")
	public String toAddSku(HttpServletRequest request, Integer spuId) {
		Spu spuById = goodsService.getSpuById(spuId);
		request.setAttribute("spu", spuById);
		
		List<Brand> allBrands = goodsService.getAllBrands();
		request.setAttribute("brands", allBrands);
		
		List<Spec> allSpec = specService.getAllSpec();
		request.setAttribute("specs", allSpec);
		return "sku/add";
	}
	
	@RequestMapping("sku/detail")
	public String detail(HttpServletRequest request,Integer id) {
		Sku skuById = goodsService.getSkuById(id);
		List<Brand> allBrands = goodsService.getAllBrands();
		
		request.setAttribute("sku", skuById);
		request.setAttribute("brand", allBrands);
		
		return "sku/detail";
	}
	
	@ResponseBody
	@RequestMapping("addSku")
	public Object addSku(HttpServletRequest request, Sku sku, int[] specIds, int[] specOptionIds) {
		System.err.println(sku);
		System.err.println(specIds);
		System.err.println(specOptionIds);
		List<SpecOption> specs = new ArrayList<>();
		for (int i = 0; i < specIds.length && i < specOptionIds.length; i++) {
			int j = specIds[i];
			SpecOption specOption = new SpecOption();
			specOption.setSpecId(specIds[i]);
			specOption.setId(specOptionIds[i]);
			specs.add(specOption);
		}
		sku.setSpecs(specs);
		
		return goodsService.addSku(sku);
	}

}
