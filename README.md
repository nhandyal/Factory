Team 14
======
##Compiing
All source files for the project are located under the src folder. When compiling you must specify the sourcepath when compiing. Recommended compile settings are:
```r
javac -d bin -sourcepath src [path to source file]
```

This will create a new folder called bin and recreate the src file hierarchy and place the class files in the appropriate areas.

###Example
To compile the server files the command would be
```r
javac -d bin -sourcepath src src/server/*.java
```

##Running
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