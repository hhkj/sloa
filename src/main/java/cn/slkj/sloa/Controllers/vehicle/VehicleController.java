package cn.slkj.sloa.Controllers.vehicle;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import cn.slkj.hbsl.util.javaUtil.StringUtil;
import cn.slkj.sloa.Entity.Vehicle;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.impl.VechicleServiceImpl;
import cn.slkj.sloa.util.Const;
import cn.slkj.sloa.util.easyuiUtil.EPager;
import cn.slkj.sloa.util.easyuiUtil.JsonResult;

/**
 * 
 * @author maxh
 * @ClassName : VehicleController
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月17日 上午9:14:17
 */
@Controller
@RequestMapping("/vehicle")
public class VehicleController {
	@Resource
	private VechicleServiceImpl vehicleService;

	@RequestMapping("/toVehicleListPage")
	public String toLogin(HttpServletRequest request) {
		return "/car/carList";
	}
	@RequestMapping("/vehicleAdd")
	public String toAddPage() {
		return "/car/vehicleForm";
	}
	@RequestMapping("/vehicleInfo")
	public String toInfoPage(HttpServletRequest request) {
		return "/car/carInfo";
	}
	@RequestMapping("/vehicleEdit")
	public String toEdiPage(HttpServletRequest request) {
		return "/car/carEdit";
	}
	/**
	 * 查询车辆列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<Vehicle> getList(HttpServletRequest request, HttpSession session, @RequestParam(required = false, defaultValue = "1") Integer page, // 第几页
			@RequestParam(required = false, defaultValue = "15") Integer rows) {
		String carNumber = request.getParameter("carNumber");
		String companyName = request.getParameter("companyName");
		String equitment = request.getParameter("equitment");
		String simNumber = request.getParameter("simNumber");
		String carOwner = request.getParameter("carOwner");
		String seasonExamE1 = request.getParameter("seasonExamE1");
		String seasonExamE2 = request.getParameter("seasonExamE2");
		String yearExamE1 = request.getParameter("yearExamE1");
		String yearExamE2 = request.getParameter("yearExamE2");
		String carVin = request.getParameter("carVin");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("carNumber", carNumber);
		hashMap.put("companyName", companyName);
		hashMap.put("equitment", equitment);
		hashMap.put("simNumber", simNumber);
		hashMap.put("carOwner", carOwner);
		hashMap.put("seasonExamE1", seasonExamE1);
		hashMap.put("seasonExamE2", seasonExamE2);
		hashMap.put("yearExamE1", yearExamE1);
		hashMap.put("yearExamE2", yearExamE2);
		hashMap.put("carVin", carVin);
		User u = (User) session.getAttribute(Const.SESSION_USER);
//		String depId = u.getDepartcode();
//		hashMap.put("depId", depId);

		// 排序
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String sortString = "";

		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sortString = sort + "." + order;
		}
		if (StringUtil.isEmpty(sortString)) {
			sortString = "keeptime.desc";
		}

		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Vehicle> list = vehicleService.getList(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Vehicle>(pageList.getPaginator().getTotalCount(), list);
	}

	/**
	 * 查询单条信息
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOne", method = { RequestMethod.POST })
	public Vehicle queryOne(String id) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("id", id);
		Vehicle vehicle = vehicleService.queryOne(hashMap);
		return vehicle;
	}

	/**
	 * 保存车辆信息
	 * 
	 * @param vehicle
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonResult save(Vehicle vehicle, HttpServletRequest request) {
		try {
			int i = -1;
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute(Const.SESSION_USER);
			String depId = u.getDepartcode();
			if (StringUtil.isEmpty(depId)) {
				depId = "5";
			}
			vehicle.setDepId(Integer.parseInt(depId));
			vehicle.setId(UuidUtil.get32UUID());
			i = vehicleService.save(vehicle);
			if (i != -1) {
				return new JsonResult(true, "添加成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "系统异常，添加失败！");
		}
		return new JsonResult(false, "添加失败！");
	}

	/**
	 * 编辑车辆信息
	 * 
	 * @param vehicle
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public JsonResult editVehicle(Vehicle vehicle) {
		try {
			int i = -1;
			i = vehicleService.edit(vehicle);
			if (i != -1) {
				return new JsonResult(true, "操作成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "操作异常！");
		}
		return new JsonResult(false, "操作失败！");
	}

	/**
	 * 编辑车辆季审信息
	 * 
	 * @param vehicle
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editSeasonExam", method = RequestMethod.POST)
	public JsonResult editSeasonExam(Vehicle vehicle) {
		try {
			int i = -1;
			i = vehicleService.editSeasonExam(vehicle);
			if (i != -1) {
				return new JsonResult(true, "操作成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "操作异常！");
		}
		return new JsonResult(false, "操作失败！");
	}

	/**
	 * 编辑车辆年审信息
	 * 
	 * @param vehicle
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editYearExam", method = RequestMethod.POST)
	public JsonResult editYearExam(Vehicle vehicle) {
		try {
			int i = -1;
			i = vehicleService.editYearExam(vehicle);
			if (i != -1) {
				return new JsonResult(true, "操作成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "操作异常！");
		}
		return new JsonResult(false, "操作失败！");
	}

	/**
	 * 删除车辆信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonResult deletes(String id) {
		int i = vehicleService.delete(id);
		try {
			if (i > 0) {
				return new JsonResult(true, "删除成功。");
			} else {
				return new JsonResult(false, "删除失败。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "操作异常。");
		}
	}

	/** 编辑保存 */
	@ResponseBody
	@RequestMapping(value = "/sfbz")
	public boolean sfbz(Vehicle vehicle) throws Exception {
		try {
			int i = vehicleService.sfbz(vehicle);
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
