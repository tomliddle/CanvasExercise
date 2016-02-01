
object Canvas {
	def create(width: Int, height: Int): Canvas = Canvas(Seq.fill[Char](height, width){' '})
}

case class Canvas(charArray: Seq[Seq[Char]])  {

	def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Canvas = {
		var updatedCharArray = charArray
		for (x <- x1 to x2; y <- y1 to y2) {
			updatedCharArray.updated(y, updatedCharArray(y).updated(x, 'X'))
		}
		Canvas(updatedCharArray)
	}

	def drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int): Canvas = {
		drawLine(x1, y1, x2, y1)
		.drawLine(x1, y2, x2, y2)
		.drawLine(x1, y1, x1, y2)
		.drawLine(x2, y1, x2, y2)
	}


	def getStringOutput: String = {
		charArray.indices.map {
			y => charArray(y).mkString(" ")
		}.mkString(" ")
	}

}

object MainClass {

	var canvas = Canvas.create(0, 0)


	def main(args: Array[String]): Unit = {

		while (true) {
			val command: Array[String] = scala.io.StdIn.readLine().split(" ")
			if (command.length < 1) commandNotValid()

			command(0) match {
				case "C" => canvas = Canvas.create(1, 1)
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

