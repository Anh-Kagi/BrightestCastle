package fr.polytech.project.brightestcastle.controller;

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
import fr.polytech.project.brightestcastle.gameplay.Game;
import fr.polytech.project.brightestcastle.gameplay.map.Direction;

@Controller
public class HomepageController {
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
	
	@GetMapping(path="/map")
	public String grid(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		if (req.getSession().getAttribute("game") == null) {
			res.sendRedirect("/");
			return "blank";
		}
		
		// TODO debug
		if (req.getParameter("reset") != null)
			req.getSession().setAttribute("game", new Game(12, 6));
		
		model.addAttribute("map", ((Game) req.getSession().getAttribute("game")).getMap());
		model.addAttribute("game", req.getSession().getAttribute("game"));
		return "grid";
	}
	
	@PostMapping(path="/map")
	public String move(HttpServletRequest req, HttpServletResponse res, @RequestParam(name="direction") Direction direction, Model model) throws IOException {
		if (req.getSession().getAttribute("game") == null) {
			res.sendRedirect("/");
			return "blank";
		}
		
		Game game = (Game) req.getSession().getAttribute("game");
		game.move(direction);
		
		res.sendRedirect("/map");
		return "blank";
	}
}
