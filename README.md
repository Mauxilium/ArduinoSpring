# ArduinoSpring

Spring @EnableArduino annotation library, developed on top of ArduinoJavaSerialRpc library.

## Architecture

Tbdf

## Features

* Multiple Cards access
* Addressing by port name or card name
* Automatic card discovering

# Getting Started

## Arduino Side Installation
It is required to:
 * download the Arduino library from: https://github.com/Mauxilium/ArduinoSerialRpc
 * expand it into your "library" path of Arduino Ide,
  or follows the manual installation section of https://www.arduino.cc/en/guide/libraries
  
## Java Side Installation
For Maven projects, it is only required to include the following dependency in you pom
```xml
<properties>
    <arduinospring.version>1.0.0</arduinospring.version>
</properties>
    
<dependencies>
    <dependency>
        <groupId>it.mauxilium</groupId>
        <artifactId>ArduinoSpring</artifactId>
        <version>${arduinospring.version}</version>
    </dependency>
</dependencies>
```
NOTE: the maven central repository is not already available. Please clone this GitHub repository, compile it and publish into your local repository.

## Arduino Sketch basic example
## Java Class basic example
### Build and run 


### Build and run 
Java and Arduino communication is performed by the way of RxTx external library:
```
(from his readme)
RXTX binary builds provided by Mfizz Inc. (http://mfizz.com/).
Please see http://mfizz.com/oss/rxtx-for-java for more info.
```
A copy of 64Bit RxTx library is added to this git repository. 

In order to execute the integration example test, please follows this steps:
* Open the sketch ArduinoJavaSerialRpc\src\test\java\integration\sketch\sketch.ino
* Download it into your Arduino Card
* Execute the following commands
```bash
mvn clean test
java -Djava.library.path=RxTx\mfz-rxtx-2.2-20081207-win-x64 -cp target/test-classes;target/classes;RxTx/mfz-rxtx-2.2-20081207-win-x64/RXTXcomm.jar integration.java.IntegrationTest COM5 9600
```

### Next steps
* ArduinoJavaSerialRpc tutorial - A tutorial to discover a more complex use of library (On Working)
* ArduinoSpring - The Spring library developed on top of ArduinoJavaSeriaRpc (On Working)
* www.mauxilium.it - The reference site for my other projects (On Working)

