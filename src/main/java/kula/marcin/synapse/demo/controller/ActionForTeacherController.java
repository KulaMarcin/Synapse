package kula.marcin.synapse.demo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kula.marcin.synapse.demo.entity.Test;
import kula.marcin.synapse.demo.entity.Teacher;
import kula.marcin.synapse.demo.entity.Student;
import kula.marcin.synapse.demo.entity.Question;
import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.service.TestService;
import kula.marcin.synapse.demo.service.TeacherService;
import kula.marcin.synapse.demo.service.StudentService;
import kula.marcin.synapse.demo.service.QuestionService;
import kula.marcin.synapse.demo.service.UserService;

/*
* TODO:
*  field injection should be replace to constructor injection
*  some business logic should be moved to @Service
* */

@Controller
public class ActionForTeacherController {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private TestService testService;
	
	@Autowired
	private QuestionService questionService;
	
	public Teacher getCurrentInstructor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		Teacher currentInstructor = currentUser.getInstructor();
		return currentInstructor;
	}

	public User getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		return currentUser;
	}

	@GetMapping("/deleteMyStudent")
	public String deleteMyStudent(@RequestParam("studentId") int studentId) {

		Teacher currentInstructor = this.getCurrentInstructor();
		List<Student> studentsOfTheCurrentInstructor = currentInstructor.getStudents();
		Student studentToDelete = studentService.findById(studentId);

		if (studentsOfTheCurrentInstructor.contains(studentToDelete)) {
			studentsOfTheCurrentInstructor.remove(studentToDelete);
		}

		teacherService.save(currentInstructor);

		return "redirect:/showMyStudents";
	}

	@GetMapping("/addStudent")
	public String addStudent(@RequestParam("studentId") int studentId) {
		
		Teacher currentInstructor = this.getCurrentInstructor();
		Student studentToAdd = studentService.findById(studentId);
		currentInstructor.addStudent(studentToAdd);
		studentService.save(studentToAdd);
		
		return "redirect:/showAllStudents";
	}

	@PostMapping("/saveOrUpdateTeacher")
	public String saveUserDetailsInstructor(@ModelAttribute("instructor") Teacher instructor) {

		Teacher currentTeacher = this.getCurrentInstructor();
		User currentUser = this.getCurrentUser();

		if (currentTeacher == null) {
			currentUser.setInstructor(instructor);
			teacherService.save(instructor);
		} else {
			teacherService.save(instructor);
		}

		return "redirect:/showTeacherDetail";
	}

	@GetMapping("/updateTeacher")
	public String updateTeacher(Model model) {

		Teacher currentTeacher = this.getCurrentInstructor();

		if (currentTeacher != null)
			model.addAttribute("instructor", currentTeacher);
		else
			model.addAttribute("instructor", new Teacher());

		return "teacher/save-or-update-current-teacher-page";
	}

	@PostMapping("/addCourse")
	public String addCourse(@ModelAttribute("course") Test course) {
		
		Teacher currentInstructor = this.getCurrentInstructor();
		currentInstructor.addTest(course);
		testService.save(course);
		
		return "redirect:/showTeacherCourses";
	}

	@GetMapping("/deleteQuestion")
	public String deleteQuestion(@RequestParam("questionId") int questionId, Model model) {

		Question question = questionService.findById(questionId);
		int courseId = question.getCourse().getId();
		questionService.deleteById(questionId);
		Test course = testService.findById(courseId);
		List<Question> questionsForCourse = course.getQuestions();
		model.addAttribute("questions", questionsForCourse);

		return "teacher/course-questions-page";
	}
	
	@PostMapping("/updateQuestionAction")
	public String updateQuestionAction(@ModelAttribute("questionToUpdate") Question questionToUpdate, Model model ) {
		
		Test test = testService.findById(questionToUpdate.getCourseId());
		test.addQuestion(questionToUpdate);
		questionService.save(questionToUpdate);
		List<Question> questionsForCourse = test.getQuestions();
		model.addAttribute("questions", questionsForCourse);

		return "teacher/course-questions-page";
	}
	
	@PostMapping("/addNewQuestionToCourseAction")
	public String addNewQuestionToCourseAction(@ModelAttribute ("newQuestion") Question newQuestion, Model model) {
		
		Test test = testService.findById(newQuestion.getCourseId());
		test.addQuestion(newQuestion);
		questionService.save(newQuestion);
		List<Question> questionsForCourse = test.getQuestions();
		model.addAttribute("questions", questionsForCourse);
		model.addAttribute("courseId", newQuestion.getCourse().getId());

		return "teacher/course-questions-page";
	}
	
	@GetMapping("/searchStudentsByLastName")
	public String searchStudentsByLastName(@RequestParam("studentLastName") String studentLastName, Model model) {
		
		Teacher currentTeacher = this.getCurrentInstructor();
		List<Student> studentsOfTheCurrentInstructor = currentTeacher.getStudents();
		List<Student> allStudents = studentService.findByLastName(studentLastName);

		for (int i = 0; i < studentsOfTheCurrentInstructor.size(); i++) {
			if (allStudents.contains(studentsOfTheCurrentInstructor.get(i))) {
				allStudents.remove(studentsOfTheCurrentInstructor.get(i));
			}
		}

		model.addAttribute("students", allStudents);

		return "teacher/all-students-page";
	}

	@GetMapping("/searchStudentsByClass")
	public String searchStudentsByClass(@RequestParam("searchClass") String searchClass, Model model) {
		
		Teacher currentInstructor = this.getCurrentInstructor();
		List<Student> studentsOfTheCurrentInstructor = currentInstructor.getStudents();
		List<Student> allStudents = studentService.findByStudentClass(searchClass);

		for (int i = 0; i < studentsOfTheCurrentInstructor.size(); i++) {
			if (allStudents.contains(studentsOfTheCurrentInstructor.get(i))) {
				allStudents.remove(studentsOfTheCurrentInstructor.get(i));
			}
		}

		model.addAttribute("students", allStudents);

		return "teacher/all-students-page";
	}
	
	@GetMapping("/searchInMyStudentsByLastName")
	public String searchInMyStudentsByLastName(@RequestParam("studentLastName") String studentLastName, Model model) {
		
		Teacher currentInstructor = this.getCurrentInstructor();
		List<Student> allStudentsOfTheCurrentInstructor = currentInstructor.getStudents();
		List<Student> studentsOfTheCurrentInstructor = new LinkedList<Student>();
		
		for(Student x : allStudentsOfTheCurrentInstructor) {
			if(x.getLastName().equals(studentLastName))
				studentsOfTheCurrentInstructor.add(x);
		}
		
		model.addAttribute("myStudents", studentsOfTheCurrentInstructor);
		return "teacher/current-teacher-students-page";
	}
	
	@GetMapping("/searchInMyStudentsByClass")
	public String searchInMyStudentsByClass(@RequestParam("searchClass") String searchClass, Model model) {
		
		Teacher currentInstructor = this.getCurrentInstructor();
		List<Student> allStudentsOfTheCurrentInstructor = currentInstructor.getStudents();
		List<Student> studentsOfTheCurrentInstructor = new LinkedList<Student>();
		
		for(Student x : allStudentsOfTheCurrentInstructor) {
			if(x.getStudentClass().equals(searchClass))
				studentsOfTheCurrentInstructor.add(x);
		}
		
		model.addAttribute("myStudents", studentsOfTheCurrentInstructor);
		return "teacher/current-teacher-students-page";
	}

	@GetMapping("/searchTestsByDestinyFor")
	public String searchTestsByDestinyFor(@RequestParam("destinyFor") String destinyFor, Model model) {

		Teacher currentInstructor = this.getCurrentInstructor();
		List<Test> allCoursesOfTheCurrentInstructor = currentInstructor.getCourses();
		List<Test> coursesOfTheCurrentInstructor = new ArrayList<Test>();

		for(Test x : allCoursesOfTheCurrentInstructor){
			if(x.getDestinyFor().equals(destinyFor))
				coursesOfTheCurrentInstructor.add(x);
		}

		model.addAttribute("courses", coursesOfTheCurrentInstructor);
		return "teacher/current-teacher-courses-page";
	}

	@GetMapping("/enableTest")
	public String enableTest(@RequestParam("courseId") int courseId) {

		Test test = testService.findById(courseId);
		test.setActive(1);
		testService.save(test);

		return "redirect:/showTeacherCourses";
	}

	@GetMapping("/disableTest")
	public String disableTest(@RequestParam("courseId") int courseId) {

		Test test = testService.findById(courseId);
		test.setActive(0);
		testService.save(test);

		return "redirect:/showTeacherCourses";
	}
}
