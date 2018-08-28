package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.Devices;
import cn.slkj.sloa.Entity.Devices_record;

public interface DevicesMapper {
	List<Devices> getAll(Map<String, Object> map, PageBounds pageBounds);


	int insert(Devices devices);

	int testing(Map<String, Object> map);

	int outRepertory(Devices devices);

	List<Devices> getList(Map<String, Object> map);

	int insert_record(Devices devices);

	int getAllRecordCount(Map<String, Object> pageMap);

	List<Devices_record> getAllRecord(Map<String, Object> pageMap, PageBounds pageBounds);

	Devices queryOne(Map<String, Object> map);

	int ckData(HashMap<String, Object> map);

	int deletes(String[] ids);

	int edit(Devices devices);

	int pack(Map<String, Object> map);

	List<Devices_record> getListRecd(Map<String, Object> map);

	// int getCountByListnum(String listNum);
	// int insertBatch(List<Devices> list);
	int insertBatch(Devices devices);

	int packList(Map<String, Object> map);

	int revoke(HashMap<String, Object> map);

	int editbyNum(Devices devices);

	int insert_recordList(HashMap<String, Object> map);

	int isReceive(HashMap<String, Object> map);
}
