package kula.marcin.synapse.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kula.marcin.synapse.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> findByLastName(String lastName);
	List<Student> findByStudentClass(String studentClass);
	
}
