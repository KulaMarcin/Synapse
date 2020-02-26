package kula.marcin.synapse.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kula.marcin.synapse.demo.dao.TeacherRepository;
import kula.marcin.synapse.demo.entity.Teacher;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public List<Teacher> findByLastName(String lastName) {
		return teacherRepository.findByLastName(lastName);
	}

	@Override
	public Teacher findById(int theId) {
		Optional<Teacher> result = teacherRepository.findById(theId);
		Teacher instructor = null;

		if (result.isPresent()) {
			instructor = result.get();
		} else {
			throw new RuntimeException("Did not find instructor id - " + theId);
		}

		return instructor;
	}

	@Override
	public void save(Teacher instructor) {
		teacherRepository.save(instructor);
	}

	@Override
	public void deleteById(int theId) {
		teacherRepository.deleteById(theId);
	}

}
