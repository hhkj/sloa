package cn.slkj.sloa.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.stock.Sim;

public interface ISimService {

	/**
	 * 查询所有sim数据库
	 * 
	 * @param map
	 * @param pageBounds
	 *            返回easyui 分页数据
	 * @return
	 */
	public List<Sim> getAll(Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 查询所有sim数据库
	 * 
	 * @param map
	 * @return
	 */
	public List<Sim> getList(Map<String, Object> map);

	/**
	 * 保存sim数据库
	 * 
	 * @param sim
	 * @return
	 */
	public int insert(Sim sim);

	/**
	 * 编辑sim信息
	 * 
	 * @param sim
	 * @return
	 */
	public int edit(Sim sim);

	public int editPay(Sim sim);

	public int simPay(Sim sim);

	public int deletes(String[] ids);

	/**
	 * 查询sim缴费记录
	 * 
	 * @param pageMap
	 * @param pageBounds
	 * @return
	 */
	public List<Sim> simPaylist(Map<String, Object> pageMap, PageBounds pageBounds);

	/**
	 * 批量出库
	 * 
	 * @param map
	 * @return
	 */
	public int use(HashMap<String, Object> map);

	/**
	 * 根据设备ids批量出库
	 * 
	 * @param map
	 * @return
	 */
	public int useByDevices(HashMap<String, Object> map);

	/**
	 * 修改卡的使用状态
	 * 
	 * @param sim
	 * @return
	 */
	public int editByNum(Sim sim);

	public int revoke(HashMap<String, Object> map);
	/**
	 * 导入导出
	 * @param map
	 * @param titles
	 * @param outputStream
	 */
	public void exportExcel(HashMap<String, Object> map, String[] titles, ServletOutputStream outputStream);
	public int importBrandPeriodSort(InputStream in) throws IOException;
}
