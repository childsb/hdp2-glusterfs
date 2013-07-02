hdp2-glusterfs
==============
hdp 2 glusterfs enablement. hdp = hortonworks hadoop platform.

maven environment
=================

Dependencies are in maven repo formated hosted on git.  Replace "/Users/bc/dev/git/raven" with the absolute path to your clone of the git raven repo hosting this plugin. Also keep the group/artifact/version holy.

mvn install:install-file -Dfile= -DgroupId=hdp  -DartifactId=common -Dversion=2.0.3.22-alpha -Dpackaging=jar -DlocalRepositoryPath=/Users/bc/dev/git/raven -DcreateChecksum=true 
