package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.forms.CharaForm;
import fr.polytech.project.brightestcastle.gameplay.Game;

@Controller
public class CreationController {
	@GetMapping(path={"", "/"})
	public String index(HttpSession session, HttpServletResponse res, Model model, @RequestParam(name="invalid", required=false) String invalid) throws IOException {
		// if player already started a game
		if (session.getAttribute("game") != null) {
			res.sendRedirect("/map");
			return "blank";
		}
		
		CharaForm chara = (CharaForm) session.getAttribute("chara");
		
		if (chara == null || !(chara instanceof CharaForm))
			chara = new CharaForm();
		
		model.addAttribute("form", chara);
		model.addAttribute("invalid", invalid != null);
		return "index";
	}

	@PostMapping(path={"", "/"})
	public String chara(HttpSession session, HttpServletResponse res, @ModelAttribute CharaForm form, Model model) throws IOException {
		// if player already started a game
		if (session.getAttribute("game") != null) {
			res.sendRedirect("/map");
			return "blank";
		}
		// if the data the player sent is invalid
		session.setAttribute("chara", form);
		if (!form.isValid()) {
			res.setStatus(302);
			res.sendRedirect("/?invalid");
			return "blank";
		}
		
		model.addAttribute("chara", form);
		Game game = new Game(12, 6);
		game.getGroup().add(form.toCharacter());
		for (int i=0; i<2; i++)
			game.getGroup().add(Character.generate());
		session.setAttribute("game", game);
		return "chara";
	}
}
