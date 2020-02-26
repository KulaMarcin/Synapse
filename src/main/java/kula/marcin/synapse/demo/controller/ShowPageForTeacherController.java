package kula.marcin.synapse.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kula.marcin.synapse.demo.entity.CourseScore;
import kula.marcin.synapse.demo.entity.Test;
import kula.marcin.synapse.demo.entity.Teacher;
import kula.marcin.synapse.demo.entity.Student;
import kula.marcin.synapse.demo.entity.Question;
import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.results.CourseResultModelForPage;
import kula.marcin.synapse.demo.service.CourseScoreService;
import kula.marcin.synapse.demo.service.TestService;
import kula.marcin.synapse.demo.service.StudentService;
import kula.marcin.synapse.demo.service.QuestionService;
import kula.marcin.synapse.demo.service.UserService;

@Controller
public class ShowPageForTeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CourseScoreService courseScoreService;

    public Teacher getCurrentInstructor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = authentication.getName();
        User currentUser = userService.findByUserName(loggedUserName);
        Teacher currentInstructor = currentUser.getInstructor();

        return currentInstructor;
    }

    @GetMapping("/showAddCoursePage")
    public String showAddCoursePage(Model model) {

        Test course = new Test();
        model.addAttribute("course", course);

        return "teacher/add-course-page";
    }

    @GetMapping("/showTeacherCourses")
    public String showTeacherCourses(Model model) {

        Teacher currentInstructor = this.getCurrentInstructor();
        List<Test> coursesOfTheCurrentInstructor;

        try {
            coursesOfTheCurrentInstructor = currentInstructor.getCourses();
        } catch (Exception e) {
            coursesOfTheCurrentInstructor = new ArrayList<Test>();
        }

        model.addAttribute("courses", coursesOfTheCurrentInstructor);

        return "teacher/current-teacher-courses-page";
    }

    @GetMapping("/showTeacherDetail")
    public String showTeacherDetail(Model model) {

        Teacher currentInstructor = this.getCurrentInstructor();

        if (currentInstructor != null)
            model.addAttribute("instructor", currentInstructor);
        else
            model.addAttribute("instructor", new Teacher());

        return "teacher/show-current-teacher-details-page";
    }

    @GetMapping("/showAllStudents")
    public String showAllStudents(Model model) {

        Teacher currentInstructor = this.getCurrentInstructor();
        List<Student> studentsOfTheCurrentInstructor = currentInstructor.getStudents();
        List<Student> allStudents = studentService.findAll();

        for (int i = 0; i < studentsOfTheCurrentInstructor.size(); i++) {
            if (allStudents.contains(studentsOfTheCurrentInstructor.get(i))) {
                allStudents.remove(studentsOfTheCurrentInstructor.get(i));
            }
        }

        model.addAttribute("students", allStudents);

        return "teacher/all-students-page";
    }

    @GetMapping("/showMyStudents")
    public String showMyStudents(Model model) {

        Teacher currentInstructor = this.getCurrentInstructor();
        List<Student> studentsOfTheCurrentInstructor = currentInstructor.getStudents();
        model.addAttribute("myStudents", studentsOfTheCurrentInstructor);
        return "teacher/current-teacher-students-page";
    }

    @GetMapping("/showTestQuestions")
    public String showTestQuestions(@RequestParam("courseId") int courseId, Model model) {

        Test course = testService.findById(courseId);
        List<Question> questionsForCourse = course.getQuestions();
        model.addAttribute("questions", questionsForCourse);
        model.addAttribute("courseId", courseId);

        return "teacher/course-questions-page";
    }

    @GetMapping("/showUpdateQuestionPage")
    public String updateQuestion(@ModelAttribute("questionId") int questionId, Model model) {

        Question questionToUpdate = questionService.findById(questionId);
        Test course = questionToUpdate.getCourse();
        questionToUpdate.setCourseId(course.getId());
        model.addAttribute("questionToUpdate", questionToUpdate);

        return "teacher/update-question-page";
    }

    @GetMapping("/showAddQuestionPage")
    public String showAddQuestionPage(@ModelAttribute("courseId") int courseId, Model model) {

        Test currentCourse = testService.findById(courseId);
        Question newQuestion = new Question();
        newQuestion.setCourseId(currentCourse.getId());
        model.addAttribute("newQuestion", newQuestion);

        return "teacher/add-new-course-question-page";
    }

    @GetMapping("/showCourseScorePage")
    public String showCourseScorePage(@RequestParam("courseId") int courseId, Model model) {

        Test course = testService.findById(courseId);
        List<CourseScore> currentCourseScores = courseScoreService.findByCourseId(courseId);
        List<CourseResultModelForPage> currentCourseScoresModelForPage = new ArrayList<CourseResultModelForPage>();

        for (CourseScore x : currentCourseScores) {
            Student student = x.getStudent();
            CourseResultModelForPage result = new CourseResultModelForPage();
            result.setCourseName(x.getCourse().getTitle());
            result.setInstructorFirstName(x.getCourse().getInstructor().getFirstname());
            result.setInstructorLastName(x.getCourse().getInstructor().getLastName());
            result.setMark(x.getMark());
            result.setMaxScore(x.getMaxCourseScore());
            result.setProcentageResult(x.getProcentageScore());
            result.setScore(x.getScore());
            result.setStudentFirstName(student.getFirstName());
            result.setStudentLastName(student.getLastName());
            result.setStudentClass(student.getStudentClass());
            currentCourseScoresModelForPage.add(result);
        }

        model.addAttribute("currentCourseScoresModelForPage", currentCourseScoresModelForPage);

        return "teacher/course-score-page";
    }

    @GetMapping("/showStudentMarksPage")
    public String showStudentMarkPage(@RequestParam("studentId") int studentId, Model model) {

        Teacher currentTeacher = this.getCurrentInstructor();
        Student student = studentService.findById(studentId);
        List<CourseScore> studentCoursesScores = courseScoreService.findByStudentId(studentId);
        List<CourseScore> studentCoursesScoresForCurrentTeacherCourses = new LinkedList<CourseScore>();
        Map<String, Integer> testsScoresSeriesForTheChart = new LinkedHashMap<String, Integer>();
        List<CourseResultModelForPage> currentCourseScoresModelForPage = new LinkedList<CourseResultModelForPage>();

        for (CourseScore x : studentCoursesScores) {
            if (x.getCourse().getInstructor().getId() == currentTeacher.getId()) {
                studentCoursesScoresForCurrentTeacherCourses.add(x);
            }
        }

        for (CourseScore x : studentCoursesScoresForCurrentTeacherCourses) {
            CourseResultModelForPage result = new CourseResultModelForPage();
            result.setCourseName(x.getCourse().getTitle());
            result.setInstructorFirstName(x.getCourse().getInstructor().getFirstname());
            result.setInstructorLastName(x.getCourse().getInstructor().getLastName());
            result.setMark(x.getMark());
            result.setMaxScore(x.getMaxCourseScore());
            result.setProcentageResult(x.getProcentageScore());
            result.setScore(x.getScore());
            result.setStudentFirstName(student.getFirstName());
            result.setStudentLastName(student.getLastName());
            result.setTestCompletionDate(x.getTestCompletionDate());
            testsScoresSeriesForTheChart.put(x.getCourse().getTitle(), x.getMark());
            currentCourseScoresModelForPage.add(result);
        }

        studentCoursesScores.clear();
        Double markAverage = studentCoursesScoresForCurrentTeacherCourses.stream().mapToDouble(val -> val.getMark()).average().orElse(0.0);

        model.addAttribute("student", student);
        model.addAttribute("currentTeacherSubject", currentTeacher.getSubject());
        model.addAttribute("quantityOfTheCompletedTests", studentCoursesScoresForCurrentTeacherCourses.size());
        model.addAttribute("currentCourseScoresModelForPage", currentCourseScoresModelForPage);
        model.addAttribute("markAverage", markAverage);
        model.addAttribute("testsScoresSeriesForTheChart", testsScoresSeriesForTheChart);

        return "teacher/student-marks-page";
    }

}
