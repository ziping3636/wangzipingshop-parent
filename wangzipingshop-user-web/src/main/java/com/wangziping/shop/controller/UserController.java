package com.wangziping.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.comm.ShopConstant;
import com.wangziping.shop.pojo.Cart;
import com.wangziping.shop.pojo.Order;
import com.wangziping.shop.pojo.SpuEsVo;
import com.wangziping.shop.pojo.User;
import com.wangziping.shop.service.CartService;
import com.wangziping.shop.service.OrderService;
import com.wangziping.shop.service.WebUserService;
import com.wangziping.shop.utils.ElasticSearchUtils;

@Controller
@RequestMapping("user")
public class UserController {

	@Reference
	private WebUserService webUserService;

	@Reference
	private CartService cartService;

	@Reference
	private OrderService orderService;

	@Autowired
	private ElasticSearchUtils<SpuEsVo> elasticSearchUtils;

	@RequestMapping("toLogin")
	public String toLogin() {
		return "user/login";
	}

	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/register";
	}

	/**
	 * @Title: login
	 * @Description: TODO 登录
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 * @return: String
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request, String username, String password) {
		User user = webUserService.login(username, password);

		if (user == null) {
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("error", "用户名或密码错误！！");
			return "user/login";
		}

		request.getSession().setAttribute(ShopConstant.USERKEY, user);

		return "redirect:home";
	}

	/**
	 * @Title: register
	 * @Description: TODO 注册
	 * @param request
	 * @param user
	 * @return
	 * @return: String
	 */
	@RequestMapping("register")
	public String register(HttpServletRequest request, User user) {
		User register = webUserService.register(user);

		if (register == null) {
			request.setAttribute("user", user);
			request.setAttribute("error", "注册失败！！");
			return "user/register";
		}
		return "redirect:/user/toLogin";
	}

	/**
	 * @Title: home
	 * @Description: TODO 进入个人中心
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {

		return "/user/index";
	}

	/**
	 * @Title: addCart
	 * @Description: TODO 加入购物车
	 * @param skuId
	 * @param buyNum 购买数量
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("addCart")
	public String addCart(HttpServletRequest request, Integer skuId, Integer buyNum) {
		User loginUser = (User) request.getSession().getAttribute(ShopConstant.USERKEY);
		if (loginUser == null) {
			return "您尚未登录，请登录后加入购物车";
		}
		int result = cartService.addCart(loginUser.getUid(), skuId, buyNum);
		return result > 0 ? "success" : "Failed";
	}

	@RequestMapping("cartList")
	public String cartList(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize) {
		User loginUser = (User) request.getSession().getAttribute(ShopConstant.USERKEY);
		if (loginUser == null) {
			request.setAttribute("errot", "您尚未登录，请先登录");
			return "error";
		}
		PageInfo<Cart> list = cartService.List(loginUser.getUid(), pageNum, pageSize);
		request.setAttribute("info", list);
		return "user/cartlist";
	}

	@RequestMapping("orderList")
	public String orderList(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize) {
		User loginUser = (User) request.getSession().getAttribute(ShopConstant.USERKEY);
		if (loginUser == null) {
			request.setAttribute("errot", "您尚未登录，请先登录");
			return "error";
		}
		PageInfo<Order> orderList = orderService.orderList(pageNum, pageSize, loginUser.getUid());
		request.setAttribute("info", orderList);
		return "user/orderlist";
	}

	@RequestMapping("addOrder")
	public Object addOrder(HttpServletRequest request, @RequestParam(name = "cartIds[]") int[] cartIds,
			String address) {
		User loginUser = (User) request.getSession().getAttribute(ShopConstant.USERKEY);
		if (loginUser == null) {
			request.setAttribute("errot", "您尚未登录，请先登录");
			return "error";
		}
		int result = cartService.createOrder(loginUser.getUid(), cartIds, address);
		return result > 0 ? true : false;
	}

}
