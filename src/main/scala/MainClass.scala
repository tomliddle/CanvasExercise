

object MainClass {
	def main(args: Array[String]): Unit = {

		while (true) {
			val command: Array[String] = scala.io.StdIn.readLine().split(" ")
			if (command.length < 1) commandNotValid()

			command(0) match {
				case "C" => createCanvas(5, 3)
				case "L" =>
				case "R" =>
				case "B" =>
				case "Q" => System.exit(0)
				case _ => commandNotValid()
			}
		}
	}


	private def commandNotValid() = println("Please enter a valid command")

	private def createCanvas(width: Int, height: Int): Unit = {

	}
}

