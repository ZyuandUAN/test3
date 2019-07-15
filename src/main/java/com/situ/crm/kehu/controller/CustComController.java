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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.situ.crm.kehu.model.CustComModel;
import com.situ.crm.kehu.service.CustComService;
import com.situ.util.FmtEmpty;
import com.situ.util.FormatPOI;

@Controller
@RequestMapping("/custcom")
public class CustComController {
	@Autowired
	private CustComService service;
	private String message;
	@ResponseBody
	@RequestMapping(value="/action",produces="application/json;charset=UTF-8")
	private String action(CustComModel u,String action) {
		if(action!=null) {
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
		}else {return "action空";}
		return message;
		}
	
	private String delete(CustComModel u) {
		int d=service.delete(u);
		if(d==1) {
			// TODO Auto-generated method stub
			return "删除成功";
		}else  
		return "删除失败";
	}
	private String selectOne(CustComModel u) {
		// TODO Auto-generated method stub
		CustComModel uu=service.selectId(u);
		return new JSONObject(uu).toString();
	}
	private String page(CustComModel u) {
		// TODO Auto-generated method stub
		List<CustComModel> s = service.selectModel(u);
		int count =service.selectCount(u);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list",s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}
	private String addOrUpd(CustComModel u, int i) {
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
	@RequestMapping(value = "/list2", produces = "application/json;charset=UTF-8")
	public String list2(CustComModel g, Integer page, Integer limit) {
		String code = g.getCustCode();
		if (!FmtEmpty.isEmpty(code)) {
			g.setCustCode("%" + code + "%");
		}
		String code1 = g.getUserCode();
		if (!FmtEmpty.isEmpty(code1)) {
			g.setUserCode("%" + code1 + "%");
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
	@RequestMapping(value="/exportExcel",produces="application/json;charset=UTF-8")
	public void exportExcel(HttpServletResponse response,CustComModel h)throws Exception{
	List<CustComModel> dataList = service.selectModel(h);
	List<String> propList = Arrays.asList("userCode","custCode","TIME","type","content");
	List<String> fieldName = Arrays.asList("卖家","买家","时间","购买方式","评价");
	
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
	@ResponseBody
	@RequestMapping(value="/uploadExcel",produces="application/json;charset=UTF-8")
public String uploadExcel(CommonsMultipartResolver multipartResolver,HttpServletRequest request)throws Exception{
	Map<String,Object>result = new HashMap<>();
	result.put("code", "0");
	if(multipartResolver.isMultipart(request)) {
	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	Iterator<String>iter = multiRequest.getFileNames();
	while(iter.hasNext()) {
		MultipartFile file = multiRequest.getFile(iter.next().toString());
		result.put("data", parse(file.getInputStream()));
	}
	}
		return new JSONObject(result).toString();
	}

	private List<CustComModel> parse(InputStream fis) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("user");
		List<CustComModel> list = new ArrayList<>();
		for(Row row : sheet) {
			if(0==row.getRowNum()) {
				continue;
			}
			CustComModel CustComModel = new CustComModel();
			CustComModel.setUserCode(getValue(row.getCell(0)));
			CustComModel.setCustCode(getValue(row.getCell(1)));
			CustComModel.setTIME(getValue(row.getCell(2)));
			CustComModel.setType(getValue(row.getCell(3)));
			CustComModel.setContent(getValue(row.getCell(4)));
			list.add(CustComModel);
			reg(CustComModel);
		}
		workbook.close();
		fis.close();
		return list;
	}

 

	private void reg(CustComModel CustComModel) {
		// TODO Auto-generated method stub
		
	}

	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if(CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		}else if(CellType.NUMERIC.equals(type)) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}
}
