package McdoGui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.sql.SQLException;

public class PromotionManagementScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Promotion Management");

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

        // Promotion Management UI
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

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

        Button addButton = new Button("Add Promotion");
        addButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        addButton.setPrefWidth(150); // Set button width
        addButton.setPrefHeight(30); // Set button height

        addButton.setOnAction(e -> {
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

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(discountLabel, 0, 1);
        grid.add(discountField, 1, 1);
        grid.add(startDateLabel, 0, 2);
        grid.add(startDateField, 1, 2);
        grid.add(endDateLabel, 0, 3);
        grid.add(endDateField, 1, 3);
        grid.add(addButton, 1, 4);

        // Add components to the main layout
        mainLayout.getChildren().addAll(logoView, grid);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}