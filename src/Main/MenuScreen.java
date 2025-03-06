package Main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenuScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a ListView to display menu items
        ListView<String> menuList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();

        // Fetch menu items from the database
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItems")) {

            while (rs.next()) {
                String itemName = rs.getString("Description");
                double price = rs.getDouble("Price");
                items.add(itemName + " - $" + price);
            }
        } catch (Exception e) {
            items.add("Error fetching menu items: " + e.getMessage());
        }

        menuList.setItems(items);

        // Set up the layout
        VBox root = new VBox(menuList);
        root.setStyle("-fx-background-color: #FFC72C;"); // Yellow background

        // Set up the scene and stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("McDonald's Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}