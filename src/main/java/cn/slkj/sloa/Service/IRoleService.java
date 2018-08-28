package cn.slkj.sloa.Service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.system.Role;

public interface IRoleService {

	public List<Role> getAll(Map<String, Object> map, PageBounds pageBounds);


	public Role queryByid(String id);

	/**
	 * 
	 * @Title: editRole
	 * @Description: 编辑角色信息
	 * @param role
	 * @return int 返回类型
	 */
	public int editRole(Role role);

	/**
	 * 
	 * @Title: saveRole
	 * @Description: 保存角色信息
	 * @param role
	 * @return int 返回类型
	 */
	public int saveRole(Role role);

	/**
	 * 
	 * @Title: deleteRole
	 * @Description: 删除角色数据
	 * @param ids
	 * @return int 返回类型
	 */
	public int deleteRole(String id);

	public int saveRoleRes(String roleid, String[] ids);

	public int deleteRoleRes(String roleid, String[] ids);

	public List<Role> queryAll(Map<String, Object> pageMap);

	public int deleteRolePer(String roleid, String modlueid, String[] ids);

	public int saveRolePer(String roleid, String modlueid, String[] ids);

}
