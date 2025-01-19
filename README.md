# Banker's Algorithm
###Introduction
The Banker's algorithm is a deadlock-avoidance algorithm used to maintain a system's safe state. When a user - a thread in this context - requests a set of resources, if the allocation of these resources will result in an unsafe state, the thread must wait until enough resources are released; otherwise, the requested resources are released to the user and the values of the available resources are updated accordingly. This project primarily focuses on the demonstration of this characteristic of the algorithm.

### Project Breakdown
There are three .txt files provided to initialize the corresponding matrices for the Banker's Algorithm. 

- "allocation.txt" populates the "allocation" matrix
- "maximum.txt" populates the "maximum" matrix
- "available.txt" populates the "available" matrix

The source code is contained in the BA.java file. Inspect the source code to understand the data structures at work and the overall design of the project. Lastly, the "expectedoutput.txt" file contains the expected output based on examplary commands made to the system.

### Available Commands
-  To simulate a process requesting resources, use the following convention in the terminal when prompted to "ENTER A COMMAND:"
`rq <process ID> <instances requested for each resource>`
For example, `rq 3 0 0 2 0` is a request by process ID #3 for 0 instances of the first resource type, 0 instances of the second resource type, 2 instances of the third resource type, and 0 instances of the fourth resource type. The values of the process ID and instances are gathered from the need matrix displayed in the terminal.
-  To simulate a process deallocating resources, use the following convention in the terminal when prompted to "ENTER A COMMAND:"
`dal <process ID> <instances released for each resource>`
For example, `dal 0 0 0 1 2` represents process ID #3 returning 0 instances of the first resource type, 0 instances of the second resource type, 1 instance of the third resource type, and 2 instances of the fourth resource type to the available matrix. The values of the process ID and instances are gathered from the allocation matrix displayed in the terminal.

### Debugging and Problem-Solving
Known errors/bugs for the Banker’s algorithm are encountered when the terminal prompts the user to enter a command. 
-  If the user does not follow the convention specified in the README file, or there happens to be a typo in the inputed command, for example “wer wer wer” or "rw 2 0 7 5 0" an **InputMismatchException** is thrown and the program terminates. To resolve this, ensure there are no typos when prompted for the command(s). Review the "available commands" section in the README file.
-  If the user follows the convention but the process ID is invalid, for example “rq 5 0 0 0 0” an **ArrayIndexOutofBoundsException** is thrown and the program terminates. To resolve this, review your 'process ID' and ensure it is within the permitted range. Note that each array is enumerated from 0 - 4.
