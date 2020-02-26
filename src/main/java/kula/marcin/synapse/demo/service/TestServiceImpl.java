package kula.marcin.synapse.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kula.marcin.synapse.demo.dao.TestRepository;
import kula.marcin.synapse.demo.entity.Test;

@Service
@Transactional
public class TestServiceImpl implements TestService, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public List<Test> findByDestinyFor(String destinyFor) {
        return testRepository.findByDestinyFor(destinyFor);
    }

    @Override
    public Test findById(int theId) {
        Optional<Test> result = testRepository.findById(theId);
        Test course = null;

        if (result.isPresent()) {
            course = result.get();
        } else {
            throw new RuntimeException("Did not find instructor id - " + theId);
        }

        return course;
    }

    @Override
    public void save(Test course) {
        testRepository.save(course);
    }

    @Override
    public void deleteById(int theId) {
        testRepository.deleteById(theId);
    }
}
