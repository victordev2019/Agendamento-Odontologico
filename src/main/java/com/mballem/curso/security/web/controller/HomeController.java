package com.mballem.curso.security.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// abrir pagina home
	@GetMapping({"/" , "/home"})
	public String home() {
		return "home";
	}	
	
	// abrir pagina de login
		@GetMapping({"/login"})
		public String login() {
			return "login";
		}	
		
		// login inválido
		@GetMapping({"/login-error"})
		public String loginError(ModelMap model) {
			model.addAttribute("alerta", "erro");
			model.addAttribute("titulo", "Credenciais inválidas!");
			model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
			model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
			return "login";
		}		
		
		//acesso negado
		@GetMapping({"/acesso-negado"})
		public String acessoNegado(ModelMap model, HttpServletResponse resp) {
			model.addAttribute("status", resp.getStatus());
			model.addAttribute("error", "Acesso Negado!");
			model.addAttribute("message", "Você não tem permissão para  acesso a esta área ou ação.");
			
			return "error";
}

}