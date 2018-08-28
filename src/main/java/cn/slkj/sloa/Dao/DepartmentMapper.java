package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;

import cn.slkj.sloa.Entity.system.Department;

/**
 * 
 * @ClassName: DepartmentMapper
 * @Description: 部门管理 dao
 * @author maxuhui
 *
 */
public interface DepartmentMapper {

	/**
	 * 查询列表
	 * 
	 * @param map
	 * @return
	 */

	List<Department> queryList(HashMap<String, Object> map);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Department queryOne(String id);

	/**
	 * 添加
	 * 
	 */
	int save(Department department);

	/**
	 * 编辑
	 * 
	 */
	int edit(Department department);

	/**
	 * 根据id删除记录
	 */

	int delete(String id);

}
