package com.kx.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report/inverter")
public class InverterReport {
	
	@RequestMapping("/list")
	public String list() {
		
		return "/report/inverterReport";
	}
}
