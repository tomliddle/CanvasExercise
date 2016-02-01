

class Canvas(width: Int, height: Int) {

	private val charArray: Array[Array[Char]] = Array.fill[Char](width, height){' '}

	def drawLine(x1: Int, y1: Int, x2: Int, y2: Int) = {
		for (x <- x1 to x2; y <- y1 to y2) {
			charArray(x)(y) = 'X'
		}
	}

	def drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) = {
		drawLine(x1, y1, x2, y1)
		drawLine(x1, y2, x2, y2)
		drawLine(x1, y1, x1, y2)
		drawLine(x2, y1, x2, y2)
	}

}

object MainClass {

	var canvas = new Canvas(0, 0)


	def main(args: Array[String]): Unit = {

		while (true) {
			val command: Array[String] = scala.io.StdIn.readLine().split(" ")
			if (command.length < 1) commandNotValid()

			command(0) match {
				case "C" => canvas = new Canvas(6, 6)
				case "L" => canvas.drawLine(1, 3, 1, 3)
				case "R" => canvas.drawRectangle(1, 5, 1, 5)
				case "B" => 
				case "Q" => System.exit(0)
				case _ => commandNotValid()
			}
		}
	}


	private def commandNotValid() = println("Please enter a valid command")

}

