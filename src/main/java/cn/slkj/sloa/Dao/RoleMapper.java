package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.system.Role;


/**
 * 
 * @ClassName: RoleMapper
 * @Description: 角色mapper接口
 * @author maxh
 *
 */
public interface RoleMapper {

	Role queryByid(String id);

	int editRole(Role role);

	int saveRole(Role role);

	int deleteRole(String id);

	List<Role> getAll(Map<String, Object> map, PageBounds pageBounds);

	int saveRoleRes(HashMap<String, Object> map);

	int deleteRoleRes(HashMap<String, Object> map);

	List<Role> queryAll(Map<String, Object> pageMap);

	int deleteRolePer(HashMap<String, Object> map);

	int saveRolePer(HashMap<String, Object> map);
}
