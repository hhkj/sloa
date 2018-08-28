/**
 * 
 */
package cn.slkj.sloa.Controllers.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.sloa.Entity.system.Role;
import cn.slkj.sloa.Service.impl.RoleServiceImpl;
import cn.slkj.sloa.util.easyuiUtil.EPager;
import org.apache.commons.lang.StringUtils;
/**
 * @author maxh
 * @ClassName : RoleController
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月7日 下午5:42:24
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private RoleServiceImpl serviceImpl;

	@RequestMapping("/toRolePage")
	public String toRolePage() {
		return "/system/role";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EPager<Role> getAll(HttpServletRequest request) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		PageBounds pageBounds = new PageBounds(page, rows);
		List<Role> list = serviceImpl.getAll(pageMap, pageBounds);
		PageList<Role> pageList = (PageList<Role>) list;
		return new EPager<Role>(pageList.getPaginator().getTotalCount(), list);
	}

	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Role> queryAll(HttpServletRequest request) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Role> list = serviceImpl.queryAll(pageMap);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/queryOne")
	public Role queryOne(String id) {
		return serviceImpl.queryByid(id);
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public boolean save(Role role) throws Exception {
		try {
			int i = serviceImpl.saveRole(role);
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

	@ResponseBody
	@RequestMapping(value = "/edit")
	public boolean edit(Role role) throws Exception {
		try {
			int i = serviceImpl.editRole(role);
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

	@ResponseBody
	@RequestMapping(value = "/delete")
	public boolean deletes(String id) {
		int i = serviceImpl.deleteRole(id);
		try {
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

	/**
	 * 保存角色和资源之间的关系
	 * 
	 * @param member
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRoleRes", method = RequestMethod.POST)
	private boolean intoRole(@RequestParam(required = false, defaultValue = "") String roleid, @RequestParam(value = "ids[]") String[] ids) {
		if (StringUtils.isNotBlank(roleid)) {
			serviceImpl.deleteRoleRes(roleid, ids);
			int i = serviceImpl.saveRoleRes(roleid, ids);
			if (i != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存角色 菜单 下操作按钮
	 * 
	 * @param roleid
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRolePer", method = RequestMethod.POST)
	private boolean saveRolePer(@RequestParam(required = false, defaultValue = "") String roleid, @RequestParam(required = false, defaultValue = "") String modlueid, @RequestParam(value = "ids[]") String[] ids) {
		if (StringUtils.isNotBlank(roleid)) {
			serviceImpl.deleteRolePer(roleid, modlueid, ids);
			int i = serviceImpl.saveRolePer(roleid, modlueid, ids);
			if (i != -1) {
				return true;
			}
		}
		return false;
	}
}
