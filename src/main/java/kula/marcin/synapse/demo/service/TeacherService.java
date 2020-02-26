package kula.marcin.synapse.demo.service;

import java.util.List;

import kula.marcin.synapse.demo.entity.Teacher;

public interface TeacherService {

    List<Teacher> findAll();

    List<Teacher> findByLastName(String lastName);

    Teacher findById(int theId);

    void save(Teacher instructor);

    void deleteById(int theId);

}
