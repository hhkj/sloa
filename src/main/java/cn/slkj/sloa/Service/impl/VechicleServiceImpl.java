package cn.slkj.sloa.Service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Dao.VehicleMapper;
import cn.slkj.sloa.Entity.Vehicle;
import cn.slkj.sloa.Service.IVehicleService;

@Service
@Transactional
public class VechicleServiceImpl implements IVehicleService {
	@Resource
	private VehicleMapper vehicleMapper;

	/**
	 * 查询
	 */
	@Override
	public List<Vehicle> getList(HashMap<String, Object> hashMap, PageBounds pageBounds) {
		return vehicleMapper.getList(hashMap, pageBounds);
	}

	/**
	 * 查询单条
	 */
	@Override
	public Vehicle queryOne(HashMap<String, Object> map) {
		return vehicleMapper.queryOne(map);
	}

	/**
	 * 添加
	 */
	@Override
	public int save(Vehicle vehicle) {
		return vehicleMapper.save(vehicle);
	}

	/**
	 * 编辑
	 */
	@Override
	public int edit(Vehicle vehicle) {
		return vehicleMapper.edit(vehicle);
	}

	/**
	 * 编辑季审
	 */
	@Override
	public int editSeasonExam(Vehicle vehicle) {
		return vehicleMapper.editSeasonExam(vehicle);
	}

	/**
	 * 编辑年审
	 */
	@Override
	public int editYearExam(Vehicle vehicle) {
		return vehicleMapper.editYearExam(vehicle);
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(String id) {
		return vehicleMapper.delete(id);
	}

	@Override
	public int sfbz(Vehicle vehicle) {
		return vehicleMapper.sfbz(vehicle);
	}

}
