package cn.slkj.sloa.Controllers.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.hbsl.util.javaUtil.StringUtil;
import cn.slkj.hbsl.util.javaUtil.UUIDUtils;
import cn.slkj.sloa.Entity.stock.Sim;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.impl.SimServiceImpl;
import cn.slkj.sloa.util.Const;
import cn.slkj.sloa.util.easyuiUtil.EPager;

@Controller
@RequestMapping("/sim")
public class SimController {
	@Autowired
	private SimServiceImpl simService;
	@RequestMapping("/toSimPage")
	public String toSimPage() {
		return "/stock/sim/sim";
	}
	 
	@RequestMapping("/list")
	@ResponseBody
	public EPager<Sim> getAllUsers(HttpServletRequest request) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> pageMap = new HashMap<String, Object>();

		pageMap.put("telnum", request.getParameter("telnum"));
		pageMap.put("state", request.getParameter("state"));
		pageMap.put("ustate", request.getParameter("ustate"));
		pageMap.put("lyr", request.getParameter("lyr"));

		String department = request.getParameter("department");
		// 获取用户部门编码
		if (StringUtil.isEmpty(department)) {
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			department = user.getDepartcode();
		}
		/*
		 * //如果是总公司编码=5的不添加条件 if ("5".equals(department)) { department = ""; }
		 */
		pageMap.put("department", department);

		String sortString = "Intime.desc,telnum.asc";
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Sim> list = simService.getAll(pageMap, pageBounds);
		PageList<Sim> pageList = (PageList<Sim>) list;
		return new EPager<Sim>(pageList.getPaginator().getTotalCount(), list);
	}

	@RequestMapping("/getList")
	@ResponseBody
	public List<Sim> getList(HttpServletRequest request) throws Exception {
		String telnum = request.getParameter("telnum");
		Map<String, Object> pageMap = new HashMap<String, Object>();
		if (!"null".equals(telnum)) {
			pageMap.put("telnum", telnum);
		}
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		pageMap.put("department", user.getDepartcode());

		return simService.getList(pageMap);
	}

	@RequestMapping(value = "/addSim", method = { RequestMethod.POST })
	@ResponseBody
	public boolean addSim(Sim sim) {
		int i = 0;
		if (sim.getAddType() == 1) {
			int begin = Integer.parseInt(sim.getListnum_begin());
			int end = Integer.parseInt(sim.getListnum_end());
			for (int j = begin; j <= end; j++) {
				sim.setId(UUIDUtils.uuid());
				sim.setListnum("");
				StringBuilder sb = new StringBuilder();
				sb.append(sim.getListNo());
				// 0 代表前面补充0
				// 4 代表长度为4
				// d 代表参数为正数型
				String str = String.format("%04d", j);
				sb.append(str);
				sim.setTelnum(sb.toString());
				i = simService.insert(sim);
			}
		} else {
			sim.setId(UUIDUtils.uuid());
			i = simService.insert(sim);
		}

		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/editSim", method = { RequestMethod.POST })
	@ResponseBody
	public boolean editSim(Sim sim) {
		sim.setId(sim.getId());
		sim.setUstate(sim.getUstate());
		sim.setState(sim.getState());
		sim.setTelnum(sim.getTelnum());
		sim.setLyr(sim.getLyr());
		sim.setDepartment(sim.getDepartment());
		sim.setOuttime(sim.getOuttime());
		sim.setLrr(sim.getLrr());
		sim.setRemark(sim.getRemark());
		int i = simService.edit(sim);
		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/deletes")
	@ResponseBody
	public boolean deletes(@RequestParam(value = "ids[]") String[] ids) {
		int i = simService.deletes(ids);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: useSave
	 * @Description:出库
	 * @param ids
	 * @param department
	 * @param lyr
	 * @param outtime
	 * @param remark
	 * @return JsonResult
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/useSave")
	public boolean useSave(@RequestParam(value = "ids[]") String[] ids, String site, String ustate, String department, String lyr, String outtime, String remark) {
		// try {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("site", site);
		map.put("ustate", ustate);
		map.put("ids", ids);
		map.put("lyr", lyr);
		map.put("outtime", outtime);
		map.put("remark", remark);
		map.put("department", department);
		int i = simService.use(map);
		if (i > 0) {
			// 保存出入库记录
			// simService.insert_recordList(map);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/simPay", method = { RequestMethod.POST })
	@ResponseBody
	public boolean simPay(Sim sim) {
		int i = simService.editPay(sim);
		if (i > 0) {
			simService.simPay(sim);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/simPaylist")
	@ResponseBody
	public EPager<Sim> simPaylist(HttpServletRequest request) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("telnum", request.getParameter("telnum"));

		String sortString = "renewtime.desc";
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Sim> list = simService.simPaylist(pageMap, pageBounds);
		PageList<Sim> pageList = (PageList<Sim>) list;
		return new EPager<Sim>(pageList.getPaginator().getTotalCount(), list);
	}
	
	@RequestMapping(value = "/revoke")
	@ResponseBody
	public boolean revoke(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("department", user.getDepartcode());
		int i = simService.revoke(map);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

}
