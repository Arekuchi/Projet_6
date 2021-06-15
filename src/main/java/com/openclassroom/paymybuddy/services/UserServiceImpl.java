package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DAO.*;
import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.*;


import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
    IUserDAO userDAO;

    @Autowired
    IRelationDAO relationDAO;

    @Autowired
    IRoleDAO roleDAO;

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Override
    public Relation addRelation(String emailOwner, String emailBuddy) throws SQLException {

        Relation relation = new Relation();
        relation.setOwner(userDAO.findByEmail(emailOwner));
        relation.setBuddy(userDAO.findByEmail(emailBuddy));

        if (relation.getBuddy() == null) {
            throw new DataNotFoundException("Cette personne n'existe pas");
        }
        for (Relation existingRelation : relation.getOwner().getRelationList()) {
            if (existingRelation.getBuddy().equals(relation.getBuddy())) {
                throw new DataAlreadyExistsException("Ces personne sont déjà amis");
            }
        }

        try {
            relationDAO.save(relation);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new SQLException("inside addRelation save error");

        }


        return relation;
    }



    // connection

    @Override
    public List<Relation> relationListEmail(String emailOwner) {

        return relationDAO.findAllByOwner_Email(emailOwner);
    }


    @Override
    public boolean deleteRelationById(Integer id) {

        if (relationDAO.existsById(id)) {
            relationDAO.deleteById(id);
            return true;
        }

        return false;
    }


    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) throws SQLException {

        Role role = roleDAO.findRoleByName("USER");
        User user = new User(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getEmail(), encoder.encode(userRegistrationDTO.getPassword()), BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()), Arrays.asList(role));

        try {
             return userDAO.save(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new SQLException("inside User save error");

        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoleCollection()));

    }

    private Collection<?extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

}
