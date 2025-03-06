package McdoGui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class OrderManagementScreen extends Stage {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Place Order");

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

        // Create a label for the title
        Label titleLabel = new Label("Select Items");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Set font and size
        titleLabel.setTextFill(Color.web("#DA291C")); // Set text color to McDonald's red

        // GridPane to display menu items
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(20); // Horizontal gap between items
        menuGrid.setVgap(20); // Vertical gap between items
        menuGrid.setAlignment(Pos.CENTER); // Center align the grid

        // Fetch menu items from the database and display them in the grid
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT ItemID, Description, Price FROM MenuItems";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            int row = 0;
            int col = 0;

            while (rs.next()) {
                int itemID = rs.getInt("ItemID");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");

                // Create a layout for each menu item
                VBox itemBox = new VBox(10);
                itemBox.setPadding(new Insets(10));
                itemBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #DA291C; -fx-border-radius: 5; -fx-padding: 10;");

                // Add an image (placeholder for now)
                ImageView imageView = new ImageView(new Image("file:placeholder.png")); // Replace with actual image path
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                // Add item details
                Label nameLabel = new Label(description);
                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
                nameLabel.setTextFill(Color.web("#000000")); // Set text color to black

                Label priceLabel = new Label("$" + price);
                priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font and size
                priceLabel.setTextFill(Color.web("#000000")); // Set text color to black

                // Add a "Select" button
                Button selectButton = new Button("Select");
                selectButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
                selectButton.setPrefWidth(100); // Set button width
                selectButton.setPrefHeight(30); // Set button height

                selectButton.setOnAction(e -> {
                    // Open a dialog to select payment method
                    ChoiceDialog<String> paymentDialog = new ChoiceDialog<>("Cash", "Cash", "Online Payment", "Card Payment");
                    paymentDialog.setTitle("Payment Method");
                    paymentDialog.setHeaderText("Select Payment Method");
                    paymentDialog.setContentText("Choose your payment method:");

                    paymentDialog.showAndWait().ifPresent(paymentMethod -> {
                        // Place the order
                        placeOrder(itemID, description, price, paymentMethod);
                        System.out.println("Selected: " + description + " (ID: " + itemID + ") with payment: " + paymentMethod);
                    });
                });

                // Add components to the item layout
                itemBox.getChildren().addAll(imageView, nameLabel, priceLabel, selectButton);

                // Add the item layout to the grid
                menuGrid.add(itemBox, col, row);

                // Update grid position
                col++;
                if (col > 2) { // 3 items per row
                    col = 0;
                    row++;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Wrap the GridPane in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(menuGrid);
        scrollPane.setFitToWidth(true); // Ensure the ScrollPane fits the width of the GridPane
        scrollPane.setFitToHeight(true); // Ensure the ScrollPane fits the height of the GridPane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show vertical scrollbar only when needed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Never show horizontal scrollbar
        scrollPane.setStyle("-fx-background: #FFC72C; -fx-border-color: #DA291C;"); // Style the ScrollPane

        // Add components to the main layout
        mainLayout.getChildren().addAll(logoView, titleLabel, scrollPane);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 800, 600); // Adjusted size for better layout
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void placeOrder(int itemID, String description, double price, String paymentMethod) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insert into Order table
            String orderSql = "INSERT INTO `Order` (UserID, Amount, OrderStatus, ItemID, ToStreet, CustomerName, ToCity, ToZip, DeliveryDate, PaymentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement orderStmt = conn.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, 1); // Replace with actual UserID (e.g., from logged-in user)
            orderStmt.setInt(2, 1); // Amount (e.g., quantity)
            orderStmt.setString(3, "Preparing"); // OrderStatus
            orderStmt.setInt(4, itemID); // ItemID
            orderStmt.setString(5, "123 Main St"); // ToStreet (replace with actual address)
            orderStmt.setString(6, "John Doe"); // CustomerName (replace with actual name)
            orderStmt.setString(7, "Manila"); // ToCity (replace with actual city)
            orderStmt.setString(8, "1000"); // ToZip (replace with actual zip)
            orderStmt.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now())); // DeliveryDate (current date)
            orderStmt.setInt(10, 1); // PaymentID (dummy value, will be updated after inserting into PaymentMethod)

            orderStmt.executeUpdate();

            // Get the generated OrderID
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            int orderID = -1;
            if (generatedKeys.next()) {
                orderID = generatedKeys.getInt(1);
            }

            // Insert into PaymentMethod table
            String paymentSql = "INSERT INTO PaymentMethod (OrderID, PaymentMethod, PaymentStatus) VALUES (?, ?, ?)";
            PreparedStatement paymentStmt = conn.prepareStatement(paymentSql);
            paymentStmt.setInt(1, orderID); // OrderID
            paymentStmt.setString(2, paymentMethod); // PaymentMethod
            paymentStmt.setString(3, "Pending"); // PaymentStatus

            paymentStmt.executeUpdate();

            System.out.println("Order placed successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}