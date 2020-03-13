package com.wangziping.shop.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "shopindex", type = "goods")
public class SpuEsVo implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5751474086150538138L;

	@Id
	private Integer id;
	private String goodsName;
//	private String isMarketable; // 是否上线
	private Integer brandId; // 品牌
	private String caption; // 标题
	private Integer categoryId; // 分类
	private String smallPic;// 小图
	private String brandName;
	private String categoryName;

	public SpuEsVo(Spu spu) {
		super();
		this.id = spu.getId();
		this.goodsName = spu.getGoodsName();
		this.brandId = spu.getBrandId();
		this.caption = spu.getCaption();
		this.categoryId = spu.getCategoryId();
		this.smallPic = spu.getSmallPic();
		this.brandName = spu.getBrand() == null ? "" : spu.getBrand().getName();
		this.categoryName = spu.getCaption() == null ? "" : spu.getCategory().getName();
	}

	public SpuEsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsName == null) ? 0 : goodsName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpuEsVo other = (SpuEsVo) obj;
		if (goodsName == null) {
			if (other.goodsName != null)
				return false;
		} else if (!goodsName.equals(other.goodsName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpuEsVo [id=" + id + ", goodsName=" + goodsName + ", brandId=" + brandId + ", caption=" + caption
				+ ", categoryId=" + categoryId + ", smallPic=" + smallPic + ", brandName=" + brandName
				+ ", categoryName=" + categoryName + "]";
	}

}
