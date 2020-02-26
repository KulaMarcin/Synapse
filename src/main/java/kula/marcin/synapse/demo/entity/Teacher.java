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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "school")
    private String school;

    @Column(name = "subject")
    private String subject;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "instructor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Test> courses;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "instructor_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public Teacher() {

    }

    public Teacher(String firstname, String lastName, String email, String school,
                   List<Test> courses, User user, List<Student> students) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
        this.courses = courses;
        this.user = user;
        this.students = students;
    }

    public Teacher(String firstname, String lastName, String email, String school, String subject,
                   List<Test> courses, User user, List<Student> students) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
        this.subject = subject;
        this.courses = courses;
        this.user = user;
        this.students = students;
    }

    public Teacher(String firstname, String lastName, String email) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Test> getCourses() {
        return courses;
    }

    public void setCourses(List<Test> courses) {
        this.courses = courses;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addTest(Test course) {
        if (courses == null)
            courses = new ArrayList<Test>();

        courses.add(course);
        course.setInstructor(this);
    }

    public void addStudent(Student student) {
        if (students == null)
            students = new ArrayList<Student>();

        students.add(student);
    }

    @Override
    public String toString() {
        return "Instructor [id=" + id + ", firstname=" + firstname + ", lastName=" + lastName + ", email=" + email
                + "]";
    }

}
