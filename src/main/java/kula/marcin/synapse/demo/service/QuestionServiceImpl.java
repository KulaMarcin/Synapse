package kula.marcin.synapse.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kula.marcin.synapse.demo.dao.QuestionRepository;
import kula.marcin.synapse.demo.entity.Question;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	public Question findById(int theId) {
		Optional<Question> result = questionRepository.findById(theId);
		Question question = null;

		if (result.isPresent()) {
			question = result.get();
		} else {
			throw new RuntimeException("Did not find instructor id - " + theId);
		}

		return question;
	}

	@Override
	public void save(Question question) {
		questionRepository.save(question);
	}

	@Override
	public void deleteById(int theId) {
		questionRepository.deleteById(theId);
	}

}
