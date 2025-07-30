# Hotel Management System API

A backend RESTful API for hotel management built with Spring Boot.

---

## 🚀 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- JWT Authentication
- Maven
- Razorpay API (Payment Simulation)

---

## ⚙️ Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/hotel-management-system.git
   cd hotel-management-system
API Documentation
🔐 Authentication
POST /api/auth/register – Register a new user

POST /api/auth/login – Login and receive JWT token

🏨 Rooms
GET /api/rooms – View available rooms

POST /api/rooms – Add a room (Admin)

PUT /api/rooms/{id} – Update a room (Admin)

DELETE /api/rooms/{id} – Delete a room (Admin)

📅 Bookings
POST /api/bookings – Book a room

GET /api/bookings/user – Get user bookings

💳 Payments
POST /api/payments/pay – Simulate a payment
