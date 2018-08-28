package cn.slkj.sloa.Entity.system;

import java.io.Serializable;
import java.util.List;

public class MenuDTO extends Menus implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MenuDTO> menus;

	public List<MenuDTO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}

    


}