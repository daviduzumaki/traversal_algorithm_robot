David Ajayi

Ore Ibikunle

Functional specification

document

**<span style="font-variant:small-caps;">Table of Contents</span>**

1.  **Introduction**

    1.  ***Overview***…………………………………………………..3

    2.  ***Purpose***……………………………………………………3

    3.  ***Glossary of Terms***…………………………………………...3

    4.  **Scope**…………………………………………………….6

2.  **General Description**

    1.  **Product/System functions**………………………………...7

    2.  **Product Perspective**………………………………………7

    3.  **System Interfaces**…………………………………………8

        1.  **User Interfaces**………………………………………..8

        2.  **Hardware Interfaces**…………………………………..9

        3.  **Software Interfaces**……………………………………9

        4.  **Communication Interfaces**……………………………. 9

    4.  **Operating environment**…………………………………...9

    5.  **Constraints**……………………………………………….9

        1.  **Diagonal Traversal**…………………………………….9

        2.  **Graphical drawing**……………………………………..9

        3.  **Time**…………………………………………………..10

3.  **Functional Requirements**

    1.  **Grid Generation**………………………………………….10

    2.  **Maze Generation**………………………………………...11

    3.  **Addition of obstacles(maze) using a mouse**………………11

    4.  **Robot Transversal**……………………………………… 12

    5.  **Clearing the Maze**……………………………………….12

    6.  **Selection of algorithms**………………………………….13

4.  **System Architecture**……………………………………...14

    1.  **Main**……………………………………………………15

    2.  **Parser**…………………………………………………..15

    3.  **User Interface**…………………………………………..15

    4.  **Maze Solver**…………………………………………….15

5.  **High Level Design Diagram**……………………………..16

6.  **Overview of preliminary schedule**……………………..18

7.  **Appendix**……………………………………………………20

1. Introduction:

**Overview:**
-------------

The main idea of this project revolves around the various path finding
algorithms (DFS, BFS, Greedy, A\* and Dijkstra).

**Purpose:**
============

The purpose is to visualize how these algorithms work internally to
provide a better understanding for anyone who has difficulty grasping
the various algorithms.

**1.3 Glossary of Terms:**
==========================

-   **Abstract Data Type (ADT)** – an *abstract data type* (*ADT*) is a
    mathematical model for data types, where a data type is defined by
    its behaviour from the point of view of a user of the data,
    specifically in terms of possible values, possible operations on
    data of this type, and the behaviour of these operations.

-   **Algorithm** – is a well-defined procedure that allows a computer
    to solve a problem.

-   **Traversal** – is a form of graph traversal and refers to the
    process of visiting each node in a tree data structure,
    exactly once.

-   **Tree** – ***a tree*** is an abstract data type (ADT) that
    simulates a hierarchical ***tree*** structure, with a root value and
    subtrees of children with a parent node, represented as a set of
    linked nodes.

-   **Breadth-First Search (BFS)** – is an algorithm for traversing or
    searching graph data structure. It starts at the tree root and
    explores the neighbour nodes first, before moving to the next
    level neighbour.

-   **Depth-First Search (DFS)** – is an algorithm for traversing or
    searching a tree or graph data structures. One starts at the root,
    selecting some arbitrary node as the root in the case of a graph,
    and explores as far as possible along each branch
    before backtracking.

-   **Heuristic** – is used in artificial intelligence as a technique
    designed for solving a problem more quickly when classic methods are
    too slow, or for finding an approximation when classic methods fail
    to find an exact solution.

-   **Local Optimum** – is the best solution to a problem within a small
    neighbourhood of possible solutions.

-   **Greedy Algorithm** – is an algorithm that follows the
    problem-solving heuristic of making the locally optimum choice at
    each stage with the hope of finding a global optimum.

-   **A\* Algorithm** – is an algorithm that is widely used in
    pathfinding and graph traversal, the process of plotting an
    efficiently directed path between multiple points, called nodes.

-   **Dijkstra’s Algorithm** – is an algorithm for finding the shortest
    paths between nodes in a graph.

-   **Robot** – A visual object in the program that finds its way to a
    goal position.

-   **Frontier** – A frontier of nodes are maintained and the search
    strategy is what determines which node in the frontier is
    expanded next.

-   **Closed Set** – This is a data structure that remembers every
    expanded node in order to eliminate redundancy. Thus, it increases
    the speed of traversal.

-   **Open Set** – This is a data structure that determines what node to
    actually expand to next. It is essentially a priority queue.

