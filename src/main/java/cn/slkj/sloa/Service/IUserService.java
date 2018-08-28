/**
 * 
 */
package cn.slkj.sloa.Service;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Entity.system.User;

/**
 * @author maxh
 * @ClassName : IUserService
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月8日 上午9:24:21
 */
public interface IUserService {
	List<User> queryAll(HashMap<String, Object> hashMap);
	User userLogin(HashMap<String, Object> map);
	List<User> getAllUsers(HashMap<String, Object> hashMap, PageBounds pageBounds);
	public User queryOne(HashMap<String, Object> hashMap);
	public int save(User user);

	public int edit(User user);

	public int delete(String id);
}
