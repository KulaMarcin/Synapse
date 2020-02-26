package kula.marcin.synapse.demo.service;

import kula.marcin.synapse.demo.entity.User;
import kula.marcin.synapse.demo.user.CrmUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	void save(CrmUser crmUser);
	
	void update(User user);

}
