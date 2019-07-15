package com.situ.crm.grant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.situ.crm.grant.model.RoleModel;
import com.situ.crm.grant.service.RoleService;
import com.situ.util.FmtEmpty;


@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private  RoleService  service;
	private String message;

	@ResponseBody
	@RequestMapping(value = "/action", produces = "application/json;charset=UTF-8")
	private String action(RoleModel u, String action) {
		if (!action.isEmpty()) {
			switch (action) {
			case "add":
				message = addOrUpd(u, 1);
				break;
			case "list":
				message = page(u);
				break;
			case "del":
				message = delete(u);
				break;
			case "upd":
				message = selectOne(u);
				break;
			case "upds":
				message = addOrUpd(u, 2);
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
	private String delete(RoleModel u) {
		int d = service.delete(u);
		if (d == 1) {
			// TODO Auto-generated method stub
			return "删除成功";
		} else
			return "删除失败";
	}

	private String selectOne(RoleModel u) {
		// TODO Auto-generated method stub
		RoleModel uu = service.selectId(u);
		return new JSONObject(uu).toString();
	}

	private String page(RoleModel u) {
		// TODO Auto-generated method stub
		List<RoleModel> s = service.selectModel(u);
		int count = service.selectCount(u);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}

	private String addOrUpd(RoleModel u, int i) {
		// TODO Auto-generated method stub
		if (i == 1) {
			int a = service.insert(u);
			if (a > 0) {
				return "录入成功";
			} else
				return "录入失败";
		} else {
			int a = service.updateActive(u);
			if (a > 0) {
				return "修改成功";
			} else
				return "修改失败";
		}
	}

	@ResponseBody
	@RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
	private List<RoleModel> list(RoleModel g) {
		g.setOrderby("name");
		List<RoleModel> list = service.selectList(g);
		for(RoleModel aa:list) {
			System.out.println("---"+aa);
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/list2", produces = "application/json;charset=UTF-8")
	public String list2(RoleModel g, Integer page, Integer limit) {
		String code = g.getRoleCode();
		if (!FmtEmpty.isEmpty(code)) {
			g.setRoleCode("%" + code + "%");
		}
		String name = g.getRoleName();
		if (!FmtEmpty.isEmpty(name)) {
			g.setRoleName("%" + name + "%");
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