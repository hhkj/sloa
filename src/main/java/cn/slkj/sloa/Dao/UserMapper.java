package cn.slkj.sloa.Dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.system.User;

@Repository
public interface UserMapper {
	public List<User> queryAll(HashMap<String, Object> hashMap);

	public User userLogin(HashMap<String, Object> map);

	public List<User> getAllUsers(HashMap<String, Object> hashMap, PageBounds pageBounds);
	public User queryOne(HashMap<String, Object> hashMap);
	public int save(User user);

	public int edit(User user);

	public int delete(String id);

}
