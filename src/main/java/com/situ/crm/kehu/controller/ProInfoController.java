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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.situ.crm.kehu.model.ProInfoModel;
import com.situ.crm.kehu.service.ProInfoService;
import com.situ.util.FmtEmpty;
import com.situ.util.FormatPOI;

@Controller
@RequestMapping("/proinfo")
public class ProInfoController {
	@Autowired
	private ProInfoService service;
//@Autowired
//private ZiDianService Zservice;
//private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	@ResponseBody
	@RequestMapping(value = "del", produces = "application/json;charset=UTF-8")
	private String del(ProInfoModel u) {
		int d = service.delete(u);
		if (d == 1) {
			// TODO Auto-generated method stub
			return "删除成功";
		} else
			return "删除失败";
	}

	/**
	 * 删除功能，将deleted改为1
	 * 
	 * @param p
	 * @return
	 */
//@ResponseBody
//@RequestMapping(value="del",produces="application/json;charset=UTF-8")
//public String del(ProInfoModel p,String userCode) {
//	p.setUpdateTime(df.format(new Date()));
//	p.setUpdateBy(userCode);
//	p.setDeleted(1);
//		int a=service.updateActive(p);
//		if(a>0)
//			return "删除成功";
//	return "删除失败";
//}
	@ResponseBody
	@RequestMapping(value = "selectOne", produces = "application/json;charset=UTF-8")
	private String selectOne(ProInfoModel u) {
		// TODO Auto-generated method stub
		ProInfoModel uu = service.selectId(u);
		return new JSONObject(uu).toString();
	}

	@ResponseBody
	@RequestMapping(value = "page", produces = "application/json;charset=UTF-8")
	private String page(ProInfoModel u) {
		// TODO Auto-generated method stub
		List<ProInfoModel> s = service.selectModel(u);
		int count = service.selectCount(u);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}

	@ResponseBody
	@RequestMapping(value = "add", produces = "application/json;charset=UTF-8")
	private String add(ProInfoModel u) {
		// TODO Auto-generated method stub
		int a = service.insert(u);
		if (a > 0) {
			return "录入成功";
		} else
			return "录入失败";
	}

	@ResponseBody
	@RequestMapping(value = "upd", produces = "application/json;charset=UTF-8")
	private String upd(ProInfoModel u) {
		int a = service.updateActive(u);
		if (a > 0) {
			return "修改成功";
		} else
			return "修改失败";
	}

	@ResponseBody
	@RequestMapping(value = "/list2", produces = "application/json;charset=UTF-8")
	public String list2(ProInfoModel g, Integer page, Integer limit) {
		String code = g.getProCode();
		if (!FmtEmpty.isEmpty(code)) {
			g.setProCode("%" + code + "%");
		}
		String name = g.getProName();
		if (!FmtEmpty.isEmpty(name)) {
			g.setProName("%" + name + "%");
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

	@RequestMapping(value = "/exportExcel", produces = "application/json;charset=UTF-8")
	public void exportExcel(HttpServletResponse response, ProInfoModel h) throws Exception {
		List<ProInfoModel> dataList = service.selectModel(h);
		List<String> propList = Arrays.asList("proCode", "proName", "sum", "cost");
		List<String> fieldName = Arrays.asList("商品编号", "商品名称", "数量", "价格");

		Workbook wb = FormatPOI.exportExcel(dataList, propList, fieldName);
//1)设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
//2)设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=test.xls");
//3）输出流
		OutputStream out = response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}

	@ResponseBody
	@RequestMapping(value = "/uploadExcel", produces = "application/json;charset=UTF-8")
	public String uploadExcel(CommonsMultipartResolver multipartResolver, HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("code", "0");
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				result.put("data", parse(file.getInputStream()));
			}
		}
		return new JSONObject(result).toString();
	}

	private List<ProInfoModel> parse(InputStream fis) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("user");
		List<ProInfoModel> list = new ArrayList<>();
		for (Row row : sheet) {
			if (0 == row.getRowNum()) {
				continue;
			}
			ProInfoModel ProInfoModel = new ProInfoModel();
			ProInfoModel.setProCode(getValue(row.getCell(0)));
			ProInfoModel.setProName(getValue(row.getCell(1)));
			ProInfoModel.setCost(getValue(row.getCell(2)));
			ProInfoModel.setSum(getValue(row.getCell(3)));
			list.add(ProInfoModel);
			reg(ProInfoModel);
		}
		workbook.close();
		fis.close();
		return list;
	}

	private void reg(ProInfoModel ProInfoModel) {
		// TODO Auto-generated method stub

	}

	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if (CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		} else if (CellType.NUMERIC.equals(type)) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
	public String list() {
		List<ProInfoModel> list = service.selectList(new ProInfoModel());
		return new JSONArray(list).toString();
	}

///**
// * 查看被删除的记录
// * @param p
// * @return
// */
//@ResponseBody
//@RequestMapping(value="retrieve",produces="application/json;charset=UTF-8")
//public String retrieve(ProInfoModel p) {
//	p.setDeleted(1);
//	List<ProInfoModel> list = service.selectModel(p);
//	int a=service.selectCount(p);
//	Map<String,Object> map=new HashMap<String, Object>();
//	map.put("data", list);
//	map.put("count", a);
//	map.put("code", 0);
//	return new JSONObject(map).toString();
//}
///**
// * 从回收站里将数据恢复（deleted=1—>0）
// * @param p
// * @return
// */
//@ResponseBody
//@RequestMapping(value="retrieveData",produces="application/json;charset=UTF-8")
//public String retrieveData(ProInfoModel p,String userCode ) {
//	p.setUpdateTime(df.format(new Date()));
//	p.setUpdateBy(userCode);
//	p.setDeleted(0);
//	int a=service.updateActive(p);	
//	if(a>0)
//		return "恢复成功ヽ（≧□≦）ノ";
//	return "恢复失败<。)#)))≦";
//}
///**
// * 从数据库将回收站的数据删除
// * @param p
// * @return
// */
//@ResponseBody
//@RequestMapping(value="delR",produces="application/json;charset=UTF-8")
//public String delR(ProInfoModel p) {
//	int a=service.delete(p);
//	ZiDianModel z=new ZiDianModel();
//	if(a>0) {
//		z.setCode(p.getProCode());
//		
//		Zservice.delete(z);
//			return "已删除成功(ಥ _ ಥ)";
//	}
//	return "失败(￣ ‘i ￣;)";
//}
	
	@RequestMapping("toupd")
	public String toupd(ProInfoModel example, Model model) {
		ProInfoModel IDB = service.selectId(example);
		model.addAttribute("key",IDB);
		return "proinfo/upd";

	}
}
