package com.wangziping.shop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: OrderDetail
 * @Description: TODO 订单详情
 * @author: wangziping
 * @date: 2020年3月12日 下午7:31:00
 */
public class OrderDetail implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 6225356386606128474L;

	private Integer id;
	private Integer skuid;
	private BigDecimal total;
	private Integer oid;
	private Integer pnum;
	private Sku sku;

	public OrderDetail() {
		super();
	}

	public OrderDetail(Integer oid, Cart cart) {
		super();
		this.oid = oid;
		this.pnum = pnum;
		this.total = total;
		this.skuid = skuid;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", skuid=" + skuid + ", total=" + total + ", oid=" + oid + ", pnum=" + pnum
				+ ", sku=" + sku + "]";
	}

}
