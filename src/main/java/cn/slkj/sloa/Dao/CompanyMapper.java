package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.system.Company;

/**
 * 公司 / 平台分组 dao
 */
@Repository
public interface CompanyMapper {

	/**
	 * 查询公司/车队列表
	 * 
	 * @param map
	 * @return
	 */
	List<Company> queryComList(HashMap<String, Object> map);

	/**
	 * 查询公司/车队列表
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */

	List<Company> queryList(HashMap<String, Object> map, PageBounds pageBounds);

	/**
	 * 查询公司/车队总记录数
	 * 
	 * @param map
	 * @return
	 */
	int queryListCount(HashMap<String, Object> map);

	/**
	 * 添加公司/车队
	 * 
	 */
	int save(Company company);

	/**
	 * 编辑公司/车队
	 * 
	 * @param Agent
	 */
	int edit(Company company);

	/**
	 * 根据ids批量删除公司/车队记录
	 * 
	 * @param id
	 */

	int delete(String id);

	Company queryOne(String id);

	int sfbz(Company company);


}