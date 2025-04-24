# Spring Boot Application - Booking System

## Prerequisites
1. **Java JDK 17** or later installed.
2. **Maven** for dependency management.
3. **INMemoryDB** .
4. **application.properties** configured with database credentials.
5. An IDE like **IntelliJ IDEA** or **Eclipse** installed.

---

## Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/karthikdc18/Booking.git

cd Booking

mvn clean install

mvn spring-boot:run

##API Endpoints Overview
- POST Register: Register a new user.
- POST Show Timing: Retrieve show timings.
- POST Book Ticket: Book tickets.
- GET View Booking for User: View all bookings of a user.
- GET Trending: Fetch trending movies/shows.
- GET Search By Genre: Search shows by genre.
- DELETE Cancel: Cancel bookings.



