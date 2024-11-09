-------- UTESHOP - Online Fashion Store --------
~~~~ UTESHOP is an e-commerce platform designed to provide a seamless online shopping experience for fashion enthusiasts. Built using Servlet, JSP/JSTL, Bootstrap, JDBC, and SQLServer/MySQL/PostgreSQL, this project aims to deliver a responsive, modern, and secure online store for buying fashion products. The platform also utilizes Decorator Sitemesh for consistent page layout and JWT (JSON Web Token) for secure user authentication.
  
****** Technologies Used ******
~ Servlet: Java-based web application framework for handling HTTP requests and responses.
~ JSP (JavaServer Pages) / JSTL (JavaServer Pages Standard Tag Library): For dynamic web page rendering and efficient presentation logic.
~ Bootstrap: A responsive front-end framework for designing mobile-first websites and web applications.
~ JDBC: Java Database Connectivity for interacting with the database (supports SQLServer, MySQL, or PostgreSQL).
~ SQLServer: Database systems used to store and manage the website’s data.
~ Sitemesh: A page layout and decoration framework, applied with the Decorator Design Pattern, for a consistent, templated look across all pages.
~ JWT (JSON Web Token): For secure user authentication, ensuring safe access to the system.

****** Features ******
~ User Management: Customers can register, log in, and manage their profiles. Admin users can manage customer accounts, orders, and products.
~ Product Catalog: Browse and filter products based on various categories (clothes, accessories, etc.), with detailed product pages.
~ Shopping Cart: Add products to the shopping cart, update quantities, and proceed to checkout.
~ Order Management: Users can place orders and view their order history. Admin can manage and track customer orders.
~ Responsive Design: The website is fully responsive, ensuring a smooth experience across all devices (desktop, tablet, mobile).
~ Secure Authentication: The website uses JWT for secure login and session management.
~ Payment Integration: Seamless integration for processing payments (this section can be further elaborated if payment methods like PayPal, Stripe, etc., are integrated).
~ Admin Dashboard: Admin users have a separate dashboard for managing the product inventory, viewing orders, and managing the website content.

****** Setup Instructions ******
  1. Clone the repository
Clone this repository to your local machine:
  git clone https://github.com/aMrnoob/ltWeb.git
  2. Set up the database
Choose and set up SQLServer for the database.
Configure the database connection settings in the web.xml or the database configuration files within the project (DBConnection.java or similar).
Import the database schema provided in the database folder of this project.
  3. Set up the web server
The project is built using Servlets. You can use any Java web server like Apache Tomcat to run the application.
Download and install Apache Tomcat.
Deploy the UTESHOP.war file in the webapps folder of your Tomcat server or deploy the project as a Maven web application.
Start the Tomcat server and navigate to http://localhost:8080/UTESHOP
  4. Configure JWT
Ensure the JWT settings are correctly configured to enable user authentication and secure session management:
You may need to adjust JWT secret keys or token expiration times in the project’s configuration.
  5. Access the website
Once the server is running, you can access the website at http://localhost:8080/UTESHOP
