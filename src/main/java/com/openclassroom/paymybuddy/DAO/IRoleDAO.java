package com.openclassroom.paymybuddy.DAO;


import com.openclassroom.paymybuddy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDAO extends JpaRepository<Role, Integer> {


    Role findRoleByName(String user);

}
