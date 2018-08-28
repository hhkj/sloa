package cn.slkj.sloa.Entity.system;

import java.util.ArrayList;
import java.util.List;

public class RunMain {
	//构造成MenuDTO数据结构
    public static List<MenuDTO> getAllMenu(List<Menus> list) {
        List<MenuDTO> result = new ArrayList<>();
        for (Menus item : list) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuid(item.getMenuid());
            menuDTO.setParentMenu(item.getParentMenu());
            menuDTO.setMenus(null);
            result.add(menuDTO);
        }
        return result;
    }
    /**
     * 作用:遍历树形菜单得到所有的id
     * @param root  
     * @param result
     * @return
     */
    //前序遍历得到所有的id List
    private static List<Integer> ergodicList(List<MenuDTO> root, List<Integer> result){
         for (int i = 0; i < root.size(); i++) {
                // 查询某节点的子节点（获取的是list）
                result.add(root.get(i).getMenuid());//前序遍历
                if (null != root.get(i).getMenus()) {
                    List<MenuDTO> children = root.get(i).getMenus();
                    ergodicList(children,result);
                }
                //result.add(root.get(i).getId());//后序遍历
        }
        return result;
     }


    public static  List<MenuDTO> ergodicTrees(List<MenuDTO> root) throws Exception {
        for (int i = 0; i < root.size(); i++) {
            // 查询某节点的子节点（获取的是list）
            List<MenuDTO> children = new ArrayList<MenuDTO>();
            if (null != root.get(i).getMenus()) {
                children = root.get(i).getMenus();
            }
            ergodicTrees(children);
        }
        return root;
    }
    /**
     * 
     * @param list  MenuDTO数据
     * @param pid  父id
     * @return 把所有list  MenuDTO设置子节点
     * @throws Exception
     */
    public static List<MenuDTO> treeMenu(List<MenuDTO> list,Integer pid) throws Exception{
        List<MenuDTO> childList = new ArrayList<MenuDTO>();
        for (MenuDTO item : list) {
            if (item != null) {
                // 判断当前节点的父节点是否是pid
                if (pid.equals(item.getParentMenu())) {
                    List<MenuDTO> child = treeMenu(list, item.getMenuid());
                    item.setMenus(child);
                    childList.add(item);
                }
            }
        }
        return ergodicTrees(childList);
    }

    public static void main(String[] args) throws Exception {
        List<Menus> menus = new ArrayList<>();
        Menus menu1 = new Menus();
        menu1.setMenuid(1);
        menu1.setParentMenu(0);
        Menus menu2 = new Menus();
        menu2.setMenuid(2);
        menu2.setParentMenu(1);
        Menus menu3 = new Menus();
        menu3.setMenuid(3);
        menu3.setParentMenu(1);
        Menus menu4 = new Menus();
        menu4.setMenuid(4);
        menu4.setParentMenu(3);
        Menus menu5 = new Menus();
        menu5.setMenuid(5);
        menu5.setParentMenu(0);
        Menus menu6 = new Menus();
        menu6.setMenuid(6);
        menu6.setParentMenu(5);
        Menus menu7 = new Menus();
        menu7.setMenuid(7);
        menu7.setParentMenu(5);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        menus.add(menu6);
        menus.add(menu7);
        List<MenuDTO> menuDTOs = getAllMenu(menus);
        List<MenuDTO> root = treeMenu(menuDTOs,0) ;  //pid=0
        //存放树形Integer类型的结果集
        List<Integer> result = new ArrayList<>();
        ergodicList(root,result);
        for (Integer item : result) {
            System.out.print(item +" ");  //前序遍历 1 2 3 4 5 6 7
        }
    }

}
