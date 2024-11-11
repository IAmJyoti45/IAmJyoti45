---

# Registration Management System

A web-based Registration Management System built with Java Servlets, JSP, JDBC, HTML, CSS, and MySQL. This system allows users to register and manage information, with admin capabilities to review user data.

## Features

- **User Registration:** Users can register with fields such as name, email, password, mobile number, date of birth, and more.
- **Admin Dashboard:** Admins can manage registered users and view user details.
- **Responsive Design:** The frontend is designed to be responsive, providing an optimal user experience on desktop and mobile devices.
- **Database Integration:** Data is stored and managed in a MySQL database.
- **User Authentication:** Secure login for both users and admins.

## Technology Stack

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java Servlets, JSP
- **Database:** MySQL
- **Server:** Apache Tomcat
- **Tools and Libraries:** JDBC for database connectivity, JSTL for dynamic JSP content, Font Awesome for icons

## Project Structure

```plaintext
RegistrationManagementSystem/
├── src/
│   ├── controller/
│   │   ├── RegisterServlet.java
│   │   ├── LoginServlet.java
│   │   └── ShowUserServlet.java
│   ├── model/
│   │   ├── User.java
│   │   └── UserDao.java
│   └── util/
│       └── DatabaseConnection.java
├── web/
│   ├── index.html
│   ├── register.jsp
│   ├── login.jsp
│   ├── showUsers.jsp
│   └── assets/
│       ├── styles.css
│       └── logo.png
├── README.md
└── database/
    └── registration_management.sql
```

## Setup Instructions

### Prerequisites

1. Java Development Kit (JDK) 8 or above
2. Apache Tomcat 9 or above
3. MySQL Database
4. MySQL Workbench (optional for database setup)

### Database Setup

1. Open MySQL Workbench (or any MySQL client) and create a database named `registration_db`.
2. Execute the SQL script located in the `database/` folder (`registration_management.sql`) to create the necessary tables.

### Application Setup

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/yourusername/RegistrationManagementSystem.git
    ```

2. Open the project in your IDE (e.g., Eclipse or IntelliJ IDEA).
3. Set up your Apache Tomcat server in the IDE.
4. Configure the database connection in `src/util/DatabaseConnection.java` with your MySQL credentials:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/registration_db";
    private static final String USER = "your-username";
    private static final String PASSWORD = "your-password";
    ```

5. Build and deploy the project on Tomcat.

### Running the Application

1. Start the Apache Tomcat server.
2. Open a web browser and go to `http://localhost:8080/RegistrationManagementSystem/`.
3. Use the registration page to create a new user or log in as an admin (if configured).

## Usage

- **User Registration:** Fill in the registration form with required details to create a new account.
- **Admin Dashboard:** After logging in as an admin, view and manage user information through the dashboard.
- **Data Display:** Use the "Show Users" option to retrieve and display registered users in a table format.

## Screenshots and video

### Registration Form
![Screenshot 2024-11-11 233118](https://github.com/user-attachments/assets/6840c3f0-2040-4110-9434-becc3eba99ce)

### User Dashboard
![User Dashboard](https://github.com/user-attachments/assets/7fe3e9c3-3c12-4734-ab9d-664673bc70e0)
### video
https://github.com/user-attachments/assets/27334655-4643-4df6-bfbd-8c9f11b1eb31



## Future Enhancements

- Add email verification for new user registrations.
- Implement password encryption for enhanced security.
- Create additional reports and analytics for user data.

## Contributing

1. Fork this repository.
2. Create a new branch (`feature/YourFeatureName`).
3. Commit your changes.
4. Push to the branch.
5. Open a Pull Request.

## License

This project is licensed under the MIT License.

---

Feel free to adjust the content based on specific project details or custom configurations you might have added!
