
object MainClass {
	var canvas = Canvas.create(0, 0)

	def main(args: Array[String]): Unit = {

		/**
			* Loop through the user input from StdIn. The user can exit by entering q
			*/
		while (true) {
			val command = scala.io.StdIn.readLine().split(" ")
			if (command.length < 1) commandNotValid()
			else {
				try {
					command(0).toLowerCase match {
						case "c" =>
							if (command.length != 3) println("C requires 2 args")
							else {
								canvas = Canvas.create(command(1).toInt, command(2).toInt)
								println(canvas)
							}

						case "l" =>
							if (command.length != 5) println("L requires 4 args")
							else {
								canvas = canvas.drawLine(command(1).toInt, command(2).toInt, command(3).toInt, command(4).toInt)
								println(canvas)
							}

						case "r" =>
							if (command.length != 5) println("R requires 4 args")
							else {
								canvas = canvas.drawRectangle(command(1).toInt, command(2).toInt, command(3).toInt, command(4).toInt)
								println(canvas)
							}

						case "b" =>
							if (command.length != 4) println("B requires 4 args")
							else {
								canvas = canvas.drawFill(command(1).toInt, command(2).toInt, command(3)(0))
								println(canvas)
							}

						case "q" => System.exit(0)
						case _ => commandNotValid()
					}
				}
				catch {
					case n: NumberFormatException => println(s"Bad number format for ${n.getMessage}")
					case e: Exception => println(s"Error occurred: ${e.getMessage}")
				}
			}
		}
	}

	private def commandNotValid() = println("Please enter a valid command")

}

