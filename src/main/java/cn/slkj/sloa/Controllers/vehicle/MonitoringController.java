package cn.slkj.sloa.Controllers.vehicle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
*  @author  maxh  
*  @ClassName  : MonitoringController  
*  @Version  版本   
*  @Copyright  神龙科技  
*  @date  2018年8月25日 下午6:03:47
 */
@Controller
@RequestMapping("/Monitor")
public class MonitoringController {

	@RequestMapping("/toMonitorListPage")
	public String toLogin(HttpServletRequest request) {
		return "/car/jkrzList";
	}
}
