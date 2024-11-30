User Access Management System
Introduction
The User Access Management System is a web-based application designed to streamline the process of managing user access to various software applications within an organization. It includes features such as user registration, authentication, software management, access request submission, and approval workflows.

Features
User Registration

New users can sign up with the default role of "Employee."
User Authentication

Users log in and are redirected based on their role:
Employee: Access the Request page.
Manager: Access the Pending Requests page.
Admin: Access the Software Management page.
Software Management

Admins can add new software applications to the system.
Access Request Submission

Employees can request access to specific software with a specified access type (e.g., Read, Write, Admin).
Access Request Approval

Managers can approve or reject access requests.
Technologies Used
Backend: Java Servlets
Frontend: JSP, HTML, CSS
Database: PostgreSQL
Server: Apache Tomcat
Installation

1. Prerequisites
JDK: Version 8 or later
Apache Tomcat: Version 9 or later
PostgreSQL: Version 12 or later

2. Clone the Repository
bash
Copy code
git clone https://github.com/Ramakrihnatt/User_management_system.git  
cd user-access-management  

3. Setup the Database
Open your PostgreSQL client.
Execute the schema.sql file located in the /db/ directory to create the required tables.
The default users are preloaded in the database, and you can find this data in the data.sql file located in the /db/ directory.

Default Users:
Admin

Username: Ram
Password: 567
Role: Admin

Manager

Username: Krishna
Password: 890
Role: Manager

4. Configure the Project
Update the database credentials in DBUtils.java:
java
Copy code
private static final String URL = "jdbc:postgresql://localhost:5432/your_database_name";  
private static final String USER = "your_username";  
private static final String PASSWORD = "your_password";  
Project Structure
css
Copy code
User_Access_Management_System  
├── src  
│   ├── main  
│   │   ├── java  
│   │   │   └── com  
│   │   │       └── leucine  
│   │   │           ├── servlets  
│   │   │           │   ├── ApprovalServlet.java  
│   │   │           │   ├── LoginServlet.java  
│   │   │           │   ├── PendingRequestServlet.java  
│   │   │           │   ├── RequestServlet.java  
│   │   │           │   ├── SignUpServlet.java  
│   │   │           │   ├── SoftwareListServlet.java  
│   │   │           │   └── SoftwareServlet.java  
│   │   │           └── util  
│   │   │               ├── DBUtils.java  
│   │   │               ├── Request.java  
│   │   │               └── Software.java  
├── webapp  
│   ├── css  
│   │   └── style.css  
│   ├── META-INF  
│   ├── WEB-INF  
│   │   ├── lib  
│   │   └── web.xml  
│   ├── createSoftware.jsp  
│   ├── login.jsp  
│   ├── pendingRequests.jsp  
│   ├── requestAccess.jsp  
│   └── signup.jsp  


Usage
Run the Application
Deploy the project on your Apache Tomcat server.
Access the Application
Visit: http://localhost:8080/User_Access_Management_System/
Functionalities
Employees: Log in and submit access requests.
Managers: View and approve/reject pending requests.
Admins: Manage the software list.
Contributing
Fork the repository and submit pull requests.
For major changes, open an issue first to discuss your ideas.
License
This project is licensed under the MIT License.













