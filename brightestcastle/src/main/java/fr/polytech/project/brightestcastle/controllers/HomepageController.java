package fr.polytech.project.brightestcastle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path={"","/"})
public class HomepageController {
	@GetMapping(path= {"", "/"})
	public String index() {
		return "index";
	}
}
