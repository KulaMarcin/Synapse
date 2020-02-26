package kula.marcin.synapse.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_id")
	private Teacher instructor;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Student> students;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Teacher> instructors;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	public User() {

	}

	public User(String userName, String password, String firstName, String lastName, String email) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}

	public User(String userName, String password, String firstName, String lastName, String email,
				List<Role> roles) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;

	}

	public Teacher getInstructor() {
		return instructor;
	}

	public void setInstructor(Teacher instructor) {
		this.instructor = instructor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		if(students == null)
			students = new ArrayList<Student>();
			
		students.add(student);
		student.setUser(this);
	}
	
	
	
	public List<Teacher> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Teacher> instructors) {
		this.instructors = instructors;
	}

	public void addStudent(Teacher instructor) {
		if(instructors == null)
			instructors = new ArrayList<Teacher>();
			
		instructors.add(instructor);
		instructor.setUser(this);
	}
	
	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + "*********" + '\''
				+ ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\''
				+ ", roles=" + roles + '}';
	}
}
