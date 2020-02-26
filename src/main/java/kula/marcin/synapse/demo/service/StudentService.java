package kula.marcin.synapse.demo.service;

import java.util.List;

import kula.marcin.synapse.demo.entity.Student;

public interface StudentService {
	
	List<Student> findAll();

	List<Student> findByLastName(String lastName);

	List<Student> findByStudentClass(String studentClass);

	Student findById(int theId);

	void save(Student student);

	void deleteById(int theId);

}
