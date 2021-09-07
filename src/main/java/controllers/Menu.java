package controllers;

import entities.Product;
import entity.User;
import entity.UserType;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    User user = new User();
    Shop shop = new Shop();

    public void start() {
        System.out.println("===========================================");
        System.out.println("Welcome to EasyShopper Supermarket E-shop!");
        System.out.println("===========================================");

        System.out.println("Please choose your user type:" +
                "\n 1. Buyer" +
                "\n 2. Sales representative"+
                "\n 3. Owner " );

        String userChoice = scanner.nextLine();
        User activeUser = createUser(userChoice);

        if(activeUser == null){
            handleExit();
        }

        shop.setActiveUser(activeUser);

        showUserFirstMenu(activeUser.getUserType());

    }

    private void showUserFirstMenu(UserType usertype) {
        switch(usertype) {
            case BUYER:
                System.out.println(printBuyerSignInMenu());
                break;
            case OWNER:
                System.out.println(printOwnerMenu());
                break;
            case SALES_REPRESENTATIVE:
                System.out.println(printSalesRepresentativeMenu());
                break;
            default:
                start();
        }
        String userChoice = scanner.nextLine();
        handleBuyerFirstMenu(userChoice, usertype);
    }


    private void showUserMenu(UserType usertype) {
        switch(usertype) {
            case BUYER:
                System.out.println(printBuyerMenu());
                break;
            case OWNER:
                System.out.println(printOwnerMenu());
                break;
            case SALES_REPRESENTATIVE:
                System.out.println(printSalesRepresentativeMenu());
                break;
            default:
                start();
        }
        String userChoice = scanner.nextLine();
        handleMenu(usertype, userChoice);
    }



    private void handleMenu(UserType userType, String userChoice) {
        switch(userType) {
            case BUYER:
                handleBuyerMenu(userChoice, userType, user);
                break;
            case OWNER:
                handleOwnerMenu(userType, userChoice);
                break;
            case SALES_REPRESENTATIVE:
                handleSalesRepresentativeMenu(userType, userChoice);
                break;
            default:
                start();
                break;
        }

    }

    private void handleSalesRepresentativeMenu(UserType userType, String userChoice) {
        switch(userChoice) {
            case "1":
                shop.addProduct();
                break;
            case "2":
                shop.removeProduct();
                break;
            case "3":
                shop.viewSaleshistory();
                break;
            case "4":
                shop.viewProductList();
                break;
            case "5":
                handleExit();
                break;
            default:
                break;

        }
    }

    private void handleOwnerMenu(UserType userType, String userChoice) {
        switch(userChoice) {
            case "1":
                shop.addProduct();
                break;
            case "2":
                shop.removeProduct();
                break;
            case "3":
                shop.viewSaleshistory();
                break;
            case "4":
                shop.viewProductList();
                break;
            case "5":
                handleExit();
                break;
            default:
                break;

        }


    }

    private void handleBuyerFirstMenu(String userChoice, UserType userType) {
        switch(userChoice) {
                case "1":
                    createBuyerUser();
                    break;
                case "2":
                    logIn(userType, user);
                    break;
                case "3":
                    handleExit();
                    break;
                default:
                    break;

        }
        showUserMenu(shop.getActiveUser().getUserType());

    }

    private void handleBuyerMenu(String userInput, UserType userType, User user) {
        switch (userInput) {
            case "1":
                shop.buyProduct();
                break;
            case "2":
                shop.viewProductList();
                break;
            case "3":
                double newBalance = 0;
               shop.updateUserBalance(newBalance, shop.getActiveUser().getEmail());
                break;
            case "4":
                handleExit();
                break;
            default:
                break;
        }
        showUserMenu(shop.getActiveUser().getUserType());

    }

//    private void showBuyerMenu(String userInput) {
//        System.out.println(printBuyerMenu());
//        handleBuyerMenu(userInput);
//    }


    public void logIn(UserType userType, User user) {
        try {
            System.out.println("Please enter your email:");
            String emailInput = scanner.nextLine();
            System.out.println("Please enter your password");
            String passwordInput = scanner.nextLine();
            if (shop.shopController.checkEmail(emailInput).equalsIgnoreCase(emailInput) && shop.shopController.checkPassword(passwordInput).equals(passwordInput)) {
                shop.getActiveUser().setEmail(emailInput);
                shop.getActiveUser().setPassword(passwordInput);
                System.out.println("User " + shop.getActiveUser().getEmail() + " is logged in!");
                System.out.println(printBuyerMenu());
                String userInput = scanner.nextLine();
                handleBuyerMenu(userInput, userType, user);
            } else {
                System.out.println("Invalid email or password");
                handleExit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


        private void handleExit() {
        System.out.println("Enter 1 to Exit or 2 to go show main menu");
        if(scanner.nextLine().equals("1")) {
            System.exit(0);
        }
        start();

    }

    private String printSalesRepresentativeMenu() {
        return "Please choose one of the options below:" +
                "\n 1. Add product" +
                "\n 2. Remove product" +
                "\n 3. View sales history"+
                "\n 4. View Product List" +
                "\n 5. Exit";
    }

    private String printOwnerMenu() {
        return "Please choose one of the options below:" +
                "\n 1. Add product" +
                "\n 2. Remove product" +
                "\n 3. View Product List" +
                "\n 4. View sales history"+
                "\n 5. Exit";
    }

    private String printBuyerSignInMenu() {
        return  "Please choose one of the options below:"
                + "\n 1. Create User"
                + "\n 2. Login"
                + "\n 3. Exit";
    }

    private String printBuyerMenu() {
        return  "Please choose one of the options below:"
                + "\n 1. Buy Product"
                + "\n 2. View Product List"
                + "\n 3. Update balance"
                + "\n 4. Exit";
    }



    private User createUser(String userChoice) {
        switch(Integer.parseInt(userChoice)) {
            case 1:
                return new User(UserType.BUYER);
            case 2:
                return new User(UserType.SALES_REPRESENTATIVE);
            case 3:
                return new User(UserType.OWNER);
            default:
                break;
        }
        return null;
    }

    private User collectUserInfo() {
        System.out.println("Please enter your name");
        user.setName(scanner.nextLine());
        System.out.println("Please enter your email (email@mail.com)");
        user.setEmail(scanner.nextLine());
        System.out.println("Please enter your password");
        user.setPassword(scanner.nextLine());
        System.out.println("Please enter your balance");
        user.setBalance(Double.parseDouble(scanner.nextLine()));
        System.out.println("User created! Please log in: ");
        System.out.println();
        return new User(user.getName(), user.getEmail(), user.getPassword(), UserType.BUYER, user.getBalance());
    }

    private User createBuyerUser (){
        collectUserInfo();
        try {
                 shop.shopController.addUserToDB(user);
        } catch (SQLException throwables) {
                   throwables.printStackTrace();
        }
        return new User(user.getName(), user.getEmail(), user.getPassword(), UserType.BUYER, user.getBalance());
    }

}
