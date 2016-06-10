# BuyBuyBuy - A Online Shipping Website
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

![alt text][logo]

[logo]:https://github.com/Juice-XIJ/Website/blob/master/databaseflow.png "q"





Dillinger is a cloud-enabled, mobile-ready, offline-storage, AngularJS powered HTML5 Markdown editor.

  - Type some Markdown on the left
  - See HTML in the right
  - Magic

You can also:
  - Import and save files from GitHub, Dropbox, Google Drive and One Drive
  - Drag and drop files into Dillinger
  - Export documents as Markdown, HTML and PDF

Markdown is a lightweight markup language based on the formatting conventions that people naturally use in email.  As [John Gruber] writes on the [Markdown site][df1]

> The overriding design goal for Markdown's
> formatting syntax is to make it as readable
> as possible. The idea is that a
> Markdown-formatted document should be
> publishable as-is, as plain text, without
> looking like it's been marked up with tags
> or formatting instructions.

This text you see here is *actually* written in Markdown! To get a feel for Markdown's syntax, type some text into the left window and watch the results in the right.

### Version
3.2.7

### Tech

Dillinger uses a number of open source projects to work properly:

* [AngularJS] - HTML enhanced for web apps!
* [Ace Editor] - awesome web-based text editor
* [markdown-it] - Markdown parser done right. Fast and easy to extend.
* [Twitter Bootstrap] - great UI boilerplate for modern web apps
* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework [@tjholowaychuk]
* [Gulp] - the streaming build system
* [keymaster.js] - awesome keyboard handler lib by [@thomasfuchs]
* [jQuery] - duh

And of course Dillinger itself is open source with a [public repository][dill]
 on GitHub.

### Installation

Dillinger requires [Node.js](https://nodejs.org/) v4+ to run.

You need Gulp installed globally:

```sh
$ npm i -g gulp
```

```sh
$ git clone [git-repo-url] dillinger
$ cd dillinger
$ npm i -d
$ NODE_ENV=production node app
```

### Plugins

Dillinger is currently extended with the following plugins

* Dropbox
* Github
* Google Drive
* OneDrive

Readmes, how to use them in your own application can be found here:

* [plugins/dropbox/README.md] [PlDb]
* [plugins/github/README.md] [PlGh]
* [plugins/googledrive/README.md] [PlGd]
* [plugins/onedrive/README.md] [PlOd]

### Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantanously see your updates!

Open your favorite Terminal and run these commands.

First Tab:
```sh
$ node app
```

Second Tab:
```sh
$ gulp watch
```

(optional) Third:
```sh
$ karma start
```

### Docker
Dillinger is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 80, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.

```sh
cd dillinger
docker build -t <youruser>/dillinger:latest .
```
This will create the dillinger image and pull in the necessary dependencies. Once done, run the Docker and map the port to whatever you wish on your host. In this example, we simply map port 80 of the host to port 80 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 80:80 --restart="always" <youruser>/dillinger:latest
```

Verify the deployment by navigating to your server address in your preferred browser.

### N|Solid and NGINX

More details coming soon.

#### docker-compose.yml

Change the path for the nginx conf mounting path to your full path, not mine!

### Todos

 - Write Tests
 - Rethink Github Save
 - Add Code Comments
 - Add Night Mode

License
----

MIT


**Free Software, Hell Yeah!**

[1]: <http://getbootstrap.com/getting-started> (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [1]: <http://getbootstrap.com/getting-started>
   [2]: <https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller>
   [3]: <http://www.codeproject.com/Articles/25057/Simple-Example-of-MVC-Model-View-Controller-Design>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [@thomasfuchs]: <http://twitter.com/thomasfuchs>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [keymaster.js]: <https://github.com/madrobby/keymaster>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [12]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]:  <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
