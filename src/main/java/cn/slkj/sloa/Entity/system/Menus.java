/**
 * 
 */
package cn.slkj.sloa.Entity.system;

/**
 * @author maxh
 * @ClassName : Menus
 * @Version 版本
 * @Copyright 神龙科技
 * @date 2018年8月9日 下午2:37:28
 */
public class Menus {

	private Integer menuid;
	private String icon;
	private String menuname;
	private Integer parentMenu;
	private String url;
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public Integer getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Integer parentMenu) {
		this.parentMenu = parentMenu;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Menus [menuid=" + menuid + ", icon=" + icon + ", menuname=" + menuname + ", parentMenu=" + parentMenu + ", url=" + url + ", getMenuid()=" + getMenuid() + ", getIcon()=" + getIcon() + ", getMenuname()=" + getMenuname() + ", getParentMenu()=" + getParentMenu() + ", getUrl()=" + getUrl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	 

}
