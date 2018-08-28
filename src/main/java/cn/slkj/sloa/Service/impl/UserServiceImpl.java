/**
 * 
 */
package cn.slkj.sloa.Service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.sloa.Dao.UserMapper;
import cn.slkj.sloa.Entity.system.User;
import cn.slkj.sloa.Service.IUserService;

/**
 * @author maxh
 * @ClassName : UserServiceImpl
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月8日 上午9:25:57
 */
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;

	/**
	 * 条件查询所有用户列表
	 */
	@Override
	public List<User> queryAll(HashMap<String, Object> hashMap) {
		return userMapper.queryAll(hashMap);
	}

	@Override
	public User userLogin(HashMap<String, Object> map) {
		return userMapper.userLogin(map);
	}

	@Override
	public List<User> getAllUsers(HashMap<String, Object> hashMap, PageBounds pageBounds) {
		return userMapper.getAllUsers(hashMap, pageBounds);
	}

	@Override
	public User queryOne(HashMap<String, Object> hashMap) {
		return userMapper.queryOne(hashMap);
	}

	@Override
	public int save(User user) {
		return userMapper.save(user);
	}

	@Override
	public int edit(User user) {
		return userMapper.edit(user);
	}

	@Override
	public int delete(String id) {
		return userMapper.delete(id);
	}

}
