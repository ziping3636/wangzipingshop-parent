package com.wangziping.shop.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName: AdminProperties
 * @Description: 配置类：读取属性文件
 * @author: wangziping
 * @date: 2020年3月4日 上午8:47:36
 */
@Configuration
@PropertySource("classpath:shop-admin.properties")
public class AdminProperties implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 8052698738004069109L;

	@Value("${admin.name}")
	String adminName;

	@Value("${admin.password}")
	String password;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
