package McdoGui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomeScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("McDo App - Home");

        // Load McDonald's logo
        Image logoImage = new Image("file:mcdonalds_logo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        // Create buttons with McDonald's theme
        Button addItemButton = createStyledButton("Add Item");
        Button placeOrderButton = createStyledButton("Place Order");
        Button viewTransactionsButton = createStyledButton("View Transactions");
        Button manageUsersButton = createStyledButton("Manage Users");
        Button discountsButton = createStyledButton("Discounts"); // New button for DiscountManagementScreen

        // Set button actions
        addItemButton.setOnAction(e -> {
            MenuItemManagementScreen menuScreen = new MenuItemManagementScreen();
            menuScreen.start(new Stage());
        });

        placeOrderButton.setOnAction(e -> {
            OrderManagementScreen orderScreen = new OrderManagementScreen();
            orderScreen.start(new Stage());
        });

        viewTransactionsButton.setOnAction(e -> {
            TransactionHistoryScreen transactionScreen = new TransactionHistoryScreen();
            transactionScreen.start(new Stage());
        });

        manageUsersButton.setOnAction(e -> {
            UserManagementScreen userScreen = new UserManagementScreen();
            userScreen.start(new Stage());
        });

        discountsButton.setOnAction(e -> {
            DiscountManagementScreen discountScreen = new DiscountManagementScreen();
            discountScreen.start(new Stage());
        });

        // Create layout
        VBox vbox = new VBox(20, logoView, addItemButton, placeOrderButton, viewTransactionsButton, manageUsersButton, discountsButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER); // Center align all elements
        vbox.setStyle("-fx-background-color: #FFC72C;"); // Set background color to McDonald's yellow

        // Create scene
        Scene scene = new Scene(vbox, 400, 450); // Adjusted height to fit the new button
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        button.setPrefWidth(200); // Set button width
        button.setPrefHeight(40); // Set button height
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}