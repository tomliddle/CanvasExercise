import scala.collection.mutable

object Canvas {
	def create(width: Int, height: Int): Canvas = {
		val xBorder = Seq.fill[Char](width + 2){'-'}
		val xLine = '|' +:  Seq.fill[Char](width)(' ') :+ '|'

		Canvas(xBorder +: Seq.fill[Seq[Char]](height)(xLine) :+ xBorder)
	}
}

/**
	* 2D sequence of chars representing a canvas
	* @param charSeq
	*/
case class Canvas(charSeq: Seq[Seq[Char]])  {

	/**
		* Returns a canvas with a line drawn between the specified points
		*/
	def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Canvas = {
		assert(x1 == x2 || y1 == y2)

		var a = charSeq
		for (x <- x1 to x2; y <- y1 to y2) {
			a = a.updated(y, a(y).updated(x, 'X'))
		}
		Canvas(a)
	}

	/**
		* Returns a canvas with a rectangle drawn between the specified points
		*/
	def drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int): Canvas = {
		drawLine(x1, y1, x2, y1)
		.drawLine(x1, y2, x2, y2)
		.drawLine(x1, y1, x1, y2)
		.drawLine(x2, y1, x2, y2)
	}

	/**
		* Does a 4 way stack based recursive flood fill (https://en.wikipedia.org/wiki/Flood_fill#The_algorithm)
		*/
	def drawFill(x: Int, y: Int, char: Char): Canvas = {

		def floodFill(x: Int, y: Int, matrix: mutable.Buffer[mutable.Buffer[Char]], target: Char, replacement: Char): Unit = {
			val char = matrix(y)(x)

			if (target == replacement) return
			else if (char != target) return
			matrix(y)(x) = replacement
			floodFill(x, y-1, matrix, target, replacement)
			floodFill(x, y+1, matrix, target, replacement)
			floodFill(x-1, y, matrix, target, replacement)
			floodFill(x+1, y, matrix, target, replacement)
		}

		// To keep our immutibility but simplify the floodFill algorithm we convert to a 2d buffer and back.
		val buffer = charSeq.map(_.toBuffer).toBuffer
		floodFill(x, y, buffer, charSeq(y)(x), char)
		Canvas(buffer.map(_.toSeq).toSeq)
	}



	def getStringOutput: String = {
		charSeq.indices.map {
			y => charSeq(y).mkString(" ") + "\n"
		}.mkString
	}

}

object MainClass {
	var canvas = Canvas.create(0, 0)

	def main(args: Array[String]): Unit = {

		while (true) {
			val command: Array[String] = scala.io.StdIn.readLine().split(" ")
			if (command.length < 1) commandNotValid()
			else {

				command(0) match {
					case "c" =>
						if (command.length != 3) println("C requires 2 args")
						else {
							canvas = Canvas.create(command(1).toInt, command(2).toInt)
							println(canvas.getStringOutput)
						}

					case "l" =>
						if (command.length != 5) println("L requires 4 args")
						else {
							canvas = canvas.drawLine(command(1).toInt, command(2).toInt, command(3).toInt, command(4).toInt)
							println(canvas.getStringOutput)
						}

					case "r" =>
						if (command.length != 5) println("R requires 4 args")
						else {
							canvas = canvas.drawRectangle(command(1).toInt, command(2).toInt, command(3).toInt, command(4).toInt)
							println(canvas.getStringOutput)
						}

					case "b" =>
						if (command.length != 4) println("B requires 4 args")
						else {
							canvas = canvas.drawFill(command(1).toInt, command(2).toInt, command(3)(0))
							println(canvas.getStringOutput)
						}

					case "q" => System.exit(0)
					case _ => commandNotValid()
				}
			}
		}
	}

	private def commandNotValid() = println("Please enter a valid command")

}

