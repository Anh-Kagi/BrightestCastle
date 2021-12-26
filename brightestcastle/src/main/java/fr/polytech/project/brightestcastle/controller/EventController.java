package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Event;
import fr.polytech.project.brightestcastle.gameplay.Game;

@Controller
public class EventController {
	@GetMapping(path="/event")
	public String event(HttpSession session, HttpServletResponse res, Model model) throws IOException {
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
		case FIGHT:
			session.setAttribute("battle", Battle.generate(game.getGroup(), game.getMap().getBossProximity(game.getPos())));
			res.sendRedirect("/battle");
			return "blank";
		case EVENT:
			game.getSquare().setVisited(true);
			model.addAttribute("event", Event.applyEvent(game.getGroup()));
			return "square/event";
		case CAMP:
			game.getSquare().setVisited(true);
			for (Character c : game.getGroup())
				c.setHP(c.getHPmax());
			return "square/camp";
		default:
			// should not happen
			game.getSquare().setVisited(true);
			res.sendRedirect("/map");
			return "blank";
		}
	}
}
