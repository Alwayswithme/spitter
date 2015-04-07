package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {
    @Value("${url}")
    private String dburl;

	@RequestMapping("welcome")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping("url")
    public String printUrl(ModelMap model) {
        // value injection
        model.addAttribute("message", dburl);
        return "hello";
    }
}