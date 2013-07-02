#!/bin/sh
PUSHD=`pwd`
cd $PUSHD
mvn clean package deploy -DskipTests=true
# mvn -DaltDeploymentRepository=snapshot-repo::default::file:../../raven/snapshots clean deploy -DskipTests=true
