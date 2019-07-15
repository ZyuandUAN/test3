package com.situ.crm.kehu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.situ.crm.kehu.model.CustInfoModel;
import com.situ.crm.kehu.service.CustInfoService;
import com.situ.util.FmtEmpty;
import com.situ.util.FmtMail;
import com.situ.util.FormatPOI;

@Controller
@RequestMapping("/custInfo")
public class CustInfoController {
	@Autowired
	private CustInfoService service;
	private String message;
//	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	@ResponseBody
	@RequestMapping(value="/action",produces="application/json;charset=UTF-8")
	private String action(CustInfoModel u,String action) {
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
	
	private String delete(CustInfoModel u) {
		int d=service.delete(u);
		if(d==1) {
			// TODO Auto-generated method stub
			return "删除成功";
		}else  
		return "删除失败";
	}
	private String selectOne(CustInfoModel u) {
		// TODO Auto-generated method stub
		CustInfoModel uu=service.selectId(u);
		return new JSONObject(uu).toString();
	}
	private String page(CustInfoModel u) {
		// TODO Auto-generated method stub
		List<CustInfoModel> s = service.selectModel(u);
		int count =service.selectCount(u);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list",s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}
	private String addOrUpd(CustInfoModel u, int i) {
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
	@RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
	private List<CustInfoModel> list(CustInfoModel g) {
		g.setOrderby("name");
		List<CustInfoModel> list = service.selectList(g);
		for(CustInfoModel aa:list) {
			System.out.println("---"+aa);
		}
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "/list2", produces = "application/json;charset=UTF-8")
	public String list2(CustInfoModel g, Integer page, Integer limit) {
		String code = g.getCustCode();
		if (!FmtEmpty.isEmpty(code)) {
			g.setCustCode("%" + code + "%");
		}
		String name = g.getCustName();
		if (!FmtEmpty.isEmpty(name)) {
			g.setCustName("%" + name + "%");
		}
		if (!FmtEmpty.isEmpty(page)) {
			g.setPageIndex(page);
		}
		if (!FmtEmpty.isEmpty(limit)) {
			g.setPageLimit(limit);
		}
		g.setPageOn(true);
		Map<String, Object> map = new HashMap<>();
		map.put("data", service.selectModel(g));
		map.put("code", 0);
		map.put("count", service.selectCount(g));
		return new JSONObject(map).toString();
	}
/**
 * @return 
*
*/
	@ResponseBody
	@RequestMapping(value="/send",produces="application/json;charset=UTF-8")
	public String send(String email,String zhuti,String zhengwen)throws MessagingException{
	String[] to = {email};
	FmtMail fm = new FmtMail(to,"1353276013@qq.com","vqwlzguyjrxuibjb","smtp.qq.com","587");
 	int a = fm.send(zhuti,zhengwen);
	System.out.println(email+"---"+zhuti+"---"+zhengwen);
	if(a==1)
		return "邮件已成功发送";
	else return "邮件发送失败";
	}
	
	@RequestMapping(value="/exportExcel",produces="application/json;charset=UTF-8")
	public void exportExcel(HttpServletResponse response,CustInfoModel h)throws Exception{
	List<CustInfoModel> dataList = service.selectModel(h);
	List<String> propList = Arrays.asList("custCode","custName","status","email");
	List<String> fieldName = Arrays.asList("客户账号","客户姓名","订单状态","邮箱");
	
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
		List<String> fieldName=Arrays.asList("客户账号","客户姓名","订单状态","邮箱","接待职位","接待人员");
		Workbook wb=FormatPOI.moban( fieldName);
		//1.设置响应的头文件，会自动识别文件内容；
		response.setContentType("multipart/form-data");
		response.setContentType("multipart/form-data");
		//2.设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=user"+aa+".xls");
		OutputStream out = response.getOutputStream();
		wb.write(out);
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
	private List<CustInfoModel> parse(InputStream fis) throws IOException {
		// TODO Auto-generated method stub
		//由输入流得到工作簿
		XSSFWorkbook workbook =new XSSFWorkbook(fis);
		//得到工作表
		XSSFSheet sheet=workbook.getSheet("sheet1");
		List<CustInfoModel> list =new ArrayList<CustInfoModel>();
		for(Row row :sheet) {
			if(0==row.getRowNum()) {
				continue;
			}
			CustInfoModel u=new CustInfoModel();
			u.setCustCode(getValue(row.getCell(0)));
			u.setCustName(getValue(row.getCell(1)));
			u.setStatus(getValue(row.getCell(2)));
			u.setEmail(getValue(row.getCell(3)));
			u.setRoleName(getValue(row.getCell(4)));
			u.setUserName(getValue(row.getCell(5)));
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

//	/**
//	 * 查看被删除的记录
//	 * @param p
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="retrieve",produces="application/json;charset=UTF-8")
//	public String retrieve(CustInfoModel p) {
//		p.setDeleted(1);
//		List<CustInfoModel> list = service.selectModel(p);
//		int a=service.selectCount(p);
//		Map<String,Object> map=new HashMap<String, Object>();
//		map.put("data", list);
//		map.put("count", a);
//		map.put("code", 0);
//		return new JSONObject(map).toString();
//	}
//	/**
//	 * 从回收站里将数据恢复（deleted=1—>0）
//	 * @param p
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="retrieveData",produces="application/json;charset=UTF-8")
//	public String retrieveData(CustInfoModel p,String userCode ) {
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
//	public String delR(CustInfoModel p) {
//		ZiDianModel z=new ZiDianModel();
//		int a=service.delete(p);
//		if(a>0) {
//			z.setCode(p.getCustCode());
//			
//			Zservice.delete(z);
//				return "已删除成功(ಥ _ ಥ)";
//		}
//		return "失败(￣ ‘i ￣;)";
//	}
}
