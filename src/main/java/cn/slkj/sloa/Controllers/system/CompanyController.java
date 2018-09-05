package cn.slkj.sloa.Controllers.system;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.hbsl.util.javaUtil.UUIDUtils;
import cn.slkj.sloa.Entity.system.Company;
import cn.slkj.sloa.Service.impl.CompanyServiceImpl;
import cn.slkj.sloa.util.easyuiUtil.EPager;

/**
 * 
 * @ClassName: CompanyController
 * @Description: 公司/车队
 * @author maxuhui
 *
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	private static Logger log = LoggerFactory.getLogger(CompanyController.class);
	@Resource
	private CompanyServiceImpl companyService;
	//页面跳转开始
	@RequestMapping("/toCompanyPage")
	public String toCompanyPage() {
		return "/system/Company";
	}
	@RequestMapping("/toAddPage")
	public String toAddPage() {
		return "system/company/CompanyAdd";
	}
	@RequestMapping("/toEditPage")
	public String toEditPage() {
		return "system/company/CompanyEdit";
	}
	@RequestMapping("/toSfbzPage")
	public String toSfbzPage() {
		return "system/company/sfbz";
	}
	
	//页面跳转结束
	/**
	 * 
	 * @param page
	 * @param rows
	 * @param gsmc
	 * @param location
	 * @param session
	 * @return 返回easyUI datagrid 数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public EPager<Company> list(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		// String name
		// =EncodingTool.encodeStr(request.getParameter("compName"));
		String name = request.getParameter("compName");
		map.put("compName", name);
		map.put("organizationCode", request.getParameter("organizationCode"));

		String sortString = "compName.asc";
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Company> list = companyService.getAll(map, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Company>(pageList.getPaginator().getTotalCount(), list);
	}

	@ResponseBody
	@RequestMapping(value = "/queryComList")
	public List<Company> queryComList(String name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return companyService.queryComList(map);
	}

	/**
	 * 获取公司/车队实体
	 * 
	 * @param classification
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOne")
	public Company queryOne(String id) {
		return companyService.queryOne(id);
	}

	/** 保存公司/车队 */
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public boolean save(Company company) throws Exception {
		try {
			company.setId(UUIDUtils.uuid());
			int i = companyService.save(company);
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** 编辑保存 */
	@ResponseBody
	@RequestMapping(value = "/editCompany")
	public boolean editCompany(Company company) throws Exception {
		try {
			int i = companyService.edit(company);
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** 编辑保存 */
	@ResponseBody
	@RequestMapping(value = "/sfbz")
	public boolean sfbz(Company company) throws Exception {
		try {
			int i = companyService.sfbz(company);
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** 批量删除 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public boolean deletes(String id) {
		int i = companyService.delete(id);
		try {
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
