package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.polytech.project.brightestcastle.gameplay.Game;

@Controller
public class EventController {
	@GetMapping(path="/event")
	public String event(HttpServletResponse res, HttpSession session) throws IOException {
		Game game = (Game) session.getAttribute("game");
		if (game == null) {
			res.sendRedirect("/");
			return "blank";
		}
		
		if (game.getSquare().getVisited()) {
			res.sendRedirect("/map");
			return "blank";
		}
		
		switch(game.getSquare().getType()) {
		case EMPTY:
			res.sendRedirect("/map");
			game.getSquare().setVisited(true);
			return "blank";
		case BOSS:
			res.sendRedirect("/map");
			game.getSquare().setVisited(true);
			// TODO: boss fight
			return "blank";
		case FIGHT:
			res.sendRedirect("/map");
			game.getSquare().setVisited(true);
			// TODO: fight
			return "blank";
		case LOOT:
			// TODO: give loot
			game.getSquare().setVisited(true);
			return "square/loot";
		case CAMP:
			// TODO: heal characters
			game.getSquare().setVisited(true);
			return "square/camp";
		default:
			// should not happen
			game.getSquare().setVisited(true);
			res.sendRedirect("/map");
			return "blank";
		}
	}
}
