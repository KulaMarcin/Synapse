package kula.marcin.synapse.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kula.marcin.synapse.demo.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findByLastName(String lastName);

}
