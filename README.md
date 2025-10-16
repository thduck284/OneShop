# 🛍️ OneShop - E-Commerce Platform

A full-featured e-commerce web application built with Java Servlet technology, offering a seamless shopping experience with robust admin management capabilities.

---

## 🚀 Technologies Stack

### Backend
- **Servlet** - Java-based web application framework for handling HTTP requests and responses
- **JSP/JSTL** - JavaServer Pages with Standard Tag Library for dynamic web page rendering
- **JDBC** - Java Database Connectivity for database interactions
- **JWT** - JSON Web Token for secure authentication and session management

### Frontend
- **Bootstrap** - Responsive, mobile-first design framework
- **Sitemesh** - Page layout and decoration framework using Decorator Design Pattern

### Database
- **SQL Server** - Primary database system (also supports MySQL and PostgreSQL)

---

## ✨ Key Features

### 👥 User Management
- Customer registration and login
- Profile management
- Admin dashboard for user account management

### 🛒 Shopping Experience
- **Product Catalog** - Browse and filter products by categories (clothes, accessories, etc.)
- **Detailed Product Pages** - Comprehensive product information
- **Shopping Cart** - Add, update, and manage cart items
- **Checkout Process** - Streamlined ordering experience

### 📦 Order Management
- Order placement and tracking
- Order history for customers
- Admin order management and tracking system

### 🔐 Security
- JWT-based authentication
- Secure session management
- Protected admin routes

### 💳 Payment Integration
- Seamless payment processing
- Multiple payment method support (expandable)

### 📱 Responsive Design
- Fully responsive across all devices
- Optimized for desktop, tablet, and mobile

### 🎛️ Admin Dashboard
- Product inventory management
- Order tracking and management
- Content management system

---

## 🛠️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/aMrnoob/CuoiKyLapTrinhWeb
cd CuoiKyLapTrinhWeb
```

### 2️⃣ Database Configuration

1. **Install SQL Server** (or MySQL/PostgreSQL if preferred)

2. **Import Database Schema**
   - Locate the database schema file in the `/database` folder
   - Execute the SQL scripts to create tables and initial data

3. **Configure Database Connection**
   - Update database settings in `web.xml` or `DBConnection.java`
   - Set your database credentials:
     ```java
     DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=OneShop"
     DB_USER = "your_username"
     DB_PASSWORD = "your_password"
     ```

### 3️⃣ Web Server Setup

1. **Download and Install Apache Tomcat**
   - Visit [Apache Tomcat](https://tomcat.apache.org/)
   - Download version 9.0 or higher

2. **Deploy the Application**
   - **Option A**: Copy `OneShop.war` to Tomcat's `webapps` folder
   - **Option B**: Deploy as a Maven project directly to Tomcat

3. **Start Tomcat Server**
   ```bash
   # Navigate to Tomcat bin directory
   cd apache-tomcat-x.x.x/bin
   
   # Start server (Windows)
   startup.bat
   
   # Start server (Linux/Mac)
   ./startup.sh
   ```

### 4️⃣ JWT Configuration

Configure JWT settings for authentication:
- Adjust JWT secret key in configuration files
- Set token expiration time as needed
- Default configuration can be found in authentication filter classes

### 5️⃣ Access the Application

Once the server is running, access the application at:

```
http://localhost:8080/OneShop
```

**Default Admin Credentials** (if applicable):
```
Username: admin
Password: admin123
```
*Note: Change default credentials immediately after first login*

---

## 📁 Project Structure

```
OneShop/
├── src/
│   ├── main/
│   │   ├── java/          # Java source files
│   │   ├── webapp/        # Web resources (JSP, CSS, JS)
│   │   └── resources/     # Configuration files
├── database/              # Database schemas and scripts
├── docs/                  # Documentation
└── pom.xml               # Maven configuration
```

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 👤 Author

**aMrnoob**
- GitHub: [@aMrnoob](https://github.com/aMrnoob)

---

## 📞 Support

For issues and questions, please open an issue on the GitHub repository.

---

**Happy Shopping! 🛒✨**
