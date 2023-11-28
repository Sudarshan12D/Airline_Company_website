DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS Seats;
DROP TABLE IF EXISTS Flights;
DROP TABLE IF EXISTS Members;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Crews;
DROP TABLE IF EXISTS Planes;
DROP TABLE IF EXISTS Destinations;

-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FName VARCHAR(255) NOT NULL,
    LName VARCHAR(255) NOT NULL,
    User_Address VARCHAR(255) NOT NULL,
    User_Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL
);

-- Members Table
CREATE TABLE IF NOT EXISTS Members (
    MemberID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    CreditCardInfo VARCHAR(255) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Flights Table
CREATE TABLE IF NOT EXISTS Flights (
    FlightID INT AUTO_INCREMENT PRIMARY KEY,
    Origin VARCHAR(255) NOT NULL,
    Destination VARCHAR(255) NOT NULL,
    DepartureDateTime VARCHAR(20) NOT NULL,
    ArrivalDateTime VARCHAR(20) NOT NULL
);

-- Planes Table
CREATE TABLE IF NOT EXISTS Planes (
    PlaneID INT AUTO_INCREMENT PRIMARY KEY,
    Model VARCHAR(255) NOT NULL,
    Capacity INT NOT NULL
);

-- Seats Table
CREATE TABLE IF NOT EXISTS Seats (
    SeatID INT AUTO_INCREMENT PRIMARY KEY,
    PlaneID INT NOT NULL,
    SeatNumber VARCHAR(10) NOT NULL,
    SeatType ENUM('firstClass', 'economy', 'business') NOT NULL,
    Price INT NOT NULL,
    IsBooked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (PlaneID) REFERENCES Planes(PlaneID)
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS Bookings (
    BookingID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    FlightID INT NOT NULL,
    SeatID INT NOT NULL,
    CancellationInsurance BOOLEAN DEFAULT FALSE,
    BookingDateTime DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Members(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (SeatID) REFERENCES Seats(SeatID)
);
-- Payments Table
CREATE TABLE IF NOT EXISTS Payments (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    BookingID INT NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentDateTime DATETIME NOT NULL,
    CreditCardUsed VARCHAR(255) NOT NULL,
    FOREIGN KEY (BookingID) REFERENCES Bookings(BookingID)
);

-- Crews Table
CREATE TABLE IF NOT EXISTS Crews (
    CrewID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Position ENUM('pilot', 'flight_attendant', 'engineer') NOT NULL
);

-- Destinations Table
CREATE TABLE IF NOT EXISTS Destinations (
    DestinationID INT AUTO_INCREMENT PRIMARY KEY,
    City VARCHAR(255) NOT NULL,
    Country VARCHAR(255) NOT NULL
);

-- Users Table
INSERT INTO Users (FName, LName, User_Address, Email, User_Password)
VALUES
    ('John', 'Doe', '3RD STREET ', 'john.doe@example.com', 'hello'),
    ('Jane', 'Smith', '2ND STRRET ','jane.smith@example.com', 'mypassword3'),
    ('Robert', 'Johnson', '4TH AVE', 'robert.johnson@example.com', 'ummmm'),
    ('Alice', 'Williams', '5TH AVENUE', 'alice.williams@example.com', 'password123'),
    ('Bob', 'Miller', '6TH AVENUE', 'bob.miller@example.com', 'securepass');

-- Members Table
INSERT INTO Members (UserID, CreditCardInfo)
VALUES
    (1, '****-****-****-1234'),
    (2, '****-****-****-5678'),
    (3, '****-****-****-9012');

-- Flights Table
INSERT INTO Flights (Origin, Destination, DepartureDateTime, ArrivalDateTime)
VALUES
    ('New York', 'Los Angeles', '2023-12-01 08:00:00', '2023-12-01 11:00:00'),
    ('London', 'Paris', '2023-12-02 14:30:00', '2023-12-02 16:00:00'),
    ('Tokyo', 'Sydney', '2023-12-03 20:45:00', '2023-12-04 06:30:00');

-- Planes Table
INSERT INTO Planes (Model, Capacity)
VALUES
    ('Boeing 737', 32),
    ('Airbus A320', 32),
    ('Boeing 747', 32);


-- Seats Table
INSERT INTO Seats (PlaneID, SeatNumber, SeatType, Price, IsBooked)
VALUES
    -- Seats for Flight 1
    (1, '1', 'firstClass', 700, FALSE),
    (1, '2', 'firstClass', 700, FALSE),
    (1, '3', 'firstClass', 700, FALSE),
    (1, '4', 'firstClass', 700, FALSE),
    (1, '5', 'firstClass', 700, FALSE),
    (1, '6', 'firstClass', 700, FALSE),
    (1, '7', 'firstClass', 700, FALSE),
    (1, '8', 'firstClass', 700, FALSE),
    (1, '9', 'firstClass', 700, FALSE),
    (1, '10', 'firstClass', 700, FALSE),
    (1, '11', 'firstClass', 700, FALSE),
    (1, '12', 'firstClass', 700, FALSE),
    (1, '13', 'Economy', 500, FALSE),
    (1, '14', 'Economy', 500, FALSE),
    (1, '15', 'Economy', 500, FALSE),
    (1, '16', 'Economy', 500, FALSE),
    (1, '17', 'Economy', 500, FALSE),
    (1, '18', 'Economy', 500, FALSE),
    (1, '19', 'Economy', 500, FALSE),
    (1, '20', 'Economy', 500, FALSE),
    (1, '21', 'Economy', 500, FALSE),
    (1, '22', 'Economy', 500, FALSE),
    (1, '23', 'Economy', 500, FALSE),
    (1, '24', 'Economy', 500, FALSE),
    (1, '25', 'Economy', 500, FALSE),
    (1, '26', 'Economy', 500, FALSE),
    (1, '27', 'Economy', 500, FALSE),
    (1, '28', 'Economy', 500, FALSE),
    (1, '29', 'Economy', 500, FALSE),
    (1, '30', 'Economy', 500, FALSE),
    (1, '31', 'Business', 700, FALSE),
    (1, '32', 'Business', 700, FALSE),
    (1, '33', 'Business', 700, FALSE),
    (1, '34', 'Business', 700, FALSE),
    (1, '35', 'Business', 700, FALSE),
    (1, '36', 'Business', 700, FALSE),
    
    -- Seats for Flight 2
    (2, '1', 'firstClass', 700, FALSE),
    (2, '2', 'firstClass', 700, FALSE),
    (2, '3', 'firstClass', 700, FALSE),
    (2, '4', 'firstClass', 700, FALSE),
    (2, '5', 'firstClass', 700, FALSE),
    (2, '6', 'firstClass', 700, FALSE),
    (2, '7', 'firstClass', 700, FALSE),
    (2, '8', 'firstClass', 700, FALSE),
    (2, '9', 'firstClass', 700, FALSE),
    (2, '10', 'firstClass', 700, FALSE),
    (2, '11', 'firstClass', 700, FALSE),
    (2, '12', 'firstClass', 700, FALSE),
    (2, '13', 'Economy', 500, FALSE),
    (2, '14', 'Economy', 500, FALSE),
    (2, '15', 'Economy', 500, FALSE),
    (2, '16', 'Economy', 500, FALSE),
    (2, '17', 'Economy', 500, FALSE),
    (2, '18', 'Economy', 500, FALSE),
    (2, '19', 'Economy', 500, FALSE),
    (2, '20', 'Economy', 500, FALSE),
    (2, '21', 'Economy', 500, FALSE),
    (2, '22', 'Economy', 500, FALSE),
    (2, '23', 'Economy', 500, FALSE),
    (2, '24', 'Economy', 500, FALSE),
    (2, '25', 'Economy', 500, FALSE),
    (2, '26', 'Economy', 500, FALSE),
    (2, '27', 'Economy', 500, FALSE),
    (2, '28', 'Economy', 500, FALSE),
    (2, '29', 'Economy', 500, FALSE),
    (2, '30', 'Economy', 500, FALSE),
    (2, '31', 'Business', 700, FALSE),
    (2, '32', 'Business', 700, FALSE),
    (2, '33', 'Business', 700, FALSE),
    (2, '34', 'Business', 700, FALSE),
    (2, '35', 'Business', 700, FALSE),
    (2, '36', 'Business', 700, FALSE),
    
    -- Seats for Flight 3
    (3, '1', 'firstClass', 700, FALSE),
    (3, '2', 'firstClass', 700, FALSE),
    (3, '3', 'firstClass', 700, FALSE),
    (3, '4', 'firstClass', 700, FALSE),
    (3, '5', 'firstClass', 700, FALSE),
    (3, '6', 'firstClass', 700, FALSE),
    (3, '7', 'firstClass', 700, FALSE),
    (3, '8', 'firstClass', 700, FALSE),
    (3, '9', 'firstClass', 700, FALSE),
    (3, '10', 'firstClass', 700, FALSE),
    (3, '11', 'firstClass', 700, FALSE),
    (3, '12', 'firstClass', 700, FALSE),
    (3, '13', 'Economy', 500, FALSE),
    (3, '14', 'Economy', 500, FALSE),
    (3, '15', 'Economy', 500, FALSE),
    (3, '16', 'Economy', 500, FALSE),
    (3, '17', 'Economy', 500, FALSE),
    (3, '18', 'Economy', 500, FALSE),
    (3, '19', 'Economy', 500, FALSE),
    (3, '20', 'Economy', 500, FALSE),
    (3, '21', 'Economy', 500, FALSE),
    (3, '22', 'Economy', 500, FALSE),
    (3, '23', 'Economy', 500, FALSE),
    (3, '24', 'Economy', 500, FALSE),
    (3, '25', 'Economy', 500, FALSE),
    (3, '26', 'Economy', 500, FALSE),
    (3, '27', 'Economy', 500, FALSE),
    (3, '28', 'Economy', 500, FALSE),
    (3, '29', 'Economy', 500, FALSE),
    (3, '30', 'Economy', 500, FALSE),
    (3, '31', 'Business', 700, FALSE),
    (3, '32', 'Business', 700, FALSE),
    (3, '33', 'Business', 700, FALSE),
    (3, '34', 'Business', 700, FALSE),
    (3, '35', 'Business', 700, FALSE),
    (3, '36', 'Business', 700, FALSE);

-- Bookings Table
INSERT INTO Bookings (MemberID, FlightID, SeatID, CancellationInsurance, BookingDateTime)
VALUES
    (1, 1, 1, TRUE, '2023-11-18 10:30:00'),
    (2, 2, 2, FALSE, '2023-11-18 12:45:00'),
    (3, 3, 3, TRUE, '2023-11-18 15:00:00');

-- Payments Table
INSERT INTO Payments (BookingID, Amount, PaymentDateTime, CreditCardUsed)
VALUES
    (1, 120.00, '2023-11-18 11:00:00', '****-****-****-1234'),
    (2, 150.00, '2023-11-18 13:15:00', '****-****-****-5678'),
    (3, 200.00, '2023-11-18 16:30:00', '****-****-****-9012');

-- Crews Table
INSERT INTO Crews (Name, Position)
VALUES
    ('Captain Smith', 'pilot'),
    ('Linda Johnson', 'flight_attendant'),
    ('Tom Davis', 'engineer'),
	('Emily Rodriguez', 'pilot'),
    ('Michael Turner', 'flight_attendant'),
    ('Sarah White', 'engineer'),
    ('John Anderson', 'pilot'),
    ('Megan Clark', 'flight_attendant'),
    ('Robert Harris', 'engineer');

-- Destinations Table
INSERT INTO Destinations (City, Country)
VALUES
    ('Dubai', 'United Arab Emirates'),
    ('Rome', 'Italy'),
    ('Beijing', 'China');