
Springer test

Canvas is an immutable class containing the canvas logic such as draw line/rectangle etc.
Immutability was used for good functional programming practice. It may have been more efficient to
	use a mutable object but a small loss of performance was considered negligible in this instance
	against better extensibility and design.
Canvas class has a static creation function to allow a width and height to be specified. Case class
	was used to allow comparison of canvases.
Use of require allows input for each method to be validated.
drawRectangle uses drawLine in a DRY style.
drawFill uses a 4 way stack based recursive flood fill (https://en.wikipedia.org/wiki/Flood_fill#The_algorithm)

MainClass handles user input and does some minor validation on the input such as number of arguments or type.
Exceptions are caught from the canvas class if there are problems with the input logic. They are then relayed
	back to the user.


