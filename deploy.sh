#!/bin/sh
rm -rf target/
mvn package -DskipTests=true;
ssh root@192.168.55.12 "rm -rf /tmp/gl*"
ssh root@192.168.55.12 "rm /opt/hadoop-2.0.3.22-alpha-hdp/share/hadoop/common/lib/hdp*.jar"
scp target/hdp2-glusterfs-0.0.1.jar root@192.168.55.12:/opt/hadoop-2.0.3.22-alpha-hdp/share/hadoop/common/lib/
# scp target/hdp2-glusterfs-0.0.1.jar root@ambari-2:/opt/hadoop-2.0.3.22-alpha-hdp/share/hadoop/common/lib/
