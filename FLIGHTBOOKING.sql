-- Drop existing tables (if any)
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
    DepartureDateTime DATETIME NOT NULL,
    ArrivalDateTime DATETIME NOT NULL
);

-- Seats Table
CREATE TABLE IF NOT EXISTS Seats (
    SeatID INT AUTO_INCREMENT PRIMARY KEY,
    FlightID INT NOT NULL,
    SeatNumber VARCHAR(10) NOT NULL,
    SeatType ENUM('ordinary', 'comfort', 'business_class') NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    IsBooked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID)
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS Bookings (
    BookingID INT AUTO_INCREMENT PRIMARY KEY,
    MemberID INT,
    FlightID INT NOT NULL,
    SeatID INT NOT NULL,
    CancellationInsurance BOOLEAN DEFAULT FALSE,
    BookingDateTime DATETIME NOT NULL,
    FOREIGN KEY (MemberID) REFERENCES Members(MemberID),
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

-- Planes Table
CREATE TABLE IF NOT EXISTS Planes (
    PlaneID INT AUTO_INCREMENT PRIMARY KEY,
    Model VARCHAR(255) NOT NULL,
    Capacity INT NOT NULL
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

-- Seats Table
INSERT INTO Seats (FlightID, SeatNumber, SeatType, Price, IsBooked)
VALUES
    -- Seats for Flight 1
    (1, '1', 'ordinary', 100.00, FALSE),
    (1, '2', 'ordinary', 100.00, FALSE),
    (1, '3', 'ordinary', 100.00, FALSE),
    -- ... add seats 4 through 32 with the same FlightID
    (1, '32', 'ordinary', 100.00, FALSE),
    
    -- Seats for Flight 2
    (2, '1', 'ordinary', 100.00, FALSE),
    (2, '2', 'ordinary', 100.00, FALSE),
    (2, '3', 'ordinary', 100.00, FALSE),
    -- ... add seats 4 through 32 with the same FlightID
    (2, '32', 'ordinary', 100.00, FALSE),
    
    -- Seats for Flight 3
    (3, '1', 'ordinary', 100.00, FALSE),
    (3, '2', 'ordinary', 100.00, FALSE),
    (3, '3', 'ordinary', 100.00, FALSE),
    -- ... add seats 4 through 32 with the same FlightID
    (3, '32', 'ordinary', 100.00, FALSE);

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
    ('Tom Davis', 'engineer');

-- Planes Table
INSERT INTO Planes (Model, Capacity)
VALUES
    ('Boeing 737', 150),
    ('Airbus A320', 180),
    ('Boeing 747', 300);

-- Destinations Table
INSERT INTO Destinations (City, Country)
VALUES
    ('Dubai', 'United Arab Emirates'),
    ('Rome', 'Italy'),
    ('Beijing', 'China');
