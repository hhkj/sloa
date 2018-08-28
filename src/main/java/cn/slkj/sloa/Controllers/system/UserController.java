package cn.slkj.sloa.Controllers.system;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.hbsl.util.UuidUtil;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.impl.UserServiceImpl;
import cn.slkj.sloa.util.Const;
import cn.slkj.sloa.util.easyuiUtil.EPager;
import cn.slkj.sloa.util.easyuiUtil.JsonResult;

/**
 * @author maxh
 * @ClassName : UserController
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月7日 下午5:42:24
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/toUserListPage")
	public String toCarList(HttpServletRequest request) {
		return "/system/userList";
	}

	@RequestMapping("/queryAll")
	@ResponseBody
	public List<User> queryAll(HttpServletRequest request) throws Exception {
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		List<User> list = userServiceImpl.queryAll(pageMap);
		return list;
	}

	/**
	 * 查询用户列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<User> getAllUsers(HttpServletRequest request, HttpSession session, @RequestParam(required = false, defaultValue = "1") Integer page, // 第几页
			@RequestParam(required = false, defaultValue = "15") Integer rows) {
		String sortString = "create_time.desc";
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		String departcode = request.getParameter("dcode");
		String realname = request.getParameter("realname");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("status", status);
		hashMap.put("username", username);
		hashMap.put("realname", realname);
		hashMap.put("departcode", departcode);
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<User> list = userServiceImpl.getAllUsers(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<User>(pageList.getPaginator().getTotalCount(), list);
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login_login")
	@ResponseBody
	public JsonResult login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HashMap<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		User user = userServiceImpl.userLogin(map);
		if (user != null) {
			session.removeAttribute(Const.SESSION_USER);
			session.setAttribute(Const.SESSION_USER, user);
			return new JsonResult(true, "用户登陆OK！");
		} else {
			return new JsonResult(false, "用户登陆失败！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonResult save(User user) {
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("username", user.getUsername().trim());
			User u = userServiceImpl.queryOne(hashMap);
			int i = -1;
			if (u != null) {
				return new JsonResult(false, "用户已经存在，请重新填写。");
			} else {
				user.setPassword("000000");
				user.setId(UuidUtil.get32UUID());
				i = userServiceImpl.save(user);
			}
			if (i != -1) {
				return new JsonResult(true, "用户创建成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "系统异常，用户创建失败！");
		}
		return new JsonResult(false, "创建失败！");
	}

}
