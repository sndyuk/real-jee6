#!/bin/bash

pushd $(dirname $0) > /dev/null

MAVEN_OPTS='-Xmx1G -XX:MaxPermSize=256m'
mvn clean install -Papplication
mvn embedded-glassfish:run

popd > /dev/null
