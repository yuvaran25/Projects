# Group_3
Telepresence Robot

Ubiquitous Computing project: Tabletop telepresence robot for Exam Proctoring Requirements:

LeJOS NXJ 0.9.1 Beta 3
NXT Fantom driver 120
Eclipse 32 bit
Eclipse LeJOS NXJ Plugin
USB Cable
Bluetooth
Java 8 32 bit
Requirements 3 and seven doesn't work on the 64-bit counterparts.

Instructions to Install:

Install Eclipse and JDK 8 32 Bit version.
Install LeJOS NXJ in windows and then install LeJOS NXJ plugin for eclipse. (Note down the path of installation dir)
Extract the <Telep.zip> file on your desktop and Import both the projects (PCController and Lejos) in the file to your eclipse environment.
Go to Window->Preferences->LeJOSNXJ and set the path of installation dir as noted down in step 2.
Click on the leJOS NXJ menu in the Eclipse and select "Upload firmware".
Connect the Programmable brick through USB cable with your computer.
Click on the "Flash leJOS Firmware" and flash the firmware. This uploads the firmware into the Lego Programmable brick.
Open the Lejos project in eclipse. Go to src>org.sankar>Rightclick on Movement.java> run as "LeJOS NXT program". (This compiles the Java source code and uploads the bytecode to programmable brick.)
Check the console and note down the Bluetooth serial number which starts with Tele P Bot 00...
Open the PCController project in eclipse> src> org.sankar> NXTremoteControl_TA.java> line number 221 and enter your bluetooth serial number in "btspp://"
Rightclick on NXTremoteControl_TA.java and run as > Java Application. This will open the control panel to control the robot.
Click connect on the control panel. Check your console for the message "NXT is connected."

Developer Instructions:

GitHub link for the: https://github.com/sivasankarreddyy/Group_3

Class files in Lejos Project There are a total of 5 .java class files. a. Movement.java

b. Receiver.java (Taken from source1 and modified according to Modification01)

c. ObstacleDetector.java

d. DataExchange.java

e. DataReciever.java

Class files in PCController Project There will be a file called NXTRemoteControl_TA.java This was taken from the source1.

Modification 01: Reciever.java was taken from source1 and modified according to our requirements. Changes are made in the following lines:

Line no 75 to Line no 77: These modifications was made to adopt three parallel operations (Movement, Input from controller and Sensing)

Line no 101 to Line no 104: Functionalities to move the robot forward and stop condition was added using touch function.

Line no 119 to Line no 121: Functionalities to move the robot left and stop condition was added using touch function.

Line no 133 to Line no 135: Functionalities to move the robot right and stop condition was added using touch function.

Sources: [1]. https://lejos.sourceforge.io/forum/viewtopic.php?t=1723



