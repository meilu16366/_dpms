package com.kx.da.controller;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/da/charts")
public class RidainController {

	@RequestMapping("/ridian")
	public String ridian(Model model) {
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		model.addAttribute("date", date);
		return "/report/ridian";
	}
	
	@RequestMapping("/capbar")
	public String capbar(Model model) {
		String date = DateFormatUtils.format(new Date(), "yyyy-MM");
		model.addAttribute("month", date);
		return "/report/capbar";
	}
	
	@RequestMapping("/conf")
	public String conf() {
		
		return "/conf/userconf";
	}
}
