package kula.marcin.synapse.demo.dao;

import kula.marcin.synapse.demo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    void save(User user);
    void update(User user);

}
