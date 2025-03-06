package Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlaceOrderScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a form layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #FFC72C;"); // Yellow background

        // Add form fields
        grid.add(new Label("Item ID:"), 0, 0);
        TextField itemIdField = new TextField();
        grid.add(itemIdField, 1, 0);

        grid.add(new Label("Quantity:"), 0, 1);
        TextField quantityField = new TextField();
        grid.add(quantityField, 1, 1);

        grid.add(new Label("Street:"), 0, 2);
        TextField streetField = new TextField();
        grid.add(streetField, 1, 2);

        grid.add(new Label("City:"), 0, 3);
        TextField cityField = new TextField();
        grid.add(cityField, 1, 3);

        grid.add(new Label("Zip:"), 0, 4);
        TextField zipField = new TextField();
        grid.add(zipField, 1, 4);

        grid.add(new Label("Payment Method:"), 0, 5);
        ComboBox<String> paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll("Cash", "Credit Card");
        grid.add(paymentMethodCombo, 1, 5);

        // Add place order button
        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setOnAction(e -> {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO `Order` (UserID, Amount, OrderStatus, ItemID, ToStreet, ToCity, ToZip, PaymentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);

                // Set values for the query
                pstmt.setInt(1, 1); // Replace with actual UserID
                pstmt.setInt(2, Integer.parseInt(quantityField.getText()));
                pstmt.setString(3, "Queued");
                pstmt.setInt(4, Integer.parseInt(itemIdField.getText()));
                pstmt.setString(5, streetField.getText());
                pstmt.setString(6, cityField.getText());
                pstmt.setString(7, zipField.getText());
                pstmt.setInt(8, paymentMethodCombo.getSelectionModel().getSelectedIndex() + 1); // PaymentID

                pstmt.executeUpdate();
                showAlert("Order placed successfully!");
            } catch (Exception ex) {
                showAlert("Error placing order: " + ex.getMessage());
            }
        });
        grid.add(placeOrderButton, 1, 6);

        // Set up the scene and stage
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setTitle("Place Order");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}