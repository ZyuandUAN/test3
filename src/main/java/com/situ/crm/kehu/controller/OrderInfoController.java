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

import com.situ.crm.kehu.model.OrderInfoModel;
import com.situ.crm.kehu.model.ProInfoModel;
import com.situ.crm.kehu.service.OrderInfoService;
import com.situ.util.FmtEmpty;
import com.situ.util.FormatPOI;

@Controller
@RequestMapping("/orderinfo")
public class OrderInfoController {
	@Autowired
	private OrderInfoService service;

	@ResponseBody
	@RequestMapping(value = "del", produces = "application/json;charset=UTF-8")
	private String del(OrderInfoModel u) {
		int d = service.delete(u);
		if (d == 1) {
			// TODO Auto-generated method stub
			return "删除成功";
		} else
			return "删除失败";
	}

	@ResponseBody
	@RequestMapping(value = "selectOne", produces = "application/json;charset=UTF-8")
	private String selectOne(OrderInfoModel u) {
		// TODO Auto-generated method stub
		OrderInfoModel uu = service.selectId(u);
		return new JSONObject(uu).toString();
	}

	@ResponseBody
	@RequestMapping(value = "page", produces = "application/json;charset=UTF-8")
	private String page(OrderInfoModel u) {
		// TODO Auto-generated method stub
		List<OrderInfoModel> s = service.selectModel(u);
		int count = service.selectCount(u);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}

	@ResponseBody
	@RequestMapping(value = "add", produces = "application/json;charset=UTF-8")
	private String add(OrderInfoModel u) {
		int a = service.insert(u);
		if (a > 0) {
			return "录入成功";
		} else
			return "录入失败";
	}

	@ResponseBody
	@RequestMapping(value = "upd", produces = "application/json;charset=UTF-8")
	private String upd(OrderInfoModel u) {
		System.out.println(u+"1111");
		int a = service.updateActive(u);
		if (a > 0) {
			return "修改成功";
		} else
			return "修改失败";
	}
	@ResponseBody
	@RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
	private List<OrderInfoModel> list(OrderInfoModel g) {
		g.setOrderby("name");
		List<OrderInfoModel> list = service.selectList(g);
		for (OrderInfoModel aa : list) {
			System.out.println("---" + aa);
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/list2", produces = "application/json;charset=UTF-8")
	public String list2(OrderInfoModel g, Integer page, Integer limit) {
		String code = g.getCustCode();
		if (!FmtEmpty.isEmpty(code)) {
			g.setCustCode("%" + code + "%");
		}
		String code1 = g.getUserCode();
		if (!FmtEmpty.isEmpty(code1)) {
			g.setUserCode("%" + code1 + "%");
		}
		String code2 = g.getProCode();
		if (!FmtEmpty.isEmpty(code2)) {
			g.setProCode("%" + code2 + "%");
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
	public void exportExcel(HttpServletResponse response, OrderInfoModel h) throws Exception {
		List<OrderInfoModel> dataList = service.selectModel(h);
		List<String> propList = Arrays.asList("userCode", "custCode", "proCode", "count", "TIME", "status");
		List<String> fieldName = Arrays.asList("卖家", "买家", "商品", "库存", "时间", "订单状态");

		Workbook wb = FormatPOI.exportExcel(dataList, propList, fieldName);
//1)设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=test.xls");
		// 3）输出流
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

	private List<OrderInfoModel> parse(InputStream fis) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("user");
		List<OrderInfoModel> list = new ArrayList<>();
		for (Row row : sheet) {
			if (0 == row.getRowNum()) {
				continue;
			}
			OrderInfoModel OrderInfoModel = new OrderInfoModel();
			OrderInfoModel.setUserCode(getValue(row.getCell(0)));
			OrderInfoModel.setCustCode(getValue(row.getCell(1)));
			OrderInfoModel.setProCode(getValue(row.getCell(2)));
			OrderInfoModel.setCount(getValue(row.getCell(3)));
			OrderInfoModel.setTIME(getValue(row.getCell(4)));
			OrderInfoModel.setStatus(getValue(row.getCell(5)));
			list.add(OrderInfoModel);
			reg(OrderInfoModel);
		}
		workbook.close();
		fis.close();
		return list;
	}

	private void reg(OrderInfoModel OrderInfoModel) {
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
	@RequestMapping("xiaoliang")
	public String xiaoliang(OrderInfoModel o) {
		return new JSONArray(service.selectSum()).toString();
	}
	@RequestMapping("toupd")
	public String toupd(OrderInfoModel example, Model model) {
		OrderInfoModel IDB = service.selectId(example);
		model.addAttribute("key",IDB);
		return "orderinfo/upd";

	}
}
