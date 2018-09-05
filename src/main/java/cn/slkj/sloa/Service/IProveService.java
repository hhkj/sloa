package cn.slkj.sloa.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.stock.Prove;
import cn.slkj.sloa.Entity.stock.Prove_record;

public interface IProveService {

	/**
	 * @param pageBounds
	 * 
	 * @Title: getAll
	 * @Description: 条件查询所有
	 * @param map
	 * @return List<Prove>
	 * @throws
	 */
	public List<Prove> getAll(Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 根据id获取
	 * 
	 * @param Agent
	 * @return
	 */
	public Prove queryOne(Prove prove);

	/**
	 * 添加
	 * 
	 * @param Agent
	 */
	public int save(Prove prove);

	/**
	 * 编辑
	 * 
	 * @param Agent
	 */
	public int edit(Prove prove);

	/**
	 * 使用
	 * 
	 * @param Agent
	 */
	public int use(Map<String, Object> map);

	/**
	 * 作废
	 * 
	 * @param Agent
	 */
	public int tovoid(Map<String, Object> map);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public int delete(String id);

	public int billing(HashMap<String, Object> map);

	public int fees(HashMap<String, Object> map);

	public boolean updateImg(String imgPath, String filename, String number);

	public int canceled(HashMap<String, Object> map);

	public int revoke(HashMap<String, Object> map);

	/**
	 * 出库记录
	 * 
	 * @param map
	 * @return
	 */
	public int insert_recordList(HashMap<String, Object> map);

	/**
	 * 查询出入库记录
	 * @param map
	 * @return
	 */
	public List<Prove_record> getListRecd(Map<String, Object> map);
	/**
	 * 导入导出
	 * @param map
	 * @param titles
	 * @param outputStream
	 */
	public void exportExcel(HashMap<String, Object> map, String[] titles, ServletOutputStream outputStream);
	public int importBrandPeriodSort(InputStream in) throws IOException;
}
