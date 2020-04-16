package kula.marcin.synapse.demo.controller;

import java.util.ArrayList;
import java.util.List;

import kula.marcin.synapse.demo.entity.*;
import kula.marcin.synapse.demo.results.CourseResultModelForPage;
import kula.marcin.synapse.demo.service.CourseScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kula.marcin.synapse.demo.service.TeacherService;
import kula.marcin.synapse.demo.service.StudentService;
import kula.marcin.synapse.demo.service.UserService;

/*
* TODO:
*  method searchTestsByTeacherLastName() should be rewrite
*  field injection should be replace to constructor injection
*  some business logic should be moved to @Service
* */

@Controller
public class ActionForStudentController {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseScoreService courseScoreService;

	public Student getCurrentStudent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		Student currentStudent = currentUser.getStudent();
		return currentStudent;
	}

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		return currentUser;
	}
	
	@GetMapping("/updateStudent")
	public String updateStudent(Model model) {

		Student currentStudent = this.getCurrentStudent();
		
		if(currentStudent != null)
			model.addAttribute("currentStudent", currentStudent);
		else 
			model.addAttribute("currentStudent", new Student());
		
		return "student/save-or-update-student-page";
	}
	
	@PostMapping("/saveOrUpdateStudent") 
	public String saveOrUpdateStudent(@ModelAttribute("student") Student student) {

		Student currentStudent = this.getCurrentStudent();
		User currentUser = this.getCurrentUser();
		
		if(currentStudent == null) {
			currentUser.setStudent(student);
			studentService.save(student);
		} else {
			studentService.save(student);
		}
		
		return "redirect:/showStudentDetail";
	}

	@GetMapping("/showAllTeachers") 
	public String showAllTeachers(Model model) {
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers = teacherService.findAll();
		model.addAttribute("teachers", teachers);
		
		return "student/all-teachers-page";
	}

	@GetMapping("/searchTeachersByLastName")
	public String searchTeachersByLastName(@RequestParam("teacherLastName") String teacherLastName, Model model) {

		User currentUser = this.getCurrentUser();
		Student currentStudent = currentUser.getStudent();
		List<Teacher> myAllTeachers = currentStudent.getInstructors();
		List<Teacher> myTeachers = new ArrayList<Teacher>();

		for(Teacher x : myAllTeachers){
			if(x.getLastName().equals(teacherLastName))
				myTeachers.add(x);
		}

		model.addAttribute("myTeachers", myTeachers);

		return "student/teachers-for-current-student-page";
	}
	
	@GetMapping("/searchTestsScoresByTeacherLastName")
	public String searchTestsScoresByTeacherLastName(@RequestParam("teacherLastName") String teacherLastName, Model model) {

		int currentStudentId = this.getCurrentStudent().getId();
		List<CourseScore> scores = courseScoreService.findByStudentId(currentStudentId);
		List<CourseResultModelForPage> coursesResultsForCurrentUser = new ArrayList<CourseResultModelForPage>();

		for(CourseScore x : scores) {
			if(x.getCourse().getInstructor().getLastName().equals(teacherLastName)) {
				CourseResultModelForPage result = new CourseResultModelForPage();
				result.setCourseName(x.getCourse().getTitle());
				result.setInstructorFirstName(x.getCourse().getInstructor().getFirstname());
				result.setInstructorLastName(x.getCourse().getInstructor().getLastName());
				result.setMark(x.getMark());
				result.setMaxScore(x.getMaxCourseScore());
				result.setProcentageResult(x.getProcentageScore());
				result.setScore(x.getScore());
				coursesResultsForCurrentUser.add(result);
			}
		}

		model.addAttribute("coursesResultsForCurrentUser", coursesResultsForCurrentUser);

		return "student/current-student-courses-results-page";
	}

	@GetMapping("/searchTestsByTeacherLastName")
	public String searchTestsByTeacherLastName(@RequestParam("teacherLastName") String teacherLastName, Model model){

		int currentStudentId = this.getCurrentStudent().getId();
		List<CourseScore> scores = courseScoreService.findByStudentId(currentStudentId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);

		Student me = currentUser.getStudent();
		List<Teacher> teachers = me.getInstructors();
		List<Test> allCoursesForCurrentStudent = new ArrayList<Test>();

		for(int i = 0; i < teachers.size(); i++) {
			allCoursesForCurrentStudent.addAll(teachers.get(i).getCourses());
		}

		List<Integer> coursesScoresIdList = new ArrayList<Integer>();


		List<Test> coursesToDelete = new ArrayList<Test>();

		for(Test x : allCoursesForCurrentStudent) {
			if(!(x.getDestinyFor().equals(me.getStudentClass()))) {
				coursesToDelete.add(x);
			}
		}

		List<Test> coursesForCurrentStudent = new ArrayList<Test>();

		for(Test x : allCoursesForCurrentStudent){
			if(x.getInstructor().getLastName().equals(teacherLastName))
				coursesForCurrentStudent.add(x);
		}

		for(Test x : coursesForCurrentStudent) {
			x.setQuestionsQuantity(x.getQuestions().size());
		}

		coursesForCurrentStudent.removeAll(coursesToDelete);

		for(CourseScore x : scores) {
			coursesScoresIdList.add(x.getCourse().getId());
		}

		for(Test x : coursesForCurrentStudent) {
			if(coursesScoresIdList.contains(x.getId())) {
				x.setIsCompleted(1);
			}
		}

		model.addAttribute("courses", coursesForCurrentStudent);

		return "student/teachers-courses-for-current-student-page";
	}

}
