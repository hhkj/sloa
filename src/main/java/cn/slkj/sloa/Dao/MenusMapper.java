/**
 * 
 */
package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;

import cn.slkj.sloa.Entity.system.Menus;

/**
 * @author maxh
 * @ClassName : MenusMapper
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月9日 下午4:38:34
 */
public interface MenusMapper {
	List<Menus> queryAll(HashMap<String, Object> hashMap);

	int insert(Menus menus);

	public int update(Menus menus);

	public int delete(String id);

	public Menus queryOne(String id);

	public List<Menus> getModuleByUserId(HashMap<String, Object> map);

	public int saveUserRes(HashMap<String, Object> map);

	public List<Menus> getModuleByRoleId(HashMap<String, Object> map);

	public List<Menus> getRolePer(HashMap<String, Object> map);
}
