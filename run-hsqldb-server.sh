#!/bin/bash

pushd $(dirname $0) > /dev/null


if [ ! -a "ext/database/realjee6-sql.script" ]; then
	cp ext/realjee6-sql.script.origin ext/database/realjee6-sql.script
fi

mvn -f modules/real-jee-ear/pom.xml exec:java -Dhsqldb-server.skip=false