-    **Priority Queue** – A priority queue is an [abstract data
    type](https://en.wikipedia.org/wiki/Abstract_data_type) which is
    like a
    regular [queue](https://en.wikipedia.org/wiki/Queue_(abstract_data_type)) or [stack](https://en.wikipedia.org/wiki/Stack_(abstract_data_type)) data
    structure, but where additionally each element has a "priority"
    associated with it. In a priority queue, an element with high
    priority is served before an element with low priority. If two
    elements have the same priority, they are served according to their
    order in the queue.

**1.4 Scope:**

The application to be discussed is the Maze Finder. It will graphically
demonstrate what nodes the ‘robot’ is looking at, obstacles in the way
of the robot, backtracking and once the path is found; a highlight of
the path said algorithm determined.

Furthermore, it will implement ***maze generation**.* The program will
allow for a user to generate a grid of a range of sizes, draw their own
grid in real-time, determine animation speed and generate random mazes
for use of ease.

Where backtracking is required we will include the ability to visually
represent arrows on the path to show what direction the robot visited.
Choosing how the robot searches will be a matter of clicking a button
selecting what algorithm the robot will search the maze with. It is also
vital to include the number of steps taken by the robot and the time for
the program to run.

This application will not provide a solution to how practical products
such as Google Maps work but is a fundamental representation of the
search algorithms and could be considered a learning tool.

2. General Description

**2.1 Product/System Functions**

Below is a list of the main functions of our project. This is a
preliminary list and is open for additions should we think of anything
worth adding. Each function has with it; its own parameters, which will
be discussed in more detail in section 3.

-   Maze Generation

-   Grid generation

-   Clearing a maze

-   User defined Maze generation

-   Step by step robot traversal

-   Search algorithm selection

**2.2 Product perspective**

The objective of the program is to have a user modify certain variables
(but most importantly the ‘search algorithm variable’) and run the
program to view how a robot will arrive to its goal position. This
process takes place entirely through Java.

**2.3 System Interfaces **

**2.3.1 User Interface**

The grid generated will also be a constant area.
![](https://i.imgur.com/VQQMAXR.png)


**Figure 1: What running the program may look like.**

The grid will contain a robot (highlighted in blue) and a goal position
(highlighted in green). The robot will move itself through the grid to
reach the goal position in a manner that relates to the user’s
“algorithm choice”.

Below the grid is a box of how the traversal information of the robot
may look like. Here we aim to include the number of nodes visited by the
robot.

On the top right is a box that will include features such as random maze
generation and clearing said maze. We will also aim to make it possible
for a user to draw a custom maze with a mouse by drawing on the grid
itself.

**2.3.2 Hardware Interfaces**

This product does not control any hardware components but.

**2.3.3 Software Interfaces**

As the application will be 100% java you can assume that all Java
libraries are at our disposable.

**2.3.4 Communication Interfaces**

The application does not implement communication protocols because it is
only run on one system.

**2.4 Operating environment**

The Framework will require Microsoft® Windows® 7 or higher as the base
level operating system. Alongside that, having any recent version of
Java installed will be required to successfully run the application.

**2.5 Constraints**

**2.5.1 Diagonal Traversal**

It may be obvious to us that the robot could achieve easier traversal if
it can move diagonally but will be a problem for the application if we
don’t make it assume the robot has volume and that objects adjacent to
it understand that too. So, it will be important for us to implement
that understanding.

**2.5.2 Graphical drawing**

Another constraint is the drawing of connection between predecessor and
successor nodes. This is a time-consuming process as when a new node
expands, the connections of the nodes that have already been drawn and
calculated will have to do it again to update the current display. So,
this is exponential and may take minutes to generate a result for large
mazes. However, implementing the option to turn this off will make
searching take place in seconds.

**2.5.3 Time**

It is essential we don’t become too ambitious with features that can be
added to the application as we will most definitely run out of time
rendering us unable to demonstrate a working application.

3. Functional Requirements:

***3.1 Grid Generation:***

-   **Description**

The program must be able to allow the user to generate their own grid of
a range of sizes.

-   **Criticality**

Generating the grid is critical to the application because it is needed
for the maze generation to function properly and to calculate where to
place the robot and goal position.

-   **Technical Issue**

The surface area of the application of which the grid is drawn on must
use a constant surface area for grid generation. E.g. 1000 x 1000
pixels. We will have to tackle the issue of responsive window scaling by
limiting the surface area for the grid. The grid size can only have a
certain number of rows and columns because path finding will take too
long.

-   **Dependencies with other requirements**

The generation of the grid depends on number of columns and rows that
the user defines.

***3.2 Maze Generation:***

-   **Description**

The program must be able to generate a maze on top of the grid that has
just been created, so that the robot can have a path to search on.

-   **Criticality**

Generating the maze is critical to the application because there are
places on the maze where obstacles must be placed between the cells.

-   **Technical Issue**

The maze generated must never expand greater than the size of the grid
at any position. We will tackle the issue by making sure that the user
cannot generate a maze larger than the grid.

-   **Dependencies with other requirements**

The generation of the maze depends on the size of the grid that the user
has generated.

***3.3 Addition of Obstacles (maze) Using A Mouse:***

-   **Description**

The user must be able to “paint” obstacles on the grid that was
generated.

-   **Criticality**

This feature isn’t necessary, but it provides a more interactive user
experience

-   **Technical Issue**

The obstacles added by the user must not expand greater than the size of
the grid.

-   **Dependencies with other requirements**

The addition of obstacles depends on capturing mouse input. More
specifically, this means capturing when the user clicks and holds the
left mouse button and simultaneously drags the mouse across the grid.

***3.4 Robot Traversal:***

-   **Description**

The decisions made by the robot are visually represented. (Frontier,
open set, final path decided on)

-   **Criticality**

The visualization of the robot traversal is critical to the program
because without the program’s purpose is entirely voided.

-   **Technical Issue**

Will require re drawing of graphics every time one node is expanded.
Could lead to efficiency issues.

-   **Dependencies with other requirements**

It depends on the maze generated.

***3.5 Clearing the Maze:***

-   **Description**

The user must have the ability to clear the maze once the search has
been completed or when it is still in progress.

-   **Criticality**

This is a critical part to the program because it is needed for the
program to be able to carry out other searches on different obstacles
using different algorithms or data.

-   **Technical Issue**

No issue.

-   **Dependencies with other requirements**

Clearing the maze depends on whether the search is still in progress as
it is not possible for the user to change the position of obstacles,
robot and target as well as the search algorithm while the search is in
progress.

***3.6 Selection of Algorithms:***

-   **Description**

The user can enable the robot to search the maze using different
algorithms.

-   **Criticality**

This along with robot traversal are of the upmost importance as the
application’s main purpose is to visually represent different search
algorithms.

-   **Technical Issue**

The user shouldn’t be able to select a different algorithm while a
search on the maze is in progress. The user also shouldn’t be able to
search with more than one algorithm at a time.

-   **Dependencies with other requirements**

This will depend on the what algorithm the user selects.

4. System Architecture

![](https://i.imgur.com/CwP3Mvv.png)


***Figure 2.***

**Figure 2:** Above illustrates the architecture of the product. As the
above diagram shows there are 5 distinct aspects to the architecture.
The first is the Main (responsible for producing an actual output for a
user to see), then the parser (which handles input from a user changing
the settings), the algorithm component (which deals with all the search
algorithms), the user interface (which allows the program to be used
easily) and the Maze Solver (which is the same as the main but without
producing an output).

**4.1 Main**

The main component of the application is responsible for initializing
the program so it can be viewable, runnable and interactable. This
includes defining the size of the application window and allowing the
Maze solver program to actually be visible. This will be the least
technical element of the application.

**4.2 Parser**

This is responsible for handling all of the inputs from the user. This
includes but not limited to, having the program be able to execute the
correct instruction after a user asking for a grid size 20x20, clearing
a grid when a user clicks “clear’, having the robot respond in an A\*
manner when the program is run and the A\* algorithm was clicked.

**4.3 User interface**

The user interface is the means of a user and our application
interacting. In other words, it is everything designed into the maze
solver that allows the user to interact with. This includes when the
user draws a maze to block the path of the robot, chooses the DFS
algorithm, generates a grid or maze and every other aspect we attend on
implementing.

**4.4 Maze Solver**

This can be viewed as the maze solver package in terms of Java. It’s the
namespace that organizes all the different classes and interfaces that
are in the application. It is comparable to how a computer stores
different folders under a drive. It contains all of the logic behind the
parser, algorithm and user interface component and it’s the Main’s job
to initialize this data.

5. High level design Diagram

To describe the design at a high level we found it best to do so with a
state transition diagram. This is due to the fact our system is best
described as being in one of several possible states.

The maze solver’s current state when run is a combination of three
parameters.

1.  Whether the search is in progress(searching).

2.  Whether the search has reached the end (endOfSearch).

3.  Whether the target has been found (found).

This means there are a total of eight combinations of values that can
hold the above three parameters. However, out of these eight
combinations only four of them allow for possible search states and this
is shown in the below state diagram (figure 3):


![](https://i.imgur.com/YEvcQnC.png)


**Figure 3.**

6 Overview of Preliminary Schedule

The schedule below was designed using Smartsheet. The Gannt chart
(Figure 4) shows the plan of how and when tasks are to be completed.
Each task is represented by a bar.

![](https://i.imgur.com/1kDl41H.png)


Figure 4

1.  Appendix

**Appendix 1 – Resources**

**Similar programs:**

-   <https://github.com/leonardo-ono/JavaMazeSolverTest/blob/master/src/mazesolver/View.java>

-   <https://github.com/leonardo-ono/JavaMazeSolverTest/blob/master/src/mazesolver/DepthFirst.java>

-   <https://github.com/AnasKhedr/Maze_Java_BFS-DFS>

    **Research tools:**

-   <http://toodle.computing.dcu.ie/>

-   <https://stackoverflow.com/>

-   <https://www.udemy.com/>

-   <https://www.youtube.com/>

    **Appendix 2 – References**

-   https://www.google.com

