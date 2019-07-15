package com.situ.crm.grant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

 
import com.situ.crm.grant.model.MenuModel;
import com.situ.crm.grant.model.RelModel;
import com.situ.crm.grant.model.UserModel;
import com.situ.crm.grant.service.RelService;
import com.situ.crm.grant.service.UserService;
import com.situ.util.FmtEmpty;
import com.situ.util.FormatPOI;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private  UserService service;
	@Autowired
	private RelService Rservice;
	private String message;
	@ResponseBody
	@RequestMapping(value="/action",produces="application/json;charset=UTF-8")
	private String action(UserModel u,String action) {
		if(!action.isEmpty()) {
			switch (action) {
			case "add":
				message=addOrUpd(u,1);
				break;
			case "list":
				message=page(u);
				break;
			case"del":
				message=delete(u);
				break;
			case "upd":
				message=selectOne(u);
				break;
			case "upds":
				message=addOrUpd(u,2);
				break;
			default:
				break;
			}
		}
		return message;
		}
	
//	@RequestMapping("login1")
//	public String toLogin(){
//	return "login";
//	}
	private String delete(UserModel u) {
		int d=service.delete(u);
		if(d==1) {
			// TODO Auto-generated method stub
			return "删除成功";
		}else  
		return "删除失败";
	}
	private String selectOne(UserModel u) {
		// TODO Auto-generated method stub
		UserModel uu=service.selectId(u);
		return new JSONObject(uu).toString();
	}
	private String page(UserModel u) {
		// TODO Auto-generated method stub
		List<UserModel> s = service.selectModel(u);
		int count =service.selectCount(u);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list",s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}
	private String addOrUpd(UserModel u, int i) {
		// TODO Auto-generated method stub
		if(i==1) {
			int a=service.insert(u);
			if(a>0) {
				return "录入成功";
			}else return "录入失败";
		}else {
			int a=service.updateActive(u);
			if(a>0) {
				return "修改成功";
			}else return "修改失败";
		}
	}
	@ResponseBody
	@RequestMapping(value="/login",produces="application/json;charset=UTF-8")
	public String login(UserModel u,String authcode,HttpServletRequest request) {
		int a=-1;
		Object authCode1 = request.getSession().getAttribute("picCode");
		System.out.println(authCode1);
		System.out.println(authcode+"wangye");
		if(authcode.equals(authCode1)) {
			UserModel uu = service.selectId(u);
			if(uu!=null) {
				if(uu.isAdmin()) {
					 a= 0;	
					 request.getSession().setAttribute("userAdmin",uu);
				}				
				else
					a= 3;	
			}else a= 1;	
		}	
		else a= 4;	
		//System.out.println("a="+a);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("a", a);
		return new JSONObject(map).toString();
	}
	@RequestMapping("/login1")
	public String login1(UserModel t,HttpSession session,Model model) {
		UserModel u = service.selectId(t);
		System.out.println(u+"该用户是");
		String msg;//发送的提示信息
		String view;//转发的页面地址
		if(u==null) {
			msg="账号不存在";
			view="login";
		}else if(u.getPass().equals(t.getPass())){
			msg="登陆成功";
			view="user/main1";
			session.setAttribute("user", u);
			model.addAttribute("menus",getMenu(u));//？
//			for(MenuModel li:getMenu(u) ) {
//				System.out.println(li+"菜单时--------------------------------------");
//			}
		}else {
			msg="密码错误";
			view="login";
		}
		//System.out.println(msg+"______"+view);
		model.addAttribute("msg",msg);
		return view;
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";	
	}
	@ResponseBody
	@RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
	private List<UserModel> list(UserModel g) {
		g.setOrderby("name");
		List<UserModel> list = service.selectList(g);
//		for(UserModel aa:list) {
//			System.out.println("---"+aa);
//		}
		return list;
	}
	//动态表格
	@ResponseBody
	@RequestMapping(value="/list2",produces="application/json;charset=UTF-8")
	public String list2(UserModel g,Integer page,Integer limit) {
 String code = g.getUserCode();
 if(!FmtEmpty.isEmpty(code)) {
	 g.setUserCode("%"+ code +"%");
 }
 String name = g.getName();
 if(!FmtEmpty.isEmpty(name)) {
	 g.setName("%"+ name +"%");
 }
 if(!FmtEmpty.isEmpty(page)) {
	 g.setPageIndex(page);
 }
 if(!FmtEmpty.isEmpty(limit)) {
	 g.setPageLimit(limit);
 }
 g.setPageOn(true);
 Map<String,Object>map=new HashMap<>();
 map.put("data", service.selectModel(g));
 map.put("code", 0);
 map.put("count",service.selectCount(g));
return new JSONObject(map).toString();
	}
 //exportExcel导出
	@RequestMapping(value="/exportExcel",produces="application/json;charset=UTF-8")
	public void exportExcel(HttpServletResponse response,UserModel h)throws Exception{
	List<UserModel> dataList = service.selectModel(h);
	List<String> propList = Arrays.asList("userCode","name","pass");
	List<String> fieldName = Arrays.asList("账号","姓名","密码");
	
	Workbook wb = FormatPOI.exportExcel(dataList, propList, fieldName);
//1)设置响应的头文件，会自动识别文件内容
	response.setContentType("multipart/form-data");
	//2)设置Content-Disposition
	response.setHeader("Content-Disposition", "attachment;filename=test.xls");
	//3）输出流
	OutputStream out=response.getOutputStream();
	wb.write(out);
	wb.close();
	out.close();
	}	
	/**
	 * 导出上传用的模板
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/moban",produces="application/json;charset=UTF-8")
	public void moban(HttpServletResponse response) throws Exception {
		int a=(int) (Math.random()*10000);
		String aa=a+"";//为导出的文件名生成一个随机数	
		List<String> fieldName=Arrays.asList("编号","姓名","密码","职位","上级领导");
		Workbook wb=FormatPOI.moban( fieldName);
		//1.设置响应的头文件，会自动识别文件内容；
		response.setContentType("multipart/form-data");
		response.setContentType("multipart/form-data");
		//2.设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=user"+aa+".xls");
		OutputStream out = response.getOutputStream();
		wb.write(out);//生成
		wb.close();
		out.close();
	}
	/**
	 * 上传文件接口
	 * @param multipartResolver
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("upload")
	public String uploadExcel(CommonsMultipartResolver multipartResolver,HttpServletRequest request) throws IOException {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("code",0);
		if(multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()) {
				MultipartFile file=multiRequest.getFile(iter.next().toString());
				map.put("data",parse(file.getInputStream()));
			}
		}
		return new JSONObject(map).toString();		
	}
	/**
	 * 将文档转换为数据库中的记录
	 * @param fis
	 * @return
	 * @throws IOException 
	 */
	private List<UserModel>parse(InputStream fis) throws IOException {
		// TODO Auto-generated method stub
		//由输入流得到工作簿
		XSSFWorkbook workbook =new XSSFWorkbook(fis);
		//得到工作表
		XSSFSheet sheet=workbook.getSheet("sheet1");
		List<UserModel> list =new ArrayList<UserModel>();
		for(Row row :sheet) {
			if(0==row.getRowNum()) {
				continue;
			}
			UserModel u=new UserModel();
			u.setUserCode(getValue(row.getCell(0)));
			u.setName(getValue(row.getCell(1)));
			u.setPass(getValue(row.getCell(2)));
			u.setRoleCode(getValue(row.getCell(3)));
			u.setParentCode(getValue(row.getCell(4)));
			addOrUpd(u,1);//加入数据库中
		}
		workbook.close();
		fis.close();
		return list;
	}
	/**
	 * 根据单元格的类型确定存入数据库的类型
	 * @param cell
	 * @return
	 */
	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if(CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		} else if (CellType.NUMERIC.equals(type)) {
			return String.valueOf((int)cell.getNumericCellValue());
		}
		return null;	
	}

	/**
	 * 获取登陆的角色等级所对应的菜单
	 */
	private List<MenuModel> getMenu(UserModel u){//？
		System.out.println(u+"taoni");
		String roleCode=u.getRoleCode(); 
//		System.out.println(roleCode+":roleCode");
		if(roleCode==null) {
			return null;
		}		
		RelModel l=new RelModel();
		l.setRoleCode(roleCode);
		List<RelModel> list = Rservice.selectList(l);//找到权限管理里该角色所对的菜单项
//		for(RelModel li:list ) {
//			System.out.println(li+"菜单时");
//		}
		if(list==null) {
			System.out.println("list是空的");
			return null;
		}
		List<MenuModel> result=new ArrayList<MenuModel>();
		for(RelModel rel:list) {//一级菜单
			MenuModel menu = rel.getMenuModel();
			//System.out.println("一级菜单是"+menu);
			String parentCode=menu.getParentCode();
			//System.out.println("1111"+parentCode);
			if(parentCode!=null &&"一".equals(parentCode)) {
				result.add(menu);
				System.out.println("一级菜单是"+menu);
				continue;
			}
			for (MenuModel m:result) {//二级菜单
				if(m.getMenuCode().equals(parentCode)) {
					m.getChild().add(menu);
					System.out.println("子菜单是"+menu);
					break;
				}
			}
		}
		for (MenuModel m:result) {//二级菜单
			System.out.println(m+"fasongdecaidan");
		}
		return result;
	}
//	/**
//	 * 从回收站里将数据恢复（deleted=1—>0）
//	 * @param p
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="retrieveData",produces="application/json;charset=UTF-8")
//	public String retrieveData(UserModel p,String userCode ) {
//		p.setUpdateTime(df.format(new Date()));
//		p.setUpdateBy(userCode);
//		p.setDeleted(0);
//		int a=service.updateActive(p);	
//		if(a>0)
//			return "恢复成功ヽ（≧□≦）ノ";
//		return "恢复失败<。)#)))≦";
//	}
//	/**
//	 * 从数据库将回收站的数据删除
//	 * @param p
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="delR",produces="application/json;charset=UTF-8")
//	public String delR(UserModel p) {
//		int a=service.delete(p);
//		ZiDianModel z=new ZiDianModel();
//		if(a>0) {
//			z.setCode(p.getUserCode());	
//		Zservice.delete(z);
//				return "已删除成功(ಥ _ ಥ)";
//		}
//		
//		return "失败(￣ ‘i ￣;)";
//}
}