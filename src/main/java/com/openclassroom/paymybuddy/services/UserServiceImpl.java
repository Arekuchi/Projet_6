package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DAO.*;
import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;
import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.*;


import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataMissingException;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    IBankAccountDAO bankAccountDAO;

    @Autowired
    IExternalTransferDAO externalTransferDAO;

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


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
    public boolean addUser(UserInfoCreate userInfoCreate) {

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

        user.setBalance(new BigDecimal(0.00));
        user.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

        userDAO.save(user);



        return true; // rajout pour éviter une erreur
    }


    @Override
    public boolean deleteRelation(User owner, User buddy) {

        // on a besoin de trouver la relation
        Relation relation = new Relation();
        relation.setId(relationDAO.findByBuddy(buddy).getId());

        relationDAO.delete(relation);

        return true;
    }




    public boolean deleteUserByEmail(String email) {

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

    @Override
    public Relation addRelation(String emailOwner, String emailBuddy) {

        Relation relation = new Relation();
        relation.setOwner(userDAO.findByEmail(emailOwner));
        relation.setBuddy(userDAO.findByEmail(emailBuddy));

        if (relation.getBuddy() == null) {
            throw new DataNotFoundException("Cette personne n'existe pas");
        }
        for (Relation existingRelation : relation.getOwner().getRelationList()) {
            if (existingRelation.getBuddy().equals(relation.getBuddy())) {
                throw new DataAlreadyExistsException("Les utilisateurs sont déjà amis");
            }
        }
        relationDAO.save(relation);

        return relation;
    }





    // connection

    @Override
    public List<Relation> relationListEmail(String emailOwner) {

        return relationDAO.findAllByOwner_Email(emailOwner);
    }

    @Override
    public List<BankAccount> bankAccountListEmail(String emailOwner) {

        return bankAccountDAO.findBankAccountsByUser_Email(emailOwner);
    }

    @Override
    public List<ExternalTransfer> externalTransferListByIban(String iban) {



        return externalTransferDAO.findAllByBankAccountIban(iban);
    }

//    @Override
//    public List<ExternalTransfer> externalTransferListByEmail(String email) {
//
//        List<BankAccount> bankAccountList = bankAccountListEmail(email);
//        List<ExternalTransfer> externalTransferList = new ArrayList<>();
//
//
//
//        return externalTransferDAO.findAllByUserEmail(bankAccountList);
//    }

    @Override
    public boolean deleteRelationById(Integer id) {

        if (relationDAO.existsById(id)) {
            relationDAO.deleteById(id);
            return true;
        }

        return false;
    }


    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {

        Role role = roleDAO.findRoleByName("USER");
        User user = new User(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getEmail(), encoder.encode(userRegistrationDTO.getPassword()), BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()), Arrays.asList(role));


        return userDAO.save(user);
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
