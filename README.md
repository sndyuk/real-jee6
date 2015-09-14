Quick start
===

1. Run hsqldb server.

	```
	$ ./run-hsqldb-server.sh
	```

1. Open the other console.

1. Install all modules

	```
	$ mvn clean install
	```

1. Run server one of following:

	WER version
	--- 
	
	```
	$ cd modules/real-jee-web
	$ ./run-server-local.sh
	```

	[http://localhost:8080/realjee/](http://localhost:8080/realjee/)


	EAR version
	---

	1. Install modules and Run glassfish server.

		```
		$ ./run-server-local.sh
		```
	
	1. Wait until the console shows:

		```
		Hit ENTER to redeploy, X to exit
		```	

	[http://localhost:8080/realjee/](http://localhost:8080/realjee/)



Clean install all modules to Maven local repository
---

	$ mvn clean install

