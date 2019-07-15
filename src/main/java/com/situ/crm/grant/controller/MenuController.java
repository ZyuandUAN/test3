package com.situ.crm.grant.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.situ.crm.grant.model.MenuModel;
import com.situ.crm.grant.service.MenuService;
import com.situ.util.FmtEmpty;

@Controller
@RequestMapping("/Menu")
public class MenuController {
	@Autowired
	private MenuService service;

	@ResponseBody
	@RequestMapping(value = "del", produces = "application/json;charset=UTF-8")
	private String del(MenuModel u) {
		int d = service.delete(u);
		if (d == 1) {
			// TODO Auto-generated method stub
			return "删除成功";
		} else
			return "删除失败";
	}

	@ResponseBody
	@RequestMapping(value = "selectOne", produces = "application/json;charset=UTF-8")
	private String selectOne(MenuModel u) {
		// TODO Auto-generated method stub
		MenuModel uu = service.selectId(u);
		return new JSONObject(uu).toString();
	}

	@ResponseBody
	@RequestMapping(value = "page", produces = "application/json;charset=UTF-8")
	private String page(MenuModel u) {
		// TODO Auto-generated method stub
		List<MenuModel> s = service.selectModel(u);
		int count = service.selectCount(u);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}

	@ResponseBody
	@RequestMapping(value = "add", produces = "application/json;charset=UTF-8")
	private String add(MenuModel u) {
			int a = service.insert(u);
			if (a > 0) {
				return "录入成功";
			} else
				return "录入失败";
	}
	@ResponseBody
	@RequestMapping(value = "Upd", produces = "application/json;charset=UTF-8")
	private String Upd(MenuModel u) {
            int a = service.updateActive(u);
			if (a > 0) {
				return "修改成功";
			} else
				return "修改失败";
	}

	@ResponseBody
	@RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
	private List<MenuModel> list(MenuModel g) {
		g.setOrderby("name");
		List<MenuModel> list = service.selectList(g);
		System.out.println(list);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/list2", produces = "application/json;charset=UTF-8")
	public String list2(MenuModel g, Integer page, Integer limit) {
		String code = g.getMenuCode();
		if (!FmtEmpty.isEmpty(code)) {
			g.setMenuCode("%" + code + "%");
		}
		String name = g.getMenuName();
		if (!FmtEmpty.isEmpty(name)) {
			g.setMenuName("%" + name + "%");
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
}