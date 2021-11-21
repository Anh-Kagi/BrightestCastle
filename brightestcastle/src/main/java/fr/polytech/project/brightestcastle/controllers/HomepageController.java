package fr.polytech.project.brightestcastle.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.polytech.project.brightestcastle.forms.CharaForm;

@Controller
public class HomepageController {
	@GetMapping(path= {"", "/"})
	public String index(HttpServletRequest req, Model model, @RequestParam(name="invalid", required=false) String invalid) {
		CharaForm chara = (CharaForm) req.getSession().getAttribute("chara");
		
		if (chara == null || !(chara instanceof CharaForm))
			chara = new CharaForm();
		
		model.addAttribute("form", chara);
		model.addAttribute("invalid", invalid != null);
		return "index";
	}
	
	@PostMapping(path="/chara")
	public String chara(HttpServletRequest req, HttpServletResponse res, @ModelAttribute CharaForm form, Model model) throws IOException {
		req.getSession().setAttribute("chara", form);
		if (form.isValid()) {
			model.addAttribute("chara", form);
			return "chara";
		} else {
			res.setStatus(302);
			res.sendRedirect("/?invalid");
			return "blank";
		}
	}
}
