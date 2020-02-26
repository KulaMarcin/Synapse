package kula.marcin.synapse.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kula.marcin.synapse.demo.dao.StudentRepository;
import kula.marcin.synapse.demo.entity.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);
		Student student = null;

		if (result.isPresent()) {
			student = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return student;
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		return studentRepository.findByLastName(lastName);
	}

	@Override
	public List<Student> findByStudentClass(String studentClass) {
		return studentRepository.findByStudentClass(studentClass);
	}

}
