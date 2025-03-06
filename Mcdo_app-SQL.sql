-- Create the User table
CREATE TABLE IF NOT EXISTS User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Street VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    Zip VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL
);

-- Create the Promotions table
CREATE TABLE IF NOT EXISTS Promotions (
    PromotionID INT PRIMARY KEY AUTO_INCREMENT,
    PromotionName VARCHAR(255) NOT NULL,
    Discount DECIMAL(5, 2) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL
);

-- Create the MenuItems table
CREATE TABLE IF NOT EXISTS MenuItems (
    ItemID INT PRIMARY KEY AUTO_INCREMENT,
    Description VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    PromotionID INT,
    FOREIGN KEY (PromotionID) REFERENCES Promotions(PromotionID)
);

-- Create the Order table
CREATE TABLE IF NOT EXISTS `Order` (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    Amount INT NOT NULL,
    OrderStatus VARCHAR(255) NOT NULL,
    ItemID INT NOT NULL,
    ToStreet VARCHAR(255) NOT NULL,
    CustomerName VARCHAR(255) NOT NULL,
    ToCity VARCHAR(255) NOT NULL,
    ToZip VARCHAR(255) NOT NULL,
    DeliveryDate DATE NOT NULL,
    PaymentID INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (ItemID) REFERENCES MenuItems(ItemID)
);

-- Create the PaymentMethod table
CREATE TABLE IF NOT EXISTS PaymentMethod (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT NOT NULL,
    PaymentMethod VARCHAR(255) NOT NULL,
    PaymentStatus VARCHAR(255) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID)
);

-- Insert sample data into User
INSERT INTO User (Name, Street, City, Zip, Email, PhoneNumber, Password) VALUES
('John Doe', '123 Main St', 'Manila', '1000', 'john.doe@example.com', '09171234567', 'password123'),
('Jane Smith', '456 Elm St', 'Quezon City', '1100', 'jane.smith@example.com', '09176543210', 'password456');

-- Insert sample data into Promotions
INSERT INTO Promotions (PromotionName, Discount, StartDate, EndDate) VALUES
('Summer Sale', 10.00, '2023-10-01', '2023-10-31'),
('Winter Discount', 15.00, '2023-12-01', '2023-12-31');

-- Insert sample data into MenuItems
INSERT INTO MenuItems (Description, Price, PromotionID) VALUES
('1-pc Chicken McDo with drink', 99.00, NULL),  -- No promotion
('Crispy Chicken Fillet Ala King with Extra Rice', 99.00, NULL),  -- No promotion
('1-pc Mushroom Pepper Steak with fries', 99.00, 1),  -- PromotionID 1 refers to 'Summer Sale'
('Cheeseburger with regular fries', 99.00, NULL);  -- No promotion

-- Insert sample data into Order
INSERT INTO `Order` (UserID, Amount, OrderStatus, ItemID, ToStreet, CustomerName, ToCity, ToZip, DeliveryDate, PaymentID) VALUES
(1, 2, 'Preparing', 1, '123 Main St', 'John Doe', 'Manila', '1000', '2023-10-15', 1),
(2, 1, 'Ready', 3, '456 Elm St', 'Jane Smith', 'Quezon City', '1100', '2023-10-16', 2);

-- Insert sample data into PaymentMethod
INSERT INTO PaymentMethod (OrderID, PaymentMethod, PaymentStatus) VALUES
(1, 'Credit Card', 'Paid'),
(2, 'Cash', 'Pending');