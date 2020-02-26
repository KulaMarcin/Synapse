package kula.marcin.synapse.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kula.marcin.synapse.demo.entity.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {

    List<Test> findByDestinyFor(String destinyFor);
}
