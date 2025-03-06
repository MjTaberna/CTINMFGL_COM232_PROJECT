package McdoGui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHistoryScreen extends Application {

    public static class Transaction {
        private final int orderID;
        private final String customerName;
        private final String item;
        private final String paymentMethod;
        private final String paymentStatus;

        public Transaction(int orderID, String customerName, String item, String paymentMethod, String paymentStatus) {
            this.orderID = orderID;
            this.customerName = customerName;
            this.item = item;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
        }

        public int getOrderID() {
            return orderID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getItem() {
            return item;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Transaction History");

        // Create a layout for the screen
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER); // Center align all elements
        vbox.setStyle("-fx-background-color: #FFC72C;"); // Set background color to McDonald's yellow

        // Load McDonald's logo (replace with actual path to the logo image)
        Image logoImage = new Image("file:mcdonalds_logo.png"); // Ensure the image file is in the correct path
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150); // Adjust size as needed
        logoView.setPreserveRatio(true);

        // Create a label for the title
        Label titleLabel = new Label("Transaction History");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Set font and size
        titleLabel.setTextFill(Color.web("#DA291C")); // Set text color to McDonald's red

        // Create a TableView to display transaction history
        TableView<Transaction> tableView = new TableView<>();
        tableView.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14px;"); // Set table style

        // Create columns for the TableView
        TableColumn<Transaction, Integer> orderIDColumn = new TableColumn<>("Order ID");
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<Transaction, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Transaction, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

        TableColumn<Transaction, String> paymentMethodColumn = new TableColumn<>("Payment Method");
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        TableColumn<Transaction, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

        // Add columns to the TableView
        tableView.getColumns().addAll(orderIDColumn, customerColumn, itemColumn, paymentMethodColumn, statusColumn);

        // Fetch transaction history from the database
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT o.OrderID, o.CustomerName, m.Description, p.PaymentMethod, p.PaymentStatus " +
                         "FROM `Order` o " +
                         "JOIN MenuItems m ON o.ItemID = m.ItemID " +
                         "JOIN PaymentMethod p ON o.OrderID = p.OrderID"; // Corrected join condition
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set and add each transaction to the TableView
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("OrderID"),
                        rs.getString("CustomerName"),
                        rs.getString("Description"),
                        rs.getString("PaymentMethod"),
                        rs.getString("PaymentStatus")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Set the items in the TableView
        tableView.setItems(transactions);

        // Wrap the TableView in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true); // Ensure the ScrollPane fits the width of the TableView
        scrollPane.setStyle("-fx-background: #FFFFFF; -fx-border-color: #DA291C;"); // Style the ScrollPane

        // Add components to the layout
        vbox.getChildren().addAll(logoView, titleLabel, scrollPane);

        // Set up the scene
        Scene scene = new Scene(vbox, 600, 400); // Adjusted size for better layout
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}