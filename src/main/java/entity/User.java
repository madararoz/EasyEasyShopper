package entity;

import repository.DBHandler;

import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);
    DBHandler dbHandler = new DBHandler();


    String name;
    String email;
    String password;
    private UserType userType;
    private double balance;

    public User(String name, String email, String password, UserType userType, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.balance = balance;
    }

    public User(UserType userType) {
        this.userType = userType;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UserType getUserType() {
        return userType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
