package kula.marcin.synapse.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kula.marcin.synapse.demo.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
}
