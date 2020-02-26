package kula.marcin.synapse.demo.dao;

import kula.marcin.synapse.demo.entity.Role;

public interface RoleDao {

	Role findRoleByName(String theRoleName);
	
}
