package kula.marcin.synapse.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import kula.marcin.synapse.demo.entity.CourseScore;
import kula.marcin.synapse.demo.entity.Test;
import kula.marcin.synapse.demo.entity.Teacher;
import kula.marcin.synapse.demo.entity.Student;
import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.results.CourseResultModelForPage;
import kula.marcin.synapse.demo.service.CourseScoreService;
import kula.marcin.synapse.demo.service.TeacherService;
import kula.marcin.synapse.demo.service.UserService;

@Controller
public class ShowPageForStudentController {

	@Autowired
	private UserService userService;

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
	
	@GetMapping("/showStudentDetail")
	public String showStudentDetail(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		Student student = currentUser.getStudent();
		
		if(student != null)
			model.addAttribute("student", student);
		else 
			model.addAttribute("student", new Student());
		
		return "student/student-details-page";
	}

	@GetMapping("/showMyTeachers")
	public String showMyTeachers(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName); 
		
		Student me = currentUser.getStudent();
		
		List<Teacher> myTeachers = me.getInstructors();
		model.addAttribute("myTeachers", myTeachers);
		
		return "student/teachers-for-current-student-page";
	}
	
	@GetMapping("/showMyTeacherCourses")
	public String showMyTeacherCourses (Model model){

		int currentStudentId = this.getCurrentStudent().getId();
		List<CourseScore> scores = courseScoreService.findByStudentId(currentStudentId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName); 
		
		Student me = currentUser.getStudent();
		List<Teacher> teachers = me.getInstructors();
		List<Test> coursesForCurrentStudent = new ArrayList<Test>();
		
		for(int i = 0; i < teachers.size(); i++) {
			coursesForCurrentStudent.addAll(teachers.get(i).getCourses());
		}
		
		List<Integer> coursesScoresIdList = new ArrayList<Integer>();
		List<Test> coursesToDelete = new ArrayList<Test>();
		
		for(Test x : coursesForCurrentStudent) {
			if(!(x.getDestinyFor().equals(me.getStudentClass()))) {
				coursesToDelete.add(x);
			}
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
	
	@GetMapping("/showTests")
	public String showTestsForTeacher(@ModelAttribute("teacherId") int teacherId, Model model) {
		
		Teacher instructor = teacherService.findById(teacherId);
		List<Test> coursesOfTheInstructor = instructor.getCourses();
		model.addAttribute("courses", coursesOfTheInstructor);
		
		return "student/teacher-tests-page";
	}
	
	@GetMapping("/showMyCoursesResults")
	public String showMyCoursesResults(Model model) {
		
		int currentStudentId = this.getCurrentStudent().getId();
		List<CourseScore> scores = courseScoreService.findByStudentId(currentStudentId);
		List<CourseResultModelForPage> coursesResultsForCurrentUser = new ArrayList<CourseResultModelForPage>();
		Map<String, Integer> testsScoresSeriesForTheChart = new LinkedHashMap<String, Integer>();

		for(CourseScore x : scores) {
			 
			CourseResultModelForPage result = new CourseResultModelForPage();
			result.setCourseName(x.getCourse().getTitle());
			result.setInstructorFirstName(x.getCourse().getInstructor().getFirstname());
			result.setInstructorLastName(x.getCourse().getInstructor().getLastName());
			result.setMark(x.getMark());
			result.setMaxScore(x.getMaxCourseScore());
			result.setProcentageResult(x.getProcentageScore());
			result.setScore(x.getScore());
			result.setTestCompletionDate(x.getTestCompletionDate());
			testsScoresSeriesForTheChart.put(x.getCourse().getTitle(), x.getMark());
			coursesResultsForCurrentUser.add(result);
		}
		
		model.addAttribute("coursesResultsForCurrentUser", coursesResultsForCurrentUser);
		model.addAttribute("testsScoresSeriesForTheChart", testsScoresSeriesForTheChart);

		return "student/current-student-courses-results-page";
	}
}
