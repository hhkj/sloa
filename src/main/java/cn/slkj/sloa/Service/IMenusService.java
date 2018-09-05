package cn.slkj.sloa.Service;

import java.util.HashMap;
import java.util.List;

import cn.slkj.sloa.Entity.system.Menus;

public interface IMenusService {
	List<Menus> queryAll(HashMap<String, Object> hashMap);

	public int insert(Menus menus);

	public Menus queryOne(String id);

	public int update(Menus menus);

	public int delete(String id);

	public List<Menus> getModuleByRoleId(HashMap<String, Object> map);

	public List<Menus> getModuleByUserId(HashMap<String, Object> map);

	public int saveUserRes(HashMap<String, Object> map);

	public List<Menus> getRolePer(HashMap<String, Object> map);
}
