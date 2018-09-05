package cn.slkj.sloa.Service;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.system.Company;

/**
 * @ClassName: CompanyService
 * @Description: 公司/车队信息
 * @author maxh
 * 
 */
public interface ICompanyService {

	/**
	 * 查询分页记录
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<Company> getAll(HashMap<String, Object> map, PageBounds pageBounds);

	/**
	 * 查询公司/车队列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Company> queryComList(HashMap<String, Object> map);

	/**
	 * 添加公司/车队
	 * 
	 * @param Agent
	 */
	public int save(Company company);

	/**
	 * 编辑公司/车队
	 * 
	 * @param Agent
	 */
	public int edit(Company company);

	/**
	 * 根据ids批量删除公司/车队记录
	 * 
	 * @param ids
	 */
	public int delete(String id);

	/**
	 * 根据id查询公司信息
	 * 
	 * @param id
	 * @return
	 */
	public Company queryOne(String id);

	public int sfbz(Company company);

}
