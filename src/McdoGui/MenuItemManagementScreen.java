package McdoGui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemManagementScreen extends Stage {

    private boolean isAdminLoggedIn = false; // Track login status

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Item Management");

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER); // Center align all elements
        mainLayout.setStyle("-fx-background-color: #FFC72C;"); // Set background color to McDonald's yellow

        // Load McDonald's logo (replace with actual path to the logo image)
        Image logoImage = new Image("file:mcdonalds_logo.png"); // Ensure the image file is in the correct path
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150); // Adjust size as needed
        logoView.setPreserveRatio(true);

        // Login Section
        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(20));
        loginGrid.setVgap(10);
        loginGrid.setHgap(10);
        loginGrid.setAlignment(Pos.CENTER);

        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        emailLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        passwordLabel.setTextFill(Color.web("#000000")); // Set text color to black

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        loginButton.setPrefWidth(100); // Set button width
        loginButton.setPrefHeight(30); // Set button height

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (authenticateUser(email, password)) {
                isAdminLoggedIn = true;
                showMainMenuUI(mainLayout, primaryStage); // Show the main menu UI
            } else {
                System.out.println("Invalid credentials");
            }
        });

        loginGrid.add(emailLabel, 0, 0);
        loginGrid.add(emailField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);

        // Add login section to the main layout
        mainLayout.getChildren().addAll(logoView, loginGrid);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean authenticateUser(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM User WHERE Email = ? AND Password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // If a record is found, return true
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void showMainMenuUI(VBox mainLayout, Stage primaryStage) {
        // Clear the login UI
        mainLayout.getChildren().clear();

        // Main Menu UI
        Button manageMenuButton = new Button("Manage Menu");
        manageMenuButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        manageMenuButton.setPrefWidth(150); // Set button width
        manageMenuButton.setPrefHeight(40); // Set button height

        Button manageDiscountsButton = new Button("Discounts");
        manageDiscountsButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        manageDiscountsButton.setPrefWidth(150); // Set button width
        manageDiscountsButton.setPrefHeight(40); // Set button height

        manageMenuButton.setOnAction(e -> showMenuManagementUI(mainLayout, primaryStage));
        manageDiscountsButton.setOnAction(e -> showPromotionsManagementUI(mainLayout, primaryStage));

        // Add buttons to the main layout
        mainLayout.getChildren().addAll(manageMenuButton, manageDiscountsButton);

        // Update the scene
        primaryStage.sizeToScene(); // Resize the window to fit the new content
    }

    private void showMenuManagementUI(VBox mainLayout, Stage primaryStage) {
        // Clear the main menu UI
        mainLayout.getChildren().clear();

        // Menu Management UI
        GridPane menuGrid = new GridPane();
        menuGrid.setPadding(new Insets(20));
        menuGrid.setVgap(10);
        menuGrid.setHgap(10);
        menuGrid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Item Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        nameLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Label priceLabel = new Label("Price:");
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        priceLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField priceField = new TextField();
        priceField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Button addButton = new Button("Add Item");
        addButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        addButton.setPrefWidth(100); // Set button width
        addButton.setPrefHeight(30); // Set button height

        Button deleteButton = new Button("Delete Item");
        deleteButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        deleteButton.setPrefWidth(100); // Set button width
        deleteButton.setPrefHeight(30); // Set button height

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO MenuItems (Description, Price) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setDouble(2, price);
                pstmt.executeUpdate();
                System.out.println("Menu item added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        deleteButton.setOnAction(e -> {
            String name = nameField.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "DELETE FROM MenuItems WHERE Description = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                int rowsDeleted = pstmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Menu item deleted successfully!");
                } else {
                    System.out.println("No item found with that name.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        menuGrid.add(nameLabel, 0, 0);
        menuGrid.add(nameField, 1, 0);
        menuGrid.add(priceLabel, 0, 1);
        menuGrid.add(priceField, 1, 1);
        menuGrid.add(addButton, 0, 2);
        menuGrid.add(deleteButton, 1, 2);

        // Add menu management UI to the main layout
        mainLayout.getChildren().add(menuGrid);

        // Update the scene
        primaryStage.sizeToScene(); // Resize the window to fit the new content
    }

    private void showPromotionsManagementUI(VBox mainLayout, Stage primaryStage) {
        // Clear the main menu UI
        mainLayout.getChildren().clear();

        // Promotions Management UI
        GridPane promotionsGrid = new GridPane();
        promotionsGrid.setPadding(new Insets(20));
        promotionsGrid.setVgap(10);
        promotionsGrid.setHgap(10);
        promotionsGrid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Promotion Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        nameLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Label discountLabel = new Label("Discount (%):");
        discountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        discountLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField discountField = new TextField();
        discountField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Label startDateLabel = new Label("Start Date (YYYY-MM-DD):");
        startDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        startDateLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField startDateField = new TextField();
        startDateField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Label endDateLabel = new Label("End Date (YYYY-MM-DD):");
        endDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
        endDateLabel.setTextFill(Color.web("#000000")); // Set text color to black

        TextField endDateField = new TextField();
        endDateField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        Button addPromotionButton = new Button("Add Promotion");
        addPromotionButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        addPromotionButton.setPrefWidth(150); // Set button width
        addPromotionButton.setPrefHeight(30); // Set button height

        addPromotionButton.setOnAction(e -> {
            String name = nameField.getText();
            double discount = Double.parseDouble(discountField.getText());
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO Promotions (PromotionName, Discount, StartDate, EndDate) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setDouble(2, discount);
                pstmt.setString(3, startDate);
                pstmt.setString(4, endDate);
                pstmt.executeUpdate();
                System.out.println("Promotion added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        promotionsGrid.add(nameLabel, 0, 0);
        promotionsGrid.add(nameField, 1, 0);
        promotionsGrid.add(discountLabel, 0, 1);
        promotionsGrid.add(discountField, 1, 1);
        promotionsGrid.add(startDateLabel, 0, 2);
        promotionsGrid.add(startDateField, 1, 2);
        promotionsGrid.add(endDateLabel, 0, 3);
        promotionsGrid.add(endDateField, 1, 3);
        promotionsGrid.add(addPromotionButton, 1, 4);

        // Add promotions management UI to the main layout
        mainLayout.getChildren().add(promotionsGrid);

        // Update the scene
        primaryStage.sizeToScene(); // Resize the window to fit the new content
    }

    public static void main(String[] args) {
        launch(args);
    }
}