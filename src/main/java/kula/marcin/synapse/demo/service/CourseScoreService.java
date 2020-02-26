package kula.marcin.synapse.demo.service;

import java.util.List;

import kula.marcin.synapse.demo.entity.CourseScore;

public interface CourseScoreService {

	List<CourseScore> findAll();

	List<CourseScore> findByStudentId(int studentId);

	CourseScore findById(int theId);

	CourseScore findByStudentIdAndCourseId(int studentId, int courseId);

	List<CourseScore> findByCourseId(int courseId);

	void save(CourseScore courseScore);

	void deleteById(int theId);

}
