package com.situ.crm.grant.controller;

 
import java.util.HashMap;
 
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.grant.model.RelModel;
import com.situ.crm.grant.service.RelService;
import com.situ.util.FmtEmpty;

@Controller
@RequestMapping("/rel")
public class RelController {
	@Autowired
	private  RelService  service;
	 
//	@RequestMapping("login1")
//	public String toLogin(){
//	return "login";
//	}
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=UTF-8")
	private String delete(RelModel u) {
		int d=service.delete(u);
		if(d==1) {
			// TODO Auto-generated method stub
			return "删除成功";
		}else  
		return "删除失败";
	}
	@ResponseBody
	@RequestMapping(value="/selectOne",produces="application/json;charset=UTF-8")
	private String selectOne(RelModel u) {
		// TODO Auto-generated method stub
		RelModel uu=service.selectId(u);
		return new JSONObject(uu).toString();
	}
	@ResponseBody
	@RequestMapping(value="/page",produces="application/json;charset=UTF-8")
	private String page(RelModel u) {
		// TODO Auto-generated method stub
		List<RelModel> s = service.selectModel(u);
		int count =service.selectCount(u);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list",s);
		map.put("count", count);
		return new JSONObject(map).toString();

	}
	@ResponseBody
	@RequestMapping(value="/add",produces="application/json;charset=UTF-8")
	private String add(RelModel u) {
		// TODO Auto-generated method stub
			int a=service.insert(u);
			if(a>0) {
				return "录入成功";
			}else return "录入失败";
	}
	@ResponseBody
	@RequestMapping(value="/Upd",produces="application/json;charset=UTF-8")
	private String Upd(RelModel u) {
			int a=service.updateActive(u);
			if(a>0) {
				return "修改成功";
			}else return "修改失败";
		}
	@ResponseBody
	@RequestMapping(value="list",produces="application/json;charset=UTF-8")
	private String list(RelModel g) {
    	g.setOrderby("name");
    	List<RelModel> list = service.selectList(g);
    	return new JSONObject(list).toString();
		}
	@ResponseBody
	@RequestMapping(value="/list2",produces="application/json;charset=UTF-8")
	public String list2(RelModel g,Integer page,Integer limit) {
 String code = g.getRoleCode();
 if(!FmtEmpty.isEmpty(code)) {
	 g.setRoleCode("%"+ code +"%");
 }
 String code1 = g.getMenuCode();
 if(!FmtEmpty.isEmpty(code1)) {
	 g.setMenuCode("%"+ code1 +"%");
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
}