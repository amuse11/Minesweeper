How the game was made:

The minefield is displayed using a pane. 
The game essentially consists of a grid
of the size minefield length by minefield width

The data structure has been defined as Tile
that extends StackPane 

Each tile will have an x and y value 
and a boolean has passed to signify if it
has a bomb or not

For the graphics, a rectangle has been created #
which is the border for the tile of and it will
be tileSize - 2

border stroke has been set as 1 and the border colour
is light grey

A text object to display is created 

The text will be set to "X" if there is a bomb
otherwise leave blank

Grid has been declared which is a two dimensional array 
of the number of mines horizontally by the number of mines vertically

Tiles have been populated and will have x value and a y value 
with a probability of whether it has a mine of less than 20%

getNeighbours class has been created of type List

points contains all dx and dy values relative to the tile like so:
|||
|X|
|||

Add all these points as neighbours 

When a tile is clicked, it opens from the closed state 
(all tiles are in a closed state by default), 
the text is visible the borders fill with colour and 
value displays the number of bombs surround the tile



If the player clicks on a mine, the explosion sound is played,
gameOverAlert alert box shows and gives
the user the option to play again or end the program

When the program executes, music will be played and the player
has the option to implement a timer

How to play:

Music shall be played when you start the game, a timer alert box will
show, you must decide if you wish to implement a timer or not

If you do wish to implement a timer, please enter the seconds
you wish the countdown to run for in the console and close 
the timer alert box by pressing the X close button 
or whatever your OS' close button is

Otherwise, close the timer alert box anyway and begin the game

If you step on a mine, an explosion sound will play as you've lost the
game and you'll be given an option to play again or end the game

Have fun!

