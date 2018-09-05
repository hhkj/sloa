package cn.slkj.sloa.Controllers.stock;

import java.util.Date;
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
import cn.slkj.hbsl.util.javaUtil.date.DateStyle;
import cn.slkj.hbsl.util.javaUtil.date.DateUtil;
import cn.slkj.sloa.Entity.stock.Prove;
import cn.slkj.sloa.Entity.stock.Prove_record;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.impl.ProveServiceImpl;
import cn.slkj.sloa.util.Const;
import cn.slkj.sloa.util.easyuiUtil.EPager;
import cn.slkj.sloa.util.easyuiUtil.JsonResult;

@Controller
@RequestMapping("/prove")
public class ProveController {
	@Autowired
	private ProveServiceImpl proveService;


	@RequestMapping("/toProvePage")
	public String toProvePage() {
		return "/stock/prove/prove";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EPager<Prove> getAll(HttpServletRequest request) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		pageMap.put("number", request.getParameter("number"));
		String department = request.getParameter("department");

		// String state = request.getParameter("state");
		// 获取用户部门编码
		if (StringUtil.isEmpty(department)) {
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			department = user.getDepartcode();
			if ("38".equals(department)) {
				department = "5";
			}
		}
		pageMap.put("state", request.getParameter("state"));
		pageMap.put("department", department);
		pageMap.put("carNumber", request.getParameter("carNumber"));
		pageMap.put("usman", request.getParameter("usman"));
		pageMap.put("startTime", request.getParameter("startTime"));
		pageMap.put("endTime", request.getParameter("endTime"));
		pageMap.put("ustate", request.getParameter("ustate"));
		pageMap.put("ustype", request.getParameter("ustype"));
		// 排序
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String sortString = "";
		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sortString = sort + "." + order;
		}
		if (StringUtil.isEmpty(sortString)) {
			sortString = "number.asc";
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Prove> list = proveService.getAll(pageMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Prove>(pageList.getPaginator().getTotalCount(), list);

	}

	/**
	 * 获取实体
	 * 
	 * @param insurance
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOne")
	public Prove queryOne(Prove prove) {
		return proveService.queryOne(prove);
	}

	/** 添加 */
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public JsonResult save(Prove prove) {
		try {
			int i = 0;
			if (prove.getAddType() == 1) {
				int begin = Integer.parseInt(prove.getListnum_begin());
				int end = Integer.parseInt(prove.getListnum_end());
				for (int j = begin; j <= end; j++) {
					StringBuilder sb = new StringBuilder();
					sb.append(prove.getListNo());
					// 0 代表前面补充0
					// 4 代表长度为4
					// d 代表参数为正数型
					String str = String.format("%04d", j);
					sb.append(str);
					prove.setNumber(sb.toString());
					prove.setId(UUIDUtils.uuid());
					i = proveService.save(prove);
				}
			} else {
				prove.setId(UUIDUtils.uuid());
				prove.setNumber(prove.getListNo());
				i = proveService.save(prove);
			}
			if (i != -1) {
				return new JsonResult(true, "保存成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "保存失败！");
	}

	/** 编辑 */
	@ResponseBody
	@RequestMapping(value = "/editSave", method = { RequestMethod.POST })
	public JsonResult editSave(Prove prove) {
		try {
			prove.setId(prove.getId());
			prove.setUstate(prove.getUstate());
			prove.setUstype(prove.getUstype());
			prove.setNumber(prove.getNumber());
			prove.setState(prove.getState());
			prove.setUsriqi(prove.getUsriqi());
			prove.setDepartment(prove.getDepartment());
			prove.setUsman(prove.getUsman());
			prove.setPurpose(prove.getPurpose());
			prove.setCarNumber(prove.getCarNumber());
			prove.setRemark(prove.getRemark());
			prove.setFees(prove.getFees());
			prove.setPayee(prove.getPayee());
			int i = proveService.edit(prove);
			if (i != -1) {
				return new JsonResult(true, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "编辑失败！");
	}

	/** 使用 */
	@ResponseBody
	@RequestMapping(value = "/useSave")
	public boolean useSave(@RequestParam(value = "ids[]") String[] ids, String site, String state, String usman, String usriqi, String remark, String department) {
		// try {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("site", site);
		map.put("state", state);
		map.put("ids", ids);
		map.put("usman", usman);
		map.put("usriqi", usriqi);
		map.put("remark", remark);
		map.put("department", department);
		int i = proveService.use(map);
		if (i > 0) {
			// 保存出入库记录
			proveService.insert_recordList(map);
			return true;
		} else {
			return false;
		}
		/*
		 * if (i != -1) { return new JsonResult(true, ""); } } catch (Exception e) {
		 * e.printStackTrace(); } return new JsonResult(false, "编辑失败！");
		 */
	}

	/** 确认收款 */
	@ResponseBody
	@RequestMapping(value = "/fees")
	public JsonResult fees(@RequestParam String id, @RequestParam String amounts, HttpServletRequest request) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);

			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			map.put("payee", user.getRealname());
			map.put("amounts", amounts);

			int i = proveService.fees(map);
			if (i != -1) {
				return new JsonResult(true, "确认收款 ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "失败！");
	}

	@ResponseBody
	@RequestMapping(value = "/voidSave", method = { RequestMethod.POST })
	public JsonResult voidSave(@RequestParam(value = "ids[]") String[] ids, Integer state) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids);
			map.put("state", state);
			map.put("recycleTime", DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD));
			int i = proveService.tovoid(map);
			if (i != -1) {
				return new JsonResult(true, "");
			}
		} catch (Exception e) {
			throw new Exception("this is the detail of ajax exception information");
		}
		return new JsonResult(false, "编辑失败！");
	}

	@ResponseBody
	@RequestMapping(value = "/canceled", method = { RequestMethod.POST })
	public JsonResult canceled(@RequestParam(value = "ids[]") String[] ids, Integer state) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids);
			map.put("ustate", state);
			int i = proveService.canceled(map);
			if (i != -1) {
				return new JsonResult(true, "");
			}
		} catch (Exception e) {
			throw new Exception("this is the detail of ajax exception information");
		}
		return new JsonResult(false, "编辑失败！");
	}

	/** 使用 */
	@ResponseBody
	@RequestMapping(value = "/billing", method = { RequestMethod.POST })
	public String billing(String id, String purpose, String carNumber, String riqi, String remark, String inspector, String startTime, String endTime, String colAmounts,
			String owner, String ownerId) {
		try {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("inspector", inspector);
			map.put("purpose", purpose);
			map.put("carNumber", carNumber);
			map.put("riqi", riqi);
			map.put("remark", remark);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("colAmounts", colAmounts);
			map.put("owner", owner);
			map.put("ownerId", ownerId);
			if (ownerId == "" && ownerId.length() == 0) {
				return "<script>window.top.SL.msgShow('提示', '业户名称不存在，请先添加业户单位信息', 'warning');</script>";
			}
			int i = proveService.billing(map);
			if (i != -1) {
				return "<script>window.parent.backPage();</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "<script>window.top.SL.msgShow('提示', '系统异常，操作失败', 'warning');</script>";
	}

	/** 单条删除 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonResult delete(String id) {
		int i = proveService.delete(id);
		if (i > 0) {
			return new JsonResult(true, "");
		}
		return new JsonResult(false, "删除失败！");
	}

	/**
	 * 撤回数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/revoke")
	@ResponseBody
	public boolean revoke(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("department", user.getDepartcode());
		int i = proveService.revoke(map);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询设备出口记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getListRecd")
	@ResponseBody
	public List<Prove_record> getListRecd(HttpServletRequest request) {
		String listnum = request.getParameter("listnum");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listnum", listnum);
		return proveService.getListRecd(map);
	}

}
