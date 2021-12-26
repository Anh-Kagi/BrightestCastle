package fr.polytech.project.brightestcastle.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EndController {
	@GetMapping(path="/gameover")
	public String gameover(HttpSession session, HttpServletResponse res) {
		session.removeAttribute("battle");
		session.removeAttribute("game");
		return "end/gameover";
	}
	
	@GetMapping(path="/victory")
	public String victory(HttpSession session, HttpServletResponse res) {
		session.removeAttribute("battle");
		session.removeAttribute("game");
		return "end/vistory";
	}
}
