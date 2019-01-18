JDK_INCLUDE_PLAT="/usr/lib/jvm/java-8-openjdk-amd64/include/linux"
JDK_INCLUDE="/usr/lib/jvm/java-8-openjdk-amd64/include"

javac lib.java
javah lib
gcc -shared -I$JDK_INCLUDE_PLAT -I$JDK_INCLUDE -fPIC lib.c -o ../libABC.so