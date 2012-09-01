#!/bin/bash

pushd $(dirname $0) > /dev/null

mvn clean install
mvn -f real-jee-web/pom.xml embedded-glassfish:run

popd > /dev/null
