package McdoGui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManagementScreen extends Stage {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Management");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Button addButton = new Button("Add User");

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO User (Name, Street, City, Zip, Email, PhoneNumber, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, ""); // Street (empty for now)
                pstmt.setString(3, ""); // City (empty for now)
                pstmt.setString(4, ""); // Zip (empty for now)
                pstmt.setString(5, email);
                pstmt.setString(6, ""); // PhoneNumber (empty for now)
                pstmt.setString(7, password);

                pstmt.executeUpdate();
                System.out.println("User added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(addButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}