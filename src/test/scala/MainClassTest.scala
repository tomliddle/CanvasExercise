
import org.scalatest.BeforeAndAfterEach
import org.scalatest.Matchers
import org.scalatest.WordSpec

class MainClassTest extends WordSpec with Matchers with BeforeAndAfterEach{


	"MainClass" when {

		"Drawing a line" should {

			"draw a line of length 1" in {
				val canvas = Canvas.create(4, 4)

				val array =
					Seq(
						Seq.fill[Char](5)('-'),
						Seq('|', ' ', ' ', ' ', '|'),
						Seq('|', ' ', ' ', ' ', '|'),
						Seq('|', ' ', ' ', ' ', '|'),
						Seq('|', ' ', ' ', ' ', '|'),
						Seq.fill[Char](5)('-'))

				canvas.drawLine(0, 0, 0, 0) should equal(Canvas(array))
			}

		}
	}
}