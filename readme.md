
Working with ToyRobot in Intellij 2019.1
==============

prerequisites
--------------
The following items should be installed in your system:
- Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
- IntelliJ 2019.1
- Java 11

Running The Toy Robot Application
--------------
If no parameters are given when running the main class, the application will
run via standard input. 

To run a set of commands within a file, you just need to specify a filepath
as the first parameter when running the main class.
There are some sample command files that can be run as listed below:
- "ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsBoundariesFile.txt"
- "ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsFile.txt"
- "ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsFileOutOfBounds.txt"

**Inside Intellij**
- Open project into Intellij
    *Running All Tests*
    - Click on ToyRobot project within Intellij -> "Run All Tests"

    *Running Toy Robot and using standard input*
    - Right click on src/main/java/com/toyrobot/Application.java
    - Click on "Run 'Application.main()'

    *Running main App using custom filePaths*
    -Select ToyRobot project folder
    -Go to Run -> Edit Configurations
    -On the left pane Create New Application Configuration
    -Select Application.java for the main class for the new Application Configuration
    -Under "Program Arguments" type in [InputCSV_FilePath] 
    -Hit "Apply"
    -Right click on src/main/java/com/toyrobot/Application.java
    -Click Run on your newly created Run Application Configuration



**Terminal Command Line**

   *Running main App using default input commands File*
   
       mvn exec:java -Dexec.mainClass="com.toyrobot.Application"

   *Running main App using custom input commands File*
   
       mvn exec:java -Dexec.mainClass="com.toyrobot.Application -Dexec.args=[FILEPATH]
        e.g. exec:java -Dexec.mainClass="com.toyrobot.Application" -Dexec.args="ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsBoundariesFile.txt"
    
    
Assumptions
=============
   -Valid commands with correct formatting is specified within the problems.md are as follows:
        PLACE X,Y,F
        MOVE
        LEFT
        RIGHT
        REPORT
    -Commands are not case sensitive
    -Commands are read line by line
    -Any commands before a place command will be ignored.
    -Non valid incorrectly formatted commands will print an error message to log.error
    -As mentioned in the problem briefing, the  tabletop of dimensions 5 units x 5 units,
    however i've allowed the ability to create a tabletop with any dimension.
    
   

    
    
