# Java Technical Test

## Solving a maze

The idea here is to write a program to solve simple mazes. The mazes are given in 
a file and the program must read in the file, solve the maze and output the solution.

The program should be written to the following specification:

  - The program must be written in Java or C#
  - It can be assumed that the input file will always follow the guidelines provided in the "Maze input/output formats" section
    (no validation is necessary)
  - Maze outputs should be written to the console as per the "Maze input/output formats" guidelines
  - If there is no possible solution to the maze the output should indicate this somehow
  - Arbitrary sized mazes should be handled
  - The program should allow all valid movements as per the "Movement" guidelines
  - Name and location of the input file should be selectable at runtime

The emphasis should be on code readability and simplicity. Please provide the source code for your solution, including clear 
instructions on how to run.

A folder of sample mazes has been provided (included in the "resources" folder of the project). The samples are all solveable, and your program should be able
to solve them within seconds. Be aware that your solution may be tested against other mazes, some of which are not solveable.


## Maze input/output formats

The input is a maze description file in plain text.  
 1 - denotes walls<br/>
 0 - traversable passage way

INPUT:<br/>
\<WIDTH> \<HEIGHT>\<CR><br/>
<START_X> <START_Y>\<CR> (x,y) location of the start. (0,0) is upper left and (width-1,height-1) is lower right<br/>
<END_X> <END_Y>\<CR> (x,y) location of the end<br/>
\<HEIGHT> rows where each row has \<WIDTH> {0,1} integers space delimited

OUTPUT:<br/>
The maze with a path from start to end<br/>
Walls marked by '#', passages marked by ' ', path marked by 'X', start/end marked by 'S'/'E'

Example file:

10 10<br/>
1 1<br/>
8 8<br/>
1 1 1 1 1 1 1 1 1 1<br/>
1 0 0 0 0 0 0 0 0 1<br/>
1 0 1 0 1 1 1 1 1 1<br/>
1 0 1 0 0 0 0 0 0 1<br/>
1 0 1 1 0 1 0 1 1 1<br/>
1 0 1 0 0 1 0 1 0 1<br/>
1 0 1 0 0 0 0 0 0 1<br/>
1 0 1 1 1 0 1 1 1 1<br/>
1 0 1 0 0 0 0 0 0 1\
1 1 1 1 1 1 1 1 1 1<br/>

Example output:

##########<br/>
#SXX&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; #X######<br/>
#&nbsp; #XX&nbsp; &nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; ##X#&nbsp; ###<br/>
#&nbsp; #&nbsp; X#&nbsp; #&nbsp; #<br/>
#&nbsp; #&nbsp; XX&nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; ###X####<br/>
#&nbsp; #&nbsp; &nbsp; XXXE#<br/>
##########<br/>


## Movement

Valid moves are N, S, E, W only, with no diagonal movement allowed. Moves in any of these directions will be blocked by maze walls, which are
identified as 1's in the input file. The edges of the grid should not be considered as walls unless specified, and allow for wrapping movement.

Example of horizontal wrapping:

10 10<br/>
1 1<br/>
8 8<br/>
1 1 1 1 1 1 1 1 1 1<br/> 
0 0 1 0 0 0 0 0 0 0<br/>
0 0 1 0 0 1 1 1 1 1<br/>
1 0 1 0 0 0 0 0 0 1<br/>
1 0 1 1 0 1 0 1 1 1<br/>
1 0 1 0 0 1 0 1 0 1<br/>
1 0 1 0 0 0 0 0 0 1<br/>
1 0 1 0 0 0 1 1 1 1<br/>
1 0 1 0 0 0 0 0 0 1<br/>
1 1 1 1 1 1 1 1 1 1<br/>

##########<br/>
XS#&nbsp; XXXXXX<br/>
&nbsp; &nbsp; #&nbsp; X#####<br/>
#&nbsp; #&nbsp; X&nbsp; &nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; ##X#&nbsp; ###<br/>
#&nbsp; #&nbsp; X#&nbsp; #&nbsp; #<br/>
#&nbsp; #&nbsp; X&nbsp; &nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; #&nbsp; X&nbsp; ####<br/>
#&nbsp; #&nbsp; XXXXE#<br/>
##########<br/>

Example of vertical wrapping:

10 10<br/>
1 1<br/>
8 8<br/>
1 0 1 1 1 1 1 1 1 1<br/>
1 0 1 0 0 0 0 0 0 0<br/>
1 0 1 0 1 1 1 1 1 1<br/>
1 1 1 0 0 0 0 0 0 1<br/>
1 0 1 1 0 1 0 1 1 1<br/>
1 0 1 0 0 1 0 1 0 1<br/>
1 0 1 0 0 0 0 0 0 1<br/>
1 0 1 1 1 0 1 1 1 1<br/>
1 0 0 0 0 0 0 0 0 1<br/>
1 0 1 1 1 1 1 1 1 1<br/>

#X########<br/>
#S#&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <br/>
#&nbsp; #&nbsp; ######<br/>
###&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; ##&nbsp; #&nbsp; ###<br/>
#&nbsp; #&nbsp; &nbsp; #&nbsp; #&nbsp; #<br/>
#&nbsp; #&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #<br/>
#&nbsp; ###&nbsp; ####<br/>
#XXXXXXXE#<br/>
#X########<br/>


## Instructions on how to run

MazeSolver is the executable class.

Breading First Search Algorithm has been used to solve the exercise.
Also, I have added an extra file selection mode.

Its use is very simple, once you run the application, it will ask you how you want to load the .txt file.
Press 1 to display the available files. Then, you can select the file you want, indicating the position in which they are displayed (e.g. 3).
Press 2 to specify full directory including the file name (e.g. C:\...\input.txt).


## Stack

* Java 8
* Junit 5
