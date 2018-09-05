package cn.slkj.sloa.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.sloa.Entity.system.User;

/**
 * 记录信息:</br>
 * 访问时间</br>
 * Controller路径</br>
 * 对应方法名</br>
 * 请求参数信息</br>
 * 请求相对路径</br>
 * 请求处理时长
 * 
 * @author Administrator
 * 
 */
public class TimeCostInterceptor implements HandlerInterceptor {
	public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除
	public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }
	// before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);

			sb.append("-----------------------").append(new Date()).append("-------------------------------------\n");
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
			sb.append("URI       : ").append(request.getRequestURI()).append("\n");
			System.out.println(sb.toString());
		}
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
		if(null != allowUrls && allowUrls.length>=1) {
            for(String url : allowUrls) {  
                if(requestUrl.contains(url)) {
                    return true;  
                }  
            }
		}
		//获取登录session
        User user =(User) request.getSession().getAttribute("sessionUser");
        //如果访问请求为首页，则通过
        if(request.getRequestURI().startsWith(request.getContextPath()+"/toLogin")){
            return true;
        }else if(null==user){
            //如果没有登录session，则返回到首页
            response.sendRedirect(request.getContextPath()+"/toLogin");
            return false;
        }
        return true;
	}

	// after the handler is executed
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
			sb.append("-------------------------------------------------------------------------------");
			System.out.println(sb.toString());
		}
	}

	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("\t");
			}
		}
		return sb.toString();
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}
}