Team 14
======
##Compile and Run Commands
The following commands will compile and run all nescessary files. To compile / run indavidual files please read the appropriate sections below.
###Compile Instructions
```r
javac -d bin -sourcepath src src/factory/server/Server.java
javac -d bin -sourcepath src src/factory/client/partManager/PartsManager.java
javac -d bin -sourcepath src src/factory/client/kitManager/KitManager.java
javac -b bin -sourcepath src src/factory/client/factoryManager/FactoryManager.java
javac -d bin -sourcepath src src/factory/client/kitAssemblyManager/KitAssemblyManager.java
javac -d bin -sourcepath src src/factory/client/laneManager/LaneManager.java
javac -d bin -sourcepath src src/factory/client/gantryManager/GantryManager.java
```
###Run Instructions
```r
java -classpath bin factory.server.Server
java -classpath bin factory.client.partManager.PartsManager
java -classpath bin factory.client.kitManager.KitManager
java -classpath bin factory.client.factoryManager.FactoryManager
java -classpath bin factory.client.kitAssemblyManager.KitAssemblyManager
java -classpath bin factory.client.laneManager.LaneManager
java -classpath bin factory.client.gantryManager.GantryManager
```

##Compiling Indavidual Files
All source files for the project are located under the src folder. When compiling you must specify the sourcepath when compiing. Recommended compile settings are:
```r
javac -d bin -sourcepath src [path to source file]
```

This will create a new folder called bin and recreate the src file hierarchy and place the class files in the appropriate areas. If this doesn't work, create a directory called bin in the team14 root directory and then rerun the above command.

###Example
To compile the server files the command would be
```r
javac -d bin -sourcepath src src/factory/server/*.java
```

##Running Individual Files
Since we are using packages with class files located in multiple locations, you must specify the classpath when running. Recommended run settings are:
```r
java -classpath bin [package].Filename
```

*Note that in the above example Filename is the file that contains main. It is not terminated in .java or .class and must match the filename exactly (including case).

###Example
To run the server file the command would be
```r
java -classpath bin server.Server
```
*lowercase server is the package that Server is part of