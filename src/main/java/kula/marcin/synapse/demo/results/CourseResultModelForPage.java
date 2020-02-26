package kula.marcin.synapse.demo.results;

import java.sql.Date;

public class CourseResultModelForPage {

	private String instructorFirstName;

	private String instructorLastName;

	private String courseName;

	private int score;

	private int maxScore;

	private double procentageResult;

	private int mark;
	
	private String studentFirstName; 
	
	private String studentLastName;  
	
	private String studentClass;

	private Date testCompletionDate;

	public CourseResultModelForPage() {

	}

	public CourseResultModelForPage(String instructorFirstName, String instructorLastName, String courseName, int score,
									int maxScore, double procentageResult, int mark, String studentFirstName, String studentLastName) {

		this.instructorFirstName = instructorFirstName;
		this.instructorLastName = instructorLastName;
		this.courseName = courseName;
		this.score = score;
		this.maxScore = maxScore;
		this.procentageResult = procentageResult;
		this.mark = mark;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;

	}

	public Date getTestCompletionDate() {
		return testCompletionDate;
	}

	public void setTestCompletionDate(Date testCompletionDate) {
		this.testCompletionDate = testCompletionDate;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getInstructorFirstName() {
		return instructorFirstName;
	}

	public void setInstructorFirstName(String instructorFirstName) {
		this.instructorFirstName = instructorFirstName;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getInstructorLastName() {
		return instructorLastName;
	}

	public void setInstructorLastName(String instructorLastName) {
		this.instructorLastName = instructorLastName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public double getProcentageResult() {
		return procentageResult;
	}

	public void setProcentageResult(double procentageResult) {
		this.procentageResult = procentageResult;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "CoursesResults [instructorName=" + instructorFirstName + ", instructorLastName=" + instructorLastName
				+ ", courseName=" + courseName + ", score=" + score + ", maxScore=" + maxScore + ", procentageResult="
				+ procentageResult + ", mark=" + mark + "]";
	}

}
