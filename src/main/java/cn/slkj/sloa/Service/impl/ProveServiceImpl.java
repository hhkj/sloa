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

import cn.slkj.sloa.Dao.ProveMapper;
import cn.slkj.sloa.Entity.stock.Prove;
import cn.slkj.sloa.Entity.stock.Prove_record;
import cn.slkj.sloa.Service.IProveService;

@Repository
public class ProveServiceImpl implements IProveService {
	@Autowired
	private ProveMapper mapper;

	@Override
	public List<Prove> getAll(Map<String, Object> map, PageBounds pageBounds) {
		return mapper.getAll(map, pageBounds);
	}

	@Override
	public Prove queryOne(Prove prove) {
		return mapper.queryOne(prove);
	}

	@Override
	public int save(Prove prove) {
		return mapper.save(prove);
	}

	@Override
	public int edit(Prove prove) {
		return mapper.edit(prove);
	}

	@Override
	public int use(Map<String, Object> map) {
		return mapper.use(map);
	}

	@Override
	public int tovoid(Map<String, Object> map) {
		return mapper.tovoid(map);
	}

	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}

	@Override
	public int billing(HashMap<String, Object> map) {
		return mapper.billing(map);
	}

	@Override
	public int fees(HashMap<String, Object> map) {
		return mapper.fees(map);
	}

	@Override
	public boolean updateImg(String imgPath, String filename, String number) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", imgPath);
		map.put("filename", filename);
		map.put("number", number);
		int i = mapper.updateImg(map);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int canceled(HashMap<String, Object> map) {
		return mapper.canceled(map);
	}

	@Override
	public int revoke(HashMap<String, Object> map) {
		return mapper.revoke(map);
	}

	@Override
	public int insert_recordList(HashMap<String, Object> map) {
		return mapper.insert_recordList(map);
	}

	@Override
	public List<Prove_record> getListRecd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.getListRecd(map);
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
