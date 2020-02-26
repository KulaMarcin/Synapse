package kula.marcin.synapse.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "course_score")
public class CourseScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "max_course_score")
	private int maxCourseScore;

	@Column(name = "score")
	private int score;

	@Column(name = "procentage_score")
	private double procentageScore;

	@Column(name = "mark")
	private int mark;

	@Column(name = "test_completion_date")
	private Date testCompletionDate;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "course_id")
	private Test course;

	public CourseScore() {
	}

	public CourseScore(int maxCourseScore, int score, double procentageScore, int mark, Student student,
			Test course) {
		this.maxCourseScore = maxCourseScore;
		this.score = score;
		this.procentageScore = procentageScore;
		this.mark = mark;
		this.student = student;
		this.course = course;
	}

	public Date getTestCompletionDate() {
		return testCompletionDate;
	}

	public void setTestCompletionDate(Date testCompletionDate) {
		this.testCompletionDate = testCompletionDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxCourseScore() {
		return maxCourseScore;
	}

	public void setMaxCourseScore(int maxCourseScore) {
		this.maxCourseScore = maxCourseScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Test getCourse() {
		return course;
	}

	public void setCourse(Test course) {
		this.course = course;
	}

	public double getProcentageScore() {
		return procentageScore;
	}

	public void setProcentageScore(double procentageScore) {
		this.procentageScore = procentageScore;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "CourseScore [id=" + id + ", maxCourseScore=" + maxCourseScore + ", score=" + score
				+ ", procentageScore=" + procentageScore + ", mark=" + mark + ", course=" + course + "]";
	}
}
