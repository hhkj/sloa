/**
 * 
 */
package cn.slkj.sloa.Service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.slkj.sloa.Dao.MenusMapper;
import cn.slkj.sloa.Entity.system.Menus;
import cn.slkj.sloa.Service.IMenusService;

/**
 * @author maxh
 * @ClassName : MenusServiceImpl
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月9日 下午4:37:57
 */
@Service
public class MenusServiceImpl implements IMenusService {
	@Resource
	private MenusMapper menusMapper;

	@Override
	public List<Menus> queryAll(HashMap<String, Object> hashMap) {
		return menusMapper.queryAll(hashMap);
	}

	@Override
	public Menus queryOne(String id) {
		return menusMapper.queryOne(id);
	}

	@Override
	public int insert(Menus menus) {

		return menusMapper.insert(menus);
	}

	@Override
	public int update(Menus menus) {
		try {
			return menusMapper.update(menus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		try {
			return menusMapper.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Menus> getModuleByRoleId(HashMap<String, Object> map) {
		return menusMapper.getModuleByRoleId(map);
	}

	@Override
	public List<Menus> getModuleByUserId(HashMap<String, Object> map) {
		return menusMapper.getModuleByUserId(map);
	}

	@Override
	public int saveUserRes(HashMap<String, Object> map) {
		return menusMapper.saveUserRes(map);
	}

	@Override
	public List<Menus> getRolePer(HashMap<String, Object> map) {
		return menusMapper.getRolePer(map);
	}

}
