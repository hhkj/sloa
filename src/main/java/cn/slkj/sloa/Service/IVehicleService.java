package cn.slkj.sloa.Service;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.Vehicle;

public interface IVehicleService {
	public List<Vehicle> getList(HashMap<String, Object> hashMap, PageBounds pageBounds);
	
	public Vehicle queryOne(HashMap<String, Object> map);
	
	public int save(Vehicle vehicle);

	public int edit(Vehicle vehicle);
	
	public int editSeasonExam(Vehicle vehicle);
	
	public int editYearExam(Vehicle vehicle);

	public int delete(String id);

	public int sfbz(Vehicle vehicle);
}
