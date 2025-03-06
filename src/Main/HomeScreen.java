package Main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Set up the layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #FFC72C;"); // Yellow background

        // Add McDonald's logo (replace with actual image path)
        Image logoImage = new Image("file:path/to/mcdonalds_logo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);

        // Add buttons
        Button menuButton = new Button("Menu");
        styleButton(menuButton);
        menuButton.setOnAction(e -> new MenuScreen().start(new Stage()));

        Button orderButton = new Button("Place Order");
        styleButton(orderButton);
        orderButton.setOnAction(e -> new PlaceOrderScreen().start(new Stage()));

        Button adminButton = new Button("Admin Panel");
        styleButton(adminButton);
        adminButton.setOnAction(e -> new AdminPanel().start(new Stage()));

        // Add components to the layout
        root.getChildren().addAll(logoView, menuButton, orderButton, adminButton);

        // Set up the scene and stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("McDonald's App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #DA291C; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
    }

    public static void main(String[] args) {
        launch(args);
    }
}