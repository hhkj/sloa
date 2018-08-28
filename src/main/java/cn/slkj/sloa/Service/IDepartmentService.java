package cn.slkj.sloa.Service;

import java.util.HashMap;
import java.util.List;

import cn.slkj.sloa.Entity.system.Department;

public interface IDepartmentService {

	public List<Department> queryList(HashMap<String, Object> map);

	/**
	 * 添加
	 */
	public int save(Department department);

	/**
	 * 编辑
	 */
	public int edit(Department department);

	/**
	 * 根据id 批量记录
	 */
	public int delete(String id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Department queryOne(String id);
}
