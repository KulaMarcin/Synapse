package kula.marcin.synapse.demo.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kula.marcin.synapse.demo.answer.Answer;
import kula.marcin.synapse.demo.entity.CourseScore;
import kula.marcin.synapse.demo.entity.Test;
import kula.marcin.synapse.demo.entity.Student;
import kula.marcin.synapse.demo.entity.Question;
import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.results.CourseResultModelForPage;
import kula.marcin.synapse.demo.service.CourseScoreService;
import kula.marcin.synapse.demo.service.TestService;
import kula.marcin.synapse.demo.service.StudentService;
import kula.marcin.synapse.demo.service.UserService;

@Controller
public class TestForTheStudentController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TestService testService;

	@Autowired
	private CourseScoreService courseScoreService;
	
	@Autowired
	private UserService userService;

	@Autowired
	Map<Integer, Answer> answers;

	public Student getCurrentStudent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();
		User currentUser = userService.findByUserName(loggedUserName);
		Student currentStudent = currentUser.getStudent();

		return currentStudent;
	}
	
	@GetMapping("/startTest")
	public String startTest(@RequestParam("courseId") int courseId, @RequestParam("courseName") String courseName, @RequestParam("destinyFor") String destinyFor, Model model) {

		Test course = testService.findById(courseId);
		int questionQuantity = course.getQuestions().size();
		int questionNumber = -1;
		answers.clear();

		model.addAttribute("questionQuantity", questionQuantity);
		model.addAttribute("questionNumber", questionNumber);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseName", courseName);
		model.addAttribute("destinyFor", destinyFor);

		return "student/start-selected-test-page";
	}

	@GetMapping("/nextQuestion")
	public String nextQuestion(@RequestParam("questionQuantity") int questionQuantity,
			@RequestParam("questionNumber") int questionNumber, @RequestParam("courseId") int courseId, Model model) {

		Test course = testService.findById(courseId);

		model.addAttribute("questionQuantity", questionQuantity);
		model.addAttribute("questionNumber", ++questionNumber);
		model.addAttribute("courseId", courseId);
		model.addAttribute("question", course.getQuestions().get(questionNumber));

		String currentSelectedAnswer;

		try {
			currentSelectedAnswer = answers.get(course.getQuestions().get(questionNumber).getId()).getSelectedAnswer();
		} catch (Exception e) {
			currentSelectedAnswer = "BRAK";
		}

		model.addAttribute("currentSelectedAnswer", currentSelectedAnswer);

		return "student/show-question-page";
	}

	@GetMapping("/previousQuestion")
	public String previousQuestion(@RequestParam("questionQuantity") int questionQuantity,
			@RequestParam("questionNumber") int questionNumber, @RequestParam("courseId") int courseId, Model model) {

		Test course = testService.findById(courseId);

		model.addAttribute("questionQuantity", questionQuantity);
		model.addAttribute("questionNumber", --questionNumber);
		model.addAttribute("courseId", courseId);
		model.addAttribute("question", course.getQuestions().get(questionNumber));

		String currentSelectedAnswer;

		try {
			currentSelectedAnswer = answers.get(course.getQuestions().get(questionNumber).getId()).getSelectedAnswer();
		} catch (Exception e) {
			currentSelectedAnswer = "BRAK";
		}

		model.addAttribute("currentSelectedAnswer", currentSelectedAnswer);

		return "student/show-question-page";
	}

	@PostMapping("/confirmAnswerActionTest")
	public String confirmAnswerAction(@RequestParam("questionQuantity") int questionQuantity,
			@RequestParam("questionNumber") int questionNumber, @RequestParam("courseId") int courseId,
			@ModelAttribute("question") Question question) {

		Answer answer = new Answer(question.getQuestion(), question.getSelectedAnswer(), question.getCorrectAnswer());
		answers.put(question.getId(), answer);

		return "redirect:/previousQuestion?questionQuantity=" + questionQuantity + "&questionNumber="
				+ (++questionNumber) + "&courseId=" + courseId;
	}

	@GetMapping("/finishTheCourseAction")
	public String finishTheCourse(@RequestParam("questionQuantity") int questionQuantity,
			@RequestParam("courseId") int courseId, Model model) {

		int score = 0;
		int mark = 0;
		for (Map.Entry<Integer, Answer> entry : answers.entrySet()) {
			if (entry.getValue().getCorrectAnswer().equals(entry.getValue().getSelectedAnswer())) 
				score++;	
		}
		
		double procentageResult = ((double) score / (double) questionQuantity) * 100;

		if(procentageResult < 41.0)
		{
			mark = 1;
		} else if (procentageResult >= 41.0 && procentageResult < 51.0) {
			mark = 2;
		} else if(procentageResult >= 51.0 && procentageResult < 71.0) {
			mark = 3;
		} else if(procentageResult >= 71.0 && procentageResult < 86.0) {
			mark = 4;
		} else if(procentageResult >= 86.0 && procentageResult < 98.0) {
			mark = 5;
		} else {
			mark = 6;
		}  

		Student currentStudent = this.getCurrentStudent();
		Test currentCourse = testService.findById(courseId);
		CourseScore currentCourseScore = new CourseScore(questionQuantity, score, procentageResult, mark, currentStudent, currentCourse);
		currentCourseScore.setTestCompletionDate(Date.valueOf(java.time.LocalDate.now()));
		currentCourse.addScore(currentCourseScore); 
		currentStudent.addScore(currentCourseScore);
		courseScoreService.save(currentCourseScore);

		model.addAttribute("currentCourseScore", currentCourseScore);

		return "student/completed-course-page";
	}
	
	@GetMapping("/testScore")
	public String testScore(@RequestParam("courseId") int courseId, Model model) {

		Student currStudent = this.getCurrentStudent();
		Test course = testService.findById(courseId);
		CourseScore score = courseScoreService.findByStudentIdAndCourseId(currStudent.getId(), courseId);
		CourseResultModelForPage courseResultModel = new CourseResultModelForPage();
		courseResultModel.setCourseName(course.getTitle());
		courseResultModel.setInstructorFirstName(course.getInstructor().getFirstname());
		courseResultModel.setInstructorLastName(course.getInstructor().getLastName());
		courseResultModel.setMark(score.getMark());
		courseResultModel.setMaxScore(score.getMaxCourseScore());
		courseResultModel.setProcentageResult(score.getProcentageScore());
		courseResultModel.setScore(score.getScore());
		
		model.addAttribute("courseResultModel", courseResultModel);
		
		return "student/show-selected-test-score-page";
	}
	
}
