package com.kx.realdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/realdata/home")
public class HomeDataController {

	@RequestMapping("/page")
	public String page() {
		return "/realdata/homepage";
	}
	
}
