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
```

Run all the tests and package a Jar file

Generates a PlantUML graph in target/generated-docs/testdiagram1.txt
```
mvn clean plantuml-generator:generate    
```
Generate a PlantUML image png
```
export PLANTUML_LIMIT_SIZE=9000 && java -jar plantuml-1.2024.8.jar -verbose target/generated-docs/testdiagram1.txt
```


### Teacher Section
[uml-diagram](Work/uml.png)



