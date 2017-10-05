package com.kx.realdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/realdata/inverter")
public class RealInverterController {
	
	@RequestMapping("/page")
	public String page() {
		return "/realdata/realinverter"; 
	}
}
