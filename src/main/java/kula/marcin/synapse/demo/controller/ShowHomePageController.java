package kula.marcin.synapse.demo.controller;

import kula.marcin.synapse.demo.entity.Student;
import kula.marcin.synapse.demo.entity.Teacher;
import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowHomePageController {

	@Autowired
	private UserService userService;

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		return currentUser;
	}

	@GetMapping("/")
	public String showHomePage(Model model) {

		if(getCurrentUser().getStudent()==null) {

			Teacher currentInstructor = getCurrentUser().getInstructor();
			if (currentInstructor != null)

				model.addAttribute("instructor", currentInstructor);
			else
				model.addAttribute("instructor", new Teacher());

		} else {

			Student student = getCurrentUser().getStudent();
			if(student != null)
				model.addAttribute("student", student);
			else
				model.addAttribute("student", new Student());

		}

		if(getCurrentUser().getStudent()==null && getCurrentUser().getInstructor()==null) {

			model.addAttribute("student", new Student());
			model.addAttribute("instructor", new Teacher());
			model.addAttribute("message", "Uwaga! Musisz uaktualnić swoje dane, aby móc korzystać z platformy." +
					" Jeżeli tego nie zrobisz, platforma może działać błędnie. Kliknij w przycisk 'uaktualnij' i uważnie wprowadź swoje dane.");
		}

		return "home";
	}
	
}
