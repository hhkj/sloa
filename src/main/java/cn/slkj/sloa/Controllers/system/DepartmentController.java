package cn.slkj.sloa.Controllers.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.slkj.sloa.Entity.system.Department;
import cn.slkj.sloa.Service.IDepartmentService;
import cn.slkj.sloa.util.easyuiUtil.JsonResult;
import cn.slkj.sloa.util.easyuiUtil.Tree;


/**
 * 部门管理 控制层
 * 
 * @author maxh
 *
 */
@Controller
@RequestMapping(value = "/dep")
public class DepartmentController {
	private static Logger log = LoggerFactory.getLogger(DepartmentController.class);
	@Resource
	private IDepartmentService departmentService;
	/* 跳转页面 */
	@RequestMapping("/departmentPage")
	public String toUserPage() {
		return "/system/department";
	}
	/* 跳转页面 */
	@RequestMapping("/depInfo")
	public String toInfoPage() {
		return "/system/depInfo";
	}
	
	// 获取实体
	@ResponseBody
	@RequestMapping(value = "/queryOneDep")
	public Department queryOne(String id) {
		return departmentService.queryOne(id);
	}

	// / 保存
	@ResponseBody
	@RequestMapping(value = "/saveDep", method = { RequestMethod.POST })
	public JsonResult saveDep(Department department) throws Exception {
		try {
			int i = departmentService.save(department);
			if (i != -1) {
				return new JsonResult(true, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "保存失败！");
	}

	// 编辑
	@ResponseBody
	@RequestMapping(value = "/editDep")
	public JsonResult editDep(Department department) throws Exception {
		try {
			int i = departmentService.edit(department);
			if (i != -1) {
				return new JsonResult(true, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(false, "编辑失败！");
	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "/delDep")
	public JsonResult delDep(String id) {
		int i = departmentService.delete(id);
		if (i > 0) {
			return new JsonResult(true, "");
		}
		return new JsonResult(false, "删除失败！");
	}

	// 查询树
	@ResponseBody
	@RequestMapping(value = "/getDepTree")
	public List<Tree> getComTree(String id, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String dId = "";
		// 1、先获取用户属于哪个单位下 获取用户部门编码
		// User user = (User)
		// request.getSession().getAttribute(SystemConstants.SESSION_USER);
		// dId = user.getDepartcode();
		Department department = new Department();
		// 2、根据id查询到单位编码
		map.put("id", dId);
		department = departmentService.queryOne(dId);
		List<Department> list = departmentService.queryList(map);
		return toTree(list, "0");
	}

	// 组装树
	private List<Tree> toTree(List<Department> list, String code) {
		List<Tree> trees = new ArrayList<Tree>();
		for (Department c : list) {
			Tree t = new Tree();
			t.setId(c.getId() + "");
			t.setText(c.getD_name());
			t.setAttributes(c);
			if (code.equals(c.getParentid())) {
				t.setChildren(toTree(list, c.getId()));
				trees.add(t);
			}
		}
		return trees;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Department> list() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Department> list = departmentService.queryList(map);
		return makeTree(list);
	}

	/**
	 * 组装树形列表 treegrid
	 * 
	 * @param list
	 * @return
	 */
	private List<Department> makeTree(List<Department> list) {
		List<Department> parent = new ArrayList<Department>();
		for (Department e : list) {
			if (e.getParentid().equals("0")) {
				e.setChildren(new ArrayList<Department>(0));
				parent.add(e);
			}
		}
		list.removeAll(parent);
		makeChildren(parent, list);
		return parent;
	}

	private void makeChildren(List<Department> parent, List<Department> children) {
		if (children.isEmpty()) {
			return;
		}
		List<Department> tmp = new ArrayList<Department>();
		for (Department c1 : parent) {
			for (Department c2 : children) {
				c2.setChildren(new ArrayList<Department>(0));
				if (c1.getId().equals(c2.getParentid())) {
					c1.getChildren().add(c2);
					if (!c1.getParentid().equals("0")) {
						c1.setState("closed");
					}
					tmp.add(c2);
				}
			}
		}
		children.removeAll(tmp);
		makeChildren(tmp, children);
	}
}
