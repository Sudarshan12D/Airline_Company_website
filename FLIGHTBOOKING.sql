DROP DATABASE IF EXISTS FLIGHTBOOKING;
CREATE DATABASE FLIGHTBOOKING; 
USE FLIGHTBOOKING;

DROP TABLE IF EXISTS PLANE;
CREATE TABLE PLANE (
    Plane_Number                     varchar(10) not null,
    primary key (Exhibition_Name)
);

DROP TABLE IF EXISTS SEAT;
CREATE TABLE SEAT (
	Seat_Number						varchar(3) not null,
    On_Plane						varchar(10) not null,
    primary key (Seat_Number),
    foreign key (On_Plane) references PLANE(Plane_Number)
);