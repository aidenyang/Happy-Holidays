package com.aidenyang.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController{
	
	@RequestMapping(value = "/error")
	public String error() {
		return "ERROR: Access denied or not a valid endpoint."; // Replace this with a nicer pager
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
