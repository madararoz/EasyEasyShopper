package controllers;

import entities.Product;
import entity.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    ShopController shopController = new ShopController();

    private User user;
    Scanner scanner = new Scanner(System.in);
    
    private ArrayList<Product> products = new ArrayList<>();

    private ArrayList<Sale> salesHistory = new ArrayList<>();
    

    public void buyProduct() {
        viewProductList();
        System.out.println("Please enter product id you would like to buy");
        int productId = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter quantity of the product you would like to buy");
        int amountOfProduct = scanner.nextInt();

        Product product = findProductById(productId);

       // syncProducts();

        boolean productIsAvailable = false;

        try {
            productIsAvailable = productIsAvailable(product, user.getBalance(), productId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if(productIsAvailable) {
            try {
                shopController.updateProduct(productId,shopController.getQuantity(productId) - amountOfProduct );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            product.setQuantity(product.getQuantity() - amountOfProduct);
            updateUserBalance(user.getBalance()- (product.getPrice()*amountOfProduct),getActiveUser().getEmail());

            try {
                updateProduct(product);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addSalesHistory(product.getId(), amountOfProduct, product.getPrice());
            System.out.println("Product bought");
        }

    }

    private void syncProducts() {
            try {
                this.products = ShopController.getProducts();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }

    private void addSalesHistory(int productId, int quantity, double total) {
        this.salesHistory.add(new Sale(productId, total, quantity));
    }


    public void viewProductList() {
        ArrayList <Product> productList = new ArrayList<>();
        try {
            productList = shopController.getProductListFromDB();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        displayProductList(productList);
    }



    public User getActiveUser() {
        return this.user;
    }

    public void addProduct() {

        Product product = collectProductInfo();
        products.add(product);
        System.out.println(product);
        try {
            shopController.addProductToDB(product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Product added!");

    }

    public Product collectProductInfo() {
        Product newProduct = new Product();
        System.out.println("Please enter product name");
        newProduct.setName(scanner.nextLine());
        System.out.println("Please enter product price (piece/kilo)");
        newProduct.setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.println("Please enter product quantity");
        newProduct.setQuantity(Integer.parseInt(scanner.nextLine()));
        System.out.println("Please enter product cost");
        newProduct.setCost(Double.parseDouble(scanner.nextLine()));
        newProduct.setId(generateProductId());
        return newProduct;
    }




    public void removeProduct() {
        System.out.println("Please enter product you would like to remove:");
        int productId = Integer.parseInt(scanner.nextLine());
        try {
            shopController.deleteProduct(productId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void viewSaleshistory() {
        double totalSale = 0;
        System.out.println("Name \t Price \t Quantity \t  Total");

        for(Sale sale: salesHistory) {
            Product product = products.get(sale.getProductId());
            System.out.println(product.getName() + "\t " + product.getPrice() + "\t" + product.getQuantity() + "\t" + sale.getTotal());
            totalSale = totalSale + sale.getTotal();

        }
        System.out.println("Total sum " + totalSale);

    }



    public void setActiveUser(User activeUser) {
        this.user = activeUser;
    }

    private int generateProductId() {
        System.out.println("Generating product Id");
        return (products.size()<1) ? 0 : products.get(products.size() - 1).getId() + 1;
    }

    private void displayProductList(ArrayList<Product> productList) {
        System.out.println("Id \t name \t price \t quantity \t cost");
        productList.forEach(System.out::println);
    }

    private boolean productIsAvailable(Product product, double balance, int id) throws Exception {
        if(shopController.getProductName(id) == null) throw new Exception("Invalid input");
        if(shopController.getBalance(getActiveUser().getEmail()) < product.getPrice()) throw new Exception("You don't have enough balance");
        if(shopController.getQuantity(id) < 1) throw new Exception ("Product is not available");
        return true;
    }

    private void updateProduct(Product product) throws SQLException {
        shopController.updateProduct(product.getId(),product.getQuantity());
        for(Product product1: this.products){
            if(product.getId() == product.getId());
            product1.setName(product.getName());
            product1.setQuantity(product.getQuantity());
            product1.setPrice(product.getPrice());
            product1.setCost(product.getCost());
        }
    }

    public void updateUserBalance(double newBalance, String email) {
        System.out.println("Please enter new balance");
        newBalance = Double.parseDouble(scanner.nextLine());
        this.user.setBalance(newBalance);
        System.out.println("New balance for user " + getActiveUser().getEmail() + " is " + user.getBalance());
        try {
            shopController.updateUserBalance(newBalance, user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private Product findProductById(int productId) {
        for(Product product: this.products) {
            if(product.getId() == productId){
                System.out.println("Product found!");
                return product;
            }
        }
        return null;
    }


}
