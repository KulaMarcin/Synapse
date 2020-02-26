package kula.marcin.synapse.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "test")
public class Test implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "destiny_for")
    private String destinyFor;

    @Column(name = "description")
    private String description;

    @Column(name = "subject")
    private String subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "instructor_id")
    private Teacher instructor;

    @Column(name = "active")
    private int active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinTable(name = "test_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    private List<Question> questions;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    private List<CourseScore> scores;

    @Transient
    private int isCompleted;

    @Transient
    private int questionsQuantity;

    public Test() {

    }

    public Test(String title, Teacher instructor) {

        this.title = title;
        this.instructor = instructor;

    }

    public Test(String title) {

        this.title = title;

    }

	public Test(String title, String destinyFor, String description, Teacher instructor,
				List<Student> students, List<Question> questions, List<CourseScore> scores, int isCompleted) {

		this.title = title;
		this.destinyFor = destinyFor;
		this.description = description;
		this.instructor = instructor;
		this.students = students;
		this.questions = questions;
		this.scores = scores;
		this.isCompleted = isCompleted;

	}

	public Test(String title, String destinyFor, String description, String subject, Teacher instructor,
				List<Student> students, List<Question> questions, List<CourseScore> scores, int isCompleted) {

		this.title = title;
		this.destinyFor = destinyFor;
		this.description = description;
		this.subject = subject;
		this.instructor = instructor;
		this.students = students;
		this.questions = questions;
		this.scores = scores;
		this.isCompleted = isCompleted;

	}

    public int getQuestionsQuantity() {
        return questionsQuantity;
    }

    public void setQuestionsQuantity(int questionsQuantity) {
        this.questionsQuantity = questionsQuantity;
    }

    public String getDestinyFor() {
        return destinyFor;
    }

    public void setDestinyFor(String destinyFor) {
        this.destinyFor = destinyFor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CourseScore> getScores() {
        return scores;
    }

    public void setScores(List<CourseScore> scores) {
        this.scores = scores;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + ", instructor=" + instructor + "]";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void addStudent(Student student) {

        if (students == null)
            students = new ArrayList<Student>();

        students.add(student);
    }

    public void addQuestion(Question question) {

        if (questions == null)
            questions = new ArrayList<Question>();

        questions.add(question);
        question.setCourse(this);
    }

    public void addScore(CourseScore courseScore) {

        if (scores == null)
            scores = new ArrayList<CourseScore>();

        scores.add(courseScore);
        courseScore.setCourse(this);

    }

}
