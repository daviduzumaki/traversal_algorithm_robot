Ore Ibikunle

David Ajayi

User Manual

**Table of Contents**
1. **General Information**...................................1
    1. **System Overview**...................................1
    2. **Format Of User manual**.............................1

2. **System Summary**........................................2
    1. **System Configuration**..............................2
    2. **System Access Levels**..............................2

3. **Getting Started**.......................................3
    1. **Download and Installation**.........................3
    2. **Program Layout**....................................3

4. **Using the buttons**.....................................4
    1. **Functionality of Buttons**..........................4

5. **Reporting**.............................................6
    1. **Detailed Exaplanation of the Output**...............6

# **General Information**

## **1.0 General Information**
This section just explains the system in general terms and the purpose that it is intended for.

## **1.1 System Overview**
The main idea of this project revolves around various path finding algorithms, such as BFS, DFS, Greedy, A*, and Dijkstra’s algorithm. The purpose is to visualize how these algorithms work internally to provide a better understanding for anyone who has difficulty grasping the various algorithms.
The software implements maze generation. The program will allow for a user to generate a grid of a range of sizes, draw their own grid in real-time, determine animation speed and generate random mazes for use of ease.

## **1.2 Format Of User Manual**
The user manual for this program will be made up of 5 sections: General Information, System Summary, Getting Started, Using the Program, and Reporting.

The system summary provides the general overview and layout of the system. This section will include information on both hardware and software requirements.

Getting Started simply explains where to download the maze solver and how to install it onto your computer.

The Using the Program section will underline a detailed the description of the programs functions.

The reporting section will describe a detailed explanation of what the user will be seeing as the run the program. 

# **System Summary**

## **2.0 System Summary**
The system summary provides the general overview and layout of the system. This section will include information on both hardware and software requirements.

## **2.1 System Configuration**
Maze Solver will work on computers with 32/64-bit Windows 7, 8, or 10 with Intel Core™ i3, i5, or i7. The computer must have Java™ Runtime Environment 1.5 (32 bit) or 1.6 and it will require at least 2 GB of DDR3/4 RAM. The display of the computer must be set at 1920 x 1080 to fully display all buttons in the program. Maze Solver is not required to be connected to the internet.

## **2.2 User Access Levels**
Anyone that downloads the program will be able to use it if their computer meets the specifications required to run the program.

# **Getting Started**

## **3.0 Getting Started**
Getting Started simply explains where to download the maze solver and how to install it onto your computer.

## **3.1 Download and Installation**
To download the program, simply just click on the link provided. Here are the steps to installing the program:

   1.	Download the file maze_test.jar from the provided download link.
   2.	Double click on the file to open when it is installed.

## **3.2 Program Layout**
If you have downloaded and installed the program correctly, the layout in image below (Figure 1) is what you should see.

![] (https://imgur.com/53rO5RY.png)

If all the buttons in the program are not displayed, go to the display setting and set your screen resolution to 1920 x 1080.

# **Using the Program**

## **4.0 Using the Program**
The Using the Program section will underline a detailed the description of the programs functions.

## **4.1 Buttons**
*   Create Maze – This button allows the user to create any maze. This is the first button the user should click. 

![] (https://imgur.com/IfEHjI9.png)

*   Search Algorithms – This section of the program layout consists of all the different algorithms the user can select from to search the maze. There are 5 algorithms to select from. 

![] (https://imgur.com/JQRXNz0.png)

*   Draw Path – This button allows the user to choose the speed (in ms) at which the robot searches the maze. The speed can be adjusted by the user as shown below. 

![] (https://imgur.com/OtjDp85.png)

*   Real Time (CPU Time) – The user can click this button to search the maze in real time. After this button has been clicked, to search again the user must click the Create Maze button or the Clear button. 

![] (https://imgur.com/9iPgYHB.png)

*   Clear – The user must clear the maze before they can begin to search it again. 

![] (https://imgur.com/5puhMTr.png)

*   Back Button – After the robot has finished searching the maze, some information will be displayed below on the algorithm they chose (described in the Reporting section of the User Manual). The user can click this button to go back to the main menu. 

![] (https://imgur.com/8zwnOVU.png)

# Reporting

## **5.0 Reporting**
The reporting section will describe a detailed explanation of what the user will be seeing as the run the program.

## **5.1 Detailed Explanation of the output**
After the robot has finished searching the maze, the image below (Figure 2) will be outputted to the user.

![] (https://imgur.com/LCggluK.png)

*   The node that it is red is the root and is also the robots initial position in the maze.
*   The green node is the goal.
*   The path highlighted in yellow is the shortest path to the goal.
*   The magenta coloured squares represent the closed set (visited nodes).
*   The green squares represent the nodes in the Open set(to be searched).

The user will also see a short description of the algorithm they have chosen, as well as the total number of nodes expanded and the number of squares taken by the shortest path.

![] (https://imgur.com/z9lMUhn.png)

