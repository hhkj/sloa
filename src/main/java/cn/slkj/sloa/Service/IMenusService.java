package cn.slkj.sloa.Service;

import java.util.HashMap;
import java.util.List;

import cn.slkj.sloa.Entity.system.Menus;

public interface IMenusService {
	List<Menus> queryAll(HashMap<String, Object> hashMap);
	public int insert(Menus menus);
}
