package kula.marcin.synapse.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kula.marcin.synapse.demo.entity.CourseScore;

public interface CourseScoreRepository extends JpaRepository<CourseScore, Integer> {
		
	List<CourseScore> findByStudentId(int studentId);
	CourseScore findByStudentIdAndCourseId(int studentId, int courseId);
	List<CourseScore> findByCourseId(int courseId);
	
}
