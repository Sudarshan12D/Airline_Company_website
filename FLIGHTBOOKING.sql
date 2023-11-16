-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    UserID 																		INT AUTO_INCREMENT PRIMARY KEY,
    User_Name VARCHAR(255) 														NOT NULL,
    Email VARCHAR(255) 															UNIQUE NOT NULL,
    UserType ENUM('passenger', 'tourism_agent', 'airline_agent', 'admin') 		NOT NULL,
    MembershipStatus 															BOOLEAN DEFAULT FALSE,
    CreditCardInfo 																VARCHAR(255)
);