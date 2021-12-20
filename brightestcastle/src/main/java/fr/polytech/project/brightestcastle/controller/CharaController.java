package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.polytech.project.brightestcastle.forms.CharaForm;
import fr.polytech.project.brightestcastle.gameplay.Game;

@Controller
public class CharaController {
	@PostMapping(path="/chara")
	public String chara(HttpServletRequest req, HttpServletResponse res, @ModelAttribute CharaForm form, Model model) throws IOException {
		req.getSession().setAttribute("chara", form);
		if (!form.isValid()) {
			res.setStatus(302);
			res.sendRedirect("/?invalid");
			return "blank";
		}

		model.addAttribute("chara", form);
		req.getSession().setAttribute("game", new Game(12, 6));
		return "chara";
	}
}
