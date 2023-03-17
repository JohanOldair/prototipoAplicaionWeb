package com.practica.anime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesoController {


	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}
	
}
