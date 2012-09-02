Quick start
===

1. Run hsqldb server.

	```
	$ ./run-hsqldb-server.sh
	```

1. Open the other console.

1. Run server one of following:


	JSP + Servlet
	---
	
	```
	$ cd modules/real-jee-web
	$ ./run-server-local.sh
	```

	[http://localhost:8080/realjee-web/](http://localhost:8080/realjee-web/)


	JAX-RS
	---

	```
	$ cd modules/real-jee-rs
	$ ./run-server-local.sh
	```
	
	[http://localhost:8080/realjee-rs/](http://localhost:8080/realjee-rs/)
	



	EAR
	---

	1. Install modules and Run glassfish server.

		```
		$ ./run-server-local.sh
		```
	
	1. Wait until the console shows: This is not work!  Please fix it.

		```
		Hit ENTER to redeploy, X to exit
		```	

	[http://localhost:8080/realjee-web/](http://localhost:8080/realjee-web/)



Clean install all modules to Maven local repository
---

	$ mvn clean install


Run web servlet server
---

1. 

1. Open web browser.

	[http://localhost:8080/realjee-web/](http://localhost:8080/realjee-web/)
