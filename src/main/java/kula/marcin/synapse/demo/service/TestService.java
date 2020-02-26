package kula.marcin.synapse.demo.service;

import java.util.List;

import kula.marcin.synapse.demo.entity.Test;

public interface TestService {

	List<Test> findAll();

    List<Test> findByDestinyFor(String destinyFor);

    Test findById(int theId);

    void save(Test course);

    void deleteById(int theId);

}
