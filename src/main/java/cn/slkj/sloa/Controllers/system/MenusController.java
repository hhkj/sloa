package cn.slkj.sloa.Controllers.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.slkj.sloa.Entity.system.MenuDTO;
import cn.slkj.sloa.Entity.system.Menus;
import cn.slkj.sloa.Entity.system.RunMain;
import cn.slkj.sloa.Service.impl.MenusServiceImpl;
import cn.slkj.sloa.util.Tree;
import cn.slkj.sloa.util.easyuiUtil.JsonResult;

/**
 * @author maxh
 * @ClassName : ModuleController
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月7日 下午5:42:24
 */
@Controller
@RequestMapping("/menus")
public class MenusController {
	@Resource
	private MenusServiceImpl menusServiceImpl;

	@RequestMapping("/toMenusListPage")
	public String toMenusListPage(HttpServletRequest request) {
		return "/system/resource";
	}

	/**
	 * 获取权限菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<MenuDTO> list() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Menus> menus = menusServiceImpl.queryAll(map);
		List<MenuDTO> menuDTOs = getAllMenu(menus);
		List<MenuDTO> root = treeMenu(menuDTOs, 0); // pid=0
		return root;
	}

	/**
	 * 获取菜单树形结构
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listTree")
	@ResponseBody
	public Object listTree() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Menus> menus = menusServiceImpl.queryAll(map);
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<Map<String, Object>> treeGridList = new ArrayList<Map<String, Object>>();
		treeGridList = createTreeGridTree(menus);
		return treeGridList;
	}

	/**
	 * 添加资源菜单
	 * 
	 * @param module
	 * @return
	 */
	@RequestMapping(value = "/addMenus", method = { RequestMethod.POST })
	@ResponseBody
	public JsonResult addModule(Menus menus) {
		Map<String, String> map = new HashMap<String, String>();
		int i = menusServiceImpl.insert(menus);
		if (i > 0) {
			return new JsonResult(true, "操作成功");
		} else {
			return new JsonResult(false, "操作失败！");
		}
	}

	@RequestMapping(value = "/getCombotree")
	@ResponseBody
	public List<Tree> getCombotree() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("priority", 3);
		List<Menus> list = menusServiceImpl.queryAll(map);
		return toTree(list, "0");
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryOne/{id}")
	@ResponseBody
	public Menus queryOne(@PathVariable String id) {
		return menusServiceImpl.queryOne(id);
	}

	/**
	 * 编辑菜单 or 按钮
	 * 
	 * @param module
	 * @return
	 */
	@RequestMapping(value = "/editModule", method = { RequestMethod.POST })
	@ResponseBody
	public boolean editModule(Menus menus) {
		int i = menusServiceImpl.update(menus);
		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 删除菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delModule")
	@ResponseBody
	public boolean delModule(String id) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int i = menusServiceImpl.delete(id);
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/* -------------------------------- */

	private List<Tree> toTree(List<Menus> list, String code) {
		List<Tree> trees = new ArrayList<Tree>();
		for (Menus m : list) {
			Tree t = new Tree();
			t.setId(m.getMenuid() + "");
			t.setText(m.getMenuname());
			if (code.equals(m.getParentMenu() + "")) {
				t.setChildren(toTree(list, m.getMenuid() + ""));
				trees.add(t);
			}
		}
		return trees;
	}

	// 构造成MenuDTO数据结构
	public static List<MenuDTO> getAllMenu(List<Menus> list) {
		List<MenuDTO> result = new ArrayList<>();
		for (Menus item : list) {
			MenuDTO menuDTO = new MenuDTO();
			menuDTO.setMenuid(item.getMenuid());
			menuDTO.setParentMenu(item.getParentMenu());
			menuDTO.setIcon(item.getIcon());
			menuDTO.setMenuname(item.getMenuname());
			menuDTO.setUrl(item.getUrl());
			menuDTO.setMenus(null);
			result.add(menuDTO);
		}
		return result;
	}

	/**
	 * 将资源封装成资源树
	 * 
	 * @param list
	 * @param parentId
	 */
	private List<Map<String, Object>> createTreeGridTree(List<Menus> list) {
		// 存放转换后的资源树
		List<Map<String, Object>> treeGridList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = null;
			Menus menus = list.get(i);
			if (menus.getParentMenu().equals(0)) {
				map = new HashMap<String, Object>();
				map.put("id", menus.getMenuid());
				map.put("name", menus.getMenuname());
				map.put("url", menus.getUrl());
				map.put("icon", menus.getIcon());
				map.put("children", createTreeGridChildren(list, menus.getMenuid()));
			}
			if (map != null) {
				treeGridList.add(map);
			}
		}
		return treeGridList;
	}

	/**
	 * 递归设置资源树
	 * 
	 * @param list
	 * @param parentId
	 * @return childList
	 */
	private List<Map<String, Object>> createTreeGridChildren(List<Menus> list, int parentId) {
		List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = null;
			Menus treeChild = list.get(j);
			if (treeChild.getParentMenu().equals(parentId)) {
				map = new HashMap<String, Object>();
				map.put("id", treeChild.getMenuid());
				map.put("name", treeChild.getMenuname());
				map.put("url", treeChild.getUrl());
				map.put("icon", treeChild.getIcon());
				map.put("children", createTreeGridChildren(list, treeChild.getMenuid()));
			}

			if (map != null) {
				childList.add(map);
			}
		}
		return childList;
	}

	/**
	 * 作用:遍历树形菜单得到所有的id
	 * 
	 * @param root
	 * @param result
	 * @return
	 */
	// 前序遍历得到所有的id List
	private static List<Integer> ergodicList(List<MenuDTO> root, List<Integer> result) {
		for (int i = 0; i < root.size(); i++) {
			// 查询某节点的子节点（获取的是list）
			result.add(root.get(i).getMenuid());// 前序遍历
			if (null != root.get(i).getMenus()) {
				List<MenuDTO> children = root.get(i).getMenus();
				ergodicList(children, result);
			}
			// result.add(root.get(i).getId());//后序遍历
		}
		return result;
	}

	public static List<MenuDTO> ergodicTrees(List<MenuDTO> root) throws Exception {
		for (int i = 0; i < root.size(); i++) {
			// 查询某节点的子节点（获取的是list）
			List<MenuDTO> children = new ArrayList<MenuDTO>();
			if (null != root.get(i).getMenus()) {
				children = root.get(i).getMenus();
			}
			ergodicTrees(children);
		}
		return root;
	}

	/**
	 * 
	 * @param list
	 *            MenuDTO数据
	 * @param pid
	 *            父id
	 * @return 把所有list MenuDTO设置子节点
	 * @throws Exception
	 */
	public static List<MenuDTO> treeMenu(List<MenuDTO> list, Integer pid) throws Exception {
		List<MenuDTO> childList = new ArrayList<MenuDTO>();
		for (MenuDTO item : list) {
			if (item != null) {
				// 判断当前节点的父节点是否是pid
				if (pid.equals(item.getParentMenu())) {
					List<MenuDTO> child = treeMenu(list, item.getMenuid());
					item.setMenus(child);
					childList.add(item);
				}
			}
		}
		// return ergodicTrees(childList);
		return childList;

	}

}
