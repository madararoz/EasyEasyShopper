import controllers.Menu;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.start();

    }
}


//    Build an application for a supermarket. The application should be able to collect information about products in the supermarket
//    (name, the total number of the product available, price, weight and etc).
//     Should have a main menu which is controlled by a loop where users can choose what they want to do e.g add new user,
//     add product, buy product, etc.
//     We should be able to buy multiple products from the supermarket and every time a product is bought, the number of
//     the available products should go down. Also should be a report on how much has been sold and the history of sales.
//     For products, we would like to track the cost of the goods supplied to the shop and how much is the selling price.
//     This way, we can also provide a report of sales to the shop owner (like a profit and loss statement)
//     When the product is finished, the system should show an error if you try to buy the product error should be “item is sold out”.
//     It should be possible to tell what you want to buy from the supermarket by giving the name of the product with
//     scanner (keyboard) and how many you want to buy at a time, also with scanner.
//     All information about the application should be collected through the scanner if you make an application in CLI / terminal
//     or if you want an hybrid solution, you can use something called JOption which has input with your CLI or if you have
//     the knowledge, you can make a UI application.
//     For user, it should be possible to provide name, email, password and balance for user in the supermarket
//     (multiple user can be registered) and when a user will buy product, user balance should be reduced.
//     If the user doesn’t have money left also error should be shown to the user “not enough money to buy product”
//     Should be possible to register new user or switch users
//     Only certain type of users can add product to the supermarket, made different types for users such as
//     (buyer, sales representative, owner etc), at start of program, ask user to login using their info then
//     direct them to the correct menu for their user type.
//     Should be possible collect all information about the product user etc at the start of program and then
//     ask the user to enter the name of product they want to buy, after that quantity and then do all logic what is mentioned in the task.
//     Save all information to the database or file depending on what you have better understanding of.