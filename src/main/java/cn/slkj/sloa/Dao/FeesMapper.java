package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.Vehicle;
import cn.slkj.sloa.Entity.shfw.Fees;
import cn.slkj.sloa.Entity.shfw.PayFees;

@Repository
public interface FeesMapper {

	public List<Fees> list(HashMap<String, Object> pageMap, PageBounds pageBounds);

	public List<Fees> getAll(HashMap<String, Object> pageMap, PageBounds pageBounds);

	public List<Fees> listByCarNumber(HashMap<String, Object> pageMap, PageBounds pageBounds);

	public int insert(Fees fees);

	public int update(Fees fees);

	public int delete(String id);

	public Fees queryOne(String id);

	public int fees(HashMap<String, Object> map);

	public int pay(HashMap<String, Object> map);

	public int billing(HashMap<String, Object> map);

	public int updateVehicle(HashMap<String, Object> map);

	public Vehicle getFeesByCarNum(HashMap<String, Object> hashMap);

	public Fees getCarFeesByWx(HashMap<String, Object> map);

	
//	车辆缴费记录
	public List<PayFees> listByPayFees(HashMap<String, Object> pageMap, PageBounds pageBounds);
	
	public int payFees(HashMap<String, Object> map);
	

}
