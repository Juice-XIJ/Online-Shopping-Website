# BuyBuyBuy - An Online Shipping Website
----
With the development of things like the internet, shopping online became much more popular in the world. This highlights the need for developing a platform, in order to satisfy the demands for customers and sellers. In this project, we would show you a shopping website which you can buy something you want or sell your items. This website, based on MVC framework, is a combination of MySQL, Tomcat, J2EE (JSP, Servlet and JDBC). We also use [bootstrap][1] to make the website more elegant.

### Softwares
------
   - Web Server: apache-tomcat-9.0.0.M4
   - IDE: IntelliJ IDEA 14.1.5
   - SQL: MySQL
   - API: JDBC

### Installation and Settings
------
1. Install the Tomcat and MySQL Workbench and Server and make sure you set the environment variables correctly.
2. Open MySQL Workbench, create a "SQL connection" and set up your **username** and **password**. 
3. Load the SQL script **project.sql** from *"\SQL"* and execute it.
4. In Intellij IDEA, import the project.
5. Click  **File -> Project Structure ->Modules**. 
6. In the **Path** tag, set up your **Output path** and **Test output path**.
7. In the **Dependencies** tag, import the **mysql-connector-java-5.1.38-bin** in *"\web\WEB-INF\lib"* and **Tomcat** in the directory that you installed. 
8. Open *"src/com/product/jdbc/dbutil/JdbcUtils.Jave"*, edit the variable **USERNAME** and **PASSWORD** based on your "SQL connection".

    > Notes: if you encounter any problems in Intellij, you can follow the tips the IDE shown. Most problems can be fixed using the tips.
9. **Run and Enjoy !**

### To do list
------
For the privacy, the personal information, like Email, phone number and address, can only be offer to the seller after the customers submit an order. It is not allowed to offer any personal information to customers or sellers when they just want to ask some questions to others. Therefore, we should achieve following in the future:
* Design a **chat system** for users. 
* If one customer wants to send an Email to seller, the server could **send the email using the server Email** instead of users'.
* **Encrypt** the data when we transmit.

For more convenient, the following goals are necessary:
* Create a **statistical table** for sellers. The seller could know the sales volume of all goods which they published.
* Develope related **combos**. When a customer wants to buy an item, the website would propose some related items. For example, if customers want to purchase a table, the website would raise some chairs for them

### Design
------
##### The MVC framework
Model–view–controller [(MVC)][2] is a software architectural pattern for implementing user interfaces on computers. It divides a given software application into three interconnected parts, so as to separate internal representations of information from the ways that information is presented to or accepted from the user.

The central component of MVC, the model, captures the behavior of the application in terms of its problem domain, independent of the user interface. [Reference.][3]

* The model directly manages the data, logic and rules of the application. 
* A view can be any output representation of information, such as a chart or a diagram. Multiple views of the same information are possible, such as a bar chart for management and a tabular view for accountants.
* The third part, the controller, accepts input and converts it to commands for the model or view. 

The following figure is the workflow of the MVC Framwork.

![alt text][workflow]

[workflow]:https://github.com/Juice-XIJ/Website/blob/master/databaseflow.png "WorkFlow"

##### UML
The following figure is the UML of project database.

![alt text][UML]

[UML]:https://github.com/Juice-XIJ/Website/blob/master/UML.png "UML"



   [1]: <http://getbootstrap.com/getting-started>
   [2]: <https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller>
   [3]: <http://www.codeproject.com/Articles/25057/Simple-Example-of-MVC-Model-View-Controller-Design>
   
