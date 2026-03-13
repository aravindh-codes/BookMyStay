Book My Stay – Hotel Booking Management System
Project Overview

Book My Stay is a simplified Hotel Booking Management System developed using Core Java and fundamental Data Structures. The project demonstrates how real-world booking systems manage room availability, booking requests, and reservation confirmation while maintaining system consistency and preventing double-booking.
The system is developed incrementally through multiple use cases, each introducing key software engineering and data structure concepts.

Key Concepts Demonstrated

Object-Oriented Programming (OOP)
Encapsulation and Separation of Concerns
HashMap for centralized inventory management
Queue for fair booking request handling (FIFO)
Set for enforcing unique room assignments
Safe inventory updates and booking confirmation
Prevention of double-booking

Use Cases Implemented
UC1 – Application Startup
Initializes the application and displays a welcome message.

UC2 – Room Types & Static Availability
Introduces the domain model for rooms and basic room attributes.

UC3 – Centralized Room Inventory
Uses HashMap to maintain room availability as a single source of truth.

UC4 – Room Search & Availability Check
Allows guests to view available rooms without modifying system state.

UC5 – Booking Request Queue
Uses Queue (FIFO) to store booking requests and ensure fair processing order.

UC6 – Reservation Confirmation & Room Allocation
Confirms reservations by assigning unique room IDs using Set and updating inventory immediately to prevent double-booking.
