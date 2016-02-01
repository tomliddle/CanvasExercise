
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
						Seq.fill[Char](6)('-'),
						Seq('|', 'X', ' ', ' ', ' ', '|'),
						Seq('|', ' ', ' ', ' ', ' ', '|'),
						Seq('|', ' ', ' ', ' ', ' ', '|'),
						Seq('|', ' ', ' ', ' ', ' ', '|'),
						Seq.fill[Char](6)('-'))

				canvas.drawLine(1, 1, 1, 1) should equal(Canvas(array))
			}

		}
	}
}