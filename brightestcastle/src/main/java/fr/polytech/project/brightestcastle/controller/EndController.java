package fr.polytech.project.brightestcastle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EndController {
	@GetMapping(path="/gameover")
	public String gameover(HttpSession session, HttpServletResponse res) throws IOException {
		session.removeAttribute("battle");
		session.removeAttribute("game");
		return "end/gameover";
	}
}
