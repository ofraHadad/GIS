#GIS

In this project (gradually) a complex system is opened that enables the collection of geographic information and production of insights
From this information and display the information in each live.
Look at the Waze app, it produces
Many know, and Tee
Map Row
OpenStreenMap, Mon-G, and OpenSignal all enable the collection of diverse geographic information in approaches
Other information: Phone reception, browsing, location, speed, mapping, and other information
User up. Note that there are many types of "geographic apps" some of which collect information about
12 formal desire these, p. On "Collecting physical measures, pulse, speed, etc.", and even applications
For collecting information of a vehicle computer such as a torque


Packman game:
The user will have a menu that will allow him to upload a "game" file, save a game file, and run the
The game. After running, the running result can be saved as a KML file with time signatures to enable
Google Earth is comfortable.
After calculating the motion path algorithm for each Pacman, a motion simulation can be run
The pecmans are on the board until all the fruits are eaten. The simulation will display the panel update in "real time". That is
That in each real second we see a second progression of the Pacmans (according to their speed and location data).
Try to think of an effective algorithm for realizing the motion of the Packmans, note that the height represents the height above the surface
the ground. The game map is attached to this task.

Map:
A class that represents a map that contains a map image file and all the parameters required for its adaptation to a global coordinate system. The class should allow conversion of the pixel to the image and vice versa. In addition, the division needs GPS coordinates from a global representation (such as to allow calculation of studies in meters between two "pixels in an image") and the angle of their sons.

Packman: A class that represents a "robot" with a location, orientation and ability to move.

Fruit: A class that represents a "target" in a known geographical location (without movement).

game: A class that includes a collection of fruit and a collection of robots, the department has the ability to be built
From the csv file, see the metadata format, and save its information to such a file.

• Path: A track made up of a set of GPS points
Need.

• ShortestPathAlgo: A class that receives a game
The optimal route (very short) so that all fruits will "eat" as quickly as possible - that is
The main algorithmic department includes the calculation of "fruit tracks" for each
From the clerks. Information on algorithms can be found in the following link. The purpose of the algorithm
Is to bring to a minimum the amount of time it takes for all the Pecmans together to "eat" all the fruits -
The current score at each time point of each Pacman is the sum of the weights of all the fruit that he ate
So far, the general score at any point in time is that sum.

• MyFrame: A graphic class that allows robots and fruits to be displayed on the map,
The activity of algorithms, data retention, and the recovery of data from .csv or .csv files
Creating a game by selecting robots and fruits and placing them on the map
Of the display should be in "real time" meaning that the pakmans needs to move at their own pace
- For this purpose, a process should be used to enable the correct movement of the actans on the board
Depending on time.

• Path2KML: A class that allows you to keep track of the movement of a robot (or robots) while in a file
KML, the track should be kept in a way that will allow it to run on GoogleEarth and see the
The movement of robots and the "eating" of fruits.

• You must write a detailed explanation of the game in your github documentation
The system, the algorithms for calculating routes for each Pacman, how to run and the game, how to save
And upload information, a main department diagram (including interfaces).
