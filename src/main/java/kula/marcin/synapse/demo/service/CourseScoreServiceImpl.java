package kula.marcin.synapse.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kula.marcin.synapse.demo.dao.CourseScoreRepository;
import kula.marcin.synapse.demo.entity.CourseScore;

@Service
public class CourseScoreServiceImpl implements CourseScoreService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CourseScoreRepository courseScoreRepository;
	
	@Override
	public List<CourseScore> findAll() {
		return courseScoreRepository.findAll();
	}

	@Override
	public CourseScore findById(int theId) {
		Optional<CourseScore> result = courseScoreRepository.findById(theId);
		CourseScore courseScore = null;
		
		if (result.isPresent()) {
			courseScore = result.get();
		}
		else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return courseScore;
	}

	@Override
	public void save(CourseScore courseScore) {
		courseScoreRepository.save(courseScore);
	}

	@Override
	public void deleteById(int theId) {
		courseScoreRepository.deleteById(theId);
	}

	@Override
	public List<CourseScore> findByStudentId(int theId) {
		return courseScoreRepository.findByStudentId(theId);
	}

	@Override
	public CourseScore findByStudentIdAndCourseId(int studentId, int courseId) {
		CourseScore courseScore = courseScoreRepository.findByStudentIdAndCourseId(studentId, courseId);
		return courseScore;
	}

	@Override
	public List<CourseScore> findByCourseId(int courseId) {
		return courseScoreRepository.findByCourseId(courseId);
	}

}
