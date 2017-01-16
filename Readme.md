ASCIIdrawing tool
=================

This project implements a solution to a simple ascii drawing tool.
The canvas is modeled as a char array of arrays. 

Points of action are expected to be between 1..canvas size. In the case a point is given outside the prevously specified range the action will not be executed.

Tech Stack
----------
Maven
Java 8
jUnit  (tests)

Run
---
```
$ mvn exec:java -Dexec.mainClass="com.asciiDrawing.Main"
```

Test
----
```
$ mvn test
```