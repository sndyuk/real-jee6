#!/bin/bash

pushd $(dirname $0) > /dev/null

MAVEN_OPTS='-Xmx1G -XX:MaxPermSize=256m'
mvn clean install
mvn -f modules/real-jee-ear/pom.xml embedded-glassfish:run

popd > /dev/null
