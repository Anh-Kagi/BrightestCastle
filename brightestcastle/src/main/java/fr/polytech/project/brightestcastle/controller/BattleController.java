package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Game;
import fr.polytech.project.brightestcastle.gameplay.Played;

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
			if (sender < 0 || sender >= battle.getCharacters().size() || battle.getCharacters().get(sender).getPlayed()
					|| battle.getCharacters().get(sender).entity().isAffected(StatusEnum.STUNNED)) {
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
				for (int i = battle.getCharacters().size() - 1; i >= 0; i--)
					if (battle.getCharacters().get(i).entity().getHP() <= 0)
						battle.getCharacters().remove(i);

				// remove dead monsters
				for (int i = battle.getMonsters().size() - 1; i >= 0; i--)
					if (battle.getMonsters().get(i).entity().getHP() <= 0)
						battle.getMonsters().remove(i);
			} else {
				res.sendRedirect("/battle");
				return "blank";
			}
		}

		boolean all_played = true;
		for (Played<Character> c : battle.getCharacters())
			if (!c.getPlayed()) {
				all_played = false;
				break;
			}
		endTurn = all_played ? "" : endTurn;
		if (endTurn != null) {
			if (battle.endTurn()) {
				Game game = (Game) session.getAttribute("game");
				session.removeAttribute("battle");
				game.getSquare().setVisited(true);
				if (battle.won())
					if (game.getSquare().getDistance() == 0)
						res.sendRedirect("/victory");
					else
						res.sendRedirect("/map");
				else
					res.sendRedirect("/gameover");
				return "blank";
			}
		}

		res.sendRedirect("/battle");
		return "blank";
	}
}
