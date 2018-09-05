package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.stock.Prove;
import cn.slkj.sloa.Entity.stock.Prove_record;

public interface ProveMapper {
	List<Prove> getAll(Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 根据id获取
	 * 
	 * @param Insurance
	 * @return
	 */
	public Prove queryOne(Prove prove);

	/**
	 * 保存
	 * 
	 * @param Insurance
	 */
	public int save(Prove prove);

	/**
	 * 编辑
	 * 
	 * @param prove
	 */
	public int edit(Prove prove);

	/**
	 * 使用
	 * 
	 * @param prove
	 */
	public int use(Map<String, Object> map);

	/**
	 * 作废
	 * 
	 * @param ids
	 */
	int tovoid(Map<String, Object> map);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public int delete(String id);

	int billing(HashMap<String, Object> map);

	int fees(HashMap<String, Object> map);

	int updateImg(HashMap<String, Object> map);

	int canceled(HashMap<String, Object> map);

	List<Prove> getList(HashMap<String, Object> map);

	int revoke(HashMap<String, Object> map);

	int insert_recordList(HashMap<String, Object> map);

	/**
	 * excel插入
	 */
	int insertBatch(Prove prove);

	List<Prove_record> getListRecd(Map<String, Object> map);
}
