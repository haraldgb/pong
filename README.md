# pong
Exercise for the course TDT4240 at NTNU.
## Introduction exercise task 4 - create Pong
The game should work fairly well with correctly sized textures. The rightmost paddle acts on the first input, and the leftmost on the second. When there are no second (or first for that matter), the leftmost paddle moves up and down to mimic a very stupid AI. If no one scores for 15 seconds the speed is multiplied with 1.5. This happens at every 15 seconds until someone scores, then the speed is reset. The game keeps score, and quits when one of the players reach the score of 21. Otherwise the program should fit the task description. 

## Exercise 2
Step 1 - We chose the pong program.

Step 2 - Implemented the Score class a a singleton.

Step 3 - Implemented the Pong and Paddle classes as observers to the Score class, and the Paddle and Ball class to be used as Drawable objects, making use of the abstract factory pattern.

### 4a)

### 4b)

### 4c)
