package cn.slkj.sloa.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request) {
		return "/login";
	}
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request) {
		return "/index";
	}
	@RequestMapping("/toCarList")
	public String toCarList(HttpServletRequest request) {
		return "/car/carList";
	}
}
