# pong
Exercise for the course TDT4240 at NTNU.
## Introduction exercise task 4 - create Pong
The game should work fairly well with correctly sized textures. The rightmost paddle acts on the first input, and the leftmost on the second. When there are no second (or first for that matter), the leftmost paddle moves up and down to mimic a very stupid AI. If no one scores for 15 seconds the speed is multiplied with 1.5. This happens at every 15 seconds until someone scores, then the speed is reset. The game keeps score, and quits when one of the players reach the score of 21. Otherwise the program should fit the task description. 

## Exercise 2
Step 1 - We chose the pong program.

Step 2 - Implemented the Score class a a singleton.

Step 3 - Implemented the Pong and Paddle classes as observers to the Score class, and the Paddle and Ball class to be used as Drawable objects, making use of the abstract factory pattern.

### 4a)
*Architectural patterns:*
<ul>
  <li>Model-view-controller</li>
  <li>Pipe and Filter</li>
  <li>Entity component system</li>
</ul>

*Design patterns:*
<ul>
  <li>Abstract factory pattern</li>
  <li>Observer pattern</li>
  <li>Template method</li>
  <li>State pattern</li>
 </ul>

Both have similarities in that they are patterns, a generalized way of solving problems between for objects in a specified context. They differ in scope, and in level of abstraction. Architectural patterns are patterns for the structure of the whole application, at the highest level, while design patterns are more localized, pertaining to how to design specific parts/problems within an application. 

### 4b)
*Observer pattern:*
Classes implementing the ScoreObserver interface are interested in knowing when a goal is scored. Score calls .goal() on its observers when appropriate. Pong(game class) is interested in goals, so that it doesn’t have to check for score updates of the game. Paddle objects are interested because they are given a random y position when someone scores (as long as the players have let go of the paddle).


Abstract Factory pattern:
Drawable is implemented as an abstract factory. The Ball and Paddle class have different implementations of methods they override from Drawable, like update() or stepOnce(). The Pong class doesn’t care about those differences, and treats them all as Drawables after they have been initiated.

We’re a bit unsure how well this pattern is implemented, as the ball relies on the paddles. It “knows” that the paddles are paddles, not only drawables. The Pong class however only initializes them, and afterwards treats them only as drawables.



### 4c)
Observer pattern: Objects interested in when goals occur don’t need to check the score themselves. This saves resources.

Abstract factory pattern: The game can look at the game parts as just drawable objects - it doesn't need to keep track of what is what. It can then run a set of methods on all its drawables in one go, instead of having to do them separately.
