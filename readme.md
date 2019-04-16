
Working with ToyRobot in Intellij 2017.3.1
==============

prerequisites
--------------
The following items should be installed in your system:
- Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
- IntelliJ
- Java 8

Running The Toy Robot Application
--------------

**Inside Intellij**
- Open project into Intellij
    *Running All Tests*
    - Click on ToyRobot project within Intellij -> "Run All Tests"

    *Running Toy Robot and using standard input*
    - Right click on src/main/java/com/toyrobot/App.java
    - Click on "Run 'App.main()'

    *Running main App using custom filePaths*
    -Select ToyRobot project folder
    -Go to Run -> Edit Configurations
    -On the left pane Create New Application Configuration
    -Select Application.java for the main class for the new Application Configuration
    -Under "Program Arguments" type in [InputCSV_FilePath] 
    -Hit "Apply"
    -Right click on src/main/java/com/toyrobot/App.java
    -Click Run on your newly created Run Application Configuration



**Terminal Command Line **
   *Running main App using default input commands File*
       mvn exec:java -Dexec.mainClass="com.toyrobot.Application"

   *Running main App using custom input commands File*
       mvn exec:java -Dexec.mainClass="com.toyrobot.App -Dexec.args=[FILEPATH]
        e.g. exec:java -Dexec.mainClass="com.toyrobot.App" -Dexec.args="ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsBoundariesFile.txt"
    
    **Example command files that can be used for demo or testing **
    - "ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsBoundariesFile.txt"
    - "ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsFile.txt"
    - "ToyRobotV2/src/main/resources/exampleCommandFiles/TestInstructionsFileOutOfBounds.txt"
    
Assumptions
=============
   -Valid commands with correct formatting is specified within the problems.md are as follows:
        PLACE X,Y,F
        MOVE
        LEFT
        RIGHT
        REPORT
    -Commands are read line by line
    -Any commands before a place command will be ignored.
    -Non valid incorrectly formatted commands will print an error message to log.error
    -As per the requirement, only one Robot was needed to be on board the at any given time,
     however the current BoardControllerIMPL class can be redesigned or a new BoardController
     could be created to hold many placeableItems.

Project Discussions regarding design and future development
==============
   **Future Development**
    -Persistance layer can be developed.
    -A new Board or the existing board class can be created/modified to hold multiple PlaceableItems.
    -More commands can be implemented
    -Give the user ability to specify size of board and number of placeablitems
    -Have the ability to specify un-occupiable spaces on map
    -Support for entering commmands via command line input aside from also reading commands from a file. 
    
   **Design**
   -Foreign ID of enums are stored in the Robot object and not the actual enums themselves. 
    This would allow the foreign key to be saved as persistance data
   -Methods of how to access enums is stored in the enums themselves as it seemed like a logical place
    to store all related logics.
    
