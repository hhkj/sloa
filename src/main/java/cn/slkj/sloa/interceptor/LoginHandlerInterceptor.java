package cn.slkj.sloa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.util.Const;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
//		if (path.matches(Const.NO_INTERCEPTOR_PATH) || path.isEmpty()) {
//			return true;
//		} else {
//			HttpSession session = request.getSession(true);
//			User user = (User) session.getAttribute(Const.SESSION_USER);
//			if (user == null) {
//				// 登陆过滤
//				response.sendRedirect(request.getContextPath() + Const.LOGIN);
//			}
//			return false;
//		}
		if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
			return true;
		}
		// intercept
		HttpSession session = request.getSession();
		if (session.getAttribute(Const.SESSION_USER) == null) {
//			throw new AuthorizationException();
			  response.sendRedirect(request.getContextPath() + Const.LOGIN);
              return false;  
		} else {
			return true;
		}
	}

}
