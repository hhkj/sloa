package cn.slkj.sloa.Service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Dao.DevicesMapper;
import cn.slkj.sloa.Entity.Devices;
import cn.slkj.sloa.Entity.Devices_record;
import cn.slkj.sloa.Service.IDevicesService;

@Repository
public class DevicesServiceImpl implements IDevicesService {
	@Autowired
	private DevicesMapper mapper;

	@Override
	public List<Devices> getAll(Map<String, Object> map, PageBounds pageBounds) {
		return mapper.getAll(map, pageBounds);
	}

	 
	@Override
	public int insert(Devices devices) {
		return mapper.insert(devices);
	}

	@Override
	public int outRepertory(Devices devices) {
		return mapper.outRepertory(devices);
	}

	@Override
	public int outRepertory(HashMap<String, Object> map) {
		return mapper.ckData(map);
	}

	@Override
	public int testing(Map<String, Object> map) {
		return mapper.testing(map);
	}

	@Override
	public List<Devices> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public int insert_record(Devices devices) {
		return mapper.insert_record(devices);
	}


	@Override
	public List<Devices_record> getAllRecord(Map<String, Object> pageMap, PageBounds pageBounds) {
		return mapper.getAllRecord(pageMap, pageBounds);
	}

	@Override
	public Devices queryOne(Map<String, Object> map) {
		return mapper.queryOne(map);
	}

	@Override
	public int deletes(String[] ids) {
		return mapper.deletes(ids);
	}

	@Override
	public int edit(Devices devices) {
		return mapper.edit(devices);
	}

	@Override
	public int pack(Map<String, Object> map) {
		return mapper.pack(map);
	}

	@Override
	public List<Devices_record> getListRecd(Map<String, Object> map) {
		return mapper.getListRecd(map);
	}

	@Override
	public int packList(Map<String, Object> map) {
		return mapper.packList(map);
	}

	@Override
	public int revoke(HashMap<String, Object> map) {
		return mapper.revoke(map);
	}

	@Override
	public int editbyNum(Devices devices) {
		return mapper.editbyNum(devices);
	}

	@Override
	public int insert_recordList(HashMap<String, Object> map) {
		return mapper.insert_recordList(map);
	}

	@Override
	public int isReceive(HashMap<String, Object> map) {
		return mapper.isReceive(map);
	}


	@Override
	public void exportExcel(HashMap<String, Object> map, String[] titles, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int importBrandPeriodSort(InputStream in) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
}
