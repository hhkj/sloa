package cn.slkj.sloa.Service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.hbsl.util.javaUtil.UUIDUtils;
import cn.slkj.sloa.Dao.SimMapper;
import cn.slkj.sloa.Entity.stock.Sim;
import cn.slkj.sloa.Service.ISimService;

@Repository
public class SimServiceImpl implements ISimService {
	@Autowired
	private SimMapper mapper;

	@Override
	public List<Sim> getAll(Map<String, Object> map, PageBounds pageBounds) {
		return mapper.getAll(map, pageBounds);
	}

	@Override
	public List<Sim> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public int insert(Sim sim) {
		return mapper.insert(sim);
	}

	@Override
	public int edit(Sim sim) {
		return mapper.edit(sim);
	}

	@Override
	public int editPay(Sim sim) {
		return mapper.editPay(sim);
	}

	@Override
	public int simPay(Sim sim) {
		return mapper.simPay(sim);
	}

	@Override
	public int deletes(String[] ids) {
		return mapper.deletes(ids);
	}

	@Override
	public List<Sim> simPaylist(Map<String, Object> pageMap, PageBounds pageBounds) {
		return mapper.simPaylist(pageMap, pageBounds);
	}

	@Override
	public int use(HashMap<String, Object> map) {
		return mapper.use(map);
	}

	@Override
	public int useByDevices(HashMap<String, Object> map) {
		return mapper.use(map);
	}

	@Override
	public int editByNum(Sim sim) {
		return mapper.editByNum(sim);
	}

	@Override
	public int revoke(HashMap<String, Object> map) {
		return mapper.revoke(map);
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
