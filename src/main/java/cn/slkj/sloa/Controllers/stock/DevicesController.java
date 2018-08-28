package cn.slkj.sloa.Controllers.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.hbsl.util.javaUtil.StringUtil;
import cn.slkj.sloa.Entity.Devices;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.impl.DevicesServiceImpl;
import cn.slkj.sloa.util.Const;
import cn.slkj.sloa.util.easyuiUtil.EPager;

@Controller
@RequestMapping("/devices")
public class DevicesController {
	@Autowired
	private DevicesServiceImpl service;
	@RequestMapping("/toDevicesPage")
	public String toDevicesPage() {
		return "/stock/devices/devices";
	}

	/**
	 * 查询所有设备列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EPager<Devices> getAll(HttpServletRequest request) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		pageMap.put("rktime", request.getParameter("rktime"));
		pageMap.put("rktime1", request.getParameter("rktime1"));
		pageMap.put("lytime", request.getParameter("lytime"));
		pageMap.put("lytime1", request.getParameter("lytime1"));
		pageMap.put("fhtime", request.getParameter("fhtime"));
		pageMap.put("fhtime1", request.getParameter("fhtime1"));
		pageMap.put("state", request.getParameter("state"));
		pageMap.put("ustate", request.getParameter("ustate"));
		pageMap.put("lyr", request.getParameter("lyr"));
		pageMap.put("firm", request.getParameter("firm"));
		pageMap.put("listnum", request.getParameter("listnum"));
		pageMap.put("carNumber", request.getParameter("carNumber"));
		pageMap.put("model", request.getParameter("model"));
		pageMap.put("simNumber", request.getParameter("simNumber"));
		pageMap.put("inspector", request.getParameter("inspector"));
		String department = request.getParameter("department");
		// 获取用户部门编码
		if (StringUtil.isEmpty(department)) {
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			department = user.getDepartcode();
		}
		pageMap.put("department", department);
		pageMap.put("test", request.getParameter("test"));
		String packBm = request.getParameter("packBm");
		if (!StringUtil.isEmpty(packBm)) {
			String[] temp = packBm.trim().toUpperCase().split(",");
			pageMap.put("packBm", temp);
		}

		// 排序
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String sortString = "";

		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sortString = sort + "." + order;
		}
		if (StringUtil.isEmpty(sortString)) {
			sortString = "listnum.asc";
			// packbm.desc,cstime.desc,firm.asc,
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Devices> list = service.getAll(pageMap, pageBounds);
		PageList<Devices> pageList = (PageList<Devices>) list;
		return new EPager<Devices>(pageList.getPaginator().getTotalCount(), list);
	}
}
