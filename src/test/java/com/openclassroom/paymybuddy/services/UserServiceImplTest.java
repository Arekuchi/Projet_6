package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IRelationDAO;
import com.openclassroom.paymybuddy.DAO.IRoleDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.Role;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IRelationDAO relationDAO;

    @MockBean
    IRoleDAO roleDAO;

    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    Relation relation = new Relation(owner, buddy);

    Role role = new Role("USER");

    UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("Barack", "Obama", "usa@mail.com", "dudu");


    @Test
    void addRelation() throws SQLException {

        // GIVEN
        try {
            userService.addRelation(relation.getOwner().getEmail(), relation.getBuddy().getEmail());
            verify(userDAO, times(2)).findByEmail(any());
            verify(relationDAO, times(0)).save(any());
        } catch (DataNotFoundException e) {
            assert(e.getMessage().contains("Cette personne n'existe pas"));
        }

        // WHEN

        // THEN


    }

    @Test
    void relationListEmail() {

        // GIVEN

        // WHEN
        when(relationDAO.findAllByOwner_Email(any())).thenReturn(Arrays.asList(relation));
        userService.relationListEmail(any());
        verify(relationDAO, times(1)).findAllByOwner_Email(any());

        // THEN

    }

    @Test
    void deleteRelationById() {

        // GIVEN

        // WHEN
        when(relationDAO.existsById(any())).thenReturn(true);
        userService.deleteRelationById(owner.getId());
        verify(relationDAO, times(1)).deleteById(any());

        // THEN

    }

    @Test
    void save() throws SQLException {

        // GIVEN

        // WHEN
        when(roleDAO.findRoleByName("USER")).thenReturn(role);
        userService.save(userRegistrationDTO);
        verify(roleDAO, times(1)).findRoleByName(any());


        // THEN

    }

}