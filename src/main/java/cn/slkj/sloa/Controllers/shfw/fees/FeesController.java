package cn.slkj.sloa.Controllers.shfw.fees;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import cn.slkj.sloa.Entity.Vehicle;
import cn.slkj.sloa.Entity.shfw.Fees;
import cn.slkj.sloa.Entity.shfw.PayFees;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.impl.FeesServiceImpl;
import cn.slkj.sloa.util.Const;
import cn.slkj.sloa.util.easyuiUtil.EPager;
import cn.slkj.sloa.util.easyuiUtil.JsonResult;

@Controller
@RequestMapping("/fees")
public class FeesController {

	@Autowired
	private FeesServiceImpl feesService;

	@RequestMapping("/toFeesPage")
	public String toFeesPage() {
		return "/shfw/feesList";
	}
	@RequestMapping("/toFeesAddPages")
	public String toFeesAddPages() {
		return "/shfw/feesForm";
	}
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public @ResponseBody EPager<Fees> list(HttpServletRequest request) {
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String depid = request.getParameter("department");
		if (StringUtil.isEmpty(depid)) {
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute(Const.SESSION_USER);
			depid = u.getDepartcode();
		}
		pageMap.put("depId", depid);
		pageMap.put("carNumber", request.getParameter("carNumber"));
		pageMap.put("endTime", request.getParameter("endTime"));
		pageMap.put("endTime1", request.getParameter("endTime1"));
		pageMap.put("purpose", request.getParameter("purpose"));
		pageMap.put("fees", request.getParameter("fees"));
		pageMap.put("number", request.getParameter("number"));

		pageMap.put("riqi", request.getParameter("riqi"));
		pageMap.put("riqi1", request.getParameter("riqi1"));

		// 排序
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String sortString = "";
		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sortString = sort + "." + order;
		}
		if (StringUtil.isEmpty(sortString)) {
			sortString = "riqi.desc";
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));

		List<Fees> list = feesService.listByCarNumber(pageMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Fees>(pageList.getPaginator().getTotalCount(), list);
	}

	/**
	 * 根据车牌号查询缴费记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listByCarNumber", method = { RequestMethod.POST })
	public @ResponseBody EPager<Fees> listByCarNumber(HttpServletRequest request) {
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		pageMap.put("carNumber", request.getParameter("carNumber"));
		// 排序
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String sortString = "";
		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sortString = sort + "." + order;
		}
		if (StringUtil.isEmpty(sortString)) {
			sortString = "endTime.desc";
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));

		List<Fees> list = feesService.listByCarNumber(pageMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Fees>(pageList.getPaginator().getTotalCount(), list);
	}

	/**
	 * 查询单条信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getFeesByCarNum")
	public Vehicle getByCarNum(String carNum) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("carNumber", carNum);
		Vehicle vehicle = feesService.getFeesByCarNum(hashMap);
		return vehicle;
	}

	/** 确认收款 */
	@ResponseBody
	@RequestMapping(value = "/fees")
	public JsonResult fees(@RequestParam String id, @RequestParam String amounts, @RequestParam String sriqi, @RequestParam String payee, HttpServletRequest request) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			// User user = (User)
			// request.getSession().getAttribute(SystemConstants.SESSION_USER);
			// map.put("payee", user.getRealname());
			map.put("payee", payee);
			map.put("amounts", amounts);
			map.put("sriqi", sriqi);
			int i = feesService.fees(map);
			if (i != -1) {
				return new JsonResult(true, "确认收款 ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "失败！");
	}

	/** 服务费收取 */
	@ResponseBody
	@RequestMapping(value = "/pay")
	public JsonResult pay(String caruid, String carNumber, String owner, String purpose, String number, String proverId, String startTime, String endTime, String colAmounts,
			String riqi, String remark, String inspector, String deviceNumber, String devicePice, String simNumber, String simPice, String upkeep, String upkeepPice,
			HttpServletRequest request) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", UUIDUtils.uuid());
			map.put("caruid", caruid);
			map.put("carNumber", carNumber);
			map.put("owner", owner);
			map.put("purpose", purpose);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("colAmounts", colAmounts);
			map.put("riqi", riqi);
			map.put("remark", remark);
			map.put("inspector", inspector);
			map.put("number", number);
			map.put("proverId", proverId);

			map.put("deviceNumber", deviceNumber);
			map.put("devicePice", devicePice);
			map.put("simNumber", simNumber);
			map.put("simPice", simPice);
			map.put("upkeep", upkeep);
			map.put("upkeepPice", upkeepPice);

			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			map.put("agent", user.getRealname());
			map.put("depCode", user.getDepartcode());
			map.put("depName", user.getDepartName());
			int i = feesService.pay(map);
			if (i != -1) {
				// feesService.updateVehicle(map);
				if (number.length() > 0) {
					feesService.billing(map);
				}

				return new JsonResult(true, "OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "失败！");
	}

	/* 收费记录 */
	@RequestMapping(value = "/listPayFees", method = { RequestMethod.POST })
	public @ResponseBody EPager<PayFees> listPayFees(HttpServletRequest request) {
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));

		pageMap.put("carNumber", request.getParameter("carNumber"));
		pageMap.put("sfriqi", request.getParameter("sfriqi"));
		pageMap.put("sfriqi1", request.getParameter("sfriqi1"));

		// 排序
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String sortString = "";
		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sortString = sort + "." + order;
		}
		if (StringUtil.isEmpty(sortString)) {
			sortString = "initriqi.desc";
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<PayFees> list = feesService.listPayFees(pageMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<PayFees>(pageList.getPaginator().getTotalCount(), list);
	}

	/** 服务费收取 */
	@ResponseBody
	@RequestMapping(value = "/payFees")
	public JsonResult payFees(String carNumber, String owner, String fees, String sfry, String sfriqi, String startTime, String endTime, String purpose, String companyName,
			String remark, String ownerTel, HttpServletRequest request) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("carNumber", carNumber);
			map.put("owner", owner);
			map.put("fees", fees);
			map.put("sfry", sfry);
			map.put("sfriqi", sfriqi);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("purpose", purpose);
			map.put("remark", remark);
			map.put("companyName", companyName);
			map.put("ownerTel", ownerTel);

			int i = feesService.payFees(map);
			if (i != -1) {
				return new JsonResult(true, "OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "失败！");
		}
		return new JsonResult(false, "失败！");
	}
}
