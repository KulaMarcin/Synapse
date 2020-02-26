package kula.marcin.synapse.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "question")
public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "question")
	private String question;

	@Column(name = "answer_a")
	private String answerA;

	@Column(name = "answer_b")
	private String answerB;

	@Column(name = "answer_c")
	private String answerC;

	@Column(name = "answer_d")
	private String answerD;
	
	@Column(name = "student_answer")
	private String studentAnswer;

	@Column(name = "correct_answer")
	private String correctAnswer;

	@Column(name = "type")
	private String type;

	@Column(name = "resource")
	private String resource;

	@Transient
	private int courseId;
	
	@Transient
	private String selectedAnswer;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "course_id")
	private Test course;
	
	public Question() {
	}
	
	public Question(String question, String answerA, String answerB, String answerC, String answerD,
			String studentAnswer, String correctAnswer, int courseId, Test course) {
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.studentAnswer = studentAnswer;
		this.correctAnswer = correctAnswer;
		this.courseId = courseId;
		this.course = course;
	}

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getAnswerD() {
		return answerD;
	}

	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Test getCourse() {
		return course;
	}

	public void setCourse(Test course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", answerA=" + answerA + ", answerB=" + answerB
				+ ", answerC=" + answerC + ", answerD=" + answerD + ", studentAnswer=" + studentAnswer
				+ ", correctAnswer=" + correctAnswer + ", courseId=" + courseId + ", selectedAnswer=" + selectedAnswer
				+ ", course=" + course + "]";
	}

}
