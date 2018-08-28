package cn.slkj.sloa.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.Devices;
import cn.slkj.sloa.Entity.Devices_record;

public interface IDevicesService {
	public List<Devices> getAll(Map<String, Object> map, PageBounds pageBounds);

	public int insert(Devices devices);

	public int outRepertory(Devices devices);

	public int outRepertory(HashMap<String, Object> map);

	public int testing(Map<String, Object> map);

	public List<Devices> getList(Map<String, Object> map);

	public int insert_record(Devices devices);

	public List<Devices_record> getAllRecord(Map<String, Object> pageMap, PageBounds pageBounds);

	public Devices queryOne(Map<String, Object> map);

	public int deletes(String[] ids);

	public int edit(Devices devices);

	public int pack(Map<String, Object> map);

	public List<Devices_record> getListRecd(Map<String, Object> map);

	public int packList(Map<String, Object> map);

	public int revoke(HashMap<String, Object> map);

	/**
	 * 修改设备使用状态
	 * 
	 * @param devices
	 * @return
	 */
	public int editbyNum(Devices devices);

	/**
	 * 出库记录
	 * 
	 * @param map
	 * @return
	 */
	public int insert_recordList(HashMap<String, Object> map);

	public int isReceive(HashMap<String, Object> map);
	/**
	 * 导入导出
	 * @param map
	 * @param titles
	 * @param outputStream
	 */
	public void exportExcel(HashMap<String, Object> map, String[] titles, ServletOutputStream outputStream);
	public int importBrandPeriodSort(InputStream in) throws IOException;
	
}
