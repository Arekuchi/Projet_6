package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DAO.IRelationDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.User;


import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataMissingException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
    IUserDAO userDAO;

    @Autowired
    IRelationDAO relationDAO;

    @Override
    public List<UserInfo> findAll() {
        List<User> userList = userDAO.findAll();
        List<UserInfo> userInfoList = new ArrayList<>();

        for (User user : userList) {
            UserInfo userInfo = new UserInfo();
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setEmail(user.getEmail());
            userInfo.setId(user.getId());
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public UserInfo findByEmail(String email) {
        User user = userDAO.findByEmail(email);
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmail(user.getEmail());
        userInfo.setId(user.getId());

        return userInfo;
    }



    @Override
    public int countUsers() {
        List<User> userList = userDAO.findAll();

        return userList.size(); // ou utiliser .count de la JPA
    }

    @Override
    public Boolean addUser(UserInfoCreate userInfoCreate) {

        // etape 1 : Vérifier que toutes les entrées de l'objet en param est valide (InvalidArgument, DataMissing) / if etc...
        if (userInfoCreate.getEmail().isEmpty()) {
            throw new DataMissingException("L'email ne peut pas être vide");
        }
        if (userInfoCreate.getFirstName().isEmpty()) {
            throw new DataMissingException("Le prénom ne peut pas être vide");
        }
        if (userInfoCreate.getLastName().isEmpty()) {
            throw new DataMissingException("Le nom de famille ne peut pas être vide");
        }
        if (userInfoCreate.getPassword().isEmpty()) {
            throw new DataMissingException("Le mot de passe ne peut pas être vide");
        }
//         etape 2 : DataAlreadyException, son mail ne doit pas exister dans la DB  / existsByEmail / if userDAO.existByEmail(userInfoCreate.getEmail)
        if (userDAO.existsByEmail(userInfoCreate.getEmail())) {
            throw new DataAlreadyExistsException("L'email est déjà présent dans la base de donnée");
        }
        // etape 3 : utiliser le .save de la JPA (sur User uniquement), donc mapper userInfoCreate dans User : pour faire userDAO.save(User)

        User user = new User();
        user.setEmail(userInfoCreate.getEmail());
        user.setFirstName(userInfoCreate.getFirstName());
        user.setLastName(userInfoCreate.getLastName());
        user.setPassword(userInfoCreate.getPassword());

        user.setBalance(0.00);
        user.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

        userDAO.save(user);



        return true; // rajout pour éviter une erreur
    }

    @Override
    public Boolean addRelation(User owner, User buddy) {

        Relation relation = new Relation();
        relation.setOwner(owner);
        relation.setBuddy(buddy);

        relationDAO.save(relation);

        return true;
    }




    public boolean deleteUserByEmail(String email) throws Exception {

        // on vérifie que le param est bien rempli
        if (email.isEmpty() || email.isBlank()) {
            throw new DataMissingException("L'email de l'utilisateur ne peut être vide");
        }
        // on vérifie que l'email existe dans la DB
        if (!userDAO.existsByEmail(email)) {
            throw new InvalidArgumentException("L'email de l'utilisateur n'existe pas");
        }

        userDAO.delete(userDAO.findByEmail(email));

        return true;
    }


}
