import scala.collection.mutable

object Canvas {
	def create(width: Int, height: Int): Canvas = {
		require(width >= 0 && height >= 0, "only positive numbers are supported for canvas creation")

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
		require(x1 == x2 || y1 == y2, "only straight lines are supported")
		require(x1 <= charSeq.head.length, s"$x1 > ${charSeq.head.length}")
		require(x2 <= charSeq.head.length, s"$x2 > ${charSeq.head.length}")
		require(y1 <= charSeq.length, s"$x2 > ${charSeq.length}")
		require(y2 <= charSeq.length, s"$x2 > ${charSeq.length}")
		require(x1 > 0 && x2 > 0 && y1 > 0 && y2 > 0, "only numbers above 0 are supported")

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
		require(x <= charSeq.head.length && y <= charSeq.length, "")
		require(x > 0 && y > 0, "only numbers above 0 are supported")

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

	override def toString: String = {
		charSeq.indices.map {
			y => charSeq(y).mkString(" ") + "\n"
		}.mkString
	}

}