package kula.marcin.synapse.demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.service.UserService;
import kula.marcin.synapse.demo.user.CrmUser;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

	}

	@GetMapping("/showRegisterPage")
	public String showRegisterPage(Model theModel) {

		theModel.addAttribute("crmUser", new CrmUser());

		return "register";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
			BindingResult theBindingResult, Model theModel) {

		String userName = theCrmUser.getUserName();

		if (theBindingResult.hasErrors()) {
			return "register";
		}

		User existing = userService.findByUserName(userName);
		if (existing != null) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			return "register";
		}

		userService.save(theCrmUser);

		return "registration-confirmation";
	}
}
