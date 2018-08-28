package cn.slkj.sloa.Service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Dao.RoleMapper;
import cn.slkj.sloa.Entity.system.Role;
import cn.slkj.sloa.Service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper mapper;

	@Override
	public List<Role> getAll(Map<String, Object> map, PageBounds pageBounds) {
		return mapper.getAll(map, pageBounds);
	}

	@Override
	public Role queryByid(String id) {
		return mapper.queryByid(id);
	}

	@Override
	public int editRole(Role role) {
		return mapper.editRole(role);
	}

	@Override
	public int saveRole(Role role) {
		return mapper.saveRole(role);
	}

	@Override
	public int deleteRole(String id) {
		return mapper.deleteRole(id);
	}

	@Override
	public int saveRoleRes(String roleid, String[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleid);
		map.put("ids", ids);
		return mapper.saveRoleRes(map);
	}

	@Override
	public int deleteRoleRes(String roleid, String[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleid);
		map.put("ids", ids);
		return mapper.deleteRoleRes(map);
	}

	@Override
	public List<Role> queryAll(Map<String, Object> pageMap) {
		// TODO Auto-generated method stub
		return mapper.queryAll(pageMap);
	}

	@Override
	public int deleteRolePer(String roleid, String modlueid, String[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleid);
		map.put("modlueid", modlueid);
		map.put("ids", ids);
		return mapper.deleteRolePer(map);
	}

	@Override
	public int saveRolePer(String roleid, String modlueid, String[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleid);
		map.put("modlueid", modlueid);
		map.put("ids", ids);
		return mapper.saveRolePer(map);
	}

}
