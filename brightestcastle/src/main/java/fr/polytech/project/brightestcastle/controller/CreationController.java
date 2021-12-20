package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.polytech.project.brightestcastle.forms.CharaForm;

@Controller
public class CreationController {
	@GetMapping(path= {"", "/"})
	public String index(HttpServletRequest req, HttpServletResponse res, Model model, @RequestParam(name="invalid", required=false) String invalid) throws IOException {
		if (req.getSession().getAttribute("game") != null) {
			res.sendRedirect("/map");
			return "blank";
		}
		
		CharaForm chara = (CharaForm) req.getSession().getAttribute("chara");
		
		if (chara == null || !(chara instanceof CharaForm))
			chara = new CharaForm();
		
		model.addAttribute("form", chara);
		model.addAttribute("invalid", invalid != null);
		return "index";
	}
}
