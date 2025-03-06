package McdoGui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentMethodManagementScreen extends Stage {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Payment Method Management");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label methodNameLabel = new Label("Method Name:");
        TextField methodNameField = new TextField();
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();
        Button addButton = new Button("Add Payment Method");

        addButton.setOnAction(e -> {
            String methodName = methodNameField.getText();
            String description = descriptionField.getText();

            // Add payment method to database
            System.out.println("Added payment method: " + methodName + " - " + description);
        });

        grid.add(methodNameLabel, 0, 0);
        grid.add(methodNameField, 1, 0);
        grid.add(descriptionLabel, 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(addButton, 1, 2);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}