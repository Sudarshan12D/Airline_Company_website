-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    UserID 																		INT AUTO_INCREMENT PRIMARY KEY,
    User_Name VARCHAR(255) 														NOT NULL,
    Email VARCHAR(255) 															UNIQUE NOT NULL,
    UserType ENUM('passenger', 'tourism_agent', 'airline_agent', 'admin') 		NOT NULL,
    MembershipStatus 															BOOLEAN DEFAULT FALSE,
    CreditCardInfo 																VARCHAR(255)
);

-- Flights Table
CREATE TABLE IF NOT EXISTS Flights (
    FlightID 																	INT AUTO_INCREMENT PRIMARY KEY,
    Origin 																		VARCHAR(255) NOT NULL,
    Destination 																VARCHAR(255) NOT NULL,
    DepartureDateTime 															DATETIME NOT NULL,
    ArrivalDateTime 															DATETIME NOT NULL
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
    UserID INT NOT NULL,
    FlightID INT NOT NULL,
    SeatID INT NOT NULL,
    CancellationInsurance BOOLEAN DEFAULT FALSE,
    BookingDateTime DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (SeatID) REFERENCES Seats(SeatID)
);