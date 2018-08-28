/**
 * 
 */
package cn.slkj.sloa.Service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.slkj.sloa.Dao.MenusMapper;
import cn.slkj.sloa.Entity.system.Menus;
import cn.slkj.sloa.Service.IMenusService;

/**
*  @author  maxh  
*  @ClassName  : MenusServiceImpl  
*  @Version  版本   
*  @Copyright  神龙科技  
*  @date  2018年8月9日 下午4:37:57 
*/
@Service
public class MenusServiceImpl implements IMenusService {
	@Resource
	private MenusMapper menusMapper ;
	 
	@Override
	public List<Menus> queryAll(HashMap<String, Object> hashMap) {
 		return menusMapper.queryAll(hashMap);
	}
	@Override
	public int insert(Menus menus) {
		
		return menusMapper.insert(menus);
	}

}
