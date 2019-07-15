package com.situ.crm.grant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.util.BaiKe;
import com.situ.util.FmtEmpty;

@Controller
@RequestMapping("jsoup")
public class JsoupController {
	
	@ResponseBody
	@RequestMapping(value = "/item", produces = "application/json;charset=UTF-8")
	public String item(String item) {
		if(FmtEmpty.isEmpty(item)) {
			return "";
		}
		try {
			return new BaiKe().getContent2(BaiKe.baseUrl + item);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

}
