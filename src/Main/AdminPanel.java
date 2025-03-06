package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPanel extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a layout
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #FFC72C;"); // Yellow background

        // Add buttons
        Button addItemButton = new Button("Add Menu Item");
        styleButton(addItemButton);

        Button managePromotionsButton = new Button("Manage Promotions");
        styleButton(managePromotionsButton);

        Button viewOrdersButton = new Button("View Orders");
        styleButton(viewOrdersButton);

        // Add components to the layout
        root.getChildren().addAll(addItemButton, managePromotionsButton, viewOrdersButton);

        // Set up the scene and stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Admin Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
    }
}