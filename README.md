# A Stranger Among Us

A Stranger Among Us is a text adventure game loosely based on the Among Us video game. The game consists of navigating a spaceship that has been disabled by a stranger, finding items to solve puzzles, and repairing your ship to find your crew.

### Quick Start

Start the game by typing the text below in your console.

```
mvn clean compile exec:java  
```
When the game runs a Map.dot file is created in the current directory.
If you have the graphviz dot compiler installed it can compile this into a png like so

```
 dot -Tpng Map.dot -o game_map.png   
```

Javadoc Site Generator
```
mvn javadoc:javadoc
```
index.html is in `target\reports\apidocs\index.html`


Run all the tests and package a Jar file

```
mvn package
```
The following jar can then be run using the following command 

```
java -jar target/text_adventure-1.0-SNAPSHOT.jar
```

Generates a PlantUML graph in target/generated-docs/testdiagram1.txt

```
mvn clean plantuml-generator:generate    
```

Generate a PlantUML (UML) image png , [plantuml.jar](https://plantuml.com/download) required for this operation

```
export PLANTUML_LIMIT_SIZE=9000 && java -jar plantuml-1.2024.8.jar -verbose target/generated-docs/testdiagram1.txt
```


### Teacher Section
All work related files are inside the [/work](Work) directory.  
ALl education related documentation is in the [/Education](Education)

The uml diagram is at `Work/uml.png`
[uml-diagram](Work/uml.png)

**To Test for Timers run the game using the above command `mvn clean compile exec:java` and wait 10 minutes** 
This will show the generator timer, the ambiance timer, and the game over timer. 

All other grade tests can be run by selecting just the grade tests using `-Dtest=bnr_GradeC` and `-Dtest=bnr_GradeB`
```
mvn test -Dtest="bnr_GradeC"

mvn test -Dtest="bnr_GradeB"
```

