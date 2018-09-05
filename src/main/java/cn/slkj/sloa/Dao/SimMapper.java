package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.stock.Sim;

public interface SimMapper {
	List<Sim> getAll(Map<String, Object> map, PageBounds pageBounds);

	int getAllCount(Map<String, Object> map);

	List<Sim> getList(Map<String, Object> map);

	int insert(Sim sim);

	int edit(Sim sim);

	int editPay(Sim sim);

	int simPay(Sim sim);

	int deletes(String[] ids);

	List<Sim> simPaylist(Map<String, Object> pageMap, PageBounds pageBounds);

	int use(HashMap<String, Object> map);

	int useByDevices(HashMap<String, Object> map);

	int editByNum(Sim sim);
	
	int revoke(HashMap<String, Object> map);
	
	
	/**
	 * excel插入	
	 */
	int insertBatch(Sim sim);

}
