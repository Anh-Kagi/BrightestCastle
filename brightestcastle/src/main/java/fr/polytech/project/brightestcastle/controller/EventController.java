package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Game;

@Controller
public class EventController {
	@GetMapping(path="/event")
	public String event(HttpSession session, HttpServletResponse res) throws IOException {
		// if player didn't start a game
		Game game = (Game) session.getAttribute("game");
		if (game == null) {
			res.sendRedirect("/");
			return "blank";
		}
		
		// if the current event has already been triggered
		if (game.getSquare().getVisited()) {
			res.sendRedirect("/map");
			return "blank";
		}
		
		// if a battle already started
		if (session.getAttribute("battle") != null) {
			res.sendRedirect("/battle");
			return "blank";
		}
		
		switch(game.getSquare().getType()) {
		case EMPTY:
			res.sendRedirect("/map");
			game.getSquare().setVisited(true);
			return "blank";
		case BOSS:
			res.sendRedirect("/map"); // tmp
			game.getSquare().setVisited(true); //tmp
			// TODO: boss fight
			return "blank";
		case FIGHT:
			session.setAttribute("battle", Battle.generate(game.getGroup(), game.getSquare().getDistance()));
			res.sendRedirect("/battle");
			return "blank";
		case LOOT:
			// TODO: give loot
			game.getSquare().setVisited(true);
			return "square/loot";
		case CAMP:
			// TODO: heal characters
			return "square/camp";
		default:
			// should not happen
			game.getSquare().setVisited(true);
			res.sendRedirect("/map");
			return "blank";
		}
	}
}
