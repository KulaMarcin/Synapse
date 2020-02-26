package kula.marcin.synapse.demo.service;

import java.util.List;

import kula.marcin.synapse.demo.entity.Question;

public interface QuestionService {

	List<Question> findAll();

	Question findById(int theId);

	void save(Question question);

	void deleteById(int theId);

}
