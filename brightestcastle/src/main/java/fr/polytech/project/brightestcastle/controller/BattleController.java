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
import fr.polytech.project.brightestcastle.gameplay.Game;

@Controller
public class BattleController {
	@GetMapping(path = "/battle")
	public String battle(HttpSession session, HttpServletResponse res, Model model,
			@RequestParam(name = "sender", required = false) Byte sender,
			@RequestParam(name = "attack", required = false) Byte attack,
			@RequestParam(name = "target", required = false) Byte target) throws IOException {
		Battle battle = (Battle) session.getAttribute("battle");
		if (battle == null) {
			res.sendRedirect("/event");
			return "blank";
		}

		if (sender != null) {
			if (sender < 0 || sender >= battle.getCharacters().size()
					|| battle.getCharacters().get(sender).getPlayed()) {
				sender = null;
				attack = null;
				target = null;
			} else if (attack != null) {
				if (attack < 0 || attack >= battle.getCharacters().get(sender).entity().getAttacks().size()) {
					attack = null;
					target = null;
				} else if (!battle.getCharacters().get(sender).entity().getAttacks().get(attack).needTarget()
						&& target != null && (target < 0 || target >= battle.getMonsters().size())) {
					target = null;
				}
			}
		}

		model.addAttribute("sender", sender);
		model.addAttribute("attack", attack);
		model.addAttribute("target", target);
		model.addAttribute("battle", battle);
		return "square/battle";
	}

	@PostMapping(path = "/battle")
	public String processBattle(HttpSession session, HttpServletResponse res,
			@RequestParam(name = "sender", required = false) Byte sender,
			@RequestParam(name = "attack", required = false) Byte attack,
			@RequestParam(name = "target", required = false) Byte target,
			@RequestParam(name = "endturn", required = false) String endTurn) throws IOException {
		Battle battle = (Battle) session.getAttribute("battle");
		if (battle == null) {
			res.sendRedirect("/event");
			return "blank";
		}

		if (sender != null && attack != null
				&& (!battle.getCharacters().get(sender).entity().getAttacks().get(attack).needTarget()
						|| target != null)) {
			target = (target == null ? 0 : target);
			
			if (battle.attack(sender, target, attack)) {
				// remove dead characters
				for (int i=battle.getCharacters().size()-1; i>=0; i--)
					if (battle.getCharacters().get(i).entity().getHP() <= 0)
						battle.getCharacters().remove(i);
				if (battle.getCharacters().size() == 0) {
					session.removeAttribute("battle");
					res.sendRedirect("/gameover");
					return "blank";
				}
					
				// remove dead monsters
				for (int i=battle.getMonsters().size()-1; i>=0; i--)
					if (battle.getMonsters().get(i).entity().getHP() <= 0)
						battle.getMonsters().remove(i);
				if (battle.getMonsters().size() == 0) {
					session.removeAttribute("battle");
					((Game) session.getAttribute("game")).getSquare().setVisited(true);
					res.sendRedirect("/map");
					return "blank";
				}
			} else {
				res.sendRedirect("/battle");
				return "blank";
			}
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
		}

		res.sendRedirect("/battle");
		return "blank";
	}
}
