package org.quick.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {

	@RequestMapping(value = "/toLogin.htm")
	public ModelAndView index_sys(Model model) {
		ModelAndView m=new ModelAndView("login");
		return m;
	}
	
	@RequestMapping(value = "/index.htm")
	public ModelAndView index_user(Model model) {
		ModelAndView m=new ModelAndView("index");
		return m;
	}
}
