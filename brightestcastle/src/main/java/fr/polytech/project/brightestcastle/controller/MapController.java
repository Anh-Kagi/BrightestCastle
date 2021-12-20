package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.polytech.project.brightestcastle.gameplay.Game;
import fr.polytech.project.brightestcastle.gameplay.map.Direction;

@Controller
public class MapController {
	@GetMapping(path="/map")
	public String grid(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		Game game = (Game) req.getSession().getAttribute("game");
		if (game == null) {
			res.sendRedirect("/");
			return "blank";
		}
		
		if (!game.getMap().getSquare(game.getPos()).getVisited()) {
			res.sendRedirect("/event");
			return "blank";
		}
		
		// TODO remove for prod
		if (req.getParameter("reset") != null)
			req.getSession().setAttribute("game", new Game(12, 5));
		
		model.addAttribute("map", game.getMap());
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
		if (game.move(direction)) // if square was not visited
			res.sendRedirect("/event");
		else
			res.sendRedirect("/map");
		return "blank";
	}
}
