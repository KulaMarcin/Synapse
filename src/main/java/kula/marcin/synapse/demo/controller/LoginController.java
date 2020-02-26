package kula.marcin.synapse.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "fancy-zlogin";
		
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

}