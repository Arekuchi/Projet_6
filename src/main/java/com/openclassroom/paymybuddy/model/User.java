package com.openclassroom.paymybuddy.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames="email", name = "uniqueEmailConstraint")})
public class User {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="balance")
    double balance;
    @Column(name="createdate")
    Timestamp createDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
    private List<BankAccount> bankAccountList;


    // constructors
    public User() {

    }

    public User(String firstName, String lastName, String email, String password, double balance, Timestamp createDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createDate = createDate;
    }

    public User(int id, String firstName, String lastName, String email, String password, double balance, Timestamp createDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createDate = createDate;
    }



    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList){
        this.bankAccountList = bankAccountList;
    }


    // convenience method

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // convenience method for bi-directional relationship

    public void add(BankAccount tempBankAccount) {

        if (bankAccountList == null) {
            bankAccountList = new ArrayList<>();
        }
        bankAccountList.add(tempBankAccount);
        tempBankAccount.setUser(this);
    }


}
