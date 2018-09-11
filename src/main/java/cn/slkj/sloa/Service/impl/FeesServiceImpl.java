package cn.slkj.sloa.Service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Dao.FeesMapper;
import cn.slkj.sloa.Entity.Vehicle;
import cn.slkj.sloa.Entity.shfw.Fees;
import cn.slkj.sloa.Entity.shfw.PayFees;
import cn.slkj.sloa.Service.IFeesService;

@Repository
public class FeesServiceImpl implements IFeesService {
	@Autowired
	private FeesMapper mapper;

	@Override
	public List<Fees> getAll(HashMap<String, Object> pageMap, PageBounds pageBounds) {
		return mapper.getAll(pageMap, pageBounds);
	}
	@Override
	public List<Fees> listByCarNumber(HashMap<String, Object> pageMap, PageBounds pageBounds) {
		return mapper.listByCarNumber(pageMap, pageBounds);
	}
	@Override
	public Fees queryOne(String id) {
		return mapper.queryOne(id);
	}

	@Override
	public int insert(Fees fees) {
		return mapper.insert(fees);
	}

	@Override
	public int update(Fees fees) {
		return mapper.update(fees);
	}

	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}

	@Override
	public int fees(HashMap<String, Object> map) {
		return mapper.fees(map);
	}

	@Override
	public int pay(HashMap<String, Object> map) {
		return mapper.pay(map);
	}

	@Override
	public int billing(HashMap<String, Object> map) {
		return mapper.billing(map);
	}

	@Override
	public int updateVehicle(HashMap<String, Object> map) {
		return mapper.updateVehicle(map);
	}
	@Override
	public Vehicle getFeesByCarNum(HashMap<String, Object> hashMap) {
		return mapper.getFeesByCarNum(hashMap);
	}
	@Override
	public Fees getCarFeesByWx(HashMap<String, Object> map) {
		return mapper.getCarFeesByWx(map);
	}
	
	
//	收费记录
	@Override
	public List<PayFees> listPayFees(HashMap<String, Object> pageMap, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return mapper.listByPayFees(pageMap, pageBounds);
	}
	@Override
	public int payFees(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.payFees(map);
	}

}
