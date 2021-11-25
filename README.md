# How to run the program

Run below command to run the application.
1. Unix
> ./mvnw compile exec:java

2. Windows
> mvnw.cmd compile exec:java

Run below command to run all tests.
1. Unix
> ./mvnw test

2. Windows
> mvnw.cmd test


# Assumptions
1. Line, rectangle or bucket fill command will only work if a canvas is already created. If canvas is not created then these commands will fail.
2. If a canvas is already created, another canvas can not be created again. To create another canvas, quit the current session and restart the session.
3. Line and rectangle can be redrawn over other existing structures.
4. Once the canvas is filled with a character using bucketfill, same space can't be refilled with another character.
5. Only vertical or horizontal lines can be drawn. Drawing of slopped line is not supported.
6. While drawing rectangle first coordinates(represented by 2nd and 3rd argument) should be left corner and 2nd coordinates (represented by 4th and 5th argument) should be lower right corner.