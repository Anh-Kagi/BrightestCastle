package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Played;
import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Monster;

@Controller
public class BattleController {
	@GetMapping(path="/battle")
	public String battle(HttpSession session, HttpServletResponse res, Model model) throws IOException {
		Battle battle = (Battle) session.getAttribute("battle");
		if (battle == null) {
			res.sendRedirect("/event");
			return "blank";
		}
		
		model.addAttribute("battle", battle);
		return "square/battle";
	}
	
	@PostMapping(path="/battle")
	public String processBattle(HttpSession session, HttpServletResponse res, @RequestParam(name="endturn", required=false) String endTurn) throws IOException {
		Battle battle = (Battle) session.getAttribute("battle");
		if (battle == null) {
			res.sendRedirect("/event");
			return "blank";
		}
		
		if (endTurn != null) {
			if (battle.endTurn()) {
				if (battle.won()) {
					res.sendRedirect("/map");
					return "blank";
				} else {
					res.sendRedirect("/gameover");
					return "blank";
				}
			}
			
			// reset played
			for (Played<Character> c : battle.getCharacters())
				c.setPlayed(false);
			for (Played<Monster> m : battle.getMonsters())
				m.setPlayed(true);
		}
		return "blank";
	}
}
