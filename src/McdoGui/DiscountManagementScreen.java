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

public class DiscountManagementScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Discount Management");

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: #FFC72C;"); // McDonald's yellow background

        // Load McDonald's logo
        Image logoImage = new Image("file:mcdonalds_logo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        // Discount Management UI
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Item selection
        Label itemLabel = new Label("Select Item:");
        itemLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        itemLabel.setTextFill(Color.web("#000000")); // Black text

        TextField itemField = new TextField();
        itemField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        // Discount input
        Label discountLabel = new Label("Discount (%):");
        discountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        discountLabel.setTextFill(Color.web("#000000")); // Black text

        TextField discountField = new TextField();
        discountField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;");

        // Apply discount button
        Button applyButton = new Button("Apply Discount");
        applyButton.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        applyButton.setPrefWidth(150);
        applyButton.setPrefHeight(30);

        // Display discounted menu
        Label menuLabel = new Label("Discounted Menu:");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        menuLabel.setTextFill(Color.web("#000000")); // Black text

        Label discountedMenu = new Label();
        discountedMenu.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        discountedMenu.setTextFill(Color.web("#000000")); // Black text

        // Apply discount logic
        applyButton.setOnAction(e -> {
            String item = itemField.getText();
            double discount = Double.parseDouble(discountField.getText());

            // Calculate discounted price (example logic)
            double originalPrice = 99.0; // Example price
            double discountedPrice = originalPrice * (1 - discount / 100);

            // Update the discounted menu display
            discountedMenu.setText(item + ": $" + String.format("%.2f", discountedPrice));
        });

        // Add components to the grid
        grid.add(itemLabel, 0, 0);
        grid.add(itemField, 1, 0);
        grid.add(discountLabel, 0, 1);
        grid.add(discountField, 1, 1);
        grid.add(applyButton, 1, 2);
        grid.add(menuLabel, 0, 3);
        grid.add(discountedMenu, 1, 3);

        // Add components to the main layout
        mainLayout.getChildren().addAll(logoView, grid);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}