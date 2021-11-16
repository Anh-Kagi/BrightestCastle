package fr.polytech.project.brightestcastle.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.polytech.project.brightestcastle.forms.CharaForm;

@Controller
@RequestMapping(path={"","/"})
public class HomepageController {
	@GetMapping(path= {"", "/"})
	public String index(HttpServletRequest req, Model model) {
		CharaForm chara = (CharaForm) req.getSession().getAttribute("chara");
		
		if (chara == null || !(chara instanceof CharaForm))
			chara = new CharaForm();
		
		model.addAttribute("CharaForm", chara);
		return "index";
	}
	
	@PostMapping(path="/createchara")
	public String createChara(HttpServletRequest req, @ModelAttribute CharaForm form, Model model) {
		req.getSession().setAttribute("chara", form);
		model.addAttribute("chara", form);
		return "createchara";
	}
}
